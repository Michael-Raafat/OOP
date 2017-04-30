
package models.plates.collection;

import java.util.Iterator;

import logs.LogService;
import models.plates.Plate;

/**
 * Implementation of the plate iterator.
 * @author Amr
 *
 */
public class PlateIteratorImp implements Iterator<Plate> {
	/**
	 * Index of the iterator.
	 */
	private int index;
	/**
	 * The plate collection of the iterator.
	 */
	private PlateCollection plates;
	/**
	 * Constructor.
	 * @param collection
	 * The collection of plates to iterate on.
	 */
	public PlateIteratorImp(final PlateCollection collection) {
		LogService.printTrace(getClass(), "Plate iterator created.");
		plates = collection;
		index = collection.size();
	}

	@Override
	public final boolean hasNext() {
		LogService.printTrace(getClass(),
				"Plate iterator checks for next item existence");
		return index > 0;
	}

	@Override
	public final Plate next() {
		LogService.printTrace(getClass(), "Plate iterator get next item.");
		index--;
		return plates.get(index);
	}

}
