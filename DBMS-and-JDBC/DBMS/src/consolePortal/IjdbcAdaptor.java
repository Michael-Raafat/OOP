package consolePortal;

import java.sql.SQLException;

import models.Table;
/**
 * JDBC adaptor. responsible of combining the functionalities
 * of the parser and the engine.
 * @author Amr
 *
 */
public interface IjdbcAdaptor {
	/**
	 * Performs a query and returns a table if the function
	 * can return a table.
	 * @param command
	 * String to be parsed and executed.
	 * @return
	 * Table if the command really returns a table or a null
	 * value if it doesn't.
	 * @throws SQLException 
	 */
	Table performQuery(String command)
			throws SQLException;
	/**
	 * Performs a query and returns an Integer if the function
	 * can return an integer.
	 * @param command
	 * String to be parsed and executed.
	 * @return
	 * Integer if the command really returns an integer or a null
	 * value if it doesn't.
	 * @throws SQLException 
	 */
	Integer performUpdate(String command)
			throws SQLException;
	/**
	 * Performs a query.
	 * @param command
	 * String to be parsed and executed.
	 * @throws SQLException
	 */
	void performCommand(String command)
			throws SQLException;
	/**
	 * Funtion to check if a command can return a table.
	 * @param command
	 * String to check.
	 * @return
	 * True if it can return a table.
	 * @throws SQLException
	 */
	boolean returnsTable(String command)
			throws SQLException;
	/**
	 * Function to check if a command can return an Integer.
	 * @param command
	 * String to check.
	 * @return
	 * True if it can return a table.
	 * @throws SQLException
	 */
	boolean returnsInteger(String command)
			throws SQLException;
	/**
	 * Connects to a database through the url and sets
	 * the save type.
	 * @param path
	 * Path of the database.
	 * @param url
	 * String url of the databases.
	 * @return
	 * True if it finds and connects successfully.
	 */
	boolean connectToURL(String path, String url);
	/**
	 * Checks if the url passed is valid.
	 * @param url
	 * String containing the url.
	 * @return
	 * True if it is valid.
	 */
	boolean acceptsURL(String url);
}
