/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ontology.common.Descriptor;
import ontology.common.GroupingHeuristic;
import ontology.common.Structure;


/**
 * @author Armando
 *
 */
public class Hypothesis {
	private List<Descriptor<Object>> description;
	private List<Descriptor<Object>> justification;
	private List<Descriptor<Object>> unmatchedDescription;
	private List<PossibleSolution> possibleSolutions;
	private double points;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	// Ojo el ordenamiento
	public Hypothesis() {
		setDescription(new ArrayList<Descriptor<Object>>());

		// Sort criteria: taxonomic level
		setPossibleSolutions(new ArrayList<PossibleSolution>());

		// Sort criteria: concatenated structure and attribute names
		setUnmatchedDescription(new ArrayList<Descriptor<Object>>());
		setJustification(new ArrayList<Descriptor<Object>>());
		setPoints(0);
	}

	/**
	 * @see "Método descriptiveElement: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescElt
	 */
	public boolean setDescription(List<Descriptor<Object>> aDescElt) {
		this.description = aDescElt;
		
		return true;
	}

	/**
	 * @see "Método descriptiveElement del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getDescription() {
		return description;
	}

	/**
	 * Método de instancia agregado
	 * @param justification
	 */
	public void setJustification(List<Descriptor<Object>> justification) {
		this.justification = justification;
	}

	/**
	 * @see "Método justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getJustification() {
		return justification;
	}
	
	/**
	 * @see "Método justification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustification
	 */
	public void addJustification(Descriptor<Object> aJustification) {
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
	public void setUnmatchedDescription(List<Descriptor<Object>> unmatchedDescription) {
		this.unmatchedDescription = unmatchedDescription;
	}

	/**
	 * @see "Método unmatchedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addUnmatchedDescription(Descriptor<Object> aDescriptor) {
		if (this.getUnmatchedDescription().contains(aDescriptor))
			return false;

		this.getUnmatchedDescription().add(aDescriptor);
		Collections.sort(this.getUnmatchedDescription());
		
		return true;

	}
	
	/**
	 * @see "Método unmatchedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getUnmatchedDescription() {
		return unmatchedDescription;
	}
	
	/**
	 * @see "Método copyToJustificationFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationDescription
	 * @return
	 */
	public boolean copyToJustificationFrom(List<Descriptor<Object>> aJustificationDescription) {
		if (aJustificationDescription == null) return false;
                for (Descriptor d:aJustificationDescription){
                    this.addJustification(d);
                }
		return true;
	}
	
	/**
	 * @see "Método copyToUnmatchedDescriptionFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param anUnmatchedDescription
	 * @return
	 */
	public boolean copyToUnmatchedDescriptionFrom(List<Descriptor<Object>> anUnmatchedDescription) {
		if (anUnmatchedDescription == null)return false;
                for (Descriptor d:anUnmatchedDescription){
                    this.addUnmatchedDescription(d);
                }
		return true;
	}
	
//	/**
//	 * @see "Método problemDescriptionFor: del protocolo description generation en SUKIA SmallTalk"
//	 * @param aTaxonomicGroupName
//	 * @return
//	 */
//	public  List<Descriptor<Object>> problemDescriptionFor(String aTaxonomicGroupName) {
//		if (this.getDescription() == null)
//			return null;
//
//		if (this.getDescription() instanceof Structure)
//			return ((Structure)this.getDescription()).createDescription(aTaxonomicGroupName);
//		else
//			return ((GroupingHeuristic)this.getDescription()).createSAVDescription(aTaxonomicGroupName);
//	}

	/**
	 * Determines if the argument aSAVDescriptor is a member of the argument aDescription.
	 * Returns:	-1 (error value) : if aDescription is NOT a description list belonging to self.
	 * nil : if aSAVDescriptor was NOT found in aDescription.
	 * not nil: the instance in aDescription that matched aSAVDescriptor's structure and attribute names.
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean contains(Descriptor<Object> aDescriptor, List<Descriptor<Object>> aDescription) {
		// Make sure that aDescription is indeed one of my description lists. If not, return the -1 error value
		 if (!(aDescription == this.getUnmatchedDescription()))
			 return false;
                for (Descriptor d:aDescription){
                    if (d.getStructure().equals(aDescriptor.getStructure()) &&
                            (d.getAttribute().equals(aDescriptor.getAttribute())))
                        return true;
			// Stop searching if the next descriptor's structure name is (alphabetically) greater than the argument's structure name
                        if (aDescriptor.getStructure().compareTo(d.getStructure()) < 0)
                                return false;
                }
		return false;
	}
	
	/**
	 * Verifica si existen contradicciones entre los descriptores (estructura, atributo, valor)
	 * Se dice que existe contradiccion  si existe dos descripciones distintas para el mismo par
	 * (estructura, atributo)
	 * @see "M&eacute;todo thereAreContradictions: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean areThereContradictions(Descriptor<Object> aDescriptor, 
		List<Descriptor<Object>> aDescription) {

		for(Descriptor<Object> d: aDescription) {
			if (d.getStructure().equals(aDescriptor.getStructure()) &&
					d.getAttribute().equals(aDescriptor.getAttribute())	) {
					return true; // Hay contradiccion
			}
		}
				
	    return false;
	}
}
