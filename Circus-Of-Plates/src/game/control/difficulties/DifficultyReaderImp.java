package game.control.difficulties;

import java.io.File;
import java.util.Properties;

import file.manager.JsonObjectLoader;
import logs.LogService;
import models.dto.Speed;
import models.dto.SpeedRange;
/**
 * Implements the difficulty reader.
 * @author Amr
 *
 */
public class DifficultyReaderImp implements DifficultyReader {
	/**
	 * Properties read.
	 */
	private Properties properties;
	/**
	 * Constructor.
	 */
	public DifficultyReaderImp() {
		LogService.printTrace(this.getClass(),
				"Construction of DifficultyReaderImp class");
	}
	@Override
	public final void setDifficultyFile(final String path) {
		LogService.printTrace(this.getClass(),
				"void Method setDifficultyFile is called");
		properties = (Properties)
				JsonObjectLoader.loadObject(
					new File(path), Properties.class);
	}

	@Override
	public final int getPlatesColorsCount() {
		LogService.printTrace(this.getClass(),
				"int Method getPlatesColorsCount is called");
		return Integer.valueOf((String)
				properties.get("Plate color number"));
	}

	@Override
	public final SpeedRange getSpeedRange() {
		LogService.printTrace(this.getClass(),
				"SpeedRange Method getSpeedRange is called");
		int xMin, xMax, yMin, yMax;
		xMin = Integer.valueOf(
				(String) properties.get(
						"Minimum horizontal speed"));
		xMax = Integer.valueOf(
				(String) properties.get(
						"Maximum horizontal speed"));
		yMin = Integer.valueOf(
				(String) properties.get(
						"Minimum vertical speed"));
		yMax = Integer.valueOf(
				(String) properties.get(
						"Maximum vertical speed"));
		SpeedRange result = new SpeedRange(
				new Speed(xMin, yMin),
				new Speed(xMax, yMax));
		return result;
	}

	@Override
	public final int getSpawnWaitTime() {
		LogService.printTrace(this.getClass(),
				"int Method getSpawnWaitTime is called");
		return Integer.valueOf(
				(String) properties.get(
						"Spawn time in milliseconds"));
	}

}
