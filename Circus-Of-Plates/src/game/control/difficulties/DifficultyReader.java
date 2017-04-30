package game.control.difficulties;

import models.dto.SpeedRange;

/**
 * The reader for the difficulties.
 * @author Amr
 *
 */
public interface DifficultyReader {
	/**
	 * Sets the file to read from the properties.
	 * @param path
	 * The path of the file to read.
	 */
	void setDifficultyFile(String path);
	/**.
	 * @return return the size of the colors of the
	 * plates to initialize with.
	 */
	int getPlatesColorsCount();
	/**.
	 * @return return an array containing two objects,
	 * a range speed represented as X and Y speed.
	 */
	SpeedRange getSpeedRange();

	/**.
	 *
	 * @return the time interval between the generation
	 * of the plates in milliseconds.
	 */
	int getSpawnWaitTime();
}
