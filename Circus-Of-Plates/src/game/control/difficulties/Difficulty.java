package game.control.difficulties;

import logs.LogService;
import models.dto.Speed;
import models.dto.SpeedRange;

/**.
 * Defining the difficulty properties, and the algorithm
 * for computing a new speed.
 * @author Marc Magdi
 *
 */
public abstract class Difficulty {
	/**
	 * The type of difficulty.
	 */
	private DifficultyType type;
	/**
	 * Difficulty static settings reader.
	 */
	private DifficultyReader reader;
	/**
	 * Constructor of the difficulty.
	 * @param path
	 * The path of the difficulty.
	 * @param specifiedType the type of the difficulty.
	 */
	public Difficulty(final String path,
			final DifficultyType specifiedType) {
		LogService.printTrace(this.getClass(),
				"Construction of Difficulty Abstract class");
		reader = new DifficultyReaderImp();
		reader.setDifficultyFile(path);
		type = specifiedType;
	}
	/**.
	 * @return return the size of the colors of the
	 * plates to initialize with.
	 */
	public final int getPlatesColorsCount() {
		LogService.printTrace(this.getClass(),
				"Method getPlatesColorsCount"
				+ " is called");
		return reader.getPlatesColorsCount();
	}
	/**.
	 * @return return an array containing two objects,
	 * a range speed represented as X and Y speed.
	 */
	public final SpeedRange getSpeedRange() {
		LogService.printTrace(this.getClass(),
				"Method getSpeedRange is called");
		return reader.getSpeedRange();
	}

	/**.
	 * Calculate a new speed for current plate
	 * @param currentSpeed the current speed to calculate the new one from
	 * @return a new Point object containing a new calculated speed, through
	 * there algorithm.
	 */
	public abstract Speed updateFallingSpeed(Speed currentSpeed);

	/**.
	 *
	 * @return the time interval between the generation
	 * of the plates in milliseconds.
	 */
	public final int getSpawnWaitTime() {
		LogService.printTrace(this.getClass(),
				"Method getSpawnWaitTime is called");
		return reader.getSpawnWaitTime();
	}
	/**
	 * Getter for the difficulty type.
	 * @return
	 * An Enum of the difficulty type.
	 */
	public final DifficultyType getType() {
		LogService.printTrace(this.getClass(), "DifficultyType Method"
				+ " getType is called");
		return type;
	}
}
