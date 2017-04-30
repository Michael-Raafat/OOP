package fileManager.savableModels;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
/**.
 * 
 * @author Marc Magdi
 *
 */
@XmlRootElement
public class SavableRow {
	/**.
	 * The cells of the row
	 */
	private List<SavableCell> cells;

	/**
	 * @return the cells
	 */
	public List<SavableCell> getCells() {
		return cells;
	}

	/**
	 * @param cells the cells to set
	 */
	@XmlAnyElement
	public void setCells(List<SavableCell> cells) {
		this.cells = cells;
	}
}
