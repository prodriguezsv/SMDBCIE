/**
 * @see "Categoría Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ontology.common.GroupingHeuristic;
import ontology.common.Structure;

import searchHintsBase.Elements.WeightedDescriptorPattern;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class WeightedPatternsList extends HintsList<WeightedDescriptorPattern> {

	/**
	 * @see "Método add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(WeightedDescriptorPattern aPattern) {
		WeightedDescriptorPattern pattern;
		
		if (aPattern.getAccumulatedWeight() <= 0)
			return false;

		if (!(this.contains(aPattern))) {
			super.add(aPattern);
			return true;
		}
		
		pattern = this.getWeightedDescriptorPattern(aPattern.getPattern().getStructure());
		pattern.incrementAccumulatedWeight(aPattern.getAccumulatedWeight());
		pattern.incrementNumberTaxa();

		return true;
	}
	
	/**
	 * Return the argument list sorted by (highest) mean weight, if possible.
	 * NOTE: The elements of anUnsortedList may be instances of ONE of the following classes:
	 * GroupingHeuristic or Structure.
	 * @see "Método sortByFailureCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public List<Object> sortBySuccessCriteria(List<Object> aObjectList) {
		return this.getSortedStructureList(aObjectList, 
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
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(Object aDescriptiveElement) {
		for (WeightedDescriptorPattern wdp:this)
			if (aDescriptiveElement instanceof GroupingHeuristic)
				wdp.getPattern().getStructure().equals(((GroupingHeuristic) aDescriptiveElement).getName());
			else if (aDescriptiveElement instanceof Structure)
				wdp.getPattern().getStructure().equals(((Structure) aDescriptiveElement).getName());
		
		return false;
	}
			
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public WeightedDescriptorPattern getWeightedDescriptorPattern(String aStructureName) {
		for (WeightedDescriptorPattern wdp:this)
			if (wdp.getPattern().getStructure().equals(aStructureName))
				return wdp;
		
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
	// Pendiente de traducir
	@SuppressWarnings("unchecked")
	private List<Object> getSortedStructureList(List<Object> aObjectList, Comparator c) {
		List<Object> tempList, leftOvers;
		WeightedPatternsList sortedList;
		Object ule;
		WeightedDescriptorPattern wlElt;
		int numElements, numProcessedElts, i;
		
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<Object>();

		// Check precondition
		if (this.isEmpty() || aObjectList.isEmpty())
			return tempList;
		
		numElements = aObjectList.size();
		sortedList = new WeightedPatternsList();
		leftOvers = new ArrayList<Object>();
		
		while (!(aObjectList.isEmpty())) {
			ule = aObjectList.remove(0);
			wlElt = null;
			if (this.contains(ule)) {
				if (ule instanceof GroupingHeuristic)
					wlElt = this.getWeightedDescriptorPattern(((GroupingHeuristic)ule).getName());
				else if (ule instanceof Structure)
					wlElt = this.getWeightedDescriptorPattern(((Structure)ule).getName());
			} 
			
			if (wlElt == null) leftOvers.add(ule);
			else {
				tempList.add(ule);
				sortedList.add(wlElt);
				Collections.sort(sortedList, c);
			}
		}

		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {
			wlElt = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				if (tempList.get(i-1) instanceof GroupingHeuristic) {
					if (((GroupingHeuristic)tempList.get(i-1)).getName().equals(wlElt.getPattern().getStructure())) {
						aObjectList.add(tempList.remove(i-1));
						i = tempList.size();
					}
				} else if (tempList.get(i-1) instanceof Structure) {
					if (((Structure)tempList.get(i-1)).getName().equals(wlElt.getPattern().getStructure())) {
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
}
