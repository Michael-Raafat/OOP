package consolePortal;

import java.io.File;
import java.util.Scanner;

import databaseManagement.DatabaseEngine;
import databaseManagement.DatabaseEngineImplementation;
import exceptions.UnknownExtension;
import models.Table;
import parser.Parser;
import parser.ParserImplementation;
import printingService.dbms.PrintingData;
/**
 * Implementation of the system portal interface.
 * @author Amr
 *
 */
public class SystemPortalImp implements SystemPortal {
	/**
	 * The parser the the system will use.
	 */
	private Parser parser;
	/**
	 * The engine interface that will perform the actions.
	 */
	private DatabaseEngine databaseEngine;
	/**
	 * Logger.
	 */
	private PrintingData logger;
	/**
	 * Constructor.
	 * @throws UnknownExtension 
	 */
	public SystemPortalImp() throws UnknownExtension {
		parser = new ParserImplementation();
		databaseEngine = new DatabaseEngineImplementation();
		logger = PrintingData.getInstance();
	}
	@Override
	public final void enterCommand(final String command) {
		try {
			Object table = databaseEngine.performQuery(
					parser.parseCommand(command));
			if (table instanceof Table) {
				logger.printTableview((Table) table);
			}
		} catch (Exception e) {
		   PrintingData.getInstance().printException(e);
		}
	}

	@Override
	public final void enterBatchFile(final File file) {
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
					this.enterCommand(scanner.nextLine());
			}
		} catch (Exception e) {
			PrintingData.getInstance().printException(e);
		}
	}
}
