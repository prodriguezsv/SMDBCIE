/**
 * Este paquete agrupa las listas que almacenan patrones de b&uacute;squeda de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ontology.common.Description;
import ontology.common.Descriptor;

import searchHintsBase.Elements.SpecificDescriptorPattern;
import searchHintsBase.Elements.SpecificPatternsbyStructure;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class SpecificPatternsbyStructureList extends HintsList<SpecificPatternsbyStructure> {

	/**
	 * Performs one of two actions:
	 * 1. If the argument is not included in self (i.e., self is empty OR the argument's name is different
	 * from the names of all elements in self), then it is included as a new element in self.
	 * 2. If the argument already exists in self (i.e., self contains an element whose name is the same as
	 * the argument), then the attributes of the argument are either added to the attributes of the existing
	 * element in self, OR the frequency values are updated (in case attributes in the argument already exist
	 * for the element in self.
	 * @see "M&eacute;todo add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(SpecificPatternsbyStructure patterns) {
		SpecificPatternsbyStructure spbs;

		if (patterns == null)
			return false;
		
		if (patterns.getPatterns().isEmpty())
			return false;
		
		if (!(this.contains(patterns))) {
			super.add(patterns);
			return true;
		}
		
		spbs = this.getPatterns(patterns.getStructureName());
		
		for (SpecificDescriptorPattern sdp:patterns.getPatterns())
			spbs.addPattern(sdp);
		
		return true;
	}
	
	/**
	 * Return a structure's attribute list sorted by their frequency as indices to successful cases in the case memory.
	 * @see "M&eacute;todo sortBySuccessCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public Description sortBySuccessCriteria(Description description) {
		return this.getSortedSpecificDescriptorsList(description, 
				new Comparator<SpecificDescriptorPattern>() {
					public int compare(SpecificDescriptorPattern elem1, SpecificDescriptorPattern elem2) {
						return (elem2.getFrequency() - elem1.getFrequency());
					}
				}
		);
	}
	
	/**
	 * Determines if the argument's name (i.e., a Structure name or a SpecificStructureAttributeElt name)
	 * is already included in self
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(SpecificPatternsbyStructure aPattern) {
		for (SpecificPatternsbyStructure spbs:this) {
			if (spbs.getStructureName().equals(aPattern.getStructureName()))
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
		for (SpecificPatternsbyStructure spbs:this) {
			if (spbs.getStructureName().equals(aName))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Obtiene un patr&oacute;n espec&iacute;fico con nombre de estructura aName
	 * @return
	 */
	public SpecificPatternsbyStructure getPatterns(String aName) {
		for (SpecificPatternsbyStructure spbs:this) {
			if (spbs.getStructureName().equals(aName))
				return spbs;
		}
		
		return null;
	}
	
	/**
	 * This method takes a structure as argument.  The structure's attributes may be sorted according to
	 * their frequency (as successful top-level indices in the case memory), when compared against the
	 * SpecificAttributes belonging to a structure reference in self. The process is as follows:
	 * 1. Determine if the name of the structure argument exists in self. If so, obtain the corresponding
	 * element from self. Else, return an empty list (because the structure's attributes can't be sorted).
	 * 2. All structure attributes, that can be compared against the SpecificAttributes in the retrieved
	 * element from self, are introduced in a temporary list (i.e., tempList), while the corresponding
	 * SpecificAttributes are passed to a SortedCollection (which is setup to sort by frquency -- the sort
	 * block).
	 * 3. All structure attributes that can NOT be compared against the SpecificAttributes are stored in
	 * another temporary list (i.e., leftOvers);
	 * 4. All structure attributes in tempList are put back into the structure's attribute list, BUT in the
	 * order dictated by the references in the SortedCollection;
	 * 5. All structure attributes in the list called LeftOvers are appended to the end of the structure's
	 * attribute list.
	 * PRECONDITION: (self isEmpty not) and (aStructure attributes isEmpty not)
	 * @see "M&eacute;todo sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 * @return An empty list, if either self or the list to be processed are empty; The attribute list of
	 * the structure, possibly sorted by frequency.
	 */
	@SuppressWarnings("unchecked")
	private Description getSortedSpecificDescriptorsList(Description  description, Comparator c) {
		Description tempList, leftOvers, outList;
		List<SpecificDescriptorPattern> sortedList;
		SpecificPatternsbyStructure spbs;
		Descriptor descriptor;
		SpecificDescriptorPattern sdp;
		int numElements, numProcessedElts, i;
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new Description();
		/* Output list: all sorted attributes are stored in this list, as well as in the structure's attribute list.  However, outList
		 is the list to be returned as result from this process because the structure's attribute list is sorted by name (default).*/
		outList = new Description();
		outList.setDescriptors(description.getDescriptors());

		// Check precondition
		if (this.isEmpty() || outList.getDescriptors().isEmpty())
			return tempList;

		numElements = outList.getDescriptors().size();
		spbs = this.getPatterns(((Descriptor)outList.getDescriptors().get(0)).getStructure());
		if (spbs == null) return tempList;
		sortedList = new ArrayList<SpecificDescriptorPattern>();
		
		leftOvers = new Description();

		while (!(outList.getDescriptors().isEmpty())) {
			descriptor = (Descriptor)outList.getDescriptors().remove(0);
			if ((sdp = spbs.getPattern(descriptor)) == null)			
				leftOvers.addDescriptors(descriptor);
			else {
				tempList.addDescriptors(descriptor);
				sortedList.add(sdp);
				Collections.sort(sortedList, c);
			}
		}
				
		// Get the number of elements to be processed
		numProcessedElts = tempList.getDescriptors().size();

		while (!(sortedList.isEmpty())) {
			sdp = sortedList.remove(0);
			i = 1;
			while (i <= tempList.getDescriptors().size()) {
				if (((Descriptor)tempList.getDescriptors().get(i-1)).getAttribute().equals(sdp.getPattern().getAttribute())) {
					descriptor = (Descriptor)tempList.getDescriptors().remove(i-1);
					outList.addDescriptors(descriptor);
					i = tempList.getDescriptors().size();
				}
				i = i + 1;
			}
		}
		
		while (!(leftOvers.getDescriptors().isEmpty()))
			outList.addDescriptors((Descriptor)leftOvers.getDescriptors().remove(0)); 

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return outList;
	}	
}
