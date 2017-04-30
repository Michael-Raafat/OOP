package fileManager;

import exceptions.UnknownExtension;

/**.
 *
 * @author Marc Magdi
 *
 */
public interface FileWriterFactory {
	/**.
	 * Get a saveLoadHelper object based on the used extension,
	 * it's treated as singleton inside
	 * @param extension the extension to switch on to get the Save Helper
	 * @return the created object
	 * @throws UnknownExtension through exception
	 * if unknown extension is used
	 */
	public FileWriter getSaveHelper(SaveExtension extension)
			throws UnknownExtension;
}
