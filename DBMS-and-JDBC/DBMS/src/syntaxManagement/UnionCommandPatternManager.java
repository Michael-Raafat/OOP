package syntaxManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import parser.PatternLibrary;
import parser.StringToList;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Syntax checker and information extractor for
 * the union select command.
 * @author Amr
 *
 */
public class UnionCommandPatternManager implements CommandPatternManager {
	/**
	 * Select pattern to build the two smaller select command.
	 */
	private SelectCommandPatternManager selectBuilder;
	/**
	 * Regular expression the defines the structure of
	 * a valid select part.
	 */
	private static final String UNION_SELECT_PART
		= "(?:SELECT|SELECT\\s+DISTINCT)\\s+"
			+ PatternLibrary.getIsbcOrStarRegex()
			+ "\\s+(?:FROM)\\s+"
			+ PatternLibrary.getWpwOrVw()
			+ "(?:\\s+"
			+ PatternLibrary.getWhereStatement()
			+ ")?";
	/**
	 * Union pattern regex.
	 */
	private static final String UNION_PATTERN_REGEX
		= "(?i)^(\\s*"
		+ UNION_SELECT_PART
		+ "\\s+(UNION|UNION\\s+ALL)\\s+)+("
		+ UNION_SELECT_PART
		+ ")(?:\\s+"
		+ PatternLibrary.getOrderByStatement()
		+ "\\s*$|\\s*$)";
	/**
	 * Group index of the first select statement.
	 */
	private static final int UNION_SELECT_UNION_GROUP
		= 1;
	/**
	 * Group index of the second select statement.
	 */
	private static final int UNION_SELECT_TWO_GROUP
		= 12;
	/**
	 * Group index of the order by statement.
	 */
	private static final int UNION_ORDER_STATEMENT
		= 22;
	/**
	 * Constructor.
	 */
	public UnionCommandPatternManager() {
		selectBuilder = new SelectCommandPatternManager();
	}
	@Override
	public final boolean isValid(final String command) {
		return Pattern.matches(UNION_PATTERN_REGEX, command);
	}

	@Override
	public final QueryBuildUnit buildBuildUnit(final String command)
			throws WrongSyntaxException, UnknownColumnDatatypeException {
		QueryBuildUnit unit = new QueryBuildUnit();
		Matcher matcher = SyntaxManagementUtility.getMatcher(
				UNION_PATTERN_REGEX, command);
		matcher.find();
		String select1, select2, order;
		select1 = matcher.group(UNION_SELECT_UNION_GROUP);
		select2 = matcher.group(UNION_SELECT_TWO_GROUP);
		order = matcher.group(UNION_ORDER_STATEMENT);
		List<String> select = StringToList.stringToListByOneSeperator(
				"\\s+(UNION|UNION\\s+ALL)\\s+",
				select1);
		for (int i = 0; i < select.size(); i++) {
			select1 = select1.replace(select.get(i), "SELECTSTATEMENT");
		}
		List<String> union = StringToList.stringToListByOneSeperator(
				"SELECTSTATEMENT",
				select1);
		List<QueryBuildUnit> unitList = new ArrayList<QueryBuildUnit>();
		for (int i = 0; i < select.size(); i++) {
			unitList.add(this.selectBuilder.buildBuildUnit(select.get(i)));
		}
		unitList.add(this.selectBuilder.buildBuildUnit(select2));
		unit.setBuildUnits(unitList);
		List<Boolean> distinct = new ArrayList<Boolean>();
		for (int i = 0; i < union.size(); i++) {
			
			if (union.get(i).trim().toUpperCase().equals("UNION")) {
				distinct.add(true);
			} else if (!union.get(i).trim().equals("")){
				distinct.add(false);
			}
		}
		unit.setBooleanList(distinct);
		if (order != null) {
			unit.setOrder(true);
			SyntaxManagementUtility.setUnitOrderLists(unit, order);
		}
		return unit;
	}

	@Override
	public final QueryAction getAction() {
		return QueryAction.UNION_SELECT;
	}

}
