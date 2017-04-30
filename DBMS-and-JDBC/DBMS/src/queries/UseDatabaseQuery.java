package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Query class of the use database command.
 * @author Amr
 *
 */
public class UseDatabaseQuery extends Query {

	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public UseDatabaseQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.USE_DB, buildUnit);
	}
	@Override
	public String getMessage() {
		return "Use database command to use the database "
				+ this.getDatabaseName();
	}
}
