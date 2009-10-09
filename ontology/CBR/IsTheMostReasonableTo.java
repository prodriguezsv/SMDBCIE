package ontology.CBR;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


/**
* Protege name: IsTheMostReasonableTo
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
public class IsTheMostReasonableTo implements jade.content.Predicate, Serializable {
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

  public IsTheMostReasonableTo() {
    this._internalInstanceName = "";
  }

  public IsTheMostReasonableTo(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: proposedSolution
   */
   private ProposedSolution proposedSolution;
   public void setProposedSolution(ProposedSolution value) { 
     pcs.firePropertyChange("proposedSolution", (this.proposedSolution==null?new ProposedSolution():this.proposedSolution), value);
    this.proposedSolution=value;
   }
   public ProposedSolution getProposedSolution() {
     return this.proposedSolution;
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
