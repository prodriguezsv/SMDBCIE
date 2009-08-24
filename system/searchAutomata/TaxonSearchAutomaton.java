
package system.searchAutomata;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.values.Value;

import system.PossibleSolution;
import system.searchAutomata.output.TaxonAutomatonOutput;

/**
 *
 * @author pabloq
 */

public class TaxonSearchAutomaton {
    private List<Value> valueDescriptors;
    private List<Descriptor<Object>> justification;
    private List<PossibleSolution> taxonList;
    private List<Descriptor<Object>> tSolutionDescription;
    private List<Descriptor<Object>> tUnmatchedDescription;
    private List<Descriptor<Object>> searchIndex;
    private String status;
    private TaxonAutomatonOutput searchOutput;

   public TaxonSearchAutomaton (){

        searchOutput = new TaxonAutomatonOutput();
        taxonList = new ArrayList<PossibleSolution>();
        valueDescriptors = new ArrayList<Value>();
        tSolutionDescription = new ArrayList<Descriptor<Object>>();
        tUnmatchedDescription = new ArrayList<Descriptor<Object>>();

        /*
         * #fail - the search was unsuccessful. This is the default value.
         * #success - at least one possible solution was found.
	 * #cancel - the user canceled the search process.
         * #error - a processing error occurred.
         */
        status = "fail";
}

 /**
 *Category adding
 */


/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
   /**
    * The possible values for the argument aStatusValue are:
    * fail - the search was unsuccessful. This is the default value.
    * success - at least one possible solution was found.
    * cancel - the user canceled the search process.
    * error - a processing error occurred.
    */
    public void setStatus(String aStatusValue){
        status = aStatusValue;
}
    public void setSearchIndex( List<Descriptor<Object>> aSearchIndex) {
        searchIndex = aSearchIndex;
    }

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void addTaxonList(PossibleSolution aTaxon){
        this.taxonList.add(aTaxon);
}

    public void setTaxonList(List<PossibleSolution> taxonList){
    	        this.taxonList = taxonList;
    	}
    
/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean setTSolutionDescription(Descriptor<Object> aDescriptor){
        if (includes(aDescriptor,tSolutionDescription)) return true;
        tSolutionDescription.add(aDescriptor);
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean setTUnmatchedDescription(Descriptor<Object> aDescriptor){
        if (includes(aDescriptor,tUnmatchedDescription)) return true;
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

//    public List<Descriptor<Object>> getJustification(){
//        return justification;
//}
    public TaxonAutomatonOutput getSearchOutput(){
        return searchOutput;
}
    public String getStatus(){
        return status;
}
    public List<PossibleSolution> getTaxonList(){
        return taxonList;
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
            //TODO check this!!
            ps.setSolutionDescription(tSolutionDescription);
            //ps.copy(tSolutionDescription, ps.getSolutionDescription());
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
        // no se si apltestCheckPreconditionica aca, ya que es una lista d SAVDescriptor, no de objetos
        //(aProblemDescription at: 1) class name = (SAVDescriptor getClassName)
        String sName = aProblemDescription.get(0).getStructure();
        if (aProblemDescription.size()>0){
            for (int i = 1;(i<aProblemDescription.size());i++){
                
                //TODO ver si hay que chequear si es un descriptor normal o de heuristico

                if ((aProblemDescription.get(i) instanceof Descriptor) != true) {return false;}
                
                if (sName.equals(aProblemDescription.get(i).getStructure()) != true) {return false;}

            }
        
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
    public boolean prepareSuccessfulOutputWith(List<PossibleSolution> aPossibleSolutionsList){
        if (searchOutput.getPossibleSolutions().size() != 0) return true;
        searchOutput.setPossibleSolutions(aPossibleSolutionsList);
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(tUnmatchedDescription);
        status = "success";
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    // Ojo
    @SuppressWarnings("unchecked")
	public void resetList(List  vdList){
        vdList.clear();
}

///**
// * @see Define method name.
// * @param my parameters list
// * @return my return values
// */
//    public void setSearchIndex(List<Descriptor<Object>> aSearchIndex){
//        searchIndex = aSearchIndex;
//}



 /**
 *Category testing
 */


/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean includes(Descriptor<Object> aDescriptor, List<Descriptor<Object>>  aDescription) {
/**includes: aSAVDescriptor in: aDescription

	"Determines if aSAVDescriptor is already a member of aDescriptionList. The argument aSAVDescriptor is a member of
	aDescriptionList when its structure name, its attribute name, and its value match with the structure, attribute, and value
	of a list element.
	Returns: 	-1 (error state): The argument aDescriptionList IS NOT a valid list for self.
				nil: aSAVDescriptor IS NOT a member of aDescriptionList.
				not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor"

	| d |

	"First step: make sure that the process is executed against one of my lists"
	((aDescription = (self tSolutionDescription)) |
	(aDescription = (self tUnmatchedDescription)))
	ifFalse: [ ^(-1) ].

	1 to: (aDescription size) do:
	[:i |
		d := aDescription at: i.

		((d structure = aSAVDescriptor structure) &amp; (d attribute = aSAVDescriptor attribute) &amp; (d value = aSAVDescriptor value))
		ifTrue: [ ^d ].
	].

	^nil.*/
        if (( (aDescription.equals(tSolutionDescription)) ||
                (aDescription.equals(tUnmatchedDescription)))!=true) return false;
        for (Descriptor<Object> d : aDescription){
            if ((d.getStructure().equals(aDescriptor.getStructure())) &&
                    (d.getAttribute().equals(aDescriptor.getAttribute())) &&
                    (d.getValue().equals(aDescriptor.getValue()))
                    ) return true;


        }
        return false;
}

}
