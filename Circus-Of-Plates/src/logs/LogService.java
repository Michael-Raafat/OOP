package logs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Michael.
 */
public final class LogService {

	/**.
	 * private constructor to prevent
	 * creating an instance.
	 */
	private LogService() { }

	/**
	 * same log for every classes.
	 */
	private static Logger logee;
	/**
	 * write debug to be logged in log.
	 * @param user class calling message.
	 * @param message content to be informed.
	 */
	public static void printDebug(final Class<?> user,
			final String message) {
		logee = LogManager.getLogger(user);
		logee.debug(message);
	}
	/**
	 * write error to be logged.
	 * @param user class calling message.
	 * @param message content to be informed.
	 */
	public static void printError(final Class<?> user,
			final String message) {
		logee = LogManager.getLogger(user);
		logee.error(message);
	}
	/**
	 * write info to be logged.
	 * @param user class calling message.
	 * @param message content to be informed.
	 */
	public static void printTrace(final Class<?> user,
			final String message) {
		logee = LogManager.getLogger(user);
		logee.trace(message);
	}
	/**
	 * write fatal to be logged.
	 * @param user class calling message.
	 * @param message content to be informed.
	 */
	public static void printFatal(final Class<?> user,
			final String message) {
		logee = LogManager.getLogger(user);
		logee.fatal(message);
	}
	/**
	 * write trace to be logged.
	 * @param user class calling message.
	 * @param message content to be informed.
	 */
	public static void printInfo(final Class<?> user,
			final String message) {
		logee = LogManager.getLogger(user);
		logee.info(message);
	}
	/**
	 * write a warn to be logged.
	 * @param user class calling message.
	 * @param message content to be informed.
	 */
	public static void printWarn(final Class<?> user,
			final String message) {
		logee = LogManager.getLogger(user);
		logee.warn(message);
	}

}

