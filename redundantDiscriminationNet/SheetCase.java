/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import jade.util.leap.Iterator;

import java.util.ArrayList;

import ontology.CBR.Case;
import ontology.common.Descriptor;


/**
 * Estructura que embebe un caso que representa una hoja en la red. Está estructura tiene un descriptor
 * y sus predecesores pueden ser ya sea índices si el descriptor no agrupa varios casos o una norma si así
 * lo hace
 * @author Armando
 *
 */
public class SheetCase extends Node {
	/**
	 *  depicting the grouping (generalizing) concept
	 */
	private Descriptor descriptor;
	/**
	 * El caso que el nodo representa
	 */
	private Case aCase;

	/**
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SheetCase(Descriptor descriptor) {
		super.setPredecessors(new ArrayList<Node>());
		this.descriptor = descriptor;
		aCase = null;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo descriptor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor getDescriptor() {
		return descriptor;
	}
	
	/** Asigna el nodo predecesor. Un nodo v&aacute;lido es ya sea Index o Norm
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public boolean addPredecessor(Node aPredecessor) {
		if (aPredecessor == null)
			return false;
		
		return aPredecessor.addSuccessor(this);
	}
	
	/**
	 * @see "M&eacute;todo addSuccessor: del protocolo adding en SUKIA SmallTalk"
	 */
	public boolean addSuccessor(Node aSuccessor) {
		return false;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public Case getCase() {
		return aCase;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @param aCase
	 */
	public boolean setCase(Case aCase) {
		Iterator i = aCase.getProblem().getDescription().getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			if (d.equals(this.descriptor)) {
				this.aCase = aCase;
				return true;
			}
		}
		
		return false;
	}
}
