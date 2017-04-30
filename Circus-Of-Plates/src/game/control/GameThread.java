package game.control;
import logs.LogService;
/**
 * Game Thread.
 * @author Amr
 *
 */
public class GameThread implements Runnable {
	/**
	 * The game manager to use.
	 */
	private GameManager gameManager;
	/**
	 * The boolean to indicate whether the game is stopped.
	 */
	private volatile boolean stopped;
	/**
	 * The thread that runs the runnable.
	 */
	private Thread thread;
	/**
	 * boolean to indicate exit.
	 */
	private volatile boolean exit;
	/**
	 * Constructor.
	 * @param manager
	 * The game manager to run.
	 */
	public GameThread(final GameManager manager) {
		LogService.printTrace(this.getClass(),
				"Construction of GameThread class");
		this.gameManager = manager;
		this.stopped = false;
		this.exit = false;
	}

	@Override
	public final void run() {
		LogService.printTrace(this.getClass(),
				"void Method run is called");
		while (!exit) {
			if (!stopped && !gameManager.isClosed()) {
				try {
					gameManager.update();
				} catch (NullPointerException e) {
					LogService.printError(getClass(),
						"Null point exception happened "
						+ "in gameManager.update() due "
						+ "to executing itand the "
						+ "manager getting closed in"
						+ " the middle.");
				}
			}
		}
	}
	/**
	 * Pauses the game thread.
	 */
	public final void pause() {
		LogService.printTrace(this.getClass(),
				"void Method pause is called");
		stopped = true;
	}
	/**
	 * Resumes the game thread.
	 */
	public final void resume() {
		LogService.printTrace(this.getClass(),
				"void Method resume is called");
		stopped = false;
	}
	/**
	 * Finishes the game thread.
	 */
	public final void stop() {
		LogService.printTrace(this.getClass(),
				"void Method stop is called");
		if (thread != null) {
			exit = true;
			stopped = true;
			gameManager.endGame();
			thread = null;
		}
	}
	/**
	 * Starts the game thread.
	 */
	public final void start() {
		LogService.printTrace(this.getClass(),
				"void Method start is called");
		exit = false;
		stopped = false;
		if (thread != null) {
			thread.start();
		} else {
			thread = new Thread(this);
			thread.start();
		}
	}
	/**
	 * Function to get a copy of a current instant
	 * of the game.
	 * @return
	 * A snapshot of the game.
	 */
	public final GameManagerSnapShot getGameSnapshot() {
		LogService.printTrace(this.getClass(), "InGameManagerSnapShot"
				+ " Method getGameSnapshot is called");
		return gameManager.getSnapshot();
	}
}
