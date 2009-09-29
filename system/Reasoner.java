/**
 * 
 */
package system;

import java.util.ArrayList;
import java.util.List;

import ontology.CBR.Hypothesis;
import ontology.CBR.PossibleSolution;
import ontology.CBR.ProposedSolution;
import ontology.CBR.SimilarityDegree;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;

import redundantDiscriminationNet.RDMultiNet;
import redundantDiscriminationNet.RDNet;
import redundantDiscriminationNet.RootNorm;
import searchHintsBase.HintsBase;
import system.searchAutomata.GoalApproachingDialog;
import system.searchAutomata.CaseMemoryDFSAutomaton;
import system.searchAutomata.SearchStatus;
import system.searchAutomata.TaxonomySearchAutomaton;
import system.searchAutomata.output.CaseMemoryDFSAutomatonOutput;

/**
 * @author Armando
 *
 */
public class Reasoner {
	private Description caseMemorySearchJustification;
	private Description taxonHierarchySearchJustification;
	private Description routeSelectJustification;
	private Description description;
	private List<Hypothesis> successfulConflictSet;
	private List<Hypothesis> failureConflictSet;
	private List<Hypothesis> noResultsSet;
	private List<ProposedSolution> proposedSolutions;
	private TaxonomicRank identGoal;
	private OracleIDApplication identSystem;
	private int maxNumberSolutions;
	private SimilarityDegree minSimilarityDegree;
	private boolean presentFailedSolutions;
	
	/**
	 * identSystem will be associated to the Sukia system object. Through this object, access is
	 * gained main data structures: the memory case, the taxonomic hierarchy, and the hints base
	 * @see "Método initialize: del protocolo initializing en SUKIA SmallTalk"
	 */
	public Reasoner(OracleIDApplication anIdentificationSystem) {
		setIdentSystem(anIdentificationSystem);

		this.initialize();
	}
	
	/**
	 * Initializes all instance variables needed for the identification process.
	 * @see "Método initialize: del protocolo initializing en SUKIA SmallTalk"
	 */
	public void initialize() {
		// The identification goal. Default value: species
		setIdentGoal(TaxonomicRank.getMostSpecificLevel());

		// USER EXPECTATION: Maximum number of solutions to present. Default value: 3
		setMaxNumberSolutions(3);

		/* USER EXPECTATION: Show failed cases, in case no successful ones are available
		 Default: true (i.e., show them)*/
		setPresentFailedSolutions(true);

		/* USER EXPECTATION: Minimal similarity degree used in comparisons. 
		 Default: moderately similar to equal*/
		this.setMinSimilarityDegree(SimilarityDegree.MEDIANAMENTESIMILAR);
		//if (this.getMinSimilarityDegree().isEmpty()) return;

		// List with Structures that constitute the morphological description given by the user
		setDescription(new Description());


		/* CONFLICT SET: Hypotheses that contain successful possible solutions (i.e., positive cases or taxa) 
		 for a given Structure belonging to the problem description*/
		setSuccessfulConflictSet(new ArrayList<Hypothesis>());

		/*CONFLICT SET: Hypothesis that contain failed possible solutions (i.e., negative cases only) for a given 
		 Structure belonging to the problem description*/
		setFailureConflictSet(new ArrayList<Hypothesis>());

		/* CONFLICT SET: Hypothesis that do not contain any possible solutions.  These Hypotheses are associated
		 to either Structures or GroupingHeuristics for which the search was unsuccessful*/
		setNoResultsSet(new ArrayList<Hypothesis>());

		// Set of solutions to present to the user
		setProposedSolutions(new ArrayList<ProposedSolution>());

		// Justification list: route selection (using the HintsBase) 
		setRouteSelectJustification(new Description());

		// Justification list: case memory search 
		setCaseMemorySearchJustification(new Description());

		// Justification list: taxonomic hierarchy serach
		setTaxonHierarchyJustification(new Description());
	}

	/**
	 * Método de instancia agregado
	 * @param caseMemorySearchJustification
	 */
	public void setCaseMemorySearchJustification(Description caseMemorySearchJustification) {
		this.caseMemorySearchJustification = caseMemorySearchJustification;
	}
	
	/**
	 * @see "Método caseMemorySearchJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationItem
	 */
	public void addToCaseMemoryJustification(Descriptor aJustificationItem) {
		this.getCaseMemoryJustification().add(aJustificationItem);
	}

