/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

import java.util.ArrayList;
import java.util.List;

import domainTheory.Structure;


/**
 * Case representa un caso que es una representación de una situación, donde una descripción
 * (i.e., un conjunto de objetos Descriptor) y una solución ilustra como un problema particular fue resuelto
 * Además, una justificación explica las razones de solución del caso.  Cuando un caso (problema) se resuelve,
 * se agrega a una red para referencia futura vía métodos de búsqueda heuristica
 * @see "Categoría Main de SUKIA Smalltalk"
 * @author Armando
 *
 */
public class Case extends Node {
	private String solution;
	private Description<Descriptor<Object>> description;
	private Description<Descriptor<Object>> justification;
	private List<PredecessorNode> predecessors;
	private boolean state;
	
	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Case() {
		solution = null;
		description = new Description<Descriptor<Object>>();
		justification = new Description<Descriptor<Object>>();
		predecessors = new ArrayList<PredecessorNode>();
		state = false;
	}
	
	/**
	 * @see "Método solution del protocolo adding en SUKIA SmallTalk"
	 * @param solution
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	/**
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getSolution() {
		return solution;
	}
	
	public void setDescription(Description<Descriptor<Object>> description) {
		this.description = description;
	}
	
	/**
	 * @see "Método description del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getDescription() {
		return description;
	}

	public void setJustification(Description<Descriptor<Object>> justification) {
		this.justification = justification;
	}

	/**
	 * @see "Método justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getJustification() {
		return justification;
	}

	public void setPredecesor(List<PredecessorNode> predecesor) {
		this.predecessors = predecesor;
	}

	/**
	 * @see "Método predecessor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<PredecessorNode> getPredecesors() {
		return predecessors;
	}

	/**
	 * @see "Método state del protocolo adding en SUKIA SmallTalk"
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @see "Método state del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Argument aPredecessor can be an Index or a Norm
	 * @see "Método addPredecessorWith:and: del protocolo adding en SUKIA SmallTalk"
	 * @param aPredecessor
	 */
	public void addPredecessor(Node aPredecessor, Object aValue) {
		PredecessorNode pn;
		pn = new PredecessorNode(aPredecessor, aValue); 
		predecessors.add(pn);
	}
	
	/**
	 * @see "Método removePredecessorWith:and: del protocolo removing en SUKIA SmallTalk"
	 * @param aPredecessor
	 * @param aValue
	 * @return
	 */
	public PredecessorNode removePredecessor(Node aPredecessor, Object aValue) {
		for( int i = 1; i <= predecessors.size(); i++) {
			if (predecessors.get(i-1).getValue().equals(aValue) && predecessors.get(i-1).getNode().equals(aPredecessor))
				return predecessors.remove(i-1);
		}
		
		return null;
	}
	
	/**
	 * Clears and resets all of the case's instance variables
	 * @see "Método flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void clear() {
		this.getDescription().clear();
		solution = null;
		this.getJustification().clear();
		this.getPredecesors().clear();
		this.setState(false);
	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método currentStructure del protocolo special en SUKIA SmallTalk"
	 * @return
	 */
	public String currentStructure() {
		return null;
	}

	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método flushDescriptionCopy del protocolo special en SUKIA SmallTalk"
	 */
	public void flushDescriptionCopy() {
		
	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método flushStructureCopy del protocolo special en SUKIA SmallTalk"
	 */
	public void flushStructureCopy() {
		
	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método prepareDescriptionWith del protocolo special en SUKIA SmallTalk"
	 * @param aStructure
	 */
	public void prepareDescriptionWith(Structure aStructure) {

	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método restoreDescription del protocolo special en SUKIA SmallTalk"
	 */
	public void restoreDescription() {

	}
}