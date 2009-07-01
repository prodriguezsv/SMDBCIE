/**
 * 
 */
package patternMatching;


import jade.util.leap.ArrayList;
import jade.util.leap.List;
import main.Case;

/**
 * ProblemCase es una especialización de Case que agrega la funcionalidad requerida para buscar solucionar
 * el caso problema con algun(os) de los casos dentro de la memoria de casos.
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class ProblemCase extends Case{
	private List weights;
	private List solutionCases;
	
	public ProblemCase() {
		super();
		weights = new ArrayList();
		solutionCases = new ArrayList();
	}
	
	public void setWeights(List weights) {
		this.weights = weights;
	}
	public List getWeights() {
		return weights;
	}
	
	public void setSolutionCases(List solutionCases) {
		this.solutionCases = solutionCases;
	}
	public List getSolutionCases() {
		return solutionCases;
	}
	
	public Object getWeightAt(Integer index) {
		return weights.get(index);
	}
}
