/**
 * 
 */
package searchAutomata;

import redundantDiscriminantNet.SAVDescriptor;

/**
 * @author Armando
 *
 */
public class ReturnValue {
	private String response;
	private SAVDescriptor aSAVDescriptor;

	/**
	 * 
	 */
	public ReturnValue() {
	}
	
	/**
	 * 
	 */
	public ReturnValue(String response, SAVDescriptor aSAVDescriptor) {
		this.response = response;
		this.aSAVDescriptor = aSAVDescriptor;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setASAVDescriptor(SAVDescriptor aSAVDescriptor) {
		this.aSAVDescriptor = aSAVDescriptor;
	}

	public SAVDescriptor getASAVDescriptor() {
		return aSAVDescriptor;
	}

}
