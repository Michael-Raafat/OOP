package eg.edu.alexu.csd.oop.paint;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.jnlp.BasicService;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

import com.thoughtworks.xstream.mapper.CannotResolveClassException;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.drawComponents.DrawingBoard;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
import eg.edu.alexu.csd.oop.paint.interfaces.IPaintSaver;
/**
 * Main Class for our Paint program.
 * @author Amr
 *
 */
public class PaintGUIImplementation implements ActionListener {
	/**
	 * Service for our JNLP.
	 */
	private static BasicService service = null;
	/**
	 * The framePanel to hold all the GUI pieces.
	 */
	private JFrame frame;
	/**
	 * The Panel which will be the drawing panel of
	 * our project.
	 */
	private DrawingBoard board;
	/**
	 * The panels that will hold all the components.
	 */
	private JPanel buttonPanel, framePanel;
	/**
	 * list containing the classes places.
	 */
	private ArrayList<Class<? extends GeoShapes>> files;
	/**
	 * The buttons responsible of operations.
	 */
	private JButton save, load, select,
		resize, move, delete, draw, undo,
		redo, addLib, borderC, fillC, thickness;
	/**
	 * List of the shapes to draw.
	 */
	private Choice chsLib;
	/**
	 * Modified color chooser to be shown to the user.
	 */
	private JColorChooser jcl;
	/**
	 * File manager to load or save files.
	 */
	private IPaintSaver writeReader;
	/**
	 * Class loader.
	 */
	private URLClassLoader cl;
	/**
	 * You can guess this variable function from its name.
	 */
	private ArrayList<URL> listOfAddedJars;
	/**
	 * Constructor for main GUI windows.
	 */
	PaintGUIImplementation() {
		cl = URLClassLoader.newInstance(new URL[] {});
		listOfAddedJars = new ArrayList<URL>();
		framePanel = new JPanel();
		try {
			writeReader = (IPaintSaver)
				Config.getIpaintsaverImplementation()
				.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		files = Config.getConcreteClasses();
		this.setFrame();
		this.setButtonsPanel();
		this.setColorPicker();
		this.setDrawingBoard();
		this.setDrawingInspector();
		framePanel.setBorder(Config.getEmptyborderSpace());
		frame.add(framePanel);
		frame.setVisible(true);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == select) {
			try {
				board.setOperation(0);
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
					"Must finish current action !");
			}
		} else if (e.getSource() == move) {
			try {
				board.setOperation(Config.getMove());
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
					"Must finish current action !");
			}
		} else if (e.getSource() == delete) {
			try {
				board.setOperation(Config.getDelete());
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
						"Must finish current action !");
			}
		} else if (e.getSource() == resize) {
			try {
				board.setOperation(Config.getResize());
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
						"Must finish current action !");
			}
		} else if (e.getSource() == draw) {
			try {
				board.setOperation(Config.getDraw());
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
						"Must finish current action !");
			}
		} else if (e.getSource() == undo) {
			try {
				board.undo();
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
					"No more previous steps available !");
			}
		} else if (e.getSource() == redo) {
			try {
				board.redo();
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null,
					"No more next steps available !");
			}
		} else if (e.getSource() == save) {
			saveAction();
		} else if (e.getSource() == load) {
			loadAction();
		} else if (e.getSource() == addLib) {
			dynamicClassLoading();
		} else if (e.getSource() == fillC) {
			jcl.setColor(board.getFill());
			JColorChooser.createDialog(null,
			"Fill Color", true, jcl, new ActionListener() {
				@Override
				public void actionPerformed(
						final ActionEvent e) {
					// TODO Auto-generated method stub
					board.setFill(jcl.getColor());
				}
			}, null).setVisible(true);
		} else if (e.getSource() == borderC) {
			jcl.setColor(board.getBorderC());
			JColorChooser.createDialog(null, "Border Color",
					true, jcl, new ActionListener() {
				@Override
				public void actionPerformed(
						final ActionEvent e) {
					// TODO Auto-generated method stub
					board.setBorderC(jcl.getColor());
				}
			}, null).setVisible(true);
		} else if (e.getSource() == thickness) {
			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(1);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    formatter.setCommitsOnValidEdit(true);
		    JFormattedTextField field
		    = new JFormattedTextField(formatter);
		    field.setText(String.valueOf(board.getThickness()));
		    JOptionPane.showMessageDialog(null, field);
		    board.setThickness(Integer.parseInt(field.getText()));
		}
	}
	/**
	 * Function responsible of dynamically adding classes.
	 */
	private void dynamicClassLoading() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter jclfilter
			= new FileNameExtensionFilter(
				"jar files (*.jar)", "jar");
		chooser.addChoosableFileFilter(jclfilter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(jclfilter);
		int choice = chooser.showOpenDialog(null);
		if (choice != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File chosenFile = chooser.getSelectedFile();
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(
			chosenFile.getAbsolutePath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Enumeration<JarEntry> entrie = jarFile.entries();
		URL[] urls = new URL[listOfAddedJars.size() + 1];
		try {
			listOfAddedJars.add(
				new URL("jar:file:"
			+ chosenFile.getAbsolutePath() + "!/"));
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		urls = listOfAddedJars.toArray(urls);
		cl = URLClassLoader.newInstance(urls);

		while (entrie.hasMoreElements()) {
		    JarEntry je = entrie.nextElement();
		    if (je.isDirectory()
		    	|| !je.getName().endsWith(".class")) {
		        continue;
		    }
		    // -6 because of .class
		    String className = je.getName()
		    	.substring(0, je.getName().length()
		    			- ".class".length());
		    System.out.println(className);
		    className = className.replace('/', '.');
		    Class<? extends GeoShapes> c = null;
			try {
				c = (Class<? extends GeoShapes>)
						cl.loadClass(className);
				GeoShapes test
				= (GeoShapes) c.newInstance();
				String name
				= Config.getClassNamePure(c.getName());
				int choicesLength
				= chsLib.getItemCount();
				ArrayList<String> names
				= new ArrayList<String>();
				for (int i = 0; i < choicesLength;
						i++) {
					names.add(chsLib.getItem(i));
				}
				if (names.contains(name)) {
					JOptionPane.showMessageDialog(
							null,
					"Already have a class with "
					+ "the same name added !");
				} else {
					board.addClass(c);
					chsLib.add(name);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						"Unsuitable Class : "
				+ Config.getClassNamePure(
							c.getName()));
			}
		}
	}
	/**
	 * Performs the saving action.
	 */
	private void saveAction() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter
		= new FileNameExtensionFilter(
				"xml files (*.xml)", "xml");
		FileNameExtensionFilter jsonfilter
		= new FileNameExtensionFilter(
				"json files (*.json)", "json");
		chooser.addChoosableFileFilter(xmlfilter);
		chooser.addChoosableFileFilter(jsonfilter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(xmlfilter);
		int choice = chooser.showSaveDialog(null);
		if (choice != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File chosenFile = chooser.getSelectedFile();
		if (chooser.getFileFilter().equals(xmlfilter)) {
			try {
				writeReader.writeXml(
			board.getDataCore(),
			correctName(chosenFile, "xml"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
					"Error! please try again!");
			}
		} else {
			try {
			writeReader.writeJson(board.getDataCore(),
				correctName(chosenFile, "json"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
					"Error! please try again!");
			}
		}
	}
	/**
	 * Performs the loading action.
	 */
	private void loadAction() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter
		= new FileNameExtensionFilter(
				"xml files (*.xml)", "xml");
		FileNameExtensionFilter jsonfilter
		= new FileNameExtensionFilter(
				"json files (*.json)", "json");
		chooser.addChoosableFileFilter(xmlfilter);
		chooser.addChoosableFileFilter(jsonfilter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(xmlfilter);
		int choice = chooser.showOpenDialog(null);
		if (choice != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File chosenFile = chooser.getSelectedFile();
		if (chooser.getFileFilter().equals(xmlfilter)) {
			try {
				board.setDataCore(
				writeReader.readXml(cl, chosenFile));
				board.repaint();
			} catch (CannotResolveClassException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,
					"Error !This save file contains"
					+ " unadded shapes. Please add them"
					+ " before loading.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
					"Error ! please try again");
			}
		} else {
			try {
				board.setDataCore(
				writeReader.readJson(cl, chosenFile));
				board.repaint();
			} catch (CannotResolveClassException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
					"Error !This save file contains"
					+ " unadded shapes. Please add them"
					+ " before loading.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
					"Error ! please try again");
			}
		}
	}
	/**
	 * Sets the buttons and the button Panel.
	 */
	private void setButtonsPanel() {
		ArrayList<JButton> tempList = new ArrayList<JButton>();
		save = new JButton("Save");
		tempList.add(save);
		load = new JButton("Load");
		tempList.add(load);
		select = new JButton("Select");
		tempList.add(select);
		resize = new JButton("Resize");
		tempList.add(resize);
		move = new JButton("Move");
		tempList.add(move);
		delete = new JButton("Delete");
		tempList.add(delete);
		draw = new JButton("Draw");
		tempList.add(draw);
		undo = new JButton("<");
		tempList.add(undo);
		redo = new JButton(">");
		tempList.add(redo);
		addLib = new JButton("Add shape");
		tempList.add(addLib);
		chsLib = new Choice();
		fillC = new JButton("Fill Color");
		tempList.add(fillC);
		borderC = new JButton("Border Color");
		tempList.add(borderC);
		thickness = new JButton("Thickness");
		tempList.add(thickness);
		for (int i = 0; i < tempList.size(); i++) {
			tempList.get(i).addActionListener(this);
		}
		buttonPanel = new JPanel();
		int gap = Config.getFlowlayoutGap();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,
				gap, gap));
		buttonPanel.setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);

		for (int i = 0; i < tempList.size(); i++) {
			buttonPanel.add(tempList.get(i));
		}
		for (int i = 0; i < files.size(); i++) {
	 	        chsLib.add(
	 	        Config.getClassNamePure(
	 	        files.get(i).getName()));
		}
		chsLib.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent e) {
				// TODO Auto-generated method stub
				try {
					board.setShape(
						chsLib.getSelectedIndex());
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null,
					"Unknown shape or invalid Class!"
					+ " please choose another");
				}
			}
		});
		buttonPanel.add(chsLib);
		buttonPanel.setBorder(Config.getEmptyborderSpace());
		framePanel.add(buttonPanel, BorderLayout.PAGE_START);
	}
	/**
	 * Sets the framePanel.
	 */
	private void setFrame() {
		frame = new JFrame("Paint Program");
		int gap = Config.getBorderlayoutGap();
		framePanel.setLayout(new BorderLayout(gap, gap));
		frame.setSize(Config.getMainguiWidth(),
				Config.getMainguiHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Sets the drawing board.
	 */
	private void setDrawingBoard() {
		board = new DrawingBoard();
		board.setClasses(files);
		framePanel.add(board, BorderLayout.CENTER);
	}
	/**
	 * Adds the inspector to the framePanel.
	 */
	private void setDrawingInspector() {
		board.getInpsector().setBorder(Config.getEmptyborderSpace());
		framePanel.add(board.getInpsector(), BorderLayout.EAST);
	}
	/**
	 * Sets the Color picker settings.
	 */
	private void setColorPicker() {
		jcl = new JColorChooser(Color.BLACK);
		AbstractColorChooserPanel[] panels = jcl.getChooserPanels();
		for (AbstractColorChooserPanel accp : panels) {
		   if (!accp.getDisplayName().equals("RGB")) {
		      jcl.removeChooserPanel(accp);
		   }
		}
		jcl.setPreviewPanel(new JPanel());
	}
	/**
	 * Responsible of running the actual GUI.
	 * @param s
	 * cmd arguments.
	 */
    public static void main(final String...s) {
    	/*try {
    		service = (BasicService)
    	 ServiceManager.lookup("javax.jnlp.BasicService");
     	} catch (UnavailableServiceException e) {
     		e.printStackTrace();
     	}*/
        new PaintGUIImplementation();
    }
    /**
     * Corrects a file given name so that it has the right extension.
     * @param chosenFile
     * The file name.
     * @param extension
     * The required extension.
     * @return
     * The file name with the required extension.
     */
	private File correctName(final File chosenFile,
			final String extension) {
		String[] name = chosenFile.getName().split(".");
		if (name.length != 0
			&& name[name.length - 1].equals(extension)) {
			return chosenFile;
		} else {
			File file = new File(
				chosenFile.toString() + "." + extension);
			return file;
		}
	}
}
