package actionManagement;

import java.io.IOException;

import databaseManagement.DatabaseEngine;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import queries.Query;
import queries.QueryAction;
import queries.TableDroppingQuery;
/**
 * Action taker responsible of the drop table queries.
 * @author Amr
 *
 */
public class DropTableQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public DropTableQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.DROP_TABLE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException {
		TableDroppingQuery tableDroppingQuery = (TableDroppingQuery) query;
		if (tableDroppingQuery.getDatabaseName() != null 
				&& this.isTableFound(
						tableDroppingQuery.getTableName(),
						tableDroppingQuery.getDatabaseName())) {
			this.getFileManager().deleteTable(tableDroppingQuery.getTableName(),
					tableDroppingQuery.getDatabaseName());
			return null;
		} else if (DatabaseEngine.getCurrentDatabaseName() != null
				&& this.isTableFound(
						tableDroppingQuery.getTableName(),
						DatabaseEngine.getCurrentDatabaseName())) {
			this.getFileManager().deleteTable(tableDroppingQuery.getTableName(),
					DatabaseEngine.getCurrentDatabaseName());
			return null;
		} else {
			throw new UnknownTableException();
		}
	}
}
