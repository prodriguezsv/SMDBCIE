/**
 * @see "Categoría Sukia Redundant Discriminant Net de SUKIA Smalltalk"
 */
package redundantDiscriminantNet;

import java.util.ArrayList;
import java.util.List;

import main.RDNet;

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
		nets = new ArrayList<RDNet>();
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
	public RDNet getRDNet(String NetRootStructure) {
		for( int i = 1; i <= this.getNets().size(); i++) {
			if (((SAVRoot)this.getNets().get(i-1).getRoot()).getStructure().equals(NetRootStructure))
				return this.getNets().get(i-1);
		}
			
		return null;
	}
	
	/**
	 * Adds a new instance of RDNet to the nets list.  Before doing so, the root reference of the newly-created RDNet is changed for one
	 * belonging to the class SAVRoot (a subclass of RootNorm).  This way, the 'structure' attribute in the changed root can be accessed
	 * @see "Método addRDNetWith: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructure
	 */
	public void addRDNet(String aStructure) {
		RDNet anRDNet;
		SAVRoot aSAVRoot;
		
		anRDNet = new RDNet();
		aSAVRoot = new SAVRoot(aStructure);

		anRDNet.setRoot(aSAVRoot);
		this.getNets().add(anRDNet);
	}

	/**
	 * Returns true if aStructure is the descriptor of an RDNet's root, in the nets list. Returns false otherwise
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructure
	 */
	public boolean contains(String aStructure) {
		for( int i = 1; i <= this.getNets().size(); i++) {
			if (((SAVRoot)this.getNets().get(i-1).getRoot()).getStructure().equals(aStructure))
				return true;
		}
		
		return false;
	}
}
