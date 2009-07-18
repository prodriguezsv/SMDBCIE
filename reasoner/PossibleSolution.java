/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package reasoner;

import java.util.Collections;

import domainTheory.Taxon;
import domainTheory.TaxonomicLevels;
import main.Case;
import main.Description;
import main.Descriptor;

/**
 * @author Armando
 *
 */
public class PossibleSolution implements Comparable<PossibleSolution> {
	private Description<Descriptor<Object>> confirmedDescription;
	private Description<Descriptor<Object>> contradictions;
	private Description<Descriptor<Object>> doubtfulDescription;
	private boolean evaluated;
	private Hypothesis hypothesis;
	private double points;
	private Object solution;
	private Description<Descriptor<Object>> solutionDescription;
	private Description<Descriptor<Object>> unconfirmedDescription;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public PossibleSolution() {
		setSolution(null);
		setSolutionDescription(new Description<Descriptor<Object>>());
		setConfirmedDescription(new Description<Descriptor<Object>>());
		setUnconfirmedDescription(new Description<Descriptor<Object>>());
		setDoubtfulDescription(new Description<Descriptor<Object>>());
		setContradictions(new Description<Descriptor<Object>>());
		setEvaluated(false);
		setPoints(0);
		setHypothesis(null);
	}

	/**
	 * Método de instancia agregado
	 * @param confirmedDescription
	 */
	public void setConfirmedDescription(Description<Descriptor<Object>> confirmedDescription) {
		this.confirmedDescription = confirmedDescription;
	}
	
	/**
	 * @see "Método confirmedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addConfirmedDescription(Descriptor<Object> aDescriptor) {
		if (contains(aDescriptor, this.getConfirmedDescription()))
			return false;

		this.getConfirmedDescription().add(aDescriptor);
		Collections.sort(this.getConfirmedDescription());
		
		return true;
	}

	/**
	 * @see "Método confirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getConfirmedDescription() {
		return confirmedDescription;
	}

	/**
	 * Método de instancia agregado
	 * @param contradictions
	 */
	public void setContradictions(Description<Descriptor<Object>> contradictions) {
		this.contradictions = contradictions;
	}

	/**
	 * @see "Método contradictions: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addContradictions(Descriptor<Object> aDescriptor) {
		if (contains(aDescriptor, this.getContradictions()))
			return false;

		this.getContradictions().add(aDescriptor);
		Collections.sort(this.getContradictions());
		
		return true;
	}
	
	/**
	 * @see "Método contradictions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getContradictions() {
		return contradictions;
	}

	/**
	 * Método de instancia agregado
	 * @param doubtfulDescription
	 */
	public void setDoubtfulDescription(Description<Descriptor<Object>> doubtfulDescription) {
		this.doubtfulDescription = doubtfulDescription;
	}
	
	/**
	 * @see "Método doubtfulDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addDoubtfulDescription(Descriptor<Object> aDescriptor) {
		if (contains(aDescriptor, this.getDoubtfulDescription()))
			return false;

		this.getDoubtfulDescription().add(aDescriptor);
		Collections.sort(this.getDoubtfulDescription());
		
		return true;
	}
	
	/**
	 * @see "Método doubtfulDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getDoubtfulDescription() {
		return doubtfulDescription;
	}

	/**
	 * @see "Método evaluated: del protocolo adding en SUKIA SmallTalk"
	 * @param evaluated
	 */
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

	/**
	 * @see "Método evaluated del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isEvaluated() {
		return evaluated;
	}

	/**
	 * @see "Método hypothesis: del protocolo adding en SUKIA SmallTalk"
	 * @param hypothesis
	 */
	public void setHypothesis(Hypothesis hypothesis) {
		this.hypothesis = hypothesis;
	}

	/**
	 * @see "Método hypothesis del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Hypothesis getHypothesis() {
		return hypothesis;
	}

	/**
	 * Métofo de instancia agregado
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
	 * @see "Método solution: del protocolo adding en SUKIA SmallTalk"
	 * @param solution
	 */
	public boolean setSolution(Object solution) {

		// Make sure class of the PossibleSolution is OK
		if (!(solution instanceof Case || solution instanceof Taxon))
			return false;

		this.solution = solution;
		
		return true;
	}

	/**
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getSolution() {
		return solution;
	}

	/**
	 * Método de instancia agregado
	 * @param solutionDescription
	 */
	public void setSolutionDescription(Description<Descriptor<Object>> solutionDescription) {
		this.solutionDescription = solutionDescription;
	}
	
	/**
	 * @see "Método solutionDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addSolutionDescription(Descriptor<Object> aDescriptor) {
		if (contains(aDescriptor, this.getSolutionDescription()))
			return false;

		this.getSolutionDescription().add(aDescriptor);
		Collections.sort(this.getSolutionDescription());
		
		return true;
	}

	/**
	 * @see "Método solutionDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getSolutionDescription() {
		return solutionDescription;
	}

	/**
	 * Método de instancia agregado
	 * @param unconfirmedDescription
	 */
	public void setUnconfirmedDescription(Description<Descriptor<Object>> unconfirmedDescription) {
		this.unconfirmedDescription = unconfirmedDescription;
	}

