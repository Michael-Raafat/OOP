/**
 * 
 */
package fileManager;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import databaseManagement.DBMSMeta;
import exceptions.UnknownColumnException;
import fileManager.savableModels.SavableTable;
import models.Table;

/**
 * @author Marc Magdi
 *
 */
public class XMLFileWriter implements FileWriter {

	/**.
	 * service responsible for converting from and to savable objects
	 */
	private XMLObjectAdapter objectAdapter;

	/**.
	 * Default Constructor to initialize data
	 */
	public XMLFileWriter() {
		objectAdapter = new XMLObjectAdapter();
	}

	@Override
	public final void saveTable(final Table table, final File file) {
		SavableTable savTable = objectAdapter.getSavableTable(table);
		this.saveObject(savTable, file);
	}

	@Override
	public final Table loadTable(final File file) throws UnknownColumnException {
		SavableTable savTable = this.loadSavTable(file);
		return objectAdapter.getTableFromSavable(savTable);
	}

	/**.
	 * Save the given object to the given file
	 * @param savableObject the object to save
	 * @param file the file to save to
	 */
	private void saveObject(final Object savableObject, final File file) {
		try {
  			JAXBContext jaxbContext = JAXBContext.
  					newInstance(savableObject.getClass());
  	  		Marshaller jaxbMarshaller =
  	  				jaxbContext.createMarshaller();

  	  		// output pretty printed
  	  		jaxbMarshaller.setProperty(
  	  				Marshaller.JAXB_FORMATTED_OUTPUT, true);
  	  		
  	  		// disable standalone to validate it verse a DTD file
  	  		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
  	  		// add the default declaration of XML
  	  		jaxbMarshaller.setProperty(
  	  				"com.sun.xml.internal.bind.xmlHeaders",
  	  				"<?xml version=\"1.0\" encoding=\"UTF-8\" "
  	  				+ "standalone=\"no\"?>");

  	  		jaxbMarshaller.marshal(savableObject, file);
//		    jaxbMarshaller.marshal(db, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**.
	 * Load a saved object from the given file
	 * @param file the file to load the object from
	 * @return return the loaded object as saved one
	 */
	private SavableTable loadSavTable(final File file) {
		JAXBContext jaxbContext;
		SavableTable table = null;
		try {
			jaxbContext = JAXBContext
					.newInstance(SavableTable.class);
			Unmarshaller jaxbUnmarshaller =
					jaxbContext.createUnmarshaller();
			table = (SavableTable)
					jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return table;
	}

	/**.
	 * Load meta data of the our database management system.
	 * @param file the file to load the data from
	 * @return return DBMSMeta object containing our data
	 */
	public final DBMSMeta loadMeta(final File file) {
		JAXBContext jaxbContext;
		DBMSMeta meta = null;
		try {
			jaxbContext = JAXBContext
					.newInstance(DBMSMeta.class);
			Unmarshaller jaxbUnmarshaller =
					jaxbContext.createUnmarshaller();
			meta = (DBMSMeta)
					jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return meta;
	}

	@Override
	public final String getExtension() {
		return "xml";
	}
}
