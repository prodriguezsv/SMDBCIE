
package system.searchAutomata;

import jade.util.leap.Iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import ontology.CBR.PossibleSolution;
import ontology.CBR.SimilarityDegree;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.RangeValue;
import ontology.common.SingleValue;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.Taxonomy;

import system.searchAutomata.output.TaxonomyAutomatonOutput;
import system.similarityAssessment.SimilarityAssessor;

/**
 *
 * @author pabloq
 */

public class TaxonomySearchAutomaton {
	private Description justification;
	private Description solutionDescription;
	private Description unmatchedDescription;
	private List<PossibleSolution> possibleSolutions;
	private TaxonomyAutomatonOutput searchOutput;
	private final Taxonomy taxonomy;
	private final SimilarityDegree minSimilarityDegree;
	private SearchStatus status;

   public TaxonomySearchAutomaton (Taxonomy searchIndex, SimilarityDegree minSimilarityDegree) {
        searchOutput = new TaxonomyAutomatonOutput();
        possibleSolutions = new ArrayList<PossibleSolution>();
        solutionDescription = new Description();
        unmatchedDescription = new Description();
        this.taxonomy = searchIndex;
        this.minSimilarityDegree = minSimilarityDegree;
        status = SearchStatus.FAIL;
   }

 /**
 *Category adding
 */

   /**
    * 
    */
	public Taxonomy getTaxonomy() {
		return taxonomy;
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
        Collections.sort(this.possibleSolutions);
    }

    /**
     * 
     * @param possibleSolutions
     */
    public void setPossibleSolutions(List<PossibleSolution> possibleSolutions){
    	        this.possibleSolutions = possibleSolutions;
    }
    
