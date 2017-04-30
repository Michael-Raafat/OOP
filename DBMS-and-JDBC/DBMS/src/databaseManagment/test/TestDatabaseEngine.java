package databaseManagment.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import databaseManagement.DatabaseEngine;
import databaseManagement.DatabaseEngineImplementation;
import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownExtension;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import logicalComponents.RelationalNode;
import logicalComponents.RelationalOperandType;
import logicalComponents.RelationalOperations;
import models.Datatype;
import models.Table;
import queries.DatabaseCreationQuery;
import queries.DatabaseDroppingQuery;
import queries.DeleteQuery;
import queries.InsertIntoSpecificColumnsQuery;
import queries.InsertQuery;
import queries.SelectQuery;
import queries.SelectSpecificColumnsQuery;
import queries.TableCreationQuery;
import queries.UpdateQuery;
import queryBuilders.QueryBuildUnit;

public class TestDatabaseEngine {

//	@Test
//	public void testCreateDatabase() throws UnknownDatabaseException, DatabaseAlreadyExist {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("FirstTrialDatabase");
//		DatabaseCreationQuery q = new DatabaseCreationQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		engine.createDatabase(q);
//		unit.setDatabaseName("SecondTrialDatabase");
//		q = new DatabaseCreationQuery(unit);
//		engine.createDatabase(q);
//	}
//	
//	@Test
//	public void testDropDatabase() throws UnknownDatabaseException {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("SecondTrialDatabase");
//		DatabaseDroppingQuery q = new DatabaseDroppingQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		engine.dropDatabase(q);
//	}
//	
//	@Test
//	public void testCreateTable() throws UnknownDatabaseException, UnknownColumnException, TableAlreadyExist, IOException {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("FirstTrialDatabase");
//		unit.setTableName("FirstTable");
//		unit.setColumnNames(new ArrayList<>(Arrays.asList("userID", "username", "Job")));
//		unit.setColumnTypes(new ArrayList<>(Arrays.asList(Datatype.INTEGER, Datatype.VARCHAR, Datatype.VARCHAR)));
//		TableCreationQuery q = new TableCreationQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		engine.createTable(q);
//		unit.setTableName("SecondTable");
//		q = new TableCreationQuery(unit);
//		engine.createTable(q);
//	}
//	
//	@Test
//	public void testInsertIntoTable() throws UnknownDatabaseException,
//					UnknownTableException, WrongDatatypeInputException, UnknownColumnException, IOException {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("FirstTrialDatabase");
//		unit.setTableName("FirstTable");
//		unit.setValuesTypes(new ArrayList<>(Arrays.asList(Datatype.INTEGER, Datatype.VARCHAR, Datatype.VARCHAR)));
//		unit.setNewValues(new ArrayList<>(Arrays.asList("1", "Bishoy","Eng")));
//		InsertQuery q = new InsertQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		engine.insertIntoTable(q);
//		unit.setNewValues(new ArrayList<>(Arrays.asList("2", "Nader","Teach")));
//		q = new InsertQuery(unit);
//		engine.insertIntoTable(q);
//		unit.setNewValues(new ArrayList<>(Arrays.asList("3", "Fathy","Doc")));
//		q = new InsertQuery(unit);
//		engine.insertIntoTable(q);
//	}
//	
//	@Test
//	public void testSelectFromTable() 
//			throws UnknownDatabaseException, UnknownTableException,
//			WrongDatatypeInputException, UnknownColumnException {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("FirstTrialDatabase");
//		unit.setTableName("FirstTable");
//		SelectQuery q = new SelectQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		Table ret = engine.selectFromTable(q);
//		assertEquals(6, ret.getRows().size());
//	}
//	
//	@Test
//	public void testSelectFromSpecificColumnsFromTable()
//			throws UnknownDatabaseException, UnknownTableException,
//			WrongDatatypeInputException, UnknownColumnException {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("FirstTrialDatabase");
//		unit.setTableName("FirstTable");
//		unit.setColumnNames(new ArrayList<>(Arrays.asList("username", "Job")));
//		SelectSpecificColumnsQuery q = new SelectSpecificColumnsQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		Table ret = engine.selectFromSpecificColumnsFromTable(q);
//		assertEquals(6, ret.getRows().size());
//		assertEquals(2, ret.getHeader().getSize());
//	}
//	
	@Test
	public void testUpdateRowInTable()
			throws UnknownDatabaseException, UnknownTableException,
			WrongDatatypeInputException, UnknownColumnException, IOException, UnknownExtension {
		QueryBuildUnit unit = new QueryBuildUnit();
		unit.setDatabaseName("FirstTrialDatabase");
		unit.setTableName("FirstTable");
		unit.setValuesTypes(new ArrayList<>(Arrays.asList(Datatype.INTEGER, Datatype.VARCHAR, Datatype.VARCHAR)));
		unit.setColumnNames(new ArrayList<>(Arrays.asList("userID", "username", "Job")));
		unit.setNewValues(new ArrayList<>(Arrays.asList("2", "7amada", "Eng")));
		RelationalOperandType leftOperand = new RelationalOperandType("username", true, null);
		RelationalOperandType rightOperand = new RelationalOperandType("Bishoy", false, Datatype.VARCHAR);

		RelationalNode cond = new RelationalNode(leftOperand, rightOperand, RelationalOperations.EQUAL);
		unit.setCondition(cond);
		UpdateQuery q = new UpdateQuery(unit);
		DatabaseEngine engine = new DatabaseEngineImplementation();
		try {
			engine.performUpdate(q);
		} catch (DatabaseAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TableAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	@Test
//	public void testDeleteRowFromTable()
//			throws UnknownDatabaseException, UnknownTableException,
//			WrongDatatypeInputException, UnknownColumnException, IOException {
//		QueryBuildUnit unit = new QueryBuildUnit();
//		unit.setDatabaseName("FirstTrialDatabase");
//		unit.setTableName("FirstTable");
//		RelationalOperandType leftOperand = new RelationalOperandType("username", true, null);
//		RelationalOperandType rightOperand = new RelationalOperandType("7amada", false, Datatype.VARCHAR);
//
//		RelationalNode cond = new RelationalNode(leftOperand, rightOperand, RelationalOperations.EQUAL);
//		unit.setCondition(cond);
//		DeleteQuery q = new DeleteQuery(unit);
//		DatabaseEngine engine = new DatabaseEngine();
//		engine.deleteFromTable(q);
//	}

}
