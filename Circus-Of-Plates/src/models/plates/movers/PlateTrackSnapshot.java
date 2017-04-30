
package models.plates.movers;

import java.awt.Point;
import java.util.List;

import logs.LogService;
import models.plates.PlateSnapshot;
import models.plates.collection.PlateCollection;

/**
 * The snapshot of plate track.
 * @author Amr
 *
 */
public class PlateTrackSnapshot {
	
	/**
	 * list of plate snapshots.
	 */
	private List<PlateSnapshot> plates;
	
	/**
	 * The points of start and end.
	 */
	private Point startPoint, endPoint;
	
	/**
	 * The time passed since the last spawn.
	 */
	private int timeLastSpawn;
	
	/**
	 * Constructor.
	 * @param startPoint
	 * The start point.
	 * @param endPoint
	 * The end point.
	 * @param collection
	 * The plate collection.
	 * @param timeLastSpawn
	 * The time passed since the last spawn.
	 */
	public PlateTrackSnapshot(final Point startPoint, final Point endPoint,
			final PlateCollection collection, final int timeLastSpawn) {
		LogService.printTrace(this.getClass(), "Construction of "
				+ "PlateTrackSnapshot"
				+ " class");
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.plates = collection.getSnapshot();
		this.timeLastSpawn = timeLastSpawn;
	}
	
	/**
	 * @return the plates
	 */
	public final List<PlateSnapshot> getPlates() {
		LogService.printTrace(this.getClass(), "list<PlateSnapshot> Method"
				+ " getPlates is called.");
		return plates;
	}
	
	/**
	 * @return the startPoint
	 */
	public final Point getStartPoint() {
		LogService.printTrace(this.getClass(), "Point Method"
				+ " getStartPoint is called.");
		return startPoint;
	}
	
	/**
	 * @return the endPoint
	 */
	public final Point getEndPoint() {
		LogService.printTrace(this.getClass(), "Point Method"
				+ " getEndPoint is called.");
		return endPoint;
	}
	
	/**
	 * @return the timeLastSpawn
	 */
	public final int getTimeLastSpawn() {
		LogService.printTrace(this.getClass(), "int Method"
				+ " getTimeLastSpawn is called.");
		return timeLastSpawn;
	}
	
}
