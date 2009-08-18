/**
 * 
 */
package system;

import java.util.ArrayList;
import java.util.List;

import ontology.CBR.SimilarityDegree;
import ontology.common.Descriptor;
import ontology.common.GroupingHeuristic;
import ontology.common.Structure;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;

import redundantDiscriminationNet.RDMultiNet;
import redundantDiscriminationNet.RDNet;
import redundantDiscriminationNet.RootNorm;
import searchHintsBase.HintsBase;
import system.searchAutomata.GoalApproachingDialog;
import system.searchAutomata.SAVCaseDFSAutomaton;
import system.searchAutomata.TaxonGHISAutomaton;
import system.searchAutomata.TaxonSISAutomaton;
import system.searchAutomata.output.DFSAutomatonOutput;

/**
 * @author Armando
 *
 */
public class Reasoner {
	private List<Descriptor<Object>> caseMemorySearchJustification; //Ojo
	private List<Hypothesis> failGHConflictSet;
	private List<Hypothesis> failStructConflictSet;
	private List<GroupingHeuristic> groupHDescription;
	private TaxonomicRank identGoal;
	private System identSystem;
	private int maxNumberSolutions;
	private SimilarityDegree minSimilarityDegree;
	private List<Hypothesis> noResultsSet;
	private boolean presentFailedSolutions;
	private List<ProposedSolution> proposedSolutions;
	private List<Descriptor<Object>> routeSelectJustification; //Ojo
	private List<Structure> structDescription;
	private List<Hypothesis> succGHConflictSet;
	private List<Hypothesis> succStructConflictSet;
	private List<Descriptor<Object>> taxonHierarchySearchJustification; // OJo

	/**
	 * Método de instancia agregado
	 */
	public Reasoner() {
		super();
	}
	
	/**
	 * identSystem will be associated to the Sukia system object. Through this object, access is
	 * gained main data structures: the memory case, the taxonomic hierarchy, and the hints base
	 * @see "Método initialize: del protocolo initializing en SUKIA SmallTalk"
	 */
	public Reasoner(System anIdentificationSystem) {
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
		setStructDescription(new ArrayList<Structure>());

		// List with GroupingHeuristics which complement the description given by the user
		setGroupHDescription(new ArrayList<GroupingHeuristic>());

		/* CONFLICT SET: Hypotheses that contain successful possible solutions (i.e., positive cases or taxa) 
		 for a given Structure belonging to the problem description*/
		setSuccStructConflictSet(new ArrayList<Hypothesis>());

		/*CONFLICT SET: Hypothesis that contain failed possible solutions (i.e., negative cases only) for a given 
		 Structure belonging to the problem description*/
		setFailStructConflictSet(new ArrayList<Hypothesis>());

		/*CONFLICT SET: Hypothesis that contain successful possible solutions (i.e., positive cases or taxa) for 
		 a given GroupingHeuristic belonging to the problem description*/
		setSuccGHConflictSet(new ArrayList<Hypothesis>());

		/*CONFLICT SET: Hypothesis that contain failed possible solutions (i.e., negative cases only) for a given 
		 GroupingHeuristic belonging to the problem description*/
		setFailGHConflictSet(new ArrayList<Hypothesis>());

		/* CONFLICT SET: Hypothesis that do not contain any possible solutions.  These Hypotheses are associated
		 to either Structures or GroupingHeuristics for which the search was unsuccessful*/
		setNoResultsSet(new ArrayList<Hypothesis>());

		// Set of solutions to present to the user
		setProposedSolutions(null);

		// Justification list: route selection (using the HintsBase) 
		setRouteSelectJustification(new ArrayList<Descriptor<Object>>());

		// Justification list: case memory search 
		setCaseMemorySearchJustification(new ArrayList<Descriptor<Object>>());

		// Justification list: taxonomic hierarchy serach
		setTaxonHierarchySearchJustification(new ArrayList<Descriptor<Object>>());
	}

	/**
	 * Método de instancia agregado
	 * @param caseMemorySearchJustification
	 */
	public void setCaseMemorySearchJustification(List<Descriptor<Object>> caseMemorySearchJustification) {
		this.caseMemorySearchJustification = caseMemorySearchJustification;
	}
	
	/**
	 * @see "Método caseMemorySearchJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationItem
	 */
	public void addCaseMemorySearchJustification(Descriptor<Object> aJustificationItem) {
		this.getCaseMemorySearchJustification().add(aJustificationItem);
	}

	/**
	 * @see "Método caseMemorySearchJustification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getCaseMemorySearchJustification() {
		return caseMemorySearchJustification;
	}

	/**
	 * Método de instancia agregado
	 * @param failGHConflictSet
	 */
	public void setFailGHConflictSet(List<Hypothesis> failGHConflictSet) {
		this.failGHConflictSet = failGHConflictSet;
	}
	
