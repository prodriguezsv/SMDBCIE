
/*
 * @see "Categor�a Sukia Search Automata en SUKIA SmallTalk" 
 */

package system.searchAutomata;



import redundantDiscriminationNet.RootNorm;
import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


import redundantDiscriminationNet.Index;
import redundantDiscriminationNet.Node;
import redundantDiscriminationNet.Norm;
import redundantDiscriminationNet.SheetNode;
import system.PossibleSolution;
import system.searchAutomata.output.DFSAutomatonOutput;

/**
 * SAVCase Depth-First-Search Automaton.
 * 1. The search process is based on a problem description composed of a non-empty set of SAVDescriptors.
 * 2. The search strategy is depth-first. That is, using all possible SAVDescriptors from the problem description, advance as
 * deep as possible within the Case Memory, before selecting solution cases.  This strategy ensures that all descriptors
 * are considered.
 * @author pabloq
 */

public class SAVCaseDFSAutomaton {
    public RootNorm netRoot;
    public Norm currentNorm;
    public int currentLevel;
    public int stopLevel;
    private List<Descriptor<Object>> tSolutionDesc;
    private List<Descriptor<Object>> tConfirmedDesc;
    private List<Descriptor<Object>> tUnconfirmedDesc;
    private List<Descriptor<Object>> tDoubtfulDesc;
    private List<Descriptor<Object>> tUnmatchedDesc;
    private List<Descriptor<Object>> justification;
    public String status;
    public DFSAutomatonOutput searchOutput;

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SAVCaseDFSAutomaton(RootNorm aSAVRoot){
        netRoot = aSAVRoot;
        currentNorm = null;
        resetLevel();
        setStopLevel(currentLevel);
        tSolutionDesc = new ArrayList<Descriptor<Object>>();
        tConfirmedDesc = new ArrayList<Descriptor<Object>>();
        tUnconfirmedDesc = new ArrayList<Descriptor<Object>>();
        tDoubtfulDesc = new ArrayList<Descriptor<Object>>();
        tUnmatchedDesc = new ArrayList<Descriptor<Object>>();
        justification = new ArrayList<Descriptor<Object>>();
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
    public void setCurrentNorm(Norm aNorm){
        currentNorm = aNorm;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addJustification(Descriptor<Object> aJustificationElement){
        justification.add(aJustificationElement);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setNextLevel(){
        currentLevel += 1;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setPreviousLevel(){
        currentLevel -= 1;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void resetLevel(){
        currentLevel = 0;
    }
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setStatus(String aStatusValue){
        status = aStatusValue;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setStopLevel(int aLevelNumber){
     stopLevel = aLevelNumber;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setTConfirmedDescription(Descriptor<Object> aSAVDescriptor){
        tConfirmedDesc.add(aSAVDescriptor);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setTDoubtfulDescription(Descriptor<Object> aSAVDescriptor){
        tDoubtfulDesc.add(aSAVDescriptor);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setTSolutionDescription(Descriptor<Object> aSAVDescriptor){
        tSolutionDesc.add(aSAVDescriptor);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setTUnconfirmedDescription(Descriptor<Object> aSAVDescriptor){
        tUnconfirmedDesc.add(aSAVDescriptor);
    }
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void setTUnmatchedDescription(Descriptor<Object> aSAVDescriptor){
        tUnmatchedDesc.add(aSAVDescriptor);
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public int getCurrentLevel(){
        return currentLevel;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Object getCurrentNorm(){
        return currentNorm;
    }
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<Descriptor<Object>> getJustification(){
        return justification;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public RootNorm getNetRoot(){
        return netRoot;
    }
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public int getRootLevel(){
        return 1;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public DFSAutomatonOutput getSearchOutput(){
       return searchOutput;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public String getStatus(){
        return status;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public int getStopLevel(){
        return stopLevel;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<Descriptor<Object>> getTConfirmedDescription(){
        return tConfirmedDesc;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<Descriptor<Object>> getTDoubtfulDescription(){
    	return tDoubtfulDesc;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<Descriptor<Object>> getTSolutionDescription(){
        return tSolutionDesc;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<Descriptor<Object>> getTUnconfirmedDescription(){
        return tUnconfirmedDesc;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<Descriptor<Object>> getTUnmatchedDescription(){
        return tUnmatchedDesc;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public String backtrack(){
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
        currentNorm = currentNorm.getPredecessorNorm();
        setPreviousLevel();
        if ((currentLevel < stopLevel) || (currentNorm == netRoot)){
            return null;
        }
        String r = (String)verifyIndices();
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
        //	"Removes, from tConfirmedDescription, a Descriptor<Object> that matches aNorm's attribute
        //	 and value.  The removed Descriptor<Object> is placed in the tUnconfirmedDescription.
        //
        //	 Returns: nil - if the aNorm's level (i.e., currentLevel) is the less than or equal to the stopLevel,
        //				   or if the Descriptor<Object> was not found in tConfirmedDescriptor;
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
        for (Descriptor<Object> tcd : getTConfirmedDescription()) {

            if (tcd.getAttribute().equals(aNorm.getDescriptor().getAttribute()) &&
                    (tcd.getValue().equals(aNorm.getDescriptor().getValue()))){
                setTUnconfirmedDescription(getTConfirmedDescription().remove(++idx));
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
    public Object verifyIndices(){
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
        //		self - if at least one index value (in the form of a Descriptor<Object>) is not member of either the the unconfirmed
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
        //		"get the next successor. It's got to be an index!!"
        //		normSucc := (self currentNorm successors) at: i.
        //
        //		"get its list of successors (i.e., IndexValues)"
        //		idxSucc := normSucc succidxSuccessors.
        //
        //		"Scan the list of successors for the current index"
        //		1 to: idxSucc size do:
        //		[:j |
        //			"Create a Descriptor<Object>"
        //			d := Descriptor<Object> new.
        //			d addStructure: (self netRoot structure) Attribute: (normSucc label) Value: (idxSucc at: j) value.
        //
        //			"If the Descriptor<Object> is not a member of the unconfirmed description, check it against
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
        
        //Scan the list of successors for the current norm
        int successorsSize = currentNorm.getSuccessors().size();
        for (int i=0;(i<successorsSize);i++){
            //get the next successor. It's got to be an index!!
            Node normSucc = currentNorm.getSuccessors().get(i);

            if (normSucc instanceof Index) {
	            //get its list of successors (i.e., IndexValues)
	            List<Node> idxSucc = ((Index)normSucc).getSuccessors();
	
	            //Scan the list of successors for the current index
	            for (int j = 0; (j< idxSucc.size()); j++){
	                Descriptor<Object> d = new Descriptor<Object>();
	                d.set(netRoot.getStructure(), ((Index)normSucc).getLabel(), idxSucc.get(j).getValue());
	                //If the Descriptor<Object> is not a member of the unconfirmed description, check it against
	                //the doubtful description. If this time the SAVDescriptior is NOT a member of the doubtful
	                //description, return self, indicating that there is at least one index-value to show to the user
	
	                if (includes(d,getTUnconfirmedDescription())== null){
	                    if (includes(d,getTDoubtfulDescription())== null){
	                        return true;
	                    }
	                }
	            }
            }
        }
        
        //At this point, the entire list of norm successors was scanned, and all of them were members
        // of either the unconfirmed or doubtful descriptions. Return nil to indicate that another backtrack
        // must be performed. Before returning, the descriptor of the current norm must be removed from
        // the confirmed description and placed in the unconfirmed description
        if (removeSAVDescriptorFromTConfirmedDesc(currentNorm)==null){
            return null;
        }
        return "backtrack";
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Object searchForCasesUnderCurrNormUsing(List<Descriptor<Object>> aProblemDescription,List<PossibleSolution> aPossibleSolutionsList){
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
        //		"Remove the next Descriptor<Object>"
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
        //			"Index found. get the IndexValue successor"
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

        //Check precondition
        if ((aProblemDescription.isEmpty()) || (currentNorm == netRoot) || (aPossibleSolutionsList.isEmpty() != true)){
            return -1;
        }
        //Create the temporary process lists
        List  tempList = new ArrayList();
        List<PossibleSolution> pSolutionList = new ArrayList<PossibleSolution>();
        //Scan the the Descriptor list of the problem description. Look for indices that strictly point to cases

        while (aProblemDescription.isEmpty() != true){
            //Remove the next Descriptor<Object>
            Descriptor<Object> d = aProblemDescription.remove(0);
            //Look for a matching index

            Index idx = currentNorm.getSuccessorIndex(d.getAttribute(), d.getValue());


            //If the descriptor did not match any index, take it out of the problem description and
            //place it in the temporary list
            if (idx == null){
                //The descriptor may have an inaccurate value. Try to establish a dialog with the user.
                String result = (String)searchCasesDialogUsing(d,aPossibleSolutionsList);
                if (result.equals("cancel")){ return "cancel";}
                if (result.equals("fail") || result.equals("idxNotFound")){tempList.add(d);}
                if (result.equals("success")){setTUnmatchedDescription(d);}
            }else{
                //Index found. get the IndexValue successor
                Node succ = idx.getSuccessor(d.getValue()).getSuccessors().get(0);

                //If a matched index points to a Norm, don't process it. Place the descriptor in a temporary list
                if (succ instanceof Norm){
                    tempList.add(d);
                }else{
                    //The matched index points to a case. Place the corresponding descriptor in the solution description
                    //and associate the corresponding case to a PossibleSolution. Next, place the possible solution in the
                    //output possible solutions list. Finally, remove the descriptor from the solution description
                    setTSolutionDescription(d);

                    //PENDIENTE
                    //pSolutionList.add((SheetNode)succ); Ojo
                    // pSolutionList = associateCasesToPossibleSolutions(pSolutionList);
                    aPossibleSolutionsList.add(pSolutionList.get(0));
                    getTSolutionDescription().remove(getTSolutionDescription().size()-1);
                }
            }
        }
        //Put the descriptors that didn't match indices back in the problem description list
        while (tempList.isEmpty() != true){
            aProblemDescription.add((Descriptor<Object>)tempList.remove(0));
        }
        return true;

    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object searchForCasesUnderRootUsing(List<Descriptor<Object>> aProblemDescription,List<PossibleSolution> aPossibleSolutionsList){
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
        //		"Remove the next Descriptor<Object>"
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
        //			"Index found. get the IndexValue successor"
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

        //Check precondition
        
        if (aProblemDescription.isEmpty()){return -1;}

        if ((currentNorm == netRoot) && (aPossibleSolutionsList.isEmpty()) && (getTSolutionDescription().isEmpty()!=true)){return -1;}
        //Create the temporary process lists

        List<Descriptor<Object>> tempList = new ArrayList<Descriptor<Object>>();
        List<SheetNode> pSolutionList = new ArrayList<SheetNode>();

        //Scan the the Descriptor list of the problem description. Look for indices that strictly point to cases
        while (aProblemDescription.isEmpty() != true){
            //Remove the next Descriptor<Object>
            Descriptor<Object> d = aProblemDescription.get(0);
            //Look for a matching index
            Index idx = currentNorm.getSuccessorIndex(d.getAttribute(),d.getValue());

            //If the descriptor did not match any index, take it out of the problem description and
            //place it in the unmatched descriptor list

            if (idx == null){
                //At this point, the descriptor inevitably goes to the unmatched description
                setTUnmatchedDescription(d);

                //However, the descriptor may have an inaccurate value. Try to establish a dialog with the user
                //using a partial match.

                String result = searchCasesDialogUsing(d,aPossibleSolutionsList);
                if (result.equals("cancel")){return "cancel";}

            }else{
                //Index found. get the IndexValue successor
                Object succ = idx.getSuccessor(d.getValue()).getSuccessors().get(0);

                if (succ instanceof Norm){
                    tempList.add(d);
                }else{
                    //The matched index points to a case. Place the corresponding descriptor in the solution description
                    //and associate the corresponding case to a PossibleSolution. Next, place the possible solution in the
                    //output possible solutions list. Finally, remove the descriptor from the solution description
                    setTSolutionDescription(d);

                    //PENDIENTE
                    // pSolutionList.add((SheetNode)succ);
                    // pSolutionList = associateCasesToPossibleSolutions(pSolutionList);
                    // aPossibleSolutionsList.add(pSolutionList.remove(0));
                    getTSolutionDescription().remove(0);

                }
            }

        }
        //Put the descriptors that match Norms back in the problem description list
        while (tempList.isEmpty() != true){
            aProblemDescription.add(tempList.remove(0));

        }
        return true;
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object searchForCasesUsing(List<Descriptor<Object>> aProblemDescription){
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


        //If the argument search-list is empty, something wrong happened. Return error value

        if (aProblemDescription.isEmpty()){prepareFailedOutput(); status = "error"; return null;}
        //Make sure the current level is ALWAYS greater than or equal to the root level

        if (currentLevel < getRootLevel()){prepareFailedOutput(); status = "error"; return null;}

        List<PossibleSolution> possibleSolutions = new ArrayList<PossibleSolution>();

        //Check if control is located at the root (i.e., no Norm was ever found in the previous state)

        if (currentLevel == getRootLevel()){
            //Search for cases pointed to by indices directly under the root. If there are any, the argument
            //list possibleSolutions will not be empty
            Object result = searchForCasesUnderRootUsing(aProblemDescription,possibleSolutions);
            if (result.equals("cancel")){prepareFailedOutput();status = "cancel"; return null;}
            if (((Integer)result)== -1 ){prepareFailedOutput();status = "error"; return null;}

            //If the possible solutions list has at least one item, return self along  with whatever Descriptors
            //remained in the problem description. This guarantees that all left-over Descriptors point to Norms
            if (possibleSolutions.isEmpty() != true){
                prepareSuccessfulOutputWith(possibleSolutions);
            }

            //If the case list is empty, either the problem description has all descriptors that point to Norms
            //in other paths, OR all descriptors are located in the unmatched list. Return nil
            prepareFailedOutput();
            return null;

        }
        // At this point, control is located on a Norm other than the net root. Initially, scan the Descriptor list in search of indices
        // that strictly point to cases. If a matched index points to a case, remove the corresponding Descriptor from the problem
        // description and place it in the solution description.  Next, associate the retrieved case to a PossibleSolution, and place
        // this possible solution in the case list. Finally, remove the last descriptor of the solution list. If the matched index DOES
        // NOT point to a case, leave the corresponding Descriptor in the problem description

        Object result = searchForCasesUnderCurrNormUsing(aProblemDescription,possibleSolutions);
        if (result.equals("cancel")){prepareFailedOutput(); status = "cancel"; return null;}
        if (((Integer)result)== -1 ){prepareFailedOutput();status = "error"; return null;}

        // If at the end of the scanning process, the problem description is empty, the possible solutions list MUST have at least
        // one item. Else, something weird happened.  In that situation, return the error value. However, if the case list is not empty,
        // return self
        if (aProblemDescription.isEmpty()){
            if (possibleSolutions.isEmpty()){prepareFailedOutput();status = "error"; return null;}
            return prepareSuccessfulOutputWith(possibleSolutions);
        }else{

        }
        // At this point, the problem description is not empty. Move control to the root and search for
        // indices that strictly point to cases.
        Norm savNorm = currentNorm;
        int savLevel = currentLevel;
        setCurrentNorm(netRoot);
        resetLevel();
        setNextLevel();
        
        //Searching for cases under the root means that the solution description thus far obtained is invalid.
        //Therefore, this description should be considered as unmatched. Thus, place the solution description
        //items in the unmatched description, and also copy them to a temporary list. The reason for the copy
        //list is that, depending on the output from the root-search, it may be necessary to put all descriptors
        //back in the solution description, in order to try the next search strategy

        List<Descriptor<Object>> tempDeleteSolution = new ArrayList<Descriptor<Object>>();
        moveDescriptorsFrom(getTSolutionDescription(),getTUnmatchedDescription(),tempDeleteSolution);

        // Same sitution as with the solution description.  In this case, place the confirmed description items in the
        // unconfirmed description, and also copy them to another temporary list

        List<Descriptor<Object>> tempDeleteConfirmed = new ArrayList<Descriptor<Object>>();
        moveDescriptorsFrom(getTConfirmedDescription(),getTUnconfirmedDescription(),tempDeleteConfirmed);
        
        // Call the search-cases-under-root method with a clean &amp; empty possible solutions list (its part of the precondition)
        List<PossibleSolution> pSolutions = new ArrayList<PossibleSolution>();

        result = searchForCasesUnderRootUsing(aProblemDescription,pSolutions);
        if (result.equals("cancel")){prepareFailedOutput(); status = "cancel"; return null;}
        if (((Integer)result)== -1 ){prepareFailedOutput();status = "error"; return null;}
        
        //Concatenate the solution lists into one single list
        while (pSolutions.isEmpty() != true){possibleSolutions.add(pSolutions.remove(0));}

        //Restore everything to its previous state

        setCurrentNorm(savNorm);
        resetLevel();

        while (savLevel> 0) {
            setNextLevel();
            savLevel = savLevel -1;
        }
        // If after the root-search the possible solutions and problem description lists are empty, the next strategy is to try
        // retrieving cases from the current norm. Therefore, just as with the current norm and current level, both the solution
        // and confirmed descriptions MUST be set back to their original state (i.e., before doing the root-search). So, remove
        // all the matching items in the temporary lists from the unmatched and unconfirmed descriptions and place them back
        // in the corresponding solution and confirmed ones
        deleteDescriptorsIn(tempDeleteSolution,getTUnmatchedDescription(),getTSolutionDescription());
        deleteDescriptorsIn(tempDeleteConfirmed,getTUnconfirmedDescription(),getTConfirmedDescription());

        //Upon return from the root search, the following situations may occur:
        //	a) the possible solutions list is empty.
        //		a1) if the problem description is now empty (i.e., all Descriptors are located in the unmatched list), then call the
        //		     state-method retrieveCasesUnderCurrNorm, in order to give the search process a last chance.
        //		a2) if the problem description is still NOT empty (i.e., the remaining descriptors point to Norms in other paths), return nil.
        //	b) the possible solutions list is NOT empty. Return self, regardless of whether or not the problem description is empty.

        if (possibleSolutions.isEmpty()){
            //Precondition for method retrieveCasesUnderCurrNorm : isEmpty(aProblemDescription)
            if (aProblemDescription.isEmpty()){
                return retrieveCasesUnderCurrNorm();
            }
            prepareFailedOutput();
            return null;
        }
        return prepareSuccessfulOutputWith(possibleSolutions);

    }
/**
 *Category private
 */
 /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private List<PossibleSolution> associateCasesToPossibleSolutions(List<SheetNode> aCaseList){
        //<body>associateCasesToPossibleSolutions: aCaseList
        //
        //	"This method is used in conjuntion with prepareSuccessfulOutput.  The purpose
        //	 of this method is to create an instance of PossibleSolution for every case in the
        //	 list argument aCaseList.
        //
        //	Returns: a list of PossibleSolutions.
        //
        //	 Automaton reference: none."

        List<PossibleSolution> psList = new ArrayList<PossibleSolution>();
        for (SheetNode mycase: aCaseList){
            PossibleSolution ps = new PossibleSolution();
            ps.setSolution(mycase);
            ps.copy(getTSolutionDescription(), ps.getSolutionDescription());
            ps.copy(getTConfirmedDescription(), ps.getConfirmedDescription());
            ps.copy(getTUnconfirmedDescription(), ps.getUnconfirmedDescription());
            ps.copy(getTDoubtfulDescription(), ps.getDoubtfulDescription());
            psList.add(ps);
        }
        return psList;
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void deleteDescriptorsIn(List<Descriptor<Object>> aTempDeleteList,List<Descriptor<Object>> aList,List<Descriptor<Object>> anotherList){
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
        for (int i=0;(i<aTempDeleteList.size());i++){
            Descriptor<Object> d = aTempDeleteList.get(i);
            int j = 0;
            while (j<= aList.size()){
                if ((aList.get(j).getAttribute().equals(d.getAttribute())) &&(aList.get(j).getAttribute().equals(d.getAttribute()))){
                    aList.remove(0);
                    j = aList.size() + 1;
                }else{
                    j += 1;
                }
            }
            anotherList.add(d);
        }
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private List<Norm> filterUselessNorms(List<Norm> aNormAlternativeList){

        List<Norm> newList = new ArrayList<Norm>();
        while (aNormAlternativeList.isEmpty() != true){
            Node normAlternative = aNormAlternativeList.remove(0);
            //n := (normAlternative last successors) first.
            if ((normAlternative instanceof SheetNode)){return null;}

            if (((Norm)normAlternative).successorCases().isEmpty() != true){
                newList.add((Norm)normAlternative);
            }else{
                //First, get the list of successors for the current norm
                List<Index> successorList = ((Norm)normAlternative).successorIndexes();
                for (int i = 0; (i<successorList.size());i++){
                    //Proceed to extract the next index
                    Index idx = successorList.get(i);

                    //Parse the list of IndexValues associated to the index
                    List<Node> idxSuccessors = idx.getSuccessors();
                    for (int j = 0; (j<idxSuccessors.size());j++){
                        //For every associated IndexValue: create a Descriptor<Object>
                        Descriptor<Object> d = new Descriptor<Object>();
                        d.set(netRoot.getStructure(), idx.getLabel(), idxSuccessors.get(j).getValue());

                        //Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions
                        if ((includes(d,getTUnconfirmedDescription())== null) &&(includes(d,getTDoubtfulDescription())== null)){
                            newList.add((Norm)normAlternative);
                        }
                    }

                }
            }
        }
        return newList;

    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void flush(List<Descriptor<Object>> aList){
        //<body>flush: aList
        //
        //	[ aList isEmpty ]
        //	whileFalse: [ aList removeFirst ].
        //	^self.</body>
        aList.clear();
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private boolean isUseless(Alternative anAlternative){
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
        //			"For every associated IndexValue: create a Descriptor<Object>"
        //			d := Descriptor<Object> new.
        //			d addStructure: (netRoot structure) Attribute: (idx label) Value: ((idxSuccessors at: j) value).
        //
        //			"Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions"
        //			(((self includes: d in: (self tUnconfirmedDescription)) = nil) &amp; ((self includes: d in: (self tDoubtfulDescription)) = nil))
        //			ifTrue: [ ^false. ]
        //
        //		].    "END 1 to: (idxSuccessors size) do:"
        //
        //	].    "END 1 to: (successorList size) do:"
        //stopLevel
        //	^true.</body>

        Node n = anAlternative.getNode().getSuccessors().get(0);
        if ((n instanceof SheetNode)){return false;}
        if (((Norm)n).successorCases().isEmpty() != true){return false;}
        //First, get the list of successors for the current norm
        List<Index> successorList = ((Norm)n).successorIndexes();

        for (int i = 0; (i<successorList.size());i++){
            //Proceed to extract the next index
            Index idx = successorList.get(i);
            //Parse the list of IndexValues associated to the index
            List<Node> idxSuccessors = idx.getSuccessors();
            for (int j = 0; (j<idxSuccessors.size());j++){
                //For every associated IndexValue: create a Descriptor<Object>
                Descriptor<Object> d = new Descriptor<Object>();
                d.set(netRoot.getStructure(), idx.getLabel(), idxSuccessors.get(j).getValue());
                //Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions
                if ((includes(d,getTUnconfirmedDescription()) == null) && (includes(d,getTDoubtfulDescription()) == null)){ return false;}
            }
        }
        return true;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void moveDescriptorsFrom(List<Descriptor<Object>> aList,List<Descriptor<Object>> anotherList,List<Descriptor<Object>> aCopyList){
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
        while (aList.isEmpty() != true){
            Descriptor<Object> d  = aList.remove(0);
            anotherList.add(d);
            aCopyList.add(d);
        }
    }
/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void newSearchWith(List<Descriptor<Object>> anOldProblemDescription){
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
        flush(getTSolutionDescription());
        flush(getTConfirmedDescription());
        flush(getTUnmatchedDescription());
        flush(justification);
        setStatus("fail");
        setCurrentNorm(null);
        resetLevel();
        setStopLevel(currentLevel);
        newOutput();
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private Object prepareFailedOutput(){
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
        if (searchOutput.getJustification() != null){return true;}
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(getTUnmatchedDescription());
        return true;
        


    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private boolean prepareSuccessfulOutputWith(List<PossibleSolution> aPossibleSolutionsList){
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
        if (searchOutput.getPossibleSolutions() != null)
        {return true;}
        searchOutput.setPossibleSolutions(aPossibleSolutionsList);
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(getTUnmatchedDescription());
        setStatus("success");
        return true;

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

        List<Alternative> caseAlternatives = new ArrayList<Alternative>();
        List<Alternative> normAlternatives = new ArrayList<Alternative>();
        
        //First, get the list of successors for the current norm
        List<Index> successorList = currentNorm.successorIndexes();

        for (int i=0;(i<successorList.size());i++){

            //Proceed to extract the next index
            Index idx = successorList.get(i);

            //Parse the list of IndexValues associated to the index
            
            List<Node> idxSuccessors = idx.getSuccessors();
            for (int j=0;(j<idxSuccessors.size());j++){
                Descriptor<Object> d =  new Descriptor<Object>();

                d.set(netRoot.getStructure(), idx.getLabel(), idxSuccessors.get(j).getValue());

                //Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions
                if ((includes(d,getTUnconfirmedDescription()) == null) && ((includes(d,getTDoubtfulDescription()) == null))){
                    //Pack the descriptor, along with the associated IndexValue, in a list called alternative. The form of this list
                    //will be (Descriptor<Object> IndexValue)
                    Alternative alternative = new Alternative();
                    alternative.setDescriptor(d);
                    alternative.setNode(idxSuccessors.get(j));

                    //(((idxSuccessors at: j) successors) first class name = Norm getClassName)
                    if (idxSuccessors.get(j).getValue() instanceof Norm){
                        normAlternatives.add(alternative);
                    }else{
                        caseAlternatives.add(alternative);
                    }

                }

                
            }
        }
        //Present the list of alternatives (associated to one index) to the user, preferably the cases
        if (caseAlternatives.isEmpty() != true){
            String result = presentChoices(caseAlternatives);
            
            //TODO this
            //		(result = self) ifTrue: [ ^self ].
            if (result == null){ prepareFailedOutput(); return null;}
            if (result.equals("cancel")){prepareFailedOutput(); status = result; return null;}
            
        }


        if (normAlternatives.isEmpty() != true){

            List<Alternative> newList = new ArrayList<Alternative>();
            while (normAlternatives.isEmpty() != true){
                Alternative nA = normAlternatives.get(0);
                if (isUseless(nA) != false){newList.add(nA);}
            }
            if (newList.isEmpty() != true){
                String result = presentChoices(newList);
                //TODO
                //(result = self) ifTrue: [ ^self ].
                if (result == null){prepareFailedOutput(); return null;}
                if (result.equals("cancel")){prepareFailedOutput(); status = result; return null;}

            }

        }
        //At this point, all alternatives for this norm failed because they were either unconfirmed or rejected
        //due to doubt. Present to the user the possibility to BACKTRACK

        //TODO DIALOG
        //	answer := (SukiaDialog
        //				confirm: 'Hasta ahora las alternativas presentadas \no han ayudado a resolver el problema.\Desea continuar evaluando otras alternativas?'
        //				initialAnswer: false).
        boolean answer = false;
        if (answer == false){
            prepareFailedOutput();
            return null;
        }
        return backtrack();
    }


    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String presentChoices(List<Alternative> alternativeList){
        
        //<body>presentChoices: alternativeList
        //
        //	"The elements of the argument alternativeList are lists of the form: (Descriptor<Object> IndexValue)
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

        for (Alternative al: alternativeList){
            if (isUseless(al)!= true){
            //TODO PRESENT OPTIONs in dialog


                String answer = null;
            return answer;

            }

        }

        return "fail";
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void processNextNormWith(Descriptor<Object> aSAVDescriptor,Norm aNorm){
        //<body>processNextNormWith: aSAVDescriptor and: aNorm
        //
        //	"Automaton reference: PNN"
        //
        //	self tConfirmedDescription: aSAVDescriptor.
        //	self currentNorm: aNorm.
        //	self nextLevel.
        //	^self.</body>
        setTConfirmedDescription(aSAVDescriptor);
        setCurrentNorm(aNorm);
        setNextLevel();
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public String searchCasesDialogUsing(Descriptor<Object> aSAVDescriptor,List<PossibleSolution> aPossibleSolutionsList){

        //<body>searchCasesDialogUsing: aSAVDescriptor saveIn: aPossibleSolutionsList
        //
        //	"MANAGEMENT OF A POSSIBLY INNACURATE DESCRIPTOR.
        //	 This method establishes a dialog with the user. First, an index is searched, under the current
        //	 norm, that matches the descriptor argument's attribute only. If there is a match, a new Descriptor<Object>
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
        //		"get the next index value successor"
        //		succ := ((indexValues at: i) successors) at: 1.
        //
        //		"Determine if the successor is a norm"
        //		(succ class name = (Norm getClassName))
        //		ifFalse: [
        //			"The successor is a case. Create a new Descriptor<Object> with the information from the current
        //			 structure, index, and index value"
        //			d := Descriptor<Object> new.
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

        //Partial match: Look for an index under the current norm, whose label matches the
        //descriptor's attribute. Disregard the descriptor's value
        Index idx = currentNorm.getSuccessorIndex(aSAVDescriptor.getAttribute());
        if (idx == null) {return "idxNotFound";}

        //The index was found. Create a temporary possible solutions list
        List<PossibleSolution> pSolutionList = new ArrayList<PossibleSolution>();

        List displayValues = new ArrayList();
        List returnValues = new ArrayList();
        List<Descriptor<Object>> descriptorList = new ArrayList<Descriptor<Object>>();

        //Scan the index's index-values
        List<Node> indexValues = idx.getSuccessors();
        for (int i = 0; (i<indexValues.size());i++){
            //get the next index value successor
            //TODO check if this is correct
            Object succ = ((Index)indexValues.get(i).getValue()).getSuccessors().get(0);

            //Determine if the successor is a norm
            if ((succ instanceof Norm) != true){
                //The successor is a case. Create a new Descriptor<Object> with the information from the current
                //structure, index, and index value
                Descriptor<Object> d = new Descriptor<Object>();
                d.set(netRoot.getStructure(), idx.getLabel() , indexValues.get(i).getValue());

                //Since this descriptor corresponds to a partial match, make sure that it is NOT already
                //included in neither the unconfirmed, doubtful, unmatched, solution, or confirmed descriptions
                if (((includes(d,getTUnconfirmedDescription())) == null) &&
                ((includes(d,getTDoubtfulDescription())) == null) &&
                ((includes(d,getTUnmatchedDescription())) == null) &&
                ((includes(d,getTSolutionDescription())) == null) &&
                ((includes(d,getTConfirmedDescription()))) == null){
                    //FUTURE IMPROVEMENT (documented by HB on 10-Sep-1999):
                    //1. Retrieve the taxon corresponding to the successor case.
                    //2. Retrieve the structure-atribute, from the taxon's description, that matches (d structure) and (d attribbute).
                    //3. Retrieve the weighted-value list from the corresponding taxon's structure-attribute.
                    //4. Determine, using the weighted-value list, SimAssessor, and SimRanges, the degree of similarity
                    //between d and aSAVDescriptor.
                    //5. If the degree of similarity satisfies the user-defined expectation E7, then present d to the user.
                    //Else, don't present it to the user and continue with the rest of this process.
                    //6. Document the whole process in the search process justification.
                    String idxValue = null;
                    if (d.getValue() instanceof Integer){
                        idxValue = ((Integer)d.getValue()).toString();
                    }else{
                        idxValue = (String)d.getValue();
                    }
                    displayValues.add(idxValue);
                    returnValues.add(succ);
                    descriptorList.add(d);


                }
            }
            
        }
        if (displayValues.size() == 0){return "fail";}
        String descValue = null;
        if (aSAVDescriptor.getValue() instanceof Integer){
            descValue = ((Integer)(aSAVDescriptor.getValue())).toString();
        }else{
            descValue = (String)aSAVDescriptor.getValue();
        }
        //TODO remplace with Java Dialog
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

        String result = null;
        
        if (result.equals("cancel")){return "cancel";}
        if (result.equals("reject")){
            while (descriptorList.isEmpty() != true){
                setTUnconfirmedDescription(descriptorList.remove(0));
            }
            return "fail";
        }
        if (result.equals("doubt")){
            while (descriptorList.isEmpty() != true){
                setTDoubtfulDescription(descriptorList.remove(0));
            }
            return "fail";
        }

        //At this point, the answer must be successful.
        //Associate the confirmed case to PossibleSolution. Then exit successfully
        setTConfirmedDescription(descriptorList.get(returnValues.indexOf(result)));

        //PENDIENTE
//        pSolutionList.add(result);
//        pSolutionList = associateCasesToPossibleSolutions(pSolutionList);


        aPossibleSolutionsList.add(pSolutionList.remove(0));
        getTConfirmedDescription().remove(getTConfirmedDescription().size()-1);
        return "success";
    }
/**
 *Category norm-pointing search
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object beginWith(List<Descriptor<Object>> aProblemDescription){
        //<body>beginWith: aProblemDescription
        //
        //	"Initial state of the search automaton.  Control is placed on the root norm. The final step is a call to the next state
        //	 (i.e., searchForNormWith). NOTE: The argument aProblemDescription is a non-empty list of Descriptors.
        //
        //	 Precondition:
        //	 1. aProblemDescription is a non-empty set of SAVDescriptors.
        //	 2. For all s1, s2::Descriptor<Object> in aProblemDescription : (s1 structure) = (s2 structure).
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
        //	(aProblemDescription at: 1) class name = (Descriptor<Object> getClassName)
        //	ifFalse: [ ^nil ].
        //
        //	((aProblemDescription size) &gt; 1)
        //	ifTrue: [
        //		"get the structure name of the first descriptor"
        //		sName := (aProblemDescription at: 1) structure.
        //
        //		2 to: (aProblemDescription size) do:
        //		[:i |
        //			((aProblemDescription at: i) class name = (Descriptor<Object> getClassName)) ifFalse: [ ^nil ].
        //		      (sName = ((aProblemDescription at: i) structure)) ifFalse: [ ^nil ] ].
        //	].
        //
        //	"Initialization steps. At this point, nextLevel = 1. Thus, the root level is 1"
        //	self currentNorm: (self netRoot).
        //	self nextLevel.
        //
        //	"Move to the next state, and return its returning value (nil or self)"
        //	^(self searchForNormWith: aProblemDescription).</body>

        //Check part 1. of the precondition
        if (aProblemDescription.isEmpty()){return null;}

        //Check part 2. of precondition
        if ((aProblemDescription.get(0) instanceof Descriptor) != true){return null;}
        String sName = null;
        if (aProblemDescription.size()>0){
            sName = aProblemDescription.get(0).getStructure();
        }
        for (int i=1;(i<aProblemDescription.size());i++){
            if ((aProblemDescription.get(i) instanceof Descriptor) != true){return null;}
            if ((sName.equals(aProblemDescription.get(i).getStructure())) != true){return null;}
        }
        //Initialization steps. At this point, nextLevel = 1. Thus, the root level is 1
        setCurrentNorm(netRoot);
        setNextLevel();

        //Move to the next state, and return its returning value (nil or self)
        return searchForNormWith(aProblemDescription);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object retrieveCasesUnderCurrNorm(){
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
        List<SheetNode> caseList = currentNorm.successorCases();
        if (caseList.isEmpty() != true){

            return prepareSuccessfulOutputWith(associateCasesToPossibleSolutions(caseList));

        }
        return indexDialog();



    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object searchForNormWith(List<Descriptor<Object>> aProblemDescription){
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

        //If the problem description is empty, all its descriptors matched norms. Make the current norm
        //the stop-level norm, and start the search for cases under it
        if (aProblemDescription.isEmpty()){
            setStopLevel(currentLevel);
            return retrieveCasesUnderCurrNorm();
        }
        
        //Scan the problem description
        int i = 0;
        Descriptor<Object> d = null;
        Norm nextNorm = null;
        while (i<=aProblemDescription.size()){
            //Search for a norm whose descriptor matches the scanned descriptor. If found,
            //remove the descriptor from the problem case and stop the loop
            nextNorm = currentNorm.getSuccessorNorm(aProblemDescription.get(i));

            if (nextNorm == null){
                i += 1;
            }else{

                d = aProblemDescription.remove(i);
                i = aProblemDescription.size() + 1;

            }
        }
        //if no descriptor available, the entire list was scanned and no norm was found.  Start the search
        //for indices (using the remaining descriptors) that point to cases
        if (d == null){
            setStopLevel(currentLevel);
            return searchForCasesUsing(aProblemDescription);
        }
        
        //A norm with a matching descriptor was found.  Add the removed descriptor to the solution
        //list, and move on to the next (recursive) norm search
        updateNormSearchWith(d,nextNorm);
        return searchForNormWith(aProblemDescription);
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void updateNormSearchWith(Descriptor<Object> aSAVDescriptor,Norm aNewNorm){
        //<body>updateNormSearchWith: aSAVDescriptor and: aNewNorm
        //
        //	"Automaton reference: UNS"
        //
        //	self tSolutionDescription: aSAVDescriptor.
        //	self currentNorm: aNewNorm.
        //	self nextLevel.
        //	^self.</body>
        setTSolutionDescription(aSAVDescriptor);
        currentNorm = aNewNorm;
        setNextLevel();

    }
/**
 *Category testing
 */
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object includes(Descriptor<Object> aSAVDescriptor,List<Descriptor<Object>> aDescription){
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
        if (((aDescription == getTSolutionDescription()) ||
            (aDescription == getTConfirmedDescription()) ||
            (aDescription == getTUnconfirmedDescription()) ||
            (aDescription == getTUnmatchedDescription()) ||
            (aDescription == getTDoubtfulDescription())) != true){return -1;}
        for (Descriptor<Object> d: aDescription){
            if ((d.getStructure().equals(aSAVDescriptor.getStructure())) &&
                (d.getAttribute().equals(aSAVDescriptor.getAttribute())) &&
                (d.getValue().equals(aSAVDescriptor.getValue()))){return d;}

        }
        return null;

    }


}
