package parser;

import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import queries.Query;
import queries.QueryAction;

/**
 * Interface of the parser responsible of validating
 * the command and returning a query returning all usefull
 * information in it.
 * @author Amr
 *
 */
public interface Parser {
	/**
	 * Function responsible of taking the command and
	 * checking its correctness. If it is correct, it then extracts
	 * the important information and returns a suitable query containing
	 * the action and the information it needs to be done.
	 * @param command
	 * A string containing the command to be evaluated.
	 * @return
	 * A query containing the action and its paramters.
	 * @throws WrongSyntaxException
	 * If it finds invalid command.
	 * @throws UnknownColumnDatatypeException
	 * If it finds that a column datatype that is ented is unknown.
	 */
	Query parseCommand(String command) throws WrongSyntaxException,
	UnknownColumnDatatypeException;
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
}
