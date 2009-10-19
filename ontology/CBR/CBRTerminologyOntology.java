// file: CBRTerminologyOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package ontology.CBR;

import ontology.common.CommonTerminologyOntology;
import ontology.taxonomy.TaxonomyOntology;
import jade.content.onto.*;
import jade.content.schema.*;

/** file: CBRTerminologyOntology.java
 * @author ontology bean generator
 * @version 2009/10/6, 23:03:27
 */
@SuppressWarnings("serial")
public class CBRTerminologyOntology extends jade.content.onto.Ontology  {

  //NAME
  public static final String ONTOLOGY_NAME = "CBRTerminology";
  // The singleton instance of this ontology
  private static Ontology theInstance = new CBRTerminologyOntology();
  public static Ontology getInstance() {
     return theInstance;
  }

   // VOCABULARY
  	public static final String ARESIMILARTO_FAILURECONFLICTSET="failureConflictSet";
  	public static final String ARESIMILARTO_PROBLEM="problem";
  	public static final String ARESIMILARTO_SUCCESSFULCONFLICTSET="successfulConflictSet";
  	public static final String ARESIMILARTO="AreSimilarTo";
    public static final String ISNEGATIVE_CASE="case";
    public static final String ISNEGATIVE="IsNegative";
    public static final String ISPOSITIVE_CASE="case";
    public static final String ISPOSITIVE="IsPositive";
    public static final String ISTHEMOSTREASONABLETO_PROBLEM="problem";
    public static final String ISTHEMOSTREASONABLETO_PROPOSEDSOLUTION="proposedSolution";
    public static final String ISTHEMOSTREASONABLETO="IsTheMostReasonableTo";
    public static final String AREREASONABLESOLUTIONSTO_PROPOSEDSOLUTIONS="proposedSolutions";
    public static final String AREREASONABLESOLUTIONSTO_PROBLEM="problem";
    public static final String AREREASONABLESOLUTIONSTO="AreReasonableSolutionsTo";
    public static final String RETRIEVE_SIMILARTO="similarTo";
    public static final String RETRIEVE="Retrieve";
    public static final String DESCRIBES="Describes";
    public static final String ADAPT_FAILURECONFLICTSET="failureConflictSet";
    public static final String ADAPT_SUCCESSFULCONFLICTSET="successfulConflictSet";
    public static final String ADAPT_TO="to";
    public static final String ADAPT="Adapt";
    public static final String RESOLVE_PROBLEM="problem";
    public static final String RESOLVE="Resolve";
    public static final String RETAIN_CASE="case";
    public static final String RETAIN="Retain";
    public static final String HYPOTHESIS_UNMATCHEDDESCRIPTION="unmatchedDescription";
    public static final String HYPOTHESIS_DESCRIPTION="description";
    public static final String HYPOTHESIS_JUSTIFICATION="justification";
    public static final String HYPOTHESIS_POSSIBLESOLUTIONS="possibleSolutions";
    public static final String HYPOTHESIS="Hypothesis";
    public static final String CASE_STATE="state";
    public static final String CASE_PROBLEM="problem";
    public static final String CASE_SOLUTION="solution";
    public static final String CASE="Case";
    public static final String PROBLEM_LEASTSIMILARITYDEGREE="leastSimilarityDegree";
    public static final String PROBLEM_GOALRANK="goalRank";
    public static final String PROBLEM_DESCRIPTION="description";
    public static final String PROBLEM="Problem";
    public static final String SOLUTION_NAME="name";
    public static final String SOLUTION_JUSTIFICATION="justification";
    public static final String SOLUTION_RANK="rank";
    public static final String SOLUTION="Solution";
    public static final String POSSIBLESOLUTION_CONTRADICTIONS="contradictions";
    public static final String POSSIBLESOLUTION_DOUBTFULDESCRIPTION="doubtfulDescription";
    public static final String POSSIBLESOLUTION_SOLUTIONDESCRIPTION="solutionDescription";
    public static final String POSSIBLESOLUTION_UNCONFIRMEDDESCRIPTION="unconfirmedDescription";
    public static final String POSSIBLESOLUTION_SOLUTION="solution";
    public static final String POSSIBLESOLUTION_CONFIRMEDDESCRIPTION="confirmedDescription";
    public static final String POSSIBLESOLUTION_POINTS="points";
    public static final String POSSIBLESOLUTION="PossibleSolution";
    public static final String PROPOSEDSOLUTION_STATE="state";
    public static final String PROPOSEDSOLUTION_SOLUTION="solution";
    public static final String PROPOSEDSOLUTION_CERTAINTYDEGREE="certaintyDegree";
    public static final String PROPOSEDSOLUTION="ProposedSolution";
    public static final String SELECT_TO="to";
    public static final String SELECT_SUCCESSFULCONFLICTSET="successfulConflictSet";
    public static final String SELECT_FAILURECONFLICTSET="failureConflictSet";
    public static final String SELECT="Select";
    public static final String EVALUATE_TO="to";
    public static final String EVALUATE_SUCCESSFULCONFLICTSET="successfulConflictSet";
    public static final String EVALUATE_FAILURECONFLICTSET="failureConflictSet";
    public static final String EVALUATE="Evaluate";
    public static final String ARESELECTEDSOLUTIONSTO_PROPOSEDSOLUTIONS="proposedSolutions";
    public static final String ARESELECTEDSOLUTIONSTO_TO="to";
    public static final String ARESELECTEDSOLUTIONSTO="AreSelectedSolutionsTo";
    public static final String AREEVALUATEDSOLUTIONSTO_FAILURECONFLICTSET="failureConflictSet";
    public static final String AREEVALUATEDSOLUTIONSTO_SUCCESSFULCONFLICTSET="successfulConflictSet";
    public static final String AREEVALUATEDSOLUTIONSTO_TO="to";
    public static final String AREEVALUATEDSOLUTIONSTO="AreEvaluatedSolutionsTo";