	/**
	 * @see "Método unconfirmedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addUnconfirmedDescription(Descriptor<Object> aDescriptor) {
		if (contains(aDescriptor, this.getUnconfirmedDescription()))
			return false;

		this.getUnconfirmedDescription().add(aDescriptor);
		Collections.sort(this.getUnconfirmedDescription());
		
		return true;
	}
		
	/**
	 * @see "Método unconfirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getUnconfirmedDescription() {
		return unconfirmedDescription;
	}
	
	/**
	 * Determines if aSAVDescriptor is already a member of aDescriptionList. The argument aSAVDescriptor is a member of
	 * aDescriptionList when its structure and attribute names match with the structure and attribute names of a list element.
	 * Returns: 	-1 (error state): The argument aDescriptionList IS NOT a valid list for self.
	 * nil: aSAVDescriptor IS NOT a member of aDescriptionList.
	 * not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aSAVDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean contains(Descriptor<Object> aSAVDescriptor, Description<Descriptor<Object>> aDescription) {
		// Make sure that aDescription is indeed one of my description lists. If not, return the -1 error value
		 if (!(aDescription == this.getSolutionDescription() || 
				 aDescription == this.getConfirmedDescription() || 
				 aDescription == this.getUnconfirmedDescription() || 
				 aDescription == getDoubtfulDescription() ||
				 aDescription == getContradictions()))
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
	 * not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean containsFull(Descriptor<Object> aDescriptor, Description<Descriptor<Object>> aDescription) {
		// Make sure that aDescription is indeed one of my description lists. If not, return the -1 error value
		 if (!(aDescription == this.getSolutionDescription() || 
				 aDescription == this.getConfirmedDescription() || 
				 aDescription == this.getUnconfirmedDescription() || 
				 aDescription == getDoubtfulDescription() ||
				 aDescription == getContradictions()))
			 return false;

		for( int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getStructure() == aDescriptor.getStructure() &&
					aDescription.get(i-1).getAttribute() == aDescriptor.getAttribute() &&
					aDescription.get(i-1).getValue().equals(aDescriptor.getValue()))
				return true;

			// Stop searching if the next descriptor's structure name is (alphabetically) greater than the argument's structure name
			if (i < aDescription.size())
				if (aDescriptor.getStructure().compareTo(aDescription.get(i).getStructure()) < 0)
					return false;
		}

		return false;
	}
	
	/**
	 * @see "Método copy:to: del protocolo copying en SUKIA SmallTalk"
	 * @param anExternalDescription
	 * @param aLocalDescription
	 * @return
	 */
	public boolean copy(Description<Descriptor<Object>> anExternalDescription, Description<Descriptor<Object>> aLocalDescription) {
		// Make sure that aDescription is indeed one of my description lists.
		 if (!(aLocalDescription == this.getSolutionDescription() || 
				 aLocalDescription == this.getConfirmedDescription() || 
				 aLocalDescription == this.getUnconfirmedDescription() || 
				 aLocalDescription == getDoubtfulDescription() ||
				 aLocalDescription == getContradictions()))
			 return false;
		 
		 for( int i = 1; i <= anExternalDescription.size(); i++) {
			 aLocalDescription.add(anExternalDescription.get(i-1));
		 }
		 
		 return true;
	}

	/**
	 * @see "Método appendToConfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToConfirmedDescription(Description<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addConfirmedDescription(aDescription.get(i-1));
		 }
	}
	
	/**
	 * @see "Método appendToDoubtfulDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToDoubtfulDescription(Description<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addDoubtfulDescription(aDescription.get(i-1));
		 }
	}

	/**
	 * @see "Método appendToSolutionDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToSolutionDescription(Description<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addSolutionDescription(aDescription.get(i-1));
		 }
	}

	/**
	 * @see "Método appendToUnconfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToUnconfirmedDescription(Description<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addUnconfirmedDescription(aDescription.get(i-1));
		 }
	}
	
	public String getLevel() {
		if (this.getSolution() == null) return null;
		
		if (this.getSolution() instanceof Case)
			return (((Case)this.getSolution()).getSolution().getTaxonLevel());

		if (this.getSolution() instanceof Taxon)
			return (((Taxon)this.getSolution()).getLevel());
		
		return null;
	}
	
	public boolean getStatus() {
		// Taxon instances always have a positive status
		if (this.getSolution() instanceof Taxon)
			return true;

		// Return the status of the associated Case or SAVCase
		return (((Case)this.getSolution()).getState());
	}
	
	public String getName() {
		if (this.getSolution() == null) return null;
		
		if (this.getSolution() instanceof Case)
			return (((Case)this.getSolution()).getSolution().getTaxonName());

		if (this.getSolution() instanceof Taxon)
			return (((Taxon)this.getSolution()).getName());
		
		return null;
	}
	
	/**
	 * Método de instancia agregado
	 */
	// OJo
	public int compareTo(PossibleSolution aPossibleSolution) {
		return (TaxonomicLevels.getLevels().indexOf(aPossibleSolution.getLevel()) - 
				TaxonomicLevels.getLevels().indexOf(this.getLevel()));
	}
}
