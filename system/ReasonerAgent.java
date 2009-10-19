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

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.List;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.content.abs.AbsPredicate;
import jade.content.lang.*;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import ontology.CBR.Adapt;
import ontology.CBR.AreEvaluatedSolutionsTo;
import ontology.CBR.AreSelectedSolutionsTo;
import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.Evaluate;
import ontology.CBR.Problem;
import ontology.CBR.Select;

@SuppressWarnings("serial")
public class ReasonerAgent extends Agent {
  //Se registra el lenguaje de contenido y la ontología
  private Codec codec = new SLCodec();
  private Ontology ontology = CBRTerminologyOntology.getInstance();
  private List proposedSolutions;
  private Problem problem;
  private List successfulConflictSet;
  private List failureConflictSet;
  private AID replyToAgent;

  // Incialización del agente
	@Override
  protected void setup() {
    // Imprimir un mensaje de bienvenida
	System.out.println("¡Hola! Agente razonador "+getAID().getName()+" listo.");

    getContentManager().registerLanguage(codec);
    getContentManager().registerOntology(ontology);
    
    this.setProposedSolutions(new ArrayList());
    
    // Agrega el comportamiento de servir solicitudes de identificación
    addBehaviour(new AdaptationRequestsServer());
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
    // Imprimir un mensaje de despedida
    System.out.println("¡Que tenga buen día! Agente razonador "+getAID().getName()+" fuera de servicio.");
  }
	/**
	 * La clase interna AdaptationRequestsServer.
	 */
	private class AdaptationRequestsServer extends CyclicBehaviour {
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
                    	Adapt adapt = (Adapt) ((Action)ce).getAction();
                    	
                    	getProposedSolutions().clear();
                    	
                    	setSuccessfulConflictSet(adapt.getSuccessfulConflictSet());
                    	setFailureConflictSet(adapt.getFailureConflictSet());
                    	setProblem(adapt.getTo());
                    	
                    	replyToAgent = msg.getSender();
                    	myAgent.addBehaviour(new AdaptationPerformer());                                       	                       
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
	 } // Fin de la clase interna AdaptationRequestsServer
	
	/**
	 * Este es el comportamiento usado por el agente interfaz para realizar un proceso de identificación
	 */
	private class AdaptationPerformer extends Behaviour {
	  private MessageTemplate mt; // La plantilla para recibir respuestas
	  private int step = 0; // Guarda el estado de la conversación
	  
