package commandLineInterface;

public class CliPrintHelper {
    
    /**
     * static instance for singleton.
     */
	private static CliPrintHelper printHelper;

	/**
	 * Empty private constructor for singleton.
	 */
	private CliPrintHelper() {

	}

	/**
	 * gets the same and only instance at every call.
	 * @return the same and only instance at every call.
	 */
	public static CliPrintHelper getInstance() {
		if(printHelper == null) {
			printHelper = new CliPrintHelper();
		}
		return printHelper;
	}

	/**
	 * print to the cmd requesting configuration file.
	 */
	public void requestConfigFile() {
		System.out.println("Please enter a valid Configuration file!");
		System.out.println("Type " + "'done'" + " when file is added to its default path.");
	}

	/**
	 * print to the cmd requesting a valid URL.
	 */
	public void requestURL() {
		System.out.println("Please enter a valid URL");
		System.out.println("A valid URL is: jdbc:xmldb://localhost or jdbc:jsondb://localhost or jdbc:protodb://localhost" );
	}

	/**
	 * print to the cmd requesting a valid SQL query.
	 */
	public void requestQuery() {
		System.out.println("Enter a valid SQL Query");
		System.out.println("Type " + "'exit'" + " to close");
	}

}
