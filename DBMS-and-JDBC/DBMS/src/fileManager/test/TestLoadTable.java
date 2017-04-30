package fileManager.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import exceptions.UnknownColumnException;
import exceptions.UnknownExtension;
import fileManager.FileManager;
import fileManager.FileManagerSingleton;
import models.Database;
import models.Datatype;
import models.Row;
import models.Table;
import models.implementation.HeaderImp;

@RunWith(MockitoJUnitRunner.class)
public class TestLoadTable {

	@Mock
    Database mockDatabase;
	
	@Mock
    Table mockTable;

	@Mock
    Row mockRow;
	
	@Mock 
	HeaderImp mockHeader;
	
	@Before
	public void init() {
		// initialize data
		when(mockTable.getName()).thenReturn("testTable");
		List<String> cells = new ArrayList<String>(Arrays.asList("1", "Marc", "Manager"));

		when(mockDatabase.getName()).thenReturn("testDatabase");
		when(mockDatabase.getTables()).thenReturn(null);

		when(mockHeader.getcolumnName(0)).thenReturn("ID");
		when(mockHeader.getcolumnName(1)).thenReturn("Name");
		when(mockHeader.getcolumnName(2)).thenReturn("Job");

		when(mockHeader.getcolumnType(0)).thenReturn(Datatype.INTEGER);
		when(mockHeader.getcolumnType(1)).thenReturn(Datatype.VARCHAR);
		when(mockHeader.getcolumnType(2)).thenReturn(Datatype.VARCHAR);

		when(mockHeader.getSize()).thenReturn(3);

		when(mockRow.getCellDataByIndex(0)).thenReturn("1");
		when(mockRow.getCellDataByIndex(1)).thenReturn("Marc");
		when(mockRow.getCellDataByIndex(2)).thenReturn("Manager");

		when(mockRow.getCells()).thenReturn(cells);
		when(mockTable.getHeader()).thenReturn(mockHeader);

		when(mockTable.getRows()).thenReturn(new ArrayList<Row>(Arrays.asList(mockRow, mockRow)));

		when(mockDatabase.getName()).thenReturn("testDatabase");

		when(mockTable.getDatabase()).thenReturn(mockDatabase);
		// end initialize data
	}

	@Test
	public void test() throws UnknownExtension, UnknownColumnException, IOException {

		FileManager fileManager = FileManagerSingleton.getInstance();
		fileManager.writeDB(mockDatabase);
		fileManager.writeTable(mockTable);
	
		Table table = fileManager.readTable("testTable", "testDatabase");
		assertEquals("Assert Name", "testTable", table.getName());
		assertEquals("Assert Rows Size", 2, table.getRows().size());
		assertEquals("Assert First row name", "Marc", table.getRows().get(0).getCellDataByIndex(1));
		assertEquals("Assert First row Job", "Manager", table.getRows().get(0).getCellDataByIndex(2));
	}

}
