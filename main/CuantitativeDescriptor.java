package main;


public class CuantitativeDescriptor extends Descriptor {
	private static final long serialVersionUID = 5687089858125395496L;
	private float value;
	
	public CuantitativeDescriptor(String structure, String attribute, Float value) {
		_internalInstanceName = "("+structure+", "+attribute+", "+value.toString()+")";
		super.structure = structure;
		super.attribute = attribute;
		this.value = value;
	}

    @Override
    public String toString() {
    	return _internalInstanceName;
    }
    
    public void setValue(float value) {
    	this.value=value;
    }
    
    public float getValue() {
    	return value;
    }
}
