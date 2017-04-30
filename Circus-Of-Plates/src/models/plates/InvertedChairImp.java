
package models.plates;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import logs.LogService;

/**
 * A kind of plates.
 * @author Michael.
 *
 */
public class InvertedChairImp extends Plate {
	
	/**
	 * the width of the plate.
	 */
	private static final int WIDTH = 76;
	
	/**
	 * the height of the plate.
	 */
	private static final int HEIGHT = 20;
	
	/**
	 * constructor of shape.
	 */
	public InvertedChairImp() {
		this(Color.BLACK);
		LogService.printTrace(this.getClass(),
				"Construction of InvertedChair Class"
				+ " inherited from Plate Abstract class");
	}
	
	/**
	 * constructor using color.
	 * @param color
	 * fill color of shape
	 */
	public InvertedChairImp(final Color color) {
		super(WIDTH, HEIGHT);
		this.fillColor = color;
		LogService.printTrace(this.getClass(),
				"Construction of InvertedChair Class"
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
				0.0,0.0, 10.0, 0.0,
			    10.0, 10.0,	66.0, 10.0,
				66.0, 0.0, 76.0, 0.0,
				76.0, 20.0,	0.0, 20.0});
		this.shape = polygon;
		LogService.printInfo(this.getClass(),
				"Inverted Chair Shape is Created.");
	}
	
}
