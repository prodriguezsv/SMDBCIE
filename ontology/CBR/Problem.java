/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 */
package ontology.CBR;

import ontology.common.Description;

/**
 * Representa la especificación de un problema particular a resolver
 * @author Armando
 *
 */
public class Problem {
	/**
	 * A list containing a set of Descriptor's (a description of the problem)
	 */
	private Description description;
	/**
	 * Especifica la meta de identificaci&oacute;n
	 */
	private String goalRank;
	/**
	 * Especifica la espectativa de desempe&ntilde;o m&iacutenima
	 */
	private SimilarityDegree leastDegreeOfCertainty;

	/**
	 * Constructor por defecto
	 */
	public Problem() {
		description = new Description();
		goalRank = null;
		leastDegreeOfCertainty = null;
	}
	
	/**
	 * Constructor por defecto
	 */
	public Problem(Description description) {
		this.description = description;
		goalRank = null;
		leastDegreeOfCertainty = null;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param description
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param goalRank
	 */
	public void setGoalRank(String goalRank) {
		this.goalRank = goalRank;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getGoalRank() {
		return goalRank;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param leastDegreeOfCertainty
	 */
	public void setLeastDegreeOfCertainty(SimilarityDegree leastDegreeOfCertainty) {
		this.leastDegreeOfCertainty = leastDegreeOfCertainty;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public SimilarityDegree getLeastDegreeOfCertainty() {
		return leastDegreeOfCertainty;
	}
}
