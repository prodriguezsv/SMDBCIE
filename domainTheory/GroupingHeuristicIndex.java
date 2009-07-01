/**
 * 
 */
package domainTheory;

import java.util.ArrayList;

/**
 * @author Armando
 *
 */
public class GroupingHeuristicIndex extends ArrayList<GroupingHeuristic> {
	/**
	 *  @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public GroupingHeuristicIndex() {
		// TODO Auto-generated constructor stub
		//Pendiente el ordenamiento
	}
	
	/**
	 * @see "Método groupingHeuristicWith: del protocolo accessing en SUKIA SmallTalk"
	 * @param aGHName
	 * @return
	 */
	public GroupingHeuristic groupingHeuristicWith(String aGHName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i).getName().equals(aGHName)) 
				return this.get(i);
		}
		
		return null;
	}
	
	/**
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aGroupingHeuristicName
	 * @return
	 */
	public boolean includes(String aGroupingHeuristicName) {
		for (int i = 1; i <= this.size(); i++) {
			if (this.get(i).getName().equals(aGroupingHeuristicName))
				return true;
		}
		
		return false;
	}
}
