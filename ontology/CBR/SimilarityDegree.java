/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package ontology.CBR;


/**
 * Assigns the range names to be used to assess similarity between the values of attributes.
 * IMPORTANT NOTES:
 * 1. The first element should ALWAYS be called #diferente and the last one should always be called #igual.
 * These are special symbols around which functionality in other components will be built.
 * 2. self should ALWAYS have at least 3 elements (i.e., (#diferente #xxx1 ... #xxxN  #igual)), where #xxxK is some
 * arbitrary similarity measure).  Otherwise, most methods won't work.  The reason for having at least 3 range names
 * is simple: if only two were defined (i.e., (#diferente #igual)), none of this similarity assessment stuff would be necessary.
 * It would just suffice to perform an exact match between two values and, if they don't match the implicit answer is
 * #diferente; likewise, if the values match, the implicit answer is #igual.
 * 3. Take a look at method self nonComparable for more range details.
 * If a given value does not exist in a given value-weight list, nothing can be said about the
 * similarity degree of the value.  Thus, return a symbol which expresses this fact.  This symbol
 * can be regarded as a special type of SimRange with range (-1.0 -1.0)
 * @author Armando
 *
 */
public enum SimilarityDegree {
	DIFERENTE("Diferente"),
	POCOSIMILAR("Poco Similar"),
	RELATIVAMENTESIMILAR("Relativamente Similar"),
	MEDIANAMENTESIMILAR("Medianamente Similar"),
	CONSIDERABLEMENTESIMILAR("Considerablemente Similar"),
	ALTAMENTESIMILAR("Altamente Similar"),
	IGUAL("igual"),
	VALORNOCOMPARABLE("Valor no comparable");
	
	
	private final String similarityDegree;
	
	SimilarityDegree(String similaryDegree) {
		this.similarityDegree = similaryDegree; 
	}
	
	public String getSimilarityDegree() {
		return similarityDegree;
	}	
}
