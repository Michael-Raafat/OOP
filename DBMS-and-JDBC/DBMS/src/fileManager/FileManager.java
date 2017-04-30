package fileManager;

import java.io.IOException;

import exceptions.UnknownColumnException;
import models.Database;
import models.Table;

/**
 * . This class is responsible to handle the i/o operations The creations and
 * update of any database or table
 *
 * @author Marc Magdi
 *
 */
public interface FileManager {
	/**
	 * . Create new database on the physical hard
	 *
	 * @param db
	 *            the database to save
	 */
	void writeDB(Database db);

	/**
	 * . Create new table on the physical hard
	 *
	 * @param table
	 *            the table to save
	 * @throws IOException
	 *             throws exception when can't deal with a file
	 */
	void writeTable(Table table) throws IOException;

	/**
	 * . Search a database by its name and return it
	 *
	 * @param name
	 *            the name to search with
	 * @return the database after deserialization
	 */
	Database readDB(String name);

	/**
	 * . Search a table within a database
	 *
	 * @param tableName
	 *            the table name to search for
	 * @param dbName
	 *            the database name to search into
	 * @return the table object after deserialization
	 * @throws UnknownColumnException throws unknown column
	 * exception when trying
	 * to map to an invalid column
	 */
	Table readTable(String tableName, String dbName)
			throws UnknownColumnException;

	/**
	 * . Delete a database by its name
	 *
	 * @param name
	 *            the name of the database to be deleted
	 */
	void deleteDB(String name);

	/**
	 * . Delete a table by its name
	 *
	 * @param tableName the table name to delete.
	 * @param databaseName the database name containing the table
	 *            the name of the table to be deleted
	 */
	void deleteTable(String tableName, String databaseName);

	/**
	 * . Check if a table exist
	 *
	 * @param tableName
	 *            the table to check
	 * @param databaseName
	 *            the database that contains the table
	 * @return return true if table found else false
	 */
	Boolean checkTableExist(String tableName, String databaseName);

	/**
	 * . Check if a database exist within the system
	 *
	 * @param databaseName the database name to check its existence
	 * @return true if the database is found
	 */
	Boolean checkDatabaseExist(String databaseName);

	/**
	 * . Set the default extension used to save files
	 *
	 * @param extension
	 *            the extension to save with
	 */
	public void setDefaultSaveExtension(SaveExtension extension);

	/**
	 * . Change the default save path
	 *
	 * @param path
	 *            the path to change
	 */
	public void setDefaultSavePath(String path);
}
