
package models.plates.movers;

import javafx.scene.Node;

/**
 * Interface of the plate Track.
 * @author Amr
 *
 */
public interface PlateTrack {

	/**
	 * @return
	 * Return a snapshot of the current track.
	 */
	PlateTrackSnapshot getSnapshot();
	
	/**
	 * Uses the snapshot to roll back the plate track.
	 * @param snap
	 * The snapshot to roll back to.
	 */
	void useSnapshot(PlateTrackSnapshot snap);
	
	/**
	 * Updates the track.
	 */
	void update();
	
	/**
	 * Function to return node.
	 * @return
	 * The node to get from the track.
	 */
	Node getNode();
	
	/**
	 * Closes the object and free the resources.
	 */
	void close();

}
