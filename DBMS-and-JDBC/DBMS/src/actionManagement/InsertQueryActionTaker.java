package actionManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Row;
import models.Table;
import models.implementation.RowImp;
import queries.InsertIntoSpecificColumnsQuery;
import queries.InsertQuery;
import queries.Query;
import queries.QueryAction;
/**
 * Action taker responsible of the insert queries.
 * @author Amr
 *
 */
public class InsertQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public InsertQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.INSERT_INTO_TABLE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException {
		InsertQuery insertQuery = (InsertQuery) query;
		Table currentTable
			= this.getTableFromQuery(insertQuery);
		Row newRow;
		if (insertQuery instanceof InsertIntoSpecificColumnsQuery) {
			newRow = insertIntoSpecificColumnsIntoTable(
					(InsertIntoSpecificColumnsQuery) insertQuery,
					currentTable);
		} else {
			newRow = insertIntoTable(insertQuery, currentTable);
		}
		currentTable.addRow(newRow);
		this.getFileManager().writeTable(currentTable);
		return 1;
	}
	
	/**
	 * insert new row into an existing table and save it to the hard disk.
	 * @param insertQuery the query parameters encapsulated
	 * @param table The table which we will add the row to.
	 * @return integer value of the number of rows.
	 * @throws UnknownTableException 
	 * @throws UnknownDatabaseException 
	 * @throws WrongDatatypeInputException 
	 * @throws UnknownColumnException 
	 * @throws IOException 
	 */
	private Row insertIntoTable(final InsertQuery insertQuery,
			final Table table)
			throws UnknownTableException, UnknownDatabaseException,
			WrongDatatypeInputException, UnknownColumnException, IOException {
		for (int i = 0; i < insertQuery.getFieldsValues().size(); i++) {
			if (insertQuery.getFieldsTypes().get(i)
					!= table.getHeader().getcolumnType(i)) {
				throw new UnknownColumnException();
			}
		}
		Row newRow = new RowImp(table, insertQuery.getFieldsValues());
		return newRow;
	}
	
	/**
	 * insert specific fields into a new row in a table.
	 * @param insertQuery the query parameters encapsulated
	 * @param table The table which we will add this row to.
	 * @return The new row.
	 * @throws UnknownTableException 
	 * @throws UnknownDatabaseException 
	 * @throws WrongDatatypeInputException 
	 * @throws UnknownColumnException 
	 * @throws IOException 
	 */
	private Row insertIntoSpecificColumnsIntoTable(
			final InsertIntoSpecificColumnsQuery insertQuery,
			final Table table)
			throws UnknownTableException, UnknownDatabaseException, 
			WrongDatatypeInputException, UnknownColumnException, IOException {
		List<String> cells = fillSpecificDataInRow(insertQuery, table);
		Row newRow = new RowImp(table, cells);
		return newRow;
	}
	
	/**
	 * fill the list of cells of the new row.
	 * @param insertQuery the query parameters encapsulated
	 * @param table the table that the new row will be inserted into
	 * @return the list of cells of the new row.
	 * @throws UnknownColumnException 
	 */
	private List<String> fillSpecificDataInRow(
			final InsertIntoSpecificColumnsQuery insertQuery,
			final Table table) throws UnknownColumnException {
		List<String> cells = new ArrayList<>();
		for (int i = 0; i < table.getHeader().getSize(); i++) {
			cells.add(
					table.getHeader().getcolumnType(i).getDefaultNullValue());
		}
		for (int i = 0; i < insertQuery.getColumnToAddTo().size(); i++) {
			String columnToAddTo = insertQuery.getColumnToAddTo().get(i);
			int columnIndex = table.getColumnIndexByName(columnToAddTo);
			if (insertQuery.getFieldsTypes().get(i)
					!= table.getHeader().getcolumnType(columnIndex)) {
				throw new UnknownColumnException();
			}
			cells.set(columnIndex, insertQuery.getFieldsValues().get(i));
		}
		return cells;
	}
}
