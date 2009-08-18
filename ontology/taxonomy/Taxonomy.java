/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.taxonomy;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class Taxonomy {
	private DescriptorsIndex descriptorsIndex;
	private Taxon rootTaxon;
	private List<List<Taxon>> levelIndex;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxonomy() {
		List<Taxon> level;

		setDescriptorsIndex(new DescriptorsIndex());
		
		rootTaxon = new Taxon();
		rootTaxon.setName(null);
		rootTaxon.setLevel(TaxonomicRank.ROOT);
		
		setLevelIndex(new ArrayList<List<Taxon>>());
			
		for (int i = 1; i <= TaxonomicRank.getNomenclaturalRanksNumber(); i++) {
			level = new ArrayList<Taxon>();
			levelIndex.add(level);
		}
	}
	
	/**
	 * Método de instancia agregado
	 * @param descriptorsIndex
	 */
	private void setDescriptorsIndex(DescriptorsIndex descriptorsIndex) {
		this.descriptorsIndex = descriptorsIndex;
	}
	
	/**
	 * Precondition: all attributes included in the structures of aNewTaxon MUST have one-level values
	 * @see "Método structureIndex: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aNewTaxon
	 */
	private void addToDescriptorsIndex(Taxon aNewTaxon) {
		for (Descriptor<Object> d:aNewTaxon.getDescription()) {
			// Find a structure, in the Structure Index, with a name that matches the new taxon's structure name
			if (!this.getDescriptorsIndex().getDescriptorsIndex().contains(d)) {
				this.getDescriptorsIndex().getDescriptorsIndex().add(d);
				this.getDescriptorsIndex().getOtherTaxons().add(new ArrayList<Taxon>());
				this.getDescriptorsIndex().getOtherTaxons().get(this.getDescriptorsIndex()
						.getDescriptorsIndex().indexOf(d)).add(aNewTaxon);
			} else {
				if (!this.getDescriptorsIndex().getDescriptorsIndex().get(this.getDescriptorsIndex()
						.getDescriptorsIndex().indexOf(d)).getAssociatedObject().equals(aNewTaxon) &&
						!this.getDescriptorsIndex().getOtherTaxons().get(this.getDescriptorsIndex()
								.getDescriptorsIndex().indexOf(d)).contains(aNewTaxon));
					this.getDescriptorsIndex().getOtherTaxons().get(this.getDescriptorsIndex()
							.getDescriptorsIndex().indexOf(d)).add(aNewTaxon);				
			}
		}
	}

	/**
	 * @see "Método structureIndex del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public DescriptorsIndex getDescriptorsIndex() {
		return descriptorsIndex;
	}
	
	/**
	 * Método de instancia agregado
	 * @param levelIndex
	 */
	private void setLevelIndex(List<List<Taxon>> levelIndex) {
		this.levelIndex = levelIndex;
	}
	
	/**
	 * @see "Método leveIndex del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<List<Taxon>> getLevelIndex() {
		return levelIndex;
	}

	/**
	 * @see "Método rootTaxon del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxon getRootTaxon() {
		return rootTaxon;
	}
	
	/**
	 * @see "Método levelIndex: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aTaxon
	 */
	private boolean addTaxonToLevelIndex(Taxon aTaxon) {
		int levelNumber;

		levelNumber = TaxonomicRank.getIndex(aTaxon.getLevel());
		
		if (levelNumber == -1 || levelNumber == 0) return false;

		levelIndex.get(levelNumber-1).add(aTaxon);
		
		return true;
	}
	
	/**
	 * @see "Método levelIndexAt: del protocolo accessing en SUKIA SmallTalk"
	 * @param aLevel
	 * @return
	 */
	public List<Taxon> getTaxonListFromLevelIndex(TaxonomicRank aLevel) {
		int levelNumber;

		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == -1) return null;

		return (levelIndex.get(levelNumber));
	}
	
	/**
	 * @see "Método getTaxonByName:level: del protocolo accessing en SUKIA SmallTalk"
	 * @param aTaxonName
	 * @param aLevel
	 * @return
	 */
	public Taxon getTaxonFromLevelIndex(String aTaxonName, TaxonomicRank aLevel) {
		List<Taxon> aList;
		
		aList = getTaxonListFromLevelIndex(aLevel);
		
		for (int i = 1; i <= aList.size(); i++) {
			if ((aList.get(i-1).getName().equals(aTaxonName)))
				return (aList.get(i-1));
		}
		
		return null;
	}
	
	/**
	 * @see "Método getTaxonByName: del protocolo accessing en SUKIA SmallTalk"
	 * @param aTaxonName
	 * @return
	 */
	public Taxon getTaxonFromLevelIndex(String aTaxonName) {
		List<List<Taxon>> aListDividedByLevels;
		
		aListDividedByLevels = levelIndex;
		
		for (int i = 1; i <= aListDividedByLevels.size(); i++) {
			for (int j = 1; j <= aListDividedByLevels.get(i-1).size(); j++) {
				if (aListDividedByLevels.get(i-1).get(j-1).getName().equals(aTaxonName))
					return aListDividedByLevels.get(i-1).get(j-1);
			}
		}
			
		return null;
	}
	
	/**
	 * @see "Método add:linkTo: del protocolo adding en SUKIA SmallTalk"
	 * @param aNewTaxon
	 * @param aParentTaxon
	 */
	public boolean addTaxon(Taxon aNewTaxon, Taxon aParentTaxon) {
		// Step 1: link the new taxon to the existing taxon in the hierarchy, if all taxonomic dependencies are OK.
		if (this.areTaxonomicDependenciesOK(aParentTaxon, aNewTaxon))
			return false;
		//Step 2: Reference the new taxon in levelIndex (i.e., alphabetically by taxon name, by taxonomic level)
		if (!(this.addTaxonToLevelIndex(aNewTaxon))) {
			aNewTaxon.unlinkFromTheHierarchy();
			return false;
		}
		// Step 3: Reference the new taxon in structureIndex
		this.addToDescriptorsIndex(aNewTaxon);

		return true;
	}
	
	/**
	 * @see "Método processTaxonomicDependenciesBetween:and: del protocolo taxonomic dependencies en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @param aSuccessorTaxon
	 * @return
	 */
	public boolean areTaxonomicDependenciesOK(Taxon aParentTaxon, Taxon aSuccessorTaxon) {

		//Step 1: Make sure that (at least) the SAV description of the successor taxon is not empty
		if (aSuccessorTaxon.getDescription().isEmpty())
			return false;		
		
		//Step 3: Make sure that the successor taxon can indeed be linked to the parent taxon
		if (aSuccessorTaxon.isOKDirectLink(aParentTaxon))
			return false;
		//Step 4: Make sure that a taxon with the successor's name does not already exist
		if (this.getTaxonFromLevelIndex(aSuccessorTaxon.getName())==null)
				return false;
		/*****     *****     ***** 
		Step 5: Special case for SPECIES: Make sure that the associated genus does not contain another 
		species with the same name.  
		NOTE (26-Jun-1999, HB): Step 5 is now obsolete, since ALL taxon names are now assumed to be unique, even
		at the species level (names at the species level are composite; see the name: method of class Taxon for details).
		(aSuccessorTaxon level = #species)
		ifTrue: [ (aParentTaxon includes: (aSuccessorTaxon name) in: (aParentTaxon successors)) ifTrue: [ ^nil ] ]. 
		*****     *****     *****/
		//Step 6: Link the successor taxon to the hierarchy
		aSuccessorTaxon.setPredecessor(aParentTaxon);
		/* Step 7: Make sure that all the value ranges specified in the SAV description of the successor taxon are
		 consistent with those of its predecessors*/
		if (!(aSuccessorTaxon.isRangesConsistent(aParentTaxon))) {
			aSuccessorTaxon.unlinkFromTheHierarchy();
			return false;
		}
		
		return false;
	}
	
	/**
	 * This program assumes the name of all taxa to be unique, regarless of the level.  That is, even at the
	 * species level, names MUST be unique because they are composite names (see the name: method in 
	 * class Taxon for details
	 * @see "Método include: del protocolo testing en SUKIA SmallTalk" 
	 * @param aTaxon
	 * @return
	 */
	public boolean contains(Taxon aTaxon) {
		if (this.getTaxonFromLevelIndex(aTaxon.getName()) ==null)
			return false;
		else return true;
	}
	
	/**
	 * This program assumes the name of a species as: taxon->species name + taxon->species predecessor name.
	 * That's the reason why the species level is NOT scanned: epithets may repeat for different genera
	 * @see "Método include_old: del protocolo testing en SUKIA SmallTalk"
	 * @param aTaxon
	 * @return
	 */
	public boolean includes_old(Taxon aTaxon) {
		Taxon t;
		for (int i = 1; i <= levelIndex.size(); i++) {
			if (!(i == (TaxonomicRank.getIndex(TaxonomicRank.SPECIES)))) {
				for (int j = 1; j <= levelIndex.get(i-1).size(); j++) {
					t =  levelIndex.get(i).get(j-1);
					if (t.getName().equals(aTaxon.getName())) 
						return true;
				}
			}
		}
		return false;
	}
}
