package eg.edu.alexu.csd.oop.paint.commandHandler;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
/**
 * class of attributes.
 * @author Mico
 */
public class InfoCapsule {
	/**
	 * Arraylist of shapeNode.
	 */
	private ArrayList<ShapeNode> changeList;
	/**
	 * Changed action.
	 */
	private ChangedInfo action;
	/**
	 * color.
	 */
	private Color color;
	/**
	 * thickness.
	 */
	private Integer thickness;
	/**
	 * new point.
	 */
	private Point parametersOfAction;
	/**
	 * enum of attributes.
	 * @author mico
	 */
	public enum ChangedInfo {
	    BORDERCOLOR, FILLCOLOR, MOVE, RESIZE,
	    DRAW, DELETE, THICKNESS
	}
	/**
	 * constructor of InfoCapsule class.
	 */
	public InfoCapsule() {
		this(null, null);
	}
	/**
	 * getter of changed list.
	 * @return
	 * list.
	 */
	public ArrayList<ShapeNode> getChangeList() {
		return changeList;
	}
	/**
	 * setter to new list.
	 * @param changeList
	 * new list.
	 */
	public void setChangeList(ArrayList<ShapeNode> changeList) {
		this.changeList = changeList;
	}
	/**
	 * constructor of InfoCapsule class.
	 * @param shape
	 * list of shapeNodes.
	 * @param changeInfo
	 * changed information.
	 */
	public InfoCapsule(ArrayList<ShapeNode> shape,
			ChangedInfo changeInfo) {
		this(shape, changeInfo, null, null, null);
	}
	/**
	 * getter of action.
	 * @return
	 * action.
	 */
	public ChangedInfo getAction() {
		return action;
	}
	/**
	 * setter to new action.
	 * @param action
	 * new action.
	 */
	public void setAction(ChangedInfo action) {
		this.action = action;
	}
	/**
	 * getter of color.
	 * @return
	 * color.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * setter to color.
	 * @param color
	 * new color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * getter of thickness.
	 * @return
	 * thickness.
	 */
	public Integer getThickness() {
		return thickness;
	}
	/**
	 * setter to thickness.
	 * @param thickness
	 * new thickness.
	 */
	public void setThickness(Integer thickness) {
		this.thickness = thickness;
	}
	/**
	 * constructor of InfoCapsule class.
	 * @param shape
	 * new list of ShapeNodes.
	 * @param changeInfo
	 * changed information.
	 * @param value
	 * new color.
	 */
	public InfoCapsule(ArrayList<ShapeNode> shape,
			ChangedInfo changeInfo,
			Color value) {
		this(shape, changeInfo, value, null, null);
	}
	/**
	 * constructor of InfoCapsule class.
	 * @param shape
	 * new list of ShapeNodes.
	 * @param changeInfo
	 * changed information.
	 * @param value
	 * new thickness.
	 */
	public InfoCapsule(ArrayList<ShapeNode> shape,
			ChangedInfo changeInfo,
			Integer value) {
		this(shape, changeInfo, null, value, null);
	}
	/**
	 * constructor of InfoCapsule class.
	 * @param shape
	 * new list of ShapeNodes.
	 * @param changeInfo
	 * changed information.
	 * @param value
	 * new point.
	 */
	public InfoCapsule(ArrayList<ShapeNode> shape,
			ChangedInfo changeInfo,
			Point value) {
		this(shape, changeInfo, null, null, value);
	}
	/**
	 * constructor of InfoCapsule class.
	 * @param shape
	 * new list of ShapeNodes.
	 * @param changeInfo
	 * changed information.
	 * @param value
	 * new color.
	 * @param tvalue
	 * new thickness.
	 * @param pvalue
	 * new point.
	 */
	public InfoCapsule(ArrayList<ShapeNode> shape,
			ChangedInfo changeInfo,
			Color value,
			Integer tvalue,
			Point pvalue) {
		action = changeInfo;
		changeList = shape;
		deselectAll();
		color = value;
		thickness = tvalue;
		setParametersOfAction(pvalue);
	}
	/**
	 * getter of point.
	 * @return
	 * point.
	 */
	public Point getParametersOfAction() {
		return parametersOfAction;
	}
	/**
	 * setter to point.
	 * @param parametersOfAction
	 * new point.
	 */
	public void setParametersOfAction(Point parametersOfAction) {
		this.parametersOfAction = parametersOfAction;
	}
	/**
	 * to deselect all shapes.
	 */
	private void deselectAll() {
		for (int i = 0; i < changeList.size(); i++) {
			changeList.get(i).getShape().setSelected(false);
		}
	}
}
