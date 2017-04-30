package syntaxManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import models.Datatype;
import parser.CheckingUtilities;
import parser.Order;
import parser.StringToList;
import queryBuilders.QueryBuildUnit;

/**
 * Helper class containing functions needed in
 * the command pattern managers.
 * @author Amr
 *
 */
public final class SyntaxManagementUtility {
	/**
	 * private Constructor to prevent instances from this class.
	 */
	private SyntaxManagementUtility() {
	}
	/**
	 * Identifies each value type and process it
	 * according to its type and save the processed word instead of
	 * the old one then returns a new list of datatypes which contains
	 * the datatype of each value in the original list.
	 * @param list
	 * List of strings containing the values.
	 * @return
	 * List of datatypes of the values in the list in the same order.
	 * @throws WrongSyntaxException
	 * If a value can't be identified.
	 */
	public static List<Datatype> processAndIdentifyValues(
			final List<String> list)
			throws WrongSyntaxException {
		List<Datatype> valuetypes = new ArrayList<Datatype>();
		for (int i = 0; i < list.size(); i++) {
			Datatype datatype
			= CheckingUtilities.getStringDatatype(list.get(i));
			if (datatype != null) {
				list.set(i,
						CheckingUtilities.processStringByDatatype(list.get(i)));
				valuetypes.add(datatype);
			} else {
				throw new WrongSyntaxException();
			}
		}
		return valuetypes;
	}
	/**
	 * Function that extracts the data and giving the unit
	 * the name of database and table if they are available.
	 * @param tableName
	 * String containing the either both names or only table name.
	 * @param unit
	 * The build unit to be given the information.
	 */
	public static void tableAndDatabaseUnitSetter(
			final String tableName, final QueryBuildUnit unit) {
		List<String> temp
		= StringToList.stringToListByOneSeperator("\\.", tableName);
		if (temp.size() == 2) {
			unit.setDatabaseName(temp.get(0));
			unit.setTableName(temp.get(1));
		} else {
			unit.setTableName(temp.get(0));
		}
	}
	/**
	 * Function that returns a suitable matcher.
	 * @param regex
	 * The regex pattern to match.
	 * @param command
	 * The command to be processed by the matcher.
	 * @return
	 * A suitable matcher of the command according to the pattern.
	 */
	public static Matcher getMatcher(final String regex, final String command) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(command);
	}
	/**
	 * Trims the items in a string list.
	 * @param list
	 * List to have its items list.
	 */
	public static void trimStringList(final List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).trim());
		}
	}
	/**
	 * Function to transform string data to datatype.
	 * @param source
	 * List of strings.
	 * @return
	 * List of datatypes.
	 * @throws UnknownColumnDatatypeException
	 * If a string contains an undefined type.
	 */
	public static List<Datatype> parseStringToDatatype(
			final List<String> source)
			throws UnknownColumnDatatypeException {
		List<Datatype> list = new ArrayList<Datatype>();
		for (int i = 0; i < source.size(); i++) {
			Datatype type = Datatype.parseStringToDatatype(source.get(i));
			if (type == null) {
				throw new UnknownColumnDatatypeException();
			} else {
				list.add(type);
			}
		}
		return list;
	}
	/**
	 * Sets the column names of orders values
	 * and then detects which is the type of ordering
	 * of each column and adds that list to the build unit.
	 * @param unit
	 * The query build unit to be modified.
	 * @param value
	 * The string containing the list of column names and
	 * the type of ordering.
	 */
	public static void setUnitOrderLists(final QueryBuildUnit unit,
			final String value) {
		List<List<String>> orderList
		= StringToList.stringToTwoListByTwoSeperator(
				"\\s*,\\s*", "\\s+", value);
		List<String> columnTypes = orderList.get(1); 
		unit.setNewValues(orderList.get(0));
		ArrayList<Order> columnOrderTypes = new ArrayList<Order>();
		for (int i = 0; i < columnTypes.size(); i++) {
			if (columnTypes.get(i) == null) {
				columnOrderTypes.add(Order.ASC);
			} else if (columnTypes.get(i).toUpperCase().equals("ASC")) {
				columnOrderTypes.add(Order.ASC);
			} else {
				columnOrderTypes.add(Order.DESC);
			}
		}
		unit.setOrderTypes(columnOrderTypes);
	}
}
