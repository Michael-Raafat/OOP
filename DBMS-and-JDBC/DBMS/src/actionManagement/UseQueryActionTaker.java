package actionManagement;

import java.io.IOException;

import databaseManagement.DatabaseEngine;
import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import queries.Query;
import queries.QueryAction;
import queries.UseDatabaseQuery;

/**
 * Action taker responsible of the use queries.
 * @author Amr
 *
 */
public class UseQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public UseQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.USE_DB;
	}
	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist, TableAlreadyExist {
		UseDatabaseQuery useDatabaseQuery = (UseDatabaseQuery) query;
		if (this.isDatabaseFound(
				useDatabaseQuery.getDatabaseName())) {
			DatabaseEngine.setCurrentDatabaseName(
					useDatabaseQuery.getDatabaseName());
			return null;
		} else {
			throw new UnknownDatabaseException();
		}
	}
}
