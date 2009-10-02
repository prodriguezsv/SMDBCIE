/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.common;

/**
 * The first level MUST always be ROOT. All other level names MUST be added
 * according to their hierarchical ordering.  Thus, the second name in the list represents the MOST general
 * taxonomic rank, while the last name represents the most specific taxonomic rank.
 * PRECONDITION: There must be at least two taxonomic level names: ROOR + aTaxonomicLevelName.
 * @see "Método initialize del protocolo de clase class initialization en SUKIA SmallTalk"
 * @author Armando
 *
 */
public enum MeasuringUnit {
	/**
	 * Constantes de la enumeraci&oacute;n
	 */
	MM("mm"),
	CM("cm"),
	M("m"),
	INCH("inch"),
	COUNT("count");
	
	/**
	 * Variable para manejar el valor de la constante
	 */
	private final String unit;
	
	/**
	 * Contructor de la enumeraci&oacute;n
	 * @param similaryDegree
	 */
	MeasuringUnit(String unit) {
		this.unit = unit; 
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getMeasuringUnit() {
		return unit;
	}			
}
