package models;

import java.util.List;
import logicalComponents.*;
import exceptions.UnknownColumnException;
import exceptions.WrongDatatypeInputException;
import models.implementation.HeaderImp;
import parser.Order;
/**
 * 
 * @author michael.
 *
 */
public interface Table {
	/**
	 * Gets index of column from its name.
	 * @param name of the column
	 * @return index of column in table
	 * @throws UnknownColumnException no column with such name.
	 */
	int getColumnIndexByName(String name) throws UnknownColumnException;

	/**
	 * add new record to the table.
	 * @param newRow the fields of the new record.
	 * @throws WrongDatatypeInputException type mismatch exception.
	 * @throws UnknownColumnException 
	 */
	void addRow(Row newRow) throws WrongDatatypeInputException, UnknownColumnException;
	
	/**
	 * @return the name
	 */
	public String getName();
	/**
	 * @param name the name to set
	 */
	public void setName(String name);
	/**
	 * @return the header
	 */
	public Header getHeader();
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderImp header);
	/**
	 * @return the rows
	 */
	public List<Row> getRows();
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Row> rows);
	/**
	 * @return the database
	 */
	public Database getDatabase();
	/**
	 * @param database the database to set
	 */
	public void setDatabase(Database database);
	/**.
	 * Filter the table and return a new table object aplied the filter condition
	 * @param condition the condition to filter with
	 * @return return a new filtered table object
	 * @throws WrongDatatypeInputException 
	 * @throws UnknownColumnException 
	 */
	public Table filter(Condition condition) throws UnknownColumnException, WrongDatatypeInputException;

	/**.
	 * Return a new table object with only the selected columns
	 * @param fields the fields to only display from the table
	 * @return a new table object with only the selected fields
	 * @throws UnknownColumnException 
	 */
	public Table getSpecificFields(List<String> fields) throws UnknownColumnException;
	
	/**.
	 * Update the current table selected columns by their new value (fields)
	 * @param fields the new values of the columns
	 * @param columns the columns to be updated
	 * @throws UnknownColumnException 
	 */
	public void updateRows(List<String> fields, List<String> columns) throws UnknownColumnException;

	/**.
	 * delete rows satisfying condition
	 * @param condition the condition the row must satisfy to be deleted
	 * @return table without the deleted rows
	 * @throws WrongDatatypeInputException 
	 * @throws UnknownColumnException 
	 */
	Table deleteRowsByCondition(Condition condition)
			throws UnknownColumnException, WrongDatatypeInputException;
	/**
	 * Removes Duplicated rows from a table.
	 * @return
	 * A new table after all the duplicated rows were removed.
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 */
	Table removeDuplicates() throws WrongDatatypeInputException,
	UnknownColumnException;
	/**
	 * Functions that sorts the table according to certain columns
	 * and the ordering chosen for them.
	 * @param orderOfColumns
	 * A list of the chosen ordering of each column.
	 * @param columnNames
	 * List of columns names to sort by.
	 * @return
	 * The sorted table.
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 */
	Table sortTable(List<Order> orderOfColumns, List<String> columnNames)
			throws UnknownColumnException, WrongDatatypeInputException;
	/**
	 * Function to add a column to the table.
	 * @param columnName
	 * The name of the column to add.
	 * @param datatype
	 * The datatype of the column to add.
	 * @return
	 * Table with updated column.
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 */
	Table addColumn(String columnName, Datatype datatype)
			throws WrongDatatypeInputException, UnknownColumnException;
	/**
	 * Function to remove a column from a table.
	 * @param columnName
	 * The name of the column.
	 * @return
	 * A table with the removed column.
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 */
	Table removeColumn(String columnName)
			throws UnknownColumnException, WrongDatatypeInputException;
	
}
