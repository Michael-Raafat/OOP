
package models.player;

import java.util.Observer;

import javafx.scene.Node;
import models.plates.Plate;

/**
 * Interface of the player.
 * @author Amr
 *
 */
public interface Player {

	/**
	 * Checks if a plate intersects with any of the player hands.
	 * When plate intersects with a player hand, add it to the player score
	 * and ask the hand to update the score.
	 * @param newPlate
	 * check which list to add plate in and if it intersects.
	 * @return true if plate intersect and is added to any list
	 * false if there is no intersection.
	 */
	boolean attachPlate(Plate newPlate);
	
	/**
	 * increases the score of the player by a specific value.
	 * @param value to add to player's score.
	 */
	void increaseScoreByValue(int value);
	
	/**
	 * @return the score
	 */
	int getScore();

	/**
	 * @param leftMove the leftMove to set
	 */
	void setLeftMove(boolean leftMove);

	/**
	 * @param rightMove the rightMove to set
	 */
	void setRightMove(boolean rightMove);

	/**
	 * Moves the center x coordinate of the player to a specified one,
	 * suitable for mouse movement.
	 * @param x
	 * New x coordinate.
	 */
	void moveX(final int x);
	
	/**
	 * Returns a graphical node representation.
	 * The player must extend GraphicalGameObject.
	 * @return
	 * The node of the player.
	 */
	Node getNode();
	
	/**
	 * Getter for the right hand.
	 * @return
	 * The right hand of the player.
	 */
	PlayerHand getRightHand();
	
	/**
	 * Getter for the left hand.
	 * @return
	 * The left hand of the player.
	 */
	PlayerHand getLeftHand();
	
	/**
	 * Creates a snapshot of the player.
	 * @return
	 * Snapshot.
	 */
	PlayerSnapshot getSnapshot();
	
	/**
	 * Uses the snapshot to roll back to a certain version.
	 * @param snap
	 * The snapshot of the player.
	 */
	void useSnapshot(PlayerSnapshot snap);
	
	/**
	 * Updates the player.
	 */
	void update();

	/**
	 * adds observer to the player.
	 * @param observer the observer to be added.
	 */
	void addObserver(Observer observer);

	/**
	 * removes observer from the player.
	 * @param observer the observer to be removed.
	 */
	void removeObserver(Observer observer);

	/**
	 * @return the playerNumber
	 */
	PlayerNumber getPlayerNumber();

	/**
	 * @param playerNumber the playerNumber to set
	 */
	void setPlayerNumber(PlayerNumber playerNumber);
	
	/**
	 * Closes the object and free the resources.
	 */
	void close();
	
}
