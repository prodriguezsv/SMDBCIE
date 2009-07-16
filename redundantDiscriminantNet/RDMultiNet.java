/**
 * @see "Categoría Sukia Redundant Discriminant Net de SUKIA Smalltalk"
 */
package redundantDiscriminantNet;

import java.util.ArrayList;
import java.util.List;

import main.Description;
import main.Descriptor;
import main.RDNet;

/**
 * Purpose: Implements the entry structure for a set of redundant discrimination networks, all linked by a common root.
 * The reasons for having several redundant nets are:
 * a) This application uses a different type of Descriptor that, instead of consisting of an attribute-value two-tuple, consists
 * of a structure-attribute-value three-tuple.
 * b) User requirements specify that user-system interaction should be focused on a structure basis.  That is, it is undesirable
 * to evaluate Descriptors belonging to a structure different from the one indicated by the user.
 * c) This application should reuse, as much as possible, the existing functionality to create and traverse redundant nets
 * based on two-tuple Descriptors.
 * Therefore, in order to keep all attribute-value pairs grouped in nets by their corresponding structure, the RDMultiNet creates
 * as many redundant discrimination nets as structures there are.  The root of each net is represented by the corresponding
 * structure name, the structure part of the three-tuple Descriptor is factored out thus leaving the two-tuple Descriptor's, and the
 * final redundant discrimination nets will all be constructed in terms of attibutes and values.
 * @author Armando
 *
 */
public class RDMultiNet {
	private RDMultiNetRoot root; 	//Instance of RDMultiNetRoot, which contains pointers to all the redundant nets in this muti-net structure.

	/**
	 * Initializes a newly created redundant discrimination multi-network.
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RDMultiNet() {
		root = new RDMultiNetRoot();
	}

	/**
	 * @see "Método root del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public RDMultiNetRoot getRoot() {
		return root;
	}

	/**
	 * Método de instancia agregado
	 * @param root
	 */
	public void setRoot(RDMultiNetRoot root) {
		this.root = root;
	}
	
	/**
	 * @see "Método add del protocolo adding en SUKIA SmallTalk"
	 * @param aCase
	 */
	// Pendiente de traducir
	public void add(SAVCase aCase) {
		List<Description<Descriptor<Object>>> descriptorLists;
		List<String> structureList;
		SAVDescriptor SAVdescriptor;
		Descriptor<Object> descriptor;
		Description<Descriptor<Object>> SDescription;
		int s;
		RDNet anRDNet;
		
		// Step 1: Restore the (SAV) case's description
		aCase.backupDescription();

		/* Step 2: Segregate the case's description components (.i.e., the tuples (structure, attribute, value)) in separate Descriptor lists
			       represented each of the structures*/
		descriptorLists = new ArrayList<Description<Descriptor<Object>>>();
		structureList    = new ArrayList<String>();
		for( int i = 1; i <= aCase.getDescription().size(); i++) {
			// Get the next SAVdescriptor
			SAVdescriptor = (SAVDescriptor) aCase.getDescription().get(i-1);
			// Create a new Descriptor and assign its values with those of SAVdescriptor
			descriptor = new Descriptor<Object>();
			descriptor.add(SAVdescriptor.getAttribute(), SAVdescriptor.getValue());

			// Determine if the structure name in SAVdescriptor has already been included in structureList
			if (!(structureList.contains(SAVdescriptor.getStructure()))) {
				// The structure name was not found in structureList. Append it to structureList
				structureList.add(SAVdescriptor.getStructure());

				/* Create a new list that will contain all descriptors referenced by the structure name (in SAVdesscriptor).  
				 Append this new list to descriptorLists*/
				SDescription = new Description<Descriptor<Object>>();
				SDescription.add(descriptor);
				descriptorLists.add(SDescription);
			} else {
				// Find the structure name position in structureList (i..e, the index value)
				s = 1;
				while (s <= structureList.size()) {
					if (SAVdescriptor.getStructure().equals(structureList.get(s-1))) {
						// Append the descriptor to the corresponding SDescription 
						descriptorLists.get(s-1).add(descriptor); 

						// Break out of the loop
						s = structureList.size() + 1;
					} else {
						// Try the next element
						s = s + 1;
					}
				}
			}
		}

		/* Step 3: For every (structure-defined) list: a) Copy the segregated description list to the case's description. 
		             b) Create a new NetRoot. c) Add the case to the structure-defined network.*/
		aCase.getStructureCopy().clear();
		aCase.copyStructureListWith(structureList);
		for( int i = 1; i <= structureList.size(); i++) {
			// Verificar
			aCase.getDescription().clear(); 
			while (!(descriptorLists.get(i-1).isEmpty())) {
				aCase.addToDescription(descriptorLists.get(i-1).get(0)); 
				descriptorLists.get(i-1).remove(0);
			}

			if (!(this.getRoot().contains(structureList.get(i-1))))
				this.getRoot().addRDNet(structureList.get(i-1));
			
			anRDNet = this.getRoot().getRDNet(structureList.get(i-1));
			
			
			anRDNet.add(aCase);
			aCase.removeCurrentStructure();
		}
				
		// Step 4: Restore the case's original description
		aCase.restoreDescription();
		aCase.getStructureCopy().clear();
		aCase.getDescriptionCopy().clear();
		
	}
}
