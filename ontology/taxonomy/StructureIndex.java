/**
 * 
 */
package ontology.taxonomy;

import java.util.ArrayList;
import java.util.Collections;

import ontology.common.Attribute;
import ontology.common.Structure;
import ontology.values.RangeDescriptor;
import ontology.values.ValueDescriptor;



/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class StructureIndex extends ArrayList<Structure> {
	
	/**
	 * @see "Método structureWith: del protocolo accessing en SUKIA SmallTalk"
	 * @param aStructureName
	 * @return
	 */
	public Structure getStructure(String aStructureName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getName().equals(aStructureName))
				return this.get(i-1);
		}
		return null;
	}
	
	public void addStructure(Structure aStructure) {
		this.add(aStructure);
		Collections.sort(this);
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructureName
	 * @return
	 */
	public boolean includes(String aStructureName) {
		if (getStructure(aStructureName)==null)
			return false;
		else return true;
	}

	/**
	 * If the receiver's SAV description contains structures with attributes that have range values, then, for each one of those structures:
	 * seek a structure in the receiver's predecessor's SAV description whose name matches the (receiver's) structure name;
	 * if the structure is found, seek in the attributes of the found structure for an attribute that matches the (receiver's structure) attribute name;
	 * if the attribute is found, seek a ValueDescriptor that is a range value;
	 * if the ValueDescriptor is found, determine if the measuring units are the same, and if the receiver's range value lies within the ValueDescriptor
	 * range just found.
	 * If the ranges are consistent, return true. Else, return false (i.e., inconsistent ranges).
	 * If neither the first nor the second steps are satisfied, get the receiver's predecessor's predecessor, and start again.
	 * This method will stop when the predecessor's level is ROOT
	 * @see "Método SAVRangesConsistentWith: del protocolo testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @return
	 */
	public boolean isRangesConsistent(Taxon aParentTaxon) {
		Structure s, ps;
		ValueDescriptor vd, pvd;
		Taxon pt;
		Attribute a, pa;
		
		if (aParentTaxon.getLevel().equals(TaxonomicRank.ROOT))
			return true;
		
		// Parse the receiver's SAV (structure) description
		for (int i = 1; i <= this.size(); i++) {
			// Get the next structure in the receiver's SAV description
			s = this.get(i-1);
			for (int j = 1; j <= s.getAttributes().size(); j++) {
				// Get the next attribute a from the retrieved structure
				a = s.getAttributes().get(j-1);
				for (int k = 1; k <= s.getAttributes().size(); k++) {
					// For the retrieved attribute, get the next value descriptor, and test if it's a range value
					vd = a.getValues().get(Attribute.oneLevel()).get(k-1);
					if (vd instanceof RangeDescriptor) {
						/*The attribute's value descriptor is a range value.  Get the receiver's predecessor and loop while the 
						 predecessor's level is not ROOT*/
						pt = aParentTaxon;
						while(!(pt.getLevel().equals(TaxonomicRank.ROOT))) {

							/*Find a structure, in the predecessor's SAV description, such that its name matches the
		 					 receiver's retrieved structure*/
							ps = pt.getSAVDescription().getStructure(s.getName());
							if (ps == null) {
								// Structure not found. Get the predecessor's predecessor, and loop all over again
								pt = pt.getPredecessor();
							} else {
								/* The structure was found.  Find an attribute, in the predecessor's structure's attribute list, 
							 	 such that its name matches the receiver's retrieved attribute name*/
								pa = ps.getAttribute(a.getName());
								if (pa == null) {
									// Structure not found. Get the predecessor's predecessor, and loop all over again
									pt = pt.getPredecessor();
								} else {
									for (int l = 1; l <= pa.getValues().get(Attribute.oneLevel()).size(); l++) {
										// Find a value descriptor, in the predecessor's attribute value descriptor list, such that it is a range value
										pvd = pa.getValues().get(Attribute.oneLevel()).get(l-1);
										if (pvd instanceof RangeDescriptor) {
											/*Value descriptor found. If the measuring units for the receiver's retrieved range value and the 
											 predecessor's range value are different, then there is an inconsistency*/
											if (!((RangeDescriptor) vd).getMeasuringUnit().equals(((RangeDescriptor) pvd).getMeasuringUnit()))
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
			}
		}	
											
		/*"The entire SAV decription of the receiver was parsed and nothing was found in the SAV description of all its
		 predecessors. Thus, assume there are no inconsistencies*/
		return true;
	}
}
