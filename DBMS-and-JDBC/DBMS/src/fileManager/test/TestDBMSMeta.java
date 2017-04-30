package fileManager.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import databaseManagement.DBMSMeta;
import exceptions.UnknownExtension;
import fileManager.ConfigurationFileManager;
import fileManager.FileManagerSingleton;
import fileManager.SaveExtension;

public class TestDBMSMeta {

	@Test
	public void testMeta2() throws UnknownExtension {
		DBMSMeta meta = new DBMSMeta();
		meta.setUserName("root");
		meta.setPassword("dbmsRoot");
		meta.setExtension(SaveExtension.XML);
		String path = System.getProperty("user.dir")
				+ System.getProperty("file.separator")
				+ "Databases";
		meta.setDefaultPath(path);
		ConfigurationFileManager manager = FileManagerSingleton.getInstance();
		
		
		
		manager.saveDBMSMeta(meta);
	}
	
	@Test
	public void testMeta() throws Exception {
		DBMSMeta meta = new DBMSMeta();
		meta.setUserName("root");
		meta.setPassword("dbmsRoot");
		meta.setExtension(SaveExtension.XML);
		String path = System.getProperty("user.dir")
				+ System.getProperty("file.separator")
				+ "Databases";
		meta.setDefaultPath(path);
		ConfigurationFileManager manager = FileManagerSingleton.getInstance();
		
		File file = new File(path + System.getProperty("file.separator") + "_DBMSMeta.xml");
		
		JAXBContext jaxbContext = JAXBContext.
					newInstance(meta.getClass());
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

	  		jaxbMarshaller.marshal(meta, file);
		
		manager.saveDBMSMeta(meta);
		
		manager.getDBMSMeta();
	}

}
