/**
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.common;


/**
 * Descriptor es un elemento descriptivo de la descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ning&uacute;n par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public class RVHeuristicDescriptor extends QualitativeHeuristicDescriptor {
	RangeValue value;
	
	public RVHeuristicDescriptor() {
		
	}
	
	private static final long serialVersionUID = -3087841394215437493L;

    public RVHeuristicDescriptor(String instance_name) {
    	super(instance_name);
    }
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RVHeuristicDescriptor(String aStructure, String anAttribute, RangeValue aValue) {
		super(aStructure, anAttribute);
		this.setValue(aValue);
	}
	
	public void setValue(Object value) {
		this.value = (RangeValue)value;
	}

	public RangeValue getValue() {
		return (RangeValue)value;
	}
}
