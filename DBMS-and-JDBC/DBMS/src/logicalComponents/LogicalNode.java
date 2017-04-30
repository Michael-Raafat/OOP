package logicalComponents;

public class LogicalNode extends Condition {
	
	/**
	 * left operation.
	 */
	private Condition leftOperation;
	
	/**
	 * right operation.
	 */
	private Condition rightOperation;
	
	/**
	 * logical operation to perform.
	 */
	private LogicalOperations operation;
	
	/**
	 * @param leftOperation left operation
	 * @param rightOpertatiion right operation 
	 * @param operation logical operation to perform
	 */
	public LogicalNode(Condition leftOperation, Condition rightOperation,
			LogicalOperations operation) {
		super();
		this.leftOperation = leftOperation;
		this.rightOperation = rightOperation;
		this.operation = operation;
	}

	/**
	 * @return the leftOperation
	 */
	public Condition getLeftOperation() {
		return leftOperation;
	}

	/**
	 * @param leftOperation the leftOperation to set
	 */
	public void setLeftOperation(Condition leftOperation) {
		this.leftOperation = leftOperation;
	}

	/**
	 * @return the rightOpertatiion
	 */
	public Condition getRightOperation() {
		return rightOperation;
	}

	/**
	 * @param rightOpertatiion the rightOpertatiion to set
	 */
	public void setRightOperation(Condition rightOpertatiion) {
		this.rightOperation = rightOpertatiion;
	}

	/**
	 * @return the operation
	 */
	public LogicalOperations getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(LogicalOperations operation) {
		this.operation = operation;
	}
}
