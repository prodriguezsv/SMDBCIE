/**
 * 
 */
package ontology.values;

import java.util.ArrayList;
import java.util.List;

import ontology.taxonomy.TaxonomicRank;


/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Value extends ArrayList<List<ValueDescriptor>> {
	
	/**
	 * @see "Método init del protocolo initializing en SUKIA SmallTalk"
	 */
	public Value() {
		List<ValueDescriptor> level;

		for (int i = 1; i <= TaxonomicRank.getNomenclaturalRanksNumber(); i++) {
			level = new ArrayList<ValueDescriptor>();
			this.add(level);
		}
	}
	
	/**
	 * @see "Método newWithOneLevel del protocolo initializing en SUKIA SmallTalk"
	 */
	public Value(boolean oneLevel) {
		List<ValueDescriptor> level;

		if (oneLevel) {
			level = new ArrayList<ValueDescriptor>();
			this.add(level);
		}
	}
	
	/**
	 * @see "Método getMatchingValueDescriptorFor:in: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 */
	@SuppressWarnings("unchecked")
	public <T extends ValueDescriptor> ValueDescriptor getValueDescriptors(T aDescriptor, TaxonomicRank aLevel) {
		List<ValueDescriptor> vdList;
		
		if (aDescriptor instanceof SingleDescriptor)			
			vdList = this.getSingleDescriptors(((SingleDescriptor)aDescriptor).getValue(),
					((SingleDescriptor)aDescriptor).getWeight(), aLevel, "=");
		else
			vdList = this.getRangeDescriptorsWithRange(((RangeDescriptor)aDescriptor).getLowerBound(), 
					((RangeDescriptor)aDescriptor).getUpperBound(), aLevel);

		if (vdList == null) return null;
		
		if (vdList.isEmpty() || vdList.size() > 1) return null;
		
		return vdList.get(0);
	}
	
	/**
	 * Return the entire list of ValueDescriptors, distributed in level-lists
	 * @see "Método valueDescriptors del protocolo accessing-general en SUKIA SmallTalk"
	 */
	public Value getValueDescriptors() {
		return this;
	}
	
	/**
	 * Returns nil or all ValueDescriptors in a level-list
	 * @see "Método valueDescriptorsIn: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aLevel
	 * @return
	 */
	public List<ValueDescriptor> getValueDescriptors(TaxonomicRank aLevel) {
		int levelNumber;
		
		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;

		// Return the level-list indexed in self (i.e, Value) by levelNumber
		return (this.get(levelNumber-1));
	}

	/**
	 * Returns nil or a (possibly empty) list of ValueDescriptors, such that
	 * aNumber lies within the range determined by their lower and
	 * upper bounds.
	 * IMPORTANT: Returning nil means that this method is in a state of error.
	 * @see "Método numberInRange: del protocolo accessing-range values en SUKIA SmallTalk"
	 * @param aNumber
	 * @param aLevel
	 */
	public List<ValueDescriptor> getRangeDescriptorsWithNumber(double aNumber, TaxonomicRank aLevel) {
		int levelNumber;
		List<ValueDescriptor> vdList;
		ValueDescriptor vd;
		RangeDescriptor rvd;
		double n;
		
		//Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;
	
		n = aNumber;
		vdList = new ArrayList<ValueDescriptor>();
		for (int i = 1; i <=  this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (vd instanceof RangeDescriptor) {
				rvd = (RangeDescriptor)vd;
				if (n >= rvd.getLowerBound() && n <= rvd.getUpperBound())
					vdList.add(vd);
			}
		}
		
		return vdList;
	}
	
	/**
	 * Returns nil or a (possibly empty) list of one ValueDescriptor, such that
	 * its lower bound and upper bound match the arguments aLowerBound
	 * and anUpperBound. NOTE: Each level-list should contain unique-valued
	 * ranges. That's the reason for stopping loop execution when a descriptor is
	 * found. Method valueDescriptor:for: MUST ensure the addition of descriptors
	 * with UNIQUE VALUES.
	 * IMPORTANT: Returning nil means that this method is in a state of error.
	 * @see "Método rangeMatching:and:inLevel: del protocolo accessing-range values en SUKIA SmallTalk"
	 * @param aLowerBound
	 * @param anUpperBound
	 * @param aLevel
	 * @return
	 */
	public List<ValueDescriptor> getRangeDescriptorsWithRange(double aLowerBound, double anUpperBound, TaxonomicRank aLevel) {
		int levelNumber;
		List<ValueDescriptor> vdList;
		ValueDescriptor vd;
		RangeDescriptor rvd;
		double lb, ub;
		
		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;
		
		lb = aLowerBound;
		ub = anUpperBound;
		
		vdList = new ArrayList<ValueDescriptor>();

		for (int i = 1; i <= this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (vd instanceof RangeDescriptor) {
				rvd = (RangeDescriptor) vd;
				if (lb == rvd.getLowerBound() && ub == rvd.getUpperBound()) {
					vdList.add(vd);
					return vdList;
				}
				
			}
		}

		return vdList;
	}
	
	/**
	 * Returns nil or a (possibly empty) list with all ValueDescriptors from a level-list, such that their value matches aValue.
	 * IMPORTANT: Returning nil means that this method is in an error condition
	 * @see "Método value:in: del protocolo accessing-weight values en SUKIA SmallTalk"
	 * @param aValue
	 * @param aLevel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<ValueDescriptor> getSingleDescriptors(T aValue, TaxonomicRank aLevel) {
		int levelNumber;
		ValueDescriptor vd;
		SingleDescriptor<T> svd;
		List<ValueDescriptor> vdList;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;

		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<ValueDescriptor>();

		// Parse all ValueDescriptors in the level-list
		for (int i = 1; i <= this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (!(vd instanceof RangeDescriptor)) {
				svd = (SingleDescriptor)vd;
				if (svd.getValue() == aValue)
					vdList.add(vd);
			}
		}

		// Return the list of ValueDescriptors
		return vdList;
	}
	
	/**
	 * Returns nil or a (possibly empty) list with all ValueDescriptors from a level-list such that
	 * their value matches aValue and their weights lie between a range determined by aLowerBound
	 * and anUpperBound.
	 * IMPORTANT: Returning nil means that this method is in an error condition
	 * @see "Método value:in:weightBetween:and: del protocolo accessing-weight values en SUKIA SmallTalk"
	 * @param aValue
	 * @param aLevel
	 * @param aLowerBound
	 * @param anUpperBound
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<ValueDescriptor> getSingleDescriptorsWithWeightInRange(T aValue, TaxonomicRank aLevel, double aLowerBound, double anUpperBound) {
		int levelNumber;
		ValueDescriptor vd;
		SingleDescriptor<T> svd;
		List<ValueDescriptor> vdList;
		double lb, ub;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;

		// Make a copy of the lower and upper bound arguments
		lb = aLowerBound;
		ub = anUpperBound;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<ValueDescriptor>();

		// Parse all ValueDescriptors in the level-list and copy, into vwList, all those whose value matches aValue and their
		// weight is between the range given by lb and ub
		for (int i = 1; i <= this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (!(vd instanceof RangeDescriptor)) {
				svd = (SingleDescriptor)vd;
				if (svd.getValue() == aValue)
					if (svd.getWeight() >= lb && svd.getWeight() <= ub)
						vdList.add(vd);
			}
		}

		// Return the list of ValueDescriptors
		return vdList;
	}
	
	/**
	 * Returns nil or a (possibly empty) list with all ValueDescriptors from a level-list,
	 * such that their value matches aValue and their weight is compared against
	 * a given binary operator (i.e., =, ~=, < , <=, >, >=).
	 * IMPORTANT: Returning nil means that this method is in an error condition
	 * @see "Método value:weight:in:operator: del protocolo accessing-weight values en SUKIA SmallTalk"
	 * @param aValue
	 * @param aWeight
	 * @param aLevel
	 * @param anOperator
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<ValueDescriptor> getSingleDescriptors(T aValue, double aWeight, TaxonomicRank aLevel, String anOperator) {
		int levelNumber, i;
		ValueDescriptor vd;
		SingleDescriptor<T> svd;
		List<ValueDescriptor> vdList;
		double w;
		boolean stop, flag;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;

		// Make a copy of the argument aWeight and make sure that it is a Float
		w = aWeight;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<ValueDescriptor>();

		/* Parse the level-list and copy, into vdList, all those ValueDescriptors whose value matches aValue and their 
		 weight compares to true according to the given binary operator.  NOTE: It is assumed that for any given 
		 instance of  Value (i.e., self), all instances of ValueDescriptor must be UNIQUE, that is, the combination 
		 value/weight can not be repeated. That is the reason for stopping the loop when the '=' operator is used. 
		 This way, once the corresponding ValueDescriptor is found, it becomes unnecessary to continue parsing 
		 the level-list */
		
		i = 1;
		stop = false;
	 	while (i <= this.get(levelNumber-1).size()) {
	 		vd = this.get(levelNumber-1).get(i-1);
			if (!(vd instanceof RangeDescriptor)) {
				svd = (SingleDescriptor)vd;
				if (svd.getValue() == aValue) {
					flag = false;
					if (anOperator.equals("=")) {
						flag = (svd.getWeight() == w);
						stop = true;
					}
					if (anOperator.equals("~="))
						flag = (svd.getWeight() != w);
					if (anOperator.equals("<"))
						flag = (svd.getWeight() < w);
					if (anOperator.equals("<="))
						flag = (svd.getWeight() <= w);
					if (anOperator.equals(">"))
						flag = (svd.getWeight() > w);
					if (anOperator.equals(">="))
						flag = (svd.getWeight() >= w);
	
					if (flag == true) vdList.add(vd);
					if (stop == true) i = this.get(levelNumber-1).size();
				}
			}

			i = i + 1;
	 	}
		

		// Return the list of ValueDescriptors
		return vdList;
	}
	
	/**
	 * Returns nil or a (possibly empty) list with all ValueDescriptors from a level-list
	 * such that their weights lie between a range determined by aLowerBound and anUpperBound.
	 * IMPORTANT: Returning nil means that this method is in an error condition
	 * @see "Método weight:in:between:and: del protocolo accessing-weight values en SUKIA SmallTalk"
	 * @param aWeight
	 * @param aLevel
	 * @param aLowerBound
	 * @param anUpperBound
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<ValueDescriptor> getSingleDescriptors(double aWeight, TaxonomicRank aLevel, double aLowerBound, double anUpperBound) {
		int levelNumber;
		ValueDescriptor vd;
		SingleDescriptor<T> svd;
		List<ValueDescriptor> vdList;
		double lb, ub;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;

		// Make a copy of the lower and upper bound arguments
		lb = aLowerBound;
		ub = anUpperBound;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<ValueDescriptor>();

		// Parse all ValueDescriptors in the level-list and copy, into vwList, all those whose value matches aValue and their
		// weight is between the range given by lb and ub
		for (int i = 1; i <= this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (!(vd instanceof RangeDescriptor)) {
				svd = (SingleDescriptor)vd;
				if (svd.getWeight() == aWeight)
					if (svd.getWeight() >= lb && svd.getWeight() <= ub)
						vdList.add(vd);
			}
		}
		
		// Return the list of ValueDescriptors
		return vdList;
	}
	
	/**
	 * Returns nil or a (possibly empty) list with all ValueDescriptors from a level-list
	 * such that their weight is compared against a given binary operator (i.e., =, ~=, <, <=, >, >=).
	 * IMPORTANT: Returning nil means that this method is in an error condition
	 * @see "Método weight:in:operator: del protocolo accessing-weight values en SUKIA SmallTalk"
	 * @param aWeight
	 * @param aLevel
	 * @param anOperator
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<ValueDescriptor> getSingleDescriptors(double aWeight, TaxonomicRank aLevel, String anOperator) {
		int levelNumber, i;
		ValueDescriptor vd;
		SingleDescriptor<T> svd;
		List<ValueDescriptor> vdList;
		double w;
		boolean stop, flag;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber ==-1) return null;

                if (anOperator == null) return null;

		// Make a copy of the argument aWeight and make sure that it is a Float
		w = aWeight;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<ValueDescriptor>();

		// Parse the level-list and copy, into vdList, all those ValueDescriptors whose weight compares 
		// to true according to the given binary operator
		
		i = 1;
		stop = false;
	 	while (i <= this.get(levelNumber-1).size()) {
	 		vd = this.get(levelNumber-1).get(i);
	 		if (!(vd instanceof RangeDescriptor)) {
				svd = (SingleDescriptor)vd;
				flag = false;
				if (anOperator.equals("=")) {
					flag = (svd.getWeight() == w);
					stop = true;
				}
				if (anOperator.equals("~="))
					flag = (svd.getWeight() != w);
				if (anOperator.equals("<"))
					flag = (svd.getWeight() < w);
				if (anOperator.equals("<="))
					flag = (svd.getWeight() <= w);
				if (anOperator.equals(">"))
					flag = (svd.getWeight() > w);
				if (anOperator.equals(">="))
					flag = (svd.getWeight() >= w);

				if (flag == true) vdList.add(vd);
				if (stop == true) i = this.get(levelNumber-1).size();
			}

			i = i + 1;
	 	}
		

		// Return the list of ValueDescriptors
		return vdList;
	}
	
	/**
	 * @see "Método valueDescriptor:for: del protocolo adding en SUKIA SmallTalk"
	 * @param aValueDescriptor
	 * @param aLevel
	 */
	public void addValueDescriptor(ValueDescriptor aValueDescriptor, TaxonomicRank aLevel) {
		int levelNumber;

		/* Adds a ValueDescriptor to a level-list in self.  This method makes sure that: a) if aDescriptor is not a range,
		then its pair value-weight must be unique, or b) if aDescriptor is a range, its lower and upper bounds must
		 be unique.
		 Returns: 	nil - Bad input or ValueDescriptor already exists;
					self - Process OK.
		NOTE: This method should be used when creating the StructureIndex for Taxonomy */
	
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return;

		/* IMPORTANT ACTION TO ENSURE THE ADDITION OF DESCRIPTORS WITH UNIQUE VALUE-WEIGHTS OR RANGES:
		 Make sure that aDescriptor DOES NOT exist in aLevel.  NOTE: Since the argument for includes: is levelNumber, the return 
		 value (in this case) from includes:in: will always be either true or false */
			
		if (this.includes(aValueDescriptor, levelNumber-1)) return;
		
		this.get(levelNumber-1).add(aValueDescriptor);
	}
	
	/**
	 * @see "Método valueDescriptorWithUniqueValue:for: del protocolo adding en SUKIA SmallTalk"
	 * @param aValueDescriptor
	 * @param aLevel
	 */
	public void addValueDescriptorWithUniqueValue(ValueDescriptor aValueDescriptor, TaxonomicRank aLevel) {
		int levelNumber;
		
		/* Adds a ValueDescriptor to a level-list in self.  This method makes sure that: a) if aDescriptor is not a range,
		then its value must be unique (disregards the weight), or b) if aDescriptor is a range, its lower and upper bounds
		 must be unique.
		 Returns: 	nil - Bad input or ValueDescriptor already exists;
					self - Process OK.
		NOTE: This method should be used when creating a Taxon's SAV or GH description */
		
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return;
		
		/* IMPORTANT ACTION TO ENSURE THE ADDITION OF DESCRIPTORS WITH UNIQUE VALUES OR RANGES:
		 Make sure that the value of aDescriptor DOES NOT exist in aLevel.  NOTE: Since the argument for the method
		 includesUniqueValue:in: is levelNumber, the return value (in this case) from includes: will always be either true or false */
		
		if (this.includesUniqueValue(aValueDescriptor, levelNumber-1)) return;
		this.get(levelNumber-1).add(aValueDescriptor);
	}
	
	/**
	 * Determines whether or not aDescriptor exists in the level-list determined by aLevel.
	 * Returns: 	nil - bad input;
	 * true - there is a descriptor that matches aDescriptor;
	 * false - a descriptor matching aDescriptor was not found
	 * @see "Método includes:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean includes(ValueDescriptor aDescriptor, int aNumberLevel) {
		int levelNumber;
		ValueDescriptor vd;
		RangeDescriptor nrvd, rvd;
		SingleDescriptor<Object> nsvd, svd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i-1);
			
			if (aDescriptor instanceof RangeDescriptor) {
				rvd = (RangeDescriptor) aDescriptor;
				if (vd instanceof RangeDescriptor) {
					nrvd = (RangeDescriptor) vd;
					if (nrvd.getLowerBound() == rvd.getLowerBound() && nrvd.getUpperBound() == rvd.getUpperBound())
						return true;
				}
			} else {
				svd = (SingleDescriptor) aDescriptor;
				if (!(vd instanceof RangeDescriptor)) {
					nsvd = (SingleDescriptor) vd;
					if (nsvd.getValue() == svd.getValue() && nsvd.getWeight() == svd.getWeight())
						return true;
				}
			}	
		}		
		
		return false;
	}
	
	/**
	 * Determines whether or not aDescriptor exists in the level-list determined by aLevel.
	 * Returns: 	nil - bad input;
	 * true - there is a descriptor that matches aDescriptor;
	 * false - a descriptor matching aDescriptor was not found
	 * @see "Método includesUniqueValue:in: del protocolo testing en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean includesUniqueValue(ValueDescriptor aDescriptor, int aNumberLevel) {
		int levelNumber;
		ValueDescriptor vd;
		RangeDescriptor nrvd, rvd;
		SingleDescriptor<Object> nsvd, svd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i-1);
			
			if (aDescriptor instanceof RangeDescriptor) {
				rvd = (RangeDescriptor) aDescriptor;
				if (vd instanceof RangeDescriptor) {
					nrvd = (RangeDescriptor) vd;
					if (nrvd.getLowerBound() == rvd.getLowerBound() && nrvd.getUpperBound() == rvd.getUpperBound())
						return true;
				}
			} else {
				svd = (SingleDescriptor) aDescriptor;
				if (!(vd instanceof RangeDescriptor)) {
					nsvd = (SingleDescriptor) vd;
					// Disregard the weight in this comparison					
					if (nsvd.getValue() == svd.getValue())
						return true;
				}
			}
		}		
		
		return false;
	}
}
