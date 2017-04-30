package models;

import java.util.List;

import exceptions.WrongDatatypeInputException;

public interface Row {
	
	/**
	 * Gets the data inside a specific cell from its index
	 * @param index of the cell in the row
	 * @return data inside the cell
	 */
	String getCellDataByIndex(int index);

	/**
	 * Edits a value of a specific cell in the row
	 * @param index of the cell in the row
	 * @param newValue of the cell in the row
	 * @throws WrongDatatypeInputException 
	 */
	void editCellDataByIndex(int index, String newValue) throws WrongDatatypeInputException;
	
	/**.
	 * Set the row value by the given cells
	 * @param cells to set
	 */
	/**
	 * @return the cells
	 */
	public List<String> getCells();

	/**
	 * @param cells the cells to set
	 */
	public void setCells(List<String> cells);

	/**
	 * @return the table
	 */
	public Table getTable();

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table);
	/**
	 * Checks if this row cells is equal another ons.
	 * @param row
	 * The row to compare with.
	 * @return
	 * True if they are equal.
	 */
	boolean equals(Object row);
	/**
	 * Adds a new cell to a row.
	 * @param value
	 * The value to add.
	 * @return
	 * A row with the updated cell.
	 */
	Row addCell(String value);
	/**
	 * Removes a cell from a row.
	 * @param index
	 * The index of the cell to remove.
	 * @return
	 * A row with the removed cell.
	 */
	Row removeCell(int index);
}
