/**
 * 
 */
package fileManager;

import java.util.ArrayList;
import java.util.List;

import exceptions.UnknownColumnException;
import fileManager.savableModels.SavableDatabase;
import fileManager.savableModels.SavableHeader;
import fileManager.savableModels.SavableRow;
import fileManager.savableModels.SavableTable;
import fileManager.savableModels.TableMetaForDatabase;
import models.Database;
import models.Row;
import models.Table;
import models.implementation.HeaderImp;
import models.implementation.TableImp;

/**
 * @author Marc Magdi
 *
 */
public class XMLObjectAdapter {
	
	/**.
	 * Helper to serializing and deserializing objects
	 */
	private XMLAdapterHelper helper;
	
	/**.
	 * Default constructor
	 * Initialize objects
	 */
	public XMLObjectAdapter() {
		helper = new XMLAdapterHelper();
	}
	
	/**.
	 * Serialize the database and return a savable object
	 * @param db the database to be serialized
	 * @return the serialized object of the database
	 */
	public SavableDatabase getSavableDatabase(Database db) {
		SavableDatabase database = new SavableDatabase();
		database.setDbName(db.getName());
		
		List<TableMetaForDatabase> tablesMeta = helper.getTablesMeta(db);
		database.setTablesData(tablesMeta);
		return database;
	}
	
	/**.
	 * Serialize the table and return a savable object
	 * @param table the table to be serialized
	 * @return the serialized object of the table
	 */
	public SavableTable getSavableTable(Table table) {
		SavableTable savableTable = new SavableTable();
		savableTable.setTableName(table.getName());
		SavableHeader savableHeader = helper.getSavableHeader(table);
		savableTable.setHeader(savableHeader);
		
		List<SavableRow> rows = helper.getSavableRows(table);
		savableTable.setRows(rows);

		return savableTable;
	}
	
	/**.
	 * Get a table object from savable one
	 * @param savTable The savable table to convert to Table
	 * @return a deserialized object of savable table
	 * @throws UnknownColumnException 
	 */
	public Table getTableFromSavable(SavableTable savTable) throws UnknownColumnException {
		HeaderImp header = new HeaderImp(savTable.getHeader().getColName()
				,savTable.getHeader().getColType());
		
		Table table = new TableImp(savTable.getTableName(), null, header);
		List<Row> rows = new ArrayList<Row>();
		if (savTable.getRows() != null)
				rows = helper.getRowsFromSavable(savTable.getRows(), table);	
		
		table.setRows(rows);
		return table;
	}
}