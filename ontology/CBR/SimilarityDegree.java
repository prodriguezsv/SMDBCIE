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
	DIFERENTE("Diferente", 0, 0),
	POCOSIMILAR("Poco Similar", 0, 0),
	RELATIVAMENTESIMILAR("Relativamente Similar", 0, 0),
	MEDIANAMENTESIMILAR("Medianamente Similar", 0, 0),
	CONSIDERABLEMENTESIMILAR("Considerablemente Similar", 0, 0),
	ALTAMENTESIMILAR("Altamente Similar", 0, 0),
	IGUAL("igual", 0, 0),
	VALORNOCOMPARABLE("Valor no comparable", 0, 0);
	
	/**
	 * Variable para manejar el valor de la constante
	 */
	private final String similarityDegree;
	private double lowerBound;
	private double upperBound;
	
	/**
	 * Contructor de la enumeraci&oacute;n
	 * @param similaryDegree
	 */
	SimilarityDegree(String similaryDegree, double lowerBound, double upperBound) {
		this.similarityDegree = similaryDegree;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getSimilarityDegree() {
		return similarityDegree;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @return
	 */
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public double getLowerBound() {
		return lowerBound;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @return
	 */
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public double getUpperBound() {
		return upperBound;
	}
}
