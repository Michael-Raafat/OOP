package eg.edu.alexu.csd.oop.paint.interfaces;

import java.io.File;

/**
 * Interface that describes the saving and loading functionalities
 * of our project.
 * @author Amr&Mico
 */
public interface IPaintSaver {
	/**
	 * Function responsible of saving your paint as an XML.
	 * @param board
	 * The data of the paint
	 * @param saveName
	 * The file to save the painting in.
	 * @throws Exception
	 * The type of exception might vary according to the technique used.
	 */
	void writeXml(IDrawingDataCore board, File saveName) throws Exception;
	/**
	 * Function responsible of loading the saved XML and returning the
	 * data.
	 * @param loadFile
	 * The file to be loaded from.
	 * @return
	 * An object containing the all the saved information.
	 * @throws Exception
	 * The type of exception might vary according to the technique used.
	 */
	IDrawingDataCore readXml(ClassLoader cl,
			File loadFile) throws Exception;
	/**
	 * Function responsible of saving your paint as an JSON.
	 * @param board
	 * The data containing the information of the paint.
	 * @param filename
	 * The file to save the painting in.
	 * @throws Exception
	 * The type of exception might vary according to the technique used.
	 */
	void writeJson(IDrawingDataCore board,
			File filename) throws Exception;
	/**
	 * Function responsible of loading the saved XML and returning the
	 * data.
	 * @param loadFile
	 * The file to be loaded from.
	 * @return
	 * An object containing the all the saved information.
	 * @throws Exception
	 * The type of exception might vary according to the technique used.
	 */
	IDrawingDataCore readJson(ClassLoader cl,
			File loadFile) throws Exception;
}
