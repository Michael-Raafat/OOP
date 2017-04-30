package databaseManagement;

import java.io.IOException;

import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Table;
import queries.Query;
/**
 * Abstract class of the engine.
 * @author Amr
 *
 */
public abstract class DatabaseEngine {
	/**
	 * The name of the database that is currently in use.
	 */
	private static String currentDatabaseName;
	/**
	 * @return the currentDatabaseName
	 */
	public static String getCurrentDatabaseName() {
		return currentDatabaseName;
	}
	/**
	 * @param newDatabaseName the currentDatabaseName to set
	 */
	public static void setCurrentDatabaseName(
			final String newDatabaseName) {
		DatabaseEngine.currentDatabaseName = newDatabaseName;
	}
	/**
	 * Default Constructor for initialization.
	 */
	public DatabaseEngine() {
	}
	/**
	 * Function responsible of taking a query and calling
	 * the suitable action.
	 * @param query The query to process.
	 * @throws UnknownTableException 
	 * @throws UnknownDatabaseException 
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 * @throws DatabaseAlreadyExist 
	 * @throws TableAlreadyExist 
	 * @throws IOException 
	 */
	public abstract void performAction(Query query)
			throws UnknownTableException,
	UnknownDatabaseException, UnknownColumnException,
	WrongDatatypeInputException, DatabaseAlreadyExist,
	TableAlreadyExist, IOException;
	/**
	 * Function responsible of taking a query and calling
	 * the suitable action.
	 * @param query The query to process.
	 * @return
	 * A table if the function returns a table
	 * or null if it doesn't return anything.
	 * @throws UnknownTableException 
	 * @throws UnknownDatabaseException 
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 * @throws DatabaseAlreadyExist 
	 * @throws TableAlreadyExist 
	 * @throws IOException 
	 */
	public abstract Table performQuery(Query query)
			throws UnknownTableException,
	UnknownDatabaseException, UnknownColumnException,
	WrongDatatypeInputException, DatabaseAlreadyExist,
	TableAlreadyExist, IOException;
	/**
	 * Function responsible of taking a query and calling
	 * the suitable action.
	 * @param query The query to process.
	 * @return
	 * An integer representing the number of changed rows
	 * or null if it doesn't return anything.
	 * @throws UnknownTableException 
	 * @throws UnknownDatabaseException 
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 * @throws DatabaseAlreadyExist 
	 * @throws TableAlreadyExist 
	 * @throws IOException 
	 */
	public abstract Integer performUpdate(Query query)
			throws UnknownTableException,
	UnknownDatabaseException, UnknownColumnException,
	WrongDatatypeInputException, DatabaseAlreadyExist,
	TableAlreadyExist, IOException;
}