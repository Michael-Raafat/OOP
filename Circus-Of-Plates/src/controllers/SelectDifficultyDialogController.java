package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import game.control.difficulties.DifficultyType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logs.LogService;

/**.
 * Controller of the select difficulty dialog.
 * @author Marc Magdi
 *
 */
public class SelectDifficultyDialogController extends
				BaseController implements Initializable  {

	/**.
	 * The main menu controller, used to set the chosen dialog.
	 */
	private MainMenuController controller;

	@Override
	public final void initialize(final URL location,
			final ResourceBundle resources) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "initialize(URL, ResourceBundle) is called.");
	}

	/**.
	 * Select a difficulty and sets it in the main menu controller.
	 * @param event the event of choosing of the difficulties.
	 */
	@FXML
	private void selectDifficulty(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "selectDifficulty(ActioEvent) is called.");
		Button btn = (Button) event.getSource();
		String text = btn.getText();

		switch (text) {
			case "Easy":
				this.controller.setDifficultyAndStart(
						DifficultyType.EASY);
				break;
			case "Medium":
				this.controller.setDifficultyAndStart(
						DifficultyType.MEDIUM);
				break;
			case "Hard":
				this.controller.setDifficultyAndStart(
						DifficultyType.HARD);
				break;
		default:
			break;
		}

		Stage stage = (Stage) btn.getScene().getWindow();
	    stage.close();
	}

	/**.
	 * Set the main menu controller so it can be used to
	 * set the chosen difficulty.
	 * @param menuController the main menu controller to set.
	 */
	public final void setMainMenuController(
			final MainMenuController menuController) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "setMainMenuController(MainMenuController)"
				+ " is called.");
		this.controller = menuController;
	}
}
