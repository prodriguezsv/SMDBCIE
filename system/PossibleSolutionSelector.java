/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import onthology.CBR.Hypothesis;
import onthology.CBR.PossibleSolution;
import onthology.CBR.ProposedSolution;
import onthology.taxonomy.TaxonomicLevels;


/**
 * @author Armando
 *
 */
public class PossibleSolutionSelector {
	private List<Hypothesis> failedGrpHeuristicConflictSet;
	private List<Hypothesis> failedStructConflictSet;
	private List<ProposedSolution> generalSolutions;
	private List<ProposedSolution> goalSolutions;
	private String identificationGoal;
	private int maxNumberSolutions;
	private boolean showFailedSolutions;
	private List<ProposedSolution> specificSolutions;
	private boolean status;
	private List<Hypothesis> successfulGrpHeuristicConflictSet;
	private List<Hypothesis> successfulStructConflictSet;

	/**
	 * Método de instancia agregado
	 */
	public PossibleSolutionSelector() {
		super();
	}
	
	/**
	 * USER EXPECTATION: aNumberOfSolutions is an integer argument, that determines the maximum number of solutions the user wants to see.
	 * USER EXPECTATION: showFailed is a boolean argument, that determines whether or not to show failed solutions to the user.
	 * @see "Método initializeWith:and:and:and:and:and:and: del protocolo initializing en SUKIA SmallTalk"
	 */
	public PossibleSolutionSelector(String anIdentGoal, List<Hypothesis> aSuccSList, List<Hypothesis> aFailSList,
			List<Hypothesis> aSuccGHList,  List<Hypothesis> aFailGHList, int aNumberOfSolutions, boolean showFailed) {
		setIdentificationGoal(anIdentGoal);
		setSuccessfulStructConflictSet(aSuccSList);
		setFailedStructConflictSet(aFailSList);
		setSuccessfulGrpHeuristicConflictSet(aSuccGHList);
		setFailedGrpHeuristicConflictSet(aFailGHList);
		setMaxNumberSolutions(aNumberOfSolutions);
		setShowFailedSolutions(showFailed);

		setStatus(false);

		setGoalSolutions(new ArrayList<ProposedSolution>());
		setSpecificSolutions(new ArrayList<ProposedSolution>());
		setGeneralSolutions(new ArrayList<ProposedSolution>());
	}

	/**
	 * Método de instancia agregado
	 * @param failedGrpHeuristicConflictSet
	 */
	public void setFailedGrpHeuristicConflictSet(List<Hypothesis> failedGrpHeuristicConflictSet) {
		this.failedGrpHeuristicConflictSet = failedGrpHeuristicConflictSet;
	}

	/**
	 * @see "Método failedGrpHeuristicConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getFailedGrpHeuristicConflictSet() {
		return failedGrpHeuristicConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param failedStructConflictSet
	 */
	public void setFailedStructConflictSet(List<Hypothesis> failedStructConflictSet) {
		this.failedStructConflictSet = failedStructConflictSet;
	}

	/**
	 * @see "Método failedStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getFailedStructConflictSet() {
		return failedStructConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param generalSolutions
	 */
	public void setGeneralSolutions(List<ProposedSolution> generalSolutions) {
		this.generalSolutions = generalSolutions;
	}

	/**
	 * @see "Método generalSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<ProposedSolution> getGeneralSolutions() {
		return generalSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param goalSolutions
	 */
	public void setGoalSolutions(List<ProposedSolution> goalSolutions) {
		this.goalSolutions = goalSolutions;
	}

	/**
	 * @see "Método generalSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aProposedSolution
	 */
	public void addGeneralSolution(ProposedSolution aProposedSolution) {
		this.getGeneralSolutions().add(aProposedSolution);
		Collections.sort(this.getGeneralSolutions());
	}
	
	/**
	 * @see "Método goalSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<ProposedSolution> getGoalSolutions() {
		return goalSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param identificationGoal
	 */
	public void setIdentificationGoal(String identificationGoal) {
		this.identificationGoal = identificationGoal;
	}
	
