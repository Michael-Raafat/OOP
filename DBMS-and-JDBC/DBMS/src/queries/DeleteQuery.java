package queries;

import logicalComponents.Condition;
import queryBuilders.QueryBuildUnit;
/**
 * Query class of a delete query.
 * @author Amr
 *
 */
public class DeleteQuery extends TableQuery {
	/**
	 * Condition of deletion.
	 */
	 private Condition condition;

	 /**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public DeleteQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.DELETE_FROM_TABLE, buildUnit);
		this.condition = buildUnit.getCondition();
	}

	/**
	 * @return the condition
	 */
	public final Condition getCondition() {
		return condition;
	}
	@Override
	public String getMessage() {
		return "Delete rows command on the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
