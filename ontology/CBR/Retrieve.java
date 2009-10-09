package ontology.CBR;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


/**
* Protege name: Retrieve
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
public class Retrieve implements jade.content.AgentAction, Serializable {
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
     pcs.firePropertyChange("similarTo", (this.similarTo==null?new Problem():this.similarTo), value);
    this.similarTo=value;
   }
   public Problem getSimilarTo() {
     return this.similarTo;
   }

}
