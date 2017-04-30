package controllers;

/**.
 * The module responsible for switching between views.
 * @author Marc Magdi
 *
 */
public interface ViewSwitcher {

	/**.
	 * Remove the current view and load a new
	 * view from the given FXML file.
	 * @param fxmlName the FXML file to load the view from,
	 * it's case sensitive.
	 */
	public void switchFxml(String fxmlName);
}
