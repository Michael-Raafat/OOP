package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Alter query of a table.
 * @author Amr
 *
 */
public abstract class AlterQuery extends TableQuery {
	/**
	 * The column name of the column to alter.
	 */
	private String columnName;
	/**
	 * Constructor of any alter query.
	 * @param unit
	 * The build unit.
	 * @param action
	 * The action to be done by a query.
	 */
	public AlterQuery(final QueryAction action,
			final QueryBuildUnit unit) {
		super(action, unit);
		this.columnName = unit.getColumnNames().get(0);
	}
	/**
	 * @return the columnName
	 */
	public final String getColumnName() {
		return columnName;
	}

}
