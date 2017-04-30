package models.dto;

import java.io.File;

import game.control.difficulties.DifficultyType;
import logs.LogService;

/**.
 * Class containing the new game data needed to start a new game.
 * @author Marc Magdi
 *
 */
public class NewGameMeta {
	/**.
	 * The difficulty chosen for the game.
	 */
	private DifficultyType difficulty;

	/**.
	 * the path to load a file
	 */
	private File loadPath;
	/**.
	 * The width of the current window.
	 */
	private double width;

	/**.
	 * The height of the current window
	 */
	private double height;

	/**.
	 * Default constructor
	 * @param loadPath the path to load a file.
	 * @param width the width of the playing area.
	 * @param height the height of the playing area.
	 */
	public NewGameMeta(final File loadPath,
			final double width, final double height) {
		this.height = height;
		this.width = width;
		this.loadPath = loadPath;
	}

	/**.
	 * Default constructor
	 * @param difficulty the difficulty chosen for the game.
	 * @param width the width of the playing area.
	 * @param height the height of the playing area.
	 */
	public NewGameMeta(final DifficultyType difficulty,
			final double width, final double height) {
		LogService.printTrace(this.getClass(),
				"Construction of NewGameMeta Class.");
		this.height = height;
		this.width = width;
		this.difficulty = difficulty;
	}

	/**
	 * @return the difficulty
	 */
	public DifficultyType getDifficulty() {
		LogService.printTrace(this.getClass(),
				"DifficultyType Method"
				+ " getDifficulty is called.");
		return difficulty;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		LogService.printTrace(this.getClass(),
				"double Method getWidth is called.");
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		LogService.printTrace(this.getClass(),
				"double Method getHeight is called.");
		return height;
	}

	/**
	 * @return the load path
	 */
	public File getLoadPath() {
		LogService.printTrace(this.getClass(),
				"File Method getLoadPath is called.");
		return loadPath;
	}
}
