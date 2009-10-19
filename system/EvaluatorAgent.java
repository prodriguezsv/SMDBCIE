/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package system;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.Iterator;
import jade.util.leap.List;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.content.abs.AbsPredicate;
import jade.content.lang.*;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.Evaluate;
import ontology.CBR.Hypothesis;
import ontology.CBR.PossibleSolution;
import ontology.CBR.Problem;
import ontology.common.HeuristicDescriptor;
import ontology.taxonomy.Taxon;

@SuppressWarnings("serial")
public class EvaluatorAgent extends Agent {
  //Se registra el lenguaje de contenido y la ontologÃ­a
  private Codec codec = new SLCodec();
  private Ontology ontology = CBRTerminologyOntology.getInstance();
  private Problem problem;
  private List successfulConflictSet;
  private List failureConflictSet;

  // Incialización del agente
	@Override
  protected void setup() {
    // Imprimir un mensaje de bienvenida
	System.out.println("¡Hola! Agente evaluador "+getAID().getName()+" listo.");

    getContentManager().registerLanguage(codec);
    getContentManager().registerOntology(ontology);
    
    // Agrega el comportamiento de servir solicitudes de identificación
    addBehaviour(new EvaluationRequestsServer());
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
    // Imprimir un mensaje de despedida
    System.out.println("¡Que tenga buen día! Agente evaluador "+getAID().getName()+" fuera de servicio.");
  }

	/**
	   La clase interna IdentificationRequestsServer.
	 */
	private class EvaluationRequestsServer extends CyclicBehaviour {
	  public void action() {

		// Preparar plantilla para recibir el mensaje
        MessageTemplate mt = MessageTemplate.and(MessageTemplate.and(
            MessageTemplate.MatchLanguage(codec.getName()),
            MessageTemplate.MatchOntology(ontology.getName())),
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

        ACLMessage msg = receive(mt);

        if (msg != null) {
        	try {
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    ContentElement ce = null;
                    // Convertir la cadena a objetos Java
                    ce = getContentManager().extractContent(msg);
                    if (ce instanceof Action) {
                    	Evaluate eval = (Evaluate) ((Action) ce).getAction();
                    	
                    	setSuccessfulConflictSet(eval.getSuccessfulConflictSet());
                    	setFailureConflictSet(eval.getFailureConflictSet());
                    	setProblem(eval.getTo());
                    	
                    	evaluate();
                    	
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        
                        AbsPredicate ap = new AbsPredicate(CBRTerminologyOntology.AREEVALUATEDSOLUTIONSTO);
                        
                        ap.set(CBRTerminologyOntology.AREEVALUATEDSOLUTIONSTO_FAILURECONFLICTSET, ontology.fromObject(getFailureConflictSet()));
                        ap.set(CBRTerminologyOntology.AREEVALUATEDSOLUTIONSTO_SUCCESSFULCONFLICTSET, ontology.fromObject(getSuccessfulConflictSet()));
                        ap.set(CBRTerminologyOntology.AREEVALUATEDSOLUTIONSTO_TO, ontology.fromObject(getProblem()));
                        
                        // Convertir objetos Java a cadena
          	          	getContentManager().fillContent(reply, ap);

                        myAgent.send(reply);

                        System.out.println(getAID().getName()+" ha enviado posibles soluciones evaluadas.");
                    }
                }
            }
            catch (CodecException ce) {
                ce.printStackTrace();
            }
            catch (OntologyException oe) {
                oe.printStackTrace();
            }
        } else {
		    block();
		}
       }
	  } // Fin de la clase interna LearningRequestsServer
	
	private Problem getProblem() {
		return problem;
	}

	private void setProblem(Problem problem) {
		this.problem = problem;
	}

	/**
	 * Método de instancia agregado
	 * @param failStructConflictSet
	 */
	private void setFailureConflictSet(List failStructConflictSet) {
		this.failureConflictSet = failStructConflictSet;
	}

