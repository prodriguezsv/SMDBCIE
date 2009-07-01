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
public class Value extends ArrayList<List<ValueDescriptor>> {
	
	/**
	 * @see "Método init del protocolo initializing en SUKIA SmallTalk"
	 */
	public Value() {
		List<ValueDescriptor> level;

		for (int i = 1; i <= TaxonomicLevels.nomenclaturalNumber(); i++) {
			level = new ArrayList<ValueDescriptor>();
			//Pendiente el ordenamiento
			this.add(level);
		}
	}
	
	/**
	 * @see "Método getMatchingValueDescriptorFor:in: del protocolo accessing-general en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aLevel
	 */
	// Pendiente de traducir
	public void getMatchingValueDescriptorFor(ValueDescriptor aDescriptor, String aLevel) {
		ValueDescriptor vd;
		
		
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
	public List<ValueDescriptor> getValueDescriptorsIn(String aLevel) {
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
	// pendiente de traducir
	public List<ValueDescriptor> numberInRange(double aNumber, String aLevel) {
		int levelNumber;
		List<ValueDescriptor> vdList;
		ValueDescriptor vd;
		
		//Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;
	
		vdList = new ArrayList<ValueDescriptor>();
		for (int i = 1; i <=  this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			
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
	// Pendiente de traducir
	public List<ValueDescriptor> rangeMatching(double aLowerBound, double anUpperBound, String aLevel) {
		int levelNumber;
		List<ValueDescriptor> vdList;
		ValueDescriptor vd;
		
		// Get the level-list index (in self) corresponding to aLevel
		levelNumber = TaxonomicLevels.transformToIndex(aLevel);
		if (levelNumber == -1) return null;
		
		return null;
	}
	
	/**
	 * Returns nil or a (possibly empty) list with all ValueDescriptors from a level-list, such that their value matches aValue.
	 * IMPORTANT: Returning nil means that this method is in an error condition
	 * @see "Método value:in: del protocolo accessing-weight values en SUKIA SmallTalk"
	 * @param aValue
	 * @param aLevel
	 * @return
	 */
	// Pendiente de traducir
	public double value(double aValue, String aLevel) {
		return 0;
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
	// Pendiente de traducir
	public double value(double aValue, String aLevel, double aLowerBound, double anUpperBound) {
		return 0;
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
	// Pendiente de traducir
	public double value(double aValue, double aWeight, String aLevel, String anOperator) {
		return 0;
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
	// Pendiente de traducir
	public double weight(double aWeight, String aLevel, double aLowerBound, double anUpperBound) {
		return 0;
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
	// Pendiente de traducir
	public double weight(double aWeight, String aLevel, String anOperator) {
		return 0;
	}
	
	/**
	 * @see "Método valueDescriptor:for: del protocolo adding en SUKIA SmallTalk"
	 * @param aValueDescriptor
	 * @param aLevel
	 */
	public void addValueDescriptor(ValueDescriptor aValueDescriptor, String aLevel) {
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
	public void addValueDescriptorWithUniqueValue(ValueDescriptor aValueDescriptor, String aLevel) {
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
	// Pendiente de traducir
	public boolean includes(ValueDescriptor aDescriptor, int aNumberLevel) {
		int levelNumber;
		ValueDescriptor vd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			
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
	// Pendiente de traducir
	public boolean includesUniqueValue(ValueDescriptor aDescriptor, int aNumberLevel) {
		int levelNumber;
		ValueDescriptor vd;
		
		levelNumber = aNumberLevel;
		if (levelNumber == -1) return false;
		
		for (int i = 1; i <= this.get(levelNumber).size(); i++) {
			vd = this.get(levelNumber).get(i);
			
		}
		
		return false;
	}
}
