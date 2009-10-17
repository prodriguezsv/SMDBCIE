package ontology.CBR;

import java.io.Serializable;


/**
* Protege name: IsNegative
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
@SuppressWarnings("serial")
public class IsNegative implements jade.content.Predicate, Serializable {

  private String _internalInstanceName = null;

  public IsNegative() {
    this._internalInstanceName = "";
  }

  public IsNegative(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: case
   */
   private Case aCase;
   public void setCase(Case value) { 
    this.aCase=value;
   }
   public Case getCase() {
     return this.aCase;
   }
}
