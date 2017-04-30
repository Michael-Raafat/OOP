package logicalComponents;

public class RelationalNode extends Condition {
	
	/**
	 * left argument.
	 */
	private RelationalOperandType leftArgument;
	
	/**
	 * right argument.
	 */
	private RelationalOperandType rightArgument;
	
	/**
	 * relational operation to perform
	 */
	private RelationalOperations operation;
	
	/**
	 * @param column column name.
	 * @param value field value for selection.
	 * @param operation relational operation to perform
	 */
	public RelationalNode(RelationalOperandType leftargument,
			RelationalOperandType rightargument,
			RelationalOperations operation) {
		super();
		this.leftArgument = leftargument;
		this.rightArgument = rightargument;
		this.operation = operation;
	}

	/**
	 * @return the leftArgument
	 */
	public RelationalOperandType getLeftArgument() {
		return leftArgument;
	}

	/**
	 * @param leftArgument the leftArgument to set
	 */
	public void setLeftArgument(RelationalOperandType leftArgument) {
		this.leftArgument = leftArgument;
	}

	/**
	 * @return the rightArgument
	 */
	public RelationalOperandType getRightArgument() {
		return rightArgument;
	}

	/**
	 * @param rightArgument the rightArgument to set
	 */
	public void setRightArgument(RelationalOperandType rightArgument) {
		this.rightArgument = rightArgument;
	}

	/**
	 * @return the operation
	 */
	public RelationalOperations getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(RelationalOperations operation) {
		this.operation = operation;
	}
}
