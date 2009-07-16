/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Armando
 *
 */
public abstract class Node {
	private List<Node> successors; // A list of items directly linked to the Node, i.e, cases, indices, or norms
	private List<Node> predecessors; // Pointer to the Node's parent Node .

	/**
	 * 
	 */
	public Node() {
		successors = null;
		predecessors = null;
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public boolean addPredecessor(Node predecessor) {
		if (this.predecessors == null)
			this.predecessors = new ArrayList<Node>();
		
		// Make sure that an identical object hasn't already been included
		if (this.predecessors.contains(predecessor)) return false;
		
		this.predecessors.add(predecessor);
		
		return true;
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public boolean removePredecessor(Node predecessor) {
		if (this.predecessors == null)
			return false;
		
		return this.predecessors.remove(predecessor);
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public void setPredecessors(List<Node> predecessors) {
		this.predecessors = predecessors;
	}
	
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public List<Node> getPredecessors() {
		return predecessors;
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
		if (this.successors == null)
			this.successors = new ArrayList<Node>();
		
		// Make sure that an identical object hasn't already been included
		if (this.getSuccessors().contains(aSuccessor)) return false;
		this.getSuccessors().add(aSuccessor);
		
		return true;
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessorIndex
	 */
	public boolean removeSuccessor(Node aSuccessor) {
		if (this.successors == null)
			return false;
		
		return this.successors.remove(aSuccessor);
	}
	
	/**
	 * @see "Método value del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getValue() {
		return null;
	}
}
