package jdbc.tests;

import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import exceptions.UnknownColumnException;
import jdbc.ResultSetImp;
import jdbc.ResultSetMetaDataImp;
import models.Database;
import models.Datatype;
import models.Row;
import models.Table;
import models.implementation.DatabaseImp;
import models.implementation.HeaderImp;
import models.implementation.RowImp;
import models.implementation.TableImp;
/**
 *
 * @author Michael.
 *
 */
public class ResultSetTest {
	/**
	 * data of DBMS and a ResultSetMetaData.
	 * a statement and a resultSet.
	 */
	ArrayList<Database> cell;
	ArrayList<String> cells4;
	ArrayList<String> cells1;
	ArrayList<String> cells2;
	ArrayList<String> cells3;
	ArrayList<String> names;
	ArrayList<Datatype> types;
	HeaderImp head;
	DatabaseImp database;
	Table table;
	Row row1;
	Row row4;
	Row row2;
	Row row3;
	ResultSetMetaData data;
	ResultSet res;
	Statement statement;
	ResultSet res1;
	/**
	 * before testing.
	 * @throws UnknownColumnException
	 * if invalid data.
	 */
	@Before
	public final void loadData() throws UnknownColumnException {
		cell = new ArrayList<Database>();
		cells4 = new ArrayList<String>();
		cells1 = new ArrayList<String>();
		cells2 = new ArrayList<String>();
		cells3 = new ArrayList<String>();
		names = new ArrayList<String>();
		types = new ArrayList<Datatype>();
		head = new HeaderImp(names, types);
		database = new DatabaseImp("Hello");
		table = new TableImp("works", database, head);
		row1 = new RowImp(table, cells1);
		row4 = new RowImp(table, cells4);
		data = new ResultSetMetaDataImp(table);
		row2 = new RowImp(table, cells2);
		row3 = new RowImp(table, cells3);
		res = new ResultSetImp(table, statement);
		res1 = new ResultSetImp(
				new TableImp("hamada",
						database, head), statement);
	}
	/**
	 * test absolute method.
	 */
	@Test
	public final void testAbsolute() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(4));
		} catch (Exception e) {
			fail("you shouldn't throw exception"
					+ " : absolute or getdate error");
		}
	}
	/**
	 * test get string by name and index.
	 */
	@Test
	public final void testGetStringByNameAndIndex() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(2));
			assertEquals("A professional Evaluator",
					res.getString("Mission"));
		} catch (Exception e) {
			fail("you shouldn't throw exception "
				+ ": absolute or getstring by name error");
		}
		try {
			assertEquals(true, res.absolute(2));
			assertEquals("Bishoy", res.getString(1));
		} catch (Exception e) {
			fail("you shouldn't throw exception"
				+ " : absolute or getstring ny index error");
		}
	}
	/**
	 * test get wrong object.
	 */
	@Test
	public final void testGetWrongObject() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(3));
			res.getObject(7);
			fail("you should throw exception"
					+ " : absolute or getObject error");
		} catch (Exception e) {
			if (e.equals(new IndexOutOfBoundsException())) {
				fail("you should throw different exception :"
					+ " exception throwed was IndexOut");
			}
		}
	}
	/**
	 * test get float by name and index.
	 */
	@Test
	public final void testGetFloatByNameAndIndex() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(4));
			assertEquals(22.5, res.getFloat("Float"),
					0.000000001);
		} catch (Exception e) {
			fail("you shouldn't throw exception"
					+ " : absolute or getFloat error");
		}
		try {
			assertEquals(true, res.absolute(1));
			assertEquals(20.5,
					res.getFloat(5), 0.000001);
		} catch (Exception e) {
			fail("you shouldn't throw exception"
					+ " : absolute or getFloat error");
		}
	}
	/**
	 * test get wrong float.
	 */
	@Test
	public final void testGetWrongFloat() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(1));
			res.getFloat(7);
			fail("you should throw exception : incorrect index");
		} catch (IndexOutOfBoundsException e) {
			fail("you should throw different exception : IndexOut");
		} catch (SQLException e) {
		}
	}
	/**
	 * test get int by name and index.
	 */
	@Test
	public final void testGetIntByNameAndIndex() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(3));
			assertEquals(21, res.getInt("Age"));
		} catch (Exception e) {
			fail("you shouldn't throw exception"
					+ " : absolute or getInt error");
		}
		try {
			assertEquals(true, res.absolute(4));
			assertEquals(20, res.getInt(2));
		} catch (Exception e) {
			fail("you shouldn't throw exception"
					+ " : absolute or getInt error");
		}
	}
	/**
	 * test get data by name and index method.
	 */
	@Test
	public final void testGetDateByNameAndIndex() {
		this.supplyData();
		try {
			assertEquals(true, res.absolute(1));
			assertEquals(new Date(2017 - 1900,
					5 - 1 , 1), res.getDate(4));
			assertEquals(false, res.absolute(0));
			assertEquals(false, res.absolute(5));
			assertEquals(true, res.absolute(-4));
			assertEquals(new Date(2017 - 1900,
					5 - 1 , 1), res.getDate(4));
			assertEquals(false, res.absolute(-5));
		} catch (Exception e) {
			fail("you shouldn't throw exception"
					+ " : absolute or getdate error");
		}
		try {
			assertEquals(new Date(2017 - 1900,
					5 - 1 , 1), res.getDate("Date"));
		} catch (Exception e) {
			}
	}
	/**
	 * test booleans methods.
	 */
	@Test
	public final void testBooleansMethods() {
		this.supplyData();
		try {
			res.close();
			res.next();
			fail("you should throw exception : next method");
		} catch (SQLException e1) {
		}
		try {
			res.first();
			fail("you should throw exception : first method");
		} catch (SQLException e1) {
		}
		try {
			res.previous();
			fail("you should throw exception : previous method");
		} catch (SQLException e1) {
		}
		try {
			res.last();
			fail("you should throw exception : last method");
		} catch (SQLException e1) {
		}
		try {
			res1.next();
		} catch (SQLException e1) {
			fail("you shouldnot throw exception : empty ResultSet");
		}
	}
	/**
	 * test find column method.
	 */
	@Test
	public final void testFindColumn() {
		this.supplyData();
		try {
			assertEquals(2, res.findColumn("Age"));
			assertEquals(3, res.findColumn("Mission"));
			assertEquals(1, res.findColumn("Name"));
		} catch (Exception e) {
			fail("you shouldn't throw exception");
		}
		try {
		res.findColumn("");
		fail("you should throw SQLException");
		} catch (IndexOutOfBoundsException e) {
			fail("you should throw different exception : IndexOut");
		} catch (SQLException e) {
		}
	}
	/**
	 * test get statement method.
	 */
	@Test
	public final void testGetStatement() {
		this.supplyData();
		try {
			assertEquals(statement, res1.getStatement());
		} catch (SQLException e1) {
			fail("you shouldn't throw exception"
					+ " : getStatement method");
		}
	}
	/**
	 * supplies tests with data.
	 */
	private void supplyData() {
		names.add("Name");
		names.add("Age");
		names.add("Mission");
		names.add("Date");
		names.add("Float");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.VARCHAR);
		types.add(Datatype.DATE);
		types.add(Datatype.FLOAT);
		cells1.add("Marc");
		cells1.add("20");
		cells1.add("A Bob Saver");
		cells1.add("2017-05-01");
		cells1.add("20.5");
		cells2.add("Bishoy");
		cells2.add("20");
		cells2.add("A professional Evaluator");
		cells2.add("2020-12-20");
		cells2.add("25.5");
		cells3.add("Michael");
		cells3.add("21");
		cells3.add("A perfect formatter");
		cells3.add("2017-09-11");
		cells3.add("21.5");
		cells4.add("Amr");
		cells4.add("20");
		cells4.add("An Expert Parser");
		cells4.add("2010-10-10");
		cells4.add("22.5");
		try {
		table.addRow(row1);
		table.addRow(row2);
		table.addRow(row3);
		table.addRow(row4);
		} catch (Exception e) {
		}
	}
}
