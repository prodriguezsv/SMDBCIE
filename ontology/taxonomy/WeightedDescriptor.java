/**
 * 
 */
package ontology.taxonomy;

import ontology.common.Descriptor;

/**
 * 
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class WeightedDescriptor implements jade.content.Concept {
	private Descriptor descriptor;
	private Modifier modifier;

	/**
	 * 
	 * @param descriptor
	 */
	public WeightedDescriptor(Descriptor descriptor) {
		this.setDescriptor(descriptor);
		this.setModifier(new Modifier(1.0, 1.0, 1.0));
	}

	public WeightedDescriptor(Descriptor descriptor, Modifier modifier) {
		this.setDescriptor(descriptor);
		this.setModifier(modifier);
	}

	private String _internalInstanceName = null;

	public WeightedDescriptor() {
		this._internalInstanceName = "";
	}

	public WeightedDescriptor(String instance_name) {
		this._internalInstanceName = instance_name;
	}

	public String toString() {
		return _internalInstanceName;
	}

	public void setDescriptor(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public Modifier getModifier() {
		return modifier;
	}
}
