package game.control.difficulties;

import logs.LogService;
import models.dto.Speed;

/**
 * An implementation of Difficulty interface.
 * It provide a simple algorithm to provide the next speed as
 * long as providing the number of plates colors and speed range.
 * @author Marc Magdi
 *
 */
public class EasyDifficulty extends Difficulty {
	/**
	 * The path of the difficulties.
	 */
	private static final String PATH = "./Difficulties/Easy.json";
	/**
	 * Constructor.
	 */
	public EasyDifficulty() {
		super(PATH, DifficultyType.EASY);
		LogService.printTrace(this.getClass(),
				"Construction of EasyDifficulty class"
				+ " inherited from DifficultyAbstract class");
	}

	@Override
	public final Speed updateFallingSpeed(final Speed currentSpeed) {
		LogService.printTrace(this.getClass(), "Speed Method"
				+ " updateFallingSpeed is called");
		return currentSpeed;
	}
}