	/**
	 * @see "Método failStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	private List getFailureConflictSet() {
		return failureConflictSet;
	}
	
	/**
	 * Método de instancia agregado
	 * @param succStructConflictSet
	 */
	private void setSuccessfulConflictSet(List succStructConflictSet) {
		this.successfulConflictSet = succStructConflictSet;
	}

	/**
	 * @see "Método succStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	private List getSuccessfulConflictSet() {
		return successfulConflictSet;
	}
	
	/**
	 * @see "M&ecute;todo losePoints del protocolo de clase point-accummulatind scheme en SUKIA SmallTalk"
	 * @return
	 */
	private static String losePoints() {
		return "-";
	}
	
	/**
	 * @see "M&ecute;todo winPoints del protocolo de clase point-accummulatind scheme en SUKIA SmallTalk"
	 * @return
	 */
	private static String winPoints() {
		return "+";
	}
	
	/**
	 * Conflict set evaluation process
	 * @see "Método evaluate del protocolo evaluating en SUKIA SmallTalk"
	 */
	public void evaluate() {
		// Step 1: Evaluate each conflict set individually
		this.evaluate(getSuccessfulConflictSet(), winPoints());
		this.evaluate(getFailureConflictSet(), winPoints());

		// Step 2: Evaluate every conflict set against other conflict sets
		this.evaluate(getSuccessfulConflictSet(), getFailureConflictSet(), losePoints());
	}
	
