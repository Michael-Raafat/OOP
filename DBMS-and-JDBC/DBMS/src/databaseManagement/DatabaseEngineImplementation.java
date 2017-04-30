package databaseManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import actionManagement.AlterAddQueryActionTaker;
import actionManagement.AlterDropQueryActionTaker;
import actionManagement.CreateDatabaseQueryActionTaker;
import actionManagement.CreateTableQueryActionTaker;
import actionManagement.DeleteQueryActionTaker;
import actionManagement.DropDatabaseQueryActionTaker;
import actionManagement.DropTableQueryActionTaker;
import actionManagement.InsertQueryActionTaker;
import actionManagement.QueryActionTaker;
import actionManagement.SelectQueryActionTaker;
import actionManagement.UnionQueryActionTaker;
import actionManagement.UpdateQueryActionTaker;
import actionManagement.UseQueryActionTaker;
import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Table;
import queries.Query;

/**
 * Implementation of the database engine.
 * @author Amr
 *
 */
public class DatabaseEngineImplementation extends DatabaseEngine {
	/**
	 * List of the action takers.
	 */
	private List<QueryActionTaker> actionTakers;
	/**
	 * Constructor.
	 * @throws UnknownExtension 
	 */
	public DatabaseEngineImplementation() throws UnknownExtension {
		super();
		actionTakers = new ArrayList<QueryActionTaker>();
		actionTakers.add(new CreateDatabaseQueryActionTaker());
		actionTakers.add(new CreateTableQueryActionTaker());
		actionTakers.add(new DeleteQueryActionTaker());
		actionTakers.add(new DropDatabaseQueryActionTaker());
		actionTakers.add(new DropTableQueryActionTaker());
		actionTakers.add(new InsertQueryActionTaker());
		actionTakers.add(new SelectQueryActionTaker());
		actionTakers.add(new UpdateQueryActionTaker());
		actionTakers.add(new UseQueryActionTaker());
		actionTakers.add(new AlterAddQueryActionTaker());
		actionTakers.add(new AlterDropQueryActionTaker());
		actionTakers.add(new UnionQueryActionTaker());
	}
	@Override
	public final void performAction(final Query query)
			throws UnknownTableException, UnknownDatabaseException,
			UnknownColumnException, WrongDatatypeInputException,
			DatabaseAlreadyExist, TableAlreadyExist, IOException {
		doAction(query);
	}

	@Override
	public final Table performQuery(final Query query)
			throws UnknownTableException, UnknownDatabaseException,
			UnknownColumnException, WrongDatatypeInputException,
			DatabaseAlreadyExist, TableAlreadyExist, IOException {
		Object result = doAction(query);
		Table table;
		if (query.willReturnTable()) {
			table = (Table) result;
		} else {
			table = null;
		}
		return table;
	}

	@Override
	public final Integer performUpdate(final Query query)
			throws UnknownTableException, UnknownDatabaseException,
			UnknownColumnException, WrongDatatypeInputException,
			DatabaseAlreadyExist, TableAlreadyExist, IOException {
		Object result = doAction(query);
		Integer count;
		if (query.willReturnInteger()) {
			count = (Integer) result;
		} else {
			count = null;
		}
		return count;
	}
	/**
	 * Identifies which actionTaker can do this operation
	 * and performs the operation.
	 * @param query
	 * The query to perform.
	 * @return
	 * The object returning from the operation.
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 * @throws UnknownTableException 
	 * @throws UnknownDatabaseException 
	 * @throws IOException 
	 * @throws DatabaseAlreadyExist 
	 * @throws TableAlreadyExist 
	 */
	private Object doAction(final Query query)
			throws UnknownColumnException, WrongDatatypeInputException,
			UnknownTableException, UnknownDatabaseException, IOException,
			DatabaseAlreadyExist, TableAlreadyExist {
		for (int i = 0; i < actionTakers.size(); i++) {
			if (actionTakers.get(i).accepts(query)) {
				return actionTakers.get(i).doQuery(query);
			}
		}
		throw new UnsupportedOperationException();
	}
}
