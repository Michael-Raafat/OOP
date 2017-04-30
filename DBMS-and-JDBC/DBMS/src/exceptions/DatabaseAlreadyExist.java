package exceptions;

public class DatabaseAlreadyExist extends Exception implements CustomException {
	/**
	 * Serialization key.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Basic Constructor for the exception.
	 */
	public DatabaseAlreadyExist() {
		super("Database already exist.");
	}

	/**
	 * Calls the super constructor to pass the message that the exception should
	 * hold.
	 * 
	 * @param message
	 *            Massage to be thrown with the exception.
	 */
	public DatabaseAlreadyExist(final String message) {
		super(message);
	}

	/**
	 * Calls the super constructor to pass the cause of the error and a message
	 * that the exception should hold.
	 * 
	 * @param cause
	 *            Cause of the exception.
	 * @param message
	 *            Massage to be thrown with the exception.
	 */
	public DatabaseAlreadyExist(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Calls the super constructor to pass the cause of the error that the
	 * exception should save.
	 * 
	 * @param cause
	 *            Cause of the exception.
	 */
	public DatabaseAlreadyExist(final Throwable cause) {
		super(cause);
	}
}
