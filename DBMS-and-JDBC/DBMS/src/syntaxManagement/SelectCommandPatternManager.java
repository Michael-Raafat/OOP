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
 * Select command syntax pattern manager.
 * @author Amr
 *
 */
public class SelectCommandPatternManager implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public SelectCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct select query.
	 */
	private static final String SELECT_PATTERN_REGEX
		= "(?i)^\\s*(SELECT|SELECT\\s+DISTINCT)\\s+"
			+ PatternLibrary.getIsbcOrStarRegex()
			+ "\\s+(FROM)\\s+"
			+ PatternLibrary.getWpwOrVw()
			+ PatternLibrary.getWhereStatementOrOrderbyOrEndingRegex();
	/**
	 * The index of the group of the select or select distinct.
	 */
	private static final int SELECT_DISTINCT_INDEX = 1;
	/**
	 * The index of the group of the column.
	 */
	private static final int SELECT_COLUMN_INDEX = 2;
	/**
	 * The index of the group of the table name.
	 */
	private static final int SELECT_TABLE_NAME_INDEX = 6;
	/**
	 * The index of the group of the condition.
	 */
	private static final int SELECT_CONDITION_INDEX = 12;
	/**
	 * The index of the group of the order by.
	 */
	private static final int SELECT_ORDER_BY_INDEX = 13;
	/**
	 * The index of the group of the order by.
	 */
	private static final int SELECT_ORDER_BY_INDEX_ALTERNATE = 16;

	@Override
	public final boolean isValid(final String command) {
		if (command.split(
				"(?i)\\s+(UNION|UNION\\s+ALL)\\s+(SELECT)\\s+").length > 1) {
			return false;
		}
		return Pattern.matches(SELECT_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				SELECT_PATTERN_REGEX, command);
		matcher.find();
		String selectStatement, columnString, tableName, condition;
		selectStatement = matcher.group(SELECT_DISTINCT_INDEX);
		columnString = matcher.group(SELECT_COLUMN_INDEX);
		tableName = matcher.group(SELECT_TABLE_NAME_INDEX);
		condition = matcher.group(SELECT_CONDITION_INDEX);
		setUnitOrderProperties(unit, matcher);
		if (!selectStatement.toUpperCase().equals("SELECT")) {
			unit.setDistinct(true);
		}
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		setUnitColumns(unit, columnString);
		unit.setCondition(
				StringToCondition.stringToCondition(condition));
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.SELECT_TABLE;
	}
	/**
	 * Sets the choosen column list of the unit to the columns
	 * extracted from the command.
	 * @param unit
	 * Query unit to be modified.
	 * @param columnString
	 * String containing the column names to select from.
	 */
	private void setUnitColumns(final QueryBuildUnit unit,
			final String columnString) {
		List<String> columns = StringToList.stringToListByOneSeperator(
				"\\s*,\\s*", columnString);
		if (!columns.get(0).equals("*")) {
			SyntaxManagementUtility.trimStringList(columns);
			unit.setColumnNames(columns);
		}
	}
	/**
	 * Detects the order by string if there is any
	 * and sets the unit properties accordingly.
	 * @param unit
	 * Query unit to be modified.
	 * @param matcher
	 * The matcher that will locate the string from the command.
	 */
	private void setUnitOrderProperties(final QueryBuildUnit unit,
			final Matcher matcher) {
		String orderByString = matcher.group(SELECT_ORDER_BY_INDEX);
		if (orderByString == null) {
			orderByString = matcher.group(SELECT_ORDER_BY_INDEX_ALTERNATE);
		}
		if (orderByString != null) {
			unit.setOrder(true);
			SyntaxManagementUtility.setUnitOrderLists(unit, orderByString);
		}
	}
}
