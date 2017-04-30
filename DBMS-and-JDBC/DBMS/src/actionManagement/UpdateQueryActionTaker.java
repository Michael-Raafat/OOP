package actionManagement;

import java.io.IOException;

import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Table;
import queries.Query;
import queries.QueryAction;
import queries.UpdateQuery;
/**
 * Action taker responsible of the update queries.
 * @author Amr
 *
 */
public class UpdateQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public UpdateQueryActionTaker() throws UnknownExtension {
		super();
	}

	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.UPDATE_TABLE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException {
		UpdateQuery updateQuery = (UpdateQuery) query;
		Table currentTable = this.getTableFromQuery(
				updateQuery);
		Table filteredTable = currentTable.filter(updateQuery.getCondition());
		for (int i = 0; i < updateQuery.getFields().size(); i++) {
			int columnIndex = filteredTable.getColumnIndexByName(
					updateQuery.getColumns().get(i));
			if (updateQuery.getFieldsType().get(i)
					!= currentTable.getHeader().getcolumnType(columnIndex)) {
				throw new UnknownColumnException();
			}
		}
		filteredTable.updateRows(updateQuery.getFields(),
				updateQuery.getColumns());
		this.getFileManager().writeTable(currentTable);
		return filteredTable.getRows().size();
	}

}
