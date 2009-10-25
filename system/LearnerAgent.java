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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.KnowledgeBase;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.content.lang.*;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;

import ontology.CBR.CBRTerminologyOntology;
import ontology.CBR.Case;
import ontology.CBR.Retain;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SVHeuristicDescriptor;
import ontology.common.SingleValue;

@SuppressWarnings("serial")
public class LearnerAgent extends Agent {
  //Se registra el lenguaje de contenido y la ontologÃ­a
  private Codec codec = new SLCodec();
  private Ontology ontology = CBRTerminologyOntology.getInstance();

  // Incialización del agente
	@Override
  protected void setup() {
    // Imprimir un mensaje de bienvenida
	System.out.println("¡Hola! Agente aprendíz "+getAID().getName()+" listo.");

    getContentManager().registerLanguage(codec);
    getContentManager().registerOntology(ontology);
    
    // Agrega el comportamiento de servir solicitudes de identificación
    addBehaviour(new LearnigRequestsServer());
  }

  // Operaciones de limpieza del agente
    @Override
  protected void takeDown() {
    // Imprimir un mensaje de despedida
    System.out.println("¡Que tenga buen día! Agente aprendíz "+getAID().getName()+" fuera de servicio.");
  }

	/**
	   La clase interna IdentificationRequestsServer.
	 */
	private class LearnigRequestsServer extends CyclicBehaviour {
	  @SuppressWarnings("unchecked")
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
                    	Retain ret = (Retain) ((Action) ce).getAction();
                    	
                    	Case aCase = ret.getCase();

                    	KnowledgeBase kb = OracleIDSystem.getInstance().getCbrKb();
                    	                    	
                    	Instance caseInst = kb.createInstance(null, kb.getCls("Case"));
                    	                    	
                    	Instance problemInst = kb.createInstance(null, kb.getCls("Problem"));
                    	                  
                    	Instance descriptionInst = kb.createInstance(null, kb.getCls("Description"));
                    	
                    	java.util.List descriptors = new ArrayList<Descriptor>();
                    	
                    	Iterator i = aCase.getProblem().getDescription().getAllDescriptors();
                		
                		while (i.hasNext()) {
                			Descriptor d = (Descriptor) i.next();
                			Instance descriptorInst=null;
                			if (d instanceof SSCharacterDescriptor) {
                				descriptorInst = kb.createInstance(null, kb.getCls("SSCharacterDescriptor"));
                				descriptorInst.setOwnSlotValue(kb.getSlot("structure"), getStructureInstance(d.getStructure()));
                				descriptorInst.setOwnSlotValue(kb.getSlot("attribute"), getAttributeInstance(d.getAttribute()));
                				descriptorInst.setOwnSlotValue(kb.getSlot("score"), getStateInstance((String)d.getValue()));                				
                			}
                			if (d instanceof SSHeuristicDescriptor) {
                				descriptorInst = kb.createInstance(null, kb.getCls("SSHeuristicDescriptor"));
                				descriptorInst.setOwnSlotValue(kb.getSlot("structure"), getStructureInstance(d.getStructure()));
                				descriptorInst.setOwnSlotValue(kb.getSlot("attribute"), getAttributeInstance(d.getAttribute()));
                				descriptorInst.setOwnSlotValue(kb.getSlot("score"), getStateInstance((String)d.getValue()));                				
                			}
                			
                			if (d instanceof SVHeuristicDescriptor) {
                				descriptorInst = kb.createInstance(null, kb.getCls("SVHeuristicDescriptor"));
                				descriptorInst.setOwnSlotValue(kb.getSlot("structure"), getStructureInstance(d.getStructure()));
                				descriptorInst.setOwnSlotValue(kb.getSlot("attribute"), getAttributeInstance(d.getAttribute()));
                				
                				Instance singleValueInst = kb.createInstance(null, kb.getCls("SingleValue"));
                				singleValueInst.setOwnSlotValue(kb.getSlot("value"), ((SingleValue)d.getValue()).getValue());
                				singleValueInst.setOwnSlotValue(kb.getSlot("measuringUnit"), ((SingleValue)d.getValue()).getMeasuringUnit());
                				
                				descriptorInst.setOwnSlotValue(kb.getSlot("score"), singleValueInst);                				
                			}
                			
                			if (d instanceof SVCharacterDescriptor) {
                				descriptorInst = kb.createInstance(null, kb.getCls("SVCharacterDescriptor"));
                				descriptorInst.setOwnSlotValue(kb.getSlot("structure"), getStructureInstance(d.getStructure()));
                				descriptorInst.setOwnSlotValue(kb.getSlot("attribute"), getAttributeInstance(d.getAttribute()));
                				
                				Instance singleValueInst = kb.createInstance(null, kb.getCls("SingleValue"));
                				singleValueInst.setOwnSlotValue(kb.getSlot("value"), ((SingleValue)d.getValue()).getValue());
                				singleValueInst.setOwnSlotValue(kb.getSlot("measuringUnit"), ((SingleValue)d.getValue()).getMeasuringUnit());
                				
                				descriptorInst.setOwnSlotValue(kb.getSlot("score"), singleValueInst);                				
                			}

                			descriptors.add(descriptorInst);
                		}
                    	
