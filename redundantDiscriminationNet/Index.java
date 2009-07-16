/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.List;

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
	private String label; //A Symbol that represents an attribute.

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk" 
	 */
	public Index() {
		label = null;
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
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getPredecessor() {
		if (super.getPredecessors() != null) {
			if (!(super.getPredecessors().isEmpty()))
				return (Norm) super.getPredecessors().get(0);
		}
		
		return null;
	}
	
	/** Asigna el predecesor Norm Index. Si ya tiene asignado un valor lo anula.
	 * @see "Método predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public void setPredecessor(Norm aPredecessor) {
		if (super.getPredecessors() != null) {
			if (!super.getPredecessors().isEmpty())
				super.getPredecessors().clear();
		}
		
		super.addPredecessor(aPredecessor);
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
		if (!(aSuccessor instanceof Norm || aSuccessor instanceof SheetNode)) return false;
		
		return super.addSuccessor(aSuccessor);
	}
	
	/**
	 * @see "Método getIndexValueWith:aValue: del protocolo searching en SUKIA SmallTalk"
	 * @return
	 */
	public Node getSuccessor(Object aValue) {
		if (super.getSuccessors() != null) {
			for (Node n: this.getSuccessors()) {
				if (n.getValue().equals(aValue))
					return n;
			}
		}
		
		return null;
	}
}
