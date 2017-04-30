
package models.graphics;

/**
 * Interface for all game objects.
 * @author Amr
 *
 */
public interface GameObject {

	/**
	 * The method responsible for updating
	 * the current game object location.
	 */
	void update();
	
	/**
	 * Closes the object and releases all resources.
	 */
	void close();

}
