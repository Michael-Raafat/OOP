package models.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import conditionService.ConditionServiceProvider;
import exceptions.UnknownColumnException;
import exceptions.WrongDatatypeInputException;
import logicalComponents.Condition;
import models.Database;
import models.Datatype;
import models.Header;
import models.Row;
import models.Table;
import parser.Order;
/**
 *
 * @author michael.
 *
 */
public class TableImp implements Table, Cloneable {
	/**
	 * table's name.
	 */
	private String name;
	/**
	 * table's header
	 */
	private Header header;
	/**
	 * table's rows.
	 */
	private List<Row> rows;
    /**
     * table belongs to this database.
     */
    private Database database;

	/**
	 * Responsible for getting and deleting rows satisfying condition.
	 */
	private ConditionServiceProvider conditionServiceProvider;

    /**
     * initialize table.
     * @param name
     * table's name.
     * @param database
     * database that table belongs to.
     * @param head
     * table's head.
     */
	public TableImp(String name, Database database, Header head) {
		this.name = name;
		this.header = head;
		this.rows = new ArrayList<Row>();
		this.database = database;
		this.conditionServiceProvider = ConditionServiceProvider.getInstance();
	}
	
	@Override
	public int getColumnIndexByName(String name) throws UnknownColumnException {
		for (int i = 0; i < header.getSize(); i++) {
			if (header.getcolumnName(i).toLowerCase().equals(
					name.toLowerCase())) {
				return i;
			}
		}
		throw new UnknownColumnException();
	}

	@Override
	public void addRow(final Row newRow)
			throws WrongDatatypeInputException, UnknownColumnException {
		if (newRow.getCells().size() > header.getSize()) {
			throw new UnknownColumnException();
		}
		for (int i = 0; i < header.getSize(); i++) {
			if (!Datatype.matchesDatatype(newRow.getCellDataByIndex(i),
					header.getcolumnType(i))) {
				throw new WrongDatatypeInputException();
			}
		}
		rows.add(newRow);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}


	/**
	 * @return the rows
	 */
	public List<Row> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}


	/**
	 * @return the database
	 */
	public Database getDatabase() {
		return database;
	}

	/**
	 * @param dataBase the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderImp header) {
		this.header = header;
	}

	@Override
	public Table filter(Condition condition) throws UnknownColumnException, WrongDatatypeInputException {
		return conditionServiceProvider.getRowsForCondition(this, condition);
	}
	
	@Override
	public TableImp clone() {
		TableImp table = new TableImp(this.getName(), this.database, this.header.clone());
		table.setRows(new ArrayList<>(this.getRows()));
		return table;
		
	}

	@Override
	public Table getSpecificFields(List<String> fields) throws UnknownColumnException {
		List<Integer> indexes = new ArrayList<>();
		for(int i = 0; i < fields.size(); i++) {
			indexes.add(this.getColumnIndexByName(fields.get(i)));
		}
		
		Header header = this.extractHeader(this.getHeader(), indexes);
		Table specificColumnTable = getSpecificTable(this, indexes, header);
		return specificColumnTable;
	}
	
	/**
	 * extracts the new header from the table and the query,
	 * @param table to select from. 
	 * @param selectQuery the query parameters encapsulated
	 * @param indexes of the specified columns in select query.
	 * @return the new header of the table
	 * @throws UnknownColumnException
	 */
	private Header extractHeader(Header header, List<Integer> indexes)
			throws UnknownColumnException {
		List<String> colName = new ArrayList<>();
		List<Datatype> colType = new ArrayList<>();
		for(int i = 0; i < indexes.size(); i++) {
			colName.add(header.getcolumnName(indexes.get(i)));
			colType.add(header.getcolumnType(indexes.get(i)));
		}
		return new HeaderImp(colName, colType);
	}
	
	/**
	 * return a table filled with only required fields.
	 * @param table full table with all fields
	 * @param indexes of the specified columns in select query.
	 * @param header the new header of the table
	 * @return a table filled with only required fields.
	 */
	private Table getSpecificTable(Table table, List<Integer> indexes, Header header) {
		Table specificColumnTable = new TableImp(table.getName(), table.getDatabase(), header); 
		List<Row> rows = new ArrayList<>();
		for(int i = 0; i < table.getRows().size(); i++) {
			List<String> cells = new ArrayList<>();
			for(int j = 0; j < indexes.size(); j++) {
				cells.add(table.getRows().get(i).getCells().get(indexes.get(j)));
			}
			rows.add(new RowImp(specificColumnTable, cells));
		}
		specificColumnTable.setRows(rows);
		return specificColumnTable;
	}

	@Override
	public void updateRows(List<String> fields, List<String> columns) throws UnknownColumnException {
		for(int i = 0; i < fields.size(); i++) {
			int columnIndex = this.getColumnIndexByName(columns.get(i));
			for(int j = 0; j < this.getRows().size(); j++) {
				this.getRows().get(j).getCells().set(columnIndex, fields.get(i));
			}
		}
	}

	@Override
	public Table deleteRowsByCondition(Condition condition) throws UnknownColumnException, WrongDatatypeInputException {
		Table table = this.clone();
		conditionServiceProvider.removeRowsForCondition(table, condition);
		return table;
	}

	@Override
	public final Table removeDuplicates()
			throws WrongDatatypeInputException,
			UnknownColumnException {
		TableImp modTable = new TableImp(this.name, this.database, this.header);
		for (int i = 0; i < this.rows.size(); i++) {
			if (!modTable.getRows().contains(this.rows.get(i))) {
				modTable.addRow(this.rows.get(i));
			}
		}
		return modTable;
	}

	@Override
	public final Table sortTable(final List<Order> orderOfColumns,
			final List<String> columnNames)
			throws UnknownColumnException, WrongDatatypeInputException {
		TableImp modTable = new TableImp(this.name, this.database, this.header);
		ArrayList<Integer> columnIndexes = new ArrayList<Integer>();
		ArrayList<Datatype> columnTypes = new ArrayList<Datatype>();
		for (int i = 0; i < columnNames.size(); i++) {
			int index = this.getColumnIndexByName(columnNames.get(i));
			columnIndexes.add(index);
			columnTypes.add(header.getcolumnType(index));
		}
		RowComparator comparator = new RowComparator(columnIndexes,
				orderOfColumns, columnTypes);
		List<Row> modRows = new ArrayList<Row>();
		for (int i = 0; i < this.rows.size(); i++) {
			modRows.add(this.rows.get(i));
		}
		Collections.sort(modRows, comparator);
		for (int i = 0; i < modRows.size(); i++) {
			modTable.addRow(modRows.get(i));
		}
		return modTable;
	}

	@Override
	public final Table addColumn(final String columnName,
			final Datatype datatype)
			throws WrongDatatypeInputException, UnknownColumnException {
		Table modTable = new TableImp(name, database,
				header.clone().addNewNode(columnName, datatype));
		for (int i = 0; i < this.rows.size(); i++) {
			modTable.addRow(this.rows.get(i).addCell(
					datatype.getDefaultNullValue()));
		}
		return modTable;
	}

	@Override
	public final Table removeColumn(final String columnName)
			throws UnknownColumnException, WrongDatatypeInputException {
		int index = this.getColumnIndexByName(columnName);
		Table modTable = new TableImp(name, database,
				header.clone().removeNode(index));
		for (int i = 0; i < this.rows.size(); i++) {
			modTable.addRow(this.rows.get(i).removeCell(index));
		}
		return modTable;
	}
}
