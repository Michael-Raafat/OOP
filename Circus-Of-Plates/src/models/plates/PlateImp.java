
package models.plates;

import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import logs.LogService;

/**
 * plateImp class.
 * responsible for create a plate shape.
 * @author Michael.
 *
 */
public class PlateImp extends Plate {

	/**
	 * the width of the plate.
	 */
	private static final int WIDTH = 76;
	
	/**
	 * the height of the plate.
	 */
	private static final int HEIGHT = 20;
	
	/**
	 * constructor of plate.
	 */
	public PlateImp() {
		this(Color.BLACK);
		LogService.printTrace(this.getClass(), "Construction of PlateImp Class"
				+ " inherited from Plate Abstract class");
	}
	
	/**
	 * constructor using color.
	 * @param fillColor
	 * fill color of shape
	 */
	public PlateImp(final Color fillColor) {
		super(WIDTH, HEIGHT);
		this.fillColor = fillColor;
		this.center = new Point();
		LogService.printTrace(this.getClass(),
				"Construction of PlateImp Class"
				+ " inherited from Plate Abstract class"
				+ " .. it contians ( Color ) as parameter");
		this.createShape();
	}
	
	@Override
	public final void createShape() {
		LogService.printTrace(this.getClass(),
				"void Method createShape is called");
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[]{
				0.0,0.0, 76.0, 0.0,
			    76.0, 10.0, 55.0, 10.0,
				55.0, 20.0, 21.0, 20.0,
				21.0, 10.0, 0.0, 10.0});
		this.shape = polygon;
		LogService.printInfo(this.getClass(),
				"Plate Shape is Created.");
	}

}
