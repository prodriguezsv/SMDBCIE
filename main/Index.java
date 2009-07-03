/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;

import auxiliary.IndexValue;

/**
 * Purpose: Structure that points to one or more Case's or Norm's, according to:
 * a) an attribute, and
 * b) a set of values that the attribute may have.
 * The identifier of an Index is its label, that is, the attribute name (e.g., shape, color). The elements pointed to are packed
 * in instances of IndexValue, and placed in a list.
 * IMPORTANT NOTES:1
 * 1. There must be at least one IndexValue for each Index.
 * 2. There shall be no value duplication in the Index's set of IndexValue's.
 * 3. An index may exist only in the context of a net.
 * 4. The only valid predecessor for an Index is either a Norm or net root.
 * @author Armando
 *
 */
public class Index extends Node {
	String label; //A Symbol that represents an attribute.
	List<IndexValue> successors; // List of IndexValues, each pointing to a case or a norm.
	Norm predecessorNorm; // Pointer to the Index's predecessor norm.

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk" 
	 */
	public Index() {
		// TODO Auto-generated constructor stub

		label = "";
		successors = new ArrayList<IndexValue>();
		predecessorNorm = null;
	}
	
	/**
	 * Clears and resets the Index's instance variables
	 * @see "Método clear del protocolo initializing en SUKIA SmallTalk"
	 */
	public void clear() {
		label = "";
		while (!(successors.isEmpty())) successors.remove(0);
		predecessorNorm = null;
	}

	/**
	 * @see "Método label del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @see "Método addLabel del protocolo adding en SUKIA SmallTalk"
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * IMPORTANT NOTE: The only valid predecessor for an INDEX  is either a Norm or net root
	 * @see "Método addPredecessor del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorNorm
	 */
	public void setPredecessorNorm(Norm predecessorNorm) {
		this.predecessorNorm = predecessorNorm;
	}

	/**
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getPredecessorNorm() {
		return predecessorNorm;
	}

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<IndexValue> getSuccessors() {
		return successors;
	}
	
	/**
	 * Método de instancia agregado
	 * @param successors
	 */
	public void setSuccessors(List<IndexValue> successors) {
		this.successors = successors;
	}
	
	/**
	 * @see "Método addIndexValue: del protocolo adding en SUKIA SmallTalk"
	 * @param anIndexValue
	 */
	// Pendiente de traducir
	public void addIndexValue(IndexValue anIndexValue) {
		int i;
		IndexValue ixv;
	
		// If argument is not an IndexValue, or if argument contains invalid data, do not add
	}
	
	/**
	 * @see "Método successors: del protocolo navigating en SUKIA SmallTalk"
	 * @param <T>
	 * @param aValue
	 */
	// Pendiente de traducir
	public <T> void successors(T aValue) {
		return;
	}
	
	/**
	 * @see "Método getIndexValueWithaValue: del protocolo searching en SUKIA SmallTalk"
	 * @param <T>
	 * @return
	 */
	// Pendiente de traducir
	public <T> IndexValue getIndexValueWith(T aValue) {
		if (successors.isEmpty()) return null;

		for (int i = 1; i <= successors.size(); i++) {
		}
		
		return null;
	}

	/**
	 * @see "Método getIndexValueWithaValue: del protocolo testing en SUKIA SmallTalk"
	 * @param aSuccessorList
	 * @return
	 */
	// Pendiente de traducir
	public boolean isSuccessorIncluded(List aSuccessorList) {
		return false;
	}
	
	public <T> boolean isValueIncluded(T aValue) {
		return false;
	}
}
