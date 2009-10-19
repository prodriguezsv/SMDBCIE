package ontology.CBR;

import jade.util.leap.*;

/**
* Protege name: AreReasonableSolutionsTo
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
@SuppressWarnings("serial")
public class AreReasonableSolutionsTo implements jade.content.Predicate {

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
   private Problem problem;
   public void setProblem(Problem value) { 
    this.problem=value;
   }
   public Problem getProblem() {
     return this.problem;
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
