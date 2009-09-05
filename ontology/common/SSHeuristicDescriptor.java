/**
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.common;

/**
 * Descriptor es un elemento descriptivo de la descripci�n de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las ra�ces de red (especializaci�nes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ning&uacute;n par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public class SSHeuristicDescriptor extends QualitativeHeuristicDescriptor {
	String value;
	
	public SSHeuristicDescriptor() {
		
	}
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SSHeuristicDescriptor(String aStructure, String aAttribute, String aValue) {
		super(aStructure, aAttribute);
		this.setValue(aValue);
	}
	
	/**
	 * Constructor alternativo
	 */
	public SSHeuristicDescriptor(Object associatedObject, String aStructure, String anAttribute, String aValue) {
		super(associatedObject, aStructure, anAttribute);
		this.setValue(aValue);
	}
	
	protected void setValue(Object value) {
		this.value = (String)value;
	}

	public String getValue() {
		return (String)value;
	}
}