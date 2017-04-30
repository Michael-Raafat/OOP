package syntaxManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Drop database command syntax pattern manager.
 * @author Amr
 *
 */
public class DropDatabaseCommandPatternManager
	implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public DropDatabaseCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct drop DB query.
	 */
	private static final String DROP_DB_PATTERN_REGEX
		= "(?i)^\\s*(DROP)"
		+ "\\s+"
		+ "(DATABASE)"
		+ "\\s+"
		+ PatternLibrary.getValidWordRegex()
		+ "\\s*$";
	/**
	 * The index of the group of the database name.
	 */
	private static final int DROP_DB_DATABASE_NAME_INDEX
		= 3;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(DROP_DB_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				DROP_DB_PATTERN_REGEX, command);
		matcher.find();
		String databaseName;
		databaseName = matcher.group(DROP_DB_DATABASE_NAME_INDEX);
		unit.setDatabaseName(databaseName);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.DROP_DB;
	}

}
