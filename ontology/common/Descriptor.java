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
@SuppressWarnings("serial")
public abstract class Descriptor implements jade.content.Concept, Comparable<Descriptor> {
	private String structure;
	private String attribute;
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Descriptor() {
		this._internalInstanceName = "";
	}

	private String _internalInstanceName = null;

	public Descriptor(String instance_name) {
		this._internalInstanceName = instance_name;
	}
	
	public String get_internalInstanceName() {
		return _internalInstanceName;
	}

	/**
	 * Constructor alternativo
	 */
	public Descriptor(String aStructure, String anAttribute) {
		this.setStructure(aStructure);
		this.setAttribute(anAttribute);
	}
	
	/**
	 * @see "M&eacute;todo addStructure:Attribute:Value: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructure
	 * @param anAttribute
	 * @param aValue
	 */
	public <T> void set(String aStructure, String anAttribute, T aValue) {
		this.setStructure(aStructure);
		this.setAttribute(anAttribute);
		this.setValue(aValue);
	}
		
	/**
	 * @see "M&eacute;todo addStructure: del protocolo adding-private en SUKIA SmallTalk"
	 * @param structure
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	/**
	 * @see "M&eacute;todo structure del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructure() {
		return structure;
	}
    
	/**
	 * @see "M&eacute;todo addAttribute: del protocolo adding-private en SUKIA SmallTalk"
	 * @param attribute
	 */
    public void setAttribute(String attribute) {
    	this.attribute = attribute;
    }
    
    /**
     * @see "M&eacute;todo attribute del protocolo accessing en SUKIA SmallTalk"
     * @return
     */
    public String getAttribute() {
    	return attribute;
    }

    /**
     * @see "M&eacute;todo value del protocolo accessing en SUKIA SmallTalk"
     * @return
     */
	public abstract Object getValue();

	/**
	 * @see "M&eacute;todo addValue: del protocolo adding-private en SUKIA SmallTalk"
	 * @param value
	 */
	public abstract void setValue(Object value);
	
	/**
	 * Compara dos instancias de Descriptor combinando el nombre de la estructura y su atributo
	 */
	public int compareTo(Descriptor aDescriptor) {
		//Descriptor aDescriptor = (Descriptor) d;
		return this.getStructure().concat(this.getAttribute()).compareTo(aDescriptor.getStructure().concat(aDescriptor.getAttribute()));
	}
	
	/**
	 * Determina la igualdad entre dos descriptores
	 * @param aDescriptor
	 * @return
	 */
	public boolean equals(Object aDescriptor) {
		if (aDescriptor == null) return false;
		if (!(aDescriptor instanceof Descriptor)) return false;
		
		if (this.getStructure().equals(((Descriptor)aDescriptor).getStructure()) &&
				this.getAttribute().equals(((Descriptor)aDescriptor).getAttribute()) &&
				this.getValue().equals(((Descriptor)aDescriptor).getValue()))
			return true;
		else return false;
	}
	
	/**
	 * Calcula el c&oacute;digo hash para una instancia de Descriptor
	 */
	public int hashCode(){
		return this.getStructure().hashCode() + this.getAttribute().hashCode() + this.getValue().hashCode();
	}
	
	/**
	 * 
	 */
	public String toString() {
		return  "(" + this.getStructure()+ "; " + this.getAttribute() + "; " + this.getValue().toString() + ")";
	}
}
