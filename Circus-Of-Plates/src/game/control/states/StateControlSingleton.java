package game.control.states;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import controllers.ViewSwitcher;
import logs.LogService;
/**
 *
 * @author Michaal.
 *
 */
public final class StateControlSingleton implements StateControl {
	/**.
	 * The state controller.
	 */
	private static StateControlSingleton control;
	/**.
	 * The current state.
	 */
	private GameState state;
	/**.
	 * The Fxml view switcher.
	 */
	private ViewSwitcher viewSwitcher;
	/**.
	 * List of score observers.
	 */
	private List<Observer> scoreObservers;

	/**.
	 * Private constructor for singelton.
	 */
	private StateControlSingleton() {
		LogService.printTrace(this.getClass(), "Construction of "
				+ "StateControlSingleton class");
		state = new MenuState();
		scoreObservers = new LinkedList<Observer>();
	}

	/**.
	 * @return an instance of the class as singelton.
	 */
	public static synchronized StateControlSingleton
					getInstance() {
		if (control == null) {
			control = new StateControlSingleton();
		}
		LogService.printTrace(control.getClass(),
				"StateControlSingleton Method"
				+ " getInstance is called");
		return control;
	}

	@Override
	public void setState(final GameState newState) {
		LogService.printTrace(this.getClass(),
				"void Method setState is called");
		this.state = newState;
	}

	@Override
    public GameState getState() {
		LogService.printTrace(this.getClass(), "GameState Method"
				+ " getState is called");
		return this.state;
	}

	@Override
	public void setViewSwitcher(final ViewSwitcher fxmlViewSwitcher) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "setViewSwitcher(ViewSwitcher) is called");
		this.viewSwitcher = fxmlViewSwitcher;
	}

	@Override
	public void updateCurrentView(final String view) {
		LogService.printTrace(this.getClass(),
				"void Method updateCurrentView"
				+ " is called");
		this.viewSwitcher.switchFxml(view);
	}

	@Override
	public void handleAction(final Actions action) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " handleAction is called");
		this.state.handleAction(this, action);
	}

	@Override
	public void setScoreObserver(final Observer observer) {
		LogService.printTrace(this.getClass(), "void Method "
				+ "setScoreObserver(Observer) is called");
		this.scoreObservers.add(observer);
	}
	@Override
	public List<Observer> getScoreObserver() {
		LogService.printTrace(this.getClass(),
				"Observer Method getScoreObserver"
				+ " is called");
		return this.scoreObservers;
	}
}
