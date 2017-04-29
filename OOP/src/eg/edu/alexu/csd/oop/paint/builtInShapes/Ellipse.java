package eg.edu.alexu.csd.oop.paint.builtInShapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
/**
 * Ellipse class.
 * @author Mico
 */
public class Ellipse extends GeoShapes {
	/**
	 * new ellipse.
	 */
	public Ellipse() {
		this(Config.getDefaultBorderColor());
	}
	/**
	 * new ellipse.
	 * @param border
	 * color of ellipse's border
	 */
	public Ellipse(final Color border) {
		this(border, Config.getDefaultThickness());
	}
	/**
	 * new ellipse.
	 * @param border
	 * color of ellipse's border.
	 * @param thick
	 * thickness of border.
	 */
	public Ellipse(final Color border, final int thick) {
		this(border, Config.getDefaultFillColor(), thick);
	}
	/**
	 * initialize new ellipse.
	 * @param border
	 * color of ellipse's border.
	 * @param thick
	 * thickness of border.
	 * @param fill
	 * fill color of ellipse.
	 */
	public Ellipse(final Color border, final Color fill, final int thick) {
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
	public final void drawShape(final Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		g.setColor(fillColor);
		g.fillOval(center.x - width / 2,
			center.y - height / 2, width, height);
		g.setColor(borderColor);
		g.drawOval(center.x - width / 2,
			center.y - height / 2, width, height);
	}

	@Override
	public final void testDrawShape(final Graphics2D g, final Point temp) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(thickness));
		if (points.size() == 1) {
			int tempWidth = Math.abs(temp.x - center.x) * 2;
			int tempHeight = Math.abs(temp.y - center.y) * 2;
			g.setColor(fillColor);
			g.fillOval(center.x - (tempWidth / 2),
					center.y - (tempHeight / 2),
					tempWidth,
					tempHeight);
			g.setColor(borderColor);
			g.drawOval(center.x - (tempWidth / 2),
					center.y - (tempHeight / 2),
					tempWidth,
					tempHeight);
		} else if (points.size() == 2) {
			drawShape(g);
		}
	}

	@Override
	public final void enterPoint(final Point p) {
		// TODO Auto-generated method stub
		if (getPointsListSize() >= numberOfPointsNeeded) {
			throw new RuntimeException();
		} else {
			if (getPointsListSize() == 0) {
				points.add(p);
				center = p;
			} else {
				points.add(p);
				width = Math.abs(p.x - center.x) * 2;
				height = Math.abs(p.y - center.y) * 2;
				calculateCenter();
			}
		}
	}

	@Override
	protected final void calculateCenter() {
		// TODO Auto-generated method stub
		center = points.get(0);
		width = Math.abs(points.get(1).x - center.x) * 2;
		height = Math.abs(points.get(1).y - center.y) * 2;
	}

}
