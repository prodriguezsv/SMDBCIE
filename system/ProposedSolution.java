/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;



/**
 * @author Armando
 *
 */
public class ProposedSolution implements Comparable<ProposedSolution> {
	private CertaintyDegree degreeOfCertainty;
	private PossibleSolution solution;
	private boolean status;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public ProposedSolution() {
		this.setSolution(null);
		this.setStatus(true);
		this.setDegreeOfCertainty(CertaintyDegree.UNKNOWN);
	}

	/**
	 * The only possible values for this variable are:
	 * #unknown or #uncertain or #doubfult or #certain
	 * @param degreeOfCertainty
	 */
	public void setDegreeOfCertainty(CertaintyDegree aDegreeOfCertainty) {
		this.degreeOfCertainty = aDegreeOfCertainty;
	}

	/**
	 * @see "Método degreeOfCertainty del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public CertaintyDegree getDegreeOfCertainty() {
		return degreeOfCertainty;
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
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @see "Método status del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * Método de instancia agregado
	 */
	public int compareTo(ProposedSolution aProposedSolution) {
		return (aProposedSolution.getDegreeOfCertainty().compareTo(this.getDegreeOfCertainty()));
	}
}
