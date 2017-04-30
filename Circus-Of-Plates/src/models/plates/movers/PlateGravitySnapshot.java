
package models.plates.movers;

import java.util.List;

import logs.LogService;
import models.plates.PlateSnapshot;

/**
 * The snapshot of the plate gravity component.
 * @author Amr
 *
 */
public class PlateGravitySnapshot {

	/**
	 * The collection of the falling plates.
	 */
	private List<PlateSnapshot> fallingPlatesSnapshot;
	
	/**
	 * Constructor.
	 * @param gravity
	 * The gravity object to create a snapshot of.
	 */
	public PlateGravitySnapshot(final PlateGravity gravity) {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " PlateGravitySnapshot class");
		this.fallingPlatesSnapshot = gravity.getFallingPlates().getSnapshot();
	}
	
	/**
	 * @return the fallingPlatesSnapshot
	 */
	public final List<PlateSnapshot> getFallingPlatesSnapshot() {
		LogService.printTrace(this.getClass(), "list<PlateSnapshot> Method"
				+ " getFallingPlatesSnapshot is called.");
		return fallingPlatesSnapshot;
	}

}