                    	descriptionInst.setOwnSlotValues(kb.getSlot("descriptors"), descriptors);
                    	
                    	problemInst.setOwnSlotValue(kb.getSlot("description"), descriptionInst);
                    	problemInst.setOwnSlotValue(kb.getSlot("goalRank"), aCase.getProblem().getGoalRank());
                    	problemInst.setOwnSlotValue(kb.getSlot("leastSimilarityDegree"), aCase.getProblem().getLeastSimilarityDegree());
                    	
                    	caseInst.setOwnSlotValue(kb.getSlot("problem"), problemInst);
                    	
                    	Instance solutionInst = kb.createInstance(null, kb.getCls("Solution"));
                    	solutionInst.setOwnSlotValue(kb.getSlot("rank"), aCase.getSolution().getTaxonLevel());                    	                    	
                    	solutionInst.setOwnSlotValue(kb.getSlot("name"), aCase.getSolution().getTaxonName());
                    	
                    	caseInst.setOwnSlotValue(kb.getSlot("solution"), solutionInst);
                    	
                    	caseInst.setOwnSlotValue(kb.getSlot("state"), aCase.getState());
                    	
                    	Collection errors = new ArrayList();
                        OracleIDSystem.getInstance().getCBRProject().save(errors);
                        
                        if (errors.size() != 0) {
                        	Iterator<String> j = errors.iterator();
                            while (j.hasNext()) {
                            	System.out.println("Error: " + j.next());
                            }
                        } else {
                        	System.out.println(getAID().getName()+" ha retenido el caso en la base de conocimiento.");
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
	  } // Fin de la clase interna LearningRequestsServer
	
	@SuppressWarnings("unchecked")
	Instance getStructureInstance(String structure) {
		KnowledgeBase kb = OracleIDSystem.getInstance().getCbrKb();
		Cls cls = kb.getCls("Structure");
		
		Iterator i = cls.getDirectInstances().iterator();
        while (i.hasNext()) {
        	Instance instance = (Instance) i.next();
        	if (((String)instance.getDirectOwnSlotValue(kb.getSlot("term"))).equals(structure))
        		return instance;		                    
        }
        
        cls = kb.getCls("EnvironmentalCategory");
		
		Iterator j = cls.getDirectInstances().iterator();
        while (j.hasNext()) {
        	Instance instance = (Instance) j.next();
        	if (((String)instance.getDirectOwnSlotValue(kb.getSlot("term"))).equals(structure))
        		return instance;		                    
        }
        
        return null;
	}
	
	@SuppressWarnings("unchecked")
	Instance getAttributeInstance(String attribute) {
		KnowledgeBase kb = OracleIDSystem.getInstance().getCbrKb();
		Cls cls = kb.getCls("Attribute");
		
		Iterator i = cls.getDirectInstances().iterator();
        while (i.hasNext()) {
        	Instance instance = (Instance) i.next();
        	if (((String)instance.getDirectOwnSlotValue(kb.getSlot("term"))).equals(attribute))
        		return instance;		                    
        }
                
        return null;
	}
	
	@SuppressWarnings("unchecked")
	Instance getStateInstance(String state) {
		KnowledgeBase kb = OracleIDSystem.getInstance().getCbrKb();
		Cls cls = kb.getCls("State");
		
		Iterator i = cls.getDirectInstances().iterator();
        while (i.hasNext()) {
        	Instance instance = (Instance) i.next();
        	if (((String)instance.getDirectOwnSlotValue(kb.getSlot("term"))).equals(state))
        		return instance;		                    
        }
                
        return null;
	}
}
