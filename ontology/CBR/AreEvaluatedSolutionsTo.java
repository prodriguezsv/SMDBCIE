package ontology.CBR;

import jade.util.leap.*;

/**
* Protege name: AreEvaluatedSolutionsTo
* @author ontology bean generator
* @version 2009/10/17, 19:00:46
*/
@SuppressWarnings("serial")
public class AreEvaluatedSolutionsTo implements jade.content.Predicate {
	private String _internalInstanceName = null;

	  public AreEvaluatedSolutionsTo() {
	    this._internalInstanceName = "";
	  }

	  public AreEvaluatedSolutionsTo(String instance_name) {
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
	   }
	   public boolean removeSuccessfulConflictSet(Hypothesis elem) {
	     boolean result = successfulConflictSet.remove(elem);	     
	     return result;
	   }
	   public void clearAllSuccessfulConflictSet() {
	     successfulConflictSet.clear();     
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
	   }
	   public boolean removeFailureConflictSet(Hypothesis elem) {
	     boolean result = failureConflictSet.remove(elem);	    
	     return result;
	   }
	   public void clearAllFailureConflictSet() {
	     failureConflictSet.clear();	     
	   }
	   public Iterator getAllFailureConflictSet() {return failureConflictSet.iterator(); }
	   public List getFailureConflictSet() {return failureConflictSet; }
	   public void setFailureConflictSet(List l) {failureConflictSet = l; }

}
