package eg.edu.alexu.csd.oop.paint.drawComponents;

import java.awt.Color;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.commandHandler.CommandManager;
import eg.edu.alexu.csd.oop.paint.commandHandler.InfoCapsule;
import eg.edu.alexu.csd.oop.paint.commandHandler.InfoCapsule.ChangedInfo;
import eg.edu.alexu.csd.oop.paint.commandHandler.ShapeNode;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingDataCore;
/**
 * Our implementation of IDrawingDataCore.
 * @author Amr
 *
 */
public class DrawingData implements IDrawingDataCore {
	/**
	 * List of shapes.
	 */
	private ArrayList<GeoShapes> shapes;
	/**
	 * Current Chosen border color.
	 */
	private Color borderC;
	/**
	 * Current Chosen Thickness.
	 */
    private int thick;
    /**
     * Current Chosen Fill Color.
     */
    private Color fill;
    /**
     * list of available shape classes.
     */
    private ArrayList<Class<? extends GeoShapes>> classes;
    /**
     * Current shape chosen to be drawn.
     */
    private Class<?> shape;
    /**
     * List of shapes lists that constitute the history.
     */
    private CommandManager history;
    /**
     * Constructor of the Data.
     */
    public DrawingData() {
 	   shapes = new ArrayList<GeoShapes>();
        borderC = Config.getDefaultBorderColor();
        fill = Config.getDefaultFillColor();
        thick = Config.getDefaultThickness();
        history = new CommandManager();
        classes = new ArrayList<Class<? extends GeoShapes>>();
        shape = Config.getDefaultShape();
    }
    @Override
	public final ArrayList<GeoShapes> getShapes() {
    	return shapes;
    }
    /**
     * Setter for the Shapes list.
     * @param list
     * New list of shapes.
     */
    public final void setShapes(final ArrayList<GeoShapes> list) {
    	shapes = list;
    }
    /**
     * Getter for the history variable.
     * @return
     * History list.
     */
    public final CommandManager getHistory() {
		return history;
	}
    /**
     * Setter for the history variable.
     * @param newhistory
     * New history.
     */
	public final void setHistory(
			final CommandManager newhistory) {
		this.history = newhistory;
	}
	@Override
	public final int getThick() {
		return thick;
	}
	@Override
	public final Class<?> getShape() {
		return shape;
	}
	@Override
	public final void setShape(final int value) {
		this.shape = classes.get(value);
	}
	@Override
	public final Color getBorderC() {
		return borderC;
	}
	@Override
	public final Color getFill() {
		return fill;
	}
	/**
	 * Getter for the classes list.
	 * @return
	 * The list of classes.
	 */
	public final ArrayList<Class<? extends GeoShapes>> getClasses() {
		return classes;
	}
    @Override
	public final void setClasses(final
			ArrayList<Class<? extends GeoShapes>> value) {
    	classes = value;
    }
    @Override
	public final void setBorderC(final Color value) {
    	borderC = value;
    	ArrayList<ShapeNode> changeList
    		= new ArrayList<ShapeNode>();
    	boolean change = false;
    	for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				change = true;
				changeList.add(new ShapeNode(GeoShapes.copy(shapes.get(i)), i));
				shapes.get(i).setBorderColor(borderC);
			}
		}
    	if (change) {
    		InfoCapsule cm = new InfoCapsule(changeList,
    				ChangedInfo.BORDERCOLOR, value);
    		updateHistory(cm);
		}
    }
    @Override
	public final void setThick(final int value) {
    	thick = value;
    	ArrayList<ShapeNode> changeList
		= new ArrayList<ShapeNode>();
    	boolean change = false;
    	for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				changeList.add(new ShapeNode(GeoShapes.copy(shapes.get(i)), i));
				shapes.get(i).setThickness(thick);
				change = true;
			}
		}
    	if (change) {
    		InfoCapsule cm = new InfoCapsule(changeList,
    				ChangedInfo.THICKNESS, value);
    		updateHistory(cm);
		}
    }
    @Override
	public final void setFill(final Color value) {
    	fill = value;
    	ArrayList<ShapeNode> changeList
		= new ArrayList<ShapeNode>();
    	boolean change = false;
    	for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				change = true;
				changeList.add(new ShapeNode(GeoShapes.copy(shapes.get(i)), i));
				shapes.get(i).setFillColor(fill);
			}
		}
    	if (change) {
    		InfoCapsule cm = new InfoCapsule(changeList,
    		ChangedInfo.FILLCOLOR, value);
    		updateHistory(cm);
		}
    }
    @Override
	public final void updateHistory(InfoCapsule cm) {
    	history.saveAction(cm);
    }
    @Override
	public final void redo() {
    	history.redoAction(this);
    }
    @Override
	public final void undo() {
    	history.undoAction(this);
    }
    @Override
	public final void deSelectAll() {
    	for (int i = 0; i < shapes.size(); i++) {
			shapes.get(i).setSelected(false);
		}
    }
    @Override
	public final void deleteSelectedShapes() {
    	boolean deleted = false;
    	ArrayList<ShapeNode> changeList
		= new ArrayList<ShapeNode>();
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				changeList.add(new ShapeNode(GeoShapes.copy(shapes.get(i)), i));
				shapes.remove(i);
				i--;
				deleted = true;
			}
		}
		if (deleted) {
			InfoCapsule cm = new InfoCapsule(changeList,
		    		ChangedInfo.DELETE);
			updateHistory(cm);
		}
    }
    @Override
	public final void addClass(final Class<? extends GeoShapes> value) {
    	classes.add(value);
    }
	@Override
	public void resetHistory() {
		// TODO Auto-generated method stub
		history = new CommandManager();
	}
}
