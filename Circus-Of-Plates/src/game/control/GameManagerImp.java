package game.control;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import controllers.NodeService;
import controllers.NodeServiceSingelton;
import game.control.difficulties.Difficulty;
import game.control.difficulties.DifficultyFactory;
import game.control.difficulties.DifficultyFactoryImp;
import game.control.difficulties.DifficultyType;
import javafx.scene.input.KeyCode;
import logs.LogService;
import models.plates.Plate;
import models.plates.generator.PlatesGenerator;
import models.plates.generator.PlatesGeneratorImp;
import models.plates.generator.PlatesPool;
import models.plates.generator.PlatesPoolImp;
import models.plates.generator.RandomColorGenerator;
import models.plates.generator.RandomColorGeneratorImp;
import models.plates.movers.PlateGravity;
import models.plates.movers.PlateGravityImp;
import models.plates.movers.PlateTrack;
import models.plates.movers.PlateTrackImp;
import models.player.Player;
import models.player.PlayerImp;
import models.player.PlayerNumber;
import models.player.input.manager.PlayerInputManagerSingelton;

/**
 * The game manager.
 * @author Amr
 *
 */
public class GameManagerImp implements GameManager {
	/**
	 * Indicates if the game is closed.
	 */
	private boolean closed;
	/**
	 * Long track width.
	 */
	private static final int LONG_TRACK_WIDTH = 500;
	/**
	 * Short track width.
	 */
	private static final int SHORT_TRACK_WIDTH = 200;
	/**
	 * Move Delay.
	 */
	private static final int DELAY = 20;
	/**
	 * List of the game players.
	 */
	private List<Player> players;
	/**
	 * game gravity.
	 */
	private PlateGravity gravity;
	/**
	 * List of tracks.
	 */
	private List<PlateTrack> tracks;
	/**
	 * Difficulty.
	 */
	private Difficulty gameDiff;
	/**
	 * Difficulty factory.
	 */
	private DifficultyFactory diffFactory;
	/**
	 * The pool used in the game.
	 */
	private PlatesPool pool;
	/**
	 * The graphics service.
	 */
	private NodeService service;
	/**
	 * Constructor that initiates a game.
	 * @param yBound
	 * The bound of the y coordinate of the game.
	 * @param xBound
	 * The bound of x coordinate of the game.
	 * @param type
	 * The type of the difficulty to be used.
	 */
	public GameManagerImp(final int xBound, final int yBound,
			final DifficultyType type) {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " InGameManagerImp class.");
		initialize(xBound, yBound, type);
	}
	/**
	 * Contructor with snapshot.
	 * @param xBound
	 * The max x of the window.
	 * @param yBound
	 * The max y of the window.
	 * @param snapshot
	 * snapshot to load.
	 */
	public GameManagerImp(final int xBound, final int yBound,
			final GameManagerSnapShot snapshot) {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " InGameManagerImp class with Snapshot.");
		initialize(xBound, yBound, snapshot.getCurrentType());
		for (int i = 0; i < players.size(); i++) {
			players.get(i).useSnapshot(snapshot.getPlayersSnapshot().get(i));
		}
		for (int i = 0; i < tracks.size(); i++) {
			tracks.get(i).useSnapshot(snapshot.getTracksSnapshot().get(i));
		}
		gravity.useSnapshot(snapshot.getGravitySnapshot());
	}
	/**
	 * Initializer of all variables.
	 * @param xBound
	 * The xBound of the screen.
	 * @param yBound
	 * The ybound of the screen.
	 * @param type
	 * The difficulty type to initialize.
	 */
	private void initialize(final int xBound, final int yBound,
			final DifficultyType type) {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " initialize(xBound, yBound, DifficultyType) is called.");
		closed = false;
		diffFactory = new DifficultyFactoryImp();
		gameDiff = diffFactory.createDifficulty(type);
		pool = PlatesPoolImp.getInstance();
		service = NodeServiceSingelton.getInstance();
		RandomColorGenerator colorGenerator = new RandomColorGeneratorImp();
		PlatesGenerator generator = new PlatesGeneratorImp(pool, gameDiff,
				colorGenerator);
		gravity = new PlateGravityImp(gameDiff, yBound,
				service, pool, xBound);
		tracks = setTracks(xBound, generator);
		players = setPlayers(xBound, yBound, pool, service);
		for (int i = 0; i < tracks.size(); i++) {
			service.bufferRegisterNode(tracks.get(i).getNode());
		}
		for (int i = 0; i < players.size(); i++) {
			service.bufferRegisterNode(players.get(i).getNode());
		}
	}
	@Override
	public final void update() {
		LogService.printTrace(this.getClass(), "void Method update is called.");
		if (!closed) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				LogService.printError(getClass(),
						"Interrupted exception in game thread.");
			}
			for (int i = 0; i < tracks.size(); i++) {
				tracks.get(i).update();
			}
			gravity.update();
			for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				player.update();
				for (Plate current : gravity.getFallingPlates()) {
					if (player.attachPlate(current)) {
						gravity.getFallingPlates().remove(current);
					}
				}
			}
			service.clearBuffer();
		}
	}
	private List<PlateTrack> setTracks(final int xBound,
			final PlatesGenerator generator) {
		LogService.printTrace(this.getClass(), "private List<PlateTrack> Method"
				+ " setTracks(xBound, PlatesGenerator) is called.");
		List <PlateTrack> track
			= new ArrayList<PlateTrack>();
		track.add(createTrack(
				new Point (0, 200),
				new Point (SHORT_TRACK_WIDTH, 200),
				generator));
		track.add(createTrack(
				new Point (xBound, 200),
				new Point (xBound - SHORT_TRACK_WIDTH, 200),
				generator));
		track.add(createTrack(
				new Point (0, 50),
				new Point (LONG_TRACK_WIDTH, 50),
				generator));
		track.add(createTrack(
				new Point (xBound, 50),
				new Point (xBound - LONG_TRACK_WIDTH, 50),
				generator));
		return track;
	}
	private PlateTrack createTrack(
			final Point start, final Point end,
			final PlatesGenerator generator) {
		LogService.printTrace(this.getClass(), "private PlateTrack Method"
				+ " createTrack(start point, end point,"
				+ " PlatesGenerator) is called.");
		return new PlateTrackImp(start, end, gameDiff, gravity,
				service, generator, pool);
	}
	/**
	 * Creates and registers two players.
	 * @param pool
	 * The plate pool.
	 * @param service
	 * The graphic service.
	 * @param xBound
	 * Maximum x coordinate.
	 * @param yBound
	 * Maximum y coordinate.
	 * @return
	 * A list of players.
	 */
	private List<Player> setPlayers(final int xBound, final int yBound,
			final PlatesPool pool, final NodeService service) {
		LogService.printTrace(this.getClass(), "private List<Player> Method"
				+ " setPlayers(xBound, yBound, PlatesPool,"
				+ " NodeService) is called.");
		List<Player> list = new ArrayList<Player>();
		Player player = new PlayerImp(xBound, yBound, pool, service);
		player.setPlayerNumber(PlayerNumber.FIRST);
		PlayerInputManagerSingelton.getInstance().setFirstPlayer(player,
				KeyCode.LEFT, KeyCode.RIGHT);
		list.add(player);
		player = new PlayerImp(xBound, yBound, pool, service);
		PlayerInputManagerSingelton.getInstance().setSecondPlayer(player);
		player.setPlayerNumber(PlayerNumber.SECOND);
		list.add(player);
		return list;
	}
	@Override
	public final GameManagerSnapShot getSnapshot() {
		LogService.printTrace(this.getClass(), "InGameManagerSnapShot Method"
				+ " getSnapshot is called.");
		return new GameManagerSnapShot(players, tracks, gravity,
				gameDiff.getType());
	}
	@Override
	public final void attachObserverToPlayers(final Observer observer) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " attachObserverToPlayers(Observer) is called.");
		for (Player player : players) {
			player.addObserver(observer);
		}
	}
	@Override
	public final void endGame() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " endGame is called.");
		closed = true;
		service.clearScreen();
		for (Player player : players) {
			player.close();
		}
		players.clear();
		for (PlateTrack track : tracks) {
			track.close();
		}
		tracks.clear();
		gravity.close();
		gravity = null;
		pool = null;
		gameDiff = null;
		diffFactory = null;
		service = null;
	}
	@Override
	public final boolean isClosed() {
		LogService.printTrace(this.getClass(), "boolean Method"
				+ " isClosed is called.");
		return closed;
	}
}
