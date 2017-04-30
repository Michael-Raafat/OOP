package jdbc.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import jdbc.DriverImp;
/**
 *
 * @author Michael.
 *
 */
public class DriverTest {
	/**
	 * test acceptsUrl method.
	 */
	@Test
	public final void testAcceptsURL() {
		Driver driver = new DriverImp();
		String url = "jdbc:jsondb://localhost";
		try {
			assertEquals(true, driver.acceptsURL(url));
		} catch (SQLException e) {
			fail("acceptURL should not throw exception");
		}
		String url2 = "jxbc:jsondb://localhost";
		try {
			assertEquals(false, driver.acceptsURL(url2));
		} catch (SQLException e) {
			fail("acceptURL should not throw exception");
		}
		String url3 = "jxbc:jsofndb://localhost";
		try {
			assertEquals(false, driver.acceptsURL(url3));
		} catch (SQLException e) {
			fail("acceptURL should not throw exception");
		}
		String url4 = "jxbc:jsondb://locamlhost";
		try {
			assertEquals(false, driver.acceptsURL(url4));
		} catch (SQLException e) {
			fail("acceptURL should not throw exception");
		}
	}
	/**
	 * test connect method.
	 */
	@Test
	public final void testConnect() {
		Driver driver = new DriverImp();
		Properties info = new Properties();
		info.setProperty("username", "Bishoy");
		info.setProperty("password", "123");
		info.setProperty("path", System.getProperty("user.dir"));
		String url = "jdbc:jsondb://localhost";
		Connection conn = null;
		try {
			conn = driver.connect(url, info);
		} catch (SQLException e) {
			fail("connect should not throw exception");
		}
	}
	/**
	 * test getPropertyInfo method.
	 */
	@Test
	public final void testGetPropertyInfo() {
		Driver driver = new DriverImp();
		DriverPropertyInfo[] propertyInfo = null;
		try {
			propertyInfo = driver.getPropertyInfo(null, null);
		} catch (SQLException e) {
			fail("getPropertyInfo should not throw exception");
		}
		assertEquals("Number of properties", 3, propertyInfo.length);
		assertEquals("username", propertyInfo[0].name);
		assertEquals("password", propertyInfo[1].name);
		assertEquals("path", propertyInfo[2].name);
	}
}
