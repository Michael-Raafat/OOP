
package models.player.input.manager;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.player.Player;

/**
 * Interface for the player input manager.
 * @author Bishoy Nader
 *
 */
public interface PlayerInputManager {

	/**
	 * respond to an event by the main thread.
	 * @param keyEvent the event to respond to.
	 */
	void respondToMoveEvent(KeyEvent keyEvent);

	/**
	 * respond to an event by the main thread.
	 * @param xLocation the direction of motion.
	 */
	void respondToMoveEvent(int xLocation);

	/**
	 * set the keyboard player with its controls.
	 * @param player reference to the player.
	 * @param left keyCode to move left.
	 * @param right keyCode to move right.
	 */
	void setFirstPlayer(Player player, KeyCode left, KeyCode right);
	
	/**
	 * set the keyboard player.
	 * @param player reference to the player.
	 */
	void setSecondPlayer(Player player);
}
