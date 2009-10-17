package ontology.CBR;

import java.io.Serializable;


/**
* Protege name: Resolve
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
@SuppressWarnings("serial")
public class Resolve implements jade.content.AgentAction, Serializable {

  private String _internalInstanceName = null;

  public Resolve() {
    this._internalInstanceName = "";
  }

  public Resolve(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: problema
   */
   private Problem problema;
   public void setProblema(Problem value) { 
    this.problema=value;
   }
   public Problem getProblema() {
     return this.problema;
   }

}
