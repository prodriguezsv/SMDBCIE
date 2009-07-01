package main;


public class CualitativeDescriptor extends Descriptor {
	private static final long serialVersionUID = 5687089858125395496L;
	private String state;
	
	public CualitativeDescriptor(String structure, String attribute, String state) {
		_internalInstanceName = "("+structure+", "+attribute+", "+state+")";
		this.structure = structure;
		this.attribute = attribute;
		this.state = state;
	}

    @Override
    public String toString() {
    	return _internalInstanceName;
    }
    
    public void setState(String state) {
    	this.state = state;
    }
    
    public String getState() {
    	return state;
    }
}
