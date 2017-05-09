package eg.edu.alexu.csd.oop.paint.builtInShapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;

public class Line extends GeoShapes {
	/**
	 * new line.
	 */
	public Line() {
		this(Config.getDefaultBorderColor());
	}
	/**
	 * new line.
	 * @param border
	 * color of ellipse's border
	 */
	public Line(Color border) {
		this(border, Config.getDefaultThickness());
	}
	/**
	 * new line.
	 * @param border
	 * color of line's border.
	 * @param thick
	 * thickness of border.
	 */
	public Line(Color border, int thick) {
		this(border, Config.getDefaultFillColor(), thick);
	}
	/**
	 * initialize new line.
	 * @param border
	 * color of line's border.
	 * @param thick
	 * thickness of border.
	 * @param fill
	 * fill color of line.
	 */
	public Line(Color border, Color fill, int thick) {
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
	public void drawShape(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		g.setColor(borderColor);
		g.setBackground(fillColor);
		g.drawLine(points.get(0).x, points.get(0).y,
				points.get(1).x, points.get(1).y);
	}

	@Override
	public void testDrawShape(Graphics2D g, Point temp) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		g.setColor(borderColor);
		g.setBackground(fillColor);
		if (points.size() == 1) {
			g.drawLine(points.get(0).x, points.get(0).y, temp.x, temp.y);
		} else if (points.size() == 2) {
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
		width = 0;
		height = 0;
		center = new Point();
		for (int i = 0; i < points.size(); i++) {
			center.x += points.get(i).x;
			center.y += points.get(i).y;
		}
		center.x /= numberOfPointsNeeded;
		center.y /= numberOfPointsNeeded;
		width = Math.abs(points.get(0).x - points.get(1).x);
		height = Math.abs(points.get(0).y - points.get(1).y);
		if (height < 1) {
			height = 2;
		}
		if (width < 1) {
			width = 2;
		}
	}

}
