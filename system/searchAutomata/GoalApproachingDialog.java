/*
 * @see "Categor�a Sukia Search Automata en SUKIA SmallTalk"
 */

package system.searchAutomata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import javax.swing.JOptionPane;

import ontology.CBR.Case;
import ontology.CBR.SimilarityDegree;
import ontology.common.Attribute;
import ontology.common.Descriptor;
import ontology.common.GroupingHeuristic;
import ontology.common.Structure;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
import ontology.values.RangeDescriptor;
import ontology.values.SingleDescriptor;
import ontology.values.ValueDescriptor;

import system.Hypothesis;
import system.PossibleSolution;
import system.similarityAssessment.SimAssessor;

/**
 * Instances of this class are invoked when the search automatas have performed successfully (i.e., with at least one possible solution),
 * but the taxonomic level of all the possible solutions is more general than the stated identification goal.
 * The purpose of this class is then, for each qualifying possible solution, establish a dialog with the user
 * asking him/her questions about successor taxa of each solution, *all related to the descriptive element*.
 * As questions are positively answered, the corresponding taxon moves "one level down", approaching the stated goal.
 * This dialog stopswhen the user cancels, when all possible solutions have been processed but no positive "down-movement" was done,
 * or when at least ONE taxon reaches the stated goal.
 * @author pabloq
 */

public class GoalApproachingDialog {

    private Hypothesis hypothesis;
    private TaxonomicRank goal;
    private Taxonomy taxonomy;
    private List<PossibleSolution> processList;
    private String status;
    private List<PossibleSolution> OKList;
    private SimilarityDegree minSimilarityDegree;

