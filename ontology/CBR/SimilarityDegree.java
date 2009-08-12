/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package ontology.CBR;


/**
 * Assigns the range names to be used to assess similarity between the values of attributes.
 * IMPORTANT NOTES:
 * 1. The first element should ALWAYS be called #diferente and the last one should always be called #igual.
 * 2. It Should ALWAYS have at least 3 elements (i.e., (DIFERENTE xxx1 ... xxxN  IGUAL)), where xxxK is
 * some arbitrary similarity measure).  Otherwise, most methods won't work.  The reason for having at least 3
 * range names is simple: if only two were defined (i.e., (DIFERENTE IGUAL)), none of this similarity
 * assessment stuff would be necessary. It would just suffice to perform an exact match between two values and,
 * if they don't match the implicit answer is DIFERENTE; likewise, if the values match, the implicit answer
 * is IGUAL.
 * 3. If a given value does not exist in a given value-weight list, nothing can be said about the
 * similarity degree of the value.  Thus, return a symbol which expresses this fact: VALORNOCOMPARABLE
 * @author Armando
 *
 */
public enum SimilarityDegree {
	/**
	 * Constantes de la enumeraci&oacute;n
	 */
	DIFERENTE("Diferente"),
	POCOSIMILAR("Poco Similar"),
	RELATIVAMENTESIMILAR("Relativamente Similar"),
	MEDIANAMENTESIMILAR("Medianamente Similar"),
	CONSIDERABLEMENTESIMILAR("Considerablemente Similar"),
	ALTAMENTESIMILAR("Altamente Similar"),
	IGUAL("igual"),
	VALORNOCOMPARABLE("Valor no comparable");
	
	/**
	 * Variable para manejar el valor de la constante
	 */
	private final String similarityDegree;
	
	/**
	 * Contructor de la enumeraci&oacute;n
	 * @param similaryDegree
	 */
	SimilarityDegree(String similaryDegree) {
		this.similarityDegree = similaryDegree; 
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getSimilarityDegree() {
		return similarityDegree;
	}	
}
