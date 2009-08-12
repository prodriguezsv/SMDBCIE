/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package system.similarityAssessment;

import ontology.CBR.SimilarityDegree;

/**
 * Clase agregada
 * @author Armando
 *
 */
public class ValuedRange {
	private int lowerBound;
	private int upperBound;
	private SimilarityDegree categoria;
	
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	public int getLowerBound() {
		return lowerBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setCategoria(SimilarityDegree categoria) {
		this.categoria = categoria;
	}

	public SimilarityDegree getCategoria() {
		return categoria;
	}
	
}