	/**
	 * @see "Método caseMemorySearchJustification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getCaseMemoryJustification() {
		return caseMemorySearchJustification;
	}

	/**
	 * Método de instancia agregado
	 * @param failStructConflictSet
	 */
	public void setFailureConflictSet(List<Hypothesis> failStructConflictSet) {
		this.failureConflictSet = failStructConflictSet;
	}
	
	/**
	 * @see "Método failStructConflictSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addFailureConflictSet(Hypothesis aHypothesis) {
		this.getFailureConflictSet().add(aHypothesis);
	}

	/**
	 * @see "Método failStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getFailureConflictSet() {
		return failureConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param groupHDescription
	 */
	public void setDescription(Description description) {
		this.description = description;
	}
	
	/**
	 * @see "Método groupHDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public boolean addToDescription(Descriptor descriptor) {
		return this.getDescription().addToConcreteDescription(descriptor);
	}

	/**
	 * @see "Método groupHDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * @see "Método identGoal: del protocolo adding en SUKIA SmallTalk"
	 * @param identGoal
	 */
	public void setIdentGoal(TaxonomicRank aTaxonomicLevelsValue) {
		this.identGoal = aTaxonomicLevelsValue;
	}

	/**
	 * @see "Método identGoal del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public TaxonomicRank getIdentGoal() {
		return identGoal;
	}

	/**
	 * Método de instancia agregado
	 * @param identSystem
	 */
	public void setIdentSystem(OracleIDApplication identSystem) {
		this.identSystem = identSystem;
	}

	/**
	 * @see "Método identSystem del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public OracleIDApplication getIdentSystem() {
		return identSystem;
	}

	/**
	 * @see "Método maxNumberSolutions: del protocolo adding en SUKIA SmallTalk"
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
	 * @see "Método minSimilarityDegree: del protocolo adding en SUKIA SmallTalk"
	 * @param aSimRangesValue
	 */
	public void setMinSimilarityDegree(SimilarityDegree aSimRangesValue) {
		minSimilarityDegree = aSimRangesValue;
	}

	/**
	 * @see "Método minSimilarityDegree del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public SimilarityDegree getMinSimilarityDegree() {
		return minSimilarityDegree;
	}

	/**
	 * Método de instancia agregado
	 * @param noResultsSet
	 */
	public void setNoResultsSet(List<Hypothesis> noResultsSet) {
		this.noResultsSet = noResultsSet;
	}

	/**
	 * @see "Método noResultsSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addNoResultsSet(Hypothesis aHypothesis) {
		this.getNoResultsSet().add(aHypothesis);
	}
	
	/**
	 * @see "Método noResultsSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getNoResultsSet() {
		return noResultsSet;
	}

	/**
	 * @see "Método presentFailedSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param presentFailedSolutions
	 */
	public void setPresentFailedSolutions(boolean presentFailedSolutions) {
		this.presentFailedSolutions = presentFailedSolutions;
	}

	/**
	 * @see "Método presentFailedSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isPresentFailedSolutions() {
		return presentFailedSolutions;
	}

	/**
	 * @see "Método proposedSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param proposedSolutions
	 */
	public void setProposedSolutions(List<ProposedSolution> proposedSolutions) {
		this.proposedSolutions = proposedSolutions;
	}

	/**
	 * @see "Método proposedSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<ProposedSolution> getProposedSolutions() {
		return proposedSolutions;
	}

	/**
	 * Método de instancia agregado
	 * @param routeSelectJustification
	 */
	public void setRouteSelectJustification(Description routeSelectJustification) {
		this.routeSelectJustification = routeSelectJustification;
	}
	
	/**
	 * @see "Método routeSelectJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationItem
	 */
	public void addToRouteSelectJustification(Descriptor aJustificationItem) {
		this.getRouteSelectJustification().addToConcreteDescription(aJustificationItem);
	}

	/**
	 * @see "Método routeSelectJustification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getRouteSelectJustification() {
		return routeSelectJustification;
	}
	
	/**
	 * Método de instancia agregado
	 * @param succStructConflictSet
	 */
	public void setSuccessfulConflictSet(List<Hypothesis> succStructConflictSet) {
		this.successfulConflictSet = succStructConflictSet;
	}
	
