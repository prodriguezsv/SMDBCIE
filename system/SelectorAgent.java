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
import jade.util.leap.Set;
import jade.util.leap.SortedSetImpl;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.content.abs.AbsPredicate;
import jade.content.lang.*;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.CertaintyDegree;
import ontology.CBR.Hypothesis;
import ontology.CBR.PossibleSolution;
import ontology.CBR.Problem;
import ontology.CBR.ProposedSolution;
import ontology.CBR.Select;
import ontology.taxonomy.TaxonomicRank;

@SuppressWarnings("serial")
public class SelectorAgent extends Agent {
  //Se registra el lenguaje de contenido y la ontologÃ­a
  private Codec codec = new SLCodec();
  private Ontology ontology = CBRTerminologyOntology.getInstance();
  private List proposedSolutions;
  private Problem problem;
  private List successfulConflictSet;
  private List failureConflictSet;
  private List generalSolutions;
  private List goalSolutions;
  private List specificSolutions;
  private boolean status;
  private String identificationGoal;
  private int maxNumberSolutions;
  private boolean showFailedSolutions;

  // Incialización del agente
	@Override
  protected void setup() {
    // Imprimir un mensaje de bienvenida
	System.out.println("¡Hola! Agente selector "+getAID().getName()+" listo.");

    getContentManager().registerLanguage(codec);
    getContentManager().registerOntology(ontology);
    
    setGoalSolutions(new ArrayList());
	setSpecificSolutions(new ArrayList());
	setGeneralSolutions(new ArrayList());
    
    // Agrega el comportamiento de servir solicitudes de identificación
    addBehaviour(new EvaluationRequestsServer());
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
    // Imprimir un mensaje de despedida
    System.out.println("¡Que tenga buen día! Agente selector "+getAID().getName()+" fuera de servicio.");
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
                    	Select select = (Select) ((Action) ce).getAction();
                    	
                    	setSuccessfulConflictSet(select.getSuccessfulConflictSet());
                    	setFailureConflictSet(select.getFailureConflictSet());
                    	setProblem(select.getTo());
                    	initialize();
                    	
                    	setProposedSolutions(select());
                    	
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        
                        AbsPredicate ap = new AbsPredicate(CBRTerminologyOntology.ARESELECTEDSOLUTIONSTO);
                        
                        ap.set(CBRTerminologyOntology.ARESELECTEDSOLUTIONSTO_PROPOSEDSOLUTIONS, ontology.fromObject(getProposedSolutions()));         
                        ap.set(CBRTerminologyOntology.ARESELECTEDSOLUTIONSTO_TO, ontology.fromObject(getProblem()));
                        
                        // Convertir objetos Java a cadena
          	          	getContentManager().fillContent(reply, ap);

                        myAgent.send(reply);

                        System.out.println(getAID().getName()+" ha enviado posibles soluciones más razonables.");
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
	  } // Fin de la clase interna SelectionRequestsServer
	
	private void initialize() {
	  generalSolutions.clear();
	  goalSolutions.clear();
	  specificSolutions.clear();
	  status = false;
	  identificationGoal = getProblem().getGoalRank();
	  maxNumberSolutions = OracleIDSystem.getInstance().getMaxNumberSolutions();
	  showFailedSolutions = OracleIDSystem.getInstance().isPresentFailedSolutions();
	}
	
	/**
	 * @see "Método proposedSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param proposedSolutions
	 */
	private void setProposedSolutions(List proposedSolutions) {
		this.proposedSolutions = proposedSolutions;
	}

	/**
	 * @see "Método proposedSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	private List getProposedSolutions() {
		return proposedSolutions;
	}

	private Problem getProblem() {
		return problem;
	}

	private void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	/**
	 * Método de instancia agregado
	 * @param failedStructConflictSet
	 */
	public void setFailureConflictSet(List failedStructConflictSet) {
		this.failureConflictSet = failedStructConflictSet;
	}

	/**
	 * @see "Método failedStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getFailureConflictSet() {
		return failureConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param generalSolutions
	 */
	public void setGeneralSolutions(List generalSolutions) {
		this.generalSolutions = generalSolutions;
	}

