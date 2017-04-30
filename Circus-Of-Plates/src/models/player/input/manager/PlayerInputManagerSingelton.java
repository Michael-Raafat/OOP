
package models.player.input.manager;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logs.LogService;
import models.graphics.GraphicalGameObject;
import models.player.Player;

/**
 * Implementation for the player input manager interface.
 * @author Bishoy Nader
 *
 */
public final class PlayerInputManagerSingelton implements PlayerInputManager {
	
	/**
	 * keyboard player reference.
	 */
	private Player firstPlayer;

	/**
	 * keyCode to move keyboard player left.
	 */
	private KeyCode left;
	
	/**
	 * keyCode to move keyboard player right.
	 */
	private KeyCode right;
	
	/**
	 * mouse player reference.
	 */
	private Player secondPlayer;

	/**.
	 * A version of the player input manager to serve the singelton
	 */
	private static PlayerInputManager inputManager;

	/**
	 * a private constructor.
	 */
	private PlayerInputManagerSingelton() {
		LogService.printTrace(this.getClass(), "Construction of"
				+ " PlayerInputManagerSingleton class"
				+ " that implements PlayerInputManager interface.");
	}

	/**.
	 * @return a shared instance of the player input manager
	 */
	public static PlayerInputManager getInstance() {
		if (inputManager == null) {
			inputManager = new PlayerInputManagerSingelton();
		}
		LogService.printTrace(inputManager.getClass(),
				"static PlayerInputManager"	+ " Method"
				+ " getInstance is called.");
		return inputManager;
	}

	@Override
	public void setFirstPlayer(final Player player,
						final KeyCode leftParam, final KeyCode rightParam) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setFirstPlayer(Player, KeyCode left, KeyCode right)"
				+ " is called.");
		this.firstPlayer = player;
		this.left = leftParam;
		this.right = rightParam;
	}

	@Override
	public void setSecondPlayer(final Player player) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setSecondPlayer(Player) is called.");
		this.secondPlayer = player;
	}

	@Override
	public void respondToMoveEvent(final KeyEvent keyEvent) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " respondToMoveEvent(KeyEvent) is called.");
		if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
			firstPlayer.setLeftMove(false);
			firstPlayer.setRightMove(false);
		} else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
			if (keyEvent.getCode() == left) {
				firstPlayer.setLeftMove(true);
				firstPlayer.setRightMove(false);
			} else if (keyEvent.getCode() == right) {
				firstPlayer.setLeftMove(false);
				firstPlayer.setRightMove(true);
			}
		}
	}

	@Override
	public void respondToMoveEvent(final int xLocation) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " respondToMoveEvent(xLocation) is called.");
		if (secondPlayer instanceof GraphicalGameObject) {
			secondPlayer.moveX(xLocation);
		}
	}

}
