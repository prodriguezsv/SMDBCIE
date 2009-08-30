/*
 * @see "Categoría output de SUKIA Smalltalk"
 * 
 */

package system.searchAutomata.output;


import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;
import system.PossibleSolution;


/**
 * This is the base class for search outputs done on the Case Memory or the Taxonomic Hierarchy.
 * @author pabloq
 */
public class SearchAutomatonOutput {
    private List<PossibleSolution> possibleSolutions;
    private List<Descriptor> unmatchedDescription;
    private List<Descriptor> justification;


    public SearchAutomatonOutput() {
        possibleSolutions = new ArrayList<PossibleSolution>();
        unmatchedDescription = new ArrayList<Descriptor>();
        justification = new ArrayList<Descriptor>();

    }

	 /**
	 *Category adding
	 */
    public void setJustification(List<Descriptor> aJustificationsList){
        justification = aJustificationsList; // .copy()
    }
    
    public void setPossibleSolutions(List<PossibleSolution> aPossibleSolutionList){
        possibleSolutions.clear();
        possibleSolutions.addAll(aPossibleSolutionList);
//        possibleSolutions = aPossibleSolutionList.subList(0, aPossibleSolutionList.size());
    }
    
    public void setUnmatchedDescription(List<Descriptor> anUnmatchedDescription){
        unmatchedDescription = anUnmatchedDescription; // .copy();
    }
    
	 /**
	 *Category accessing
	 */
    public List<Descriptor> getJustification(){
        return justification;
    }
    
    public List<PossibleSolution> getPossibleSolutions(){
        return possibleSolutions;
    }
    
    public List<Descriptor> getUnmatchedDescription(){
        return unmatchedDescription;
    }
    
	 /**
	 *Category appending
	 */
    public void appendToJustification(List<Descriptor> aJustificationList){
        if (aJustificationList != null) {
            justification.addAll(aJustificationList);
        }
    }
    public void appendToPossibleSolutions(List<PossibleSolution> aPossibleSolutionsList){
        if (aPossibleSolutionsList != null) {
            possibleSolutions.addAll(aPossibleSolutionsList);
        }
    }
    
    public void appendToUnmatchedDescription(List<Descriptor> anUnmatchedDescription){
        if (anUnmatchedDescription != null) {
            for (Descriptor d : anUnmatchedDescription) {
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
    public Descriptor getDescriptor(Descriptor aDescriptor, List<Descriptor> aDescription){
        if (!(this.unmatchedDescription.equals(aDescription))) return null;
        
        for (Descriptor d : unmatchedDescription) {
            if (d.getStructure().equals(aDescriptor.getStructure()) && d.getAttribute().equals(aDescriptor.getAttribute())
            		&& d.getValue().equals(aDescriptor.getValue())) {
                return d;
            }
        }
        
        return null;
    }
    
    public boolean contains(Descriptor aSAVDescriptor, List<Descriptor> aDescription){
        if (!(aDescription.equals(unmatchedDescription))) return false;
        
        for (Descriptor d : unmatchedDescription) {
            if (d.getStructure().equals(aSAVDescriptor.getStructure()) && d.getAttribute().equals(aSAVDescriptor.getAttribute())
            		&& d.getValue().equals(aSAVDescriptor.getValue())) {
                return true;
            }
        }
        
        return false;
    }
}
