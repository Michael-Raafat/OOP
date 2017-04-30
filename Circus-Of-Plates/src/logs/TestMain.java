package logs;
/**
 * 
 * @author Michael.
 *
 */
public class TestMain {

	public static void main(String[] args) {
		LogService.printDebug(TestMain.class, "go");
		LogService.printTrace(TestMain.class, "go");
		LogService.printTrace(TestMain.class, "go");
		LogService.printWarn(TestMain.class, "go");
		LogService.printFatal(TestMain.class, "go");
		LogService.printError(TestMain.class, "go");
	}

}
