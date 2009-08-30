/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package ontology.common;

/**
 * Descriptor es un elemento descriptivo de una descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ningún par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public abstract class CharacterDescriptor extends Descriptor {
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public CharacterDescriptor() {
		super();
	}
	
	/**
	 * Método agregado
	 */
	public CharacterDescriptor(String aStructure, String anAttribute) {
		super(aStructure, anAttribute);
	}
	
	/**
	 * Método agregado
	 */
	public CharacterDescriptor(Object associatedObject, String aStructure, String anAttribute) {
		super(associatedObject, aStructure, anAttribute);
	}
}
