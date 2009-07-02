/**
 * 
 */
package values;

import java.util.ArrayList;
import java.util.List;

import domainTheory.TaxonomicLevels;

/**
 * @author Armando
 *
 */
public class Value extends ArrayList<List<Descriptor>> {
	
	/**
	 * @see "Método init del protocolo initializing en SUKIA SmallTalk"
	 */
	public Value() {
		List<Descriptor> level;

		for (int i = 1; i <= TaxonomicLevels.nomenclaturalNumber(); i++) {
			level = new ArrayList<Descriptor>();
			//Pendiente el ordenamiento
			this.add(level);
		}
	}
	
	/**
	 * @see "Método getMatchingValueDescriptorFor:in: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 */
	public <T> Descriptor getMatchingValueDescriptorFor(SingleDescriptor<T> aDescriptor, String aLevel) {
		List<Descriptor> vdList;
		
		vdList = this.value(aDescriptor.getValue(), aDescriptor.getWeight(), aLevel, "=");

		if (vdList == null) return null;
		
		if (vdList.isEmpty() || vdList.size() > 1) return null;
		
		return vdList.get(1);
	}
	
	/**
	 * @see "Método getMatchingValueDescriptorFor:in: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 */
	public Descriptor getMatchingValueDescriptorFor(RangeDescriptor aDescriptor, String aLevel) {
		List<Descriptor> vdList;
		
		
		vdList = this.rangeMatching(aDescriptor.getLowerBound(), aDescriptor.getUpperBound(), aLevel);

		if (vdList == null) return null;
		
		if (vdList.isEmpty() || vdList.size() > 1) return null;
		
		return vdList.get(1);
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
	public List<Descriptor> getValueDescriptorsIn(String aLevel) {
		int levelNumber;
		
		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		// Return the level-list indexed in self (i.e, Value) by levelNumber
		return (this.get(levelNumber));
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
	public List<Descriptor> numberInRange(double aNumber, String aLevel) {
		int levelNumber;
		List<Descriptor> vdList;
		Descriptor vd;
		RangeDescriptor rvd;
		double n;
		
		//Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;
	
		n = aNumber;
		vdList = new ArrayList<Descriptor>();
		for (int i = 1; i <=  this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			if (vd.getClass().getName().equals("RangeDescriptor")) {
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
	public List<Descriptor> rangeMatching(double aLowerBound, double anUpperBound, String aLevel) {
		int levelNumber;
		List<Descriptor> vdList;
		Descriptor vd;
		RangeDescriptor rvd;
		double lb, ub;
		
		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;
		
		lb = aLowerBound;
		ub = anUpperBound;
		
		vdList = new ArrayList<Descriptor>();

		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			if (vd.getClass().getName().equals("RangeDescriptor")) {
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
	public <T> List<Descriptor> value(T aValue, String aLevel) {
		int levelNumber;
		Descriptor vd;
		SingleDescriptor<T> svd;
		List<Descriptor> vdList;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<Descriptor>();

		// Parse all ValueDescriptors in the level-list
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
				svd = (SingleDescriptor<T>)vd;
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
	public <T> List<Descriptor> value(T aValue, String aLevel, double aLowerBound, double anUpperBound) {
		int levelNumber;
		Descriptor vd;
		SingleDescriptor<T> svd;
		List<Descriptor> vdList;
		double lb, ub;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		// Make a copy of the lower and upper bound arguments
		lb = aLowerBound;
		ub = anUpperBound;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<Descriptor>();

		// Parse all ValueDescriptors in the level-list and copy, into vwList, all those whose value matches aValue and their
		// weight is between the range given by lb and ub
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
				svd = (SingleDescriptor<T>)vd;
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
	public <T> List<Descriptor> value(T aValue, double aWeight, String aLevel, String anOperator) {
		int levelNumber, i;
		Descriptor vd;
		SingleDescriptor<T> svd;
		List<Descriptor> vdList;
		double w;
		boolean stop, flag;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		// Make a copy of the argument aWeight and make sure that it is a Float
		w = aWeight;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<Descriptor>();

		/* Parse the level-list and copy, into vdList, all those ValueDescriptors whose value matches aValue and their 
		 weight compares to true according to the given binary operator.  NOTE: It is assumed that for any given 
		 instance of  Value (i.e., self), all instances of ValueDescriptor must be UNIQUE, that is, the combination 
		 value/weight can not be repeated. That is the reason for stopping the loop when the '=' operator is used. 
		 This way, once the corresponding ValueDescriptor is found, it becomes unnecessary to continue parsing 
		 the level-list */
		
		i = 1;
		stop = false;
	 	while (i <= this.get(levelNumber).size()) {
	 		vd = this.get(levelNumber).get(i);
			if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
				svd = (SingleDescriptor<T>)vd;
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
				if (stop == true) i = this.get(levelNumber).size();
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
	public <T> List<Descriptor> weight(double aWeight, String aLevel, double aLowerBound, double anUpperBound) {
		int levelNumber;
		Descriptor vd;
		SingleDescriptor<T> svd;
		List<Descriptor> vdList;
		double lb, ub;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		// Make a copy of the lower and upper bound arguments
		lb = aLowerBound;
		ub = anUpperBound;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<Descriptor>();

		// Parse all ValueDescriptors in the level-list and copy, into vwList, all those whose value matches aValue and their
		// weight is between the range given by lb and ub
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
				svd = (SingleDescriptor<T>)vd;
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
	public <T> List<Descriptor> weight(double aWeight, String aLevel, String anOperator) {
		int levelNumber, i;
		Descriptor vd;
		SingleDescriptor<T> svd;
		List<Descriptor> vdList;
		double w;
		boolean stop, flag;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;

		// Make a copy of the argument aWeight and make sure that it is a Float
		w = aWeight;
		
		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<Descriptor>();

		// Parse the level-list and copy, into vdList, all those ValueDescriptors whose weight compares 
		// to true according to the given binary operator
		
		i = 1;
		stop = false;
	 	while (i <= this.get(levelNumber).size()) {
	 		vd = this.get(levelNumber).get(i);
			if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
				svd = (SingleDescriptor<T>)vd;
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
				if (stop == true) i = this.get(levelNumber).size();
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
	public void addValueDescriptor(Descriptor aValueDescriptor, String aLevel) {
		int levelNumber;

		/* Adds a ValueDescriptor to a level-list in self.  This method makes sure that: a) if aDescriptor is not a range,
		then its pair value-weight must be unique, or b) if aDescriptor is a range, its lower and upper bounds must
		 be unique.
		 Returns: 	nil - Bad input or ValueDescriptor already exists;
					self - Process OK.
		NOTE: This method should be used when creating the StructureIndex for Taxonomy */
	
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return;

		/* IMPORTANT ACTION TO ENSURE THE ADDITION OF DESCRIPTORS WITH UNIQUE VALUE-WEIGHTS OR RANGES:
		 Make sure that aDescriptor DOES NOT exist in aLevel.  NOTE: Since the argument for includes: is levelNumber, the return 
		 value (in this case) from includes:in: will always be either true or false */
			
		if (this.includes(aValueDescriptor, levelNumber)) return;
		
		this.get(levelNumber).add(aValueDescriptor);
	}
	
	/**
	 * @see "Método valueDescriptorWithUniqueValue:for: del protocolo adding en SUKIA SmallTalk"
	 * @param aValueDescriptor
	 * @param aLevel
	 */
	public void addValueDescriptorWithUniqueValue(Descriptor aValueDescriptor, String aLevel) {
		int levelNumber;
		
		/* Adds a ValueDescriptor to a level-list in self.  This method makes sure that: a) if aDescriptor is not a range,
		then its value must be unique (disregards the weight), or b) if aDescriptor is a range, its lower and upper bounds
		 must be unique.
		 Returns: 	nil - Bad input or ValueDescriptor already exists;
					self - Process OK.
		NOTE: This method should be used when creating a Taxon's SAV or GH description */
		
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return;
		
		/* IMPORTANT ACTION TO ENSURE THE ADDITION OF DESCRIPTORS WITH UNIQUE VALUES OR RANGES:
		 Make sure that the value of aDescriptor DOES NOT exist in aLevel.  NOTE: Since the argument for the method
		 includesUniqueValue:in: is levelNumber, the return value (in this case) from includes: will always be either true or false */
		
		if (this.includesUniqueValue(aValueDescriptor, levelNumber)) return;
		this.get(levelNumber).add(aValueDescriptor);
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
	public boolean includes(Descriptor aDescriptor, int aNumberLevel) {
		int levelNumber;
		Descriptor vd;
		RangeDescriptor nrvd, rvd;
		SingleDescriptor nsvd, svd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			
			if (aDescriptor.getClass().getName().equals("RangeDescriptor")) {
				rvd = (RangeDescriptor) aDescriptor;
				if (vd.getClass().getName().equals("RangeDescriptor")) {
					nrvd = (RangeDescriptor) vd;
					if (nrvd.getLowerBound() == rvd.getLowerBound() && nrvd.getUpperBound() == rvd.getUpperBound())
						return true;
				}
			} else {
				svd = (SingleDescriptor) aDescriptor;
				if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
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
	public boolean includesUniqueValue(Descriptor aDescriptor, int aNumberLevel) {
		int levelNumber;
		Descriptor vd;
		RangeDescriptor nrvd, rvd;
		SingleDescriptor nsvd, svd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			
			if (aDescriptor.getClass().getName().equals("RangeDescriptor")) {
				rvd = (RangeDescriptor) aDescriptor;
				if (vd.getClass().getName().equals("RangeDescriptor")) {
					nrvd = (RangeDescriptor) vd;
					if (nrvd.getLowerBound() == rvd.getLowerBound() && nrvd.getUpperBound() == rvd.getUpperBound())
						return true;
				}
			} else {
				svd = (SingleDescriptor) aDescriptor;
				if (!(vd.getClass().getName().equals("RangeDescriptor"))) {
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
