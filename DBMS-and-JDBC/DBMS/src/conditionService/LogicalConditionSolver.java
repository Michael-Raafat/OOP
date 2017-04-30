package conditionService;

import exceptions.UnknownColumnException;
import exceptions.WrongDatatypeInputException;
import logicalComponents.Condition;
import logicalComponents.LogicalConstant;
import logicalComponents.LogicalNode;
import logicalComponents.LogicalOperations;
import logicalComponents.RelationalNode;
import models.Row;

public class LogicalConditionSolver {

	/**
	 * responsible for solving relational operations.
	 * within logical operations.
	 */
	private RelationalConditionSolver relationalSolver;
	
	/**
	 * Default constructor for solver initialization. 
	 */
	public LogicalConditionSolver() {
		relationalSolver = new RelationalConditionSolver();
	}

	/**
	 * return whether this row satisfies a logical condition or not
	 * @param row to check for.
	 * @param operation containing condition value and operation type
	 * either AND or OR
	 * @return true it row satisfies condition else false
	 * @throws WrongDatatypeInputException 
	 */
	public boolean isRowSolvingLogicalCondition(Row row, LogicalNode condition)
												throws UnknownColumnException,
												WrongDatatypeInputException {
		Boolean leftResult = getOperationResult(row, condition.getLeftOperation());
		Boolean rightResult = getOperationResult(row, condition.getRightOperation());
		return getLogicalOperationResult(leftResult, rightResult, condition.getOperation());
	}
	
	/**
	 * gets the boolean result of an operation
	 * @param row to check.
	 * @param condition to check for.
	 * @return true it row satisfies condition else false.
	 * @throws UnknownColumnException
	 * @throws WrongDatatypeInputException 
	 */
	private Boolean getOperationResult(Row row, Condition condition)
											throws UnknownColumnException,
											WrongDatatypeInputException {
		Boolean result = true;
		if(condition instanceof RelationalNode) {
			result = relationalSolver.isRowSolvingRelationalCondition(row, 
													(RelationalNode) condition);
		} else if(condition instanceof LogicalNode) {
			result = isRowSolvingLogicalCondition(row, (LogicalNode) condition);
		} else if (condition instanceof LogicalConstant){
			return ((LogicalConstant) condition).isTrue();
		}
		return result;
	}

	/**
	 * apply AND or OR for two booleans
	 * @param leftResult boolean result of left operation
	 * @param rightResult boolean result of right operation
	 * @param operation either AND or OR operation
	 * @return the result of AND or OR for two booleans
	 */
	private boolean getLogicalOperationResult(Boolean leftResult, Boolean rightResult,
											LogicalOperations operation) {
		switch(operation) {
		case AND:
			return leftResult && rightResult;
		case OR:
			return leftResult || rightResult;
		default:
			return false;
		}
	}
}
