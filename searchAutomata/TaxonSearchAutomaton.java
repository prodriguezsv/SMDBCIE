
package searchAutomata;

import java.util.ArrayList;
import java.util.List;

import output.TaxonAutomatonOutput;
import domainTheory.Taxon;
import domainTheory.values.ValueDescriptor;
import reasoner.PossibleSolution;
import redundantDiscriminationNet.Index;
import main.Description;
import main.Descriptor;

/**
 *
 * @author pabloq
 */

public class TaxonSearchAutomaton {
    private List<ValueDescriptor> valueDescriptors;
    private Description<Descriptor<Object>> tSolutionDesc;
    private Description<Descriptor<Object>> tUnmatchedDesc;
    private Description<Descriptor<Object>> justification;
    private List<Taxon> taxonList;
    private Description<Descriptor<Object>> tSolutionDescription;
    private Description<Descriptor<Object>> tUnmatchedDescription;
    private Index searchIndex;
    private String status;
    private TaxonAutomatonOutput searchOutput;
/**
<name>TaxonSearchAutomaton</name>
<environment>Smalltalk</environment>
<super>Core.Object</super>
<private>false</private>
<indexed-type>none</indexed-type>
<inst-vars>searchIndex valueDescriptors taxonList tSolutionDesc tUnmatchedDesc justification searchOutput status </inst-vars>
<class-inst-vars></class-inst-vars>
<imports></imports>
<category>CBR - Sukia Search Automata</category>
</class>
*/

 /**
 *Category initializing
 */

/**
 * @see it just create an default instance.
 * @param nothing.
 * @return nothing.
 */
    public TaxonSearchAutomaton (){
/**initialize

	self newOutput.

	"taxonList: list of [Taxon] possible solutions, ordered by taxonomic level: from the most
	 specific level the the most general. The main reason is that the method self compress checks
	 for hierarchy (using Taxon isSuccessorOf), and temporarily removes items from taxonList. So,
	 it's convenient to process the most specific taxa first"
	taxonList := SortedCollection new.
	taxonList sortBlock: [ :x :y |
							(TaxonomicLevels transformToIndex: (x level)) &gt;=
							(TaxonomicLevels transformToIndex: (y level)) ].

	valueDescriptors := OrderedCollection new.
	tSolutionDesc := OrderedCollection new.
	tUnmatchedDesc := OrderedCollection new.
	justification := OrderedCollection new.

	"The instance variable status indicates the search status at the end of the process.
	 The possible values it may have are:
	#fail - the search was unsuccessful. This is the default value.
	#success - at least one possible solution was found.
	#cancel - the user canceled the search process.
	#error - a processing error occurred."
	self status: #fail.

	^self.*/
        newOutput();
        taxonList = new ArrayList<Taxon>();

//        Comparator  comparator = new Comparator<String>() {
//            /**
//            * @see Define method name.
//            * @param my parameters list
//            * @return my return values
//            */
//                public int compare(String o1, String o2) {
//                    if (TaxonomicLevels.transformToIndex(o1) >= TaxonomicLevels.transformToIndex(o2)) {
//                        return 1;
//                    } else {
//                        return 0;
//                    }
//                }
//            };
//        //processList.addAll(super.possibleSolutions());
//        Collections.sort(taxonList,comparator);

        
        valueDescriptors = new ArrayList<ValueDescriptor>();
        tSolutionDesc = new Description<Descriptor<Object>>();
        tUnmatchedDesc = new Description<Descriptor<Object>>();
        //justification = new ArrayList<SAVDescriptor>();
        status = "fail";
}

/**
 * @see nothing
 * @param my parameters list
 * @return my return values
 */
    public void newOutput(){
/**newOutput

	^(searchOutput := TaxonAutomatonOutput new).*/
        searchOutput = new TaxonAutomatonOutput();
}



