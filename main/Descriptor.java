/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

/**
 * Descriptor es un elemento descriptivo de una descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ningún par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public class Descriptor<T> {
	protected String attribute;
	protected T value;
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Descriptor() {
		this.setAttribute(null);
		this.setValue(null);
	}
	
	/**
	 * Sets self to default values: self = (nil, nil)
	 * @see "Método clear del protocolo initializing en SUKIA SmallTalk"
	 */
	public void clear() {
		this.setAttribute(null);
		this.setValue(null);
	}
    
	/**
	 * @see "Método addAttribute: del protocolo adding-private en SUKIA SmallTalk"
	 * @param attribute
	 */
    private void setAttribute(String attribute) {
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
	private void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * @see "Método addAttribute:withValue: del protocolo adding en SUKIA SmallTalk"
	 * @param anAttribute
	 * @param aValue
	 */
	public void add(String anAttribute, T aValue) {
		this.setAttribute(anAttribute);
		this.setValue(aValue);
	}
}
