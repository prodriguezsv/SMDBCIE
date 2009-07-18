/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package domainTheory;

import java.util.ArrayList;
import java.util.List;

import values.ValueDescriptor;
import values.MeasuringUnit;
import values.RangeDescriptor;
import values.SingleDescriptor;

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

		setStructureIndex(new StructureIndex());
		setGroupingHeuristicIndex(new GroupingHeuristicIndex());
		
		rootTaxon = new Taxon();
		rootTaxon.setName(null);
		rootTaxon.setLevel("root");
		
		setLevelIndex(new ArrayList<List<Taxon>>());
			
		for (int i = 1; i <= TaxonomicLevels.getNomenclaturalLevelsNumber(); i++) {
			level = new ArrayList<Taxon>();
			levelIndex.add(level);
		}
	}
	
	/**
	 * Initialize all classes needed by Taxonomy
	 * @see "Método initializeClasses del protocolo de clase class initialization en SUKIA SmallTalk"
	 */
	public static void initializeParameters() {
		TaxonomicLevels.initialize();
		MeasuringUnit.initialize();
	}
	
	/**
	 * Método de instancia agregado
	 * @param structureIndex
	 */
	private void setStructureIndex(StructureIndex structureIndex) {
		this.structureIndex = structureIndex;
	}
	
	/**
	 * Precondition: all attributes included in the structures of aNewTaxon MUST have one-level values
	 * @see "Método structureIndex: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aNewTaxon
	 */
	private void addTaxonToStructureIndex(Taxon aNewTaxon) {
		Structure ns, s;
		Attribute na, a;
		ValueDescriptor nvd, vd;

		for (int i = 1; i <= aNewTaxon.getSAVDescription().size(); i++) {
			// Get the next structure from the new taxon's description
			ns = aNewTaxon.getSAVDescription().get(i-1);

			// Find a structure, in the Structure Index, with a name that matches the new taxon's structure name
			s = this.getStructureIndex().getStructure(ns.getName());
			if (s == null) {
				/* Structure not found.  Create a new structure, copy its contents from the new taxon's structure
				 (referencing the new taxon in all the ValueDescriptor instances), and add it to the Structure Index*/
				s = new Structure();
				s.addAtributes(ns, aNewTaxon);
				s.setWeight(0.0);
				this.getStructureIndex().addStructure(s);
			} else {
				// Structure found in the Structure Index
				for (int j = 1; j <= ns.getAttributes().size(); j++) {
					// Get the next attribute from the new taxon's structure
					na = ns.getAttributes().get(j-1);

					/* Find an attribute, belonging to the structure found in in the Structure Index, with a name 
					 that matches the new taxon's structure attribute name*/
					a = s.getAttribute((na.getName()));
					if (a == null) {
						/* Attribute not found.  Create a new attribute, copy its contents from the new taxon's structure
						 attribute (referencing the new taxon in all the ValueDescriptor instances), and add it to the structure's
						attribute list*/
						a = new Attribute();
						a.addValues(na, aNewTaxon);
						s.addAttribute(a);
					} else {
						// Attribute found in the structure's list (the structure that belongs to the Structure Index)
						for (int k = 1; k <= na.getValues().get(Attribute.oneLevel()).size(); k++) {
							// Get the next value descriptor from the attribute that belongs to the new taxon's structure"
							nvd = na.getValues().get(Attribute.oneLevel()).get(k-1);

							/* Find a value descriptor for the attribute (that was found in the structure that belongs to the Structure Index), 
							 with a descriptor matching that of the new taxon's structure-attribute descriptor*/
							vd = a.getValues().getValueDescriptors(nvd, aNewTaxon.getLevel());

							if (vd == null) {
								/* ValueDescriptor not found.  Create a new value descriptor, copy its contents from the new taxon's structure
								 attribute value descriptor (referencing the new taxon), and add it to the structure's attribute values list*/
								if (nvd instanceof RangeDescriptor)
									vd = new RangeDescriptor();
								else
									vd = new SingleDescriptor<Object>();
								
								vd.addValues(nvd, aNewTaxon);
								a.getValues().addValueDescriptor(vd, aNewTaxon.getLevel());
							} else {
								/* ValueDescriptor found for the attribute (that belongs to the structure found in the Structure Index).
								 Here, all that we need to do is reference the new taxon to this ValueDescriptor*/
								vd.addTaxon(aNewTaxon);
							}
						}
					}
					
				}
			}
			
		}
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
	private void addTaxonToGroupingHeuristicIndex(Taxon aNewTaxon) {
		GroupingHeuristic ngh, gh;
		ValueDescriptor nvd, vd;

		for (int i = 1; i <= aNewTaxon.getGHDescription().size(); i++) {
			// Get the next grouping heuristic from the new taxon
			ngh = aNewTaxon.getGHDescription().get(i-1);
			// Find a grouping heuristic, in the Grouping Heuristic Index, with a name that matches the new taxon's heuristic name"
			gh = this.getGroupingHeuristicIndex().getGroupingHeuristic(ngh.getName());
			if (gh == null) {
				/* Grouping heuristic not found.  Create a new grp. heuristic, copy its contents from the new taxon's grp. heurist.
				 (referencing the new taxon in all the ValueDescriptor instances), and add it to the Grouping Heuristic Index*/
				gh = new GroupingHeuristic();
				gh.addValues(ngh, aNewTaxon);
				gh.setWeight(0.0);
				this.getGroupingHeuristicIndex().addGroupingHeuristic(gh);
			} else {
				// Grouping heuristic found in the Grouping Heuristic Index
				for (int j = 1; j <= ngh.getValues().get(GroupingHeuristic.oneLevel()).size(); j++) {
					// Get the next value descriptor that belongs to the new taxon's grouping heuristic
					nvd = ngh.getValues().get(GroupingHeuristic.oneLevel()).get(j-1);

					/* Find a value descriptor for the grouping heuristic (that was found in the Grouping Heuristic Index), 
					 with a descriptor matching that of the new taxon's grouping heuristic descriptor*/
					vd = gh.getValues().getValueDescriptors(nvd, aNewTaxon.getLevel());
					if (vd == null) {
						/* ValueDescriptor not found.  Create a new value descriptor, copy its contents from the new taxon's grouping
						 heuristic value descriptor (referencing the new taxon), and add it to the grouping heuristic values list*/
						if (nvd instanceof RangeDescriptor)
							vd = new RangeDescriptor();
						else
							vd = new SingleDescriptor<Object>();
						
						vd.addValues(nvd, aNewTaxon);
						gh.getValues().addValueDescriptor(vd, aNewTaxon.getLevel());
					} else {
						/* ValueDescriptor found for the grp. heurist. (that belongs to the Grouping Heuristic Index).
						 Here, all that we need to do is reference the new taxon to this ValueDescriptor*/
						vd.addTaxon(aNewTaxon);
					}
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
	private boolean addTaxonToLevelIndex(Taxon aTaxon) {
		int levelNumber;

		levelNumber = TaxonomicLevels.getLevels().indexOf(aTaxon.getLevel());
		
		if (levelNumber == -1 || levelNumber == 0) return false;

		levelIndex.get(levelNumber-1).add(aTaxon);
		
		return true;
	}
	
	/**
	 * @see "Método levelIndexAt: del protocolo accessing en SUKIA SmallTalk"
	 * @param aLevel
	 * @return
	 */
	public List<Taxon> getTaxonListFromLevelIndex(String aLevel) {
		int levelNumber;

		levelNumber = TaxonomicLevels.getLevels().indexOf(aLevel);
		if (levelNumber == -1) return null;

		return (levelIndex.get(levelNumber));
	}
	
	/**
	 * @see "Método getTaxonByName:level: del protocolo accessing en SUKIA SmallTalk"
	 * @param aTaxonName
	 * @param aLevel
	 * @return
	 */
	public Taxon getTaxonFromLevelIndex(String aTaxonName, String aLevel) {
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
	 * @param anParentTaxon
	 */
	public boolean addTaxon(Taxon aNewTaxon, Taxon anParentTaxon) {
		// Step 1: link the new taxon to the existing taxon in the hierarchy, if all taxonomic dependencies are OK.
		if (this.areTaxonomicDependenciesOK(anParentTaxon, aNewTaxon))
			return false;
		//Step 2: Reference the new taxon in levelIndex (i.e., alphabetically by taxon name, by taxonomic level)
		if (!(this.addTaxonToLevelIndex(aNewTaxon))) {
			aNewTaxon.unlinkFromTheHierarchy();
			return false;
		}
		// Step 3: Reference the new taxon in structureIndex
		this.addTaxonToStructureIndex(aNewTaxon);

		// Step 4: Reference the new taxon in groupingHeuristicIndex
		this.addTaxonToGroupingHeuristicIndex(aNewTaxon);

		return true;
	}
	
	/**
	 * @see "Método taxonInDividedList:with: del protocolo searching en SUKIA SmallTalk"
	 * @param aListDividedByLevels
	 * @param aTaxonName
	 * @return
	 */
	/*public Taxon searchForTaxonInDividedList(List<List<Taxon>> aListDividedByLevels, String aTaxonName) {
		for (int i = 1; i <= aListDividedByLevels.size(); i++) {
			for (int j = 1; j <= aListDividedByLevels.get(i-1).size(); j++) {
				if (aListDividedByLevels.get(i-1).get(j-1).getName().equals(aTaxonName))
					return aListDividedByLevels.get(i-1).get(j-1);
			}
		}
		
		return null;
	}*/	
	
	/**
	 * @see "Método taxonInSimpleList:with: del protocolo searching en SUKIA SmallTalk"
	 * @param aList
	 * @param aTaxonName
	 * @return
	 */
	/*public Taxon searchForTaxonInSimpleList(List<Taxon> aList, String aTaxonName) {
		for (int i = 1; i <= aList.size(); i++) {
			if ((aList.get(i-1).getName().equals(aTaxonName)))
				return (aList.get(i-1));
		}
		
		return null;
	}*/
	
	/**
	 * @see "Método processTaxonomicDependenciesBetween:and: del protocolo taxonomic dependencies en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @param aSuccessorTaxon
	 * @return
	 */
	public boolean areTaxonomicDependenciesOK(Taxon aParentTaxon, Taxon aSuccessorTaxon) {

		//Step 1: Make sure that (at least) the SAV description of the successor taxon is not empty
		if (aSuccessorTaxon.getSAVDescription().isEmpty())
			return false;		
		//Step 2: Make sure that the successor taxon's level name exists
		if (!(TaxonomicLevels.getLevels().contains(aSuccessorTaxon.getLevel())))
			return false;
		//Step 3: Make sure that the successor taxon can indeed be linked to the parent taxon
		if (aSuccessorTaxon.isDirectLinkOK(aParentTaxon))
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
		if (!(aSuccessorTaxon.getSAVDescription().isRangesConsistent(aParentTaxon))) {
			aSuccessorTaxon.unlinkFromTheHierarchy();
			return false;
		}
		//Step 8: Make sure that all the value ranges specified in the GH description of the successor taxon are
		//consistent with those of its predecessors
		if (!(aSuccessorTaxon.getGHDescription().isEmpty())) {
			if (!(aSuccessorTaxon.getGHDescription().isRangesConsistent(aParentTaxon))) {
				aSuccessorTaxon.unlinkFromTheHierarchy();
				return false;
			}
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
			if (!(i == (TaxonomicLevels.getLevels().indexOf("species")))) {
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
