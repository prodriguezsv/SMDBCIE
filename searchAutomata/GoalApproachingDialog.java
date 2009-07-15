/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchAutomata;
import reasoner.Hypothesis;
import domainTheory.Taxonomy;
import domainTheory.Structure;
import similarityAssessment.SimRanges;
/**
 * Instances of this class are invoked when the search automatas have performed successfully (i.e., with at least one possible solution), but the taxonomic level of all the possible solutions is more general than the stated identification goal.  The purpose of this class is then, for each qualifying possible solution, establish a dialog with the user asking him/her questions about successor taxa of each solution, *all related to the descriptive element*.  As questions are positively answered, the corresponding taxon moves "one level down", approaching the stated goal.
 * This dialog stopswhen the user cancels, when all possible solutions have been processed but no positive "down-movement" was done, or when at least ONE taxon reaches the stated goal.
 * @author pabloq
 */

public class GoalApproachingDialog {

    private Hypothesis hypothesis;

 /**
 *Category instance creation
 */
/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */

    public GoalApproachingDialog(){
    }
    public GoalApproachingDialog(Object aGoal,Hypothesis aHypothesis,Taxonomy aTaxonomy,SimRanges simRangesList){
    /**newWithGoal: aGoal hypothesis: aHypothesis taxonomy: aTaxonomy similarityRanges: simRangesList

	| gad |

	"Make sure the hypothesis descriptive element is a structure"
	((aHypothesis descriptiveElement) class name = (Structure getClassName))
	ifFalse: [ ^nil ].

	gad := super new.
	gad initializeGoal: aGoal hypothesis: aHypothesis taxonomy: aTaxonomy similarityRanges: simRangesList.
	^gad.*/
        super();
        if ((aHypothesis.getDescriptiveElement() instanceof Structure) == true){
            initializeGoal(aGoal,aHypothesis,aTaxonomy,simRangesList);
        }
    }


/**<class>
<name>GoalApproachingDialog</name>
<environment>Smalltalk</environment>
<super>Core.Object</super>
<private>false<    private>
<indexed-type>none</indexed-type>
<inst-vars>goal processList OKList hypothesis status taxonomy similarityRanges </inst-vars>
<class-inst-vars></class-inst-vars>
<imports></imports>
<category>CBR - Sukia Search Automata</category>
</class>*/

