
package models.plates;

import java.awt.Point;

import javafx.scene.paint.Color;
import logs.LogService;
import models.dto.Speed;

/**
 * Snapshot of any plate.
 * @author Amr
 *
 */
public class PlateSnapshot {
	
	/**
	 * The center of the plate.
	 */
	private Point center;
	
	/**
	 * The speed of the plate.
	 */
	private Speed speed;
	
	/**
	 * Red component of color of plate.
	 */
	private double red;
	
	/**
	 * Green component of color of plate.
	 */
	private double green;
	
	/**
	 * Blue component of color of plate.
	 */
	private double blue;
	
	/**
	 * Opacity component of color of plate.
	 */
	private double opacity;
	
	/**
	 * The class of the plate.
	 */
	private String plateClass;
	
	/**
	 * Constructor.
	 * @param plate
	 * The plate to take a snapshot of.
	 */
	public PlateSnapshot(final Plate plate) {
		LogService.printTrace(this.getClass(), "Construction of PlateSnapshot"
				+ " class");
		this.speed = plate.getVelocity();
		this.center = plate.getCenter();
		this.red = plate.getColor().getRed();
		this.green = plate.getColor().getGreen();
		this.blue = plate.getColor().getBlue();
		this.opacity = plate.getColor().getOpacity();
		this.plateClass = plate.getClass().getName();
	}
	
	/**
	 * @return the center
	 */
	public final Point getCenter() {
		LogService.printTrace(this.getClass(),
				"Point Method getCenter is called");
		return center;
	}
	
	/**
	 * @return the speed
	 */
	public final Speed getSpeed() {
		LogService.printTrace(this.getClass(),
				"Speed Method getSpeed is called");
		return speed;
	}
	
	/**
	 * @return the color
	 */
	public final Color getColor() {
		LogService.printTrace(this.getClass(),
				"Color Method getColor is called");
		return new Color(red, green, blue, opacity);
	}
	
	/**
	 * @return the plateClass
	 */
	public final String getPlateClass() {
		LogService.printTrace(this.getClass(), "String Method"
				+ " getPlateClass is called");
		return plateClass;
	}

}
