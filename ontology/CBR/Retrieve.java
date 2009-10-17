package ontology.CBR;


/**
* Protege name: Retrieve
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
@SuppressWarnings("serial")
public class Retrieve implements jade.content.AgentAction {
	
  private String _internalInstanceName = null;

  public Retrieve() {
    this._internalInstanceName = "";
  }

  public Retrieve(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: similarTo
   */
   private Problem similarTo;
   public void setSimilarTo(Problem value) { 
    this.similarTo=value;
   }
   public Problem getSimilarTo() {
     return this.similarTo;
   }

}
