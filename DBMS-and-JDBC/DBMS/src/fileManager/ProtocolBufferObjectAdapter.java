package fileManager;

import java.util.ArrayList;
import java.util.List;

import exceptions.UnknownColumnException;
import fileManager.savableModels.TableProtoContainer.TableProto;
import fileManager.savableModels.TableProtoContainer.TableProto.Builder;
import fileManager.savableModels.TableProtoContainer.TableProto.HeaderProto;
import models.Datatype;
import models.Header;
import models.Row;
import models.Table;
import models.implementation.HeaderImp;
import models.implementation.RowImp;
import models.implementation.TableImp;

/**
 * .
 *
 * @author Marc Magdi
 *
 */
public class ProtocolBufferObjectAdapter {

	/**
	 * . Get a new table object from the proto buffer object
	 * 
	 * @param tableProto
	 *            the object to extract the new table object from
	 * @return return a new table object
	 * @throws UnknownColumnException throw unknown column exception
	 * when selecting an invalid table
	 */
	public Table getTableFromProto(TableProto tableProto)
			throws UnknownColumnException {
		Table table = new TableImp(tableProto.getName()
				, null, this.getHeaderFromProto(tableProto.getHeader()));

		table.setRows(this.getRowsFromProto(
				table, tableProto.getRowsList()));
		return table;
	}

	/**
	 * . Get a new header object from the proto object
	 * 
	 * @param headerProto
	 *            the header proto to extract the new header from
	 * @return return the created header
	 * @throws UnknownColumnException throw unknown column exception
	 */
	private Header getHeaderFromProto(TableProto.HeaderProto headerProto)
			throws UnknownColumnException {
		List<String> colsName = headerProto.getColsNameList();
		List<Datatype> colsType = this.getColsTypeFromProto(headerProto.getColsTypeList());
		return new HeaderImp(colsName, colsType);
	}

	/**
	 * . Create a list of supported data type enums
	 *
	 * @return a list of supported data type enums
	 */
	private List<Datatype> getColsTypeFromProto(List<TableProto.Datatype> colsTypeProto) {
		List<Datatype> colsType = new ArrayList<Datatype>();

		for (int i = 0; i < colsTypeProto.size(); i++) {
			switch (colsTypeProto.get(i)) {
			case DATE:
				colsType.add(Datatype.DATE);
				break;
			case FLOAT:
				colsType.add(Datatype.FLOAT);
				break;
			case INTEGER:
				colsType.add(Datatype.INTEGER);
				break;
			case VARCHAR:
				colsType.add(Datatype.VARCHAR);
				break;
			default:
				colsType.add(Datatype.VARCHAR);
			}
		}

		return colsType;
	}

	/**
	 * . Return a filled list of rows for the given table
	 * 
	 * @param table
	 *            the table to which the rows will belong
	 * @param rowsProto
	 *            the rows object to extract the data from
	 * @return return the rows list
	 */
	private List<Row> getRowsFromProto(Table table, List<TableProto.RowProto> rowsProto) {
		List<Row> rows = new ArrayList<Row>();

		for (int i = 0; i < rowsProto.size(); i++) {
			Row row = new RowImp(table, this.getCellsFromProto(rowsProto.get(i).getValueList()));
			rows.add(row);
		}

		return rows;
	}

	/**
	 * . Return an editable List of the cells as the protocol buffers list is
	 * immutable
	 * 
	 * @param cellsProto
	 *            The list of cells to return a new on of
	 * @return return a new list of string representing the cells data
	 */
	private List<String> getCellsFromProto(List<String> cellsProto) {
		List<String> cells = new ArrayList<String>();
		for (int i = 0; i < cellsProto.size(); i++) {
			cells.add(cellsProto.get(i));
		}

		return cells;
	}

	/**
	 * . Create a proto object from the given Table
	 * 
	 * @param table
	 *            the table to create the object from
	 * @return the created object from the given table
	 */
	public TableProto getProtoFromTable(Table table) {
		HeaderProto header = TableProto.HeaderProto.newBuilder().addAllColsName(table.getHeader().getColumnsName())
				.addAllColsType(this.getColsType(table.getHeader().getColumnsType())).build();

		Builder builder = TableProto.newBuilder().setName(table.getName()).setHeader(header)
				.addAllRows(this.getRows(table.getRows()));

		return builder.build();

	}

	/**
	 * . Create a list of supported data type enums
	 * 
	 * @return a list of supported data type enums
	 */
	private List<TableProto.Datatype> getColsType(List<Datatype> colsType) {
		List<TableProto.Datatype> colsTypeProto = new ArrayList<TableProto.Datatype>();

		for (int i = 0; i < colsType.size(); i++) {
			switch (colsType.get(i)) {
			case DATE:
				colsTypeProto.add(TableProto.Datatype.DATE);
				break;
			case FLOAT:
				colsTypeProto.add(TableProto.Datatype.FLOAT);
				break;
			case INTEGER:
				colsTypeProto.add(TableProto.Datatype.INTEGER);
				break;
			case VARCHAR:
				colsTypeProto.add(TableProto.Datatype.VARCHAR);
				break;
			default:
				colsTypeProto.add(TableProto.Datatype.VARCHAR);
			}
		}

		return colsTypeProto;
	}

	/**
	 * . Get a list of Row Proto objects to be saved
	 *
	 * @param rows
	 *            the list of rows to extract the data from
	 * @return a list of Row Proto objects to be saved
	 */
	private List<TableProto.RowProto> getRows(List<Row> rows) {
		List<TableProto.RowProto> rowsProto = new ArrayList<TableProto.RowProto>();

		for (int i = 0; i < rows.size(); i++) {
			TableProto.RowProto row = TableProto.RowProto.newBuilder().addAllValue(rows.get(i).getCells()).build();
			rowsProto.add(row);
		}

		return rowsProto;
	}
}
