
package models.plates.movers;

import java.util.List;
import java.util.Random;

import controllers.NodeService;
import game.control.difficulties.Difficulty;
import logs.LogService;
import models.dto.Speed;
import models.dto.SpeedRange;
import models.plates.Plate;
import models.plates.PlateSnapshot;
import models.plates.collection.PlateCollection;
import models.plates.collection.PlateCollectionImp;
import models.plates.generator.PlatesPool;

/**
 * Class responsible of dropping the plates.
 * @author Amr
 *
 */
public class PlateGravityImp implements PlateGravity {

	/**
	 * The collection of the falling plates.
	 */
	private PlateCollection fallingPlates;
	
	/**
	 * The difficulty controller of the game.
	 */
	private Difficulty difficulty;
	
	/**
	 * Speed range of falling plates.
	 */
	private SpeedRange fallSpeedRange;
	
	/**
	 * Random number generator.
	 */
	private Random rand;
	
	/**
	 * The y coordinate of the floor.
	 */
	private int floor;
	
	/**
	 * The max x distance of the gravity.
	 */
	private int maxX;
	
	/**
	 * The service to add/remove nodes from scene.
	 */
	private NodeService graphicService;
	
	/**
	 * Pool that the plates were taken from.
	 */
	private PlatesPool pool;
	
	/**
	 * The Constructor.
	 * @param diff
	 * The difficulty of the game.
	 * @param service
	 * The node service for registering and unregistering nodes
	 * @param poolParam
	 * reference to the plates pool
	 * @param yFloor
	 * The y coordinate of the bottom of the window.
	 * @param xBound
	 * The x coordinate of the most left of the window.
	 */
	public PlateGravityImp(final Difficulty diff, final int yFloor,
			final NodeService service, final PlatesPool poolParam,
			final int xBound) {
		LogService.printTrace(this.getClass(), "Construction of PlateGravityImp"
				+ " that implements PlateGravity Interface");
		this.difficulty = diff;
		this.fallingPlates = new PlateCollectionImp();
		this.fallSpeedRange = diff.getSpeedRange();
		this.rand = new Random();
		this.floor = yFloor;
		this.graphicService = service;
		this.pool = poolParam;
		this.maxX = xBound;
	}
	
	@Override
	public final void update() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " update is called.");
		for (Plate currentPlate : fallingPlates) {
			currentPlate.update();
			currentPlate.setVelocity(
					difficulty.updateFallingSpeed(
							currentPlate.getVelocity()));
			if (checkOutOfBounds(currentPlate)) {
				fallingPlates.remove(currentPlate);
				pool.releasePlate(currentPlate);
				graphicService.bufferUnregisterNode(currentPlate.getShape());
			}
		}
	}
	
	/**
	 * Checks if a plate is out of bounds.
	 * @param currentPlate
	 * The plate to check out of bounds.
	 * @return
	 * True if out of bounds, false otherwise.
	 */
	private boolean checkOutOfBounds(final Plate currentPlate) {
		LogService.printTrace(this.getClass(), "private boolean Method"
				+ " checkOutOfBounds(Plate) is called.");
		if (currentPlate.getCenter().y
					- currentPlate.getHeight() / 2
					> floor) {
			return true;
		}
		return (currentPlate.getCenter().x + currentPlate.getWidth() / 2 < 0
			|| currentPlate.getCenter().x - currentPlate.getWidth()	/ 2 > maxX);
	}
	@Override
	public final void addPlate(final Plate plate) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " addPlate(Plate) is called.");
		setPlateFallSpeed(plate);
		fallingPlates.add(plate);
	}

	@Override
	public final PlateCollection getFallingPlates() {
		LogService.printTrace(this.getClass(), "PlateCollection Method"
				+ " getFallingPlates is called.");
		return fallingPlates;
	}
	
	/**
	 * Function that sets the plate velocity when it leaves the track.
	 * @param plate
	 * The plate that will leave the track.
	 */
	private void setPlateFallSpeed(final Plate plate) {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " setPlateFallSpeed(Plate) is called.");
		float yVelocity = rand.nextInt(
				(int) (fallSpeedRange.getMaxSpeed().getySpeed()
			- fallSpeedRange.getMinSpeed().getySpeed()))
				+ fallSpeedRange.getMinSpeed().getySpeed();
		plate.setVelocity(
				new Speed(plate.getVelocity().getxSpeed(), yVelocity));
	}
	
	@Override
	public final PlateGravitySnapshot createSnapshot() {
		LogService.printTrace(this.getClass(), "PlateGravitySnapshot Method"
				+ " createSnapshot is called.");
		return new PlateGravitySnapshot(this);
	}
	
	@Override
	public final void useSnapshot(final PlateGravitySnapshot snapshot) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " useSnapShot(PlateGravitySnapshot) is called.");
		fallingPlates = new PlateCollectionImp();
		List<PlateSnapshot> list = snapshot.getFallingPlatesSnapshot();
		int index;
		for (int i = 0; i < list.size(); i++) {
			PlateSnapshot snap = list.get(i);
			index = pool.getTypeIndex(snap.getPlateClass());
			Plate plate = pool.aquirePlate(index);
			if (plate != null) {
				plate.useSnapshot(snap);
				fallingPlates.add(plate);
				graphicService.bufferRegisterNode(plate.getShape());
			}
		}
	}
	
	@Override
	public final void close() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " close is called.");
		for (int i = 0; i < fallingPlates.size(); i++) {
			pool.releasePlate(fallingPlates.get(i));
		}
		fallingPlates = null;
		pool = null;
		graphicService = null;
		fallSpeedRange = null;
	}

}