	/**
	 * @see "Método goalSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aProposedSolution
	 */
	public void addGoalSolution(ProposedSolution aProposedSolution) {
		this.getGoalSolutions().add(aProposedSolution);
		Collections.sort(this.getGoalSolutions());
	}

	/**
	 * @see "Método identificationGoal del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getIdentificationGoal() {
		return identificationGoal;
	}

	/**
	 * Método de instancia agregado
	 * @param maxNumberSolutions
	 */
	public void setMaxNumberSolutions(int maxNumberSolutions) {
		this.maxNumberSolutions = maxNumberSolutions;
	}

	/**
	 * @see "Método maxNumberSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getMaxNumberSolutions() {
		return maxNumberSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param showFailedSolutions
	 */
	public void setShowFailedSolutions(boolean showFailedSolutions) {
		this.showFailedSolutions = showFailedSolutions;
	}

	/**
	 * @see "Método showFailedSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isShowFailedSolutions() {
		return showFailedSolutions;
	}

	/**
	 * Mpetodo de instancia agregado
	 * @param specificSolutions
	 */
	public void setSpecificSolutions(List<ProposedSolution> specificSolutions) {
		this.specificSolutions = specificSolutions;
	}

	/**
	 * @see "Método specificSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<ProposedSolution> getSpecificSolutions() {
		return specificSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param successfulGrpHeuristicConflictSet
	 */
	public void setSuccessfulGrpHeuristicConflictSet(List<Hypothesis> successfulGrpHeuristicConflictSet) {
		this.successfulGrpHeuristicConflictSet = successfulGrpHeuristicConflictSet;
	}
	
	/**
	 * @see "Método specificSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param aProposedSolution
	 */
	public void addSpecificSolution(ProposedSolution aProposedSolution) {
		this.getSpecificSolutions().add(aProposedSolution);
		Collections.sort(this.getSpecificSolutions());
	}

	/**
	 * @see "Método successfulGrpHeuristicConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getSuccessfulGrpHeuristicConflictSet() {
		return successfulGrpHeuristicConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param successfulStructConflictSet
	 */
	public void setSuccessfulStructConflictSet(List<Hypothesis> successfulStructConflictSet) {
		this.successfulStructConflictSet = successfulStructConflictSet;
	}