	/**
	 * @see "Método succGHConflictSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addSuccessfulConflictSet(Hypothesis aHypothesis) {
		this.getSuccessfulConflictSet().add(aHypothesis);
	}

	/**
	 * @see "Método succStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getSuccessfulConflictSet() {
		return successfulConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param aJustification
	 */
	public void setTaxonHierarchyJustification(Description aJustification) {
		this.taxonHierarchySearchJustification = aJustification;
	}
	
	/**
	 * @see "Método routeSelectJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationItem
	 */
	public void addToTaxonHierarchyJustification(Descriptor aJustificationItem) {
		this.getTaxonHierarchyJustification().addToConcreteDescription(aJustificationItem);
	}

	/**
	 * @see "Método taxonHierarchySearchJustification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getTaxonHierarchyJustification() {
		return taxonHierarchySearchJustification;
	}
	
	/**
	 * @see "Método caseMemory del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public RDMultiNet getCaseMemory() {
		return this.getIdentSystem().getCaseMemory();
	}
	
	/**
	 * @see "Método hintsBase del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public HintsBase getHintsBase() {
		return this.getIdentSystem().getHintsBase();
	}
	
	/**
	 * @see "Método taxonomy del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxonomy getTaxonomy() {
		return this.getIdentSystem().getTaxonomy();
	}
	
	/**
	 * @see "Método taxonomicGroupName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonomicGroupName() {
		return this.getIdentSystem().getTaxonomy().getRootTaxon().getName();
	}

	/**
	 * Evaluation of all possible solutions belonging to Hypotheses stored in all conflict sets
	 * @see "Método evaluatePossibleSolutions del protocolo evaluating and selecting en SUKIA SmallTalk"
	 */
	public boolean evaluatePossibleSolutions() {
		PossibleSolutionEvaluator evaluator;
		
		evaluator = new PossibleSolutionEvaluator(this.getSuccessfulConflictSet(), this.getFailureConflictSet(),
				this.getTaxonomy());
	
		evaluator.evaluate();
		
		return true;
	}
	
	/**
	 * Selection of the best possible solutions, as proposed solutions for an identification session.
	 * @see "Método selectSolutions del protocolo evaluating and selecting en SUKIA SmallTalk"
	 */
	public List<ProposedSolution> selectSolutions() {
		PossibleSolutionSelector selector;
		
		selector = new PossibleSolutionSelector(this.getIdentGoal(), this.getSuccessfulConflictSet(), 
				this.getFailureConflictSet(), this.getMaxNumberSolutions(), this.isPresentFailedSolutions());

		return selector.select();
	}
	
	/**
	 * Main driver for the Reasoner. The first thing is to do is to read in a problem description (structure
	 * and grouping heuristic descriptions), along with the identification goal and all the user-defined
	 * system behavior expectations.  Once the problem description is obtanied, the HintsBase is
	 * consulted, in order to arrange the descriptive elements (i.e., Structures and/or GroupingHeuristics)
	 * in a search-effective way. Next, if Sukia has a case memory, it will be used as first resort to begin
	 * the problem solution process. If no case memory is available, the taxonomic hierarchy is used
	 * instead. Once the search process is done, the other two processes left are the possible solution
	 * evaluation and the solution(s) selection.
	 * Precondition: (at least the taxonomy MUST be available)
	 * Returns: nil : if the precondition is not met, OR any involved of the processes fails.
	 * self : if the process runs OK. This doesn't mean that the problem is solved, because the
	 * empty solution is a valid response.
	 * @see "Método identify del protocolo evaluating and selecting en SUKIA SmallTalk"
	 * @return
	 */
	public boolean identify() {
		// Check the precondition
		if (this.getTaxonomy().getRootTaxon().getSuccessors().isEmpty())
			return false;

		/* Get a problem description from the user. The process may continue if either the Structure-based or 
		 GroupingHeuristic-based descriptions is NOT empty*/
		if (this.getProblemDescription() == null)
			return false;

		// Use the HintsBase with the Structure-based description
		if (this.useIndexHintsForDescription() == false)
			return false;

		// First choice: use the case memory primary search data structure
		if (!(this.getCaseMemory().getRoot().getNets().isEmpty())) {
			if (this.useCaseMemory() == false) return false;
		} else {
			// Second choice: use the taxonomic hierarchy as primary search data structure
			if (this.useTaxonomicHierarchy() == false) return false;
		}

		// If there are no available possible solutions, exit
		if (this.getSuccessfulConflictSet().isEmpty() && this.getFailureConflictSet().isEmpty())
			return false;

		// Evaluate all possible solutions
		if (this.evaluatePossibleSolutions() == false)
			return false;

		// Select the proposed solutions
		this.setProposedSolutions(this.selectSolutions());

		// Return OK
		return true;
	}
	
