/**
 * @see "Categoría Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

/**
 * @author Armando
 *
 */
public class WeightedIndicator {
	private String indicatorName;
	private double accumulatedWeight;
	private int numberTaxa;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public WeightedIndicator() {
		setIndicatorName(null);
		setAccumulatedWeight(0);
		setNumberTaxa(0);
	}

	/**
	 * @see "Método indicatorName: del protocolo adding en SUKIA SmallTalk"
	 * @param indicatorName
	 */
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	/**
	 * @see "Método name del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getIndicatorName() {
		return indicatorName;
	}

	/**
	 * Método de instancia agregado
	 * @param accumulatedWeight
	 */
	public void setAccumulatedWeight(double accumulatedWeight) {
		this.accumulatedWeight = accumulatedWeight;
	}

	/**
	 * @see "Método accumulatedWeight: del protocolo adding en SUKIA SmallTalk"
	 * @param accumulatedWeight
	 */
	public void incrementAccumulatedWeight(double incrementAccumulatedWeight) {
		this.accumulatedWeight = this.accumulatedWeight + incrementAccumulatedWeight;
	}
	
	/**
	 * @see "Método accumulatedWeight del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double getAccumulatedWeight() {
		return accumulatedWeight;
	}

	/**
	 * Método de instancia agregado
	 * @param numberTaxa
	 */
	public void setNumberTaxa(int numberTaxa) {
		this.numberTaxa = numberTaxa;
	}

	/**
	 * @see "Método numberTaxa: del protocolo adding en SUKIA SmallTalk"
	 * @param numberTaxa
	 */
	public void incrementNumberTaxa() {
		this.numberTaxa = this.numberTaxa + 1;
	}
	
	/**
	 * @see "Método numberTaxa del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getNumberTaxa() {
		return numberTaxa;
	}

	/**
	 * @see "Método meanWeight del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double meanWeight() {
		return (double)(accumulatedWeight / numberTaxa);
	}
	
}