	/**
	 * @see "Método successfulStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getSuccessfulStructConflictSet() {
		return successfulStructConflictSet;
	}

	/**
	 * @see "Método status: del protocolo adding en SUKIA SmallTalk"
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @see "Método status del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * This method takes as argument a list of possible solutions, sorted by their point score. Each possible solution
	 * has a taxonomic level, which can be represented numerically. This level is compared against the [numeric representation
	 * of the] level stated in the identification goal. If the possible solution's level is less than the stated identification goal, then
	 * that possible solution is added to the general-solutions list. If the level is equal to the stated identification goal, the possible
	 * solution is added to the goal-solutions list. Finally, if the level is greater than the stated identification goal, the possible solution
	 * is added to the specific-solutions list.
	 * NOTE: Since the argument is a sorted list, then the first elements to be added to any of the solutions lists are those
	 * with the highest scores.
	 * The process will stop when either of these conditions hold:
	 * 1) The number of elements in the goal-solutions list is equal to the maximum number of solutions expected by the user.
	 * 2) The number of elements in the specific-solutions list is equal to the maximum number of solutions expected by the user.
	 * 3) The sum of the elements belonging to the goal-solutions and specific-solutions lists is greater than or equal to the
	 * maximum number of solutions expected by the user.
	 * 4) The argument aSortedPossibleSolutionsList is empty.
	 * PRECONDITION: aSortedPossibleSolutionsList is NOT empty.
	 * @see "Método distribute: del protocolo selecting solutions en SUKIA SmallTalk"
	 * Returns: self. The process ran OK.
	 * @return
	 */
	public boolean distribute(List<PossibleSolution> aSortedPossibleSolutionsList) {
		ProposedSolution ps;
		int max, goalLevel, i, psLevel;
		
		// Check the precondition
		if (aSortedPossibleSolutionsList.isEmpty())
			return false;

		// Get the maximum number of elements that can be added  to any solutions list
		if (aSortedPossibleSolutionsList.size() <= this.getMaxNumberSolutions())
			max = aSortedPossibleSolutionsList.size();
		else max = this.getMaxNumberSolutions();

		// Get the identification goal as number
		goalLevel = TaxonomicLevels.getLevels().indexOf(this.getIdentificationGoal());

		i = 1;
		while (i <= max) {
			ps = new ProposedSolution();
			ps.setStatus(this.isStatus());
			ps.setSolution(aSortedPossibleSolutionsList.remove(0));

			if (ps.getSolution().getPoints() < 0) ps.setDegreeOfCertainty("uncertain");
			if (ps.getSolution().getPoints() == 0) ps.setDegreeOfCertainty("doubtful");
			if (ps.getSolution().getPoints() > 0) ps.setDegreeOfCertainty("certain");
			
			psLevel = TaxonomicLevels.getLevels().indexOf(ps.getSolution().getLevel());

			// If applicable, insert the new proposed solution to the general solutions list
			if ((psLevel < goalLevel) && (this.getGeneralSolutions().size() < this.getMaxNumberSolutions()))
				this.addGeneralSolution(ps);

			// If applicable, insert the new proposed solution to the goal solutions list
			if ((psLevel == goalLevel) && (this.getGoalSolutions().size() < this.getMaxNumberSolutions()))
				this.addGoalSolution(ps);
			
			// If applicable, insert the new proposed solution to the specific solutions list
			if ((psLevel > goalLevel) && (this.getSpecificSolutions().size() < this.getMaxNumberSolutions()))
				this.addSpecificSolution(ps);

			i = i + 1;
		}

		/* Condition to stop the process: either the number of elements in either the specific or goal 
		 solutions lists is equal to maximum expected by the user, OR the sum of both lists satisfies
		 this user expectation*/
		if ((this.getGoalSolutions().size() == this.getMaxNumberSolutions()) ||
		 ((this.getSpecificSolutions().size() == this.getMaxNumberSolutions()) |
		 ((this.getGoalSolutions().size() + this.getSpecificSolutions().size()) >= this.getMaxNumberSolutions())))
			return true;

		// Try to load some more PossibleSolutions to the solutions lists
		return this.distribute(aSortedPossibleSolutionsList);
	}
	
	/**
	 * Driver method for this class. First, it calls method sortPossibleSolutions, in order to sort the possible
	 * solutions (by point score) of all eligible hypotheses included in the conflict sets. Next, the method
	 * distribute: is called, in order to distribute all sorted-out possible solutions into their corresponding
	 * solutions lists. Finally, an attempt is made to extract, from the goal-solutions list first, the number of
	 * proposed solutions that the user is willing to see.  If that list becomes empty, the specific-solutions
	 * list is used instead. Finally, if the specific-solutions list also becomes empty, the general-solutions
	 * list is used as last resort.
	 * The solution lists are ordered by degree of certainty and by point score also. Hence, the first elements
	 * to be subtracted from each of the solutions lists are the best ones.
	 * @see "Método select del protocolo selecting solutions en SUKIA SmallTalk"
	 * Returns: an empty list, OR a list with THE BEST identification solutions.
	 * @return
	 */
	public List<ProposedSolution> select() {
		List<PossibleSolution> proposedSolutionsList;
		List<ProposedSolution> solutions;
		int i;
		
		proposedSolutionsList = this.sortPossibleSolutions();
		solutions = new ArrayList<ProposedSolution>();

		if (proposedSolutionsList.isEmpty())
			return solutions;

		this.distribute(proposedSolutionsList);

		i = 1;
		while (i <= this.getMaxNumberSolutions()) {
			if (!(this.getGoalSolutions().isEmpty()))
				solutions.add(this.getGoalSolutions().remove(0));
			else { 
				if (!(this.getSpecificSolutions().isEmpty()))
					solutions.add(this.getSpecificSolutions().remove(0));
				else {
					if (!(this.getGeneralSolutions().isEmpty()))
						solutions.add(this.getGeneralSolutions().remove(0));
					else i = this.getMaxNumberSolutions();
				}

			} 

			i = i + 1;
		}
		
		return solutions;
	}
	
