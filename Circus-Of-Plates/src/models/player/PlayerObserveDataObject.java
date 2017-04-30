
package models.player;

/**
 * Data transfer object for the player observer.
 * @author Bishoy Nader
 *
 */
public class PlayerObserveDataObject {

	/**
	 * player number.
	 */
	private PlayerNumber playerNumber;

	/**
	 * the score added.
	 */
	private int score;

	/**
	 * @return the playerNumber
	 */
	public final PlayerNumber getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * @param playerNumberParam the playerNumber to set
	 */
	public final void setPlayerNumber(final PlayerNumber playerNumberParam) {
		this.playerNumber = playerNumberParam;
	}

	/**
	 * @return the addedScore
	 */
	public final int getScore() {
		return score;
	}

	/**
	 * @param modifiedScore Score the addedScore to set
	 */
	public final void setScore(final int modifiedScore) {
		this.score = modifiedScore;
	}

}
