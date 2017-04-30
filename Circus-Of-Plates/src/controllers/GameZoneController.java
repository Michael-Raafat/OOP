package controllers;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import game.control.states.Actions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import logs.LogService;
import models.player.PlayerNumber;
import models.player.PlayerObserveDataObject;

/**.
 * The controller responsible for managing the playing area.
 * @author Marc Magdi
 *
 */
public class GameZoneController extends BaseController
	implements Initializable, GUIController, Observer {

	/**.
	 * The needed score to get to win the game.
	 */
	private static final int GAME_WIN_SCORE = 10;

	/**
	 * The main area for playing.
	 */
	@FXML
	private Group mainArea;

	/**
	 * the label of the first player score.
	 */
	@FXML
	private Label firstPlayerScoreText;

    /**
     * The group to add the game elements to.
     */
    private Group gameGroup;
    /**
     * First player score.
     */
    private int firstPlayerScore;
    /**
     * Second player score.
     */
    private int secondPlayerScore;
	/**
	 * the label of the second player score.
	 */
	@FXML
	private Label secondPlayerScoreText;

	@Override
	public final void initialize(final URL arg0,
			final ResourceBundle arg1) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "initialize(URL, ResourceBundle) is called.");
    	NodeServiceSingelton.getInstance().registerController(this);
    	resetScores();
    	gameGroup = new Group();
    	this.mainArea.getChildren().add(gameGroup);
	}

	/**
	 * reset the two scores and the two labels.
	 */
	private void resetScores() {
		firstPlayerScore = 0;
    	firstPlayerScoreText.setText(firstPlayerScore + "");
    	secondPlayerScore = 0;
    	secondPlayerScoreText.setText(secondPlayerScore + "");
	}

	@Override
	public final void update(final Observable arg0, final Object arg1) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "update(Observable, Object) is called.");

		if (arg1 instanceof PlayerObserveDataObject) {
			PlayerObserveDataObject observerData
					= (PlayerObserveDataObject) arg1;
			int score = observerData.getScore();
			Platform.runLater(
				() -> {
					this.updateScore(observerData
						.getPlayerNumber(), score);
					this.checkWinner(score);
				}
			);
		}
	}

	/**.
	 * Update the last scored player.
	 * @param playerNumber the player that has just scored.
	 * @param score the new score of the player.
	 */
	private void updateScore(final PlayerNumber playerNumber,
			final int score) {
		switch (playerNumber) {
			case FIRST:
				firstPlayerScore = score;
				firstPlayerScoreText.setText(
						score + "");
				break;
			case SECOND:
				secondPlayerScore = score;
				secondPlayerScoreText.setText(
						score + "");
				break;
			default:
				break;
		}
	}

	/**.
	 * Check if there is a winner, and change the
	 * current state if it found a one.
	 * @param score the score to check the winner on.
	 */
	private void checkWinner(final int score) {
		if (score == GAME_WIN_SCORE || score < 0) {
			resetScores();
	    	this.stateController.handleAction(
	    			Actions.GAME_OVER);
		}
	}

	@Override
	public final void updateNodes(final List<Node> toRegisterNodes,
			final List<Node> toUnregisterNodes) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "updateNodes(List<Node>"
				+ ", list<Node>) is called.");

		Platform.runLater(
				() -> {
					this.removeNodes(toUnregisterNodes);
					this.addNodes(toRegisterNodes);
				}
			);
	}

	/**.
	 * Remove the current nodes by disabling them and
	 * making their opacity being zero.
	 * @param toUnregisterNodes the list of nodes to be removed.
	 */
	private void removeNodes(final List<Node> toUnregisterNodes) {
		for (Node node : toUnregisterNodes) {
			node.setDisable(true);
			node.setOpacity(0);
		}
		toUnregisterNodes.clear();
	}

	/**.
	 * Add the given nodes to the current view,
	 * by switching their opacity and disabled statues
	 * or by adding them to the view.
	 * @param toRegisterNodes the list of nodes to add.
	 */
	private void addNodes(final List<Node> toRegisterNodes) {
		for (Node node : toRegisterNodes) {
			node.setDisable(false);
			node.setOpacity(1);
			if (!this.gameGroup.getChildren().contains(node)) {
				this.gameGroup
				.getChildren().add(node);
			}
		}
		toRegisterNodes.clear();
	}

	@Override
	public final void clearNodes() {
		LogService.printTrace(this.getClass(),
				"void Method clearNodes is called.");
		firstPlayerScore = 0;
		secondPlayerScore = 0;
		firstPlayerScoreText.setText(firstPlayerScore + "");
		secondPlayerScoreText.setText(secondPlayerScore + "");
		Platform.runLater(
				() -> {
					this.gameGroup.getChildren().clear();
				}
			);
	}
}
