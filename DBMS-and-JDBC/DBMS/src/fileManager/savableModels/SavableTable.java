package fileManager.savableModels;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**.
 * The table object to be saved as XML
 * @author Marc Magdi
 *
 */
@XmlRootElement
public class SavableTable {
	/**.
	 * The table name
	 */
	private String tableName;
	
	/**.
	 * HeaderImp of the table
	 */
	private SavableHeader header;
	
	/**
	 * @return the header
	 */
	public SavableHeader getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	@XmlElement
	public void setHeader(SavableHeader header) {
		this.header = header;
	}

	/**.
	 * Rows Data
	 */
	private List<SavableRow> rows;

	/**
	 * @return the rows
	 */
	public List<SavableRow> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	@XmlElement(name="row")
	public void setRows(List<SavableRow> rows) {
		this.rows = rows;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
    @XmlAttribute(name="table-name")
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
