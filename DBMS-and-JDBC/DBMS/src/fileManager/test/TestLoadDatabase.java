package fileManager.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import exceptions.UnknownExtension;
import fileManager.FileManager;
import fileManager.FileManagerSingleton;
import models.Database;

@RunWith(MockitoJUnitRunner.class)
public class TestLoadDatabase {

	private FileManager fileManager;

	@Mock
	Database mockDatabase;

	@Before
	public void loadData() throws UnknownExtension {
		fileManager = FileManagerSingleton.getInstance();
		when(mockDatabase.getName()).thenReturn("testDatabase");
		when(mockDatabase.getTables()).thenReturn(null);
	}

	@Test
	public void testLoadASavedDatabase() {
		fileManager.writeDB(mockDatabase);
		assertNotNull("Test Database exist", fileManager.readDB(mockDatabase.getName()));
	}

	@Test
	public void testNotExistDatabaseLoad() {
		assertNull("Test Database doesn't exist", fileManager.readDB("test2Database"));
	}

}
