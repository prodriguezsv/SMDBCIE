/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import java.util.ArrayList;
import java.util.List;

import onthology.CBR.Hypothesis;
import onthology.CBR.PossibleSolution;
import onthology.taxonomy.Taxon;
import onthology.taxonomy.Taxonomy;


/**
 * @author Armando
 *
 */
public class PossibleSolutionEvaluator {
	private List<Hypothesis> failedGrpHeuristicConflictSet;
	private List<Hypothesis> failedStructConflictSet;
	private List<Hypothesis> successfulGrpHeuristicConflictSet;
	private List<Hypothesis> successfulStructConflictSet;
	private Taxonomy taxonomy;

	/**
	 * @see "Método initializeWith:and:and:and:and:and: del protocolo initializing en SUKIA SmallTalk"
	 */
	public PossibleSolutionEvaluator(List<Hypothesis> aSuccessfulStructList, List<Hypothesis> aFailedStructList, List<Hypothesis> aSuccessfulGrpHeuristicList,
			List<Hypothesis> aFailedGrpHeuristicList, Taxonomy aTaxonomy) {
		setSuccessfulStructConflictSet(aSuccessfulStructList);
		setFailedStructConflictSet(aFailedStructList);
		setSuccessfulGrpHeuristicConflictSet(aSuccessfulGrpHeuristicList);
		setFailedGrpHeuristicConflictSet(aFailedGrpHeuristicList);
		setTaxonomy(aTaxonomy);
	}
	
	/**
	 * @see "Método losePoints del protocolo de clase point-accummulatind scheme en SUKIA SmallTalk"
	 * @return
	 */
	public static String losePoints() {
		return "-";
	}
	
	/**
	 * @see "Método winPoints del protocolo de clase point-accummulatind scheme en SUKIA SmallTalk"
	 * @return
	 */
	public static String winPoints() {
		return "+";
	}

