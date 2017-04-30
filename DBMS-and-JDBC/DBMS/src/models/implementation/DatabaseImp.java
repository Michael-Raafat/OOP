package models.implementation;

import java.util.ArrayList;
import java.util.List;
import exceptions.UnknownTableException;
import models.Database;
import models.Header;
import models.Table;

/**
 * 
 * @author michael.
 *
 */
public class DatabaseImp implements Database {
	/**
	 * tables in this database.
	 */
	private List<Table> tables;
	/**
	 * database name.
	 */
	private String name;
	
	/**
	 * initialize database.
	 * @param name
	 * name of our database.
	 */
	public DatabaseImp(String name) {
		tables = new ArrayList<Table>();
		this.name = name; 
	}

	@Override
	public Table getTableByName(String tableName) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName(
					).equals(tableName)) {
				return tables.get(i);
			}
		}
		return null;
	}

	@Override
	public void addTable(Table table) throws UnknownTableException {
		for (int i = 0; i < tables.size(); i++) {
			if(table.getName().equals(tables.get(i).getName())) {
				throw new UnknownTableException(
				"There is already a table has this name");
			}
		}
		tables.add(table);
	}

	@Override
	public void removeTableByName(String tableName) throws UnknownTableException {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName(
					).equals(tableName)) {
				tables.remove(i);
				return;
			}
		}
		
		// not found
		throw new UnknownTableException();
	}


	/**
	 * @return the tables
	 */
	public List<Table> getTables() {
		return tables;
	}

	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Table createTable(String tableName, Header header) {
		Table table = new TableImp(tableName, (Database)this, header);
		this.tables.add(table);
		return table;
	}
}
