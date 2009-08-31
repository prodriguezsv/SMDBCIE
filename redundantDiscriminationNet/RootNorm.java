/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import ontology.common.Descriptor;



/**
 * Specialization of a Norm, and its mission is to act as a net's entry point.  The two most important
 * features of a RootNorm are: 1. it does not have a predecessor, and 2. its Descriptor is of the form
 * (structure nil nil ).
 * @author Armando
 *
 */
public class RootNorm extends Norm {
	
	/**
	 * Constructor de la clase
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public RootNorm(Descriptor aDescriptor) {
		super(aDescriptor);
	}
	
	/**
	 * @see "M&eacute;todo descriptor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor getDescriptor() {
		return null;
	}
	
	/**
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public void setPredecessor(Node aSuccessor) {
		
	}
	
	/**
	 * @see "M&eacute;todo structure del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructure() {
		return super.getDescriptor().getStructure();
	}
}
