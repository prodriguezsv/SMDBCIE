/**
 * Este paquete agrupa las listas que almacenan patrones de b&uacute;squeda de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ontology.common.Attribute;
import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;
import ontology.common.GroupingHeuristic;
import ontology.common.Structure;
import ontology.values.SingleValue;
import ontology.values.Values;


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
				
		if (patterns.getStructureName() == null)
			return false;

		if (patterns.getDescriptorsPatterns().isEmpty())
			return false;

		if (!(this.contains(patterns))) {
			super.add(patterns);
			return true;
		}
		
		pbs = this.getPatternByStructure(patterns.getStructureName());
		
		for (DescriptorsPattern dp:patterns.getDescriptorsPatterns())
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
	public List<Object> sortBySuccessFrecuencyCriteria(List<Object> aStructureList) {
		return this.getSortedStructureList(aStructureList, 
				new Comparator<PatternsbyStructure>() {
					public int compare(PatternsbyStructure elem1, PatternsbyStructure elem2) {
						return (elem2.getDescriptorsPatterns().get(0).getSuccessFrequency()
								- elem1.getDescriptorsPatterns().get(0).getSuccessFrequency());
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
	public List<Object> sortByFailureFrecuencyCriteria(List<Object> aStructureList) {
		return this.getSortedStructureList(aStructureList, 
				new Comparator<PatternsbyStructure>() {
					public int compare(PatternsbyStructure elem1, PatternsbyStructure elem2) {
						return (elem1.getDescriptorsPatterns().get(0).getFailureFrequency()
								- elem2.getDescriptorsPatterns().get(0).getFailureFrequency());
					}
				}
		);
	}
	
	/**
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(PatternsbyStructure aListElement) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aListElement.getStructureName()))
				return true;
		}
		
		return false;
	}
		
	/**
	 * M&eacute;todo de instancia agregado
	 * @return
	 */
	public PatternsbyStructure getPatternByStructure(String aStructureName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aStructureName))
				return this.get(i-1);
		}
		
		return null;
	}
	
	/**
	 * @see "M&eacute;todo isMember: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructure
	 * @return
	 */
	public boolean contains(Object aDescriptiveElement) {
		for (PatternsbyStructure wdp:this)
			if (aDescriptiveElement instanceof GroupingHeuristic)
				wdp.getStructureName().equals(((GroupingHeuristic) aDescriptiveElement).getName());
			else if (aDescriptiveElement instanceof Structure)
				wdp.getStructureName().equals(((Structure) aDescriptiveElement).getName());
		
		return false;
	}
			
	/**
	 * This method takes as argument an unsorted list of Structures, and sorts them in according to the
	 * frequency rate of patterns in self that are similar or equal to the Structures' descriptions (i.e.,
	 * their list of Attributes). The process is as follows:
	 * 1. if the next Structure's name does not match the name of any the elements in self, such Structure
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
	private List<Object> getSortedStructureList(List<Object> aObjectList, Comparator c) {
		List<Object> tempList, leftOvers;
		PatternsbyStructureList sortedList;
		Object object;
		PatternsbyStructure pbs, singlepbs;
		DescriptorsPattern dp;
		List<Descriptor<Object>> descriptors;
		int numElements, numProcessedElts, i;
		
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<Object>();

		// Check precondition
		if (this.isEmpty() || aObjectList.isEmpty())
			return tempList;

		numElements = aObjectList.size();
		sortedList = new PatternsbyStructureList();
		leftOvers = new ArrayList<Object>();

		while (!(aObjectList.isEmpty())) {
			object = aObjectList.remove(0);
			pbs = null;
			if (this.contains(object)) {
				if (object instanceof GroupingHeuristic)
					pbs = this.getPatternByStructure(((GroupingHeuristic)object).getName());
				else if (object instanceof Structure)
					pbs = this.getPatternByStructure(((Structure)object).getName());
			} 
			
			if (pbs == null) leftOvers.add(object);
			else {
				tempList.add(object);
				sortedList.add(pbs);
				
				if (object instanceof GroupingHeuristic)
					Collections.sort(sortedList, c);
				else if (object instanceof Structure) {			
					descriptors = this.convertAttributesToDescriptorsOf((Structure)object);
					dp = pbs.whatPatternIsMostSimilarTo(descriptors);
					if (dp == null) leftOvers.add(object);
					else {
						tempList.add(object);
						singlepbs = new PatternsbyStructure(pbs.getStructureName());
						singlepbs.addPattern(dp);
						sortedList.add(singlepbs);
						Collections.sort(sortedList, c);
					}
				}
			}
		}
		
		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {
			singlepbs = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				if (tempList.get(i-1) instanceof GroupingHeuristic) {
					if (((GroupingHeuristic)tempList.get(i-1)).getName().equals(singlepbs.getStructureName())) {
						aObjectList.add(tempList.remove(i-1));
						i = tempList.size();
					}
				} else if (tempList.get(i-1) instanceof Structure) {
					if (((Structure)tempList.get(i-1)).getName().equals(singlepbs.getStructureName())) {
						aObjectList.add(tempList.remove(i-1));
						i = tempList.size();
					}
				}
				
				i = i + 1;
			}
		}
		if (!(leftOvers.isEmpty()))
			aObjectList.addAll(leftOvers);

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return aObjectList;
	}
	
	@SuppressWarnings("unchecked")
	private List<Descriptor<Object>> convertAttributesToDescriptorsOf(Structure aStructure) {
		List<Descriptor<Object>> aDescriptorList;
		Attribute a;
		Values vdList;
		Descriptor<Object> d;
		
		aDescriptorList = new ArrayList<Descriptor<Object>>();

		for (int i = 1; i <= aStructure.getAttributes().size(); i++) {
			a = aStructure.getAttributes().get(i-1);
			vdList = a.getValues().getValueDescriptors();
			
			if (vdList.size() == 1 &&
				vdList.get(Attribute.oneLevel()).get(Attribute.oneLevel()) instanceof SingleValue) {
				d = new CharacterDescriptor<Object>();
				
				d.set(aStructure.getName(), a.getName(), ((SingleValue<Object>)vdList.get(Attribute.oneLevel()).get(Attribute.oneLevel())).getValue());
				aDescriptorList.add(d);
			} else return null;
		}

		return aDescriptorList;
	}
}
