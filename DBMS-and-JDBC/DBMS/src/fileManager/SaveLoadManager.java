package fileManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import databaseManagement.DBMSMeta;
import exceptions.UnknownColumnException;
import fileManager.savableModels.SavableTable;
import models.Table;

/**.
 * 
 * @author Marc Magdi
 *
 */
public class SaveLoadManager {
	/**.
	 * DTD Generator helper
	 */
	private DTDHelper dtdHelper;

	private XMLFileWriter xmlHelper;

	/**.
	 * The object responsible for writing and reading all data
	 */
	private FileWriter fileWriter;

	/**.
	 * Default path for saving the databases and the files
	 */
	private String defaultFilesPath;
	/**
	 * @param defaultFilesPath the defaultFilesPath to set
	 */
	public void setDefaultFilesPath(final String defaultFilesPath) {
		this.defaultFilesPath = defaultFilesPath;
	}


	/**.
	 * Default Constructor
	 */
	public SaveLoadManager(final FileWriter fileWriter, final String defaultFilesPath) {
		this.dtdHelper = new DTDHelper();
		this.fileWriter = fileWriter;
		this.defaultFilesPath = defaultFilesPath;
		this.xmlHelper = new XMLFileWriter();
//		this.defaultFilesPath = System.getProperty("user.dir"); // current projectp path
	}
	
	public final void saveTable(final Table table, final String fileName) {
		File file = this.getFile(fileName, this.fileWriter.getExtension());
		this.fileWriter.saveTable(table, file);
	}
	
	/**.
	 * Create a DTD file for the Class Table
	 * @param table the table to create the DTD File;
	 * @param file the file to write to
	 * @throws IOException 
	 */
	public void createDTDForTable(final SavableTable table, final File file) throws IOException {
		StringBuilder data = new StringBuilder();
		file.createNewFile();
		PrintWriter out = new PrintWriter(file);
		// add main fields
		data.append(dtdHelper.getFirstStaticPart());
		data.append(dtdHelper.getElementsForColumns(table));
		data.append("\n"); // add all fields
		
		data.append(dtdHelper.getAttributes(table));
		out.print(data.toString());
		
		out.close();
	}
	
	/**.
	 * Create Directory of the given file
	 * @param dir the directory to create
	 */
	public void createDirectory(final File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	/**.
	 * Return a full file path of the given extension and file name
	 * @param name the file name to add to full path
	 * @param extension the extension to add to the file
	 * @return
	 */
	public File getFile(final String name, final String extension) {
		String path = this.getDirectory(name) + "." + extension;
		return new File(path);
	}
	
	/**.
	 * Create Directory of the given file
	 * @param name the directory name to return a full directory to
	 */
	public File getDirectory(final String name) {
		String path = this.defaultFilesPath
				+ System.getProperty("file.separator")
				+ "Databases"
				+ System.getProperty("file.separator")
				+ name;
		return new File(path);
	}
	
	/**.
	 * Read
	 * @param file the file to read from
	 * @return a savable object of the table to read
	 * @throws UnknownColumnException 
	 */
	public Table loadTable(final String fileName) throws UnknownColumnException {
		File file = this.getFile(fileName, this.fileWriter.getExtension());	
		return this.fileWriter.loadTable(file);
	}
	
	/**.
	 * Check if a given file object is a directory and exist
	 * @param file the file to check
	 * @return return true if the given file object is a directory
	 */
	public Boolean directoryExist(final File file) {
		return file.isDirectory() && file.exists();
	}
	
	/**.
	 * Delete a specified file by its name and extension
	 * @param file the file to be deleted
	 */
	public void deleteFile(final String name) {
		File file = this.getFile(name, this.fileWriter.getExtension());
		this.deleteFile(file);
	}
	
	/**.
	 * Delete a specified file
	 * @param file the file to be deleted
	 */
	public void deleteFile(final File file) {
		file.delete();
	}
	
	/**.
	 * Delete a specified folder
	 * @param file the folder to be deleted
	 */
	public void deleteFolder(final File file) {
		String[]entries = file.list();
		for(String s: entries){
		    File currentFile = new File(file.getPath(), s);
		    currentFile.delete();
		}
		
		file.delete();
	}
	
	/**.
	 * Check if a file exist and it's a file
	 * @param file the file to check
	 * @return true if it's a file and it exist
	 */
	public Boolean fileExist(final File file) {
		return file.exists() && file.isFile();
	}

	/**.
	 * Get the dbms meta object
	 * @return DBMS meta object containing the system data.
	 */
	public final DBMSMeta getDBMSMeta() {
		File file = this.getFile("_DBMSMeta", "xml");
		return this.xmlHelper.loadMeta(file);
	}

	/**.
	 * Update the file writer object
	 * @param fileWriter the new file writer to set
	 */
	public final void setFileWriter(final FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	/**.
	 * Get the current saving extension
	 * @return the current saving extension as string
	 */
	protected final String getSaveExtension() {
		return this.fileWriter.getExtension();
	}

}
