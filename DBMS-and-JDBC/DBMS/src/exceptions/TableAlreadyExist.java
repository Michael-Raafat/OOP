package exceptions;

public class TableAlreadyExist extends Exception implements CustomException{
	/**
	 * Serialization key.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Basic Constructor for the exception.
	 */
	public TableAlreadyExist() {
		super("Table already exist.");
	}

	/**
	 * Calls the super constructor to pass the message that the exception should
	 * hold.
	 * 
	 * @param message
	 *            Massage to be thrown with the exception.
	 */
	public TableAlreadyExist(final String message) {
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
	public TableAlreadyExist(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Calls the super constructor to pass the cause of the error that the
	 * exception should save.
	 * 
	 * @param cause
	 *            Cause of the exception.
	 */
	public TableAlreadyExist(final Throwable cause) {
		super(cause);
	}
}