	  public void action() {
	    switch (step) {
	    case 0:
		    // Enviar el mensaje al agente recuperador de posibles soluciones
		    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
	        msg.addReceiver(OracleIDSystem.getInstance().getEvaluatorAID());
		    
	        try {
	          msg.setLanguage(codec.getName());
	          msg.setOntology(ontology.getName());
	          msg.setConversationId("species-id"+System.currentTimeMillis());
	          msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
	
	          Evaluate eval = new Evaluate();
	          eval.setSuccessfulConflictSet(getSuccessfulConflictSet());
	          eval.setFailureConflictSet(getFailureConflictSet());
	          eval.setTo(getProblem());
	          
	          Action action = new Action();
	          action.setAction(eval);
	          action.setActor(OracleIDSystem.getInstance().getEvaluatorAID());
	          
	          // Convertir objetos Java a cadena
	          getContentManager().fillContent(msg, action);
	          send(msg);
	          System.out.println(getAID().getName()+" solicitando evaluación de soluciones posibles... ");
	        }
	        catch (CodecException ce) {
	          ce.printStackTrace();
	        }
	        catch (OntologyException oe) {
	          oe.printStackTrace();
	        }
	      
	        step = 1;
	        
	        break;
	    case 1:
	    	// Preparar plantilla para recibir el mensaje
	    	mt = MessageTemplate.and(MessageTemplate.and(
			MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(ontology.getName())),
			MessageTemplate.MatchPerformative(ACLMessage.INFORM));

	    	// Recibir todas las aceptaciones de servicio
	    	ACLMessage reply = myAgent.blockingReceive(mt);
	    	if (reply != null) {
	    		try {
		    		// Respuesta recibida
		    		ContentElement ce = null;
	              
		    		// Convertir la cadena a objetos Java
		    		ce = getContentManager().extractContent(reply);
	
		    		if (ce instanceof AreEvaluatedSolutionsTo) {
		    			AreEvaluatedSolutionsTo areEST = (AreEvaluatedSolutionsTo) ce;
	            	  
				        System.out.println(getAID().getName()+" ha recibido las soluciones posibles evaluadas...");
				          
				        if (!areEST.getSuccessfulConflictSet().isEmpty() 
				        		|| !areEST.getFailureConflictSet().isEmpty()) {
					        // Enviar el mensaje a agente razonador para evaluar las posibles soluciones
						    msg = new ACLMessage(ACLMessage.REQUEST);
					        msg.addReceiver(OracleIDSystem.getInstance().getSelectorAID());
					        msg.addReplyTo(msg.getSender());
						    
					        msg.setLanguage(codec.getName());
					        msg.setOntology(ontology.getName());
					        msg.setConversationId("species-id"+System.currentTimeMillis());
					        msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
					
					        Select select = new Select();
					        select.setFailureConflictSet(areEST.getFailureConflictSet());
					        select.setSuccessfulConflictSet(areEST.getSuccessfulConflictSet());
					        select.setTo(areEST.getTo());
					        
					        Action action = new Action();
					        action.setAction(select);
					        action.setActor(OracleIDSystem.getInstance().getSelectorAID());
					          
					        // Convertir objetos Java a cadena
					        getContentManager().fillContent(msg, action);
					        send(msg);
					        System.out.println(getAID().getName()+" seleccionando las hipotesis más razonables al problema...");
					        step = 2;
				        } else {
				        	JOptionPane.showMessageDialog(null, "No se han encontrado hipotesis de soluciones posibles...\n\n" +
				        			"Intente especificar más su descripción", "OracleID", JOptionPane.INFORMATION_MESSAGE);
				        	step = 3;
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
              
	    	break;
	    case 2:
	    	// Preparar plantilla para recibir el mensaje
	    	mt = MessageTemplate.and(MessageTemplate.and(
	    	MessageTemplate.MatchLanguage(codec.getName()),
	    	MessageTemplate.MatchOntology(ontology.getName())),
	    	MessageTemplate.MatchPerformative(ACLMessage.INFORM));

	    	// Recibir todos los casos devueltos
	    	reply = myAgent.blockingReceive(mt);
	    	if (reply != null) {
	    		try {
		    		// Respuesta recibida
		    		ContentElement ce = null;
	              
		    		// Convertir la cadena a objetos Java
		    		ce = getContentManager().extractContent(reply);
	
		    		if (ce instanceof AreSelectedSolutionsTo) {
		    			AreSelectedSolutionsTo areSST = (AreSelectedSolutionsTo) ce;
		    			
		    			setProposedSolutions(areSST.getProposedSolutions());
	            	  
				        System.out.println(getAID().getName()+"ha recibido las soluciones propuestas seleccionadas...");
				        
				        ACLMessage sendingMsg = reply.createReply();
				        sendingMsg.clearAllReceiver();
				        sendingMsg.addReceiver(replyToAgent);
				        sendingMsg.setPerformative(ACLMessage.INFORM);
                        
                        AbsPredicate ap = new AbsPredicate(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO);
                        
                        ap.set(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO_PROPOSEDSOLUTIONS, ontology.fromObject(getProposedSolutions()));
                        ap.set(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO_PROBLEM, ontology.fromObject(getProblem()));
                        
                        // Convertir objetos Java a cadena
          	          	getContentManager().fillContent(sendingMsg, ap);

                        myAgent.send(sendingMsg);

                        System.out.println(getAID().getName()+" ha enviado respuesta de consulta sobre posibles soluciones.");
				        
				        step = 3;
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
	  }

	  public boolean done() {
		  if (step == 3) {
			  System.out.println(getAID().getName()+" ha terminado de adaptar las posibles soluciones...");
		  }
		  
		  return (step == 3);
	  }
	}  // Fin de la clase interna IdentificationPerformer

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
}
