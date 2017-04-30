/**
 * 
 */
package fileManager;

import databaseManagement.DBMSMeta;

/**
 * @author Marc Magdi
 *
 */
public interface ConfigurationFileManager {
	/**.
	 * get the DBMS meta data
	 * @return an object of the DBMS meta data loaded with its properties
	 */
	public DBMSMeta getDBMSMeta();

	/**.
	 * Save the object meta to a file
	 * @param meta the object to be saved
	 */
	public void saveDBMSMeta(DBMSMeta meta);
}
