/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package reasoner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import redundantDiscriminantNet.SAVDescriptor;
import domainTheory.GroupingHeuristic;
import domainTheory.Structure;
import main.Description;

/**
 * @author Armando
 *
 */
public class Hypothesis {
	private Object descriptiveElement;
	private Description<SAVDescriptor> justification;
	private double points;
	private List<PossibleSolution> possibleSolutions;
	private Description<SAVDescriptor> unmatchedDescription;


	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	// Ojo el ordenamiento
	public Hypothesis() {
		setDescriptiveElement(null);

		// Sort criteria: taxonomic level
		setPossibleSolutions(new ArrayList<PossibleSolution>());

		// Sort criteria: concatenated structure and attribute names
		setUnmatchedDescription(new Description<SAVDescriptor>());
		setJustification(new Description<SAVDescriptor>());
		setPoints(0);
	}

	/**
	 * @see "Método descriptiveElement: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescElt
	 */
	public boolean setDescriptiveElement(Object aDescElt) {
		if (!((aDescElt instanceof Structure) || (aDescElt instanceof GroupingHeuristic)))
			return false;

		this.descriptiveElement = aDescElt;
		
		return true;
	}

	/**
	 * @see "Método descriptiveElement del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getDescriptiveElement() {
		return descriptiveElement;
	}

	/**
	 * Método de instancia agregado
	 * @param justification
	 */
	public void setJustification(Description<SAVDescriptor> justification) {
		this.justification = justification;
	}

	/**
	 * @see "Método justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<SAVDescriptor> getJustification() {
		return justification;
	}
	
	/**
	 * @see "Método justification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustification
	 */
	public void addJustification(SAVDescriptor aJustification) {
		justification.add(aJustification);
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
	public boolean addPossibleSolutions(PossibleSolution aPossibleSolution) {
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
	public void setUnmatchedDescription(Description<SAVDescriptor> unmatchedDescription) {
		this.unmatchedDescription = unmatchedDescription;
	}

	/**
	 * @see "Método unmatchedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aSAVDescriptor
	 * @return
	 */
	public boolean addUnmatchedDescription(SAVDescriptor aSAVDescriptor) {
		if (containsFull(aSAVDescriptor, this.getUnmatchedDescription()))
			return false;

		this.getUnmatchedDescription().add(aSAVDescriptor);
		Collections.sort(this.getUnmatchedDescription());
		
		return true;

	}
	
	/**
	 * @see "Método unmatchedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<SAVDescriptor> getUnmatchedDescription() {
		return unmatchedDescription;
	}
	
	/**
	 * @see "Método copyToJustificationFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationDescription
	 * @return
	 */
	public boolean copyToJustificationFrom(Description<SAVDescriptor> aJustificationDescription) {
		if (aJustificationDescription == null)
			return false;

		for( int i = 1; i <= aJustificationDescription.size(); i++) {
			this.addJustification(aJustificationDescription.get(i-1));
		}
		
		return true;
	}
	
	/**
	 * @see "Método copyToUnmatchedDescriptionFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param anUnmatchedDescription
	 * @return
	 */
	public boolean copyToUnmatchedDescriptionFrom(Description<SAVDescriptor> anUnmatchedDescription) {
		if (anUnmatchedDescription == null)
			return false;

		for( int i = 1; i <= anUnmatchedDescription.size(); i++) {
			this.addUnmatchedDescription(anUnmatchedDescription.get(i-1));
		}
		
		return true;
	}
	
	/**
	 * @see "Método problemDescriptionFor: del protocolo description generation en SUKIA SmallTalk"
	 * @param aTaxonomicGroupName
	 * @return
	 */
	public  List<SAVDescriptor> problemDescriptionFor(String aTaxonomicGroupName) {
		if (this.getDescriptiveElement() == null)
			return null;
		
		if (this.getDescriptiveElement() instanceof Structure)
			return ((Structure)this.getDescriptiveElement()).createSAVDescription(aTaxonomicGroupName);
		else
			return ((GroupingHeuristic)this.getDescriptiveElement()).createSAVDescription(aTaxonomicGroupName);
	}

	/**
	 * Determines if the argument aSAVDescriptor is a member of the argument aDescription.
	 * Returns:	-1 (error value) : if aDescription is NOT a description list belonging to self.
	 * nil : if aSAVDescriptor was NOT found in aDescription.
	 * not nil: the instance in aDescription that matched aSAVDescriptor's structure and attribute names.
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aSAVDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean contains(SAVDescriptor aSAVDescriptor, Description<SAVDescriptor> aDescription) {
		// Make sure that aDescription is indeed one of my description lists. If not, return the -1 error value
		 if (!(aDescription == this.getUnmatchedDescription()))
			 return false;

		for( int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getStructure() == aSAVDescriptor.getStructure() &&
					aDescription.get(i-1).getAttribute() == aSAVDescriptor.getAttribute())
				return true;

			// Stop searching if the next descriptor's structure name is (alphabetically) greater than the argument's structure name
			if (i < aDescription.size())
				if (aSAVDescriptor.getStructure().compareTo(aDescription.get(i).getStructure()) < 0)
					return false;
		}

		return false;
	}
	
	/**
	 * Determines if a full aSAVDescriptor is already a member of aDescriptionList. The argument aSAVDescriptor is a member of
	 * aDescriptionList when its structure, attribute and value match with the structure and attribute names of a list element.
	 * Returns: 	-1 (error state): The argument aDescriptionList IS NOT a valid list for self.
	 * nil: aSAVDescriptor IS NOT a member of aDescriptionList.
	 * not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor"
	 * @see "Método includesFull:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aSAVDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean containsFull(SAVDescriptor aSAVDescriptor, Description<SAVDescriptor> aDescription) {
		// Make sure that aDescription is indeed one of my description lists. If not, return the -1 error value
		 if (!(aDescription == this.getUnmatchedDescription()))
			 return false;

		for( int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getStructure().equals(aSAVDescriptor.getStructure()) &&
					aDescription.get(i-1).getAttribute().equals(aSAVDescriptor.getAttribute()) &&
					aDescription.get(i-1).getValue().equals(aSAVDescriptor.getValue()))
				return true;

			// Stop searching if the next descriptor's structure name is (alphabetically) greater than the argument's structure name
			if (i < aDescription.size())
				if (aSAVDescriptor.getStructure().compareTo(aDescription.get(i).getStructure()) < 0)
					return false;
		}

		return false;
	}	
}
