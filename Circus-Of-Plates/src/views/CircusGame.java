/**
 *
 */
package views;

import game.control.states.Actions;
import game.control.states.StateControl;
import game.control.states.StateControlSingleton;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logs.LogService;

/**
 * @author Marc Magdi
 *
 */
public class CircusGame extends Application {
	/**
	 * state controller of the game.
	 */
	private StateControl stateController;
	/**
	 * Constructor.
	 */
	public CircusGame() {
		LogService.printTrace(this.getClass(),
				"Construction of CircusGame class");
		this.stateController = StateControlSingleton.getInstance();
	}

	/**
     * The main start function of the application.
     * @param args the arguments to start with
     */
    public static void main(final String[] args) {
        launch(args);
    }

	@Override
	public final void start(final Stage primaryStage) throws Exception {
		LogService.printTrace(this.getClass(),
				"void method start(Stage) is called");
		primaryStage.setTitle("Circus Plates AB2M");
		FXMLLoader myLoader = new FXMLLoader(getClass()
				.getResource("EntryPoint.fxml"));

        Parent root = myLoader.load();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
            	Actions action = Actions.KEYBOARD_TYPE;
            	action.setValue(event);
                stateController.handleAction(action);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
            	Actions action = Actions.KEYBOARD_TYPE;
            	action.setValue(event);
                stateController.handleAction(action);
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_MOVED,
				new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(final MouseEvent mouseEvent) {
            	Actions action = Actions.MOUSE_MOVE;
            	action.setValue(mouseEvent);
                stateController.handleAction(action);
		    }
		});
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        this.fullScreenApp(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getRoot().requestFocus();
	}

	/**
     * . Start the application as full screen
     * @param primaryStage the Application stage to apply the new dimensions
     */
    private void fullScreenApp(final Stage primaryStage) {
    	LogService.printTrace(this.getClass(),
    			"private void method fullScreenApp(Stage) is called");
    	Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
    }
}