 /**
 *Category adding
 */


/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void setStatus(String aStatusValue){
/**status: aStatusValue

	"The possible values for the argument aStatusValue are:
	#fail - the search was unsuccessful. This is the default value.
	#success - at least one possible solution was found.
	#cancel - the user canceled the search process.
	#error - a processing error occurred."

	status := aStatusValue.
	^self.*/
        status = aStatusValue;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void addTaxonList(Taxon taxonList){
/**taxonList: aTaxon

	taxonList add: aTaxon.
	^self.*/
        this.taxonList.add(taxonList);
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean setTSolutionDescription(Descriptor<Object> aDescriptor){
/**tSolutionDescription: aSAVDescriptor

	"Automaton reference: AtS"

	((self includes: aSAVDescriptor in: (self tSolutionDescription)) = nil)
	ifFalse: [ ^self ].

	tSolutionDesc add: aSAVDescriptor.
	^self.*/
        if (includes(aDescriptor,tSolutionDescription) != null){
            return true;
        };
        tSolutionDesc.add(aDescriptor);
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean setTUnmatchedDescription(Descriptor<Object> aDescriptor){
/**tUnmatchedDescription: aSAVDescriptor

	"Automaton reference: AtUMD"

	((self includes: aSAVDescriptor in: (self tUnmatchedDescription)) = nil)
	ifFalse: [ ^self ].

	tUnmatchedDesc add: aSAVDescriptor.
	^self.*/
        if (includes(aDescriptor,tUnmatchedDescription) != null){
        return true;
        }
        tUnmatchedDesc.add(aDescriptor);
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean setValueDescriptors(List<ValueDescriptor> aValueDescriptorList){
/**valueDescriptors: aValueDescriptorList

	[ aValueDescriptorList isEmpty ]
	whileFalse: [ valueDescriptors add: (aValueDescriptorList removeFirst) ].

	^self.*/
        return valueDescriptors.addAll(aValueDescriptorList);
}



 /**
 *Category accessing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Description<Descriptor<Object>> getJustification(){
/**justification

	^justification.*/
        return justification;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object getSearchIndex(){
/**searchIndex

	^searchIndex.*/
        return searchIndex;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public TaxonAutomatonOutput getSearchOutput(){
/**searchOutput

	^searchOutput.*/
        return searchOutput;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String getStatus(){
/**status

	^status.*/
        return status;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<Taxon> getTaxonList(){
/**taxonList

	^taxonList.*/
        return taxonList;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Description<Descriptor<Object>> getTSolutionDescription(){
/**tSolutionDescription

	^tSolutionDesc.*/
        return tSolutionDesc;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Description<Descriptor<Object>>  getTUnmatchedDescription(){
/**tUnmatchedDescription

	^tUnmatchedDesc.*/
        return tUnmatchedDesc;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<ValueDescriptor> getValueDescriptors(){
/**valueDescriptors

	^valueDescriptors.*/
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
/**associateTaxaToPossibleSolutions: aTaxonList

	"This method is used in conjuntion with prepareSuccessfulOutput.  The purpose
	 of this method is to create an instance of PossibleSolution for every taxon in the
	 list argument aTaxonList.

	Returns: a list of PossibleSolutions.

	 Automaton reference: none."

	| ps psList |

	psList := OrderedCollection new.

	1 to: (aTaxonList size) do:
	[ :i |
		ps := PossibleSolution new.
		ps solution: (aTaxonList at: i).
		ps copy: (self tSolutionDescription) to: (ps solutionDescription).
		psList add: ps.
	].

	^psList.*/

        List<PossibleSolution> psList = new ArrayList<PossibleSolution>();
        for (Taxon tx : aTaxonList){
            PossibleSolution ps = new PossibleSolution();
            ps.setSolution(tx);
            ps.copy(tSolutionDescription, ps.getSolutionDescription());
            psList.add(ps);

        }
        return psList;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean checkPrecondition(Description<Descriptor<Object>>  aProblemDescription){
/**checkPrecondition: aProblemDescription

	"beginWith Check:
	 1. aProblemDescription is a non-empty set of SAVDescriptors.
	 2. For all s1, s2::SAVDescriptor in aProblemDescription : (s1 structure) = (s2 structure).

	Returns: self - OK.
			  nil - Precondition failed."

	| sName |

	"Check part 1. of precondition"
	(aProblemDescription isEmpty)
	ifTrue: [ ^nil ].

	"Check part 2. of precondition"
	(aProblemDescription at: 1) class name = (SAVDescriptor getClassName)
	ifFalse: [ ^nil ].

	"get the structure name of the first descriptor"
	sName := (aProblemDescription at: 1) structure.

	"Check the precondition for the rest of the elements"
	((aProblemDescription size) &gt; 1)
	ifTrue: [
		2 to: (aProblemDescription size) do:
		[:i |
			((aProblemDescription at: i) class name = (SAVDescriptor getClassName)) ifFalse: [ ^nil ].
		      (sName = ((aProblemDescription at: i) structure)) ifFalse: [ ^nil ] ].
	].

	^self.*/
        if (aProblemDescription.size()<1){return false;}

        // no se si aplica aca, ya que es una lista d SAVDescriptor, no de objetos
        //(aProblemDescription at: 1) class name = (SAVDescriptor getClassName)
        String sName = aProblemDescription.get(0).getStructure();
        if (aProblemDescription.size()>0){
            for (int i = 1;(i<aProblemDescription.size());i++){
                if ((aProblemDescription.get(i) instanceof Descriptor) != true) {return false;}
                if (sName.equals(aProblemDescription.get(i).getStructure()) != true) {return false;}

            }
        
        }
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean prepareFailedOutput(){
/**prepareFailedOutput

	"This method is called from beginWith, and is executed whenn the automaton has failed to provide
	 a solution for the given problem description.

	 Automaton reference: PFO"

	((self searchOutput) justification = nil)
	ifFalse: [ ^self ].

	(self searchOutput) justification: (self justification).
	(self searchOutput) unmatchedDescription: (self tUnmatchedDescription).
	^self.*/

        if (searchOutput.getJustification() != null) {return true;}
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(tSolutionDescription);
        return false;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean prepareSuccessfulOutputWith(List<PossibleSolution> aPossibleSolutionsList){
/**prepareSuccessfulOutputWith: aPossibleSolutionsList

	"Automaton reference: PSO"

	(((self searchOutput) possibleSolutions) = nil)
	ifFalse: [ ^self ].

	(self searchOutput) possibleSolutions: aPossibleSolutionsList.
	(self searchOutput) justification: (self justification).
	(self searchOutput) unmatchedDescription: (self tUnmatchedDescription).
	self status: #success.
	^self.*/
        if (searchOutput.getPossibleSolutions() != null) {return true;}
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
    public void resetList(Description<Descriptor<Object>>  anOrderedCollection){
/**resetList: anOrderedCollection

	[ anOrderedCollection isEmpty ]
	whileFalse: [ anOrderedCollection removeFirst ].

	^self.*/
        anOrderedCollection.clear();
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void setSearchIndex(Index aSearchIndex){
/**searchIndex: aSearchIndex

	searchIndex := aSearchIndex.
	^self.*/
        searchIndex = aSearchIndex;
}



 /**
 *Category testing
 */


/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Descriptor<Object> includes(Descriptor<Object> aDescriptor, Description<Descriptor<Object>>  aDescription) {
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
                (aDescription.equals(tUnmatchedDescription)))!=true) {return null;}
        for (Descriptor<Object> d : aDescription){
            if ((d.getStructure().equals(aDescriptor.getStructure())) &&
                    (d.getAttribute().equals(aDescriptor.getAttribute())) &&
                    (d.getValue().equals(aDescriptor.getValue()))
                    ){return d;}


        }
        return null;
}

}
