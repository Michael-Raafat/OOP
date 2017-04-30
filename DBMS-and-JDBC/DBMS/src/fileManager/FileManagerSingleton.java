package fileManager;

import java.io.File;
import java.io.IOException;

import databaseManagement.DBMSMeta;
import exceptions.UnknownColumnException;
import exceptions.UnknownExtension;
import fileManager.savableModels.SavableTable;
import models.Database;
import models.Table;
import models.implementation.DatabaseImp;

/**
 * . The main entry point to the FileManager It can perform all basic functions
 *
 * @author Marc Magdi
 *
 */
public final class FileManagerSingleton implements
					FileManager, ConfigurationFileManager {
	/**
	 * . Save Load Manager to help save and load any object
	 */
	private SaveLoadManager saveLoadHelper;
	/**.
	 * The xml object adapter used with same configuration data
	 */
	private XMLObjectAdapter serializer;
	/**.
	 * The factory to get the FileWriter object
	 */
	private FileWriterFactory factory;

	/**.
	 * A singleton object for the File Manager
	 */
	private static FileManagerSingleton fileManager;

	/**.
	 * Default private constructor to make the class singelton
	 * and initialize its instance variables with default data.
	 * @param defaultSavePath the default save path of the databases
	 * @param extension the default extension to save with
	 * @throws UnknownExtension through unkown exception when using
	 * unknown one for saving files
	 */
	private FileManagerSingleton(final String defaultSavePath,
						final SaveExtension extension)
						throws UnknownExtension {
		this.factory = new FileWriterFactoryImp();
		FileWriter fileWriter = factory.getSaveHelper(extension);

		this.saveLoadHelper = new SaveLoadManager(fileWriter, defaultSavePath);
		this.serializer = new XMLObjectAdapter();
		// this.dbmsMeta = this.getDBMSMeta();
		// this.extension = this.dbmsMeta.getExtension();
	}

	public static FileManagerSingleton getInstance() throws UnknownExtension {
		if (fileManager == null) {
			fileManager = new FileManagerSingleton(
					System.getProperty("user.dir"), SaveExtension.XML);
		}
		return fileManager;
	}

	public static FileManagerSingleton getInstance(
			final String defaultSavePath, final SaveExtension extension)
					throws UnknownExtension {
		if (fileManager == null) {
			fileManager = new FileManagerSingleton(defaultSavePath, extension);
		}
		return fileManager;
	}

	public static void setInstance(final String defaultSavePath, final SaveExtension extension) throws UnknownExtension {
		if (fileManager == null) {
			fileManager = new FileManagerSingleton(defaultSavePath, extension);
		} else {
			fileManager.setDefaultSavePath(defaultSavePath);
			fileManager.setDefaultSaveExtension(extension);
		}
	}

	@Override
	public void writeDB(final Database db) {
		this.createRootDirectory();
		// SavableDatabase savDB = serializer.getSavableDatabase(db);
		File databaseDir = saveLoadHelper.getDirectory(db.getName());
		this.saveLoadHelper.createDirectory(databaseDir);
		// this.saveLoadHelper.saveTable(savDB, db.getName());
	}

	/**
	 * . Create the root directory to all databases
	 */
	private void createRootDirectory() {
		File dir = saveLoadHelper.getDirectory("");
		this.saveLoadHelper.createDirectory(dir);
	}

	@Override
	public void writeTable(final Table table) throws IOException {
		String fileName = this.getTableFileNameWithDatabase(table.getName(), table.getDatabase().getName());
		File dtdFile = saveLoadHelper.getFile(fileName, "dtd");
		SavableTable savableTable = serializer.getSavableTable(table);
		this.saveLoadHelper.saveTable(table, fileName);
		this.saveLoadHelper.createDTDForTable(savableTable, dtdFile);
		// TODO update the database tables file
	}

	@Override
	public Database readDB(final String name) {
		Database db = new DatabaseImp(name);
		File file = saveLoadHelper.getDirectory(name);

		if (saveLoadHelper.directoryExist(file)) {
			return db;
		}

		return null;
	}

	@Override
	public Table readTable(final String tableName, final String dbName) throws UnknownColumnException {
		String fileName = this.getTableFileNameWithDatabase(tableName, dbName);
		return saveLoadHelper.loadTable(fileName);
	}

	/**
	 * . Get the file name of a table
	 *
	 * @param table
	 *            the table to get its file name
	 * @return the full name of the table with its database
	 */
	private String getTableFileNameWithDatabase(
			final String tableName, final String dbName) {
		String fileName = dbName
				+ System.getProperty("file.separator") + tableName;

		return fileName;
	}

	@Override
	public void deleteDB(final String name) {
		File dir = saveLoadHelper.getDirectory(name);
		saveLoadHelper.deleteFile(name);
		saveLoadHelper.deleteFolder(dir);
	}

	@Override
	public void deleteTable(final String tableName, final String databaseName) {
		String fileName = databaseName
				+ System.getProperty("file.separator") + tableName;
		File dtdFile = saveLoadHelper.getFile(fileName, "dtd");
		saveLoadHelper.deleteFile(fileName);
		saveLoadHelper.deleteFile(dtdFile);
	}

	@Override
	public Boolean checkTableExist(final String tableName
			, final String databaseName) {
		String fileName = databaseName
				+ System.getProperty("file.separator") + tableName;

		File file = saveLoadHelper.getFile(fileName, this.saveLoadHelper.getSaveExtension());
		return saveLoadHelper.fileExist(file);
	}

	@Override
	public Boolean checkDatabaseExist(final String databaseName) {
		File file = saveLoadHelper.getDirectory(databaseName);
		return saveLoadHelper.directoryExist(file);
	}

	@Override
	public DBMSMeta getDBMSMeta() {
		return this.saveLoadHelper.getDBMSMeta();
	}

	@Override
	public void saveDBMSMeta(final DBMSMeta meta) {
		// this.saveLoadHelper.saveObject(meta, "_DBMSMeta");
	}

	@Override
	public void setDefaultSavePath(final String path) {
		this.saveLoadHelper.setDefaultFilesPath(path);
	}

	@Override
	public void setDefaultSaveExtension(final SaveExtension extension) {
		FileWriter fileWriter = null;
		try {
			fileWriter = factory.getSaveHelper(extension);
			this.saveLoadHelper.setFileWriter(fileWriter);
		} catch (UnknownExtension e) {
			e.printStackTrace();
		}
	}
}
