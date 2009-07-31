/**
 * 
 */
package system.searchAutomata;

import ontology.common.Descriptor;

/**
 * @author Armando
 *
 */
public class ReturnValue {
	private String response;
	private Descriptor<Object> aDescriptor;

	/**
	 * 
	 */
	public ReturnValue() {
	}
	
	/**
	 * 
	 */
	public ReturnValue(String response, Descriptor<Object> aDescriptor) {
		this.response = response;
		this.aDescriptor = aDescriptor;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setDescriptor(Descriptor<Object> aDescriptor) {
		this.aDescriptor = aDescriptor;
	}

	public Descriptor<Object> getDescriptor() {
		return aDescriptor;
	}

}
