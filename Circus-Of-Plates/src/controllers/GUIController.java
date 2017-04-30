package controllers;

import java.util.List;

import javafx.scene.Node;

/**
 * Controls the GUI by adding or removing any nodes.
 * @author Marc Magdi
 *
 */
public interface GUIController {

	/**.
	 * Add the given node.
	 * @param toRegisterNodes
	 * List of nodes to add to the current GUI scene.
	 * @param toUnregisterNodes
	 * List of nodes to remove from the current Gui scene.
	 */
	void updateNodes(List<Node> toRegisterNodes,
			List<Node> toUnregisterNodes);

	/**
	 * clears all added non original nodes.
	 */
	void clearNodes();
}
