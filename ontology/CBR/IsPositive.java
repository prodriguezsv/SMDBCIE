package ontology.CBR;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


/**
* Protege name: IsPositive
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
public class IsPositive implements jade.content.Predicate, Serializable {
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

  public IsPositive() {
    this._internalInstanceName = "";
  }

  public IsPositive(String instance_name) {
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
     pcs.firePropertyChange("case", (this.aCase==null?new Case():this.aCase), value);
    this.aCase=value;
   }
   public Case getCase() {
     return this.aCase;
   }

}
