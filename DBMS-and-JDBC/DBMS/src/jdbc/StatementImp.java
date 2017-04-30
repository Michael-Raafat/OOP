package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import consolePortal.IjdbcAdaptor;
import models.Table;
/**
 * ToDos is our assumption and we didn't make sure yet from it  
 * @author Michael.
 *
 */
public class StatementImp implements java.sql.Statement{
	/**
	 * connection of database.
	 */
	private Connection connection;
	/**
	 * list of commands.
	 */
	private List<String> commands;
	/**
	 * boolean to check if connection
	 * and statement is closed or not.
	 */
	private boolean isClosed;
	/**
	 * an adaptor to help in calling actions from DBMS.
	 */
	private IjdbcAdaptor jdbcAdapter;
	/**
	 * our result.
	 */
	private ResultSet resultSet;
	/**
	 * the update counter.
	 */
	private int updateCount;
	/**
	 * Constuctor of StatementImp.
	 * @param connection
	 * connection to a specific database.
	 * @param connectionInfo
	 * properties of this connection.
	 * @param jdbcAdapter
	 * our helper that gets action from DBMS.
	 */
	public StatementImp(Connection connection, Properties connectionInfo, IjdbcAdaptor jdbcAdapter) {
		this.connection = connection;
		this.commands = new ArrayList<String>(); 
		this.jdbcAdapter = jdbcAdapter;
		this.isClosed = false;
		this.updateCount = -1;
	}
	
	/**
	 * throws SQL exception if the statement is closed.
	 * @throws SQLException if the statement is closed.
	 */
	private void throwSQlExceptionIfClosed() throws SQLException {
		if(isClosed) {
			throw new SQLException();
		}
	}
	
	@Override
	public void addBatch(String command) throws SQLException {
		throwSQlExceptionIfClosed();
		commands.add(command);
	}

	@Override
	public void clearBatch() throws SQLException {
		throwSQlExceptionIfClosed();
		commands = new ArrayList<String>();
	}

	@Override
	public void close() throws SQLException {
		this.isClosed = true;
		this.connection = null;
		this.commands = null;
		this.jdbcAdapter = null;
		this.updateCount = -1;
	}

	@Override
	public boolean execute(final String command) throws SQLException {
		throwSQlExceptionIfClosed();
		boolean ret = false;
		try {
			 if (jdbcAdapter.returnsTable(command)) {
				Table table = jdbcAdapter.performQuery(command);
				if (table.getRows().size() == 0) {
					ret = false;
				} else {
					ret = true;
				}
				updateCount = -1;
				resultSet = new ResultSetImp(table, this);
			} else if (jdbcAdapter.returnsInteger(command)) {
					updateCount = jdbcAdapter.performUpdate(command);
			} else {
				jdbcAdapter.performCommand(command);
				updateCount = -1;
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return ret;
	}
	@Override
	public final int[] executeBatch() throws SQLException {
		throwSQlExceptionIfClosed();
		int[] updateCounts = new int[commands.size()];
		for (int i = 0; i < commands.size(); i++) {
			try {
				if (this.execute(commands.get(i))) {
					updateCounts[i] = SUCCESS_NO_INFO;
				} else if(getUpdateCount() != -1) {
					updateCounts[i] = getUpdateCount();
				} else {
					updateCounts[i] = SUCCESS_NO_INFO;
				}
			} catch (SQLException e) {
				updateCounts[i] = EXECUTE_FAILED;
			}
		}
		return updateCounts;
	}
	@Override
	public ResultSet executeQuery(String command) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			if (jdbcAdapter.returnsTable(command)) {
				Table table = jdbcAdapter.performQuery(command);
				if (table.getRows().size() == 0) {
					return null;
				} else {
					resultSet = new ResultSetImp(
							jdbcAdapter.performQuery(command),
							this);
					return resultSet;
				}
			}
		} catch (Exception e) {
			throw new SQLException();
		}
		throw new SQLException();
	}
	@Override
	public int executeUpdate(String command) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			if (jdbcAdapter.returnsInteger(command)) {
				updateCount = jdbcAdapter.performUpdate(command);
				return updateCount;
			} else {
				this.execute(command);
				return 0;
			}
		} catch (Exception e) {
			throw new SQLException();
		}
	}
	@Override
	public Connection getConnection() throws SQLException {
		throwSQlExceptionIfClosed();
		return connection;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		throwSQlExceptionIfClosed();
		return resultSet;
	}
	@Override
	public int getUpdateCount() throws SQLException {
		throwSQlExceptionIfClosed();
		return updateCount;
	}
	@Override
	public int getQueryTimeout() throws SQLException {
		throw new UnsupportedOperationException();
	}
	@Override
	public void setQueryTimeout(int queryTimeout) throws SQLException {
		throw new UnsupportedOperationException();
	}
	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void cancel() throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void closeOnCompletion() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxRows() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetType() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
