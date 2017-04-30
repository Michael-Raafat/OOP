
package models.plates.collection;

import java.util.Iterator;
import java.util.List;

import models.plates.Plate;
import models.plates.PlateSnapshot;

/**
 * Interface of a plate collection.
 * @author Amr
 *
 */
public interface PlateCollection extends Iterable<Plate> {

	/**
	 * Adds a plate to the collection.
	 * @param plate
	 * The plate to be added.
	 */
	void add(Plate plate);

	/**
	 * Returns the plate in the list at the specified index.
	 * @param index
	 * The index of the wanted plate.
	 * @return
	 * The plate in the specified index.
	 */
	Plate get(int index);

	/**
	 * Removes a plate from the list at the specified index.
	 * @param index
	 * The index of the plate to be removed.
	 */
	void remove(int index);

	/**
	 * Removes a plate from the list.
	 * @param plate
	 * The plate to be removed.
	 */
	void remove(Plate plate);

	/**
	 * Gets an iterator to iterate through the collection.
	 * @return
	 * A plateIterator.
	 */
	Iterator<Plate> iterator();

	/**
	 * Returns the size of the Plate Collection.
	 * @return
	 * An integer of the number of items in the collection.
	 */
	int size();

	/**
	 * Returns a list of snapshots of plates in collection.
	 * @return
	 * The list of snapshots of plates in collection.
	 */
	List<PlateSnapshot> getSnapshot();

}
