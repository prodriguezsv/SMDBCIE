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
public class Values extends ArrayList<List<Value>> {
	
	/**
	 * @see "Método init del protocolo initializing en SUKIA SmallTalk"
	 */
	public Values() {
		List<Value> level;

		for (int i = 1; i <= TaxonomicRank.getNomenclaturalRanksNumber(); i++) {
			level = new ArrayList<Value>();
			this.add(level);
		}
	}
	
	/**
	 * @see "Método newWithOneLevel del protocolo initializing en SUKIA SmallTalk"
	 */
	public Values(boolean oneLevel) {
		List<Value> level;

		if (oneLevel) {
			level = new ArrayList<Value>();
			this.add(level);
		}
	}
	
	/**
	 * @see "Método getMatchingValueDescriptorFor:in: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 */
	public <T extends Value> Value getValueDescriptors(T aDescriptor, TaxonomicRank aLevel) {
		List<Value> vdList;
		
		if (aDescriptor instanceof SingleValue)			
			vdList = this.getSingleDescriptors(((SingleValue)aDescriptor).getValue(), aLevel);
		else
			vdList = this.getRangeDescriptorsWithRange(((RangeValue)aDescriptor).getLowerBound(), 
					((RangeValue)aDescriptor).getUpperBound(), aLevel);

		if (vdList == null) return null;
		
		if (vdList.isEmpty() || vdList.size() > 1) return null;
		
		return vdList.get(0);
	}
	
	/**
	 * Return the entire list of ValueDescriptors, distributed in level-lists
	 * @see "Método valueDescriptors del protocolo accessing-general en SUKIA SmallTalk"
	 */
	public Values getValueDescriptors() {
		return this;
	}
	
	/**
	 * Returns nil or all ValueDescriptors in a level-list
	 * @see "Método valueDescriptorsIn: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aLevel
	 * @return
	 */
	public List<Value> getValueDescriptors(TaxonomicRank aLevel) {
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
	public List<Value> getRangeDescriptorsWithNumber(double aNumber, TaxonomicRank aLevel) {
		int levelNumber;
		List<Value> vdList;
		Value vd;
		RangeValue rvd;
		double n;
		
		//Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;
	
		n = aNumber;
		vdList = new ArrayList<Value>();
		for (int i = 1; i <=  this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (vd instanceof RangeValue) {
				rvd = (RangeValue)vd;
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
	public List<Value> getRangeDescriptorsWithRange(double aLowerBound, double anUpperBound, TaxonomicRank aLevel) {
		int levelNumber;
		List<Value> vdList;
		Value vd;
		RangeValue rvd;
		double lb, ub;
		
		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;
		
		lb = aLowerBound;
		ub = anUpperBound;
		
		vdList = new ArrayList<Value>();

		for (int i = 1; i <= this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (vd instanceof RangeValue) {
				rvd = (RangeValue) vd;
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
	public <T> List<Value> getSingleDescriptors(T aValue, TaxonomicRank aLevel) {
		int levelNumber;
		Value vd;
		SingleValue svd;
		List<Value> vdList;

		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicRank.getIndex(aLevel);
		if (levelNumber == 0) return null;

		// Create the output ValueDescriptor list (vdList)
		vdList = new ArrayList<Value>();

		// Parse all ValueDescriptors in the level-list
		for (int i = 1; i <= this.get(levelNumber-1).size(); i++) {
			vd = this.get(levelNumber-1).get(i-1);
			if (!(vd instanceof RangeValue)) {
				svd = (SingleValue)vd;
				if (svd.equals(aValue)) //Ojo revisar
					vdList.add(vd);
			}
		}

		// Return the list of ValueDescriptors
		return vdList;
	}
	
	/**
	 * @see "Método valueDescriptor:for: del protocolo adding en SUKIA SmallTalk"
	 * @param aValueDescriptor
	 * @param aLevel
	 */
	public void addValueDescriptor(Value aValueDescriptor, TaxonomicRank aLevel) {
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
	public void addValueDescriptorWithUniqueValue(Value aValueDescriptor, TaxonomicRank aLevel) {
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
	public boolean includes(Value aDescriptor, int aNumberLevel) {
		int levelNumber;
		Value vd;
		RangeValue nrvd, rvd;
		SingleValue nsvd, svd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i-1);
			
			if (aDescriptor instanceof RangeValue) {
				rvd = (RangeValue) aDescriptor;
				if (vd instanceof RangeValue) {
					nrvd = (RangeValue) vd;
					if (nrvd.getLowerBound() == rvd.getLowerBound() && nrvd.getUpperBound() == rvd.getUpperBound())
						return true;
				}
			} else {
				svd = (SingleValue) aDescriptor;
				if (!(vd instanceof RangeValue)) {
					nsvd = (SingleValue) vd;
					if (nsvd.getValue() == svd.getValue())
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
	public boolean includesUniqueValue(Value aDescriptor, int aNumberLevel) {
		int levelNumber;
		Value vd;
		RangeValue nrvd, rvd;
		SingleValue nsvd, svd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i-1);
			
			if (aDescriptor instanceof RangeValue) {
				rvd = (RangeValue) aDescriptor;
				if (vd instanceof RangeValue) {
					nrvd = (RangeValue) vd;
					if (nrvd.getLowerBound() == rvd.getLowerBound() && nrvd.getUpperBound() == rvd.getUpperBound())
						return true;
				}
			} else {
				svd = (SingleValue) aDescriptor;
				if (!(vd instanceof RangeValue)) {
					nsvd = (SingleValue) vd;
					// Disregard the weight in this comparison					
					if (nsvd.getValue() == svd.getValue())
						return true;
				}
			}
		}		
		
		return false;
	}
}
