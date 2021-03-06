/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 */
package ontology.CBR;

import ontology.common.Description;
import ontology.taxonomy.TaxonomicRank;

/**
 * Representa la especificación de un problema particular a resolver
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Problem implements jade.content.Concept {
	/**
	 * A list containing a set of Descriptor's (a description of the problem)
	 */
	private Description description;
	/**
	 * Especifica la meta de identificaci&oacute;n
	 */
	private String goalRank;
	/**
	 * Especifica la espectativa de desempe&ntilde;o m&iacute;nima
	 */
	private String leastSimilarityDegree;

	/**
	 * Constructor por defecto
	 */
	public Problem() {
		this._internalInstanceName = "";
		description = new Description();
		goalRank = null;
		leastSimilarityDegree = null;
	}

  	private String _internalInstanceName = null;

  	public Problem(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}
	
	/**
	 * Constructor por defecto
	 */
	public Problem(Description description) {
		this.description = description;
		goalRank = null;
		leastSimilarityDegree = null;
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
		boolean iscontained = false;
		
		for (TaxonomicRank t:TaxonomicRank.values()) {
			if (t.getRank().equals(goalRank)) {
				iscontained = true;
				break;
			}
		}
		
		if (iscontained) this.goalRank = goalRank;
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
	public void setLeastSimilarityDegree(String leastDegreeOfCertainty) {
		boolean iscontained = false;
		
		for (SimilarityDegree sd:SimilarityDegree.values()) {
			if (sd.getSimilarityDegree().equals(leastDegreeOfCertainty)) {
				iscontained = true;
				break;
			}
		}
		
		if (iscontained) this.leastSimilarityDegree = leastDegreeOfCertainty;;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getLeastSimilarityDegree() {
		return leastSimilarityDegree;
	}
	
	/**
	 * Determina la igualdad entre dos descriptores
	 * @param aDescriptor
	 * @return
	 */
	public boolean equals(Object aProblem) {
		if (aProblem == null) return false;
		if (!(aProblem instanceof Problem)) return false;
		
		if (this.getDescription().equals(((Problem)aProblem).getDescription())
				&& this.getGoalRank().equals(((Problem)aProblem).getGoalRank())
				&& this.getLeastSimilarityDegree().equals(((Problem)aProblem).getLeastSimilarityDegree()))
			return true;
		else return false;
	}
}
