/*
 * Paquete que se encarga de las funciones de b&uacute;squeda a trav&eacute;s de algoritmos de entre los
 * cuales se encuentra la b&uacute;squeda primero en profundidad y back-tracking
 * @see "Categor&iacute;a Sukia Search Automata en SUKIA SmallTalk" 
 */

package system.searchAutomata;

import redundantDiscriminationNet.RootNorm;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.values.SingleValue;


import redundantDiscriminationNet.Index;
import redundantDiscriminationNet.Node;
import redundantDiscriminationNet.Norm;
import redundantDiscriminationNet.SheetCase;
import system.PossibleSolution;
import system.searchAutomata.output.CaseBaseDFSAutomatonOutput;

/**
 * SAVCase Depth-First-Search Automaton.
 * 1. The search process is based on a problem description composed of a non-empty set of SAVDescriptors.
 * 2. The search strategy is depth-first. That is, using all possible SAVDescriptors from the problem
 * description, advance as deep as possible within the Case Memory, before selecting solution cases. This
 * strategy ensures that all descriptors are considered.
 * @author pabloq
 */

public class CaseBaseDFSAutomaton {
    public RootNorm netRoot;
    public Norm currentNorm;
    public int currentLevel;
    public int stopLevel;
    private Description tSolutionDesc;
    private Description tConfirmedDesc;
    private Description tUnconfirmedDesc;
    private Description tDoubtfulDesc;
    private Description tUnmatchedDesc;
    private Description justification;
    public SearchStatus status;
    public CaseBaseDFSAutomatonOutput searchOutput;

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public CaseBaseDFSAutomaton(RootNorm aSAVRoot){
        netRoot = aSAVRoot;
        currentNorm = null;
        resetLevel();
        setStopLevel(currentLevel);
        tSolutionDesc = new Description();
        tConfirmedDesc = new Description();
        tUnconfirmedDesc = new Description();
        tDoubtfulDesc = new Description();
        tUnmatchedDesc = new Description();
        justification = new Description();
        newOutput();
        status = SearchStatus.FAIL;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void newOutput(){
        searchOutput = new CaseBaseDFSAutomatonOutput();
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
    public void addToJustification(Descriptor aJustificationElement){
        justification.addToConcreteDescription(aJustificationElement);
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
    public void setStatus(SearchStatus aStatusValue){
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
    public void addToTConfirmedDescription(Descriptor aSAVDescriptor){
        tConfirmedDesc.addToConcreteDescription(aSAVDescriptor);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addToTDoubtfulDescription(Descriptor aSAVDescriptor){
        tDoubtfulDesc.addToConcreteDescription(aSAVDescriptor);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addToTSolutionDescription(Descriptor aSAVDescriptor){
        tSolutionDesc.addToConcreteDescription(aSAVDescriptor);
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addToTUnconfirmedDescription(Descriptor aSAVDescriptor){
        tUnconfirmedDesc.addToConcreteDescription(aSAVDescriptor);
    }
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addToTUnmatchedDescription(Descriptor aSAVDescriptor){
        tUnmatchedDesc.addToConcreteDescription(aSAVDescriptor);
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
    public Description getJustification(){
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
    public CaseBaseDFSAutomatonOutput getSearchOutput(){
       return searchOutput;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SearchStatus getStatus(){
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
    public Description getTConfirmedDescription(){
        return tConfirmedDesc;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Description getTDoubtfulDescription(){
    	return tDoubtfulDesc;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Description getTSolutionDescription(){
        return tSolutionDesc;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Description getTUnconfirmedDescription(){
        return tUnconfirmedDesc;
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Description getTUnmatchedDescription(){
        return tUnmatchedDesc;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SearchStatus backtrack(){
        if (!removeFromTConfirmedDescription(currentNorm.getDescriptor())) {
            prepareFailedOutput();
            return SearchStatus.FAIL;
        }
        
        if(!processPreviousNorm()){
            prepareFailedOutput();
            return SearchStatus.FAIL;
        }
        
        return indexDialog();
    }
    
    /**
     * Moves up a norm. This operation fails if an attempt is made to move farther up than the stop-level
     * norm, or if control is already at the net root.  If all goes well, an index verification is performed
     * on the norm. 
     * Automaton reference: PPN
	 * @see Define method name.
	 * @param my parameters list
	 * @return Returns: nil - if the process fails; self - if everything dandy.

	 */
    public boolean processPreviousNorm(){
        currentNorm = currentNorm.getNearestPredecessorNorm();
        this.setPreviousLevel();
        if ((currentLevel < stopLevel) || (currentNorm == netRoot))
            return false;

        return verifyIndices();        
    }
    /**
     * Removes, from tConfirmedDescription, a Descriptor<Object> that matches aNorm's attribute 
     * and value.  The removed Descriptor<Object> is placed in the tUnconfirmedDescription.
     * Automaton reference: RtCD
	 * @see Define method name.
	 * @param my parameters list
	 * @return null - if the aNorm's level (i.e., currentLevel) is the less than or equal to the stopLevel,
     * or if the Descriptor<Object> was not found in tConfirmedDescriptor;
     * self - if all OK.
	 */
    public boolean removeFromTConfirmedDescription(Descriptor descritor){
        
        if (currentLevel <= stopLevel) return false;
        
        for (Descriptor tcd : getTConfirmedDescription()) {
            if (tcd.equals(descritor)) {
                addToTUnconfirmedDescription(getTConfirmedDescription()
                		.remove(getTConfirmedDescription().indexOf(tcd)));
                return true;
            }

        }
        return false;
    }
    /**
     * This method is called during the backtracking process.  The purpose here is to determine if there is
     * at least one index to show to the user, hence continuing with the dialog. All of the current norm's
     * successors are scanned. As a reminder, at this point all of the current norm's successors must be
     * indices. For every retrieved index, all its index values should be checked against the unconfirmed or
     * doubtful descriptions. If at least ONE index value is not a member of either list, the dialog with the
     * user may continue in the context of the current norm. Else, a new backtrack operation should be
     * performed.
     * NOTE: The possibility of finding successor cases for the current norm is discarded, because successor
     * cases have already been searched for by the method retrieveCasesUnderNorm.
     * Automaton reference: VIdx
	 * @see Define method name.
	 * @param my parameters list
	 * @return self - if at least one index value (in the form of a Descriptor<Object>) is not member of
	 * either the the unconfirmed or doubtful lists; null - if removeSAVDescriptorFromTConfirmedDesc:
	 * returns null; backtrack - if a new bactrack operation should be performed.
	 */
    public boolean verifyIndices(){        
        for (Node normSucc: currentNorm.getSuccessors()){
            if (normSucc instanceof Index) {	
	            //Scan the list of successors for the current index
	            for (Node idxSucc: ((Index)normSucc).getSuccessors()){
	                // If the Descriptor<Object> is not a member of the unconfirmed description, check it
	                // against the doubtful description. If this time the SAVDescriptior is NOT a member of
	                // the doubtful description, return self, indicating that there is at least one
	                // index-value to show to the user
	                if (!this.getTUnconfirmedDescription().contains(((Norm)idxSucc).getDescriptor())) {
	                    if (!this.getTDoubtfulDescription().contains(((Norm)idxSucc).getDescriptor()))
	                        return true;
	                }
	            }
            }
        }
        
        // At this point, the entire list of norm successors was scanned, and all of them were members
        // of either the unconfirmed or doubtful descriptions. Return null to indicate that another backtrack
        // must be performed. Before returning, the descriptor of the current norm must be removed from
        // the confirmed description and placed in the unconfirmed description
        if (!removeFromTConfirmedDescription(currentNorm.getDescriptor()))
            return false;

        return processPreviousNorm();
      }

    /**
     * This method searches for indices, strictly under a norm other than the net root, that point to cases.
     * If a Descriptor in the argument list aProblemDescription matches one such index, it is placed in the
     * solution description. If a Descriptor matches an index that points to a Norm, it will remain in
     * aProblemDescription. If a Descriptor does not match any index, it will also remain in aProblemDescription.
     * PRECONDITION: (aProblemDescription isEmpty not) and (self currentNorm != self netRoot not) and
     * (aPossibleSolutionsList isEmpty)
	 * @see Define method name.
	 * @param my parameters list
	 * @return self : if the process ran OK; cancel : is the user cancels; -1 : error value, if the
	 * precondition is not met.
 	 */
    public SearchStatus searchForCasesUnderCurrNormUsing(List<Descriptor> aProblemDescription,
    	List<PossibleSolution> aPossibleSolutionsList){

        //Check precondition
        if (aProblemDescription.isEmpty() || (currentNorm == netRoot) || !aPossibleSolutionsList.isEmpty())
            return SearchStatus.ERROR;

        //Create the temporary process lists
        List<Descriptor>  tempList = new ArrayList<Descriptor>();
        List<SheetCase> aCaseList = new ArrayList<SheetCase>();
        //Scan the the Descriptor list of the problem description. Look for indices that strictly point to cases

        while (aProblemDescription.isEmpty() != true){
            //Remove the next Descriptor<Object>
            Descriptor d = aProblemDescription.remove(0);
            //Look for a matching index

            Index idx = currentNorm.getSuccessorIndex(d);

            //If the descriptor did not match any index, take it out of the problem description and
            //place it in the temporary list
            if (idx == null){
                //The descriptor may have an inaccurate value. Try to establish a dialog with the user.
            	SearchStatus result = searchCasesDialogUsing(d, aPossibleSolutionsList);
                if (result.equals("cancel"))
                	return SearchStatus.CANCEL;
                if (result.equals("fail") || result.equals("idxNotFound"))
                	tempList.add(d);
                if (result.equals("success"))
                	addToTUnmatchedDescription(d);
            }else{
                //Index found. get the IndexValue successor
                Node succ = idx.getSuccessor(d);

                //If a matched index points to a Norm, don't process it. Place the descriptor in a temporary list
                if (succ instanceof Norm){
                    tempList.add(d);
                }else{
                    //The matched index points to a case. Place the corresponding descriptor in the solution description
                    //and associate the corresponding case to a PossibleSolution. Next, place the possible solution in the
                    //output possible solutions list. Finally, remove the descriptor from the solution description
                    this.addToTSolutionDescription(d);
                    
                    aCaseList.add((SheetCase)succ);
                    List<PossibleSolution> pSolutionList = associateCasesToPossibleSolutions(aCaseList);
                    aPossibleSolutionsList.add(pSolutionList.remove(0));
                    
                    this.getTSolutionDescription().remove(getTSolutionDescription().size()-1);
                }
            }
        }
        
        //Put the descriptors that didn't match indices back in the problem description list
        while (tempList.isEmpty() != true)
            aProblemDescription.add(tempList.remove(0));
        
        return SearchStatus.SUCCESS;

    }

    /**
     * This method searches for indices, strictly under the net root, that point to cases. If a Descriptor
     * in the argument list aProblemDescription matches one such index, it is placed in the solution
     * description. If a Descriptor matches an index that points to a Norm, it will remain in
     * aProblemDescription. If a Descriptor does not match any index, it will be placed in the unmatched
     * descriptor list.
     * PRECONDITION:(aProblemDescription isEmpty not) and (self currentNorm = self netRoot) and (aPossibleSolutionsList isEmpty) and  (tSolutionDescription isEmpty)"
	 * @see Define method name.
	 * @param my parameters list
	 * @return true : if the process ran OK; cancel : is the user cancels; false : error value, if the
	 * precondition is not met.    
	 */
    public SearchStatus searchForCasesUnderRootUsing(List<Descriptor> aProblemDescription,
    		List<PossibleSolution> aPossibleSolutionsList){
        //Check precondition
        
        if (aProblemDescription.isEmpty()){return SearchStatus.ERROR;}

        if ((currentNorm == netRoot) && (aPossibleSolutionsList.isEmpty()) 
        		&& (!this.getTSolutionDescription().isEmpty())) return SearchStatus.ERROR;
        
        //Create the temporary process lists
        List<Descriptor> tempList = new ArrayList<Descriptor>();
        List<SheetCase> aCaseList = new ArrayList<SheetCase>();

        //Scan the the Descriptor list of the problem description. Look for indices that strictly point to cases
        while (aProblemDescription.isEmpty() != true){
            //Remove the next Descriptor<Object>
            Descriptor d = aProblemDescription.get(0);
            //Look for a matching index
            Index idx = currentNorm.getSuccessorIndex(d);

            //If the descriptor did not match any index, take it out of the problem description and
            //place it in the unmatched descriptor list

            if (idx == null){
                //At this point, the descriptor inevitably goes to the unmatched description
                addToTUnmatchedDescription(d);

                //However, the descriptor may have an inaccurate value. Try to establish a dialog with the user
                //using a partial match.

                SearchStatus result = searchCasesDialogUsing(d,aPossibleSolutionsList);
                if (result.equals("cancel"))
                	return SearchStatus.CANCEL;
            }else{
                //Index found. get the IndexValue successor
                Node succ = idx.getSuccessor(d);

                if (succ instanceof Norm)
                    tempList.add(d);
                else {
                    //The matched index points to a case. Place the corresponding descriptor in the solution description
                    //and associate the corresponding case to a PossibleSolution. Next, place the possible solution in the
                    //output possible solutions list. Finally, remove the descriptor from the solution description
                    addToTSolutionDescription(d);
                    
                    aCaseList.add((SheetCase)succ);
                    List<PossibleSolution> pSolutionList = associateCasesToPossibleSolutions(aCaseList);
                    aPossibleSolutionsList.add(pSolutionList.remove(0));
                    getTSolutionDescription().remove(getTSolutionDescription().size()-1);

                }
            }

        }
        //Put the descriptors that match Norms back in the problem description list
        while (tempList.isEmpty() != true)
            aProblemDescription.add(tempList.remove(0));

        return SearchStatus.SUCCESS;
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public SearchStatus searchForCasesUsing(List<Descriptor> aProblemDescription) {
        //If the argument search-list is empty, something wrong happened. Return error value
        if (aProblemDescription.isEmpty()) {
        	prepareFailedOutput();
        	status = SearchStatus.ERROR;
        	return status;
        }
        //Make sure the current level is ALWAYS greater than or equal to the root level
        if (currentLevel < getRootLevel()) {
        	prepareFailedOutput();
        	status = SearchStatus.ERROR;
        	return status;
        }

        List<PossibleSolution> possibleSolutions = new ArrayList<PossibleSolution>();

        //Check if control is located at the root (i.e., no Norm was ever found in the previous state)

        if (currentLevel == getRootLevel()){
            //Search for cases pointed to by indices directly under the root. If there are any, the argument
            //list possibleSolutions will not be empty
        	SearchStatus result = searchForCasesUnderRootUsing(aProblemDescription, possibleSolutions);
            if (result.equals("cancel")) {
            	prepareFailedOutput();
            	status = SearchStatus.CANCEL;
            	return status;
            }
            
            if (result == SearchStatus.ERROR ){
            	prepareFailedOutput();
            	status = SearchStatus.ERROR;
            	return status;
            }

            //If the possible solutions list has at least one item, return self along  with whatever Descriptors
            //remained in the problem description. This guarantees that all left-over Descriptors point to Norms
            if (possibleSolutions.isEmpty() != true){
                prepareSuccessfulOutputWith(possibleSolutions);
            }

            //If the case list is empty, either the problem description has all descriptors that point to Norms
            //in other paths, OR all descriptors are located in the unmatched list. Return nil
            prepareFailedOutput();
            return SearchStatus.FAIL;

        }
        // At this point, control is located on a Norm other than the net root. Initially, scan the Descriptor list in search of indices
        // that strictly point to cases. If a matched index points to a case, remove the corresponding Descriptor from the problem
        // description and place it in the solution description.  Next, associate the retrieved case to a PossibleSolution, and place
        // this possible solution in the case list. Finally, remove the last descriptor of the solution list. If the matched index DOES
        // NOT point to a case, leave the corresponding Descriptor in the problem description

        SearchStatus result = searchForCasesUnderCurrNormUsing(aProblemDescription, possibleSolutions);
        if (result.equals("cancel")) {
        	prepareFailedOutput();
        	status = SearchStatus.CANCEL;
        	return status;
        }
        
        if (result == SearchStatus.ERROR ) {
        	prepareFailedOutput();
        	status = SearchStatus.ERROR;
        	return status;
        }

        // If at the end of the scanning process, the problem description is empty, the possible solutions list MUST have at least
        // one item. Else, something weird happened.  In that situation, return the error value. However, if the case list is not empty,
        // return self
        if (aProblemDescription.isEmpty()){
            if (possibleSolutions.isEmpty()) {
            	prepareFailedOutput();
            	status = SearchStatus.ERROR;
            	return status;
            }
            
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

        List<Descriptor> tempDeleteSolution = new ArrayList<Descriptor>();
        moveDescriptorsFrom(getTSolutionDescription(),getTUnmatchedDescription(),tempDeleteSolution);

        // Same sitution as with the solution description.  In this case, place the confirmed description items in the
        // unconfirmed description, and also copy them to another temporary list

        List<Descriptor> tempDeleteConfirmed = new ArrayList<Descriptor>();
        moveDescriptorsFrom(getTConfirmedDescription(),getTUnconfirmedDescription(),tempDeleteConfirmed);
        
        // Call the search-cases-under-root method with a clean &amp; empty possible solutions list (its part of the precondition)
        List<PossibleSolution> pSolutions = new ArrayList<PossibleSolution>();

        result = searchForCasesUnderRootUsing(aProblemDescription,pSolutions);
        if (result.equals("cancel")) {
        	prepareFailedOutput();
        	status = SearchStatus.CANCEL;
        	return status;
        }
        
        if (result == SearchStatus.ERROR ) {
        	prepareFailedOutput();
        	status = SearchStatus.ERROR;
        	return null;
        }
        
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
                return retrieveCasesUnderCurrentNorm();
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
  * This method is used in conjuntion with prepareSuccessfulOutput.  The purpose of this method is to create
  * an instance of PossibleSolution for every case in the list argument aCaseList.
  * Automaton reference: none.
  * @see Define method name.
  * @param my parameters list
  * @return a list of PossibleSolutions.
  */
    private List<PossibleSolution> associateCasesToPossibleSolutions(List<SheetCase> aCaseList){
        List<PossibleSolution> psList = new ArrayList<PossibleSolution>();
        
        for (SheetCase mycase: aCaseList){
            PossibleSolution ps = new PossibleSolution();
            ps.setSolution(mycase);
            ps.getSolutionDescription().addAllToConcreteDescription(getTSolutionDescription());
            ps.getConfirmedDescription().addAllToConcreteDescription(getTSolutionDescription());
            ps.getUnconfirmedDescription().addAllToConcreteDescription(getTUnconfirmedDescription());
            ps.getDoubtfulDescription().addAllToConcreteDescription(getTDoubtfulDescription());
            psList.add(ps);
        }
        
        return psList;
    }

    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void deleteDescriptorsIn(List<Descriptor> aTempDeleteList,List<Descriptor> aList,
    		List<Descriptor> anotherList){
        for (int i=0;(i<aTempDeleteList.size());i++){
            Descriptor d = aTempDeleteList.get(i);
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
    // Ojo este método no se usa
    @SuppressWarnings("unused")
	private List<Norm> filterUselessNorms(List<Norm> aNormAlternativeList){

        List<Norm> newList = new ArrayList<Norm>();
        while (aNormAlternativeList.isEmpty() != true){
            Norm normAlternative = aNormAlternativeList.remove(0);

            if (normAlternative.successorCases().isEmpty() != true)
                newList.add(normAlternative);
            else {
                //First, get the list of successors for the current norm
                List<Index> successorList = ((Norm)normAlternative).successorIndexes();
                for (int i = 0; (i<successorList.size());i++){
                    //Proceed to extract the next index
                    Index idx = successorList.get(i);

                    //Parse the list of IndexValues associated to the index
                    List<Node> idxSuccessors = idx.getSuccessors();
                    for (int j = 0; (j<idxSuccessors.size());j++) {
                        //Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions
                        if (!getTUnconfirmedDescription().contains(idxSuccessors.get(j).getDescriptor()) &&
                        		!getTDoubtfulDescription().contains(idxSuccessors.get(j).getDescriptor())) {
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
    private void flush(List<Descriptor> aList){
        aList.clear();
    }
    
    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    private boolean isUseless(Node n){
        if ((n instanceof SheetCase)) return false;
        
        if (((Norm)n).successorCases().isEmpty() != true) return false;
        //First, get the list of successors for the current norm
        for (Index idx:((Norm)n).successorIndexes()) {
            //Parse the list of IndexValues associated to the index
            for (Node idxSuccessor:idx.getSuccessors()) {
                //Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions
                if (!getTUnconfirmedDescription().contains(idxSuccessor.getDescriptor())
                		&& !getTDoubtfulDescription().contains(idxSuccessor.getDescriptor()))
                	return false;
            }
        }
        
        return true;
    }
    /**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    private void moveDescriptorsFrom(List<Descriptor> aList,List<Descriptor> anotherList,
    		List<Descriptor> aCopyList){
        while (aList.isEmpty() != true){
            Descriptor d  = aList.remove(0);
            anotherList.add(d);
            aCopyList.add(d);
        }
    }
/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void newSearchWith(List<Descriptor> anOldProblemDescription){
    	//If the automaton returns a non-empty problem description list, then the REASONER MUST call it again with that remaining description,
        // using this method. Before doing so, all lists, except the doubtful and unconfirmed ones, MUST be flushed. Make sure the new search
        // begins at root level, and all necessary control variables are correctly set. This process repeats until the problem description list is EMPTY"
        flush(getTSolutionDescription());
        flush(getTConfirmedDescription());
        flush(getTUnmatchedDescription());
        flush(justification);
        setStatus(SearchStatus.FAIL);
        setCurrentNorm(null);
        resetLevel();
        setStopLevel(currentLevel);
        newOutput();
        
        beginWith(anOldProblemDescription);
    }
    /**
     * This method is called from indexDialog, and it may be called recursively. However, it needs to be
     * executed only once. In order to avoid continuous execution of this method, check the (list) variable
     * 'justification' of searchOutput. Upon termination of the automaton process, 'justification' MUST
     * always be non-nil. Thus, if it's non-nil, then don't execute this method.
     * This method is executed whennthe automaton has failed to provide a solution for the given problem
     * description.
     * Automaton reference: PFO
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    private boolean prepareFailedOutput(){
        if (searchOutput.getJustification() != null) return false;
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(getTUnmatchedDescription());
        
        return true;
    }

    /**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    private SearchStatus prepareSuccessfulOutputWith(List<PossibleSolution> aPossibleSolutionsList){
        if (searchOutput.getPossibleSolutions() != null)
        	return status;
        
        searchOutput.setPossibleSolutions(aPossibleSolutionsList);
        searchOutput.setJustification(justification);
        searchOutput.setUnmatchedDescription(getTUnmatchedDescription());
        setStatus(SearchStatus.SUCCESS);
        
        return status;

    }
/**
 *Category user dialog
 */
    
    /**
     * MANAGEMENT OF AN INCOMPLETE DESCRIPTION. A dialog with the user is established when the automaton
     * has run out of descriptors from the problem description, and has not located any cases.  In addition,
     * the current norm does not have any successor cases. The idea is then to present index alternatives to
     * the user, initially those indices pointing to cases. If no case is retrieved, index alternatives
     * pointing to norms is then presented to the user. If nothing works, the user has the chance to cancel
     * the operation or backtrack to a previous norm.
     * PRECONDITION: The successor list of the current norm does not contain any associated cases.
     * Automaton reference: IdxDial"
	 * @see Define method name.
	 * @param my parameters list
	 * @return null - If the process was unsuccessful in finding a case; self - If one case was found.
	 */
    public SearchStatus indexDialog(){
        List<Node> alternativeCases = new ArrayList<Node>();
        List<Node> alternativeNorms = new ArrayList<Node>();
        
        //First, get the list of successors for the current norm
        List<Index> successorList = currentNorm.successorIndexes();
        
        for (Index idx:successorList){
            //Parse the list of IndexValues associated to the index            
            for (Node idxSuccessor:idx.getSuccessors()){
                //Make sure that the descriptor is NOT already included in neither the unconfirmed and doubtful descriptions
                if (!getTUnconfirmedDescription().contains(idxSuccessor.getDescriptor())
                		&& !getTDoubtfulDescription().contains(idxSuccessor.getDescriptor()))
                    alternativeCases.add(idxSuccessor);                
            }
        }
        //Present the list of alternatives (associated to one index) to the user, preferably the cases
        if (alternativeCases.isEmpty() != true){
            SearchStatus result = presentChoices(alternativeCases);
            
            if (result == SearchStatus.SUCCESS) return result;
            
            if (result == SearchStatus.FAIL) {
            	prepareFailedOutput();
            	return status;
            }
            
            if (result == SearchStatus.CANCEL) {
            	prepareFailedOutput();
            	status = result;
            	return status;
            }
        }


        if (alternativeNorms.isEmpty() != true){
            List<Node> newList = new ArrayList<Node>();
            
            while (alternativeNorms.isEmpty() != true){
            	Node n = alternativeNorms.remove(0);
                if (!isUseless(alternativeNorms.remove(0))) newList.add(n);
            }
            if (newList.isEmpty() != true){
            	SearchStatus result = presentChoices(newList);
            	
            	if (result == SearchStatus.SUCCESS) return result;
                
                if (result == SearchStatus.FAIL) {
                	prepareFailedOutput();
                	return status;
                }
                
                if (result == SearchStatus.CANCEL) {
                	prepareFailedOutput();
                	status = result;
                	return status;
                }
            }

        }
        
        //At this point, all alternatives for this norm failed because they were either unconfirmed or
        // rejected due to doubt. Present to the user the possibility to BACKTRACK

        int answer = JOptionPane.showConfirmDialog(null, "Hasta ahora las alternativas presentadas \n no han " +
        		"ayudado a resolver el problema. \n ¿Desea continuar evaluando otras alternativas?", "OracleID",
        		JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.NO_OPTION){
            prepareFailedOutput();
            return SearchStatus.CANCEL;
        }
        
        return backtrack();
    }


    /**
     * The elements of the argument alternativeList are lists of the form: (Descriptor<Object> IndexValue)
     * Automaton reference: This method is an extension of indexDialog, referenced by IdxDial
	 * @see Define method name.
	 * @param my parameters list
	 * @return self - if successful (i.e., at least one possible solution was found); nil - if
	 * retrieveCasesUnderNorm fails; cancel - if the user cancels the dialog; fail - if all alternatives
	 * were rejected (either because they did not match or the user was in doubt)
	 */
	public SearchStatus presentChoices(List<Node> alternativeList){
    	String value = null, message;
    	int answer = -1;
    	
    	for (Node n:alternativeList) {
    		if (!this.isUseless(n)) {
    			if (n.getDescriptor().getValue() instanceof String)
    				value = (String)n.getDescriptor().getValue();
    			else if (n.getDescriptor().getValue() instanceof SingleValue)
    				value = "" + ((SingleValue)n.getDescriptor().getValue()).getValue();
    			
        		// Prepare the inquiry to be presented to the user
        		message = "¿Presenta la estructura" + n.getDescriptor().getStructure() + 
        					" la característica " + n.getDescriptor().getAttribute() +
        					" con el siguiente valor: " + value + "?";
        		
        		answer = JOptionPane.showConfirmDialog(null, message, "OracleID", JOptionPane.YES_NO_CANCEL_OPTION);
        		
        		if (answer == JOptionPane.YES_OPTION) {
        			// The solution is a norm
        			if (n instanceof Norm) {
        				processNextNormWith((Norm)n);
        				return retrieveCasesUnderCurrentNorm();
        			} else if (n instanceof SheetCase) {
        				// The solution is a case
        				addToTConfirmedDescription(n.getDescriptor());
        				List<SheetCase> caseList = new ArrayList<SheetCase>();
        				caseList.add((SheetCase)n);
        				return prepareSuccessfulOutputWith(associateCasesToPossibleSolutions(caseList));
        			}
        		}
        		//Pendiente de revisar
        		if (answer == JOptionPane.NO_OPTION)
        			addToTUnconfirmedDescription(n.getDescriptor());
        		if (answer == JOptionPane.CANCEL_OPTION)
        			addToTDoubtfulDescription(n.getDescriptor());
        		if (answer == -1)
        			return SearchStatus.CANCEL;
    		}
        	
    	}

    	return SearchStatus.FAIL;
    }
    /**
     * Automaton reference: PNN
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void processNextNormWith(Norm aNorm){
        addToTConfirmedDescription(aNorm.getDescriptor());
        setCurrentNorm(aNorm);
        setNextLevel();
    }
    
    /**
     * MANAGEMENT OF A POSSIBLY INNACURATE DESCRIPTOR.
     * This method establishes a dialog with the user. First, an index is searched, under the current 
     * norm, that matches the descriptor argument's attribute only. If there is a match, a new
     * Descriptor<Object> is created for every index value found for the retrieved index. The new
     * information is presented to the user, in order for him/her to determine if it applies.
	 * @see Define method name.
	 * @param my parameters list
	 * @return idxNotFound - if an index was not found using the descriptor argument's attribute. 
	 * cancel - if the user cancels; success - if a case was accepted; fail - if index values point to norms,
	 * or the user rejected possibilities, or a combination of both situtions occurred.
	 */
	public SearchStatus searchCasesDialogUsing(Descriptor aSAVDescriptor, List<PossibleSolution> aPossibleSolutionsList){
        //Partial match: Look for an index under the current norm, whose label matches the
        //descriptor's attribute. Disregard the descriptor's value
        Index idx = currentNorm.getSuccessorIndex(aSAVDescriptor.getAttribute());
        if (idx == null)
        	return SearchStatus.IDXNOTFOUND;

        //The index was found. Create a temporary possible solutions list
        List<SheetCase> aCaseList = new ArrayList<SheetCase>();
        List<Descriptor> descriptorList = new ArrayList<Descriptor>();

        for (Node n:idx.getSuccessors()){
            //Determine if the successor is a norm
            if ((n instanceof Norm) != true){
                //Since this descriptor corresponds to a partial match, make sure that it is NOT already
                //included in neither the unconfirmed, doubtful, unmatched, solution, or confirmed descriptions
                if (!this.getTUnconfirmedDescription().contains(n.getDescriptor()) &&
                		!this.getTDoubtfulDescription().contains(n.getDescriptor()) &&
                		!this.getTUnmatchedDescription().contains(n.getDescriptor()) &&
                		!this.getTSolutionDescription().contains(n.getDescriptor()) &&
                		!this.getTConfirmedDescription().contains(n.getDescriptor())) {
                    //FUTURE IMPROVEMENT (documented by HB on 10-Sep-1999):
                    //1. Retrieve the taxon corresponding to the successor case.
                    //2. Retrieve the structure-atribute, from the taxon's description, that matches (d structure) and (d attribbute).
                    //3. Retrieve the weighted-value list from the corresponding taxon's structure-attribute.
                    //4. Determine, using the weighted-value list, SimAssessor, and SimRanges, the degree of similarity
                    //between d and aSAVDescriptor.
                    //5. If the degree of similarity satisfies the user-defined expectation E7, then present d to the user.
                    //Else, don't present it to the user and continue with the rest of this process.
                    //6. Document the whole process in the search process justification.
                    aCaseList.add((SheetCase)n);
                }
            }
            
        }
        
        if (aCaseList.size() == 0)
        	return SearchStatus.FAIL;
        
        String oldValue = null;
        if (aSAVDescriptor.getValue() instanceof String)
			oldValue = (String) aSAVDescriptor.getValue();
		else if (aSAVDescriptor.getValue() instanceof SingleValue)
			oldValue = "" + ((SingleValue)aSAVDescriptor.getValue()).getValue();
        
        String message =	aSAVDescriptor.getStructure()+":" +  aSAVDescriptor.getAttribute()+  "." +
        			"\nNo reconozco el valor " + oldValue + "\nbrindado en la descripción del espécimen." +
        			"\nSin embargo, sí puedo reconocer los siguientes valores.\n ¿Es alguno valor válido?\n";
        
    	for (SheetCase sc:aCaseList) {
    		if (sc.getDescriptor().getValue() instanceof String)
    			message = message + sc.getDescriptor().getValue() + "\n";
    		else if (sc.getDescriptor().getValue() instanceof SingleValue)
    			message = message + ((SingleValue)sc.getDescriptor().getValue()).getValue() + "\n";
    	}
    	
    	String result = JOptionPane.showInputDialog(message);
    	
    	//El usuario rechaza la sugerencia
        if (result.equals("")){
            while (descriptorList.isEmpty() != true){
                addToTUnconfirmedDescription(descriptorList.remove(0));
            }
            
            return SearchStatus.FAIL;
        }

        //User is in doubt. Flush the descriptor list by placing all SAVDescriptors in the doubtful
        //description. Continue processing the next attribute
        if (result.equals("doubt")){
        	while (descriptorList.isEmpty() != true){
                addToTDoubtfulDescription(descriptorList.remove(0));
            }
            
            return SearchStatus.FAIL;
        }
        
        //User cancels. Cancel the process and exit.
        if (result == null)
        	return (status = SearchStatus.CANCEL);

        SheetCase sc = this.getSheetCase((Object)result, aCaseList);
        
        //At this point, the answer must be successful.
        //Associate the confirmed case to PossibleSolution. Then exit successfully
        addToTConfirmedDescription(sc.getDescriptor());
        
        aCaseList.add(sc);
        List<PossibleSolution> pSolutionList = associateCasesToPossibleSolutions(aCaseList);
        aPossibleSolutionsList.add(pSolutionList.remove(0));
        
        this.getTSolutionDescription().remove(getTSolutionDescription().size()-1);
        
        return SearchStatus.SUCCESS;
    }
    
/**
 *Category norm-pointing search
 */
    
    /**
     * Initial state of the search automaton.  Control is placed on the root norm. The final step is a call
     * to the next state (i.e., searchForNormWith).
     * NOTE: The argument aProblemDescription is a non-empty list of Descriptors.
     * Precondition:
     * 1. aProblemDescription is a non-empty set of SAVDescriptors.
     * 2. For all s1, s2::Descriptor in aProblemDescription : (s1 structure) = (s2 structure).
     * Automaton reference: bW
	 * @see Define method name.
	 * @param my parameters list
	 * @return null - if the argument is an empty list, or object returned by searchForNormWith:
	 */
    public SearchStatus beginWith(List<Descriptor> aProblemDescription){
    	String sName;
    	
        //Check part 1. of the precondition
        if (aProblemDescription.isEmpty()) return SearchStatus.ERROR;
    
        sName = aProblemDescription.get(0).getStructure();
        
        for (Descriptor d: aProblemDescription)
            if ((sName.equals(d.getStructure())) != true) return SearchStatus.ERROR;

        //Initialization steps. At this point, nextLevel = 1. Thus, the root level is 1
        setCurrentNorm(netRoot);
        setNextLevel();

        //Move to the next state, and return its returning value (nil or self)
        return searchForNormWith(aProblemDescription);
    }
    
    /**
     * This method attempts to retrieve norm-dependant cases, from the stop-level norm. At this point, the
     * problem description is empty of descriptors.  If at least one case was retrieved, prepare the
     * successful output and stop the automaton. Else, start a dialog with the user.
     * Automaton reference: RCUN
	 * @see Define method name.
	 * @param my parameters list
	 * @return object returned by either prepareSuccessfulOutputWith: (self), or indexDialog.
	 */
    public SearchStatus retrieveCasesUnderCurrentNorm(){
        List<SheetCase> caseList = currentNorm.successorCases();
        if (caseList.isEmpty() != true){
            return prepareSuccessfulOutputWith(this.associateCasesToPossibleSolutions(caseList));
        }
        
        return indexDialog();
    }
    
    /**
     * The argument aProblemDescription is scanned in search for descriptors that correspond to indices
     * pointing to norms. Every time one such descriptor is located, currNorm is reassigned and the
     * descriptor placed in the solution description.  If after the process the argument list is empty, a
     * call to state retrieveCasesUnderNorm is performed.  If the list is completely scanned and no
     * descriptor was located, a call to state searchForCasesWith is executed.
     * NOTE: The argument aProblemDescription is a list of Descriptors
     * Automaton reference: SNW
	 * @see Define method name.
	 * @param my parameters list
	 * @return self, or object returned by retrieveCasesUnderCurrNorm, or object returned by
	 * searchForCasesUsing:
	 */
    public SearchStatus searchForNormWith(List<Descriptor> aProblemDescription){
        //If the problem description is empty, all its descriptors matched norms. Make the current norm
        //the stop-level norm, and start the search for cases under it
        if (aProblemDescription.isEmpty()){
            setStopLevel(currentLevel);
            return retrieveCasesUnderCurrentNorm();
        }
        
        //Scan the problem description
        int i = 0;
        Descriptor d = null;
        Norm nextNorm = null;
        while (i<=aProblemDescription.size()){
            //Search for a norm whose descriptor matches the scanned descriptor. If found,
            //remove the descriptor from the problem case and stop the loop
            nextNorm = currentNorm.getNearestSuccessorNorm(aProblemDescription.get(i));

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
    public void updateNormSearchWith(Descriptor aSAVDescriptor, Norm aNewNorm){
        addToTSolutionDescription(aSAVDescriptor);
        currentNorm = aNewNorm;
        setNextLevel();
    }
    
	SheetCase getSheetCase(Object value, List<SheetCase> aCaseList) {
    	for (SheetCase sc:aCaseList) {
    		if (sc.getDescriptor().getValue() instanceof String) {
    			if (value.equals(sc.getDescriptor().getValue()))
    				return sc;
    		} else if (sc.getDescriptor().getValue() instanceof SingleValue) {
    				if (((SingleValue)sc.getDescriptor().getValue())
    						.equals((value))) //Ojo revisar
        				return sc;
    		}
    	}
    	
    	return null;
    }
}
