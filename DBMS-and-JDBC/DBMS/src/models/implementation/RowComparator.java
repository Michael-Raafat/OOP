package models.implementation;

import java.util.Comparator;
import java.util.List;

import conditionService.ComparisonMaster;
import models.Datatype;
import models.Row;
import parser.Order;
/**
 * Class that compares two rows.
 * @author Amr
 *
 */
public class RowComparator implements Comparator<Row> {
	/**
	 * The column indexes to sort by.
	 */
	private List<Integer> columnIndexes;
	/**
	 * The ordering type of the columns.
	 */
	private List<Order> columnOrderType;
	/**
	 * The datatypes of the columns to compare by.
	 */
	private List<Datatype> columnDatatypes;
	/**
	 * Constructor.Sets the parameters of the comparison.
	 * @param indexes
	 * The indexes of the columns to sort by.
	 * @param orderList
	 * The order types of each column.
	 * @param datatypes
	 * The datatype of each column.
	 */
	public RowComparator(final List<Integer> indexes,
			final List<Order> orderList,
			final List<Datatype> datatypes) {
		this.columnIndexes = indexes;
		this.columnDatatypes = datatypes;
		this.columnOrderType = orderList;
	}

	@Override
	public final int compare(final Row o1, final Row o2) {
		for (int i = 0; i < columnIndexes.size(); i++) {
			String data1 = o1.getCellDataByIndex(columnIndexes.get(i));
			String data2 = o2.getCellDataByIndex(columnIndexes.get(i));
			int result = ComparisonMaster.compareTo(data1, data2,
					columnDatatypes.get(i));
			if (result > 0) {
				if (columnOrderType.get(i) == Order.ASC) {
					return 1;
				} else {
					return -1;
				}
			} else if (result < 0) {
				if (columnOrderType.get(i) == Order.ASC) {
					return -1;
				} else {
					return 1;
				}
			}
		}
		return 0;
	}
}
