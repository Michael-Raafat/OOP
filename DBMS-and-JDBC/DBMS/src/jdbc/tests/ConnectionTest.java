package jdbc.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import consolePortal.IjdbcAdaptor;
import consolePortal.JdbcAdaptor;
import jdbc.ConnectionImp;
import models.Database;
/**
 *
 * @author Michael.
 *
 */
public class ConnectionTest {
	@Mock
	Database database;
	Properties info;
	IjdbcAdaptor adapter;
	Connection conn;
	Statement st;
	@Before
	public void loadData() {
		info = new Properties();
		info.setProperty("path", "jdbc:jsondb://localhost");
		adapter = new JdbcAdaptor();
		conn = new ConnectionImp(info, adapter);
	}
	/**
	 * test close method.
	 */
	@Test
	public final void testClose() {
		try {
			conn.close();
		} catch (SQLException e1) {
			fail("close should not throw a SQLException");
		}
		try {
			Statement st = conn.createStatement();
			fail("closed conn should throw a SQLException");
		} catch (Exception e) {
			assertTrue(e instanceof SQLException);
		}
	}
	/**
	 * test create statement.
	 */
	@Test
	public final void testCreateStatment() {
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			fail("createStatment shouldn't throw an exception");
		}
		try {
			assertNull(st.getResultSet());
		} catch (SQLException e) {
			fail("getResultSet shouldn't throw an exception");
		}
		try {
			assertEquals(-1, st.getUpdateCount());
		} catch (SQLException e) {
			fail("getUpdateCount shouldn't throw an exception");
		}
	}
}
