package consolePortal.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import consolePortal.IjdbcAdaptor;
import consolePortal.JdbcAdaptor;
/**
 * Test on the jdbc adapter.
 * @author Amr
 *
 */
public class JdbcAdapterTest {
	/**
	 * Adaptor.
	 */
	private IjdbcAdaptor adaptor;
	/**
	 * Function done before each test.
	 */
	@Before
	public final void setUpAdapter() {
		adaptor = new JdbcAdaptor();
	}
	/**
	 * Test on the url acceptance.
	 */
	@Test
	public final void testAcceptUrl() {
		assertTrue(adaptor.acceptsURL("jdbc:xmldb://localhost"));
		assertTrue(adaptor.acceptsURL("jdbc:jsondb://localhost"));
		assertTrue(adaptor.acceptsURL("jdbc:altdb://localhost"));
		assertTrue(adaptor.acceptsURL("jdbc:protodb://localhost"));
		assertFalse(adaptor.acceptsURL("jdbc:faildb://localhost"));
		assertFalse(adaptor.acceptsURL("jdbd:xmldb://localhost"));
		assertFalse(adaptor.acceptsURL("jdbc:xmldb://localhostFail"));
		assertFalse(adaptor.acceptsURL("jdbc:xmldb:/ /localhost"));
		assertFalse(adaptor.acceptsURL("jdbc:xmldb://local host"));
		assertFalse(adaptor.acceptsURL("jdbc:xml db://localhost"));
		assertFalse(adaptor.acceptsURL(" jdbc:xmldb://localhost"));
	}
	/**
	 * Test to check if it returns a table.
	 */
	@Test
	public final void testReturnTable() {
		try {
			assertTrue(adaptor.returnsTable("Select * from manu"));
			assertTrue(adaptor.returnsTable("Select * from manu"
					+ " order by player"));
			assertTrue(adaptor.returnsTable("Select Distinct * from manu"));
			assertTrue(adaptor.returnsTable("Select * from manu"
					+ " union select * from manu"));
			assertTrue(adaptor.returnsTable("  Select * from"
					+ " manu union all select * from manu"
					+ " UnIoN AlL select * from manu"));
		} catch (SQLException e) {
			fail("Return an exception for valid query");
		} catch (Throwable e) {
			fail("Return false for a query that returns a table");
		}
		try {
			assertFalse(adaptor.returnsTable("Insert into table values "
					+ "('amr', 5)"));
			assertFalse(adaptor.returnsTable("Create database test"));
			assertFalse(adaptor.returnsTable("Create table test (more"
					+ " int, darm varchar)"));
			assertFalse(adaptor.returnsTable("Delete from table where x = 5"));
			assertFalse(adaptor.returnsTable("  Alter table test add max int"));
		} catch (SQLException e) {
			fail("Exception for valid query");
		}  catch (Throwable e) {
			fail("Return true for a query that doesn't returns a table");
		}
	}
	/**
	 * Test to check if it returns an Integer.
	 */
	@Test
	public final void testReturnInteger() {
		try {
			assertFalse(adaptor.returnsInteger("Select * from manu"));
			assertFalse(adaptor.returnsInteger("Select * from manu"
					+ " order by player"));
			assertFalse(adaptor.returnsInteger("Select Distinct * from manu"));
			assertFalse(adaptor.returnsInteger("Select * from manu"
					+ " union select * from manu"));
			assertFalse(adaptor.returnsInteger("  Select * from"
					+ " manu union all select * from manu"
					+ " UnIoN AlL select * from manu"));
			assertFalse(adaptor.returnsInteger("Alter table test add max int"));
			assertFalse(adaptor.returnsInteger("Create database test"));
			assertFalse(adaptor.returnsInteger("Create table test (more"
					+ " int, darm varchar)"));
		} catch (SQLException e) {
			fail("Return an exception for valid query");
		} catch (Throwable e) {
			fail("Return true for a query that doesn't return an integer");
		}
		try {
			assertTrue(adaptor.returnsInteger("Insert into table values "
					+ "('amr', 5)"));
			assertTrue(adaptor.returnsInteger("Delete from table where x = 5"));
			assertTrue(adaptor.returnsInteger("Update table set x = 5 where"
					+ " y = 10"));
		} catch (SQLException e) {
			fail("Exception for valid query");
		}  catch (Throwable e) {
			fail("Return false for a query that returns an integer");
		}
	}
	/**
	 * Test perform query.
	 */
	@Test
	public final void performQuery() {
		try {
			assertEquals(null, adaptor.performQuery(
					"Drop database amrtest"));
		} catch (Exception e) {
			// nothing.
		}
		try {
			assertEquals(null, adaptor.performQuery(
					"Create database amrtest"));
			assertEquals(null, adaptor.performQuery(
					"Use amrtest"));
			assertEquals(null, adaptor.performQuery(
					"Create table test (name varchar)"));
			assertNotEquals(null, adaptor.performQuery(
					"Select * from test"));
			assertEquals(null, adaptor.performQuery(
					"Insert into test values ('amr')"));
			assertEquals(null, adaptor.performQuery(
					"Update test set name='marc' where name = 'amr'"));
			assertEquals(null, adaptor.performQuery(
					"delete from test where name = 'marc'"));
			assertEquals(null, adaptor.performQuery(
					"Drop database amrtest"));
		} catch (SQLException e) {
			fail("Wrong exception thrown");
		}
	}
	/**
	 * Test on perform update.
	 */
	@Test
	public final void performUpdate() {
		try {
			assertEquals(null, adaptor.performUpdate(
					"Drop database amrtest"));
		} catch (Exception e) {
			// nothing.
		}
		try {
			assertEquals(null, adaptor.performUpdate(
					"Create database amrtest"));
			assertEquals(null, adaptor.performUpdate(
					"Use amrtest"));
			assertEquals(null, adaptor.performUpdate(
					"Create table test (name varchar)"));
			assertEquals(null, adaptor.performUpdate(
					"Select * from test"));
			assertNotEquals(null, adaptor.performUpdate(
					"Insert into test values ('amr')"));
			assertNotEquals(null, adaptor.performUpdate(
					"Update test set name='marc' where name = 'amr'"));
			assertNotEquals(null, adaptor.performUpdate(
					"delete from test where name = 'marc'"));
			assertEquals(null, adaptor.performUpdate(
					"Drop database amrtest"));
		} catch (SQLException e) {
			fail("Wrong exception thrown");
		}
	}
	/**
	 * Test on perform command.
	 */
	@Test
	public final void performCommand() {
		try {
			adaptor.performCommand(
					"Drop database amrtest");
		} catch (Exception e) {
			// nothing.
		}
		try {
			adaptor.performCommand(
					"Create database amrtest");
			adaptor.performCommand(
					"Use amrtest");
			adaptor.performCommand(
					"Create table test (name varchar)");
			adaptor.performCommand(
					"Select * from test");
			adaptor.performCommand(
					"Insert into test values ('amr')");
			adaptor.performCommand(
					"Update test set name='marc' where name = 'amr'");
			adaptor.performCommand(
					"delete from test where name = 'marc'");
			adaptor.performCommand(
					"Drop database amrtest");
		} catch (SQLException e) {
			fail("Wrong exception thrown");
		}
	}
}
