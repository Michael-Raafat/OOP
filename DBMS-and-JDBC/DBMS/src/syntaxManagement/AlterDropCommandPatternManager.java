package syntaxManagement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;

/**
 * Alter remove command parser.
 * @author Amr
 *
 */
public class AlterDropCommandPatternManager implements CommandPatternManager {
	/**
	 * String containing the regex of the command.
	 */
	private static final String ALTER_DROP_REGEX = "(?i)^\\s*ALTER\\s+TABLE\\s+"
			+ PatternLibrary.getWpwOrVw()
			+ "\\s+DROP\\s+COLUMN\\s+"
			+ PatternLibrary.getValidWordRegex()
			+ "\\s*$";
	/**
	 * TABLE name group.
	 */
	private static final int ALTER_DROP_TABLE_GROUP
		= 1;
	/**
	 * Column to add name.
	 */
	private static final int ALTER_DROP_COLUMN_GROUP
		= 6;
	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(ALTER_DROP_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException, UnknownColumnDatatypeException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				ALTER_DROP_REGEX, command);
		matcher.find();
		String tableName, columnName;
		tableName = matcher.group(ALTER_DROP_TABLE_GROUP);
		columnName = matcher.group(ALTER_DROP_COLUMN_GROUP);
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		ArrayList<String> column = new ArrayList<String>();
		column.add(columnName);
		unit.setColumnNames(column);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.ALTER_TABLE_REMOVE;
	}

}