	 /**
	 * Category instance creation
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public GoalApproachingDialog(){
    	
    }
    
    public GoalApproachingDialog(TaxonomicRank aGoal,Hypothesis aHypothesis,Taxonomy aTaxonomy, SimilarityDegree minSimilarityDegree){
        if ((aHypothesis.getDescriptiveElement() instanceof Structure) == true){
            initializeGoal(aGoal, aHypothesis, aTaxonomy, minSimilarityDegree);
        }
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public TaxonomicRank getGoal(){
        return goal;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Hypothesis getHypothesis(){
        return hypothesis;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<PossibleSolution> getOKList(){
        return OKList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public List<PossibleSolution> getProcessList(){
        return processList;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public SimilarityDegree getSimilarityRanges(){
        return minSimilarityDegree;
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
    public Taxonomy getTaxonomy(){
        return taxonomy;
    }

	/**
	 * The purpose of this method is to initially do all the administrative work necessary to select
	 * those possible solutions (from the associated hypothesis) that are elegible for processing.
	 * That is, ONLY positive cases or Taxon instances will be processed. Once a possible solution
	 * is selected, it is placed in the processList.  Next, out of those previously filtered in, a second
	 * selection comes about: select only those (positive cases or taxa)  possible solutions whose
	 * taxonomic level is *closest to the stated identification goal*. These elements will remain in the
	 * processList; the rest will be filtered out.
	 * Precondition: the associated hypothesis MUST have at least one possible solution.
	 * Returns: nil - if the precondition fails, or a processing error occurred, or processList is empty.
	 * value returned by doDialog.
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public Object chat(){
        //Check precondition
        if (hypothesis.getPossibleSolutions().isEmpty()){return null;}

        //Transform the stated identification goal to a numeric value
        int goalAsIndex = TaxonomicRank.getIndex(goal);

	//Scan the associated hypothesis possible solutions list

        for (int i = 0;(i<hypothesis.getPossibleSolutions().size());i++){
            //get the next possible solution
            PossibleSolution ps = hypothesis.getPossibleSolutions().get(i);

            //Transform the possible solution level to a numeric value
            int psLevelAsIndex = TaxonomicRank.getIndex(ps.getLevel());

            //If the possible solution's level is equal to, or more specific than the goal, ignore it

            if ((psLevelAsIndex >= goalAsIndex) != true){
                //If the possible solution is a taxon, place it in the process list. Else, it must be a case. Before placing the possible solution in the process list, find its corresponding taxon in the associated taxonomy
                if (ps.getSolution() instanceof Taxon){
                    processList.add(ps);

                }else{
                    //If the taxon's status is positive, continue processing it. Else, ignore it
                    if (((Case)ps.getSolution()).getState()){
                        //Solution is a positive case. Retrieve the corresponding taxon from the taxonomy
                        Taxon taxon = taxonomy.getTaxonFromLevelIndex(ps.getName(), TaxonomicRank.values()[psLevelAsIndex]);

                        //If the taxon is not found, there is an error. set ba tate of error and return
                        if (taxon == null){status = "error"; return  null;}

                        //Exchange the case for the taxon. Finally, place it in the process list
                        ps.setSolution(taxon);
                        addProcessList(ps);
                    }

                }
            }

        }
        //If the process list ends up having zip, return fail status (default)

        if (processList.isEmpty()){return null;}

        //At this point, processList contains a set of possible solutions whose level is more general than the stated identificaction goal. Moreover, all solutions are taxa. Proceed to select those items whose level is *closest* to the goal

        selectPossibleSolutionsNearestToGoal();

        return doDialog();

    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    @SuppressWarnings("unchecked")
	public boolean doDialog(){
    	ReturnValue result;
    	List<Object> returnValues;
    	List<String> displayValues;
    	String v;

    	//Scan the processList
        while(processList.isEmpty() != true){
            //Remove the next possible solution
            PossibleSolution ps = processList.remove(0);
            //get the next possible solution's taxon
            Taxon taxon = (Taxon)ps.getSolution();

            //Scan the taxon's successor list
            for (int i=0;(i<taxon.getSuccessors().size());i++){
                //get the next-level successor taxon
                Taxon nextLevelTaxon = taxon.getSuccessors().get(i);

                //get the related structure from the successor taxon's SAV description
                Structure structure = nextLevelTaxon.getSAVDescription().getStructure(((Taxon)hypothesis.getDescriptiveElement()).getName());
                if (structure != null){
                    //get the attributes from the retrieved structure
                    List<Attribute> attributeList = structure.getAttributes();

                    for (Attribute attribute:attributeList){    						
                        //Make sure this attribute is not already processed (i.e., included in the solution or confirmed descriptions of ANY item in the processList)
                        if (isAttributeAlreadyProcessed(attribute) != true){
                            //get the attribute's list of values
                            List<ValueDescriptor> valueList  = attribute.getValues().getValueDescriptors(TaxonomicRank.values()[Attribute.oneLevel()]);
                            if (valueList == null){
                            	status= "error";
                            	return false;
                            }
                            List<Descriptor<Object>> OKSAVDescriptorList = new ArrayList<Descriptor<Object>>();
                            displayValues = new ArrayList<String>();
                            returnValues = new ArrayList<Object>();
                            //Scan the value descriptor list: valueList with range value descriptors: only ONE element.
                            //valueList with non-range value descriptors: at least one element
                            for (ValueDescriptor vd: valueList){
                                //If the value decriptor is a range, do a range-driven dialog
                                if ((vd instanceof RangeDescriptor)){
                                    while (OKSAVDescriptorList.isEmpty() != true){
                                        OKSAVDescriptorList.remove(0);
                                    }

                                    result = rangeValueDescriptorDialogWith((RangeDescriptor)vd, attribute);

                                    if (result.getResponse().equals("cancel") || result.getResponse().equals("error")){
                                        status = (String)result.getResponse();
                                        return false;
                                    }
                                    
                                    if (result.getResponse().equals("reject")){
                                        ps.addUnconfirmedDescription(result.getDescriptor());
                                    }
                                    if (result.getResponse().equals("unmatch")){
                                        hypothesis.addUnmatchedDescription((Descriptor<Object>)result.getDescriptor());
                                    }
                                    //The user typed a value within the range. Thus result contains a SAVDescriptor
                                    //that can be used to update ps confirmedDescription. Additionally, assign
                                    //nextLevelTaxon to ps solution, and place ps in the OKList. Finally, do a recursive
                                    //call to doDialog, in order to process the next possible solution in processList
                                    if (result.getResponse().equals("confirm")){
                                        ps.setSolution(nextLevelTaxon);
                                        ps.addConfirmedDescription(result.getDescriptor());
                                        addOKList(ps);
                                        return doDialog();
                                    }
                                } else {
                                    //Create a SAV descriptor for each [structure-attribute]-value
                                    Descriptor<Object> savDescriptor = new Descriptor<Object>();
                                    savDescriptor.set(((Structure)hypothesis.getDescriptiveElement()).getName(), attribute.getName(), ((SingleDescriptor<Object>)vd).getValue());
                                    //Make sure the SAV descriptor is not already processed (i.e., included in the unconfirmed,
                                    //doubtful, or unmatched descriptions of ANY item in the processList. The reason for this
                                    //check is to avoid asking the user questions previously answered
                                    if (isDescriptorAlreadyProcessed(savDescriptor) != true){
                                        //For the new descriptor's value, make sure its degree of similarity (with respect to
                                        //the attribute's typical value) is acceptable. If it isn't, ignore it"
                                        if (determineSimilarityFor(savDescriptor, nextLevelTaxon) != null){
                                            //Place the SAV descriptor value in the Array that will be displayed
                                            if (savDescriptor.getValue() instanceof Integer){
                                                v = ((Integer)savDescriptor.getValue()).toString();
                                            }else{
                                                v = ((String)savDescriptor.getValue());
                                            }
                                            
                                            displayValues.add(v);
                                            returnValues.add(savDescriptor.getValue());

                                            //Place the descriptor is a separate descriptor list. Process the next value
                                            OKSAVDescriptorList.add(savDescriptor);
                                        }
                                    }
                                }
                            }
                            //Once all of the attribute's values have been processed, the number of items in the
                            //display array MUST be equal to the number of descriptors in the descriptor list. If
                            //such number is greater than zero, display the dialog
                            if (OKSAVDescriptorList.isEmpty() != true){
                                result = valueDescriptorDialogWith(displayValues, returnValues, attribute);

                                //User rejects. Flush the descriptor list by placing all SAVDescriptors in the unconfirmed
                                //description. Continue processing the next attribute

                                if (result.getResponse().equals("reject")){
                                    while (OKSAVDescriptorList.isEmpty() != true){
                                        ps.addUnconfirmedDescription(OKSAVDescriptorList.remove(0));
                                    }
                                }

                                //User is in doubt. Flush the descriptor list by placing all SAVDescriptors in the doubtful
                                //description. Continue processing the next attribute
                                if (result.getResponse().equals("doubt")){
                                    while (OKSAVDescriptorList.isEmpty() != true){
                                        ps.addDoubtfulDescription(OKSAVDescriptorList.remove(0));
                                    }
                                }
                                
                                //User cancels. Cancel the process and exit.
                                if (result.getResponse().equals("cancel")){
                                    status = "cancel";
                                    return false;
                                }
                                //User selects one item. The dialog method returns the value included in a SAVDescriptor.
                                //Assign nextLevelTaxon to ps solution. Place ps in the OKList. Call doDialog recursively
                                if (result.getDescriptor() != null){
                                    ps.setSolution(nextLevelTaxon);
                                    ps.addConfirmedDescription(result.getDescriptor());
                                    OKList.add(ps);
                                    return doDialog();
                                }
                            }
                        }
                    }
                }
            }
        }
        //At this point, all of the possible solutions have been processed. If the OKList is NOT empty, all the elements now
        //included in it have advanced one level with respect to the original ps solution. If the If the FIRST possible solution's
        //level meets the goal, exit successfully. Else, copy everything back to the processList and call doDialog again. The
        //return value is whatever this new call returns
        if (OKList.isEmpty() != true){
            if (goal.equals(OKList.get(0).getLevel())){
                status = "success";
                return true;
            }
            while (OKList.isEmpty() != true){
                addProcessList(OKList.remove(0));
            }
            return doDialog();
        }
        return false;

}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public Object selectPossibleSolutionsNearestToGoal(){
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

	"get the level of the first element"
	level := (self processList first) level.

	"Starting with the second element, process the list"
	i := 2.
	[ i <= (self processList size) ]
	whileTrue: [

		(level = ((self processList) at: i) level)
		ifTrue: [ i := i + 1 ]
		ifFalse: [ (self processList) removeAtIndex: i ].

	].    "END [ i &lt;= (self processList size) ] whileTrue:"

	^self.*/
        //Check precondition
        if (processList.isEmpty()){return null;}

