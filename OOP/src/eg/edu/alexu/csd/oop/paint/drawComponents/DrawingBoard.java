package eg.edu.alexu.csd.oop.paint.drawComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingDataCore;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingMouseReaction;
/**
 * Class responsible of shaping up the board and
 * initializing it.
 * @author Amr
 *
 */
public class DrawingBoard extends JPanel {
    /**
	 * UID for the serialization.
	 */
	private static final long serialVersionUID = 3368841546423781581L;
	/**
	 * Modified mouse motion adapter.
	 */
	private DrawingMouseAdapter mickey;
	/**
	 * Interface of the data core of the program.
	 */
	private IDrawingDataCore dataCore;
	/**
	 * Interface of the actions done when we press the mouse.
	 */
	private IDrawingMouseReaction pen;
	/**
	 * Inspector of the elements.
	 */
	private DrawingInspector inspector;
	/**
	 * Getter for the inspector.
	 * @return
	 * The inspector object.
	 */
	public final DrawingInspector getInpsector() {
		return inspector;
	}
	/**
	 * Getter for the data core.
	 * @return
	 * The data core of the painting.
	 */
	public final IDrawingDataCore getDataCore() {
		return dataCore;
	}
	/**
	 * Setter for the data core. Changes the other objects related to the
	 * datacore to the new value.
	 * @param value
	 * The new value of the datacore.
	 */
	public final void setDataCore(final IDrawingDataCore value) {
		dataCore = value;
		pen.setDataCore(dataCore);
		inspector.setDataCore(dataCore);
	}
	/**
	 * Constructor for the class.
	 */
    public DrawingBoard() {
    	try {
			dataCore = (IDrawingDataCore)
			Config.getDefaultIdrawingDataCore().newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			pen = (IDrawingMouseReaction)
				Config.getMouseReactionImplementation()
				.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pen.setDataCore(dataCore);
        pen.setDrawingBoard(this);
        inspector = new DrawingInspector(dataCore, this);
        mickey = new DrawingMouseAdapter(pen);
        setBackground(Config.getDefaultBoardBackground());
        addMouseMotionListener(mickey);
        addMouseListener(mickey);
    }

    @Override
	public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        inspector.updateHierachy();
        inspector.updateInspector();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        	RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        try {
            pen.drawFullPainting(g2);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
    /**
     * Calls the set operation from the interface
     * of the IDrawingReaction.
     * @param value
     * Index of the chosen operation.
     */
    public final void setOperation(final int value) {
    	pen.setOperation(value);
    }
    /**
     * Sets the thickness of a data core.
     * @param value
     * The new value of thickness.
     */
    public final void setThickness(final int value) {
    	dataCore.setThick(value);
    	repaint();
    }
    /**
     * Sets the fill color of a data core.
     * @param value
     * The new value of fill color.
     */
    public final void setFill(final Color value) {
    	dataCore.setFill(value);
    	repaint();
    }
    /**
     * Sets the border color of a data core.
     * @param value
     * The new value of border color.
     */
    public final void setBorderC(final Color value) {
    	dataCore.setBorderC(value);
    	repaint();
    }
    /**
     * Getter for the data core border color.
     * @return
     * The border Color.
     */
    public final Color getBorderC() {
    	return dataCore.getBorderC();
    }
    /**
     * Getter for the data core fill color.
     * @return
     * The fill Color.
     */
    public final Color getFill() {
    	return dataCore.getFill();
    }
    /**
     * Getter for the data core thickness.
     * @return
     * The Thickness number.
     */
    public final int getThickness() {
    	return dataCore.getThick();
    }
    /**
     * Function to call the undo operation of the dataCore.
     */
    public final void undo() {
    	dataCore.undo();
    	repaint();
    }
    /**
     * Function to call the redo operation of the dataCore.
     */
    public final void redo() {
    	dataCore.redo();
    	repaint();
    }
    /**
     * Sets the classes list available to draw.
     * @param files
     * An arrayList of the classes to be the new list of shapes.
     */
    public final void setClasses(
    		final ArrayList<Class<? extends GeoShapes>> files) {
    	dataCore.setClasses(files);
    }
    /**
     * Sets the current shape to be drawn to a class.
     * @param value
     * The index of the chosen class.
     */
    public final void setShape(final int value) {
    	dataCore.setShape(value);
    }
    /**
     * Adds a new class to the list of available classes.
     * @param newClass
     * The class of the new shapes.
     */
    public final void addClass(final Class<? extends GeoShapes> newClass) {
    	dataCore.addClass(newClass);
    }
}
