package parser;


import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import queries.Query;
import queries.QueryAction;
import queryBuilders.IQueryFactory;
import queryBuilders.QueryBuildUnit;
import queryBuilders.QueryFactory;
/**
 * Implementation class of parser.
 * @author Amr
 *
 */
public class ParserImplementation implements Parser {
	/**
	 * An object that is responsible of validating
	 * then extracting the information from the command
	 * and returning it in a QueryBuildUnit format.
	 */
	private IPatternManager pm;
	/**
	 * An object that is responsible of creating
	 * the query from a query build unit and the type of
	 * action.
	 */
	private IQueryFactory factory;
	/**
	 * Constructor to initialize both pattern manager and
	 * the factory.
	 */
	public ParserImplementation() {
		pm = new PatternManager();
		factory = new QueryFactory();
	}
	@Override
	public final Query parseCommand(final String command)
			throws WrongSyntaxException,
			UnknownColumnDatatypeException {
		QueryAction action = pm.getCommandAction(command);
		QueryBuildUnit unit = pm.generateBuildUnit(command);
		return factory.buildQuery(action, unit);
	}
	@Override
	public final QueryAction getCommandAction(final String command)
			throws WrongSyntaxException {
		return pm.getCommandAction(command);
	}

}
