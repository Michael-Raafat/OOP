package exceptions;

/**
 * Exception class for the exception that will be thrown when a table that is
 * requested in an operation is invalid.
 * 
 * @author Marc Magdi
 *
 */
public class UnknownExtension extends Exception implements CustomException{
	/**
	 * Serialization key.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Basic Constructor for the exception.
	 */
	public UnknownExtension() {
		super("Unknown extension.");
	}

	/**
	 * Calls the super constructor to pass the message that the exception should
	 * hold.
	 * 
	 * @param message
	 *            Massage to be thrown with the exception.
	 */
	public UnknownExtension(final String message) {
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
	public UnknownExtension(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Calls the super constructor to pass the cause of the error that the
	 * exception should save.
	 * 
	 * @param cause
	 *            Cause of the exception.
	 */
	public UnknownExtension(final Throwable cause) {
		super(cause);
	}
}
