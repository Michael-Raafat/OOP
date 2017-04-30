package printingService.dbms;


import java.io.IOException;
import java.util.ArrayList;

import exceptions.DatabaseAlreadyExist;
import exceptions.TableAlreadyExist;
import exceptions.UnknownColumnDatatypeException;
import exceptions.UnknownColumnException;
import exceptions.UnknownDatabaseException;
import exceptions.UnknownTableException;
import exceptions.WrongDatatypeInputException;
import exceptions.WrongSyntaxException;
import models.Database;
import models.Table;
/**
 * @author michael.
 * It is a singleton class.
 */
public final class PrintingData {
	/**
	 * one single log.
	 */
	private static PrintingData print;
	/**
	 private constructor to prevent instantiating objects from
	 * it.
	 */
	private PrintingData() {
	}
	/**
	 * to get instance.
	 * @return
	 * this single log.
	 */
	public static PrintingData getInstance() {
		if (print == null) {
			print = new PrintingData();
		}
		return print;
	}
	/**
	 * print table.
	 * @param table
	 * table wanted to be printed.
	 */
	public void printTableview(final Table table) {
		TableviewFormat format = new TableviewFormat(table);
		format.printFullBorderLine(table, table.getHeader().getSize());
		format.printFullHeaderLine(table, table.getHeader().getSize());
		format.printFullBorderLine(table, table.getHeader().getSize());
		for(int i = 0; i < table.getRows().size(); i++) {
			format.printFullElementLine(table,
					table.getHeader().getSize(), i);
		}
		format.printFullBorderLine(table, table.getHeader().getSize());
	}
	/**
	 * printing databases.
	 * @param databases
	 */
	public void printdatabasesview(final ArrayList<Database> databases) {
		System.out.println("Our System :");
		TableviewFormat format = new TableviewFormat(databases);
		format.printFullBorderLine(databases);
		format.printFullHeaderLine(databases);
		format.printFullBorderLine(databases);
		int size = format.maxDatabase(databases);
		for(int i = 0; i < size; i++) {
			format.printFullElementLine(databases, i);
		}
		format.printFullBorderLine(databases);
	}
	/**
	 * print table including tables in database.
	 * @param database
	 * database to be printed.
	 */
	public void printDatabaseTablesView(final Database database) {
		TableviewFormat format = new TableviewFormat(database);
		format.printSingleColumnBorder();
		format.printFullSingleHeadLine(
				"Tables in " + database.getName());
		format.printSingleColumnBorder();
		for (int i = 0; i < database.getTables().size(); i++) {
			format.printSingleColumnElement(
			database.getTables().get(i).getName());
		}
		format.printSingleColumnBorder();
	}
	/**
	 * printing errors.
	 * @param e
	 * exceptions.
	 */
	public void printException(final Exception e) {
		if (e.getClass().equals(UnknownTableException.class)) {
		System.out.println("Table Error: Using an unknown table, mispelled "
				+ "or may be not create yet");
		} else if (e.getClass().equals(DatabaseAlreadyExist.class)) {
			System.out.println("Database Error: Try to create or redefine an "
					+ "already defined database");
		} else if (e.getClass().equals(TableAlreadyExist.class)) {
			System.out.println("Table Error: Try to create or redefine an "
					+ "already defined table");
		} else if (e.getClass().equals(UnknownDatabaseException.class)) {
		System.out.println("Database Error: Using of unknown database, mispelled "
				+ "or may be not create yet");
		} else if (e.getClass().equals(UnknownColumnException.class)) {
		System.out.println("Column Error: Column doesn't exist.");
		} else if (e.getClass().equals(UnknownColumnDatatypeException.class)) {
	    System.out.println("Column Error: Datatypes isn't supported or may be a wrong one?");
		} else if (e.getClass().equals(WrongDatatypeInputException.class)) {
		System.out.println("Input Error: Input datatypes mismatch, check the table definition");
		} else if (e.getClass().equals(WrongSyntaxException.class)) {
		System.out.println("Syntax error : please make sure you followed sql command syntax");
		} else if (e.getClass().equals(IOException.class)) {
			System.out.println("Hard error : something is wrong while writing or reading files.");

		} else {
			System.out.println("System error, please try again in a few moments");
		}
	}
	/**
	 * print All databases names in system.
	 * @param databases
	 * list of databases.
	 */
	public void printExistingDatabases(
			final ArrayList<Database> databases) {
		TableviewFormat format = new TableviewFormat(databases,
				databases.size());
		format.printSingleColumnBorder();
		format.printFullSingleHeadLine("Available Databases");
		format.printSingleColumnBorder();
		for(int i = 0; i < databases.size(); i++) {
			format.printSingleColumnElement(
					databases.get(i).getName());
		}
		format.printSingleColumnBorder();
	}
	/**
	 * printing WELCOME.
	 */
	public void printWelcomeScreen() {
		MenuFormat menu = new MenuFormat();
		menu.printAllWelcome();
	}
}
