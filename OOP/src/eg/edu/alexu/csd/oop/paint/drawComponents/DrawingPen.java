package eg.edu.alexu.csd.oop.paint.drawComponents;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.commandHandler.InfoCapsule;
import eg.edu.alexu.csd.oop.paint.commandHandler.ShapeNode;
import eg.edu.alexu.csd.oop.paint.commandHandler.InfoCapsule.ChangedInfo;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingDataCore;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingMouseReaction;
/**
 * Implementation of the IDrawingMouseReaction interface.
 * @author Amr
 *
 */
public class DrawingPen implements IDrawingMouseReaction {
	/**
	 * Constants that represent the int value for each operation.
	 */
	private final int resize = Config.getResize(),
			move = Config.getMove(),
			delete = Config.getDelete(),
			select = Config.getSelect(),
			draw = Config.getDraw();
	/**
	 * The object that contains all the data of the painting.
	 */
	private IDrawingDataCore dataCore;
	/**
	 * A variable where we save the mouse coordinates at a certain event
	 * to reuse it later.
	 */
	private Point pointSaver;
	/**
	 * A variable that tells us if there is an unfinished action underway.
	 */
	private boolean operationUnderway;
	/**
	 * Variable to hold the current operation chosen.
	 */
    private int operationState = Config.getInitialOperationDrawingReaction();
    /**
     * Variable to hold the undergoing under construction shape.
     */
    private GeoShapes current;
    /**
     * The drawing board to be shown to the user.
     */
    private DrawingBoard board;
    /**
     * A variable which will act as an indication to how many more
     * points are needed to complete the construction of the shape.
     */
    private int counterInput;
    /**
     * @deprecated "Be careful when using this. You will need to set the board
     * and data correctly"
     */
    @Deprecated
    public DrawingPen() {
    	this(null, null);
    }
    /**
     * Constructor of our object will have to contain two variables.
     * @param newBoard
     * The board needed to make calls to the repaint to update the board.
     * @param data
     * The drawingData that will be used in the different operations.
     */
    public DrawingPen(final DrawingBoard newBoard,
    		final IDrawingDataCore data) {
    	this.board = newBoard;
    	this.dataCore = data;
    	operationUnderway = false;
    }
    @Override
	public final void releaseAction(final MouseEvent e) {
		if (operationState == select) {
			for (int i = 0; i < dataCore.getShapes().size(); i++) {
				if (dataCore.getShapes().get(i).contains(
						e.getPoint())) {
					dataCore.getShapes().get(i).setSelected(
				!dataCore.getShapes().get(i).isSelected());
				}
			}
			board.repaint();
		} else if (operationState == move) {
			if (operationUnderway) {
				ArrayList<ShapeNode> changeList
				= new ArrayList<ShapeNode>();
			Point parameters = new Point(
					e.getX()
					- pointSaver.x,
					e.getY()
					- pointSaver.y);
			for (int i = 0; i < dataCore.getShapes().size(); i++) {
			if (dataCore.getShapes().get(i).isSelected()) {
				changeList.add(new ShapeNode(
					GeoShapes.copy(dataCore.getShapes().get(i)), i));
				dataCore.getShapes().set(i,
					dataCore.getShapes().get(i).move(
					parameters.x, parameters.y));
				}
				}
				board.repaint();
				InfoCapsule cm = new InfoCapsule(changeList,
						ChangedInfo.MOVE,
						parameters);
				dataCore.updateHistory(cm);
				operationUnderway = false;
			}
		} else if (operationState == resize) {
			if (operationUnderway) {
				ArrayList<ShapeNode> changeList
				= new ArrayList<ShapeNode>();
			Point parameters = new Point(
					-(e.getX() - pointSaver.x),
					-(e.getY() - pointSaver.y)
					);
				for (int i = 0; i < dataCore.getShapes().size();
						i++) {
					if (dataCore.getShapes().get(i)
							.isSelected()) {
						changeList.add(new ShapeNode(GeoShapes.copy(
								dataCore.getShapes().get(i)), i));
						dataCore.getShapes().set(i,
					dataCore.getShapes().get(i).resize(
						parameters.x, parameters.y));
					}
				}
				board.repaint();
				InfoCapsule cm = new InfoCapsule(changeList,
						ChangedInfo.RESIZE,
						parameters);
				operationUnderway = false;
				dataCore.updateHistory(cm);
			}
		} else if (operationState == draw) {
			if (operationUnderway) {
				current.enterPoint(e.getPoint());
				board.repaint();
				if (counterInput
					== current.getPointsListSize()) {
					operationUnderway = false;
					ArrayList<ShapeNode> changeList
					= new ArrayList<ShapeNode>();
					changeList.add(new ShapeNode(current,
							dataCore.getShapes().size()));
				InfoCapsule cm = new InfoCapsule(changeList,
						ChangedInfo.DRAW);
					dataCore.getShapes().add(current);
					dataCore.updateHistory(cm);
				}
			}
		} else if (operationState != delete) {
			throw new RuntimeException();
    	}
    }
    @Override
	public final void moveAction(final MouseEvent e) {
    	if (operationUnderway) {
    		board.repaint();
    	}
    }
    @Override
	public final void dragAction(final MouseEvent e) {
    	if (operationUnderway) {
    		board.repaint();
    	}
    }
    @Override
	public final void pressAction(final MouseEvent e) {
		if (operationState == move) {
			boolean beginOperation = false;
			for (int i = 0; i < dataCore.getShapes().size(); i++) {
				if (dataCore.getShapes().get(i).isSelected()
					&& dataCore.getShapes().get(i)
					.isInDrawResizable(
								e.getPoint())) {
					beginOperation = true;
				}
			}
			if (beginOperation) {
				operationUnderway = true;
				pointSaver = new Point(e.getX(), e.getY());
			}
		} else if (operationState == resize) {
			boolean beginOperation2 = false;
			for (int i = 0; i < dataCore.getShapes().size(); i++) {
				if (dataCore.getShapes().get(i).isSelected()
					&& dataCore.getShapes().get(i)
					.isInDrawResizable(
							e.getPoint())) {
					beginOperation2 = true;
				}
			}
			if (beginOperation2) {
				operationUnderway = true;
				pointSaver = new Point(e.getX(), e.getY());
			}
		} else if (operationState == draw) {
			if (!operationUnderway) {
				try {
				current = (GeoShapes) dataCore.getShape()
							.newInstance();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	operationUnderway = true;
            	current.setBorderColor(dataCore.getBorderC());
            	current.setFillColor(dataCore.getFill());
            	current.setThickness(dataCore.getThick());
            	counterInput = current.getNumberOfPointsNeeded();
        	}
		} else if (operationState != delete
				&& operationState != select) {
			throw new RuntimeException();
		}
    }
    @Override
	public final void setDataCore(final IDrawingDataCore data) {
		this.dataCore = data;
	}
    @Override
	public final void drawFullPainting(final Graphics2D g2) {
    	for (int i = 0; i < dataCore.getShapes().size(); i++) {
        	if (dataCore.getShapes().get(i).isSelected()
        			&& (operationState == move
        			|| operationState == resize)
        		&& operationUnderway) {
        		Point temp = new Point(
            			MouseInfo.getPointerInfo().getLocation().x
            			- board.getLocationOnScreen().x - pointSaver.x,
            			MouseInfo.getPointerInfo().getLocation().y
            			- board.getLocationOnScreen().y - pointSaver.y);
        		if (operationState == move) {
        			dataCore.getShapes().get(i).move(
        					temp.x, temp.y).completeDraw(
        							g2, null);
        		} else {
        			dataCore.getShapes().get(i).resize(
        					-temp.x, -temp.y).completeDraw(
        							g2, null);
        		}
        	} else {
        		dataCore.getShapes().get(i).completeDraw(g2, null);
        	}
        }
        if (operationUnderway && operationState == draw) {
        	Point temp = new Point(
        			MouseInfo.getPointerInfo().getLocation().x
        			- board.getLocationOnScreen().x,
        			MouseInfo.getPointerInfo().getLocation().y
        			- board.getLocationOnScreen().y);
        	current.completeDraw(g2, temp);
        }
    }
    @Override
	public final void setOperation(final int value) {
    	if (operationUnderway) {
    		throw new RuntimeException();
    	}
    	if (value == select
    || value == move
    || value == resize) {
    		operationState = value;
    	} else if (value == draw) {
    		dataCore.deSelectAll();
    		operationState = value;
    	} else if (value == delete) {
    		dataCore.deleteSelectedShapes();
    		board.repaint();
    	} else {
    		throw new RuntimeException();
    	}
    }
	@Override
	public final void setDrawingBoard(final DrawingBoard newBoard) {
		// TODO Auto-generated method stub
		this.board = newBoard;
	}
}
