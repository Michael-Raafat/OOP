package queries;

import java.util.List;

import models.Datatype;
import queryBuilders.QueryBuildUnit;
/**
 * Query class of insertion of rows.
 * @author Amr
 *
 */
public class InsertQuery extends TableQuery {

	/**
	 * Fields of the new record.
	 */
	private List<String> fieldsValues;
	/**
	 * List of the datatype of the entered values.
	 */
	private List<Datatype> fieldsTypes;
	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public InsertQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.INSERT_INTO_TABLE, buildUnit);
		this.fieldsValues = buildUnit.getNewValues();
		this.fieldsTypes = buildUnit.getValuesTypes();
	}
	/**
	 * @return the fieldsValues
	 */
	public final List<String> getFieldsValues() {
		return fieldsValues;
	}
	/**
	 * @return the fieldsTypes
	 */
	public final List<Datatype> getFieldsTypes() {
		return fieldsTypes;
	}
	@Override
	public String getMessage() {
		return "Insert command on the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
