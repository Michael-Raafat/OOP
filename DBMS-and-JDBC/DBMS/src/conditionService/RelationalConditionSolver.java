package conditionService;

import exceptions.UnknownColumnException;
import exceptions.WrongDatatypeInputException;
import logicalComponents.RelationalNode;
import logicalComponents.RelationalOperandType;
import logicalComponents.RelationalOperations;
import models.Row;
import parser.StringToData;

public class RelationalConditionSolver {

	/**
	 * return whether this row satisfies a relational condition or not
	 * 
	 * @param row
	 *            to check for.
	 * @param operation
	 *            containing condition value and operation type either
	 *            GREATER_THAN, LESS_THAN or EQUAL
	 * @param columnIndex
	 *            index of the attribute checked for.
	 * @return true it row satisfies condition else false
	 * @throws UnknownColumnException 
	 * @throws WrongDatatypeInputException 
	 */
	public boolean isRowSolvingRelationalCondition(Row row, RelationalNode operation) 
														throws UnknownColumnException,
														WrongDatatypeInputException {
		// column and value
		if (operation.getLeftArgument().isVariable() && !operation.getRightArgument().isVariable()) {
			return compareFirstColumnAndValue(row, operation);
		// value and column
		} else if(!operation.getLeftArgument().isVariable() && operation.getRightArgument().isVariable()) {
			return compareFirstValueAndColumn(row, operation);
		// two columns
		} else if (operation.getLeftArgument().isVariable() && operation.getRightArgument().isVariable()) {
			return compareTwoColumns(row, operation);
		// two values
		} else if (!operation.getLeftArgument().isVariable() && !operation.getRightArgument().isVariable()) {
			return compareTwoValues(operation);
		} else {
			return true;
		}
	}

	/**
	 * get the result of applying an operation on two values.
	 * @param operation the type of relational operation
	 * @return true if condition true, else false
	 * @throws WrongDatatypeInputException 
	 */
	private boolean compareTwoValues(RelationalNode operation)
			throws WrongDatatypeInputException {
		if (operation.getLeftArgument().getValueDataType()
				!= operation.getRightArgument().getValueDataType()) {
			throw new WrongDatatypeInputException();
		}
		return getRelationalResult(operation.getOperation(), operation.getLeftArgument(),
				operation.getRightArgument());
	}

	/**
	 * get the result of applying an operation on two columns.
	 * @param row to check for.
	 * @param operation the type of relational operation
	 * @return true if condition true, else false
	 * @throws UnknownColumnException
	 */
	private boolean compareTwoColumns(Row row, RelationalNode operation) throws UnknownColumnException {
		int firstColumnIndex = row.getTable().getColumnIndexByName(operation.getLeftArgument().getOperand());
		int secondColumnIndex = row.getTable().getColumnIndexByName(operation.getRightArgument().getOperand());
		if(row.getTable().getHeader().getcolumnType(firstColumnIndex)
				!= row.getTable().getHeader().getcolumnType(secondColumnIndex)) {
			throw new UnknownColumnException();
		}
		String firstCellData = row.getCellDataByIndex(firstColumnIndex);
		String secondCellData = row.getCellDataByIndex(secondColumnIndex);
		RelationalOperandType firstSide = new RelationalOperandType(firstCellData, true, 
											row.getTable().getHeader().getcolumnType(firstColumnIndex));
		RelationalOperandType secondSide = new RelationalOperandType(secondCellData, true, 
				row.getTable().getHeader().getcolumnType(secondColumnIndex));
		return getRelationalResult(operation.getOperation(), firstSide, secondSide);
	}

	/**
	 * get the result of applying an operation on a column and a value.
	 * @param row to check for.
	 * @param operation the type of relational operation
	 * @return true if condition true, else false
	 * @throws UnknownColumnException
	 */
	private boolean compareFirstColumnAndValue(Row row, RelationalNode operation) throws UnknownColumnException {
		int columnIndex = row.getTable().getColumnIndexByName(operation.getLeftArgument().getOperand());
		String cellData = row.getCellDataByIndex(columnIndex);
		String operationValue = operation.getRightArgument().getOperand();
		if(row.getTable().getHeader().getcolumnType(columnIndex) != operation
										.getRightArgument().getValueDataType()) {
			throw new UnknownColumnException();
		}
		RelationalOperandType firstSide = new RelationalOperandType(cellData, true, 
				row.getTable().getHeader().getcolumnType(columnIndex));
		RelationalOperandType secondSide = new RelationalOperandType(operationValue, false, 
				operation.getRightArgument().getValueDataType());
		return getRelationalResult(operation.getOperation(), firstSide, secondSide);
	}
	
