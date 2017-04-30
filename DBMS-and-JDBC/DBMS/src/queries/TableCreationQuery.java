package queries;

import java.util.List;

import models.Datatype;
import queryBuilders.QueryBuildUnit;
/**
 * Query class of table creation.
 * @author Amr
 *
 */
public class TableCreationQuery extends TableQuery {

	/**
	 * The names of the fields of the table.
	 */
	private List<String> fieldsNames;

	/**
	 * The Type of fields of the table.
	 * initially supporting 'int' and 'varchar'.
	 */
	private List<Datatype> fieldsDataTypes;

	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public TableCreationQuery(final QueryBuildUnit buildUnit) {
		super(QueryAction.CREATE_TABLE, buildUnit);
		this.fieldsNames = buildUnit.getColumnNames();
		this.fieldsDataTypes = buildUnit.getColumnTypes();
	}

	/**
	 * @return the fieldsNames
	 */
	public final List<String> getFieldsNames() {
		return fieldsNames;
	}

	/**
	 * @return the fieldsDataTypes
	 */
	public final List<Datatype> getFieldsDataTypes() {
		return fieldsDataTypes;
	}
	@Override
	public String getMessage() {
		return "Create table command to create the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}
}
