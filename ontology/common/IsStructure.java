package ontology.common;


/**
* Protege name: IsStructure
* @author ontology bean generator
* @version 2009/10/17, 11:59:53
*/
@SuppressWarnings("serial")
public class IsStructure implements jade.content.Predicate {

	  private String _internalInstanceName = null;

	  public IsStructure() {
	    this._internalInstanceName = "";
	  }

	  public IsStructure(String instance_name) {
	    this._internalInstanceName = instance_name;
	  }

	  public String toString() {
	    return _internalInstanceName;
	  }
	  
	  /**
	   * Protege name: structure
	   */
	   private String structure;
	   public void setStructure(String value) { 
	    this.structure=value;
	   }
	   public String getStructure() {
	     return this.structure;
	   }
}
