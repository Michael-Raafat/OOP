package models;

import java.util.List;

import exceptions.UnknownTableException;

/**
 * The interface to the database class
 * 
 * @author Amr
 *
 */
public interface Database {
	
	/**
	 * Searches the database for the table with the specified name 
	 * and return it if it finds it.
	 * return null if table is not found.
	 * @param tableName table name required to be fetched.
	 * @return Table found with suitable name or null if not found
	 */
	Table getTableByName(String tableName);

	/**
	 * add a new table to the database.
	 * @param table the new table to be added.
	 * @throws UnknownTableException when a table
	 * with the name already exists in the database
	 */
	void addTable(Table table) throws UnknownTableException;

	/**
	 * removes a table with a certain name.
	 * @param tableName the name of the table to be removed.
	 * @throws UnknownTableException table not found.
	 */
	void removeTableByName(String tableName) throws UnknownTableException;

	/**
	 * @return the tables
	 */
	public List<Table> getTables();

	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<Table> tables);

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name the name to set
	 */
	public void setName(String name);
	
	/**.
	 * create a new table and add it to the current database
	 * @param header the header of the table containing its structure
	 * @param tableName the header of the table containing its structure
	 * @return return the created table
	 */
	public Table createTable(String tableName, Header header);
}
