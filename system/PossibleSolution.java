/**
 * @see "Categor&iacute;a Sukia Reasoner en SUKIA SmallTalk"
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
	private List<Descriptor> confirmedDescription;
	private List<Descriptor> unconfirmedDescription;
	private List<Descriptor> contradictions;
	private List<Descriptor> doubtfulDescription;
	private List<Descriptor> solutionDescription;
	private Hypothesis hypothesis;
	private Object solution;
	private boolean evaluated;
	private double points;

	/**
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public PossibleSolution() {
		setSolutionDescription(new ArrayList<Descriptor>());
		setConfirmedDescription(new ArrayList<Descriptor>());
		setUnconfirmedDescription(new ArrayList<Descriptor>());
		setDoubtfulDescription(new ArrayList<Descriptor>());
		setContradictions(new ArrayList<Descriptor>());
		setHypothesis(null);
		setSolution(null);
		setEvaluated(false);
		setPoints(0);
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param confirmedDescription
	 */
	public void setConfirmedDescription(List<Descriptor> confirmedDescription) {
		this.confirmedDescription = confirmedDescription;
	}
	
	/**
	 * @see "M&eacute;todo confirmedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addConfirmedDescription(Descriptor aDescriptor) {
		if (this.getConfirmedDescription().contains(aDescriptor))
			return false;

		this.getConfirmedDescription().add(aDescriptor);
		Collections.sort(this.getConfirmedDescription());
		
		return true;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo confirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor> getConfirmedDescription() {
		return confirmedDescription;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * M&eacute;todo de instancia agregado
	 * @param contradictions
	 */
	public void setContradictions(List<Descriptor> contradictions) {
		this.contradictions = contradictions;
	}

	/**
	 * @see "M&eacute;todo contradictions: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addContradictions(Descriptor aDescriptor) {
		if (this.getContradictions().contains(aDescriptor))
			return false;

		this.getContradictions().add(aDescriptor);
		Collections.sort(this.getContradictions());
		
		return true;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método contradictions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor> getContradictions() {
		return contradictions;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param doubtfulDescription
	 */
	public void setDoubtfulDescription(List<Descriptor> doubtfulDescription) {
		this.doubtfulDescription = doubtfulDescription;
	}
	
	/**
	 * @see "Método doubtfulDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addDoubtfulDescription(Descriptor aDescriptor) {
		if (this.getDoubtfulDescription().contains(aDescriptor))
			return false;

		this.getDoubtfulDescription().add(aDescriptor);
		Collections.sort(this.getDoubtfulDescription());
		
		return true;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método doubtfulDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor> getDoubtfulDescription() {
		return doubtfulDescription;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @see "Método evaluated: del protocolo adding en SUKIA SmallTalk"
	 * @param evaluated
	 */
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método evaluated del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isEvaluated() {
		return evaluated;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @see "Método hypothesis: del protocolo adding en SUKIA SmallTalk"
	 * @param hypothesis
	 */
	public void setHypothesis(Hypothesis hypothesis) {
		this.hypothesis = hypothesis;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método hypothesis del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Hypothesis getHypothesis() {
		return hypothesis;
	}

	/**
	 * M&eacute;todo accesor de escritura
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
	 * M&eacute;todo accesor de lectura
	 * @see "Método points del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * M&eacute;todo accesor de escritura
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
	 * M&eacute;todo accesor de lectura
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getSolution() {
		return solution;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param solutionDescription
	 */
	public void setSolutionDescription(List<Descriptor> solutionDescription) {
		this.solutionDescription = solutionDescription;
	}
	
	/**
	 * @see "Método solutionDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addSolutionDescription(Descriptor aDescriptor) {
		if (this.getSolutionDescription().contains(aDescriptor))
			return false;

		this.getSolutionDescription().add(aDescriptor);
		Collections.sort(this.getSolutionDescription());
		
		return true;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método solutionDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor> getSolutionDescription() {
		return solutionDescription;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param unconfirmedDescription
	 */
	public void setUnconfirmedDescription(List<Descriptor> unconfirmedDescription) {
		this.unconfirmedDescription = unconfirmedDescription;
	}

	/**
	 * @see "Método unconfirmedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addUnconfirmedDescription(Descriptor aDescriptor) {
		if (this.getUnconfirmedDescription().contains(aDescriptor))
			return false;

		this.getUnconfirmedDescription().add(aDescriptor);
		Collections.sort(this.getUnconfirmedDescription());
		
		return true;
	}
		
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método unconfirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor> getUnconfirmedDescription() {
		return unconfirmedDescription;
	}

	/**
	 * @see "Método appendToConfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToConfirmedDescription(List<Descriptor> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addConfirmedDescription(aDescription.get(i-1));
		 }
	}
	
	/**
	 * @see "Método appendToDoubtfulDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToDoubtfulDescription(List<Descriptor> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addDoubtfulDescription(aDescription.get(i-1));
		 }
	}

	/**
	 * @see "Método appendToSolutionDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToSolutionDescription(List<Descriptor> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addSolutionDescription(aDescription.get(i-1));
		 }
	}

	/**
	 * @see "Método appendToUnconfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToUnconfirmedDescription(List<Descriptor> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addUnconfirmedDescription(aDescription.get(i-1));
		 }
	}
	
	/**
	 * @see "Método appendToContradictions: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void appendToContradictions(List<Descriptor> aDescription) {
		for( int i = 1; i <= aDescription.size(); i++) {
			 this.addContradictions(aDescription.get(i-1));
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
	 * Método de instancia agregado
	 */
	public int compareTo(PossibleSolution aPossibleSolution) {
		return (TaxonomicRank.getIndex(aPossibleSolution.getLevel()) - 
				TaxonomicRank.getIndex(this.getLevel()));
	}
}
