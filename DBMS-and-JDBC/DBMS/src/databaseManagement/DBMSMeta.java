/**
 * 
 */
package databaseManagement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fileManager.SaveExtension;

/**
 * @author Marc Magdi
 *
 */
@XmlRootElement
public class DBMSMeta {
	/**.
	 * The user name to log in to the DBMS
	 */
	private String userName;
	/**.
	 * The password to log in to the DBMS
	 */
	private String password;
	/**.
	 * The extension of saving all tables and databases files
	 */
	private SaveExtension extension;
	/**.
	 * The default path to save databases
	 */
	private String defaultPath;
	/**
	 * @return the defaultPath
	 */
	public String getDefaultPath() {
		return defaultPath;
	}
	/**
	 * @param defaultPath the defaultPath to set
	 */
	public void setDefaultPath(String defaultPath) {
		this.defaultPath = defaultPath;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	@XmlElement
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the extension
	 */
	public SaveExtension getExtension() {
		return extension;
	}
	/**
	 * @param exntension the extension to set
	 */
	@XmlElement
	public void setExtension(SaveExtension exntension) {
		this.extension = exntension;
	}
}
