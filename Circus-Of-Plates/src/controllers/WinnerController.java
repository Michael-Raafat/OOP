package controllers;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import game.control.states.Actions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import logs.LogService;
import models.player.PlayerNumber;
import models.player.PlayerObserveDataObject;
/**.
 * The controller of the winner view that shows the winner player.
 * @author Marc Magdi
 *
 */
public class WinnerController extends
	BaseController implements Initializable, Observer {

	/**.
	 * The winner player.
	 */
	@FXML
	private Label winnerText;

	@Override
	public final void initialize(final URL arg0,
			final ResourceBundle arg1) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "initialize(URL, ResourceBundle) is called.");
		this.winnerText.setTextFill(Color.WHITE);
	}

	/**.
	 * Handle the action of choosing to going back to the main menu.
	 * @param event the event of clicking the button.
	 */
	@FXML
	private void handleBackToMenu(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleBackToMenu(ActionEvent) is called.");
		stateController.handleAction(Actions.BACK_TO_MENU);
	}

	/**.
	 * Handling the quit button click to exit the game.
	 * @param event the event of clicking the button.
	 */
	@FXML
	private void handleClose(final ActionEvent event) {
		LogService.printTrace(this.getClass(), "private void Method "
				+ "handleClose(ActionEvent) is called.");
		this.stateController.handleAction(Actions.EXIT);
	}

	/**.
	 * passes the winner to the winner controller
	 * @param number the winner either first or second player
	 */
	public final void setWinner(final PlayerNumber number) {
		LogService.printTrace(this.getClass(), "winner is setted ");
		switch (number) {
		case FIRST:
			winnerText.setText("Player 1 wins");
			break;
		case SECOND:
			winnerText.setText("Player 2 wins");
			break;
		default:
			break;
		}
	}

	@Override
	public final void update(final Observable o, final Object arg) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "update(Observable, Object) is called.");
		if (arg instanceof PlayerObserveDataObject) {
			PlayerObserveDataObject observerData
			= (PlayerObserveDataObject) arg;
			Platform.runLater(
				() -> {
					notifyWinnerController(observerData);
				}
			);
		}
	}

	/**
	 * notifies the winner controller with the winner.
	 * @param observerData the date to notify the controller with.
	 */
	private void notifyWinnerController(final 
				PlayerObserveDataObject observerData) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "notifyWinnerController(PlayerObserveDataObject) is called.");
		if (observerData.getScore() < 0) {
			switch (observerData.getPlayerNumber()) {
			case FIRST:
				setWinner(PlayerNumber.SECOND);
				break;
			case SECOND:
				setWinner(PlayerNumber.FIRST);
				break;
			default:
				break;
			}
		} else {
			setWinner(observerData.getPlayerNumber());
		}
	}
}
