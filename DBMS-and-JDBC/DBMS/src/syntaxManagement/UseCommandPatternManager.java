package syntaxManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Use command syntax pattern manager.
 * @author Amr
 *
 */
public class UseCommandPatternManager implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public UseCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct use query.
	 */
	private static final String USE_PATTERN_REGEX
	 	= "(?i)^\\s*(USE)"
	 	+ "\\s+"
	 	+ PatternLibrary.getValidWordRegex()
	 	+ "\\s*$";
	/**
	 * The index of the group of the database name.
	 */
	private static final int USE_DATABASE_NAME_INDEX
		= 2;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(USE_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				USE_PATTERN_REGEX, command);
		matcher.find();
		String databaseName;
		databaseName = matcher.group(USE_DATABASE_NAME_INDEX);
		unit.setDatabaseName(databaseName);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.USE_DB;
	}

}
