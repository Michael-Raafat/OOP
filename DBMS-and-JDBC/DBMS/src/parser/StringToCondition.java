package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongSyntaxException;
import logicalComponents.Condition;
import logicalComponents.LogicalConstant;
import logicalComponents.LogicalNode;
import logicalComponents.LogicalOperations;
import logicalComponents.RelationalNode;
import logicalComponents.RelationalOperandType;
import logicalComponents.RelationalOperations;
import models.Datatype;
import syntaxManagement.SyntaxManagementUtility;

/**
 * Turns the string into a condition.
 * @author Amr
 *
 */
public final class StringToCondition {
	/**
	 * Private constructor to prevent making instances.
	 */
	private StringToCondition() {
	}
	/**
	 * Function responsible of transforming a string into a condition.
	 * @param original
	 * The string to be transformed.
	 * @return
	 * A condition.
	 * @throws WrongSyntaxException
	 * If an invalid string is entered.
	 */
	public static Condition stringToCondition(
			final String original)
			throws WrongSyntaxException {
		if (original == null) {
			return new LogicalConstant(true);
		}
		List<String> listLowStatements
			= StringToList.stringToListByOneSeperator(
				"(?i)\\s+(AND|OR)\\s+", original);
		SyntaxManagementUtility.trimStringList(listLowStatements);
		for (int i = 0; i < listLowStatements.size(); i++) {
			String word = listLowStatements.get(i);
			if (word.charAt(0) == '(') {
				listLowStatements.set(i,
						word.substring(1, word.length()));
			}
			if (word.charAt(word.length() - 1) == ')') {
				listLowStatements.set(i,
						word.substring(0, word.length() - 1));
			}
		}
		List<Condition> relationNodes
			= new ArrayList<Condition>();
		for (int i = 0; i < listLowStatements.size();
				i++) {
			relationNodes.add(
				parseRelationalNode(listLowStatements.get(i)));
		}
		Condition condition;
		if (relationNodes.size() == 1) {
			condition = relationNodes.get(0);
			return condition;
		} else if (relationNodes.size() == 0) {
			condition = new LogicalConstant(true);
			return condition;
		}
		List<String> listComplexStatements = new ArrayList<String>();
		String tempComplex;
		tempComplex = original;
		for (int i = 0; i < listLowStatements.size(); i++) {
			tempComplex = tempComplex.replaceAll(listLowStatements.get(i), "");
		}
		listComplexStatements = StringToList.stringToListByOneSeperator(
				"\\s+",
			tempComplex.trim());
		SyntaxManagementUtility.trimStringList(listComplexStatements);
		List<Object> expression
			= constructExpression(listComplexStatements, relationNodes);
		condition = constructTree(expression);
		return condition;
	}
	/**
	 * Construct condition tree form a list of objects.
	 * @param expression
	 * The list of objects.
	 * @return
	 * A tree of conditions after handling the list of objects.
	 * @throws WrongSyntaxException
	 * If it is an invalid list of objects and can't be turned
	 * to a condition tree.
	 */
	private static Condition constructTree(List<Object> expression)
				throws WrongSyntaxException {
		Stack<Condition> operands = new Stack<Condition>();
		Stack<Object> operators = new Stack<Object>();
		try {
			for (int i = 0; i < expression.size(); i++) {
				if (expression.get(i) instanceof RelationalNode
						|| expression.get(i) instanceof LogicalConstant) {
					operands.push((Condition) expression.get(i));
				} else if (expression.get(i) instanceof LogicalNode) {
					constructTreeLogicalNodeAction(operators, operands,
							(LogicalNode) expression.get(i));
				} else {
					String temp = (String) expression.get(i);
					if (temp.equals("(")) {
						operators.push("(");
					} else if (temp.equals(")")) {
						if (operators.isEmpty()) {
							throw new WrongSyntaxException();
						}
							while (!operators.isEmpty()
									&& (!(operators.peek() instanceof String)
										|| !operators.peek().equals("("))) {
								LogicalNode node = (LogicalNode) operators.pop();
								node.setRightOperation(operands.pop());
								node.setLeftOperation(operands.pop());
								operands.push(node);
							}
							if (operators.isEmpty()
								|| !(operators.peek() instanceof String)
								|| !operators.pop().equals("(")) {
								throw new WrongSyntaxException();
							}
						}
					}
			}
			while (!operators.isEmpty()) {
				LogicalNode node = (LogicalNode) operators.pop();
				node.setRightOperation(operands.pop());
				node.setLeftOperation(operands.pop());
				operands.push(node);
			}
			Condition condition;
			if (operands.size() != 1) {
				throw new WrongSyntaxException();
			}
			condition = operands.pop();
			return condition;
		} catch (Exception e) {
			throw new WrongSyntaxException();
		}
	}
	/**
	 * Function responsible of what to do if an item is identified to be a
	 * Logical node.
	 * @param operators
	 * Stack that contains operators.
	 * @param operands
	 * Stack that contains an operation.
	 * @param node
	 * The logical node detected in the expression.
	 * @throws WrongSyntaxException
	 * If there is a wrong syntax in the expression passed.
	 */
	private static void constructTreeLogicalNodeAction(
			final Stack<Object> operators,
			final Stack<Condition> operands, final LogicalNode node)
					throws WrongSyntaxException {
		if (operands.isEmpty()) {
			throw new WrongSyntaxException();
		} else {
			while (!operators.isEmpty()
					&& operators.peek() instanceof LogicalNode
					&& !logicalOperatorFollow(
						node.getOperation(),
					((LogicalNode) operators.peek()).getOperation())) {
				LogicalNode tempNode = (LogicalNode) operators.pop();
				tempNode.setRightOperation(operands.pop());
				tempNode.setLeftOperation(operands.pop());
				operands.push(tempNode);
			}
			operators.push(node);
		}
	}
	/**
	 * Constructs the expression using condition componenets.
	 * @param listComplexStatements
	 * List of the complex statements in the string.
	 * @param relationNodes
	 * List of the relation statements in the string.
	 * @return
	 * A list of objects containing the expression.
	 * @throws WrongSyntaxException
	 * If there is an undefined value in the string.
	 */
	private static List<Object> constructExpression(
			final List<String> listComplexStatements,
			final List<Condition> relationNodes) 
				throws WrongSyntaxException {
		int relationCounter = 0;
		List<Object> expression = new ArrayList<Object>();
		for (int i = 0; i < listComplexStatements.size();
				i++) {
			if (listComplexStatements.get(i).equals("(")) {
				expression.add("(");
			} else if (listComplexStatements.get(i).equals(")")) {
				expression.add(relationNodes.get(relationCounter));
				relationCounter++;
				expression.add(")");
			} else if (!listComplexStatements.get(i).trim().equals("")) {
				expression.add(relationNodes.get(relationCounter));
				relationCounter++;
				expression.add(new LogicalNode(null, null,
						parseLogicalOperation(listComplexStatements.get(i))));
			}
		}
		if (relationCounter == relationNodes.size() - 1) {
			expression.add(relationNodes.get(relationCounter));
		} else if (relationCounter < relationNodes.size() - 1) {
			throw new WrongSyntaxException();
		}
		return expression;
	}
	/**
	 * Checks if a logical operation has lower precedence or not.
	 * @param operationPreceds
	 * Operation we assume to precede.
	 * @param operationToFollow
	 * Operation we assume to follow.
	 * @return
	 * True if our assumption is right.
	 */
	private static boolean logicalOperatorFollow(
			final LogicalOperations operationPreceds,
			final LogicalOperations operationToFollow) {
		if (operationPreceds.equals(operationToFollow)) {
			return false;
		} else {
			return !(operationToFollow == LogicalOperations.AND);
		}
	}
	/**
	 * parses a string to its logical operation.
	 * @param source
	 * String containing the operation.
	 * @return
	 * An enum that represents a logical operation.
	 * @throws WrongSyntaxException
	 * If an invalid operation in the string is entered.
	 */
	private static LogicalOperations parseLogicalOperation(
			final String source) throws WrongSyntaxException {
		String modSource = source.trim().toUpperCase();
		if (modSource.equals("AND")) {
			return LogicalOperations.AND;
		} else if (modSource.equals("OR")) {
			return LogicalOperations.OR;
		} else {
			throw new WrongSyntaxException();
		}
	}
	/**
	 * Function that transforms a relational string to
	 * a suitable relationalNode.
	 * @param original
	 * String containing the relational expression.
	 * @return
	 * A relational Node containing all the information.
	 * @throws WrongSyntaxException
	 * If an invalid string was given.
	 */
	private static Condition parseRelationalNode(final String original)
			throws WrongSyntaxException {
		Pattern lowLevelPattern = Pattern.compile(
				PatternLibrary.getLowLogicalStatement());
		Matcher lowLevelMatcher = lowLevelPattern.matcher(original);
		if (!lowLevelMatcher.matches()) {
			throw new WrongSyntaxException();
		}
		lowLevelMatcher.start();
		if (isBooleanConstant(lowLevelMatcher.group(1))) {
			return new LogicalConstant(Pattern.matches("(?i)TRUE",
					lowLevelMatcher.group(1)));
		} else {
			return createRelationalNode(
				lowLevelMatcher.group(PatternLibrary.getLlsLeftArugmentIndex()),
				lowLevelMatcher.group(
						PatternLibrary.getLlsRightArguemntIndex()),
				lowLevelMatcher.group(PatternLibrary.getLlsOperatorIndex()));
		}
	}
	/**
	 * Function that takes three strings and return a relational node
	 * representing the relation between them.
	 * @param left
	 * String containing left argument
	 * @param right
	 * String containing Right argument.
	 * @param operation
	 * String containing the operation.
	 * @return
	 * A relational Node containing the information.
	 * @throws WrongSyntaxException
	 * If there an invalid value or operation.
	 */
	private static RelationalNode createRelationalNode(final String left,
			final String right, final String operation)
					throws WrongSyntaxException {
		RelationalNode node =
				new RelationalNode(
						getRelationOperandType(left),
						getRelationOperandType(right),
						getRelationOperation(operation));
		return node;
	}
	/**
	 * Checks if a string is a boolean constant value.
	 * @param value
	 * String to check.
	 * @return
	 * True if it is a boolean constant.
	 */
	private static boolean isBooleanConstant(final String value) {
		Pattern trueOrFalsePattern = Pattern.compile(
				PatternLibrary.getLogicalTrueOrFalse());
		Matcher trueOrFalseMatcher = trueOrFalsePattern.matcher(
				value);
		return trueOrFalseMatcher.matches();
	}
	/**
	 * private static function that gets the type
	 * of relational operations.
	 * @param source
	 * String to be parsed.
	 * @return
	 * The suitable enum of the relational operation.
	 * @throws WrongSyntaxException
	 * If it is an invalid string.
	 */
	private static RelationalOperations getRelationOperation(
			final String source) throws WrongSyntaxException {
		String modSource = source.trim();
		if (modSource.equals("=")) {
			return RelationalOperations.EQUAL;
		} else if (modSource.equals(">")) {
			return RelationalOperations.GREATER_THAN;
		} else if (modSource.equals("<")) {
			return RelationalOperations.LESS_THAN;
		} else if (modSource.equals(">=")) {
			return RelationalOperations.GREATER_THAN_OR_EQUAL;
		} else if (modSource.equals("<=")) {
			return RelationalOperations.LESS_THAN_OR_EQUAL;
		} else if (modSource.equals("!=")) {
			return RelationalOperations.NOT_EQUAL;
		} else {
			throw new WrongSyntaxException();
		}
	}
	/**
	 * Function that gets the suitable relational operand type
	 * for a string.
	 * @param source
	 * The string containing the information to be turned
	 * to a relationalOperandType.
	 * @return
	 * A relational OperandType.
	 * @throws WrongSyntaxException
	 * If an unknown string is given.
	 */
	private static RelationalOperandType getRelationOperandType(
			final String source) throws WrongSyntaxException {
		String modSource = source.trim();
		boolean variable = false;
		Datatype datatype = null;
		String value = null;
		datatype = CheckingUtilities.getStringDatatype(modSource);
		if (datatype != null) {
			value = CheckingUtilities.processStringByDatatype(modSource);
		} else if (CheckingUtilities.isVariable(modSource)) {
			value = modSource;
			variable = true;
		}
		if (datatype != null || variable) {
			return new RelationalOperandType(value, variable, datatype);
		} else {
			throw new WrongSyntaxException();
		}
	}
}
