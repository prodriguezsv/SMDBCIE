/**
 * Paquete que reune los conceptos CBR, sus relaciones y sus restricciones de uso
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 */
package ontology.CBR;

import ontology.common.Description;
import ontology.common.Descriptor;


/**
 * Case representa un caso que es una representaci&oacute;n de una situaci&oacute;n, donde una
 * descripci&oacute;n (i.e., un conjunto de objetos Descriptor) y una soluci&oacute;n ilustra como un
 * problema particular fue resuelto. Adem&aacute;s, una justificaci&oacute;n explica las razones de la
 * soluci&oacute;n del caso. Cuando un caso (problema) se resuelve, este se agrega a una red para referencia
 * futura v&iacute;a m&eacute;todos de b&uacute;squeda heur&�acute;stica
 * @see "Categor&iacute;a Main de SUKIA Smalltalk"
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Case implements jade.content.Concept {
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
		this._internalInstanceName = "";
	}

  	private String _internalInstanceName = null;

  	public Case(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}
	
	/**
	 * Constructor alternativo
	 */
	public Case(Description description) {
		problem = new Problem(description);
		solution = new Solution();
		state = false;
		this._internalInstanceName = "";
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
	public boolean addToDescription(Descriptor aDescriptor) {
		if (aDescriptor == null) return false; //Ojo analizar comportamiento alternativo
		
		boolean value = this.getProblem().getDescription().addToConcreteDescription(aDescriptor);
		
		return value;
	}
	
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromDescription(Descriptor aDescriptor) {
		if (aDescriptor == null) return false;
		
		return this.getProblem().getDescription().getDescriptors().remove(aDescriptor);
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToJustification(Descriptor aDescriptor) {
		if (aDescriptor == null) return false; //Ojo analizar comportamiento alternativo
		
		boolean value = this.getSolution().getJustification().addToConcreteDescription(aDescriptor);
		
		return value;
	}
	
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromJustification(Descriptor aDescriptor) {
		if (aDescriptor == null) return false;
		
		return this.getSolution().getJustification().getDescriptors().remove(aDescriptor);
	}
	
	/**
	 * Clears and resets all of the case's instance variables
	 * @see "M&eacute;todo flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void setToDefault() {
		this.getProblem().getDescription().clearAllDescriptors();
		this.getSolution().getJustification().clearAllDescriptors();
		this.setState(false);
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final java.util.List<String> getCharacterStructuresList() {
		return this.getProblem().getDescription().getCharacterStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final java.util.List<String> getHeuristicStructuresList() {
		return this.getProblem().getDescription().getHeuristicStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	public final Description getDescription(String aStructureName) {
		return this.getProblem().getDescription().getDescription(aStructureName);
	}
	
	/**
	 * Determina la igualdad entre dos descriptores
	 * @param aDescriptor
	 * @return
	 */
	public boolean equals(Object aCase) {
		if (aCase == null) return false;
		if (!(aCase instanceof Case)) return false;
		
		if (this.getProblem().equals(((Case)aCase).getProblem())
				&& this.getSolution().equals(((Case)aCase).getSolution())
				&& (this.getState() == ((Case)aCase).getState()))
			return true;
		else return false;
	}
}
