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
import javax.swing.SwingUtilities;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.content.ContentElement;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.content.lang.*;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import ontology.CBR.Adapt;
import ontology.CBR.AreReasonableSolutionsTo;
import ontology.CBR.AreSimilarTo;
import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.Problem;
import ontology.CBR.Retrieve;

import oracleIDGui.OracleIDGui;

@SuppressWarnings("serial")
public class InterfaceAgent extends Agent {
  // El GUI por medio del cual el usuario introduce la descripción de un espécimen
  private OracleIDGui myGui;

  private Problem currentProblem = null; // El caso actual que se está resolviendo

  //Se registra el lenguaje de contenido y la ontología
  private Codec codec = new SLCodec();
  private Ontology ontology = CBRTerminologyOntology.getInstance();

  // Inicialización del agente
	@Override
  protected void setup() {
    // Imprimir un mensaje de bienvenida
	System.out.println("¡Hola! Sistema de identificación "+getAID().getName()+" listo.");

	getContentManager().registerLanguage(codec);
    getContentManager().registerOntology(ontology);
    
    // Crea y muestra el GUI
    myGui = new OracleIDGui(this);
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
  	// Cierra la GUI
  	myGui.dispose();
    // Imprimir un mensaje de despedida
    System.out.println("¡Que tenga buen día! Sistema de identificación "+getAID().getName()+" abortado.");
  }

  /**
   * Invocado por el GUI cuando el usuario urge identificar el espécimen
   */
  public void identifySpecimen(Problem problem) {
	setCurrentProblem(problem);
	
	getCurrentProblem().setGoalRank(OracleIDSystem.getInstance().getIdentGoal());
	getCurrentProblem().setLeastSimilarityDegree(OracleIDSystem.getInstance().getMinSimilarityDegree());
	
    addBehaviour(new OneShotBehaviour() {
      public void action() {
          System.out.println(getAID().getName()+" Iniciando proceso de identificación...");
            
          myAgent.addBehaviour(new IdentificationPerformer());
      }
    });
  }

	/**
	 * Este es el comportamiento usado por el agente interfaz para realizar un proceso de identificación
	 */
	private class IdentificationPerformer extends Behaviour {
	  private MessageTemplate mt; // La plantilla para recibir respuestas
	  private int step = 0; // Guarda el estado de la conversación
	  
	  public void action() {
	    switch (step) {
	    case 0:
		    // Enviar el mensaje al agente recuperador de posibles soluciones
		    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
	        msg.addReceiver(OracleIDSystem.getInstance().getRetrieverAID());
		    
	        try {
	          msg.setLanguage(codec.getName());
	          msg.setOntology(ontology.getName());
	          msg.setConversationId("species-id"+System.currentTimeMillis());
	          msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
	
	          Retrieve ret = new Retrieve();
	          ret.setSimilarTo(getCurrentProblem());
	          
	          Action action = new Action();
	          action.setAction(ret);
	          action.setActor(OracleIDSystem.getInstance().getRetrieverAID());
	          
	          // Convertir objetos Java a cadena
	          getContentManager().fillContent(msg, action);
	          send(msg);
	          System.out.println(getAID().getName()+" solicitando casos similares o soluciones posibles... ");
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
	
		    		if (ce instanceof AreSimilarTo) {
		    			AreSimilarTo areSimilarTo = (AreSimilarTo) ce;
	            	  
				        System.out.println(getAID().getName()+"se han recibido las hipótesis de soluciones posibles...");
				          
				        if (!areSimilarTo.getSuccessfulConflictSet().isEmpty() 
				        		|| !areSimilarTo.getFailureConflictSet().isEmpty()) {
					        // Enviar el mensaje a agente razonador para evaluar las posibles soluciones
						    msg = new ACLMessage(ACLMessage.REQUEST);
					        msg.addReceiver(OracleIDSystem.getInstance().getReasonerAID());
						    
					        msg.setLanguage(codec.getName());
					        msg.setOntology(ontology.getName());
					        msg.setConversationId("species-id"+System.currentTimeMillis());
					        msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
					
					        Adapt adapt = new Adapt();
					        adapt.setFailureConflictSet(areSimilarTo.getFailureConflictSet());
					        adapt.setSuccessfulConflictSet(areSimilarTo.getSuccessfulConflictSet());
					        adapt.setTo(getCurrentProblem());
					          
					        // Convertir objetos Java a cadena
					        getContentManager().fillContent(msg, adapt);
					        send(msg);
					        System.out.println(getAID().getName()+" solicitando la adaptación de las hipotesis al problema...");
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
	
		    		if (ce instanceof AreReasonableSolutionsTo) {
		    			AreReasonableSolutionsTo areReasonableSolutionsTo = (AreReasonableSolutionsTo) ce;
	            	  
				        System.out.println(getAID().getName()+"ha recibido las soluciones propuestas...");
				          
				        if (!areReasonableSolutionsTo.getProposedSolutions().isEmpty()) {					        
					        System.out.println(getAID().getName()+" presentado las soluciones propuestas...");
					        
					        myGui.setProposedSolutions(areReasonableSolutionsTo.getProposedSolutions());
					        
					        Runnable addIt = new Runnable() {
					        	public void run() {
					        		myGui.presentFirstSolution();
					        	}
					        };
					        
					        SwingUtilities.invokeLater(addIt);
				        } 
				        
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
			  System.out.println(getAID().getName()+" ha terminado proceso de identificación...");
		  }
		  
		  return (step == 3);
	  }
	}  // Fin de la clase interna IdentificationPerformer

	public Problem getCurrentProblem() {
		return currentProblem;
	}

	public void setCurrentProblem(Problem currentProblem) {
		this.currentProblem = currentProblem;
	}
}
