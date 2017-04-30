
package models.plates;

import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import logs.LogService;
import models.dto.Speed;
import models.graphics.GameObject;

/**
 * The main plate abstract class.
 * @author Michael.
 *
 */
public abstract class Plate implements GameObject {
	
	/**
	 * color of plate.
	 */
	protected Color fillColor;
	
	/**
	 * center of plate.
	 */
	protected Point center;
	
	/**
	 * shape of javafx.
	 */
	protected Shape shape;
	
	/**
	 * speed of plate.
	 */
	private Speed speed;
	
	/**
	 * width of plate.
	 */
	private int width;
	
	/**
	 * height of plate.
	 */
	private int height;
	
	/**
	 * constructor of super plate.
	 * @param width width given to plate.
	 * @param height height given to plate.
	 */
	public Plate(final int width, int height) {
		this.height = height;
		this.width = width;
		LogService.printTrace(this.getClass(), "Construction of Plate Abstract"
				+ " class");
	}
	
	/**
	 * update plate's position.
	 */
	public final void update() {
		LogService.printTrace(this.getClass(), "void Method update is called");
		center.x += (int) speed.getxSpeed();
		center.y += (int) speed.getySpeed();
		this.shape.relocate(
				center.x - this.getWidth() / 2,
				center.y - this.getHeight() / 2);
	}
	/**.
	 * Get a reference of Java FX shape object.
	 * @return return a reference to its javaFx object as a shape.
	 */
	public final Shape getShape() {
		LogService.printTrace(this.getClass(),
				"Shape Method getShape is called");
		return this.shape;
	}

	/**.
	 * Update the center of the current shape by the given point
	 * @param point the point object to update the shape center with.
	 */
	public final void setCenter(final Point point) {
		LogService.printTrace(this.getClass(),
				"void Method setCenter(Point) is called");
		center = point;
		this.shape.relocate(
				point.x - this.getWidth() / 2,
				point.y - this.getHeight() / 2);
	}
	
	/**.
	 * Set the color of the shape by the given color
	 * @param color the color to update the shape fill color with.
	 */
	public final void setColor(final Color color) {
		LogService.printTrace(this.getClass(),
				"void Method setColor(Color) is called");
		this.fillColor = color;
		this.shape.setFill(color);
	}
	
	/**.
	 * Get the current center for the shape
	 * @return the current center for the shape
	 */
	public final Point getCenter() {
		LogService.printTrace(this.getClass(),
				"Point Method getCenter is called");
		return this.center;
	}
    
	/**
     * @return the fillColor
     */
    public final Color getColor() {
		LogService.printTrace(this.getClass(),
				"Color Method getColor is called");
        return this.fillColor;
    }
    
    /**
     * reset plate to original state.
     */
    public final void reset() {
		LogService.printTrace(this.getClass(), "void Method reset is called");
    	fillColor = Color.BLACK;
    	this.shape.setFill(Color.BLACK);
    	center = new Point();
    	this.shape.setTranslateX(0);
    	this.shape.setTranslateY(0);
    }
    
    /**
     * getter for velocity.
     * @return point of velocityX and velocityY
     */
    public final Speed getVelocity() {
    	LogService.printTrace(this.getClass(),
    			"Speed Method getVelocity is called");
    	return this.speed;
    }
    
    /**
     * setter for velocity.
     * @param speed point of x and y.
     */
    public final void setVelocity(final Speed speed) {
		LogService.printTrace(this.getClass(), "void Method setVelocity(Speed)"
				+ " is called");
    	this.speed = speed;
    }
    
    /**
     * Creates a snapshot of the plate.
     * @return
     * A snapshot of this plate.
     */
    public final PlateSnapshot createSnapshot() {
    	LogService.printTrace(this.getClass(),
    			"PlateSnapshot Method createSnapshot" + " is called");
    	return new PlateSnapshot(this);
    }
    
    /**
     * Rolls back the plate to a certain snapshot.
     * @param snap
     * The snapshot to roll back to.
     */
    public final void useSnapshot(final PlateSnapshot snap) {
		LogService.printTrace(this.getClass(), "void Method useSnapshot"
				+ "(PlateSnapshot) is called");
    	setVelocity(snap.getSpeed());
    	setColor(snap.getColor());
    	setCenter(snap.getCenter());
    }
    
    /**.
	 * Get the height of the shape
	 * @return the current height of the shape
	 */
	public final int getHeight() {
		LogService.printTrace(this.getClass(),
				"int Method getHeight is called");
		return this.height;
	}
	
	/**.
	 * Get the width of the shape
	 * @return the current width of the shape
	 */
	public final int getWidth() {
		LogService.printTrace(this.getClass(), "int Method getWidth is called");
		return this.width;
	}
	
	/**
	 * create shape.
	 */
	public abstract void createShape();
	
	@Override
	public final void close() {
		this.speed = null;
		this.center = null;
		this.fillColor = null;
		this.shape = null;
	}
	
}

