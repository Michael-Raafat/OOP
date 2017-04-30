package console;

import java.util.Scanner;

import consolePortal.SystemPortal;
import consolePortal.SystemPortalImp;
import exceptions.UnknownExtension;
import printingService.dbms.PrintingData;
/**
 * Console class.
 * It uses the system to offer the user to enter his inputs.
 * @author Amr
 *
 */
public class Console {
	/**
	 * Main function.
	 * @param args
	 * @throws UnknownExtension
	 */
	public static void main(final String[] args) throws UnknownExtension {
		SystemPortal portal = new SystemPortalImp();
		Scanner scanner = new Scanner(System.in);
		PrintingData.getInstance().printWelcomeScreen();
		String input = scanner.nextLine();
		while (!input.equals("exit")) {
			portal.enterCommand(input);
			input = scanner.nextLine();
		}
		scanner.close();
	}

}
