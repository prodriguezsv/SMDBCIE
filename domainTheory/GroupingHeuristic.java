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
public class GroupingHeuristic {
	private String name;
	private double weight;
	private Value values;
	

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 * @see "Método newWithOneLevel del protocolo instance creation en SUKIA SmallTalk"
	 */
	public GroupingHeuristic() {
		// TODO Auto-generated constructor stub
		setName(null);
		setWeight(0.0);
		setValues(new Value());
	}
	
	/**
	 * @see "Método oneLevel del protocolo de clase one level en SUKIA SmallTalk"
	 * @return
	 */
	public static int oneLevel() {
		return 1;
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
	 * @param attributes
	 */
	private void setValues(Value attributes) {
		this.values = attributes;
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
	// Pendiente de traducir
	public void copy(GroupingHeuristic aGroupingHeuristic, Taxon aTaxon) {
		
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
	// Pendiente de traducir
	public List<Structure> createSAVDescription(String aTaxonomicGroupName) {
		List<Structure> description;
		
		// Check if its value has more than one value descriptor container
		
		return null;
	}
}
