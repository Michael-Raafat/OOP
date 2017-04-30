
package models.player;

import java.awt.Point;
import java.util.Iterator;

import controllers.NodeService;
import javafx.scene.paint.Color;
import logs.LogService;
import models.plates.Plate;
import models.plates.PlateSnapshot;
import models.plates.collection.PlateCollection;
import models.plates.collection.PlateCollectionImp;
import models.plates.generator.PlatesPool;

/**
 * Implementation of the player hand.
 * @author Amr
 *
 */
public class PlayerHandImp implements PlayerHand {
	
	/**
	 * Epsilon for float comparison.
	 */
	private static final double EPSILON = 0.0001;
	
	/**
	 * Static constant for the half width of the hand.
	 */
	private static final int STARTING_WIDTH = 60;
	
	/**
	 * Static constant for the half height of the hand.
	 */
	private static final int STARTING_HEIGHT = 20;
	
	/**
	 * The maximum number of plates in the hand.
	 */
	private static final int MAX_NUMBER_OF_PLATES_IN_HAND = 30;
	
	/**
	 * This value when added to score indicates that the player loses.
	 */
	private static final int INVALID_INCREASE_SCORE = -100;
	
	/**
	 * The number of successive plates of same color
	 * to calculate a point.
	 */
	private static final int SUCCESSIVE_NUMBER_OF_PLATES = 3;
	
	/**
	 * velocity of player motion.
	 */
	private static final int SCORE_INCREASE_VALUE = 1;

	/**
	 * reference to the player.
	 */
	private Player player;
	
	/**
	 * The plate pool.
	 */
	private PlatesPool pool;
	
	/**
	 * The graphic service.
	 */
	private NodeService graphics;

	/**
	 * maximum height reached.
	 */
	private double maximumHeightReached;
	
	/**
	 * minimum height reached.
	 */
	private double minimumHeightReached;
	
	/**
	 * maximum width reached.
	 */
	private double maximumWidthReached;
	
	/**
	 * minimum height reached.
	 */
	private double minimumWidthReached;
	
	/**
	 * The collection of plates.
	 */
	private PlateCollection plates;
	
	/**
	 * the center of the hand.
	 */
	private Point center;
	
	/**
	 * Constructor for the player hand.
	 * @param owner
	 * The player whose hand is this.
	 * @param plPool
	 * The pool to return the plates to.
	 * @param service
	 * The graphic service to unregister nodes from.
	 * @param handCenter
	 * The center of the hand.
	 */
	public PlayerHandImp(final Player owner,
			final PlatesPool plPool, final NodeService service,
			final Point handCenter) {
		LogService.printTrace(this.getClass(), "Construction of PlayerHandImp"
				+ " class that implements PlayerHand interface.");
		this.player = owner;
		this.plates = new PlateCollectionImp();
		this.graphics = service;
		this.pool = plPool;
		this.center = handCenter;
	}

	@Override
	public final boolean isPlateIntersecting(final Plate newPlate) {
		LogService.printTrace(this.getClass(), "boolean Method"
				+ " isPlateIntersecting(Plate) is called.");
		if (plates.size() == 0) {
			setMinMaxwidthHeight(center, STARTING_WIDTH, STARTING_HEIGHT);
			if (newPlate.getCenter().y < maximumHeightReached
				&& newPlate.getCenter().y > minimumHeightReached
				&& newPlate.getCenter().x < maximumWidthReached
				&& newPlate.getCenter().x > minimumWidthReached) {
				LogService.printInfo(this.getClass(), "boolean Method"
					+ " isPlateIntersecting(Plate) is called returns true.");
				return true;
			}
			LogService.printInfo(this.getClass(), "boolean Method"
					+ " isPlateIntersecting(Plate) is called.returns false.");
			return false;
		} else {
			LogService.printInfo(this.getClass(), "boolean Method"
				+ " isPlateIntersecting(Plate) is called" 
				+ " returns result of intersection");
			return newPlate.getShape().getBoundsInParent().intersects(
				plates.get(plates.size() - 1).getShape().getBoundsInParent());
		}

	}

