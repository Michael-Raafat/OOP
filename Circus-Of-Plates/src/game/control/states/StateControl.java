package game.control.states;

import java.util.List;
import java.util.Observer;

import controllers.ViewSwitcher;
/**
 *
 * @author Marc Magdi.
 *
 */
public interface StateControl {

	/**.
	 * getter for state
	 * @return state of game.
	 */
	public GameState getState();

	/**.
	 * Set the next state.
	 * @param state the state to be set.
	 */
	public void setState(GameState state);

	/**.
	 * Set the state switcher object.
	 * @param viewSwitcher the object responsible for switching the scenes.
	 */
	public void setViewSwitcher(ViewSwitcher viewSwitcher);

	/**.
	 * handle the given action depending on the Enum.
	 * @param action the action the user selected
	 */
	public void handleAction(Actions action);

	/**.
	 * update the current view the user is using.
	 * @param view the view name to be set.
	 */
	public void updateCurrentView(String view);

	/**.
	 * attach a new score observer.
	 * @param observer the observer to be attached
	 */
	public void setScoreObserver(Observer observer);

	/**.
	 * @return the observer list that need to be attached to score.
	 */
	public List<Observer> getScoreObserver();
}
