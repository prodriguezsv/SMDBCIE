package ontology.CBR;

import jade.util.leap.*;

/**
* Protege name: AreSelectedSolutionsTo
* @author ontology bean generator
* @version 2009/10/17, 19:00:46
*/
@SuppressWarnings("serial")
public class AreSelectedSolutionsTo implements jade.content.Predicate {
	  private String _internalInstanceName = null;

	  public AreSelectedSolutionsTo() {
	    this._internalInstanceName = "";
	  }

	  public AreSelectedSolutionsTo(String instance_name) {
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
