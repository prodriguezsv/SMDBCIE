/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchAutomata;

import redundantDiscriminantNet.SAVDescriptor;
import domainTheory.Taxon;
import domainTheory.Structure;
import domainTheory.Attribute;
import java.util.ArrayList;
import java.util.List;
import values.Value;

/*
 *
 * Taxon Structure-Index Search Automaton.
 * 1. The search is based on a problem description composed of a non-empty set of SAVDescriptors.
 * 2. The search strategy is to use the StructureIndex defined in the class Taxonomy.
 * @author pabloq
 */

public class TaxonSISAutomaton extends TaxonSearchAutomaton{

    private Attribute attribute;
    private Structure structure;
    private Object similarityRanges;
    private Object structureIndex;
    private String status;


/*
<name>TaxonSISAutomaton</name>
<environment>Smalltalk</environment>
<super>TaxonSearchAutomaton</super>
<private>false</private>
<indexed-type>none</indexed-type>
<inst-vars>structure attribute similarityRanges </inst-vars>
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
 public TaxonSISAutomaton(StructureIndex aStructureIndex,SimilarityRangeList aSimilarityRangeList){
 
        StructureIndex searchIndex = aStructureIndex;
        SimilarityRangeList similarityRanges = aSimilarityRangeList;
        resetStructure();
        resetAttribute();

 }
 /*
 *Category initializing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void resetAttribute(){
/*resetAttribute

	attribute := nil.
	^self.*/
        attribute = null;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void resetStructure(){
/*resetStructure

	structure := nil.
	^self.*/
        structure = null;
}

 /*
 *Category adding
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void attribute(Object anAttribute){
/*attribute: anAttribute

	attribute := anAttribute.
	^self.*/
        attribute = anAttribute;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void structure(Structure aStructure){
/*structure: aStructure

	structure := aStructure.
	^self.*/
        structure = aStructure;
}

