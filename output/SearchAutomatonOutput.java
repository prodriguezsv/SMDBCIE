/*
 * @see "Categoría output de SUKIA Smalltalk"
 * 
 */

package output;


import java.util.List;

import main.Description;
import reasoner.PossibleSolution;
import redundantDiscriminantNet.SAVDescriptor;

/**
 * This is the base class for search outputs done on the Case Memory or the Taxonomic Hierarchy.
 * @author pabloq
 */
public class SearchAutomatonOutput {
    private List<PossibleSolution> possibleSolutions;
    private Description<SAVDescriptor> unmatchedDescription;
    private Description<SAVDescriptor> justification;


    public SearchAutomatonOutput() {
        possibleSolutions = null;
        unmatchedDescription = null;
        justification = null;

    }

	 /**
	 *Category adding
	 */
    public void setJustification(Description<SAVDescriptor> aJustificationsList){
        justification = aJustificationsList; // .copy()
    }
    
    public void setPossibleSolutions(List<PossibleSolution> aPossibleSolutionList){
        possibleSolutions = aPossibleSolutionList.subList(0, aPossibleSolutionList.size()-1);
    }
    
    public void setUnmatchedDescription(Description<SAVDescriptor> anUnmatchedDescription){
        unmatchedDescription = anUnmatchedDescription; // .copy();
    }
    
	 /**
	 *Category accessing
	 */
    public Description<SAVDescriptor> getJustification(){
        return justification;
    }
    
    public List<PossibleSolution> getPossibleSolutions(){
        return possibleSolutions;
    }
    
    public Description<SAVDescriptor> getUnmatchedDescription(){
        return unmatchedDescription;
    }
    
	 /**
	 *Category appending
	 */
    public void appendToJustification(Description<SAVDescriptor> aJustificationList){
        if (aJustificationList != null) {
            justification.addAll(aJustificationList);
        }
    }
    public void appendToPossibleSolutions(List<PossibleSolution> aPossibleSolutionsList){
        if (aPossibleSolutionsList != null) {
            possibleSolutions.addAll(aPossibleSolutionsList);
        }
    }
    
    public void appendToUnmatchedDescription(Description<SAVDescriptor> anUnmatchedDescription){
        if (anUnmatchedDescription != null) {
            for (SAVDescriptor d : anUnmatchedDescription) {
                 if (this.getSAVDescriptor(d,this.unmatchedDescription)== null){
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
    public SAVDescriptor getSAVDescriptor(SAVDescriptor aSAVDescriptor, Description<SAVDescriptor> aDescription){
        if (!(aDescription.equals(unmatchedDescription))) return null;
        
        for (SAVDescriptor d : unmatchedDescription) {
            if (d.getStructure().equals(aSAVDescriptor.getStructure()) && d.getAttribute().equals(aSAVDescriptor.getAttribute())
            		&& d.getValue().equals(aSAVDescriptor.getValue())) {
                return d;
            }
        }
        
        return null;
    }
    
    public boolean contains(SAVDescriptor aSAVDescriptor, Description<SAVDescriptor> aDescription){
        if (!(aDescription.equals(unmatchedDescription))) return false;
        
        for (SAVDescriptor d : unmatchedDescription) {
            if (d.getStructure().equals(aSAVDescriptor.getStructure()) && d.getAttribute().equals(aSAVDescriptor.getAttribute())
            		&& d.getValue().equals(aSAVDescriptor.getValue())) {
                return true;
            }
        }
        
        return false;
    }
}
