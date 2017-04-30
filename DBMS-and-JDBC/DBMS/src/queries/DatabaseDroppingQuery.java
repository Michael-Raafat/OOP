package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Query class of dropping a database.
 * @author Amr
 *
 */
public class DatabaseDroppingQuery extends Query {
	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public DatabaseDroppingQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.DROP_DB, buildUnit);
	}
	@Override
	public String getMessage() {
		return "Drop database command to drop the database "
				+ this.getDatabaseName();
	}
}
