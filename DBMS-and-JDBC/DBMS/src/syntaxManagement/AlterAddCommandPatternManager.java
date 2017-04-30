package syntaxManagement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import models.Datatype;
import parser.PatternLibrary;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;

/**
 * Alter add command parser.
 * @author Amr
 *
 */
public class AlterAddCommandPatternManager implements CommandPatternManager {
	/**
	 * String containing the regex of the command.
	 */
	private static final String ALTER_ADD_REGEX = "(?i)^\\s*ALTER\\s+TABLE\\s+"
			+ PatternLibrary.getWpwOrVw()
			+ "\\s+ADD\\s+"
			+ PatternLibrary.getValidWordRegex()
			+ "\\s+("
			+ Datatype.getDatatypesWords()
			+ ")\\s*$";
	/**
	 * TABLE name group.
	 */
	private static final int ALTER_ADD_TABLE_GROUP
		= 1;
	/**
	 * Column to add name.
	 */
	private static final int ALTER_ADD_COLUMN_GROUP
		= 6;
	/**
	 * Datatype of the column.
	 */
	private static final int ALTER_ADD_COLUMN_DATATYPE
		= 7;
	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(ALTER_ADD_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException, UnknownColumnDatatypeException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				ALTER_ADD_REGEX, command);
		matcher.find();
		String tableName, columnName, datatypeString;
		tableName = matcher.group(ALTER_ADD_TABLE_GROUP);
		columnName = matcher.group(ALTER_ADD_COLUMN_GROUP);
		datatypeString = matcher.group(ALTER_ADD_COLUMN_DATATYPE);
		SyntaxManagementUtility.tableAndDatabaseUnitSetter(tableName, unit);
		ArrayList<String> column = new ArrayList<String>();
		column.add(columnName);
		ArrayList<Datatype> datatype = new ArrayList<Datatype>();
		datatype.add(Datatype.parseStringToDatatype(datatypeString));
		unit.setColumnNames(column);
		unit.setColumnTypes(datatype);
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.ALTER_TABLE_ADD;
	}

}
