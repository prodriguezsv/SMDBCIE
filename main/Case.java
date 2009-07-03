package main;

import java.util.ArrayList;
import java.util.List;


/**
 * Case representa un caso que es una representación de una situación, donde una descripción
 * (i.e., un conjunto de objetos Descriptor) y una solución ilustra como un problema particular fue resuelto
 * Además, una justificación explica las razones de solución del caso.  Cuando un caso (problema) se resuelve,
 * se agrega a una red para referencia futura vía métodos de búsqueda heuristica
 * @author Armando
 *
 */
public class Case extends Node {
	private String solution;
	private List<Descriptor> description;
	private List<Descriptor> justification;
	private List predecessors;
	private Boolean state;
	
	public Case() {
		solution = "";
		description = new ArrayList<Descriptor>();
		justification = new ArrayList<Descriptor>();
		predecessors = new ArrayList();
		state = null;
	}
	
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	public String getSolution() {
		return solution;
	}
	
	public void setDescription(List<Descriptor> description) {
		this.description = description;
	}
	
	public List<Descriptor> getDescription() {
		return description;
	}

	public void setJustification(List<Descriptor> justification) {
		this.justification = justification;
	}

	public List<Descriptor> getJustification() {
		return justification;
	}

	public void setPredecesor(List predecesor) {
		this.predecessors = predecesor;
	}

	public List getPredecesor() {
		return predecessors;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Boolean getState() {
		return state;
	}
}