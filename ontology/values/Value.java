/**
 * @see "Categor&iacute;a Sukia Values de SUKIA Smalltalk"
 */
package ontology.values;


/**
 * Clase modificada
 * @see "Clase ValueDescriptor"
 * @author Armando
 */
public abstract class Value {
	private MeasuringUnit measuringUnit;
		
	/**
	 * aMeasuringUnit can be #cm, #mm, #inch
	 * @see "M&eacute;todo measuringUnit: del protocolo setting-range values (private) en SUKIA SmallTalk"
	 * @param measuringUnit
	 */
	protected void setMeasuringUnit(MeasuringUnit aMeasuringUnit) {
		this.measuringUnit = aMeasuringUnit;
	}

	/**
	 * @see "M&eacute;todo measuringUnit del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public MeasuringUnit getMeasuringUnit() {
		return measuringUnit;
	}

	public abstract boolean equals(Object aValue);
}
