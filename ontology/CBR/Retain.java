package ontology.CBR;


/**
* Protege name: Retain
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
@SuppressWarnings("serial")
public class Retain implements jade.content.AgentAction {

  private String _internalInstanceName = null;

  public Retain() {
    this._internalInstanceName = "";
  }

  public Retain(String instance_name) {
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