	/**
	 * @see "Método failGHConflictSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addFailGHConflictSet(Hypothesis aHypothesis) {
		this.getFailGHConflictSet().add(aHypothesis);
	}

	/**
	 * @see "Método failGHConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getFailGHConflictSet() {
		return failGHConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param failStructConflictSet
	 */
	public void setFailStructConflictSet(List<Hypothesis> failStructConflictSet) {
		this.failStructConflictSet = failStructConflictSet;
	}
	
	/**
	 * @see "Método failStructConflictSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addFailStructConflictSet(Hypothesis aHypothesis) {
		this.getFailStructConflictSet().add(aHypothesis);
	}

	/**
	 * @see "Método failStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getFailStructConflictSet() {
		return failStructConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param groupHDescription
	 */
	public void setGroupHDescription(List<GroupingHeuristic> groupHDescription) {
		this.groupHDescription = groupHDescription;
	}
	
	/**
	 * @see "Método groupHDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addGroupHDescription(GroupingHeuristic aGroupingHeuristic) {
		for( int i = 1; i <= this.getGroupHDescription().size(); i++) {
			if (this.getGroupHDescription().get(i-1).getName().equals(aGroupingHeuristic.getName()))
				return;
		}

		this.getGroupHDescription().add(aGroupingHeuristic);
	}

	/**
	 * @see "Método groupHDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<GroupingHeuristic> getGroupHDescription() {
		return groupHDescription;
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
	public void setIdentSystem(System identSystem) {
		this.identSystem = identSystem;
	}

	/**
	 * @see "Método identSystem del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public System getIdentSystem() {
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
	public void setRouteSelectJustification(List<Descriptor<Object>> routeSelectJustification) {
		this.routeSelectJustification = routeSelectJustification;
	}
	
	/**
	 * @see "Método routeSelectJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationItem
	 */
	public void addRouteSelectJustification(Descriptor<Object> aJustificationItem) {
		this.getRouteSelectJustification().add(aJustificationItem);
	}

	/**
	 * @see "Método routeSelectJustification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getRouteSelectJustification() {
		return routeSelectJustification;
	}

	/**
	 * Método de instancia agregado
	 * @param structDescription
	 */
	public void setStructDescription(List<Structure> structDescription) {
		this.structDescription = structDescription;
	}
	
	/**
	 * @see "Método groupHDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addStructDescription(Structure aStructure) {
		for( int i = 1; i <= this.getStructDescription().size(); i++) {
			if (this.getStructDescription().get(i-1).getName().equals(aStructure.getName()))
				return;
		}

		this.getStructDescription().add(aStructure);
	}

	/**
	 * @see "Método structDescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Structure> getStructDescription() {
		return structDescription;
	}

	/**
	 * Método de instancia agregado
	 * @param succGHConflictSet
	 */
	public void setSuccGHConflictSet(List<Hypothesis> succGHConflictSet) {
		this.succGHConflictSet = succGHConflictSet;
	}
	
	/**
	 * @see "Método succGHConflictSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addSuccGHConflictSet(Hypothesis aHypothesis) {
		this.getSuccGHConflictSet().add(aHypothesis);
	}

	/**
	 * @see "Método succGHConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getSuccGHConflictSet() {
		return succGHConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param succStructConflictSet
	 */
	public void setSuccStructConflictSet(List<Hypothesis> succStructConflictSet) {
		this.succStructConflictSet = succStructConflictSet;
	}
	
	/**
	 * @see "Método succGHConflictSet: del protocolo adding en SUKIA SmallTalk"
	 * @param aHypothesis
	 */
	public void addSuccStructConflictSet(Hypothesis aHypothesis) {
		this.getSuccStructConflictSet().add(aHypothesis);
	}

	/**
	 * @see "Método succStructConflictSet del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Hypothesis> getSuccStructConflictSet() {
		return succStructConflictSet;
	}

	/**
	 * Método de instancia agregado
	 * @param taxonHierarchySearchJustification
	 */
	public void setTaxonHierarchySearchJustification(List<Descriptor<Object>> taxonHierarchySearchJustification) {
		this.taxonHierarchySearchJustification = taxonHierarchySearchJustification;
	}
	
	/**
	 * @see "Método routeSelectJustification: del protocolo adding en SUKIA SmallTalk"
	 * @param aJustificationItem
	 */
	public void addTaxonHierarchySearchJustification(Descriptor<Object> aJustificationItem) {
		this.getTaxonHierarchySearchJustification().add(aJustificationItem);
	}

	/**
	 * @see "Método taxonHierarchySearchJustification del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getTaxonHierarchySearchJustification() {
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
		return this.getIdentSystem().getTaxonomicGroupName();
	}

	/**
	 * Evaluation of all possible solutions belonging to Hypotheses stored in all conflict sets
	 * @see "Método evaluatePossibleSolutions del protocolo evaluating and selecting en SUKIA SmallTalk"
	 */
	public boolean evaluatePossibleSolutions() {
		PossibleSolutionEvaluator evaluator;
		
		evaluator = new PossibleSolutionEvaluator(this.getSuccStructConflictSet(), this.getFailStructConflictSet(), 
				this.getSuccGHConflictSet(), this.getFailGHConflictSet(), this.getTaxonomy());
	
		evaluator.evaluate();
		
		return true;
	}
	