	/**
	 * Reads the description given by the user. The following instance variables are updated at this point:
	 * 1) identGoal: the identification goal; a taxonomic level defined in TaxonomicLevels.
	 * 2) maxNumberSolutions: maximum number of solutions that the user wants to see.
	 * 3) presentFailedSolutions: true if the user wants to inspect cases previously solved incorrectly.
	 * 4) minSimilarityDegree: minimal similarity degree that user expects the system to consider when 
	 * comparing values (see SimRanges).
	 * 5) structDescription: problem description in terms of Structures.
	 * 6) groupHDescription: problem description in terms of GroupingHeuristics.
	 * For now (8-Oct-1999), this method will not enter a dialog with the user. The before-mentioned
	 * variables will be updated via method calls (see the adding category) from one of Smalltalk's
	 * workspace screens.  Once this method is developed, it must satisfy the following conditions:
	 * -Aceptar una meta de identificación:el nombre de un nivel taxonómico
	 * -Aceptar la descripción del especímen a identificar: : una lista de Estructuras y Heurísticas de
	 * agrupamiento.
	 * Condiciones que debe cumplir la descripción
	 * i) La descripción no puede tener dos Estructuras con el mismo nombre.
	 * ii) Las Estructuras pueden tener asignado un peso entre 0.0 y 1.0, el cual se interpreta como el grado
	 * de interés que el usuario muestra por la estructura descrita.
	 * iii) Cada Estructura debe tener al menos un atributo.
	 * iv) Ninguna Estructura puede tener dos atributos con el mismo nombre.
	 * v) El atributo de una Estructura debe tener solo un valor.
	 * vi) El valor de un atributo debe ser ByteSymbol o Número (no rango).
	 * vii) El valor de un atributo puede ser obtenido de la lista de valores definidos para ese atributo,
	 * o ser un valor nuevo.
	 * viii) El valor de un atributo puede tener un peso asignado, el cual se interpreta como 'el grado de 
	 * certidumbre del usuario': 0.0 -- no tiene grado de certidumbre asignado; 0.5 - el usuario no está seguro
	 * del valor brindado; 1.0 - el usuario está eguro del valor brindado.
	 * ix) La descripción no puede tener dos Heurísticas de Agrupamiento con el mismo nombre
	 * x) Las Heurísticas de Agrupamiento pueden tener asignado un peso entre 0.0 y 1.0, el cual se 
	 * interpreta como 'el grado de interés que el usuario muestra por la heurística de agrupamiento descrita'.
	 * xi) Cada Heurística de Agrupamiento debe tener solo un valor.
	 * xii) El valor de una Heurística de Agrupamiento debe ser ByteSymbol o Número (no rango).
	 * xiii) El valor de una Heurística de Agrupamiento puede ser obtenido de la lista de valores definidos
	 * para esa Heurística de Agrupamiento, o ser un valor nuevo.
	 * xiv) El valor de una Heurística de Agrupamiento puede tener un peso asignado, interpretado como 
	 * 'el grado de certidumbre del usuario': 0.0 -- no tiene grado de certidumbre asignado; 0.5 - el usuario
	 * no está eguro del valor brindado; 1.0 - el usuario está seguro del valor brindado.
	 * @see "M&eacute;todo getProblemDescription del protocolo private-general en SUKIA SmallTalk"
	 * @return
	 */
	private Object getProblemDescription() {
		return this;
	}
	
	/**
	 * @see "M&eacute;todo useCaseMemory del protocolo private-general en SUKIA SmallTalk"
	 * @return
	 */
	private boolean useCaseMemory() {
		if (this.searchHypothesisInCaseMemory() == false)
			return false;

		return true;
	}
	
	/**
	 * @see "M&eacute;todo useCaseMemory del protocolo private-general en SUKIA SmallTalk"
	 * @return
	 */
	private boolean useTaxonomicHierarchy() {
		if (this.searchHypothesisInTaxonomy() == false)
			return false;

		return true;
	}
	
