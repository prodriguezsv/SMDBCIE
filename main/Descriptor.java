package main;



/**
 * Descriptor es un elemento descriptivo de una descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ningún par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
public abstract class Descriptor implements jade.content.Concept {
	private static final long serialVersionUID = 5687089858125395496L;
	protected String _internalInstanceName = null;
	protected String attribute;
	protected String structure;
	
	public Descriptor() {
		_internalInstanceName = "";
	}

    @Override
    public String toString() {
    	return _internalInstanceName;
    }
    
    public void setAttribute(String attribute) {
    	this.attribute = attribute;
    }
    
    public String getAttribute() {
    	return attribute;
    }

   
   
   public void setStructure(String structure) {
    this.structure = structure;
   }
   public String getStructure() {
     return structure;
   }
}
