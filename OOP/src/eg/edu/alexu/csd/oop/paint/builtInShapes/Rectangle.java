package eg.edu.alexu.csd.oop.paint.builtInShapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
/**
 * Rectangle class.
 * @author Mico
 */
public class Rectangle extends GeoShapes {
	/**
	 * new Rectangle.
	 */
	public Rectangle() {
		this(Config.getDefaultBorderColor());
	}
	/**
	 * new Rectangle.
	 * @param border
	 * color of Rectangle's border
	 */
	public Rectangle(final Color border) {
		this(border, Config.getDefaultThickness());
	}
	/**
	 * new Rectangle.
	 * @param border
	 * color of Rectangle's border.
	 * @param thick
	 * thickness of border.
	 */
	public Rectangle(final Color border, final int thick) {
		this(border, Config.getDefaultFillColor(), thick);
	}
	/**
	 * initialize new Rectangle.
	 * @param border
	 * color of Rectangle's border.
	 * @param thick
	 * thickness of border.
	 * @param fill
	 * fill color of Rectangle.
	 */
	public Rectangle(final Color border, final Color fill, final int thick) {
		this.numberOfPointsNeeded = 2;
		this.points = new ArrayList<Point>();
		this.center = new Point();
		this.width = 0;
		this.height = 0;
		this.borderColor = border;
		this.fillColor = fill;
		this.thickness = thick;
	}

	@Override
	public void drawShape(final Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		helpDraw(g, points.get(1));
	}

	@Override
	public void testDrawShape(final Graphics2D g, final Point temp) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		if (points.size() == 1) {
			helpDraw(g, temp);
		} else if (points.size() == 2) {
			drawShape(g);
		}
	}

	public void helpDraw(final Graphics2D g, final Point temp) {
		g.setColor(fillColor);
		g.fillRect(points.get(0).x - Math.abs(temp.x - points.get(0).x)  ,
				points.get(0).y - Math.abs(temp.y - points.get(0).y) ,
				Math.abs(temp.x - points.get(0).x)* 2,
				Math.abs(temp.y - points.get(0).y)* 2);
		g.setColor(borderColor);
		g.drawRect(points.get(0).x - Math.abs(temp.x - points.get(0).x)  ,
			points.get(0).y - Math.abs(temp.y - points.get(0).y) ,
			Math.abs(temp.x - points.get(0).x)* 2,
			Math.abs(temp.y - points.get(0).y)* 2);
	}
	@Override
	public void enterPoint(Point p) {
		// TODO Auto-generated method stub
		if (getPointsListSize() >= numberOfPointsNeeded) {
			throw new RuntimeException();
		} else {
			points.add(p);
			if (points.size() == numberOfPointsNeeded) {
				center.x = points.get(0).x;
				center.y = points.get(0).y;
				width = Math.abs(points.get(0).x - points.get(1).x) * 2;
				height = Math.abs(points.get(0).y - points.get(1).y) * 2;
			}
		}
	}

	@Override
	public void calculateCenter() {
		// TODO Auto-generated method stub
		center = points.get(0);
		width = Math.abs(points.get(0).x - points.get(1).x) * 2;
		height = Math.abs(points.get(0).y - points.get(1).y) * 2;
	}

}
