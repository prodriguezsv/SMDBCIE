/**
 * Este paquete agrupa los elementos de las distintas listas que almacenan patrones de b&uacute;squeda
 * de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import ontology.common.Descriptor;

/**
 * @author Armando
 *
 */
public class WeightedDescriptorPattern implements Comparable<WeightedDescriptorPattern> {
	private Descriptor<Object> pattern;
	private double accumulatedWeight;
	private int numberTaxa;
	
	/**
	 * Constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public WeightedDescriptorPattern(Descriptor<Object> descriptor, double weight, int numberTaxa) {
		setPattern(descriptor);
		setAccumulatedWeight(weight);
		setNumberTaxa(numberTaxa);
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
	 * @param accumulatedWeight
	 */
	public void setAccumulatedWeight(double accumulatedWeight) {
		this.accumulatedWeight = accumulatedWeight;
	}

	/**
	 * @see "M&eacute;todo accumulatedWeight: del protocolo adding en SUKIA SmallTalk"
	 * @param accumulatedWeight
	 */
	public void incrementAccumulatedWeight(double incrementAccumulatedWeight) {
		this.accumulatedWeight = this.accumulatedWeight + incrementAccumulatedWeight;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo accumulatedWeight del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double getAccumulatedWeight() {
		return accumulatedWeight;
	}

	/**
	 * M&eacute;todo de instancia agregado
	 * @param numberTaxa
	 */
	public void setNumberTaxa(int numberTaxa) {
		if (numberTaxa > 0)
			this.numberTaxa = numberTaxa;
	}

	/**
	 * @see "M&eacute;todo numberTaxa: del protocolo adding en SUKIA SmallTalk"
	 * @param numberTaxa
	 */
	public void incrementNumberTaxa() {
		this.numberTaxa = this.numberTaxa + 1;
	}
	
	/**
	 * @see "M&eacute;todo numberTaxa: del protocolo adding en SUKIA SmallTalk"
	 * @param numberTaxa
	 */
	public void incrementNumberTaxa(int increment) {
		this.numberTaxa = this.numberTaxa + increment;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo numberTaxa del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getNumberTaxa() {
		return numberTaxa;
	}

	/**
	 * @see "M&eacute;todo meanWeight del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double meanWeight() {
		return (double)(accumulatedWeight / numberTaxa);
	}
	
	/**
	 * Compara por criterio de peso medio
	 */
	public int compareTo(WeightedDescriptorPattern aPattern) {
		if ((aPattern.meanWeight()	- this.meanWeight()) > 0)
			return 1;
		else if ((aPattern.meanWeight() - this.meanWeight()) < 0)
			return -1;
		else return 0;
	}
}
