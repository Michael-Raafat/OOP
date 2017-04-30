package jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import exceptions.UnknownColumnException;
import models.Datatype;
import models.Row;
import models.Table;
import parser.StringToData;

/**
 * ToDos is just an assumption so we won't solve it. 
 * till we know from online tester.
 * @author Michael.
 */
public class ResultSetImp implements java.sql.ResultSet {
	//TODO: when calling getFloat(3) and column 3 contains non float 
	//TODO: dealing with null and "NULL"
	/**
	 * our table.
	 */
	private Table table;
	/**
	 * meta data.
	 */
	private ResultSetMetaData metaData;
	/**
	 * Statement object.
	 */
	private Statement statement;
	/**
	 * the row index is one based.
	 */
	private int currentRowIndex;
	/**
	 * boolean to check if object is closed or not.
	 */
	private boolean isClosed;
	
	/**
	 * Constructor initializes the result set with the corresponding table.
	 * @param table for initialization
	 */
	public ResultSetImp(final Table table, final Statement statement) {
		this.table = table;
		this.metaData = new ResultSetMetaDataImp(table);
		this.statement = statement;
		this.currentRowIndex = 0;
		this.isClosed = false;
	}

	/**
	 * throws SQL exception if the result set is closed.
	 * @throws SQLException if the result set is closed.
	 */
	private void throwSQlExceptionIfClosed() throws SQLException {
		if (isClosed) {
			throw new SQLException();
		}
	}
	
	/**
	 * throws SQlException when getting value from non existing column.
	 * @param columnIndex index of the column.
	 * @throws SQLException when getting value from non existing column.
	 */
	private void throwSQLExceptionIfColumnIndexOutOfBounds(
			final int columnIndex) throws SQLException {
		if ((columnIndex < 1) || (columnIndex > metaData.getColumnCount())) {
			throw new SQLException();
		}
	}
	
	/**
	 * throws SQlException when getting a value as a datatype.
	 * from a column with a different datatype.
	 * For example: calling getFloat(3) and column 3 is of type Date.
	 * @param datatype the expected datatype.
	 * @param columnIndex the index of the column to test.
	 * @throws SQLException when getting a value as a datatype.
	 * from a column with a different datatype.
	 */
	private void throwSQLExceptionIfTypesNotMatch(
			final Datatype datatype, final int columnIndex) throws SQLException {
		if(table.getHeader().getcolumnType(columnIndex - 1) != datatype) {
			throw new SQLException();
		}
	}
	
	@Override
	public final boolean absolute(final int row) throws SQLException {
		throwSQlExceptionIfClosed();
		if(row >= 0) {
			currentRowIndex = row;
		} else {
			currentRowIndex = table.getRows().size() + row + 1;
		}
		return ((currentRowIndex > 0) &&
				(currentRowIndex <= table.getRows().size()));
	}

	@Override
	public final void afterLast() throws SQLException {
		throwSQlExceptionIfClosed();
		currentRowIndex = table.getRows().size() + 1;
	}

	@Override
	public final void beforeFirst() throws SQLException {
		throwSQlExceptionIfClosed();
		currentRowIndex = 0;
	}
	
	@Override
	public final void close() throws SQLException {
		isClosed = true;
		table = null;
		statement = null;
	}

