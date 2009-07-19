/**
 * @see "Categoría Sukia Search Hints Lists de SUKIA Smalltalk"
 */
package searchHintsBase.Lists;

import java.util.ArrayList;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class HintsList<T> extends ArrayList<T> {
	private double percentageItemsProcessed;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public HintsList() {
		resetPercentageItemsProcessed();
	}

	/**
	 * @see "Método percentageItemsProcessed: del protocolo adding en SUKIA SmallTalk"
	 * @param percentageItemsProcessed
	 */
	public void setPercentageItemsProcessed(double percentageItemsProcessed) {
		this.percentageItemsProcessed = percentageItemsProcessed;
	}

	/**
	 * @see "Método percentageItemsProcessed: del protocolo adding en SUKIA SmallTalk"
	 * @param percentageItemsProcessed
	 */
	public void resetPercentageItemsProcessed() {
		this.percentageItemsProcessed = 0;
	}
	
	/**
	 * "Método percentageItemsProcessed del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public double getPercentageItemsProcessed() {
		return percentageItemsProcessed;
	}

}
