package ontology.CBR;


/**
* Protege name: IsTheMostReasonableTo
* @author ontology bean generator
* @version 2009/10/6, 23:03:27
*/
@SuppressWarnings("serial")
public class IsTheMostReasonableTo implements jade.content.Predicate {
	
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
    this.problema=value;
   }
   public Problem getProblema() {
     return this.problema;
   }

}
