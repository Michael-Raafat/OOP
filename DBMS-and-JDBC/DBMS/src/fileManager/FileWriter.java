/**
 * 
 */
package fileManager;

import java.io.File;

import exceptions.UnknownColumnException;
import models.Table;

/**
 * @author Marc Magdi
 *
 */
public interface FileWriter {

	/**.
	 * Save the given table to a file
	 * @param table the table to be saved
	 * @param file the file to save to
	 */
	public void saveTable(Table table, File file);

	/**.
	 * Read
	 * @param file the file to read from
	 * @return a table object from the file
	 * @throws UnknownColumnException when select an invalid table
	 */
	public Table loadTable(File file) throws UnknownColumnException;

	/**.
	 * Get a the extension used to save the file
	 * @return the extension to save and load with as a string
	 */
	public String getExtension();
}
