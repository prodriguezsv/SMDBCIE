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
import jade.util.leap.List;
import jade.content.onto.*;
import jade.content.lang.*;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import ontology.CBR.Adapt;
import ontology.CBR.AreReasonableSolutionsTo;
import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.Problem;

@SuppressWarnings("serial")
public class ReasonerAgent extends Agent {
  //Se registra el lenguaje de contenido y la ontología
  private Codec codec = new SLCodec();
  private Ontology ontology = CBRTerminologyOntology.getInstance();
  private List proposedSolutions;
  private Problem problem;
  private List successfulConflictSet;
  private List failureConflictSet;

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
    System.out.println("¡Qué tenga buen día! Agente razonador "+getAID().getName()+" fuera de servicio.");
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
                    if (ce instanceof Adapt) {
                    	Adapt adapt = (Adapt) ce;
                    	
                    	getProposedSolutions().clear();
                    	
                    	setSuccessfulConflictSet(adapt.getSuccessfulConflictSet());
                    	setFailureConflictSet(adapt.getSuccessfulConflictSet());
                    	setProblem(adapt.getTo());
                    	
                    	adaptHypothesis();
                    	
                    	AreReasonableSolutionsTo areReasonableSolutionsTo = new AreReasonableSolutionsTo();
                    	areReasonableSolutionsTo.setProposedSolutions(getProposedSolutions());
                    	areReasonableSolutionsTo.setProblema(adapt.getTo());
                    	
                    	// Convertir objetos Java a cadena
          	          	getContentManager().fillContent(msg, areReasonableSolutionsTo);
                    	
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);

                        myAgent.send(reply);

                        System.out.println(getAID().getName()+" ha enviado respuesta de consulta sobre posibles soluciones.");
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
	
	private void adaptHypothesis() {
		// Evaluate all possible solutions
		if (this.evaluatePossibleSolutions() == false)
			return;

		// Select the proposed solutions
		this.setProposedSolutions(this.selectSolutions());
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
	
	@SuppressWarnings("unused")
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
	 * Evaluation of all possible solutions belonging to Hypotheses stored in all conflict sets
	 * @see "Método evaluatePossibleSolutions del protocolo evaluating and selecting en SUKIA SmallTalk"
	 */
	private boolean evaluatePossibleSolutions() {
		PossibleSolutionEvaluator evaluator;
		
		evaluator = new PossibleSolutionEvaluator(this.getSuccessfulConflictSet(), this.getFailureConflictSet(),
				OracleIDSystem.getInstance().getTaxonomy());
	
		evaluator.evaluate();
		
		return true;
	}
	
	/**
	 * Selection of the best possible solutions, as proposed solutions for an identification session.
	 * @see "Método selectSolutions del protocolo evaluating and selecting en SUKIA SmallTalk"
	 */
	private List selectSolutions() {
		PossibleSolutionSelector selector;
		
		selector = new PossibleSolutionSelector(OracleIDSystem.getInstance().getIdentGoal()
				, this.getSuccessfulConflictSet(), this.getFailureConflictSet()
				, OracleIDSystem.getInstance().getMaxNumberSolutions(), OracleIDSystem.getInstance().isPresentFailedSolutions());

		return selector.select();
	}
}
