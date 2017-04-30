package queryBuilders;

import queries.Query;
import queries.QueryAction;

/**
 * Interface of the query factory that
 * should be responsible of creating new queries.
 * @author Amr
 *
 */
public interface IQueryFactory {
	/**
	 * Function responsible of generating the query using
	 * the query build unit that should contain all needed information and
	 * the action type of the query.
	 * @param action
	 * Enum value that will point to which concrete query it is.
	 * @param unit
	 * A pure data structure that should contain all needed information to
	 * build the query.
	 * @return
	 * A valid and suitable query class to its abstract class.
	 */
	Query buildQuery(QueryAction action, QueryBuildUnit unit);
}
