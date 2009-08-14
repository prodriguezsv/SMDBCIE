/**
 * Paquete que reune clases auxiliares para el paquete redundantDiscrininationNet
 * @see "Categoría Pattern-Matching de SUKIA Smalltalk"
 */
package redundantDiscriminationNet.auxiliary;


import java.util.ArrayList;
import java.util.List;

import ontology.CBR.Case;
import ontology.common.Descriptor;


/**
 * Tiene la funcionalidad requerida para buscar solucionar el caso problema con algun(os) de los casos
 * dentro de la memoria de casos.
 * @author Armando
 *
 */
public class ProblemSolutions {
	/**
	 * Caso problema actual a resolver
	 */
	private Case problemCase;
	/**
	 * vector que almacena pesos asociados a cada uno de los pares (atributo, valor) que describen el caso.
	 * Los pesos son indicadores de que tan predictivo es el atributo y son reflejo directo del número de
	 * casos bajo el (indice, valor).  Cada norma almacena un contador que representa el numero de casos
	 * bajo esa norma. Entre menos casos existan bajo una norma más predictivo es el indice que la precede.
	 */
	private List<Integer> weights; 
	/**
	 * Vector que almacena los casos solución del problema actual
	 */
	private List<Case> solutionCases;
	
	/**
	 * Constructor por defecto de la clase
	 */
	public ProblemSolutions() {
		problemCase = null;
		weights = new ArrayList<Integer>();
		solutionCases = new ArrayList<Case>();
	}
	
	/**
	 * Constructor alternativo de la clase
	 */
	public ProblemSolutions(Case aProblemCase) {
		problemCase = aProblemCase;
		weights = new ArrayList<Integer>();
		solutionCases = new ArrayList<Case>();
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @param problemCase
	 */
	public void setProblemCase(Case problemCase) {
		this.problemCase = problemCase;
	}

	/**
	 *  M&eacute;todo accesor de lectura
	 * @return
	 */
	public Case getProblemCase() {
		return problemCase;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param weights
	 */
	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo weights del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Integer> getWeights() {
		return weights;
	}
	
	/**
	 * M&eacute;todo accesor de escritura
	 * @see "M&eacute;todo solutionCases: del protocolo accessing en SUKIA SmallTalk"
	 * @param solutionCases
	 */
	public void setSolutionCases(List<Case> solutionCases) {
		this.solutionCases = solutionCases;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo solutionCases del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Case> getSolutionCases() {
		return solutionCases;
	}
	
	/**
	 * Devuelve el peso que se encuentra en la posicion index, es decir el peso correspondiente al
	 * (atributo, valor) del caso problema en la posici&oacute;n index
	 * @see "M&eacute;todo weightAt: del protocolo accessing en SUKIA SmallTalk"
	 * @param index Indice del peso a obtener
	 * @return El peso del elemento descriptivo en la posici&oacute;n index de la descripci&oacute;n
	 * del problema
	 */
	public int getWeight(int index) {
		return weights.get(index);
	}
	
	/**
	 * Busca en los descriptores del caso problema un atributo y devuelve su valor.
	 * @see "M&eacute;todo valueOf: del protocolo accessing en SUKIA SmallTalk"
	 * @param anAttribute
	 * @return El valor de anAttribute o null si el atributo no existe
	 */ 
	public Object getValue(String anAttribute) {
		for(Descriptor<Object> d: this.getProblemCase().getProblem().getDescription()) {
			if (d.getAttribute().equals(anAttribute))
				return d.getValue();
		}
		
		return null;
	}
	
	/**
	 * Agrega un descriptor al caso problema.
	 * @see "M&eacute;todo addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return true si agrega el descriptor o false de lo contrario
	 */
	public boolean addToDescription(Descriptor<Object> aDescriptor) {
		if (this.getProblemCase().addToDescription(aDescriptor)) {
			this.getWeights().add(0);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Modifica el valor actual del vector de pesos en la posicion index
	 * @see "M&eacute;todo weightsAt:modifiedWith: del protocolo modifying en SUKIA SmallTalk"
	 * @param index Indice del peso a actualizar
	 * @param aWeigh El valor del peso a actualizar
	 * @return
	 */
	public boolean setWeight(int index, int aWeight) {
		if (!(index < this.getWeights().size()))
			return false;
		
		this.getWeights().set(index, aWeight);
		 
		return true;
	}
	
	/**
	 * Actualiza las variables de instancia a sus valores por defecto
	 * @see "M&eacute;todo flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void setToDefault() {
		if (this.getProblemCase() != null) this.getProblemCase().setToDefault();
		
		// Borra todos los elementos del vector de pesos
	    this.getWeights().clear();
	    
    	// Borra todos los elementos del vector de solucion
		this.getSolutionCases().clear();
	    
	}
	
	/**
	 * Verifica si existen contradicciones entre los pares (atributo, valor) del caso problema y aCase.
	 * Se dice que existe contradiccion  si existe un atributo que presenta diferentes valores en cada caso.
	 * @see "M&eacute;todo thereAreContradictions: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	private boolean areThereContradictions(Case aCase) {
		Object valor;
		
		// Para cada par (atributo, valor) de aCase.
		for(Descriptor<Object> d: aCase.getProblem().getDescription()) {
			valor = this.getValue(d.getAttribute());

			if (!(valor == null)) {
				if (!(valor.equals(d.getValue())))
					return true; // Hay contradiccion
			}
		}

		return false;  //No hubo contradiccion
	}
	
	/**
	 * Agrega un caso a la lista de casos soluci&oacute;n.  Antes de agregar el caso verifica si este es una
	 * posible soluci&oacute;n al problema y ademas si el caso no existe ya en la lista de soluciones. El
	 * caso soluci&oacute;n no debe contradecir en ninguno de sus atributos al caso problema.
	 * @see "M&eacute;todo Add:for: del protocolo adding en SUKIA SmallTalk"
	 */
	public boolean addSolutionCase(Case aHypothesis) {
		// Si la hipotesis no existe en la lista de soluciones
		if (!(this.getSolutionCases().contains(aHypothesis))) {
			if (!(this.areThereContradictions(aHypothesis))) {
				// Agrego el caso aunque sea negativo para saber como no deberia solucionarse el problema.
				this.getSolutionCases().add(aHypothesis);
				return true;
			}
		}
		
		return false;
	}
}
