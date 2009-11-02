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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.KnowledgeBase;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.List;
import jade.content.ContentElement;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.content.abs.AbsAgentAction;
import jade.content.abs.AbsAggregate;
import jade.content.abs.AbsConcept;
import jade.content.abs.AbsIRE;
import jade.content.abs.AbsPredicate;
import jade.content.abs.AbsPrimitive;
import jade.content.abs.AbsVariable;
import jade.content.lang.*;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import ontology.CBR.Adapt;
import ontology.CBR.AreReasonableSolutionsTo;
import ontology.CBR.AreSimilarTo;
import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.Case;
import ontology.CBR.Problem;
import ontology.CBR.Resolve;

import ontology.CBR.Retrieve;
import ontology.common.CommonTerminologyOntology;

import oracleIDGui.OracleIDGui;

@SuppressWarnings("serial")
public class InterfaceAgent extends Agent {
  // El GUI por medio del cual el usuario introduce la descripción de un espécimen
  private OracleIDGui myGui;
  private KnowledgeBase kb = null;

  private Problem currentProblem = null; // El caso actual que se está resolviendo
  private Case aNewCase = null; // Representa un nuevo caso por retenerse
  private AID requester = null; // El agente que solicita colaboración
  private AID[] identifierAgents; //Lista de agentes identificadores encontrados
  private List successfulConflictSet;
  private List failureConflictSet;

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
    
    this.setSuccessfulConflictSet(new ArrayList());
    this.setFailureConflictSet(new ArrayList());
    
    // Crea y muestra el GUI
    myGui = new OracleIDGui(this);
    
    OracleIDSystem.getInstance().setSystemGui(myGui);
    kb = OracleIDSystem.getInstance().getCommonKb();
    
    // Registra el servicio de identificación en el directorio de páginas amarillas
    DFAgentDescription dfd = new DFAgentDescription();
    dfd.setName(getAID());
    ServiceDescription sd = new ServiceDescription();
    sd.setType("species-ID");
    sd.setName("My-species-ID");
    dfd.addServices(sd);
    try {
      DFService.register(this, dfd);
    }
    catch (FIPAException fe) {
      fe.printStackTrace();
    }
    
    // Agrega comportamientos de servir solicitudes de identificación
    addBehaviour(new IdentificationRequestsServer());
    addBehaviour(new MobileRequestsServer());
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
    // Revocar suscripciÃ³n al directorio de pÃ¡ginas amarillas
    try {
      DFService.deregister(this);
    }
    catch (FIPAException fe) {
      fe.printStackTrace();
    }
  	// Cierra la GUI
  	//myGui.dispose();
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

