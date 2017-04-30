//package conditionService.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import conditionService.ConditionServiceProvider;
//import exceptions.UnknownColumnException;
//import exceptions.WrongDatatypeInputException;
//import logicalComponents.LogicalNode;
//import logicalComponents.LogicalOperations;
//import logicalComponents.RelationalNode;
//import logicalComponents.RelationalOperandType;
//import logicalComponents.RelationalOperations;
//import models.Datatype;
//import models.Row;
//import models.Table;
//import models.implementation.HeaderImp;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TestConditionService {
//
//	@Mock
//	Table mockableTable;
//	
//	@Mock
//	Row firstMockableRow, secondMockableRow, thirdMockableRow;
//	
//	@Mock
//	HeaderImp mockableHeader;
//
//	@Test
//	public void testBasicGetUserByID() throws UnknownColumnException, WrongDatatypeInputException {
//		
//		setUpHeader();
//		setUpRows();
//		setUpTable();
//
//		ConditionServiceProvider serviceProvider = ConditionServiceProvider.getInstance();
//		
//		RelationalNode leftOperation = testEqual(serviceProvider);
//		RelationalNode rightOperation = testGreaterThan(serviceProvider); 
//		
//		testOring(serviceProvider, leftOperation, rightOperation);
//		testAnding(serviceProvider, leftOperation, rightOperation);
//	}
//
//	/**
//	 * test AND condition for two operations
//	 * @param serviceProvider server provider object
//	 * @param leftOperation left operand to logical operation
//	 * @param rightOperation right operand to logical operation
//	 * @throws UnknownColumnException
//	 * @throws WrongDatatypeInputException
//	 */
//	private void testAnding(ConditionServiceProvider serviceProvider, RelationalNode leftOperation,
//			RelationalNode rightOperation) throws UnknownColumnException, WrongDatatypeInputException {
//		LogicalNode logicalAND = new LogicalNode(leftOperation, rightOperation, LogicalOperations.AND);
//		Table andResult = serviceProvider.getRowsForCondition(mockableTable, logicalAND);
//		assertEquals(1, andResult.getRows().size());
//		assertEquals("3", andResult.getRows().get(0).getCellDataByIndex(0));
//		assertEquals("Nader", andResult.getRows().get(0).getCellDataByIndex(1));
//	}
//
//	/**
//	 * test OR condition for two operations
//	 * @param serviceProvider server provider object
//	 * @param leftOperation left operand to logical operation
//	 * @param rightOperation right operand to logical operation
//	 * @throws UnknownColumnException
//	 * @throws WrongDatatypeInputException
//	 */
//	private void testOring(ConditionServiceProvider serviceProvider, RelationalNode leftOperation,
//			RelationalNode rightOperation) throws UnknownColumnException, WrongDatatypeInputException {
//		LogicalNode logicalOR = new LogicalNode(leftOperation, rightOperation, LogicalOperations.OR);
//		Table orResult = serviceProvider.getRowsForCondition(mockableTable, logicalOR);
//		assertEquals(2, orResult.getRows().size());
//		assertEquals("2", orResult.getRows().get(0).getCellDataByIndex(0));
//		assertEquals("Marc", orResult.getRows().get(0).getCellDataByIndex(1));
//		assertEquals("3", orResult.getRows().get(1).getCellDataByIndex(0));
//		assertEquals("Nader", orResult.getRows().get(1).getCellDataByIndex(1));
//	}
//
//	/**
//	 * test greater than condition for integers
//	 * @param serviceProvider server provider object
//	 * @return the operation performed
//	 * @throws UnknownColumnException
//	 * @throws WrongDatatypeInputException
//	 */
//	private RelationalNode testGreaterThan(ConditionServiceProvider serviceProvider)
//			throws UnknownColumnException, WrongDatatypeInputException {
//		RelationalOperandType leftOperand = new RelationalOperandType("userID", true, null);
//		RelationalOperandType rightOperand = new RelationalOperandType("1", false, Datatype.INTEGER);
//		
//		RelationalNode rightOperation = new 
//				RelationalNode(leftOperand, rightOperand, RelationalOperations.GREATER_THAN);
//		Table righttResult = serviceProvider.getRowsForCondition(mockableTable, rightOperation);
//		
//		assertEquals(2, righttResult.getRows().size());
//		assertEquals("2", righttResult.getRows().get(0).getCellDataByIndex(0));
//		assertEquals("Marc", righttResult.getRows().get(0).getCellDataByIndex(1));
//		assertEquals("3", righttResult.getRows().get(1).getCellDataByIndex(0));
//		assertEquals("Nader", righttResult.getRows().get(1).getCellDataByIndex(1));
//		return rightOperation;
//	}
//	
//	/**
//	 * test equal condition for strings
//	 * @param serviceProvider server provider object
//	 * @return the operation performed
//	 * @throws UnknownColumnException
//	 * @throws WrongDatatypeInputException
//	 */
//	private RelationalNode testEqual(ConditionServiceProvider serviceProvider)
//			throws UnknownColumnException, WrongDatatypeInputException {
//		RelationalOperandType leftOperand = new RelationalOperandType("username", true, null);
//		RelationalOperandType rightOperand = new RelationalOperandType("Nader", false, Datatype.VARCHAR);
//
//		RelationalNode leftOperation = new 
//				RelationalNode(leftOperand, rightOperand, RelationalOperations.EQUAL);
//		Table leftResult = serviceProvider.getRowsForCondition(mockableTable, leftOperation);
//		
//		assertEquals(1, leftResult.getRows().size());
//		assertEquals("3", leftResult.getRows().get(0).getCellDataByIndex(0));
//		assertEquals("Nader", leftResult.getRows().get(0).getCellDataByIndex(1));
//		return leftOperation;
//	}
//
//	/**
//	 * mock table
//	 * @throws UnknownColumnException
//	 */
//	private void setUpTable() throws UnknownColumnException {
//		when(mockableTable.getHeader()).thenReturn(mockableHeader);
//		
//		when(mockableTable.getColumnIndexByName("userID")).thenReturn(0);
//		when(mockableTable.getColumnIndexByName("username")).thenReturn(1);
//		when(mockableTable.getColumnIndexByName("salary")).thenReturn(2);
//		
//		when(mockableTable.getRows()).thenReturn(new ArrayList<Row>(
//			Arrays.asList(firstMockableRow, secondMockableRow, thirdMockableRow)
//		));
//		when(firstMockableRow.getTable()).thenReturn(mockableTable);
//		when(secondMockableRow.getTable()).thenReturn(mockableTable);
//		when(thirdMockableRow.getTable()).thenReturn(mockableTable);
//
//	}
//
//	/**
//	 * mock table rows.
//	 */
//	private void setUpRows() {
//		List<String> firseCells = new ArrayList<String>(Arrays.asList("1", "Bishoy", "5000"));
//		List<String> secondCells = new ArrayList<String>(Arrays.asList("2", "Marc", "4000"));
//		List<String> ThirdCells = new ArrayList<String>(Arrays.asList("3", "Nader", "10000"));
//
//		when(firstMockableRow.getCells()).thenReturn(firseCells);
//		when(secondMockableRow.getCells()).thenReturn(secondCells);
//		when(thirdMockableRow.getCells()).thenReturn(ThirdCells);
//		
//		when(firstMockableRow.getCellDataByIndex(0)).thenReturn("1");
//		when(firstMockableRow.getCellDataByIndex(1)).thenReturn("Bishoy");
//		when(firstMockableRow.getCellDataByIndex(2)).thenReturn("5000");
//
//		when(secondMockableRow.getCellDataByIndex(0)).thenReturn("2");
//		when(secondMockableRow.getCellDataByIndex(1)).thenReturn("Marc");
//		when(secondMockableRow.getCellDataByIndex(2)).thenReturn("4000");
//		
//		when(thirdMockableRow.getCellDataByIndex(0)).thenReturn("3");
//		when(thirdMockableRow.getCellDataByIndex(1)).thenReturn("Nader");
//		when(thirdMockableRow.getCellDataByIndex(2)).thenReturn("10000");
//	}
//
//	/**
//	 * mock the table header.
//	 */
//	private void setUpHeader() {
//		when(mockableHeader.getcolumnName(0)).thenReturn("userID");
//		when(mockableHeader.getcolumnName(1)).thenReturn("username");
//		when(mockableHeader.getcolumnName(2)).thenReturn("salary");
//		
//		when(mockableHeader.getcolumnType(0)).thenReturn(Datatype.INTEGER);
//		when(mockableHeader.getcolumnType(1)).thenReturn(Datatype.VARCHAR);
//		when(mockableHeader.getcolumnType(2)).thenReturn(Datatype.INTEGER);
//		when(mockableHeader.getSize()).thenReturn(3);
//	}
//
//}