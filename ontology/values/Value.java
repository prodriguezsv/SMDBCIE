/**
 * @see "Categoría Sukia Values de SUKIA Smalltalk"
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
	 * @see "Método measuringUnit: del protocolo setting-range values (private) en SUKIA SmallTalk"
	 * @param measuringUnit
	 */
	public void setMeasuringUnit(MeasuringUnit aMeasuringUnit) {
		this.measuringUnit = aMeasuringUnit;
	}

	/**
	 * @see "Método measuringUnit del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public MeasuringUnit getMeasuringUnit() {
		return measuringUnit;
	}
	
	public abstract void addValues(Value aValue);
	public abstract boolean equals(Object aValue);
}
