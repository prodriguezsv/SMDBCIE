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
	private String goalRank;
	private SimilarityDegree leastDegreeOfCertainty;

	/**
	 * 
	 */
	public Problem() {
		description = new ArrayList<Descriptor<Object>>();
		goalRank = null;
		leastDegreeOfCertainty = null;
	}

	public void setDescription(List<Descriptor<Object>> description) {
		this.description = description;
	}

	public List<Descriptor<Object>> getDescription() {
		return description;
	}

	public void setGoalRank(String goalRank) {
		this.goalRank = goalRank;
	}

	public String getGoalRank() {
		return goalRank;
	}

	public void setLeastDegreeOfCertainty(SimilarityDegree leastDegreeOfCertainty) {
		this.leastDegreeOfCertainty = leastDegreeOfCertainty;
	}

	public SimilarityDegree getLeastDegreeOfCertainty() {
		return leastDegreeOfCertainty;
	}
}
