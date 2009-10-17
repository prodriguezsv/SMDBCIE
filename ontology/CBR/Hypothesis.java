/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package ontology.CBR;

import jade.util.leap.ArrayList;
import jade.util.leap.Iterator;
import jade.util.leap.List;
import jade.util.leap.Set;
import jade.util.leap.SortedSetImpl;

import ontology.common.Description;
import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Hypothesis implements jade.content.Concept {
	private Description description;
	private Description justification;
	private Description unmatchedDescription;
	private List possibleSolutions;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Hypothesis() {
		this._internalInstanceName = "";
		setDescription(new Description());

		// Sort criteria: taxonomic level
		setPossibleSolutions(new ArrayList());

		// Sort criteria: concatenated structure and attribute names
		setUnmatchedDescription(new Description());
		setJustification(new Description());
	}

  	private String _internalInstanceName = null;

  	public Hypothesis(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}

	/**
	 * @see "Método descriptiveElement: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescElt
	 */
	public void setDescription(Description aDescElt) {
		this.description = aDescElt;
		
		//return true;
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
	 * @param possibleSolutions
	 */
	public void setPossibleSolutions(List possibleSolutions) {
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

		//Obtener el estado de la posible solución del primer elemento y la solución a insertar
		firstEltStatus = ((PossibleSolution)this.getPossibleSolutions().get(0)).getStatus();
		possSolStatus = aPossibleSolution.getStatus();

		//Los estados de ambos deben ser iguales
		if (firstEltStatus == possSolStatus){
			this.getPossibleSolutions().add(aPossibleSolution);
			this.sortPossibleSolutions(this.getPossibleSolutions());
			//Collections.sort(this.getPossibleSolutions());
			aPossibleSolution.setHypothesis(this);
			return true;
		}	

		return false;
	}

	/**
	 * @see "Método possibleSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getPossibleSolutions() {
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
	
	/**
	 * Ordena possibleSolutions según el orden natural definido
	 * @param possibleSolutions
	 */
	private void sortPossibleSolutions(List possibleSolutions) {
		Set possibleSolutionsSet = new SortedSetImpl();
		
		Iterator i = possibleSolutions.iterator();
		
		while (i.hasNext()) {
			possibleSolutionsSet.add(i.next());
		}
		
		possibleSolutions.clear();
		
		Iterator j = possibleSolutionsSet.iterator();
		
		while (j.hasNext()) {
			possibleSolutions.add(j.next());
		}
	}
}
