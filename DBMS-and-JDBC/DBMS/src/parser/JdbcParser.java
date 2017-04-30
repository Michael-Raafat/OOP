package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import fileManager.SaveExtension;
/**
 * JDPC parser for urls.
 * @author Amr
 *
 */
public class JdbcParser {
	/**
	 * Constructor.
	 */
	public JdbcParser() {
	}
	/**
	 * XML subprotocol format.
	 */
	private static final String XML_SUBPROTOCOL = "xmldb";
	/**
	 * Json subprotocol format.
	 */
	private static final String JSON_SUBPROTOCOL = "jsondb";
	/**
	 * Alternate database saving format.
	 */
	private static final String ALT_SUBPROTOCOL = "altdb";
	/**
	 * Protocol buffer subportocol format.
	 */
	private static final String PROTO_SUBPROTOCOL = "protodb";
	/**
	 * Acceptable sub-protocols.
	 */
	private static final String SUBPROTOCOLS
		= XML_SUBPROTOCOL + "|" + JSON_SUBPROTOCOL
			+ "|" + ALT_SUBPROTOCOL + "|" + PROTO_SUBPROTOCOL;
	/**
	 * Valid Url regex.
	 */
	private static final String JDBC_URL
		= "jdbc:("
			+ SUBPROTOCOLS
			+ ")://localhost";
	/**
	 * Gets the saving extension from a url.
	 * @param url
	 * The url that contains the subprotocol.
	 * @return
	 * The saving extension used as a default.
	 * @throws WrongSyntaxException
	 * If it is an unacceptable url.
	 */
	public final SaveExtension getExtensionFromSubprotocol(final String url)
			throws WrongSyntaxException {
		Pattern urlPattern = Pattern.compile(JDBC_URL);
		Matcher urlMatcher = urlPattern.matcher(url);
		if (isAcceptableURL(url)) {
			urlMatcher.find();
			String extension = urlMatcher.group(1);
			if (extension.equals(XML_SUBPROTOCOL)) {
				return SaveExtension.XML;
			} else if (extension.equals(JSON_SUBPROTOCOL)) {
				return SaveExtension.JSON;
			} else if (extension.equals(ALT_SUBPROTOCOL)) {
				return SaveExtension.JSON;
			} else if (extension.equals(PROTO_SUBPROTOCOL)) {
				return SaveExtension.PROTO;
			}
		}
		throw new WrongSyntaxException();
	}
	/**
	 * Checks if the url is acceptable.
	 * @param url
	 * String url to check if it's valid.
	 * @return
	 * True if it is a valid string.
	 */
	public final boolean isAcceptableURL(final String url) {
		return Pattern.matches(JDBC_URL, url);
	}
}
