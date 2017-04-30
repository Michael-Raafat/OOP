
package models.plates.movers;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import controllers.NodeService;
import game.control.difficulties.Difficulty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import logs.LogService;
import models.dto.Speed;
import models.dto.SpeedRange;
import models.graphics.GraphicalGameObject;
import models.plates.Plate;
import models.plates.PlateSnapshot;
import models.plates.collection.PlateCollection;
import models.plates.collection.PlateCollectionImp;
import models.plates.generator.PlatesGenerator;
import models.plates.generator.PlatesPool;

/**
 * Implementation of the plate track.
 * @author Amr
 *
 */
public class PlateTrackImp extends GraphicalGameObject implements PlateTrack {
	
	/**
	 * Random number generator.
	 */
	private static final Random RAND = new Random();
	
	/**
	 * The gravity that will be responsible of the plate after it
	 * ends the track.
	 */
	private PlateGravity gravity;
	
	/**
	 * The plate generator.
	 */
	private PlatesGenerator generator;
	
	/**
	 * The delay between spawns.
	 */
	private int delay;
	
	/**
	 * The last spawn time.
	 */
	private long lastSpawnTime;
	
	/**
	 * The list of plates.
	 */
	private PlateCollection plates;
	
	/**
	 * Speed range.
	 */
	private SpeedRange allowedSpeed;
	
	/**
	 * Start Point the track.
	 */
	private Point trackStart;
	
	/**
	 * end Point the track.
	 */
	private Point trackEnd;
	
	/**
	 * Track direction.
	 */
	private int trackDirection;
	
	/**
	 * The node service.
	 */
	private NodeService graphicService;
	
	/**
	 * The pool of plates.
	 */
	private PlatesPool pool;
	
	/**
	 * Constructor of the track.
	 * @param start
	 * The start point of the track.
	 * @param end
	 * The end point of the track.
	 * @param service
	 * The node service for registering and unregistering nodes
	 * @param platePool
	 * reference to the plates pool
	 * @param generator
	 * reference to the generator
	 * @param diff
	 * The difficulty of the game.
	 * @param plateG
	 * The gravity that will be responsible of plates.
	 */
	public PlateTrackImp(final Point start,
			final Point end, final Difficulty diff,
			final PlateGravity plateG, final NodeService service,
			final PlatesGenerator generator,
			final PlatesPool platePool) {
		super(setUpNode(start, end));
		LogService.printTrace(this.getClass(), "Construction of PlateTrackImp"
				+ " class that implements PlateTrack interface.");
		this.graphicService = service;
		this.gravity = plateG;
		this.generator = generator;
		this.plates = new PlateCollectionImp();
		this.lastSpawnTime = System.currentTimeMillis();
		this.delay = diff.getSpawnWaitTime();
		this.allowedSpeed = diff.getSpeedRange();
		this.trackStart = start;
		this.trackEnd = end;
		this.pool = platePool;
		this.trackDirection = (end.x - start.x) / Math.abs(end.x - start.x); 
	}

	@Override
	public final void update() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " update is called.");
		if (lastSpawnTime + delay < System.currentTimeMillis()) {
			try {
			Plate plate = generator.getNewPlate();
			lastSpawnTime = System.currentTimeMillis();
			setNewPlateSpeed(plate);
			graphicService.bufferRegisterNode(plate.getShape());
			plates.add(plate);
			} catch (NullPointerException e) {
				LogService.printError(this.getClass(),
						"out of plates! waiting for pool to get more");
			}
		}
		for (Plate current : plates) {
			current.update();
			int distanceXToEnd = (int) (current.getCenter().x - trackDirection
					* current.getShape().getLayoutBounds().getWidth() / 2);
			distanceXToEnd -= trackEnd.x;
			if ((trackDirection > 0 && distanceXToEnd > 0)
				|| (trackDirection < 0 && distanceXToEnd < 0)) {
				plates.remove(current);
				gravity.addPlate(current);
			}
		}
	}
	
	/**
	 * Sets the new plate settings before adding it to the list.
	 * @param plate
	 * The plate to set up its properties.
	 */
	private void setNewPlateSpeed(final Plate plate) {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " setNewPlateSpeed(Plate) is called.");
		int plateLength = (int) plate.getShape().getLayoutBounds().getHeight();
		plate.setCenter(new Point(
				trackStart.x, trackStart.y - plateLength / 2));
		float xVelocity = RAND.nextInt(
				(int) (allowedSpeed.getMaxSpeed().getxSpeed()
				- allowedSpeed.getMinSpeed().getxSpeed()))
				+ allowedSpeed.getMinSpeed().getxSpeed();
		plate.setVelocity(new Speed(xVelocity * trackDirection, 0));
	}
	
	/**
	 * Sets the shape of the line considering the start and end point.
	 * @param startPoint
	 * The start point of the track.
	 * @param endPoint
	 * The end point of the track.
	 * @return
	 * The graphical node representation of the track.
	 */
	private static Node setUpNode(final Point startPoint,
			final Point endPoint) {
		Line line = new Line();
		line.setStartX(startPoint.x);
		line.setEndX(endPoint.x);
		line.setStartY(startPoint.y);
		line.setEndY(endPoint.y);
		line.setStrokeWidth(1);
		line.setStroke(Color.BLACK);
		return line;
	}

	@Override
	public final PlateTrackSnapshot getSnapshot() {
		LogService.printTrace(this.getClass(), "PlateTrackSnapshot Method"
				+ " getSnapshot is called.");
		return new PlateTrackSnapshot(this.trackStart,
				this.trackEnd,
				this.plates,
				(int) (System.currentTimeMillis() - lastSpawnTime));
	}

	@Override
	public final void useSnapshot(final PlateTrackSnapshot snap) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " useSnapshot(PlateTrackSnapshot) is called.");
		List<PlateSnapshot> list = snap.getPlates();
		plates = new PlateCollectionImp();
		int index;
		for (PlateSnapshot snapshot : list) {
			index = pool.getTypeIndex(snapshot.getPlateClass());
			Plate plate = pool.aquirePlate(index);
			if (plate != null) {
				plate.useSnapshot(snapshot);
				plates.add(plate);
				graphicService.bufferRegisterNode(plate.getShape());
			}
		}
		this.lastSpawnTime = System.currentTimeMillis()
				- snap.getTimeLastSpawn();
		trackStart = snap.getStartPoint();
		this.trackEnd = snap.getEndPoint();
		this.trackDirection = (trackEnd.x - trackStart.x)
				/ Math.abs(trackEnd.x - trackStart.x);
		Line line = (Line) this.getNode();
		line.setStartX(trackStart.x);
		line.setEndX(trackEnd.x);
		line.setStartY(trackStart.y);
		line.setEndY(trackEnd.y);
		line.setStrokeWidth(1);
		line.setStroke(Color.BLACK);
	}

	@Override
	public final void close() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " close is called.");
		this.allowedSpeed = null;
		this.generator = null;
		this.graphicService = null;
		for (Plate plate : plates) {
			pool.releasePlate(plate);
		}
		this.plates = null;
		this.pool = null;
		this.gravity = null;
		this.trackEnd = null;
		this.trackStart = null;
	}
	
}
