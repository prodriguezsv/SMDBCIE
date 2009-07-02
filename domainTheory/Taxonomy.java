/**
 * 
 */
package domainTheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import values.Descriptor;

/**
 * @author Armando
 *
 */
public class Taxonomy {
	private StructureIndex structureIndex;
	private GroupingHeuristicIndex groupingHeuristicIndex;
	private Taxon rootTaxon;
	private List<List<Taxon>> levelIndex;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxonomy() {
		List<Taxon> level;
		// TODO Auto-generated constructor stub
		setStructureIndex(new StructureIndex());
		setGroupingHeuristicIndex(new GroupingHeuristicIndex());
		
		rootTaxon = new Taxon();
		rootTaxon.setName(null);
		rootTaxon.setLevel("root");
		
		setLevelIndex(new ArrayList<List<Taxon>>());
			
		for (int i = 1; i <= TaxonomicLevels.nomenclaturalNumber(); i++) {
			level = new ArrayList<Taxon>();
			//Pendiente el ordenamiento
			levelIndex.add(level);
		}
	}
	
	/**
	 * @see "Método initializeClasses del protocolo de clase class initialization en SUKIA SmallTalk"
	 */
	// Pendiente de traducir
	public static void initializeClasses() {
		TaxonomicLevels.initialize();
		// MeasuringUnit.initialize();
	}
	
	/**
	 * Método de instancia agregado
	 * @param structureIndex
	 */
	//Pendiente de traducir
	private void setStructureIndex(StructureIndex structureIndex) {
		this.structureIndex = structureIndex;
	}
	
	/**
	 * Precondition: all attributes included in the structures of aNewTaxon MUST have one-level values
	 * @see "Método structureIndex: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aNewTaxon
	 */
	private void addStructureIndex(Taxon aNewTaxon) {
		
	}

	/**
	 * @see "Método structureIndex del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public StructureIndex getStructureIndex() {
		return structureIndex;
	}

	/**
	 * Método de instancia agregado
	 * @param groupingHeuristicIndex
	 */
	private void setGroupingHeuristicIndex(GroupingHeuristicIndex groupingHeuristicIndex) {
		this.groupingHeuristicIndex = groupingHeuristicIndex;
	}
	
	/**
	 * Precondition: all attributes included in the structures of aNewTaxon MUST have one-level values
	 * @see "Método groupingHeuristicIndex: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aNewTaxon
	 */
	private void addGroupingHeuristicIndex(Taxon aNewTaxon) {
		GroupingHeuristic ngh, gh;
		Descriptor nvd, vd;

		for (int i = 1; i <= aNewTaxon.getGHDescription().size(); i++) {
			// Get the next grouping heuristic from the new taxon
			ngh = aNewTaxon.getGHDescription().get(i);
			// Find a grouping heuristic, in the Grouping Heuristic Index, with a name that matches the new taxon's heuristic name"
			gh = this.getGroupingHeuristicIndex().groupingHeuristicWith(ngh.getName());
			if (gh == null) {
				// Grouping heuristic not found.  Create a new grp. heuristic, copy its contents from the new taxon's grp. heurist.
				// (referencing the new taxon in all the ValueDescriptor instances), and add it to the Grouping Heuristic Index
				gh = new GroupingHeuristic();
				gh.copy(ngh, aNewTaxon);
				gh.setWeight(0.0);
				this.getGroupingHeuristicIndex().add(gh);
			} else {
				// Grouping heuristic found in the Grouping Heuristic Index
				for (int j = 1; j <= ngh.getValues().get(GroupingHeuristic.oneLevel()).size(); j++) {
					// Get the next value descriptor that belongs to the new taxon's grouping heuristic
					
				}
			}
		}			
	}

	/**
	 * @see "Método groupingHeuristicIndex del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public GroupingHeuristicIndex getGroupingHeuristicIndex() {
		return groupingHeuristicIndex;
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
	private void addLevelIndex(Taxon aTaxon) {
		int levelNumber;

		levelNumber = TaxonomicLevels.transformToIndex(aTaxon.getLevel());
		
		if (levelNumber == -1) return;

		levelIndex.get(levelNumber).add(aTaxon);
	}
	
	/**
	 * @see "Método levelIndexAt: del protocolo accessing en SUKIA SmallTalk"
	 * @param aLevel
	 * @return
	 */
	public List<Taxon> getLevelIndexAt(String aLevel) {
		int levelNumber;

		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		return (levelIndex.get(levelNumber));
	}
	
