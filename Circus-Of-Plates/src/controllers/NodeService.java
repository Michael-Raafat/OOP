package controllers;

import javafx.scene.Node;

/**.
 * Register the node to the current GUI interface
 * and unregister the node to the current interface.
 * @author Marc Magdi
 *
 */
public interface NodeService {
	/**.
	 * @param node the node to add to the GUI
	 */
	void bufferRegisterNode(Node node);
	/**.
	 * @param node schedules the node to be unregistered.
	 */
	void bufferUnregisterNode(Node node);
	/**
	 * force the unregistration of nodes from JavaFX.
	 */
	void clearBuffer();
	/**
	 * Clear screen from any registered node.
	 */
	void clearScreen();
	/**.
	 * @param controller the controller that controls the GUI
	 */
	void registerController(GUIController controller);
}