          System.out.println(getAID().getName()+" buscando agentes para solicitarles servicio de identificacion...");
          // Actualizar la lista de agentes identificadores
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("species-ID");
          template.addServices(sd);
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            identifierAgents = new AID[result.length - 1];
            if (result.length != 1) {
                System.out.println(getAID().getName()+" encontró los siguientes agentes: ");
                
                int j = 0;
                for (int i = 0; i < result.length; ++i) {
                    if (!result[i].getName().equals(getAID())) {
                        identifierAgents[j] = result[i].getName();
                        System.out.println(identifierAgents[j++].getName());
                    }
                }                
            } else
                System.out.println(getAID().getName()+" no encontró agentes remotos identificadores...");
          }
          catch (FIPAException fe) {
            fe.printStackTrace();
          }
            
          myAgent.addBehaviour(new IdentificationPerformer());
      }
    });
  }
  
  /**
   * Invocado cuando un agente remoto urge identificar un espécimen
   */
  public void mobileIdentifySpecimen(Problem problem) {
	setCurrentProblem(problem);

	getCurrentProblem().setGoalRank(OracleIDSystem.getInstance().getIdentGoal());
	getCurrentProblem().setLeastSimilarityDegree(OracleIDSystem.getInstance().getMinSimilarityDegree());
    addBehaviour(new OneShotBehaviour() {
      public void action() {
          System.out.println(getAID().getName()+" Iniciando proceso de identificación...");

          System.out.println(getAID().getName()+" buscando agentes para solicitarles servicio de identificacion...");
          // Actualizar la lista de agentes identificadores
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("species-ID");
          template.addServices(sd);
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            identifierAgents = new AID[result.length - 1];
            if (result.length != 1) {
                System.out.println(getAID().getName()+" encontró los siguientes agentes: ");

                int j = 0;
                for (int i = 0; i < result.length; ++i) {
                    if (!result[i].getName().equals(getAID())) {
                        identifierAgents[j] = result[i].getName();
                        System.out.println(identifierAgents[j++].getName());
                    }
                }
            } else
                System.out.println(getAID().getName()+" no encontró agentes remotos identificadores...");
          }
          catch (FIPAException fe) {
            fe.printStackTrace();
          }

          myAgent.addBehaviour(new MobileIdentificationPerformer());
      }
    });
  }

	/**
	 * Este es el comportamiento usado por el agente interfaz para realizar un proceso de identificación
	 */
	private class IdentificationPerformer extends Behaviour {
	  private MessageTemplate mt; // La plantilla para recibir respuestas
	  private int step = 0; // Guarda el estado de la conversación
	  private int repliesCnt = 0; // El contador de respuestas de agentes remotos
	  
	  public void action() {
	    switch (step) {
	    case 0:
		    // Enviar el mensaje al agente recuperador de posibles soluciones
		    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
	        msg.addReceiver(OracleIDSystem.getInstance().getRetrieverAID());
	        for (int i = 0; i < identifierAgents.length; ++i)
		    	msg.addReceiver(identifierAgents[i]);
	        
	        getSuccessfulConflictSet().clear();
	        getFailureConflictSet().clear();
		    
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
	            	  
		    			if (reply.getSender().equals(OracleIDSystem.getInstance().getRetrieverAID()))
		    				System.out.println(getAID().getName()+" ha recibido las hipótesis de soluciones posibles del agente local...");
		    			else 
		    				System.out.println(getAID().getName()+" ha recibido las hipótesis de soluciones posibles de un agente remoto...");
		    			
				        if (!areSimilarTo.getSuccessfulConflictSet().isEmpty()) {
				        	for (int i = 0; i < areSimilarTo.getSuccessfulConflictSet().size(); ++i)
				        		getSuccessfulConflictSet().add(areSimilarTo.getSuccessfulConflictSet().get(i));
				        }
				        
				        if (!areSimilarTo.getFailureConflictSet().isEmpty()) {
				        	for (int i = 0; i < areSimilarTo.getFailureConflictSet().size(); ++i)
				        		getFailureConflictSet().add(areSimilarTo.getFailureConflictSet().get(i));
				        }
		    		}
		    		
		    		//Se toma en cuenta la respuesta del agente local
		    		repliesCnt++;
			        if (repliesCnt > identifierAgents.length) {
			        	if (!getSuccessfulConflictSet().isEmpty() 
				        		|| !getFailureConflictSet().isEmpty()) {
					        // Enviar el mensaje a agente razonador para evaluar las posibles soluciones
						    msg = new ACLMessage(ACLMessage.REQUEST);
					        msg.addReceiver(OracleIDSystem.getInstance().getReasonerAID());
						    
					        msg.setLanguage(codec.getName());
					        msg.setOntology(ontology.getName());
					        msg.setConversationId("species-id"+System.currentTimeMillis());
					        msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
					
					        Adapt adapt = new Adapt();
					        adapt.setFailureConflictSet(getFailureConflictSet());
					        adapt.setSuccessfulConflictSet(getSuccessfulConflictSet());
					        adapt.setTo(getCurrentProblem());
					        
					        Action action = new Action();
					        action.setAction(adapt);
					        action.setActor(OracleIDSystem.getInstance().getReasonerAID());
					          
					        // Convertir objetos Java a cadena
					        getContentManager().fillContent(msg, action);
					        send(msg);
					        System.out.println(getAID().getName()+" solicitando la adaptación de las hipotesis al problema...");
					        
					        // Se han recibido todas las respuestas
				        	step = 2;
				        	repliesCnt = 0;
				        } else {
				        	JOptionPane.showMessageDialog(null, "No se han encontrado hipotesis de soluciones posibles...\n\n" +
				        			"Intente especificar más su descripción", "OracleID", JOptionPane.INFORMATION_MESSAGE);
				        	step = 3;
				        	repliesCnt = 0;
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
					        System.out.println(getAID().getName()+" presentando las soluciones propuestas...");
					        
					        myGui.setProposedSolutions(areReasonableSolutionsTo.getProposedSolutions());
					        
					        Runnable addIt = new Runnable() {
					        	public void run() {
					        		myGui.presentFirstSolution();
					        	}
					        };
					        
					        SwingUtilities.invokeLater(addIt);
				        } else {
				        	JOptionPane.showMessageDialog(null, "No se han encontrado propuestas de soluciones posibles...\n\n" +
				        			"Intente especificar más su descripción", "OracleID", JOptionPane.INFORMATION_MESSAGE);
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
	
	/**
	 * Usado para realizar un proceso de identificación solicitado por un agente remoto
	 */
	private class MobileIdentificationPerformer extends Behaviour {
	  private MessageTemplate mt; // La plantilla para recibir respuestas
	  private int step = 0; // Guarda el estado de la conversación
	  private int repliesCnt = 0; // El contador de respuestas de agentes remotos

	  public void action() {
	    switch (step) {
	    case 0:
		    // Enviar el mensaje al agente recuperador de posibles soluciones
                OracleIDSystem.getInstance().setInteractive(false);

		    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
	        msg.addReceiver(OracleIDSystem.getInstance().getRetrieverAID());
	        for (int i = 0; i < identifierAgents.length; ++i)
		    	msg.addReceiver(identifierAgents[i]);

	        getSuccessfulConflictSet().clear();
	        getFailureConflictSet().clear();

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

		    			if (reply.getSender().equals(OracleIDSystem.getInstance().getRetrieverAID()))
		    				System.out.println(getAID().getName()+" ha recibido las hipótesis de soluciones posibles del agente local...");
		    			else
		    				System.out.println(getAID().getName()+" ha recibido las hipótesis de soluciones posibles de un agente remoto...");

				        if (!areSimilarTo.getSuccessfulConflictSet().isEmpty()) {
				        	for (int i = 0; i < areSimilarTo.getSuccessfulConflictSet().size(); ++i)
				        		getSuccessfulConflictSet().add(areSimilarTo.getSuccessfulConflictSet().get(i));
				        }

				        if (!areSimilarTo.getFailureConflictSet().isEmpty()) {
				        	for (int i = 0; i < areSimilarTo.getFailureConflictSet().size(); ++i)
				        		getFailureConflictSet().add(areSimilarTo.getFailureConflictSet().get(i));
				        }
		    		}

		    		//Se toma en cuenta la respuesta del agente local
		    		repliesCnt++;
			        if (repliesCnt > identifierAgents.length) {
			        	if (!getSuccessfulConflictSet().isEmpty()
				        		|| !getFailureConflictSet().isEmpty()) {
					        // Enviar el mensaje a agente razonador para evaluar las posibles soluciones
						    msg = new ACLMessage(ACLMessage.REQUEST);
					        msg.addReceiver(OracleIDSystem.getInstance().getReasonerAID());

					        msg.setLanguage(codec.getName());
					        msg.setOntology(ontology.getName());
					        msg.setConversationId("species-id"+System.currentTimeMillis());
					        msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único

					        Adapt adapt = new Adapt();
					        adapt.setFailureConflictSet(getFailureConflictSet());
					        adapt.setSuccessfulConflictSet(getSuccessfulConflictSet());
					        adapt.setTo(getCurrentProblem());

					        Action action = new Action();
					        action.setAction(adapt);
					        action.setActor(OracleIDSystem.getInstance().getReasonerAID());

					        // Convertir objetos Java a cadena
					        getContentManager().fillContent(msg, action);
					        send(msg);
                                                OracleIDSystem.getInstance().setInteractive(true);

					        System.out.println(getAID().getName()+" enviando soluciones propuestas...");

					        // Se han recibido todas las respuestas
				        	step = 2;
				        	repliesCnt = 0;
				        } else {

                            ACLMessage myMsg = new ACLMessage(ACLMessage.INFORM);
                            myMsg.addReceiver(getRequester());

                            myMsg.setLanguage(codec.getName());
                            myMsg.setOntology(ontology.getName());
                            myMsg.setConversationId("species-id"+System.currentTimeMillis());
                            myMsg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único

                            AbsPredicate ap = new AbsPredicate(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO);

                            //Envia una lista de posibles soluciones vacía
                            ap.set(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO_PROBLEM, ontology.fromObject(getCurrentProblem()));
                            ap.set(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO_PROPOSEDSOLUTIONS, ontology.fromObject(new ArrayList()));

                            getContentManager().fillContent(myMsg, ap);
                            send(myMsg);
                            System.out.println(getAID().getName()+" enviando la información solicitada al agente movil...");
                            step = 3;
				        	repliesCnt = 0;
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

                        // Enviar respuestas!
                        msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(getRequester());

                        msg.setLanguage(codec.getName());
                        msg.setOntology(ontology.getName());
                        msg.setConversationId("species-id"+System.currentTimeMillis());
                        msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único

                        AbsPredicate ap = new AbsPredicate(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO);

                        ap.set(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO_PROBLEM, ontology.fromObject(getCurrentProblem()));
                        ap.set(CBRTerminologyOntology.AREREASONABLESOLUTIONSTO_PROPOSEDSOLUTIONS, ontology.fromObject(areReasonableSolutionsTo.getProposedSolutions()));

                        // Convertir objetos Java a cadena
                        getContentManager().fillContent(msg, ap);
                        send(msg);
                        System.out.println(getAID().getName()+" enviando la información solicitada al agente remoto del móvil...");
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
			  OracleIDSystem.getInstance().setInteractive(true);
		  }

		  return (step == 3);
	  }
	}  // Fin de la clase interna MobileIdentificationPerformer
	
  /**
   * Invocado por el GUI cuando el usuario acepta una solucion propuesta
   */
  public void learnCase(Case aNewCase) {
	setANewCase(aNewCase);
	
    addBehaviour(new OneShotBehaviour() {
      public void action() {
          System.out.println(getAID().getName()+" iniciando proceso de aprendizaje...");
            
       // Enviar el mensaje al agente aprendíz
	    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(OracleIDSystem.getInstance().getLearnerAID());
	    
        try {
          msg.setLanguage(codec.getName());
          msg.setOntology(ontology.getName());
          msg.setConversationId("species-id"+System.currentTimeMillis());
          msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
          
          AbsAgentAction aaa = new AbsAgentAction(CBRTerminologyOntology.RETAIN);
          AbsConcept ac = new AbsConcept(CBRTerminologyOntology.CASE);
          ac.set(CBRTerminologyOntology.CASE_PROBLEM, ontology.fromObject(getANewCase().getProblem()));
          
          AbsConcept sac = new AbsConcept(CBRTerminologyOntology.SOLUTION);
          AbsConcept jac = new AbsConcept(CommonTerminologyOntology.DESCRIPTION);
          jac.set(CommonTerminologyOntology.DESCRIPTION_DESCRIPTORS, ontology.fromObject(getANewCase()
        		  .getSolution().getJustification().getDescriptors()));
          sac.set(CBRTerminologyOntology.SOLUTION_JUSTIFICATION, jac);
          sac.set(CBRTerminologyOntology.SOLUTION_NAME, ontology.fromObject(getANewCase()
        		  .getSolution().getTaxonName()));
          sac.set(CBRTerminologyOntology.SOLUTION_RANK, ontology.fromObject(getANewCase()
        		  .getSolution().getTaxonLevel()));
          
          ac.set(CBRTerminologyOntology.CASE_SOLUTION, ontology.fromObject(sac));
          ac.set(CBRTerminologyOntology.CASE_STATE, ontology.fromObject(getANewCase().getState()));
          
          aaa.set(CBRTerminologyOntology.CASE, ac);
          
          Action action = new Action();
          action.setAction(aaa);
          action.setActor(OracleIDSystem.getInstance().getLearnerAID());
          
          // Convertir objetos Java a cadena
          getContentManager().fillContent(msg, action);
          send(msg);
          System.out.println(getAID().getName()+" ha informado al agente aprendíz del caso... ");
        }
        catch (CodecException ce) {
          ce.printStackTrace();
        }
        catch (OntologyException oe) {
          oe.printStackTrace();
        }
      }
    });
  }
  
  /**
   * La clase interna IdentificationRequestsServer.
   */
	private class IdentificationRequestsServer extends CyclicBehaviour {
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
                       setRequester(msg.getSender());
                       if (((Action) ce).getAction() instanceof Retrieve){
                    	   Retrieve ret = (Retrieve) ((Action) ce).getAction();
                    	   setCurrentProblem(ret.getSimilarTo());
                    	   myAgent.addBehaviour(new RetrievingRequestsPerformer());
                       }else if (((Action) ce).getAction() instanceof Resolve){
                           Resolve resolve = (Resolve) ((Action) ce).getAction();
                           mobileIdentifySpecimen(resolve.getProblem());
                       }
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
	 } // Fin de la clase interna IdentificationRequestsServer
	
	
	/**
	 * Este es el comportamiento usado por el agente interfaz para realizar un proceso de identificación
	 */
	private class RetrievingRequestsPerformer extends Behaviour {
	  private MessageTemplate mt; // La plantilla para recibir respuestas
	  private int step = 0; // Guarda el estado de la conversación
	  
	  public void action() {
	    switch (step) {
	    case 0:
	    	//Desactivar la interacción con el usuario
	    	OracleIDSystem.getInstance().setInteractive(false);
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
	          System.out.println(getAID().getName()+" procesando solicitud de casos similares... ");
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
	            	  
				        System.out.println(getAID().getName()+" ha recibido las hipótesis de soluciones posibles...");
				          
				        // Enviar el mensaje a agente que solicitó las posibles soluciones
					    msg = new ACLMessage(ACLMessage.INFORM);
				        msg.addReceiver(getRequester());
					    
				        msg.setLanguage(codec.getName());
				        msg.setOntology(ontology.getName());
				        msg.setConversationId("species-id"+System.currentTimeMillis());
				        msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único
				          
				        AbsPredicate ap = new AbsPredicate(CBRTerminologyOntology.ARESIMILARTO);
                        
				        //OJO: modificar para filtrar taxones
                        ap.set(CBRTerminologyOntology.ARESIMILARTO_FAILURECONFLICTSET, ontology.fromObject(areSimilarTo.getFailureConflictSet()));
                        ap.set(CBRTerminologyOntology.ARESIMILARTO_SUCCESSFULCONFLICTSET, ontology.fromObject(areSimilarTo.getSuccessfulConflictSet()));
                        ap.set(CBRTerminologyOntology.ARESIMILARTO_PROBLEM, ontology.fromObject(areSimilarTo.getProblem()));
                        
                        // Convertir objetos Java a cadena
          	          	getContentManager().fillContent(msg, ap);
				        send(msg);
				        System.out.println(getAID().getName()+" enviando la información solicitada al agente remoto...");
				        step = 2;
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
		  if (step == 2) {
			  System.out.println(getAID().getName()+" ha terminado de procesar solicitud de casos...");
			//Activar la interacción con el usuario
		    OracleIDSystem.getInstance().setInteractive(true);
		  }
		  
		  return (step == 2);
	  }
	}  // Fin de la clase interna RetrievingRequestsPerformer
	
//--------------------- MOBILE -------------------------------------------------
    public String toUTF8(String myString){
        // Create the encoder and decoder for ISO-8859-1
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        String s = null;
        try {
            // Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
            // The new ByteBuffer is ready to be read.
            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(myString));

            // Convert ISO-LATIN-1 bytes in a ByteBuffer to a character ByteBuffer and then to a string.
            // The new ByteBuffer is ready to be read.
            CharBuffer cbuf = decoder.decode(bbuf);
            s = cbuf.toString();
        } catch (CharacterCodingException e) {
        }
        return s;
    }
    
    //Se encarga de pasar la información de estructuras, atributos y estados al agente remoto del móvil
    private class MobileRequestsServer extends CyclicBehaviour {
        @SuppressWarnings("unchecked")
		@Override
        public void action() {

        MessageTemplate mt = MessageTemplate.and(MessageTemplate.and(
        MessageTemplate.MatchLanguage(codec.getName()),
        MessageTemplate.MatchOntology(ontology.getName())),
        MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF),
        MessageTemplate.MatchPerformative(ACLMessage.QUERY_REF)));

        ACLMessage msg = receive(mt);

        if (msg != null) {
            try {
                if (msg.getPerformative() == ACLMessage.QUERY_REF) {
                    AbsIRE ace = null;
                    // Convertir la cadena a descriptores abstractos
                    ace = (AbsIRE) getContentManager().extractAbsContent(msg);
                    if (ace.getTypeName().equals(SLVocabulary.ALL)) {
                        AbsPredicate absPredicate =  (AbsPredicate) ace.getProposition();

                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM_REF);
                        AbsPredicate equals = new AbsPredicate(SLVocabulary.EQUALS);
                        equals.set(SLVocabulary.EQUALS_LEFT, ace);

                        AbsAggregate myList = new AbsAggregate (BasicOntology.SEQUENCE);
                        if (absPredicate.getTypeName().equals("IsDescriptiveElement")){
                            Cls cls = (Cls) kb.getCls("Structure");
                            Iterator j =     cls.getDirectInstances().iterator();
                            while (j.hasNext()) {
                                Instance instance = (Instance) j.next();
                                AbsPrimitive absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                absPrimitive.set(toUTF8((String)instance.getOwnSlotValue(kb.getSlot("term"))));
                                myList.add(absPrimitive);

                                absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                absPrimitive.set(toUTF8(instance.getName()));
                                myList.add(absPrimitive);
                            }
                            cls = (Cls) kb.getCls("EnvironmentalCategory");
                            j = cls.getDirectInstances().iterator();
                            while (j.hasNext()) {
                                Instance instance = (Instance) j.next();
                                AbsPrimitive absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                absPrimitive.set(toUTF8((String)instance.getOwnSlotValue(kb.getSlot("term"))));
                                myList.add(absPrimitive);

                                absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                absPrimitive.set(toUTF8(instance.getName()));
                                myList.add(absPrimitive);
                            }
                        } else if (absPredicate.getTypeName().equals("Owns")){

                            String strt = ((AbsVariable)absPredicate.getAbsTerm(CommonTerminologyOntology.OWNS_ATTRIBUTE)).getName();

                            System.out.println("Estructura recibida: "+strt);

                            Instance structureInstance = kb.getInstance(strt);
                            if (structureInstance != null) {
                                java.util.List<Instance> tempInstanceValues =
                                new java.util.ArrayList<Instance>(structureInstance.getOwnSlotValues(kb.getSlot( "owns" )));
                                if (tempInstanceValues != null) {
                                    for (Instance instValue : tempInstanceValues) {
                                        AbsPrimitive absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                        absPrimitive.set(toUTF8((String)instValue.getOwnSlotValue(kb.getSlot("term"))));
                                        myList.add(absPrimitive);

                                        absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                        absPrimitive.set(toUTF8(instValue.getName()));
                                        myList.add(absPrimitive);
                                    }
                                }
                            }
                        } else if (absPredicate.getTypeName().equals("DescribedBy")){
                            String attr = ((AbsVariable)absPredicate.getAbsTerm(CommonTerminologyOntology.DESCRIBEDBY_ATTRIBUTE)).getName();

                            System.out.println("Atributo recibida: "+attr);

                            Instance attributeInstance = kb.getInstance(attr);

                            if (attributeInstance != null) {
                                java.util.List<Instance> tempInstanceValues =
                                new java.util.ArrayList<Instance>(attributeInstance.getOwnSlotValues(kb.getSlot("describedBy")));

                                if (tempInstanceValues != null) {
                                    for (Instance instValue : tempInstanceValues) {
                                        if (instValue.hasType(kb.getCls("State") )) {
                                            AbsPrimitive absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                            absPrimitive.set(toUTF8((String)instValue.getOwnSlotValue(kb.getSlot("term"))));
                                            myList.add(absPrimitive);

                                            absPrimitive = new AbsPrimitive(BasicOntology.STRING);
                                            absPrimitive.set(toUTF8(instValue.getName()));
                                            myList.add(absPrimitive);
                                        }
                                    }
                                }
                            }
                        }

                        equals.set(SLVocabulary.EQUALS_RIGHT, myList);

                        getContentManager().fillContent(reply, equals);

                        send(reply);

                        System.out.println(getAID().getName()+" ha enviado al movil.");
                    }
                }
            } catch (CodecException ce) {
            ce.printStackTrace();
            } catch (OntologyException oe) {
            oe.printStackTrace();
            }
        } else {
        	block();
        }
       }
    } // Fin de la clase interna MobileRequestsServer
//--------------------- END MOBILE ---------------------------------------------

	public Problem getCurrentProblem() {
		return currentProblem;
	}

	public void setCurrentProblem(Problem currentProblem) {
		this.currentProblem = currentProblem;
	}

	public void setANewCase(Case aNewCase) {
		this.aNewCase = aNewCase;
	}

	public Case getANewCase() {
		return aNewCase;
	}

	public AID getRequester() {
		return requester;
	}

	public void setRequester(AID requester) {
		this.requester = requester;
	}

	/**
	 * @see "Método failStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	private List getFailureConflictSet() {
		return failureConflictSet;
	}
	
	/**
	 * @see "Método succStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	private List getSuccessfulConflictSet() {
		return successfulConflictSet;
	}
	
	/**
	 * Método de instancia agregado
	 * @param succStructConflictSet
	 */
	private void setSuccessfulConflictSet(List succStructConflictSet) {
		this.successfulConflictSet = succStructConflictSet;
	}
	
	/**
	 * Método de instancia agregado
	 * @param failStructConflictSet
	 */
	private void setFailureConflictSet(List failStructConflictSet) {
		this.failureConflictSet = failStructConflictSet;
	}
}
