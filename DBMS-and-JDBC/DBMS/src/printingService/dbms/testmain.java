package printingService.dbms;

import java.util.ArrayList;

import exceptions.UnknownColumnException;
import exceptions.UnknownTableException;
import models.Database;
import models.Datatype;
import models.implementation.DatabaseImp;
import models.implementation.HeaderImp;
import models.implementation.RowImp;
import models.implementation.TableImp;
/**
 * @author Michael.
 *
 */
public class testmain {
	public static void main(String[] args) throws UnknownColumnException {
		ArrayList<Database> cell = new ArrayList<Database>();
		ArrayList<String> cells = new ArrayList<String>();
		ArrayList<String> cells1 = new ArrayList<String>();
		ArrayList<String> cells2 = new ArrayList<String>();
		ArrayList<String> cells3 = new ArrayList<String>();
		cells.add("Marc");
		cells.add("20");
		cells.add("A Bob Saver");
		cells3.add("Michael");
		cells3.add("21");
		cells3.add("A perfect formatter");
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Datatype> types = new ArrayList<Datatype>();
		names.add("Name");
		names.add("Age");
		names.add("Mission");
		types.add(Datatype.VARCHAR);
		types.add(Datatype.INTEGER);
		types.add(Datatype.VARCHAR);
		HeaderImp head = new HeaderImp(names, types);
		DatabaseImp database = new DatabaseImp("Hello");
		TableImp table = new TableImp("works", database, head);
		RowImp row1 = new RowImp(table, cells);
		RowImp row4 = new RowImp(table, cells3);
		try {
			table.addRow(row1);
		} catch (Exception e1) {
			PrintingData.getInstance().printException(e1);
		}
		cells1.add("Amr");
		cells1.add("20");
		cells1.add("An Expert Parser");
		RowImp row2 = new RowImp(table, cells1);
		RowImp row3 = new RowImp(table, cells2);
		cells2.add("Bishoy");
		cells2.add("20");
		cells2.add("A professional Evaluator");
		try {
			table.addRow(row2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			table.addRow(row3);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			table.addRow(row4);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
		database.addTable(table);
		} catch (Exception e) {
			PrintingData.getInstance().printException(new UnknownTableException());
		}
		cell.add(database);
		database = new DatabaseImp("GOAL");
		cell.add(database);
		try {
			database.addTable(table);
			table = new TableImp("zew", database, head);
			database.addTable(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		database = new DatabaseImp("Liverpool");
		cell.add(database);
		PrintingData.getInstance().printTableview(table);
		MenuFormat m = new MenuFormat();
		m.printAllWelcome();
		PrintingData.getInstance().printdatabasesview(cell);
	}
}
