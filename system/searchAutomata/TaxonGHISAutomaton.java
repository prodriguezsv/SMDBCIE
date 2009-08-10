/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata;


import java.util.List;

import ontology.CBR.PossibleSolution;
import ontology.common.Descriptor;
import ontology.common.GroupingHeuristic;
import ontology.taxonomy.GroupingHeuristicIndex;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicLevels;
import ontology.values.Value;
import ontology.values.ValueDescriptor;







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
    public Object beginWith(List<Descriptor<Object>> aProblemDescription){
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

        if (searchValueDescriptors(aProblemDescription.remove(0)) == false){setStatus("error"); return null;}

        if (getTaxonList().isEmpty()){prepareFailedOutput(); return null;}

        return prepareSuccessfulOutputWith(getTaxonList());

        
}

/**
 * This method searchs for value descriptors that either match aSAVDescriptor value, or correspond
 * to a range for which aSAVDescriptor value applies.  If valueDescriptors are found, each associated
 * taxon will be set up as a possible solution. All possible solutions will be stored in taxonList.
 * Returns: -1 : if a processing error occurred.
 * nil : if no value descriptors were found.
 * self : if at least one descriptor was found.
 * Automaton reference: SVD
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean searchValueDescriptors(Descriptor<Object> aDescriptor){
        Value attrValues = groupingHeuristic.getValues();


        for (int i=0;(i <= attrValues.size());i++){
        	List<ValueDescriptor> vd = null;
            if ((aDescriptor.getValue() instanceof String) != true){
                 vd = attrValues.getRangeDescriptorsWithNumber((Double)aDescriptor.getValue(), TaxonomicLevels.getLevels().get(i));
                 if (vd == null){return false;}
                 if (vd.isEmpty()){vd = null;}
            }
            //If the list of possible value descriptors is not nil, a successful range-based descriptor search was done.
            //Place the value descriptor in the valueDescriptors instance variable
            if (vd != null){
                setValueDescriptors(vd);
            }else{
                //At this point, either: a) the argument's value is a ByteSymbol, or a range search was
                //unsuccessful. Do then an exact match search
                vd = attrValues.getSingleDescriptors(aDescriptor.getValue(), TaxonomicLevels.getLevels().get(i));
                if (vd == null){return false;}
		if (vd.isEmpty() != true){setValueDescriptors(vd);}
            }
        }
        if (getValueDescriptors().isEmpty()){ setTUnmatchedDescription(aDescriptor); return false;}

        //Extract the taxa included in each of the retrieved value descriptors
        setTSolutionDescription(aDescriptor);

        while(getValueDescriptors().isEmpty() != true){
        	ValueDescriptor vd = getValueDescriptors().remove(0);
            List<Taxon> taxa = vd.getTaxonList();
            List<PossibleSolution> ps = associateTaxaToPossibleSolutions(taxa);
            while(ps.isEmpty() != true){
                addTaxonList(ps.remove(0));
            }
        }
        return true;
    }
}
