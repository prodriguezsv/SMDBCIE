/**
 * @see "Categor&iacute;a Sukia Values de SUKIA Smalltalk"
 */
package ontology.common;


/**
 * Clase modificada
 * @see "Clase ValueDescriptor"
 * @author Armando
 */
@SuppressWarnings("serial")
public abstract class Value implements jade.content.Concept {
	private String measuringUnit;
		
	private String _internalInstanceName = null;
		
	public Value() {
	  this._internalInstanceName = "";
	}
		
	public Value(String instance_name) {
	  this._internalInstanceName = instance_name;
	}
	  
	public String get_internalInstanceName() {
		return _internalInstanceName;
	}

	/**
	 * aMeasuringUnit can be #cm, #mm, #inch
	 * @see "M&eacute;todo measuringUnit: del protocolo setting-range values (private) en SUKIA SmallTalk"
	 * @param measuringUnit
	 */
	public void setMeasuringUnit(String aMeasuringUnit) {
		boolean iscontained = false;
		
		for (MeasuringUnit mu:MeasuringUnit.values()) {
			if (mu.getMeasuringUnit().equals(aMeasuringUnit)) {
				iscontained = true;
				break;
			}
		}
		
		if (iscontained) this.measuringUnit = aMeasuringUnit;;
	}

	/**
	 * @see "M&eacute;todo measuringUnit del protocolo accessing-range values en SUKIA SmallTalk"
	 * @return
	 */
	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public abstract boolean equals(Object aValue);
}
