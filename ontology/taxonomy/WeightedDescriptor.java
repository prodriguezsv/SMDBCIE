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
public class WeightedDescriptor {
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
