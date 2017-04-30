package actionManagement;

import java.io.IOException;

import databaseManagement.DatabaseEngine;
import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.implementation.HeaderImp;
import models.implementation.TableImp;
import queries.Query;
import queries.QueryAction;
import queries.TableCreationQuery;

/**
 * Action taker responsible of the create table queries.
 * 
 * @author Amr
 *
 */
public class CreateTableQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public CreateTableQueryActionTaker() throws UnknownExtension {
		super();
	}

	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.CREATE_TABLE;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException,
			IOException, DatabaseAlreadyExist, TableAlreadyExist {
		TableCreationQuery tableCreationQuery = (TableCreationQuery) query;
		HeaderImp header = new HeaderImp(tableCreationQuery.getFieldsNames(),
				tableCreationQuery.getFieldsDataTypes());
		if (isTableFound(
				tableCreationQuery.getTableName(),
				DatabaseEngine.getCurrentDatabaseName())
				|| tableCreationQuery.getDatabaseName() != null
				&& isTableFound(
						tableCreationQuery.getTableName(),
								tableCreationQuery.getDatabaseName())) {
			throw new TableAlreadyExist();
		} else if (tableCreationQuery.getDatabaseName() != null
				&& !isTableFound(
						tableCreationQuery.getTableName(),
						tableCreationQuery.getDatabaseName())) {
			this.getFileManager().writeTable(
					new TableImp(tableCreationQuery.getTableName(),
							this.getFileManager().readDB(
							tableCreationQuery.getDatabaseName()), header));
			return null;
		} else if (DatabaseEngine.getCurrentDatabaseName() != null
				&& !isTableFound(
						tableCreationQuery.getTableName(),
						DatabaseEngine.getCurrentDatabaseName())) {
			this.getFileManager().writeTable(
					new TableImp(tableCreationQuery.getTableName(),
							this.getFileManager().readDB(
							DatabaseEngine.getCurrentDatabaseName()), header));
			return null;
		} else {
			throw new UnknownDatabaseException();
		}
	}
}
