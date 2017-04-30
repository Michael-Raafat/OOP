package game.control.difficulties;

import logs.LogService;

/**
 * Concrete factory implementation of difficulties.
 * @author Amr
 *
 */
public class DifficultyFactoryImp implements DifficultyFactory {

	@Override
	public final Difficulty createDifficulty(final DifficultyType type) {
		LogService.printTrace(this.getClass(), "Difficulty Method "
				+ "createDifficulty(DifficultyType) is called");
		switch (type) {
			case EASY:
				LogService.printInfo(this.getClass(),
					"User chooses Easy Difficulty");
				return new EasyDifficulty();
			case MEDIUM:
				LogService.printInfo(this.getClass(),
					"User chooses Medium Difficulty");
				return new MediumDifficulty();
			case HARD:
				LogService.printInfo(this.getClass(),
					"User chooses Hard Difficulty");
				return new HardDifficulty();
			default:
				return new HardDifficulty();
		}
	}

}
