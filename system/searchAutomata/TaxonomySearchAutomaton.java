
package system.searchAutomata;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import ontology.CBR.SimilarityDegree;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.values.RangeValue;

import system.PossibleSolution;
import system.searchAutomata.output.TaxonomyAutomatonOutput;
import system.similarityAssessment.SimilarityAssessor;

/**
 *
 * @author pabloq
 */

public class TaxonomySearchAutomaton {
	private Description justification;
	private Description currentTaxonSolutionDescription;
	private Description currentTaxonUnmatchedDescription;
	private List<PossibleSolution> possibleSolutions;
	private TaxonomyAutomatonOutput searchOutput;
	private final Map<Descriptor, List<Taxon>> searchIndex;
	private final SimilarityDegree minSimilarityDegree;
	private SearchStatus status;

   public TaxonomySearchAutomaton (Map<Descriptor, List<Taxon>> searchIndex,
		   SimilarityDegree minSimilarityDegree) {
        searchOutput = new TaxonomyAutomatonOutput();
        possibleSolutions = new ArrayList<PossibleSolution>();
        currentTaxonSolutionDescription = new Description();
        currentTaxonUnmatchedDescription = new Description();
        this.searchIndex = searchIndex;
        this.minSimilarityDegree = minSimilarityDegree;
        status = SearchStatus.FAIL;
   }

 /**
 *Category adding
 */

   /**
    * 
    */
	public Map<Descriptor, List<Taxon>> getSearchIndex() {
		return searchIndex;
	}