	/**
	 * Método de instancia agregado
	 * @param failedGrpHeuristicConflictSet
	 */
	public void setFailedGrpHeuristicConflictSet(
			List<Hypothesis> failedGrpHeuristicConflictSet) {
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
	 * @param successfulGrpHeuristicConflictSet
	 */
	public void setSuccessfulGrpHeuristicConflictSet(
			List<Hypothesis> successfulGrpHeuristicConflictSet) {
		this.successfulGrpHeuristicConflictSet = successfulGrpHeuristicConflictSet;
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
	public void setSuccessfulStructConflictSet(
			List<Hypothesis> successfulStructConflictSet) {
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
	 * Método de instancia agregado
	 * @param taxonomy
	 */
	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	/**
	 * @see "Método taxonomy del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxonomy getTaxonomy() {
		return taxonomy;
	}

	/**
	 * Conflict set evaluation process
	 * @see "Método evaluate del protocolo evaluating en SUKIA SmallTalk"
	 */
	public void evaluate() {
		// Step 1: Evaluate each conflict set individually
		this.evaluate(this.getSuccessfulStructConflictSet(), PossibleSolutionEvaluator.winPoints());
		this.evaluate(this.getFailedStructConflictSet(), PossibleSolutionEvaluator.winPoints());
		this.evaluate(this.getSuccessfulGrpHeuristicConflictSet(), PossibleSolutionEvaluator.winPoints());
		this.evaluate(this.getFailedGrpHeuristicConflictSet(), PossibleSolutionEvaluator.winPoints());

		// Step 2: Evaluate every conflict set against other conflict sets
		this.evaluate(this.getSuccessfulStructConflictSet(), this.getFailedStructConflictSet(), PossibleSolutionEvaluator.losePoints());
		this.evaluate(this.getSuccessfulStructConflictSet(), this.getSuccessfulGrpHeuristicConflictSet(), PossibleSolutionEvaluator.losePoints());
		this.evaluate(this.getSuccessfulStructConflictSet(), this.getFailedGrpHeuristicConflictSet(), PossibleSolutionEvaluator.losePoints());

		this.evaluate(this.getSuccessfulGrpHeuristicConflictSet(), this.getFailedStructConflictSet(), PossibleSolutionEvaluator.losePoints());
		this.evaluate(this.getSuccessfulGrpHeuristicConflictSet(), this.getFailedGrpHeuristicConflictSet(), PossibleSolutionEvaluator.losePoints());

		this.evaluate(this.getFailedStructConflictSet(), this.getFailedGrpHeuristicConflictSet(), PossibleSolutionEvaluator.losePoints());
	}
	
	/**
	 * The argument aConflictSet contains a set of Hypotheses, at least one of which may contain an empty set of 
	 * PossibleSolutions. The argument aCompConflictSet also contains a set of Hypotheses, at least one of which
	 * may also contain an empty set of PossibleSolutions.  All Hypotheses in either aConflictSet or aCompConflictSet
	 * are associated to descriptive elements of the same kind (either all Structures or all GroupingHeuristics).
	 * The idea behind this method is to evaluate the PossibleSolutions of one Hypothesis, in aConflictSet, against the
	 * PossibleSolutions of all the Hypotheses in aCompConflictSet, in order to a) reduce the total number of PossibleSolutions
	 * in aCompConflictSet (that is, in case two evaluated PossibleSolutions refer to the same object), and b) determine taxonomic
	 * hierarchical dependencies.  If two compared PossibleSolutions refer to the same object, the current one will inherit the
	 * compared-one's descriptions, and the compared one will be deleted from the corresponding Hypothesis. If the current
	 * PossibleSolution is successor of another one, the current one will inherit the other's descriptions.  In either case, the current
	 * PossibleSolution will  gain or lose one point, according to the criteria established by the argument aPointAccumulatingScheme.
	 * NOTE: 	The execution-time analysis of this program shows that it is rather inefficient; its many loops may cause the program
	 * to delay if the evaluated lists are large. For the time being (04-oct-1999) it's OK to leave at that, since the important
	 * issue is to test this logic and try to find ways to improve the PossibleSolution evaluation scheme.  It is convenient,
	 * though, to find a way to optimize this process as soon as the knowledge base starts having a considerable size.
	 * ASSUMPTIONS:
	 * 1. The set of PossibleSolutions of every Hypothesis has been previously compressed, by either the taxon search automaton,
	 * or the SAV case search automaton output.
	 * 2. The set of PossibleSolutions of every Hypothesisis ordered by taxonomic level, most specific level first.
	 * PRECONDITION: aConflictSet is not empty and aCompConflictSet is not empty.
	 * Returns: nil : If an error occurred (no corresponding taxon was found for a given SAVCase-based PossibleSolution
	 * self : If aConflicSet is empty, or the process ran OK
	 * @see "Método evaluate:against:winOrLose: del protocolo evaluating en SUKIA SmallTalk"
	 * @param aConflictSet
	 * @param aCompConflictSet
	 * @param aPointAccumulatingScheme
	 */
	public void evaluate(List<Hypothesis> aConflictSet, List<Hypothesis> aCompConflictSet, String aPointAccumulatingScheme) {
		List<Hypothesis> tempList;
		Hypothesis evalHypothesis, compHypothesis;
		PossibleSolution evalPossibleSolution, compPossibleSolution;
		Taxon evalPossibleSolutionTaxon, compPossibleSolutionTaxon;
		int k;
		
		// Check the precondition
		if (aConflictSet.isEmpty() || aCompConflictSet.isEmpty())
			return;

		tempList = new ArrayList<Hypothesis>();

		// Process while aConflicSet is not empty
		while (!(aConflictSet.isEmpty())) {
			// Remove the next hypothesis-to-evaluate from aConflictSet
			evalHypothesis = aConflictSet.remove(0);

			// Scan the entire list of possible solutions belonging to the current hypothesis-to-evaluate
			for( int i = 1; i <= evalHypothesis.getPossibleSolutions().size(); i++) {
				// Get the next possibleSolution-to-evaluate, which belongs to the current hypothesis-to-evaluate
				evalPossibleSolution = evalHypothesis.getPossibleSolutions().get(i-1);

				// Get the corresponding taxon of the possibleSolution-to-evaluate, if applicable
				if (evalPossibleSolution.getSolution() instanceof Taxon)
					evalPossibleSolutionTaxon = (Taxon) evalPossibleSolution.getSolution();
				else evalPossibleSolutionTaxon = this.getTaxonomy().getTaxonFromLevelIndex(evalPossibleSolution.getName(), evalPossibleSolution.getLevel());
				
				if (evalPossibleSolutionTaxon == null) return;
		
				// Scan all the hypotheses in aCompConflictSet
				for( int j = 1; j <= aCompConflictSet.size(); j++) {
					// Get the next hypothesis-to-compare from aConflictSet
					compHypothesis = aCompConflictSet.get(j-1);

					// Scan the entire list of possible solutions belonging to the current hypothesis-to-compare"
					k = 1;
					while (k <= compHypothesis.getPossibleSolutions().size()) {
						// Get the next possibleSolution-to-comapre, which belongs to the current hypothesis-to-compare
						compPossibleSolution = compHypothesis.getPossibleSolutions().get(k-1);

						if (compPossibleSolution.getSolution() instanceof Taxon)
							compPossibleSolutionTaxon = (Taxon) compPossibleSolution.getSolution();
						else compPossibleSolutionTaxon = this.getTaxonomy().getTaxonFromLevelIndex(compPossibleSolution.getName(), compPossibleSolution.getLevel());
						
						if (compPossibleSolutionTaxon == null) return;

						// Check if the possible solutions are the same object
						if (evalPossibleSolutionTaxon == compPossibleSolutionTaxon) {
							if (aPointAccumulatingScheme.equals(PossibleSolutionEvaluator.winPoints())) {
								// Inherit the compare solution's descriptions and remove it from the hypothesis-to-compare possibleSolutions list
								this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
								evalPossibleSolution.incrementPoints();
							} else {
								// AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
								evalPossibleSolution.incrementPointsBy(-1);
							}

							compHypothesis.getPossibleSolutions().remove(k-1);
						} else {
							// At this point, evalPossibleSolutionTaxon and compPossibleSolutionTaxon are different objects
							// Determine if the possibleSolution-to-evaluate is a successor taxon of the possibleSolution-to-compare
							if (evalPossibleSolutionTaxon.isSuccessorOf(compPossibleSolutionTaxon)) {
								if (aPointAccumulatingScheme.equals(PossibleSolutionEvaluator.winPoints())) {
									this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
									evalPossibleSolution.incrementPoints();
								} else {
									// AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
									evalPossibleSolution.incrementPointsBy(-1); 
								}
							}
						}

						k = k + 1;
					}
				}
			}
			tempList.add(evalHypothesis);
			evalHypothesis = null;
		}
		
		while (!(tempList.isEmpty()))
				aConflictSet.add(tempList.remove(0));
	}
	
	/**
	 * The argument aConflictSet contains a set of Hypotheses, each of which initially contains a non-empty set of PossibleSolutions.
	 * All Hypotheses are associated to descriptive elements of the same kind (either all Structures or all GroupingHeuristics).
	 * The idea behind this method is to evaluate the PossibleSolutions of one Hypothesis against the PossibleSolutions of all
	 * other Hypotheses, in order to a) reduce the total number of PossibleSolutions in aConflictSet (that is, in case two evaluated
	 * PossibleSolutions refer to the same object), and b) determine taxonomic hierarchical dependencies.  If two compared
	 * PossibleSolutions refer to the same object, the current one will inherit the compared-one's descriptions, and the compared
	 * one will be deleted from the corresponding Hypothesis. If the current PossibleSolution is successor of another one, the
	 * current one will inherit the other's descriptions.  In either case, the current PossibleSolution will  gain or lose one point,
	 * according to the criteria established by the argument aPointAccumulatingScheme.
	 * NOTE: 	The execution-time analysis of this program shows that it is rather inefficient; its many loops may cause the program
	 * to delay if the evaluated lists are large. For the time being (04-oct-1999) it's OK to leave at that, since the important
	 * issue is to test this logic and try to find ways to improve the PossibleSolution evaluation scheme.  It is convenient,
	 * though, to find a way to optimize this process as soon as the knowledge base starts having a considerable size.
	 * ASSUMPTIONS:
	 * 1. The set of PossibleSolutions of every Hypothesis has been previously compressed, by either the taxon search automaton,
	 * or the SAV case search automaton output.
	 * 2. The set of PossibleSolutions of every Hypothesisis ordered by taxonomic level, most specific level first.
	 * PRECONDITION: aConflictSet is not empty.
	 * POSTCONDITION: aConflictSet is not empty and at least one Hypothesis MAY contain an empty set of PossibleSolutions.
	 * Returns: nil : If an error occurred (no corresponding taxon was found for a given SAVCase-based PossibleSolution
	 * self : If aConflicSet is empty, or the process ran OK
	 * @see "Método evaluate:winOrLose: del protocolo evaluating en SUKIA SmallTalk"
	 * @param aConflictSet
	 * @param aPointAccumulatingScheme
	 */
	public void evaluate(List<Hypothesis> aConflictSet, String aPointAccumulatingScheme) {
		List<Hypothesis> tempList;
		Hypothesis evalHypothesis, compHypothesis;
		PossibleSolution evalPossibleSolution, compPossibleSolution;
		Taxon evalPossibleSolutionTaxon, compPossibleSolutionTaxon;
		int k;
		
		// Check the precondition
		if (aConflictSet.isEmpty())
			return;

		tempList = new ArrayList<Hypothesis>();

		// Process while aConflicSet size has at least two hypotheses
		while (aConflictSet.size() > 1) {
			// Remove the next hypothesis-to-evaluate from aConflictSet
			evalHypothesis = aConflictSet.remove(0);

			// Scan the entire list of possible solutions belonging to the current hypothesis-to-evaluate
			for( int i = 1; i <= evalHypothesis.getPossibleSolutions().size(); i++) {
				// Get the next possibleSolution-to-evaluate, which belongs to the current hypothesis-to-evaluate
				evalPossibleSolution = evalHypothesis.getPossibleSolutions().get(i-1);

				// Get the corresponding taxon of the possibleSolution-to-evaluate, if applicable
				if (evalPossibleSolution.getSolution() instanceof Taxon)
					evalPossibleSolutionTaxon = (Taxon) evalPossibleSolution.getSolution();
				else evalPossibleSolutionTaxon = this.getTaxonomy().getTaxonFromLevelIndex(evalPossibleSolution.getName(), evalPossibleSolution.getLevel());
				
				if (evalPossibleSolutionTaxon == null) return;
				
				// Scan all the hypotheses in aCompConflictSet
				for( int j = 1; j <= aConflictSet.size(); j++) {
					// Get the next hypothesis-to-compare from aConflictSet
					compHypothesis = aConflictSet.get(j-1);

					// Scan the entire list of possible solutions belonging to the current hypothesis-to-compare"
					k = 1;
					while (k <= compHypothesis.getPossibleSolutions().size()) {
						// Get the next possibleSolution-to-comapre, which belongs to the current hypothesis-to-compare
						compPossibleSolution = compHypothesis.getPossibleSolutions().get(k-1);

						if (compPossibleSolution.getSolution() instanceof Taxon)
							compPossibleSolutionTaxon = (Taxon) compPossibleSolution.getSolution();
						else compPossibleSolutionTaxon = this.getTaxonomy().getTaxonFromLevelIndex(compPossibleSolution.getName(), compPossibleSolution.getLevel());
						
						if (compPossibleSolutionTaxon == null) return;

						// Check if the possible solutions are the same object
						if (evalPossibleSolutionTaxon == compPossibleSolutionTaxon) {
							if (aPointAccumulatingScheme.equals(PossibleSolutionEvaluator.winPoints())) {
								// Inherit the compare solution's descriptions and remove it from the hypothesis-to-compare possibleSolutions list
								this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
								evalPossibleSolution.incrementPoints();
							} else {
								// AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
								evalPossibleSolution.incrementPointsBy(-1);
							}

							compHypothesis.getPossibleSolutions().remove(k-1);
						} else {
							// At this point, evalPossibleSolutionTaxon and compPossibleSolutionTaxon are different objects
							// Determine if the possibleSolution-to-evaluate is a successor taxon of the possibleSolution-to-compare
							if (evalPossibleSolutionTaxon.isSuccessorOf(compPossibleSolutionTaxon)) {
								if (aPointAccumulatingScheme.equals(PossibleSolutionEvaluator.winPoints())) {
									this.inheritPossibleSolutionDescriptionsFrom(compPossibleSolution, evalPossibleSolution);
									evalPossibleSolution.incrementPoints();
								} else {
									// AQUI SE DEBE PONER LAS DESCRIPCIONES EN LA JUSTIFICACION, NO HEREDARLAS
									evalPossibleSolution.incrementPointsBy(-1); 
								}
							}
						}

						k = k + 1;
					}
				}
			}
			tempList.add(evalHypothesis);
			evalHypothesis = null;
		}
		
		while (!(tempList.isEmpty()))
				aConflictSet.add(tempList.remove(0));
	}
	
	/**
	 * @see "Método inheritPossibleSolutionDescriptionsFrom:to: del protocolo inheritance en SUKIA SmallTalk"
	 * @param anOldPossibleSolution
	 * @param aNewPossibleSolution
	 */
	public void inheritPossibleSolutionDescriptionsFrom(PossibleSolution anOldPossibleSolution, PossibleSolution aNewPossibleSolution) {
		aNewPossibleSolution.appendToSolutionDescription(anOldPossibleSolution.getSolutionDescription());
		aNewPossibleSolution.appendToConfirmedDescription(anOldPossibleSolution.getConfirmedDescription());
		aNewPossibleSolution.appendToUnconfirmedDescription(anOldPossibleSolution.getUnconfirmedDescription());
		aNewPossibleSolution.appendToDoubtfulDescription(anOldPossibleSolution.getDoubtfulDescription());
	}
}
