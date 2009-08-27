/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * Structure that groups (generalizes) a set of cases that share a common Descriptor.  Such cases may be
 * linked directly to the norm (when the cases' descriptions terminate at the Norm), or accessed via indices.
 * A Norm may NOT directly point to another Norm.
 * IMPORTANT NOTES:
 * 1. There shall be no Index or Case duplication in the Norm's list of successors.
 * 3. A Norm may exist only in the context of a net.
 * 4. The only valid predecessor for an Norm is either an Index.
 * @author Armando
 *
 */
public class Norm extends Node {
	/**
	 * Depicting the grouping (generalizing) concept
	 */
	private Descriptor<Object> descriptor;
	/**
	 * Number of cases grouped by the Norm, whether linked directly or located levels below.
	 */
	private int numCases;
	
	/**
	 * Constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Norm(Descriptor<Object> descriptor) {
		super.setPredecessors(new ArrayList<Node>());
		super.setSuccessors(new ArrayList<Node>());
		this.descriptor = descriptor;
		numCases = 0;
	}

	/**
	 * Constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Norm(Descriptor<Object> descriptor, Index predecessor) {
		super.setSuccessors(new ArrayList<Node>());
		this.setPredecessor(predecessor);
		this.descriptor = descriptor;
		numCases = 0;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo descriptor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Descriptor<Object> getDescriptor() {
		return descriptor;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo numCases del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getNumCases() {
		return numCases;
	}

	/**
	 * Incrementa el valor de numCases
	 * @see "M&eacute;todo incrementNumCasesBy: del protocolo adding en SUKIA SmallTalk"
	 * @param anInteger Valor de incremento del n&uacute;mero de casos
	 */
	public void incrementNumCasesBy(int anInteger) {
		numCases = (numCases + anInteger);
	}

	
	/**
	 * Recupera el &iacute;ndice predecesor de la norma
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return El &iacute;ndice predecesor si existe o null de lo contrario
	 */
	public Index getPredecessor() {
		if (!(super.getPredecessors().isEmpty()))
			return (Index) super.getPredecessors().get(0);
		
		return null;
	}
	
	/**
	 * Actualiza el índice predecesor de la norma
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public boolean setPredecessor(Index aPredecessor) {
		if (aPredecessor == null) {
			super.getPredecessors().clear();
			return true;
		}
		
		if (!this.getDescriptor().getAttribute().equals(aPredecessor.getLabel()))
			return false;
		
		super.getPredecessors().clear();
		
		return super.addPredecessor(aPredecessor);
	}
	
	/**
	 * Agrega un nodo sucesor a la norma
	 * @see "M&eacute;todo addSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @return true si se agrega aSuccessor o false de lo contrario
	 */
	public boolean addSuccessor(Node aSuccessor) {	
		if (!(aSuccessor instanceof Index) && !(aSuccessor instanceof SheetCase)) return false;
		
		// If aSuccessor is an Index, make sure that all Index-labels are unique
		if (aSuccessor instanceof Index) {
			for (Node n: this.getSuccessors()) {
				if (!(n instanceof Index))
					continue;
				else {
					 if (((Index)n).getLabel().equals(((Index) aSuccessor).getLabel()))
						 return false;
					 else continue;
				}
			}
		} else {
			// Si es un SheetCase asegurarse que los descriptores de la norma y aSuccessor sean iguales
			if (!this.getDescriptor().equals(((SheetCase)aSuccessor).getDescriptor()))
				return false;
		}
		
		return super.addSuccessor(aSuccessor);
	}
	
	/**
	 * Recupera la norma predecesor m&aacute;s cercana a la norma actual
	 * @see "M&eacute;todo predecessor del protocolo navigating en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getNearestPredecessorNorm() {
		if (this.getPredecessor() == null) return null;
		
		return this.getPredecessor().getPredecessor();
	}

	/**
	 * This method returns a collection of cases that are immediate successors of this Norm.  That is,
	 * all retrieved cases are generalized by the Norm's Descriptor
	 * @see "M&eacute;todo successorCases del protocolo navigating en SUKIA SmallTalk"
	 * @return La lista de casos sucesores de la norma o una lista vac&iacute;a si no tiene casos sucesores
	 */
	public List<SheetCase> successorCases() {
		List<SheetCase> s;
		
		s = new ArrayList<SheetCase>();

		for (Node n: super.getSuccessors()) {
			if ((n instanceof SheetCase))
				s.add((SheetCase)n);
		}
		
		return s;
	}

	/**
	 * This method returns a collection of indices that are immediate successors of this Norm.
	 * @see "M&eacute;todo successorCases del protocolo navigating en SUKIA SmallTalk"
	 * @return La lista de &iacute;ndices sucesores de la norma o una lista vac&iacute;a si no tiene
	 * &iacute;ndices sucesores
	 */
	public List<Index> successorIndexes() {
		List<Index> s;
		
		s = new ArrayList<Index>();

		for (Node n: super.getSuccessors()) {
			if (n instanceof Index)
				s.add((Index)n);
		}
		
		return s;
	}
	
	/**
	 * Given a Descriptor, this method searches for the nearest predecessor norm that matches aDescriptor
	 * @see "M&eacute;todo successorWith del protocolo navigating en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return The successor Norm, or null, otherwise
	 */
	public Norm getNearestSuccessorNorm(Descriptor<Object> aDescriptor) {
		Index index;

		index = this.getSuccessorIndex(aDescriptor.getAttribute());
		if (!(index == null))
			return index.getSuccessorNorm(aDescriptor);

		return null;
	}
	
	/**
	 * Obtiene el &iacutendice sucesor de la norma con etiqueta aLabel
	 * @see "M&eacute;todo getIndexWith: del protocolo searching en SUKIA SmallTalk"
	 * @param aLabel la etiqueta del &iacutendice sucesor a buscar 
	 * @return el &iacutendice sucesor de la norma con etiqueta aLabel o null si no existe
	 */
	public Index getSuccessorIndex(String aLabel) {
		for (Node n: this.getSuccessors()) {
			if (n instanceof Index)
				if (aLabel.equals(((Index)n).getLabel()))
					return ((Index)n);
		}
		
		return null;
	}

	/**
	 * Obtiene el &iacutendice sucesor de la norma con etiqueta aLabel y un valor se&ntilde;alado aValue
	 * @see "M&eacute;todo getIndexWith:and: del protocolo searching en SUKIA SmallTalk"
	 * @param anAttribute La etiqueta del &iacutendice sucesor a buscar
	 * @param aValue El valor se&ntilde;alado a buscar
	 * @return
	 */
	public Index getSuccessorIndex(Descriptor<Object> aDescriptor) {
		for (Node n: this.getSuccessors()) {
			if (n instanceof Index)
				if (aDescriptor.getAttribute().equals(((Index)n).getLabel()) 
						&& !(((Index)n).getSuccessor(aDescriptor) == null)) 
						return ((Index)n);
		}
		
		return null;
	}
}
