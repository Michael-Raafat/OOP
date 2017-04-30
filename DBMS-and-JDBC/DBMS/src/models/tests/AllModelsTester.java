package models.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.UnknownColumnException;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import models.Database;
import models.Datatype;
import models.Row;
import models.Table;
import models.implementation.DatabaseImp;
import models.implementation.HeaderImp;
import models.implementation.RowImp;
import models.implementation.TableImp;
import parser.Order;
import printingService.dbms.PrintingData;
/**
 *
 * @author Michael.
 *
 */
public class AllModelsTester {
	/**
	 * list of types.
	 */
  	 private ArrayList<Datatype> types;
  	/**
 	 * list of names.
 	 */
     private ArrayList<String> names;
     /**
 	 * list of cells.
 	 */
     private ArrayList<String> cells;
	   /**
	   * row interface.
	   */
	  private Row row;
	  /**
	   * table interface.
	   */
	  private Table table;
	  /**
	   * database interface.
	   */
	  private Database database;
	  /**
	   * head interface.
	   */
	  private HeaderImp head;
	  /**
	   * initialization.
	   * @throws UnknownColumnException
	   * if invaled data is detected
	   */
	@Before
	public final void begin() throws UnknownColumnException {
		types = new ArrayList<Datatype>();
		names = new ArrayList<String>();
		head = new HeaderImp(names,types);
		cells = new ArrayList<String>();
		database = new DatabaseImp("Albert");
		table = new TableImp("Mohandes", database, head);
		row = new RowImp(table, cells);
	}
	/**
	 * testing methods of Row interface.
	 */
	@Test
	public final void testRow() {
		names.add("Name");
		names.add("Age");
		names.add("Language");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.VARCHAR);
		cells.add("Hesham");
		cells.add("22");
		cells.add("English");
		Assert.assertEquals(row.getCellDataByIndex(0),"Hesham");
		Assert.assertEquals(row.getCellDataByIndex(1), "22");
		Assert.assertEquals(row.getCellDataByIndex(2), "English");
		try {
			row.editCellDataByIndex(0, "Marc");
		    row.editCellDataByIndex(0, "22");
		    Assert.assertTrue("You didn't throw exception",
						false);
		} catch (WrongDatatypeInputException e) {
		}
		try {
			row.editCellDataByIndex(1, "30");
			row.editCellDataByIndex(1, "Marc");
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (WrongDatatypeInputException e) {
		}
		Assert.assertEquals(row.getCellDataByIndex(0),"Marc");
		Assert.assertEquals(row.getCellDataByIndex(1),"30");
	}
	/**
	 * tests table.
	 */
	@Test
	public final void testTable() {
		names.add("Name");
		names.add("Age");
		names.add("Language");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.VARCHAR);
		table = new TableImp("f",database,head);
		cells.add("H");
		cells.add("1");
		cells.add("E");
		row = new RowImp(table, cells);
		try {
			table.addRow(row);
			table.addRow(row);
			table.addRow(row);
			cells = new ArrayList<String>();
			cells.add("Hesham");
			cells.add("22");
			cells.add("English");
			row = new RowImp(table, cells);
			cells.add("go");
			table.addRow(row);
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (Exception e) {
		}
		try {
			cells = new ArrayList<String>();
			cells.add("22");
			cells.add("22");
			cells.add("English");
			row = new RowImp(table, cells);
			table.addRow(row);
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (Exception e) {
		}
		try {
			cells = new ArrayList<String>();
			cells.add("Hesham");
			cells.add("Hesham");
			cells.add("English");
			row = new RowImp(table, cells);
			table.addRow(row);
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (Exception e) {
			table.getRows().get(0).getCells().get(0);
		}
		try {
			Assert.assertEquals(
					table.getColumnIndexByName("Name"),0);
		} catch (Exception e) {
			Assert.assertTrue("You didn't throw exception",
					false);
		}
		try {
			Assert.assertEquals(
					table.getColumnIndexByName("Fares"),0);
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (UnknownColumnException e) {
		}
	}
	/**
	 * testing methods of Database interface.
	 */
	@Test
	public final void testDatabase() {
		try {
			database.addTable(table);
			table = new TableImp("Amr", database, head);
			database.addTable(table);
			Assert.assertEquals(
					database.getTableByName("Amr"), table);
			Assert.assertEquals(
					database.getTableByName("Hamada"),null);
		} catch (UnknownTableException e) {
			System.out.print("0");
		}
		try {
			database.removeTableByName("Amr");
			Assert.assertEquals(
					database.getTableByName("Amr"),null);
		} catch (UnknownTableException e) {
			System.out.print("0");
		}
		try {
			database.addTable(table);
			table = new TableImp("Amr", database, head);
			database.addTable(table);
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (UnknownTableException e) {
		}
		try {
			database.removeTableByName("Hamada");
			Assert.assertTrue("You didn't throw exception",
					false);
		} catch (UnknownTableException e) {
		}
		Assert.assertEquals(database.getTableByName("Mohandes"),
				database.getTables().get(0));
	}
	/**
	 * testing header.
	 */
	@Test
	public final void testHeader() {
		names.add("Name");
		names.add("Age");
		names.add("Language");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.VARCHAR);
		Assert.assertEquals(head.getcolumnName(0),"Name");
		Assert.assertEquals(head.getcolumnName(1),"Age");
		Assert.assertEquals(head.getcolumnName(2),"Language");
		Assert.assertEquals(head.getSize(), 3);
		names.add("Boo");
		Assert.assertEquals(head.getcolumnType(0),Datatype.VARCHAR);
		Assert.assertEquals(head.getcolumnType(1),Datatype.INTEGER);
		Assert.assertEquals(head.getcolumnType(2),Datatype.VARCHAR);
	}
	/**
	 * Testing sort table.
	 */
	@Test
	public final void testSortTable() {
		names.add("Name");
		names.add("Id");
		names.add("Age");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.INTEGER);
		cells = new ArrayList<String>();
		cells.add("Ahmed");
		cells.add("5");
		cells.add("23");
		Row row5 = new RowImp(table, cells);
		try {
			table.addRow(row5);
			cells = new ArrayList<String>();
			cells.add("Mohamed");
			cells.add("1");
			cells.add("25");
			Row row1 = new RowImp(table, cells);
			table.addRow(row1);
			cells = new ArrayList<String>();
			cells.add("Khaled");
			cells.add("3");
			cells.add("21");
			Row row3 = new RowImp(table, cells);
			table.addRow(row3);
			cells = new ArrayList<String>();
			cells.add("Ahmed");
			cells.add("2");
			cells.add("24");
			Row row2 = new RowImp(table, cells);
			table.addRow(row2);
			cells = new ArrayList<String>();
			cells.add("Ahmed");
			cells.add("4");
			cells.add("24");
			Row row4 = new RowImp(table, cells);
			table.addRow(row4);
			ArrayList<String> ordNames = new ArrayList<String>();
			ordNames.add("id");
			ArrayList<Order> ordType = new ArrayList<Order>();
			ordType.add(Order.ASC);
			Table sortTable1 = table.sortTable(ordType, ordNames);
			Assert.assertTrue(
					row1.equals(
						sortTable1.getRows().get(0)));
			Assert.assertTrue(
					row2.equals(
						sortTable1.getRows().get(1)));
			Assert.assertTrue(
					row3.equals(
						sortTable1.getRows().get(2)));
			Assert.assertTrue(
					row4.equals(
						sortTable1.getRows().get(3)));
			Assert.assertTrue(
					row5.equals(
						sortTable1.getRows().get(4)));
			ordNames = new ArrayList<String>();
			ordType = new ArrayList<Order>();
			ordNames.add("Name");
			ordNames.add("Age");
			ordType.add(Order.ASC);
			ordType.add(Order.DESC);
			sortTable1 = table.sortTable(ordType, ordNames);
			Assert.assertTrue(
					row2.equals(
						sortTable1.getRows().get(0)));
			Assert.assertTrue(
					row4.equals(
						sortTable1.getRows().get(1)));
			Assert.assertTrue(
					row5.equals(
						sortTable1.getRows().get(2)));
			Assert.assertTrue(
					row3.equals(
						sortTable1.getRows().get(3)));
			Assert.assertTrue(
					row1.equals(
						sortTable1.getRows().get(4)));
		} catch (Exception e) {
		}
	}
}
