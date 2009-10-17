package ontology.common;

/**
* Protege name: ApliesTo
* @author ontology bean generator
* @version 2009/09/30, 12:55:45
*/
@SuppressWarnings("serial")
public class ApliesTo implements jade.content.Predicate {
  private String _internalInstanceName = null;

  public ApliesTo() {
    this._internalInstanceName = "";
  }

  public ApliesTo(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: attribute
   */
   private String attribute;
   public void setAttribute(String value) { 
     
    this.attribute=value;
   }
   public String getAttribute() {
     return this.attribute;
   }

   /**
   * Protege name: descriptiveElement
   */
   private String descriptiveElement;
   public void setDescriptiveElement(String value) { 
     
    this.descriptiveElement=value;
   }
   public String getDescriptiveElement() {
     return this.descriptiveElement;
   }

}
