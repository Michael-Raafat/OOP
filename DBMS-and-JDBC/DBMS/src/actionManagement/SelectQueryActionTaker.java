package actionManagement;

import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Table;
import queries.Query;
import queries.QueryAction;
import queries.SelectQuery;
import queries.SelectSpecificColumnsQuery;
/**
 * Action taker responsible of the select queries.
 * @author Amr
 *
 */
public class SelectQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor.
	 * @throws UnknownExtension 
	 */
	public SelectQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.SELECT_TABLE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException {
		SelectQuery selectQuery = (SelectQuery) query;
		Table currentTable = this.getTableFromQuery(
				selectQuery);
		currentTable = currentTable.filter(selectQuery.getCondition());
		if (selectQuery.isOrder()) {
			currentTable = currentTable.sortTable(
					selectQuery.getColumnOrderType(),
					selectQuery.getColumnOrderValues());
		}
		if (selectQuery instanceof SelectSpecificColumnsQuery) {
			currentTable = currentTable.getSpecificFields((
					(SelectSpecificColumnsQuery) selectQuery).getFields());
		}
		if (selectQuery.isDistinct()) {
			currentTable = currentTable.removeDuplicates();
		}
		return currentTable;
	}

}