 /**
 *Category accessing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetGoal(){
/**goal

	^goal.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetHypothesis(){
/**hypothesis

	^hypothesis.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetOKList(){
/**OKList

	^OKList.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetProcessList(){
/**processList

	^processList.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetSimilarityRanges(){
/**similarityRanges

	^similarityRanges.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetStatus(){
/**status

	^status.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void GetTaxonomy(){
/**taxonomy

	^taxonomy.*/
}



 /**
 *Category dialog preparation
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object chat(){
/**chat

	"The purpose of this method is to initially do all the administrative work necessary to select
	 those possible solutions (from the associated hypothesis) that are elegible for processing.
	 That is, ONLY positive cases or Taxon instances will be processed. Once a possible solution
	 is selected, it is placed in the processList.  Next, out of those previously filtered in, a second
	 selection comes about: select only those (positive cases or taxa)  possible solutions whose
	 taxonomic level is *closest to the stated identification goal*. These elements will remain in the
	 processList; the rest will be filtered out.

	Precondition: the associated hypothesis MUST have at least one possible solution.

	Returns: nil - if the precondition fails, or a processing error occurred, or processList is empty.
			 value returned by doDialog."

	| ps taxon psLevelAsIndex goalAsIndex |

	"Check precondition"
	(self hypothesis possibleSolutions isEmpty)
	ifTrue: [ ^nil ].

	"Transform the stated identification goal to a numeric value"
	goalAsIndex := (TaxonomicLevels transformToIndex: (self goal)).

	"Scan the associated hypothesis possible solutions list"
	1 to: (self hypothesis possibleSolutions size) do:
	[:i |

		"Get the next possible solution"
		ps := (self hypothesis possibleSolutions) at: i.

		"Transform the possible solution level to a numeric value"
		psLevelAsIndex := (TaxonomicLevels transformToIndex: (ps level)).

		"If the possible solution's level is equal to, or more specific than the goal, ignore it"
		(psLevelAsIndex &gt;= goalAsIndex)
		ifFalse: [

			"If the possible solution is a taxon, place it in the process list. Else, it must be a case. Before placing
			 the possible solution in the process list, find its corresponding taxon in the associated taxonomy"
			(ps solution class name = Taxon getClassName)
			ifTrue: [ self processList: ps ]
			ifFalse: [

				"If the taxon's status is positive, continue processing it. Else, ignore it"
				(ps solution status = #+)
				ifTrue: [

					"Solution is a positive case. Retrieve the corresponding taxon from the taxonomy"
					taxon := (self taxonomy getTaxonByName: (ps name) level: psLevelAsIndex).

					"If the taxon is not found, there is an error. Set ba tate of error and return"
					(taxon = nil) ifTrue: [ self status: #error. ^nil ].

					"Exchange the case for the taxon. Finally, place it in the process list"
					ps solution: taxon.
					self processList: ps.

				].    "END (ps solution status = #+) ifTrue:"

			].    "END (ps solution class name = Taxon getClassName) ifFalse:"

		].    "END (psLevelAsIndex &gt;= goalAsIndex) ifFalse:"

	].    "END 1 to: (self hypothesis possibleSolutions size) do:"

	"If the process list ends up having zip, return fail status (default)"
	(self processList isEmpty) ifTrue: [ ^nil ].

	"At this point, processList contains a set of possible solutions whose level is more general than the
	 stated identificaction goal. Moreover, all solutions are taxa. Proceed to select those items whose
	 level is *closest* to the goal"
	self selectPossibleSolutionsNearestToGoal.

	^(self doDialog).*/

        //Check precondition
        if (hypothesis.getPossibleSolutions().isEmpty()){return null;}
	//Transform the stated identification goal to a numeric value
        
	goalAsIndex := (TaxonomicLevels transformToIndex: (self goal)).

	"Scan the associated hypothesis possible solutions list"

        
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void doDialog(){
/**doDialog

	| ps nextLevelTaxon structure attributeList attribute taxon valueList savDescriptor vd result OKSAVDescriptorList v displayValues returnValues |

	"Scan the processList"
	[ self processList isEmpty ]
	whileFalse: [

		"Remove the next possible solution"
		ps := (self processList removeFirst).

		"Get the next possible solution's taxon"
		taxon := ps solution.

		"Scan the taxon's successor list"
		1 to: (taxon successors size) do:
		[:i |

			"Get the next-level successor taxon"
			nextLevelTaxon := (taxon successors) at: i.

			"Get the related structure from the successor taxon's SAV description"
			structure := (nextLevelTaxon
							getAnObjectWith: (self hypothesis descriptiveElement name)
							in: (nextLevelTaxon SAVdescription)).

			(structure = nil)
			ifFalse: [

				"Get the attributes from the retrieved structure"
				attributeList := structure attributes.

				"Scan the attributes list"
				1 to: (attributeList size) do:
				[:j |

					"Get the next attribute"
					attribute := (attributeList at: j).

					"Make sure this attribute is not already processed (i.e., included in the solution or
					 confirmed descriptions of ANY item in the processList)"
					(self isAttributeAlreadyProcessed: attribute)
					ifFalse: [

						"Get the attribute's list of values"
						valueList := (attribute values) valueDescriptorsIn: (Attribute oneLevel).
						(valueList = nil) ifTrue: [ self status: #error. ^nil ].

						OKSAVDescriptorList := OrderedCollection new.
						displayValues := List new.
						returnValues := List new.

						"Scan the value descriptor list: valueList with range value descriptors: only ONE element.
						 valueList with non-range value descriptors: at least one element"
						1 to: (valueList size) do:
						[:k |

							"Get the next value descriptor"
							vd := (valueList at: k).

							"If the value decriptor is a range, do a range-driven dialog"
							(vd asRange)
							ifTrue: [

								[ OKSAVDescriptorList isEmpty ]
								whileFalse: [ OKSAVDescriptorList removeFirst ].

								result := (self rangeValueDescriptorDialogWith: vd attribute: attribute).
								(((result at: 1) = #cancel) | ((result at: 1) = #error)) ifTrue: [ self status: (result at: 1). ^nil ].

								((result at: 1) = #reject)
								ifTrue: [ ps unconfirmedDescription: (result at: 2)  ].

								((result at: 1) = #unmatch)
								ifTrue: [ self hypothesis unmatchedDescription: (result at: 2) ].

								"The user typed a value within the range. Thus result contains a SAVDescriptor
								 that can be used to update ps confirmedDescription. Additionally, assign
								 nextLevelTaxon to ps solution, and place ps in the OKList. Finally, do a recursive
								 call to doDialog, in order to process the next possible solution in processList"
								((result at: 1) = #confirm)
								ifTrue: [
									ps solution: nextLevelTaxon.
									ps confirmedDescription: (result at: 2).
									self OKList: ps.
									^(self doDialog)
								].

							]    "END (vd asRange) ifTrue:"

							"(vd asRange)"
							ifFalse: [

								"Create a SAV descriptor for each [structure-attribute]-value"
								savDescriptor := SAVDescriptor new.
								savDescriptor
									addStructure: (self hypothesis descriptiveElement name)
									Attribute: (attribute name)
									Value: (vd value).

								"Make sure the SAV descriptor is not already processed (i.e., included in the unconfirmed,
								 doubtful, or unmatched descriptions of ANY item in the processList. The reason for this
								 check is to avoid asking the user questions previously answered"
								(self isDescriptorAlreadyProcessed: savDescriptor)
								ifFalse: [

									"For the new descriptor's value, make sure its degree of similarity (with respect to
									 the attribute's typical value) is acceptable. If it isn't, ignore it"
									((self determineSimilarityFor: savDescriptor context: nextLevelTaxon) = nil)
									ifFalse: [

										"Place the SAV descriptor value in the Array that will be displayed"
										((savDescriptor value) respondsToArithmetic)
										ifTrue: [ v := (savDescriptor value) printString ]
										ifFalse: [ v := (savDescriptor value) asString ].
										displayValues add: v.
										returnValues add: (savDescriptor value).

										"Place the descriptor is a separate descriptor list. Process the next value"
										OKSAVDescriptorList add: savDescriptor.

									].    "END ((self determineSimilarityFor: ...) = nil) ifFalse:"

								].    "END (self isDescriptorAlreadyProcessed: savDescriptor) ifFalse:"

							].    "END (vd asRange) ifFalse:"

						].    "END 1 to: (valueList size) do:"

						"Once all of the attribute's values have been processed, the number of items in the
						 display array MUST be equal to the number of descriptors in the descriptor list. If
						 such number is greater than zero, display the dialog"
						(OKSAVDescriptorList isEmpty)
						ifFalse: [

							result := (self valueDescriptorDialogWith: displayValues
										returnValueList: returnValues
										attribute: attribute).

							"User rejects. Flush the descriptor list by placing all SAVDescriptors in the unconfirmed
							 description. Continue processing the next attribute"
							(result = #reject)
							ifTrue: [
								[OKSAVDescriptorList isEmpty]
								whileFalse: [ ps unconfirmedDescription: (OKSAVDescriptorList removeFirst) ]
							].

							"User is in doubt. Flush the descriptor list by placing all SAVDescriptors in the doubtful
							 description. Continue processing the next attribute"
							(result = #doubt)
							ifTrue: [
								[OKSAVDescriptorList isEmpty]
								whileFalse: [ ps doubtfulDescription: (OKSAVDescriptorList removeFirst) ]
							].

							"User cancels. Cancel the process and exit."
							(result = #cancel) ifTrue: [ self status: #cancel. ^nil ].

							"User selects one item. The dialog method returns the value included in a SAVDescriptor.
							 Assign nextLevelTaxon to ps solution. Place ps in the OKList. Call doDialog recursively"
							(result class name = SAVDescriptor getClassName)
							ifTrue: [
								ps solution: nextLevelTaxon.
								ps confirmedDescription: result.
								self OKList: ps.
								^(self doDialog)
							].

						].    "END (OKSAVDescriptorList isEmpty) ifFalse:"

					].    "END (self isAttributeAlreadyProcessed: attribute) ifFalse:"

				].    "END 1 to: (attributeList size) do:"

			].    "END (structure = nil) ifFalse:"

		].    "END 1 to (taxon successors) do:"

	].    "END [ self processList isEmpty ] whileFalse:"

	"At this point, all of the possible solutions have been processed. If the OKList is NOT empty, all the elements now
	 included in it have advanced one level with respect to the original ps solution. If the If the FIRST possible solution's
	 level meets the goal, exit successfully. Else, copy everything back to the processList and call doDialog again. The
	 return value is whatever this new call returns"
	(self OKList isEmpty)
	ifFalse: [

		((self goal) = (self OKList first) level)
		ifTrue: [ self status: #success. ^self ].

		[ self OKList isEmpty ]
		whileFalse: [ self processList: (self OKList) removeFirst ].
		^(self doDialog).

	].    "END (self OKList isEmpty) ifFalse:"

	^nil.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void selectPossibleSolutionsNearestToGoal(){
/**selectPossibleSolutionsNearestToGoal

	"The instance list variable processList contains possible solutions ordered by level. Furthermore, only
	 possible solutions whose level is more general than the stated goal are included in this list. The idea
	 of this method is to select those items whose level is *nearest* to the goal, that is, those items whose
	 level is equal to the level of the first item.

	 Precondition: processList is NOT empty"

	| level i |

	"Check precondition"
	(self processList isEmpty)
	ifTrue: [ ^nil ].

	"If the processList contains only one element, there's nothing to do"
	((self processList size) = 1)
	ifTrue: [ ^self ].

	"Get the level of the first element"
	level := (self processList first) level.

	"Starting with the second element, process the list"
	i := 2.
	[ i &lt;= (self processList size) ]
	whileTrue: [

		(level = ((self processList) at: i) level)
		ifTrue: [ i := i + 1 ]
		ifFalse: [ (self processList) removeAtIndex: i ].

	].    "END [ i &lt;= (self processList size) ] whileTrue:"

	^self.*/
}



 /**
 *Category dialog display
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void rangeValueDescriptorDialogWith(){
/**rangeValueDescriptorDialogWith: vd attribute: anAttribute

	| msg suggestedValue result d value returnValues |

	returnValues := OrderedCollection new.

	msg :=	'Estructura: ' , (self hypothesis descriptiveElement name) ,
			'.\Por favor digite un valor en ' , (vd measuringUnit) ,
			'\para el atributo: ' , (anAttribute name) ,
			'.\\Si no es posible proveer la respuesta,\deje el campo en blanco ' ,
			'y haga click\en OK.'.

	suggestedValue := (((vd lowerBound) + (vd upperBound)) / 2) asFloat.

	result := (SukiaDialog
				request: msg withCRs
				initialAnswer: (suggestedValue printString)
				for: SukiaDialog defaultParentWindow).

	(result = #cancel)
	ifTrue: [
		returnValues add: #cancel.
		returnValues add: nil.
		^returnValues
	].

	(result = '')
	ifTrue: [
		d := SAVDescriptor new.
		d addStructure: (self hypothesis descriptiveElement name)
	   		Attribute: (anAttribute name)
	   		Value: suggestedValue.
		returnValues add: #reject.
		returnValues add: d.
		^returnValues.
	].

	value := (result asNumber).
	d := SAVDescriptor new.
	d addStructure: (self hypothesis descriptiveElement name)
		Attribute: (anAttribute name)
		Value: value.

	((value &gt;= (vd lowerBound)) &amp; (value &lt;= (vd upperBound)))
	ifTrue: [ returnValues add: #confirm ]
	ifFalse: [ returnValues add: #unmatch ].

	returnValues add: d.
	^returnValues.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void valueDescriptorDialogWith(){
/**valueDescriptorDialogWith: displayValues returnValueList: returnValues attribute: anAttribute

	| msg result d |

	msg :=	'Presenta el atributo ' ,
			(anAttribute name) ,
			'\de la estructura: ' ,
			(self hypothesis descriptiveElement name) ,
			'\ uno de los siguientes valores?'.

	result := (SukiaDialog
				choose: msg
				fromList: displayValues
				values: returnValues
				lines: 8
				for: SukiaDialog defaultParentWindow).

	((result = #reject) | (result = #doubt) | (result = #cancel))
	ifTrue: [ ^result ].

	d := SAVDescriptor new.
	d addStructure: (self hypothesis descriptiveElement name)
	   Attribute: (anAttribute name)
	   Value: result.
	^d.*/
}



 /**
 *Category initializing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void initializeGoal(){
/**initializeGoal: aGoal hypothesis: aHypothesis taxonomy: aTaxonomy similarityRanges: simRangesList

	| sb |

	"The argument aGoal MUST be a value valid for TaxonomicLevels (e.g., #genus)"
	goal := aGoal.

	"The elements of simrangesList MUST be defined in SimRanges"
	similarityRanges := simRangesList.

	hypothesis := aHypothesis.
	taxonomy := aTaxonomy.

	"The instance variable status indicates the search status at the end of the process.
	 The possible values it may have are:
	#fail - the dialog was unsuccessful. This is the default value.
	#success - at least one taxon reached the goal.
	#cancel - the user canceled the dialog process.
	#error - a processing error occurred."
	self status: #fail.

	sb := [:x :y |
			(TaxonomicLevels transformToIndex: (x level)) &gt;=
			(TaxonomicLevels transformToIndex: (y level)) ].

	"This list must contain zero or more instances of PossibleSolution"
	processList := SortedCollection new.
	processList sortBlock: sb.

	"This list must contain zero or more instances of PossibleSolution"
	OKList := SortedCollection new.
	OKList sortBlock: sb.

	^self.*/
}



 /**
 *Category adding
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void OKList(Object d){
/**OKList: aPossibleSolution

	OKList add: aPossibleSolution.
	^self.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void processList(Object d){
/**processList: aPossibleSolution

	processList add: aPossibleSolution.
	^self.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void status(Object d){
/**status: aStatusValue

	"The instance variable status indicates the search status at the end of the process.
	 The possible values it may have are:
	#fail - the dialog was unsuccessful. This is the default value.
	#success - at least one taxon reached the goal.
	#cancel - the user canceled the dialog process.
	#error - a processing error occurred."
	status := aStatusValue.
	^self.*/
}



 /**
 *Category testing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void determineSimilarityFor(){
/**determineSimilarityFor: aSAVDescriptor context: aTaxon

	"Determines the similarity range between aSAVDescriptor's value and aTaxon's value
	 weight list.  If there exists a range of similarity, it is checked against the accepted
	 ranges.

	 Returns: nil : if there is no similarity.
			  aTaxon : if there was an acceptable degree of similarity."

	| weightedValues similarity |

	weightedValues :=
		(((aTaxon getAnObjectWith: (aSAVDescriptor structure) in: (aTaxon SAVdescription))
		   attributeWith: (aSAVDescriptor attribute)) values at: (Attribute oneLevel)).

	similarity := SimAssessor similarityRangeOf: (aSAVDescriptor value) in: weightedValues.
	(self similarityRanges includes: similarity) ifFalse: [ ^nil ].

	^aTaxon.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void isAttributeAlreadyProcessed(){
/**isAttributeAlreadyProcessed: anAttribute

	| d ps |

	d := SAVDescriptor new.
	d addStructure: (self hypothesis descriptiveElement name) Attribute: (anAttribute name) Value: nil.

	1 to: (self hypothesis possibleSolutions size) do:
	[:i |

		ps := (self hypothesis possibleSolutions at: i).

		((ps includes: d in: (ps solutionDescription)) = nil)
		ifFalse: [ ^true ].

		((ps includes: d in: (ps confirmedDescription)) = nil)
		ifFalse: [ ^true ].

	].    "END 1 to: (self hypothesis possibleSolutions size) do:"

	^false.*/
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void isDescriptorAlreadyProcessed(){
/**isDescriptorAlreadyProcessed: aSAVDescriptor

	| ps |

	((self hypothesis includesFull: aSAVDescriptor in: (self hypothesis unmatchedDescription)) = nil)
	ifFalse: [ ^true ].

	1 to: (self hypothesis possibleSolutions size) do:
	[:i |

		ps := (self hypothesis possibleSolutions at: i).

		((ps includesFull: aSAVDescriptor in: (ps unconfirmedDescription)) = nil)
		ifFalse: [ ^true ].

		((ps includesFull: aSAVDescriptor in: (ps doubtfulDescription)) = nil)
		ifFalse: [ ^true ].

	].    "END 1 to: (self hypothesis possibleSolutions size) do:"

	^false.*/
}

}
