/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package ontology.CBR;

import java.io.Serializable;

import ontology.common.Description;
import ontology.taxonomy.TaxonomicRank;

/**
 * Representa la soluci&oacute;n de un problema particular y c&oacute;mo fue resuelto
 * @author Armando
 *
 */
public class Solution implements jade.content.Concept, Serializable {
	/**
	 *  A list containing a set of Descriptor's (the solution path of the case, i.e., the result of the
	 *  traversal across the net and other reference structures).
	 */
	private Description justification;
	/**
	 * El rango del tax&oacute;n de la soluci&oacute;n
	 */
	private TaxonomicRank taxonLevel;
	/**
	 * El tax&oacute;n de la soluci&oacute;n
	 */
	private String taxonName;

	/**
	 * Constructor por defecto
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Solution() {
		this._internalInstanceName = "";
		justification = new Description();
		taxonLevel = null;
		taxonName = null;
	}
	
	private static final long serialVersionUID = 4206237779038972396L;

	private String _internalInstanceName = null;

	public Solution(String instance_name) {
		this._internalInstanceName = instance_name;
	}

	public String toString() {
		return _internalInstanceName;
	}
	
	/**
	 * Constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Solution(TaxonomicRank aTaxonLevel, String aTaxonName) {
		justification = new Description();
		taxonLevel = aTaxonLevel;
		taxonName = aTaxonName;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @param justification
	 */
	public void setJustification(Description justification) {
		this.justification = justification;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getJustification() {
		return justification;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo taxonLevel del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public TaxonomicRank getTaxonLevel() {
		return taxonLevel;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @see "M&eacute;todo taxonLevel: del protocolo adding en SUKIA SmallTalk"
	 * @param taxonLevel
	 */
	public void setTaxonLevel(TaxonomicRank taxonLevel) {
		this.taxonLevel = taxonLevel;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo taxonName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonName() {
		return taxonName;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * NOTE: the method setName of the class Taxon contains useful information about the format of
	 * taxon names
	 * @see "M&eacute;todo taxonName: del protocolo adding en SUKIA SmallTalk"
	 * @param taxonName
	 */
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
}
