package game.control.states;

import logs.LogService;

/**
 * class responsible for GameOverState.
 * @author Michael.
 *
 */
public class GameOverState implements GameState {

	@Override
	public final void handleAction(final StateControl c,
			final Actions action) {
		switch (action) {
			case BACK_TO_MENU:
				LogService.printInfo(this.getClass(),
						"User chooses to go back"
						+ " to Menu");
				c.updateCurrentView("MainMenu");
				c.setState(new MenuState());
				break;
			case EXIT:
				LogService.printInfo(this.getClass(),
						"User chooses to Exit");
				System.exit(0);
				break;
			default:
				break;
		}
	}

}
