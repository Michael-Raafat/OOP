package syntaxManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;

/**
 * create database command syntax pattern manager.
 * @author Amr
 *
 */
public class CreateDatabaseCommandPatternManager
	implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public CreateDatabaseCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct creation database query.
	 */
	private static final String CREATE_DB_PATTERN_REGEX
		= "(?i)^\\s*(CREATE)"
		+ "\\s+"
		+ "(DATABASE)"
		+ "\\s+"
		+ PatternLibrary.getValidWordRegex()
		+ "\\s*$";
	/**
	 * The index of the group of the database name.
	 */
	private static final int CREATE_DB_DATABASE_NAME_INDEX
		= 3;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(CREATE_DB_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				CREATE_DB_PATTERN_REGEX, command);
		matcher.find();
		String databaseName;
		databaseName = matcher.group(CREATE_DB_DATABASE_NAME_INDEX);
		unit.setDatabaseName(databaseName);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.CREATE_DB;
	}

}
