package file.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;

import logs.LogService;
/**
 * class responsible for loading Object from a file.
 * @author Michael.
 *
 */
public final class JsonObjectLoader {

	/**.
	 * Prevent getting new instance
	 */
	private JsonObjectLoader() {

	}

	/**.
	 * Load a saved object from the given file
	 * @param file the file to load the object from
	 * @param arg of object loaded.
	 * @return return the loaded object as saved one
	 */
	public static Object loadObject(final File file, final Class<?> arg) {
		LogService.printTrace(new JsonObjectLoader(
				).getClass(), "static Object Method"
    			+ " loadObject(File, Class<?>) method.");
        FileReader fileReader;
        BufferedReader bufferReader;
	    StringBuilder builder = new StringBuilder();
	    try {
          fileReader = new FileReader(file);
          bufferReader = new BufferedReader(fileReader);
          String line = bufferReader.readLine();
          while (line != null) {
              builder.append(line);
              line = bufferReader.readLine();
          }
	    } catch (Exception exception) {
		   LogService.printError(new JsonObjectLoader(
				   ).getClass(), "Error in"
		   		+ " loading file (loadObject method).");
		   exception.printStackTrace();
        }
	    Gson gson = new Gson();
	    return gson.fromJson(
	    		builder.toString(), arg);
	}
}
