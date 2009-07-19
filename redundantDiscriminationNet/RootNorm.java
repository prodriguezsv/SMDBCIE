/**
 * 
 */
package redundantDiscriminationNet;

import java.util.List;

import onthology.common.Descriptor;



/**
 * Purpose: Specialization of a Norm, and its mission is to act as a net's entry point.  The two most important features
 * of a RootNorm are: 1. it does not have a predecessor, and 2. its Descriptor is of the form ( nil nil ).
 * @author Armando
 *
 */
public class RootNorm extends Norm {

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RootNorm() {
		
	}
	
	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RootNorm(String aStructureName) {
		this.setStructure(aStructureName);
	}
	
	/**
	 * @see "Método descriptor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor<Object> getDescriptor() {
		return null;
	}

	/**
	 * Sets the variable descriptor with the value of aDescriptor
	 * @see "Método descriptor: del protocolo adding en SUKIA SmallTalk"
	 * @param descriptor
	 */
	public void setDescriptor(Descriptor<Object> descriptor) {

	}
	
	/**
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public void setPredecessor(Node aSuccessor) {
		
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public void setPredecessors(List<Node> predecessors) {
		
	}
	
	/**
	 * @see "Método addStructure: del protocolo adding-private en SUKIA SmallTalk"
	 * @param structure
	 */
	public void setStructure(String structure) {
		super.getDescriptor().setStructure(structure);
	}
	
	/**
	 * @see "Método structure del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructure() {
		return super.getDescriptor().getStructure();
	}
	
	/**
	 * @see "Método value del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getValue() {
		return null;
	}
}
