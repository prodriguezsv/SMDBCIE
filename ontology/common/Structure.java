/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicLevels;
import ontology.values.RangeDescriptor;
import ontology.values.SingleDescriptor;
import ontology.values.ValueDescriptor;



/**
 * @author Armando
 *
 */
public class Structure implements Comparable<Structure> {
	private String name;
	private Double weight;
	private List<Attribute> attributes;
	

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Structure() {
		setName(null);
		setWeight(0.0);
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
		Collections.sort(attributes);
	}
	
	/**
	 * @see "Método attribute: del protocolo adding en SUKIA SmallTalk"
	 * @param anAttribute
	 */
	public void addAttribute(Attribute anAttribute) {
		if (getAttribute(anAttribute.getName())==null)
			return;
		
		attributes.add(anAttribute);
		Collections.sort(attributes);
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
	public Attribute getAttribute(String anAttributeName) {
		for (int i = 1; i <= attributes.size(); i++) {
			if (attributes.get(i-1).getName().equals(anAttributeName))
				return attributes.get(i-1);
		}
		
		return null;
	}
	
	/**
	 * @see "Método copyFrom:referencing del protocolo copíng en SUKIA SmallTalk"
	 * @param aStructure
	 * @param aTaxon
	 */
	public void addAtributes(Structure aStructure, Taxon aTaxon) {
		Attribute oa, na;

		this.setName(aStructure.getName());
		this.setWeight(aStructure.getWeight());
		
		for (int i = 1; i <= aStructure.getAttributes().size(); i++) {
			oa = aStructure.getAttributes().get(i-1);
			na = new Attribute();
			na.addValues(oa, aTaxon);
			addAttribute(na);
		}
		
		Collections.sort(attributes);
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
	@SuppressWarnings("unchecked")
	public Description<Descriptor<Object>> createDescription(String aDummyName) {
		Description<Descriptor<Object>> description;
		Attribute a;
		List<ValueDescriptor> vdList;
		ValueDescriptor vd;
		Descriptor<Object> d;
		
		// Make sure there's at least one attribute
		if (getAttributes().isEmpty())
			return null;

		// Check the first attribute, to see if its value has more than one value descriptor container
		if (!(getAttributes().get(0).getValues().size() == 1))
			return null;

		// Create the description holder
		description = new Description<Descriptor<Object>>();

		// Scan the receiver's attributes
		for (int i = 1; i <= this.getAttributes().size(); i++) {
			// Get the next attribute and set of value descriptors
			a = this.getAttributes().get(i-1);
			vdList = a.getValues().getValueDescriptors(TaxonomicLevels.getLevels().get(Attribute.oneLevel()));

			// Make sure that the value descriptor list only contains ONE item
			if (!(vdList.size() == 1)) return null;

			// Get the value descriptor and make sure it isn't a range descriptor
			vd = vdList.get(0);
			if (vd instanceof RangeDescriptor) return null;

			// Create the new SAVDescriptor and assign its values
			d = new Descriptor<Object>();
			d.set(this.getName(), a.getName(), ((SingleDescriptor<Object>)vd).getValue());
					
			description.add(d);
		}
		
		return description;
	}
	
	/**
	 * Método de instancia agregado
	 */
	public int compareTo(Structure aStructure) {
		return this.getName().compareTo(aStructure.getName());
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param anAttributeName
	 * @return
	 */
	public boolean contains(String anAttributeName) {
		if (getAttribute(anAttributeName) == null)
			return false;
		else return true;
	}
}
