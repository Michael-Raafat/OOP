package queryBuilders;

import java.util.List;

import logicalComponents.Condition;
import models.Datatype;
import parser.Order;

/**
 * A query build unit should be able to hold any information
 * needed to build any query.
 * @author Amr
 *
 */
public class QueryBuildUnit {
	/**
	 * String containing database name.
	 */
	private String databaseName;
	/**
	 * String containing table name.
	 */
	private String tableName;
	/**
	 * list containing names of columns.
	 */
	private List<String> columnNames;
	/**
	 * list containing types of columns.
	 */
	private List<Datatype> columnTypes;
	/**
	 * list containing a list of string which represent values.
	 */
	private List<String> newValues;
	/**
	 * List containing the datatype of the values.
	 */
	private List<Datatype> valuesTypes;
	/**
	 * A string containing a logical statement.
	 */
	private Condition condition;
	/**
	 * Boolean to indicate if there is a distinct.
	 */
	private boolean distinct;
	/**
	 * Boolean to indicate if there is an order by command.
	 */
	private boolean order;
	/**
	 * List to indicate wether the order is desc or asc.
	 */
	private List<Order> orderTypes;
	/**
	 * List of build units if a query needs other buildUnits.
	 */
	private List<QueryBuildUnit> buildUnits;
	/**
	 * List of booleans.
	 */
	private List<Boolean> booleanList;
	/**
	 * Constructor of the class.
	 */
	public QueryBuildUnit() {
		this.columnNames = null;
		this.tableName = null;
		this.condition = null;
		this.columnTypes = null;
		this.databaseName = null;
		this.newValues = null;
		this.valuesTypes = null;
		this.orderTypes = null;
		this.order = false;
		this.distinct = false;
		this.buildUnits = null;
		this.booleanList = null;
	}
	/**
	 * Gets the name of the database
	 * if it is saved.
	 * @return
	 * Name of database
	 */
	public final String getDatabaseName() {
		return databaseName;
	}
	/**
	 * Setter for the database name.
	 * @param newDatabaseName
	 * the new value of the database.
	 */
	public final void setDatabaseName(final String newDatabaseName) {
		this.databaseName = newDatabaseName;
	}
	/**
	 * Getter for the table name.
	 * @return
	 * string containing the table name.
	 */
	public final String getTableName() {
		return tableName;
	}
	/**
	 * Setter for the table name.
	 * @param newTableName
	 * new value for table name.
	 */
	public final void setTableName(final String newTableName) {
		this.tableName = newTableName;
	}
	/**
	 * getter for the list of column names.
	 * @return
	 * List of strings.
	 */
	public final List<String> getColumnNames() {
		return columnNames;
	}
	/**
	 * Setter for the list of column names.
	 * @param newColumnNames
	 * New list of values for the variable.
	 */
	public final void setColumnNames(final List<String> newColumnNames) {
		this.columnNames = newColumnNames;
	}
	/**
	 * List of data types of the columns.
	 * @return
	 * List of the data types.
	 */
	public final List<Datatype> getColumnTypes() {
		return columnTypes;
	}
	/**
	 * Setter for the list of data types of columns.
	 * @param newColumnTypes
	 * new List of values for the variable.
	 */
	public final void setColumnTypes(final List<Datatype> newColumnTypes) {
		this.columnTypes = newColumnTypes;
	}
	/**
	 * List of strings of new values.
	 * @return
	 * list of strings.
	 */
	public final List<String> getNewValues() {
		return newValues;
	}
	/**
	 * Setter for the new values list.
	 * @param values
	 * New list of values to be assigned to the variable.
	 */
	public final void setNewValues(final List<String> values) {
		this.newValues = values;
	}
	/**
	 * Getter for the condition.
	 * @return
	 * Data structure containing the condition.
	 */
	public final Condition getCondition() {
		return condition;
	}
	/**
	 * Setter for the condition variable.
	 * @param newCondition
	 * new data structure containing a new condition.
	 */
	public final void setCondition(final Condition newCondition) {
		this.condition = newCondition;
	}
	/**
	 * @return the valuesTypes
	 */
	public List<Datatype> getValuesTypes() {
		return valuesTypes;
	}
	/**
	 * @param valuesTypes the valuesTypes to set
	 */
	public void setValuesTypes(List<Datatype> valuesTypes) {
		this.valuesTypes = valuesTypes;
	}
	/**
	 * @return the orderTypes
	 */
	public List<Order> getOrderTypes() {
		return orderTypes;
	}
	/**
	 * @param orderTypes the orderTypes to set
	 */
	public void setOrderTypes(List<Order> orderTypes) {
		this.orderTypes = orderTypes;
	}
	/**
	 * @return the order
	 */
	public boolean isOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(boolean order) {
		this.order = order;
	}
	/**
	 * @return the distinct
	 */
	public boolean isDistinct() {
		return distinct;
	}
	/**
	 * @param distinct the distinct to set
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}
	/**
	 * @return the buildUnits
	 */
	public List<QueryBuildUnit> getBuildUnits() {
		return buildUnits;
	}
	/**
	 * @param buildUnits the buildUnits to set
	 */
	public void setBuildUnits(List<QueryBuildUnit> buildUnits) {
		this.buildUnits = buildUnits;
	}
	/**
	 * @return the booleanList
	 */
	public List<Boolean> getBooleanList() {
		return booleanList;
	}
	/**
	 * @param booleanList the booleanList to set
	 */
	public void setBooleanList(List<Boolean> booleanList) {
		this.booleanList = booleanList;
	}
}
