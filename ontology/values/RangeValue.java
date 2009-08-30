/**
 * @see "Categoría Sukia Values de SUKIA Smalltalk"
 */
package ontology.values;


/**
 * @author Armando
 *
 */
public class RangeValue extends Value {
	private double lowerBound;
	private double upperBound;

	/**
	 * 
	 */
	public RangeValue() {
		// this is set up for weighted values as default
		super();
		setMeasuringUnit(MeasuringUnit.COUNT);
	}
	
	/**
	 * Constructor altenativo
	 */
	public RangeValue(double lowerBound, double upperBound) {
		this.setLowerBound(lowerBound);
		this.setUpperBound(upperBound);
		this.setMeasuringUnit(MeasuringUnit.COUNT);
	}
	
	/**
	 * Constructor altenativo
	 */
	public RangeValue(double lowerBound, double upperBound, MeasuringUnit measurinUnit) {
		this.setLowerBound(lowerBound);
		this.setUpperBound(upperBound);
		this.setMeasuringUnit(measurinUnit);
	}

	/**
	 * @see "Método copyFrom:referencing: del protocolo copying en SUKIA SmallTalk"
	 * @param aValue
	 * @param aTaxon
	 */
	public RangeValue(RangeValue aValue) {
		this.setLowerBound(aValue.getLowerBound());
		this.setUpperBound(aValue.getUpperBound());
		this.setMeasuringUnit(aValue.getMeasuringUnit());
	}

	/**
	 * @see "Método lowerBound del protocolo adding-range values en SUKIA SmallTalk"
	 * @return
	 */
	private void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @see "Método lowerBound del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public double getLowerBound() {
		return lowerBound;
	}

	/**
	 * @see "Método upperBound del protocolo adding-range values en SUKIA SmallTalk"
	 * @return
	 */
	private void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @see "Método upperBound del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public double getUpperBound() {
		return upperBound;
	}	
	
	public boolean isRangeWithin(double aLowerBound, double anUpperBound) {
		if (aLowerBound > anUpperBound) return false;

		return ((this.getLowerBound() >= aLowerBound) && (this.getUpperBound() <= anUpperBound));
	}
	
	/**
	 * Método agregado
	 * @param aValue
	 * @return
	 */
	public boolean equals(Object aValue) {
		if (aValue == null) return false;
		if (!(aValue instanceof RangeValue)) return false;
		
		if (this.getLowerBound()  >= ((RangeValue)aValue).getLowerBound() &&
			this.getUpperBound()  <= ((RangeValue)aValue).getUpperBound() &&
			this.getMeasuringUnit().equals(((RangeValue)aValue).getMeasuringUnit()))
			return true;
		
		else return false;
	}
	
	public int hashCode(){
		return Double.valueOf(this.getLowerBound()).hashCode() 
			+ Double.valueOf(this.getUpperBound()).hashCode()
			+ this.getMeasuringUnit().hashCode();
	}
}
