/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.CBR;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * Case representa un caso que es una representaci&oacute;n de una situaci&oacute;n, donde una
 * descripci&oacute;n (i.e., un conjunto de objetos Descriptor) y una soluci&oacute;n ilustra como un
 * problema particular fue resuelto. Adem&aacute;s, una justificaci&oacute;n explica las razones de la
 * soluci&oacute;n del caso. Cuando un caso (problema) se resuelve, este se agrega a una red para referencia
 * futura v&iacute;a m&eacute;todos de b&uacute;squeda heur&íacute;stica
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 * @author Armando
 *
 */
public class Case {
	/**
	 * A list containing a set of Descriptor's (a description of the problem)
	 */
	private Problem problem;
	/**
	 * An object that represents the solution to the case.  Such object may be a text string, or a compound
	 * object with more associated information.
	 */
	private Solution solution;
	/**
	 * A case may be "positive" (i.e., the given solution is correct) or "negative" (i.e., the given
	 * solution is incorrect)
	 */
	private boolean state; 
	
	/**
	 * Constructor por defecto de la clase
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Case() {
		problem = new Problem();
		solution = new Solution();
		state = false;
	}
	
	/**
	 * @see "M&eacute;todo solution del protocolo adding en SUKIA SmallTalk"
	 * @param solution
	 */
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	/**
	 * @see "M&eacute;todo solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Solution getSolution() {
		return solution;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @param description
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	/**
	 * @see "M&eacute;todo description del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Problem getProblem() {
		return this.problem;
	}
	
	/**
	 * @see "M&eacute;todo state del protocolo adding en SUKIA SmallTalk"
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @see "M&eacute;todo state del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToDescription(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (this.getProblem().getDescription().contains(aDescriptor))
			return false;
		this.getProblem().getDescription().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromDescription(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		return this.getProblem().getDescription().remove(aDescriptor);
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToJustification(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (this.getSolution().getJustification().contains(aDescriptor))
			return false;
		this.getSolution().getJustification().add(aDescriptor);
		
		return true;
	}
	
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromJustification(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		return this.getSolution().getJustification().remove(aDescriptor);
	}
	
	/**
	 * Clears and resets all of the case's instance variables
	 * @see "M&eacute;todo flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void setToDefault() {
		this.getProblem().getDescription().clear();
		this.getSolution().getJustification().clear();
		this.setState(false);
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getStructuresList() {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor<Object> d: this.getProblem().getDescription()) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (!(structuresList.contains(d.getStructure()))) {
				// The structure name was not found in structureList. Append it to structureList
				structuresList.add(d.getStructure());
			} else continue;
		}
		
		return structuresList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	public List<Descriptor<Object>> getDescription(String aStructureName) {
		List<Descriptor<Object>> description;
		
		description = new ArrayList<Descriptor<Object>>();
		
		for(Descriptor<Object> d: this.getProblem().getDescription()) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName)) {
				description.add(d);
			} else continue;
		}
		
		return description;
	}
}