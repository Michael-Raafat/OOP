package game.control.states;

/**
 * The current game state.
 * @author Marc.
 *
 */
public interface GameState {
	/**
	 * Performs the current action of the game.
	 * @param c the context that controls the states.
	 * @param action the action taken on the state.
	 */
	public void handleAction(StateControl c, Actions action);
}
