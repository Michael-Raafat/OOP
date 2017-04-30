package models.plates.generator;

import java.util.List;
import java.util.Random;

import game.control.difficulties.Difficulty;
import javafx.scene.paint.Color;
import logs.LogService;
import models.plates.Plate;

/**.
 * This class responsible of getting a new random plate,
 * sets its main attributes with random values.
 * @author Marc Magdi
 *
 */
public class PlatesGeneratorImp implements PlatesGenerator {

	/**.
	 * The pool responsible of managing the available
	 * instances of the plates
	 */
	private PlatesPool platesPool;

	/**.
	 * The size of loaded plates classes
	 */
	private int platesTypeSize;

	/**.
	 * A random integer numbers generator
	 */
	private Random randomNumberGenerator;

	/**.
	 * The difficulty of the game.
	 * Used to know the range of the colors
	 * and the speed range
	 */
	private Difficulty difficulty;

	/**.
	 * A random generated list of colors,
	 * its size depends on the difficulty.
	 */
	private List<Color> randomGeneratedColors;

	/**.
	 * Main constructor, work as dependency injection
	 * @param platesPoolParam plates pool object used to get
	 * and to release plates object
	 * @param difficulty the difficulty needed for generating plates colors.
	 * @param colorGenerator the color generator to get the random color.
	 */
	public PlatesGeneratorImp(final PlatesPool platesPoolParam,
			final Difficulty difficulty,
			final RandomColorGenerator colorGenerator) {
		LogService.printTrace(this.getClass(),
				"Construction of PlatesGeneratorImp"
				+ " class");
		this.platesPool = platesPoolParam;
		this.difficulty = difficulty;
		this.platesTypeSize = platesPool.getPlateClassesCount();
		this.randomNumberGenerator = new Random();
		this.randomGeneratedColors = colorGenerator.
				getRandomColorList(
					difficulty.getPlatesColorsCount());
	}

	@Override
	public final Plate getNewPlate() {
		LogService.printTrace(this.getClass(),
				"Plate method getNewPlate"
				+ " is called");
		int randomTypeIndex = this.randomNumberGenerator.nextInt(
				this.platesTypeSize);
		Plate plate = this.platesPool.aquirePlate(randomTypeIndex);
		plate.setColor(this.getRandomColor());
		return plate;
	}

	/**.
	 * Create and return a random javaFx color
	 * @return a javaFx random generated color
	 */
	private Color getRandomColor() {
		LogService.printTrace(this.getClass(),
				"private Color method getRandomColor"
				+ " is called");
		int colorRange = this.difficulty.getPlatesColorsCount();
		int randomColor = randomNumberGenerator.nextInt(colorRange);
		return this.randomGeneratedColors.get(randomColor);
	}

}
