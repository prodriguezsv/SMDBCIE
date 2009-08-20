/**
 * @see "Categoría Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ontology.common.Attribute;
import ontology.common.Structure;

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
	 * 1. If the argument is not included in self (i.e., self is empty OR the argument's name is different from
	 * the names of all elements in self), then it is included as a new element in self.
	 * 2. If the argument already exists in self (i.e., self contains an element whose name is the same as the
	 * argument), then the attributes of the argument are either added to the attributes of the existing element
	 * in self, OR the frequency values are updated (in case attributes in the argument already exist for the element
	 * in self.
	 * @see "Método add: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public boolean add(SpecificPatternsbyStructure aListElement) {
		SpecificPatternsbyStructure elt;

		if (aListElement.getSpecificDescriptorPatterns().isEmpty())
			return false;
		
		if (!(this.contains(aListElement))) {
			super.add(aListElement);
			return true;
		}
		
		elt = getListElement(aListElement.getStructureName());
		
		//Ojo
		for (int i = 1; i <= aListElement.getSpecificDescriptorPatterns().size(); i++) {
			elt.addSpecificDescriptorPattern(aListElement.getSpecificDescriptorPatterns().get(i-1));
		}
		
		return false;
	}
	
	/**
	 * Return a structure's attribute list sorted by their frequency as indices to successful cases in the case memory.
	 * @see "Método sortBySuccessCriteria: del protocolo sorting en SUKIA SmallTalk"
	 * @return
	 */
	public List<Attribute> sortBySuccessCriteria(Structure aStructure) {
		return this.getSortedSpecificStructureAttributeList(aStructure, 
				new Comparator<SpecificDescriptorPattern>() {
					public int compare(SpecificDescriptorPattern elem1, SpecificDescriptorPattern elem2) {
						// Ojo verificar
						return (elem2.getFrequency() - elem1.getFrequency());
					}
				}
		);
	}
	
	/**
	 * Determines if the argument's name (i.e., a Structure name or a SpecificStructureAttributeElt name)
	 * is already included in self
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	/*public boolean contains(Structure aListElement) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getName().equals(aListElement.getName()))
				return true;
		}
		
		return false;
	}*/
	
	/**
	 * Determines if the argument's name (i.e., a Structure name or a SpecificStructureAttributeElt name)
	 * is already included in self
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean contains(String aName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aName))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public SpecificPatternsbyStructure getListElement(String aName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i-1).getStructureName().equals(aName))
				return this.get(i-1);
		}
		
		return null;
	}
	
	/**
	 * This method takes a structure as argument.  The structure's attributes may be sorted according to their
	 * frequency (as successful top-level indices in the case memory), when compared against the SpecificAttributes
	 * belonging to a structure reference in self. The process is as follows:
	 * 1. Determine if the name of the structure argument exists in self. If so, obtain the corresponding element from self.
	 * Else, return an empty list (because the structure's attributes can't be sorted).
	 * 2. All structure attributes, that can be compared against the SpecificAttributes in the retrieved element from self,
	 * are introduced in a temporary list (i.e., tempList), while the corresponding SpecificAttributes are passed to a
	 * SortedCollection (which is setup to sort by frquency -- the sort block).
	 * 3. All structure attributes that can NOT be compared against the SpecificAttributes are stored in another temporary
	 * list (i.e., leftOvers);
	 * 4. All structure attributes in tempList are put back into the structure's attribute list, BUT in the order dictated by the
	 * references in the SortedCollection;
	 * 5. All structure attributes in the list called LeftOvers are appended to the end of the structure's attribute list.
	 * PRECONDITION: (self isEmpty not) and (aStructure attributes isEmpty not)
	 * RETURNS:	An empty list, if either self or the list to be processed are empty;
	 * The attribute list of the structure, possibly sorted by frequency.
	 * @see "Método sortList:withBlock: del protocolo private en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	private List<Attribute> getSortedSpecificStructureAttributeList(Structure aStructure, Comparator c) {
		List<Attribute> tempList, leftOvers, outList;
		List<SpecificDescriptorPattern> sortedList;
		SpecificPatternsbyStructure s;
		Attribute a;
		SpecificDescriptorPattern aElt;
		int numElements, numProcessedElts, i;
		
		
		// First thing is to set the number of processed items to 0
		this.resetPercentageItemsProcessed();

		tempList = new ArrayList<Attribute>();

		// Check precondition
		if (this.isEmpty() || aStructure.getAttributes().isEmpty())
			return tempList;

		numElements = aStructure.getAttributes().size();
		s = this.getListElement(aStructure.getName());
		if (s == null) return tempList;
		sortedList = new ArrayList<SpecificDescriptorPattern>();

		/* Output list: all sorted attributes are stored in this list, as well as in the structure's attribute list.  However, outList
		 is the list to be returned as result from this process because the structure's attribute list is sorted by name (default).*/
		outList = new ArrayList<Attribute>();
		leftOvers = new ArrayList<Attribute>();

		while (!(aStructure.getAttributes().isEmpty())) {
			a = aStructure.getAttributes().remove(0);
			aElt = null;
			if (this.contains(a.getName())) {
				aElt = s.getSpecificDescriptorPattern(a.getName());
			} 
			
			if (aElt == null) leftOvers.add(a);
			else {
				tempList.add(a);
				sortedList.add(aElt);
				Collections.sort(sortedList, c);
			}
		}
				
		// Get the number of elements to be processed
		numProcessedElts = tempList.size();

		while (!(sortedList.isEmpty())) {
			aElt = sortedList.remove(0);
			i = 1;
			while (i <= tempList.size()) {
				if (tempList.get(i-1).getName().equals(aElt.getPattern().getAttribute())) {
					a = tempList.remove(i-1);
					aStructure.getAttributes().add(a);
					outList.add(a);
					i = tempList.size();
				}
				i = i + 1;
			}
		}
		
		while (!(leftOvers.isEmpty())) {
			a = leftOvers.remove(0);
			aStructure.getAttributes().add(a);
			outList.add(a); 
		}

		// Determine the percentage of processed elements and return the processed list
		this.setPercentageItemsProcessed(numProcessedElts / numElements);
		return outList;
	}	
}
