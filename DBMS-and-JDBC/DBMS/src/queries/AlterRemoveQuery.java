package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Alter remove column from table Query.
 * @author Amr
 *
 */
public class AlterRemoveQuery extends AlterQuery {
	/**
	 * Constructor.
	 * @param unit
	 * The building unit.
	 */
	public AlterRemoveQuery(final QueryBuildUnit unit) {
		super(QueryAction.ALTER_TABLE_REMOVE, unit);
	}
	@Override
	public String getMessage() {
		return "Alter remove column command on the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
