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

package speciesID;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.content.onto.*;
import jade.content.lang.*;
import jade.content.ContentElement;
import jade.content.abs.*;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import java.util.*;
import speciesidontology.*;

@SuppressWarnings("serial")
public class IdentifierAgent extends Agent {
  // La base de de casos
  @SuppressWarnings({ "unused", "unchecked" })
private Hashtable casebase;
  // El GUI por medio del cual el usuario introduce la descripción de un espécimen
  private IdentifierAgentGui myGui;

  private Caso currentCase = new Caso(); // El caso actual que se está resolviendo
  // El taxón raíz de la jerarquía taxonomica que maneja este agente
  private Taxon rootTaxon = new Taxon();
  // La lista de agentes encontrados
  private AID[] identifierAgents;

  //Se registra el lenguaje de contenido y la ontología
  private Codec codec = new SLCodec();
  private Ontology ontology = SpeciesIDOntology.getInstance();

  // Incialización del agente
    @SuppressWarnings("unchecked")
	@Override
  protected void setup() {
    // Crea la base de casos
    casebase = new Hashtable();

    rootTaxon.setCategoria("Filo");
    rootTaxon.setNombre("Moluscos");

    // Imprimir un mensaje de bienvenida
	System.out.println("Hola! agente identificador "+getAID().getName()+" esta listo.");

    // Crea y muestra el GUI
    myGui = new IdentifierAgentGui(this);
    myGui.show();

    getContentManager().registerLanguage(codec);
    getContentManager().registerOntology(ontology);

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
    
    // Agrega el comportamiento de servir solicitudes de identificación
    addBehaviour(new IdentificationRequestsServer());

    // Agrega el comportamiento de recibir información de otros agentes
    // addBehaviour(new InformationReciever());
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
    // Revocar suscripción al directorio de páginas amarillas
    try {
      DFService.deregister(this);
    }
    catch (FIPAException fe) {
      fe.printStackTrace();
    }
  	// Cierra la GUI
  	myGui.dispose();
    // Imprimir un mensaje de despedida
    System.out.println("Agente identificador "+getAID().getName()+" terminando.");
  }

  /**
     Invocado por el GUI cuando el usuario introduce un nuevo elemento descriptivo
     de un espécimen
   */
  public void updateDescription(final ElementoDescriptivo edc) {
    addBehaviour(new OneShotBehaviour() {
      public void action() {
        if (currentCase.getDescripcion() == null)
            currentCase.setDescripcion(new Descripcion());

        currentCase.getDescripcion().addElementosDescriptivos(edc);
        System.out.println(getAID().getName()+ " ha agregado elemento descriptivo "+edc.toString());
      }
    } );
  }

  /**
     Invocado por el GUI cuando el usuario urge identificar el espécimen
   */
  public void identifySpecimen() {
    addBehaviour(new OneShotBehaviour() {
      public void action() {
        if (currentCase.getDescripcion() != null) {
              System.out.println(getAID().getName()+" identificando el especimen con descripcion: "+currentCase.getDescripcion().toString());

              System.out.println(getAID().getName()+" buscando agentes para solicitarles servicio de identificacion...");
              // Actualizar la lista de agentes identificadores
              DFAgentDescription template = new DFAgentDescription();
              ServiceDescription sd = new ServiceDescription();
              sd.setType("species-ID");
              template.addServices(sd);
              try {
                DFAgentDescription[] result = DFService.search(myAgent, template);
                if (result.length != 0) {
                    System.out.println(getAID().getName()+" encontro los siguientes agentes: ");
                    identifierAgents = new AID[result.length - 1];
                    int j = 0;
                    for (int i = 0; i < result.length; ++i) {
                        if (!result[i].getName().equals(getAID())) {
                            identifierAgents[j] = result[i].getName();
                            System.out.println(identifierAgents[j++].getName());
                        }
                    }
                    
                    // Pide la colaboración de los agentes identificadoes encontrados
                    myAgent.addBehaviour(new CollaborationRequestPerformer());
                } else
                    System.out.println(getAID().getName()+" no encontro otros agentes identificadores.");
              }
              catch (FIPAException fe) {
                fe.printStackTrace();
              }
        }
      }
    });
  }

  public Caso getCurrentCase() {
     return this.currentCase;
   }


