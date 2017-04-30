package logicalComponents;

import models.Datatype;

public class RelationalOperandType {
	private String operand;
	private boolean variable;
	private Datatype valueDataType;
	public RelationalOperandType(String operand,
			boolean variable,
			Datatype type) {
		this.operand = operand;
		this.valueDataType = type;
		this.variable = variable;
	}
	public final String getOperand() {
		return operand;
	}
	public final boolean isVariable() {
		return variable;
	}
	/**
	 * @return the valueDataType
	 */
	public Datatype getValueDataType() {
		return valueDataType;
	}
}