	/**
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
    public void addToPossibleSolutions(PossibleSolution aPossibleSolution){
        this.possibleSolutions.add(aPossibleSolution);
    }

    /**
     * 
     * @param possibleSolutions
     */
    public void setPossibleSolutions(List<PossibleSolution> possibleSolutions){
    	        this.possibleSolutions = possibleSolutions;
    }
    
	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean addToCurrentTaxonSolutionDescription(Descriptor aDescriptor){
    	return currentTaxonSolutionDescription.addToConcreteDescription(aDescriptor);
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean addToCurrentTaxonUnmatchedDescription(Descriptor aDescriptor){
    	return currentTaxonUnmatchedDescription.addToConcreteDescription(aDescriptor);
    }

/**
 *Category accessing
 */
    /**
     * 
     */
    public TaxonomyAutomatonOutput getSearchOutput(){
        return searchOutput;
    }
    
    public SearchStatus getStatus(){
        return status;
    }
    
    public List<PossibleSolution> getPossibleSolutions(){
        return possibleSolutions;
    }
    
    public Description getCurrentTaxonSolutionDescription(){
        return currentTaxonSolutionDescription;
    }
    
    public Description  getCurrentTaxonUnmatchedDescription(){
        return currentTaxonUnmatchedDescription;
    }

/**
 *Category private
 */

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<PossibleSolution> associateTaxaToPossibleSolutions(List<Taxon> aTaxonList){
        List<PossibleSolution> psList = new ArrayList<PossibleSolution>();
        
        for (Taxon tx : aTaxonList){
            PossibleSolution ps = new PossibleSolution();
            ps.setSolution(tx);
            ps.getSolutionDescription().addAllToConcreteDescription(currentTaxonSolutionDescription);
            psList.add(ps);
        }
        return psList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean checkPrecondition(List<Descriptor>  aProblemDescription){
        if (aProblemDescription.isEmpty()) return false;

        String sName = aProblemDescription.get(0).getStructure();
        if (aProblemDescription.size() > 0) {
            for (int i = 1; i < aProblemDescription.size(); i++)                
                if (sName.equals(aProblemDescription.get(i).getStructure()) != true) return false;
        }
        
        return true;
    }

	/**
	 * @see This method is called from beginWith, and is executed whenn the automaton has failed to provide
	 * a solution for the given problem description.
	 * @param my parameters list
	 * @return my return values
	 */
    protected void prepareFailedOutput(){
        searchOutput.setJustification(justification);
        //this.getJustification().clear();
        searchOutput.setUnmatchedDescription(currentTaxonUnmatchedDescription);
        //this.getCurrentTaxonUnmatchedDescription().clear();
        status = SearchStatus.FAIL;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    protected void prepareSuccessfulOutput(){
        searchOutput.setPossibleSolutions(possibleSolutions);
        //this.getPossibleSolutions().clear();
        searchOutput.setJustification(justification);
        //this.getJustification().clear();
        searchOutput.setUnmatchedDescription(currentTaxonUnmatchedDescription);
        //this.getCurrentTaxonUnmatchedDescription().clear();
        status = SearchStatus.SUCCESS;
    }
    
    /**
     * The argument aProblemDescription MUST consist of one SAVDescriptor of the form: aSAVDescriptor = 
     * ( generalStructureName, groupingHeuristicName, value ), where:
     * generalStructureName = name of the organism for which the Reasoner is set up (e.g., 'planta'), 
     * groupingHeuristicName = a valid grouping heuristic name (e.g., #esLenoso), 
     * value = (ByteSymbol | Number)
     * Automaton reference: bW
     * @see Define method name.
     * @param my parameters list
     * @return nil - if the precondition was not met, or an error occurred, or the process failed to find
     * possible solutions. value returned by prepareSuccessfulOutputWith:
     */
    public SearchStatus beginSearch(List<Descriptor> aProblemDescription){
    	//Check general description precondition
        if (checkPrecondition(aProblemDescription) == false) {
        	setStatus(SearchStatus.ERROR);
        	return status;
        }
        
        searchPossibleSolutions(aProblemDescription);

        if (getPossibleSolutions().isEmpty()) {
        	prepareFailedOutput();
        } else {
	        this.compressPossibleSolutions();
	        prepareSuccessfulOutput();
        }
        
    	return status;
    }

    /**
     * This method searchs for value descriptors that either match aSAVDescriptor value, or correspond
     * to a range for which aSAVDescriptor value applies.  If valueDescriptors are found, each associated
     * taxon will be set up as a possible solution. All possible solutions will be stored in taxonList.
     * Automaton reference: SVD
     * @see Define method name.
     * @param my parameters list
     * @return -1 : if a processing error occurred; nil : if no value descriptors were found; self : if at
     * least one descriptor was found.
     */
    public void searchPossibleSolutions(List<Descriptor> descriptionProblem) {
    	List<Descriptor> tempList;
    	
    	tempList = new ArrayList<Descriptor>();
        for (Descriptor d: descriptionProblem) {
        	List<Taxon> taxa = this.getSearchIndex().get(d);
        	
        	if (taxa == null)
        		addToCurrentTaxonUnmatchedDescription(d);
        	else {
        		if (d.getValue() instanceof RangeValue){
                    //Value desscriptor is a range. Associate all taxa to possible solutions, place them in the taxon list

        			//Extract the taxa included in each of the retrieved value descriptors
                    addToCurrentTaxonSolutionDescription(d);
                    List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxa);
                    getCurrentTaxonSolutionDescription().clear();
                    
                    while(ps.isEmpty() != true)
                        addToPossibleSolutions(ps.remove(0));
                   
                } else tempList.add(d); //Value descriptor is not a range. Place it in a temporary list
        	}
        }
        
        /*At this point, all descriptors have been verified and processed. If there are no exact-match value
        descriptors left, return*/
        if (tempList.isEmpty()) return;
        
        List<Taxon> taxaTempList = new ArrayList<Taxon>();
        
        for (Descriptor d: tempList) {
        	List<Taxon> taxa = this.getSearchIndex().get(d);
        	
    		while (!taxa.isEmpty()) {
				Taxon taxon = determineSimilarity(d, taxa.remove(0));
				if (!(taxon == null)) taxaTempList.add(taxon);
    		}
    			
			//Extract the taxa included in each of the retrieved value descriptors
            addToCurrentTaxonSolutionDescription(d);
            List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxaTempList);
            getCurrentTaxonSolutionDescription().clear();
            
            while(ps.isEmpty() != true)
                addToPossibleSolutions(ps.remove(0));
        }
    }
    
    /**
     * Since this automaton searches for taxa one Descriptor at a time, at the end of the process
     * there may exist a number of possible solutions (with one Descriptor solution descriptions)
     * refering to:
     * a) the same taxon or b) a successor taxon of another possible solution.  What this method does is to
     * place the solution descriptions of several possible solutions (that refer to the same taxon)
     * in a single one, and to inherit the solution description of a predecessor possible-solution taxon.
     * Precondition: (self taxonList is NOT empty)
     * Automaton reference: C
     * @see Define method name.
     * @param my parameters list
     * @return -1: error condition. All posible solutions' solution description MUST consist of ONE 
     * Descriptor. The reason for this is that since earch search was done one Descriptor at a 
     * time, then all possible solutions must contain exactly one Descriptor in their solution
     * description; null - if the precondition is not met; self - the process ran OK.
     */
    public void compressPossibleSolutions(){
        List<PossibleSolution> tempList = new ArrayList<PossibleSolution>();
        List<Descriptor> inheritedDescription = new ArrayList<Descriptor>();

        while (this.getPossibleSolutions().isEmpty() != true){
            PossibleSolution ps = this.getPossibleSolutions().remove(0);

            int i = 0;
            while (i < getPossibleSolutions().size()){
                //get the next possible solution to compare against
                PossibleSolution compSolution = getPossibleSolutions().get(i);

                //Determine if the current possible solution's attribute is different from the compare possible solution's attribute
                if (ps.getSolutionDescription().get(0).getAttribute()
                		.equals(compSolution.getSolutionDescription().get(0).getAttribute())){
                    i += 1;
                } else {
                    //Check if the proposed solutions are the same object
                    if (ps.getSolution().equals(compSolution.getSolution())) {
                        //Inherit the compare solutions solutionDescription and remove it from the taxonList
                        while (compSolution.getSolutionDescription().isEmpty() != true)
                            inheritedDescription.add(compSolution.getSolutionDescription().remove(0));

                        getPossibleSolutions().remove(i);
                    } else {//At this point, ps and compSolution are different taxa
                        //Check if ps is a successor of compSolution
                        if (((Taxon)ps.getSolution()).isSuccessorOf((Taxon)compSolution.getSolution())){
                            //ps inherits compSolution's description
                            for (Descriptor d: compSolution.getSolutionDescription()){
                                inheritedDescription.add(d);
                            }
                        }
                        i += 1;
                    }
                }
            }
            
            //Place the current possible solution in a temporary list
            while (inheritedDescription.isEmpty() != true)
                ps.getSolutionDescription().add(inheritedDescription.remove(0));
                        
            tempList.add(ps);
        }
        
        //Put all processed taxa back in the taxonList
        while (tempList.isEmpty() != true)
        	getPossibleSolutions().add(tempList.remove(0));
    }
    
    /**
     * Determines the similarity range between aSAVDescriptor's value and aTaxon's value
     * weight list.  If there exists a range of similrity, it is checked against the accepted ranges.
     * Automaton reference: DS
     * @see Define method name.
     * @param my parameters list
     * @return nil : if there is no similarity; aTaxon : if there was an acceptable degree of similarity.
     */
    public Taxon determineSimilarity(Descriptor aDescriptor, Taxon aTaxon){
        Map<Object, Double> weightedValues = aTaxon.retriveValuesUsing(aDescriptor.getStructure(),
        		aDescriptor.getAttribute());
        
        SimilarityDegree similarity = SimilarityAssessor.similarityRangeOf(aDescriptor.getValue(),
        		weightedValues);
        
        if (EnumSet.range(minSimilarityDegree, SimilarityDegree.IGUAL).contains(similarity) != true)
        	return null;
        
        return aTaxon;
    }
}