	@Override
	public final int findColumn(final String columnLabel) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			return (table.getColumnIndexByName(columnLabel) + 1);
		} catch (UnknownColumnException e) {
			throw new SQLException();
		}
	}

	@Override
	public final boolean first() throws SQLException {
		return absolute(1);
	}

	@Override
	public final Date getDate(final int columnIndex) throws SQLException {
		throwSQlExceptionIfClosed();
		throwSQLExceptionIfColumnIndexOutOfBounds(columnIndex);
		throwSQLExceptionIfTypesNotMatch(Datatype.DATE, columnIndex);
		return StringToData.stringToDate(
				getCurrentRow().getCellDataByIndex(columnIndex - 1));
	}

	/**
	 * Gets the current row in the result set.
	 * @return the current row in the result set.
	 * @throws SQLException if row not exist.
	 */
	private Row getCurrentRow() throws SQLException {
		return table.getRows().get(currentRowIndex - 1);
	}
	
	@Override
	public final Date getDate(final String columnLabel) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			int columnIndex = table.getColumnIndexByName(columnLabel);
			throwSQLExceptionIfTypesNotMatch(Datatype.DATE, columnIndex + 1);
			return StringToData.stringToDate(
					getCurrentRow().getCellDataByIndex(columnIndex));
		} catch (UnknownColumnException e) {
			throw new SQLException();
		}
	}

	@Override
	public final float getFloat(final int columnIndex) throws SQLException {
		throwSQlExceptionIfClosed();
		throwSQLExceptionIfColumnIndexOutOfBounds(columnIndex);
		throwSQLExceptionIfTypesNotMatch(Datatype.FLOAT, columnIndex);
		return StringToData.stringToFloat(
				getCurrentRow().getCellDataByIndex(columnIndex - 1));
	}

	@Override
	public final float getFloat(final String columnLabel) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			int columnIndex = table.getColumnIndexByName(columnLabel);
			throwSQLExceptionIfTypesNotMatch(Datatype.FLOAT, columnIndex + 1);
			return StringToData.stringToFloat(
					getCurrentRow().getCellDataByIndex(columnIndex));
		} catch (UnknownColumnException e) {
			throw new SQLException();
		}
	}

	@Override
	public final int getInt(final int columnIndex) throws SQLException {
		throwSQlExceptionIfClosed();
		throwSQLExceptionIfColumnIndexOutOfBounds(columnIndex);
		throwSQLExceptionIfTypesNotMatch(Datatype.INTEGER, columnIndex);
		return StringToData.stringToInteger(
				getCurrentRow().getCellDataByIndex(columnIndex - 1));
	}

	@Override
	public final int getInt(final String columnLabel) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			int columnIndex = table.getColumnIndexByName(columnLabel);
			throwSQLExceptionIfTypesNotMatch(Datatype.INTEGER, columnIndex + 1);
			return StringToData.stringToInteger(
					getCurrentRow().getCellDataByIndex(columnIndex));
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public final ResultSetMetaData getMetaData() throws SQLException {
		throwSQlExceptionIfClosed();
		return metaData;
	}

	@Override
	public final Object getObject(final int columnIndex) throws SQLException {
		throwSQlExceptionIfClosed();
		throwSQLExceptionIfColumnIndexOutOfBounds(columnIndex);
		switch(table.getHeader().getcolumnType(columnIndex - 1)) {
		case INTEGER:
			return this.getInt(columnIndex);
		case VARCHAR:
			return this.getString(columnIndex);
		case FLOAT:
			return this.getFloat(columnIndex);
		case DATE:
			return this.getDate(columnIndex);
		default:
			return null;
		}
	}

	@Override
	public final Statement getStatement() throws SQLException {
		throwSQlExceptionIfClosed();
		return statement;
	}

	@Override
	public final String getString(final int columnIndex) throws SQLException {
		throwSQlExceptionIfClosed();
		throwSQLExceptionIfColumnIndexOutOfBounds(columnIndex);
		throwSQLExceptionIfTypesNotMatch(Datatype.VARCHAR, columnIndex);
		return getCurrentRow().getCellDataByIndex(columnIndex - 1);
	}

	@Override
	public final String getString(final String columnLabel) throws SQLException {
		throwSQlExceptionIfClosed();
		try {
			int columnIndex = table.getColumnIndexByName(columnLabel);
			throwSQLExceptionIfTypesNotMatch(Datatype.VARCHAR, columnIndex + 1);
			return getCurrentRow().getCellDataByIndex(columnIndex);
		} catch (UnknownColumnException e) {
			throw new SQLException();
		}
	}

	@Override
	public final boolean isAfterLast() throws SQLException {
		throwSQlExceptionIfClosed();
		return currentRowIndex > table.getRows().size();
	}

	@Override
	public final boolean isBeforeFirst() throws SQLException {
		throwSQlExceptionIfClosed();
		return currentRowIndex < 1;
	}

	@Override
	public final boolean isClosed() throws SQLException {
		return isClosed;
	}

	@Override
	public final boolean isFirst() throws SQLException {
		throwSQlExceptionIfClosed();
		return currentRowIndex == 1;
	}

	@Override
	public final boolean isLast() throws SQLException {
		throwSQlExceptionIfClosed();	
		return currentRowIndex == table.getRows().size();
	}

	@Override
	public final boolean last() throws SQLException {
		return absolute(-1);
	}

	@Override
	public final boolean next() throws SQLException {
		return absolute(currentRowIndex + 1);
	}

	@Override
	public final boolean previous() throws SQLException {
		return absolute(currentRowIndex - 1);
	}

	@Override
	public final boolean isWrapperFor(final Class<?> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final <T> T unwrap(final Class<T> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final void cancelRowUpdates() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void deleteRow() throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final Array getArray(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Array getArray(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final InputStream getAsciiStream(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final InputStream getAsciiStream(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final BigDecimal getBigDecimal(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final BigDecimal getBigDecimal(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final BigDecimal getBigDecimal(final int columnIndex, final int scale) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final BigDecimal getBigDecimal(final String columnLabel, final int scale) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final InputStream getBinaryStream(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final InputStream getBinaryStream(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Blob getBlob(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Blob getBlob(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean getBoolean(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean getBoolean(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final byte getByte(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final byte getByte(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final byte[] getBytes(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final byte[] getBytes(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Reader getCharacterStream(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Reader getCharacterStream(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Clob getClob(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Clob getClob(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int getConcurrency() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final String getCursorName() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Date getDate(final int columnIndex, final Calendar cal) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Date getDate(final String columnLabel, final Calendar cal) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final double getDouble(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final double getDouble(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int getFetchDirection() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int getFetchSize() throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final int getHoldability() throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final long getLong(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final long getLong(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final Reader getNCharacterStream(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Reader getNCharacterStream(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final NClob getNClob(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final NClob getNClob(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final String getNString(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final String getNString(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final Object getObject(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Object getObject(final int columnIndex, final Map<String, Class<?>> map) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Object getObject(final String columnLabel, final Map<String, Class<?>> map) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final <T> T getObject(final int columnIndex, final Class<T> type) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final <T> T getObject(final String columnLabel, final Class<T> type) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Ref getRef(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Ref getRef(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int getRow() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final RowId getRowId(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final RowId getRowId(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final SQLXML getSQLXML(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final SQLXML getSQLXML(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final short getShort(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final short getShort(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final Time getTime(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Time getTime(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Time getTime(final int columnIndex, final Calendar cal) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Time getTime(final String columnLabel, final Calendar cal) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Timestamp getTimestamp(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Timestamp getTimestamp(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Timestamp getTimestamp(
			final int columnIndex, final Calendar cal) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Timestamp getTimestamp(
			final String columnLabel, final Calendar cal) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int getType() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final URL getURL(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final URL getURL(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final InputStream getUnicodeStream(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final InputStream getUnicodeStream(
			final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void insertRow() throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final void moveToCurrentRow() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void moveToInsertRow() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void refreshRow() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean relative(final int rows) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean rowDeleted() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean rowInserted() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean rowUpdated() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void setFetchDirection(final int direction) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void setFetchSize(final int rows) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateArray(final int columnIndex, final Array x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateArray(final String columnLabel, final Array x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateAsciiStream(
			final int columnIndex, final InputStream x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateAsciiStream(
			final String columnLabel, final InputStream x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateAsciiStream(
			final int columnIndex, final InputStream x, final int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateAsciiStream(
			final String columnLabel, final InputStream x, final int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateAsciiStream(
			final int columnIndex, final InputStream x, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateAsciiStream(
			final String columnLabel, final InputStream x, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBigDecimal(
			final int columnIndex, final BigDecimal x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBigDecimal(
			final String columnLabel, final BigDecimal x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBinaryStream(
			final int columnIndex, final InputStream x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBinaryStream(
			final String columnLabel, final InputStream x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBinaryStream(
			final int columnIndex, final InputStream x, final int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBinaryStream(
			final String columnLabel, final InputStream x, final int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBinaryStream(
			final int columnIndex, final InputStream x, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBinaryStream(
			final String columnLabel, final InputStream x, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBlob(final int columnIndex, final Blob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBlob(final String columnLabel, final Blob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBlob(
			final int columnIndex, final InputStream inputStream) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBlob(
			final String columnLabel, final InputStream inputStream) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBlob(
			final int columnIndex, final InputStream inputStream,
			final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBlob(
			final String columnLabel, final InputStream inputStream,
			final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBoolean(final int columnIndex, final boolean x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBoolean(
			final String columnLabel, final boolean x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateByte(final int columnIndex, final byte x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateByte(final String columnLabel, final byte x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBytes(final int columnIndex, final byte[] x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateBytes(final String columnLabel, final byte[] x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateCharacterStream(
			final int columnIndex, final Reader x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateCharacterStream(
			final String columnLabel, final Reader reader) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateCharacterStream(
			final int columnIndex, final Reader x, final int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateCharacterStream(
			final String columnLabel, final Reader reader, final int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateCharacterStream(
			final int columnIndex, final Reader x, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateCharacterStream(
			final String columnLabel, final Reader reader, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateClob(final int columnIndex, final Clob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateClob(final String columnLabel, final Clob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateClob(final int columnIndex, final Reader reader) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateClob(
			final String columnLabel, final Reader reader) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateClob(
			final int columnIndex, final Reader reader, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateClob(
			final String columnLabel, final Reader reader, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateDate(final int columnIndex, final Date x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateDate(final String columnLabel, final Date x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateDouble(final int columnIndex, final double x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateDouble(final String columnLabel, final double x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateFloat(final int columnIndex, final float x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateFloat(final String columnLabel, final float x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateInt(final int columnIndex, final int x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateInt(final String columnLabel, final int x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateLong(final int columnIndex, final long x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateLong(final String columnLabel, final long x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNCharacterStream(
			final int columnIndex, final Reader x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNCharacterStream(
			final String columnLabel, final Reader reader) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNCharacterStream(
			final int columnIndex, final Reader x, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNCharacterStream(
			final String columnLabel, final Reader reader, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNClob(
			final int columnIndex, final NClob nClob) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNClob(
			final String columnLabel, final NClob nClob) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNClob(
			final int columnIndex, final Reader reader) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNClob(
			final String columnLabel, final Reader reader) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNClob(
			final int columnIndex, final Reader reader, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNClob(
			final String columnLabel, final Reader reader, final long length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNString(
			final int columnIndex, final String nString) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNString(
			final String columnLabel, final String nString) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNull(final int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateNull(final String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateObject(final int columnIndex, final Object x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateObject(final String columnLabel, final Object x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateObject(
			final int columnIndex, final Object x, final int scaleOrLength) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateObject(
			final String columnLabel, final Object x,
			final int scaleOrLength) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateRef(final int columnIndex, final Ref x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateRef(final String columnLabel, final Ref x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateRow() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateRowId(final int columnIndex, final RowId x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateRowId(final String columnLabel, final RowId x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateSQLXML(
			final int columnIndex, final SQLXML xmlObject) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateSQLXML(
			final String columnLabel, final SQLXML xmlObject) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateShort(final int columnIndex, final short x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateShort(final String columnLabel, final short x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateString(final int columnIndex, final String x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateString(final String columnLabel, final String x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateTime(final int columnIndex, final Time x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateTime(final String columnLabel, final Time x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateTimestamp(
			final int columnIndex, final Timestamp x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateTimestamp(
			final String columnLabel, final Timestamp x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean wasNull() throws SQLException {
		throw new UnsupportedOperationException();
	}

}