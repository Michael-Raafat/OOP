package actionManagement;

import java.io.IOException;

import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Table;
import queries.DeleteQuery;
import queries.Query;
import queries.QueryAction;

/**
 * Action taker responsible of the delete queries.
 * @author Amr
 *
 */
public class DeleteQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public DeleteQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.DELETE_FROM_TABLE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException {
		DeleteQuery deleteQuery = (DeleteQuery) query;
		Table currentTable = this.getTableFromQuery(
				deleteQuery);
		int beforeDeletionSize = currentTable.getRows().size();
		Table newTable = currentTable.deleteRowsByCondition(
				deleteQuery.getCondition());
		int afterDeletionSize = newTable.getRows().size();
		this.getFileManager().writeTable(newTable);
		return beforeDeletionSize - afterDeletionSize;
	}
}
