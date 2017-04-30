package game.control.states;

import java.util.List;
import java.util.Observer;

import file.manager.JsonObjectLoader;
import game.control.GameManager;
import game.control.GameManagerImp;
import game.control.GameManagerSnapShot;
import logs.LogService;
import models.dto.NewGameMeta;

/**
 * The state of the user being in the main menu.
 * @author Marc Magdi.
 *
 */
public class MenuState implements GameState {
	/**
	 * Constructor for the menu state.
	 */
	public MenuState() {
		LogService.printTrace(this.getClass(),
				"Construction of MenuState class");
	}

	@Override
	public final void handleAction(final StateControl c,
			final Actions action) {
		LogService.printTrace(this.getClass(),
				"void Method handleAction is called");
		GameManager manager;
		NewGameMeta meta;
		switch (action) {
			case START_GAME:
				LogService.printInfo(this.getClass(),
						"User chooses to Start game.");
				meta = (NewGameMeta) action.getValue();
				manager = new GameManagerImp(
						(int) meta.getWidth(),
						(int) meta.getHeight(),
						meta.getDifficulty());
				startGameTransition(manager, c);
				break;
			case LOAD_GAME:
				LogService.printInfo(this.getClass(),
						"User chooses to Load game.");
				meta = (NewGameMeta) action.getValue();
				GameManagerSnapShot snapshot =
					(GameManagerSnapShot)
					JsonObjectLoader.loadObject(
						meta.getLoadPath(),
						GameManagerSnapShot.class);
				manager = new GameManagerImp(
						(int) meta.getWidth(),
						(int) meta.getHeight(),
						snapshot);
				startGameTransition(manager, c);
				break;
			case EXIT:
				LogService.printInfo(this.getClass(),
						"User chooses to Exit game.");
				System.exit(0);
				break;
			default:
				break;
		}
	}

	/**
	 * Start the game.
	 * @param manager
	 * Game manager used in game.
	 * @param control
	 * The control service.
	 */
	private void startGameTransition(final GameManager manager,
			final StateControl control) {
		LogService.printTrace(this.getClass(), "void method "
				+ "StartGameTransition(InGameManager,"
				+ " StateControl).");
		control.updateCurrentView("GameZone");
		control.setState(new PlayState(manager));
		control.handleAction(Actions.START_GAME);

		List<Observer> scoreObserver = control.getScoreObserver();
		for (Observer o: scoreObserver) {
			manager.attachObserverToPlayers(o);
		}
	}
}
