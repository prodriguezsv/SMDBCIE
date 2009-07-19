/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package onthology.CBR;

import onthology.taxonomy.TaxonomicLevels;

/**
 * @author Armando
 *
 */
public class CaseSolution {
	String taxonLevel;
	String taxonName;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public CaseSolution() {
		taxonLevel = null;
		taxonName = null;
	}

	/**
	 * @see "Método taxonLevel del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonLevel() {
		return taxonLevel;
	}

	/**
	 * Check the argument's class. If it isn't ByteSymbol, there's no need to search in the element list
	 * of the class TaxonomicLevels
	 * @see "Método taxonLevel: del protocolo adding en SUKIA SmallTalk"
	 * @param taxonLevel
	 */
	public void setTaxonLevel(String taxonLevel) {
		// Search in the element list for the given name
		if (!(TaxonomicLevels.getLevels().contains(taxonLevel)))
			return;

		this.taxonLevel = taxonLevel;
	}

	/**
	 * @see "Método taxonName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonName() {
		return taxonName;
	}

	/**
	 * Check the argument's class. By default, it must be ByteSymbol. NOTE: the 'name:' method of the class
	 * Taxon contains useful information about the format of taxon names
	 * @see "Método taxonName: del protocolo adding en SUKIA SmallTalk"
	 * @param taxonName
	 */
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
}
