/*
 * @see "Categoría output de SUKIA Smalltalk"
 * 
 */

package system.searchAutomata.output;


import java.util.ArrayList;
import java.util.List;

import ontology.CBR.PossibleSolution;
import ontology.common.Description;


/**
 * This is the base class for search outputs done on the Case Memory or the Taxonomic Hierarchy.
 * @author pabloq
 */
public class SearchAutomatonOutput {
    private List<PossibleSolution> possibleSolutions;
    private Description unmatchedDescription;
    private Description justification;


    public SearchAutomatonOutput() {
        possibleSolutions = new ArrayList<PossibleSolution>();
        unmatchedDescription = new Description();
        justification = new Description();

    }

	/**
	 * Category adding
	 */
    public void setJustification(Description aJustificationsList){
        justification = aJustificationsList;
    }
    
    public void setPossibleSolutions(List<PossibleSolution> aPossibleSolutionList){
        possibleSolutions = aPossibleSolutionList;
    }
    
    public void setUnmatchedDescription(Description anUnmatchedDescription){
        unmatchedDescription = anUnmatchedDescription;
    }
    
	 /**
	 *Category accessing
	 */
    public Description getJustification(){
        return justification;
    }
    
    public List<PossibleSolution> getPossibleSolutions(){
        return possibleSolutions;
    }
    
    public Description getUnmatchedDescription(){
        return unmatchedDescription;
    }
    
	/**
	 * Category appending
	 */
    public void addAllToJustification(Description aJustificationList){
    	justification.addAllToConcreteDescription(aJustificationList);        
    }
    
    /*
     * 
     */
    public void addAllToPossibleSolutions(List<PossibleSolution> aPossibleSolutionsList){
        if (aPossibleSolutionsList != null) {
            possibleSolutions.addAll(aPossibleSolutionsList);
        }
    }
    
    public void addAllToUnmatchedDescription(Description anUnmatchedDescription){
    	this.unmatchedDescription.addAllToConcreteDescription(anUnmatchedDescription);
    }
}
