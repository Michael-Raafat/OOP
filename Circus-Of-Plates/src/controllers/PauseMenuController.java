package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.control.states.Actions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import logs.LogService;

/**.
 * The controller responsible for the pause menu.
 * @author Marc Magdi
 *
 */
public class PauseMenuController extends
		BaseController implements Initializable {

	@Override
	public final void initialize(final URL location,
			final ResourceBundle resources) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "initialize(URL, ResourceBundle) is called.");
	}

	/**
	 * Handle the save button click by showing a save dialog
	 * allowing the user to choose the file to save to.
	 * @param event the event of clicking the save button
	 * @throws IOException throw exception when can't save to file.
	 */
	@FXML
	private void handleSaveGame(final ActionEvent event)
			throws IOException {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleSaveGame(ActionEvent) is called.");
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json", "*.json")
            );

        File file = fileChooser.showSaveDialog(null);

		Actions action = Actions.SAVE_GAME;
		action.setValue(file);

		stateController.handleAction(Actions.SAVE_GAME);
	}

	/**.
	 * Handle the event of resuming the game by changing the state.
	 * @param event the event of clicking resume game button.
	 */
	@FXML
	private void handleResumeGame(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleResumeGame(ActionEvent) is called.");
		stateController.handleAction(Actions.RESUME_GAME);
	}

	/**.
	 * Handle return to game action.
	 * @param event the event of clicking on return to game.
	 */
	@FXML
	private void handleReturnToMenuGame(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleReturnToMenuGame(ActionEvent)"
				+ " is called.");
		stateController.handleAction(Actions.BACK_TO_MENU);
	}

	/**.
	 * Handling the quit button click to exit the game.
	 * @param event the event of clicking the button.
	 */
	@FXML
	private void handleExitGame(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleExitGame(ActionEvent) is called.");
		stateController.handleAction(Actions.EXIT);
	}
}
