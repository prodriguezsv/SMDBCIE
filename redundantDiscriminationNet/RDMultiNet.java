/**
 * @see "Categoría Sukia Redundant Discriminant Net de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.List;

import onthology.CBR.Case;



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
		root = null;
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
	public void add(Case aCase) {
		List<String> structuresList;
		RDNet aRDNet;

		/* Step 2: Segregate the case's description components (.i.e., the tuples (structure, attribute, value)) in separate Descriptor lists
			       represented each of the structures*/
		
		structuresList = aCase.getStructuresList();
		
		for(String s: structuresList) {	
			/* Step 3: For every (structure-defined) list: a) Copy the segregated description list to the case's description. 
			             b) Create a new NetRoot. c) Add the case to the structure-defined network.*/

			if (!(this.getRoot().contains(s)))
				this.getRoot().addRDNet(s);
			
			aRDNet = this.getRoot().getRDNet(s);
		
			aRDNet.add(aCase);
		}
	}
}
