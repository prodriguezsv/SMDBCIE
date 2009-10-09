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
public class SingleValue extends Value{
	private double value;
	
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

	public SingleValue() {
	  this._internalInstanceName = "";
  	}

  	public SingleValue(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}	
	
	/**
	 * Constructor altenativo
	 */
	public SingleValue(double aValue) {
		this.setValue(aValue);
		this.setMeasuringUnit(MeasuringUnit.COUNT.getMeasuringUnit());
	}

	/**
	 * Constructor altenativo
	 */
	public SingleValue(double aValue, String measuringUnit) {
		this.setValue(aValue);
		this.setMeasuringUnit(measuringUnit);
	}
	
	/**
	 * Constructor altenativo
	 */
	public SingleValue(SingleValue aSingleValue) {
		this.setValue(aSingleValue.getValue());
		this.setMeasuringUnit(aSingleValue.getMeasuringUnit());
	}
	
	/**
	 * @see "Método value del protocolo accessing-weighted values en SUKIA SmallTalk"
	 * @return
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * @see "Método value: del protocolo adding-weighted values en SUKIA SmallTalk"
	 * @param state
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * Método agregado
	 * @param aValue
	 * @return
	 */
    @Override
	public boolean equals(Object aValue) {
		if (aValue == null) return false;
		if (!(aValue instanceof SingleValue)) return false;
		
		if (this.getValue() == ((SingleValue)aValue).getValue())
			return true;
		else return false;
	}
	
    @Override
	public int hashCode(){
		return Double.valueOf(this.getValue()).hashCode() + this.getMeasuringUnit().hashCode();
	}
    
    /**
	 * 
	 */
	public String toString() {
		return  Double.toString(this.getValue())+ " " + this.getMeasuringUnit();
	}
}
