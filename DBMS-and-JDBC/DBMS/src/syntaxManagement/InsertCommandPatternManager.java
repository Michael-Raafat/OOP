package syntaxManagement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import parser.StringToList;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Insert command syntax pattern manager.
 * @author Amr
 *
 */
public class InsertCommandPatternManager implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public InsertCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct insertInto query.
	 */
	private static final String INSERT_PATTERN_REGEX
		= "(?i)^\\s*(INSERT\\s+INTO)"
			+ "\\s+"
			+ PatternLibrary.getWpwOrVw()
			+ "(\\s*"
			+ PatternLibrary.getIsbcBetweenBrackets()
			+ "\\s*(VALUES)"
			+ "|\\s+(VALUES))"
			+ "\\s*"
			+ PatternLibrary.getValueCommaListBetweenBrackets()
			+ "\\s*$";
	/**
	 * The index of the group of the table name.
	 */
	private static final int INSERT_TABLE_NAME_INDEX = 2;
	/**
	 * The index of the group of the columns names.
	 */
	private static final int INSERT_COLUMN_INDEX = 9;
	/**
	 * The index of the group of the new values.
	 */
	private static final int INSERT_VALUES_INDEX = 14;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(INSERT_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				INSERT_PATTERN_REGEX, command);
		matcher.find();
		String columnString, tableName, valueString;
		columnString = matcher.group(INSERT_COLUMN_INDEX);
		tableName = matcher.group(INSERT_TABLE_NAME_INDEX);
		valueString = matcher.group(INSERT_VALUES_INDEX);
		List<String> values
		= StringToList.stringToListByOneSeperator(
				"\\s*,\\s*", valueString);
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		setUnitColumn(unit, columnString);
		SyntaxManagementUtility.trimStringList(values);
		unit.setValuesTypes(
				SyntaxManagementUtility.processAndIdentifyValues(values));
		unit.setNewValues(values);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.INSERT_INTO_TABLE;
	}
	/**
	 * Sets the query build unit columns property accordingly.
	 * @param unit
	 * The query build unit to be modified.
	 * @param columnString
	 * The column String containing the columns details if there is any.
	 */
	private void setUnitColumn(final QueryBuildUnit unit,
			final String columnString) {
		if (columnString != null) {
			List<String> columns
				= StringToList.stringToListByOneSeperator(
						"\\s*,\\s*", columnString);
			SyntaxManagementUtility.trimStringList(columns);
			unit.setColumnNames(columns);
		}
	}
}
