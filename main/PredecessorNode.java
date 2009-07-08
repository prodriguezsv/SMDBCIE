/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

/**
 * @author Armando
 *
 */
public class PredecessorNode {
	private Object value;
	private Node node;

	/**
	 * 
	 */
	public PredecessorNode() {
		this.setValue(null);
		this.setNode(null);
	}
	
	public PredecessorNode(Node node, Object value) {
		this.setValue(value);
		this.setNode(node);
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

}
