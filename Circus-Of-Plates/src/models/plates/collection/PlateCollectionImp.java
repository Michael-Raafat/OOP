
package models.plates.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logs.LogService;
import models.plates.Plate;
import models.plates.PlateSnapshot;

/**
 * Implementation of PlateCollection.
 * @author Amr
 *
 */
public class PlateCollectionImp implements PlateCollection {
	
	/**
	 * List of plates.
	 */
	private List<Plate> list;

	/**
	 * Constructor.
	 */
	public PlateCollectionImp() {
		LogService.printTrace(this.getClass(), "Construction of "
				+ "PlateCollectionImp class"
				+ " that implements PlateCollection");
		list = new ArrayList<Plate>();
	}

	@Override
	public final void add(final Plate plate) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " add(Plate) is called.");
		list.add(plate);
	}

	@Override
	public final Plate get(final int index) {
		LogService.printTrace(this.getClass(), "Plate Method"
				+ " get(index) is called.");
		return list.get(index);
	}

	@Override
	public final void remove(final int index) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " remove(index) is called.");
		list.remove(index);
	}

	@Override
	public final Iterator<Plate> iterator() {
		LogService.printTrace(this.getClass(), "PlateIterator Method"
				+ " iterator is called.");
		return new PlateIteratorImp(this);
	}

	@Override
	public final int size() {
		LogService.printTrace(this.getClass(), "int Method"
				+ " size is called.");
		return list.size();
	}

	@Override
	public final void remove(final Plate plate) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " remove(Plate) is called.");
		list.remove(plate);
	}

	@Override
	public final List<PlateSnapshot> getSnapshot() {
		LogService.printTrace(this.getClass(), "List<PlateSnapshot> Method"
				+ " getSnapshot is called.");
		List<PlateSnapshot> snapList
			= new ArrayList<PlateSnapshot>();
		for (Plate plate : this) {
			snapList.add(plate.createSnapshot());
		}
		return snapList;
	}
}
