/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.taxonomy;

/**
 * The first level MUST always be ROOT. All other level names MUST be added
 * according to their hierarchical ordering.  Thus, the second name in the list represents the MOST general
 * taxonomic rank, while the last name represents the most specific taxonomic rank.
 * PRECONDITION: There must be at least two taxonomic level names: ROOR + aTaxonomicLevelName.
 * @see "Método initialize del protocolo de clase class initialization en SUKIA SmallTalk"
 * @author Armando
 *
 */
public enum TaxonomicRank {
	/**
	 * Constantes de la enumeraci&oacute;n
	 */
	ROOT("Root"),
	FAMILY("Family"),
	GENUS("Genus"),
	SPECIES("Species");
	
	/**
	 * Variable para manejar el valor de la constante
	 */
	private final String rank;
	
	/**
	 * Contructor de la enumeraci&oacute;n
	 * @param similaryDegree
	 */
	TaxonomicRank(String rank) {
		this.rank = rank; 
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getRank() {
		return rank;
	}
		
	/**
	 * Returns the most general taxonomic rank name, which always must be the second name in the enumeration
	 * (i.e., the taxonomic hierarchy)
	 * @see "Método mostGeneralLevel del protocolo de clase accessing en SUKIA SmallTalk"
	 * @return
	 */
	public static TaxonomicRank getMostGeneralRank() {
		return TaxonomicRank.FAMILY;
	}
	
	/**
	 * Returns the most specific taxonomic rank name, which always must be the last name in the enumeration
	 * (i.e., the taxonomic hierarchy)
	 * @see "Método mostSpecificLevel del protocolo de clase accessing en SUKIA SmallTalk"
	 * @return
	 */
	public static TaxonomicRank getMostSpecificLevel() {
		return TaxonomicRank.SPECIES;
	}
			
	/**
	 * Three taxonomic levels for this implementation: 1 - family; 2 - genus; 3 - species
	 * @see "Método nomenclaturalNumber del protocolo de clase class messages en SUKIA SmallTalk"
	 */
	public static int getNomenclaturalRanksNumber() {
		return (TaxonomicRank.values().length - 1);
	}
	
	/**
	 * Four taxonomic levels for this implementation: 0 - root; 1 - family; 2 - genus; 3 - species
	 * @see "Método totalNumber del protocolo de clase class messages en SUKIA SmallTalk"
	 */
	public static int getTotalRanksNumber() {
		return TaxonomicRank.values().length;
	}	
	
	/**
	 * Obtiene el &iacute;ndice que ocupa rank en la enumeraci&oacute;n
	 * @param taxonomicRank
	 * @return el &iacute;ndice que ocupa rank en la enumeraci&oacute;n o -1 si rank no se encontra
	 */
	public static int getIndex(TaxonomicRank taxonomicRank) {
		int i = 0;
                if (taxonomicRank == null){return -1;}
		for (TaxonomicRank tr: TaxonomicRank.values()) {
			if (tr.getRank().equals(taxonomicRank.getRank()))
				break;
			i++;
		}
		
		return i;
	}
}
