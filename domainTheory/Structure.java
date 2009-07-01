/**
 * 
 */
package domainTheory;

import java.util.ArrayList;
import java.util.List;

import values.Value;

/**
 * @author Armando
 *
 */
public class Structure {
	private String name;
	private Double weight;
	private List<Attribute> attributes;
	

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Structure() {
		// TODO Auto-generated constructor stub
		setName(null);
		setWeight(0.0);
		// Pendiente el ordenamiento
		setAttributes(new ArrayList<Attribute>());
	}

	/**
	 * @see "Método name: del protocolo adding en SUKIA SmallTalk"
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @see "Método name del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * aWeight must be a floating point number between 0 and 1.
	 * NOTE: 0 weight value means no relevance for this structure; 1 weight value means the most relevant
	 * @see "Método weight del protocolo adding en SUKIA SmallTalk"
	 * @param weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @see "Método weight del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * Método de instancia agregado
	 * @param attributes
	 */
	private void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * @see "Método attribute: del protocolo adding en SUKIA SmallTalk"
	 * @param anAttribute
	 */
	public void addAnAttribute(Attribute anAttribute) {
		if (includes(anAttribute.getName()))
			return;
		
		attributes.add(anAttribute);
	}

	/**
	 * @see "Método attributes del protocolo accessing en SUKIA SmallTalk"
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	/**
	 * @see "Método attributeWith: del protocolo accessing en SUKIA SmallTalk"
	 * @param anAttributeName
	 * @return
	 */
	public Attribute getAttributeWith(String anAttributeName) {
		for (int i = 1; i <= attributes.size(); i++) {
			if (attributes.get(i).getName().equals(anAttributeName))
				return attributes.get(i);
		}
		
		return null;
	}
	
	/**
	 * @see "Método copyFrom:referencing del protocolo copíng en SUKIA SmallTalk"
	 * @param aStructure
	 * @param aTaxon
	 */
	public void copy(Structure aStructure, Taxon aTaxon) {
		Attribute oa, na;

		this.setName(aStructure.getName());
		this.setWeight(aStructure.getWeight());
		
		for (int i = 1; i <= aStructure.getAttributes().size(); i++) {
			oa = aStructure.getAttributes().get(i);
			na = new Attribute();
			na.copy(oa, aTaxon);
			addAnAttribute(na);
		}
	}
	
	/**
	 * The purpose of this method is to create a list of attribute-unique SAVDescriptors.
	 * Since this list is to be used in the identification process, this program assumes
	 * that the receiver has been created to collect the user's description. In other words,
	 * it is neither a Structure index nor a taxon's Structure.  For this reason, the receiver's
	 * attributes must contain a single value, AND no value descriptor can be a range.
	 * NOTE: The argument aDummyName is to be ignored. This argument was included
	 * just for polymorphism reasons.
	 * Returns: nil - if the receiver has no attributes, OR more than one value container
	 * is detected (it is assumed that the receiver is a Structure index), OR
	 * more than ONE value descriptor is detected, OR the value descriptor
	 * is a range.
	 * decription: a non-empty list of SAVDescriptors.
	 * @see "Método createSAVDescription del protocolo descripción generation en SUKIA SmallTalk" 
	 * @param aTaxonomicGroupName
	 * @return
	 */
	// Pendiente de traducir
	public List<Structure> createSAVDescription(String aDummyName) {
		List<Structure> description;
		Attribute a;
		
		// Make sure there's at least one attribute
		if (getAttributes().isEmpty())
			return null;

		// Check the first attribute, to see if its value has more than one value descriptor container
		if (!(getAttributes().get(1).getValues().size() == Attribute.oneLevel()))
			return null;

		// Create the description holder
		description = new ArrayList<Structure>();

		// Scan the receiver's attributes
		for (int i = 1; i <= this.getAttributes().size(); i++) {
			// Get the next attribute and set of value descriptors
			a = this.getAttributes().get(i);
			
		}
		
		return null;
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param anAttributeName
	 * @return
	 */
	public boolean includes(String anAttributeName) {
		for (int i = 1; i <= this.getAttributes().size(); i++) {
			if (this.getAttributes().get(i).getName().equals(anAttributeName))
				return true;
		}
		
		return false;
	}
}
