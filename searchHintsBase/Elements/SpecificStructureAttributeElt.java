/**
 * @see "Categoría Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Attribute;


/**
 * @author Armando
 *
 */
public class SpecificStructureAttributeElt {
	private String structureName;
	private List<SpecificAttribute> specificAttributeList;


	/**
	 * Initialize the frequent grouping heuristic element.  The instance variable 'value' will NOT be an instance of Value,
	 * because (at least for now) it doesn't need to store extra information, such as value levels, weights or ranges. Thus,
	 * the contents of the variable value will be a discreet symbol (no ranges).
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SpecificStructureAttributeElt() {
		setStructureName(null);
		setAttributes(new ArrayList<SpecificAttribute>()); 
	}

	/**
	 * @see "Método structureName: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructureName
	 */
	public void setStructureName(String aStructureName) {
		this.structureName = aStructureName;
	}

	/**
	 * @see "Método structureName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * Método de instancia agregado
	 * @param specificAttributeList
	 */
	public void setAttributes(List<SpecificAttribute> specificAttributeList) {
		this.specificAttributeList = specificAttributeList;
	}

	/**
	 * @see "Método specificAttribute del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<SpecificAttribute> getAttributes() {
		return specificAttributeList;
	}
	
	/**
	 * Checks if the attribute name of the argument is already included in the specific attributes list.
	 * If the attribute name already exists, then its frequency is incremented by the frequency value
	 * contained in the argument. Else, the argument is included in the specific attributes list.
	 * @see "Método specificAttribute: del protocolo adding en SUKIA SmallTalk"
	 * @param aSpecificAttribute
	 * @return
	 */
	public boolean addAttribute(SpecificAttribute aSpecificAttribute) {
		for (int i = 1; i <= this.getAttributes().size(); i++) {
			if (aSpecificAttribute.getName().equals(this.getAttributes().get(i-1).getName())) {
				this.getAttributes().get(i-1).incrementFrequencyBy(aSpecificAttribute.getFrequency());
				return false;
			}
		}
		this.getAttributes().add(aSpecificAttribute);
		
		return true;
	}
	
	public boolean contains(Attribute anAttribute) {
		for (int i = 1; i <= this.getAttributes().size(); i++) {
			if (this.getAttributes().get(i-1).getName().equals(anAttribute.getName()))
				return true;
		}
		
		return false;
	}
	
	public SpecificAttribute getListElement(String anAttributeName) {
		for (int i = 1; i <= this.getAttributes().size(); i++) {
			if (this.getAttributes().get(i-1).getName().equals(anAttributeName))
				return this.getAttributes().get(i-1);
		}
		
		return null;
	}
}
