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
import queries.AlterAddQuery;
import queries.Query;
import queries.QueryAction;

/**
 * Alter add query action taker.
 * @author Amr
 *
 */
public class AlterAddQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor.
	 * @throws UnknownExtension 
	 */
	public AlterAddQueryActionTaker() throws UnknownExtension {
		super();
	}

	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.ALTER_TABLE_ADD;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist, TableAlreadyExist {
		AlterAddQuery alterAddQuery = (AlterAddQuery) query;
		Table currentTable = this.getTableFromQuery(
				alterAddQuery);
		currentTable = currentTable.addColumn(alterAddQuery.getColumnName(),
				alterAddQuery.getColumnDatatype());
		this.getFileManager().writeTable(currentTable);
		return null;
	}

}
