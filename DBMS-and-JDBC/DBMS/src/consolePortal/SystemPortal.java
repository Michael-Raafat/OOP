package consolePortal;

import java.io.File;
/**
 * System portal is considered as the face of project.
 * The user will send his command or a file and system
 * portal will handle the rest.
 * @author Amr
 *
 */
public interface SystemPortal {
	/**
	 * Takes a command and does the appropriate action with it.
	 * @param command
	 * String containing the command entered by the user.
	 */
	void enterCommand(String command);
	/**
	 * Takes a file then performs each line in it.
	 * Will stop if encountered any error.
	 * @param file
	 * File to be used.
	 */
	void enterBatchFile(File file);
}