package consolePortal;

import java.sql.SQLException;

import databaseManagement.DatabaseEngine;
import databaseManagement.DatabaseEngineImplementation;
import exceptions.UnknownExtension;
import fileManager.FileManagerSingleton;
import logs.LogService;
import models.Table;
import parser.JdbcParser;
import parser.Parser;
import parser.ParserImplementation;
import queries.Query;
/**
 * Implementation of the DBMS&Parser to jdbc adapter interface.
 * @author Amr
 *
 */
public class JdbcAdaptor implements IjdbcAdaptor {
	/**
	 * JDBC parser for the url.
	 */
	private JdbcParser urlParser;
	/**
	 * The object responsible of parsing the data.
	 */
	private Parser parser;
	/**
	 * The class responsible of doing the action.
	 */
	private DatabaseEngine engine;
	/**
	 * Logger.
	 */
	private LogService logger;
	/**
	 * Constructor of the adaptor.
	 */
	public JdbcAdaptor() {
		parser = new ParserImplementation();
		logger = new LogService();
		try {
			engine = new DatabaseEngineImplementation();
		} catch (UnknownExtension e) {
			logger.printError(e.getMessage());
		}
		urlParser = new JdbcParser();
	}

	@Override
	public final Table performQuery(final String command) throws SQLException {
		try {
			Query query = parser.parseCommand(command);
			logger.printMessage(query.getMessage());
			Table table = engine.performQuery(query);
			logger.printMessage("Operation Successful");
			return table;
		} catch (Exception e) {
			logger.printError(e.getMessage());
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public final Integer performUpdate(final String command)
			throws SQLException {
		try {
			Query query = parser.parseCommand(command);
			logger.printMessage(query.getMessage());
			Integer integer = engine.performUpdate(query);
			logger.printMessage("Operation Successful");
			return integer;
		} catch (Exception e) {
			logger.printError(e.getMessage());
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public final void performCommand(final String command) throws SQLException {
		try {
			Query query = parser.parseCommand(command);
			logger.printMessage(query.getMessage());
			engine.performAction(query);
			logger.printMessage("Operation Successful");
		} catch (Exception e) {
			logger.printError(e.getMessage());;
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public final boolean returnsTable(final String command) throws SQLException {
		try {
			return parser.getCommandAction(command).willReturnTable();
		} catch (Exception e) {
			logger.printError(e.getMessage());;
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public final boolean returnsInteger(final String command) throws SQLException {
		try {
			return parser.getCommandAction(command).willReturnInteger();
		} catch (Exception e) {
			logger.printError(e.getMessage());
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public final boolean connectToURL(
			final String path, final String url) {
		try {
			FileManagerSingleton.setInstance(path,
			urlParser.getExtensionFromSubprotocol(url));
			return true;
		} catch (Exception e) {
			logger.printError(e.getMessage());
			return false;
		}
	}

	@Override
	public final boolean acceptsURL(final String url) {
		return urlParser.isAcceptableURL(url);
	}

}
