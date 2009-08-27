
package system.searchAutomata;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import ontology.CBR.SimilarityDegree;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.values.RangeValue;
import ontology.values.Value;

import system.PossibleSolution;
import system.searchAutomata.output.TaxonomyAutomatonOutput;
import system.similarityAssessment.SimilarityAssessor;

/**
 *
 * @author pabloq
 */

public class TaxonomySearchAutomaton {
    private List<Value> valueDescriptors;
    private List<Descriptor<Object>> justification;
    private List<PossibleSolution> possibleSolutionList;
    private List<Descriptor<Object>> tSolutionDescription;
    private List<Descriptor<Object>> tUnmatchedDescription;
    private Map<Descriptor<Object>, List<Taxon>> searchIndex;
    private SearchStatus status;
    private TaxonomyAutomatonOutput searchOutput;
    private SimilarityDegree minSimilarityDegree;

   public TaxonomySearchAutomaton (Map<Descriptor<Object>, List<Taxon>> searchIndex,
		   SimilarityDegree minSimilarityDegree) {
        searchOutput = new TaxonomyAutomatonOutput();
        possibleSolutionList = new ArrayList<PossibleSolution>();
        valueDescriptors = new ArrayList<Value>();
        tSolutionDescription = new ArrayList<Descriptor<Object>>();
        tUnmatchedDescription = new ArrayList<Descriptor<Object>>();
        this.setSearchIndex(searchIndex);
        this.minSimilarityDegree = minSimilarityDegree;
        status = SearchStatus.FAIL;
   }

 /**
 *Category adding
 */


