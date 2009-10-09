/**
 * @see "Categor&iacute;a Sukia Reasoner en SUKIA SmallTalk"
 */
package ontology.CBR;

import jade.content.Concept;

import java.io.Serializable;

import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;


/**
 * @author Armando
 *
 */
public class PossibleSolution implements jade.content.Concept, Serializable, Comparable<PossibleSolution> {
	private Description confirmedDescription;
	private Description unconfirmedDescription;
	private Description contradictions;
	private Description doubtfulDescription;
	private Description solutionDescription;
	private Hypothesis hypothesis;
	private Concept solution;
	private boolean evaluated;
	private double points;

	/**
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public PossibleSolution() {
		this._internalInstanceName = "";
		setSolutionDescription(new Description());
		setConfirmedDescription(new Description());
		setUnconfirmedDescription(new Description());
		setDoubtfulDescription(new Description());
		setContradictions(new Description());
		setHypothesis(null);
		setSolution(null);
		setEvaluated(false);
		setPoints(0);
	}
	
	private static final long serialVersionUID = 4206237779038972396L;

  	private String _internalInstanceName = null;

  	public PossibleSolution(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param confirmedDescription
	 */
	public void setConfirmedDescription(Description confirmedDescription) {
		this.confirmedDescription = confirmedDescription;
	}
	
	/**
	 * @see "M&eacute;todo confirmedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToConfirmedDescription(Descriptor aDescriptor) {
		return this.getConfirmedDescription().addToConcreteDescription(aDescriptor);
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo confirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getConfirmedDescription() {
		return confirmedDescription;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * M&eacute;todo de instancia agregado
	 * @param contradictions
	 */
	public void setContradictions(Description contradictions) {
		this.contradictions = contradictions;
	}

	/**
	 * @see "M&eacute;todo contradictions: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToContradictions(Descriptor aDescriptor) {
		return this.getContradictions().addToAbstractDescription(aDescriptor);
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método contradictions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getContradictions() {
		return contradictions;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param doubtfulDescription
	 */
	public void setDoubtfulDescription(Description doubtfulDescription) {
		this.doubtfulDescription = doubtfulDescription;
	}
	
	/**
	 * @see "Método doubtfulDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToDoubtfulDescription(Descriptor aDescriptor) {
		return this.getDoubtfulDescription().addToConcreteDescription(aDescriptor);
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método doubtfulDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getDoubtfulDescription() {
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
	public void setSolution(Concept solution) {
		// Make sure class of the PossibleSolution is OK
		if (!(solution instanceof Case || solution instanceof Taxon))
			return;

		this.solution = solution;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Concept getSolution() {
		return solution;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param solutionDescription
	 */
	public void setSolutionDescription(Description solutionDescription) {
		this.solutionDescription = solutionDescription;
	}
	
	/**
	 * @see "Método solutionDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToSolutionDescription(Descriptor aDescriptor) {
		return this.getSolutionDescription().addToConcreteDescription(aDescriptor);
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método solutionDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getSolutionDescription() {
		return solutionDescription;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param unconfirmedDescription
	 */
	public void setUnconfirmedDescription(Description unconfirmedDescription) {
		this.unconfirmedDescription = unconfirmedDescription;
	}

	/**
	 * @see "Método unconfirmedDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToUnconfirmedDescription(Descriptor aDescriptor) {
		return this.getUnconfirmedDescription().addToConcreteDescription(aDescriptor);
	}
		
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "Método unconfirmedDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getUnconfirmedDescription() {
		return unconfirmedDescription;
	}

	/**
	 * @see "Método appendToConfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void addAllToConfirmedDescription(Description aDescription) {
		this.getConfirmedDescription().addAllToConcreteDescription(aDescription);
	}
	
	/**
	 * @see "Método appendToDoubtfulDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void addAllToDoubtfulDescription(Description aDescription) {
		this.getDoubtfulDescription().addAllToConcreteDescription(aDescription);
	}

	/**
	 * @see "Método appendToSolutionDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void addAllToSolutionDescription(Description aDescription) {
		this.getSolutionDescription().addAllToConcreteDescription(aDescription);
	}

	/**
	 * @see "Método appendToUnconfirmedDescription: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void addAllToUnconfirmedDescription(Description aDescription) {
		this.getUnconfirmedDescription().addAllToConcreteDescription(aDescription);
	}
	
	/**
	 * @see "Método appendToContradictions: del protocolo inheritance en SUKIA SmallTalk"
	 * @param aDescription
	 */
	public void addAllToContradictions(Description aDescription) {
		this.getContradictions().addAllToConcreteDescription(aDescription);
	}
	
	public String getLevel() {
		if (this.getSolution() == null) return null;
		
		if (this.getSolution() instanceof Case)
			return (((Case)this.getSolution()).getSolution().getTaxonLevel());

		if (this.getSolution() instanceof Taxon)
			return (((Taxon)this.getSolution()).getLevel());
		
		return null;
	}
	
	/**
	 * Devuelve el estado de la solución que representa PossibleSolution
	 * @return true si la solución es un Taxon o el caso es positivo o false si el caso es negativo
	 */
	public boolean getStatus() {
		// Taxon instances always have a positive status
		if (this.getSolution() instanceof Taxon)
			return true;

		// Return the status of the associated Case or SAVCase
		return (((Case)this.getSolution()).getState());
	}
	
	/*
	 * 
	 */
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
		return (TaxonomicRank.getIndex(TaxonomicRank.valueOf(aPossibleSolution.getLevel().toUpperCase())) - 
				TaxonomicRank.getIndex(TaxonomicRank.valueOf(getLevel().toUpperCase())));
	}
}
