/**
 * @see "Categor�a Sukia Domain Theory de SUKIA Smalltalk"
 */
package domainTheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Armando
 *
 */
public class Taxon implements Comparable<Taxon>{
	private String level;
	private String name;
	private Taxon predecessor;
	private List<Taxon> successors;
	private StructureIndex SAVDescription;
	private GroupingHeuristicIndex GHDescription;

	/**
	 * @see "M�todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxon() {
		setLevel(null);
		setName(null);
		setPredecessor(null);
		//Pendiente ordenamiento
		setSuccessors(new ArrayList<Taxon>());
		//Pendiente ordenamiento
		setGHDescription(new GroupingHeuristicIndex());
		//Pendiente ordenamiento
		setSAVDescription(new StructureIndex());
	}

	/**
	 * @see "M�todo level: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo level del protocolo accessing en SUKIA SmallTalk"
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
	 * @see "M�todo name: del protocolo adding en SUKIA SmallTalk"
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see "M�todo name del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see "M�todo predecessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param predecessor
	 */
	public void setPredecessor(Taxon predecessor) {
		this.predecessor = predecessor;
		this.predecessor.addSuccessor(this);
	}

	/**
	 * @see "M�todo predecessor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxon getPredecessor() {
		return predecessor;
	}

	/**
	 * M�todo de instancia agregado
	 * @param sucessor
	 */
	public void setSuccessors(List<Taxon> sucessors) {
		this.successors = sucessors;
		Collections.sort(this.successors);
	}

	/**
	 * @see "M�todo successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Taxon> getSuccessors() {
		return successors;
	}
	
	/**
	 * @see "M�todo sucessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param sucessor
	 */
	public void addSuccessor(Taxon successor) {
		successor.setPredecessor(successor);
		this.successors.add(successor);
		Collections.sort(this.successors);
	}

	/***
	 * M�todo de instancia agregado
	 * @param gHDescription
	 */
	public void setGHDescription(GroupingHeuristicIndex aGHDescription) {
		GHDescription = aGHDescription;
	}

	/**
	 * @see "M�todo GHdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public GroupingHeuristicIndex getGHDescription() {
		return GHDescription;
	}
	
	/**
	 * @see "M�todo GHDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aGroupingHeuristic
	 */
	/*public void addGHDescription(GroupingHeuristic aGroupingHeuristic) {
		if (includesGroupingHeuristic(aGroupingHeuristic.getName(), GHDescription))
			return;
		
		GHDescription.add(aGroupingHeuristic);
	}*/

	/***
	 * M�todo de instancia agregado
	 * @param sAVDescription
	 */
	public void setSAVDescription(StructureIndex aSAVDescription) {
		SAVDescription = aSAVDescription;
	}

	/**
	 * @see "M�todo SAVdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public StructureIndex getSAVDescription() {
		return SAVDescription;
	}

	/**
	 * @see "M�todo SAVdescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructure
	 */
	/*public void addSAVDescription(Structure aStructure) {
		if (includesStructure(aStructure.getName(), SAVDescription))
			return;
		
		SAVDescription.add(aStructure);
	}*/
	
	/**
	 * @see "M�todo includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 * @return
	 */
	/*public boolean includesStructure(String aName, List<Structure> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getName().equals(aName))
				return true;
		}
		
		return false;
	}*/
	
	/**
	 * @see "M�todo includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 * @return
	 */
	/*public boolean includesGroupingHeuristic(String aName, List<GroupingHeuristic> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getName().equals(aName))
				return true;
		}
		
		return false;
	}*/
	
	/**
	 * @see "M�todo isSuccessorOf: del protocolo inhetitence en SUKIA SmallTalk"
	 */
	public boolean isSuccessorOf(Taxon aTaxon) {
		Taxon  predecessorTaxon;
		
		if (TaxonomicLevels.getLevels().indexOf(level) <= TaxonomicLevels.getLevels().indexOf(aTaxon.getLevel()))
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
	 * @see "M�todo linkTo: del protocolo linking en SUKIA SmallTalk"
	 * @param aTaxon
	 */
	/*public void linkTo(Taxon aTaxon) {
		predecessor = aTaxon;
		aTaxon.addSucessor(this);
	}*/
	
	/**
	 * @see "M�todo unlinkFromTheHierarchy del protocolo linking en SUKIA SmallTalk"
	 */
	public void unlinkFromTheHierarchy() {
		Taxon p;
		
		p = predecessor;
		for (int i = 1; i <= p.getSuccessors().size(); i++) {
			if (p.getSuccessors().get(i-1) == this) {
				 p.getSuccessors().remove(i-1);
				 predecessor = null; 
			}
		}
	}
	
	/**
	 * @see "M�todo getAnObjectWith:in: del protocolo searching en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 */
	public Structure getAStructureWith(String aName, List<Structure> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i).getName().equals(aName)) 
				return aDescription.get(i-1);
		}
		
		return null;
	}
	
	/**
	 * @see "M�todo getAnObjectWith:in: del protocolo searching en SUKIA SmallTalk"
	 * @param aName
	 * @param aDescription
	 */
	/*public GroupingHeuristic getAGroupingHeuristicWith(String aName, List<GroupingHeuristic> aDescription) {
		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i).getName().equals(aName)) 
				return aDescription.get(i);
		}
		
		return null;
	}*/
	
	/**
	 * @see "M�todo isLinkOKBetween:and: del protocolo de clase testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @param aSuccessorTaxon
	 * @return
	 */
	public boolean isOKDirectLink(Taxon aParentTaxon) {
		return (((TaxonomicLevels.getLevels().indexOf(this.getLevel())) - (TaxonomicLevels.getLevels().indexOf(aParentTaxon.getLevel()))) == 1);
	}
	
	/**
	 * M�todo de instancia agregado
	 */
	public int compareTo(Taxon aTaxon) {
		return this.getName().compareTo(aTaxon.getName());
	}
}
