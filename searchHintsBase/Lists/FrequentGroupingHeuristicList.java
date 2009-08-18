/**
 * @see "Categoría Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ontology.common.GroupingHeuristic;
import ontology.values.SingleDescriptor;

import searchHintsBase.Elements.HeuristicPattern;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class FrequentGroupingHeuristicList extends HintsList<HeuristicPattern> {

	/**
	 * @see "Método add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(HeuristicPattern aListElement) {
		HeuristicPattern elt;
		
		if ((aListElement.getSuccessFrequency() < 1) &&
		 	(aListElement.getFailureFrequency() < 1))
		 	return false;

		if (!(this.contains(aListElement))) {
			super.add(aListElement);
			return true;
		}
		
		elt = getListElement(aListElement.getGroupingHeuristicName(), aListElement.getValue());
		
		if (aListElement.getSuccessFrequency() > 0)
			elt.incrementSuccessFrequency();

		if (aListElement.getFailureFrequency() > 0)
			elt.incrementFailureFrequency();

		return false;
	}
	
	/**
	 * Return the argument list sorted by (lowest) failure rate, if possible.
	 * @see "Método sortByFailureCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public List<GroupingHeuristic> sortByFailureCriteria(List<GroupingHeuristic> aHeuristicsList) {
		return this.getSortedGroupingHeuristicList(aHeuristicsList, 
				new Comparator<HeuristicPattern>() {
					public int compare(HeuristicPattern elem1, HeuristicPattern elem2) {
						return (elem1.getFailureFrequency() - elem2.getFailureFrequency());
					}
				}
		);
	}
	
	/**
	 * Return the argument list sorted by (highest) success rate, if possible.
	 * @see "Método sortBySuccessCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public List<GroupingHeuristic> sortBySuccessCriteria(List<GroupingHeuristic> aHeuristicsList) {
		return this.getSortedGroupingHeuristicList(aHeuristicsList, 
				new Comparator<HeuristicPattern>() {
					public int compare(HeuristicPattern elem1, HeuristicPattern elem2) {
						return (elem2.getSuccessFrequency() - elem1.getSuccessFrequency());
					}
				}
		);
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(HeuristicPattern aListElement) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getGroupingHeuristicName().equals(aListElement.getGroupingHeuristicName()) &&
					(this.get(i-1).getValue().equals(aListElement.getValue())))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public HeuristicPattern getListElement(String aGroupingHeuristicName, Object aValue) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getGroupingHeuristicName().equals(aGroupingHeuristicName) &&
					(this.get(i-1).getValue().equals(aValue)))
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
	 * @param aGroupingHeuristic
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public boolean contains(GroupingHeuristic aGroupingHeuristic) {
		SingleDescriptor<Object> svd;
		
		if (aGroupingHeuristic.getValues().get(GroupingHeuristic.oneLevel()).size() == 1 &&
				aGroupingHeuristic.getValues().get(GroupingHeuristic.oneLevel()).get(GroupingHeuristic.oneLevel()) instanceof SingleDescriptor) {
			svd = (SingleDescriptor<Object>)aGroupingHeuristic.getValues().get(GroupingHeuristic.oneLevel()).get(GroupingHeuristic.oneLevel());
		
			for (int i = 1; i <= this.size(); i++) {
				if (this.get(i-1).getGroupingHeuristicName().equals(aGroupingHeuristic.getName()) &&
						this.get(i-1).getValue().equals(svd.getValue()))
						return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method takes as argument a list of grouping heuristics (each with a value), and sorts them according
	 * to their success or failure rate when compared to the elements in self.  The process is as follows: 1. all grouping
	 * heuristics that can be compared against the elements in self are first introduced in a temporary list (i.e., tempList),
	 * while the corresponding references in self are passed to a SortedCollection (which is setup to sort by success or
	 * failre frequency -- the sort block).; 2. all elements in the argument list that can NOT be compared against the elements
	 * in self are stored in another temporary list (i.e., leftOvers); 3. all elements in tempList are put back into the argument list,
	 * BUT in the order dictated by the references in the SortedCollection; 4. all elements in the list called LeftOvers are
	 * appended to the end of the argument list.
	 * PRECONDITION: (self isEmpty not) and (aHeuristicsList isEmpty not)
	 * RETURNS:	An empty list if either self or the argument list are empty;
	 * The argument list possibly sorted by (success or failure) frequency.
	 * @see "Método sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	private List<GroupingHeuristic> getSortedGroupingHeuristicList(List<GroupingHeuristic> aHeuristicsList, Comparator c) {
		List<GroupingHeuristic> tempList, leftOvers;
		FrequentGroupingHeuristicList sortedList;
		GroupingHeuristic gh;
		HeuristicPattern ghElt;
		SingleDescriptor<Object> svd;
		int numElements, numProcessedElts, i;
		
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<GroupingHeuristic>();

		// Check precondition
		if (this.isEmpty() || aHeuristicsList.isEmpty())
			return tempList;

		numElements = aHeuristicsList.size();
		sortedList = new FrequentGroupingHeuristicList();
		leftOvers = new ArrayList<GroupingHeuristic>();
		
		while (!(aHeuristicsList.isEmpty())) {
			gh = aHeuristicsList.remove(0);
			ghElt = null;
			if (this.contains(gh)) {
				svd = (SingleDescriptor<Object>)gh.getValues().get(GroupingHeuristic.oneLevel()).get(GroupingHeuristic.oneLevel());
				ghElt = this.getListElement(gh.getName(), svd.getValue());
			} 
			
			if (ghElt == null) leftOvers.add(gh);
			else {
				tempList.add(gh);
				sortedList.add(ghElt);
				Collections.sort(sortedList, c);
			}
		}
		
		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {
			ghElt = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				svd = (SingleDescriptor<Object>)tempList.get(i-1).getValues().get(GroupingHeuristic.oneLevel()).get(GroupingHeuristic.oneLevel());
				if (tempList.get(i-1).getName().equals(ghElt.getGroupingHeuristicName()) && 
						svd.getValue().equals(ghElt.getValue())) {
					aHeuristicsList.add(tempList.remove(i-1));
					i = tempList.size();
				}
				i = i + 1;
			}
		}
		if (!(leftOvers.isEmpty()))
			aHeuristicsList.addAll(leftOvers);

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return aHeuristicsList;
	}	
}
