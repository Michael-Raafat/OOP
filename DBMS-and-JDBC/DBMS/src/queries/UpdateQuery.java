package queries;

import java.util.List;

import logicalComponents.Condition;
import models.Datatype;
import queryBuilders.QueryBuildUnit;
/**
 * Query class of update of rows that meet
 * a certain condition.
 * @author Amr
 *
 */
public class UpdateQuery extends TableQuery {

	/**
	 * List of column names to be modified.
	 */
	private List<String> columns;
	/**
	 * The updated fields of the record.
	 */
	private List<String> fields;
	/**
	 * List of the types of the entered values.
	 */
	private List<Datatype> fieldsType;
	/**
	 * conditions of the selection of which rows to update.
	 */
	 private Condition condition;

	/**
	* Constructor calling parent constructor setting the action
	* of the query to select from table.
	* then uses a build unit to get its parameters from.
	* @param buildUnit
	* Contains all needed information
	*/
	public UpdateQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.UPDATE_TABLE, buildUnit);
		this.columns = buildUnit.getColumnNames();
		this.fields = buildUnit.getNewValues();
		this.condition = buildUnit.getCondition();
		this.fieldsType = buildUnit.getValuesTypes();
	}

	/**
	 * @return the columns
	 */
	public final List<String> getColumns() {
		return columns;
	}

	/**
	 * @return the fields
	 */
	public final List<String> getFields() {
		return fields;
	}

	/**
	 * @return the condition
	 */
	public final Condition getCondition() {
		return condition;
	}

	/**
	 * @return the fieldsType
	 */
	public final List<Datatype> getFieldsType() {
		return fieldsType;
	}
	
	@Override
	public String getMessage() {
		return "update table command on the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
