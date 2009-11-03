/**
 * @see "Categoría Sukia Values de SUKIA Smalltalk"
 */
package ontology.common;

import jade.content.abs.AbsConcept;
import jade.content.abs.AbsObject;
import jade.content.abs.AbsPrimitive;
import jade.content.abs.AbsTerm;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;


/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class RangeValue extends Value {
	private double lowerBound;
	private double upperBound;

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
	
	public void externalise(AbsObject absObj, Ontology onto) throws OntologyException, jade.content.onto.OntologyException {
        try {
            AbsConcept abs = (AbsConcept) absObj;
            abs.set(CommonTerminologyOntology.VALUE_MEASURINGUNIT, (AbsTerm) onto.fromObject(getMeasuringUnit()));
            AbsPrimitive aPrimitive = new AbsPrimitive(BasicOntology.FLOAT);
            AbsPrimitive aPrimitive2 = new AbsPrimitive(BasicOntology.FLOAT);
            aPrimitive.set(getLowerBound());
            aPrimitive2.set(getUpperBound());
            abs.set(CommonTerminologyOntology.RANGEVALUE_LOWERBOUND, aPrimitive);
            abs.set(CommonTerminologyOntology.RANGEVALUE_UPPERBOUND, aPrimitive2);
        } catch (ClassCastException cce) {
            throw new OntologyException("Error externalising Value");
        }
    }

    public void internalise(AbsObject absObj, Ontology onto) throws UngroundedException, OntologyException, jade.content.onto.OntologyException {
        try {
            AbsConcept abs = (AbsConcept) absObj;
            setMeasuringUnit((String)onto.toObject(abs.getAbsObject(CommonTerminologyOntology.VALUE_MEASURINGUNIT)));
            setLowerBound(Double.parseDouble((String)onto.toObject(abs.getAbsObject(CommonTerminologyOntology.RANGEVALUE_LOWERBOUND))));
            setUpperBound(Double.parseDouble((String)onto.toObject(abs.getAbsObject(CommonTerminologyOntology.RANGEVALUE_UPPERBOUND))));
        } catch (ClassCastException cce) {
            throw new OntologyException("Error internalising Value");
        }
    }
}
