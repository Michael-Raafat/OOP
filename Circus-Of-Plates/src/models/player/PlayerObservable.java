
package models.player;

import java.util.Observable;

import logs.LogService;

/**
 * A delegated observable for the player.
 * @author Bishoy Nader
 *
 */
public class PlayerObservable extends Observable {
	
	/**
	 * constructor.
	 */
	public PlayerObservable() {
		LogService.printTrace(this.getClass(),
				"Construction of PlayerObservable" + " class.");
	}

	/**
	 * sets that the player has changed for observes to detect change.
	 */
	public final void setChanged() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setChanged is called.");
		super.setChanged();
	}
	
}
