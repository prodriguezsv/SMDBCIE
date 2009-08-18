/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package ontology.common;

import ontology.CBR.Case;
import ontology.taxonomy.Taxon;

/**
 * Descriptor es un elemento descriptivo de una descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ningún par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public abstract class Descriptor<T> implements Comparable<Descriptor<T>> {
	private Object associatedObject;
	private String structure;
	private String attribute;
	private T value;
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Descriptor() {
		this.setStructure(null);
		this.setAttribute(null);
		this.setValue(null);
	}
	
	/**
	 * Método agregado
	 */
	public Descriptor(String aStructure, String aAttribute, T aValue) {
		this.setStructure(aStructure);
		this.setAttribute(aAttribute);
		this.setValue(aValue);
	}
	
	/**
	 * Método agregado
	 */
	public Descriptor(Object associatedObject, String aStructure, String aAttribute, T aValue) {
		this.setAssociatedObject(associatedObject);
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
	 * @see "Método addStructure:Attribute:Value: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructure
	 * @param anAttribute
	 * @param aValue
	 */
	public void set(Object associatedObject, String aStructure, String anAttribute, T aValue) {
		this.setAssociatedObject(associatedObject);
		this.setStructure(aStructure);
		this.setAttribute(anAttribute);
		this.setValue(aValue);
	}
	
	/**
	 * 
	 * @param associatedObject
	 */
	public void setAssociatedObject(Object associatedObject) {
		if ((associatedObject instanceof Case) || (associatedObject instanceof Taxon))
			return;
		
		this.associatedObject = associatedObject;
	}

	/**
	 * 
	 * @return
	 */
	public Object getAssociatedObject() {
		return associatedObject;
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
	public int compareTo(Descriptor<T> aDescriptor) {
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
		if (!(aDescriptor instanceof Descriptor)) return false;
		
		if (this.getStructure().equals(((Descriptor<Object>)aDescriptor).getStructure()) &&
				this.getAttribute().equals(((Descriptor<Object>)aDescriptor).getAttribute()) &&
				this.getValue().equals(((Descriptor<Object>)aDescriptor).getValue()))
			return true;
		else return false;
	}
}
