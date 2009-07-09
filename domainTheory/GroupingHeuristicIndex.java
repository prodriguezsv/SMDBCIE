/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package domainTheory;

import java.util.ArrayList;
import java.util.Collections;

import values.ValueDescriptor;
import values.RangeDescriptor;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class GroupingHeuristicIndex extends ArrayList<GroupingHeuristic> {

	/**
	 * @see "Método groupingHeuristicWith: del protocolo accessing en SUKIA SmallTalk"
	 * @param aGHName
	 * @return
	 */
	public GroupingHeuristic getGroupingHeuristic(String aGHName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getName().equals(aGHName)) 
				return this.get(i-1);
		}
		
		return null;
	}
	
	public void addGroupingHeuristic(GroupingHeuristic aGroupingHeuristic) {
		this.add(aGroupingHeuristic);
		Collections.sort(this);
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aGroupingHeuristicName
	 * @return
	 */
	public boolean contains(String aGroupingHeuristicName) {
		if (getGroupingHeuristic(aGroupingHeuristicName)==null)
			return false;
		else return true;
	}
	
	/**
	 * If the receiver's GH description contains grouping heuristics with range values, then, for each one of those grouping heuristics:
	 * seek a grouping heuristic in the receiver's predecessor's GH description whose name matches the (receiver's) grouping heuristic name;
	 * if the grouping heuristic is found, seek a ValueDescriptor that is a range value;
	 * if the ValueDescriptor is found, determine if the measuring units are the same, and if the receiver's range value lies within  the ValueDescriptor
	 * range just found.
	 * If the ranges are consistent, return true. Else, return false (i.e., inconsistent ranges).
	 * f neither the first nor the second steps are satisfied, get the receiver's predecessor's predecessor, and start again.
	 * This method will stop when the predecessor's level is ROOT
	 * @see "Método GHRangesConsistentWith: del protocolo testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @return
	 */
	public boolean isRangesConsistent(Taxon aParentTaxon) {
		GroupingHeuristic gh, pgh;
		ValueDescriptor vd, pvd;
		Taxon pt;
		
		if (aParentTaxon.getLevel().equals(TaxonomicLevels.getRoot()))
			return true;
		
		// Parse the receiver's grouping heuristic (GH) description
		for (int i = 1; i <= this.size(); i++) {
			// Get the next grouping heuristic in the receiver's GH description
			gh = this.get(i-1);
			for (int j = 1; j <= gh.getValues().get(GroupingHeuristic.oneLevel()).size(); j++) {
				// Get the next value descriptor, and test if it's a range value
				vd = gh.getValues().get(GroupingHeuristic.oneLevel()).get(j-1);
				if (vd instanceof RangeDescriptor) {
					/* The grouping heuristic's value descriptor is a range value.  Get the receiver's predecessor 
					 and loop while the predecessor's level is not ROOT*/
					pt = aParentTaxon;
					while(!(pt.getLevel().equals(TaxonomicLevels.getRoot()))) {
						/* Find a grouping heuristic, in the predecessor's GH description, such that its name matches the
		 				 receiver's retrieved grouping heuristic*/
						pgh = pt.getGHDescription().getGroupingHeuristic(gh.getName());
						if (pgh == null) {
							// Grouping heuristic not found. Get the predecessor's predecessor, and loop all over again"
							pt = pt.getPredecessor();
						} else {
							/* The grouping heuristic was found.  Find a value descriptor, in the predecessor's grouping heuristic's value 
							 descriptor list, such that it is a range value*/
							for (int k = 1; k <= pgh.getValues().get(GroupingHeuristic.oneLevel()).size(); k++) {
								pvd = pgh.getValues().get(GroupingHeuristic.oneLevel()).get(k-1);
								if (pvd instanceof RangeDescriptor) {
									/* Value descriptor found. If the measuring units for the receiver's retrieved range value and the 
									 predecessor's range value are different, then there is an inconsistency*/
									if (((RangeDescriptor) vd).getMeasuringUnit().equals(((RangeDescriptor) pvd).getMeasuringUnit()))
										return false;
									else {
										/* Measuring units are the same for both ranges.  Determine if the receiver's value descriptor
										 range lies within the predecessor's value descriptor range */
										if (((RangeDescriptor) vd).isRangeWithin(((RangeDescriptor) pvd).getLowerBound(), ((RangeDescriptor) pvd).getUpperBound())) 
											return true;
										else return false; 
									}
								}
							}
						}
					}
				}
			}
					
		}	
											
		// The entire GH decription of the receiver was parsed and nothing was found in the GH description of all its
		// predecessors. Thus, assume there are no inconsistencies
		return true;
	}
}
