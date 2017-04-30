package file.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import logs.LogService;
/**
 *
 * @author Marc Magdi.
 *
 */
public final class JsonObjectSaver {

	/**.
	 * Prevent getting new instance
	 */
	private JsonObjectSaver() {

	}

	/**.
	 * Save the given object to the given file
	 * @param savableObject the object to save
	 * @param file the file to save to
	 */
	public static void saveObject(final Object savableObject,
			final File file) {
		LogService.printTrace(new JsonObjectSaver(
				).getClass(), "static void Method"
				+ " saveObject(Object, File) method.");
		Gson gsonT = new Gson();
        String json = gsonT.toJson(savableObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        String prettyJsonString = gson.toJson(je);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(prettyJsonString);
            writer.close();
        } catch (FileNotFoundException e) {
        	LogService.printError(new JsonObjectSaver(
  			   ).getClass(), "Error in"
  		   	+ " saving game (saveObject method). "
  		   	+ "File not found exception");
            e.printStackTrace();
        }
	}
}
