/**
 * Este paquete agrupa los elementos de las distintas listas que almacenan patrones de b&uacute;squeda
 * de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.List;

import ontology.common.Description;
import ontology.common.Descriptor;


/**
 * Esta clase representa el patr&oacute;n de una lista de descriptores utilizados en la resoluci&oacute;n de
 * un caso previo
 * @author Armando
 *
 */
public class DescriptorsPattern implements Comparable<DescriptorsPattern> {
	/**
	 * Patr&oacute;n utilizado en la resoluci&oacute;n de un(os) caso(s) previo(s)
	 */
	private Description pattern;
	/**
	 * N&uacute;mero de casos previos resueltos positivamente con este patr&oacute;n
	 */
	private int successFrequency;
	/**
	 * N&uacute;mero de casos previos resueltos negativamente con este patr&oacute;n
	 */
	private int failureFrequency;

	/**
	 * M&eacute;todo constructor por defecto
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public DescriptorsPattern() {
		setPattern(new Description());
		setSuccessFrequency(0);
		setFailureFrequency(0);
	}
	
	/**
	 * M&eacute;todo constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public DescriptorsPattern(Description pattern, int sf, int ff) {
		setPattern(pattern);
		setSuccessFrequency(sf);
		setFailureFrequency(ff);
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param frequency
	 */
	public void setSuccessFrequency(int frequency) {
		this.successFrequency = frequency;
	}

	/**
	 * @see "M&eacute;todo incrementFrequencyBy: del protocolo adding en SUKIA SmallTalk"
	 * @param successFrequency
	 */
	public void incrementSuccessFrequencyBy(int anIncrement) {
		this.successFrequency = this.successFrequency + anIncrement;
	}
	
	/**
	 * @see "M&eacute;todo incrementFrequency del protocolo adding en SUKIA SmallTalk"
	 * @param successFrequency
	 */
	public void incrementSuccessFrequency() {
		this.successFrequency = this.successFrequency + 1;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo frequency del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getSuccessFrequency() {
		return successFrequency;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param failureFrequency
	 */
	public void setFailureFrequency(int failureFrequency) {
		this.failureFrequency = failureFrequency;
	}
	
	/**
	 * @see "M&eacute;todo incrementFrequencyBy: del protocolo adding en SUKIA SmallTalk"
	 * @param successFrequency
	 */
	public void incrementFailureFrequencyBy(int anIncrement) {
		this.failureFrequency = this.failureFrequency + anIncrement;
	}
	
	/**
	 * @see "Método incrementFrequency del protocolo adding en SUKIA SmallTalk"
	 * @param successFrequency
	 */
	public void incrementFailureFrequency() {
		this.failureFrequency = this.failureFrequency + 1;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public int getFailureFrequency() {
		return failureFrequency;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param pattern
	 */
	public void setPattern(Description pattern) {
		this.pattern = pattern;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo pattern del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getPattern() {
		return pattern;
	}
	
	/**
	 * @see "M&eacute;todo addDescriptor: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addDescriptor(Descriptor aDescriptor) {
		return this.getPattern().addToConcreteDescription(aDescriptor);
	}
	
	/**
	 * Precondition: all Descriptors in aPattern must be different, in order to keep the percentage of
	 * similarity between 0 and 1.
	 * @see "M&eacute;todo howSimilarTo: del protocolo testing en SUKIA SmallTalk"
	 * @param aPattern
	 * @return v = 0 if they are different; 0 < v < 1 if they are similar in v%; v = 1 if they are equal.
	 */
	public double howSimilarTo(List<Descriptor> aPattern) {
		int c;
		
		c = 0;
		for (Descriptor d:aPattern)
			if (this.getPattern().contains(d))
				c = c + 1;

		if (aPattern.size() >= this.getPattern().size())
			return (c / (double)aPattern.size());
		else return (c / (double)this.getPattern().size());
	}
	
	/**
	 * Comparador por defecto
	 */
	public int compareTo(DescriptorsPattern aPattern) {
		return (aPattern.getPattern().size() - this.getPattern().size());
	}
}
