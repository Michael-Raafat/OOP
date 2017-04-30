
package models.graphics;

import javafx.scene.Node;
import logs.LogService;
/**
 * GraphicalGameObject.
 * @author Michael.
 *
 */
public abstract class GraphicalGameObject implements GameObject {

	/**.
	 * The graphical node representing the game object
	 */
	private Node node;
	
	/**.
	 * Default constructor
	 * @param node the graphical node to
	 * map to the current class
	 */
	public GraphicalGameObject(final Node node) {
		 LogService.printTrace(this.getClass(), "Construction of "
		 		+ "GraphicalGameObject abstract class.");
		this.node = node;
		node.setManaged(false);
	}
	
	/**.
	 * @return the graphical node representing the current object
	 */
	public final Node getNode() {
		LogService.printTrace(this.getClass(),
				"Node Method getNode is called.");	
		return this.node;
	}
	
}
