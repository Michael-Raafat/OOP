package eg.edu.alexu.csd.oop.paint.builtInShapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;

public class Triangle extends GeoShapes {
	/**
	 * new Triangle.
	 */
	public Triangle() {
		this(Config.getDefaultBorderColor());
	}
	/**
	 * new Triangle.
	 * @param border
	 * color of Triangle's border
	 */
	public Triangle(Color border) {
		this(border, Config.getDefaultThickness());
	}
	/**
	 * new Triangle.
	 * @param border
	 * color of Triangle's border.
	 * @param thick
	 * thickness of border.
	 */
	public Triangle(Color border, int thick) {
		this(border, Config.getDefaultFillColor(), thick);
	}
	/**
	 * initialize new Triangle.
	 * @param border
	 * color of Triangle's border.
	 * @param thick
	 * thickness of border.
	 * @param fill
	 * fill color of Triangle.
	 */
	public Triangle(Color border, Color fill, int thick) {
		this.numberOfPointsNeeded = 3;
		this.points = new ArrayList<Point>();
		this.center = new Point();
		this.width = 0;
		this.height = 0;
		this.borderColor = border;
		this.fillColor = fill;
		this.thickness = thick;
	}

	@Override
	public void drawShape(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		g.setColor(fillColor);
		int [] xPoints = {points.get(0).x,
				points.get(1).x,
				points.get(2).x
		};
		int [] yPoints = {points.get(0).y,
				points.get(1).y,
				points.get(2).y
		};
		g.fillPolygon(xPoints, yPoints, numberOfPointsNeeded);
		g.setColor(borderColor);
		g.drawLine(points.get(1).x, points.get(1).y, points.get(2).x, points.get(2).y);
		g.drawLine(points.get(0).x, points.get(0).y, points.get(2).x, points.get(2).y);
		g.drawLine(points.get(0).x, points.get(0).y,points.get(1).x, points.get(1).y);
	}

	@Override
	public void testDrawShape(Graphics2D g, Point temp) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		g.setColor(borderColor);
		if (points.size() == 1) {
			g.drawLine(points.get(0).x, points.get(0).y, temp.x, temp.y);
		} else if (points.size() == 2) {
			g.setColor(fillColor);
			int [] xPoints = {points.get(0).x,
					points.get(1).x,
					temp.x
			};
			int [] yPoints = {points.get(0).y,
					points.get(1).y,
					temp.y
			};
			g.fillPolygon(xPoints, yPoints, numberOfPointsNeeded);
			g.setColor(borderColor);
			g.drawLine(points.get(0).x, points.get(0).y,points.get(1).x, points.get(1).y);
			g.drawLine(points.get(1).x, points.get(1).y, temp.x, temp.y);
			g.drawLine(points.get(0).x, points.get(0).y, temp.x, temp.y);
		} else if (points.size() == 3) {
		    drawShape(g);
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
		width = Math.abs(points.get(0).x - points.get(1).x);
		center.x = (points.get(0).x + points.get(1).x) / 2;
		if (Math.abs(points.get(2).x - points.get(1).x) > width) {
			width = Math.abs(points.get(2).x - points.get(1).x);
			center.x = (points.get(2).x + points.get(1).x) / 2;
		}
		if (Math.abs(points.get(2).x - points.get(0).x) > width) {
			width = Math.abs(points.get(2).x - points.get(0).x);
			center.x = (points.get(0).x + points.get(2).x) / 2;
		}
		height = Math.abs(points.get(0).y - points.get(1).y);
		center.y = (points.get(0).y + points.get(1).y) / 2;
		if (Math.abs(points.get(2).y - points.get(1).y) > height) {
			height = Math.abs(points.get(2).y - points.get(1).y);
			center.y = (points.get(2).y + points.get(1).y) / 2;
		}
		if (Math.abs(points.get(2).y - points.get(0).y) > height) {
			height = Math.abs(points.get(2).y - points.get(0).y);
			center.y = (points.get(0).y + points.get(2).y) / 2;
		}
	}

}