	/**
	 * The argument aConflictSet contains a set of Hypotheses, at least one of which may contain an empty set
	 * of PossibleSolutions. The argument aCompConflictSet also contains a set of Hypotheses, at least one of
	 * which may also contain an empty set of PossibleSolutions.  All Hypotheses in either aConflictSet or 
	 * aCompConflictSet are associated to descriptive elements of the same kind (either all Structures or all
	 * GroupingHeuristics). The idea behind this method is to evaluate the PossibleSolutions of one Hypothesis,
	 * in aConflictSet, against the PossibleSolutions of all the Hypotheses in aCompConflictSet, in order to 
	 * a) reduce the total number of PossibleSolutions in aCompConflictSet (that is, in case two evaluated 
	 * PossibleSolutions refer to the same object), and 
	 * b) determine taxonomic hierarchical dependencies.  If two compared PossibleSolutions refer to the same
	 * object, the current one will inherit the compared-one's descriptions, and the compared one will be
	 * deleted from the corresponding Hypothesis. If the current PossibleSolution is successor of another one,
	 * the current one will inherit the other's descriptions.  In either case, the current PossibleSolution
	 * will  gain or lose one point, according to the criteria established by the argument aPointAccumulatingScheme.
	 * NOTE: 	The execution-time analysis of this program shows that it is rather inefficient; its many
	 * loops may cause the program to delay if the evaluated lists are large. For the time being (04-oct-1999)
	 * it's OK to leave at that, since the important issue is to test this logic and try to find ways to
	 * improve the PossibleSolution evaluation scheme.  It is convenient, though, to find a way to optimize
	 * this process as soon as the knowledge base starts having a considerable size.
	 * ASSUMPTIONS:
	 * 1. The set of PossibleSolutions of every Hypothesis has been previously compressed, by either the taxon
	 * search automaton, or the SAV case search automaton output.
	 * 2. The set of PossibleSolutions of every Hypothesisis ordered by taxonomic level, most specific level
	 * first.
	 * PRECONDITION: aConflictSet is not empty and aCompConflictSet is not empty.
	 * Returns: nil : If an error occurred (no corresponding taxon was found for a given SAVCase-based
	 * PossibleSolution; self : If aConflicSet is empty, or the process ran OK
	 * @see "M&ecute;todo evaluate:against:winOrLose: del protocolo evaluating en SUKIA SmallTalk"
	 * @param aConflictSet
	 * @param aCompConflictSet
	 * @param aPointAccumulatingScheme
	 */
	public void evaluate(List aConflictSet, List aCompConflictSet, String aPointAccumulatingScheme) {
		Taxon evalPossibleSolutionTaxon, compPossibleSolutionTaxon;

        //do nothing in case that one of them is empty
        if (aConflictSet.isEmpty() || aCompConflictSet.isEmpty()) return;        

        //Pendiente evaluar si un ordenamiento en la lista de hipotesis mejoraría el proceso
        List aConflictSetCopy = new ArrayList();
        
        Iterator iterator = aConflictSet.iterator();
		
		while (iterator.hasNext()) {
			aConflictSetCopy.add((Hypothesis) iterator.next());
		}
		
		while (!aConflictSetCopy.isEmpty()) {
			Hypothesis evalHypothesis = (Hypothesis) aConflictSetCopy.remove(0); 
        
            // Scan the entire list of possible solutions belonging to the current hypothesis-to-evaluate			
			List evalPossibleSolutionsCopy = new ArrayList();
            
            iterator = evalHypothesis.getPossibleSolutions().iterator();
			
			while (iterator.hasNext()) {
				evalPossibleSolutionsCopy.add((PossibleSolution) iterator.next());
			}
			
			while (!evalPossibleSolutionsCopy.isEmpty()) {
				PossibleSolution evalPossibleSolution = (PossibleSolution) evalPossibleSolutionsCopy.remove(0);

                // Get the corresponding taxon of the possibleSolution-to-evaluate, if applicable
                evalPossibleSolutionTaxon = OracleIDSystem.getInstance().getTaxonomy().getTaxonFromLevelIndex(evalPossibleSolution.getName(), evalPossibleSolution.getLevel());

                if (evalPossibleSolutionTaxon == null) return;

                List aCompConflictSetCopy = new ArrayList();
                
                iterator = aCompConflictSet.iterator();
    			
    			while (iterator.hasNext()) {
    				aCompConflictSetCopy.add((Hypothesis) iterator.next());
    			}
    			
    			while (!aCompConflictSetCopy.isEmpty()) {
    				Hypothesis compHypothesis = (Hypothesis) aCompConflictSetCopy.remove(0);

                	List aPossibleSolutionsCopy = new ArrayList();
                	
                    iterator = compHypothesis.getPossibleSolutions().iterator();
        			
        			while (iterator.hasNext()) {
        				aPossibleSolutionsCopy.add((PossibleSolution) iterator.next());
        			}                
        			
        			while (!aPossibleSolutionsCopy.isEmpty()) {
        				PossibleSolution compPossibleSolution = (PossibleSolution) aPossibleSolutionsCopy.remove(0);

                        compPossibleSolutionTaxon = OracleIDSystem.getInstance().getTaxonomy().getTaxonFromLevelIndex(compPossibleSolution.getName(), compPossibleSolution.getLevel());

                        if (compPossibleSolutionTaxon == null) return;
                        
                        // Check if the possible solutions are the same object
                        if (evalPossibleSolutionTaxon.equals(compPossibleSolutionTaxon)) {
                        	//OJO: verificar este discernimiento
                        	if (evalHypothesis.getDescription().getDescriptors().get(0) instanceof HeuristicDescriptor) {
                        		aPointAccumulatingScheme = losePoints();
                        	} else {
                        		if (compHypothesis.getDescription().getDescriptors().get(0) instanceof HeuristicDescriptor)
                        			aPointAccumulatingScheme = winPoints();
                        		else aPointAccumulatingScheme = losePoints();
                        	}
                        		
                            if (aPointAccumulatingScheme.equals(winPoints())) {
                                // Inherit the compare solution's descriptions and remove it from the hypothesis-to-compare possibleSolutions list
                                this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
                                evalPossibleSolution.incrementPoints();
                            } else {
                                // AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
                                evalPossibleSolution.incrementPointsBy(-1);
                            }
                            
                            compHypothesis.getPossibleSolutions().remove(compPossibleSolution);
                        } else {
                            // At this point, evalPossibleSolutionTaxon and compPossibleSolutionTaxon are different objects
                            // Determine if the possibleSolution-to-evaluate is a successor taxon of the possibleSolution-to-compare
                            if (evalPossibleSolutionTaxon.isSuccessorOf(compPossibleSolutionTaxon)) {
                            	//OJO: verificar este discernimiento
                            	if (evalHypothesis.getDescription().getDescriptors().get(0) instanceof HeuristicDescriptor) {
                            		aPointAccumulatingScheme = losePoints();
                            	} else {
                            		if (compHypothesis.getDescription().getDescriptors().get(0) instanceof HeuristicDescriptor)
                            			aPointAccumulatingScheme = winPoints();
                            		else aPointAccumulatingScheme = losePoints();
                            	}
                            	
                                if (aPointAccumulatingScheme.equals(winPoints())) {
                                    this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
                                    evalPossibleSolution.incrementPoints();
                                } else {
                                    // AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
                                    evalPossibleSolution.incrementPointsBy(-1);
                                }
                            }
                        }
                    }
                    
                    if (compHypothesis.getPossibleSolutions().isEmpty())
                    	aCompConflictSet.remove(compHypothesis);
                }
            }
        }
	}
	
