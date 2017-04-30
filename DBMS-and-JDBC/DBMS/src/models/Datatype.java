package models;

import java.time.LocalDate;

import parser.StringToData;

/**
 * Datatypes supported by the DBMS.
 * @author Amr
 *
 */
public enum Datatype {
	/**
	 * The different datatypes supported.
	 */
	VARCHAR, INTEGER, FLOAT, DATE;
	/**
	 * String representing the words to convert to datatype.
	 */
	private static final String DATATYPES_WORDS
		= "VARCHAR|FLOAT|INT|DATE";
	/**
	 * Gets the default null value of a datatype.
	 * @return
	 * String containing the default null value. 
	 */
	public String getDefaultNullValue() {
		switch (this) {
			case VARCHAR:
				return "";
			case DATE:
				return String.valueOf(LocalDate.MIN);
			case INTEGER:
				return String.valueOf(Integer.MIN_VALUE);
			case FLOAT:
				return String.valueOf(Float.MIN_VALUE);
			default:
				return null;
		}
	}
	/**
	 * Function responsible of transforming a string to a datatype.
	 * @param val
	 * The string to transform.
	 * @return
	 * The suitable datatype.
	 */
	public static Datatype parseStringToDatatype(final String val) {
		Datatype type;
		if (val == null) {
			return null;
		}
		String value = val.toUpperCase();
		if (value.equals("VARCHAR")) {
			type = VARCHAR;
		} else if (value.equals("INT")) {
			type = INTEGER;
		} else if (value.equals("FLOAT")) {
			type = FLOAT;
		} else if (value.equals("DATE")) {
			type = DATE;
		} else {
			type = null;
		}
		return type;
	}
	/**
	 * @return the datatypesWords
	 */
	public static String getDatatypesWords() {
		return DATATYPES_WORDS;
	}
	/**
	 * Checks if the datatypes are compatible.
	 * @param value
	 * The string to check.
	 * @param type
	 * The datatype of this value.
	 * @return
	 * True if it is from that datatype.
	 */
	public static boolean matchesDatatype(final String value,
			final Datatype type) {
		switch (type) {
			case INTEGER:
				try {
					StringToData.stringToInteger(value);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			case VARCHAR:
				return true;
			case FLOAT:
				try {
					StringToData.stringToFloat(value);
					return true;
				} catch (Exception e) {
					return false;
				}
			case DATE:
				try {
					StringToData.stringToDate(value);
					return true;
				} catch (Exception e) {
					return false;
				}
			default:
				return false;
		}
	}
}
