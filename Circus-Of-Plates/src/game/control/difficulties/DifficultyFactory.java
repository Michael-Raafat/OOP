package game.control.difficulties;
/**
 * Interface for difficulty factory.
 * @author Amr
 *
 */
public interface DifficultyFactory {
	/**
	 * Creates a difficulty with the suitable type.
	 * @param type
	 * The type of difficulty.
	 * @return
	 * A concrete class of the suitable type difficulty.
	 */
	Difficulty createDifficulty(DifficultyType type);
}
