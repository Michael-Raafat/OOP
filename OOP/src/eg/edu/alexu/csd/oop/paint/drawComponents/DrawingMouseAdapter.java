package eg.edu.alexu.csd.oop.paint.drawComponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingMouseReaction;

/**
 * My own MouseListener that will do my implemented reactions to
 * the mouse.
 * @author Amr
 *
 */
public class DrawingMouseAdapter extends MouseAdapter {
	/**
	 * The Object responsible of the actions done as response
	 * to the different events.
	 */
	private IDrawingMouseReaction pen;
	/**
	 * Constructor of the mouseAdapter.
	 * @param pens
	 * The Controller responsible of the reactions to the different
	 * events.
	 */
    public DrawingMouseAdapter(final IDrawingMouseReaction pens) {
		// TODO Auto-generated constructor stub
    	this.pen = pens;
	}

	@Override
	public final void mousePressed(final MouseEvent e) {
		pen.pressAction(e);
    }
    @Override
	public final void mouseDragged(final MouseEvent e) {
    	pen.dragAction(e);
    }
    @Override
	public final void mouseMoved(final MouseEvent e) {
    	pen.moveAction(e);
    }
    @Override
	public final void mouseReleased(final MouseEvent e) {
    	pen.releaseAction(e);
    }

}
