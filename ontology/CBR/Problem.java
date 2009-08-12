/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 */
package ontology.CBR;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;

/**
 * Representa la especificación de un problema particular a resolver
 * @author Armando
 *
 */
public class Problem {
	/**
	 * A list containing a set of Descriptor's (a description of the problem)
	 */
	private List<Descriptor<Object>> description;
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
		description = new ArrayList<Descriptor<Object>>();
		goalRank = null;
		leastDegreeOfCertainty = null;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param description
	 */
	public void setDescription(List<Descriptor<Object>> description) {
		this.description = description;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public List<Descriptor<Object>> getDescription() {
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
