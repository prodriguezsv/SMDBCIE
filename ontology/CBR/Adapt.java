package ontology.CBR;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import jade.util.leap.*;

/**
* Protege name: Adapt
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
public class Adapt implements jade.content.AgentAction, Serializable {
	   // bean stuff
	   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	   public void addPropertyChangeListener(PropertyChangeListener pcl) {
	     pcs.addPropertyChangeListener(pcl);
	   }

	   public void removePropertyChangeListener(PropertyChangeListener pcl) {
	     pcs.removePropertyChangeListener(pcl);
	   }


	  private static final long serialVersionUID = -6222351541834602176L;

	  private String _internalInstanceName = null;

	  public Adapt() {
	    this._internalInstanceName = "";
	  }

	  public Adapt(String instance_name) {
	    this._internalInstanceName = instance_name;
	  }

	  public String toString() {
	    return _internalInstanceName;
	  }

	   /**
	   * Protege name: to
	   */
	   private Problem to;
	   public void setTo(Problem value) { 
	     pcs.firePropertyChange("to", (this.to==null?new Problem():this.to), value);
	    this.to=value;
	   }
	   public Problem getTo() {
	     return this.to;
	   }

	   /**
	   * Protege name: successfulConflictSet
	   */
	   private List successfulConflictSet = new ArrayList();
	   public void addSuccessfulConflictSet(Hypothesis elem) { 
	     successfulConflictSet.add(elem);
	     //pcs.firePropertyChange("successfulConflictSet", oldList, this.successfulConflictSet);
	   }
	   public boolean removeSuccessfulConflictSet(Hypothesis elem) {
	     boolean result = successfulConflictSet.remove(elem);
	     //pcs.firePropertyChange("successfulConflictSet", oldList, this.successfulConflictSet);
	     return result;
	   }
	   public void clearAllSuccessfulConflictSet() {
	     successfulConflictSet.clear();
	     //pcs.firePropertyChange("successfulConflictSet", oldList, this.successfulConflictSet);
	   }
	   public Iterator getAllSuccessfulConflictSet() {return successfulConflictSet.iterator(); }
	   public List getSuccessfulConflictSet() {return successfulConflictSet; }
	   public void setSuccessfulConflictSet(List l) {successfulConflictSet = l; }

	   /**
	   * Protege name: failureConflictSet
	   */
	   private List failureConflictSet = new ArrayList();
	   public void addFailureConflictSet(Hypothesis elem) { 
	     failureConflictSet.add(elem);
	     //pcs.firePropertyChange("failureConflictSet", oldList, this.failureConflictSet);
	   }
	   public boolean removeFailureConflictSet(Hypothesis elem) {
	     boolean result = failureConflictSet.remove(elem);
	     //pcs.firePropertyChange("failureConflictSet", oldList, this.failureConflictSet);
	     return result;
	   }
	   public void clearAllFailureConflictSet() {
	     failureConflictSet.clear();
	     //pcs.firePropertyChange("failureConflictSet", oldList, this.failureConflictSet);
	   }
	   public Iterator getAllFailureConflictSet() {return failureConflictSet.iterator(); }
	   public List getFailureConflictSet() {return failureConflictSet; }
	   public void setFailureConflictSet(List l) {failureConflictSet = l; }

}
