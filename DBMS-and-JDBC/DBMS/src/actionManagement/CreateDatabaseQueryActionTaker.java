package actionManagement;

import java.io.IOException;

import exceptions.DatabaseAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.implementation.DatabaseImp;
import queries.DatabaseCreationQuery;
import queries.Query;
import queries.QueryAction;

/**
 * Action taker responsible of the create database queries.
 * @author Amr
 *
 */
public class CreateDatabaseQueryActionTaker extends QueryActionTaker {
	/**
	 * Constructor of the class.
	 * @throws UnknownExtension 
	 */
	public CreateDatabaseQueryActionTaker() throws UnknownExtension {
		super();
	}
	@Override
	public final boolean accepts(final Query query) {
		return query.getAction() == QueryAction.CREATE_DB;
	}

	@Override
	public final Object doQuery(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist {
		DatabaseCreationQuery databaseCreationQuery
		= (DatabaseCreationQuery) query;
		if (!this.isDatabaseFound(
				databaseCreationQuery.getDatabaseName())) {
			this.getFileManager().writeDB(new DatabaseImp(
					databaseCreationQuery.getDatabaseName()));
			return null;
		} else {
			throw new DatabaseAlreadyExist();
		}
	}

}
