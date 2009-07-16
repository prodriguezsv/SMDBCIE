/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

import java.util.ArrayList;
import java.util.List;

import auxiliary.MultipleIndexValue;

import redundantDiscriminantNet.SAVCase;

/**
 * Purpose: Structure that groups (generalizes) a set of cases that share a common Descriptor.  Such cases may be
 * linked directly to the norm (when the cases' descriptions terminate at the Norm), or accessed via indices.  A Norm
 * may NOT directly point to another Norm.
 * IMPORTANT NOTES:
 * 1. There shall be no Index or Case duplication in the Norm's list of successors.
 * 3. A Norm may exist only in the context of a net.
 * 4. The only valid predecessor for an Norm is either an Index.
 * @author Armando
 *
 */
public class Norm extends Node {
	private Descriptor<Object> descriptor; //depicting the grouping (generalizing) concept
	private List<Node> successors; // A list of items directly linked to the Norm, i.e, cases or indices
	private Index predecessorIndex; // Pointer to the Norm's parent Index .
	private int numCases; // Number of cases grouped by the Norm, whether linked directly or located levels below.

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Norm() {
		descriptor = new Descriptor<Object>();
		successors = new ArrayList<Node>();
		predecessorIndex = null;
		numCases = 0;
	}

	/**
	 * @see "Método descriptor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor<Object> getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets the variable descriptor with the value of aDescriptor
	 * @see "Método descriptor: del protocolo adding en SUKIA SmallTalk"
	 * @param descriptor
	 */
	public void setDescriptor(Descriptor<Object> descriptor) {
		this.getDescriptor().add(descriptor.getAttribute(), descriptor.getValue());
	}

	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public void setPredecessorIndex(Index predecessorIndex) {
		this.predecessorIndex = predecessorIndex;
	}

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Node> getSuccessors() {
		return successors;
	}

	/**
	 * Método de instancia agregado 
	 * @param successors
	 */
	public void setSuccessors(List<Node> successors) {
		this.successors = successors;
	}
	
	/**
	 * @see "Método addSuccessor: del protocolo adding en SUKIA SmallTalk"
	 */
	public boolean addSuccessor(Node aSuccessor) {
		int i;
		
		// Make sure that an identical object hasn't already been included
		if (this.getSuccessors().contains(aSuccessor)) return false;

		// If aSuccessor is an Index, make sure that all Index-labels are unique
		if (aSuccessor instanceof Index) {
			i = 1;
			while (i <= this.getSuccessors().size()) {
				if (!(this.getSuccessors().get(i-1) instanceof Index))
					i = i + 1 ;
				else {
					 if (((Index)this.getSuccessors().get(i-1)).getLabel().equals(((Index) aSuccessor).getLabel()))
						 return false;
					 else i = i + 1;
				}
			}
		}
		
		this.getSuccessors().add(aSuccessor);
		
		return true;
	}

	/**
	 * @see "Método numCases del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getNumCases() {
		return numCases;
	}

	/**
	 * @see "Método incrementNumCasesBy: del protocolo adding en SUKIA SmallTalk"
	 * @param anInteger
	 */
	public void incrementNumCasesBy(int anInteger) {
		numCases = (numCases + anInteger);
	}
	
	/**
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getPredecessorNorm() {
		if (predecessorIndex == null) return null;
		
		return this.predecessorIndex.getPredecessorNorm();
	}

	/**
	 * This method returns a collection of cases that are immediate successors of this Norm.  That is, all retrieved cases
	 * are generalized by the Norm's Descriptor
	 * @see "Método successorCases del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public List<Case> successorCases() {
		int i;
		List<Case> s;
		
		s = new ArrayList<Case>();

		i = 1;
		while (i <= successors.size()) {
			if ((successors.get(i-1) instanceof Case) || (successors.get(i-1) instanceof SAVCase))
				s.add((Case)successors.get(i-1));
			i = i + 1;
		}
		
		return s;
	}

	/**
	 * This method returns a collection of cases that are immediate successors of this Norm.  That is, all retrieved cases
	 * are generalized by the Norm's Descriptor
	 * @see "Método successorCases del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public List<Index> successorIndexes() {
		int i;
		List<Index> s;
		
		s = new ArrayList<Index>();

		i = 1;
		while (i <= successors.size()) {
			if ((successors.get(i-1) instanceof Index))
				s.add((Index)successors.get(i-1));
			i = i + 1;
		}
		
		return s;
	}
	/**
	 * Given a Descriptor, this method searches for an Index that matches the attribute-value parameters, and returns:
	 * - the successor Norm, or
	 * - nil, otherwise
	 * @see "Método successorWith del protocolo navigating en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public Norm getSuccessorNorm(Descriptor<Object> aDescriptor) {
		Index index;

		index = this.getIndex(aDescriptor.getAttribute());
		if (!(index == null)) {
			return this.getSuccessorNorm(index, aDescriptor.getValue());
		}

		return null;
	}
	
	/**
	 * @see "Método getIndexWith: del protocolo searching en SUKIA SmallTalk"
	 * @param aLabel
	 * @return
	 */
	public Index getIndex(String aLabel) {
		int i;
		
		i = 1;
		while (i <= successors.size()) {
			if (successors.get(i-1) instanceof Index)
				if (aLabel.equals(((Index)successors.get(i-1)).getLabel()))
					return ((Index)successors.get(i-1));
			i = i + 1;
		}
			
		return null;
	}

	/**
	 * @see "Método getIndexWith:and: del protocolo searching en SUKIA SmallTalk"
	 * @param anAttribute
	 * @param aValue
	 * @return
	 */
	public Index getIndex(String anAttribute, Object aValue) {
		int i;
		
		i = 1;
		while (i <= successors.size()) {
			if (successors.get(i-1) instanceof Index)
				if (anAttribute.equals(((Index)successors.get(i-1)).getLabel()) 
						&& !(((Index)successors.get(i-1)).getIndexValue(aValue) == null)) 
						return ((Index)successors.get(i-1));
			i = i + 1;
		}
				
		return null;
	}
	
	/**
	 * @see "Método getSuccessorNormFor:with del protocolo searching en SUKIA SmallTalk"
	 * @param anIndex
	 * @param aValue
	 * @return
	 */
	public Norm getSuccessorNorm(Index anIndex, Object aValue) {
		Object succ;

		succ = anIndex.getIndexValueSuccessors(aValue);
		if (succ instanceof MultipleIndexValue) return null;
		
		return (Norm) succ;
	}
}