 /*
 *Category accessing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object attribute(){
/*attribute

	^attribute.*/
        return attribute;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object similarityRanges(){
/*similarityRanges

	^similarityRanges.*/
        return similarityRanges;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Structure structure(){
/*structure

	^structure.*/
        return structure;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object structureIndex(){
/*structureIndex

	^self searchIndex.*/
        return structureIndex;
}



 /*
 *Category searching
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean beginWith(List<SAVDescriptor> aProblemDescription){
/*beginWith: aProblemDescription

	"Initial state of the search automaton.

	 Precondition:
	 1. aProblemDescription is a non-empty set of SAVDescriptors.
	 2. For all s1, s2::SAVDescriptor in aProblemDescription : (s1 structure) = (s2 structure).

	Returns: nil - if the precondition was not satisfied or the search process failed;
			 object returned by prepareSuccessfulOutputWith:

	Automaton reference: bW"

	((self checkPrecondition: aProblemDescription) = nil)
	ifTrue: [ self status: #error. ^nil ].

	"Search the corresponding structure in the structure index"
	self structure: (self structureIndex structureWith: (aProblemDescription at: 1) structure).
	(self structure = nil) ifTrue: [ self prepareFailedOutput. ^nil ].

	[ aProblemDescription isEmpty ]
	whileFalse: [
		self resetAttribute.
		self resetList: (self valueDescriptors).
		((self searchAttribute: (aProblemDescription removeFirst)) = -1)
		ifTrue: [ self status: #error. ^nil ].
	].

	(self compress = self)
	ifTrue: [ ^(self prepareSuccessfulOutputWith: (self taxonList)) ].

	self prepareFailedOutput.
	^nil.*/
        if (checkPrecondition(aProblemDescription)){status= "error"; return false;}
        structure = structureIndex.structureWith(aProblemDescription.get(0).getStructure());
        if (structure == null){prepareFailedOutput(); return false;}
        for (SAVDescriptor dt: aProblemDescription){
            resetAttribute();
            resetList(valueDescriptors);
            if (searchAttribute(dt)==false){
                status = "error";
                return false;
            }
        }
        if (compress(this)==true){
           return prepareSuccessfulOutputWith(taxonList());
        }
        return prepareFailedOutput();
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Taxon determineSimilarityFor(SAVDescriptor aSAVDescriptor,Taxon aTaxon){
/*determineSimilarityFor: aSAVDescriptor context: aTaxon

	"Determines the similarity range between aSAVDescriptor's value and aTaxon's value
	 weight list.  If there exists a range of similrity, it is checked against the accepted
	 ranges.

	 Returns: nil : if there is no similarity.
			  aTaxon : if there was an acceptable degree of similarity.

	Automaton reference: DS"

	| weightedValues similarity |

	weightedValues :=
		(((aTaxon getAnObjectWith: (aSAVDescriptor structure) in: (aTaxon SAVdescription))
		   attributeWith: (aSAVDescriptor attribute)) values at: (Attribute oneLevel)).

	similarity := SimAssessor similarityRangeOf: (aSAVDescriptor value) in: weightedValues.
	(self similarityRanges includes: similarity) ifFalse: [ ^nil ].

	^aTaxon.*/
        Object weightedValues = (aTaxon.getAnObjectWith(aSAVDescriptor.getStructure(),aTaxon.getSAVDescription())).attributeWith(aSAVDescriptor.getAttribute(),Attribute.oneLevel());
        similarity = SimAssessor.similarityRangeOf(aSAVDescriptor.getValue(),weightedValues);
        if similarityRanges.includes(similarity){return null;}
        return aTaxon;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean retrieveTaxa(SAVDescriptor aSAVDescriptor){
/*retrieveTaxa: aSAVDescriptor

	"This method extracts all taxa from the value descriptors located in the instance list variable
	 valueDescriptors.  ONLY exact-match value descriptors may exist at this point. The reason
	 is that a similarity check will be performed between the argument's value and the descriptor's
	 taxa weighted-value list; this check will not work with range value descriptors.

	 Returns: -1 : if a processing error occurred.
			 nil : if the argument's value is NOT similar to any value in the taxon's weighted value list.
		     self : if at least one taxon's weighted value list contains a similar item to the argument's value.

	 Automaton reference: RT"

	| vd taxa taxon tempList |

	tempList := OrderedCollection new.

	1 to: (self valueDescriptors size) do:
	[:i |
		vd := (self valueDescriptors at: i).
		taxa := vd taxonList.
		(taxa isEmpty) ifTrue: [ ^-1 ].

		[ taxa isEmpty ]
		whileFalse: [

			taxon := self determineSimilarityFor: aSAVDescriptor context: (taxa removeFirst).
			(taxon = nil) ifFalse: [ tempList add: taxon ].

		].    "END [taxa isEmpty] whileFalse:"

	].    "END 1 to: (self valueDescriptors size) do:"

	(tempList isEmpty) ifTrue: [ ^nil ].

	self tSolutionDescription: aSAVDescriptor.
	taxa := self associateTaxaToPossibleSolutions: tempList.
	[ taxa isEmpty ] whileFalse: [ self taxonList: (taxa removeFirst) ].
	self resetList: (self tSolutionDescription).

	^self.*/
        List<Taxon> taxa;

        List<Taxon> tempList = new ArrayList<Taxon>();
        for (SAVDescriptor vd: valueDescriptors()){
            taxa = (List<Taxon>)vd.getValue();
            if (taxa.size()<1){return false;}
            while (taxa.size()>0){
                Taxon taxon = determineSimilarityFor(aSAVDescriptor,taxa.remove(0));
                if (taxon != null){
                    tempList.add(taxon);
                }
            }

        }
        if (tempList.size() < 1) {return false;}
        tSolutionDescription(aSAVDescriptor);
        taxa = associateTaxaToPossibleSolutions(tempList);
        while (taxa.size()>0){
            taxa.remove(0);
            taxonList(taxa);
            resetList(tSolutionDescription());
        }
        return true;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String searchAttribute(SAVDescriptor aSAVDescriptor){
/*searchAttribute: aSAVDescriptor

	"Automaton reference: SA"

	self attribute: (self structure attributeWith: (aSAVDescriptor attribute)).
	((self attribute) = nil)
	ifTrue: [ self tUnmatchedDescription: aSAVDescriptor. ^nil ].

	^(self searchValueDescriptors: aSAVDescriptor).*/
        attribute = structure().getAttribute(aSAVDescriptor.getAttribute());
        if (attribute == null) {
            tUnmatchedDescription(aSAVDescriptor);
            return null;
        }
        return searchValueDescriptors(aSAVDescriptor);

}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean searchValueDescriptors(SAVDescriptor aSAVDescriptor){
/*searchValueDescriptors: aSAVDescriptor

	"This method searches for value descriptors that that do an exact match with aSAVDescriptor's values,
	 or value range-descriptors for which aSAVDescriptor value applies

	 Returns: -1 : if a processing error occurred.
			 nil : if no value descriptors were found.
			self : if range descriptors were found.
			value returned by methodRetrieveTaxa (this method is invoked for exact-match value descriptors).

	Automaton reference: SVD"

	| bSymbol attrValues i vd taxa tempList |

	"This variable is set just to test if aSAVDescriptor value is a ByteSymbol"
	bSymbol := ByteSymbol new: 1.

	attrValues := (self attribute) values.

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

	"Separate the range descriptors from the exact-match descriptors"
	tempList := OrderedCollection new.
	[ self valueDescriptors isEmpty ]
	whileFalse: [

		vd := (self valueDescriptors removeFirst).
		(vd asRange)
		ifTrue: [
			"Value desscriptor is a range. Associate all taxa to possible solutions, place them in the taxon list"
			taxa := vd taxonList.
			self tSolutionDescription: aSAVDescriptor.
			taxa := self associateTaxaToPossibleSolutions: taxa.
			self resetList: (self tSolutionDescription).
			[ taxa isEmpty ] whileFalse: [ self taxonList: (taxa removeFirst) ].
		]

		"Value descriptor is not a range. Place it in a temporary list"
		ifFalse: [ tempList add: vd ]

	].    "END [ self valueDescriptors isEmpty ]"

	"At this point, all descriptors have been verified and processed. If there are no exact-match value descriptors left, return"
	(tempList isEmpty) ifTrue: [ ^self ].

	"Remove all exact-match value descriptors from the temporary list, put them back in the valueDescriptos
	 list, and continue processing"
	self valueDescriptors: tempList.
	^(self retrieveTaxa: aSAVDescriptor).*/
        int i = 0;
        Value attrValues = attribute.getValues();
        while (attrValues.size()>0){
            //TODO check the real instance that it should be.
            List<SAVDescriptor> vd;
            if ((aSAVDescriptor.getValue() instanceof Object)!=true){

			vd = attrValues.numberInRange(aSAVDescriptor.getValue(),i);
                        if (vd == null) {return false;}
                        if (vd.isEmpty()) {vd = null;}
                
            }
            if (vd != null){
                valueDescriptors(vd);
            }else{
                vd = attrValues(aSAVDescriptor.getValue(),i);
                if (vd == null) {return false;}
                if (vd.isEmpty()!=true){valueDescriptors(vd);}
            }
            i += 1;
        }
        if (valueDescriptors().isEmpty()){
            tUnmatchedDescription(aSAVDescriptor);
            return false;
        }
	List<SAVDescriptor> tempList = new ArrayList<SAVDescriptor>();
        while (valueDescriptors().isEmpty()!=true){

		SAVDescriptor vd = valueDescriptors().remove(0);
                if (vd.asRange()){
                    List<Taxon> taxa = vd.taxonList();
                    tSolutionDescription(aSAVDescriptor);
                    taxa = associateTaxaToPossibleSolutions(taxa);
                    resetList(tSolutionDescription());
                    while(taxa.isEmpty() != true){
                        taxonList(taxa.remove(0));
                    }
                }else{
                    tempList.add(vd);
                }



		"Value descriptor is not a range. Place it in a temporary list"
		ifFalse: [ tempList add: vd ]

        }
        if (tempList.isEmpty()){return false;}

        valueDescriptors(tempList);
        return retrieveTaxa(aSAVDescriptor);
}



 /*
 *Category private
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void compress(){
/*compress

	"Since this automaton searches for taxa one SAVDescriptor at a time, at the end of the process there may
	 exist a number of possible solutions (with one SAVDescriptor solution descriptions) refering to: a) the same
	 taxon or b) a successor taxon of another possible solution.  What this method does is to place the solution
	 descriptions of several possible solutions (that refer to the same taxon) in a single one, and to inherit the
	 solution description of a predecessor possible-solution taxon.

	Precondition: (self taxonList is NOT empty)

	 Returns: -1: error condition. All posible solutions' solution description MUST consist of ONE SAVDescriptor.
				 The reason for this is that since earch search was done one SAVDescriptor at a time, then all possible
				 solutions must contain exactly one SAVDescriptor in their solution description.
			nil - if the precondition is not met.
			self - the process ran OK.

	 Automaton reference: C"

	| ps compSolution tempList i inheritedDescription |

	"Check precondition"
	(self taxonList isEmpty)
	ifTrue: [ ^nil ].

	tempList := OrderedCollection new.
	inheritedDescription := OrderedCollection new.

	"Scan the taxonList, which contains set of PossibleSolutions"
	[ self taxonList isEmpty ]
	whileFalse: [

		"Remove the next possible solution from the taxon list"
		ps := (self taxonList) removeFirst.
		(ps solutionDescription size &gt; 1) ifTrue: [ ^-1 ].

		"Compare ps against the rest of the possible solutions in taxonList"
		i := 1.
		[ i &lt;= (self taxonList size) ]
		whileTrue: [

			"Get the next possible solution to compare against"
			compSolution := ((self taxonList) at: i).
			(compSolution solutionDescription size &gt; 1) ifTrue: [ ^-1 ].

			"Determine if the current possible solution's attribute is different from the compare possible solution's attribute"
			(((ps solutionDescription) at: 1) attribute = ((compSolution solutionDescription) at: 1) attribute)
			ifTrue: [ i := i + 1 ]
			ifFalse: [

				"Check if the proposed solutions are the same object"
				(ps solution = compSolution solution)
				ifTrue: [

					"Inherit the compare solutions solutionDescription and remove it from the taxonList"
					[ compSolution solutionDescription isEmpty ]
					whileFalse: [ inheritedDescription add: (compSolution solutionDescription) removeFirst ].
					(self taxonList) removeAtIndex: i.

				]    "END (ps solution = compSolution solution) ifTrue:"

				"At this point, ps and compSolution are different taxa"
				ifFalse: [

					"Check if ps is a successor of compSolution"
					((ps solution) isSuccessorOf: (compSolution solution))
					ifTrue: [

						"ps inherits compSolution's description"
						1 to: (compSolution solutionDescription size) do:
						[:j | inheritedDescription add: ((compSolution solutionDescription) at: j) ].

					].    "END ((ps solution) isSuccessorOf: (compSolution solution)) ifTrue:"

					i := i + 1.

				].    "END (ps solution = compSolution solution) ifFalse:"

			].    "END (((ps solutionDescription) at: 1) attribute = ((compSolution ...) ifFalse:"

		].    "END [ i &lt;= (self taxonList size) ] whileTrue:"

		"Place the current possible solution in a temporary list"
		[ inheritedDescription isEmpty ] whileFalse: [ ps solutionDescription: (inheritedDescription removeFirst) ].
		tempList add: ps.
		ps := nil.

	].    "END [ self taxonList isEmpty ] whileFalse:"

	"Put all processed taxa back in the taxonList"
	[ tempList isEmpty ]
	whileFalse: [ self taxonList: (tempList removeFirst) ].

	^self.*/
}

}