	@Override
	public final void attachPlate(final Plate newPlate) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " attachPlate(Plate) is called.");
		plates.add(newPlate);
		newPlate.setCenter(new Point(center.x,
				(int) minimumHeightReached - (newPlate.getHeight() / 2)));
		setMinMaxwidthHeight(newPlate.getCenter(), newPlate.getWidth(),
				newPlate.getHeight());
		LogService.printInfo(this.getClass(), "void Method"
				+ " attachPlate(Plate) is called.");
		if (plates.size() > MAX_NUMBER_OF_PLATES_IN_HAND) {
			this.player.increaseScoreByValue(INVALID_INCREASE_SCORE);
			LogService.printInfo(this.getClass(), "public void Method"
					+ " attachPlate is called max number of plates caught.");
		}
	}

	/**
	 * checks if the last three plates updates the score.
	 * @return true if score should be updated else false.
	 */
	private boolean checkScoreUpdate() {
		LogService.printTrace(this.getClass(), "private boolean Method"
				+ " checkScoreUpdate is called.");
		if (plates.size() < SUCCESSIVE_NUMBER_OF_PLATES) {
			LogService.printInfo(this.getClass(), "private boolean Method"
					+ " checkScoreUpdate is called returns false.");
			return false;
		}
		Iterator<Plate> it = plates.iterator();
		Color newColor = it.next().getColor();
		for (int i = 0; i < SUCCESSIVE_NUMBER_OF_PLATES - 1; i++) {
			Color color = it.next().getColor();
			if (!(equalDouble(newColor.getRed(), color.getRed())
				&& equalDouble(newColor.getBlue(), color.getBlue())
				&& equalDouble(newColor.getGreen(), color.getGreen())
				&& equalDouble(newColor.getOpacity(), color.getOpacity()))) {
				LogService.printInfo(this.getClass(), "private boolean Method"
						+ " checkScoreUpdate is called returns false.");
				return false;
			}
		}
		LogService.printInfo(this.getClass(), "private boolean Method"
				+ " checkScoreUpdate is called returns true.");
		return true;
	}
	
	/**
	 * check if both double numbers are equal or not.
	 * @param d1 first value.
	 * @param d2 second value.
	 * @return true if their subtraction is less than EPSILON.
	 */
	private boolean equalDouble(final double d1, final double d2) {
		LogService.printTrace(this.getClass(), "private boolean Method"
				+ " equalDouble(double, double) is called.");
		return Math.abs(d2 - d1) <= EPSILON; 
	}
	
	@Override
	public final Player getPlayer() {
		LogService.printTrace(this.getClass(), "Player Method"
				+ " getPlayer is called.");
		return player;
	}

	@Override
	public final void setPlayer(final Player owner) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setPlayer(Player) is called.");
		this.player = owner;
	}
	
	/**
	 * Sets the minimum and maximum width for the hit box.
	 * @param point
	 * The coordinate of the center of the hit box.
	 * @param width
	 * The width of the hit box.
	 * @param height
	 * The height of the hit box.
	 */
	private void setMinMaxwidthHeight(final Point point,
			final int width, final int height) {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " setMinMaxWidthHeight(Point, width, hight) is called.");
		minimumWidthReached = point.x - width / 2;
		maximumWidthReached = point.x + width / 2;
		minimumHeightReached = point.y - height / 2;
		maximumHeightReached = point.y + height / 2;
	}
	
	@Override
	public final Point getCenter() {
		LogService.printTrace(this.getClass(), "Point Method"
				+ " getcenter is called.");
		return center;
	}
	
	@Override
	public final void moveCenterToXCoord(final int x) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " moveCenterToXCoord(center of hand) is called.");
		this.center.x = x;
		Iterator<Plate> it = plates.iterator();
		while (it.hasNext()) {
			Plate p = it.next();
			p.setCenter(new Point(center.x, p.getCenter().y));
		}
	}

	@Override
	public final void updateScore() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " updateScore is called.");
		if (checkScoreUpdate()) {
			for (int i = 0; i < SUCCESSIVE_NUMBER_OF_PLATES; i++) {
				Plate last = plates.get(plates.size() - 1);
				if (plates.size() > 1) {
					Plate before = plates.get(plates.size() - 2);
					setMinMaxwidthHeight(before.getCenter(),
							before.getWidth(), before.getHeight());
				} else {
					setMinMaxwidthHeight(center, STARTING_WIDTH,
							STARTING_HEIGHT);
				}
				pool.releasePlate(last);
				graphics.bufferUnregisterNode(last.getShape());
				plates.remove(plates.size() - 1);
			}
			this.player.increaseScoreByValue(SCORE_INCREASE_VALUE);
		}
	}

	@Override
	public final PlayerHandSnapshot getSnapshot() {
		LogService.printTrace(this.getClass(), "PlayerHandSnapshot Method"
				+ " getSnapshot is called.");
		return new PlayerHandSnapshot(plates);
	}

	@Override
	public final void useSnapshot(final PlayerHandSnapshot snap) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " useSnapdhot(PlayerHandSnapshot) is called.");
		plates = new PlateCollectionImp();
		if (snap.getListOfPlates().size() == 0) {
			setMinMaxwidthHeight(center, STARTING_WIDTH, STARTING_HEIGHT);
		} else {
			int index;
			Plate plate = null;
			for (PlateSnapshot snapshot : snap.getListOfPlates()) {
				index = pool.getTypeIndex(snapshot.getPlateClass());
				plate = pool.aquirePlate(index);
				if (plate != null) {
					plate.useSnapshot(snapshot);
					plates.add(plate);
					graphics.bufferRegisterNode(plate.getShape());
				}
			}
			if (plate != null) {
				setMinMaxwidthHeight(plate.getCenter(),
					plate.getWidth(), plate.getHeight());
			}
		}
	}

	@Override
	public final void close() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " close is called.");
		this.center = null;
		this.graphics = null;
		this.player = null;
		for (Plate plate : plates) {
			pool.releasePlate(plate);
		}
		plates = null;
		pool = null;
	}

}
