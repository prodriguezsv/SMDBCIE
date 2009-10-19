package ontology.CBR;

import jade.util.leap.*;

/**
* Protege name: Select
* @author ontology bean generator
* @version 2009/10/17, 18:44:01
*/
@SuppressWarnings("serial")
public class Select implements jade.content.AgentAction {
	private String _internalInstanceName = null;

	  public Select() {
	    this._internalInstanceName = "";
	  }

	  public Select(String instance_name) {
	    this._internalInstanceName = instance_name;
	  }

	  public String toString() {
	    return _internalInstanceName;
	  }

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
	   * Protege name: problem
	   */
	   private Problem problem;
	   public void setProblem(Problem value) { 
	    this.problem=value;
	   }
	   public Problem getProblem() {
	     return this.problem;
	   }

}
