package printingService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Michael.
 *
 */
public class PrintResultHandler {
	/**
	 * length of each column.
	 */
	private ArrayList<Integer> limit;
	/**
	 * initialize of format.
	 * @param result
	 * ResultSet to be printed.
	 * @throws SQLException
	 */
	public PrintResultHandler(final ResultSet result) throws SQLException {
		this.limit = new ArrayList<Integer>();
		this.updateLimit(result);
	}
	/**
	 * print a complete border line.
	 * @param result
	 * table to be printed.
	 * @param size
	 * number of its columns.
	 * @throws SQLException
	 */
	public final void printFullBorderLine(final ResultSet result,
			final int size) throws SQLException {
		for (int i = 1; i <= size; i++) {
			this.printBorder(i);
		}
		this.printEndBorder();
	}
	/**
	 * prints header of result set table.
	 * @param result
	 * result set to be printed.
	 * @param size
	 * number of columns.
	 * @throws SQLException
	 * if invalid data is detected.
	 */
	public final void printFullHeaderLine(final ResultSet result,
			final int size) throws SQLException {
		for (int i = 1; i <= size; i++) {
			String temp = result.getMetaData().getColumnName(i);
			this.printSideBorder();
			this.printElement(temp);
			this.printFormatSpace(temp.length() + 1, i);
		}
		this.printEndSideBorder();
	}
	/**
	 * prints header of result set table.
	 * @param result
	 * result set to be printed.
	 * @param size
	 * number of columns.
	 * @throws SQLException
	 * if invalid data is detected.
	 */
	public final void printFullElementLine(final ResultSet result,
			final int size) throws SQLException {
		for (int i = 1; i <= size; i++) {
			this.printSideBorder();
			String temp = String.valueOf(result.getObject(i));
			this.printElement(temp);
			this.printFormatSpace(temp.length() + 1, i);
		}
		this.printEndSideBorder();
	}
	/**
	 * to update column length.
	 * @param result
	 * table to be printed.
	 * @throws SQLException
	 * if invalid data is detected.
	 */
	private void updateLimit(final ResultSet result) throws SQLException {
		for (int j = 1; j <= result.getMetaData(
				).getColumnCount(); j++) {
			int max = result.getMetaData(
					).getColumnName(j).length();
			for (int i = 1; result.absolute(i); i++) {
				max = Math.max(
						max, String.valueOf(
						result.getObject(j)).length());
			}
			this.limit.add(max + 2);
		}
		result.absolute(1);
	}
	/**
	 * print border of each column.
	 * @param index
	 * number of index of limit list.
	 */
	private void printBorder(final int index) {
		System.out.print("+");
		for(int i = 0; i < limit.get(index - 1); i++) {
			System.out.print("-");
		}
	}
	/**
	 * print last character in border line.
	 */
	private void printEndBorder() {
		System.out.println("+");
	}
	/**
	 * print side border of each row.
	 */
	private void printSideBorder() {
		System.out.print("|");
	}
	/**
	 * print element.
	 * @param element
	 * element should be printed.
	 */
	private void printElement(final String element) {
		System.out.print(" " + element);
	}
	/**
	 * printing spaces
	 * to reach limit length of each column.
	 * @param current
	 * number of characters taken to continue.
	 * limit - index = number of extra spaces.
	 * @param index
	 * index at which we continue printing spaces.
	 */
	private void printFormatSpace(final int current, final int index) {
		for (int i = current; i < limit.get(index - 1); i++) {
			System.out.print(" ");
		}
	}
	/**
	 * print last character in row.
	 * to end any line containing elements.
	 */
	private void printEndSideBorder() {
		System.out.println("|");
	}
}
