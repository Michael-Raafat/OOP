package queryBuilders;

import queries.AlterAddQuery;
import queries.AlterRemoveQuery;
import queries.DatabaseCreationQuery;
import queries.DatabaseDroppingQuery;
import queries.DeleteQuery;
import queries.InsertIntoSpecificColumnsQuery;
import queries.InsertQuery;
import queries.Query;
import queries.QueryAction;
import queries.SelectQuery;
import queries.SelectSpecificColumnsQuery;
import queries.TableCreationQuery;
import queries.TableDroppingQuery;
import queries.UnionSelectQuery;
import queries.UpdateQuery;
import queries.UseDatabaseQuery;
/**
 * Implementation of the IQueryFactory interface.
 * Singleton.
 * @author Amr
 *
 */
public class QueryFactory implements IQueryFactory {
	/**
	 * Constructor of the class.
	 */
	public QueryFactory() {
	}
	@Override
	public final Query buildQuery(final QueryAction action,
			final QueryBuildUnit unit) {
		switch (action) {
		case CREATE_DB:
			return new DatabaseCreationQuery(unit);
		case CREATE_TABLE:
			return new TableCreationQuery(unit);
		case DELETE_FROM_TABLE:
			return new DeleteQuery(unit);
		case DROP_DB:
			return new DatabaseDroppingQuery(unit);
		case DROP_TABLE:
			return new TableDroppingQuery(unit);
		case INSERT_INTO_TABLE:
			if (unit.getColumnNames() == null) {
				return new InsertQuery(unit);
			} else {
				return new InsertIntoSpecificColumnsQuery(unit);
			}
		case SELECT_TABLE:
			if (unit.getColumnNames() == null) {
				return new SelectQuery(unit);
			} else {
				return new SelectSpecificColumnsQuery(unit);
			}
		case UPDATE_TABLE:
			return new UpdateQuery(unit);
		case USE_DB:
			return new UseDatabaseQuery(unit);
		case ALTER_TABLE_ADD:
			return new AlterAddQuery(unit);
		case ALTER_TABLE_REMOVE:
			return new AlterRemoveQuery(unit);
		case UNION_SELECT:
			return new UnionSelectQuery(unit);
		default:
			throw new RuntimeException();
		}
	}

}
