package eg.edu.alexu.csd.oop.paint.commandHandler;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.commandHandler.InfoCapsule.ChangedInfo;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingDataCore;

public class CommandManager {
	/**
	 * list of InfoCapsule.
	 */
	private ArrayList<InfoCapsule> log;
	/**
	 * getter of log list.
	 * @return
	 * log list.
	 */
	public ArrayList<InfoCapsule> getLog() {
		return log;
	}
	/**
	 * setter to log list.
	 * @return
	 * new log list.
	 */
	public void setLog(ArrayList<InfoCapsule> log) {
		this.log = log;
	}
	/**
	 * getter of historyIndex.
	 * @return
	 * Index.
	 */
	public int getHistoryIndex() {
		return historyIndex;
	}
	/**
	 * setter to historyIndex.
	 * @param historyIndex
	 * new Index.
	 */
	public void setHistoryIndex(int historyIndex) {
		this.historyIndex = historyIndex;
	}
    /**
     * history index.
     */
	private int historyIndex;
	/**
	 * constructor of CommandManager class.
	 */
	public CommandManager() {
		// TODO Auto-generated constructor stub
		historyIndex = 0;
		log = new ArrayList<InfoCapsule>();
	}
	/**
	 * to save action done.
	 * @param info
	 * information to add.
	 */
	public void saveAction(InfoCapsule info) {
		while (log.size() > historyIndex) {
			int size = log.size();
			log.remove(size - 1);
		}
		log.add(info);
		historyIndex++;
	}
	/**
	 * to return to last action.
	 * @param core
	 * shape's data.
	 */
	public void undoAction(
			IDrawingDataCore core) {
		ArrayList<GeoShapes> currentShapes = core.getShapes();
		if (historyIndex < 1) {
			throw new RuntimeException();
		} else {
			historyIndex--;
			InfoCapsule temp = log.get(historyIndex);
			if (temp.getAction() == ChangedInfo.DRAW) {
				currentShapes.remove(currentShapes.size() - 1);
			} else if (temp.getAction() == ChangedInfo.DELETE) {
				for (int i = temp.getChangeList().size() - 1; i > -1; i--) {
					currentShapes.add(temp.getChangeList().get(i).getIndex(),
							temp.getChangeList().get(i).getShape());
				}
			} else {
				for (int i = 0; i < temp.getChangeList().size(); i++) {
					currentShapes.set(temp.getChangeList().get(i).getIndex(),
							temp.getChangeList().get(i).getShape());
				}
			}
		}
	}
	/**
	 * to go to next action.
	 * @param core
	 * shape's data.
	 */
	public void redoAction(
			IDrawingDataCore core) {
			ArrayList<GeoShapes> currentShapes = core.getShapes();
			if (historyIndex >= log.size()) {
				throw new RuntimeException();
			} else {
				InfoCapsule temp = log.get(historyIndex);
				if (temp.getAction() == ChangedInfo.DRAW) {
					currentShapes.add(temp.getChangeList()
						.get(0).getShape());
				} else if (temp.getAction() == ChangedInfo.DELETE) {
					for (int i = 0 ; i < temp.getChangeList().size(); i++) {
						currentShapes.remove(temp.getChangeList().get(i)
								.getIndex());
					}
				} else if (temp.getAction() == ChangedInfo.RESIZE){
					for (int i = 0; i < temp.getChangeList().size(); i++) {
						currentShapes.set(temp.getChangeList().get(i).getIndex(),
								temp.getChangeList().get(i).getShape().resize(
								temp.getParametersOfAction().x,
								temp.getParametersOfAction().y));
					}
				} else if (temp.getAction() == ChangedInfo.MOVE){
					for (int i = 0; i < temp.getChangeList().size(); i++) {
						currentShapes.set(temp.getChangeList().get(i).getIndex(),
								temp.getChangeList().get(i).getShape().move(
								temp.getParametersOfAction().x,
								temp.getParametersOfAction().y));
					}
				} else if (temp.getAction() == ChangedInfo.BORDERCOLOR){
					for (int i = 0; i < temp.getChangeList().size(); i++) {
						GeoShapes shape = GeoShapes.copy(temp.getChangeList().get(i).getShape());
						shape.setBorderColor(temp.getColor());
						currentShapes.set(temp.getChangeList().get(i).getIndex(),
							shape);
					}
				} else if (temp.getAction() == ChangedInfo.FILLCOLOR){
						for (int i = 0; i < temp.getChangeList().size(); i++) {
							GeoShapes shape = GeoShapes.copy(temp.getChangeList().get(i).getShape());
							shape.setFillColor(temp.getColor());
							currentShapes.set(temp.getChangeList().get(i).getIndex(),
								shape);
					}
				} else if (temp.getAction() == ChangedInfo.THICKNESS){
					for (int i = 0; i < temp.getChangeList().size(); i++) {
						GeoShapes shape = GeoShapes.copy(temp.getChangeList()
								.get(i).getShape());
						shape.setThickness(temp.getThickness());
						currentShapes.set(temp.getChangeList().get(i).getIndex(),
							shape);
				}
			}
				historyIndex++;
			}
		}
}
