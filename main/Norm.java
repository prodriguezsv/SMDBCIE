/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;

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
	private Descriptor descriptor; //depicting the grouping (generalizing) concept
	private List<Node> successors; // A list of items directly linked to the Norm, i.e, cases or indices
	private Index predecessorIndex; // Pointer to the Norm's parent Index .
	private int numCases; // Number of cases grouped by the Norm, whether linked directly or located levels below.

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Norm() {
		// TODO Auto-generated constructor stub
		descriptor = new Descriptor();
		successors = new ArrayList<Node>();
		predecessorIndex = null;
		numCases = 0;
	}

	/**
	 * @see "Método descriptor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets the variable descriptor with the value of aDescriptor
	 * @see "Método descriptor: del protocolo adding en SUKIA SmallTalk"
	 * @param descriptor
	 */
	// Pendiente de traducir
	public void setDescriptor(Descriptor descriptor) {

		this.descriptor = descriptor;
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
	 * @see "Método successors: del protocolo adding en SUKIA SmallTalk"
	 * @param successors
	 */
	// Pendiente de traducir
	public void setSuccessors(List<Node> successors) {
		this.successors = successors;
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
	// Pendiente de traducir (ojo)
	public Norm predecessor() {
		if (predecessorIndex == null) return this;
		
		return null;
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
			if (successors.get(i).getClass().getName().equals("Case") || successors.get(i).getClass().getName().equals("SAVCase"))
				s.add((Case)successors.get(i));
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
	// Pendiente de traducir
	public Norm successorNormWith(Descriptor aDescriptor) {
		return null;
	}
	
	/**
	 * @see "Método getIndexWith: del protocolo searching en SUKIA SmallTalk"
	 * @param aLabel
	 * @return
	 */
	// Pendiente de traducir (ojo)
	public Index getIndexWith(String aLabel) {
		int i;
		
		i = 1;
		while (i <= successors.size()) {
			if (successors.get(i).getClass().getName().equals("Index"))
				// if (aLabel.equals(successors.get(i).getLabel()))
					return ((Index)successors.get(i));
			i = i + 1;
		}
			
		return null;
	}

	/**
	 * @see "Método getIndexWith:and: del protocolo searching en SUKIA SmallTalk"
	 * @param <T>
	 * @param anAttribute
	 * @param aValue
	 * @return
	 */
	// pendiente de traducir (ojo)
	public <T> Index getIndexWith(String anAttribute, T aValue) {
		int i;
		
		i = 1;
		while (i <= successors.size()) {
			if (successors.get(i).getClass().getName().equals("Index"))
				//if (anAttribute.equals(successors.get(i).getLabel() && !(successors.get(i).getIndexValueWith(aValue)) == null)) 
						return ((Index)successors.get(i));
			i = i + 1;
		}
				
		return null;
	}
	
	/**
	 * @see "Método getSuccessorFor:with del protocolo searching en SUKIA SmallTalk"
	 * @param <T>
	 * @param anIndex
	 * @param aValue
	 * @return
	 */
	// Pendiente de traducir
	public <T> Node getSuccessorFor(Index anIndex, T aValue) {
		return null;
	}

	/**
	 * @see "Método getSuccessorNormFor:with del protocolo searching en SUKIA SmallTalk"
	 * @param <T>
	 * @param anIndex
	 * @param aValue
	 * @return
	 */
	// Pendiente de traducir
	public <T> Norm getSuccessorNormFor(Index anIndex, T aValue) {
		return null;
	}
}
