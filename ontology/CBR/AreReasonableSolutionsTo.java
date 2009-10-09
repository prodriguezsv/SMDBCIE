package ontology.CBR;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import jade.util.leap.*;

/**
* Protege name: AreReasonableSolutionsTo
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
public class AreReasonableSolutionsTo implements jade.content.Predicate, Serializable {
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

  public AreReasonableSolutionsTo() {
    this._internalInstanceName = "";
  }

  public AreReasonableSolutionsTo(String instance_name) {
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

   /**
   * Protege name: proposedSolutions
   */
   private List proposedSolutions = new ArrayList();
   public void addProposedSolutions(ProposedSolution elem) { 
     proposedSolutions.add(elem);
    // pcs.firePropertyChange("proposedSolutions", oldList, this.proposedSolutions);
   }
   public boolean removeProposedSolutions(ProposedSolution elem) {
     boolean result = proposedSolutions.remove(elem);
     //pcs.firePropertyChange("proposedSolutions", oldList, this.proposedSolutions);
     return result;
   }
   public void clearAllProposedSolutions() {
     proposedSolutions.clear();
     //pcs.firePropertyChange("proposedSolutions", oldList, this.proposedSolutions);
   }
   public Iterator getAllProposedSolutions() {return proposedSolutions.iterator(); }
   public List getProposedSolutions() {return proposedSolutions; }
   public void setProposedSolutions(List l) {proposedSolutions = l; }

}