	/**
	 * get the result of applying an operation on a column and a value.
	 * @param row to check for.
	 * @param operation the type of relational operation
	 * @return true if condition true, else false
	 * @throws UnknownColumnException
	 */
	private boolean compareFirstValueAndColumn(Row row, RelationalNode operation) throws UnknownColumnException {
		int columnIndex = row.getTable().getColumnIndexByName(operation.getRightArgument().getOperand());
		String cellData = row.getCellDataByIndex(columnIndex);
		String operationValue = operation.getLeftArgument().getOperand();
		if(row.getTable().getHeader().getcolumnType(columnIndex) != operation
										.getLeftArgument().getValueDataType()) {
			throw new UnknownColumnException();
		}
		RelationalOperandType firstSide = new RelationalOperandType(operationValue, false, 
				operation.getLeftArgument().getValueDataType());
		RelationalOperandType secondSide = new RelationalOperandType(cellData, true, 
				row.getTable().getHeader().getcolumnType(columnIndex));
		return getRelationalResult(operation.getOperation(), firstSide, secondSide);
	}

	/**
	 * get result of operation on two fields.
	 * @param operation the type of relational operation
	 * @param firstVal first operand to the operation
	 * @param secondVal second operand to the operation
	 * @return true if satisfy else false
	 */
	private boolean getRelationalResult(RelationalOperations operation, RelationalOperandType firstVal,
																		RelationalOperandType secondVal) {
		switch (operation) {
		case EQUAL:
			return checkEqual(firstVal, secondVal);
		case GREATER_THAN:
			return checkGreaterThan(firstVal, secondVal);
		case LESS_THAN:
			return checkLessThan(firstVal, secondVal);
		case NOT_EQUAL:
			return !checkEqual(firstVal, secondVal);
		case GREATER_THAN_OR_EQUAL:
			return checkGreaterThan(firstVal,secondVal)
					|| checkEqual(firstVal, secondVal);
		case LESS_THAN_OR_EQUAL:
			return checkLessThan(firstVal, secondVal)
					|| checkEqual(firstVal, secondVal);
		default:
			return false;
		}
	}

	/**
	 * check whether the data in the cell is equal to the passed value in
	 * condition or not.
	 * 
	 * @param firstVal
	 *            data in the row cell.
	 * @param secondVal
	 *            the passed value in condition.
	 * @return true if they are equal else return false
	 */
	private boolean checkEqual(RelationalOperandType firstVal, RelationalOperandType secondVal) {
		return firstVal.getOperand().equals(secondVal.getOperand());
	}

	/**
	 * check whether the data in the cell is greater than the passed value in
	 * condition or not.
	 * 
	 * @param firstVal
	 *            data in the row cell.
	 * @param secondVal
	 *            the passed value in condition.
	 * @return true if cell data is greater than argument value else return
	 *         false
	 */
	private boolean checkGreaterThan(RelationalOperandType firstVal, RelationalOperandType secondVal) {
		switch(firstVal.getValueDataType()) {
		case VARCHAR:
			return firstVal.getOperand().compareTo(secondVal.getOperand()) > 0;
		case INTEGER:
			return Integer.parseInt(firstVal.getOperand()) > Integer.parseInt(secondVal.getOperand());
		case FLOAT:
			return Float.parseFloat(firstVal.getOperand()) > Float.parseFloat(secondVal.getOperand());
		case DATE:
			return StringToData.stringToDate(firstVal.getOperand())
					.after(StringToData.stringToDate(secondVal.getOperand())); 
		}
		return true;
	}

	/**
	 * check whether the data in the cell is less than the passed value in
	 * condition or not.
	 * 
	 * @param firstVal
	 *            data in the row cell.
	 * @param secondVal
	 *            the passed value in condition.
	 * @return true if cell data is less than argument value else return false
	 */
	private boolean checkLessThan(RelationalOperandType firstVal, RelationalOperandType secondVal) {
		switch(firstVal.getValueDataType()) {
		case VARCHAR:
			return firstVal.getOperand().compareTo(secondVal.getOperand()) < 0;
		case INTEGER:
			return Integer.parseInt(firstVal.getOperand()) < Integer.parseInt(secondVal.getOperand());
		case FLOAT:
			return Float.parseFloat(firstVal.getOperand()) < Float.parseFloat(secondVal.getOperand());
		case DATE:
			return StringToData.stringToDate(firstVal.getOperand())
					.before(StringToData.stringToDate(secondVal.getOperand()));
		}
		return true;
	}
}