	/**
	 * @see "Método generalSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getGeneralSolutions() {
		return generalSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param goalSolutions
	 */
	public void setGoalSolutions(List goalSolutions) {
		this.goalSolutions = goalSolutions;
	}

	/**
	 * @see "Método generalSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aProposedSolution
	 */
	public void addGeneralSolution(ProposedSolution aProposedSolution) {
		this.getGeneralSolutions().add(aProposedSolution);
		this.sortProposedSolutions(this.getGeneralSolutions());
	}
	
	/**
	 * @see "Método goalSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getGoalSolutions() {
		return goalSolutions;
	}
	
	/**
	 * @see "Método goalSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aProposedSolution
	 */
	public void addGoalSolution(ProposedSolution aProposedSolution) {
		this.getGoalSolutions().add(aProposedSolution);
		this.sortProposedSolutions(this.getGoalSolutions());
	}
	
	/**
	 * Mpetodo de instancia agregado
	 * @param specificSolutions
	 */
	public void setSpecificSolutions(List specificSolutions) {
		this.specificSolutions = specificSolutions;
	}

	/**
	 * @see "Método specificSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getSpecificSolutions() {
		return specificSolutions;
	}
	
	/**
	 * @see "Método specificSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aProposedSolution
	 */
	public void addSpecificSolution(ProposedSolution aProposedSolution) {
		this.getSpecificSolutions().add(aProposedSolution);
		this.sortProposedSolutions(this.getSpecificSolutions());
	}

	/**
	 * Método de instancia agregado
	 * @param successfulStructConflictSet
	 */
	public void setSuccessfulConflictSet(List successfulStructConflictSet) {
		this.successfulConflictSet = successfulStructConflictSet;
	}

	/**
	 * @see "Método successfulStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getSuccessfulConflictSet() {
		return successfulConflictSet;
	}

	/**
	 * @see "Método status: del protocolo adding en SUKIA SmallTalk"
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @see "Método status del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Método de instancia agregado
	 * @param identificationGoal
	 */
	public void setIdentificationGoal(String identificationGoal) {
		this.identificationGoal = identificationGoal;
	}

	/**
	 * @see "Método identificationGoal del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getIdentificationGoal() {
		return identificationGoal;
	}

	/**
	 * Método de instancia agregado
	 * @param maxNumberSolutions
	 */
	public void setMaxNumberSolutions(int maxNumberSolutions) {
		this.maxNumberSolutions = maxNumberSolutions;
	}

	/**
	 * @see "Método maxNumberSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getMaxNumberSolutions() {
		return maxNumberSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param showFailedSolutions
	 */
	public void setShowFailedSolutions(boolean showFailedSolutions) {
		this.showFailedSolutions = showFailedSolutions;
	}