	/**
	 * @see "Método getTaxonByName:level: del protocolo accessing en SUKIA SmallTalk"
	 * @param aTaxonName
	 * @param aLevel
	 * @return
	 */
	//Pendiente de hacer método homónimo
	public Taxon getTaxonByName(String aTaxonName, String aLevel) {
		return searchForTaxonInSimpleList(getLevelIndexAt(aLevel), aTaxonName);
	}
	
	/**
	 * @see "Método getTaxonByName: del protocolo accessing en SUKIA SmallTalk"
	 * @param aTaxonName
	 * @return
	 */
	public Taxon getTaxonByName(String aTaxonName) {
		return searchForTaxonInDividedList(levelIndex, aTaxonName);
	}
	
	/**
	 * @see "Método add:linkTo: del protocolo adding en SUKIA SmallTalk"
	 * @param aNewTaxon
	 * @param anExistingTaxon
	 */
	//Pendiente de traducir
	public void add(Taxon aNewTaxon, Taxon anExistingTaxon) {
		// Step 1: link the new taxon to the existing taxon in the hierarchy, if all taxonomic dependencies are OK.
		
		//Step 2: Reference the new taxon in levelIndex (i.e., alphabetically by taxon name, by taxonomic level)
				
		//Step 3: Reference the new taxon in structureIndex"
		

		//Step 4: Reference the new taxon in groupingHeuristicIndex"
		
	}
	
	/**
	 * @see "Método taxonInDividedList:with: del protocolo searching en SUKIA SmallTalk"
	 * @param aListDividedByLevels
	 * @param aTaxonName
	 * @return
	 */
	public Taxon searchForTaxonInDividedList(List<List<Taxon>> aListDividedByLevels, String aTaxonName) {
		for (int i = 1; i <= aListDividedByLevels.size(); i++) {
			for (int j = 1; j <= aListDividedByLevels.get(i).size(); j++) {
				if (aListDividedByLevels.get(i).get(j).getName().equals(aTaxonName))
					return aListDividedByLevels.get(i).get(j);
			}
		}
		
		return null;
	}	
	
	/**
	 * @see "Método taxonInSimpleList:with: del protocolo searching en SUKIA SmallTalk"
	 * @param aList
	 * @param aTaxonName
	 * @return
	 */
	public Taxon searchForTaxonInSimpleList(List<Taxon> aList, String aTaxonName) {
		for (int i = 1; i <= aList.size(); i++) {
			if ((aList.get(i).getName().equals(aTaxonName)))
				return (aList.get(i));
		}
		
		return null;
	}
	
	/**
	 * @see "Método processTaxonomicDependenciesBetween:and: del protocolo taxonomic dependencies en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @param aSuccessorTaxon
	 * @return
	 */
	//Pendiente de traducir
	public boolean processTaxonomicDependenciesBetween(Taxon aParentTaxon, Taxon aSuccessorTaxon) {

		//Step 1: Make sure that (at least) the SAV description of the successor taxon is not empty
				
		//Step 2: Make sure that the successor taxon's level name exists
		
		//Step 3: Make sure that the successor taxon can indeed be linked to the parent taxon
		
		//Step 4: Make sure that a taxon with the successor's name does not already exist
		
		/*****     *****     ***** 
		Step 5: Special case for SPECIES: Make sure that the associated genus does not contain another 
		species with the same name.  
		NOTE (26-Jun-1999, HB): Step 5 is now obsolete, since ALL taxon names are now assumed to be unique, even
		at the species level (names at the species level are composite; see the name: method of class Taxon for details).
		(aSuccessorTaxon level = #species)
		ifTrue: [ (aParentTaxon includes: (aSuccessorTaxon name) in: (aParentTaxon successors)) ifTrue: [ ^nil ] ]. 
		*****     *****     *****/
	
		//Step 6: Link the successor taxon to the hierarchy
	
		//Step 7: Make sure that all the value ranges specified in the SAV description of the successor taxon are
		//consistent with those of its predecessors'
	
		//Step 8: Make sure that all the value ranges specified in the GH description of the successor taxon are
		//consistent with those of its predecessors'
		
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
	public boolean includes(Taxon aTaxon) {
		Taxon t;
		for (int i = 1; i <= levelIndex.size(); i++) {
			for (int j = 1; j <= levelIndex.get(i).size(); j++) {
				t =  levelIndex.get(i).get(j);
				if (t.getName().equals(aTaxon.getName())) 
					return true;
			}
		}
		return false;
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
			if (!(i == (TaxonomicLevels.getNumberByName("species")))) {
				for (int j = 1; j <= levelIndex.get(i).size(); j++) {
					t =  levelIndex.get(i).get(j);
					if (t.getName().equals(aTaxon.getName())) 
						return true;
				}
			}
		}
		return false;
	}
}
