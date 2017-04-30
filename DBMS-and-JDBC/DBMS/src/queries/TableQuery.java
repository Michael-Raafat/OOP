package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Abstract class for queries that deal with tables.
 * @author Amr
 *
 */
public abstract class TableQuery extends Query {

	/**
	 * Table containing the records.
	 */
	private String tableName;
	/**
	 * Constructor of the abstract class.
	 * @param specifiedQueryAction
	 * The action of the class.
	 * @param unit
	 * The build unit.
	 */
	public TableQuery(final QueryAction specifiedQueryAction,
			final QueryBuildUnit unit) {
		super(specifiedQueryAction, unit);
		if (unit.getTableName() != null) {
			this.tableName = unit.getTableName().toLowerCase();
		}
	}

	/**
	 * @return the tableName
	 */
	public final String getTableName() {
		return tableName;
	}
}
