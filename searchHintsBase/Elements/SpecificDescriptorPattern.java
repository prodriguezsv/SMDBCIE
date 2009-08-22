/**
 * Este paquete agrupa los elementos de las distintas listas que almacenan patrones de b&uacute;squeda
 * de casos previamente resueltos
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
	 * M&eacute;todo constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SpecificDescriptorPattern(Descriptor<Object> descriptor, int frecuency) {
		setPattern(descriptor);
		setFrequency(frecuency);
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @param pattern
	 */
	public void setPattern(Descriptor<Object> pattern) {
		this.pattern = pattern;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo pattern del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor<Object> getPattern() {
		return pattern;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @see "M&eacute;todo frequency: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @see "M&eacute;todo incrementFrequencyBy: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void incrementFrequencyBy(int incrementFrequency) {
		this.frequency = this.frequency + incrementFrequency;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo frequency del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Compara por criterio de frecuencia
	 */
	public int compareTo(SpecificDescriptorPattern aSpecificPattern) {
		return (aSpecificPattern.getFrequency()-this.getFrequency());
	}
}
