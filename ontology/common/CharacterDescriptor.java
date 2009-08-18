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
public class CharacterDescriptor<T> implements Comparable<CharacterDescriptor<T>> {
	private String structure;
	private String attribute;
	private T value;
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public CharacterDescriptor() {
		this.setStructure(null);
		this.setAttribute(null);
		this.setValue(null);
	}
	
	/**
	 * Método agregado
	 */
	public CharacterDescriptor(String aStructure, String aAttribute, T aValue) {
		this.setStructure(aStructure);
		this.setAttribute(aAttribute);
		this.setValue(aValue);
	}

	/**
	 * @see "Método addStructure:Attribute:Value: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructure
	 * @param anAttribute
	 * @param aValue
	 */
	public void set(String aStructure, String anAttribute, T aValue) {
		this.setStructure(aStructure);
		this.setAttribute(anAttribute);
		this.setValue(aValue);
	}
	
	/**
	 * @see "Método addStructure: del protocolo adding-private en SUKIA SmallTalk"
	 * @param structure
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	/**
	 * @see "Método structure del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructure() {
		return structure;
	}
    
	/**
	 * @see "Método addAttribute: del protocolo adding-private en SUKIA SmallTalk"
	 * @param attribute
	 */
    public void setAttribute(String attribute) {
    	this.attribute = attribute;
    }
    
    /**
     * @see "Método attribute del protocolo accessing en SUKIA SmallTalk"
     * @return
     */
    public String getAttribute() {
    	return attribute;
    }

    /**
     * @see "Método value del protocolo accessing en SUKIA SmallTalk"
     * @return
     */
	public T getValue() {
		return value;
	}

	/**
	 * @see "Método addValue: del protocolo adding-private en SUKIA SmallTalk"
	 * @param value
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Método de instancia agregado
	 */
	public int compareTo(CharacterDescriptor<T> aDescriptor) {
		return this.getStructure().concat(this.getAttribute()).compareTo(aDescriptor.getStructure().concat(aDescriptor.getAttribute()));
	}
	
	/**
	 * Método agregado
	 * @param aDescriptor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object aDescriptor) {
		if (aDescriptor == null) return false;
		if (!(aDescriptor instanceof CharacterDescriptor)) return false;
		
		if (this.getStructure().equals(((CharacterDescriptor<Object>)aDescriptor).getStructure()) &&
				this.getAttribute().equals(((CharacterDescriptor<Object>)aDescriptor).getAttribute()) &&
				this.getValue().equals(((CharacterDescriptor<Object>)aDescriptor).getValue()))
			return true;
		else return false;
	}
}
