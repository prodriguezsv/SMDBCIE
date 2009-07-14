/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package similarityAssessment;

/**
 * Clase agregada
 * @author Armando
 *
 */
public class ValuedRange {
	private int lowerBound;
	private int upperBound;
	private String categoria;
	
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

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}
	
}
