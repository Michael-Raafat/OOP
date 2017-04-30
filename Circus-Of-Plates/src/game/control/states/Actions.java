package game.control.states;

import logs.LogService;

/**.
 * The actions that can be taken in the game.
 * @author Marc Magdi
 *
 */
public enum Actions {
	/**.
	 * The available actions that the user can take
	 */
	START_GAME, EXIT, LOAD_GAME, SAVE_GAME, MOUSE_MOVE, KEYBOARD_TYPE,
	/**.
	 * The available actions that the user can take
	 */
	RESUME_GAME, BACK_TO_MENU, GAME_OVER;

	/**.
	 * Object containing the value for
	 * the current action.
	 */
	private Object value;
	/**.
	 * @return the current value for the chosen action
	 */
	public Object getValue() {
		LogService.printTrace(this.getClass(),
				"Object Method getValue is "
				+ "called in enum Actions");
		return this.value;
	}

	/**.
	 * Set the action value.
	 * @param obj set the current object value with the given one.
	 */
	public void setValue(final Object obj) {
		LogService.printTrace(this.getClass(),
				"void Method setValue is called"
				+ "in enum Actions");
		this.value = obj;
	}
}
