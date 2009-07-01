package main;

import jade.util.leap.ArrayList;
import jade.util.leap.List;


/**
 * Case representa un caso que es una representación de una situación, donde una descripción
 * (i.e., un conjunto de objetos Descriptor) y una solución ilustra como un problema particular fue resuelto
 * Además, una justificación explica las razones de solución del caso.  Cuando un caso (problema) se resuelve,
 * se agrega a una red para referencia futura vía métodos de búsqueda heuristica
 * @author Armando
 *
 */
public class Case implements jade.content.Concept {
	private static final long serialVersionUID = 5687089858125395496L;
	private String solution;
	private Description description;
	private Description justification;
	private List predecesor;
	private Boolean state;
	
	public Case() {
		solution = "";
		description = new Description();
		justification = new Description();
		predecesor = new ArrayList();
		state = null;
	}
	
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	public String getSolution() {
		return solution;
	}
	
	public void setDescription(Description description) {
		this.description = description;
	}
	
	public Description getDescription() {
		return description;
	}

	public void setJustification(Description justification) {
		this.justification = justification;
	}

	public Description getJustification() {
		return justification;
	}

	public void setPredecesor(List predecesor) {
		this.predecesor = predecesor;
	}

	public List getPredecesor() {
		return predecesor;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Boolean getState() {
		return state;
	}
}