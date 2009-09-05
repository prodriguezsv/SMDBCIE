/*
 * Paquete que se encarga de las funciones de b&uacute;squeda a trav&eacute;s de algoritmos de entre los
 * cuales se encuentra la b&uacute;squeda primero en profundidad y back-tracking
 * @see "Categor&iacutea Sukia Search Automata en SUKIA SmallTalk"
 */

package system.searchAutomata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import ontology.CBR.Case;
import ontology.CBR.SimilarityDegree;
import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;
import ontology.common.HeuristicDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SVHeuristicDescriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
import ontology.values.RangeValue;
import ontology.values.SingleValue;

import system.Hypothesis;
import system.PossibleSolution;
import system.similarityAssessment.SimilarityAssessor;

/**
 * Instances of this class are invoked when the search automatas have performed successfully (i.e., with at
 * least one possible solution), but the taxonomic level of all the possible solutions is more general than
 * the stated identification goal. The purpose of this class is then, for each qualifying possible solution,
 * establish a dialog with the user asking him/her questions about successor taxa of each solution, all
 * related to the descriptive element. As questions are positively answered, the corresponding taxon moves
 * "one level down", approaching the stated goal.
 * This dialog stopswhen the user cancels, when all possible solutions have been processed but no positive
 * "down-movement" was done, or when at least ONE taxon reaches the stated goal.
 * @author pabloq
 */

public class GoalApproachingDialog {
    private Hypothesis hypothesis;
    private TaxonomicRank goal;
    private Taxonomy taxonomy;
    private List<PossibleSolution> processList;
    private List<PossibleSolution> OKList;
    private SearchStatus status;
    private SimilarityDegree minSimilarityDegree;

	 /**
	 * Category instance creation
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public GoalApproachingDialog(){
    	
    }
    
    public GoalApproachingDialog(TaxonomicRank aGoal, Hypothesis aHypothesis, Taxonomy aTaxonomy,
    	SimilarityDegree minSimilarityDegree){
        //if ((aHypothesis.getDescription() instanceof Structure) == true)
        this.initializeGoal(aGoal, aHypothesis, aTaxonomy, minSimilarityDegree);
    }
    
    /**
     *Category initializing
     */

