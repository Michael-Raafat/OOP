package fileManager.savableModels;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import fileManager.XMLRowAdapter;

/**.
 * The savable cell of the row
 * @author Marc Magdi
 *
 */
@XmlJavaTypeAdapter(XMLRowAdapter.class)
public class SavableCell {
	/**.
	 * The value of the cell
	 */
	private String val;

	/**.
	 * The Data type of the cell (Integer or varchar)
	 */
	private String dataType;

	/**
	 * @return the val
	 */
	public final String getVal() {
		return val;
	}

	/**
	 * @param valParam the valParam to set
	 */
	public final void setVal(final String valParam) {
		this.val = valParam;
	}

	/**
	 * @return the dataType
	 */
	public final String getDataType() {
		return dataType;
	}

	/**
	 * @param dataTypeParam the dataType to set
	 */
	public final void setDataType(final String dataTypeParam) {
		this.dataType = dataTypeParam;
	}

	/**
	 * @return the columnName
	 */
	public final String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnNameParam the columnName to set
	 */
	public final void setColumnName(final String columnNameParam) {
		this.columnName = columnNameParam;
	}

	/**.
	 * The Column Name of the cell
	 */
	private String columnName;

}
