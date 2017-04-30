
package models.player;

import java.util.List;

import logs.LogService;
import models.plates.PlateSnapshot;
import models.plates.collection.PlateCollection;

/**
 * The snapshot of player hand.
 * @author Amr
 *
 */
public class PlayerHandSnapshot {

	/**
	 * List of plates snapshots in the hand.
	 */
	private List<PlateSnapshot> listOfPlates;
	
	/**
	 * Constructor.
	 * @param collection
	 * The plate collection in the hand.
	 */
	public PlayerHandSnapshot(final PlateCollection collection) {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " PlayerHandSnapshot class.");
		listOfPlates = collection.getSnapshot();
	}
	
	/**
	 * @return the listOfPlates
	 */
	public final List<PlateSnapshot> getListOfPlates() {
		LogService.printTrace(this.getClass(), "list<PlateSnapshot> Method"
				+ " getListOfPlates is called.");
		return listOfPlates;
	}

}
