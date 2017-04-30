package fileManager.savableModels;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class TableMetaForDatabase {
	private String tableName;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
    @XmlValue
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableMD5
	 */
	public String getTableMD5() {
		return tableMD5;
	}

	/**
	 * @param tableMD5 the tableMD5 to set
	 */
    @XmlAttribute
	public void setTableMD5(String tableMD5) {
		this.tableMD5 = tableMD5;
	}

	private String tableMD5;
}