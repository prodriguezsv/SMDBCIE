/**
 * 
 */
package domainTheory;

import java.util.ArrayList;
import java.util.List;

import values.ValueDescriptor;

/**
 * @author Armando
 *
 */
public class Taxon {
	private String level;
	private String name;
	private Taxon predecessor;
	private List<Taxon> sucessors;
	private List<Structure> SAVDescription;
	private List<GroupingHeuristic> GHDescription;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxon() {
		// TODO Auto-generated constructor stub
		setLevel(null);
		setName(null);
		setPredecessor(null);
		//Pendiente ordenamiento
		setSucessors(new ArrayList<Taxon>());
		//Pendiente ordenamiento
		setGHDescription(new ArrayList<GroupingHeuristic>());
		//Pendiente ordenamiento
		setSAVDescription(new ArrayList<Structure>());
	}

	/**
	 * @see "Método level: del protocolo adding en SUKIA SmallTalk"
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/***
	 * The name of any taxonomic level must be a sequnce of ByteSymbol characters, and it must
	 * be included in the class TaxonomicLevels (i.e., TaxonomicLevels includes: aLevel must be TRUE
	 * before the argument can be assigned to the instance variable 'level'.
	 * NOTE: This program assumes that the argument has been previously verified by the View-related objects.
	 * @see "Método level del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getLevel() {
		return level;
	}

	/***
	 * The name of  any taxon must be a single sequence of ByteSymbol characters. This program assumes
	 * that the argument 'aName' has been previously verified by the View-related objects. To avoid name
	 * confusion or duplications, without exception ALL NAMES MUST BE IN LOWERCASE LETTERS.
	 * Names belonging to the species level MUST have an underscore between the genus name and the
	 * epithet. The two main reasons for having species names in this format are:
	 * 1. Search speed: it's faster to compare against the species' instance variable 'name', than having to
	 * 	compare the epithet part against the species' 'name' and then asking its corresponding predecessor
	 * 	taxon (the genus) for it's name, before any comparison can be made.
	 * 2. In the unlikely event that a species Taxon ever became unlinked from the hierarchy, it would a lot
	 * 	easier to link it again to its parent genus if the name is complete (i..e, genus + epithet) that if the
	 * 	name consisted only of the epithet.
	 * Examples:
	 * 	For a family, the name might be: #fabaceae.
	 * 	For a genus: #lantana.
	 * 	For a species, the name MUST be composed of the corresponding genus name plus the epithet,
	 * 	separated by an undersore: #lantana_camara.
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
	 * @see "Método predecessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param predecessor
	 */
	private void setPredecessor(Taxon predecessor) {
		this.predecessor = predecessor;
	}

	/**
	 * @see "Método predecessor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxon getPredecessor() {
		return predecessor;
	}

	/**
	 * Método de instancia agregado
	 * @param sucessor
	 */
	public void setSucessors(List<Taxon> sucessor) {
		this.sucessors = sucessor;
	}

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Taxon> getSucessors() {
		return sucessors;
	}
	
	/**
	 * @see "Método sucessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param sucessor
	 */
	public void addSucessor(Taxon sucessor) {
		this.sucessors.add(sucessor);
	}

	/***
	 * Método de instancia agregado
	 * @param gHDescription
	 */
	public void setGHDescription(List<GroupingHeuristic> gHDescription) {
		GHDescription = gHDescription;
	}

	/**
	 * @see "Método GHdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<GroupingHeuristic> getGHDescription() {
		return GHDescription;
	}
	
	/**
	 * @see "Método GHDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aGroupingHeuristic
	 */
	public void addGHDescription(GroupingHeuristic aGroupingHeuristic) {
		if (includesGroupingHeuristic(aGroupingHeuristic.getName(), GHDescription))
			return;
		
		GHDescription.add(aGroupingHeuristic);
	}

	/***
	 * Método de instancia agregado
	 * @param sAVDescription
	 */
	public void setSAVDescription(List<Structure> sAVDescription) {
		SAVDescription = sAVDescription;
	}

	/**
	 * @see "Método SAVdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Structure> getSAVDescription() {
		return SAVDescription;
	}

	/**
	 * @see "Método SAVdescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructure
	 */
	public void addSAVDescription(Structure aStructure) {
		if (includesStructure(aStructure.getName(), SAVDescription))
			return;
		
		SAVDescription.add(aStructure);
	}
	
