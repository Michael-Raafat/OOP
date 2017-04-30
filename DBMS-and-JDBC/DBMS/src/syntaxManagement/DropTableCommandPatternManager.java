package syntaxManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Drop table command syntax pattern manager.
 * @author Amr
 *
 */
public class DropTableCommandPatternManager implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public DropTableCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct drop Table query.
	 */
	private static final String DROP_TABLE_PATTERN_REGEX
		= "(?i)^\\s*(DROP)"
		+ "\\s+"
		+ "(TABLE)"
		+ "\\s+"
		+ PatternLibrary.getWpwOrVw()
		+ "\\s*$";
	/**
	 * The index of the group of the table name.
	 */
	private static final int DROP_TABLE_TABLE_NAME_INDEX
		= 3;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(DROP_TABLE_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException, UnknownColumnDatatypeException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				DROP_TABLE_PATTERN_REGEX, command);
		matcher.find();
		String tableName;
		tableName = matcher.group(DROP_TABLE_TABLE_NAME_INDEX);
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.DROP_TABLE;
	}

}
