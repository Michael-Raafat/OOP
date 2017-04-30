package fileManager;

import java.util.ArrayList;
import java.util.List;

import fileManager.savableModels.SavableCell;
import fileManager.savableModels.SavableHeader;
import fileManager.savableModels.SavableRow;
import fileManager.savableModels.TableMetaForDatabase;
import models.Database;
import models.Datatype;
import models.Header;
import models.Row;
import models.Table;
import models.implementation.RowImp;

/**.
 * A class to help serializing and deserializing objects
 * @author Marc Magdi
 *
 */
public class XMLAdapterHelper {

	/**.
	 * Build the TableMetaForDatabase list to be saved
	 * @param db the database to get the meta from
	 * @return the list of tableMeta objects
	 */
	protected List<TableMetaForDatabase> getTablesMeta(Database db) {
		if (db.getTables() == null) return null;

		List<Table> tables = db.getTables();
		List<TableMetaForDatabase> tablesMeta = new ArrayList<TableMetaForDatabase>(tables.size());
		for (int i = 0; i < tables.size(); i++) {
			TableMetaForDatabase table = new TableMetaForDatabase();
			table.setTableName(tables.get(i).getName());
			table.setTableMD5("");

			tablesMeta.add(table);
		}

		return tablesMeta;
	}

	/**.
	 * Get a list of savableRows from a specific table
	 * @param table the table to get rows from
	 * @return a serialized version of the rows
	 */
	protected List<SavableRow> getSavableRows(Table table) {
		List<Row> rows = table.getRows();
		List<SavableRow> savableRows = new ArrayList<SavableRow>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			List<SavableCell> cells = this.getSavableCells(rows.get(i), table);
			SavableRow savableRow = new SavableRow();
			savableRow.setCells(cells);
			savableRows.add(savableRow);
		}
		
		return savableRows;
	}
	
	/**.
	 * Serialize one row as a list of savable cells
	 * @param row the row to serialize
	 * @param table the table to get the column type and name from
	 * @return a serialized version of the row
	 */
	private List<SavableCell> getSavableCells(Row row, Table table) {
		List<String> cells = row.getCells();
		List<SavableCell> savableCells = new ArrayList<SavableCell>(cells.size());
		for (int i = 0; i < cells.size(); i++) {
			SavableCell cell = new SavableCell();
			cell.setColumnName(table.getHeader().getcolumnName(i));
			cell.setDataType(table.getHeader().getcolumnType(i).toString());
			cell.setVal(cells.get(i));
			savableCells.add(cell);
		}
		
		return savableCells;
	}
	
	protected SavableHeader getSavableHeader(Table table) {
		SavableHeader savHeader = new SavableHeader();
		Header header = table.getHeader();
		List<String> colNames = new ArrayList<String>(header.getSize());
		List<Datatype> colTypes = new ArrayList<Datatype>(header.getSize());
		
		for (int i = 0; i < header.getSize(); i++) {
			String colName = header.getcolumnName(i);
			Datatype colType = header.getcolumnType(i);
			colNames.add(colName);
			colTypes.add(colType);
		}
		
		savHeader.setColName(colNames);
		savHeader.setColType(colTypes);
		return savHeader;
	}
		
	/**.
	 * Create a list of Rows from a savable rows
	 * @param savableRows the savableRows to get the rows from
	 * @return list of Rows
	 */
	protected List<Row> getRowsFromSavable(List<SavableRow> savableRows, Table table) {
		List<Row> rows = new ArrayList<Row>(savableRows.size());
		for (int i = 0; i < savableRows.size(); i++) {
			List<String> values = new ArrayList<String>();
			List<SavableCell> cells = savableRows.get(i).getCells();
			for (int j = 0; j < cells.size(); j++) {
				values.add(cells.get(j).getVal());
			}
			
			Row row = new RowImp(table, values);
			rows.add(row);
		}
		return rows;
	}
}
