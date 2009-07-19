/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package onthology.CBR;


import java.util.ArrayList;
import java.util.List;

import onthology.common.Description;
import onthology.common.Descriptor;





/**
 * Case representa un caso que es una representación de una situación, donde una descripción
 * (i.e., un conjunto de objetos Descriptor) y una solución ilustra como un problema particular fue resuelto
 * Además, una justificación explica las razones de solución del caso.  Cuando un caso (problema) se resuelve,
 * se agrega a una red para referencia futura vía métodos de búsqueda heuristica
 * @see "Categoría Main de SUKIA Smalltalk"
 * @author Armando
 *
 */
public class Case {
	private CaseSolution solution; // An object that represents the solution to the case.  Such object may be a text string, or a compund object with more associated information.
	private Description<Descriptor<Object>> description; // A list containing a set of Descriptor's (a description of the problem)
	private Description<Descriptor<Object>> justification; // A list containing a set of Descriptor's (the solution path of the case, i.e., the result of the traversal across the net and other reference structures).  
	private boolean state; // A case may be "positive" (i.e., the given solution is correct) or "negative" (i.e., the given solution is incorrect)
	
	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Case() {
		solution = null;
		description = new Description<Descriptor<Object>>();
		justification = new Description<Descriptor<Object>>();
		state = false;
	}
	
	/**
	 * @see "Método solution del protocolo adding en SUKIA SmallTalk"
	 * @param solution
	 */
	public void setSolution(CaseSolution solution) {
		this.solution = solution;
	}
	
	/**
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public CaseSolution getSolution() {
		return solution;
	}
	
	/**
	 * Método de instancia agregado
	 * @param description
	 */
	public void setDescription(Description<Descriptor<Object>> description) {
		this.description = description;
	}
	
	/**
	 * @see "Método description del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getDescription() {
		return description;
	}

	/**
	 * Método de instancia agregado
	 * @param justification
	 */
	public void setJustification(Description<Descriptor<Object>> justification) {
		this.justification = justification;
	}

	/**
	 * @see "Método justification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getJustification() {
		return justification;
	}

	/**
	 * @see "Método state del protocolo adding en SUKIA SmallTalk"
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @see "Método state del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "Método addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToDescription(Descriptor<Object> aDescriptor) {
		if (this.getDescription().contains(aDescriptor))
			return false;
		this.getDescription().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "Método addToJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addToJustification(Descriptor<Object> aDescriptor) {
		if (this.getDescription().contains(aDescriptor))
			return false;
		this.getJustification().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * Clears and resets all of the case's instance variables
	 * @see "Método flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void clear() {
		this.getDescription().clear();
		this.setSolution(null);
		this.getJustification().clear();
		this.setState(false);
	}
	
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public List<String> getStructuresList() {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (!(structuresList.contains(d.getStructure()))) {
				// The structure name was not found in structureList. Append it to structureList
				structuresList.add(d.getStructure());
			} else continue;
		}
		
		return structuresList;
	}
	
	/**
	 * Método de instancia agregado
	 * @return
	 */
	public Description<Descriptor<Object>> getDescription(String aStructureName) {
		Description<Descriptor<Object>> description;
		
		description = new Description<Descriptor<Object>>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (!(description.contains(d.getStructure()))) {
				// The structure name was not found in structureList. Append it to structureList
				description.add(d);
			} else continue;
		}
		
		return description;
	}
}