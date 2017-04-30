package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import models.Datatype;
/**
 * Utility class that has the checking of types
 * functions and some other important functions.
 * @author Amr
 *
 */
public final class CheckingUtilities {
	/**
	 * Private constructor to prevent instances from
	 * a utility class.
	 */
	private CheckingUtilities() {
	}
	/**
	 * Checks if a string is a string of an integer.
	 * @param value
	 * The string to check.
	 * @return
	 * True if it is an integer.
	 */
	private static boolean isInteger(final String value) {
		return Pattern.matches(
				PatternLibrary.getIntegarValue(),
				value.trim());
	}
	/**
	 * Checks if a string is a string of a float.
	 * @param value
	 * The string to check.
	 * @return
	 * True if it is a float.
	 */
	private static boolean isFloat(final String value) {
		return Pattern.matches(PatternLibrary.getFloatValue(),
				value.trim());
	}
	/**
	 * Checks if a string is a string of a date.
	 * @param value
	 * The string to check.
	 * @return
	 * True if it is a date.
	 */
	private static boolean isDate(final String value) {
		if (!Pattern.matches(PatternLibrary.getDateValue(),
				value.trim())) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			//if not valid, it will throw ParseException
			sdf.parse(value.substring(1, value.length() - 1));
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/**
	 * Checks if it is a string value.
	 * @param source
	 * The original string.
	 * @return
	 * True if it is a string value.
	 */
	private static boolean isString(final String source) {
		String modSource = source.trim();
		if (modSource.charAt(0) == '"'
			&& modSource.charAt(modSource.length() - 1) == '"') {
			return true;
		}
		if (modSource.charAt(0) == '\''
			&&
			modSource.charAt(modSource.length() - 1) == '\'') {
			return true;
		}
		return false;
	}
	/**
	 * Checks if this argument is a variable.
	 * @param source
	 * The string to check.
	 * @return
	 * true if it is a variable.
	 */
	public static boolean isVariable(final String source) {
		return Pattern.matches(
				"[A-z0-9_#]+",
				source.trim());
	}
	/**
	 * Gets the string equivalent datatype.
	 * @param value
	 * String to check its value.
	 * @return
	 * The datatype of this string or null if it doesn't match any value.
	 */
	public static Datatype getStringDatatype(final String value) {
		if (CheckingUtilities.isInteger(value)) {
			return Datatype.INTEGER;
		} else if (CheckingUtilities.isDate(value)) {
			return Datatype.DATE;
		} else if (CheckingUtilities.isString(value)) {
			return Datatype.VARCHAR;
		} else if (CheckingUtilities.isFloat(value)) {
			return Datatype.FLOAT;
		} else {
			return null;
		}
	}
	/**
	 * Function to process a string by checking its datatype
	 * and then processing it according to its datatype and returning
	 * the processed value.
	 * @param value
	 * String to be processed.
	 * @return
	 * The string after it is processed according to its datatype
	 * or null if it doesn't match to any datatype.
	 */
	public static String processStringByDatatype(final String value) {
		if (CheckingUtilities.isInteger(value)) {
			return String.valueOf(StringToData.stringToInteger(value));
		} else if (CheckingUtilities.isDate(value)) {
			return value.substring(1, value.length() - 1);
		} else if (CheckingUtilities.isString(value)) {
			return value.substring(1, value.length() - 1);
		} else if (CheckingUtilities.isFloat(value)) {
			return String.valueOf(StringToData.stringToFloat(value));
		} else {
			return null;
		}
	}
}
