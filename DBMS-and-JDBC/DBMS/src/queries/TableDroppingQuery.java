package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Query class of table dropping.
 * @author Amr
 *
 */
public class TableDroppingQuery extends TableQuery {

	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public TableDroppingQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.DROP_TABLE, buildUnit);
	}
	@Override
	public String getMessage() {
		return "Drop table command to drop the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
