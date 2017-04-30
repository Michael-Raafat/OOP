package game.control.difficulties;

import java.util.Random;

import logs.LogService;
import models.dto.Speed;
/**
 * Hard difficulty.
 * @author Amr
 *
 */
public class HardDifficulty extends Difficulty {
	/**
	 * The change in speed between each frame.
	 */
	private static final Random SPEED_CHANGE = new Random();
	/**
	 * The path of the difficulties.
	 */
	private static final String PATH = "./Difficulties/Hard.json";
	/**
	 * Constructor.
	 */
	public HardDifficulty() {
		super(PATH, DifficultyType.HARD);
		LogService.printTrace(this.getClass(),
				"Construction of HardDifficulty class"
				+ " inherited from DifficultyAbstract class");

	}

	@Override
	public final Speed updateFallingSpeed(final Speed currentSpeed) {
		LogService.printTrace(this.getClass(), "Speed Method"
				+ " updateFallingSpeed is called");
		Speed speed = new Speed(0, 0);
		float minX = getSpeedRange().getMinSpeed().getxSpeed();
		float maxX = getSpeedRange().getMaxSpeed().getxSpeed();
		float minY = getSpeedRange().getMinSpeed().getySpeed();
		float maxY = getSpeedRange().getMaxSpeed().getySpeed();
		float currentX = currentSpeed.getxSpeed();
		if (currentX  > maxX) {
			speed.setxSpeed(-minX);
		} else if (currentX  < -maxX) {
			speed.setxSpeed(minX);
		} else if (currentX  >= 0) {
			speed.setxSpeed(currentX + SPEED_CHANGE.nextFloat());
		} else if (currentX  < 0) {
			speed.setxSpeed(currentX - SPEED_CHANGE.nextFloat());
		}
		speed.setySpeed(SPEED_CHANGE.nextInt((int)
				(maxY - minY)) + minY);
		return speed;
	}
}
