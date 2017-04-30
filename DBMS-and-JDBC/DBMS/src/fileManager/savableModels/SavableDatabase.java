package fileManager.savableModels;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**.
 * The database object to be saved as XML
 * @author Marc Magdi
 *
 */
@XmlRootElement
public class SavableDatabase {
	/**.
	 * The database name to save
	 */
	private String dbName;
	/**.
	 * The tables meta data to save
	 */
	private List<TableMetaForDatabase> tablesData;

	/**
	 * @return the dbName
	 */
	public final String getDbName() {
		return dbName;
	}

	/**
	 * @param dbNameParam the dbName to set
	 */
    @XmlAttribute(name="DatabaseName")
	public final void setDbName(final String dbNameParam) {
		this.dbName = dbNameParam;
	}

	/**
	 * @return the tablesData
	 */
	public final List<TableMetaForDatabase> getTablesData() {
		return tablesData;
	}

	/**
	 * @param tablesDataParam the tablesData to set
	 */
    @XmlElement(name="table-name")
	public final void setTablesData(
			final List<TableMetaForDatabase> tablesDataParam) {
		this.tablesData = tablesDataParam;
	}
}