	/**
	 * PRECONDITION: (anOutputCopy possible solutions list is NOT empty)
	 * RETURNS:	nil - if the precondition is not met, OR an error occurred.
	 * self - if all OK
	 * @param searchAutomaton
	 * @param hypothesis1
	 * @param hypothesis2
	 * @return
	 */
	private boolean processSuccessfulSearchOutput(CaseMemoryDFSAutomatonOutput anOutputCopy, 
			Hypothesis hypothesis1, Hypothesis hypothesis2) {
		Hypothesis currHypothesis;
		PossibleSolution ps;
		GoalApproachingDialog dialog;
		int index;
		SearchStatus status;
	
		// Check the precondition
		if (anOutputCopy.getPossibleSolutions().isEmpty())
			return false;

		// Successful search: at least one case was found. Scan the output copy's possible solution's list
		while (!(anOutputCopy.getPossibleSolutions().isEmpty())) {
			// Get the next possible solution from the search automaton's output
			ps = anOutputCopy.getPossibleSolutions().remove(0);

			/*Attempt to add the possible solution to the first hypothesis. If not successful, attempt
			to add it to the second hypothesis. If unsuccessful again, return an error value. At the 
			end of this loop, the following assertion will always hold: hypothesis1 will never be empty
			(it will always have at least ONE element), and hypothesis2 may be empty*/
			if (hypothesis1.addPossibleSolution(ps) == false)
				if (hypothesis2.addPossibleSolution(ps) == false) return false;
		}
		
		// Once all possible solutions have been loaded, copy the search automaton's unmatched description
		if (!(hypothesis1.getPossibleSolutions().isEmpty())) {
			hypothesis1.addAllToUnmatchedDescription(anOutputCopy.getUnmatchedDescription());
			hypothesis1.addAllToJustification(anOutputCopy.getJustification());
		}

		if (!(hypothesis2.getPossibleSolutions().isEmpty())) {
			hypothesis2.addAllToUnmatchedDescription(anOutputCopy.getUnmatchedDescription());
			hypothesis2.addAllToJustification(anOutputCopy.getJustification());
		}
		
		/* HYPOTHESIS1: If none of the possible solutions is equal to, or more specific than
		 the identification goal, establish a new dialog with the user, in order to try to draw
		 the existing possible solutions nearer to the said goal*/
		if (hypothesis1.getPossibleSolutions().get(0).getStatus() == true) {
			if (TaxonomicRank.getIndex(hypothesis1.getPossibleSolutions().get(0).getLevel()) <
					 TaxonomicRank.getIndex(this.getIdentGoal())) {
				dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis1, this.getTaxonomy(), this.getMinSimilarityDegree());
				dialog.chat();
				status = dialog.getStatus();
				if (status == SearchStatus.ERROR || status == SearchStatus.CANCEL)
					return false;
			}
		} else {
			// hypothesis1 does not contain positive cases
			/* HYPOTHESIS2: If none of the possible solutions is equal to, or more specific than
			 the identification goal, establish a new dialog with the user, in order to try to draw
		 	the existing possible solutions nearer to the said goal*/
			// OJO:hypothesis2.isEmpty() en SUKIA
			if (!(hypothesis2.getPossibleSolutions().isEmpty())) {
				if (TaxonomicRank.getIndex(hypothesis2.getPossibleSolutions().get(0).getLevel()) <
						 TaxonomicRank.getIndex(this.getIdentGoal())) {
					dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis2, this.getTaxonomy(), this.getMinSimilarityDegree());
					dialog.chat();
					status = dialog.getStatus();
					if (status == SearchStatus.ERROR || status == SearchStatus.CANCEL)
						return false;
				}
			}
		}
		
		// Finally, add the hypotheses to conflict sets
		index = 1;
		currHypothesis = hypothesis1;
		
		while (index <= 2) {
			if (!(currHypothesis.getPossibleSolutions().isEmpty())) {
				if (currHypothesis.getPossibleSolutions().get(0).getStatus() == true)
					this.addSuccessfulConflictSet(currHypothesis);
				else {
					if (currHypothesis.getPossibleSolutions().get(0).getStatus() == false)
						this.addFailureConflictSet(currHypothesis);
					else return false;
				}
			}
			
			currHypothesis = hypothesis2;
			index = index + 1;
		}

		return true;
	}
	
	/**
	 * Use the structure description as search data.
	 * RETURNS: nil - If an error occurred, or the user cancelled the search process.
	 * self - OK
	 * @return
	 */
	public boolean searchHypothesisInCaseMemory() {
		String s;
		Hypothesis hypothesis1, hypothesis2;
		List<Descriptor> problemDescription;
		RDNet net;
		RootNorm caseNetRoot = null;
		CaseMemoryDFSAutomaton searchAutomaton;
		CaseMemoryDFSAutomatonOutput outputCopy;
		SearchStatus status;
		List<String> structureList;
		
		structureList = this.getStructuresList();
		
		while (!(structureList.isEmpty())) {
			// Remove the next grouping heuristic from the description
			s = structureList.remove(0);

			// Create a first instance of Hypothesis and assign the structure as descriptive element
			hypothesis1 = new Hypothesis();
			hypothesis1.setDescription(this.getDescription(s));

			// Create a second instance of hypothesis, and again, assign the same structure as descriptive element
			hypothesis2 = new Hypothesis();
			hypothesis2.setDescription(this.getDescription(s));
			
			// Get the SAV problem description from the structure. If no description available, return error value
			problemDescription = this.getDescription(s);
			if (problemDescription == null) return false;
			
			// Get the net root that corresponds to the structure
			net = this.getCaseMemory().getRoot().getRDNet(s);
			if (net == null) 
				caseNetRoot = null;
			else caseNetRoot = net.getRoot();
					
			if (!(caseNetRoot == null)) {
				// Create a new instance of case net search automaton
				searchAutomaton = new CaseMemoryDFSAutomaton(caseNetRoot);
	
				// Begin the search with the given problem description
				searchAutomaton.beginSearch(problemDescription);
				status = searchAutomaton.getStatus();
	
				if (status == SearchStatus.ERROR || status == SearchStatus.CANCEL)
					return false;
	
				/* If the automaton returns a non-empty problem description list, then the REASONER MUST call 
				 it again with that remaining description. Before doing so, all lists, except the doubtful and unconfirmed 
				 ones, MUST be flushed. Make sure the new search begins at root level, and all necessary control variables 
				 are correctly set. This process repeats until the problem description list is EMPTY. After each successive 
				 call, the unmatched description, the justification and whatever possible solutions MUST be concatenated 
				 to the original search-automaton output copy*/
				outputCopy = searchAutomaton.getSearchOutput();
				outputCopy.setTaxonomy(this.getTaxonomy());
				
				while (!(problemDescription.isEmpty())) {
					searchAutomaton.beginNewSearch(problemDescription);
					status = searchAutomaton.getStatus();

					if (status == SearchStatus.ERROR || status == SearchStatus.CANCEL)
						return false;

					outputCopy.addAllToPossibleSolutions(searchAutomaton.getSearchOutput().getPossibleSolutions());
					outputCopy.addAllToJustification(searchAutomaton.getSearchOutput().getJustification());
					outputCopy.addAllToUnmatchedDescription(searchAutomaton.getSearchOutput().getUnmatchedDescription());
				}
				
				outputCopy.compressPossibleSolutions();

				if (!(outputCopy.getPossibleSolutions().isEmpty())) {
					if (this.processSuccessfulSearchOutput(outputCopy, hypothesis1, hypothesis2) == false)
						return false;

					continue;
				}
				
				/* At this point, status is neither #error, #cancel, nor #success (this value is implied by the
				 condition (outputCopy possibleSolutions isEmpty) ifFalse:. Thus, is must be #fail. Copy 
				 the unmatched description and the justification to the first hypothesis, which is the one
			   	 we'll continue to use from now on.*/
				hypothesis1.addAllToUnmatchedDescription(outputCopy.getUnmatchedDescription());
				hypothesis1.addAllToJustification(outputCopy.getJustification());
			}
		
			/* At this point, either: a) no net root was found, or b) the status of the net search was unsuccessful 
			 (i.e., status = #fail). Try doing a taxonomic search*/
	
			if (searchInTaxonomyByStructure(s, hypothesis1) == false)
				return false;
		}
	
		return true;
	}
	
	/**
	 * This method uses the HintsBase lists, and tries to arrange the Structure description in a
	 * search-efective way (i.e., the Structures at the beginning of the list are the ones with more
	 * possibilities to find positive solution cases).  For now (08-Oct-1999):
	 * a) 	If the percentage of items successfully processed is greater than zero, then it suffices.
	 * It is convenient, though, to device a way to determine if certain percentage is acceptable
	 * or not.
	 * b) 	The (hb failedFrequentStructurePatternList) will not be used.
	 * RETURNS: self.
	 */
	private boolean useIndexHintsForDescription() {
		HintsBase hb;
		
		hb = this.getHintsBase();

		if (!(hb.getWeightedPatternsbyStructureList().sortByMeanWeightCriteria(this.getDescription()).isEmpty()))
			if (hb.getWeightedPatternsbyStructureList().getPercentageItemsProcessed() > 0.0) return true;
		
		if (!(hb.getPatternsbyStructureList().sortBySuccessFrecuencyCriteria(this.getDescription()).isEmpty()))
			if (hb.getPatternsbyStructureList().getPercentageItemsProcessed() > 0.0) return true;

		List<String> structureList = this.getStructuresList();
		
		for( String s: structureList) {
			hb.getSpecificPatternsbyStructureList().resetPercentageItemsProcessed();

			hb.getSpecificPatternsbyStructureList().sortBySuccessCriteria(this.getDescription(s));
		}
		
		return true;
	}
	
	/**
	 * Performs  a search in the taxonomic hierarchy, using a set of structures
	 * as input data.
	 * RETURNS: nil - if an error occurs, OR the user cancels the search process.
	 * self - OK
	 */
	private boolean searchHypothesisInTaxonomy() {
		String s;
		Hypothesis hypothesis;
		List<String> structureList;
		
		structureList = this.getStructuresList();
		
		while (!(structureList.isEmpty())) {
			// Remove the next structure from the description
			s = structureList.remove(0);
			
			// Create a first instance of Hypothesis and assign the grouping heuristic as descriptive element
			hypothesis = new Hypothesis();
			hypothesis.setDescription(this.getDescription(s));

			if (searchInTaxonomyByStructure(s, hypothesis) == false)
				return false;
		}
			
		return true;
	}
	
	protected boolean searchInTaxonomyByStructure(String s, Hypothesis hypothesis) {
		List<Descriptor> problemDescription;
		SearchStatus status;
		TaxonomySearchAutomaton searchAutomaton;
		GoalApproachingDialog dialog;
		
		// Get the SAV problem description from the structure
		problemDescription = this.getDescription(s);
		if (problemDescription == null) return false;
		
		// Perform a taxonomic search
		searchAutomaton = new TaxonomySearchAutomaton(this.getTaxonomy(), this.getMinSimilarityDegree());
		searchAutomaton.beginSearch(problemDescription);
		status = searchAutomaton.getStatus();

		if (status == SearchStatus.ERROR || status == SearchStatus.CANCEL)
			return false;
						
		if (status == SearchStatus.SUCCESS) {
			// Load all the possible solutions into the hypothesis
			while (!(searchAutomaton.getSearchOutput().getPossibleSolutions().isEmpty())) {
				/* Attempt to add the possible solution to the hypothesis. If not successful, return 
				an error value*/
				 if (hypothesis.addPossibleSolution(searchAutomaton.getSearchOutput().getPossibleSolutions().remove(0)) == false)
					 return false;
			}
			
			hypothesis.addAllToUnmatchedDescription(searchAutomaton.getSearchOutput().getUnmatchedDescription());
			hypothesis.addAllToJustification(searchAutomaton.getSearchOutput().getJustification());
			
			/* Determine if necessary to establish a dialog with the user (in case none of the possible solutions is
			 equal to or more specific than the identification goal)*/
			if (hypothesis.getPossibleSolutions().get(0).getStatus() == true) {
				if (TaxonomicRank.getIndex(hypothesis.getPossibleSolutions().get(0).getLevel()) <
						 TaxonomicRank.getIndex(this.getIdentGoal())) {
					dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis, this.getTaxonomy(), this.getMinSimilarityDegree());
					dialog.chat();
					status = dialog.getStatus();
					if (status == SearchStatus.ERROR || status == SearchStatus.CANCEL)
						return false;
				}
			}

			// Add the hypothesis to the successful conflict set
			this.addSuccessfulConflictSet(hypothesis);

			//return this.searchTaxonStructures();
		} else this.addNoResultsSet(hypothesis); // Search was unsuccessful. Add hypothesis to the no-results set
		
		return true;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public Description getCharacterList() {
		return this.getDescription().getCharacterList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public Description getHeuristicList() {
		return this.getDescription().getHeuristicList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getCharacterStructuresList() {
		return this.getDescription().getCharacterStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getHeuristicStructuresList() {
		return this.getDescription().getHeuristicStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getStructuresList() {
		return this.getDescription().getStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	public Description getDescription(String aStructureName) {
		return this.getDescription().getDescription(aStructureName);
	}
}