    /**
     * @see Define method name.
     * @param my parameters list
     * @return my return values
     */
    protected void initializeGoal(TaxonomicRank aGoal, Hypothesis aHypothesis, Taxonomy aTaxonomy,
    		SimilarityDegree simRangesList){        
    	//The argument aGoal MUST be a value valid for TaxonomicLevels (e.g., #genus)
        goal = aGoal;

        //The elements of simrangesList MUST be defined in SimRanges
        minSimilarityDegree = simRangesList;

        hypothesis = aHypothesis;
        taxonomy = aTaxonomy;

        this.setStatus(SearchStatus.FAIL);
        
        //This list must contain zero or more instances of PossibleSolution
    	processList = new ArrayList<PossibleSolution>();
        
    	//This list must contain zero or more instances of PossibleSolution
        OKList = new ArrayList<PossibleSolution>();
    }


	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public TaxonomicRank getGoal(){
        return goal;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Hypothesis getHypothesis(){
        return hypothesis;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<PossibleSolution> getOKList(){
        return OKList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<PossibleSolution> getProcessList(){
        return processList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SimilarityDegree getSimilarityRanges(){
        return minSimilarityDegree;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SearchStatus getStatus(){
        return status;
    }
    
	/**
	 * The instance variable status indicates the search status at the end of the process.
	 * The possible values it may have are:
	 * #fail - the dialog was unsuccessful. This is the default value.
	 * #success - at least one taxon reached the goal.
	 * #cancel - the user canceled the dialog process.
	 * #error - a processing error occurred.
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setStatus(SearchStatus aStatusValue){
        status = aStatusValue;        
	 }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Taxonomy getTaxonomy(){
        return taxonomy;
    }
    
	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addOKList(PossibleSolution aPossibleSolution){
        OKList.add(aPossibleSolution);
        Collections.sort(this.getOKList());
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addProcessList(PossibleSolution aPossibleSolution){
        processList.add(aPossibleSolution);
        Collections.sort(this.getProcessList());
    }

	/**
	 * The purpose of this method is to initially do all the administrative work necessary to select
	 * those possible solutions (from the associated hypothesis) that are elegible for processing.
	 * That is, ONLY positive cases or Taxon instances will be processed. Once a possible solution
	 * is selected, it is placed in the processList.  Next, out of those previously filtered in, a second
	 * selection comes about: select only those (positive cases or taxa)  possible solutions whose
	 * taxonomic level is *closest to the stated identification goal*. These elements will remain in the
	 * processList; the rest will be filtered out.
	 * Precondition: the associated hypothesis MUST have at least one possible solution.
	 * @see Define method name.
	 * @param my parameters list
	 * @return null - if the precondition fails, or a processing error occurred, or processList is empty;
	 * value returned by doDialog.
	 */
    public SearchStatus chat(){
    	Taxon taxon;
    	int psLevelAsIndex;
    	
        //Check precondition
        if (hypothesis.getPossibleSolutions().isEmpty()) return SearchStatus.FAIL;

        //Transform the stated identification goal to a numeric value
        int goalAsIndex = TaxonomicRank.getIndex(goal);

        //Scan the associated hypothesis possible solutions list
        for (PossibleSolution ps:hypothesis.getPossibleSolutions()) {
            //Transform the possible solution level to a numeric value
            psLevelAsIndex = TaxonomicRank.getIndex(ps.getLevel());

            //If the possible solution's level is equal to, or more specific than the goal, ignore it
            if (!(psLevelAsIndex >= goalAsIndex)){
                //If the possible solution is a taxon, place it in the process list. Else, it must be a case.
            	//Before placing the possible solution in the process list, find its corresponding taxon in
            	//the associated taxonomy
                if (ps.getSolution() instanceof Taxon)
                    processList.add(ps);
                else {
                    //If the case's status is positive, continue processing it. Else, ignore it
                    if (((Case)ps.getSolution()).getState()){
                        //Solution is a positive case. Retrieve the corresponding taxon from the taxonomy
                        taxon = taxonomy.getTaxonFromLevelIndex(ps.getName(), ps.getLevel());

                        //If the taxon is not found, there is an error. set ba tate of error and return
                        if (taxon == null)
                        	return (status = SearchStatus.ERROR);

                        //Exchange the case for the taxon. Finally, place it in the process list
                        ps.setSolution(taxon);
                        addProcessList(ps);
                    }

                }
            }

        }
        
        //If the process list ends up having zip, return fail status (default)
        if (processList.isEmpty())
        	return SearchStatus.FAIL;

        //At this point, processList contains a set of possible solutions whose level is more general than
        //the stated identificaction goal. Moreover, all solutions are taxa. Proceed to select those items
        //whose level is *closest* to the goal
        this.selectPossibleSolutionsNearestToGoal();

        return doDialog();

    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
	protected SearchStatus doDialog(){
    	List<Descriptor> description, OKSAVDescriptorList;
    	List<String> attributesList;
    	double value;
    	String response;

    	//Scan the processList
        while(processList.isEmpty() != true) {
            //Remove the next possible solution
            PossibleSolution ps = processList.remove(0);
            //get the next possible solution's taxon
            Taxon taxon = (Taxon)ps.getSolution();

            //Scan the taxon's successor list
            for (Taxon t:taxon.getSuccessors()){
                //get the related structure from the successor taxon's SAV description
                
                attributesList = t.getAttributeList(hypothesis.getDescription().get(0).getStructure());
                for (String a:attributesList) {
                	description = t.getDescription(hypothesis.getDescription().get(0).getStructure(), a);
                	
                	if (!description.isEmpty()){
                    	for (Descriptor d:description){
                    		OKSAVDescriptorList = new ArrayList<Descriptor>();
                            // Make sure this attribute is not already processed (i.e., included in the solution
                    		// or confirmed descriptions of ANY item in the processList)
                            if (!this.areThereContradictions(d)) {
                                if (d.getValue() == null)
                                	return (status= SearchStatus.ERROR);                              
                                
                                //If the value decriptor is a range, do a range-driven dialog
                                if ((d.getValue() instanceof RangeValue)) {
                                	response = null;
                                	//Suponiendo que el usuario introduce valores válidos
                                    response = this.rangeValueDescriptorDialog(d);
                            		if (response == null)
                            			return (status = SearchStatus.CANCEL);
                            		
                            		if (response.equals("reject")) {
                            			value = (((RangeValue)d.getValue()).getLowerBound()  + 
                        						((RangeValue)d.getValue()).getUpperBound()) / (double)2;
                            			
                            			if (d instanceof CharacterDescriptor) {
                    						ps.addToUnconfirmedDescription(new SVCharacterDescriptor(d.getStructure(),
                                    				d.getAttribute(), new SingleValue(value)));

                    					} else {
                    						ps.addToUnconfirmedDescription(new SVHeuristicDescriptor(d.getStructure(),
                                    				d.getAttribute(), new SingleValue(value)));
                    					}
                            		}
                            		
                            		value = Double.parseDouble(response);
                            			
                            		if (value >= ((RangeValue)d.getValue()).getLowerBound() 
                            				&& value <= ((RangeValue)d.getValue()).getUpperBound()) {
                            		     //The user typed a value within the range. Thus result contains a SAVDescriptor
                            	        //that can be used to update ps confirmedDescription. Additionally, assign
                            	        //nextLevelTaxon to ps solution, and place ps in the OKList. Finally, do a recursive
                            	        //call to doDialog, in order to process the next possible solution in processList
                            			ps.setSolution(t);
                            			if (d instanceof HeuristicDescriptor)
                            				ps.addToConfirmedDescription(new SVHeuristicDescriptor(d.getStructure(),
                            						d.getAttribute(), new SingleValue(value)));
                            			else ps.addToConfirmedDescription(new SVCharacterDescriptor(d.getStructure(),
                            					d.getAttribute(), new SingleValue(value)));
                            			
                                    	addOKList(ps);
                                    	return doDialog();
                                	} else hypothesis.addToUnmatchedDescription(new SVCharacterDescriptor(d.getStructure(),
                            					d.getAttribute(), new SingleValue(value)));
                                } else {
                                    // Make sure the SAV descriptor is not already processed (i.e., included in 
                                    // the unconfirmed, doubtful, or unmatched descriptions of ANY item in the
                                    // processList. The reason for this check is to avoid asking the user questions
                                    // previously answered
                                    if (this.isDescriptorAlreadyProcessed(d) != true){
                                        //For the new descriptor's value, make sure its degree of similarity (with respect to
                                        //the attribute's typical value) is acceptable. If it isn't, ignore it"
                                        if (this.determineSimilarityFor(d, t) != false){
                                            //Place the descriptor is a separate descriptor list. Process the next value
                                            OKSAVDescriptorList.add(d);
                                        }
                                    }
                                }
                            }
                            
                            // Once all of the attribute's values have been processed, the number of items in
                            // the display array MUST be equal to the number of descriptors in the descriptor
                            // list. If such number is greater than zero, display the dialog
                            if (OKSAVDescriptorList.isEmpty() != true){
                            	response = null;
                                response = valueDescriptorDialog(OKSAVDescriptorList);

                                //User rejects. Flush the descriptor list by placing all SAVDescriptors in the unconfirmed
                                //description. Continue processing the next attribute

                                if (response.equals("reject")){
                                    while (OKSAVDescriptorList.isEmpty() != true){
                                        ps.addToUnconfirmedDescription(OKSAVDescriptorList.remove(0));
                                    }
                                    continue;
                                }

                                //User is in doubt. Flush the descriptor list by placing all SAVDescriptors in the doubtful
                                //description. Continue processing the next attribute
                                if (response.equals("doubtful")){
                                    while (OKSAVDescriptorList.isEmpty() != true){
                                        ps.addToDoubtfulDescription(OKSAVDescriptorList.remove(0));
                                    }
                                    continue;
                                }
                                
                                //User cancels. Cancel the process and exit.
                                if (response == null)
                                	return (status = SearchStatus.CANCEL);
                                
                                //User selects one item. The dialog method returns the value included in a
                                // SAVDescriptor. Assign nextLevelTaxon to ps solution. Place ps in the OKList. Call doDialog recursively
                                value = Double.parseDouble(response);
                                
                                ps.setSolution(t);
                                if (d instanceof HeuristicDescriptor)
                    				ps.addToConfirmedDescription(new SVHeuristicDescriptor(d.getStructure(),
                    						d.getAttribute(), new SingleValue(value)));
                    			else ps.addToConfirmedDescription(new SVCharacterDescriptor(d.getStructure(),
                    					d.getAttribute(), new SingleValue(value)));
                                
                                OKList.add(ps);
                                return doDialog();
                            }    
                    	}   
                	}
                }
            }
        }
        // At this point, all of the possible solutions have been processed. If the OKList is NOT empty, all
        // the elements now included in it have advanced one level with respect to the original ps solution.
        // If the FIRST possible solution's level meets the goal, exit successfully. Else, copy everything
        // back to the processList and call doDialog again. The return value is whatever this new call returns
        if (OKList.isEmpty() != true){
            if (goal.equals(OKList.get(0).getLevel()))
            	return (status = SearchStatus.SUCCESS);

            while (OKList.isEmpty() != true){
                addProcessList(OKList.remove(0));
            }
            
            return doDialog();
        }
        
        return (status = SearchStatus.SUCCESS);
    }

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    protected boolean selectPossibleSolutionsNearestToGoal() {
        //Check precondition
        if (processList.isEmpty())
        	return false;

        //If the processList contains only one element, there's nothing to do
        if (processList.size() == 1)
        	return true;

        //get the level of the first element
        TaxonomicRank level = processList.get(0).getLevel();

        //Starting with the second element, process the list
        int i = 1;
        while (i < processList.size()){
            if (level.equals(processList.get(i).getLevel())){
                i += 1;
            }else{
                processList.remove(i);
            }
        }
        
        return true;
    }


	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    protected String rangeValueDescriptorDialog(Descriptor descriptor){
    	String msg;
    	double suggestedValue;
    	
    	if (!(descriptor.getValue() instanceof RangeValue))
    		return null;

		msg =	"\nPor favor digite un valor en unidades de \"" + ((RangeValue)descriptor.getValue())
				.getMeasuringUnit().getMeasuringUnit() + "\"" +
				"\npara el atributo \"" + descriptor.getAttribute() + "\" de la estructura \"" 
				+ descriptor.getStructure() + "\" o acepte el valor sugerido." +
				"\n\nSi no es posible proveer la respuesta, escriba \"reject\"." +
				"\nSi quiere abortar la interacción, haga click en cancelar.\n";
	
		suggestedValue = (((RangeValue)descriptor.getValue()).getLowerBound()  + 
						((RangeValue)descriptor.getValue()).getUpperBound()) / (double)2;
	
		return JOptionPane.showInputDialog(msg, Double.toString(suggestedValue));
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
	protected String valueDescriptorDialog(List<Descriptor> descriptors){
    	String msg;
    	String values = "\n";
    	
    	if (descriptors.isEmpty())
    		return null;
    	
    	for (Descriptor d:descriptors) {
    		if (d.getValue() instanceof String)
    			values = values + d.getValue() + "    ";
    		else if (d.getValue() instanceof SingleValue)
    			values = values + ((SingleValue)d.getValue()).getValue() + "    ";
    		else return null;
    	}

		msg =	"¿Cuál de los siguientes valores: " + values + 
				"\npresenta el atributo \"" + descriptors.get(0).getAttribute() +
				"\"\nde la estructura \"" + descriptors.get(0).getStructure() + "\"?" +
				"\n\nSi no es posible proveer la respuesta, escriba \"reject\"." +
				"\nSi tiene dudas de la respuesta, escriba \"doubtful\"." +
				"\nSi quiere abortar la interacción, haga click en cancelar.\n";

		return JOptionPane.showInputDialog(msg);		
    }

	/**
	 * Determines the similarity range between aSAVDescriptor's value and aTaxon's value weight list.
	 * If there exists a range of similarity, it is checked against the accepted ranges.
	 * @see Define method name.
	 * @param my parameters list
	 * @return null : if there is no similarity. aTaxon : if there was an acceptable degree of similarity.
	 */
    protected boolean determineSimilarityFor(Descriptor aSAVDescriptor,Taxon aTaxon){
    	Map<Object, Double> weightedValues;
        
        weightedValues = aTaxon.retriveValuesUsing(aSAVDescriptor.getStructure(),
        		aSAVDescriptor.getAttribute());
        
        SimilarityDegree similarity = SimilarityAssessor.similarityRangeOf(aSAVDescriptor.getValue(), weightedValues);
        
        if (EnumSet.range(minSimilarityDegree, SimilarityDegree.IGUAL).contains(similarity) != true)
        	return false;
        
        return true;
        
    }
    	
	/**
	 * Verifica si existen contradicciones entre los descriptores (estructura, atributo, valor)
	 * Se dice que existe contradiccion  si existe dos descripciones distintas para el mismo par
	 * (estructura, atributo)
	 * @see "M&eacute;todo thereAreContradictions: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	protected boolean areThereContradictions(Descriptor aDescriptor) {
	    for (PossibleSolution ps: hypothesis.getPossibleSolutions()){
	    	// Para cada par (atributo, valor)
			for(Descriptor d: ps.getSolutionDescription()) {
				if (d.getStructure().equals(aDescriptor.getStructure()) &&
						d.getAttribute().equals(aDescriptor.getAttribute())	) {
						return true; // Hay contradiccion
				}
			}
			
			// Para cada par (atributo, valor)
			for(Descriptor d: ps.getConfirmedDescription()) {
				if (d.getStructure().equals(aDescriptor.getStructure()) &&
						d.getAttribute().equals(aDescriptor.getAttribute())	) {
						return true; // Hay contradiccion
				}
			}
	    }
	
	    return false;
	}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    protected boolean isDescriptorAlreadyProcessed(Descriptor aSAVDescriptor){
        if (hypothesis.getUnmatchedDescription().contains(aSAVDescriptor)) return true;

        for (PossibleSolution ps:hypothesis.getPossibleSolutions()){
            if (ps.getUnconfirmedDescription().contains(aSAVDescriptor)) return true;
            if (ps.getDoubtfulDescription().contains(aSAVDescriptor)) return true;
        }
        
        return false;
    }
}
