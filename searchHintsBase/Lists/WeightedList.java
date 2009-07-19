/**
 * @see "Categoría Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import onthology.common.GroupingHeuristic;
import onthology.common.Structure;

import searchHintsBase.Elements.WeightedIndicator;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class WeightedList<T extends WeightedIndicator> extends HintsList<T> {

	/**
	 * @see "Método add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(T aListElement) {
		T elt;
		
		if (aListElement.getAccumulatedWeight() <= 0)
			return false;

		if (!(this.contains(aListElement))) {
			super.add(aListElement);
			return true;
		}
		
		elt = getListElement(aListElement.getIndicatorName());
		elt.incrementAccumulatedWeight(aListElement.getAccumulatedWeight());
		elt.incrementNumberTaxa();

		return false;
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
				new Comparator<T>() {
					public int compare(T elem1, T elem2) {
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
	public boolean contains(Object aListElement) {
		
		for (int i = 1; i <= this.size(); i++) {
			if (aListElement instanceof GroupingHeuristic)
				if (this.get(i-1).getIndicatorName().equals(((GroupingHeuristic)aListElement).getName()))
					return true;
			else if (aListElement instanceof Structure)
				if (this.get(i-1).getIndicatorName().equals(((Structure)aListElement).getName()))
					return true;
		}
		
		return false;
	}
			
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public T getListElement(String aIndicatorName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getIndicatorName().equals(aIndicatorName))
				return this.get(i-1);
		}
		
		return null;
	}
		
	/**
	 * This method takes as argument a list of EITHER grouping heuristics OR structures, and sorts them according
	 * to their mean weight when compared to the elements in self.  The process is as follows: 1. all elements that can
	 * be compared against the elements in self are first introduced in a temporary list (i.e., tempList), while the corresponding
	 * references in self are passed to a SortedCollection (which is setup to sort by mean weight -- the sort block).; 2. all elements
	 * in the argument list that can NOT be compared against the elements in self are stored in another temporary list (i.e., leftOvers);
	 * 3. all elements in tempList are put back into the argument list, BUT in the order dictated by the references in the
	 * SortedCollection; 4. all elements in the list called LeftOvers are appended to the end of the argument list.
	 * PRECONDITION: (self isEmpty not) and (anUnsorted isEmpty not)
	 * RETURNS:	An empty list, if either self or the argument list are empty;
	 * The argument list possibly sorted by mean weight."
	 * @see "Método sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 */
	// Pendiente de traducir
	@SuppressWarnings("unchecked")
	private List<Object> getSortedStructureList(List<Object> aObjectList, Comparator c) {
		List<Object> tempList, leftOvers;
		WeightedList<T> sortedList;
		Object ule;
		T wlElt;
		int numElements, numProcessedElts, i;
		
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<Object>();

		// Check precondition
		if (this.isEmpty() || aObjectList.isEmpty())
			return tempList;
		
		numElements = aObjectList.size();
		sortedList = new WeightedList<T>();
		leftOvers = new ArrayList<Object>();
		
		while (!(aObjectList.isEmpty())) {
			ule = aObjectList.remove(0);
			wlElt = null;
			if (this.contains(ule)) {
				if (ule instanceof GroupingHeuristic)
					wlElt = this.getListElement(((GroupingHeuristic)ule).getName());
				else if (ule instanceof Structure)
					wlElt = this.getListElement(((Structure)ule).getName());
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
					if (((GroupingHeuristic)tempList.get(i-1)).getName().equals(wlElt.getIndicatorName())) {
						aObjectList.add(tempList.remove(i-1));
						i = tempList.size();
					}
				} else if (tempList.get(i-1) instanceof Structure) {
					if (((Structure)tempList.get(i-1)).getName().equals(wlElt.getIndicatorName())) {
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
