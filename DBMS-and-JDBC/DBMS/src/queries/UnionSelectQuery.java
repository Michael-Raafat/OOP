package queries;

import java.util.ArrayList;
import java.util.List;

import parser.Order;
import queryBuilders.QueryBuildUnit;
import queryBuilders.QueryFactory;

/**
 * Query class for the union selection class.
 * @author Amr
 *
 */
public class UnionSelectQuery extends Query {
	/**
	 * The first selection query.
	 */
	private List<SelectQuery> selectQueries;
	/**
	 * Distinct union or not.
	 */
	private List<Boolean> distinct;
	/**
	 * Order the result or not.
	 */
	private boolean order;
	/**
	 * The order columns.
	 */
	private List<String> orderValues;
	/**
	 * The order types.
	 */
	private List<Order> orderTypes;
	/**
	 * Constructor.
	 * @param unit
	 * The build unit.
	 */
	public UnionSelectQuery(final QueryBuildUnit unit) {
		super(QueryAction.UNION_SELECT, unit);
		QueryFactory factory = new QueryFactory();
		this.selectQueries = new ArrayList<SelectQuery>();
		for (int i = 0; i < unit.getBuildUnits().size(); i++) {
			this.selectQueries.add((SelectQuery) factory.buildQuery(
					QueryAction.SELECT_TABLE,
					unit.getBuildUnits().get(i)));
		}
		this.distinct = unit.getBooleanList();
		this.order = unit.isOrder();
		this.orderValues = unit.getNewValues();
		this.orderTypes = unit.getOrderTypes();
	}
	/**
	 * @return the distinct
	 */
	public List<Boolean> getDistinct() {
		return distinct;
	}
	/**
	 * @return the order
	 */
	public final boolean isOrder() {
		return order;
	}
	/**
	 * @return the orderValues
	 */
	public final List<String> getOrderValues() {
		return orderValues;
	}
	/**
	 * @return the orderTypes
	 */
	public final List<Order> getOrderTypes() {
		return orderTypes;
	}
	@Override
	public String getMessage() {
		StringBuilder infoMessage = new StringBuilder(
				"Union select command on the tables ");
		infoMessage.append(this.selectQueries.get(0).getTableName());
		infoMessage.append(" from the database ");
		infoMessage.append(this.selectQueries.get(0).getDatabaseName());
		for (int i = 1; i < this.selectQueries.size(); i++) {
			infoMessage.append(" and ");
			infoMessage.append(this.selectQueries.get(i).getTableName());
			infoMessage.append(" from the database ");
			infoMessage.append(this.selectQueries.get(i).getDatabaseName());
		}
		return infoMessage.toString();
	}
	/**
	 * @return the selectQueries
	 */
	public List<SelectQuery> getSelectQueries() {
		return selectQueries;
	}

}
