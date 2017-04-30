package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import logs.LogService;

/**
 * The entry point controller is responsible for the
 * transition for the views.
 * @author Marc Magdi
 *
 */
public class EntryPointController extends BaseController
					implements Initializable, ViewSwitcher {

	/**.
	 * The main pane containing current view
	 */
	@FXML
	private Pane mainPane;

	/**.
	 * Hash table containing the FXML view object.
	 */
	private Hashtable<String, Node> viewsTable;

	/**.
	 * Default constructor.
	 */
	public EntryPointController() {
		super();
		LogService.printTrace(this.getClass(), "Construction of"
				+ " EntryPointController"
				+ " Class which extends BaseController Class");
		this.viewsTable = new Hashtable<>();
	}

	@Override
	public final void initialize(final URL location,
			final ResourceBundle resources) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "initialize(URL, ResourceBundle) is called.");
        this.mainPane.setBackground(getBackground());
        this.mainPane.getChildren().add(getView("MainMenu"));
		this.stateController.setViewSwitcher(this);

		this.loadDefaultViewsObservers("GameZone");
		this.loadDefaultViewsObservers("Winner");
	}

	/**.
	 * @return the background to use for any shown view in the Main Area.
	 */
	private Background getBackground() {
		LogService.printTrace(this.getClass(), "void Method "
				+ "getBackground() is called.");

		Image img = new Image("file:theaterBackground.jpg");
		BackgroundSize size = new BackgroundSize(0, 0, false, false,
				false, true);
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.REPEAT,
        		BackgroundRepeat.NO_REPEAT,
        		BackgroundPosition.CENTER, size);
        Background background = new Background(bg);
        return background;
	}

	/**.
	 * set the default main menu.
	 * @param fxmlName the name of the view to load.
	 */
	private void loadDefaultViewsObservers(final String fxmlName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass()
				.getClassLoader()
				.getResource("views/" + fxmlName + ".fxml"));
			Node view = loader.load();
			viewsTable.put(fxmlName, view);
			Object controller = (Object) loader.getController();
			this.stateController.setScoreObserver(
					(Observer) controller);
		} catch (IOException e) {
			LogService.printError(this.getClass(),
					"Error in void Method initialize.");
			e.printStackTrace();
		}
	}

	@Override
	public final void switchFxml(final String fxmlName) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "switchFxml(String) is called.");
		mainPane.getChildren().clear();
		mainPane.getChildren().add(getView(fxmlName));
	}

	/**.
	 * Get a view of the given view name, if it's loaded
	 * before it give a reference to it. If this is the
	 * first time to load it it create it and return a new reference to it.
	 * @param fxmlName the name of the fxml view file.
	 * @return a reference of the loaded view object.
	 */
	private Node getView(final String fxmlName) {
		Node view = null;

		try {
			if (viewsTable.containsKey(fxmlName)) {
				view = viewsTable.get(fxmlName);
			} else {
				view = FXMLLoader.load(getClass()
						.getClassLoader()
						.getResource("views/"
						+ fxmlName + ".fxml"));
				viewsTable.put(fxmlName, view);
			}

		} catch (IOException e) {
			LogService.printError(this.getClass(),
					"Error in void Method switchFxml.");
			e.printStackTrace();
		}
		return view;
	}
}
