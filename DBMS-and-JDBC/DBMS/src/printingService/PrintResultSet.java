package printingService;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Michael.
 *
 */
public final class PrintResultSet {
	/**
	 * printer of ResultSet.
	 */
	private static PrintResultSet printer;
	/**
	 * private constructor to be singleton.
	 */
	private PrintResultSet(){
	}
	/**
	 * instance of PrintResultSet.
	 * @return
	 * ResultSet.
	 */
	public static PrintResultSet getInstance(){
		if (printer == null) {
			printer = new PrintResultSet();
		}
		return printer;
	}
	/**
	 * prints result set.
	 * @param result
	 * result set to be printed.
	 * @throws SQLException
	 * if invalid data are detected.
	 */
	public void printResultSet(final ResultSet result) throws SQLException {
		PrintResultHandler handle = new PrintResultHandler(result);
		handle.printFullBorderLine(result,
				result.getMetaData().getColumnCount());
		handle.printFullHeaderLine(result,
				result.getMetaData().getColumnCount());
		handle.printFullBorderLine(result,
				result.getMetaData().getColumnCount());
		for (int i = 1; result.absolute(i); i++) {
			handle.printFullElementLine(result,
					result.getMetaData().getColumnCount());
		}
		handle.printFullBorderLine(result,
				result.getMetaData().getColumnCount());
	}

}
