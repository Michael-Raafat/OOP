package syntaxManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import parser.StringToCondition;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;

/**
 * Delete command syntax pattern manager.
 * @author Amr
 *
 */
public class DeleteCommandPatternManager implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public DeleteCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct delete from query.
	 */
	private static final String DELETE_PATTERN_REGEX
		= "(?i)^\\s*(DELETE)"
		+ "\\s+"
		+ "(\\*\\s++|\\s*)"
		+ "(FROM)"
		+ "\\s+"
		+ PatternLibrary.getWpwOrVw()
		+ PatternLibrary.getWhereStatementOrEndingRegex();
	/**
	 * The index of the group of the table name.
	 */
	private static final int DELETE_TABLE_NAME_INDEX
		= 4;
	/**
	 * The index of the group of the condition.
	 */
	private static final int DELETE_CONDITION_INDEX
		= 10;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(DELETE_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				DELETE_PATTERN_REGEX, command);
		matcher.find();
		String tableName, condition;
		tableName = matcher.group(DELETE_TABLE_NAME_INDEX);
		condition = matcher.group(DELETE_CONDITION_INDEX);
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		unit.setCondition(StringToCondition.stringToCondition(condition));
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.DELETE_FROM_TABLE;
	}

}