	public Map<Descriptor<Object>, List<Taxon>> getSearchIndex() {
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
    
    public void setSearchIndex(Map<Descriptor<Object>, List<Taxon>> aSearchIndex) {
        searchIndex = aSearchIndex;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addToPossibleSolutionList(PossibleSolution aPossibleSolution){
        this.possibleSolutionList.add(aPossibleSolution);
    }

    public void setPossibleSolutionList(List<PossibleSolution> possibleSolutionList){
    	        this.possibleSolutionList = possibleSolutionList;
    	}
    
	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean addToTSolutionDescription(Descriptor<Object> aDescriptor){
        if (tSolutionDescription.contains(aDescriptor)) return true;
        tSolutionDescription.add(aDescriptor);
        return true;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean addToTUnmatchedDescription(Descriptor<Object> aDescriptor){
        if (tUnmatchedDescription.contains(aDescriptor)) return true;
        tUnmatchedDescription.add(aDescriptor);
        return true;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean setValueDescriptors(List<Value> aValueDescriptorList){
        return valueDescriptors.addAll(aValueDescriptorList);
    }

/**
 *Category accessing
 */

    public TaxonomyAutomatonOutput getSearchOutput(){
        return searchOutput;
    }
    
    public SearchStatus getStatus(){
        return status;
    }
    
    public List<PossibleSolution> getPossibleSolutionList(){
        return possibleSolutionList;
    }
    
    public List<Descriptor<Object>> getTSolutionDescription(){
        return tSolutionDescription;
    }
    
    public List<Descriptor<Object>>  getTUnmatchedDescription(){
        return tUnmatchedDescription;
    }
    
    public List<Value> getValueDescriptors(){
        return valueDescriptors;
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
            ps.setSolutionDescription(new ArrayList<Descriptor<Object>>(tSolutionDescription));
            psList.add(ps);
        }
        return psList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean checkPrecondition(List<Descriptor<Object>>  aProblemDescription){
        if (aProblemDescription.isEmpty()) return false;

        String sName = aProblemDescription.get(0).getStructure();
        if (aProblemDescription.size() > 0) {
            for (int i = 1; i < aProblemDescription.size(); i++)                
                if (sName.equals(aProblemDescription.get(i).getStructure()) != true) return false;
        }
        
        return true;
    }

	/**
	 * @see This method is called from beginWith, and is executed whenn the automaton has failed to provide a solution for the given problem description.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean prepareFailedOutput(){
        if (searchOutput.getJustification() != null) return false;
        
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(tUnmatchedDescription);
        
        return true;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SearchStatus prepareSuccessfulOutputWith(List<PossibleSolution> aPossibleSolutionsList){
        if (searchOutput.getPossibleSolutions().size() != 0)
        	return status;
        
        searchOutput.setPossibleSolutions(aPossibleSolutionsList);
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(tUnmatchedDescription);
        status = SearchStatus.SUCCESS;
        return status;
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
    public SearchStatus beginWith(List<Descriptor<Object>> aProblemDescription){
    	//Check general description precondition
        if (checkPrecondition(aProblemDescription) == false) {
        	setStatus(SearchStatus.ERROR);
        	return status;
        }
        
        if (searchPossibleSolutions(aProblemDescription) == false) {
        	setStatus(SearchStatus.ERROR);
        	return status;
        }

        if (getPossibleSolutionList().isEmpty()) {
        	prepareFailedOutput();
        	return status;
        }

        if (compress())
        	return prepareSuccessfulOutputWith(getPossibleSolutionList());
        
        prepareFailedOutput();
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
    public boolean searchPossibleSolutions(List<Descriptor<Object>> descriptionProblem){
    	boolean response = false; 
    	List<Descriptor<Object>> tempList;
    	
    	tempList = new ArrayList<Descriptor<Object>>();
        for (Descriptor<Object> d: descriptionProblem) {
        	List<Taxon> taxa = this.getSearchIndex().get(d);
        	
        	if (taxa == null)
        		addToTUnmatchedDescription(d);
        	else {
        		if (d.getValue() instanceof RangeValue){
                    //Value desscriptor is a range. Associate all taxa to possible solutions, place them in the taxon list

        			//Extract the taxa included in each of the retrieved value descriptors
                    addToTSolutionDescription(d);
                    List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxa);
                    getTSolutionDescription().clear();
                    while(ps.isEmpty() != true){
                        addToPossibleSolutionList(ps.remove(0));
                        response = true;
                    }
                } else //Value descriptor is not a range. Place it in a temporary list
                    tempList.add(d);
        	}
        }
        
        //At this point, all descriptors have been verified and processed. If there are no exact-match value descriptors left, return
        if (tempList.isEmpty()) return response;
        
        List<Taxon> taxaTempList = new ArrayList<Taxon>();
        
        for (Descriptor<Object> d: tempList) {
        	List<Taxon> taxa = this.getSearchIndex().get(d);
        	
        	if (taxa == null)
        		addToTUnmatchedDescription(d);
        	else {
        		
        		while (!taxa.isEmpty()) {
    				Taxon taxon = determineSimilarityFor(d, taxa.remove(0));
    				if (!(taxon == null)) taxaTempList.add(taxon);
        		}
        			
    			//Extract the taxa included in each of the retrieved value descriptors
                addToTSolutionDescription(d);
                List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxaTempList);
                getTSolutionDescription().clear();
                while(ps.isEmpty() != true){
                    addToPossibleSolutionList(ps.remove(0));
                    response = true;
                }
        	}
        }
        
        return response;
    }
    
    /**
     * Since this automaton searches for taxa one Descriptor<Object> at a time, at the end of the process
     * there may exist a number of possible solutions (with one Descriptor<Object> solution descriptions)
     * refering to:
     * a) the same taxon or b) a successor taxon of another possible solution.  What this method does is to
     * place the solution descriptions of several possible solutions (that refer to the same taxon)
     * in a single one, and to inherit the solution description of a predecessor possible-solution taxon.
     * Precondition: (self taxonList is NOT empty)
     * Automaton reference: C
     * @see Define method name.
     * @param my parameters list
     * @return -1: error condition. All posible solutions' solution description MUST consist of ONE 
     * Descriptor<Object>. The reason for this is that since earch search was done one Descriptor<Object> at a 
     * time, then all possible solutions must contain exactly one Descriptor<Object> in their solution
     * description; null - if the precondition is not met; self - the process ran OK.
     */
    private boolean compress(){
        if (this.getPossibleSolutionList().isEmpty()) return false;
        
        List<PossibleSolution> tempList = new ArrayList<PossibleSolution>();
        List<Descriptor<Object>> inheritedDescription = new ArrayList<Descriptor<Object>>();

        while (this.getPossibleSolutionList().isEmpty() != true){
            PossibleSolution ps = this.getPossibleSolutionList().get(0);
            if (ps.getSolutionDescription().size() > 1) return false;

            int i = 0;
            while (i <= getPossibleSolutionList().size()){
                //get the next possible solution to compare against
                PossibleSolution compSolution = getPossibleSolutionList().get(i);
                if (compSolution.getSolutionDescription().size() > 1) return false;

                //Determine if the current possible solution's attribute is different from the compare possible solution's attribute
                if (ps.getSolutionDescription().get(0).getAttribute()
                		.equals(compSolution.getSolutionDescription().get(0).getAttribute())){
                    i += 1;
                } else {
                    //Check if the proposed solutions are the same object
                    if (ps.getSolution().equals(compSolution.getSolution())) {
                        //Inherit the compare solutions solutionDescription and remove it from the taxonList
                        while (compSolution.getSolutionDescription().isEmpty() != true){
                            inheritedDescription.add(compSolution.getSolutionDescription().remove(0));
                        }
                        
                        getPossibleSolutionList().remove(i);
                    } else {//At this point, ps and compSolution are different taxa
                        //Check if ps is a successor of compSolution
                        if (((Taxon)ps.getSolution()).isSuccessorOf((Taxon)compSolution.getSolution())){
                            //ps inherits compSolution's description
                            for (Descriptor<Object> d: compSolution.getSolutionDescription()){
                                inheritedDescription.add(d);
                            }
                            i += 1;
                        }
                    }
                }
            }
            
            //Place the current possible solution in a temporary list
            while (inheritedDescription.isEmpty() != true){
                ps.getSolutionDescription().add(inheritedDescription.remove(0));
            }
            
            tempList.add(ps);
            ps = null;
        }
        
        //Put all processed taxa back in the taxonList
        while (tempList.isEmpty() != true){
        	getPossibleSolutionList().add(tempList.remove(0));
        }
        
        return true;
    }
    
    /**
     * Determines the similarity range between aSAVDescriptor's value and aTaxon's value
     * weight list.  If there exists a range of similrity, it is checked against the accepted ranges.
     * Automaton reference: DS
     * @see Define method name.
     * @param my parameters list
     * @return nil : if there is no similarity; aTaxon : if there was an acceptable degree of similarity.
     */
        public Taxon determineSimilarityFor(Descriptor<Object> aDescriptor,Taxon aTaxon){
            Map<Object, Double> weightedValues = aTaxon.retriveValuesUsing(aDescriptor.getStructure(),
            		aDescriptor.getAttribute());
            
            SimilarityDegree similarity = SimilarityAssessor.similarityRangeOf(aDescriptor.getValue(),
            		weightedValues);
            
            if (EnumSet.range(minSimilarityDegree, SimilarityDegree.IGUAL).contains(similarity) != true)
            	return null;
            
            return aTaxon;
        }
}
