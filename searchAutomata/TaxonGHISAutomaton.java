/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchAutomata;

import java.util.ArrayList;
import java.util.List;

import reasoner.PossibleSolution;



import domainTheory.GroupingHeuristicIndex;
import domainTheory.Taxon;
import domainTheory.GroupingHeuristic;
import redundantDiscriminantNet.SAVDescriptor;

import main.Description;
import main.Index;

import values.Value;
import values.ValueDescriptor;

/**
 * Taxon GroupingHeuristic-Index Search Automaton.
 * 1. The search is based on a problem description composed of a one-element set of SAVDescriptor.
 * 2. The form of the SAVDescriptor MUST be:
 * 	aSAVDescriptor = ( generalStructureName, groupingHeuristicName, value ), where:
 * 	 generalStructureName = name of the organism for which the Reasoner is set up (e.g., 'planta'),
 * 	 groupingHeuristicName = a valid grouping heuristic name (e.g., #esLenoso),
 * 	 value = (ByteSymbol | Number)
 * 3. The search strategy is to use the GroupingHeuristicIndex defined in the class Taxonomy.
 * @author pabloq
 */
    public class TaxonGHISAutomaton extends TaxonSearchAutomaton{

        GroupingHeuristicIndex searchIndex;
        GroupingHeuristic groupingHeuristic;
        GroupingHeuristicIndex groupingHeuristicIndex;

/*
<name>TaxonGHISAutomaton</name>
<environment>Smalltalk</environment>
<super>TaxonSearchAutomaton</super>
<private>false</private>
<indexed-type>none</indexed-type>
<inst-vars>groupingHeuristic </inst-vars>
<class-inst-vars></class-inst-vars>
<imports></imports>
<category>CBR - Sukia Search Automata</category>
*/

 /*
 *Category instance creation
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public TaxonGHISAutomaton(GroupingHeuristicIndex aGroupingHeuristicIndex) {
/*newWith: aGroupingHeuristicIndex

	| searchAutomaton |

	searchAutomaton := super new.
	searchAutomaton initializeWithIndex: aGroupingHeuristicIndex.
	^searchAutomaton.*/
        super();
        initializeWithIndex(aGroupingHeuristicIndex);
}
 /*
 *Category initializing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void initializeWithIndex(GroupingHeuristicIndex aGroupingHeuristicIndex){
/*initializeWithIndex: aGroupingHeuristicIndex

	self searchIndex: aGroupingHeuristicIndex.
	self resetGroupingHeuristic.
	^self.*/
        searchIndex =  aGroupingHeuristicIndex;
        resetGroupingHeuristic();
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void resetGroupingHeuristic(){
/*resetGroupingHeuristic

	groupingHeuristic := nil.
	^nil.*/
        groupingHeuristic = null;
}

 /*
 *Category adding
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void setGroupingHeuristic(GroupingHeuristic aGroupingHeuristic){
/*groupingHeuristic: aGroupingHeuristic

	groupingHeuristic := aGroupingHeuristic.
	^self.*/
        groupingHeuristic = aGroupingHeuristic;
}

 /*
 *Category accessing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public GroupingHeuristic getGroupingHeuristic(){
/*groupingHeuristic

	^groupingHeuristic.*/
        return groupingHeuristic;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public GroupingHeuristicIndex GetGroupingHeuristicIndex(){
/*groupingHeuristicIndex

	^self searchIndex.*/
        return searchIndex;
}



 /*
 *Category searching
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object beginWith(Description<SAVDescriptor> aProblemDescription){
/*beginWith: aProblemDescription

	"The argument aProblemDescription MUST consist of one SAVDescriptor of the form:
	 aSAVDescriptor = ( generalStructureName, groupingHeuristicName, value ), where:

	 generalStructureName = name of the organism for which the Reasoner is set up (e.g., 'planta'),
	 groupingHeuristicName = a valid grouping heuristic name (e.g., #esLenoso),
	 value = (ByteSymbol | Number)

	Returns: nil - if the precondition was not met, or an error occurred, or the process failed to find possible solutions.
			value returned by prepareSuccessfulOutputWith:

	 Automaton reference: bW"

	"Check general description precondition"
	((self checkPrecondition: aProblemDescription) = nil)
	ifTrue: [ self status: #error. ^nil ].

	"Local precondition: Make sure that aProblemDescription consists of only ONE element"
	((aProblemDescription size) &gt; 1)
	ifTrue: [ self status: #error. ^nil ].

	"Search the corresponding grouping heuristic in the grouping heuristic index"
	self groupingHeuristic: (self groupingHeuristicIndex groupingHeuristicWith: ((aProblemDescription first) attribute)).
	(self groupingHeuristic = nil) ifTrue: [ self prepareFailedOutput. ^nil ].

	((self searchValueDescriptors: (aProblemDescription removeFirst)) = -1)
	ifTrue: [ self status: #error. ^nil ].

	(self taxonList isEmpty)
	ifTrue: [ self prepareFailedOutput. ^nil. ].

	^(self prepareSuccessfulOutputWith: (self taxonList)).*/

	//Check general description precondition
        if (checkPrecondition(aProblemDescription) == false){setStatus("error"); return null;}
        
        //Local precondition: Make sure that aProblemDescription consists of only ONE element
        if (aProblemDescription.size()>0){setStatus("error"); return null;}

        //Search the corresponding grouping heuristic in the grouping heuristic index
        setGroupingHeuristic(groupingHeuristicIndex.getGroupingHeuristic(aProblemDescription.get(0).getAttribute()));
        
        if (groupingHeuristic == null){prepareFailedOutput(); return null;}

        if (searchValueDescriptors(aProblemDescription.remove(0)) == -1){setStatus("error"); return null;}

        if (getTaxonList().isEmpty()){prepareFailedOutput(); return null;}

        return prepareSuccessfulOutputWith(getTaxonList());

        
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public int searchValueDescriptors(SAVDescriptor aSAVDescriptor){
        
/*searchValueDescriptors: aSAVDescriptor

	"This method searchs for value descriptors that either match aSAVDescriptor value, or correspond
	 to a range for which aSAVDescriptor value applies.  If valueDescriptors are found, each associated
	 taxon will be set up as a possible solution. All possible solutions will be stored in taxonList.

	Returns: -1 : if a processing error occurred.
			 nil : if no value descriptors were found.
			 self : if at least one descriptor was found.

	Automaton reference: SVD"

	| bSymbol attrValues i vd taxa |

	"This variable is set just to test if aSAVDescriptor value is a ByteSymbol"
	bSymbol := ByteSymbol new: 1.

	attrValues := (self groupingHeuristic values).

	i := 1.
	[ i &lt;= (attrValues size) ]
	whileTrue: [

		"Set the list of possible value descriptors to nil"
		vd := nil.

		"If the argument's value is not ByteSymbol, then it MUST be a number. If so, search for
		 a match using value descriptor ranges. If the search was unsuccessful, reset vd to nil"
		((aSAVDescriptor value) class name = bSymbol class name)
		ifFalse: [
			vd := attrValues numberInRange: (aSAVDescriptor value) atLevel: i.
			(vd = nil) ifTrue: [ ^-1 ].
			(vd isEmpty) ifTrue: [ vd := nil ].
		].

		"If the list of possible value descriptors is not nil, a successful range-based descriptor search was done.
		 Place the value descriptor in the valueDescriptors instance variable"
		(vd = nil)
		ifFalse: [ self valueDescriptors: vd ]
		ifTrue: [
			"At this point, either: a) the argument's value is a ByteSymbol, or a range search was
			 unsuccessful. Do then an exact match search"
			vd := attrValues value: (aSAVDescriptor value) in: i.
			(vd = nil) ifTrue: [ ^-1 ].
			(vd isEmpty) ifFalse: [ self valueDescriptors: vd ].
		].

		i := i + 1.

	].    "END [ i &lt;= (attrValues size) ] whileTrue:"

	(self valueDescriptors isEmpty)
	ifTrue: [ self tUnmatchedDescription: aSAVDescriptor. ^nil ].

	"Extract the taxa included in each of the retrieved value descriptors"
	self tSolutionDescription: aSAVDescriptor.
	[ self valueDescriptors isEmpty ]
	whileFalse: [

		vd := (self valueDescriptors removeFirst).
		taxa := vd taxonList.
		taxa := self associateTaxaToPossibleSolutions: taxa.
		[ taxa isEmpty ] whileFalse: [ self taxonList: (taxa removeFirst) ].

	].     "END [ self valueDescriptors isEmpty ] whileFalse:"

	^self.*/
        Value attrValues = groupingHeuristic.getValues();


        for (int i=0;(i <= attrValues.size());i++){
            List<SAVDescriptor> vd = null;
            if ((aSAVDescriptor.getValue() instanceof String) != true){
                 vd = attrValues.getValueDescriptors(aSAVDescriptor.getValue(), i);
                 if (vd == null){return -1;}
                 if (vd.isEmpty()){vd = null;}
            }
            //If the list of possible value descriptors is not nil, a successful range-based descriptor search was done.
            //Place the value descriptor in the valueDescriptors instance variable
            if (vd != null){
                setValueDescriptors(vd);
            }else{
                //At this point, either: a) the argument's value is a ByteSymbol, or a range search was
                //unsuccessful. Do then an exact match search
                vd = attrValues.getValueDescriptors(aSAVDescriptor.getValue(), i);
                if (vd == null){return -1;}
		if (vd.isEmpty() != true){setValueDescriptors(vd);}
            }
        }
        if (getValueDescriptors().isEmpty()){ setTUnmatchedDescription(aSAVDescriptor); return null;}

        //Extract the taxa included in each of the retrieved value descriptors
        setTSolutionDescription(aSAVDescriptor);

        while(getValueDescriptors().isEmpty() != true){
            SAVDescriptor vd = getValueDescriptors().remove(0);
            List<Taxon> taxa = vd.getTaxonList();
            List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxa);
            while(ps.isEmpty() != true){
                setTaxonList(ps.remove(0));
            }
        }
        return true;
}
}
