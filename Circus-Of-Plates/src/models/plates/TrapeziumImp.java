
package models.plates;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import logs.LogService;

/**
 * A kind of plates.
 * @author Michael.
 *
 */
public class TrapeziumImp extends Plate {

	/**
	 * the width of the plate.
	 */
	private static final int WIDTH = 76;
	
	/**
	 * the height of the plate.
	 */
	private static final int HEIGHT = 15;

	/**
	 * constructor of shape.
	 */
	public TrapeziumImp() {
		this(Color.BLACK);
		LogService.printTrace(this.getClass(),
				"Construction of Trapezium Class"
				+ " inherited from Plate Abstract class");
	}
	
	/**
	 * constructor using color.
	 * @param color
	 * fill color of shape
	 */
	public TrapeziumImp(final Color color) {
		super(WIDTH, HEIGHT);
		this.fillColor = color;
		LogService.printTrace(this.getClass(),
				"Construction of TrapeziumImp Class"
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
				10.0,0.0, 66.0, 0.0,
			    76.0, 15.0, 0.0, 15.0});
		this.shape = polygon;
		LogService.printInfo(this.getClass(),
				"Trapezium Shape is Created.");
	}
	
}
