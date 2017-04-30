package queries;

import java.util.List;

import queryBuilders.QueryBuildUnit;
/**
 * Query class of insertion of rows that is
 * a special case as you specify which columns
 * to add the information to it.
 * @author Amr
 *
 */
public class InsertIntoSpecificColumnsQuery extends InsertQuery {
	/**
	 * Columns to add the new values to.
	 */
	private List<String> columnToAddTo;
	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public InsertIntoSpecificColumnsQuery(final QueryBuildUnit buildUnit) {
		super(buildUnit);
		this.columnToAddTo = buildUnit.getColumnNames();
	}

	/**
	 * @return the columnToAddTo
	 */
	public final List<String> getColumnToAddTo() {
		return columnToAddTo;
	}
}
