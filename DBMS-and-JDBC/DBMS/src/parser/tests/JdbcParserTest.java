package parser.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.WrongSyntaxException;
import fileManager.SaveExtension;
import parser.JdbcParser;
/**
 * JDBC parser test.
 * @author Amr
 *
 */
public class JdbcParserTest {
	/**
	 * Parser to test on.
	 */
	private JdbcParser urlParser;
	/**
	 * Sets up the urlParser.
	 */
	@Before
	public final void setUp() {
		urlParser = new JdbcParser();
	}
	/**
	 * Checks if the parser accepts the valid urls and refuse the bad ones.
	 */
	@Test
	public final void testAcceptUrl() {
		assertTrue(urlParser.isAcceptableURL("jdbc:xmldb://localhost"));
		assertTrue(urlParser.isAcceptableURL("jdbc:jsondb://localhost"));
		assertTrue(!urlParser.isAcceptableURL("jdbc:wrongdb://localhost"));
		assertTrue(!urlParser.isAcceptableURL("jdbf:xmldb://localhost"));
	}
	/**
	 * Extracts the extension and returns the saving format.
	 */
	@Test
	public void testExtensionExtraction() {
		try {
			assertEquals(SaveExtension.XML,
			urlParser.getExtensionFromSubprotocol("jdbc:xmldb://localhost"));
		} catch (WrongSyntaxException e) {
			fail();
		}
		try {
			assertEquals(SaveExtension.JSON,
			urlParser.getExtensionFromSubprotocol("jdbc:jsondb://localhost"));
		} catch (WrongSyntaxException e) {
			fail();
		}
		try {
			assertEquals(SaveExtension.JSON,
			urlParser.getExtensionFromSubprotocol("jdbc:altdb://localhost"));
		} catch (WrongSyntaxException e) {
			fail();
		}
		try {
			assertEquals(SaveExtension.XML,
			urlParser.getExtensionFromSubprotocol("jdbc:xmldb:// localhost"));
			fail();
		} catch (WrongSyntaxException e) {
		}
		try {
			assertEquals(SaveExtension.XML,
			urlParser.getExtensionFromSubprotocol("jdbf:xmldb://localhost"));
			fail();
		} catch (WrongSyntaxException e) {
		}
	}
}
