/**
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.common;

import ontology.values.SingleValue;

/**
 * Descriptor es un elemento descriptivo de la descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ning&uacute;n par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public class SVCharacterDescriptor extends QuantitativeCharacterDescriptor {
	SingleValue value;
	
	public SVCharacterDescriptor() {
		
	}
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SVCharacterDescriptor(String aStructure, String aAttribute, SingleValue aValue) {
		super(aStructure, aAttribute);
		this.setValue(aValue);
	}
	
	/**
	 * Constructor alternativo
	 */
	public SVCharacterDescriptor(Object associatedObject, String aStructure, String anAttribute, SingleValue aValue) {
		super(associatedObject, aStructure, anAttribute);
		this.setValue(aValue);
	}
	
	protected void setValue(Object value) {
		this.value = (SingleValue)value;
	}

	public SingleValue getValue() {
		return (SingleValue)value;
	}
}
