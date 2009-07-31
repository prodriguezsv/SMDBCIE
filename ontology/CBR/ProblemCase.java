/**
 * @see "Categoría Pattern-Matching de SUKIA Smalltalk"
 */
package ontology.CBR;


import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * ProblemCase es una especialización de Case que agrega la funcionalidad requerida para buscar solucionar
 * el caso problema con algun(os) de los casos dentro de la memoria de casos.
 * @author Armando
 *
 */
public class ProblemCase extends Case{
	/* vector que almacena pesos asociados a cada uno de los pares (atributo, valor) que describen el caso.
	 Los pesos son indicadores de que tan predictivo es el atributo y son reflejo directo del número de casos
	 bajo el (indice, valor).  Cada norma almacena un contador que representa el numero de casos bajo esa norma.
	 Entre menos casos existan bajo una norma más predictivo es el indice que la precede.*/
	private List<Integer> weights; 
	private Solution solutionCases; // Vector que almacena los casos solución del problema actual
	
	public ProblemCase() {
		super();
		weights = new ArrayList<Integer>();
		solutionCases = new Solution();
	}
	
	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}
	
	/**
	 * @see "Método weights del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Integer> getWeights() {
		return weights;
	}
	
	/**
	 * @see "Método solutionCases: del protocolo accessing en SUKIA SmallTalk"
	 * @param solutionCases
	 */
	public void setSolutionCases(Solution solutionCases) {
		this.solutionCases = solutionCases;
	}
	
	/**
	 * @see "Método solutionCases del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Solution getSolutionCases() {
		return solutionCases;
	}
	
	/**
	 * Devuelve el peso que se encuentra en la posicion index, es decir el peso correspondiente al (atributo, valor)
	 * del caso problema en la posicion index
	 * @see "Método weightAt: del protocolo accessing en SUKIA SmallTalk"
	 * @param index
	 * @return
	 */
	public int getWeightAt(Integer index) {
		/*if (index <= this.getWeights().size())
			return -1;*/

		return weights.get(index);
	}
	
	/**
	 * Busca en los descriptores del caso problema un atributo y devuelve el valor.  Si el atributo no existe devuelve nil
	 * @see "Método valueOf: del protocolo accessing en SUKIA SmallTalk"
	 * @param aAttribute
	 * @return
	 */ 
	public Object valueOf(String aAttribute) {
		for( int i = 1; i <= this.getDescription().size(); i++) {
			if (this.getDescription().get(i-1).getAttribute().equals(aAttribute))
				return this.getDescription().get(i-1).getValue();
		}
		
		return false;
	}
	
	/**
	 * Agrega un descriptor al caso problema.
	 * @see "Método addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 */
	public boolean addToDescription(Descriptor<Object> aDescriptor) {
		super.getDescription().add(aDescriptor);
		this.getWeights().add(0);
		
		return true;
	}
	
	/**
	 * Modifica el valor actual del vector de pesos en la posicion index
	 * @see "Método weightsAt:modifiedWith: del protocolo modifying en SUKIA SmallTalk"
	 * @param index
	 * @param aWeigh
	 * @return
	 */
	public boolean weightsAt(int index, int aWeight) {
		if (!(index <= this.getWeights().size()))
			return false;
		this.getWeights().set(index, aWeight);
		 
		return true;
	}
	
	/**
	 * @see "Método flush del protocolo resetting en SUKIA SmallTalk"
	 */
	public void clear() {
		super.clear();
		
		// Borra todos los elementos del vector de pesos
	    this.getWeights().clear();
	    
	    for( int i = 1; i <= this.getDescription().size(); i++) {
	    	this.getWeights().add(0);
	    }
	    
	    if (this.getSolutionCases().size() > 0) {
	    	// Borra todos los elementos del vector de solucion
    		this.getSolutionCases().clear();
	    }
	}
	
	/**
	 * Verifica si existen contradicciones entre los pares (atributo, valor) del caso problema y aCase.  Se dice que existe contradiccion  si existe un atributo que presenta diferentes valores en cada caso.
	 * Asume que aCase es una instancia de Case.
	 * @see "Método thereAreContradictions: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean thereAreContradictions(Case aCase) {
		Descriptor<Object> descriptorAct;
		Object valor;
		
		// Para cada par (atributo, valor) de aCase.
		for( int i = 1; i <= aCase.getDescription().size(); i++) {
			descriptorAct = aCase.getDescription().get(i-1);
			valor = this.valueOf(descriptorAct.getAttribute());

			if (!(valor == null)) {
				if (!(valor == descriptorAct.getValue()))
					return true; // Hay contradiccion"
			}
				   
					
		}

		return false;  //Es decir no hubo contradiccion
	}
}
