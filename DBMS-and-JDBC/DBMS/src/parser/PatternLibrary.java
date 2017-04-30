package parser;
/**
 * Utility class containing repeated patterns.
 * @author Amr
 *
 */
public final class PatternLibrary {
	/**
	 * private constructor to prevent instantiating objects from
	 * it.
	 */
	private PatternLibrary() {
	}
	/**
	 * Pattern of a list of items separated by commas
	 * example: "amr, mico, bishoy, marc".
	 */
	private static final String ITEMS_SEPARATED_BY_COMMA_REGEX
		= "((\\s*[^\\*,\\s]+\\s*,)*\\s*[^\\*,\\s]+)";
	/**
	 * Pattern of a list of items where each two items are seperated
	 * from the next by a comma
	 * example : "amr varchar, mark int".
	 */
	private static final String TWO_ITEMS_LIST_COMMA_REGEX
		= "((\\s*[^\\*,\\s]+\\s+s*[^\\*,\\s]+\\s*,)*"
				+ "\\s*[^\\*,\\s]+\\s+[^\\*,\\s]+)";
	/**
	 * A pattern where we can have a star or a comma item list.
	 */
	private static final String ISBC_OR_STAR_REGEX
		= "(\\*|"
				+ PatternLibrary.getItemsSeparatedByCommaRegex()
				+ ")";
	/**
	 * A pattern where we have an item comma list between two brackets
	 * ().
	 */
	private static final String ISBC_BETWEEN_BRACKETS
		= "(\\(\\s*"
				+ PatternLibrary.getItemsSeparatedByCommaRegex()
				+ "\\s*\\))";
	/**
	 * Pattern of an integer value.
	 */
	private static final String INTEGAR_VALUE
		= "-?[0-9]+";
	/**
	 * Pattern of a string value.
	 */
	private static final String STRING_VALUE
		= "(?:\"[^\"]*\"|'[^']*')";
	/**
	 * Pattern of a float number.
	 */
	private static final String FLOAT_VALUE
		= "-?\\d+\\.\\d+";
	/**
	 * Pattern of a date value.
	 */
	private static final String DATE_VALUE
	 	= "'\\d{4}\\s*-\\s*\\d{2}\\s*-\\s*\\d{2}'|"
	 			+ "\"\\d{4}\\s*-\\s*\\d{2}\\s*-\\s*\\d{2}\"";
	/**
	 * @return the integarValue
	 */
	public static String getIntegarValue() {
		return INTEGAR_VALUE;
	}
	/**
	 * @return the stringValue
	 */
	public static String getStringValue() {
		return STRING_VALUE;
	}
	/**
	 * @return the floatValue
	 */
	public static String getFloatValue() {
		return FLOAT_VALUE;
	}
	/**
	 * @return the dateValue
	 */
	public static String getDateValue() {
		return DATE_VALUE;
	}
	/**
	 * Pattern of a value in a logical statement.
	 */
	private static final String LOGICAL_VALUE
		= "("
		+ FLOAT_VALUE
		+ "|"
		+ INTEGAR_VALUE
		+ "|"
		+ DATE_VALUE
		+ "|"
		+ STRING_VALUE
		+ ")";
	/**
	 * 
	 */
	private static final String VALUE_COMMA_LIST_BETWEEN_BRACKETS
		= "(\\(\\s*"
				+ "((\\s*"
				+ LOGICAL_VALUE
				+ "\\s*,)*\\s*"
				+ LOGICAL_VALUE
				+ ")"
				+ "\\s*\\))";
	/**
	 * Pattern of a valid word for the name of a database or table.
	 */
	private static final String VALID_WORD_REGEX
		= "([A-z0-9_#]+)";
	/**
	 * Pattern of logical operations.
	 */
	private static final String LOGICAL_OPERATIONS_REGEX
		= "(?i)(AND|OR)";
	/**
	 * Pattern of a true or false.
	 */
	private static final String LOGICAL_TRUE_OR_FALSE
		= "(?i)(TRUE|FALSE)";
	/**
	 * A list of equal statements separated by commas
	 * example : "student = amr, mark = 1000, ...".
	 */
	private static final String EQUAL_STATEMENTS_COMMA_LIST_REGEX
		= "((\\s*[^\\*,\\s]+\\s*=\\s*"
				+ LOGICAL_VALUE
				+ "\\s*,)*\\s*[^\\*,\\s]"
				+ "+\\s*=\\s*"
				+ LOGICAL_VALUE
				+ ")";
	/**
	 * Pattern of a name of table preceded by the name of its database.
	 * like : database.table
	 */
	private static final String WORD_POINT_WORD
		= "(([A-z0-9_#]+)\\.([A-z0-9_#]+))";
	/**
	 * Pattern that accepts a word if it follows
	 * Word point word pattern or a valid word patter.
	 */
	private static final String WPW_OR_VW
		= "(" + WORD_POINT_WORD
		+ "|" + VALID_WORD_REGEX
		+ ")";
	/**
	 * Arithmateic operators.
	 */
	private static final String ARITHM_OPERATORS
		= "(>|<|=|<=|>=|!=)";
	/**
	 * A side of a relational argument.
	 */
	private static final String RELATIONAL_ARGUMENT
		= "("
		+ VALID_WORD_REGEX
		+ "|"
		+ LOGICAL_VALUE
		+ ")";
	/**
	 * Lowest level logical statement.
	 */
	private static final String LOW_LOGICAL_STATEMENT
		= "("
		+ RELATIONAL_ARGUMENT
		+ "\\s*"
		+ ARITHM_OPERATORS
		+ "\\s*"
		+ RELATIONAL_ARGUMENT
		+ "|"
		+ LOGICAL_TRUE_OR_FALSE
		+ ")";
	/**
	 * Group index of the column in the low logical statement.
	 */
	private static final int LLS_LEFT_ARUGMENT_INDEX = 2;
	/**
	 * Group index of the operator in the low logical statement.
	 */
	private static final int LLS_OPERATOR_INDEX = 5;
	/**
	 * Group index of the value in the low logical statement.
	 */
	private static final int LLS_RIGHT_ARGUEMNT_INDEX = 6;
	/**
	 * Where statement.
	 */
	private static final String WHERE_STATEMENT
		= "WHERE\\s+"
		+ "(.+)";
	/**
	 * Pattern of an ending where it could end or
	 * it could contain a Where and then a statement.
	 */
	private static final String WHERE_STATEMENT_OR_ENDING_REGEX
		= "(\\s*$|\\s+"
		+ WHERE_STATEMENT
		+ "\\s*$)";
	/**
	 * Order by statement.
	 */
	private static final String ORDER_BY_STATEMENT
		= "ORDER\\s+BY\\s+"
		+ "((?:\\s*"
		+ VALID_WORD_REGEX
		+ ""
		+ "(?:\\s+ASC|\\s+DESC|\\s*)"
		+ "\\s*,)*"
		+ "\\s*"
		+ VALID_WORD_REGEX
		+ "(?:\\s+ASC|\\s+DESC|\\s*))";
	/**
	 * @return the orderByStatement
	 */
	public static String getOrderByStatement() {
		return ORDER_BY_STATEMENT;
	}
	/**
	 * Pattern of an ending where it could end or
	 * it could contain a Where and then a statement and then an order by or end.
	 */
	private static final String WHERE_STATEMENT_OR_ORDERBY_OR_ENDING_REGEX
		= "(\\s*$|\\s+"
		+ WHERE_STATEMENT
		+ "\\s*(?:"
		+ ORDER_BY_STATEMENT
		+ "|$)|\\s+"
		+ ORDER_BY_STATEMENT
		+ "\\s*$)";
	/**
	 * Getter for constant comma list of items regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getItemsSeparatedByCommaRegex() {
		return ITEMS_SEPARATED_BY_COMMA_REGEX;
	}
	/**
	 * @return the logicalValue
	 */
	public static String getLogicalValue() {
		return LOGICAL_VALUE;
	}
	/**
	 * @return the arithmOperators
	 */
	public static String getArithmOperators() {
		return ARITHM_OPERATORS;
	}
	/**
	 * @return the lowLogicalStatement
	 */
	public static String getLowLogicalStatement() {
		return LOW_LOGICAL_STATEMENT;
	}
	/**
	 * Getter for constant where or ending regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getWhereStatementOrEndingRegex() {
		return WHERE_STATEMENT_OR_ENDING_REGEX;
	}
	/**
	 * Getter for constant comma list of items or a star regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getIsbcOrStarRegex() {
		return ISBC_OR_STAR_REGEX;
	}
	/**
	 * Getter for constant comma list of items between brackets regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getIsbcBetweenBrackets() {
		return ISBC_BETWEEN_BRACKETS;
	}
	/**
	 * Getter for constant valid word regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getValidWordRegex() {
		return VALID_WORD_REGEX;
	}
	/**
	 * Getter for constant comma list of equal statements regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getEqualStatementsCommaListRegex() {
		return EQUAL_STATEMENTS_COMMA_LIST_REGEX;
	}
	/**
	 * Getter for constant comma list of pairs regex.
	 * @return
	 * String containing the regex.
	 */
	public static String getTwoItemsListCommaRegex() {
		return TWO_ITEMS_LIST_COMMA_REGEX;
	}
	/**
	 * @return the wordPointWord
	 */
	public static String getWordPointWord() {
		return WORD_POINT_WORD;
	}
	/**
	 * @return the wpwOrVw
	 */
	public static String getWpwOrVw() {
		return WPW_OR_VW;
	}
	/**
	 * @return the llsOperatorIndex
	 */
	public static int getLlsOperatorIndex() {
		return LLS_OPERATOR_INDEX;
	}
	/**
	 * @return the logicalOperationsRegex
	 */
	public static String getLogicalOperationsRegex() {
		return LOGICAL_OPERATIONS_REGEX;
	}
	/**
	 * @return the logicalTrueOrFalse
	 */
	public static String getLogicalTrueOrFalse() {
		return LOGICAL_TRUE_OR_FALSE;
	}
	/**
	 * @return the llsLeftArugmentIndex
	 */
	public static int getLlsLeftArugmentIndex() {
		return LLS_LEFT_ARUGMENT_INDEX;
	}
	/**
	 * @return the llsRightArguemntIndex
	 */
	public static int getLlsRightArguemntIndex() {
		return LLS_RIGHT_ARGUEMNT_INDEX;
	}
	/**
	 * @return the valueCommaListBetweenBrackets
	 */
	public static String getValueCommaListBetweenBrackets() {
		return VALUE_COMMA_LIST_BETWEEN_BRACKETS;
	}
	/**
	 * @return the whereStatementOrOrderbyOrEndingRegex
	 */
	public static String getWhereStatementOrOrderbyOrEndingRegex() {
		return WHERE_STATEMENT_OR_ORDERBY_OR_ENDING_REGEX;
	}
	/**
	 * @return the whereStatement
	 */
	public static String getWhereStatement() {
		return WHERE_STATEMENT;
	}
}
