/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Description;
import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class Hypothesis {
	private Description description;
	private Description justification;
	private Description unmatchedDescription;
	private List<PossibleSolution> possibleSolutions;
	private double points;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	// Ojo el ordenamiento
	public Hypothesis() {
		setDescription(new Description());

		// Sort criteria: taxonomic level
		setPossibleSolutions(new ArrayList<PossibleSolution>());

		// Sort criteria: concatenated structure and attribute names
		setUnmatchedDescription(new Description());
		setJustification(new Description());
		setPoints(0);
	}

	/**
	 * @see "Método descriptiveElement: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescElt
	 */
	public boolean setDescription(Description aDescElt) {
		this.description = aDescElt;
		
		return true;
	}

	/**
	 * @see "Método descriptiveElement del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Método de instancia agregado
	 * @param justification
	 */
	public void setJustification(Description justification) {
		this.justification = justification;
	}

	/**
	 * @see "Método justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getJustification() {
		return justification;
	}
	
	/**
	 * @see "Método justification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustification
	 */
	public void addToJustification(Descriptor aJustification) {
		justification.addToConcreteDescription(aJustification);
	}

	/**
	 * Método de instancia agregado
	 * @param points
	 */
	public void setPoints(double points) {
		this.points = points;
	}
	
	/**
	 * @see "Método incrementPointsBy: del protocolo adding en SUKIA SmallTalk"
	 * @param increment
	 */
	public void incrementPointsBy(double increment) {
		this.points = this.points + increment;
	}
	
	/**
	 * @see "Método incrementPointsBy: del protocolo adding en SUKIA SmallTalk"
	 */
	public void incrementPoints() {
		this.points = this.points + 1;
	}
	
	/**
	 * @see "Método points del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * Método de instancia agregado
	 * @param possibleSolutions
	 */
	public void setPossibleSolutions(List<PossibleSolution> possibleSolutions) {
		this.possibleSolutions = possibleSolutions;
	}

	/**
	 * Adds aPossibleSolution under the following conditions:
	 * 1. If the list is empty, the possible solution may be added directly.
	 * 2. The list may contain ONLY taxa or positive instances of Case or SAVCase, or
	 * 3. The list may contain ONLY negative instances of SAVCase
	 * @see "Método possibleSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aPossibleSolution
	 * @return
	 */
	public boolean addPossibleSolution(PossibleSolution aPossibleSolution) {
		boolean firstEltStatus, possSolStatus;
	
		if (this.getPossibleSolutions().isEmpty()) {
			this.getPossibleSolutions().add(aPossibleSolution);
			aPossibleSolution.setHypothesis(this);
			return true;
		}

		firstEltStatus = this.getPossibleSolutions().get(0).getStatus();
		possSolStatus = aPossibleSolution.getStatus();

		if (firstEltStatus == possSolStatus){
			this.getPossibleSolutions().add(aPossibleSolution);
			aPossibleSolution.setHypothesis(this);
			return true;
		}	

		return false;
	}

	/**
	 * @see "Método possibleSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<PossibleSolution> getPossibleSolutions() {
		return possibleSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param unmatchedDescription
	 */
	public void setUnmatchedDescription(Description unmatchedDescription) {
		this.unmatchedDescription = unmatchedDescription;
	}

	/**
	 * @see "Método unmatchedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToUnmatchedDescription(Descriptor aDescriptor) {
		return this.getUnmatchedDescription().addToConcreteDescription(aDescriptor);
	}
	
	/**
	 * @see "Método unmatchedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getUnmatchedDescription() {
		return unmatchedDescription;
	}
	
	/**
	 * @see "Método copyToJustificationFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationDescription
	 * @return
	 */
	public boolean addAllToJustification(Description aJustificationDescription) {
		return this.getJustification().addAllToConcreteDescription(aJustificationDescription);
	}
	
	/**
	 * @see "Método copyToUnmatchedDescriptionFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param anUnmatchedDescription
	 * @return
	 */
	public boolean addAllToUnmatchedDescription(Description anUnmatchedDescription) {
		return this.getUnmatchedDescription().addAllToConcreteDescription(anUnmatchedDescription);
	}
}
