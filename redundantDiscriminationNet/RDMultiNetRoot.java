/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Sukia Redundant Discriminant Net de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the main element of an RDMultiNet.  The main feature of an RDMultiNetRoot is that it contains
 * a collection of pointers to all the redundant discrimination nets.
 * @author Armando
 *
 */
public class RDMultiNetRoot {
	/**
	 *  List whose elements son redes de discrinación redundantes.
	 */
	private List<RDNet> nets;
	
	/**
	 * Initializes a newly created multi-net root.  The nets ordered collection is empty at first
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RDMultiNetRoot() {
		nets = new ArrayList<RDNet>();
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo nets del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<RDNet> getNets() {
		return nets;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param nets
	 */
	public void setNets(List<RDNet> nets) {
		this.nets = nets;
	}
	
	/**
	 * Recupera una red que maneja la estructura aStructureName
	 * @see "M&eacute;todo getNetWith del protocolo accessing en SUKIA SmallTalk"
	 * @return An RDNet whose root's descriptor has the same name as aStructureName; null otherwise.
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
	 * Adds a new instance of RDNet to the nets list.  Before doing so, the root reference of the
	 * newly-created RDNet is changed for one belonging to the class SAVRoot (a subclass of RootNorm).
	 * This way, the 'structure' attribute in the changed root can be accessed
	 * @see "M&eacute;todo addRDNetWith: del protocolo adding en SUKIA SmallTalk"
	 * @param aRootNorm
	 */
	public boolean addRDNet(RootNorm aRootNorm) {
		RDNet aRDNet;
		
		if (this.nets == null)
			this.nets = new ArrayList<RDNet>();
		
		if (!this.contains(aRootNorm.getStructure())) {
			aRDNet = new RDNet(aRootNorm);
			return this.getNets().add(aRDNet);
		}
		
		return false;
	}

	/**
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aStructureName
	 * @return true if aStructure is the descriptor of an RDNet's root, in the nets list. false otherwise
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
