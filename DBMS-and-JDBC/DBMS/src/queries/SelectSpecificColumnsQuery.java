package queries;

import java.util.List;

import queryBuilders.QueryBuildUnit;
/**
 * Query class of selection of certain columns
 * of rows that meet a certain condition.
 * @author Amr
 *
 */
public class SelectSpecificColumnsQuery extends SelectQuery {

	/**
	 * Fields to be selected.
	 */
	private List<String> fields;
	/**
	 * Constructor calling parent constructor setting the action
	 * of the query to select from table.
	 * then uses a build unit to get its parameters from.
	 * @param buildUnit
	 * Contains all needed information
	 */
	public SelectSpecificColumnsQuery(final QueryBuildUnit buildUnit) {
		super(buildUnit);
		this.fields = buildUnit.getColumnNames();
	}

	/**
	 * @return the fields
	 */
	public final List<String> getFields() {
		return fields;
	}
}
