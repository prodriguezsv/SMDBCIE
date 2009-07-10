/**
 * @see "Categoría Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsLists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Descriptor;

import searchHintsElements.FrequentDescriptorPattern;
import searchHintsElements.FrequentStructurePatternElt;
import values.SingleDescriptor;
import values.Value;
import domainTheory.Attribute;
import domainTheory.Structure;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class FrequentStructurePatternList extends HintsList<FrequentStructurePatternElt> {

	/**
	 * @see "Método add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(FrequentStructurePatternElt aListElement) {
		FrequentStructurePatternElt elt;
				
		if (aListElement.getStructureName() == null)
			return false;

		if (aListElement.getFrequentDescriptorPatternList().isEmpty())
			return false;

		if (!(this.contains(aListElement))) {
			super.add(aListElement);
			return true;
		}
		
		elt = getListElement(aListElement.getStructureName());
		
		for (int i = 1; i <= aListElement.getFrequentDescriptorPatternList().size(); i++) {
			elt.addPattern(aListElement.getFrequentDescriptorPatternList().get(i-1));
		}

		return false;
	}
	
	/**
	 * Receives an unsorted list of Structures, each with a set of Attributes (i.e., the description of the Structure).
	 * Returns the same list but sorted (in descending order) according to the frequency of description patterns
	 * found in the elements of self that are similar to the Structures' descriptions
	 * @see "Método sortByFailureCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public List<Structure> sortByFrecuencyCriteria(List<Structure> aStructureList) {
		return this.getSortedStructureList(aStructureList, 
				new Comparator<FrequentStructurePatternElt>() {
					public int compare(FrequentStructurePatternElt elem1, FrequentStructurePatternElt elem2) {
						return (elem2.getFrequentDescriptorPatternList().get(0).getFrequency()
								- elem1.getFrequentDescriptorPatternList().get(0).getFrequency());
					}
				}
		);
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(FrequentStructurePatternElt aListElement) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aListElement.getStructureName()))
				return true;
		}
		
		return false;
	}
		
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public FrequentStructurePatternElt getListElement(String aStructureName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aStructureName))
				return this.get(i-1);
		}
		
		return null;
	}
	
	/**
	 * Determines if OK to include aListElement in self (i.e., all elements in self must belong to a class that self can handle)
	 * Returns: TRUE - Element OK;
	 * FALSE - Element not OK
	 * @see "Método isElementOK: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	/*public boolean isElementOK(FrecuentGroupingHeuristicElt aListElement) {
		return (aListElement instanceof FrecuentGroupingHeuristicElt);
	}*/
	
	/**
	 * @see "Método isMember: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructure
	 * @return
	 */
	public boolean contains(Structure aStructure) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aStructure.getName()))
					return true;
		}
		
		return false;
	}
		
	/**
	 * This method takes as argument an unsorted list of Structures, and sorts them in according to the frequency rate
	 * of patterns in self that are similar or equal to the Structures' descriptions (i.e., their list of Attributes). The process
	 * is as follows: 1. if the next Structure's name does not match the name of any the elements in self, such Structure
	 * is added to a leftOvers list (i.e., a list that contains all Structures that can't be processed); 2. if the Structure's name
	 * matches an element in self, the Structure's description is checked against the patterns contained in the retrieved
	 * element from self, to determine if there is a similar pattern; 3. if there is a similar pattern, the Structure is stored in a
	 * temporary list, and the element along with the pattern are stored in a sorted list (sort criteria: pattern frequency);
	 * 4. if no similar pattern is found, the Structure is added to the leftOvers list; 5. all Structures in the temporary list are
	 * added back to the argument list, according to the sort order dictated by the sorted list; 5. all Structures in the leftOvers
	 * list are appended to the end of the argument list.
	 * PRECONDITION: (self isEmpty not) and (anUnsorted isEmpty not)
	 * RETURNS:	An empty list, if either self or the argument list are empty;
	 * The argument list possibly sorted by similar pattern frequency.
	 * @see "Método sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	private List<Structure> getSortedStructureList(List<Structure> aStructureList, Comparator c) {
		List<Structure> tempList, leftOvers;
		FrequentStructurePatternList sortedList;
		Structure s;
		FrequentStructurePatternElt fsp, fspSinglePattern;
		FrequentDescriptorPattern p;
		List<Descriptor<Object>> descriptors;
		int numElements, numProcessedElts, i;
		
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<Structure>();

		// Check precondition
		if (this.isEmpty() || aStructureList.isEmpty())
			return tempList;

		numElements = aStructureList.size();
		sortedList = new FrequentStructurePatternList();
		leftOvers = new ArrayList<Structure>();
		
		while (!(aStructureList.isEmpty())) {
			s = aStructureList.remove(0);
			fsp = null;
			if (this.contains(s)) {
				fsp = this.getListElement(s.getName());
			} 
			
			if (fsp == null) leftOvers.add(s);
			else {
				tempList.add(s);
				sortedList.add(fsp);
				
				
				descriptors = this.convertAttributesToDescriptorsOf(s);
				p = fsp.whatPatternIsMostSimilarTo(descriptors);
				if (p == null) leftOvers.add(s);
				else {
					tempList.add(s);
					fspSinglePattern = new FrequentStructurePatternElt();
					fspSinglePattern.setStructureName(fsp.getStructureName());
					fspSinglePattern.addPattern(p);
					sortedList.add(fspSinglePattern);
					Collections.sort(sortedList, c);
				}
			}
		}
		
		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {
			fspSinglePattern = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				if (tempList.get(i-1).getName().equals(fspSinglePattern.getStructureName())) {
					aStructureList.add(tempList.remove(i-1));
					i = tempList.size();
				}
				i = i + 1;
			}
		}
		if (!(leftOvers.isEmpty()))
			aStructureList.addAll(leftOvers);

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return aStructureList;
	}
	
	@SuppressWarnings("unchecked")
	private List<Descriptor<Object>> convertAttributesToDescriptorsOf(Structure aStructure) {
		List<Descriptor<Object>> aDescriptorList;
		Attribute a;
		Value vdList;
		Descriptor<Object> d;
		
		aDescriptorList = new ArrayList<Descriptor<Object>>();

		for (int i = 1; i <= aStructure.getAttributes().size(); i++) {
			a = aStructure.getAttributes().get(i-1);
			vdList = a.getValues().getValueDescriptors();
			
			if (vdList.size() == 1 &&
				vdList.get(Attribute.oneLevel()).get(Attribute.oneLevel()) instanceof SingleDescriptor) {
				d = new Descriptor<Object>();
				
				d.add(a.getName(), ((SingleDescriptor<Object>)vdList.get(Attribute.oneLevel()).get(Attribute.oneLevel())).getValue());
				aDescriptorList.add(d);
			} else return null;
		}

		return aDescriptorList;
	}
}
