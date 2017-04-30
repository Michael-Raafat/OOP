package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.control.difficulties.DifficultyType;
import game.control.states.Actions;
import game.control.states.StateControlSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logs.LogService;
import models.dto.NewGameMeta;

/**.
 * The controller for the main menu loaded in the start of the game.
 * @author Marc Magdi
 *
 */
public class MainMenuController extends
	BaseController implements Initializable {

	/**.
	 * The dialog pane for choosing difficulty
	 */
	private Parent dialogPane;
	/**.
	 * The stage containing the dialog difficulty.
	 */
    private Stage dialog;
    /**.
     * The Fxml loader used to load dialog difficulty
     */
    private FXMLLoader loader;

    /**.
     * The start new game button.
     */
    @FXML
    private Button startGameBtn;

	/**.
	 * The main area for menu
	 */
	@FXML
	private Group mainArea;

	@Override
	public final void initialize(final URL location,
			final ResourceBundle resources) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "initialize(URL, ResourceBundle) is called.");
		stateController = StateControlSingleton.getInstance();
		// loading the dialog (choose difficulty) view.
        try {
        	loader = new FXMLLoader(getClass().getClassLoader()
    			.getResource("views/SelectDifficultyDialog.fxml"));
			dialogPane = loader.load();
        } catch (IOException e) {
        	LogService.printError(this.getClass(), "Error in"
        			+ " initialize(URL, ResourceBundle) method.");
			e.printStackTrace();
		}
	}

	/**.
	 * The click event for start new game button.
	 * It shows a new dialog for the user to pick up a difficulty.
	 * @param event the event of the clicked button.
	 * @throws IOException throws exception if it can't
	 * open the view for the dialog.
	 */
	@FXML
	private void handleStartGame(final ActionEvent event)
			throws IOException {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleStartGame(ActionEvent) is called.");
		showDialog().show();
	}

	/**.
	 * Show the dialog of the difficulty chooser.
	 * @return the stage that contains the dialog.
	 */
	private Stage showDialog() {
		LogService.printTrace(this.getClass(), "private Stage Method "
				+ "showDialog is called.");
		 if (dialog == null) {
	            Scene scene = new Scene(dialogPane);
	            dialog = new Stage();
	            dialog.setScene(scene);
	            dialog.initModality(Modality.WINDOW_MODAL);
	            dialog.initOwner(startGameBtn.getScene().getWindow());
	            SelectDifficultyDialogController controller
	            		= (SelectDifficultyDialogController)
	            				loader.getController();
	        	controller.setMainMenuController(this);
        }

        return dialog;
	}

	/**.
	 * Is called back from the difficulty chooser,
	 * it set the difficulty and start the game.
	 * @param difficulty the difficulty chosen.
	 */
	public final void setDifficultyAndStart(
			final DifficultyType difficulty) {
		LogService.printTrace(this.getClass(), "void Method "
			+ "setDifficultyAndStart(DifficultyType) is called.");
		NewGameMeta meta = new NewGameMeta(difficulty,
				mainArea.getScene().getWidth(),
				mainArea.getScene().getHeight());

		Actions startGame = Actions.START_GAME;
		startGame.setValue(meta);
	    stateController.handleAction(startGame);
	}

	/**.
	 * Handle loading a saved game by opening a load dialog
	 * for the user to choose a file.
	 * @param event the event of clicking the button.
	 * @throws IOException through exception when ever there is
	 * an error with opening the file.
	 */
	@FXML
	private void handleLoadGame(final ActionEvent event)
			throws IOException {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleLoadGame(ActionEvent) is called.");
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load game");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json", "*.json")
            );

        File file = fileChooser.showOpenDialog(null);

		NewGameMeta meta = new NewGameMeta(file,
				mainArea.getScene().getWidth(),
			    mainArea.getScene().getHeight());

		Actions loadGame = Actions.LOAD_GAME;
		loadGame.setValue(meta);

		stateController.handleAction(loadGame);
	}

	/**.
	 * Handling the quit button click to exit the game.
	 * @param event the event of clicking the button.
	 */
	@FXML
	private void handleQuitGame(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleQuitGame(ActionEvent) is called.");
		stateController.handleAction(Actions.EXIT);
	}
}