	/**
	 * Selection of the best possible solutions, as proposed solutions for an identification session.
	 * @see "Método selectSolutions del protocolo evaluating and selecting en SUKIA SmallTalk"
	 */
	public List<ProposedSolution> selectSolutions() {
		PossibleSolutionSelector selector;
		
		selector = new PossibleSolutionSelector(this.getIdentGoal(), this.getSuccStructConflictSet(), this.getFailStructConflictSet(),  
				this.getSuccGHConflictSet(), this.getFailGHConflictSet(), this.getMaxNumberSolutions(), this.isPresentFailedSolutions());

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
		if (this.chooseIndexHintsForStructures() == false)
			return false;

		// Use the HintsBase with the GroupingHeuristic-based description
		if (this.chooseIndexHintsForGroupingHeuristics() == false)
			return false;

		// First choice: use the case memory primary search data structure
		if (!(this.getCaseMemory().getRoot().getNets().isEmpty())) {
			if (this.useCaseMemory() == false) return false;
		} else {
			// Second choice: use the taxonomic hierarchy as primary search data structure
			if (this.useTaxonomicHierarchy() == false) return false;
		}

		// If there are no available possible solutions, exit
		if (this.getSuccStructConflictSet().isEmpty() && this.getFailStructConflictSet().isEmpty() &&
			this.getSuccGHConflictSet().isEmpty() && this.getFailGHConflictSet().isEmpty())
			return false; // Ojo

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
	 * 4) minSimilarityDegree: minimal similarity degree that user expects the system to consider when comparing values (see SimRanges).
	 * 5) structDescription: problem description in terms of Structures.
	 * 6) groupHDescription: problem description in terms of GroupingHeuristics.
	 * For now (8-Oct-1999), this method will not enter a dialog with the user. The before-mentioned variables will be
	 * updated via method calls (see the adding category) from one of Smalltalk's workspace screens.  Once this method
	 * is developed, it must satisfy the following conditions:
	 * -Aceptar una meta de identificación:el nombre de un nivel taxonómico
	 * -Aceptar la descripción del especímen a identificar: : una lista de Estructuras y Heurísticas de agrupamiento
	 * Condiciones que debe cumplir la descripción
	 * i) La descripción no puede tener dos Estructuras con el mismo nombre.
	 * ii) Las Estructuras pueden tener asignado un peso entre 0.0 y 1.0, el cual se interpreta como 'el grado de interés
	 * que el usuario muestra por la estructura descrita.
	 * iii) Cada Estructura debe tener al menos un atributo.
	 * iv) Ninguna Estructura puede tener dos atributos con el mismo nombre.
	 * v) El atributo de una Estructura debe tener solo un valor.
	 * vi) El valor de un atributo debe ser ByteSymbol o Número (no rango).
	 * vii) El valor de un atributo puede ser obtenido de la lista de valores definidos para ese atributo, o ser un valor nuevo.
	 * viii) El valor de un atributo puede tener un peso asignado, el cual se interpreta como 'el grado de certidumbre del
	 * usuario': 0.0 -- no tiene grado de certidumbre asignado; 0.5 - el usuario no está eguro del valor brindado;
	 * 1.0 - el usuario está eguro del valor brindado.
	 * ix) La descripción no puede tener dos Heurísticas de Agrupamiento con el mismo nombre
	 * x) Las Heurísticas de Agrupamiento pueden tener asignado un peso entre 0.0 y 1.0, el cual se interpreta como
	 * 'el grado de interés que el usuario muestra por la heurística de agrupamiento descrita'.
	 * xi) Cada Heurística de Agrupamiento debe tener solo un valor.
	 * xii) El valor de una Heurística de Agrupamiento debe ser ByteSymbol o Número (no rango).
	 * xiii) El valor de una Heurística de Agrupamiento puede ser obtenido de la lista de valores definidos para esa Heurística
	 * de Agrupamiento, o ser un valor nuevo.
	 * xiv) El valor de una Heurística de Agrupamiento puede tener un peso asignado, interpretado como 'el grado de certidumbre
	 * del usuario': 0.0 -- no tiene grado de certidumbre asignado; 0.5 - el usuario no está eguro del valor brindado; 1.0 - el
	 * usuario está seguro del valor brindado. (Ojo falta)
	 * @see "Método getProblemDescription del protocolo private-general en SUKIA SmallTalk"
	 * @return
	 */
	private Object getProblemDescription() {
		return this;
	}
	
	/**
	 * @see "Método useCaseMemory del protocolo private-general en SUKIA SmallTalk"
	 * @return
	 */
	private boolean useCaseMemory() {
		if (this.searchCaseGroupingHeuristics() == false)
			return false;

		if (this.searchCaseStructures() == false)
			return false;

		return true;
	}
	
	/**
	 * @see "Método useCaseMemory del protocolo private-general en SUKIA SmallTalk"
	 * @return
	 */
	private boolean useTaxonomicHierarchy() {
		if (this.searchTaxonGroupingHeuristics() == false)
			return false;

		if (this.searchTaxonStructures() == false)
			return false;

		return true;
	}
	
	/**
	 * PRECONDITION: (Search atuomaton's possible solutions list is NOT empty)
	 * RETURNS:	nil - if the precondition is not met, OR an error occurred.
	 * self - if all OK
	 * @param searchAutomaton
	 * @param hypothesis1
	 * @param hypothesis2
	 * @return
	 */
	// Ojo falta la traducción de los automatas
	private boolean processSuccessfulGHSearchOutputWith(SAVCaseDFSAutomaton searchAutomaton, Hypothesis hypothesis1, Hypothesis hypothesis2) {
		Hypothesis currHypothesis;
		PossibleSolution ps;
		int index;
	
		// Check the precondition
		if (searchAutomaton.getSearchOutput().getPossibleSolutions() == null)
			return false;

		// Successful search: at least one case was found. Scan the search automaton's possible solution's list
		while (!(searchAutomaton.getSearchOutput().getPossibleSolutions().isEmpty())) {
			// Get the next possible solution from the search automaton's output
			ps = searchAutomaton.getSearchOutput().getPossibleSolutions().remove(0);

			/* Attempt to add the possible solution to the first hypothesis. If not successful, attempt
			to add it to the second hypothesis. If unsuccessful again, return an error value*/
			if (hypothesis1.addPossibleSolutions(ps) == false)
				if (hypothesis2.addPossibleSolutions(ps) == false) return false;
		}
		
		// Once all possible solutions have been loaded, copy the search automaton's unmatched description
		if (!(hypothesis1.getPossibleSolutions().isEmpty()))
			hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton.getSearchOutput().getUnmatchedDescription());

		if (!(hypothesis2.getPossibleSolutions().isEmpty()))
			hypothesis2.copyToUnmatchedDescriptionFrom(searchAutomaton.getSearchOutput().getUnmatchedDescription());

		// Now copy the justification
		if (!(hypothesis1.getPossibleSolutions().isEmpty()))
			hypothesis1.copyToJustificationFrom(searchAutomaton.getSearchOutput().getJustification());

		if (!(hypothesis2.getPossibleSolutions().isEmpty()))
			hypothesis1.copyToJustificationFrom(searchAutomaton.getSearchOutput().getJustification());
		
		// Finally, add the hypotheses to conflict sets
		index = 1;
		currHypothesis = hypothesis1;
		
		while (index <= 2) {
			if (!(currHypothesis.getPossibleSolutions().isEmpty())) {
				if (currHypothesis.getPossibleSolutions().get(0).getStatus() == true)
					this.addSuccGHConflictSet(currHypothesis);
				else {
					if (currHypothesis.getPossibleSolutions().get(0).getStatus() == false)
						this.addFailGHConflictSet(currHypothesis);
					else return false;
				}
			}
			
			currHypothesis = hypothesis2;
			index = index + 1;
		}

		return true;
	}
	
	/**
	 * Use the grouping heuristic description as search data.
	 * RETURNS: nil - If an error occurred, or the user cancelled the search process.
	 * self - OK
	 * @return
	 */
	public boolean searchCaseGroupingHeuristics() {
		GroupingHeuristic gh;
		Hypothesis hypothesis1, hypothesis2;
		RootNorm caseNetRoot;
		SAVCaseDFSAutomaton searchAutomaton1;
		TaxonGHISAutomaton searchAutomaton2;
		List<Descriptor<Object>> problemDescription;
		String status;
		
		while (!(this.getGroupHDescription().isEmpty())) {
			// Remove the next grouping heuristic from the description
			gh = this.getGroupHDescription().remove(0);

			// Create a first instance of Hypothesis and assign the grouping heuristic as descriptive element
			hypothesis1 = new Hypothesis();
			hypothesis1.setDescriptiveElement(gh);

			// Create a second instance of hypothesis, and again, assign the same grouping heuristic as descriptive element
			hypothesis2 = new Hypothesis();
			hypothesis2.setDescriptiveElement(gh);
			
			// Get the SAV problem description from the grouping heuristic. If no description available, return error value
			problemDescription = gh.createSAVDescription(this.getTaxonomicGroupName());
			
			if (problemDescription == null) return false;

			// Get the net root that corresponds to the grouping heuristic
			caseNetRoot = this.getCaseMemory().getRoot().getRDNet(this.getTaxonomicGroupName()).getRoot();
		
			if (!(caseNetRoot == null)) {
				// Create a new instance of case net search automaton
				searchAutomaton1 = new SAVCaseDFSAutomaton(caseNetRoot);
	
				// Begin the search with the given problem description
				searchAutomaton1.beginWith(problemDescription);
				status = searchAutomaton1.getStatus();
	
				if (status.equals("error") || status.equals("cancel"))
					return false;
	
				if (status.equals("success")) {
					this.processSuccessfulGHSearchOutputWith(searchAutomaton1, hypothesis1, hypothesis2);
					return this.searchCaseGroupingHeuristics();
				}
	
				if (status.equals("fail")) {
					/* Copy the unmatched description and the justification to the first hypothesis, which is the one
					 we'll continue to use from now on.*/
					hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton1.getSearchOutput().getUnmatchedDescription());
					hypothesis1.copyToJustificationFrom(searchAutomaton1.getSearchOutput().getJustification());
				}
			}
		
			/* At this point, either: a) no net root was found, or b) the status of the net search was unsuccessful 
			 (i.e., status = #fail). Try doing a taxonomic search*/
	
			// Refresh the problem description
			problemDescription = gh.createSAVDescription(this.getTaxonomicGroupName());
			if (problemDescription == null) return false;
	
			// Perform a taxonomic search (Ojo)
			searchAutomaton2 = new TaxonGHISAutomaton(this.getTaxonomy().getGroupingHeuristicIndex());
			searchAutomaton2.beginWith(problemDescription);
			status = searchAutomaton2.getStatus();
	
			if (status.equals("error") || status.equals("cancel"))
				return false;
	
			if (status.equals("success")) {
				// Load all the possible solutions into the hypothesis
				while (!(searchAutomaton2.getSearchOutput().getPossibleSolutions().isEmpty())) {
					/* Attempt to add the possible solution to the first hypothesis. If not successful,
					 return an error value*/
					 if (hypothesis1.addPossibleSolutions(searchAutomaton2.getSearchOutput().getPossibleSolutions().remove(0)) == false)
						 return false;
				}
				
				hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton2.getSearchOutput().getUnmatchedDescription());
				//hypothesis1.copyToJustificationFrom(searchAutomaton.getSearchOutput().getJustification());
				hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton2.getSearchOutput().getUnmatchedDescription());
	
				// Add the hypothesis to the successful conflict set
				this.addSuccGHConflictSet(hypothesis1);
	
				return this.searchCaseGroupingHeuristics();
			}
	
			if (status.equals("fail")) {
				/* Copy the unmatched description and the justification to the first hypothesis, which is the one
				 we'll continue to use from now on.*/
				hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton2.getSearchOutput().getUnmatchedDescription());
				hypothesis1.copyToJustificationFrom(searchAutomaton2.getSearchOutput().getJustification());
			}
			
			// Search was unsuccessful. Add hypothesis to the no-results set
			this.addNoResultsSet(hypothesis1);
		}
	
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
	// Ojo falta la traducción de los automatas
	private boolean processSuccessfulStructSearchOutputWith(DFSAutomatonOutput anOutputCopy, Hypothesis hypothesis1, Hypothesis hypothesis2) {
		Hypothesis currHypothesis;
		PossibleSolution ps;
		GoalApproachingDialog dialog;
		int index;
		String status;
	
		// Check the precondition
		if (anOutputCopy.getPossibleSolutions() == null)
			return false;

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
			if (hypothesis1.addPossibleSolutions(ps) == false)
				if (hypothesis2.addPossibleSolutions(ps) == false) return false;
		}
		
		// Once all possible solutions have been loaded, copy the search automaton's unmatched description
		if (!(hypothesis1.getPossibleSolutions().isEmpty()))
			hypothesis1.copyToUnmatchedDescriptionFrom(anOutputCopy.getUnmatchedDescription());

		if (!(hypothesis2.getPossibleSolutions().isEmpty()))
			hypothesis2.copyToUnmatchedDescriptionFrom(anOutputCopy.getUnmatchedDescription());

		// Now copy the justification
		if (!(hypothesis1.getPossibleSolutions().isEmpty()))
			hypothesis1.copyToJustificationFrom(anOutputCopy.getJustification());

		if (!(hypothesis2.getPossibleSolutions().isEmpty()))
			hypothesis1.copyToJustificationFrom(anOutputCopy.getJustification());
		
		/* HYPOTHESIS1: If none of the possible solutions is equal to, or more specific than
		 the identification goal, establish a new dialog with the user, in order to try to draw
		 the existing possible solutions nearer to the said goal*/
		if (hypothesis1.getPossibleSolutions().get(0).getStatus() == true) {
			if (TaxonomicRank.getIndex(hypothesis1.getPossibleSolutions().get(0).getLevel()) <
					 TaxonomicRank.getIndex(this.getIdentGoal())) {
				dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis1, this.getTaxonomy(), this.getMinSimilarityDegree());
				dialog.chat();
				status = dialog.getStatus();
				if (status.equals("error") || status.equals("cancel"))
					return false;
			}
		} else {
			// hypothesis1 does not contain positive cases
			/* HYPOTHESIS2: If none of the possible solutions is equal to, or more specific than
			 the identification goal, establish a new dialog with the user, in order to try to draw
		 	the existing possible solutions nearer to the said goal*/
			// Ojo
			if (!(hypothesis2.getPossibleSolutions().isEmpty())) {
				if (TaxonomicRank.getIndex(hypothesis2.getPossibleSolutions().get(0).getLevel()) <
						 TaxonomicRank.getIndex(this.getIdentGoal())) {
					dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis2, this.getTaxonomy(), this.getMinSimilarityDegree());
					dialog.chat();
					status = dialog.getStatus();
					if (status.equals("error") || status.equals("cancel"))
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
					this.addSuccStructConflictSet(currHypothesis);
				else {
					if (currHypothesis.getPossibleSolutions().get(0).getStatus() == false)
						this.addFailStructConflictSet(currHypothesis);
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
	public boolean searchCaseStructures() {
		Structure s;
		Hypothesis hypothesis1, hypothesis2;
		List<Descriptor<Object>> problemDescription;
		RDNet net;
		RootNorm caseNetRoot;
		SAVCaseDFSAutomaton searchAutomaton1;
		TaxonSISAutomaton searchAutomaton2;
		GoalApproachingDialog dialog;
		DFSAutomatonOutput outputCopy;
		String status;


		while (!(this.getStructDescription().isEmpty())) {
			// Remove the next grouping heuristic from the description
			s = this.getStructDescription().remove(0);

			// Create a first instance of Hypothesis and assign the structure as descriptive element
			hypothesis1 = new Hypothesis();
			hypothesis1.setDescriptiveElement(s);

			// Create a second instance of hypothesis, and again, assign the same structure as descriptive element
			hypothesis2 = new Hypothesis();
			hypothesis2.setDescriptiveElement(s);
			
			// Get the SAV problem description from the structure. If no description available, return error value
			problemDescription = s.createDescription(this.getTaxonomicGroupName());
			
			if (problemDescription == null) return false;
			
			// Get the net root that corresponds to the structure
			net = this.getCaseMemory().getRoot().getRDNet(s.getName());
			if (net == null) 
				caseNetRoot = null;
			else caseNetRoot = net.getRoot();
					
			if (!(caseNetRoot == null)) {
				// Create a new instance of case net search automaton
				searchAutomaton1 = new SAVCaseDFSAutomaton(caseNetRoot);
	
				// Begin the search with the given problem description
				searchAutomaton1.beginWith(problemDescription);
				status = searchAutomaton1.getStatus();
	
				if (status.equals("error") || status.equals("cancel"))
					return false;
	
				/* If the automaton returns a non-empty problem description list, then the REASONER MUST call 
				 it again with that remaining description. Before doing so, all lists, except the doubtful and unconfirmed 
				 ones, MUST be flushed. Make sure the new search begins at root level, and all necessary control variables 
				 are correctly set. This process repeats until the problem description list is EMPTY. After each successive 
				 call, the unmatched description, the justification and whatever possible solutions MUST be concatenated 
				 to the original search-automaton output copy*/
				outputCopy = searchAutomaton1.getSearchOutput(); // Ojo .copy()
				outputCopy.setTaxonomy(this.getTaxonomy());
				
				while (!(problemDescription.isEmpty())) {
					searchAutomaton1.newSearchWith(problemDescription);
					status = searchAutomaton1.getStatus();

					if (status.equals("error") || status.equals("cancel"))
						return false;

					outputCopy.appendToPossibleSolutions(searchAutomaton1.getSearchOutput().getPossibleSolutions());
					outputCopy.appendToJustification(searchAutomaton1.getSearchOutput().getJustification());
					outputCopy.appendToUnmatchedDescription(searchAutomaton1.getSearchOutput().getUnmatchedDescription());
				}
				
				outputCopy.compress();

				if (!(outputCopy.getPossibleSolutions() == null)) {
					if (!(outputCopy.getPossibleSolutions().isEmpty())) {
						if (this.processSuccessfulStructSearchOutputWith(outputCopy, hypothesis1, hypothesis2) == false)
							return false;

						return this.searchCaseStructures();
					}
				}
				
				/* At this point, status is neither #error, #cancel, nor #success (this value is implied by the
				 condition (outputCopy possibleSolutions isEmpty) ifFalse:. Thus, is must be #fail. Copy 
				 the unmatched description and the justification to the first hypothesis, which is the one
			   	 we'll continue to use from now on.*/
				hypothesis1.copyToUnmatchedDescriptionFrom(outputCopy.getUnmatchedDescription());
				hypothesis1.copyToJustificationFrom(outputCopy.getJustification());
			}
		
			/* At this point, either: a) no net root was found, or b) the status of the net search was unsuccessful 
			 (i.e., status = #fail). Try doing a taxonomic search*/
	
			// Refresh the problem description
			problemDescription = s.createDescription(this.getTaxonomicGroupName());
			if (problemDescription == null) return false;
	
			// Perform a taxonomic search (Ojo)
			searchAutomaton2 = new TaxonSISAutomaton(this.getTaxonomy().getDescriptorsIndex(), this.getMinSimilarityDegree());
			searchAutomaton2.beginWith(problemDescription);
			status = searchAutomaton2.getStatus();
	
			if (status.equals("error") || status.equals("cancel"))
				return false;
	
			
			if (status.equals("success")) {
				// Load all the possible solutions into the hypothesis
				while (!(searchAutomaton2.getSearchOutput().getPossibleSolutions().isEmpty())) {
					/* Attempt to add the possible solution to the first hypothesis. If not successful,
					 return an error value*/
					 if (hypothesis1.addPossibleSolutions(searchAutomaton2.getSearchOutput().getPossibleSolutions().remove(0)) == false)
						 return false;
				}
				
				hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton2.getSearchOutput().getUnmatchedDescription());
				//hypothesis1.copyToJustificationFrom(searchAutomaton.getSearchOutput().getJustification());
				hypothesis1.copyToUnmatchedDescriptionFrom(searchAutomaton2.getSearchOutput().getUnmatchedDescription());

				/*HYPOTHESIS1: If none of the possible solutions is equal to, or more specific than the identification goal, 
				 establish a new dialog with the user, in order to try to draw the existing possible solutions nearer to the goal*/
				if (hypothesis1.getPossibleSolutions().get(0).getStatus() == true) {
					if (TaxonomicRank.getIndex(hypothesis1.getPossibleSolutions().get(0).getLevel()) <
							 TaxonomicRank.getIndex(this.getIdentGoal())) {
						dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis1, this.getTaxonomy(), this.getMinSimilarityDegree());
						dialog.chat();
						status = dialog.getStatus();
						if (status.equals("error") || status.equals("cancel"))
							return false;
					}
				}
				
				// Add the hypothesis to the successful conflict set
				this.addSuccStructConflictSet(hypothesis1);
	
				return this.searchCaseStructures();
			}
			
			// Search was unsuccessful. Add hypothesis to the no-results set
			this.addNoResultsSet(hypothesis1);
		}
	
		return true;
	}
	
	/**
	 * This method uses the HintsBase lists, and tries to arrange the GroupingHeuristic description
	 * in a search-efective way (i.e., the GroupingHeuristics at the beginning of the list are the ones
	 * with more possibilities to find positive solution cases).  For now (08-Oct-1999):
	 * a) 	If the percentage of items successfully processed is greater than zero, then it suffices.
	 * It is convenient, though, to device a way to determine if certain percentage is acceptable
	 * or not.
	 * b)	Failed lists will not be used.
	 * RETURNS: self.
	 */
	@SuppressWarnings("unchecked")
	private boolean chooseIndexHintsForGroupingHeuristics() {
		HintsBase hb;
		
		hb = this.getHintsBase();
		
		if (!(hb.getWeightedGrpHeuristicList().sortBySuccessCriteria((List)this.getGroupHDescription()).isEmpty()))
			if (hb.getWeightedGrpHeuristicList().getPercentageItemsProcessed() > 0.0) return false;

		hb.getFreqGrpHeuristicList().sortBySuccessCriteria(this.getGroupHDescription());
		
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
	@SuppressWarnings("unchecked")
	private boolean chooseIndexHintsForStructures() {
		HintsBase hb;
		Structure s;
		
		hb = this.getHintsBase();

		if (!(hb.getWeightedStructList().sortBySuccessCriteria((List)this.getStructDescription()).isEmpty()))
				if (hb.getWeightedStructList().getPercentageItemsProcessed() > 0.0) return false;
		
		if (!(hb.getSuccessfulFreqStructPattList().sortByFrecuencyCriteria(this.getStructDescription()).isEmpty()))
			if (hb.getSuccessfulFreqStructPattList().getPercentageItemsProcessed() > 0.0) return false;

		for( int i = 1; i <= this.getStructDescription().size(); i++) {
			hb.getSpecStructAttrList().resetPercentageItemsProcessed();

			s = this.getStructDescription().get(i-1);
			hb.getSpecStructAttrList().sortBySuccessCriteria(s);
		}
		
		return true;
	}

	/**
	 * Performs  a search in the taxonomic hierarchy, using a set of grouping
	 * heuristics as input data.
	 * RETURNS: nil - if an error occurs, OR the user cancels the search process.
	 * self - OK
	 */
	private boolean searchTaxonGroupingHeuristics() {
		GroupingHeuristic gh;
		Hypothesis hypothesis;
		String status;
		List<Descriptor<Object>> problemDescription;
		TaxonGHISAutomaton searchAutomaton;
	
		while (!(this.getGroupHDescription().isEmpty())) {
			// Remove the next grouping heuristic from the description
			gh = this.getGroupHDescription().remove(0);

			// Create a first instance of Hypothesis and assign the grouping heuristic as descriptive element
			hypothesis = new Hypothesis();
			hypothesis.setDescriptiveElement(gh);

			// Get the SAV problem description from the grouping heuristic
			problemDescription = gh.createSAVDescription(this.getTaxonomicGroupName());
			if (problemDescription == null) return false;

			// Perform a taxonomic search
			searchAutomaton = new TaxonGHISAutomaton(this.getTaxonomy().getGroupingHeuristicIndex());
			searchAutomaton.beginWith(problemDescription);
			status = searchAutomaton.getStatus();

			if (status.equals("error") || status.equals("cancel"))
				return false;
			
			if (status.equals("success")) {
				// Load all the possible solutions into the hypothesis
				while (!(searchAutomaton.getSearchOutput().getPossibleSolutions().isEmpty())) {
					// Attempt to add the possible solution to the hypothesis. If not successful, return an error value*/
					 if (hypothesis.addPossibleSolutions(searchAutomaton.getSearchOutput().getPossibleSolutions().remove(0)) == false)
						 return false;
				}
				
				hypothesis.copyToUnmatchedDescriptionFrom(searchAutomaton.getSearchOutput().getUnmatchedDescription());
				hypothesis.copyToUnmatchedDescriptionFrom(searchAutomaton.getSearchOutput().getUnmatchedDescription());

				// Add the hypothesis to the successful conflict set
				this.addSuccGHConflictSet(hypothesis);

				return this.searchTaxonGroupingHeuristics();
			}
			
			// Search was unsuccessful. Add hypothesis to the no-results set
			this.addNoResultsSet(hypothesis);
		}
			
		return true;
	}
	
	/**
	 * Performs  a search in the taxonomic hierarchy, using a set of structures
	 * as input data.
	 * RETURNS: nil - if an error occurs, OR the user cancels the search process.
	 * self - OK
	 */
	private boolean searchTaxonStructures() {
		Structure s;
		Hypothesis hypothesis;
		String status;
		List<Descriptor<Object>> problemDescription;
		TaxonSISAutomaton searchAutomaton; // Ojo
		GoalApproachingDialog dialog;
	
		while (!(this.getStructDescription().isEmpty())) {
			// Remove the next structure from the description
			s = this.getStructDescription().remove(0);

			// Create a first instance of Hypothesis and assign the grouping heuristic as descriptive element
			hypothesis = new Hypothesis();
			hypothesis.setDescriptiveElement(s);

			// Get the SAV problem description from the structure
			problemDescription = s.createDescription(this.getTaxonomicGroupName());
			if (problemDescription == null) return false;

			// Perform a taxonomic search
			searchAutomaton = new TaxonSISAutomaton(this.getTaxonomy().getDescriptorsIndex(), this.getMinSimilarityDegree());
			searchAutomaton.beginWith(problemDescription);
			status = searchAutomaton.getStatus();

			if (status.equals("error") || status.equals("cancel"))
				return false;
							
			if (status.equals("success")) {
				// Load all the possible solutions into the hypothesis
				while (!(searchAutomaton.getSearchOutput().getPossibleSolutions().isEmpty())) {
					// Attempt to add the possible solution to the hypothesis. If not successful, return an error value*/
					 if (hypothesis.addPossibleSolutions(searchAutomaton.getSearchOutput().getPossibleSolutions().remove(0)) == false)
						 return false;
				}
				
				hypothesis.copyToUnmatchedDescriptionFrom(searchAutomaton.getSearchOutput().getUnmatchedDescription());
				hypothesis.copyToUnmatchedDescriptionFrom(searchAutomaton.getSearchOutput().getUnmatchedDescription());
				
				/* Determine if necessary to establish a dialog with the user (in case none of the possible solutions is
				 equal to or more specific than the identification goal)*/
				if (TaxonomicRank.getIndex(hypothesis.getPossibleSolutions().get(0).getLevel()) <
						 TaxonomicRank.getIndex(this.getIdentGoal())) {
					dialog = new GoalApproachingDialog(this.getIdentGoal(), hypothesis, this.getTaxonomy(), this.getMinSimilarityDegree());
					dialog.chat();
					status = dialog.getStatus();
					if (status.equals("error") || status.equals("cancel"))
						return false;
				}

				// Add the hypothesis to the successful conflict set
				this.addSuccStructConflictSet(hypothesis);

				return this.searchTaxonStructures();
			}
			
			// Search was unsuccessful. Add hypothesis to the no-results set
			this.addNoResultsSet(hypothesis);
		}
			
		return true;
	}
}
