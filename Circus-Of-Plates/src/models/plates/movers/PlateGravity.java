
package models.plates.movers;

import models.graphics.GameObject;
import models.plates.Plate;
import models.plates.collection.PlateCollection;

/**
 * Plate Gravity interface responsible of updating falling plates.
 * @author Amr
 *
 */
public interface PlateGravity extends GameObject {
	
	/**
	 * adds a plate to the list of the plate falling.
	 * @param plate
	 * The plate that will begin falling.
	 */
	void addPlate(Plate plate);
	
	/**
	 * Returns the collection of the falling plates.
	 * @return
	 * Collection of falling plates.
	 */
	PlateCollection getFallingPlates();
	
	/**
	 * Creates a snapshot in the current moment for the gravity item.
	 * @return
	 * A snapshot item for the gravity.
	 */
	PlateGravitySnapshot createSnapshot();
	
	/**
	 * Rolls back the gravity item to a snapshot.
	 * @param snapshot
	 * Snapshot of the gravity to load.
	 */
	void useSnapshot(PlateGravitySnapshot snapshot);

}