  /**
   * Constructor
  */
  private CBRTerminologyOntology(){ 
    super(ONTOLOGY_NAME, TaxonomyOntology.getInstance());
    try { 

    ConceptSchema proposedSolutionSchema = new ConceptSchema(PROPOSEDSOLUTION);
    add(proposedSolutionSchema, ontology.CBR.ProposedSolution.class);
    ConceptSchema possibleSolutionSchema = new ConceptSchema(POSSIBLESOLUTION);
    add(possibleSolutionSchema, ontology.CBR.PossibleSolution.class);
    ConceptSchema solutionSchema = new ConceptSchema(SOLUTION);
    add(solutionSchema, ontology.CBR.Solution.class);
    ConceptSchema problemSchema = new ConceptSchema(PROBLEM);
    add(problemSchema, ontology.CBR.Problem.class);
    ConceptSchema caseSchema = new ConceptSchema(CASE);
    add(caseSchema, ontology.CBR.Case.class);
    ConceptSchema hypothesisSchema = new ConceptSchema(HYPOTHESIS);
    add(hypothesisSchema, ontology.CBR.Hypothesis.class);

    // adding AgentAction(s)
    AgentActionSchema retainSchema = new AgentActionSchema(RETAIN);
    add(retainSchema, ontology.CBR.Retain.class);
    AgentActionSchema resolveSchema = new AgentActionSchema(RESOLVE);
    add(resolveSchema, ontology.CBR.Resolve.class);
    AgentActionSchema adaptSchema = new AgentActionSchema(ADAPT);
    add(adaptSchema, ontology.CBR.Adapt.class);
    AgentActionSchema retrieveSchema = new AgentActionSchema(RETRIEVE);
    add(retrieveSchema, ontology.CBR.Retrieve.class);
    AgentActionSchema evaluateSchema = new AgentActionSchema(EVALUATE);
    add(evaluateSchema, ontology.CBR.Evaluate.class);
    AgentActionSchema selectSchema = new AgentActionSchema(SELECT);
    add(selectSchema, ontology.CBR.Select.class);

    // adding AID(s)

    // adding Predicate(s)
    PredicateSchema areReasonableSolutionsToSchema = new PredicateSchema(AREREASONABLESOLUTIONSTO);
    add(areReasonableSolutionsToSchema, ontology.CBR.AreReasonableSolutionsTo.class);
    PredicateSchema isTheMostReasonableToSchema = new PredicateSchema(ISTHEMOSTREASONABLETO);
    add(isTheMostReasonableToSchema, ontology.CBR.IsTheMostReasonableTo.class);
    PredicateSchema isPositiveSchema = new PredicateSchema(ISPOSITIVE);
    add(isPositiveSchema, ontology.CBR.IsPositive.class);
    PredicateSchema isNegativeSchema = new PredicateSchema(ISNEGATIVE);
    add(isNegativeSchema, ontology.CBR.IsNegative.class);
    PredicateSchema areSimilarToSchema = new PredicateSchema(ARESIMILARTO);
    add(areSimilarToSchema, ontology.CBR.AreSimilarTo.class);
    PredicateSchema areEvaluatedSolutionsToSchema = new PredicateSchema(AREEVALUATEDSOLUTIONSTO);
    add(areEvaluatedSolutionsToSchema, ontology.CBR.AreEvaluatedSolutionsTo.class);
    PredicateSchema areSelectedSolutionsToSchema = new PredicateSchema(ARESELECTEDSOLUTIONSTO);
    add(areSelectedSolutionsToSchema, ontology.CBR.AreSelectedSolutionsTo.class);


    // adding fields
    proposedSolutionSchema.add(PROPOSEDSOLUTION_CERTAINTYDEGREE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    proposedSolutionSchema.add(PROPOSEDSOLUTION_SOLUTION, possibleSolutionSchema, ObjectSchema.MANDATORY);
    proposedSolutionSchema.add(PROPOSEDSOLUTION_STATE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
    possibleSolutionSchema.add(POSSIBLESOLUTION_POINTS, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    possibleSolutionSchema.add(POSSIBLESOLUTION_CONFIRMEDDESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.OPTIONAL);
    possibleSolutionSchema.add(POSSIBLESOLUTION_SOLUTION, new ConceptSchema("Concept"), ObjectSchema.MANDATORY);
    possibleSolutionSchema.add(POSSIBLESOLUTION_UNCONFIRMEDDESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.OPTIONAL);
    possibleSolutionSchema.add(POSSIBLESOLUTION_SOLUTIONDESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.MANDATORY);
    possibleSolutionSchema.add(POSSIBLESOLUTION_DOUBTFULDESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.OPTIONAL);
    possibleSolutionSchema.add(POSSIBLESOLUTION_CONTRADICTIONS, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.OPTIONAL);
    solutionSchema.add(SOLUTION_RANK, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    solutionSchema.add(SOLUTION_JUSTIFICATION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.MANDATORY);
    solutionSchema.add(SOLUTION_NAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    problemSchema.add(PROBLEM_DESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.MANDATORY);
    problemSchema.add(PROBLEM_GOALRANK, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    problemSchema.add(PROBLEM_LEASTSIMILARITYDEGREE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    caseSchema.add(CASE_SOLUTION, solutionSchema, ObjectSchema.MANDATORY);
    caseSchema.add(CASE_PROBLEM, problemSchema, ObjectSchema.MANDATORY);
    caseSchema.add(CASE_STATE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
    hypothesisSchema.add(HYPOTHESIS_POSSIBLESOLUTIONS, possibleSolutionSchema, 1, ObjectSchema.UNLIMITED);
    hypothesisSchema.add(HYPOTHESIS_JUSTIFICATION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.OPTIONAL);
    hypothesisSchema.add(HYPOTHESIS_DESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.MANDATORY);
    hypothesisSchema.add(HYPOTHESIS_UNMATCHEDDESCRIPTION, (ConceptSchema) getSchema(CommonTerminologyOntology.DESCRIPTION), ObjectSchema.OPTIONAL);
    retainSchema.add(RETAIN_CASE, caseSchema, ObjectSchema.MANDATORY);
    resolveSchema.add(RESOLVE_PROBLEM, problemSchema, ObjectSchema.MANDATORY);
    adaptSchema.add(ADAPT_SUCCESSFULCONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    adaptSchema.add(ADAPT_FAILURECONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    adaptSchema.add(ADAPT_TO, problemSchema, ObjectSchema.MANDATORY);
    retrieveSchema.add(RETRIEVE_SIMILARTO, problemSchema, ObjectSchema.MANDATORY);
    areReasonableSolutionsToSchema.add(AREREASONABLESOLUTIONSTO_PROBLEM, problemSchema, ObjectSchema.MANDATORY);
    areReasonableSolutionsToSchema.add(AREREASONABLESOLUTIONSTO_PROPOSEDSOLUTIONS, proposedSolutionSchema, 0, ObjectSchema.UNLIMITED);
    isTheMostReasonableToSchema.add(ISTHEMOSTREASONABLETO_PROPOSEDSOLUTION, proposedSolutionSchema, ObjectSchema.MANDATORY);
    isTheMostReasonableToSchema.add(ISTHEMOSTREASONABLETO_PROBLEM, problemSchema, ObjectSchema.MANDATORY);
    isPositiveSchema.add(ISPOSITIVE_CASE, caseSchema, ObjectSchema.MANDATORY);
    isNegativeSchema.add(ISNEGATIVE_CASE, caseSchema, ObjectSchema.MANDATORY);
    areReasonableSolutionsToSchema.add(AREREASONABLESOLUTIONSTO_PROBLEM, problemSchema, ObjectSchema.MANDATORY);
    areSimilarToSchema.add(ARESIMILARTO_SUCCESSFULCONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    areSimilarToSchema.add(ARESIMILARTO_FAILURECONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    areSimilarToSchema.add(ARESIMILARTO_PROBLEM, problemSchema, ObjectSchema.MANDATORY);
    evaluateSchema.add(EVALUATE_FAILURECONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    evaluateSchema.add(EVALUATE_SUCCESSFULCONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    evaluateSchema.add(EVALUATE_TO, problemSchema, ObjectSchema.MANDATORY);    
    selectSchema.add(SELECT_FAILURECONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    selectSchema.add(SELECT_SUCCESSFULCONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    selectSchema.add(SELECT_TO, problemSchema, ObjectSchema.MANDATORY);
    areEvaluatedSolutionsToSchema.add(AREEVALUATEDSOLUTIONSTO_TO, problemSchema, ObjectSchema.MANDATORY);
    areEvaluatedSolutionsToSchema.add(AREEVALUATEDSOLUTIONSTO_SUCCESSFULCONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    areEvaluatedSolutionsToSchema.add(AREEVALUATEDSOLUTIONSTO_FAILURECONFLICTSET, hypothesisSchema, 0, ObjectSchema.UNLIMITED);
    areSelectedSolutionsToSchema.add(ARESELECTEDSOLUTIONSTO_TO, problemSchema, ObjectSchema.MANDATORY);
    areSelectedSolutionsToSchema.add(ARESELECTEDSOLUTIONSTO_PROPOSEDSOLUTIONS, proposedSolutionSchema, 0, ObjectSchema.UNLIMITED);

    // adding name mappings

    // adding inheritance
    
   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
}
