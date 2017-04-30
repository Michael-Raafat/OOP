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
import queries.Query;
import queries.QueryAction;
import queries.UnionSelectQuery;

/**
 * Union Query action taker.
 * @author Amr
 *
 */
public class UnionQueryActionTaker extends QueryActionTaker {
	/**
	 * Select query action taker.
	 */
	private SelectQueryActionTaker selectActionTaker;
	/**
	 * Constructor.
	 * @throws UnknownExtension 
	 */
	public UnionQueryActionTaker() throws UnknownExtension {
		super();
		selectActionTaker = new SelectQueryActionTaker();
	}

	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.UNION_SELECT;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist, TableAlreadyExist {
		UnionSelectQuery unionSelectQuery = (UnionSelectQuery) query;
		Table table1 = (Table) selectActionTaker.doQuery(
				unionSelectQuery.getSelectQueries().get(0));
		for (int i = 1; i < unionSelectQuery.getSelectQueries().size(); i++) {
			Table table2 = (Table) selectActionTaker.doQuery(
					unionSelectQuery.getSelectQueries().get(i));
			table1 = unionTwoTables(table1, table2,
					unionSelectQuery.getDistinct().get(i - 1));
		}
		if (unionSelectQuery.isOrder()) {
			table1 = table1.sortTable(unionSelectQuery.getOrderTypes(),
					unionSelectQuery.getOrderValues());
		}
		return table1;
	}
	/**
	 * Returns the union two times.
	 * @param table1
	 * First table.
	 * @param table2
	 * Second table.
	 * @param distinct
	 * Distinct or not.
	 * @return
	 * The union of both tables with the header of the first one.
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 */
	private Table unionTwoTables(final Table table1,
			final Table table2, final Boolean distinct)
			throws UnknownColumnException, WrongDatatypeInputException {
		if (table1.getHeader().getSize() != table2.getHeader().getSize()) {
			throw new UnknownColumnException();
		}
		for (int i = 0; i < table1.getHeader().getSize(); i++) {
			if (!table1.getHeader().getcolumnType(i).equals(
					table2.getHeader().getcolumnType(i))) {
				throw new UnknownColumnException();
			}
		}
		for (int i = 0; i < table2.getRows().size(); i++) {
			table1.addRow(table2.getRows().get(i));
		}
		Table modTable;
		if (distinct) {
			modTable = table1.removeDuplicates();
		} else {
			modTable  = table1;
		}
		return modTable;
	}
}
