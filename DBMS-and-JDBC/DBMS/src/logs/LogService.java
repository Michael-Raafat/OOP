package logs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author Michael.
 *
 */
public class LogService {
	/**
	 * logger.
	 */
	private static Logger logger;
	/**
	 * constructor.
	 */
	public LogService() {
		if (logger == null) {
			 logger = LogManager.getLogger();
		}
	}
	/**
	 * log information.
	 * @param message
	 * information to be logged.
	 */
	public final void printMessage(final String message) {
		logger.info(message);
	}
	/**
	 * log errors and mistakes.
	 * @param error
	 * message of error.
	 */
	public final void printError(final String error) {
		logger.error(error);
	}
}

