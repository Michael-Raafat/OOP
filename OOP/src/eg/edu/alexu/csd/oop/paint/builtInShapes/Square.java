package eg.edu.alexu.csd.oop.paint.builtInShapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;

public class Square extends GeoShapes {
	/**
	 * new Square.
	 */
	public Square() {
		this(Config.getDefaultBorderColor());
	}
	/**
	 * new Square.
	 * @param border
	 * color of Square's border
	 */
	public Square(Color border) {
		this(border, Config.getDefaultThickness());
	}
	/**
	 * new Square.
	 * @param border
	 * color of Square's border.
	 * @param thick
	 * thickness of border.
	 */
	public Square(Color border, int thick) {
		this(border, Config.getDefaultFillColor(), thick);
	}
	/**
	 * initialize new Square.
	 * @param border
	 * color of Square's border.
	 * @param thick
	 * thickness of border.
	 * @param fill
	 * fill color of Square.
	 */
	public Square(Color border, Color fill, int thick) {
		this.numberOfPointsNeeded = 2;
		this.points = new ArrayList<Point>();
		this.center = new Point();
		this.width = 0;
		this.height = 0;
		this.rotationAngle = 0;
		this.borderColor = border;
		this.fillColor = fill;
		this.thickness = thick;
	}

	@Override
	public void drawShape(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		helpDraw(g, points.get(1));
	}

	@Override
	public void testDrawShape(Graphics2D g, Point temp) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		if (points.size() == 1) {
			helpDraw(g,temp);
		} else if (points.size() == 2) {
			drawShape(g);
		}
	}

	public void helpDraw(Graphics2D g, Point temp) {
		if (Math.abs(temp.x - points.get(0).x) >
		Math.abs(temp.y - points.get(0).y)) {
			g.setColor(fillColor);
			g.fillRect(points.get(0).x - Math.abs(temp.x - points.get(0).x)  ,
					points.get(0).y - Math.abs(temp.x - points.get(0).x) ,
					Math.abs(temp.x - points.get(0).x)* 2,
					Math.abs(temp.x - points.get(0).x)* 2);
			g.setColor(borderColor);
			g.drawRect(points.get(0).x - Math.abs(temp.x - points.get(0).x)  ,
					points.get(0).y - Math.abs(temp.x - points.get(0).x) ,
					Math.abs(temp.x - points.get(0).x)* 2,
					Math.abs(temp.x - points.get(0).x)* 2);
		} else {
			g.setColor(fillColor);
			g.drawRect(points.get(0).x - Math.abs(temp.y - points.get(0).y)  ,
					points.get(0).y - Math.abs(temp.y - points.get(0).y) ,
					Math.abs(temp.y - points.get(0).y)* 2,
					Math.abs(temp.y - points.get(0).y)* 2);
			g.setColor(borderColor);
			g.drawRect(points.get(0).x - Math.abs(temp.y - points.get(0).y)  ,
					points.get(0).y - Math.abs(temp.y - points.get(0).y) ,
					Math.abs(temp.y - points.get(0).y)* 2,
					Math.abs(temp.y - points.get(0).y)* 2);
		}
    }
	@Override
	public void enterPoint(Point p) {
		// TODO Auto-generated method stub
		if (getPointsListSize() >= numberOfPointsNeeded) {
			throw new RuntimeException();
		} else {
			points.add(p);
			if (points.size() == numberOfPointsNeeded) {
				calculateCenter();
			}
		}
	}

	@Override
	public void calculateCenter() {
		// TODO Auto-generated method stub
		center = points.get(0);
		if (Math.abs(points.get(1).x - points.get(0).x) >
		Math.abs(points.get(1).y - points.get(0).y)) {
			width = Math.abs(points.get(1).x - points.get(0).x) * 2;
			height = Math.abs(points.get(1).x - points.get(0).x) * 2;
		} else {
			width = Math.abs(points.get(1).y - points.get(0).y) * 2;
			height = Math.abs(points.get(1).y - points.get(0).y) * 2;
		}
	}

}
