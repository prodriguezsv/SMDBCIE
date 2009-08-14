/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el concepto abstracto de un nodo de la red de discriminaci&oacute;n redundante 
 * @author Armando
 *
 */
public abstract class Node {
	/**
	 * A list of successor Nodes directly linked to the Node, i.e, cases, indices, or norms
	 */
	private List<Node> successors;
	/**
	 * A list of predecessor Nodes directly to the Node, i.e, cases, indices, or norms
	 */
	private List<Node> predecessors; 

	/**
	 * Constructor por defecto: no todads las subclases necesitan sucesores o predecesores
	 */
	public Node() {
		successors = null;
		predecessors = null;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessors Lista de nodos de inicializaci&oacuten
	 */
	protected void setPredecessors(List<Node> predecessors) {
		this.predecessors = predecessors;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public List<Node> getPredecessors() {
		return predecessors;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Node> getSuccessors() {
		return successors;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @param successors
	 */
	protected void setSuccessors(List<Node> successors) {
		this.successors = successors;
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessor Nodo predecesor a agregar
	 * @return true si agrega predecessor y false de lo contrario
	 */
	public boolean addPredecessor(Node predecessor) {
		if (this.predecessors == null)
			this.predecessors = new ArrayList<Node>();
		
		// Make sure that an identical object hasn't already been included
		if (this.predecessors.contains(predecessor)) return false;
		
		this.predecessors.add(predecessor);
		predecessor.getSuccessors().add(this);
		
		return true;
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param predecessor predecessor Nodo predecesor a remover
	 * @return true si remueve predecessor y false de lo contrario
	 */
	public boolean removePredecessor(Node predecessor) {
		if (this.predecessors == null)
			return false;
		
		if (this.predecessors.remove(predecessor))
			predecessor.getSuccessors().remove(this);
		else return false;
		
		return true;
	}	
	
	/**
	 * @see "Método addSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aSuccessor Nodo sucesor a agregar
	 * @return true si agrega aSuccessor y false de lo contrario
	 */
	public boolean addSuccessor(Node aSuccessor) {
		if (this.successors == null)
			this.successors = new ArrayList<Node>();
		
		// Make sure that an identical object hasn't already been included
		if (this.getSuccessors().contains(aSuccessor)) return false;
		this.getSuccessors().add(aSuccessor);
		aSuccessor.getPredecessors().add(this);
		
		return true;
	}
	
	/**
	 * @see "Método predecessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aSuccessor Nodo sucesor a remover
	 * @return true si remueve aSuccessor y false de lo contrario
	 */
	public boolean removeSuccessor(Node aSuccessor) {
		if (this.successors == null)
			return false;
		
		if (this.successors.remove(aSuccessor))
			aSuccessor.getPredecessors().remove(this);
		else return false;
		
		return true;
	}
	
	/**
	 * M&eacute;todo a anular para polimofirmo
	 * @see "Método value del protocolo accessing en SUKIA SmallTalk"
	 * @return 
	 */
	public Object getValue() {
		return null;
	}
}