	/**
	 * @see "Método showFailedSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isShowFailedSolutions() {
		return showFailedSolutions;
	}
	
	/**
	 * This method takes as argument a list of possible solutions, sorted by their point score. Each possible
	 * solution has a taxonomic level, which can be represented numerically. This level is compared against
	 * the [numeric representation of the] level stated in the identification goal. If the possible solution's
	 * level is less than the stated identification goal, then that possible solution is added to the general
	 * -solutions list. If the level is equal to the stated identification goal, the possible solution is
	 * added to the goal-solutions list. Finally, if the level is greater than the stated identification goal,
	 * the possible solution is added to the specific-solutions list.
	 * NOTE: Since the argument is a sorted list, then the first elements to be added to any of the solutions
	 * lists are those with the highest scores.
	 * The process will stop when either of these conditions hold:
	 * 1) The number of elements in the goal-solutions list is equal to the maximum number of solutions
	 * expected by the user.
	 * 2) The number of elements in the specific-solutions list is equal to the maximum number of solutions
	 * expected by the user.
	 * 3) The sum of the elements belonging to the goal-solutions and specific-solutions lists is greater than
	 * or equal to the maximum number of solutions expected by the user.
	 * 4) The argument aSortedPossibleSolutionsList is empty.
	 * PRECONDITION: aSortedPossibleSolutionsList is NOT empty.
	 * @see "Método distribute: del protocolo selecting solutions en SUKIA SmallTalk"
	 * Returns: self. The process ran OK.
	 * @return
	 */
	public boolean distribute(List aSortedPossibleSolutionsList) {
		ProposedSolution ps;
		int max, goalLevel, i, psLevel;
		
		// Check the precondition
		if (aSortedPossibleSolutionsList.isEmpty())
			return false;

		// Get the maximum number of elements that can be added  to any solutions list
		if (aSortedPossibleSolutionsList.size() <= this.getMaxNumberSolutions())
			max = aSortedPossibleSolutionsList.size();
		else max = this.getMaxNumberSolutions();

		// Get the identification goal as number
		goalLevel = TaxonomicRank.getIndex(TaxonomicRank.valueOf(this.getIdentificationGoal().toUpperCase()));

		i = 1;
		while (i <= max) {
			ps = new ProposedSolution();
			ps.setState(this.isStatus());
			ps.setSolution((PossibleSolution)aSortedPossibleSolutionsList.remove(0));

			if (ps.getSolution().getPoints() < 0.0) ps.setCertaintyDegree(CertaintyDegree.UNCERTAIN.getDegree());
			if (ps.getSolution().getPoints() == 0.0) ps.setCertaintyDegree(CertaintyDegree.DOUBTFUL.getDegree());
			if (ps.getSolution().getPoints() > 0.0) ps.setCertaintyDegree(CertaintyDegree.CERTAIN.getDegree());
			
			psLevel = TaxonomicRank.getIndex(TaxonomicRank.valueOf(ps.getSolution().getLevel().toUpperCase()));

			// If applicable, insert the new proposed solution to the general solutions list
			if ((psLevel < goalLevel) && (this.getGeneralSolutions().size() < this.getMaxNumberSolutions()))
				this.addGeneralSolution(ps);

			// If applicable, insert the new proposed solution to the goal solutions list
			if ((psLevel == goalLevel) && (this.getGoalSolutions().size() < this.getMaxNumberSolutions()))
				this.addGoalSolution(ps);
			
			// If applicable, insert the new proposed solution to the specific solutions list
			if ((psLevel > goalLevel) && (this.getSpecificSolutions().size() < this.getMaxNumberSolutions()))
				this.addSpecificSolution(ps);

			i = i + 1;
		}

		/* Condition to stop the process: either the number of elements in either the specific or goal 
		 solutions lists is equal to maximum expected by the user, OR the sum of both lists satisfies
		 this user expectation*/
		if ((this.getGoalSolutions().size() == this.getMaxNumberSolutions()) ||
	            (this.getSpecificSolutions().size() == this.getMaxNumberSolutions()) ||
	            ((this.getGoalSolutions().size() + this.getSpecificSolutions().size()) >= this.getMaxNumberSolutions()))
			return true;

		// Try to load some more PossibleSolutions to the solutions lists
		return this.distribute(aSortedPossibleSolutionsList);
	}
	
	/**
	 * Driver method for this class. First, it calls method sortPossibleSolutions, in order to sort the possible
	 * solutions (by point score) of all eligible hypotheses included in the conflict sets. Next, the method
	 * distribute: is called, in order to distribute all sorted-out possible solutions into their corresponding
	 * solutions lists. Finally, an attempt is made to extract, from the goal-solutions list first, the number of
	 * proposed solutions that the user is willing to see.  If that list becomes empty, the specific-solutions
	 * list is used instead. Finally, if the specific-solutions list also becomes empty, the general-solutions
	 * list is used as last resort.
	 * The solution lists are ordered by degree of certainty and by point score also. Hence, the first elements
	 * to be subtracted from each of the solutions lists are the best ones.
	 * @see "Método select del protocolo selecting solutions en SUKIA SmallTalk"
	 * Returns: an empty list, OR a list with THE BEST identification solutions.
	 * @return
	 */
	public List select() {
		List proposedSolutionsList;
		List solutions;
		int i;
		
		proposedSolutionsList = this.sortPossibleSolutions();
		solutions = new ArrayList();

		if (proposedSolutionsList.isEmpty())
			return solutions;

		this.distribute(proposedSolutionsList);

		i = 1;
		while (i <= this.getMaxNumberSolutions()) {
			if (!(this.getGoalSolutions().isEmpty()))
				solutions.add(this.getGoalSolutions().remove(0));
			else { 
				if (!(this.getSpecificSolutions().isEmpty()))
					solutions.add(this.getSpecificSolutions().remove(0));
				else {
					if (!(this.getGeneralSolutions().isEmpty()))
						solutions.add(this.getGeneralSolutions().remove(0));
					else i = this.getMaxNumberSolutions();
				}

			} 

			i = i + 1;
		}
		
		return solutions;
	}
	