    /**
     * 
     * @return
     */
    public List<PossibleSolution> getPossibleSolutions(){
        return possibleSolutions;
    }
    
	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean addToSolutionDescription(Descriptor aDescriptor){
    	return solutionDescription.addToConcreteDescription(aDescriptor);
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean addToUnmatchedDescription(Descriptor aDescriptor){
    	return unmatchedDescription.addToConcreteDescription(aDescriptor);
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
    
    public Description getSolutionDescription(){
        return solutionDescription;
    }
    
    public Description  getUnmatchedDescription(){
        return unmatchedDescription;
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
            ps.getSolutionDescription().addAllToConcreteDescription(solutionDescription);
            psList.add(ps);
        }
        return psList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public boolean checkPrecondition(Description  aProblemDescription){
        if (aProblemDescription.getDescriptors().isEmpty()) return false;

        String sName = ((Descriptor)aProblemDescription.getDescriptors().get(0)).getStructure();
        if (aProblemDescription.getDescriptors().size() > 0) {
            for (int i = 1; i < aProblemDescription.getDescriptors().size(); i++)                
                if (sName.equals(((Descriptor)aProblemDescription.getDescriptors().get(i)).getStructure())
                		!= true) return false;
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
    	/*if (searchOutput.getJustification() != null) return;*/
    	
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(unmatchedDescription);
        status = SearchStatus.FAIL;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    protected void prepareSuccessfulOutput(){
    	/*if (searchOutput.getPossibleSolutions() != null)
    	return;*/
    	
        searchOutput.setPossibleSolutions(possibleSolutions);
        
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(unmatchedDescription);
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
    public SearchStatus beginSearch(Description aProblemDescription){
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
    public void searchPossibleSolutions(Description descriptionProblem) {
    	List<Descriptor> tempList, matchedDescriptors;
    	Description descriptors;
    	
    	tempList = new ArrayList<Descriptor>();
    	matchedDescriptors = new ArrayList<Descriptor>();
    	
    	Iterator i = descriptionProblem.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
        	descriptors = this.getTaxonomy().searchBySA(d.getStructure(), d.getAttribute());
        	
    		matchedDescriptors.clear();
    		
    		Iterator j = descriptors.getAllDescriptors();
    		
    		while (j.hasNext()) {
    			Descriptor d2 = (Descriptor) j.next(); 
				if (d.getValue() instanceof SingleValue) {
    				if (d2.getValue() instanceof RangeValue) {
    					if (((RangeValue)d2.getValue()).containsNumber(((SingleValue)d.getValue()).getValue()))
    						matchedDescriptors.add(d2);
    					continue;
    				}
				}
				
				if (d.equals(d2))
					matchedDescriptors.add(d2);
			}
			
			if (matchedDescriptors.isEmpty())
				addToUnmatchedDescription(d);
    		
    		for (Descriptor d3: matchedDescriptors) {
            	
        		if (d3.getValue() instanceof RangeValue){
        			List<Taxon> taxa = this.getTaxonomy().getDescriptorsIndex().get(d3);
                    //Value desscriptor is a range. Associate all taxa to possible solutions, place them in the taxon list

        			//Extract the taxa included in each of the retrieved value descriptors
                    addToSolutionDescription(d);
                    List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxa);
                    getSolutionDescription().clearAllDescriptors();
                    
                    while(ps.isEmpty() != true)
                        addToPossibleSolutions(ps.remove(0));
                   
                } else tempList.add(d3); //Value descriptor is not a range. Place it in a temporary list
            }
    		
    	       /*At this point, all descriptors have been verified and processed. If there are no exact-match value
            descriptors left, return*/
            if (tempList.isEmpty()) continue;
            
            List<Taxon> taxaTempList = new ArrayList<Taxon>();
            
            for (Descriptor d4: tempList) {
            	List<Taxon> taxa = this.getTaxonomy().getDescriptorsIndex().get(d4);
            	
            	for (Taxon t:taxa) {
    				Taxon taxon = determineSimilarity(d, t);
    				if (!(taxon == null)) taxaTempList.add(taxon);
        		}
        			
    			//Extract the taxa included in each of the retrieved value descriptors
                addToSolutionDescription(d);
                List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxaTempList);
                getSolutionDescription().clearAllDescriptors();
                
                while(ps.isEmpty() != true)
                    addToPossibleSolutions(ps.remove(0));
            }
            
            tempList.clear();
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
                if (((Descriptor)ps.getSolutionDescription().getDescriptors().get(0)).getAttribute()
                		.equals(((Descriptor)compSolution.getSolutionDescription().getDescriptors().get(0)).getAttribute())){
                    i += 1;
                } else {
                    //Check if the proposed solutions are the same object
                    if (ps.getSolution().equals(compSolution.getSolution())) {
                        //Inherit the compare solutions solutionDescription and remove it from the taxonList
                        while (compSolution.getSolutionDescription().getDescriptors().isEmpty() != true)
                            inheritedDescription.add(((Descriptor)compSolution.getSolutionDescription().getDescriptors().remove(0)));

                        getPossibleSolutions().remove(i);
                    } else {//At this point, ps and compSolution are different taxa
                        //Check if ps is a successor of compSolution
                        if (((Taxon)ps.getSolution()).isSuccessorOf((Taxon)compSolution.getSolution())){
                            //ps inherits compSolution's description
                        	Iterator j = compSolution.getSolutionDescription().getAllDescriptors();
                    		
                    		while (j.hasNext()) {
                    			Descriptor d = (Descriptor) j.next(); 
                                inheritedDescription.add(d);
                            }
                        }
                        i += 1;
                    }
                }
            }
            
            //Place the current possible solution in a temporary list
            while (inheritedDescription.isEmpty() != true)
                ps.getSolutionDescription().addToConcreteDescription(inheritedDescription.remove(0));
                        
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
        Map<Object, Double> weightedValues = aTaxon.retriveWeightedValues(aDescriptor.getStructure(),
        		aDescriptor.getAttribute());
        
        SimilarityDegree similarity = SimilarityAssessor.similarityRangeOf(aDescriptor.getValue(),
        		weightedValues);
        
        if (EnumSet.range(minSimilarityDegree, SimilarityDegree.IGUAL).contains(similarity) != true)
        	return null;
        
        return aTaxon;
    }
}
