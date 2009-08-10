/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package redundantDiscriminationNet.auxiliary;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;

/**
 * Purpose:  This class is used when the case-adding process needs to compare the descriptors of two cases.  When a case is being added to the net,
 * every descriptor is evaluated against indices and norms, in order to traverse the net until a spot is found to insert the new case.  If during net traversal
 * another case is found, then a new set of indices and norms must be created according to the similarities and differences between the descriptors of both
 * cases (i.e., the case-to-insert and the case-to-compare --the case found).  In this situation a ComparingTable is created, and ComparingTableTuple's
 * are added to it.
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class ComparingTable extends ArrayList<ComparingTableTuple<Object>> {
	
	/**
	 * Takes the descriptors of aCase1 and aCase2 and:
	 * a) Creates ComparingTableTuple's according to similarities and differences between these descriptors;
	 * b) Each newly created ComparingTableTuple is added to the ComparingTable
	 * @see "Método fillWith:and: del protocolo filling en SUKIA SmallTalk"
	 * @param aCase1
	 * @param aCase2
	 */
	public void fill(List<Descriptor<Object>> desc1, List<Descriptor<Object>> desc2) {
		Descriptor<Object> d1, d2;
		ComparingTableTuple<Object> tuple;
		int increment;
		
		if (desc1.size() > desc2.size()) increment = 0;
		else increment = desc2.size() - desc1.size();
		
		for (int i = 0; i < desc1.size()+ increment; i++) {			
			if  (i < desc1.size()) {
				d1 = desc1.get(i-1);
   				d2 = this.getDescriptor(desc2, d1.getAttribute());
   				
   				tuple = new ComparingTableTuple<Object>(d1.getAttribute(), d1.getValue(), ((d2 == null)? null:d2.getValue()));
   			} else {
   				d2 = desc2.get(i-1);
   		   		tuple = new ComparingTableTuple<Object>(d2.getAttribute(), null, d2.getValue());
   			}
   			
			this.add(tuple);
		}
	}

	/**
	 * @see "Método copyDescription:into: del protocolo reading en SUKIA SmallTalk"
	 * @param anInputDescription
	 * @param anOutputDescription
	 */
	/*public void copyDescription(List<Descriptor<Object>> anInputDescription, List<Descriptor<Object>> anOutputDescription) {
		for (int i = 1; i <= anInputDescription.size(); i++)
			anOutputDescription.add(anInputDescription.get(i-1));
	}*/
	
	/**
	 * @see "Método extractTuple del protocolo removing en SUKIA SmallTalk"
	 * @return
	 */
	public ComparingTableTuple<Object> extractTuple() {
		if (this.isEmpty()) return null;
		
		return this.remove(0);
	}
	
	/**
	 * @see "Método removeDescriptorIn:with: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescription
	 * @param anAttribute
	 * @return
	 */
	public Descriptor<Object> getDescriptor(List<Descriptor<Object>> aDescription, String anAttribute) {
		if (aDescription.isEmpty()) return null;

		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getAttribute().equals(anAttribute))
				return aDescription.get(i-1);
		}
		
		return null;
	}
}
