package ontology.CBR;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


/**
* Protege name: Resolve
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
public class Resolve implements jade.content.AgentAction, Serializable {
  // bean stuff
   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener pcl) {
     pcs.addPropertyChangeListener(pcl);
   }

   public void removePropertyChangeListener(PropertyChangeListener pcl) {
     pcs.removePropertyChangeListener(pcl);
   }


  private static final long serialVersionUID = 4206237779038972396L;

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
     pcs.firePropertyChange("problema", (this.problema==null?new Problem():this.problema), value);
    this.problema=value;
   }
   public Problem getProblema() {
     return this.problema;
   }

}
