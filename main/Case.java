/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

import java.util.ArrayList;
import java.util.List;

import reasoner.CaseSolution;

import auxiliary.SingleIndexValue;


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
	private CaseSolution solution; // An object that represents the solution to the case.  Such object may be a text string, or a compund object with more associated information.
	private Description<Descriptor<Object>> description; // A list containing a set of Descriptor's (a description of the problem)
	private Description<Descriptor<Object>> justification; // A list containing a set of Descriptor's (the solution path of the case, i.e., the result of the traversal across the net and other reference structures). 
	private List<SingleIndexValue<Node>> predecessors; // A list containing links to the case's predecessor norms and/or indices within the net. 
	private boolean state; // A case may be "positive" (i.e., the given solution is correct) or "negative" (i.e., the given solution is incorrect)
	
	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Case() {
		solution = null;
		description = new Description<Descriptor<Object>>();
		justification = new Description<Descriptor<Object>>();
		predecessors = new ArrayList<SingleIndexValue<Node>>();
		state = false;
	}
	
	/**
	 * @see "Método solution del protocolo adding en SUKIA SmallTalk"
	 * @param solution
	 */
	public void setSolution(CaseSolution solution) {
		this.solution = solution;
	}
	
	/**
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public CaseSolution getSolution() {
		return solution;
	}
	
	/**
	 * Método de instancia agregado
	 * @param description
	 */
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

	/**
	 * Método de instancia agregado
	 * @param justification
	 */
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

	public void setPredecesor(List<SingleIndexValue<Node>> predecesor) {
		this.predecessors = predecesor;
	}

	/**
	 * @see "Método predecessor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<SingleIndexValue<Node>> getPredecessors() {
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
	public boolean addPredecessor(Node aPredecessor, Object aValue) {
		SingleIndexValue<Node> pn;
		
		pn = new SingleIndexValue<Node>();
		pn.setValue(aValue);
		pn.setSuccessor(aPredecessor);
		
		if (!(this.getPredecessors().contains(pn)))
			predecessors.add(pn);
		
		return true;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "Método addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToDescription(Descriptor<Object> aDescriptor) {
		if (this.getDescription().contains(aDescriptor))
			return false;
		this.getDescription().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "Método addToJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToJustification(Descriptor<Object> aDescriptor) {
		if (this.getDescription().contains(aDescriptor))
			return false;
		this.getJustification().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * @see "Método removePredecessorWith:and: del protocolo removing en SUKIA SmallTalk"
	 * @param aPredecessor
	 * @param aValue
	 * @return
	 */
	public boolean removePredecessor(Node aPredecessor, Object aValue) {
		for( int i = 1; i <= predecessors.size(); i++) {
			if (predecessors.get(i-1).getValue().equals(aValue) && predecessors.get(i-1).getSuccessor().equals(aPredecessor))
				predecessors.remove(i-1);
				return true;
		}
		
		return false;
	}
	
	/**
	 * Clears and resets all of the case's instance variables
	 * @see "Método flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void clear() {
		this.getDescription().clear();
		solution = null;
		this.getJustification().clear();
		this.getPredecessors().clear();
		this.setState(false);
	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método currentStructure del protocolo special en SUKIA SmallTalk"
	 * @return
	 */
	public String getCurrentStructure() {
		return null;
	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método flushDescriptionCopy del protocolo special en SUKIA SmallTalk"
	 * @return
	 */
	public void flushDescriptionCopy() {

	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método flushStructureCopy del protocolo special en SUKIA SmallTalk"
	 * @return
	 */
	public void flushStructureCopy() {

	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método prepareDescriptionWith del protocolo special en SUKIA SmallTalk"
	 * @param aStructure
	 */
	public void prepareDescriptionWith(String aStructure) {

	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método restoreDescription del protocolo special en SUKIA SmallTalk"
	 */
	public void restoreDescription() {

	}
}