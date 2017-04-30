package fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import exceptions.UnknownColumnException;
import fileManager.savableModels.SavableTable;
import models.Table;

/**.
 * Json File Writer Class to save all files objects as json
 * @author Marc Magdi
 *
 */
public class JsonFileWriter implements FileWriter {

	/**.
	 * service responsible for converting from and to savable objects
	 */
	private XMLObjectAdapter objectAdapter;

	/**.
	 * Default constructor to initialize data
	 */
	public JsonFileWriter() {
		objectAdapter = new XMLObjectAdapter();
	}

	@Override
	public final void saveTable(final Table table, final File file) {
		SavableTable savTable = objectAdapter.getSavableTable(table);
		this.saveObject(savTable, file);
	}

	@Override
	public final Table loadTable(final File file)
			throws UnknownColumnException {
		SavableTable savTable = this.loadSavTable(file);
		return objectAdapter.getTableFromSavable(savTable);
	}

	/**.
	 * Save the given object to the given file
	 * @param savableObject the object to save
	 * @param file the file to save to
	 */
	private void saveObject(final Object savableObject, final File file) {
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
            e.printStackTrace();
        }
	}

	/**.
	 * Load a saved object from the given file
	 * @param file the file to load the object from
	 * @return return the loaded object as saved one
	 */
	private SavableTable loadSavTable(final File file) {
        FileReader fr;
        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                builder.append(line);
                line = br.readLine();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Gson gson = new Gson();
        SavableTable table = gson.fromJson(
        		builder.toString(), SavableTable.class);

        return table;
	}

	@Override
	public final String getExtension() {
		return "json";
	}

}
