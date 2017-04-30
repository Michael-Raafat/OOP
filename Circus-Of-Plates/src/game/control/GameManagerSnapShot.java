package game.control;

import java.util.ArrayList;
import java.util.List;

import game.control.difficulties.DifficultyType;
import logs.LogService;
import models.plates.movers.PlateGravity;
import models.plates.movers.PlateGravitySnapshot;
import models.plates.movers.PlateTrack;
import models.plates.movers.PlateTrackSnapshot;
import models.player.Player;
import models.player.PlayerSnapshot;

/**
 * The snapshot of the InGameManager.
 * @author Amr
 *
 */
public class GameManagerSnapShot {
	/**
	 * List of players snapshots.
	 */
	private List<PlayerSnapshot> playersSnapshot;
	/**
	 * List of plate tracks snapshots.
	 */
	private List<PlateTrackSnapshot> tracksSnapshot;
	/**
	 * Plate gravity.
	 */
	private PlateGravitySnapshot gravitySnapshot;
	/**
	 * The current game difficulty type.
	 */
	private DifficultyType currentType;
	/**
	 * Constructor.
	 * @param players
	 * list of players.
	 * @param tracks
	 * list of tracks.
	 * @param gravity
	 * list of gravity.
	 * @param type
	 * The type of difficulty.
	 */
	public GameManagerSnapShot(final List<Player> players,
			final List<PlateTrack> tracks,
			final PlateGravity gravity, final DifficultyType type) {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " InGameManagerSnapShot class.");
		this.currentType = type;
		this.gravitySnapshot = gravity.createSnapshot();
		this.playersSnapshot = new ArrayList<PlayerSnapshot>();
		for (int i = 0; i < players.size(); i++) {
			playersSnapshot.add(players.get(i).getSnapshot());
		}
		this.tracksSnapshot = new ArrayList<PlateTrackSnapshot>();
		for (int i = 0; i < tracks.size(); i++) {
			tracksSnapshot.add(tracks.get(i).getSnapshot());
		}
	}
	/**
	 * @return the playersSnapshot
	 */
	public final List<PlayerSnapshot> getPlayersSnapshot() {
		LogService.printTrace(this.getClass(),
				"list<PlayerSnapshot> method"
				+ "getPlayerSnapshot is called.");
		return playersSnapshot;
	}
	/**
	 * @return the tracksSnapshot
	 */
	public final List<PlateTrackSnapshot> getTracksSnapshot() {
		LogService.printTrace(this.getClass(),
				"list<PlateTrackSnapshot> method"
				+ "getTracksSnapshot is called.");
		return tracksSnapshot;
	}
	/**
	 * @return the gravitySnapshot
	 */
	public final PlateGravitySnapshot
		getGravitySnapshot() {
		LogService.printTrace(this.getClass(),
				"PlateGravitySnapshot method"
				+ "getGravitySnapshot is called.");
		return gravitySnapshot;
	}
	/**
	 * @return the currentType
	 */
	public final DifficultyType getCurrentType() {
		LogService.printTrace(this.getClass(), "DifficultyType method"
				+ "getCurrentType is called.");
		return currentType;
	}
}
