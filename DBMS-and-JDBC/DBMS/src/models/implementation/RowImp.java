package models.implementation;

import java.util.ArrayList;
import java.util.List;

import exceptions.WrongDatatypeInputException;
import models.Datatype;
import models.Row;
import models.Table;
import parser.StringToData;
/**
 * 
 * @author michael.
 *
 */
public class RowImp implements Row {
    /**
     * cells containing data.
     */
	List<String> cells;
	/**
	 * the table which has this row.
	 */
	Table table;
	/**
	 * initialize row.
	 * @param table
	 * row belong to this table.
	 * @param cells
	 * list of cells in the row.
	 */
	public RowImp(Table table, List<String> cells) {
		this.cells = cells;
		this.table = table;
	}
	
	@Override
	public String getCellDataByIndex(int index) {
		return cells.get(index);
	}

	@Override
	public void editCellDataByIndex(int index,
			String newValue) throws WrongDatatypeInputException {

		Datatype columnDatatype = this.table.getHeader().getcolumnType(index);
		switch(columnDatatype) {
		case VARCHAR:
			break;
		case INTEGER:
				StringToData.stringToInteger(newValue);
				break;
		case FLOAT:
			StringToData.stringToFloat(newValue);
			break;
		case DATE:
			StringToData.stringToDate(newValue);
			break;
		}
		
		cells.set(index, newValue);

		
		//		try{
//			Integer.valueOf(newValue);
//		}catch (Exception e) {
//			if (table.getHeader().getcolumnType(
//					index).equals(Datatype.VARCHAR)) {
//				cells.set(index, newValue);
//			} else {
//				throw new WrongDatatypeInputException();
//			}
//		}
//		if (table.getHeader().getcolumnType(
//				index).equals(Datatype.INTEGER)) {
//			cells.set(index, newValue);
//		} else {
//			throw new WrongDatatypeInputException();
//		}
	}

	/**
	 * @return the cells
	 */
	public List<String> getCells() {
		return cells;
	}

	/**
	 * @param cells the cells to set
	 */
	public void setCells(List<String> cells) {
		this.cells = cells;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}
	@Override
	public final boolean equals(final Object row) {
		if (row instanceof Row) {
			return this.cells.equals(((Row) row).getCells());
		} else {
			return false;
		}
	}

	@Override
	public final Row addCell(final String value) {
		List<String> modCells = new ArrayList<String>(cells);
		modCells.add(value);
		return new RowImp(table, modCells);
	}

	@Override
	public final Row removeCell(final int index) {
		List<String> modCells = new ArrayList<String>(cells);
		modCells.remove(index);
		return new RowImp(table, modCells);
	}
}
