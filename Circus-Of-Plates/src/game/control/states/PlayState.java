package game.control.states;

import game.control.GameThread;
import game.control.GameManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import logs.LogService;
import models.player.input.manager.PlayerInputManager;
import models.player.input.manager.PlayerInputManagerSingelton;
/**
 *
 * @author Marc Magdi.
 *
 */
public class PlayState implements GameState {
	/**
	 * inputManager handle movement.
	 */
	private PlayerInputManager inputManager;
	/**
	 * Game manager.
	 */
	private GameManager manager;
	/**
	 * The game thread.
	 */
	private GameThread gameThread;
	/**
	 * Next state.
	 */
	private GameState nextState;
	/**
	 * Constructor.
	 * @param gameManager
	 * The game manager to use.
	 */
	public PlayState(final GameManager gameManager) {
		LogService.printTrace(this.getClass(),
				"Construction of PlayState class");
		this.inputManager = PlayerInputManagerSingelton.getInstance();
		this.manager = gameManager;
		this.gameThread = new GameThread(manager);
		this.nextState = new PauseState(this, gameThread);
	}

	@Override
	public final void handleAction(final StateControl c,
			final Actions action) {
		LogService.printTrace(this.getClass(),
				"void Method handleAction is called.");

		switch (action) {
			case START_GAME:
				LogService.printInfo(this.getClass(),
						"User chooses Start game.");
				gameThread.start();
				break;
			case KEYBOARD_TYPE:
				KeyEvent keyEvent = (KeyEvent) action.getValue();
				if (keyEvent.getCode() == KeyCode.ESCAPE
						&& KeyEvent.KEY_RELEASED
						== keyEvent.getEventType()) {
					LogService.printInfo(this.getClass(),
							"User press Escape.");
					gameThread.pause();
					c.updateCurrentView("PauseMenu");
					c.setState(nextState);
				} else {
					this.inputManager.respondToMoveEvent(
						keyEvent);
				}
				break;
			case GAME_OVER:
				LogService.printInfo(this.getClass(),
						"Game is Finished.");
				c.updateCurrentView("Winner");
				c.setState(new GameOverState());
				gameThread.stop();
				break;
			case RESUME_GAME:
				LogService.printInfo(this.getClass(),
						"User chooses Resume game.");
				gameThread.resume();
				break;
			case MOUSE_MOVE:
				LogService.printInfo(this.getClass(),
						"User who controls"
						+ " mouse is moving.");
				MouseEvent event = (MouseEvent) action.getValue();
				this.inputManager.respondToMoveEvent(
						(int) event.getSceneX());
				break;
			default:
				break;
		}
	}
}
