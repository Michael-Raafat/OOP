package queries;
/**
 * Enum that defines the type of actions of a query.
 * @author Amr
 *
 */
public enum QueryAction {
	/**
	 * The different valid actions that a query might do.
	 */
	CREATE_DB, CREATE_TABLE, DROP_DB, DROP_TABLE, USE_DB,
	/**
	 * More actions.
	 */
	ALTER_TABLE_REMOVE, ALTER_TABLE_ADD, SELECT_TABLE, UPDATE_TABLE,
	/**
	 */
	INSERT_INTO_TABLE, DELETE_FROM_TABLE, UNION_SELECT;
	/**
	 * Function that will return true if the type of action is supposed
	 * to return an integer value.
	 * @return
	 * True if this action can return an integer.
	 */
	public boolean willReturnInteger() {
		switch (this) {
			case INSERT_INTO_TABLE:
			case DELETE_FROM_TABLE:
			case UPDATE_TABLE:
				return true;
			default:
				return false;
		}
	}
	/**
	 * Function that will return true if the type of action is supposed
	 * to return a table.
	 * @return
	 * True if this action can return a table.
	 */
	public boolean willReturnTable() {
		switch (this) {
		case SELECT_TABLE:
		case UNION_SELECT:
			return true;
		default:
			return false;
		}
	}
}
