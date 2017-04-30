package fileManager.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import exceptions.UnknownExtension;

import static org.junit.Assert.*;

import fileManager.FileManager;
import fileManager.FileManagerSingleton;
import models.Database;
import models.Table;

@RunWith(MockitoJUnitRunner.class)
public class TestSaveDatabase {
	
	@Mock
    Database mockDatabase;
	
	@Mock
    Table mocktable;
	
	@Test
	public void testSaveNewEmptyDatabase() throws UnknownExtension {
		// initialize data
		when(mockDatabase.getName()).thenReturn("testDatabase");
		when(mockDatabase.getTables()).thenReturn(null);
		// end initialize data
		FileManager fileManager = FileManagerSingleton.getInstance();
		fileManager.writeDB(mockDatabase);
		assertTrue("check empty database creation", fileManager.checkDatabaseExist("testDatabase"));
		fileManager.deleteDB(mockDatabase.getName());
	}

	@Test
	public void testSaveNewFilledDatabase() throws UnknownExtension {
		// initialize data
		when(mockDatabase.getName()).thenReturn("testDatabase");
		when(mocktable.getName()).thenReturn("testTable");
		List<Table> tables = new ArrayList<Table>();
		tables.add(mocktable);
		tables.add(mocktable);
		when(mockDatabase.getTables()).thenReturn((ArrayList<Table>) tables);
		// end initialize data
		FileManager fileManager = FileManagerSingleton.getInstance();
		fileManager.writeDB(mockDatabase);
		assertTrue("Database is created", fileManager.checkDatabaseExist("testDatabase"));
		fileManager.deleteDB(mockDatabase.getName());
	}
}