	/**
	 * This method sorts possible solutions by their point scores.
	 * Returns: a [possibly empty] sorted possible solutions list.
	 * @see "Método sortPossibleSolutions del protocolo selecting solutions en SUKIA SmallTalk"
	 * @return
	 */
	public List<PossibleSolution> sortPossibleSolutions() {
		List<PossibleSolution> pSolutions;
		Hypothesis hypothesis;
	
		/* All possible solutions will be sorted by their point number: those with higher scores will 
		 be at the beginning of the list*/
		pSolutions = new ArrayList<PossibleSolution>();

		// FIRST CHOICE: Load all successful (i.e., positive) structure possible solutions, if any
		for( int i = 1; i <= this.getSuccessfulStructConflictSet().size(); i++) {
			hypothesis = this.getSuccessfulStructConflictSet().get(i-1);
			for( int j = 1; j <= hypothesis.getPossibleSolutions().size(); j++) {
				pSolutions.add(hypothesis.getPossibleSolutions().get(j-1));
				this.sort(pSolutions);
			}
		}
		
		// SECOND CHOICE: Load all successful (i.e., positive) grouping heuristic possible solutions, if any
		for( int i = 1; i <= this.getSuccessfulGrpHeuristicConflictSet().size(); i++) {
			hypothesis = this.getSuccessfulGrpHeuristicConflictSet().get(i-1);
			for( int j = 1; j <= hypothesis.getPossibleSolutions().size(); j++) {
				pSolutions.add(hypothesis.getPossibleSolutions().get(j-1));
				this.sort(pSolutions);
			}
		}
			
		if (!(pSolutions.isEmpty())) {
			this.setStatus(true);
			return pSolutions;
		}

		/* If the user wants to see failed solutions, *in case there aren't any positive solutions*, 
		  load them in the list*/
		if (this.isShowFailedSolutions()) {
			// Load all failed (i.e., negative) structure possible solutions, if any
			for( int i = 1; i <= this.getFailedStructConflictSet().size(); i++) {
				hypothesis = this.getFailedStructConflictSet().get(i-1);
				for( int j = 1; j <= hypothesis.getPossibleSolutions().size(); j++) {
					pSolutions.add(hypothesis.getPossibleSolutions().get(j-1));
					this.sort(pSolutions);
				}
			}

			// Load all failed (i.e., negative) grouping heuristic possible solutions, if any
			for( int i = 1; i <= this.getFailedGrpHeuristicConflictSet().size(); i++) {
				hypothesis = this.getFailedGrpHeuristicConflictSet().get(i-1);
				for( int j = 1; j <= hypothesis.getPossibleSolutions().size(); j++) {
					pSolutions.add(hypothesis.getPossibleSolutions().get(j-1));
					this.sort(pSolutions);
				}
			}
			
			if (!(pSolutions.isEmpty()))
				this.setStatus(false);
		}
		
		return pSolutions;
	}
	
	/**
	 * Método de instancia agregado
	 * @param pSolutions
	 */
	private void sort(List<PossibleSolution> pSolutions) {
		Collections.sort(pSolutions,
				new Comparator<PossibleSolution>() {
					public int compare(PossibleSolution elem1, PossibleSolution elem2) {
						if ((elem2.getPoints()	- elem1.getPoints()) > 0)
							return 1;
						else if ((elem2.getPoints() - elem1.getPoints()) < 0)
							return -1;
						else return 0;
					}
				});
	}
}
