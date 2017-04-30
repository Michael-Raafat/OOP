package syntaxManagement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import parser.StringToCondition;
import parser.StringToList;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;

/**
 * Update command syntax pattern manager.
 * @author Amr
 *
 */
public class UpdateCommandPatternManager implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public UpdateCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct update query.
	 */
	private static final String UPDATE_PATTERN_REGEX
		= "(?i)^\\s*(UPDATE)"
			+ "\\s+"
			+ PatternLibrary.getWpwOrVw()
			+ "\\s+"
			+ "(SET)"
			+ "\\s+"
			+ PatternLibrary.getEqualStatementsCommaListRegex()
			+ PatternLibrary.getWhereStatementOrEndingRegex();
	/**
	 * The index of the group of the table name.
	 */
	private static final int UPDATE_TABLE_NAME_INDEX = 2;
	/**
	 * The index of the group of the column value pair list.
	 */
	private static final int UPDATE_COLUMN_VALUES_PAIR_LIST_INDEX = 8;
	/**
	 * The index of the group of the condition.
	 */
	private static final int UPDATE_CONDITION_INDEX = 13;
	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(UPDATE_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				UPDATE_PATTERN_REGEX, command);
		matcher.find();
		String columnValueString, tableName, condition;
		tableName = matcher.group(UPDATE_TABLE_NAME_INDEX);
		columnValueString = matcher.group(UPDATE_COLUMN_VALUES_PAIR_LIST_INDEX);
		condition = matcher.group(UPDATE_CONDITION_INDEX);
		setUnitColumnAndValuesAndTypes(unit, columnValueString);
		unit.setCondition(StringToCondition.stringToCondition(condition));
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.UPDATE_TABLE;
	}
	/**
	 * Function to set the query columns and new values and
	 * data types from the string.
	 * @param unit
	 * Query build unit to be modified.
	 * @param columnValueString
	 * String containing each column with its new data values.
	 * @throws WrongSyntaxException
	 * If an invalid value is entered.
	 */
	private void setUnitColumnAndValuesAndTypes(final QueryBuildUnit unit,
			final String columnValueString) throws WrongSyntaxException {
		List<List<String>> valuesPair
		= StringToList.stringToTwoListByTwoSeperator(
				"\\s*,\\s*", "\\s*=\\s*", columnValueString);
		List<String> values = valuesPair.get(1);
		List<String> columns = valuesPair.get(0);
		SyntaxManagementUtility.trimStringList(columns);
		SyntaxManagementUtility.trimStringList(values);
		unit.setColumnNames(columns);
		unit.setValuesTypes(
				SyntaxManagementUtility.processAndIdentifyValues(values));
		unit.setNewValues(values);
	}
}
