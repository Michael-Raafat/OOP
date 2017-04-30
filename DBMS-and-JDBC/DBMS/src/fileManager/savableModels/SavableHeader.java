package fileManager.savableModels;

import java.util.List;

import javax.xml.bind.annotation.XmlList;


import models.Datatype;

/**.
 * A SavableHeader for the table
 * @author Marc Magdi
 *
 */
public class SavableHeader {
	/**
	 * names of columns.
	 */
	private List<String> colName;
	/**
	 * data type of columns.
	 */
	private List<Datatype> colType;
	/**
	 * @return the colName
	 */
	public List<String> getColName() {
		return colName;
	}
	/**
	 * @param colName the colName to set
	 */
	@XmlList
	public void setColName(List<String> colName) {
		this.colName = colName;
	}
	/**
	 * @return the colType
	 */
	public List<Datatype> getColType() {
		return colType;
	}
	/**
	 * @param colType the colType to set
	 */
	@XmlList
	public void setColType(List<Datatype> colType) {
		this.colType = colType;
	}
	
}
