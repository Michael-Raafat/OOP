package syntaxManagement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import models.Datatype;
import parser.PatternLibrary;
import parser.StringToList;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Create table command syntax pattern manager.
 * @author Amr
 *
 */
public class CreateTableCommandPatternManager
	implements CommandPatternManager {
	/**
	 * Constructor of the class.
	 */
	public CreateTableCommandPatternManager() {
	}
	/**
	 * Regular expression the defines the structure of
	 * a correct creation Table query.
	 */
	private static final String CREATE_TABLE_PATTERN_REGEX
		= "(?i)^\\s*(CREATE)"
		+ "\\s+"
		+ "(TABLE)"
		+ "\\s+"
		+ PatternLibrary.getWpwOrVw()
		+ "\\s*"
		+ "\\(\\s*"
		+ PatternLibrary.getTwoItemsListCommaRegex()
		+ "\\s*\\)"
		+ "\\s*$";
	/**
	 * The index of the group of the database name.
	 */
	private static final int CREATE_TABLE_TABLE_NAME_INDEX
		= 3;
	/**
	 * The index of the group of the database name.
	 */
	private static final int CREATE_TABLE_COLUMN_NAME_TYPE_INDEX
		= 8;

	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(CREATE_TABLE_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException, UnknownColumnDatatypeException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				CREATE_TABLE_PATTERN_REGEX, command);
		matcher.find();
		String tableName, columnNameType;
		tableName = matcher.group(CREATE_TABLE_TABLE_NAME_INDEX);
		columnNameType = matcher.group(CREATE_TABLE_COLUMN_NAME_TYPE_INDEX);
		List<List<String>> valuesPair
			= StringToList.stringToTwoListByTwoSeperator(
					"\\s*,\\s*",
			"\\s+", columnNameType);
		List<String> names = valuesPair.get(0);
		SyntaxManagementUtility.trimStringList(valuesPair.get(1));
		List<Datatype> types
			= SyntaxManagementUtility.parseStringToDatatype(valuesPair.get(1));
		SyntaxManagementUtility.trimStringList(names);
		unit.setColumnNames(names);
		unit.setColumnTypes(types);
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.CREATE_TABLE;
	}

}
