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

import searchHintsBase.Elements.WeightedDescriptorPattern;
import searchHintsBase.Elements.WeightedPatternsbyStructure;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class WeightedPatternsbyStructureList extends HintsList<WeightedPatternsbyStructure> {

	/**
	 * @see "M&eacute;todo add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(WeightedPatternsbyStructure patterns) {
		WeightedPatternsbyStructure wpbs;

		if (patterns == null)
			return false;
		
		if (patterns.getPatterns().isEmpty())
			return false;
		
		if (!(this.contains(patterns))) {
			super.add(patterns);
			return true;
		}
		
		wpbs = this.getPatterns(patterns.getStructureName());
		
		for (WeightedDescriptorPattern wdp:patterns.getPatterns())
			wpbs.addPattern(wdp);

		return true;
	}
	
	/**
	 * Return the argument list sorted by (highest) mean weight, if possible.
	 * NOTE: The elements of anUnsortedList may be instances of ONE of the following classes:
	 * GroupingHeuristic or Structure.
	 * @see "M&eacute;todo sortByFailureCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> sortByMeanWeightCriteria(List<Descriptor<Object>> descriptors) {
		return this.getSortedDescriptorsList(descriptors, 
				new Comparator<WeightedDescriptorPattern>() {
					public int compare(WeightedDescriptorPattern elem1, WeightedDescriptorPattern elem2) {
						if ((elem2.meanWeight()	- elem1.meanWeight()) > 0)
							return 1;
						else if ((elem2.meanWeight() - elem1.meanWeight()) < 0)
							return -1;
						else return 0;
					}
				}
		);
	}
	
	/**
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(WeightedPatternsbyStructure aPattern) {		
		for (WeightedPatternsbyStructure wpbs:this) {
			if (wpbs.getStructureName().equals(aPattern.getStructureName()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Determines if the argument's name (i.e., a Structure name or a SpecificStructureAttributeElt name)
	 * is already included in self
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(String aName) {
		for (WeightedPatternsbyStructure wpbs:this) {
			if (wpbs.getStructureName().equals(aName))
				return true;
		}
		
		return false;
	}
			
	/**
	 * Obtiene un patr&oacute;n espec&iacute;fico con nombre de estructura aStructureName
	 * @return
	 */
	public WeightedPatternsbyStructure getPatterns(String aName) {
		for (WeightedPatternsbyStructure wpbs:this) {
			if (wpbs.getStructureName().equals(aName))
				return wpbs;
		}
		
		return null;
	}
		
	/**
	 * This method takes as argument a list of EITHER grouping heuristics OR structures, and sorts them
	 * according to their mean weight when compared to the elements in this list.  The process is as follows:
	 * 1. all elements that can be compared against the elements in this list are first introduced in a
	 * temporary list (i.e., tempList), while the corresponding references in this list are passed to a
	 * sorted list (which is setup to sort by mean weight);
	 * 2. all elements in the argument list that can NOT be compared against the elements in this list are
	 * stored in another temporary list (i.e., leftOvers);
	 * 3. all elements in tempList are put back into the argument list, BUT in the order dictated by the
	 * references in the sorted list;
	 * 4. all elements in the list called LeftOvers are appended to the end of the argument list.
	 * PRECONDITION: (this list isEmpty not) and (anUnsorted isEmpty not)
	 * @see "Método sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 * @return An empty list, if either this list or the argument list are empty; The argument list possibly
	 * sorted by mean weight.
	 */
	@SuppressWarnings("unchecked")
	private List<Descriptor<Object>> getSortedDescriptorsList(List<Descriptor<Object>> description, Comparator c) {
		List<Descriptor<Object>> tempList, leftOvers;
		List<WeightedDescriptorPattern> sortedList;
		WeightedPatternsbyStructure wpbs;
		Descriptor<Object> descriptor;
		WeightedDescriptorPattern wdp;
		int numElements, numProcessedElts, i;
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<Descriptor<Object>>();

		// Check precondition
		if (this.isEmpty() || description.isEmpty())
			return tempList;
		
		numElements = description.size();
		wpbs = this.getPatterns(description.get(0).getStructure());
		if (wpbs == null) return tempList;
		sortedList = new ArrayList<WeightedDescriptorPattern>();
		
		leftOvers = new ArrayList<Descriptor<Object>>();
		
		while (!(description.isEmpty())) {
			descriptor = description.remove(0);
			if ((wdp = wpbs.getPattern(descriptor)) == null)
				leftOvers.add(descriptor);
			else {
				tempList.add(descriptor);
				sortedList.add(wdp);
				Collections.sort(sortedList, c);
			}
		}

		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {		
			wdp = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				if (tempList.get(i-1).getAttribute().equals(wdp.getPattern().getAttribute())) {
					descriptor = tempList.remove(i-1);
					description.add(descriptor);
					i = tempList.size();
				}
				i = i + 1;
			}
			
		}
				
		if (!(leftOvers.isEmpty()))
			description.addAll(leftOvers);

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return description;
	}
}
