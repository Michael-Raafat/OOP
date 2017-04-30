package actionManagement;

import java.io.IOException;

import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import queries.DatabaseDroppingQuery;
import queries.Query;
import queries.QueryAction;
/**
 * Action taker responsible of the drop databases queries.
 * @author Amr
 *
 */
public class DropDatabaseQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public DropDatabaseQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.DROP_DB;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException {
		DatabaseDroppingQuery databaseDroppingQuery
			= (DatabaseDroppingQuery) query;
		if (isDatabaseFound(
				databaseDroppingQuery.getDatabaseName())) {
			this.getFileManager().deleteDB(
					databaseDroppingQuery.getDatabaseName());
			return null;
		} else {
			throw new UnknownDatabaseException();
		}
	}

}
