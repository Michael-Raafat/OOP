package game.control.states;

import java.io.File;

import file.manager.JsonObjectSaver;
import game.control.GameThread;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logs.LogService;
/**
 *
 * @author Marc Magdi.
 *
 */
public class PauseState implements GameState {
	/**
	 * Previous game state.
	 */
	private GameState previousState;
	/**
	 * Manager of the state.
	 */
	private GameThread thread;
	/**
	 * Constructor.
	 * @param previous
	 * The game previous state.
	 * @param gameThread
	 * The game thread.
	 */
	public PauseState(final GameState previous,
			final GameThread gameThread) {
		LogService.printTrace(this.getClass(),
				"Construction of PauseState class");
		previousState = previous;
		thread = gameThread;
	}
	@Override
	public final void handleAction(final StateControl c,
			final Actions action) {
		LogService.printTrace(this.getClass(),
				"void Method handleAction is called");
		switch (action) {
			case KEYBOARD_TYPE:
				KeyEvent event = (KeyEvent) action.getValue();
				if (event.getCode().equals(KeyCode.ESCAPE)
					&& KeyEvent.KEY_RELEASED
					== event.getEventType()) {
					LogService.printInfo(this.getClass(),
							"User press Escape");
					resumeGame(c, Actions.RESUME_GAME);
				}
				break;
			case RESUME_GAME:
				LogService.printInfo(this.getClass(),
						"User chooses Resume game.");
				resumeGame(c, Actions.RESUME_GAME);
				break;
			case SAVE_GAME:
				LogService.printInfo(this.getClass(),
						"User chooses Save game.");
				JsonObjectSaver.saveObject(
						thread.getGameSnapshot(),
						(File) action.getValue());
				break;
			case BACK_TO_MENU:
				LogService.printInfo(this.getClass(),
						"User chooses Back to Menu.");
				thread.stop();
				c.updateCurrentView("MainMenu");
				c.setState(new MenuState());
				break;
			case EXIT:
				LogService.printInfo(this.getClass(),
						"User chooses Exit game.");
				System.exit(0);
				break;
		default:
			break;
		}
	}
	/**
	 * Resume game.
	 * @param c
	 * State control.
	 * @param action
	 * The action to give to the other node.
	 */
	private void resumeGame(final StateControl c, final Actions action) {
		LogService.printTrace(this.getClass(), "void method "
				+ "resumeGame(StateControl, Actions)"
				+ " is called");
		c.updateCurrentView("GameZone");
		c.setState(previousState);
		previousState.handleAction(c, action);
	}
}
