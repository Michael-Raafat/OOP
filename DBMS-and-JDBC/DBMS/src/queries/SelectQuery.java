package queries;

import java.util.List;

import logicalComponents.Condition;
import parser.Order;
import queryBuilders.QueryBuildUnit;
/**
 * Query class of selection of rows that meet
 * a certain condition.
 * @author Amr
 *
 */
public class SelectQuery extends TableQuery {

	/**
	 * Condition of the selection.
	 */
	private Condition condition;
	/**
	 * Boolean to indicate if it is a distinct order.
	 */
	private boolean distinct;
	/**
	 * Boolean to indicate if it is an order by statement.
	 */
	private boolean order;
	/**
	 * Column to sort by.
	 */
	private List<String> columnOrderValues;
	/**
	 * Sorting type of each column.
	 */
	private List<Order> columnOrderType;
	/**
	 * @return the distinct
	 */
	public final boolean isDistinct() {
		return distinct;
	}
	/**
	 * @return the order
	 */
	public final boolean isOrder() {
		return order;
	}
	/**
	 * @return the columnOrderValues
	 */
	public final List<String> getColumnOrderValues() {
		return columnOrderValues;
	}
	/**
	 * @return the columnOrderType
	 */
	public final List<Order> getColumnOrderType() {
		return columnOrderType;
	}
	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public SelectQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.SELECT_TABLE, buildUnit);
		this.condition = buildUnit.getCondition();
		this.distinct = buildUnit.isDistinct();
		this.order = buildUnit.isOrder();
		this.columnOrderType = buildUnit.getOrderTypes();
		this.columnOrderValues = buildUnit.getNewValues();
	}
	/**
	 * @return the condition
	 */
	public final Condition getCondition() {
		return condition;
	}
	@Override
	public String getMessage() {
		return "Select from table command on the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
