/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package similarityAssessment;

/**
 * Clase agregada
 * @author Armando
 *
 */
public class WeightedValue {
	private Object value;
	private double weight;
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}
}
