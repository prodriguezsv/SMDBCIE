/**
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.common;

import java.util.List;

/**
 * Descriptor es un elemento descriptivo de la descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ning&uacute;n par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public class MSCharacterDescriptor extends QualitativeCharacterDescriptor {
	private List<String> value;
	
	public MSCharacterDescriptor() {
		
	}
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public MSCharacterDescriptor(String aStructure, String anAttribute, List<String> aValue) {
		super(aStructure, anAttribute);
		this.setValue(aValue);
	}

	@SuppressWarnings("unchecked")
	protected void setValue(Object value) {
		this.value = (List<String>)value;
	}

	public List<String> getValue() {
		return (List<String>)value;
	}
}
