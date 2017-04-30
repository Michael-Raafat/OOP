package fileManager;

import java.util.List;

import fileManager.savableModels.SavableHeader;
import fileManager.savableModels.SavableTable;

/**.
 * A helper for the dtd generator
 * @author Marc Magdi
 *
 */
class DTDHelper {
	/**.
	 * Get the first part of the DTD file
	 * @return the static predefined attributes for the DTD File
	 */
	protected String getFirstStaticPart() {
		return "<!ELEMENT savableTable (header,row*)>\n"
				+ "<!ELEMENT header (colName,colType)>\n"
				+ "<!ELEMENT colName (#PCDATA)>\n"
				+ "<!ELEMENT colType (#PCDATA)>\n";
	}

	/**.
	 * Get a list of the available columns
	 * @param table the table to extract the columns from
	 * @return return a DTD format for the columns
	 */
	protected String getElementsForColumns(final SavableTable table) {
		StringBuilder mainRow = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		SavableHeader head = table.getHeader();
		List<String> tags = head.getColName();
		mainRow.append("<!ELEMENT row (");
		for (int i = 0; i < tags.size(); i++) {
			mainRow.append(tags.get(i));
			if (i != tags.size() - 1) {
				mainRow.append(",");
			}
			builder.append("<!ELEMENT "
					+ tags.get(i) + " (#PCDATA)>\n");
		}

		mainRow.append(")>\n");
		mainRow.append(builder.toString());
		return mainRow.toString();
	}

	/**.
	 * Get a list of all attributes
	 * @param table the table to extract the attributes for
	 * @return The attributes as a string
	 */
	protected String getAttributes(final SavableTable table) {
		StringBuilder builder = new StringBuilder();
		builder.append("<!ATTLIST savableTable"
				+ " table-name CDATA #REQUIRED>\n");
		SavableHeader head = table.getHeader();
		List<String> colNames = head.getColName();
		for (int i = 0; i < colNames.size(); i++) {
			builder.append("<!ATTLIST " + colNames.get(i)
			+ " db-type CDATA #REQUIRED>\n");

			builder.append("<!ATTLIST " + colNames.get(i)
			+ " type CDATA #REQUIRED>\n");
		}

		return builder.toString();
	}
}
