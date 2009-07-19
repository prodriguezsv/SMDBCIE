/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;

import onthology.common.Descriptor;




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
	private int numCases; // Number of cases grouped by the Norm, whether linked directly or located levels below.

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Norm() {
		descriptor = null;
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
		this.descriptor = descriptor;
	}
	
	/**
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public Index getPredecessor() {
		if (super.getSuccessors() != null) {
			if (!(super.getPredecessors().isEmpty()))
				return (Index) super.getPredecessors().get(0);
		}
		
		return null;
	}
	
	/**
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public void setPredecessor(Node aSuccessor) {
		if (super.getSuccessors() == null)
			super.addSuccessor(aSuccessor);
		else {
			super.getSuccessors().clear();
			super.addSuccessor(aSuccessor);
		}
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public void setPredecessors(List<Node> predecessors) {
		
	}
	
	/**
	 * @see "Método addSuccessor: del protocolo adding en SUKIA SmallTalk"
	 */
	public boolean addSuccessor(Node aSuccessor) {	
		if (!(aSuccessor instanceof Index || aSuccessor instanceof SheetNode)) return false;
		
		// If aSuccessor is an Index, make sure that all Index-labels are unique
		if (aSuccessor instanceof Index) {
			for (Node n: this.getSuccessors()) {
				if (!(n instanceof Index))
					continue;
				else {
					 if (((Index)n).getLabel().equals(((Index) aSuccessor).getLabel()))
						 return false;
					 else continue;
				}
			}
		}
		
		return this.getSuccessors().add(aSuccessor);
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
		if (this.getPredecessor() == null) return null;
		
		return this.getPredecessor().getPredecessor();
	}

	/**
	 * This method returns a collection of cases that are immediate successors of this Norm.  That is, all retrieved cases
	 * are generalized by the Norm's Descriptor
	 * @see "Método successorCases del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public List<SheetNode> successorCases() {
		List<SheetNode> s;
		
		if (super.getSuccessors() != null) {
			s = new ArrayList<SheetNode>();
	
			for (Node n: this.getSuccessors()) {
				if ((n instanceof SheetNode))
					s.add((SheetNode)n);
			}
			
			return s;
		}
		
		return null;
	}

	/**
	 * This method returns a collection of cases that are immediate successors of this Norm.  That is, all retrieved cases
	 * are generalized by the Norm's Descriptor
	 * @see "Método successorCases del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public List<Index> successorIndexes() {
		List<Index> s;
		
		if (super.getSuccessors() != null) {
			s = new ArrayList<Index>();
	
			for (Node n: this.getSuccessors()) {
				if (n instanceof Index)
					s.add((Index)n);
			}
			
			return s;
		}
		
		return null;
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

		index = this.getSuccessorIndex(aDescriptor.getAttribute());
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
	public Index getSuccessorIndex(String aLabel) {
		if (super.getSuccessors() != null) {
			for (Node n: this.getSuccessors()) {
				if (n instanceof Index)
					if (aLabel.equals(((Index)n).getLabel()))
						return ((Index)n);
			}
		}
		
		return null;
	}

	/**
	 * @see "Método getIndexWith:and: del protocolo searching en SUKIA SmallTalk"
	 * @param anAttribute
	 * @param aValue
	 * @return
	 */
	public Index getSuccessorIndex(String anAttribute, Object aValue) {
		if (super.getSuccessors() != null) {
			for (Node n: this.getSuccessors()) {
				if (n instanceof Index)
					if (anAttribute.equals(((Index)n).getLabel()) 
							&& !(((Index)n).getSuccessor(aValue) == null)) 
							return ((Index)n);
			}
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
		Node successor;

		successor = anIndex.getSuccessor(aValue);
		if (successor != null) {
			if (successor instanceof Norm) return (Norm)successor;
		}
		
		return null;
	}
	
	/**
	 * @see "Método value del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getValue() {
		if (this.getDescriptor() != null)
			return this.getDescriptor().getValue();
		
		return null;
	}
}
