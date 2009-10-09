/**
 * @see "Categoría Sukia Values de SUKIA Smalltalk"
 */
package ontology.common;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * @author Armando
 *
 */
public class RangeValue extends Value {
	private double lowerBound;
	private double upperBound;
	
	// bean stuff
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   	public void addPropertyChangeListener(PropertyChangeListener pcl) {
   		pcs.addPropertyChangeListener(pcl);
   	}

   	public void removePropertyChangeListener(PropertyChangeListener pcl) {
	   pcs.removePropertyChangeListener(pcl);
   	}


   	private static final long serialVersionUID = -3087841394215437493L;

  	private String _internalInstanceName = null;
  
	public String get_internalInstanceName() {
		return _internalInstanceName;
	}

	public RangeValue(String instance_name) {
		this._internalInstanceName = instance_name;
	}

	/**
	 * 
	 */
	public RangeValue() {
		// this is set up for weighted values as default
		super();
		setMeasuringUnit(MeasuringUnit.COUNT.getMeasuringUnit());
		this._internalInstanceName = "";
	}
	
	/**
	 * Constructor altenativo
	 */
	public RangeValue(double lowerBound, double upperBound) {
		this.setLowerBound(lowerBound);
		this.setUpperBound(upperBound);
		this.setMeasuringUnit(MeasuringUnit.COUNT.getMeasuringUnit());
	}
	
	/**
	 * Constructor altenativo
	 */
	public RangeValue(double lowerBound, double upperBound, String measurinUnit) {
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
	public void setLowerBound(double lowerBound) {
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
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @see "Método upperBound del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public double getUpperBound() {
		return upperBound;
	}	
	
	/**
	 * 
	 * @param aLowerBound
	 * @param anUpperBound
	 * @return
	 */
	public boolean isWithinthisBounds(double aLowerBound, double anUpperBound) {
		if (aLowerBound > anUpperBound) return false;

		return ((aLowerBound >= this.getLowerBound()) && (anUpperBound <=  this.getUpperBound()));
	}
	
	/**
	 * 
	 * @param aNumber
	 * @return
	 */
	public boolean containsNumber(double aNumber) {
		return ((aNumber >= this.getLowerBound()) && (aNumber <=  this.getUpperBound()));
	}
	
	/**
	 * Método agregado
	 * @param aValue
	 * @return
	 */
	public boolean equals(Object aValue) {
		if (aValue == null) return false;
		if (!(aValue instanceof RangeValue)) return false;
		
		if (((this.getLowerBound()  >= ((RangeValue)aValue).getLowerBound() &&
			this.getUpperBound()  <= ((RangeValue)aValue).getUpperBound()) ||
			(((RangeValue)aValue).getLowerBound()  >=  this.getLowerBound() &&
			((RangeValue)aValue).getUpperBound()  <= this.getUpperBound()) &&
			this.getMeasuringUnit().equals(((RangeValue)aValue).getMeasuringUnit())))
			return true;
		
		else return false;
	}
	
	public int hashCode(){
		return Double.valueOf(this.getLowerBound()).hashCode() 
			+ Double.valueOf(this.getUpperBound()).hashCode()
			+ this.getMeasuringUnit().hashCode();
	}
	
	/**
	 * 
	 */
	public String toString() {
		return  Double.toString(this.getLowerBound())+ "-" + this.getUpperBound() 
			+ " " + this.getMeasuringUnit();
	}
}
