package syntaxManagement;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Interface of any class responsible of any command pattern
 * validation and extraction.
 * @author Amr
 *
 */
public interface CommandPatternManager {
	/**
	 * Checks if a command follows the pattern or not.
	 * @param command
	 * String containing the sql command.
	 * @return
	 * True if it follows the defined pattern.
	 */
	boolean isValid(String command);
	/**
	 * Extracts the data following the pattern and
	 * constructs a query build unit.
	 * @param command
	 * Command containing the sql command.
	 * @return
	 * Query build unit containing all the important information.
	 * @throws WrongSyntaxException 
	 * If an invalid condition is entered.
	 * @throws UnknownColumnDatatypeException
	 * If an invalid column data type is entered.
	 */
	QueryBuildUnit buildBuildUnit(String command)
			throws WrongSyntaxException, UnknownColumnDatatypeException;
	/**
	 * Gets the action of this command Pattern.
	 * @return
	 * The suitable enum of this pattern action.
	 */
	QueryAction getAction();
}
