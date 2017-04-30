package fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import exceptions.UnknownColumnException;
import fileManager.savableModels.TableProtoContainer.TableProto;
import models.Table;


public class ProtoFileWriter implements FileWriter {

	/**.
	 * Adapter to help convert the table object
	 */
	private ProtocolBufferObjectAdapter objectAdapter;

	public ProtoFileWriter() {
		this.objectAdapter = new ProtocolBufferObjectAdapter();
	}

	@Override
	public final void saveTable(final Table table, final File file) {
		TableProto tableProto = objectAdapter.getProtoFromTable(table);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream output = new FileOutputStream(file);
			tableProto.writeTo(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final Table loadTable(final File file) throws UnknownColumnException {
		FileInputStream input = null;

		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		TableProto table = null;

		try {
			table = TableProto.parseFrom(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return objectAdapter.getTableFromProto(table);
	}

	@Override
	public final String getExtension() {
		return "bn";
	}

}
