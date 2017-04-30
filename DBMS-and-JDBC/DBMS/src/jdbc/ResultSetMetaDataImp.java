package jdbc;

import java.sql.SQLException;
import models.Datatype;
import models.Header;
import models.Table;
/**
 * 
 * @author Michael.
 *
 */
public class ResultSetMetaDataImp implements java.sql.ResultSetMetaData {
	/**
	 * table resultset.
	 */
	Table table;
	/**
	 * header.
	 */
	Header head;
	/**
	 * constructor
	 * @param table
	 * table of result set.
	 */
	public ResultSetMetaDataImp(Table table) {
		this.table = table;
		this.head = table.getHeader();
	}
	
	@Override
	public int getColumnCount() throws SQLException {
		try {
			return head.getSize();
		} catch (Exception e) {
			throw new SQLException();
		}
	}
	
	@Override
	public String getColumnLabel(int arg0) throws SQLException {
		return this.getColumnName(arg0);
	}
	
	@Override
	public String getColumnName(int arg0) throws SQLException {
		if (arg0 <= 0 || arg0 > head.getSize() ) {
			throw new SQLException();
		}
		try {
			return head.getcolumnName(arg0 - 1);
		} catch (Exception e) {
			throw new SQLException();
		}
	}
	
	@Override
	public int getColumnType(int arg0) throws SQLException {
		if (head.getcolumnType(arg0 - 1).equals(Datatype.INTEGER)) {
			return java.sql.Types.INTEGER;
		} else if (head.getcolumnType(arg0 - 1).equals(Datatype.VARCHAR)) {
			return java.sql.Types.VARCHAR;
		} else if (head.getcolumnType(arg0 - 1).equals(Datatype.FLOAT)) {
			return java.sql.Types.FLOAT;
		} else if (head.getcolumnType(arg0 - 1).equals(Datatype.DATE)) {
			return java.sql.Types.DATE;
		} else {
			throw new SQLException(); 
		}
	}
	
	@Override
	public String getTableName(int arg0) throws SQLException {
		try {
			return table.getName();
		} catch (Exception e) {
			throw new SQLException();
		}
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
	public String getCatalogName(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getColumnClassName(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getColumnDisplaySize(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getColumnTypeName(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getPrecision(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getScale(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSchemaName(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAutoIncrement(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCaseSensitive(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCurrency(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDefinitelyWritable(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int isNullable(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isReadOnly(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSearchable(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSigned(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWritable(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
