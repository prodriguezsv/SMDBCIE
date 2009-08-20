/**
 * @see "Categoría Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import ontology.common.Descriptor;

/**
 * @author Armando
 *
 */
public class SpecificDescriptorPattern implements Comparable<SpecificDescriptorPattern>{
	private Descriptor<Object> pattern;
	private int frequency;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SpecificDescriptorPattern(Descriptor<Object> descriptor) {
		setPattern(descriptor);
		setFrequency(0);
	}
	
	/**
	 * Método de instancia agregado
	 * @param pattern
	 */
	public void setPattern(Descriptor<Object> pattern) {
		this.pattern = pattern;
	}

	/**
	 * @see "Método pattern del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor<Object> getPattern() {
		return pattern;
	}

	/**
	 * @see "Método frequency: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @see "Método incrementFrequencyBy: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void incrementFrequencyBy(int incrementFrequency) {
		this.frequency = this.frequency + incrementFrequency;
	}
	
	/**
	 * @see "Método frequency del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Método de instancia agregado
	 */
	public int compareTo(SpecificDescriptorPattern aSpecificAttribute) {
		return (aSpecificAttribute.getFrequency()-this.getFrequency());
	}
}
