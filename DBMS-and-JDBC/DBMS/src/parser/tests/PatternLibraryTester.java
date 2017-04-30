package parser.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import parser.PatternLibrary;
/**
 * Test class of the more tricky patterns
 * that the library contains.
 * @author Amr
 *
 */
public class PatternLibraryTester {
	/**
	 * Pattern variable used in all the tests.
	 */
	private Pattern pattern;
	/**
	 * String variable that will contain all the
	 * strings that represent the different tests.
	 */
	private String test;
	/**
	 * Matcher variable that will be used to test the patterns.
	 */
	private Matcher matcher;
	/**
	 * Test on the list of words separated by commas
	 * between brackets.
	 */
	@Test
	public final void testBracketsIsbc() {
		pattern = Pattern.compile(
				PatternLibrary.getIsbcBetweenBrackets());
		test = "( amr , mico, ahmed  )";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
	}
	/**
	 * Test on the list of equal statements separated
	 * by commas.
	 */
	@Test
	public final void testEqualStatementsCommaList() {
		pattern = Pattern.compile(
				PatternLibrary.
				getEqualStatementsCommaListRegex());
		test = "amr = 'mico', bishoy = 'marc'";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
	}
	/**
	 * Test on Word.Word pattern.
	 */
	@Test
	public final void testWordPointWord() {
		pattern = Pattern.compile(
				PatternLibrary.
				getWordPointWord());
		test = "amr.mico";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = "amr";
		matcher = pattern.matcher(test);
		Assert.assertTrue(!matcher.matches());
	}
	/**
	 * Test on the word.word or valid word pattern.
	 */
	@Test
	public final void testWPWorVW() {
		pattern = Pattern.compile(
				PatternLibrary.
				getWpwOrVw());
		test = "amr.mico";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = "mico";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
	}
	/**
	 * Test on the orderBy pattern.
	 */
	@Test
	public final void testOrderBy() {
		pattern = Pattern.compile(
				PatternLibrary.
				getOrderByStatement());
		test = "ORDER BY max";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = "ORDER BY max ASC";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = "ORDER BY max, low";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = "ORDER BY max ASC , low";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = "ORDER BY max ASC , low DESC , ora";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
	}
	/**
	 * Test on the order by or where or end or both pattern.
	 */
	@Test
	public final void testOrderByWhereEND() {
		pattern = Pattern.compile(
				PatternLibrary.
				getWhereStatementOrOrderbyOrEndingRegex());
		test = "";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " WHERE x = 5";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " WHERE x = 5";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " ORDER BY max";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " ORDER BY max, low";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " ORDER BY max ASC, low DESC, fall";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " WHERE x = 5 ORDER BY max";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
		test = " WHERE x = 5 and y = 6 ORDER BY max";
		matcher = pattern.matcher(test);
		Assert.assertTrue(matcher.matches());
	}
}
