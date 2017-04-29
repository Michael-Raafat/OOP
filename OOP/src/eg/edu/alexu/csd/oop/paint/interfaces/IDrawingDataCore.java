package eg.edu.alexu.csd.oop.paint.interfaces;

import java.awt.Color;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.commandHandler.CommandManager;
import eg.edu.alexu.csd.oop.paint.commandHandler.InfoCapsule;
/**
 * Interface of the object that will contain all the major data of
 * our painting and will interact with the other objects to change the data
 * or manipulate it.
 * @author Amr
 *
 */
public interface IDrawingDataCore {
	/**
     * Deselects all the selected Shapes.
     */
    void deSelectAll();
	/**
     * Deletes all the selected Shapes and updates the history
     * if there was really on that is deleted.
     */
	void deleteSelectedShapes();
	/**
     * Adds a new shape to the list of the shapes available to draw.
     * @param value
     * The new Class.
     */
    void addClass(Class<? extends GeoShapes> value);
    /**
     * Rolls back the shapes to the previous one in history
     * if available.
     */
    void undo();
    /**
     * Goes to an already made next move in history if available.
     */
    void redo();
    /**
     * Adds the current list of shapes to the history.
     * @param cm
     * Info capsule that has the data of the changes done.
     */
    void updateHistory(InfoCapsule cm);
    /**
     * Sets the fill color that the user is currently using
     * and colors any selected shapes with it.
     * @param value
     * The new Color
     */
    void setFill(Color value);
    /**
     * Sets the border color that the user is currently using
     * and colors any selected shapes with it.
     * @param value
     * The new Color
     */
    void setBorderC(Color value);
    /**
     * Sets the thickness that the user is currently using
     * and changes any selected shapes to it.
     * @param value
     * width of the new line.
     */
    void setThick(int value);
    /**
     * Sets the classes list to a new list.
     * @param files
     * New arraylist of classes.
     */
    void setClasses(ArrayList<Class<? extends GeoShapes>> files);
    /**
     * Gets the current class of shape to be made.
     * @return
     * The class of the current shape.
     */
    Class<?> getShape();
    /**
     * Sets the shape to the class in the specified index of
     * the classes list.
     * @param value
     * Index of the shape.
     */
    /**
     * Gets the list of classes available to draw.
     * @return
     * Array list of classes.
     */
	ArrayList<Class<? extends GeoShapes>> getClasses();
    void setShape(int value);
	/**
	 * Gets the currently user chosen thickness.
	 * @return
	 * current chosen thickness.
	 */
	int getThick();
	/**
	 * Gets the currently user chosen border color.
	 * @return
	 * current chosen color.
	 */
	Color getBorderC();
	/**
	 * Gets the currently user chosen fill color.
	 * @return
	 * current chosen color.
	 */
	Color getFill();
	/**
	 * Function that returns the current list of shapes in the painting.
	 * @return
	 * List of shapes in the painting.
	 */
	ArrayList<GeoShapes> getShapes();
	/**
	 * Resets the history clean.
	 */
	void resetHistory();
	/**
	 * Gets the history;
	 * @return
	 */
	CommandManager getHistory();
	/**
	 * Function that sets the current list of shapes in the painting.
	 */
	void setShapes(ArrayList<GeoShapes> value);
}
