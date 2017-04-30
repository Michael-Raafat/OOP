package conditionService;

import exceptions.UnknownColumnException;
import exceptions.WrongDatatypeInputException;
import logicalComponents.Condition;
import models.Table;
import models.implementation.TableImp;

public class ConditionServiceProvider {

	/**
	 * static object for singleton.
	 */
	private static ConditionServiceProvider serviceProvider;
	
	/**
	 * responsible for solving the condition.
	 */
	private ConditionSolver solver;
	
	/**
	 * private constructor called once for singleton.
	 */
	private ConditionServiceProvider() {
		solver = ConditionSolver.getInstace();
	}
	
	/**
	 * Gets the serviceProvider instance.
	 * @return the current service provider object.
	 */
	public static ConditionServiceProvider getInstance() {
		if(serviceProvider != null) {
			return serviceProvider;
		} else {
			return new ConditionServiceProvider();
		}
	}
	
	/**
	 * Get a table containing rows satisfying condition.
	 * Rows are returned by reference not by value
	 * @param table to get result from.
	 * @param condition to check for.
	 * @return a table object with rows satisfying condition.
	 * @throws UnknownColumnException
	 * @throws WrongDatatypeInputException
	 */
	public Table getRowsForCondition(Table table, Condition condition)
			throws UnknownColumnException, WrongDatatypeInputException {
		Table ret = new TableImp(table.getName(), table.getDatabase(), table.getHeader());
		for(int i = 0; i < table.getRows().size(); i++) {
			if(solver.isRowSatisfyingCondition(table.getRows().get(i), condition)) {
				ret.addRow(table.getRows().get(i));
			}
		}
		return ret;
	}
	
	/**
	 * remove rows satisfying condition.
	 * @param table to remove result from.
	 * @param condition to check for.
	 * @throws UnknownColumnException
	 * @throws WrongDatatypeInputException
	 */
	public void removeRowsForCondition(Table table, Condition condition)
			throws UnknownColumnException, WrongDatatypeInputException {
		for(int i = table.getRows().size() - 1; i > - 1; i--) {
			if(solver.isRowSatisfyingCondition(table.getRows().get(i), condition)) {
				table.getRows().remove(i);
			}
		}
	}
	
}