        //If the processList contains only one element, there's nothing to do
        if (processList.size() == 0){return true;}

        //get the level of the first element
        TaxonomicRank level = processList.get(0).getLevel();

        //Starting with the second element, process the list
        int i = 1;
        while (i<processList.size()){
            if (level.equals(processList.get(i).getLevel())){
                i += 1;
            }else{
                processList.remove(i);
            }
        }
        return true;
    }


	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public ReturnValue rangeValueDescriptorDialogWith(RangeDescriptor vd,Attribute anAttribute){
    	ReturnValue returnValues;
    	String msg, name, result;
    	double suggestedValue, value;
    	Descriptor<Object> d;
    	
    	returnValues = new ReturnValue();
    	if (this.getHypothesis().getDescriptiveElement() instanceof Structure)
    		name = ((Structure)this.getHypothesis().getDescriptiveElement()).getName();
    	else name = ((GroupingHeuristic)this.getHypothesis().getDescriptiveElement()).getName();

		msg =	"Estructura: " + name +
				"\nPor favor digite un valor en " + vd.getMeasuringUnit() +
				"\npara el atributo: " + anAttribute.getName() +
				"\n\nSi no es posible proveer la respuesta,\ndeje el campo en blanco y haga click en OK";
	
		suggestedValue = (double)(vd.getLowerBound()  + vd.getUpperBound()) / 2;
	
		result = JOptionPane.showInputDialog(msg, Double.toString(suggestedValue));
	
		if (result == null) {
			d = new Descriptor<Object>();
			d.set(name, anAttribute.getName(), suggestedValue);
			returnValues.setResponse("reject");
			returnValues.setDescriptor(d);
			return returnValues;
		}
		
		value = Double.parseDouble(result);
		d = new Descriptor<Object>();
		d.set(name, anAttribute.getName(), value);
			
		if (value >= vd.getLowerBound() && value <= vd.getUpperBound())
			returnValues.setResponse("confirm");
		else returnValues.setResponse("unmatch");
	
		returnValues.setDescriptor(d);
		
		return returnValues;
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public ReturnValue valueDescriptorDialogWith(List<String> displayValues, List<Object> returnValues,Attribute anAttribute){
    	ReturnValue returnValue;
    	String msg, name, result;
    	Descriptor<Object> d;
    	
    	returnValue = new ReturnValue();
    	if (this.getHypothesis().getDescriptiveElement() instanceof Structure)
    		name = ((Structure)this.getHypothesis().getDescriptiveElement()).getName();
    	else name = ((GroupingHeuristic)this.getHypothesis().getDescriptiveElement()).getName();

		msg =	"Presenta el atributo " + anAttribute.getName() +
				"\nde la estructura: " + name +
				"\nuno de los siguientes valores? ";

		result = JOptionPane.showInputDialog(msg); //Ojo revisar el cuadro de dialogo
		
		if (result.equals("reject") || result.equals("doubt") || result.equals("cancel")) {
			returnValue.setResponse(result);
			returnValue.setDescriptor(null);
			return returnValue;
		}
		
		d = new Descriptor<Object>();
		d.set(name, anAttribute.getName(), result);
		returnValue.setResponse("");
		returnValue.setDescriptor(d);
		return returnValue;
    }

 /**
 *Category initializing
 */

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public void initializeGoal(TaxonomicRank aGoal,Hypothesis aHypothesis,Taxonomy aTaxonomy, SimilarityDegree simRangesList){        
    	//The argument aGoal MUST be a value valid for TaxonomicLevels (e.g., #genus)
        goal = aGoal;

        //The elements of simrangesList MUST be defined in SimRanges
        minSimilarityDegree = simRangesList;

        hypothesis = aHypothesis;
        taxonomy = aTaxonomy;

        //The instance variable status indicates the search status at the end of the process.
        //The possible values it may have are:
        //#fail - the dialog was unsuccessful. This is the default value.
        //#success - at least one taxon reached the goal.
        //#cancel - the user canceled the dialog process.
        //#error - a processing error occurred."
        //self status: #fail.
        setStatus("fail");
        
        //This list must contain zero or more instances of PossibleSolution
    	processList = new ArrayList<PossibleSolution>();
        
    	//This list must contain zero or more instances of PossibleSolution
        OKList = new ArrayList<PossibleSolution>();
    }
    
	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addOKList(PossibleSolution aPossibleSolution){
        OKList.add(aPossibleSolution);
        Collections.sort(this.getOKList());
    }

	/**
	 * @see Define method name.
	 * @param my parameters list
	 * @return my return values
	 */
    public void addProcessList(PossibleSolution aPossibleSolution){
        processList.add(aPossibleSolution);
        Collections.sort(this.getProcessList());
    }

	/**
	 * The instance variable status indicates the search status at the end of the process.
	 * The possible values it may have are:
	 * #fail - the dialog was unsuccessful. This is the default value.
	 * #success - at least one taxon reached the goal.
	 * #cancel - the user canceled the dialog process.
	 * #error - a processing error occurred.
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
    public Object determineSimilarityFor(Descriptor<Object> aSAVDescriptor,Taxon aTaxon){
        
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

        List<ValueDescriptor> weightedValues = (aTaxon.getSAVDescription().getStructure(aSAVDescriptor.getStructure()))
        	.getAttribute(aSAVDescriptor.getAttribute()).getValues().get(Attribute.oneLevel());
        
        SimilarityDegree similarity = SimAssessor.similarityRangeOf(aSAVDescriptor.getValue(), weightedValues);
        
        if (EnumSet.range(minSimilarityDegree, SimilarityDegree.IGUAL).contains(similarity) != true)
        	return null;
        
        return aTaxon;
        
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean isAttributeAlreadyProcessed(Attribute anAttribute){
        
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

        Descriptor<Object> d = new Descriptor<Object>();
        d.set(((Structure)hypothesis.getDescriptiveElement()).getName(), anAttribute.getName(), null);

        for (PossibleSolution ps: hypothesis.getPossibleSolutions()){

            if (ps.contains(d, ps.getSolutionDescription())){return true;}
            if (ps.contains(d, ps.getConfirmedDescription())){return true;}

        }

        return false;
}

/**
 * @see Define method name.
 * @param my parameters list
 * @return my return values
 */
    public boolean isDescriptorAlreadyProcessed(Descriptor<Object> aSAVDescriptor){
        
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
        if (hypothesis.containsFull(aSAVDescriptor, hypothesis.getUnmatchedDescription())){return true;}

        for (PossibleSolution ps:hypothesis.getPossibleSolutions()){
            if (ps.containsFull(aSAVDescriptor, ps.getUnconfirmedDescription())){return true;}
            if (ps.containsFull(aSAVDescriptor, ps.getDoubtfulDescription())){return true;}

        }
        return false;
}


}