	/**
	 * This method sorts possible solutions by their point scores.
	 * Returns: a [possibly empty] sorted possible solutions list.
	 * @see "Método sortPossibleSolutions del protocolo selecting solutions en SUKIA SmallTalk"
	 * @return
	 */
	public List sortPossibleSolutions() {
		List pSolutions;
		//Hypothesis hypothesis;
	
		/* All possible solutions will be sorted by their point number: those with higher scores will 
		 be at the beginning of the list*/
		pSolutions = new ArrayList();

		// FIRST CHOICE: Load all successful (i.e., positive) structure possible solutions, if any
		Iterator i = this.getSuccessfulConflictSet().iterator();
		
		while (i.hasNext()) {
			Hypothesis hypothesis = (Hypothesis) i.next(); 

			Iterator j = hypothesis.getPossibleSolutions().iterator();
			
			while (j.hasNext()) {
				PossibleSolution aPossibleSolution = (PossibleSolution) j.next();
                pSolutions.add(aPossibleSolution);
            }
        }
        
        this.sortPossibleSolutions(pSolutions);
        
		if (!(pSolutions.isEmpty())) {
			this.setStatus(true);
			return pSolutions;
		}

		/* If the user wants to see failed solutions, *in case there aren't any positive solutions*, 
		  load them in the list*/
		if (this.isShowFailedSolutions()) {
			// Load all failed (i.e., negative) structure possible solutions, if any
			i = this.getFailureConflictSet().iterator();
			
			while (i.hasNext()) {
				Hypothesis hypothesis = (Hypothesis) i.next(); 
				Iterator j = hypothesis.getPossibleSolutions().iterator();
				
				while (j.hasNext()) {
					PossibleSolution aPossibleSolution = (PossibleSolution) j.next();
                    pSolutions.add(aPossibleSolution);
                }
            }

            this.sortPossibleSolutions(pSolutions);
			if (!(pSolutions.isEmpty()))
				this.setStatus(false);
		}
		
		return pSolutions;
	}
	
	/**
	 * Método de instancia agregado
	 * @param pSolutions
	 */
	private void sortPossibleSolutions(List pSolutions) {
		java.util.List<PossibleSolution> anotherPSolutions = new java.util.ArrayList<PossibleSolution>();
		
		Iterator i = pSolutions.iterator();
		
		while (i.hasNext()) {
			anotherPSolutions.add((PossibleSolution)i.next());
		}
		
		java.util.Collections.sort(anotherPSolutions,
				new java.util.Comparator<PossibleSolution>() {
					public int compare(PossibleSolution elem1, PossibleSolution elem2) {
						if ((elem2.getPoints()	- elem1.getPoints()) > 0)
							return 1;
						else if ((elem2.getPoints() - elem1.getPoints()) < 0)
							return -1;
						else return 0;
					}
				}); //OJO buscar alternativa de ordenamiento
		
		pSolutions.clear();
		
		java.util.Iterator<PossibleSolution> j = anotherPSolutions.iterator();
		
		while (j.hasNext()) {
			pSolutions.add(j.next());
		}
	}
	
	private void sortProposedSolutions(List proposedSolutions) {
		Set proposedSolutionsSet = new SortedSetImpl();
		
		Iterator i = proposedSolutions.iterator();
		
		while (i.hasNext()) {
			proposedSolutionsSet.add(i.next());
		}
		
		proposedSolutions.clear();
		
		Iterator j = proposedSolutionsSet.iterator();
		
		while (j.hasNext()) {
			proposedSolutions.add(j.next());
		}
	}
}
