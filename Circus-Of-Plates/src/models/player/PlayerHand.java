
package models.player;

import java.awt.Point;

import models.plates.Plate;

/**
 * Interface of the player hand.
 * @author Amr
 *
 */
public interface PlayerHand {

	/**
	 * checks if the newPlate is intersecting.
	 * with the player's hand.
	 * @param newPlate the plate to be checked.
	 * @return true if plate is intersecting else false.
	 */
	boolean isPlateIntersecting(Plate newPlate);

	/**
	 * attaches the plate to the player's hand.
	 * @param newPlate
	 * the new plate to be attached.
	 */
	void attachPlate(Plate newPlate);
	
	/**
	 * Updates the score of the player.
	 */
	void updateScore();
	
	/**
	 * @return the center
	 */
	Point getCenter();

	/**
	 * Moves the center of the to the x coordinate with
	 * everything it is carrying.
	 * @param x
	 * The new x coordinate for the center of the hand.
	 */
	void moveCenterToXCoord(int x);
	
	/**
	 * Getter for the player owning this hand.
	 * @return
	 * The player that owns this hand.
	 */
	Player getPlayer();
	
	/**
	 * Setter for the player owning this hand.
	 * @param player
	 * The player that owns this hand.
	 */
	void setPlayer(Player player);
	
	/**
	 * The snapshot of the player hand.
	 * @return
	 * The snapshot of the player hand.
	 */
	PlayerHandSnapshot getSnapshot();
	
	/**
	 * Roll back to a certain snapshot of the player hand.
	 * @param snap
	 * The snapshot of the player hand to roll back to.
	 */
	void useSnapshot(PlayerHandSnapshot snap);
	
	/**
	 * closes the object and releases objects.
	 */
	void close();
	
}
