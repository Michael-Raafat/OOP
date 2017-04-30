package controllers;

import game.control.states.StateControl;
import game.control.states.StateControlSingleton;
import logs.LogService;

/**.
 * Abstract class as a start for all controllers
 * @author Marc Magdi
 *
 */
public abstract class BaseController {
	/**.
	 * The object responsible for managing the state and current input
	 */
	protected StateControl stateController;

	/**.
	 * Default constructor to create the state controller.
	 */
	public BaseController() {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " BaseController Abstract Class.");
		this.stateController = StateControlSingleton.getInstance();
	}
}
