package eg.edu.alexu.csd.oop.paint.abstractComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
/**
 * Abstract Class of the paint program.
 * It describes the general fields and behavior
 * of any shape that will be drawn.
 * @author Amr&Mico
 * OOP 2nd! assignment.
 */

public abstract class GeoShapes {
	/**
	 * the name of the class.
	 */
	private String myClass = this.getClass().getName();
	/**
	 * The center of the rectangle
	 * that the shape drawing occupies.
	 */
	protected Point center;
	/**
	 * The height and width of the rectangle
	 * that the shape occupies.
	 */
	protected int width, height;
	/**
	 * The thinkness of the borders of the shape.
	 */
	protected int thickness;
	/**
	 * Defines how many points the shape needs to get drawn.
	 */
	protected int numberOfPointsNeeded;
	/**
	 * The color of the borders of the shape.
	 */
	protected Color borderColor;
	/**
	 * The color to be filled inside the shape.
	 */
	protected Color fillColor;
	/**
	 * User Entered points that define the shape and are needed
	 * to draw the shape.
	 * Should contain all points that are definitive to the shape
	 * or give the shape all properties needed.
	 */
	protected ArrayList<Point> points;
	/**
	 * boolean variable to know if shape is selected or not.
	 */
	protected boolean selected;
	/**
	 * angle of rotation of the shape.
	 */
	protected double rotationAngle;
    /**
     * get class name.
     * @return
     * class name.
     */
	public String getMyClass() {
		return myClass;
	}
    /**
     * Just a setter.
     * @param noMeaning
     * String contains name of class.
     * we don't use it.
     */
	public void setMyClass(String noMeaning) {
	}
	/**
	 * Checks if a point exists inside the draw rectangle
	 * of the shape. Can be overridden to better suite the shape.
	 * @param currentPlace
	 * The point to be checked if it inside.
	 * @return
	 * true if inside.
	 */
	public boolean contains(final Point currentPlace) {
		if (currentPlace.x < center.x + width / 2
			&& currentPlace.x > center.x - width / 2
			&& currentPlace.y < center.y + height / 2
			&& currentPlace.y > center.y - height / 2) {
			return true;
		}
		return false;
	}
	/**
	 * Moves the shape points all together.
	 * Can be overridden if optimization is needed.
	 * @param dx
	 * distance on the x-axis to be moved.
	 * @param dy
	 * distance on the y-axis to be moved.
	 * @return
	 * moved shape with its new points.
	 */
	public GeoShapes move(final int dx, final int dy) {
		GeoShapes movedCopy = copy(this);
		movedCopy.center.translate(dx, dy);
		for (int i = 0; i < movedCopy.points.size(); i++) {
			if (!movedCopy.points.get(i).equals(movedCopy.center)) {
				movedCopy.points.get(i).translate(dx, dy);
			}
		}
		return movedCopy;
	}
	/**
	 * Resizes the shape and calculates its new points.
	 * can be overridden to be optimized.
	 * @param dx
	 * The complete increase of its size in the x-axis.
	 * @param dy
	 * The complete increase of its size in the y-axis.
	 * @return
	 * modified shape.
	 */
	public GeoShapes resize(final int dx, final int dy) {
		GeoShapes resizedCopy = copy(this);
		int realDx = dx,
			realDy = dy;
		resizedCopy.width += dx * 2;
		resizedCopy.height += dy * 2;
		for (int i = 0; i < resizedCopy.points.size(); i++) {
			if (resizedCopy.points.get(i).x <
					resizedCopy.center.x) {
				resizedCopy.points.get(i).x -= realDx;
			} else if (resizedCopy.points.get(i).x >
			resizedCopy.center.x) {
				resizedCopy.points.get(i).x += realDx;
			}
			if (resizedCopy.points.get(i).y <
					resizedCopy.center.y) {
				resizedCopy.points.get(i).y -= realDy;
			} else if (resizedCopy.points.get(i).y >
			resizedCopy.center.y){
				resizedCopy.points.get(i).y += realDy;
			}
		}
		resizedCopy.calculateCenter();
		return resizedCopy;
	}
	/**
	 * rotates the shape.
	 * recommended to be further optimized by overriding.
	 * @param d
	 * angle to be rotated by.
	 */
	public void setRotationAngle(final double d) {
		rotationAngle = d;
	}
	/**
	 * Draws the full shape, will be used inside a Jpanel.
	 * @param g
	 * Graphics variable of the Jpanel.
	 */
	protected abstract void drawShape(Graphics2D g);
	/**
	 * Draws the shape appearance if a certain point is added.
	 * @param g
	 * Graphics of the Jpanel.
	 * @param temp
	 * The point that could be added.
	 */
	protected abstract void testDrawShape(Graphics2D g, Point temp);
	/**
	 * Adds a new point to the points that define the shape.
	 * @param p
	 * Point to be added.
	 */
	public abstract void enterPoint(Point p);
	/**
	 * finish drawing of the shape.
	 * @param g
	 * Graphics of the Jpanel.
	 * @param temp
	 * point that mouse locates.
	 */
	public final void completeDraw(Graphics2D g,Point temp) {
		if (points.size() == numberOfPointsNeeded) {
			drawShape(g);
			if (selected) {
				drawDottedRectangle(g);
				drawResizableController(g);
			}
		} else {
			testDrawShape(g, temp);
		}
	}
	/**
	 * Setter to the fillColor field.
	 * @param newFill
	 * new Color value.
	 */
	public final void setFillColor(final Color newFill) {
		fillColor = newFill;
	}
	/**
	 * Setter to the Thickness field.
	 * @param newThick
	 * new Thickness value.
	 */
	public final void setThickness(final int newThick) {
		thickness = newThick;
	}
	/**
	 * Setter to the borderColor field.
	 * @param newBorder
	 * new Color value.
	 */
	public final void setBorderColor(final Color newBorder) {
		borderColor = newBorder;
	}
	/**
	 * @return
	 * The number of needed points to complete the shape.
	 */
	public final int getNumberOfPointsNeeded() {
		return numberOfPointsNeeded;
	}
	/**
	 * getter of border color.
	 * @return
	 * border color of the shape.
	 */
	public final Color getBorderColor() {
		return borderColor;
	}
	/**
	 * getter of fill color.
	 * @return
	 * color to be filled in the shape.
	 */
	public final Color getFillColor() {
		return fillColor;
	}
	/**
	 * getter of points of shape.
	 * @return
	 * list of points of the shape.
	 */
	public final ArrayList<Point> getPoints() {
		return points;
	}
	/**
	 * @return
	 * The number of points entered to the shape.
	 */
	public final int getPointsListSize() {
		return points.size();
	}
	/**
	 * checks if shape is selected or not.
	 * @return
	 * true if shape is selected
	 */
	public final boolean isSelected() {
		return selected;
	}
	/**
	 * setter to selected shape.
	 * @param value
	 * set if the shape is selected or not.
	 */
	public final void setSelected(boolean value) {
		selected = value;
	}
	/**
	 * calculates the center of shape.
	 */
	protected abstract void calculateCenter();
	/**
	 * shows that shape is selected.
	 * @param g
	 * Graphics of the Jpanel.
	 */
	protected void drawDottedRectangle(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
	        Stroke dashed = new BasicStroke(
	        		this.thickness + 3, BasicStroke.CAP_BUTT,
	        		BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	        g2d.setStroke(dashed);
	        g2d.drawRect(center.x - width / 2,
	        	center.y - height / 2, width, height);
	}
	/**
	 * controls resize operation
	 * @param g
	 * Graphics of the Jpanel.
	 */
	protected void drawResizableController(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(center.x - width / 2 - (height + width) / 50,
				center.y - height / 2 - (height + width) / 50,
				(height + width) / 25, (height + width) / 25);
		g.setColor(borderColor);
		g.drawRect(center.x - width / 2 - (height + width) / 50,
				center.y - height / 2 - (height + width) / 50,
				(height+ width) / 25, (height + width) / 25);
	}
	/**
	 * to control the movement and resizing of the shape.
	 * @param p
	 * point that is located by mouse.
	 * @return
	 * true if the point is in the small square.
	 * in the left upper corner.
	 */
	public boolean isInDrawResizable(Point p) {
		if (p.x > center.x - width/2 - (height+width)/50 &&
			p.x < center.x - width/2 + (height+width)/50 &&
			p.y > center.y - height/2 - (height+width)/50 &&
			p.y < center.y - height/2 + (height+width)/50) {
			return true;
		}
		return false;
	}
	/**
	 * getter of rotation angle.
	 * @return
	 * rotation angle.
	 */
	public double getRotationAngle() {
		return rotationAngle;
	}
	/**
	 * setter to center of the shape.
	 * @param center
	 * new center.
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
	/**
	 * setter to width of the shape.
	 * @param width
	 * new width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * setter to height of the shape.
	 * @param height
	 * new height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * setter to number of points needed to draw the shape.
	 * @param numberOfPointsNeeded
	 * number of points.
	 */
	public void setNumberOfPointsNeeded(int numberOfPointsNeeded) {
		this.numberOfPointsNeeded = numberOfPointsNeeded;
	}
	/**
	 * setter to points of the shape.
	 * @param points
	 * new points of the shape.
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	/**
	 * getter of width.
	 * @return
	 * height of the shape.
	 */
	public final int getWidth() {
		return width;
	}
	/**
	 * getter of height.
	 * @return
	 * height of the shape.
	 */
	public final int getHeight() {
		return height;
	}
	/**
	 * getter of thickness.
	 * @return
	 * the thickness.
	 */
	public final int getThickness() {
		return thickness;
	}
	/**
	 * getter of center.
	 * @return
	 * point of the shape's center.
	 */
	public final Point getCenter() {
		return center;
	}
	/**
	 * copy shape to a new instance.
	 * @param src
	 * the shape
	 * @return
	 * its copy
	 */
	public static GeoShapes copy(GeoShapes src) {
		try {
			GeoShapes cpy = src.getClass().newInstance();
			for (int i = 0; i < src.points.size(); i++) {
				cpy.enterPoint((Point) src.points.get(i).clone());
			}
			cpy.borderColor = src.borderColor;
			cpy.fillColor = src.fillColor;
			cpy.thickness = src.thickness;
			cpy.rotationAngle = src.rotationAngle;
			cpy.selected = src.selected;
			return cpy;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