	/**
	 * The argument aConflictSet contains a set of Hypotheses, each of which initially contains a non-empty set of PossibleSolutions.
	 * All Hypotheses are associated to descriptive elements of the same kind (either all Structures or all GroupingHeuristics).
	 * The idea behind this method is to evaluate the PossibleSolutions of one Hypothesis against the PossibleSolutions of all
	 * other Hypotheses, in order to a) reduce the total number of PossibleSolutions in aConflictSet (that is, in case two evaluated
	 * PossibleSolutions refer to the same object), and b) determine taxonomic hierarchical dependencies.  If two compared
	 * PossibleSolutions refer to the same object, the current one will inherit the compared-one's descriptions, and the compared
	 * one will be deleted from the corresponding Hypothesis. If the current PossibleSolution is successor of another one, the
	 * current one will inherit the other's descriptions.  In either case, the current PossibleSolution will  gain or lose one point,
	 * according to the criteria established by the argument aPointAccumulatingScheme.
	 * NOTE: 	The execution-time analysis of this program shows that it is rather inefficient; its many loops may cause the program
	 * to delay if the evaluated lists are large. For the time being (04-oct-1999) it's OK to leave at that, since the important
	 * issue is to test this logic and try to find ways to improve the PossibleSolution evaluation scheme.  It is convenient,
	 * though, to find a way to optimize this process as soon as the knowledge base starts having a considerable size.
	 * ASSUMPTIONS:
	 * 1. The set of PossibleSolutions of every Hypothesis has been previously compressed, by either the taxon search automaton,
	 * or the SAV case search automaton output.
	 * 2. The set of PossibleSolutions of every Hypothesisis ordered by taxonomic level, most specific level first.
	 * PRECONDITION: aConflictSet is not empty.
	 * POSTCONDITION: aConflictSet is not empty and at least one Hypothesis MAY contain an empty set of PossibleSolutions.
	 * Returns: nil : If an error occurred (no corresponding taxon was found for a given SAVCase-based PossibleSolution
	 * self : If aConflicSet is empty, or the process ran OK
	 * @see "Método evaluate:winOrLose: del protocolo evaluating en SUKIA SmallTalk"
	 * @param aConflictSet
	 * @param aPointAccumulatingScheme
	 */
	public void evaluate(List aConflictSet, String aPointAccumulatingScheme) {
		Taxon evalPossibleSolutionTaxon, compPossibleSolutionTaxon;
		List tempList;

        //do nothing in case that one of them is empty
        if (aConflictSet.isEmpty()) return;
        
        if (aConflictSet.size() < 2) return;

        tempList = new ArrayList();
        
        //Pendiente evaluar si un ordenamiento en la lista de hipotesis mejoraría el proceso
        while (!aConflictSet.isEmpty()) {
        	Hypothesis evalHypothesis = (Hypothesis) aConflictSet.remove(0);

            // Scan the entire list of possible solutions belonging to the current hypothesis-to-evaluate
        	Iterator i = evalHypothesis.getPossibleSolutions().iterator();
			
			while (i.hasNext()) {
				PossibleSolution evalPossibleSolution = (PossibleSolution) i.next();

                evalPossibleSolutionTaxon = OracleIDSystem.getInstance().getTaxonomy().getTaxonFromLevelIndex(evalPossibleSolution.getName(), evalPossibleSolution.getLevel());                    

                if (evalPossibleSolutionTaxon == null) return;           
                
                List aConflictSetCopy = new ArrayList();
                
                Iterator iterator = aConflictSet.iterator();
    			
    			while (iterator.hasNext()) {
    				aConflictSetCopy.add((Hypothesis) iterator.next());
    			}
                
    			Iterator k = aConflictSetCopy.iterator();
    			
    			while (k.hasNext()) {
    				Hypothesis compHypothesis = (Hypothesis) k.next();

                	List aPossibleSolutionsCopy = new ArrayList();
                	
                	iterator = compHypothesis.getPossibleSolutions().iterator();
        			
        			while (iterator.hasNext()) {
        				aPossibleSolutionsCopy.add((PossibleSolution) iterator.next());
        			}
                	
        			Iterator l = aPossibleSolutionsCopy.iterator();
        			
        			while (l.hasNext()) {
        				PossibleSolution compPossibleSolution = (PossibleSolution) l.next();
                    
                        compPossibleSolutionTaxon = OracleIDSystem.getInstance().getTaxonomy().getTaxonFromLevelIndex(compPossibleSolution.getName(), compPossibleSolution.getLevel());

                        if (compPossibleSolutionTaxon == null) return;
                        // Check if the possible solutions are the same object
                        if (evalPossibleSolutionTaxon.equals(compPossibleSolutionTaxon)) {
                        	
                            if (aPointAccumulatingScheme.equals(winPoints())) {
                                // Inherit the compare solution's descriptions and remove it from the hypothesis-to-compare possibleSolutions list
                                this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
                                evalPossibleSolution.incrementPoints();
                            } else {
                                // AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
                                evalPossibleSolution.incrementPointsBy(-1);
                            }
                            
                            compHypothesis.getPossibleSolutions().remove(compPossibleSolution);
                        } else {
                            // At this point, evalPossibleSolutionTaxon and compPossibleSolutionTaxon are different objects
                            // Determine if the possibleSolution-to-evaluate is a successor taxon of the possibleSolution-to-compare
                            if (evalPossibleSolutionTaxon.isSuccessorOf(compPossibleSolutionTaxon)) {
                            	
                                if (aPointAccumulatingScheme.equals(winPoints())) {
                                    this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
                                    evalPossibleSolution.incrementPoints();
                                } else {
                                    // AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
                                    evalPossibleSolution.incrementPointsBy(-1);
                                }
                            }
                        }
                    }
                    
                    if (compHypothesis.getPossibleSolutions().isEmpty())
                    	aConflictSet.remove(compHypothesis);
                }
            }
            
            tempList.add(evalHypothesis);
        }
        
        while (!tempList.isEmpty())
        	aConflictSet.add(tempList.remove(0));
	}
	
	/**
	 * @see "Método inheritPossibleSolutionDescriptionsFrom:to: del protocolo inheritance en SUKIA SmallTalk"
	 * @param anOldPossibleSolution
	 * @param aNewPossibleSolution
	 */
	private void inheritPossibleSolutionDescriptionsFrom(PossibleSolution anOldPossibleSolution, PossibleSolution aNewPossibleSolution) {
		aNewPossibleSolution.addAllToSolutionDescription(anOldPossibleSolution.getSolutionDescription());
		aNewPossibleSolution.addAllToConfirmedDescription(anOldPossibleSolution.getConfirmedDescription());
		aNewPossibleSolution.addAllToUnconfirmedDescription(anOldPossibleSolution.getUnconfirmedDescription());
		aNewPossibleSolution.addAllToDoubtfulDescription(anOldPossibleSolution.getDoubtfulDescription());
	}

}
