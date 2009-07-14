
package searchAutomata;

import output.TaxonAutomatonOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import domainTheory.Taxon;
import domainTheory.TaxonomicLevels;
import redundantDiscriminantNet.SAVDescriptor;
import main.Description;

/**
 *
 * @author pabloq
 */

public class TaxonSearchAutomaton {

    private List<SAVDescriptor> valueDescriptors;
    private List<SAVDescriptor> tSolutionDesc;
    private List<SAVDescriptor> tUnmatchedDesc;
    private List<Object> justification;
    private List<Taxon> taxonList;
    private SAVDescriptor tSolutionDescription;
    private SAVDescriptor tUnmatchedDescription;
    private Object searchIndex;


    private String status;
    TaxonAutomatonOutput searchOutput;
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

        Comparator  comparator = new Comparator<String>() {
            /**
            * @see Define method name.
            * @param my parameters list
            * @return my return values
            */
                public int compare(String o1, String o2) {
                    if (TaxonomicLevels.transformToIndex(o1) >= TaxonomicLevels.transformToIndex(o2)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            };
        //processList.addAll(super.possibleSolutions());
        Collections.sort(taxonList,comparator);
        valueDescriptors = new ArrayList<SAVDescriptor>();
        tSolutionDesc = new ArrayList<SAVDescriptor>();
        tUnmatchedDesc = new ArrayList<SAVDescriptor>();
        justification = new ArrayList<Object>();
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
    public void status(String aStatusValue){
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
    public void taxonList(List<Taxon> taxonList){
/**taxonList: aTaxon

	taxonList add: aTaxon.
	^self.*/
        this.taxonList.addAll(taxonList);
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean tSolutionDescription(SAVDescriptor aSAVDescriptor){
/**tSolutionDescription: aSAVDescriptor

	"Automaton reference: AtS"

	((self includes: aSAVDescriptor in: (self tSolutionDescription)) = nil)
	ifFalse: [ ^self ].

	tSolutionDesc add: aSAVDescriptor.
	^self.*/
        if (includes(aSAVDescriptor,tSolutionDescription)){
            return true;
        };
        tSolutionDesc.add(aSAVDescriptor);
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean tUnmatchedDescription(SAVDescriptor aSAVDescriptor){
/**tUnmatchedDescription: aSAVDescriptor

	"Automaton reference: AtUMD"

	((self includes: aSAVDescriptor in: (self tUnmatchedDescription)) = nil)
	ifFalse: [ ^self ].

	tUnmatchedDesc add: aSAVDescriptor.
	^self.*/
        if (includes(aSAVDescriptor,tUnmatchedDescription)){
        return true;
        }
        tUnmatchedDesc.add(aSAVDescriptor);
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean valueDescriptors(List<SAVDescriptor> aValueDescriptorList){
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
    public List<Object> justification(){
/**justification

	^justification.*/
        return justification;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object searchIndex(){
/**searchIndex

	^searchIndex.*/
        return searchIndex;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public TaxonAutomatonOutput searchOutput(){
/**searchOutput

	^searchOutput.*/
        return searchOutput;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String status(){
/**status

	^status.*/
        return status;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<Taxon> taxonList(){
/**taxonList

	^taxonList.*/
        return taxonList;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> tSolutionDescription(){
/**tSolutionDescription

	^tSolutionDesc.*/
        return tSolutionDesc;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor>  tUnmatchedDescription(){
/**tUnmatchedDescription

	^tUnmatchedDesc.*/
        return tUnmatchedDesc;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> valueDescriptors(){
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
    public List<Taxon> associateTaxaToPossibleSolutions(List<Taxon> aTaxonList){
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

        List<Taxon> psList = new ArrayList<Taxon>();
        for (Taxon tx : aTaxonList){
            PossibleSolution ps = new PossibleSolution();
            ps.solution(tx);
            ps.copy(tSolutionDescription,solutionDescription);
            psList.add(ps);

        }
        return psList;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private boolean checkPrecondition(List<SAVDescriptor> aProblemDescription){
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

	"Get the structure name of the first descriptor"
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
                if ((aProblemDescription.get(i) instanceof SAVDescriptor) != true) {return false;}
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
    private boolean prepareFailedOutput(){
/**prepareFailedOutput

	"This method is called from beginWith, and is executed whenn the automaton has failed to provide
	 a solution for the given problem description.

	 Automaton reference: PFO"

	((self searchOutput) justification = nil)
	ifFalse: [ ^self ].

	(self searchOutput) justification: (self justification).
	(self searchOutput) unmatchedDescription: (self tUnmatchedDescription).
	^self.*/

        if (searchOutput.justification() != null) {return true;}
        searchOutput.justification(justification);
        searchOutput.unmatchedDescription(tUnmatchedDescription);
        return false;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private boolean prepareSuccessfulOutputWith(aPossibleSolutionsList){
/**prepareSuccessfulOutputWith: aPossibleSolutionsList

	"Automaton reference: PSO"

	(((self searchOutput) possibleSolutions) = nil)
	ifFalse: [ ^self ].

	(self searchOutput) possibleSolutions: aPossibleSolutionsList.
	(self searchOutput) justification: (self justification).
	(self searchOutput) unmatchedDescription: (self tUnmatchedDescription).
	self status: #success.
	^self.*/
        if (searchOutput.possibleSolutions() != null) {return true;}
        searchOutput.possibleSolutions(aPossibleSolutionsList);
        searchOutput.justification(justification);
        searchOutput.unmatchedDescription(tUnmatchedDescription);
        status = "success";
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void resetList(List<SAVDescriptor> anOrderedCollection){
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
    private void searchIndex(SearchIndex aSearchIndex){
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
    public SAVDescriptor includes(SAVDescriptor aSAVDescriptor,SAVDescriptor aDescription) {
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
        for (Object desc : aDescription){
            SAVDescriptor d = (SAVDescriptor)desc;
            if ((d.getStructure().equals(aSAVDescriptor.getStructure())) &&
                    (d.getAttribute().equals(aSAVDescriptor.getAttribute())) &&
                    (d.getValue().equals(aSAVDescriptor.getValue()))
                    ){return d;}


        }
        return null;
}

}
