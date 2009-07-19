/*
 * @see "Categoría output de SUKIA Smalltalk"
 * 
 */

package system.searchAutomata.output;


import java.util.List;

import onthology.CBR.PossibleSolution;
import onthology.common.Description;
import onthology.common.Descriptor;


/**
 * This is the base class for search outputs done on the Case Memory or the Taxonomic Hierarchy.
 * @author pabloq
 */
public class SearchAutomatonOutput {
    private List<PossibleSolution> possibleSolutions;
    private Description<Descriptor<Object>> unmatchedDescription;
    private Description<Descriptor<Object>> justification;


    public SearchAutomatonOutput() {
        possibleSolutions = null;
        unmatchedDescription = null;
        justification = null;

    }

	 /**
	 *Category adding
	 */
    public void setJustification(Description<Descriptor<Object>> aJustificationsList){
        justification = aJustificationsList; // .copy()
    }
    
    public void setPossibleSolutions(List<PossibleSolution> aPossibleSolutionList){
        possibleSolutions = aPossibleSolutionList.subList(0, aPossibleSolutionList.size()-1);
    }
    
    public void setUnmatchedDescription(Description<Descriptor<Object>> anUnmatchedDescription){
        unmatchedDescription = anUnmatchedDescription; // .copy();
    }
    
	 /**
	 *Category accessing
	 */
    public Description<Descriptor<Object>> getJustification(){
        return justification;
    }
    
    public List<PossibleSolution> getPossibleSolutions(){
        return possibleSolutions;
    }
    
    public Description<Descriptor<Object>> getUnmatchedDescription(){
        return unmatchedDescription;
    }
    
	 /**
	 *Category appending
	 */
    public void appendToJustification(Description<Descriptor<Object>> aJustificationList){
        if (aJustificationList != null) {
            justification.addAll(aJustificationList);
        }
    }
    public void appendToPossibleSolutions(List<PossibleSolution> aPossibleSolutionsList){
        if (aPossibleSolutionsList != null) {
            possibleSolutions.addAll(aPossibleSolutionsList);
        }
    }
    
    public void appendToUnmatchedDescription(Description<Descriptor<Object>> anUnmatchedDescription){
        if (anUnmatchedDescription != null) {
            for (Descriptor<Object> d : anUnmatchedDescription) {
                 if (!this.contains(d, this.unmatchedDescription)){
                    this.unmatchedDescription.add(d);
                 }
            }
        }
    }
    
	/**
	 * Determines if a full aSAVDescriptor is already a member of aDescription. The argument aSAVDescriptor is a member of
	 * aDescriptionList when its structure, attribute and value match with the structure name, attribute name, and value of a list element.
	 * Returns: 	-1 (error state): The argument aDescriptionList IS NOT a valid list for self.
	 * nil: aSAVDescriptor IS NOT a member of aDescriptionList.
	 * not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor
	 *Category testing
	 */
    public Descriptor<Object> getDescriptor(Descriptor<Object> aDescriptor, Description<Descriptor<Object>> aDescription){
        if (!(aDescription.equals(unmatchedDescription))) return null;
        
        for (Descriptor<Object> d : unmatchedDescription) {
            if (d.getStructure().equals(aDescriptor.getStructure()) && d.getAttribute().equals(aDescriptor.getAttribute())
            		&& d.getValue().equals(aDescriptor.getValue())) {
                return d;
            }
        }
        
        return null;
    }
    
    public boolean contains(Descriptor<Object> aSAVDescriptor, Description<Descriptor<Object>> aDescription){
        if (!(aDescription.equals(unmatchedDescription))) return false;
        
        for (Descriptor<Object> d : unmatchedDescription) {
            if (d.getStructure().equals(aSAVDescriptor.getStructure()) && d.getAttribute().equals(aSAVDescriptor.getAttribute())
            		&& d.getValue().equals(aSAVDescriptor.getValue())) {
                return true;
            }
        }
        
        return false;
    }
}
