package eg.edu.alexu.csd.oop.paint.drawComponents;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingDataCore;
/**
 * Inspector class that contacts with the data and
 * the board and allows user input for more precise
 * drawings.
 * @author Amr
 *
 */
public class DrawingInspector extends JPanel implements ActionListener {
	/**
	 * UID for serialization.
	 */
	private static final long serialVersionUID = -1572007166527082191L;
	/**
	 * The data core where we get the data from.
	 */
	private IDrawingDataCore dataCore;
	/**
	 * The board responsible of drawing the shapes.
	 */
	private DrawingBoard board;
	/**
	 * Scroll pane containing list of shapes.
	 */
	private JScrollPane pane;
	/**
	 * List of strings that will contain all the shapes.
	 */
	private JList<String> list;
	/**
	 * Labels that will be used in the inspector.
	 */
	private JLabel centerXLabel, widthLabel,
		centerYLabel, heightLabel;
	/**
	 * Text fields that will show/set shapes properties.
	 */
	private JTextField centerXBox, centerYBox,
	  	widthBox, heightBox;
	/**
	 * Getter for the data core.
	 * @param data
	 * The used data core.
	 */
	  public final void setDataCore(final IDrawingDataCore data) {
		  dataCore = data;
	  }
	 /**
	  * Constructor for the inspector.
	  * @param core
	  * The data object that will be used in the inspector.
	  * @param tempBoard
	  * The board that will be synced with the inspector.
	  */
	 public DrawingInspector(final IDrawingDataCore core,
			 final DrawingBoard tempBoard) {
		 this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		 list = new JList<>();
		 dataCore = core;
		 Dimension textBoxDimension
		 = Config.getInspectorTextBoxDimension();
		 board = tempBoard;
		 centerXLabel = new JLabel("X-coord :");
		 centerXBox = new JTextField();
		 centerXBox.setPreferredSize(textBoxDimension);
		 centerXBox.addActionListener(this);
		 centerXBox.setMaximumSize(
			     new Dimension(Integer.MAX_VALUE,
			    centerXBox.getPreferredSize().height));
		 centerYLabel = new JLabel("Y-coord :");
		 centerYBox = new JTextField();
		 centerYBox.setPreferredSize(textBoxDimension);
		 centerYBox.addActionListener(this);
		 centerYBox.setMaximumSize(
			     new Dimension(Integer.MAX_VALUE,
			    centerYBox.getPreferredSize().height));
		 widthLabel = new JLabel("Width :");
		 widthBox = new JTextField();
		 widthBox.addActionListener(this);
		 widthBox.setPreferredSize(textBoxDimension);
		 widthBox.setMaximumSize(
			     new Dimension(Integer.MAX_VALUE,
			    widthBox.getPreferredSize().height));
		 heightLabel = new JLabel("Height :");
		 heightBox = new JTextField();
		 heightBox.setPreferredSize(textBoxDimension);
		 heightBox.addActionListener(this);
		 heightBox.setMaximumSize(
			     new Dimension(Integer.MAX_VALUE,
			    heightBox.getPreferredSize().height));
		 pane = new JScrollPane(list);
		 pane.setPreferredSize(
				 Config.getInspectorScrollpaneDimension());
		 pane.setMaximumSize(
			     new Dimension(Integer.MAX_VALUE,
			    pane.getPreferredSize().height * 2));
		 list.setSelectionMode(
				ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 list.setLayoutOrientation(JList.VERTICAL);
		 list.setVisibleRowCount(-1);
		 this.add(pane);
		 this.add(centerXLabel);
		 this.add(centerXBox);
		 this.add(centerYLabel);
		 this.add(centerYBox);
		 this.add(widthLabel);
		 this.add(widthBox);
		 this.add(heightLabel);
		 this.add(heightBox);
	 }
	 /**
	  * Function that gets the selected shape width.
	  * If more than one is selected and all have the same width,
	  * it returns it.
	  * Otherwise it returns -1.
	  * @return
	  * Width of selected shape(s) or -1.
	  */
	 private int goWidth() {
		 int temp = 0;
		 int choosen = -1;
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 if (dataCore.getShapes().get(i).isSelected()) {
				 temp++;
				 if (temp == 1) {
					 choosen = i;
				 } else {
					 if (dataCore.getShapes()
							 .get(i).getWidth()
						 != dataCore.getShapes().
						 get(choosen).getWidth()) {
						 return -1;
					 }
				 }
			 }
		 }
		 if (choosen == -1) {
			 return -1;
		 }
		 return dataCore.getShapes().get(choosen).getWidth();
	 }
	 /**
	  * Function that gets the selected shape height.
	  * If more than one is selected and all have the same height,
	  * it returns it.
	  * Otherwise it returns -1.
	  * @return
	  * Height of selected shape(s) or -1.
	  */
	 private int goHeight() {
		 int temp = 0;
		 int choosen = -1;
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 if (dataCore.getShapes().get(i).isSelected()) {
				 temp++;
				 if (temp == 1) {
					 choosen = i;
				 } else {
					 if (dataCore.getShapes().
						get(i).getHeight()
						!= dataCore.getShapes().
						get(choosen).getHeight()) {
						 return -1;
					 }
				 }
			 }
		 }
		 if (choosen == -1) {
			 return -1;
		 }
		 return dataCore.getShapes().get(choosen).getHeight();
	 }
	 /**
	  * Function that gets the selected shape center.
	  * If more than one is selected and all have the same center,
	  * it returns it.
	  * Otherwise it returns a point of (-1, -1).
	  * @return
	  * Center of selected shape(s) or (-1,-1).
	  */
	 private Point goCenter() {
		 int temp = 0;
		 int choosen = -1;
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 if (dataCore.getShapes().get(i).isSelected()) {
				 temp++;
				 if (temp == 1) {
					 choosen = i;
				 } else {
					 if (!dataCore.getShapes().get(i)
							 .getCenter().equals(
							 dataCore.getShapes()
							 .get(choosen).
							 getCenter())) {
						 return new Point(-1, -1);
					 }
				 }
			 }
		 }
		 if (choosen == -1) {
			 return new Point(-1, -1);
		 }
		 return dataCore.getShapes().get(choosen).getCenter();
	 }
	 /**
	  * Function that updates the inspector.
	  * Calling the goWidth, goHeight, goCenter
	  * and updating the textboxes accordingly.
	  */
	 public final void updateInspector() {
		 if (goWidth() == -1) {
			 widthBox.setText("");
		 } else {
			 widthBox.setText(String.valueOf(goWidth()));
		 }
		 if (goHeight() == -1) {
			 heightBox.setText("");
		 } else {
			 heightBox.setText(String.valueOf(goHeight()));
		 }
		 if (goCenter().x == -1) {
			 centerXBox.setText("");
		 } else {
			 centerXBox.setText(String.valueOf(goCenter().x));
		 }
		 if (goCenter().y == -1) {
			 centerYBox.setText("");
		 } else {
			 centerYBox.setText(String.valueOf(goCenter().y));
		 }
	 }
	 /**
	  * Function that updates the hierarchy accordingly.
	  * Listing all the shapes and highlighting selected ones.
	  */
	 public final void updateHierachy() {
		 DefaultListModel<String> listModel
		 = new DefaultListModel<String>();
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 String name = Config.getClassNamePure(
						dataCore.getShapes().get(i).getMyClass());
			 if (listModel.contains(name)) {
				 int c = 1;
				 StringBuilder f
				 = new StringBuilder(name + " " +  c);
				 while (listModel.contains(f.toString())) {
					 c++;
					 f
					 = new StringBuilder(name + " " +  c);
				 }
				 listModel.addElement(f.toString());
			 } else {
				 listModel.addElement(
				name);
			 }
		 }
		 list = new JList<>(listModel);
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 if (dataCore.getShapes().get(i).isSelected()) {
				 list.addSelectionInterval(i, i);
			 }
		 }
		 list.setSelectionMode(
			ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 list.setLayoutOrientation(JList.VERTICAL);
		 list.setVisibleRowCount(-1);
		 list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(
					final ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i
					< dataCore.getShapes().size(); i++) {
					GeoShapes cur
				= dataCore.getShapes().get(i);
					if (cur.isSelected()
						&& !list.isSelectedIndex(i)) {
						cur.setSelected(false);
					} else if (!cur.isSelected()
						&& list.isSelectedIndex(i)) {
						cur.setSelected(true);
					}
				}
				board.repaint();
			}
		});
		 Point temp = pane.getViewport().getViewPosition();
		 pane.setViewportView(list);
		 pane.getViewport().setViewPosition(temp);
	 }
	 /**
	  * Resize the selected shapes to the newly given widht and height.
	  * @param width
	  * User inputed width.
	  * @param height
	  * User inputed height.
	  */
	 private void resizeShapesToWidthHeight(
			 final int width, final int height) {
		 int dx, dy;
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 GeoShapes cur = dataCore.getShapes().get(i);
			 if (cur.isSelected()) {
				 if (width == -1) {
						dx = cur.getWidth();
					 } else {
						 dx = width;
					 }
					 if (height == -1) {
							dy = cur.getHeight();
						 } else {
							 dy = height;
						 }
				 dataCore.getShapes().set(i,
					cur.resize((dx - cur.getWidth()) / 2,
						(dy - cur.getHeight()) / 2));
			 }
		 }
		 board.repaint();
	 }
	 /**
	  * Move the selected shapes center to the user chosen ones.
	  * @param x
	  * User inputed coordinate x.
	  * @param y
	  * User inputed coordinate y.
	  */
	 private void moveShapesTo(final int x, final int y) {
		 int dx, dy;
		 for (int i = 0; i < dataCore.getShapes().size(); i++) {
			 GeoShapes cur = dataCore.getShapes().get(i);
			 if (cur.isSelected()) {
				 if (x == -1) {
					dx = cur.getCenter().x;
				 } else {
					 dx = x;
				 }
				 if (y == -1) {
						dy = cur.getCenter().y;
					 } else {
						 dy = y;
					 }
				 dataCore.getShapes().set(i,
					cur.move(dx - cur.getCenter().x,
						dy - cur.getCenter().y));
			 }
		 }
		 board.repaint();
	 }

	@Override
	public final void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == centerXBox) {
			moveShapesTo(Integer.valueOf(centerXBox.getText()), -1);
			this.updateInspector();
		}
		if (e.getSource() == centerYBox) {
			moveShapesTo(-1, Integer.valueOf(centerYBox.getText()));
			this.updateInspector();
		}
		if (e.getSource() == widthBox) {
			resizeShapesToWidthHeight(
				Integer.valueOf(widthBox.getText()), -1);
			this.updateInspector();
		}
		if (e.getSource() == heightBox) {
			resizeShapesToWidthHeight(-1,
				Integer.valueOf(heightBox.getText()));
			this.updateInspector();
		}
	}
}
