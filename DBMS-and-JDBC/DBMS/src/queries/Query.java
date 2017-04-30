package queries;

import queryBuilders.QueryBuildUnit;

/**
 * Abstract class of query
 * The class responsible of communication between parsing
 * system and the other systems.
 * @author Amr
 *
 */
public abstract class Query {
	/**
	 * Defines the action performed by the query.
	 */
	private QueryAction action;
	/**
	 * Database Name.
	 */
	private String databaseName;
	/**
	 * Constructor setting the action of the query to a specific action.
	 * @param specifiedQueryAction
	 * the action of the query
	 * @param unit
	 * The query build unit.
	 */
	public Query(final QueryAction specifiedQueryAction,
			final QueryBuildUnit unit) {
		this.action = specifiedQueryAction;
		if (unit.getDatabaseName() != null) {
			this.databaseName = unit.getDatabaseName().toLowerCase();
		}
	}

	/**
	 * @return the action
	 */
	public final QueryAction getAction() {
		return action;
	}
	
	/**
	 * @return the databaseName
	 */
	public final String getDatabaseName() {
		return databaseName;
	}
	/**
	 * Function that will return true if the type of action of a query
	 * is supposed to return an integer value.
	 * @return
	 * True if this query can return an integer.
	 */
	public final boolean willReturnInteger() {
		return this.action.willReturnInteger();
	}
	/**
	 * Function that will return true if the type of action of a query
	 * is supposed to return a table.
	 * @return
	 * True if this query can return a table.
	 */
	public final boolean willReturnTable() {
		return this.action.willReturnTable();
	}
	/**
	 * Returns a message of this query.
	 * @return
	 * A string containing the message.
	 */
	public abstract String getMessage();
}
