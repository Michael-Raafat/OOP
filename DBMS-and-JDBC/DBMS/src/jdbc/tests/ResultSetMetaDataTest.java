package jdbc.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import exceptions.UnknownColumnException;
import jdbc.ResultSetMetaDataImp;
import models.Database;
import models.Datatype;
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
public class ResultSetMetaDataTest {
	/**
	 * data of DBMS and a ResultSetMetaData.
	 */
	ArrayList<Database> cell;
	ArrayList<String> cells;
	ArrayList<String> cells1;
	ArrayList<String> cells2;
	ArrayList<String> cells3;
	ArrayList<String> names;
	ArrayList<Datatype> types;
	HeaderImp head;
	DatabaseImp database;
	Table table;
	RowImp row1;
	RowImp row4;
	ResultSetMetaDataImp data;
	/**
	 * before testing.
	 * @throws UnknownColumnException
	 * if invalid data is detected.
	 */
	@Before
	public final void loadData() throws UnknownColumnException {
		cell = new ArrayList<Database>();
		cells = new ArrayList<String>();
		cells1 = new ArrayList<String>();
		cells2 = new ArrayList<String>();
		cells3 = new ArrayList<String>();
		names = new ArrayList<String>();
		types = new ArrayList<Datatype>();
		head = new HeaderImp(names, types);
		database = new DatabaseImp("Hello");
		table = new TableImp("works", database, head);
		row1 = new RowImp(table, cells);
		row4 = new RowImp(table, cells3);
		data = new ResultSetMetaDataImp(table);
	}
	/**
	 * test valid data.
	 * @throws UnknownColumnException
	 * if invalid data is detected.
	 */
	@Test
	public final void testValidData()
		   throws UnknownColumnException {
		this.supplyData();
		try {
			assertEquals(data.getColumnCount(), 3);
			assertEquals(data.getColumnLabel(2), "Age");
			assertEquals(data.getColumnName(1), "Name");
			assertEquals(data.getColumnType(2),
					java.sql.Types.INTEGER);
		} catch (SQLException e) {
			fail("problem in index");
		}
	}
	/**
	 * test wrong index.
	 * @throws UnknownColumnException
	 * if invalid data is detected.
	 */
	@Test
	public final void testWrongIndex() throws UnknownColumnException {
		this.supplyData();
		try {
			data.getColumnName(5);
			fail("you have more than excpected no. of columns");
		} catch (Exception e) {
			if (e.equals(new IndexOutOfBoundsException())) {
				fail("you should throw different exception"
						+ " : you throw "
						+ "IndexOutOFBoundsException");
			}
		}
	}
	/**
	 * Supplies database with data eg: cells, columns and rows.
	 */
	private void supplyData() {
		cells.add("Marc");
		cells.add("20");
		cells.add("A Bob Saver");
		cells3.add("Michael");
		cells3.add("21");
		cells3.add("A perfect formatter");
		names.add("Name");
		names.add("Age");
		names.add("Mission");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.VARCHAR);
	}
}
