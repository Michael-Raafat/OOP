
package models.player;

import logs.LogService;

/**
 * Snapshot of the player.
 * @author Amr
 *
 */
public class PlayerSnapshot {
	
	/**
	 * Player hand snapshots.
	 */
	private PlayerHandSnapshot rightSnapshot, leftSnapshot;
	
	/**
	 * The score.
	 */
	private int score;
	
	/**
	 * The center point of the player.
	 */
	private int layoutX;
	
	/**
	 * Constructor.
	 * @param player
	 * The player to make a snapshot.
	 */
	public PlayerSnapshot(final Player player) {
		LogService.printTrace(this.getClass(), "construction of"
				+ " PlayerSnapshot class.");
		score = player.getScore();
		leftSnapshot = player.getLeftHand().getSnapshot();
		rightSnapshot = player.getRightHand().getSnapshot();
		layoutX = (int) player.getNode().getLayoutX();
	}
	
	/**
	 * @return the rightSnapshot
	 */
	public final PlayerHandSnapshot getRightSnapshot() {
		LogService.printTrace(this.getClass(), "playerHandSnapshot Method"
				+ " getRightSnapshot is called.");
		return rightSnapshot;
	}
	
	/**
	 * @return the leftSnapshot
	 */
	public final PlayerHandSnapshot getLeftSnapshot() {
		LogService.printTrace(this.getClass(), "playerHandSnapshot Method"
				+ " getLeftSnapshot is called.");
		return leftSnapshot;
	}
	
	/**
	 * @return the score
	 */
	public final int getScore() {
		LogService.printTrace(this.getClass(), "int Method"
				+ " getScore is called.");
		return score;
	}
	
	/**
	 * @return the layoutPoint
	 */
	public final int getLayoutX() {
		LogService.printTrace(this.getClass(), "int Method"
				+ " getLayoutX is called.");
		return layoutX;
	}

}
