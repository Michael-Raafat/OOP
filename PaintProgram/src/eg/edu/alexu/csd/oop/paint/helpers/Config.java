package eg.edu.alexu.csd.oop.paint.helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.builtInShapes.Circle;
import eg.edu.alexu.csd.oop.paint.builtInShapes.Ellipse;
import eg.edu.alexu.csd.oop.paint.builtInShapes.Line;
import eg.edu.alexu.csd.oop.paint.builtInShapes.Rectangle;
import eg.edu.alexu.csd.oop.paint.builtInShapes.Square;
import eg.edu.alexu.csd.oop.paint.builtInShapes.Triangle;
import eg.edu.alexu.csd.oop.paint.drawComponents.DrawingData;
import eg.edu.alexu.csd.oop.paint.drawComponents.DrawingPen;
import eg.edu.alexu.csd.oop.paint.fileManager.WriteReadFile;

/**
 * Config file of our project configures the settings of
 * our program.
 * @author Amr
 *
 */
public final class Config {
	/**
	 * private constructor because this class is a utility class.
	 */
	private Config() {
		//Will never be called.
	}
	/**
	 * The integer value representation of the resize operation.
	 */
	private static final int RESIZE = 3;
	/**
	 * The integer value representation of the move operation.
	 */
	private static final int MOVE = 1;
	/**
	 * The integer value representation of the delete operation.
	 */
	private static final int DELETE = 2;
	/**
	 * The integer value representation of the select operation.
	 */
	private static final int SELECT = 0;
	/**
	 * The integer value representation of the draw operation.
	 */
	private static final int DRAW = 4;
	/**
	 * The integer value representation of starting operation.
	 */
	private static final int INITIAL_OPERATION_IDRAWING_REACTION = DRAW;
	/**
	 * The default implementation class of the IDrawingMouseReaction.
	 */
	private static final Class<?>
		MOUSE_REACTION_IMPLEMENTATION = DrawingPen.class;
	/**
	 * The default border color.
	 */
	private static final Color DEFAULT_BORDER_COLOR
		= Color.BLACK;
	/**
	 * The default fill color.
	 */
	private static final Color DEFAULT_FILL_COLOR
	= new Color(1, 1, 1, 1);
	/**
	 * The default Thickness.
	 */
	private static final int DEFAULT_THICKNESS = 1;
	/**
	 * The default class to draw.
	 */
	private static final Class<? extends GeoShapes> DEFAULT_SHAPE
		= Line.class;
	/**
	 * Default used class implementation of the IDrawingDataCore.
	 */
	private static final Class<?> DEFAULT_IDRAWING_DATA_CORE
		= DrawingData.class;
	/**
	 * Default background color of the board.
	 */
	private static final Color DEFAULT_BOARD_BACKGROUND = Color.WHITE;
	/**
	 * The default dimension for the button of the inspector.
	 */
	private static final Dimension INSPECTOR_TEXTBOX_DIMENSION
		= new Dimension(40, 20);
	/**
	 * The default dimension for the scrollpane of the inspector.
	 */
	private static final Dimension INSPECTOR_SCROLLPANE_DIMENSION
		= new Dimension(80, 100);
	/**
	 * The implementation class for the IPaintSaver.
	 */
	private static final Class<?> IPAINTSAVER_IMPLEMENTATION
		= WriteReadFile.class;
	/**
	 * List of concrete shapes classes.
	 */
	private static final ArrayList<Class<? extends GeoShapes>>
		CONCRETE_CLASSES
		= new ArrayList<Class<? extends GeoShapes>>() {
			/**
			 *
			 */
			private static final long serialVersionUID
			= -6641501641683659411L;

		{
			   add(Line.class);
			   add(Triangle.class);
			   add(Circle.class);
			   add(Ellipse.class);
			   add(Rectangle.class);
			   add(Square.class);
			}};;
	/**
	* white empty space surronding my GUI components.
	*/
	private static final EmptyBorder EMPTYBORDER_SPACE
		= new EmptyBorder(10, 10, 10, 10);
	/**
	 * Gap of the flow layout.
	 */
	private static final int FLOWLAYOUT_GAP = 5;
	/**
	 * Gap of the border layout.
	 */
	private static final int BORDERLAYOUT_GAP = 4;
	/**
	 * The width of the main gui.
	 */
	private static final int MAINGUI_WIDTH = 1400;
	/**
	 * The height of the main gui.
	 */
	private static final int MAINGUI_HEIGHT = 1000;
	/**
	 * Getter function of the resize property.
	 * @return
	 * Resize integer representation.
	 */
	public static int getResize() {
		return RESIZE;
	}
	/**
	 * Getter function of the move property.
	 * @return
	 * Move integer representation.
	 */
	public static int getMove() {
		return MOVE;
	}
	/**
	 * Getter function of the select property.
	 * @return
	 * Select integer representation.
	 */
	public static int getSelect() {
		return SELECT;
	}
	/**
	 * Getter function of the delete property.
	 * @return
	 * Delete integer representation.
	 */
	public static int getDelete() {
		return DELETE;
	}
	/**
	 * Getter function of the draw property.
	 * @return
	 * Draw integer representation.
	 */
	public static int getDraw() {
		return DRAW;
	}
	/**
	 * Getter function of the default initial operation of the
	 * IDrawingMouseReaction.
	 * @return
	 * Default initial operation integer representation.
	 */
	public static int getInitialOperationDrawingReaction() {
		return INITIAL_OPERATION_IDRAWING_REACTION;
	}
	/**
	 * Getter method for the default class used.
	 * @return
	 * The implementations Class.
	 */
	public static Class<?> getMouseReactionImplementation() {
		return MOUSE_REACTION_IMPLEMENTATION;
	}
	/**
	 * Getter for the default Border Color.
	 * @return
	 * The default border color.
	 */
	public static Color getDefaultBorderColor() {
		return DEFAULT_BORDER_COLOR;
	}
	/**
	 * Getter for the default Fill Color.
	 * @return
	 * The default fill color.
	 */
	public static Color getDefaultFillColor() {
		return DEFAULT_FILL_COLOR;
	}
	/**
	 * Getter for the default thickness.
	 * @return
	 * The default value of the thickness.
	 */
	public static int getDefaultThickness() {
		return DEFAULT_THICKNESS;
	}
	/**
	 * Getter for the default shape.
	 * @return
	 * The class of the default shape.
	 */
	public static Class<? extends GeoShapes> getDefaultShape() {
		return DEFAULT_SHAPE;
	}
	/**
	 * Getter for the default implementation of IDrawingDataCore.
	 * @return
	 * The class of the implementation.
	 */
	public static Class<?> getDefaultIdrawingDataCore() {
		return DEFAULT_IDRAWING_DATA_CORE;
	}
	/**
	 * Getter for the default background color of the board.
	 * @return
	 * Color of the background of the board.
	 */
	public static Color getDefaultBoardBackground() {
		return DEFAULT_BOARD_BACKGROUND;
	}
	/**
	 * Getter for the default textBox dimension of the inspector.
	 * @return
	 * The default dimension.
	 */
	public static Dimension getInspectorTextBoxDimension() {
		return INSPECTOR_TEXTBOX_DIMENSION;
	}
	/**
	 * Getter for the default scrollpane dimension of the inspector.
	 * @return
	 * The default dimension.
	 */
	public static Dimension getInspectorScrollpaneDimension() {
		return INSPECTOR_SCROLLPANE_DIMENSION;
	}
	/**
	 * Getter for the implementation of the IPaintSaver.
	 * @return
	 * The implementation class.
	 */
	public static Class<?> getIpaintsaverImplementation() {
		return IPAINTSAVER_IMPLEMENTATION;
	}
	/**
	 * Getter for the list of concrete classes.
	 * @return
	 * ArrayList of the classes.
	 */
	public static ArrayList<Class<? extends GeoShapes>>
		getConcreteClasses() {
		return CONCRETE_CLASSES;
	}
	/**
	 * Getter for the empty border space.
	 * @return
	 * An empty border space.
	 */
	public static EmptyBorder getEmptyborderSpace() {
		return EMPTYBORDER_SPACE;
	}
	/**
	 * Getter for the flowLayoutGap.
	 * @return
	 * The value of the gap.
	 */
	public static int getFlowlayoutGap() {
		return FLOWLAYOUT_GAP;
	}
	/**
	 * Getter for the borderLayoutGap.
	 * @return
	 * The value of the gap.
	 */
	public static int getBorderlayoutGap() {
		return BORDERLAYOUT_GAP;
	}
	/**
	 * Getter for the main gui width.
	 * @return
	 * int value of width
	 */
	public static int getMainguiWidth() {
		return MAINGUI_WIDTH;
	}
	/**
	 * Getter for the main gui height.
	 * @return
	 * int value of height
	 */
	public static int getMainguiHeight() {
		return MAINGUI_HEIGHT;
	}
	/**
	  * Function that takes a class name, remove package name
	  * and return the class name only.
	  * @param classNameUnpure
	  * Full class name with the package.
	  * @return
	  * Only the class name without the package
	  */
	 public static String getClassNamePure(final String classNameUnpure) {
		 int n = classNameUnpure.length();
		 for (int i = n - 1; i > -1; i--) {
			 if (classNameUnpure.charAt(i) == '.') {
				 return classNameUnpure.substring(i + 1);
			 }
		 }
		 return classNameUnpure;
	 }
}
