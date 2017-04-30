package commandLineInterface;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
 
import databaseManagement.ConfigurationManager;
import exceptions.UnknownExtension;
import jdbc.DriverImp;
import printingService.PrintResultSet;
 
public class CliEnteryPoint {
 
    /**
     * responsible for printing messages to the user,
     */
    private static CliPrintHelper printHelper;
 
    /**
     * responsible for printing resultSet.
     */
    private static PrintResultSet resultSetPrinter;
 
    /**
     * responsible for managing configuration file
     */
    private static ConfigurationManager configurationManager;
 
    /**
     * Main running point of the application
     *
     * @param args
     * @throws UnknownExtension
     * @throws SQLException
     */
    public static void main(String[] args) throws UnknownExtension, SQLException {
        printHelper = CliPrintHelper.getInstance();
        resultSetPrinter = PrintResultSet.getInstance();
        configurationManager = new ConfigurationManager();
        Scanner scanner = new Scanner(System.in);
        Connection conn = establishConnection(scanner);
        performQuery(scanner, conn);
        scanner.close();
    }
 
    /**
     * Gets the query from user and executes it.
     *
     * @param scanner
     *            to get input from the user
     * @param conn
     *            the connection established.
     * @throws SQLException
     */
    private static void performQuery(Scanner scanner, Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            conn = establishConnection(scanner);
            performQuery(scanner, conn);
        }
        excuteQueryAccordingToType(scanner, conn, st);
    }
 
    /**
     * Executes the query and respond according to the query type.
     *
     * @param scanner
     *            to get input from the user
     * @param conn
     *            the connection established.
     * @param st
     *            the created statement from the connection.
     */
    private static void excuteQueryAccordingToType(
            Scanner scanner, Connection conn,
            Statement st) {
        printHelper.requestQuery();
        String query = scanner.nextLine();
        while (!query.equals("exit")) {
            try {
                if (st.execute(query)) {
                    resultSetPrinter.printResultSet(st.getResultSet());
                } else if (st.getUpdateCount() != -1) {
                    System.out.println(st.getUpdateCount() + " rows affected");
                } else {
                    System.out.println("Query excuted successfully");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                printHelper.requestQuery();
                query = scanner.nextLine();
            }
        }
    }
 
    /**
     * Establishes a new connection from URL entered by user.
     *
     * @param scanner
     *            to get input from the user
     * @return the connection established
     * @throws SQLException
     */
    private static Connection establishConnection(Scanner scanner) throws SQLException {
        checkConfigurationFileValidity(scanner);
        Properties info = configurationManager.getConfigProperties();
        checkValidLoginInfo(info, scanner);
        DriverManager.registerDriver(new DriverImp());
        Properties properties = new Properties();
        properties.put("path", configurationManager.getConfigProperties().get("path").toString());
//      Driver driver = new DriverImp();
        String url = getURLFromUser(scanner);
        Connection conn;
        try {
//          conn = driver.connect(url, info);
            conn = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            return establishConnection(scanner);
        }
        return conn;
    }
 
    /**
     * check valid login credentials
     *
     * @param info
     *            properties coming from configuration file.
     * @param scanner
     *            to get input from the user
     */
    private static void checkValidLoginInfo(Properties info, Scanner scanner) {
        System.out.println("Enter username : ");
        String username = scanner.nextLine();
        System.out.println("Enter password : ");
        String password = scanner.nextLine();
        String infoUsername = info.get("username").toString();
        String infoPasword = info.get("password").toString();
        if (!username.equals(infoUsername) || !password.equals(infoPasword)) {
            System.out.println("Invalid login credentials");
            try {
				establishConnection(scanner);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

    /**
     * Gets a valid URL from the user.
     *
     * @param scanner
     *            to get input from the user
     * @param driver
     *            to check for URL validity
     * @return a String for the URL.
     */
    private static String getURLFromUser(Scanner scanner) {
        printHelper.requestURL();
        String url = scanner.nextLine();
        try {
            while (!DriverManager.getDriver(url).acceptsURL(url)) {
                printHelper.requestURL();
                url = scanner.nextLine();
            }
        } catch (SQLException e) {
            return "jdbc:xmldb://localhost";
        }
        return url;
    }
 
    /**
     * Checks for the presence and validity of the configuration file.
     *
     * @param scanner
     *            to get input from the user
     */
    private static void checkConfigurationFileValidity(Scanner scanner) {
        while (!configurationManager.foundValidConfigurationFile()) {
            printHelper.requestConfigFile();
            String response = scanner.nextLine();
            while (!response.equals("done")) {
                response = scanner.nextLine();
            }
        }
    }
}
