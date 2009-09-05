/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.taxonomy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class Taxonomy {
	private Taxon rootTaxon;
	private Map<Descriptor, List<Taxon>> descriptorsIndex;
	private List<List<Taxon>> levelIndex;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxonomy(String aCommonName) {
		List<Taxon> level;

		setDescriptorsIndex(new HashMap<Descriptor, List<Taxon>>());
		
		rootTaxon = new Taxon(TaxonomicRank.ROOT, aCommonName);
		
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
	private void setDescriptorsIndex(Map<Descriptor, List<Taxon>> descriptorsIndex) {
		this.descriptorsIndex = descriptorsIndex;
	}
	
	/**
	 * Precondition: all attributes included in the structures of aNewTaxon MUST have one-level values
	 * @see "Método structureIndex: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aNewTaxon
	 */
	private void addToDescriptorsIndex(Taxon aNewTaxon) {
		for (Descriptor d:aNewTaxon.getDescription()) {
			// Find a structure, in the Structure Index, with a name that matches the new taxon's structure name
			if (!this.getDescriptorsIndex().containsKey(d)) {
				this.getDescriptorsIndex().put(d, new ArrayList<Taxon>());
				this.getDescriptorsIndex().get(d).add(aNewTaxon);
			} else {
				for(Descriptor d2:this.getDescriptorsIndex().keySet()) {
					if (d2.equals(d)) {
                        if (!this.getDescriptorsIndex().get(d).contains(aNewTaxon))
                        	this.getDescriptorsIndex().get(d).add(aNewTaxon);
					}
						
				}
			}
		}
	}

	/**
	 * @see "Método structureIndex del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Map<Descriptor, List<Taxon>> getDescriptorsIndex() {
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


    public List<Object> searchBySA(String aStructure, String anAttribute){
        List<Object> aListValues = new ArrayList<Object>();

        for (List<Taxon> aTaxonList:levelIndex){
            for (Taxon aTaxon:aTaxonList){
                for(Descriptor aDescritor:aTaxon.getDescription()){
                    if (aDescritor.getStructure().equals(aStructure)&&
                            (aDescritor.getAttribute().equals(anAttribute)))

                        //TODO fix this
                        aListValues.add(aDescritor.getValue());
                }

            }
        }
        
        return aListValues;
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

		levelNumber = TaxonomicRank.getIndex(aLevel) -1;
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
            for (Taxon aTaxon: getTaxonListFromLevelIndex(aLevel)){
                if (aTaxon.getName().equals(aTaxonName)) return aTaxon;
            }
            return null;
	}
	
	/**
	 * @see "Método getTaxonByName: del protocolo accessing en SUKIA SmallTalk"
	 * @param aTaxonName
	 * @return
	 */
	public Taxon getTaxonFromLevelIndex(String aTaxonName) {
                for (List<Taxon> aListTaxon: levelIndex){
                    for (Taxon aTaxon: aListTaxon){
                        if (aTaxon.getName().equals(aTaxonName)) return aTaxon;
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
		if (!this.areTaxonomicDependenciesOK(aParentTaxon, aNewTaxon))
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
		if (!aSuccessorTaxon.isOKDirectLink(aParentTaxon))
			return false;
		//Step 4: Make sure that a taxon with the successor's name does not already exist
		if (this.getTaxonFromLevelIndex(aSuccessorTaxon.getName())!=null)
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
		return true;
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
}
