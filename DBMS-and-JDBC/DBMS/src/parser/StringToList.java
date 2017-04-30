package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible of taking a string and returning an appropriate
 * list by using regex splitters.
 * @author Amr
 *
 */
public final class StringToList {
	/**
	 * Prevent initialization of this utility class.
	 */
	private StringToList() {
	}
	/**
	 * Takes a string and seperates it by using a regex.
	 * return the seperated strings as a list.
	 * @param seperator
	 * The regex to seperate around.
	 * @param original
	 * The original string.
	 * @return
	 * List of strings after the items get seperated.
	 */
	public static List<String> stringToListByOneSeperator(
			final String seperator, final String original) {
		return new ArrayList<String>(Arrays.asList(
				original.split(seperator)));
	}
	/**
	 * Splitter for pairs of strings, it uses the main seperator
	 * to seperate each pair from another then uses sub seperator
	 * to seperate pairs.
	 * return each value in each pair in a list.
	 * @param mainSeperator
	 * The main seperator regex
	 * @param subSeperator
	 * The sub seperator regex
	 * @param original
	 * The original string
	 * @return
	 * List containing two list of values.
	 */
	public static List<List<String>> stringToTwoListByTwoSeperator(
			final String mainSeperator, final String subSeperator,
			final String original) {
		String[] tempList = original.split(mainSeperator);
		String[] firstValueList = new String[tempList.length];
		String[] secondValueList = new String[tempList.length];
		for (int i = 0; i < tempList.length; i++) {
			firstValueList[i] = tempList[i].split(subSeperator)[0];
			if (tempList[i].split(subSeperator).length > 1) {
				secondValueList[i] = tempList[i].split(subSeperator)[1];
			} else {
				secondValueList[i] = null;
			}
		}
		List<List<String>> returnList
			= new ArrayList<List<String>>();
		returnList.add(Arrays.asList(firstValueList));
		returnList.add(Arrays.asList(secondValueList));
		return returnList;
	}
}
