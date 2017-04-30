package actionManagement;

import java.io.IOException;

import databaseManagement.DatabaseEngine;
import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import fileManager.FileManager;
import fileManager.FileManagerSingleton;
import models.Table;
import queries.Query;
import queries.TableQuery;

/**
 * Interface of all action takers.
 * @author Amr
 *
 */
public abstract class QueryActionTaker {
	/**
	 * fileManager that deals with files and directories.
	 */
	private FileManager fileManager;
	/**
	 * @return the fileManager
	 */
	public final FileManager getFileManager() {
		return fileManager;
	}
	/**
	 * Constructor of the query action taker.
	 * @throws UnknownExtension 
	 */
	public QueryActionTaker() throws UnknownExtension {
		fileManager = FileManagerSingleton.getInstance();
	}
	/**
	 * checks whether a database is found or not on the hard disk.
	 * @param databaseName the name of the database
	 * @return true when found else false
	 */
	protected final boolean isDatabaseFound(final String databaseName) {
		return fileManager.checkDatabaseExist(databaseName);
	}
	/**
	 * checks whether a database is found or not on the hard disk.
	 * @param tableName the name of the table
	 * @param databaseName the name of the database containing the table
	 * @return true when found else false
	 */
	protected final boolean isTableFound(final String tableName,
			final String databaseName) {
		return fileManager.checkTableExist(tableName, databaseName);
	}
	/**
	 * loads a specific table from the hard disk.
	 * @param tableName the name of the table
	 * @param databaseName the name of the database containing the table
	 * @return the table object or throws an exception when table not found
	 * @throws UnknownTableException
	 * If an unknown table is requested to be loaded.
	 * @throws UnknownColumnException 
	 */
	private final Table getTable(final String tableName,
			final String databaseName) throws UnknownTableException, UnknownColumnException {
		if (isTableFound(tableName, databaseName)) {
			return fileManager.readTable(tableName, databaseName);
		} else {
			throw new UnknownTableException();
		}
	}
	/**
	 * loads table from used database or passed database.
	 * @param q query passed.
	 * @return the table object
	 * @throws UnknownColumnException 
	 * @throws UnknownTableException .
	 * @throws UnknownDatabaseException .
	 */
	protected final Table getTableFromQuery(final TableQuery q)
			throws UnknownTableException, UnknownDatabaseException,
			UnknownColumnException {
		Table ret;
		if ((q.getDatabaseName() != null
				&& !isTableFound(q.getTableName(), q.getDatabaseName()))
				|| (DatabaseEngine.getCurrentDatabaseName() != null
				&& !isTableFound(q.getTableName(),
						DatabaseEngine.getCurrentDatabaseName()))) {
			throw new UnknownTableException();
		} else if (q.getDatabaseName() != null
				&& isTableFound(q.getTableName(), q.getDatabaseName())) { 
			ret = getTable(q.getTableName(), q.getDatabaseName());
			ret.setDatabase(fileManager.readDB(q.getDatabaseName()));
		} else if (DatabaseEngine.getCurrentDatabaseName() != null
				&& isTableFound(q.getTableName(),
						DatabaseEngine.getCurrentDatabaseName())) {
			ret = getTable(q.getTableName(),
					DatabaseEngine.getCurrentDatabaseName());
			ret.setDatabase(fileManager.readDB(
					DatabaseEngine.getCurrentDatabaseName()));
		} else {
			throw new UnknownDatabaseException();
		}
		return ret;
	}
	/**
	 * Checks if this actionTaker can perform
	 * the query.
	 * @param query
	 * The query to check.
	 * @return
	 * True if it can perform the action.
	 */
	public abstract boolean accepts(Query query);
	/**
	 * Performs the action of the query.
	 * @param query
	 * The query to perform.
	 * @return
	 * An object containing what this action should return
	 * It could be a table if it is a select function.
	 * It could be an Integer if it is a DM command.
	 * It could be null if it doesn't return anything.
	 * @throws UnknownDatabaseException 
	 * @throws UnknownTableException 
	 * @throws WrongDatatypeInputException 
	 * @throws UnknownColumnException 
	 * @throws IOException 
	 * @throws DatabaseAlreadyExist 
	 * @throws TableAlreadyExist 
	 */
	public abstract Object doQuery(Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist, TableAlreadyExist;
}
