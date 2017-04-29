package eg.edu.alexu.csd.oop.paint.commandHandler;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;

public class ShapeNode {
	/**
	 * Object from geoshapes class.
	 */
	private GeoShapes shape;
	/**
	 * getter of shape.
	 * @return
	 * new shape
	 */
	public GeoShapes getShape() {
		return shape;
	}
	/**
	 * setter to shape.
	 */
	public void setShape(GeoShapes shape) {
		this.shape = shape;
	}
	/**
	 * getter of index.
	 * @return
	 * new index
	 */
	public Integer getIndex() {
		return index;
	}
	/**
	 * setter to index.
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	/**
	 * index of shape.
	 */
	private Integer index;
	/**
	 * shapeNode class constructor.
	 */
	public ShapeNode() {
		// TODO Auto-generated constructor stub
		this(null, null);
	}
	/**
	 * shapeNode class constructor.
	 * @param geo
	 * new shape.
	 * @param value
	 * new index.
	 */
	public ShapeNode(GeoShapes geo, Integer value) {
		shape = geo;
		index = value;
	}
}
