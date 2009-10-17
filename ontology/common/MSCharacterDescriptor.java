/**
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.common;

import jade.util.leap.ArrayList;
import jade.util.leap.Iterator;
import jade.util.leap.List;

/**
 * Descriptor es un elemento descriptivo de la descripción de un caso: el par attribute-value.
 * Generaliza conceptos en normas.
 * Todas las raíces de red (especializaciónes de normas) contienen un descriptor de la forma (nil, nil),
 * ya que ning&uacute;n par attribute-value particular generaliza todos los casos bajo ellos.
 * 
*/
@SuppressWarnings("serial")
public class MSCharacterDescriptor extends QualitativeCharacterDescriptor {
	private List value;
	
	public MSCharacterDescriptor() {
		value = new ArrayList();
	}

	public MSCharacterDescriptor(String instance_name) {
		super(instance_name);
		value = new ArrayList();
	}
	
	/**
	 * Class instance invariant: self MUST always have exactly two values. The first
	 * element corresponds to the descriptor's attribute, and the second one to the value.
	 * Extreme care should be taken when using this method, as it assumes an empty self."
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public MSCharacterDescriptor(String aStructure, String anAttribute, List aValue) {
		super(aStructure, anAttribute);
		this.setValue(aValue);
	}
	
   public void addScore(String elem) { 
     value.add(elem);
   }
   public boolean removeScore(Object elem) {
     boolean result = value.remove(elem);
     return result;
   }
   public void clearAllScore() {
     value.clear();
   }
   public Iterator getAllScore() {return value.iterator(); }
   
   public void setValue(Object value) {
	   this.value = (List) value;
   }
	
   public List getValue() {
	   return (List)value;
   }
}
