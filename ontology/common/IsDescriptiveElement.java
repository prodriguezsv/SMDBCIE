package ontology.common;


/**
* Protege name: IsStructure
* @author ontology bean generator
* @version 2009/10/17, 11:59:53
*/
@SuppressWarnings("serial")
public class IsDescriptiveElement implements jade.content.Predicate {

	  private String _internalInstanceName = null;

	  public IsDescriptiveElement() {
	    this._internalInstanceName = "";
	  }

	  public IsDescriptiveElement(String instance_name) {
	    this._internalInstanceName = instance_name;
	  }

	  public String toString() {
	    return _internalInstanceName;
	  }
	  
	  /**
	   * Protege name: structure
	   */
	   private String descriptiveElement;
	   public void setDescriptiveElement(String value) { 
	    this.descriptiveElement=value;
	   }
	   public String getDescriptiveElement() {
	     return this.descriptiveElement;
	   }
}
