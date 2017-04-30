package parser;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
/**
 * Pattern Manager.
 * It can identify which pattern a command follows.
 * Then extracts from the command the information in
 * the suitable way by considering the action.
 * @author Amr
 *
 */
public interface IPatternManager {
	/**
	 * Function that checks and returns the type of action
	 * that the command follows.
	 * @param command
	 * The command to process
	 * @return
	 * The action that will be done.
	 * @throws WrongSyntaxException
	 * If it is an invalid command.
	 */
	QueryAction getCommandAction(String command)
	throws WrongSyntaxException;
	/**
	 * Function that generates a build unit considering the action
	 * you give it to process.
	 * @param command
	 * The command itself so we can extract information.
	 * @return
	 * A build unit containing all the information.
	 * @throws UnknownColumnDatatypeException
	 * If an invalid column datatype is entered.
	 * @throws WrongSyntaxException
	 * If a command is invalid.
	 */
	QueryBuildUnit generateBuildUnit(String command)
			throws UnknownColumnDatatypeException,
			WrongSyntaxException;
}
