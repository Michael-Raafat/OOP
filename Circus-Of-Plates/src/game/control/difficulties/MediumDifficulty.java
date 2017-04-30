package game.control.difficulties;

import logs.LogService;
import models.dto.Speed;
/**
 * Medium Difficulty.
 * @author Amr
 *
 */
public class MediumDifficulty extends Difficulty {
	/**
	 * The air resistance that will slow down falling objects.
	 */
	private static final float AIR_RESISTANCE = 0.0001f;
	/**
	 * The path of the difficulties.
	 */
	private static final String PATH = "./Difficulties/Medium.json";
	/**
	 * Constructor.
	 */
	public MediumDifficulty() {
		super(PATH, DifficultyType.MEDIUM);
		LogService.printTrace(this.getClass(),
				"Construction of MediumDifficulty class"
				+ " inherited from DifficultyAbstract class");

	}

	@Override
	public final Speed updateFallingSpeed(final Speed currentSpeed) {
		LogService.printTrace(this.getClass(), "Speed Method"
				+ " updateFallingSpeed is called");
		Speed speed = new Speed(0, currentSpeed.getySpeed());
		if (currentSpeed.getxSpeed() > 0) {
			speed.setxSpeed(currentSpeed.getxSpeed()
					- AIR_RESISTANCE);
		} else if (currentSpeed.getxSpeed() < 0) {
			speed.setxSpeed(currentSpeed.getxSpeed()
					+ AIR_RESISTANCE);
		}
		return speed;
	}
}
