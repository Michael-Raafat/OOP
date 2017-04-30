package printingService.dbms;

import java.util.ArrayList;


import models.Database;
import models.Table;
/**
 *
 * @author michael.
 */
public class TableviewFormat {
	/**
	 * length of each column.
	 */
	private ArrayList<Integer> limit;
	/**
	 * initialize of format using table.
	 * @param table
	 * table to be printed.
	 */
	public TableviewFormat(final Table table) {
		limit = new ArrayList<Integer>();
		this.updateLimit(table);
	}
	/**
	 * initialize of format using database.
	 * @param database
	 * database to be printed.
	 */
	public TableviewFormat(final Database database) {
		limit = new ArrayList<Integer>();
		this.updateLimit(database);
	}
	/**
	 * initialize of format
	 * using list of databases to
	 * print names of database in system.
	 * @param databases
	 * Available databases in system.
	 */
	public TableviewFormat(final ArrayList<Database> databases) {
		limit = new ArrayList<Integer>();
		this.updateLimit(databases);
	}
	/**
	 * initialize of format
	 * using list of databases
	 * to print whole system.
	 * @param databases
	 * Available databases in system.
	 * @param size
	 * length of databases list.
	 */
	public TableviewFormat(
			final ArrayList<Database> databases, final int size) {
		limit = new ArrayList<Integer>();
		this.updateLimit(databases, size);
	}
	/**
	 * print a complete border line.
	 * @param table
	 * table to be printed.
	 * @param size
	 * number of its columns.
	 */
	public final void printFullBorderLine(
			final Table table, final int size) {
		for (int i = 0; i < size; i++) {
			this.printBorder(i);
		}
		this.printEndBorder();
	}
	/**
	 * print border of databases table.
	 * @param databases
	 * list of databases to be printed.
	 */
	public final void printFullBorderLine(
			final ArrayList<Database> databases) {
		for (int i = 0; i < databases.size(); i++) {
			this.printBorder(i);
		}
		this.printEndBorder();
	}
	/**
	 * print a complete head line.
	 * @param table
	 * table to be printed.
	 * @param size
	 * number of its columns.
	 */
	public final void printFullHeaderLine(
			final Table table, final int size) {
		for (int i = 0; i < size; i++) {
			String temp = table.getHeader().getcolumnName(i);
			this.printSideBorder();
			this.printElement(temp);
			this.printFormatSpace(temp.length() + 1, i);
		}
		this.printEndSideBorder();
	}
	/**
	 * print header consists of databases names.
	 * @param databases
	 * header of databases table.
	 */
	public final void printFullHeaderLine(
			final ArrayList<Database> databases) {
		for (int i = 0; i < databases.size(); i++) {
			String temp = databases.get(i).getName();
			this.printSideBorder();
			this.printElement(temp);
			this.printFormatSpace(temp.length() + 1, i);
		}
		this.printEndSideBorder();
	}
	/**
	 * print a complete row.
	 * @param table
	 * table to be printed.
	 * @param size
	 * number of its columns.
	 * @param index
	 * number of chosen row.
	 */
	public final void printFullElementLine(
			final Table table, final int size, final int index) {
		for (int i = 0; i < size; i++) {
			String temp = table.getRows().get(
					index).getCellDataByIndex(i);
			this.printSideBorder();
			this.printElement(temp);
			this.printFormatSpace(temp.length() + 1, i);
		}
		this.printEndSideBorder();
	}
	/**
	 * prints tables of system , each table under its database.
	 * @param databases
	 * databases of system
	 * @param index
	 * index of tables in each database.
	 */
	public final void printFullElementLine(
			final ArrayList<Database> databases, final int index) {
		for (int i = 0; i < databases.size(); i++) {
			if (databases.get(i).getTables().size() > index) {
			String temp = databases.get(i).getTables().get(
					index).getName();
			this.printSideBorder();
			this.printElement(temp);
			this.printFormatSpace(temp.length() + 1, i);
			} else {
				this.printSideBorder();
				this.printFormatSpace(0, i);
			}
		}
		this.printEndSideBorder();
	}
	/**
	 * to print header.
	 * for table of tables in this database.
	 * @param name
	 * head of table.
	 */
	public final void printFullSingleHeadLine(final String name){
		String temp = name;
		this.printSideBorder();
		this.printElement(temp);
		this.printFormatSpace(temp.length() + 1, 0);
		this.printEndSideBorder();
	}
	/**
	 * gets maximum length of string of databases.
	 * @param databases
	 * list of databases.
	 * @return length of maximum name of databases.
	 */
	public final int maxDatabase(final ArrayList<Database> databases) {
		int max = 0;
		for (int i = 0; i < databases.size(); i++) {
			max = Math.max(max, databases.get(
					i).getTables().size());
		}
		return max;
	}
	/**
	 * to print border of table of database tables.
	 * @param database
	 */
	public final void printSingleColumnBorder() {
		this.printBorder(0);
		this.printEndBorder();
	}
	/**
	 * to print single column content.
	 * @param element
	 * element to be printed.
	 */
	public final void printSingleColumnElement(final String element) {
		this.printSideBorder();
		this.printElement(element);
		this.printFormatSpace(element.length() + 1, 0);
		this.printEndSideBorder();
	}
	/**
	 * to update column length.
	 * @param table
	 * table to be printed.
	 */
	private void updateLimit(final Table table) {
		for (int j = 0; j < table.getHeader().getSize(); j++) {
			int max = table.getHeader().getcolumnName(j).length();
			for (int i = 0; i < table.getRows().size(); i++) {
				max = Math.max(max,
						String.valueOf(table.getRows(
						).get(i).getCellDataByIndex(
								j)).length());
			}
			this.limit.add(max + 2);
		}
	}
	/**
	 * set columns size.
	 * for database tables.
	 * @param database
	 * database chosen.
	 */
	public final void updateLimit(final Database database) {
		String temp = "Tables in " + database.getName();
		int max = temp.length();
		for (int i = 0; i < database.getTables().size(); i++) {
			max = Math.max(
			max, database.getTables().get(i).getName().length());
		}
		this.limit.add(max + 2);
	}
	/**
	 * set columns size.
	 * for tables in databases of system.
	 * @param databases
	 * list of databases.
	 */
	public final void updateLimit(final ArrayList<Database> databases) {
		for (int j = 0; j < databases.size(); j++) {
			int max = databases.get(j).getName().length();
			for (int i = 0; i < databases.get(
					j).getTables().size(); i++) {
				max = Math.max(
					max, databases.get(j).getTables(
						).get(i).getName().length());
			}
			this.limit.add(max + 2);
		}
	}
	/**
	 * set columns length.
	 * for table of databases in system.
	 * @param databases
	 * list databases to be shown
	 * @param size
	 * number of databases.
	 */
	public final void updateLimit(
			final ArrayList<Database> databases, final int size) {
		int max = ("Available databases").length();
		for (int i = 0; i < size; i++) {
			max = Math.max(
			max, databases.get(i).getName().length());
		}
		this.limit.add(max + 2);
	}
	/**
	 * print border of each column.
	 * @param index
	 * index of limit list to know length of column.
	 */
	private void printBorder(final int index){
		System.out.print("+");
		for(int i = 0; i < limit.get(index); i++) {
			System.out.print("-");
		}
	}
	/**
	 * print last character in border line.
	 */
	private void printEndBorder(){
		System.out.println("+");
	}
	/**
	 * print side border of each row.
	 */
	private void printSideBorder(){
		System.out.print("|");
	}
	/**
	 * print element.
	 * @param element
	 * element should be printed.
	 */
	private void printElement(final String element) {
		System.out.print(" " + element);
	}
	/**
	 * printing spaces.
	 * to reach limit length of each column.
	 * @param index
	 * number of characters taken to continue.
	 * limit - index = number of extra spaces.
	 * @param temp
	 * starting index.
	 */
	private void printFormatSpace(final int temp, final int index) {
		for (int i = temp; i < limit.get(index); i++) {
			System.out.print(" ");
		}
	}
	/**
	 * print last character in row.
	 * to end any line containing elements.
	 */
	private void printEndSideBorder(){
		System.out.println("|");
	}
}
