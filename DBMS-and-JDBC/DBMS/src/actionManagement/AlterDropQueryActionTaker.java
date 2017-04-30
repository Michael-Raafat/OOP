package actionManagement;

import java.io.IOException;

import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Table;
import queries.AlterRemoveQuery;
import queries.Query;
import queries.QueryAction;

/**
 * Alter add query action taker.
 * @author Amr
 *
 */
public class AlterDropQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor.
	 * @throws UnknownExtension 
	 */
	public AlterDropQueryActionTaker() throws UnknownExtension {
		super();
	}

	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.ALTER_TABLE_REMOVE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist, TableAlreadyExist {
		AlterRemoveQuery alterRemoveQuery = (AlterRemoveQuery) query;
		Table currentTable = this.getTableFromQuery(
				alterRemoveQuery);
		currentTable = currentTable.removeColumn(
				alterRemoveQuery.getColumnName());
		this.getFileManager().writeTable(currentTable);
		return null;
	}

}
