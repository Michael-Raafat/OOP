package eg.edu.alexu.csd.oop.paint.interfaces;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import eg.edu.alexu.csd.oop.paint.drawComponents.DrawingBoard;
/**
 * Interface to the class responsible of the actions taken
 * when certain mouse events happen.
 * @author Amr
 *
 */
public interface IDrawingMouseReaction {
	/**
	 * Action taken when the mouse is released.
	 * @param e
	 * Mouse event parameter that will be passed down by the
	 * mouse adapter.
	 */
	void releaseAction(MouseEvent e);
	/**
	 * Action taken when the mouse is moved without pressing.
	 * @param e
	 * Mouse event parameter that will be passed down by the
	 * mouse adapter.
	 */
	void moveAction(MouseEvent e);
	/**
	 * Action taken when the mouse is pressed.
	 * @param e
	 * Mouse event parameter that will be passed down by the
	 * mouse adapter.
	 */
	void pressAction(MouseEvent e);
	/**
	 * Action taken when the mouse is dragged while pressed.
	 * @param e
	 * Mouse event parameter that will be passed down by the
	 * mouse adapter.
	 */
	void dragAction(MouseEvent e);
	/**
	 * Function responsible of setting the sort of action that
	 * should happen.
	 * @param value
	 * An int value as representation of the action, should be done with
	 * accords to main GUI implementation.
	 */
	void setOperation(int value);
	/**
	 * Function responsible of the drawing of all the figures in
	 * all the modes.
	 * @param g2
	 * Graphics passed by the repaint method in the board class.
	 */
	void drawFullPainting(Graphics2D g2);
	/**
	 * Function responsible of setting the DrawingData which holds
	 * all the details and informations about the current painting.
	 * @param data
	 * The new value of the Drawing Data.
	 */
	void setDataCore(IDrawingDataCore data);
	/**
	 * The function responsible of linking this class to the main board so
	 * you can use some of the board functions when suitable.
	 * @param newBoard
	 * DrawingBoard variable , the new value to be assigned.
	 */
	void setDrawingBoard(DrawingBoard newBoard);
}