  /**
	   Este es el comportameiento usado por el agente identificador para solicitar
       a los otros agentes su colaboración
	 */
	private class CollaborationRequestPerformer extends Behaviour {
	  private int repliesCnt = 0; // El contador de respuestas del agente identificador
	  private MessageTemplate mt; // La plantilla para recibir respuestas
	  private int step = 0; // Guarda el estado de la conversación
      private int competentRepliesCnt=0, incompetentRepliesCnt=0; //clasifica agentes según competencia

	  public void action() {
	    switch (step) {
	    case 0:
	      // Enviar el mensaje a todos los agentes encontrados
	      ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
	      for (int i = 0; i < identifierAgents.length; ++i)
            msg.addReceiver(identifierAgents[i]);
          try {
            msg.setLanguage(codec.getName());
            msg.setOntology(ontology.getName());
            msg.setConversationId("species-id"+System.currentTimeMillis());
            msg.setReplyWith(getAID().getName()+System.currentTimeMillis()); // Valor único

            Identifica ident = new Identifica();
            ident.setAgente(getAID());
            ident.setTaxon(rootTaxon);

            // Convertir objetos Java a cadena
            getContentManager().fillContent(msg, ident);
            send(msg);
            System.out.println(getAID().getName()+" enviando consulta sobre taxon a los agentes encontrados... ");
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
            MessageTemplate.MatchPerformative(ACLMessage.INFORM_IF));

	      // Recibir todas las aceptaciones de servicio
	      ACLMessage reply = myAgent.blockingReceive(mt);
	      if (reply != null) {
            try {
                // Respuesta recibida
                AbsContentElement ace = null;
                
                // Convertir la cadena a objetos Java
                ace = getContentManager().extractAbsContent(reply);

                if (ace.getTypeName().equals(SpeciesIDOntology.IDENTIFICA)) {
                    competentRepliesCnt++;
                    System.out.println(getAID().getName()+" ha recibido respuesta afirmativa de "+reply.getSender().getName());
                    // Preparar el mensaje Query-REF
                    msg = reply.createReply();
                    msg.setPerformative(ACLMessage.QUERY_REF);

                    // Preparar el contenido
                    AbsConcept absDescr = (AbsConcept) ontology.fromObject(currentCase.getDescripcion());
                    AbsVariable x = new AbsVariable("x", SpeciesIDOntology.CASO);
                    AbsPredicate absTiene = new AbsPredicate(SpeciesIDOntology.TIENE);
                    absTiene.set(SpeciesIDOntology.TIENE_DESCRIPCION, absDescr);
                    absTiene.set(SpeciesIDOntology.TIENE_CASO, x);
                    AbsIRE absAll = new AbsIRE(SLVocabulary.ALL);
                    absAll.setVariable(x);
                    absAll.setProposition(absTiene);

                    // Convertir descriptores abstractos a cadena
                    getContentManager().fillContent(msg, absAll);
                    send(msg);
                    System.out.println(getAID().getName()+" ha enviado solicitud de casos.");
                } else if (ace.getTypeName().equals(SLVocabulary.NOT)) {
                    incompetentRepliesCnt++;
                    System.out.println(getAID().getName()+" ha recibido respuesta negativa de "+reply.getSender().getName());
                }
                
            }
            catch (CodecException ce) {
                ce.printStackTrace();
            }
            catch (OntologyException oe) {
                oe.printStackTrace();
            }

	        repliesCnt++;
	        if (repliesCnt >= identifierAgents.length) {
	          // Se han recibido todas las respuestas
	          step = 2;
              repliesCnt = 0;
	        }
	      }
	      else {
	        block();
	      }
	      break;
	    case 2:
          // Preparar plantilla para recibir el mensaje
          mt = MessageTemplate.and(MessageTemplate.and(
            MessageTemplate.MatchLanguage(codec.getName()),
            MessageTemplate.MatchOntology(ontology.getName())),
            MessageTemplate.MatchPerformative(ACLMessage.INFORM_REF));

	      // Recibir todos los casos devueltos
	      reply = myAgent.blockingReceive(mt);
	      if (reply != null) {
            try {
              AbsPredicate ap = null;

              // Convertir la cadena a descriptores abstractos
              ap = (AbsPredicate) getContentManager().extractAbsContent(reply);

              if (ap.getTypeName().equals(SLVocabulary.EQUALS)) {
                AbsAggregate absSet =  (AbsAggregate) ap.getAbsObject(SLVocabulary.EQUALS_RIGHT);
                if (absSet.isEmpty())
                    System.out.println("El agente "+reply.getSender().getName()+"no envio ningún caso.");
                else
                    // Procesar los casos
                    System.out.println("Los casos del agente "+reply.getSender().getName()+ " han sido recibidos.");


                repliesCnt++;
                if (repliesCnt >= competentRepliesCnt) {
                  // Se han recibido todas las respuestas
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
	      }
	      else {
	        block();
	      }
	    }
	  }

      public boolean done() {
	  	if (step == 3 || (step == 2 && identifierAgents.length == incompetentRepliesCnt)) {
	  		System.out.println(getAID().getName()+" ha terminado tarea de identificacion");
	  	}
	    return (step == 3 || (step == 2 && identifierAgents.length == incompetentRepliesCnt));
	  }
	}  // Fin de la clase interna CollaborationRequestPerformer

	/**
	   La clase interna IdentificationRequestsServer.
	 */
	private class IdentificationRequestsServer extends CyclicBehaviour {
	  public void action() {
        Random randomNumber = new Random();

		// Preparar plantilla para recibir el mensaje
        MessageTemplate mt = MessageTemplate.and(MessageTemplate.and(
            MessageTemplate.MatchLanguage(codec.getName()),
            MessageTemplate.MatchOntology(ontology.getName())),
            MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF),
            MessageTemplate.MatchPerformative(ACLMessage.QUERY_REF)));

        ACLMessage msg = receive(mt);

        if (msg != null) {
            try {
                if (msg.getPerformative() == ACLMessage.QUERY_IF) {
                    ContentElement ce = null;
                    // Convertir la cadena a objetos Java
                    ce = getContentManager().extractContent(msg);
                    if (ce instanceof Identifica) {
                        Identifica ident = (Identifica) ce;
                        //Taxon t = ident.getTaxon();

                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM_IF);

                        if (randomNumber.nextInt(2) == 0) {
                            AbsPredicate not = new AbsPredicate(SLVocabulary.NOT);
                            not.set(SLVocabulary.NOT_WHAT, ontology.fromObject(ident));
                            getContentManager().fillContent(reply, not);
                        } else
                            getContentManager().fillContent(reply, ce);

                        myAgent.send(reply);

                        System.out.println(getAID().getName()+" ha enviado respuesta de consulta sobre taxon.");
                    }
                } else if (msg.getPerformative() == ACLMessage.QUERY_REF) {
                    AbsIRE ace = null;
                    // Convertir la cadena a descriptores abstractos
                    ace = (AbsIRE) getContentManager().extractAbsContent(msg);
                    if (ace.getTypeName().equals(SLVocabulary.ALL)) {
                        AbsPredicate absTiene =  (AbsPredicate) ace.getProposition();
                        AbsConcept absDescr =  (AbsConcept) absTiene.getAbsObject(SpeciesIDOntology.TIENE_DESCRIPCION);

                        AbsAggregate absCasesSet = new AbsAggregate (BasicOntology.SET);

                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM_REF);

                        AbsPredicate equals = new AbsPredicate(SLVocabulary.EQUALS);
                        equals.set(SLVocabulary.EQUALS_LEFT, ace);

                        if (randomNumber.nextInt(2) == 0) {
                            AbsConcept absCase = new AbsConcept (SpeciesIDOntology.CASO);
                            absCase.set(SpeciesIDOntology.CASO_DESCRIPCION, absDescr);
                            absCase.set(SpeciesIDOntology.CASO_ESPECIEINFERIDA, ontology.fromObject(rootTaxon));
                            absCasesSet.add(absCase);
                            absCase = new AbsConcept (SpeciesIDOntology.CASO);
                            absCase.set(SpeciesIDOntology.CASO_DESCRIPCION, absDescr);
                            absCase.set(SpeciesIDOntology.CASO_ESPECIEINFERIDA, ontology.fromObject(rootTaxon));
                            absCasesSet.add(absCase);
                        }
                        equals.set(SLVocabulary.EQUALS_RIGHT, absCasesSet);

                        getContentManager().fillContent(reply, equals);

                        send(reply);

                        System.out.println(getAID().getName()+" ha enviado casos solicitados.");
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

}
