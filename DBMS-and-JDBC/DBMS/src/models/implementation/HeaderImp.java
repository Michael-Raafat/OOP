package models.implementation;

import java.util.ArrayList;
import java.util.List;

import exceptions.UnknownColumnException;
import models.Datatype;
import models.Header;
/**
 * 
 * @author michael.
 *
 */
public class HeaderImp implements Header, Cloneable {
	/**
	 * names of columns.
	 */
	private List<String> colName;
	/**
	 * data type of columns.
	 */
	private List<Datatype> colType;
	/**
	 * initialize header.
	 * @param colName
	 * list of columns names.
	 * @param colType
	 * list of columns types.
	 * @throws UnknownColumnException 
	 */
	public HeaderImp(final List<String> colName,
			final List<Datatype> colType)
					throws UnknownColumnException {
		this.colName = colName;
		this.colType = colType;
		if (!this.isCorrect()) {
			throw new UnknownColumnException();
		}
	}
	/**
	 * to check validity of header.
	 * @return
	 * true if it is valid.
	 * false if it is invalid header.
	 */
	private boolean isCorrect() {
		return checkColsNameTypeEqualSize() && hasDuplicates();
	}
	
	/**.
	 * Check for the current object columns name list and columns type list for equal size
	 * @return true if the the columns name and size are equal size
	 */
	private boolean checkColsNameTypeEqualSize() {
		return this.colName.size() == this.colType.size();
	}
	
	/**.
	 * Check if the current columns contain any duplicates
	 * @return true if there are no duplicates
	 */
	private boolean hasDuplicates() {
		for (int i = 0; i < this.colName.size(); i++){
			if (this.colName.lastIndexOf(this.colName.get(i)) != i) {
				return false;				
			}
		}
		
		return true;
	}
	
	/**
	 * get column name by its index.
	 * @param index
	 * place of this column in table.
	 * @return
	 * name of column.
	 */
	@Override
	public String getcolumnName(int index) {
		return colName.get(index);
	}
	/**
	 * get column type by its index.
	 * @param index
	 * place of this column in table.
	 * @return
	 * data type of column.
	 */
	@Override
	public Datatype getcolumnType(int index) {
		return colType.get(index);
	}
	/**
	 * to known how many columns.
	 * @return
	 * no. of columns.
	 */
	@Override
	public int getSize() {
		return colName.size();
	}
	
	@Override
	public Header clone() {
		Header header;
		try {
			header = new HeaderImp(new ArrayList<>(this.colName),
					new ArrayList<>(this.colType));
		} catch (UnknownColumnException e) {
			header = null;
		}
		return header;
	}
	@Override
	public List<String> getColumnsName() {
		return this.colName;
	}
	@Override
	public List<Datatype> getColumnsType() {
		return this.colType;
	}
	@Override
	public final Header addNewNode(final String columnName,
			final Datatype datatype) throws UnknownColumnException {
		List<String> modNames = new ArrayList<String>(colName);
		List<Datatype> modTypes = new ArrayList<Datatype>(colType);
		modNames.add(columnName);
		modTypes.add(datatype);
		return new HeaderImp(modNames, modTypes);
	}
	@Override
	public final Header removeNode(final int index) throws UnknownColumnException {
		List<String> modNames = new ArrayList<String>(colName);
		List<Datatype> modTypes = new ArrayList<Datatype>(colType);
		modNames.remove(index);
		modTypes.remove(index);
		return new HeaderImp(modNames, modTypes);
	}
}
