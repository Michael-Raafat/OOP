package jdbc.tests;
 
import static org.junit.Assert.*;
 
import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
 
import consolePortal.IjdbcAdaptor;
import consolePortal.JdbcAdaptor;
import jdbc.ConnectionImp;
/**
 *
 * @author michael.
 *
 */
public class StatementTest {
 
    private String protocol = "xmldb";
    private String tmp = System.getProperty("java.io.tmpdir");
    private Properties info;
    private IjdbcAdaptor adapter;
    private Connection conn;
    private Statement st;
    /**
     * before testing.
     * @throws SQLException
     * if invalid statement.
     */
    @Before
    public final void loadData() throws SQLException {
        info = new Properties();
        info.setProperty("path", "jdbc:jsondb://localhost");
        adapter = new JdbcAdaptor();
        conn = new ConnectionImp(info, adapter);
        st = conn.createStatement();
    }
    /**
     * test execute method.
     */
    @Test
    public final void testExecute() {
        try {
            conn = createUseDatabase("TestDB_Create");
            assertEquals(false, st.execute("CREATE TABLE project("
                    + "columnName1 varchar,"
                    + " columnName2 int,"
                    + " columnName3 date)"));
            st.close();
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
        try {
            conn = createUseDatabase("TestDB_Create");
            st = conn.createStatement();
            st.execute("CREATE TABLE project("
                    + "columnName1 varchar,"
                    + " columnName2 int,"
                    + " columnName3 date)");
            assertEquals(false, st.execute("DROP TABLE project"));
            st.close();
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
        try {
            conn = createUseDatabase("TestDB_Create");
            st = conn.createStatement();
            st.execute("CREATE TABLE project("
                    + "columnName1 varchar,"
                    + " columnName2 int,"
                    + " columnName3 date)");
            assertEquals(false, st.execute(
                    "SELECT * From project"));
            st.close();
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
    }
    /**
     * test execute update.
     */
    @Test
    public final void testExecuteUpdate() {
        try {
            conn = createUseDatabase("TestDB_Create");
            st = conn.createStatement();
            st.execute("CREATE TABLE project(columnName1 varchar,"
                + " columnName2 int, columnName3 varchar)");
            Assert.assertEquals(1, st.executeUpdate(
                "INSERT INTO project(columnName1,"
                + " columnName2, columnName3)"
                + " VALUES ('go', 4, 'to')"));
            Assert.assertEquals(1, st.executeUpdate(
                "INSERT INTO project(columnName1,"
                + " columnName2, columnName3)"
                + " VALUES ('go', 4, 'to')"));
            Assert.assertEquals(2, st.executeUpdate(
                "UPDATE project SET columnName1 = '1111111111',"
                + " columnName2 = 2222222,"
                + " columnName3='333333333'"));
            st.close();
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
    }
    /**
     * test execute query.
     */
    @Test
    public final void testExecuteQuery() {
        try {
            conn = createUseDatabase("TestDB_Create");
            st = conn.createStatement();
            st.execute("CREATE TABLE project("
                    + "columnName1 varchar,"
                    + " columnName2 int,"
                    + " columnName3 varchar)");
            Assert.assertEquals(1, st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 4, 'to')"));
            Assert.assertEquals(1, st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 5, 'to')"));
            Assert.assertEquals(1, st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 6, 'to')"));
            Assert.assertEquals(1, st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('goo', 7, 'too')"));
            ResultSet result = st.executeQuery("SELECT * From project");
            Assert.assertNotNull(result);
            Assert.assertEquals(3, result.getMetaData().getColumnCount());
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
    }
 
    
    /**
     * test add and execute batch.
     */
    @Test
    public final void testAddAndExcuteBatch() {
        try {
            conn = createUseDatabase("TestDB_Create");
            st = conn.createStatement();
            st.addBatch("CREATE TABLE project("
                    + "columnName1 varchar,"
                    + " columnName2 int,"
                    + " columnName3 varchar)");
            st.addBatch(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 4, 'to')");
            st.addBatch(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 5, 'to')");
            st.addBatch(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 6, 'to')");
            st.addBatch(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('goo', 7, 'to')");
            st.addBatch("UPDATE project SET columnName1 = '1111111111',"
                + " columnName2 = 2222222,"
                + " columnName3 = '333333333' where columnName2 > 5");
            st.addBatch("Delete from project");
            st.addBatch("Drop table project");
            st.addBatch("Drop database TestDB_Create");
            int[] res = st.executeBatch();
            assertEquals(res[0], java.sql.Statement.SUCCESS_NO_INFO);
            assertEquals(res[1], 1);
            assertEquals(res[2], 1);
            assertEquals(res[3], 1);
            assertEquals(res[4], 1);
            assertEquals(res[5], 2);
            assertEquals(res[6], 4);
            assertEquals(res[7], java.sql.Statement.SUCCESS_NO_INFO);
            assertEquals(res[8], java.sql.Statement.SUCCESS_NO_INFO);
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
    }
    
    
    /**
     * test get update count.
     */
    @Test
    public final void testGetUpdateCount() {
        try {
            conn = createUseDatabase("TestDB_Create");
            st = conn.createStatement();
            st.execute("CREATE TABLE project("
                    + "columnName1 varchar,"
                    + " columnName2 int,"
                    + " columnName3 varchar)");
            Assert.assertEquals(-1, st.getUpdateCount());
            st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 4, 'to')");
            Assert.assertEquals(1, st.getUpdateCount());
            st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 5, 'to')");
            st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('go', 6, 'to')");
            st.executeUpdate(
                    "INSERT INTO project(columnName1,"
                    + " columnName2, columnName3)"
                    + " VALUES ('goo', 7, 'to')");
            st.execute("UPDATE project SET columnName1 = '1111111111',"
                + " columnName2 = 2222222,"
                + " columnName3 = '333333333' where columnName2 > 5");
            Assert.assertEquals(2, st.getUpdateCount());
            st.execute("Delete from project");
            Assert.assertEquals(4, st.getUpdateCount());
        } catch (SQLException e) {
            fail("you shouldn't throw execption");
        }
    }
    
    private Connection createUseDatabase(String databaseName) throws SQLException {
        Driver driver = (Driver) TestRunner.getImplementationInstance();
        Properties info = new Properties();
        File dbDir = new File(tmp + "/jdbc/" + Math.round((((float) Math.random()) * 100000)));
        info.put("path", dbDir.getAbsoluteFile());
        Connection connection = driver.connect("jdbc:" + protocol + "://localhost", info);
        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE " + databaseName);
       
        statement.execute("USE " + databaseName);      
        statement.close();
        return connection;
    }
 
}