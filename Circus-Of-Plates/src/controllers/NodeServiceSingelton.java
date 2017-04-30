package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import logs.LogService;
/**
 * Singelton node service implementation.
 * @author Amr
 *
 */
public final class NodeServiceSingelton implements NodeService {
	/**.
	 * Node service.
	 */
	private static NodeService service;
	/**.
	 * controller to update the javaFX UI
	 */
	private GUIController guiController;
	/**
	 * Unregister node buffer list.
	 */
	private List<Node> unregisterBuffer;
	/**
	 * register node buffer list.
	 */
	private List<Node> registerBuffer;
	/**.
	 * Private constructor to prevent creating new instance.
	 */
	private NodeServiceSingelton() {
		LogService.printTrace(this.getClass(), "private Construction of"
				+ " NodeServiceSingleton Class which implements"
				+ " NodeService interface.");
		unregisterBuffer = new ArrayList<Node>();
		registerBuffer = new ArrayList<Node>();
	}
	/**.
	 *
	 * @return a service object that is shared between all classes
	 */
	public static NodeService getInstance() {
		if (service == null) {
			service = new NodeServiceSingelton();
		}
		LogService.printTrace(service.getClass(), "NodeService Method"
				+ " getInstance is called");
		return service;
	}

	@Override
	public void registerController(final GUIController controller) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " registerController(GUIController)"
				+ " is called");
		this.guiController = controller;
	}

	@Override
	public void bufferRegisterNode(final Node node) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " bufferRegisterNode(Node) is called");
		if (unregisterBuffer.contains(node)) {
			unregisterBuffer.remove(node);
		} else {
			registerBuffer.add(node);
		}
	}

	@Override
	public void bufferUnregisterNode(final Node node) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " bufferUnregisterNode(Node) is called");
		unregisterBuffer.add(node);
	}

	@Override
	public void clearBuffer() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " ClearBuffer is called");
		if (registerBuffer.size() > 0 || unregisterBuffer.size() > 0) {
			guiController.updateNodes(
					registerBuffer, unregisterBuffer);
		}
	}

	@Override
	public void clearScreen() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " ClearScreen is called");
		registerBuffer.clear();
		unregisterBuffer.clear();
		guiController.clearNodes();
	}

}
