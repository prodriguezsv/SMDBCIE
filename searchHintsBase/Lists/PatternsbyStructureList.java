/**
 * Este paquete agrupa las listas que almacenan patrones de b&uacute;squeda de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ontology.common.Descriptor;

import searchHintsBase.Elements.DescriptorsPattern;
import searchHintsBase.Elements.PatternsbyStructure;

/**
 * Esta clase es una lista de patrones de descriptores agrupados por estructura
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class PatternsbyStructureList extends HintsList<PatternsbyStructure> {

	/**
	 * @see "M&eacute;todo add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(PatternsbyStructure patterns) {
		PatternsbyStructure pbs;
				
		if (patterns == null)
			return false;

		if (patterns.getPatterns().isEmpty())
			return false;

		if (!(this.contains(patterns))) {
			super.add(patterns);
			return true;
		}
		
		pbs = this.getPatternByStructure(patterns.getStructureName());
		
		for (DescriptorsPattern dp:patterns.getPatterns())
			pbs.addPattern(dp);

		return true;
	}
	
	/**
	 * Receives an unsorted list of Structures, each with a set of Attributes (i.e., the description of the
	 * Structure).
	 * @see "M&eacute;todo sortBySuccessCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return The same list but sorted (in descending order) according to the frequency of description
	 * patterns found in the elements of this list that are similar to the Structures' descriptions
	 */
	public List<String> sortBySuccessFrecuencyCriteria(List<Descriptor<Object>> aDescriptorList) {
		return this.getSortedStructureList(aDescriptorList, 
				new Comparator<PatternsbyStructure>() {
					public int compare(PatternsbyStructure elem1, PatternsbyStructure elem2) {
						return (elem2.getPatterns().get(0).getSuccessFrequency()
								- elem1.getPatterns().get(0).getSuccessFrequency());
					}
				}
		);
	}
	
	/**
	 * Receives an unsorted list of Structures, each with a set of Attributes (i.e., the description of the
	 * Structure).
	 * @see "M&eacute;todo sortByFailureCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return The same list but sorted (in ascending order) according to the frequency of description
	 * patterns found in the elements of this list that are similar to the Structures' descriptions
	 */
	public List<String> sortByFailureFrecuencyCriteria(List<Descriptor<Object>> aDescriptorList) {
		return this.getSortedStructureList(aDescriptorList, 
				new Comparator<PatternsbyStructure>() {
					public int compare(PatternsbyStructure elem1, PatternsbyStructure elem2) {
						return (elem1.getPatterns().get(0).getFailureFrequency()
								- elem2.getPatterns().get(0).getFailureFrequency());
					}
				}
		);
	}
	
	/**
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(PatternsbyStructure aPattern) {
		for (PatternsbyStructure pbs:this) {
			if (pbs.getStructureName().equals(aPattern.getStructureName()))
				return true;
		}
		
		return false;
	}
		
	/**
	 * M&eacute;todo de instancia agregado
	 * @return
	 */
	public PatternsbyStructure getPatternByStructure(String aStructureName) {
		for (PatternsbyStructure pbs:this) {
			if (pbs.getStructureName().equals(aStructureName))
				return pbs;
		}
		
		return null;
	}
	
	/**
	 * @see "M&eacute;todo isMember: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructure
	 * @return
	 */
	public boolean contains(String aStructureName) {
		for (PatternsbyStructure pbs:this)
			if (pbs.getStructureName().equals(aStructureName))
				return true;
		
		return false;
	}
			
	/**
	 * This method takes as argument an unsorted list of Structures, and sorts them in according to the
	 * frequency rate of patterns in this list that are similar or equal to the Structures' descriptions
	 * (i.e., their list of Attributes). The process is as follows:
	 * 1. if the next Structure's name does not match the name of any the elements in this list, such Structure
	 * is added to a leftOvers list (i.e., a list that contains all Structures that can't be processed);
	 * 2. if the Structure's name matches an element in self, the Structure's description is checked against
	 * the patterns contained in the retrieved element from self, to determine if there is a similar pattern;
	 * 3. if there is a similar pattern, the Structure is stored in a temporary list, and the element along
	 * with the pattern are stored in a sorted list (sort criteria: pattern frequency);
	 * 4. if no similar pattern is found, the Structure is added to the leftOvers list;
	 * 5. all Structures in the temporary list are added back to the argument list, according to the sort
	 * order dictated by the sorted list; 5. all Structures in the leftOvers list are appended to the end
	 * of the argument list.
	 * PRECONDITION: (this list isEmpty not) and (anUnsorted isEmpty not)
	 * @see "M&eacute;todo sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 * @return An empty list, if either this list or the argument list are empty; The argument list possibly
	 * sorted by similar pattern frequency.
	 */
	@SuppressWarnings("unchecked")
	private List<String> getSortedStructureList(List<Descriptor<Object>>  description, Comparator c) {
		List<String> mainList, tempList, leftOvers;
		PatternsbyStructureList sortedList;
		String aStructureName;
		PatternsbyStructure pbs, singlepbs;
		DescriptorsPattern dp;
		List<Descriptor<Object>> descriptors;
		int numElements, numProcessedElts, i;
		
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<String>();
		mainList = this.getStructuresList(description);

		// Check precondition
		if (this.isEmpty() || mainList.isEmpty())
			return tempList;

		numElements = mainList.size();
		sortedList = new PatternsbyStructureList();
		leftOvers = new ArrayList<String>();

		while (!(mainList.isEmpty())) {
			aStructureName = mainList.remove(0);
		
			if ((pbs = this.getPatternByStructure(aStructureName)) == null)			
				leftOvers.add(aStructureName);
			else {							
				descriptors = this.getDescription(description, aStructureName);
				dp = pbs.whatPatternIsMostSimilarTo(descriptors);
				if (dp == null) leftOvers.add(aStructureName);
				else {
					tempList.add(aStructureName);
					singlepbs = new PatternsbyStructure(pbs.getStructureName());
					singlepbs.addPattern(dp);
					sortedList.add(singlepbs);
					Collections.sort(sortedList, c);
				}
			}
		}
		
		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {
			singlepbs = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				if (tempList.get(i-1).equals(singlepbs.getStructureName())) {
					mainList.add(tempList.remove(i-1));
					i = tempList.size();
				}
				
				i = i + 1;
			}
		}
		if (!(leftOvers.isEmpty()))
			mainList.addAll(leftOvers);

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return mainList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	private List<Descriptor<Object>> getDescription(List<Descriptor<Object>> descriptors, String aStructureName) {
		List<Descriptor<Object>> description;
		
		description = new ArrayList<Descriptor<Object>>();
		
		for(Descriptor<Object> d: descriptors) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName)) {
				description.add(d);
			} else continue;
		}
		
		return description;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	private List<String> getStructuresList(List<Descriptor<Object>>  descriptors) {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor<Object> d: descriptors) { 
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (!(structuresList.contains(d.getStructure()))) {
				// The structure name was not found in structureList. Append it to structureList
				structuresList.add(d.getStructure());
			} else continue;
		}
		
		return structuresList;
	}
}
