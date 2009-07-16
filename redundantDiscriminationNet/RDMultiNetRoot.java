/**
 * @see "Categoría Sukia Redundant Discriminant Net de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;


/**
 * Purpose: Main element of an RDMultiNet.  The main feature of an RDMultiNetRoot is that it contains a collection of
 * pointers to all the redundant discrimination nets.
 * @author Armando
 *
 */
public class RDMultiNetRoot {
	private List<RDNet> nets; 	// Instance of OrderedCollection whose elements should all be of type RDNet.
	
	/**
	 * Initializes a newly created multi-net root.  The nets ordered collection is empty at first
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RDMultiNetRoot() {
		nets = null;
	}

	/**
	 * @see "Método nets del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<RDNet> getNets() {
		return nets;
	}

	/**
	 * Método de instancia agregado
	 * @param nets
	 */
	public void setNets(List<RDNet> nets) {
		this.nets = nets;
	}
	
	/**
	 * Returns an RDNet whose root's descriptor has the same name as NetRootStructure; nil otherwise.
	 * @see "Método getNetWith del protocolo accessing en SUKIA SmallTalk"
	 */
	public RDNet getRDNet(String aStructureName) {
		if (this.getNets() != null) {
			for(RDNet rdn: this.getNets()) {
				if (rdn.getRoot().getStructure().equals(aStructureName))
					return rdn;
			}
		}
		
		return null;
	}
	
	/**
	 * Adds a new instance of RDNet to the nets list.  Before doing so, the root reference of the newly-created RDNet is changed for one
	 * belonging to the class SAVRoot (a subclass of RootNorm).  This way, the 'structure' attribute in the changed root can be accessed
	 * @see "Método addRDNetWith: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructureName
	 */
	public void addRDNet(String aStructureName) {
		RDNet aRDNet;
		RootNorm aRoot;
		
		aRDNet = new RDNet();
		aRoot = new RootNorm(aStructureName);
		aRDNet.setRoot(aRoot);
		
		if (this.nets == null)
			this.nets = new ArrayList<RDNet>();
		
		this.getNets().add(aRDNet);
	}

	/**
	 * Returns true if aStructure is the descriptor of an RDNet's root, in the nets list. Returns false otherwise
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructureName
	 */
	public boolean contains(String aStructureName) {
		if (this.getNets() != null) {
			for(RDNet rdn: this.getNets()) {
				if (rdn.getRoot().getStructure().equals(aStructureName))
					return true;
			}
		}
		
		return false;
	}
}
