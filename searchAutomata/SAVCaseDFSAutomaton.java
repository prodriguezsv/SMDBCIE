
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchAutomata;

import main.Norm;
import main.Node;
import redundantDiscriminantNet.SAVRoot;
import redundantDiscriminantNet.SAVDescriptor;
import output.DFSAutomatonOutput;
import java.util.ArrayList;
import java.util.List;

/**
 *SAVCase Depth-First-Search Automaton.
 *1. The search process is based on a problem description composed of a non-empty set of SAVDescriptors.
 *2. The search strategy is depth-first. That is, using all possible SAVDescriptors from the problem description, advance as
 *deep as possible within the Case Memory, before selecting solution cases.  This strategy ensures that all descriptors
 *are considered.
 * @author pabloq
 */

public class SAVCaseDFSAutomaton {
    public SAVRoot netRoot;
    public Norm currentNorm;
    public int currentLevel;
    public int stopLevel;
    private List<SAVDescriptor> tSolutionDesc;
    private List<SAVDescriptor> tConfirmedDesc;
    private List<SAVDescriptor> tUnconfirmedDesc;
    private List<SAVDescriptor> tDoubtfulDesc;
    private List<SAVDescriptor> tUnmatchedDesc;
    private List<Object> justification;
    public String status;
    public DFSAutomatonOutput searchOutput;

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public SAVCaseDFSAutomaton(SAVRoot aSAVRoot){
        //	netRoot := aSAVRoot.
        //	self currentNorm: nil.
        //
        //	self resetLevel.
        //	self stopLevel: self currentLevel.
        //
        //	tSolutionDesc := OrderedCollection new.
        //	tConfirmedDesc := OrderedCollection new.
        //	tUnconfirmedDesc := OrderedCollection new.
        //	tDoubtfulDesc := OrderedCollection new.
        //	tUnmatchedDesc := OrderedCollection new.
        //	justification := OrderedCollection new.
        //	self newOutput.
        //
        //	"The instance variable status indicates the search status at the end of the process.
        //	 The possible values it may have are:
        //	#fail - the search was unsuccessful. This is the default value.
        //	#success - at least one possible solution was found.
        //	#cancel - the user canceled the search process.
        //	#error - a processing error occurred."
        //	self status: #fail.
        netRoot = aSAVRoot;
        currentNorm = null;
        resetLevel();
        stopLevel(currentLevel);
        tSolutionDesc = new ArrayList<SAVDescriptor>();
        tConfirmedDesc = new ArrayList<SAVDescriptor>();
        tUnconfirmedDesc = new ArrayList<SAVDescriptor>();
        tDoubtfulDesc = new ArrayList<SAVDescriptor>();
        tUnmatchedDesc = new ArrayList<SAVDescriptor>();
        justification = new ArrayList<Object>();
        newOutput();
        status = "fail";
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void newOutput(){
        searchOutput = new DFSAutomatonOutput();
    }
/**
 *Category adding
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void currentNorm(Norm aNorm){
        //<body>currentNorm: aNorm
        //
        //	"Automaton reference: CN"
        //
        //	currentNorm := aNorm.
        //	^self.</body>
        currentNorm = aNorm;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void justification(Object aJustificationElement){
        //<body>justification: aJustificationElement
        //
        //	justification add: aJustificationElement.
        //	^self.</body>
        justification.add(aJustificationElement);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void nextLevel(){
        //<body>nextLevel
        //
        //	currentLevel := currentLevel + 1.
        //	^self.</body>
        currentLevel += 1;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void previousLevel(){
        //<body>previousLevel
        //
        //	currentLevel := currentLevel - 1.
        //	^self.</body>
        currentLevel -= 1;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void resetLevel(){
        //<body>resetLevel
        //
        //	currentLevel := 0.
        //	^self.</body>
        currentLevel = 0;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void status(String aStatusValue){
        //<body>status: aStatusValue
        //
        //	"The possible values for the argument aStatusValue are:
        //	#fail - the search was unsuccessful. This is the default value.
        //	#success - at least one possible solution was found.
        //	#cancel - the user canceled the search process.
        //	#error - a processing error occurred."
        //
        //	status := aStatusValue.
        //	^self.</body>
        status = aStatusValue;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void stopLevel(int aLevelNumber){
        //<body>stopLevel: aLevelNumber
        //
        //	stopLevel := aLevelNumber.
        //	^self.</body>
     stopLevel = aLevelNumber;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void tConfirmedDescription(SAVDescriptor aSAVDescriptor){
        //<body>tConfirmedDescription: aSAVDescriptor
        //
        //	"Automaton reference: AtCD"
        //
        //	tConfirmedDesc add: aSAVDescriptor.
        //	^self.</body>
        tConfirmedDesc.add(aSAVDescriptor);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void tDoubtfulDescription(SAVDescriptor aSAVDescriptor){
        //<body>tDoubtfulDescription: aSAVDescriptor
        //
        //	"Automaton reference: AtDD"
        //
        //	tDoubtfulDesc add: aSAVDescriptor.
        //	^self.</body>
        tDoubtfulDesc.add(aSAVDescriptor);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void tSolutionDescription(SAVDescriptor aSAVDescriptor){
        //<body>tSolutionDescription: aSAVDescriptor
        //
        //	"Automaton reference: AtSD"
        //
        //	tSolutionDesc add: aSAVDescriptor.
        //	^self.</body>
        tSolutionDesc.add(aSAVDescriptor);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void tUnconfirmedDescription(SAVDescriptor aSAVDescriptor){
        //<body>tUnconfirmedDescription: aSAVDescriptor
        //
        //	"Automaton reference: AtUD"
        //
        //	tUnconfirmedDesc add: aSAVDescriptor.
        //	^self.</body>
        tUnconfirmedDesc.add(aSAVDescriptor);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void tUnmatchedDescription(SAVDescriptor aSAVDescriptor){
        //<body>tUnmatchedDescription: aSAVDescriptor
        //
        //	"Automaton reference: AtUMD"
        //
        //	tUnmatchedDesc add: aSAVDescriptor.
        //	^self.</body>
        tUnmatchedDesc.add(aSAVDescriptor);
    }
/**
 *Category accessing
 */

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public int currentLevel(){
        //<body>currentLevel
        //
        //	^currentLevel.</body>
        return currentLevel;
    }
    // TODO
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object currentNorm(){
        //<body>currentNorm
        //
        //	^currentNorm.</body>
        return currentNorm;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<Object> justification(){
        //<body>justification
        //
        //	^justification.</body>
        return justification;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public SAVRoot netRoot(){
        //<body>netRoot
        //
        //	^netRoot.</body>
        return netRoot;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public int rootLevel(){
        //<body>rootLevel
        //
        //	^1.</body>
        return 1;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public DFSAutomatonOutput searchOutput(){
        //<body>searchOutput
        //
        //	^searchOutput.</body>

       return searchOutput;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String status(){
        //<body>status
        //
        //	^status.</body>
        return status;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public int stopLevel(){
        //<body>stopLevel
        //
        //	^stopLevel.</body>
        return stopLevel;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> tConfirmedDescription(){
        //<body>tConfirmedDescription
        //
        //	^tConfirmedDesc.</body>
        return tConfirmedDesc;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> tDoubtfulDescription(){
        //<body>tDoubtfulDescription
        //
        //	^tDoubtfulDesc.</body>
        return tDoubtfulDesc;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> tSolutionDescription(){
        //<body>tSolutionDescription
        //
        //	^tSolutionDesc.</body>
        return tSolutionDesc;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> tUnconfirmedDescription(){
        //<body>tUnconfirmedDescription
        //
        //	^tUnconfirmedDesc.</body>
        return tUnconfirmedDesc;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public List<SAVDescriptor> tUnmatchedDescription(){
        //<body>tUnmatchedDescription
        //
        //	^tUnmatchedDesc.</body>
        return tUnmatchedDesc;
    }
/**
 *Category backtracking
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String backtrack(){
        //<body>backtrack
        //
        //	"A backtrack operation consists of moving up a norm, in order to present the user
        //	 with that norm's indices.  During a backtrack operation, the descriptor that corresponds
        //	 to the current norm is extracted from the confirmed list, and placed in the unconfirmed
        //	 list.  The reason for this action is that such descriptor was once confirmed, and now the
        //	 user 'states' that it's no good.
        //
        //	 Returns: - nil - if the backtracking process fails, or indexDialog returns nil,
        //			   self - if indexDialog returns self after a successful backtrack.
        //
        //	 Automaton reference: BkTk"
        //
        //	((self removeSAVDescriptorFromTConfirmedDesc: (self currentNorm)) = nil)
        //	ifTrue: [self prepareFailedOutput. ^nil. ].
        //
        //	((self processPreviousNorm) = nil)
        //	ifTrue: [ self prepareFailedOutput. ^nil. ].
        //
        //	^(self indexDialog).</body>
        if (removeSAVDescriptorFromTConfirmedDesc(currentNorm)==null) {
            prepareFailedOutput();
            return null;
        }
        if(processPreviousNorm() == null){
            prepareFailedOutput();
            return null;
        }
        return indexDialog();
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String processPreviousNorm(){
        //<body>processPreviousNorm
        //
        //	"Moves up a norm. This operation fails if an attempt is made to move
        //	 farther up than the stop-level norm, or if control is already at the net
        //	 root.  If all goes well, an index verification is performed on the norm.
        //
        //	Returns: nil - if the process fails;
        //			self - if everything dandy.
        //
        //	Automaton reference: PPN"
        //
        //	| r |
        //
        //	self currentNorm: (self currentNorm predecessor).
        //	self previousLevel.
        //	((self currentLevel &lt; self stopLevel) | (self currentNorm = self netRoot))
        //	ifTrue: [ ^nil ].
        //
        //	r := self verifyIndices.
        //	(r = nil) ifTrue: [ ^nil ].
        //	(r = #backtrack) ifTrue: [ ^(self processPreviousNorm) ].
        //
        //	^self.</body>
        currentNorm = currentNorm.getPredecessorNorm();
        previousLevel();
        if ((currentLevel < stopLevel) || (currentNorm == netRoot)){
            return null;
        }
        String r = verifyIndices();
        if (r == null) {return null;}
        if (r.equals("backtrack")){
            processPreviousNorm();
        }
        return null;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object removeSAVDescriptorFromTConfirmedDesc(Norm aNorm){
        //<body>removeSAVDescriptorFromTConfirmedDesc: aNorm
        //
        //	"Removes, from tConfirmedDescription, a SAVDescriptor that matches aNorm's attribute
        //	 and value.  The removed SAVDescriptor is placed in the tUnconfirmedDescription.
        //
        //	 Returns: nil - if the aNorm's level (i.e., currentLevel) is the less than or equal to the stopLevel,
        //				   or if the SAVDescriptor was not found in tConfirmedDescriptor;
        //			  self - if all OK.
        //
        //	 Automaton reference: RtCD"
        //
        //	(self currentLevel &lt;= self stopLevel)
        //	ifTrue: [ ^nil ].
        //
        //	1 to: (self tConfirmedDescription size) do:
        //	[:i |
        //		((((self tConfirmedDescription) at: i) attribute = (aNorm descriptor) attribute) &amp;
        //		 (((self tConfirmedDescription) at: i) value = (aNorm descriptor) value))
        //		ifTrue: [ self tUnconfirmedDescription: ((self tConfirmedDescription) removeAtIndex: i). ^self ].
        //	].
        //
        //	^nil.</body>
        if (currentLevel <= stopLevel){return null;}
         int idx = 0;
        for (SAVDescriptor tcd : tConfirmedDescription()) {

            if (tcd.getAttribute().equals(aNorm.getDescriptor().getAttribute()) &&
                    (tcd.getValue().equals(aNorm.getDescriptor().getValue()))){
                tUnconfirmedDescription(tConfirmedDescription().remove(++idx));
                return true;
            }

        }
        return null;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String verifyIndices(){
        //
        //	"This method is called during the backtracking process.  The purpose here is to determine if there is at least
        //	 one index to show to the user, hence continuing with the dialog. All of the current norm's successors are
        //	 scanned. As a reminder, at this point all of the current norm's successors must be indices. For every retrieved
        //	 index, all its index values should be checked against the unconfirmed or doubtful descriptions. If at least ONE
        //	 index value is not a member of either list, the dialog with the user may continue in the context of the current norm.
        //	 Else, a new backtrack operation should be performed.
        //
        //	NOTE: The possibility of finding successor cases for the current norm is discarded, because successor cases have
        //	already been searched for by the method retrieveCasesUnderNorm.
        //
        //	Returns:
        //		self - if at least one index value (in the form of a SAVDescriptor) is not member of either the the unconfirmed
        //			  or doubtful lists;
        //		nil - if removeSAVDescriptorFromTConfirmedDesc: returns nil;
        //		#backtrack - if a new bactrack operation should be performed.
        //
        //	 Automaton reference: VIdx"
        //
        //	| normSucc idxSucc d |
        //
        //	"Scan the list of successors for the current norm"
        //	1 to: (self currentNorm successors) size do:
        //	[:i |
        //		"Get the next successor. It's got to be an index!!"
        //		normSucc := (self currentNorm successors) at: i.
        //
        //		"Get its list of successors (i.e., IndexValues)"
        //		idxSucc := normSucc succidxSuccessors.
        //
        //		"Scan the list of successors for the current index"
        //		1 to: idxSucc size do:
        //		[:j |
        //			"Create a SAVDescriptor"
        //			d := SAVDescriptor new.
        //			d addStructure: (self netRoot structure) Attribute: (normSucc label) Value: (idxSucc at: j) value.
        //
        //			"If the SAVDescriptor is not a member of the unconfirmed description, check it against
        //			 the doubtful description. If this time the SAVDescriptior is NOT a member of the doubtful
        //			 description, return self, indicating that there is at least one index-value to show to the user"
        //			((self includes: d in: (self tUnconfirmedDescription)) = nil)
        //			ifTrue: [
        //				((self includes: d in: (self tDoubtfulDescription)) = nil)
        //				ifTrue: [ ^self. ].
        //
        //			].    "END (self includes: d in: (self tUnconfirmedDescription) = nil) ifTrue:"
        //
        //		].    "END 1 to: idxSucc size do:"
        //
        //	].    "END 1 to: (self currentNorm successors) size do:"
        //
        //	"At this point, the entire list of norm successors was scanned, and all of them were members
        //	 of either the unconfirmed or doubtful descriptions. Return nil to indicate that another backtrack
        //	 must be performed. Before returning, the descriptor of the current norm must be removed from
        //	 the confirmed description and placed in the unconfirmed description"
        //	(self removeSAVDescriptorFromTConfirmedDesc: (self currentNorm)) = nil
        //	ifTrue: [ ^nil ].
        //
        //	^#backtrack.</body>
        int successorsSize = currentNorm.getSuccessors().size();
        for (int i=0;(i<successorsSize);i++){
            Node normSucc = currentNorm.getSuccessors().get(i);
            normSucc.
            idxSucc = normSucc..succidxSuccessors();
        }
        return "backtrack";
    }
/**
 *Category case-pointing search
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void searchForCasesUnderCurrNormUsing(){
        //<body>searchForCasesUnderCurrNormUsing: aProblemDescription saveIn: aPossibleSolutionsList
        //
        //	"This method searches for indices, strictly under a norm other than the net root, that point to cases. If a Descriptor in
        //	 the argument list aProblemDescription matches one such index, it is placed in the solution description. If a Descriptor
        //	 matches an index that points to a Norm, it will remain in aProblemDescription. If a Descriptor does not match any index,
        //	 it will also remain in aProblemDescription.
        //
        //	 Returns:	self : if the process ran OK;
        //				#cancel : is the user cancels.
        //				-1 : error value, if the precondition is not met.
        //
        //	 PRECONDITION:
        //	(aProblemDescription isEmpty not) and (self currentNorm != self netRoot not) and (aPossibleSolutionsList isEmpty)"
        //
        //	| d idx succ tempList pSolutionList result |
        //
        //	"Check precondition"
        //	((aProblemDescription isEmpty) | (self currentNorm = self netRoot) | ((aPossibleSolutionsList isEmpty) not))
        //	ifTrue: [ ^-1 ].
        //
        //	"Create the temporary process lists"
        //	tempList := OrderedCollection new.
        //	pSolutionList := OrderedCollection new.
        //
        //	"Scan the the Descriptor list of the problem description. Look for indices that strictly point to cases"
        //	[ aProblemDescription isEmpty ]
        //	whileFalse: [
        //
        //		"Remove the next SAVDescriptor"
        //		d := aProblemDescription removeFirst.
        //
        //		"Look for a matching index"
        //		idx := (self currentNorm) getIndexWith: d attribute and: d value.
        //
        //		"If the descriptor did not match any index, take it out of the problem description and
        //		 place it in the temporary list"
        //		(idx = nil)
        //		ifTrue: [
        //			"The descriptor may have an inaccurate value. Try to establish a dialog with the user."
        //			result := self searchCasesDialogUsing: d saveIn: aPossibleSolutionsList.
        //			(result = #cancel) ifTrue: [ ^#cancel. ].
        //			(result = #fail) | (result = #idxNotFound) ifTrue: [ tempList add: d ].
        //			(result = #success) ifTrue: [ self tUnmatchedDescription: d ].
        //		]
        //		ifFalse: [
        //
        //			"Index found. Get the IndexValue successor"
        //			succ := ((idx getIndexValueWith: d value) successors) at: 1.
        //
        //			"If a matched index points to a Norm, don't process it. Place the descriptor in a temporary list"
        //			(succ class name = Norm getClassName)
        //			ifTrue: [ tempList add: d ]
        //			ifFalse: [
        //
        //				"The matched index points to a case. Place the corresponding descriptor in the solution description
        //		 		 and associate the corresponding case to a PossibleSolution. Next, place the possible solution in the
        //		 		output possible solutions list. Finally, remove the descriptor from the solution description"
        //				self tSolutionDescription: d.
        //				pSolutionList add: succ.
        //				pSolutionList := (self associateCasesToPossibleSolutions: pSolutionList).
        //				aPossibleSolutionsList add: (pSolutionList removeFirst).
        //				self tSolutionDescription removeLast.
        //
        //			]    "END (succ class name = Norm getClassName) ifFalse:"
        //
        //		].    "END (idx = nil) ifFalse:"
        //
        //	].    "END [ aProblemDescription isEmpty ] whileFalse:"
        //
        //	"Put the descriptors that didn't match indices back in the problem description list"
        //	[ tempList isEmpty ]
        //	whileFalse: [ aProblemDescription add: (tempList removeFirst) ].
        //
        //	^self.</body>
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void searchForCasesUnderRootUsing(){
        //<body>searchForCasesUnderRootUsing: aProblemDescription saveIn: aPossibleSolutionsList
        //
        //	"This method searches for indices, strictly under the net root, that point to cases. If a Descriptor in
        //	 the argument list aProblemDescription matches one such index, it is placed in the solution description.
        //	 If a Descriptor matches an index that points to a Norm, it will remain in aProblemDescription. If a Descriptor
        //	 does not match any index, it will be placed in the unmatched descriptor list.
        //
        //	 Returns: self : if the process ran OK;
        //			   #cancel : is the user cancels.
        //			     -1 : error value, if the precondition is not met.
        //
        //	 PRECONDITION:
        //	(aProblemDescription isEmpty not) and (self currentNorm = self netRoot) and
        //	(aPossibleSolutionsList isEmpty) and  (tSolutionDescription isEmpty)"
        //
        //	| d idx succ tempList pSolutionList result |
        //
        //	"Check precondition"
        //	(aProblemDescription isEmpty)
        //	ifTrue: [ ^-1 ].
        //	(((self currentNorm = self netRoot) &amp; (aPossibleSolutionsList isEmpty) &amp; (self tSolutionDescription isEmpty)) not)
        //	ifTrue: [ ^-1 ].
        //
        //	"Create the temporary process lists"
        //	tempList := OrderedCollection new.
        //	pSolutionList := OrderedCollection new.
        //
        //	"Scan the the Descriptor list of the problem description. Look for indices that strictly point to cases"
        //	[ aProblemDescription isEmpty ]
        //	whileFalse: [
        //
        //		"Remove the next SAVDescriptor"
        //		d := aProblemDescription removeFirst.
        //
        //		"Look for a matching index"
        //		idx := (self currentNorm) getIndexWith: d attribute and: d value.
        //
        //		"If the descriptor did not match any index, take it out of the problem description and
        //		 place it in the unmatched descriptor list"
        //		(idx = nil)
        //		ifTrue: [
        //
        //			"At this point, the descriptor inevitably goes to the unmatched description"
        //			self tUnmatchedDescription: d.
        //
        //			"However, the descriptor may have an inaccurate value. Try to establish a dialog with the user
        //			 using a partial match."
        //			result := self searchCasesDialogUsing: d saveIn: aPossibleSolutionsList.
        //			(result = #cancel) ifTrue: [ ^#cancel ].
        //		]
        //		ifFalse: [
        //
        //			"Index found. Get the IndexValue successor"
        //			succ := ((idx getIndexValueWith: d value) successors) at: 1.
        //
        //			"If a matched index points to a Norm, don't process it. Place the descriptor in a temporary list"
        //			(succ class name = Norm getClassName)
        //			ifTrue: [ tempList add: d ]
        //			ifFalse: [
        //
        //				"The matched index points to a case. Place the corresponding descriptor in the solution description
        //		 		 and associate the corresponding case to a PossibleSolution. Next, place the possible solution in the
        //		 		output possible solutions list. Finally, remove the descriptor from the solution description"
        //				self tSolutionDescription: d.
        //				pSolutionList add: succ.
        //				pSolutionList := (self associateCasesToPossibleSolutions: pSolutionList).
        //				aPossibleSolutionsList add: (pSolutionList removeFirst).
        //				self tSolutionDescription removeFirst.
        //
        //			]    "END (succ class name = Norm getClassName) ifFalse:"
        //
        //		].    "END (idx = nil) ifFalse:"
        //
        //	].    "END [ aProblemDescription isEmpty ] whileFalse:"
        //
        //	"Put the descriptors that match Norms back in the problem description list"
        //	[ tempList isEmpty ]
        //	whileFalse: [ aProblemDescription add: (tempList removeFirst) ].
        //
        //	^self.</body>
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void searchForCasesUsing(){
        //<body>searchForCasesUsing: aProblemDescription
        //
        //	"At this point, the problem description still contains descriptors, and control is located either at a norm within
        //	 the net, or still at the root.  The purpose of this method is to use those descriptors to find matching indices
        //	 that strictly point to cases
        //
        //	 Returns: nil - if the process failed to find cases, or there was a processing error, or the user cancels;
        //			self - if at least ONE case was found.
        //
        //	 Automaton reference: SCW"
        //
        //	| savNorm savLevel possibleSolutions pSolutions tempDeleteSolution tempDeleteConfirmed result |
        //
        //	"If the argument search-list is empty, something wrong happened. Return error value"
        //	(aProblemDescription isEmpty)
        //	ifTrue: [ self prepareFailedOutput. self status: #error. ^nil. ].
        //
        //	"Make sure the current level is ALWAYS greater than or equal to the root level"
        //	(self currentLevel &lt; self rootLevel)
        //	ifTrue: [ self prepareFailedOutput. self status: #error. ^nil. ].
        //
        //	possibleSolutions := OrderedCollection new.
        //
        //	"Check if control is located at the root (i.e., no Norm was ever found in the previous state)"
        //	(self currentLevel = self rootLevel)
        //	ifTrue: [
        //		"Search for cases pointed to by indices directly under the root. If there are any, the argument
        //		list possibleSolutions will not be empty"
        //
        //		result := self searchForCasesUnderRootUsing: aProblemDescription saveIn: possibleSolutions.
        //		(result = #cancel) ifTrue: [ self prepareFailedOutput. self status: #cancel. ^nil ].
        //		(result = -1) ifTrue: [ self prepareFailedOutput. self status: #error. ^nil. ].
        //
        //		"If the possible solutions list has at least one item, return self along  with whatever Descriptors
        //		 remained in the problem description. This guarantees that all left-over Descriptors point to Norms"
        //		(possibleSolutions isEmpty)
        //		ifFalse: [ ^(self prepareSuccessfulOutputWith: possibleSolutions) ].
        //
        //		"If the case list is empty, either the problem description has all descriptors that point to Norms
        //		 in other paths, OR all descriptors are located in the unmatched list. Return nil"
        //		self prepareFailedOutput.
        //		^nil.
        // 	].
        //
        //	"At this point, control is located on a Norm other than the net root. Initially, scan the Descriptor list in search of indices
        //	 that strictly point to cases. If a matched index points to a case, remove the corresponding Descriptor from the problem
        //	 description and place it in the solution description.  Next, associate the retrieved case to a PossibleSolution, and place
        //	 this possible solution in the case list. Finally, remove the last descriptor of the solution list. If the matched index DOES
        //	 NOT point to a case, leave the corresponding Descriptor in the problem description"
        //	result := self searchForCasesUnderCurrNormUsing: aProblemDescription saveIn: possibleSolutions.
        //	(result = #cancel) ifTrue: [ self prepareFailedOutput. self status: #cancel. ^nil ].
        //	(result = -1)  ifTrue: [ self prepareFailedOutput. self status: #error. ^nil. ].
        //
        //	"If at the end of the scanning process, the problem description is empty, the possible solutions list MUST have at least
        //	one item. Else, something weird happened.  In that situation, return the error value. However, if the case list is not empty,
        //	return self"
        //	(aProblemDescription isEmpty)
        //	ifTrue: [
        //		(possibleSolutions isEmpty)
        //		ifTrue: [ self prepareFailedOutput. self status: #error. ^nil. ].
        //		^(self prepareSuccessfulOutputWith: possibleSolutions).
        //	].
        //
        //	"At this point, the problem description is not empty. Move control to the root and search for
        //	 indices that strictly point to cases."
        //	savNorm := (self currentNorm).
        //	savLevel := (self currentLevel).
        //	self currentNorm: (self netRoot).
        //	self resetLevel. self nextLevel.
        //
        //	"Searching for cases under the root means that the solution description thus far obtained is invalid.
        //	 Therefore, this description should be considered as unmatched. Thus, place the solution description
        //	 items in the unmatched description, and also copy them to a temporary list. The reason for the copy
        //	 list is that, depending on the output from the root-search, it may be necessary to put all descriptors
        //	 back in the solution description, in order to try the next search strategy"
        //	tempDeleteSolution := OrderedCollection new.
        //	self moveDescriptorsFrom: (self tSolutionDescription) to: (self tUnmatchedDescription)
        //		copyTo: tempDeleteSolution.
        //
        //	"Same sitution as with the solution description.  In this case, place the confirmed description items in the
        //	 unconfirmed description, and also copy them to another temporary list"
        //	tempDeleteConfirmed := OrderedCollection new.
        //	self moveDescriptorsFrom: (self tConfirmedDescription) to: (self tUnconfirmedDescription)
        //		copyTo: tempDeleteConfirmed.
        //
        //	"Call the search-cases-under-root method with a clean &amp; empty possible solutions list (its part of the precondition)"
        //	pSolutions := OrderedCollection new.
        //	result := self searchForCasesUnderRootUsing: aProblemDescription saveIn: pSolutions.
        //	(result = #cancel) ifTrue: [ self prepareFailedOutput. self status: #cancel. ^nil ].
        //	(result = -1)  ifTrue: [ self prepareFailedOutput. self status: #error. ^nil. ].
        //
        //	"Concatenate the solution lists into one single list"
        //	[ pSolutions isEmpty ] whileFalse: [ possibleSolutions add: (pSolutions removeFirst) ].
        //
        //	"Restore everything to its previous state"
        //	self currentNorm: savNorm.
        //	self resetLevel.
        //	[ savLevel &gt; 0 ] whileTrue: [ self nextLevel. savLevel := savLevel - 1 ].
        //
        //	"If after the root-search the possible solutions and problem description lists are empty, the next strategy is to try
        //	 retrieving cases from the current norm. Therefore, just as with the current norm and current level, both the solution
        //	 and confirmed descriptions MUST be set back to their original state (i.e., before doing the root-search). So, remove
        //	 all the matching items in the temporary lists from the unmatched and unconfirmed descriptions and place them back
        //	 in the corresponding solution and confirmed ones"
        //	self deleteDescriptorsIn: tempDeleteSolution from: (self tUnmatchedDescription) andPlaceIn: (self tSolutionDescription).
        //	self deleteDescriptorsIn: tempDeleteConfirmed from: (self tUnconfirmedDescription) andPlaceIn: (self tConfirmedDescription).
        //
        //	"Upon return from the root search, the following situations may occur:
        //	a) the possible solutions list is empty.
        //		a1) if the problem description is now empty (i.e., all Descriptors are located in the unmatched list), then call the
        //		     state-method retrieveCasesUnderCurrNorm, in order to give the search process a last chance.
        //		a2) if the problem description is still NOT empty (i.e., the remaining descriptors point to Norms in other paths), return nil.
        //	b) the possible solutions list is NOT empty. Return self, regardless of whether or not the problem description is empty."
        //	(possibleSolutions isEmpty)
        //	ifTrue: [
        //		"Precondition for method retrieveCasesUnderCurrNorm : isEmpty(aProblemDescription)"
        //		(aProblemDescription isEmpty) ifTrue: [ ^(self retrieveCasesUnderCurrNorm) ].
        //		self prepareFailedOutput. ^nil.
        //	].
        //
        //	^(self prepareSuccessfulOutputWith: possibleSolutions).</body>
    }
/**
 *Category private
 */
 /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void associateCasesToPossibleSolutions(){
        //<body>associateCasesToPossibleSolutions: aCaseList
        //
        //	"This method is used in conjuntion with prepareSuccessfulOutput.  The purpose
        //	 of this method is to create an instance of PossibleSolution for every case in the
        //	 list argument aCaseList.
        //
        //	Returns: a list of PossibleSolutions.
        //
        //	 Automaton reference: none."
        //
        //	| ps psList |
        //
        //	psList := OrderedCollection new.
        //
        //	1 to: (aCaseList size) do:
        //	[ :i |
        //		ps := PossibleSolution new.
        //		ps solution: (aCaseList at: i).
        //		ps copy: (self tSolutionDescription) to: (ps solutionDescription).
        //		ps copy: (self tConfirmedDescription) to: (ps confirmedDescription).
        //		ps copy: (self tUnconfirmedDescription) to: (ps unconfirmedDescription).
        //		ps copy: (self tDoubtfulDescription) to: (ps doubtfulDescription).
        //		psList add: ps.
        //	].
        //
        //	^psList.</body>
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void deleteDescriptorsIn(){
        //<body>deleteDescriptorsIn: aTempDeleteList from: aList andPlaceIn: anotherList
        //
        //	| d j |
        //	1 to: (aTempDeleteList size) do:
        //	[:i |
        //		d := (aTempDeleteList at: i).
        //
        //		j := 1.
        //		[ j &lt;= (aList size) ]
        //		whileTrue:
        //		[
        //			(((aList at: j) attribute = (d attribute)) &amp; ((aList at: j) value = (d value)))
        //			ifTrue: [ (aList removeAtIndex: j). j := (aList size) + 1. ]
        //			ifFalse: [ j := j + 1 ].
        //		].
        //
        //		anotherList add: d.
        //	].
        //
        //	^self.</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void filterUselessNorms(){
        //<body>filterUselessNorms: aNormAlternativeList
        //
        //	| newList normAlternative n successorList idx idxSuccessors d |
        //
        //	newList := OrderedCollection new.
        //
        //	[aNormAlternativeList isEmpty]
        //	whileFalse: [
        //
        //		normAlternative := aNormAlternativeList removeFirst.
        //		n := (normAlternative last successors) first.
        //
        //		((n successorCases) isEmpty)
        //		ifFalse: [ newList add: normAlternative ]
        //		ifTrue: [
        //			"First, get the list of successors for the current norm"
        //			successorList := n successors.
        //
        //			1 to: (successorList size) do:
        //			[:i |
        //				"Proceed to extract the next index"
        //				idx := (successorList at: i).
        //
        //				"Parse the list of IndexValues associated to the index"
        //				idxSuccessors := idx successors.
        //				1 to: (idxSuccessors size) do:
        //				[:j |
        //					"For every associated IndexValue: create a SAVDescriptor"
        //					d := SAVDescriptor new.
        //					d addStructure: (netRoot structure) Attribute: (idx label) Value: ((idxSuccessors at: j) value).
        //
        //					"Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions"
        //					(((self includes: d in: (self tUnconfirmedDescription)) = nil) &amp; ((self includes: d in: (self tDoubtfulDescription)) = nil))
        //					ifTrue: [ newList add: normAlternative ]
        //
        //				].    "END 1 to: (idxSuccessors size) do:"
        //
        //			].    "END 1 to: (successorList size) do:"
        //
        //		].    "END ((n successorCases) isEmpty) ifTrue:"
        //
        //	].    "END [aNormAlternativeList isEmpty] whileFalse:"
        //
        //	^newList.</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void flush(){
        //<body>flush: aList
        //
        //	[ aList isEmpty ]
        //	whileFalse: [ aList removeFirst ].
        //	^self.</body>
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void isUseless(){
        //<body>isUseless: anAlternative
        //
        //	| d n successorList idx idxSuccessors |
        //
        //	n := (anAlternative last successors) first.
        //
        //	((n class name = SAVCase getClassName) | (n class name = Case getClassName))
        //	ifTrue: [ ^false ].
        //
        //	((n successorCases) isEmpty)
        //	ifFalse: [ ^false ].
        //
        //	"First, get the list of successors for the current norm"
        //	successorList := n successors.
        //
        //	1 to: (successorList size) do:
        //	[:i |
        //		"Proceed to extract the next index"
        //		idx := (successorList at: i).
        //
        //		"Parse the list of IndexValues associated to the index"
        //		idxSuccessors := idx successors.
        //		1 to: (idxSuccessors size) do:
        //		[:j |
        //			"For every associated IndexValue: create a SAVDescriptor"
        //			d := SAVDescriptor new.
        //			d addStructure: (netRoot structure) Attribute: (idx label) Value: ((idxSuccessors at: j) value).
        //
        //			"Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions"
        //			(((self includes: d in: (self tUnconfirmedDescription)) = nil) &amp; ((self includes: d in: (self tDoubtfulDescription)) = nil))
        //			ifTrue: [ ^false. ]
        //
        //		].    "END 1 to: (idxSuccessors size) do:"
        //
        //	].    "END 1 to: (successorList size) do:"
        //
        //	^true.</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void moveDescriptorsFrom(){
        //<body>moveDescriptorsFrom: aList to: anotherList copyTo: aCopyList
        //
        //	| d |
        //
        //	[ aList isEmpty ]
        //	whileFalse: [
        //		d := (aList removeFirst).
        //		anotherList add: d.
        //		aCopyList add: d.
        //	].
        //
        //	^self.</body>
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void newSearchWith(){
        //<body>newSearchWith: anOldProblemDescription
        //
        //	"If the automaton returns a non-empty problem description list, then the REASONER MUST call it again with that remaining description,
        //	 using this method. Before doing so, all lists, except the doubtful and unconfirmed ones, MUST be flushed. Make sure the new search
        //	 begins at root level, and all necessary control variables are correctly set. This process repeats until the problem description list is EMPTY"
        //
        //	self flush: (self tSolutionDescription).
        //	self flush: (self tConfirmedDescription).
        //	self flush: (self tUnmatchedDescription).
        //	self flush: (self justification).
        //	self status: #fail.
        //	self currentNorm: nil.
        //	self resetLevel.
        //	self stopLevel: (self currentLevel).
        //	self newOutput.
        //
        //	^(self beginWith: anOldProblemDescription).</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void prepareFailedOutput(){
        //<body>prepareFailedOutput
        //
        //	"This method is called from indexDialog, and it may be called recursively. However, it needs to be executed
        //	 only once. In order to avoid continuous execution of this method, check the (list) variable 'justification' of
        //	 searchOutput. Upon termination of the automaton process, 'justification' MUST always be non-nil. Thus,
        //	 if it's non-nil, then don't execute this method.
        //
        //	 This method is executed whennthe automaton has failed to provide a solution for the given problem description.
        //
        //	 Automaton reference: PFO"
        //
        //	((self searchOutput) justification = nil)
        //	ifFalse: [ ^self ].
        //
        //	(self searchOutput) justification: (self justification).
        //	(self searchOutput) unmatchedDescription: (self tUnmatchedDescription).
        //	^self.</body>


    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void prepareSuccessfulOutputWith(){
        //<body>prepareSuccessfulOutputWith: aPossibleSolutionsList
        //
        //	"Automaton reference: PSO"
        //
        //	(((self searchOutput) possibleSolutions) = nil)
        //	ifFalse: [ ^self ].
        //
        //	(self searchOutput) possibleSolutions: aPossibleSolutionsList.
        //	(self searchOutput) justification: (self justification).
        //	(self searchOutput) unmatchedDescription: (self tUnmatchedDescription).
        //	self status: #success.
        //	^self.</body>

    }
/**
 *Category user dialog
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String indexDialog(){
        //<body>indexDialog
        //
        //	"MANAGEMENT OF AN INCOMPLETE DESCRIPTION.
        //	 A dialog with the user is established when the automaton has run out of descriptors from the
        //	 problem description, and has not located any cases.  In addition, the current norm does not
        //	 have any successor cases. The idea is then to present index alternatives to the user, initially
        //	 those indices pointing to cases. If no case is retrieved, index alternatives pointing to norms
        //	 is then presented to the user. If nothing works, the user has the chance to cancel the operation
        //	 or backtrack to a previous norm.
        //
        //	 PRECONDITION: The successor list of the current norm does not contain any associated cases.
        //
        //	Returns: nil - If the process was unsuccessful in finding a case.
        //			self - If one case was found.
        //
        //	Automaton reference: IdxDial"
        //
        //	| idx d idxSuccessors successorList caseAlternatives normAlternatives alternative result answer newList nA |
        //
        //	caseAlternatives := OrderedCollection new.
        //	normAlternatives := OrderedCollection new.
        //
        //	"First, get the list of successors for the current norm"
        //	successorList := currentNorm successors.
        //
        //	1 to: (successorList size) do:
        //	[:i |
        //		"Proceed to extract the next index"
        //		idx := (successorList at: i).
        //
        //		"Parse the list of IndexValues associated to the index"
        //		idxSuccessors := idx successors.
        //		1 to: (idxSuccessors size) do:
        //		[:j |
        //			"For every associated IndexValue: create a SAVDescriptor"
        //			d := SAVDescriptor new.
        //			d addStructure: (netRoot structure) Attribute: (idx label) Value: ((idxSuccessors at: j) value).
        //
        //			"Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions"
        //			(((self includes: d in: (self tUnconfirmedDescription)) = nil) &amp; ((self includes: d in: (self tDoubtfulDescription)) = nil))
        //			ifTrue: [
        //				"Pack the descriptor, along with the associated IndexValue, in a list called alternative. The form of this list
        //				 will be (SAVDescriptor IndexValue)"
        //				alternative := OrderedCollection new.
        //				alternative add: d.
        //				alternative add: (idxSuccessors at: j).
        //
        //				(((idxSuccessors at: j) successors) first class name = Norm getClassName)
        //				ifTrue: [ normAlternatives add: alternative ]
        //				ifFalse: [ caseAlternatives add: alternative ].
        //
        //			].    "END ((self includes: d in: (self tUnconfirmedDescription)) &amp; (self includes: d in: (self tDoubtfulDescription))) ifFalse: ["
        //
        //		].    "END 1 to: (idxSuccessors size) do:"
        //
        //	].    "END 1 to: (successorList size) do:"
        //
        //	"Present the list of alternatives (associated to one index) to the user, preferably the cases"
        //	(caseAlternatives isEmpty)
        //	ifFalse:[
        //		result := (self presentChoices: caseAlternatives).
        //		(result = self) ifTrue: [ ^self ].
        //		(result = nil) ifTrue: [ self prepareFailedOutput. ^nil ].
        //		(result = #cancel) ifTrue: [ self prepareFailedOutput. self status: result. ^nil ].
        //	].
        //
        //	(normAlternatives isEmpty)
        //	ifFalse:[
        //		newList := OrderedCollection new.
        //		[normAlternatives isEmpty]
        //		whileFalse: [
        //			nA := normAlternatives removeFirst.
        //			(self isUseless: nA) ifFalse: [ newList add: nA ].
        //		].
        //		(newList isEmpty)
        //		ifFalse: [
        //			result := (self presentChoices: newList).
        //			(result = self) ifTrue: [ ^self ].
        //			(result = nil) ifTrue: [ self prepareFailedOutput. ^nil ].
        //			(result = #cancel) ifTrue: [ self prepareFailedOutput. self status: result. ^nil ].
        //
        //		].    "END (newList isEmpty) ifFalse:"
        //
        //	].    "END (normAlternatives isEmpty) ifFalse:["
        //
        //	"At this point, all alternatives for this norm failed because they were either unconfirmed or rejected
        //	due to doubt. Present to the user the possibility to BACKTRACK"
        //	answer := (SukiaDialog
        //				confirm: 'Hasta ahora las alternativas presentadas \no han ayudado a resolver el problema.\Desea continuar evaluando otras alternativas?'
        //				initialAnswer: false).
        //	(answer = false)
        //	ifTrue: [ self prepareFailedOutput. ^nil ].
        //
        //	^(self backtrack).</body>
        return "";
    }


    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void presentChoices(){
        //<body>presentChoices: alternativeList
        //
        //	"The elements of the argument alternativeList are lists of the form: (SAVDescriptor IndexValue)
        //	Returns:	self - if successful (i.e., at least one possible solution was found).
        //				nil - if retrieveCasesUnderNorm fails.
        //				#cancel - if the user cancels the dialog.
        //				#fail - if all alternatives were rejected (either because they did not match or the user was in doubt)
        //
        //	Automaton reference: This method is an extension of indexDialog, referenced by IdxDial"
        //
        //	| alternative message answer solution caseList value |
        //
        //	1 to: (alternativeList size) do:
        //	[:i |
        //
        //		(self isUseless: (alternativeList at: i))
        //		ifFalse: [
        //
        //			alternative := (alternativeList at: i) first.
        //			solution := ((alternativeList at: i) last successors) first.
        //
        //			((alternative value) respondsToArithmetic)
        //			ifTrue: [ value := (alternative value) printString ]
        //			ifFalse: [ value := (alternative value) asString ].
        //
        //			"Prepare the inquiry to be presented to the user"
        //			message := 'Presenta ' , ((alternative structure) asString) , ' la caracterí³Žica ' , ((alternative attribute) asString) ,
        //				' ' , value , '?'.
        //
        //			answer := (SukiaDialog
        //						choose: message
        //						for: SukiaDialog defaultParentWindow).
        //
        //			(answer = #confirm)
        //			ifTrue: [
        //
        //				"The solution is a norm"
        //				(solution class name = Norm getClassName)
        //				ifTrue: [
        //					self processNextNormWith: alternative and: solution.
        //					^(self retrieveCasesUnderCurrNorm)
        //				]
        //
        //				"The solution is a case"
        //				ifFalse: [
        //					self tConfirmedDescription: alternative.
        //					caseList := OrderedCollection new.
        //					caseList add: solution.
        //					^(self prepareSuccessfulOutputWith: (self associateCasesToPossibleSolutions: caseList))
        //				].
        //
        //			].    "END (answer = #confirm) ifTrue: ["
        //
        //			(answer = #reject) ifTrue: [ self tUnconfirmedDescription: alternative ].
        //			(answer = #doubt) ifTrue: [ self tDoubtfulDescription: alternative ].
        //			(answer = #cancel) ifTrue: [ ^answer ].
        //
        //		].    "END (self isUseless: (alternativeList at: i) ifFalse:"
        //
        //	].    "END 1 to: (alternativeList size) do: ["
        //
        //	^#fail.</body>
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void processNextNormWith(){
        //<body>processNextNormWith: aSAVDescriptor and: aNorm
        //
        //	"Automaton reference: PNN"
        //
        //	self tConfirmedDescription: aSAVDescriptor.
        //	self currentNorm: aNorm.
        //	self nextLevel.
        //	^self.</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void searchCasesDialogUsing(){

        //<body>searchCasesDialogUsing: aSAVDescriptor saveIn: aPossibleSolutionsList
        //
        //	"MANAGEMENT OF A POSSIBLY INNACURATE DESCRIPTOR.
        //	 This method establishes a dialog with the user. First, an index is searched, under the current
        //	 norm, that matches the descriptor argument's attribute only. If there is a match, a new SAVDescriptor
        //	 is created for every index value found for the retrieved index. The new information is presented to
        // 	 the user, in order for him/her to determine if it applies.
        //
        //	 Returns:	#idxNotFound - if an index was not found using the descriptor argument's attribute.
        //				#cancel - if the user cancels.
        //				#success - if a case was accepted.
        //				#fail - if index values point to norms, or the user rejected possibilities, or a combination
        //				       of both situtions occurred."
        //
        //	| idx indexValues idxValue descValue succ message pSolutionList d result displayValues returnValues descriptorList |
        //
        //	"Partial match: Look for an index under the current norm, whose label matches the
        //	 descriptor's attribute. Disregard the descriptor's value"
        //	idx := (self currentNorm) getIndexWith: (aSAVDescriptor attribute).
        //	(idx = nil) ifTrue: [ ^#idxNotFound ].
        //
        //	"The index was found. Create a temporary possible solutions list"
        //	pSolutionList := OrderedCollection new.
        //
        //	displayValues := List new.
        //	returnValues := List new.
        //	descriptorList := OrderedCollection new.
        //
        //	"Scan the index's index-values"
        //	indexValues := idx successors.
        //	1 to: (indexValues size) do:
        //	[:i |
        //		"Get the next index value successor"
        //		succ := ((indexValues at: i) successors) at: 1.
        //
        //		"Determine if the successor is a norm"
        //		(succ class name = (Norm getClassName))
        //		ifFalse: [
        //			"The successor is a case. Create a new SAVDescriptor with the information from the current
        //			 structure, index, and index value"
        //			d := SAVDescriptor new.
        //			d addStructure: (netRoot structure) Attribute: (idx label) Value: (indexValues at: i) value.
        //
        //			"Since this descriptor corresponds to a partial match, make sure that it is NOT already
        //			 included in neither the unconfirmed, doubtful, unmatched, solution, or confirmed descriptions"
        //			(((self includes: d in: (self tUnconfirmedDescription)) = nil) &amp;
        //			 ((self includes: d in: (self tDoubtfulDescription)) = nil) &amp;
        //			 ((self includes: d in: (self tUnmatchedDescription)) = nil) &amp;
        //			 ((self includes: d in: (self tSolutionDescription)) = nil) &amp;
        //			 ((self includes: d in: (self tConfirmedDescription)) = nil))
        //			ifTrue: [
        //
        //				"FUTURE IMPROVEMENT (documented by HB on 10-Sep-1999):
        //				 1. Retrieve the taxon corresponding to the successor case.
        //				 2. Retrieve the structure-atribute, from the taxon's description, that matches (d structure) and (d attribbute).
        //				 3. Retrieve the weighted-value list from the corresponding taxon's structure-attribute.
        //				 4. Determine, using the weighted-value list, SimAssessor, and SimRanges, the degree of similarity
        //					between d and aSAVDescriptor.
        //				 5. If the degree of similarity satisfies the user-defined expectation E7, then present d to the user.
        //					Else, don't present it to the user and continue with the rest of this process.
        //				 6. Document the whole process in the search process justification."
        //
        //				((d value) respondsToArithmetic)
        //				ifTrue: [ idxValue := (d value) printString ]
        //				ifFalse: [ idxValue := (d value) asString ].
        //
        //				displayValues add: idxValue.
        //				returnValues add: succ.
        //				descriptorList add: d.
        //
        //			].    "END (((self includes: d in: (self tUnconfirmedDescription)) = nil) &amp; ... ifTrue:"
        //
        //		].    "END (succ class name = (Norm getClassName)) ifFalse:"
        //
        //	].    "END 1 to: (indexValues size) do:"
        //
        //	(displayValues size = 0) ifTrue: [ ^#fail. ].
        //
        //	((aSAVDescriptor value) respondsToArithmetic)
        //	ifTrue: [ descValue := (aSAVDescriptor value) printString ]
        //	ifFalse: [ descValue := (aSAVDescriptor value) asString ].
        //
        //	message :=	(netRoot structure) , ': ' , (aSAVDescriptor attribute) , '.' ,
        //				'\No reconozco el valor ' , descValue , '\brindado en la descripció® €el\espé£©men. '.
        //
        //	(displayValues size = 1)
        //	ifTrue: [ message := message , 'Sin embargo, sí °uedo\reconocer el siguiente valor.\Es vá¬©do?' ]
        //	ifFalse: [ message := message , 'Sin embargo, sí °uedo\reconocer los siguientes valores.\Es alguno vá¬©do?' ].
        //
        //	result := (SukiaDialog
        //				choose: message
        //				fromList: displayValues
        //				values: returnValues
        //				lines: 8
        //				for: SukiaDialog defaultParentWindow).
        //
        //	(result = #cancel) ifTrue: [ ^#cancel ].
        //	(result = #reject) ifTrue: [ [descriptorList isEmpty ] whileFalse: [ self tUnconfirmedDescription: (descriptorList removeFirst) ]. ^#fail ].
        //	(result = #doubt) ifTrue: [ [descriptorList isEmpty ] whileFalse: [ self tDoubtfulDescription: (descriptorList removeFirst) ]. ^#fail ].
        //
        //	"At this point, the answer must be successful. Associate the confirmed case to PossibleSolution. Then exit successfully"
        //	self tConfirmedDescription: (descriptorList at: (returnValues indexOf: result)).
        //	pSolutionList add: result.
        //	pSolutionList := (self associateCasesToPossibleSolutions: pSolutionList).
        //	aPossibleSolutionsList add: (pSolutionList removeFirst).
        //	self tConfirmedDescription removeLast.
        //
        //	^#success.</body>
    }
/**
 *Category norm-pointing search
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void beginWith(){
        //<body>beginWith: aProblemDescription
        //
        //	"Initial state of the search automaton.  Control is placed on the root norm. The final step is a call to the next state
        //	 (i.e., searchForNormWith). NOTE: The argument aProblemDescription is a non-empty list of Descriptors.
        //
        //	 Precondition:
        //	 1. aProblemDescription is a non-empty set of SAVDescriptors.
        //	 2. For all s1, s2::SAVDescriptor in aProblemDescription : (s1 structure) = (s2 structure).
        //
        //	 Returns: nil - if the argument is an empty list, or
        //			  object returned by searchForNormWith:
        //
        //	Automaton reference: bW"
        //
        //	| sName |
        //
        //	"Check part 1. of the precondition"
        //	(aProblemDescription isEmpty)
        //	ifTrue: [ ^nil ].
        //
        //	"Check part 2. of precondition"
        //	(aProblemDescription at: 1) class name = (SAVDescriptor getClassName)
        //	ifFalse: [ ^nil ].
        //
        //	((aProblemDescription size) &gt; 1)
        //	ifTrue: [
        //		"Get the structure name of the first descriptor"
        //		sName := (aProblemDescription at: 1) structure.
        //
        //		2 to: (aProblemDescription size) do:
        //		[:i |
        //			((aProblemDescription at: i) class name = (SAVDescriptor getClassName)) ifFalse: [ ^nil ].
        //		      (sName = ((aProblemDescription at: i) structure)) ifFalse: [ ^nil ] ].
        //	].
        //
        //	"Initialization steps. At this point, nextLevel = 1. Thus, the root level is 1"
        //	self currentNorm: (self netRoot).
        //	self nextLevel.
        //
        //	"Move to the next state, and return its returning value (nil or self)"
        //	^(self searchForNormWith: aProblemDescription).</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void retrieveCasesUnderCurrNorm(){
        //<body>retrieveCasesUnderCurrNorm
        //
        //	"This method attempts to retrieve norm-dependant cases, from the stop-level norm.
        //	 At this point, the problem description is empty of descriptors.  If at least one case
        //	 was retrieved, prepare the successful output and stop the automaton. Else, start a
        //	 dialog with the user.
        //
        //	 Returns: object returned by either prepareSuccessfulOutputWith: (self), or indexDialog.
        //
        //	Automaton reference: RCUN"
        //
        //	| caseList |
        //
        //	caseList := (self currentNorm) successorCases.
        //	(caseList isEmpty)
        //	ifFalse: [ ^(self prepareSuccessfulOutputWith: (self associateCasesToPossibleSolutions: caseList)) ].
        //
        //	^(self indexDialog).</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void searchForNormWith(){
        //<body>searchForNormWith: aProblemDescription
        //
        //	"The argument aProblemDescription is scanned in search for descriptors that correspond
        //	 to indices pointing to norms. Every time one such descriptor is located, currNorm is reassigned and
        //	 the descriptor placed in the solution description.  If after the process the argument list is empty, a call
        //	 to state retrieveCasesUnderNorm is performed.  If the list is completely scanned and no descriptor was
        //	 located, a call to state searchForCasesWith is executed.
        //
        // 	 NOTE: The argument aProblemDescription is a list of Descriptors
        //
        //	 Returns: self, or
        //			  object returned by retrieveCasesUnderCurrNorm, or
        //			  object returned by searchForCasesUsing:
        //
        //	Automaton reference: SNW"
        //
        //	| i d nextNorm |
        //
        //	"If the problem description is empty, all its descriptors matched norms. Make the current norm
        //	 the stop-level norm, and start the search for cases under it"
        //	(aProblemDescription isEmpty)
        //	ifTrue: [ self stopLevel: (self currentLevel). ^(self retrieveCasesUnderCurrNorm) ].
        //
        //	"Scan the problem description"
        //	d := nil.
        //	i := 1.
        //	[ i &lt;= (aProblemDescription size) ]
        //	whileTrue: [
        //
        //		"Search for a norm whose descriptor matches the scanned descriptor. If found,
        //		 remove the descriptor from the problem case and stop the loop"
        //		nextNorm := (self currentNorm) successorWith: (aProblemDescription at: i).
        //		(nextNorm = nil)
        //		ifTrue: [ i := i + 1 ]
        //		ifFalse: [ d := (aProblemDescription removeAtIndex: i). i := (aProblemDescription size) + 1 ].
        //	].
        //
        //	"if no descriptor available, the entire list was scanned and no norm was found.  Start the search
        //	 for indices (using the remaining descriptors) that point to cases"
        //	(d = nil)
        //	ifTrue: [
        //
        //		self stopLevel: (self currentLevel).
        //		^(self searchForCasesUsing: aProblemDescription).
        //
        //	].    "END (d = nil) ifTrue:"
        //
        //	"A norm with a matching descriptor was found.  Add the removed descriptor to the solution
        //	 list, and move on to the next (recursive) norm search"
        //	self updateNormSearchWith: d and: nextNorm.
        //	^(self searchForNormWith: aProblemDescription).</body>

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void updateNormSearchWith(){
        //<body>updateNormSearchWith: aSAVDescriptor and: aNewNorm
        //
        //	"Automaton reference: UNS"
        //
        //	self tSolutionDescription: aSAVDescriptor.
        //	self currentNorm: aNewNorm.
        //	self nextLevel.
        //	^self.</body>

    }
/**
 *Category testing
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void includes(){
        //<body>includes: aSAVDescriptor in: aDescription
        //
        //	"Determines if aSAVDescriptor is already a member of aDescriptionList. The argument aSAVDescriptor is a member of
        //	aDescriptionList when its structure name, its attribute name, and its value match with the structure, attribute, and value
        //	of a list element.
        //	Returns: 	-1 (error state): The argument aDescriptionList IS NOT a valid list for self.
        //				nil: aSAVDescriptor IS NOT a member of aDescriptionList.
        //				not nil: an element of aDescriptionList whose structure and attribute names match those of aSAVDescriptor"
        //
        //	| d |
        //
        //	"First step: make sure that the process is executed against one of my lists"
        //	((aDescription = (self tSolutionDescription)) |
        //	 (aDescription = (self tConfirmedDescription)) |
        //	 (aDescription = (self tUnconfirmedDescription)) |
        //	(aDescription = (self tUnmatchedDescription)) |
        //	 (aDescription = (self tDoubtfulDescription)))
        //	ifFalse: [ ^(-1) ].
        //
        //	1 to: (aDescription size) do:
        //	[:i |
        //		d := aDescription at: i.
        //
        //		((d structure = aSAVDescriptor structure) &amp; (d attribute = aSAVDescriptor attribute) &amp; (d value = aSAVDescriptor value))
        //		ifTrue: [ ^d ].
        //	].
        //
        //	^nil.</body>
    }


}
