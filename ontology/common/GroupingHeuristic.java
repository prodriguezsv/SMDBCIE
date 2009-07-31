/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.common;

import java.util.List;

import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicLevels;
import ontology.values.RangeDescriptor;
import ontology.values.SingleDescriptor;
import ontology.values.Value;
import ontology.values.ValueDescriptor;



/**
 * @author Armando
 *
 */
public class GroupingHeuristic implements Comparable<GroupingHeuristic> {
	private String name;
	private double weight;
	private Value values;
	

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 * @see "Método newWithOneLevel del protocolo instance creation en SUKIA SmallTalk"
	 */
	public GroupingHeuristic() {
		setName(null);
		setWeight(0.0);
		setValues(new Value());
	}
	
	/**
	 * @see "Método oneLevel del protocolo de clase one level en SUKIA SmallTalk"
	 * @return
	 */
	public static int oneLevel() {
		return 0;
	}

	/**
	 * @see "Método name: del protocolo adding en SUKIA SmallTalk"
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see "Método name del protocolo accesing en SUKIA SmallTalk"
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * aWeight must be a floating point number between 0 and 1.
	 * NOTE: 0 weight value means no relevance for this structure; 1 weight value means the most relevant
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @see "Método weight del protocolo accesing en SUKIA SmallTalk"
	 * @return
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Método de intancia agregado
	 * @param values
	 */
	private void setValues(Value values) {
		this.values = values;
	}

	/**
	 * @see "Método values del protocolo accesing en SUKIA SmallTalk"
	 * @return
	 */
	public Value getValues() {
		return values;
	}
	
	/**
	 * @see "Método copyFrom:referencing del protocolo copíng en SUKIA SmallTalk"
	 * @param aGroupingHeuristic
	 * @param aTaxon
	 */
	public <T> void addValues(GroupingHeuristic aGroupingHeuristic, Taxon aTaxon) {
		List<ValueDescriptor> vList;
		ValueDescriptor ovd, nvd;

		if (values.size() < aGroupingHeuristic.getValues().size())
			return;

		name = aGroupingHeuristic.getName();
		weight = aGroupingHeuristic.getWeight();
		
		for (int i = 1; i <= aGroupingHeuristic.getValues().size(); i++) {
			vList = aGroupingHeuristic.getValues().get(i-1);
			
			for (int j = 1; j <= vList.size(); j++) {
				ovd = vList.get(j-1);
				if (ovd instanceof SingleDescriptor)
					nvd = new SingleDescriptor<T>();
				else {
					nvd = new RangeDescriptor();
				}
				
				nvd.addValues(ovd, aTaxon);
				if (this.getValues().size() == aGroupingHeuristic.getValues().size())
					this.getValues().addValueDescriptor(nvd, TaxonomicLevels.getLevels().get(i+1));
				else
					this.getValues().addValueDescriptor(nvd, aTaxon.getLevel());
				
			}
			
		}
	}
	
	/**
	 * The purpose of this method is to create a list of one SAVDescriptor. Since this list 
	 * is to be used in the identification process, this program assumes that the receiver
	 * has been created to collect the user's description. In other words, it is neither a
	 * GroupingHeuristic index nor a taxon's GroupingHeuristic.  For this reason, the receiver's
	 * values list must contain ONE value descriptor, AND the value descriptor can not be a range.
	 * Returns: nil - if more than one value container is detected (it is assumed that the receiver
	 * is a GroupingHeuristic index), OR more than ONE value descriptor is detected,
	 * OR the value descriptor is a range.
	 * decription: a non-empty list of SAVDescriptors.
	 * @see "Método createSAVDescription del protocolo descripción generation en SUKIA SmallTalk" 
	 * @param aTaxonomicGroupName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Description<Descriptor<Object>> createSAVDescription(String aTaxonomicGroupName) {
		Description<Descriptor<Object>> description;
		List<ValueDescriptor> vdList;
		ValueDescriptor vd;
		Descriptor<Object> d;
		
		// Check if its value has more than one value descriptor container
		if (!(this.getValues().size() == 1))
			return null;

		// Create the description holder
		description = new Description<Descriptor<Object>>();

		// Get the set of value descriptors
		vdList = this.getValues().getValueDescriptors(TaxonomicLevels.getLevels().get(GroupingHeuristic.oneLevel()));

		// Make sure that the value descriptor list only contains ONE item
		if (!(vdList.size() == 1)) return null;

		// Get the value descriptor and make sure it isn't a range descriptor
		vd = vdList.get(0);
		
		if (vd instanceof RangeDescriptor) return null;
		
		// Create the new SAVDescriptor and assign its values
		d = new Descriptor<Object>();
		d.set(aTaxonomicGroupName, this.getName(), ((SingleDescriptor<Object>)vd).getValue());
					
		description.add(d);
		
		return description;
	}
	
	/**
	 * Método de instancia agregado
	 */
	public int compareTo(GroupingHeuristic aGroupingHeuristic) {
		return this.getName().compareTo(aGroupingHeuristic.getName());
	}
}
