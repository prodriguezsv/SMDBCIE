/**
 * @see "Categor�a Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ontology.CBR.Case;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;


/**
 * @author Armando
 *
 */
public class PossibleSolution implements Comparable<PossibleSolution> {
	private List<Descriptor<Object>> confirmedDescription;
	private List<Descriptor<Object>> unconfirmedDescription;
	private List<Descriptor<Object>> contradictions;
	private List<Descriptor<Object>> doubtfulDescription;
	private List<Descriptor<Object>> solutionDescription;
	private Hypothesis hypothesis;
	private Object solution;
	private boolean evaluated;
	private double points;

	/**
	 * @see "M�todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public PossibleSolution() {
		setSolution(null);
		setSolutionDescription(new ArrayList<Descriptor<Object>>());
		setConfirmedDescription(new ArrayList<Descriptor<Object>>());
		setUnconfirmedDescription(new ArrayList<Descriptor<Object>>());
		setDoubtfulDescription(new ArrayList<Descriptor<Object>>());
		setContradictions(new ArrayList<Descriptor<Object>>());
		setEvaluated(false);
		setPoints(0);
		setHypothesis(null);
	}

	/**
	 * M�todo de instancia agregado
	 * @param confirmedDescription
	 */
	public void setConfirmedDescription(List<Descriptor<Object>> confirmedDescription) {
		this.confirmedDescription = confirmedDescription;
	}
	
	/**
	 * @see "M�todo confirmedDescription: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo confirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getConfirmedDescription() {
		return confirmedDescription;
	}

	/**
	 * M�todo de instancia agregado
	 * @param contradictions
	 */
	public void setContradictions(List<Descriptor<Object>> contradictions) {
		this.contradictions = contradictions;
	}

	/**
	 * @see "M�todo contradictions: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo contradictions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getContradictions() {
		return contradictions;
	}

	/**
	 * M�todo de instancia agregado
	 * @param doubtfulDescription
	 */
	public void setDoubtfulDescription(List<Descriptor<Object>> doubtfulDescription) {
		this.doubtfulDescription = doubtfulDescription;
	}
	
	/**
	 * @see "M�todo doubtfulDescription: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo doubtfulDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getDoubtfulDescription() {
		return doubtfulDescription;
	}

	/**
	 * @see "M�todo evaluated: del protocolo adding en SUKIA SmallTalk"
	 * @param evaluated
	 */
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

	/**
	 * @see "M�todo evaluated del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isEvaluated() {
		return evaluated;
	}

	/**
	 * @see "M�todo hypothesis: del protocolo adding en SUKIA SmallTalk"
	 * @param hypothesis
	 */
	public void setHypothesis(Hypothesis hypothesis) {
		this.hypothesis = hypothesis;
	}

	/**
	 * @see "M�todo hypothesis del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Hypothesis getHypothesis() {
		return hypothesis;
	}

	/**
	 * M�tofo de instancia agregado
	 * @param points
	 */
	public void setPoints(double points) {
		this.points = points;
	}
	
	/**
	 * @see "M�todo incrementPointsBy: del protocolo adding en SUKIA SmallTalk"
	 * @param increment
	 */
	public void incrementPointsBy(double increment) {
		this.points = this.points + increment;
	}
	
	/**
	 * @see "M�todo incrementPointsBy: del protocolo adding en SUKIA SmallTalk"
	 */
	public void incrementPoints() {
		this.points = this.points + 1;
	}

	/**
	 * @see "M�todo points del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * @see "M�todo solution: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getSolution() {
		return solution;
	}

	/**
	 * M�todo de instancia agregado
	 * @param solutionDescription
	 */
	public void setSolutionDescription(List<Descriptor<Object>> solutionDescription) {
		this.solutionDescription = solutionDescription;
	}
	
	/**
	 * @see "M�todo solutionDescription: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo solutionDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getSolutionDescription() {
		return solutionDescription;
	}

	/**
	 * M�todo de instancia agregado
	 * @param unconfirmedDescription
	 */
	public void setUnconfirmedDescription(List<Descriptor<Object>> unconfirmedDescription) {
		this.unconfirmedDescription = unconfirmedDescription;
	}

	/**
	 * @see "M�todo unconfirmedDescription: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "M�todo unconfirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getUnconfirmedDescription() {
		return unconfirmedDescription;
	}
	
	/**
	 * Determines if aSAVDescriptor is already a member of aDescriptionList. The argument aSAVDescriptor is a member of
	 * aDescriptionList when its structure and attribute names match with the structure and attribute names of a list element.
	 * Returns: 	-1 (error state): The argument aDescriptionList IS NOT a valid list for self.
	 * nil: aSAVDescriptor IS NOT a member of aDescriptionList.
	 * not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor
	 * @see "M�todo includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aSAVDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean contains(Descriptor<Object> aSAVDescriptor, List<Descriptor<Object>> aDescription) {
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
	 * @see "M�todo includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aDescription
	 * @return
	 */
	public boolean containsFull(Descriptor<Object> aDescriptor, List<Descriptor<Object>> aDescription) {
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
	 * @see "M�todo copy:to: del protocolo copying en SUKIA SmallTalk"
	 * @param anExternalDescription
	 * @param aLocalDescription
	 * @return
	 */
	public boolean copy(List<Descriptor<Object>> anExternalDescription, List<Descriptor<Object>> aLocalDescription) {
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
	 * @see "M�todo appendToConfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToConfirmedDescription(List<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addConfirmedDescription(aDescription.get(i-1));
		 }
	}
	
	/**
	 * @see "M�todo appendToDoubtfulDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToDoubtfulDescription(List<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addDoubtfulDescription(aDescription.get(i-1));
		 }
	}

	/**
	 * @see "M�todo appendToSolutionDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToSolutionDescription(List<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addSolutionDescription(aDescription.get(i-1));
		 }
	}

	/**
	 * @see "M�todo appendToUnconfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToUnconfirmedDescription(List<Descriptor<Object>> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addUnconfirmedDescription(aDescription.get(i-1));
		 }
	}
	
	public TaxonomicRank getLevel() {
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
	 * M�todo de instancia agregado
	 */
	// OJo
	public int compareTo(PossibleSolution aPossibleSolution) {
		return (TaxonomicRank.getIndex(aPossibleSolution.getLevel()) - 
				TaxonomicRank.getIndex(this.getLevel()));
	}
}