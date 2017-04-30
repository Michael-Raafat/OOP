package logicalComponents;

public class LogicalConstant extends Condition {
	private boolean value;
	/**
	 * Constructor of 
	 * @param val
	 */
	public LogicalConstant(boolean val) {
		setValue(val);
	}
	/**
	 * @return the value
	 */
	public boolean isTrue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(boolean newValue) {
		this.value = newValue;
	}
	
}
