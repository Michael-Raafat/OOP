/**
 * 
 */
package databaseManagement;

import java.util.Properties;

import exceptions.UnknownExtension;
import fileManager.ConfigurationFileManager;
import fileManager.FileManagerSingleton;
import fileManager.SaveExtension;

/**
 * @author Marc Magdi
 *
 */
public class ConfigurationManager {
	/**.
	 * Containing the meta of the DBMS
	 */
	private DBMSMeta meta;

	private ConfigurationFileManager fileManager;
	
	public ConfigurationManager() throws UnknownExtension {
		this.meta = new DBMSMeta();
		this.fileManager = FileManagerSingleton.getInstance(System.getProperty("user.dir"), SaveExtension.XML);
	}
	
	public Properties getConfigProperties() {
		Properties properties = new Properties();
		meta = this.fileManager.getDBMSMeta();
		properties.put("username", meta.getUserName());
		properties.put("password", meta.getPassword());
		properties.put("path", meta.getDefaultPath());
		return properties;
	}
	
	/**
	 * checks that the config file is found in its default location.
	 * and that the file contains username, password and Databases path.
	 * @return true if file is found and valid else false.
	 */
	public boolean foundValidConfigurationFile() {
		return this.fileManager.getDBMSMeta() != null;
	}
}