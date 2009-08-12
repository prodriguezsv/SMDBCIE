/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package ontology.CBR;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;
import ontology.taxonomy.TaxonomicLevels;

/**
 * Representa la soluci&oacute;n de un problema particular y c&oacute;mo fue resuelto
 * @author Armando
 *
 */
public class Solution {
	/**
	 *  A list containing a set of Descriptor's (the solution path of the case, i.e., the result of the
	 *  traversal across the net and other reference structures).
	 */
	private List<Descriptor<Object>> justification;
	/**
	 * El rango del tax&oacute;n de la soluci&oacute;n
	 */
	private String taxonLevel;
	/**
	 * El tax&oacute;n de la soluci&oacute;n
	 */
	private String taxonName;

	/**
	 * Constructor por defecto
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Solution() {
		justification = new ArrayList<Descriptor<Object>>();
		taxonLevel = null;
		taxonName = null;
	}
	
	/**
	 * Constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Solution(String aTaxonLevel, String aTaxonName) {
		justification = new ArrayList<Descriptor<Object>>();
		taxonLevel = aTaxonLevel;
		taxonName = aTaxonName;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @param justification
	 */
	public void setJustification(List<Descriptor<Object>> justification) {
		this.justification = justification;
	}

	/**
	 * @see "M&eacute;todo justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getJustification() {
		return justification;
	}

	/**
	 * @see "M&eacute;todo taxonLevel del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonLevel() {
		return taxonLevel;
	}

	/**
	 * There's need to search in the element list of the class TaxonomicLevels before setting
	 * @see "M&eacute;todo taxonLevel: del protocolo adding en SUKIA SmallTalk"
	 * @param taxonLevel
	 */
	public void setTaxonLevel(String taxonLevel) {
		// Search in the element list for the given name
		if (!(TaxonomicLevels.getLevels().contains(taxonLevel)))
			return;

		this.taxonLevel = taxonLevel;
	}

	/**
	 * @see "M&eacute;todo taxonName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonName() {
		return taxonName;
	}

	/**
	 * NOTE: the method setName of the class Taxon contains useful information about the format of
	 * taxon names
	 * @see "M&eacute;todo taxonName: del protocolo adding en SUKIA SmallTalk"
	 * @param taxonName
	 */
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToJustification(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (this.getJustification().contains(aDescriptor))
			return false;
		this.getJustification().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromJustification(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		return this.getJustification().remove(aDescriptor);
	}
}
