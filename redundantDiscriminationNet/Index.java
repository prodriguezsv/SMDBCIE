/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;

import ontology.common.Descriptor;

/**
 * Structure that points to one or more Case's or Norm's, according to:
 * a) an attribute, and
 * b) a set of values that the attribute may have.
 * The identifier of an Index is its label, that is, the attribute name (e.g., shape, color).
 * IMPORTANT NOTES:
 * 1. An index may exist only in the context of a net.
 * 2. The only valid predecessor for an Index is either a Norm or net root.
 * @author Armando
 *
 */
public class Index extends Node {
	/**
	 * A Symbol that represents an attribute y la etiqueta de la arista de la red que representa el
	 * &iacute;ndice.
	 */
	private String label;

	/**
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk" 
	 */
	public Index(String label) {
		super.setPredecessors(new ArrayList<Node>());
		super.setSuccessors(new ArrayList<Node>());
		this.label = label;
	}

	/**
	 * @see "M&eacute;todo label del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getPredecessor() {
		if (!(super.getPredecessors().isEmpty()))
			return (Norm) super.getPredecessors().get(0);
		
		return null;
	}
	
	/** Asigna el predecesor Norm Index. Si ya tiene asignado un valor lo anula.
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public boolean setPredecessor(Norm aPredecessor) {
		if (aPredecessor == null) {
			super.getPredecessors().clear();
			return true;
		}
		
		super.getPredecessors().clear();
		
		return aPredecessor.addSuccessor(this);
	}
		
	/**
	 * @see "M&eacute;todo addSuccessor: del protocolo adding en SUKIA SmallTalk"
	 */
	public boolean addSuccessor(Node aSuccessor) {	
		if (!(aSuccessor instanceof Norm) && !(aSuccessor instanceof SheetCase)) return false;
		
		// If aSuccessor is an Norm, make sure that all Descriptors are unique
		if (aSuccessor instanceof Norm) {
			for (Node n: this.getSuccessors()) {
				if (!(n instanceof Norm))
					continue;
				else {
					 if (((Norm)n).getDescriptor().equals(((Norm) aSuccessor).getDescriptor()))
						 return false;
					 else continue;
				}
			}
		} else {
			// Si es un SheetCase asegurarse que el índice y aSuccessor sean compatibles
			if (!this.getLabel().equals(((SheetCase)aSuccessor).getDescriptor().getAttribute()))
				return false;
			
			for (Node n: this.getSuccessors()) {
				if (!(n instanceof SheetCase))
					continue;
				else {
					 if (((SheetCase)n).getDescriptor().equals(((SheetCase) aSuccessor).getDescriptor()))
						 return false;
					 else continue;
				}
			}
		}
		
		return super.addSuccessor(aSuccessor);
	}
	
	/**
	 * @see "M&eacute;todo getIndexValueWith:aValue: del protocolo searching en SUKIA SmallTalk"
	 * @return
	 */
	public Node getSuccessor(Descriptor aDescriptor) {
		boolean valor;
		
		if (super.getSuccessors() != null) {
			for (Node n: this.getSuccessors()) {
				if (n instanceof Norm)
					valor = ((Norm)n).getDescriptor().equals(aDescriptor);
				else valor = ((SheetCase)n).getDescriptor().equals(aDescriptor);
				
				if (valor) return n;
			}
		}
		
		return null;
	}
	
	/**
	 * Devuelve la norma sucesor que contiene el descriptor com&uacuten aDescriptor
	 * @return norma sucesor o nul si no hay una que coincida con el descriptor
	 */
	public Norm getSuccessorNorm(Descriptor aDescriptor) {
		if (super.getSuccessors() != null) {
			for (Node n: this.getSuccessors()) {
				if (n instanceof Norm)
					if (((Norm) n).getDescriptor().equals(aDescriptor))
						return (Norm) n;
			}
		}
		
		return null;
	}
}
