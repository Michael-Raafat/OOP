package queries;

import queryBuilders.QueryBuildUnit;
/**
 * Query class of database creation.
 * @author Amr
 *
 */
public class DatabaseCreationQuery extends Query {
	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public DatabaseCreationQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.CREATE_DB, buildUnit);
	}
	@Override
	public String getMessage() {
		return "Create database command "
				+ "to create the database "
				+ this.getDatabaseName();
	}
}
