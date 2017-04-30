
package models.plates.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.scene.paint.Color;
import logs.LogService;

/**.
 * Implementation of Color random generator.
 * Contains the main algorithm computing the random colors,
 * where the max size of the list can be only 48.
 * This class computes the random list using simple
 * Permutation algorithm on given values.
 * @author Marc Magdi
 *
 */
public class RandomColorGeneratorImp implements RandomColorGenerator {

	/**.
	 * List containing the random generated colors.
	 */
	private List<Color> randomList;

	/**
	 * The main numbers count in RGB [red, green, blue].
	 */
	private static final int RGB_MAIN_COLORS = 3;

	/**
	 * The maximum value for any of the red, green or blue colors.
	 */
	private static final double MAX_COLOR_VALUE = 255.0;

	/**.
	 * The maximum size of needed list up to 27
	 */
	private final int maxSize = 27;

	/**
	 * set of the colors.
	 */
	private Set<WebColor> webColorsSet;

	/**.
	 * Default constructor
	 */
    public RandomColorGeneratorImp() {
    	LogService.printTrace(this.getClass(), "Construction of"
    			+ " RandomColorGeneratorImp" + " class");
		this.randomList = new ArrayList<Color>(maxSize);
		this.webColorsSet = new HashSet<WebColor>();
	}

    @Override
	public final List<Color> getRandomColorList() {
		LogService.printTrace(this.getClass(), "list<color> method "
				+ "getRandomColorList is called");
		return this.getRandomColorList(maxSize);
	}

	@Override
	public final List<Color> getRandomColorList(final int size) {
		LogService.printTrace(this.getClass(), "list<color> method "
				+ "getRandomColorList(int) is called");
		if (size > maxSize) {
			throw new RuntimeException("Out of range");
		}
		this.updateSetBytPermutation(size);
		Iterator<WebColor> iterator = webColorsSet.iterator();
		while (iterator.hasNext()) {
			WebColor webColor = iterator.next();

			double r = webColor.getRed() / MAX_COLOR_VALUE;
			double g = webColor.getGreen() / MAX_COLOR_VALUE;
			double b = webColor.getBlue() / MAX_COLOR_VALUE;
			Color color = new Color(r, g, b, 1);

			this.randomList.add(color);
		}

		return this.randomList;
	}

	/**.
	 * Generate a distinct list up to the give size
	 * @param size the boundary of the maximum size of the list
	 */
	private void updateSetBytPermutation(final int size) {
		LogService.printTrace(this.getClass(), "private void method "
				+ "UpdateSetBytPermutation(int) is called");
		int initColor = (int) MAX_COLOR_VALUE;

		while (webColorsSet.size() < size) {
			List<Boolean> taken = new ArrayList<Boolean>(
					RGB_MAIN_COLORS);
			List<Integer> inputNumbers = new
					ArrayList<Integer>(RGB_MAIN_COLORS);
			List<Integer> result = new ArrayList<Integer>(
					RGB_MAIN_COLORS);

			inputNumbers.add(0);
			inputNumbers.add(0);
			inputNumbers.add(initColor);

			taken.add(false);
			taken.add(false);
			taken.add(false);

			this.permutate(0, taken, inputNumbers, result);

			initColor = (int) Math.ceil(initColor / 2.0);
		}
	}

	/**.
	 * Permutate on the given numbers and add distinct colors to a set
	 * @param depth the current depth in the recursion,
	 * it's up to 3, as in web color we have 3 numbers => RGB.
	 * @param taken a boolean array indicating
	 * if the current number is taken.
	 * @param inputNumbers a list of three numbers to permutate on.
	 * @param result the current result of the permutation.
	 */
	private void permutate(final int depth, final List<Boolean> taken,
		final List<Integer> inputNumbers, final List<Integer> result) {
		LogService.printTrace(this.getClass(), "private void method "
				+ "permutate(int, List<Boolean>, List<Integer>,"
				+ " List<Integer>) is called");
		// compute the permutation row
		if (result.size() == RGB_MAIN_COLORS) {
			WebColor color = new WebColor();
			color.setBlue(result.get(0));
			color.setRed(result.get(1));
			color.setGreen(result.get(2));
			this.webColorsSet.add(color);
		}

		for (int i = 0; i < RGB_MAIN_COLORS; i++) {
			if (taken.get(i)) {
				continue; // the number already taken
			}

			taken.set(i, true);
			result.add(depth, inputNumbers.get(i));
			permutate(depth + 1, taken, inputNumbers, result);
			result.remove(depth);
			taken.set(i, false);
		}
	}

}
