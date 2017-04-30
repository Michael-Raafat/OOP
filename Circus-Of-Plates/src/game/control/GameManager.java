package game.control;

import java.util.Observer;

/**
 * The in game manager.
 * @author Amr
 *
 */
public interface GameManager {
	/**
	 * The method responsible for updating
	 * all game objects, per example: changing
	 * their current location.
	 */
	void update();
	/**
	 * Creats a snapshot of the InGameManager.
	 * @return
	 * A snapshot of the in game manager.
	 */
	GameManagerSnapShot getSnapshot();
	/**
	 * attaches an observer to the two players.
	 * @param observer to be attached.
	 */
	void attachObserverToPlayers(Observer observer);
	/**
	 * ends the game and free resources.
	 */
	void endGame();
	/**
	 * Checks if the game is closed.
	 * @return
	 * True if the game is closed.
	 */
	boolean isClosed();
}
