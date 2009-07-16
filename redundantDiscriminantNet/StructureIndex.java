/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminantNet;

import java.util.ArrayList;
import java.util.List;

import main.Node;

import auxiliary.IndexValue;
import auxiliary.MultipleIndexValue;
import auxiliary.SingleIndexValue;

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
public class StructureIndex extends Node {
	private String label; //A Symbol that represents an structure.
	private List<IndexValue<Object>> successors; // List of IndexValues, each pointing to a norm or an index.

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk" 
	 */
	public StructureIndex() {
		label = null;
		successors = new ArrayList<IndexValue<Object>>();
	}
	
	/**
	 * Clears and resets the Index's instance variables
	 * @see "Método clear del protocolo initializing en SUKIA SmallTalk"
	 */
	public void clear() {
		label = null;
		successors.clear();
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
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<IndexValue<Object>> getSuccessors() {
		return successors;
	}
	
	/**
	 * Método de instancia agregado
	 * @param successors
	 */
	public void setSuccessors(List<IndexValue<Object>> successors) {
		this.successors = successors;
	}
	
	/**
	 * @see "Método addIndexValue: del protocolo adding en SUKIA SmallTalk"
	 * @param anIndexValue
	 */
	public boolean addIndexValue(IndexValue<Object> anIndexValue) {
	
		// If argument is not an IndexValue, or if argument contains invalid data, do not add
		 if (!(anIndexValue.isValid())) return false;

		// If argument is already included, do not add
		if (this.isValueIncluded(anIndexValue.getValue())) return false;
		
		if (this.isSuccessorIncluded(((SingleIndexValue<Node>)anIndexValue).getSuccessor()))
			return false;
		
		this.getSuccessors().add(anIndexValue);
		
		return true;
	}
	
	/**
	 * @see "Método successors: del protocolo navigating en SUKIA SmallTalk"
	 * @param aValue
	 */
	
	public Object getIndexValueSuccessors(Object aValue) {
		for (int i = 1; i <= this.getSuccessors().size(); i++) {
			if (aValue.equals(this.getSuccessors().get(i-1).getValue()))
				if (this.getSuccessors().get(i-1) instanceof MultipleIndexValue)
					return ((MultipleIndexValue)this.getSuccessors().get(i-1)).getSuccessors();
				else return ((SingleIndexValue<Node>)this.getSuccessors().get(i-1)).getSuccessor();
		}
		
		return null;
	}
	
	/**
	 * @see "Método getIndexValueWith:aValue: del protocolo searching en SUKIA SmallTalk"
	 * @return
	 */
	public IndexValue<Object> getIndexValue(Object aValue) {
		if (this.getSuccessors().isEmpty()) return null;

		for (int i = 1; i <= this.getSuccessors().size(); i++) {
			if (this.getSuccessors().get(i-1).getValue().equals(aValue))
				return this.getSuccessors().get(i-1);
		}
		
		return null;
	}

	/**
	 * All successors of an Index are represented by instances of IndexValue, which have the form: (val, succ ) | ( val, succ1, ..., succN ).
	 * A successor succ may be a Norm, a Case, or a set of Cases. Since the number of successors for an IndexValue may vary (i.e., one or more),
	 * then, IndexValue returns them in a collection.
	 * Returns: true -  if there is at least ONE succesor, that belongs to any of the IndexValue successor lists, that matches an element in aSuccessorList;
	 * false - if there is no match; i.e., ALL elements in aSuccessorList are new.
	 * @see "Método getIndexValueWithaValue: del protocolo testing en SUKIA SmallTalk"
	 * @param aSuccessorList
	 * @return
	 */
	
	public boolean isSuccessorIncluded(Node aSuccessor) {

		for (int i = 1; i <= this.getSuccessors().size(); i++) {
			if (this.getSuccessors().get(i-1) instanceof SingleIndexValue) {
				return aSuccessor.equals(((SingleIndexValue<Node>)this.getSuccessors().get(i-1)).getSuccessor());
			}
		}
		
		return false;
	}
	
	/**
	 * @see "Método isValueIncluded: del protocolo testing en SUKIA SmallTalk"
	 * @param aValue
	 * @return
	 */
	public boolean isValueIncluded(Object aValue) {
		for (int i = 1; i <= this.getSuccessors().size(); i++) {
			if (aValue.equals(this.getSuccessors().get(i-1).getValue())) return true;
		}
		
		return false;
	}
}
