package models;

import java.util.List;

import exceptions.UnknownColumnException;

/**
 * 
 * @author Marc Magdi.
 *
 */
public interface Header {
	
	/**
	 * get column name by its index.
	 * @param index
	 * place of this column in table.
	 * @return
	 * name of column.
	 */
	public String getcolumnName(int index);
	
	/**
	 * get column type by its index.
	 * @param index
	 * place of this column in table.
	 * @return
	 * data type of column.
	 */
	public Datatype getcolumnType(int index);
	
	/**
	 * to known how many columns.
	 * @return
	 * no. of columns.
	 */
	public int getSize();
	
	/**.
	 * As a cloneable I am able to get a copy of the object
	 * @return a new copy of the header
	 */
	public Header clone();
	
	/**
	 * Get a list with the columns name
	 * @return return a list with the columns name
	 */
	public List<String> getColumnsName();
	
	/**.
	 * Get a list with the columns type
	 * @return return a list with the columns type
	 */
	public List<Datatype> getColumnsType();
	/**
	 * Adds a new node to the header.
	 * @param columnName
	 * The name of the new column.
	 * @param datatype
	 * Its datatype.
	 * @return
	 * A header with the new column.
	 * @throws UnknownColumnException 
	 */
	Header addNewNode(String columnName, Datatype datatype) throws UnknownColumnException;
	/**
	 * Removes a node from the header.
	 * @param index
	 * index of the node to remove.
	 * @return
	 * A header with the removed column.
	 * @throws UnknownColumnException 
	 */
	Header removeNode(int index) throws UnknownColumnException;
}