	/**
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 * @return
	 */
	public boolean includesStructure(String aName, List<Structure> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i).getName().equals(aName))
				return true;
		}
		
		return false;
	}
	
	/**
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 * @return
	 */
	public boolean includesGroupingHeuristic(String aName, List<GroupingHeuristic> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i).getName().equals(aName))
				return true;
		}
		
		return false;
	}
	
	/**
	 * @see "Método isSuccessorOf: del protocolo inhetitence en SUKIA SmallTalk"
	 */
	public boolean isSuccessorOf(Taxon aTaxon) {
		Taxon  predecessorTaxon;
		
		if (TaxonomicLevels.transformToIndex(level) <= TaxonomicLevels.transformToIndex(aTaxon.getLevel()))
			return false;
		predecessorTaxon = predecessor;
		while (!(predecessorTaxon.getLevel() == TaxonomicLevels.getRoot())) {
			if (predecessorTaxon.getName() == aTaxon.getName())
				return true;

			predecessorTaxon = predecessorTaxon.getPredecessor();
		}
		
		return false;
	}
	
	/**
	 * @see "Método linkTo: del protocolo linking en SUKIA SmallTalk"
	 * @param aTaxon
	 */
	public void linkTo(Taxon aTaxon) {
		predecessor = aTaxon;
		aTaxon.addSucessor(this);
	}
	
	/**
	 * @see "Método unlinkFromTheHierarchy del protocolo linking en SUKIA SmallTalk"
	 */
	public void unlinkFromTheHierarchy() {
		Taxon p;
		
		p = predecessor;
		for (int i = 1; i <= p.getSucessors().size(); i++) {
			if (p.getSucessors().get(i) == this) {
				 p.getSucessors().remove(i);
				 predecessor = null; 
			}
		}
	}
	
	/**
	 * @see "Método getAnObjectWith:in: del protocolo searching en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 */
	public Structure getAStructureWith(String aName, List<Structure> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i).getName().equals(aName)) 
				return aDescription.get(i);
		}
		
		return null;
	}
	
	/**
	 * @see "Método getAnObjectWith:in: del protocolo searching en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 */
	public GroupingHeuristic getAGroupingHeuristicWith(String aName, List<GroupingHeuristic> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i).getName().equals(aName)) 
				return aDescription.get(i);
		}
		
		return null;
	}
	
	/**
	 * If the receiver's GH description contains grouping heuristics with range values, then, for each one of those grouping heuristics:
	 * seek a grouping heuristic in the receiver's predecessor's GH description whose name matches the (receiver's) grouping heuristic name;
	 * if the grouping heuristic is found, seek a ValueDescriptor that is a range value;
	 * if the ValueDescriptor is found, determine if the measuring units are the same, and if the receiver's range value lies within  the ValueDescriptor
	 * range just found.
	 * If the ranges are consistent, return true. Else, return false (i.e., inconsistent ranges).
	 * f neither the first nor the second steps are satisfied, get the receiver's predecessor's predecessor, and start again.
	 * This method will stop when the predecessor's level is ROOT
	 * @see "Método GHRangesConsistentWith: del protocolo testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @return
	 */
	//Pendiente de traducir
	public boolean GHRangesConsistentWith(Taxon aParentTaxon) {
		GroupingHeuristic gh;
		ValueDescriptor vd;
		
		if (predecessor.getLevel() == TaxonomicLevels.getLevels().get(0))
			return true;
		// Parse the receiver's grouping heuristic (GH) description
		for (int i = 1; i <= GHDescription.size(); i++) {
			// Get the next grouping heuristic in the receiver's GH description
			gh = GHDescription.get(i);
			for (int j = 1; j <= gh.getValues().get(GroupingHeuristic.oneLevel()).size(); j++) {
				// Get the next value descriptor, and test if it's a range value
				
					
			}
					
		}	
		
		// The entire GH decription of the receiver was parsed and nothing was found in the GH description of all its
		// predecessors. Thus, assume there are no inconsistencies
		return true;
	}
	
	/**
	 * If the receiver's SAV description contains structures with attributes that have range values, then, for each one of those structures:
	 * seek a structure in the receiver's predecessor's SAV description whose name matches the (receiver's) structure name;
	 * if the structure is found, seek in the attributes of the found structure for an attribute that matches the (receiver's structure) attribute name;
	 * if the attribute is found, seek a ValueDescriptor that is a range value;
	 * if the ValueDescriptor is found, determine if the measuring units are the same, and if the receiver's range value lies within the ValueDescriptor
	 * range just found.
	 * If the ranges are consistent, return true. Else, return false (i.e., inconsistent ranges).
	 * If neither the first nor the second steps are satisfied, get the receiver's predecessor's predecessor, and start again.
	 * This method will stop when the predecessor's level is ROOT
	 * @see "Método SAVRangesConsistentWith: del protocolo testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @return
	 */
	public boolean SAVRangesConsistentWith(Taxon aParentTaxon) {
		// The entire SAV decription of the receiver was parsed and nothing was found in the SAV description of all its
		// predecessors. Thus, assume there are no inconsistencies
		return true;
	}
}
