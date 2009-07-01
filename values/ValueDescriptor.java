/**
 * 
 */
package values;

import java.util.ArrayList;
import java.util.List;

import domainTheory.Taxon;

/**
 * @author Armando
 *
 */
public class ValueDescriptor extends ArrayList<Double> {
	private boolean asRange;
	private String measuringUnit;	

	/**
	 * 
	 */
	public ValueDescriptor() {
		// TODO Auto-generated constructor stub
		// this is set up for weighted values as default
		this.add(null);
		this.add(0.0);
		setAsRange(false);
		setMeasuringUnit(null);
	}

	/**
	 * @see "Método setAsRange del protocolo setting-range values en SUKIA SmallTalk"
	 * @param asRange
	 */
	private void setAsRange(Boolean asRange) {
		this.asRange = asRange;
	}

	/**
	 * @see "Método asRange del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public Boolean getAsRange() {
		return asRange;
	}

	/**
	 * aMeasuringUnit can be #cm, #mm, #inch
	 * @param measuringUnit
	 */
	public void setMeasuringUnit(String aMeasuringUnit) {
		if (MeasuringUnit.exists(aMeasuringUnit))
			return;

		this.measuringUnit = aMeasuringUnit;
	}

	/**
	 * @see "Método measuringUnit del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public String getMeasuringUnit() {
		return measuringUnit;
	}
	
	/**
	 * @see "Método taxonList del protocolo accessing-general en SUKIA SmallTalk"
	 * @return
	 */
	public List<Double> taxonList() {
		List<Double> taxonList;
		
		taxonList = new ArrayList<Double>();

		for (int i = 3; i <= this.size(); i++)
			taxonList.add(this.get(i));

		return taxonList;
	}

	/**
	 * @see "Método lowerBound del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public double lowerBound() {
		if (this.getAsRange() == true)
			return this.get(1);
		else return -1;
	}

	/**
	 * @see "Método upperBound del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public double upperBound() {
		if (this.getAsRange() == true)
			return this.get(2);
		else return -1;
	}
	
	/**
	 * @see "Método value del protocolo accessing-weighted values en SUKIA SmallTalk"
	 * @return
	 */
	public double value() {
		if (this.getAsRange() == true)
			return -1;
		else return this.get(1);
	}

	/**
	 * @see "Método weight del protocolo accessing-weighted values en SUKIA SmallTalk"
	 * @return
	 */
	public double weight() {
		if (this.getAsRange() == true)
			return -1;
		else return this.get(2);
	}
	
	public void addTaxon(Taxon aTaxon) {
		boolean range;
		String mUnit;
		
		// Copy the current values of the instance variables asRange and measuringUnit
		range = this.getAsRange();
		mUnit = this.getMeasuringUnit();
		
		/* Add the taxon. What happens here is that, if the receiver needs to open space to add the new taxon, 
		 the parent class will do so by creating a new instance of the receiver with enhanced space, copying the 
		 elements of the receiver to that new instance, adding the new taxon using the enhanced space, and 
		 replacing the receiver for that new instance. The problem with this procedure is that, once the new instance 
		 of the receiver is created, asRange and measuringUnit will have default values (from the initialize method), 
		 which may not correspond to the values that the receiver had before adding the taxon */
		
		this.add(aTaxon);
		
		/* To avoid the befre-mentioned problem with adding a new taxon, set asRange and measuringUnit with
		 the values they had before the method add: aTaxon was called */
		asRange = range;
		measuringUnit = mUnit;
	}
}
