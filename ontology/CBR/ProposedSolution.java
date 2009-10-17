/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package ontology.CBR;


/**
 * @author Armando
 *
 */
public class ProposedSolution implements jade.content.Concept, Comparable<ProposedSolution> {
	private String certaintyDegree;
	private PossibleSolution solution;
	private boolean state;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public ProposedSolution() {
		this._internalInstanceName = "";
		this.setSolution(null);
		this.setState(true);
		this.setCertaintyDegree(CertaintyDegree.UNKNOWN.getDegree());
	}
	
	private static final long serialVersionUID = 4206237779038972396L;

	private String _internalInstanceName = null;

  	public ProposedSolution(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}

	/**
	 * The only possible values for this variable are:
	 * #unknown or #uncertain or #doubfult or #certain
	 * @param certaintyDegree
	 */
	public void setCertaintyDegree(String aCertaintyDegree) {
		this.certaintyDegree = aCertaintyDegree;
	}

	/**
	 * @see "Método degreeOfCertainty del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getCertaintyDegree() {
		return certaintyDegree;
	}

	/**
	 * @see "Método solution: del protocolo adding en SUKIA SmallTalk"
	 * @param solution
	 */
	public void setSolution(PossibleSolution solution) {
		this.solution = solution;
	}

	/**
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public PossibleSolution getSolution() {
		return solution;
	}

	/**
	 * The only possible values for this variable are:
	 * #+ (a positive PossibleSolution)
	 * #- (a negative PossibleSolution)"
	 * @param status
	 */
	public void setState(boolean status) {
		this.state = status;
	}

	/**
	 * @see "Método status del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Método de instancia agregado
	 */
	public int compareTo(ProposedSolution aProposedSolution) {
		return (aProposedSolution.getCertaintyDegree().compareTo(this.getCertaintyDegree()));
	}
}
