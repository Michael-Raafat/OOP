package conditionService;

import exceptions.UnknownColumnException;
import exceptions.WrongDatatypeInputException;
import logicalComponents.Condition;
import logicalComponents.LogicalConstant;
import logicalComponents.LogicalNode;
import logicalComponents.RelationalNode;
import models.Row;

public class ConditionSolver {

	/**
	 * static object for singleton.
	 */
	private static ConditionSolver conditionSolver;
	
	/**
	 * Solver for greater than, less than or equal operations.
	 */
	private RelationalConditionSolver relationalSolver;
	
	/**
	 * Solver for AND and OR operations.
	 */
	private LogicalConditionSolver logicalSolver;
	
	/**
	 * private constructor called once for singleton.
	 */
	private ConditionSolver() {
		relationalSolver = new RelationalConditionSolver();
		logicalSolver = new LogicalConditionSolver();
	}
	
	/**
	 * Gets the ConditionSolver instance.
	 * @return the current condition solver object.
	 */
	public static ConditionSolver getInstace() {
		if(conditionSolver != null) {
			return conditionSolver;
		} else {
			return new ConditionSolver();
		}	
	}

	/**
	 * return whether this row satisfies a condition or not.
	 * @param row to check for.
	 * @param conditon to check for
	 * @return true it row satisfies condition else false
	 * @throws WrongDatatypeInputException 
	 */
	public boolean isRowSatisfyingCondition(Row row, Condition condition)
								throws UnknownColumnException, WrongDatatypeInputException {
		if (condition instanceof RelationalNode) {
			RelationalNode op = (RelationalNode) condition;
			return relationalSolver.isRowSolvingRelationalCondition(row, op);
		} else if (condition instanceof LogicalNode) {
			return logicalSolver.isRowSolvingLogicalCondition(row,
									(LogicalNode) condition);
		} else if (condition instanceof LogicalConstant){
			return ((LogicalConstant) condition).isTrue();
		} else {
			return true;
		}
	}
	
}
