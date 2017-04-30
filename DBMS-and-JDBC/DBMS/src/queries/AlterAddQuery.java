package queries;

import models.Datatype;
import queryBuilders.QueryBuildUnit;
/**
 * Alter add column to a table Query.
 * @author Amr
 *
 */
public class AlterAddQuery extends AlterQuery {
	/**
	 * Datatype of the added column.
	 */
	private Datatype columnDatatype;
	/**
	 * Constructor.
	 * @param unit
	 * The building unit.
	 */
	public AlterAddQuery(final QueryBuildUnit unit) {
		super(QueryAction.ALTER_TABLE_ADD, unit);
		this.columnDatatype = unit.getColumnTypes().get(0);
	}
	/**
	 * @return the columnDatatype
	 */
	public final Datatype getColumnDatatype() {
		return columnDatatype;
	}
	@Override
	public String getMessage() {
		return "Alter add column command on the table "
				+ this.getTableName()
				+ " in the database "
				+ this.getDatabaseName();
	}

}
