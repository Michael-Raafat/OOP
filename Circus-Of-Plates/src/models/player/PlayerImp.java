
package models.player;

import java.awt.Point;
import java.util.Observer;
import java.util.Random;

import controllers.NodeService;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logs.LogService;
import models.graphics.GraphicalGameObject;
import models.plates.Plate;
import models.plates.generator.PlatesPool;

/**
 * The implementation for the player interface.
 * @author Bishoy Nader
 *
 */
public class PlayerImp extends GraphicalGameObject implements Player {

	/**
	 * the center of new center for motion of mouse.
	 */
	private int newCenter;
	
	/**
	 * the observable object.
	 */
	private PlayerObservable playerObservable;
	
	/**
	 * determines the number of this player first or second.
	 */
	private PlayerNumber playerNumber;
	
	/**
	 * The velocity of the player.
	 */
	public static final int PLAYER_VELOCITY = 20,
			LEFT_HAND_X_MODIFIER = 18,
			RIGHT_HAND_X_MODIFIER = -20,
			HAND_Y_MODIFIER = 15,
			PLAYER_DISTANCE_FROM_GROUND = 100,
			DEFAULT_SCREEN_WIDTH = 1000,
			DEFAULT_SCREEN_HEIGHT = 600;

	/**
	 * The hands of the player.
	 */
	private PlayerHand leftHand, rightHand;
	
	/**
	 * The score of the player.
	 */
	private int score;
	
	/**
	 * flag for player movements to the left.
	 */
	private boolean leftMove, rightMove;
	
	/**
	 * The x bound of the window.
	 */
	private int xBound;
	
	/**
	 * Constructor.
	 * @param maxWidth
	 * The maximum width of the screen.
	 * @param maxHeight
	 * The maximum height of the screen.
	 * @param pool
	 * The pool to return the plates to.
	 * @param graphics
	 * The graphic service to register \ unregister nodes to.
	 */
	public PlayerImp(final int maxWidth, final int maxHeight,
			final PlatesPool pool, final NodeService graphics) {
		super(createNode());
		LogService.printTrace(this.getClass(), "Construction(maxwidth,"
				+ " maxheight,"	+ " pool, NodeService) of"
				+ " PlayerImp class that implements Player interface.");
		playerObservable = new PlayerObservable();
		xBound = maxWidth;
		modifyLocation(maxWidth, maxHeight);
		this.leftHand = new PlayerHandImp(this, pool, graphics, 
				new Point(calculateLeftHandX(), calculateHandY()));
		this.rightHand = new PlayerHandImp(this, pool, graphics,
				new Point(calculateRightHandX(), calculateHandY()));
		newCenter = (int) this.getNode().getLayoutX();
	}

	/**
	 * Constructor.
	 * @param pool
	 * The pool to return used plates to.
	 * @param graphics
	 * The graphic service to use.
	 */
	public PlayerImp(final PlatesPool pool, final NodeService graphics) {
		this(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT,
				pool, graphics);
		LogService.printTrace(this.getClass(), "Construction("
				+ "pool, NodeService) of"
				+ " PlayerImp class that implements Player interface.");
	}

	@Override
	public final boolean attachPlate(final Plate newPlate) {
		LogService.printTrace(this.getClass(), "boolean Method"
				+ " attachPlate(Plate) is called.");
		if (leftHand.isPlateIntersecting(newPlate)) {
			leftHand.attachPlate(newPlate);
			leftHand.updateScore();
			return true;
		} else if (rightHand.isPlateIntersecting(newPlate)) {
			rightHand.attachPlate(newPlate);
			rightHand.updateScore();
			return true;
		}
		return false;
	}

	@Override
	public final void increaseScoreByValue(final int value) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " increaseScoreByValue(value) is called.");
		this.score += value;
		notifyObserversScore();
	}
	
	/**
	 * Notify observers that a change in score happened
	 * and send current score.
	 */
	private void notifyObserversScore() {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " notifyObserversScore is called.");
		PlayerObserveDataObject obsDataObject = new PlayerObserveDataObject();
		obsDataObject.setScore(getScore());
		obsDataObject.setPlayerNumber(playerNumber);
		playerObservable.setChanged();
		playerObservable.notifyObservers(obsDataObject);
	}
	
	@Override
	public final int getScore() {
		LogService.printTrace(this.getClass(), "int Method"
				+ " getScore is called.");
		return score;
	}

	@Override
	public final void update() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " update is called.");
		if (leftMove && this.getNode().getLayoutX() > 0) {
			int x = (int) this.getNode().getLayoutX() - PLAYER_VELOCITY;
			this.getNode().relocate(x, this.getNode().getLayoutY());
			newCenter = x;
			updateHands();
		}
		if (rightMove && this.getNode().getLayoutX()
				+ this.getNode().getLayoutBounds().getWidth() < xBound) {
			int x = (int) this.getNode().getLayoutX() + PLAYER_VELOCITY;
			newCenter = x;
			this.getNode().relocate(x, this.getNode().getLayoutY());
			updateHands();
		}
		if (newCenter != (int) this.getNode().getLayoutX()) {
			this.getNode().relocate(newCenter, this.getNode().getLayoutY());
			updateHands();
		}
	}

	@Override
	public final void setLeftMove(final boolean newLeftMove) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setLeftMove(boolean) is called.");
		this.leftMove = newLeftMove;
	}

	@Override
	public final void setRightMove(final boolean newRightMove) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setRightMove(boolean) is called.");
		this.rightMove = newRightMove;
	}


	/**
	 * create the node for player.
	 * @return a node representing the player.
	 */
	private static Node createNode() {
		//TODO can't add log because of static method i need ((this))
		Image img = new Image("file:clown.PNG");
		ImageView imageView = new ImageView();
		imageView.setImage(img);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setCache(true);
		return imageView;
	}

	/**
	 * modify location of player node to bottom of screen.
	 * @param screenWidth width of screen.
	 * @param screenHeight height of screen.
	 */
	private void modifyLocation(final int screenWidth, final int screenHeight) {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " modifyLocation(screenWidth, screenHeight) is called.");
		Random rand = new Random();
		this.getNode().setLayoutY(screenHeight - PLAYER_DISTANCE_FROM_GROUND);
		this.getNode().setLayoutX(rand.nextInt(screenWidth));
	}
	
	@Override
	public final void moveX(final int x) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " moveX(int) is called.");
		if (x > 0 && x + getNode().getLayoutBounds().getWidth() < xBound) {
			this.newCenter = x;
		}
	}
	
	/**
	 * Update the hands of the player to the latest coordinates
	 * of the player.
	 */
	private void updateHands() {
		LogService.printTrace(this.getClass(), "private void Method"
				+ " updateHands is called.");
		leftHand.moveCenterToXCoord(calculateLeftHandX());
		rightHand.moveCenterToXCoord(calculateRightHandX());
	}
	
	/**
	 * Function to calculate the left hand x coordinate
	 * from the player node perspective.
	 * @return
	 * An integer representing the x coordinate of center of the left hand.
	 */
	private int calculateLeftHandX() {
		LogService.printTrace(this.getClass(), "private int Method"
				+ " calculateLeftHandX is called.");
		return (int) (getNode().getLayoutX() + LEFT_HAND_X_MODIFIER);
	}
	
	/**
	 * Function to calculate the right hand x coordinate
	 * from the player node perspective.
	 * @return
	 * An integer representing x coordinate of the center of the right hand.
	 */
	private int calculateRightHandX() {
		LogService.printTrace(this.getClass(), "private int Method"
				+ " calculateRightHandX is called.");
		return (int) (getNode().getLayoutX() 
				+ getNode().getLayoutBounds().getWidth()
				+ RIGHT_HAND_X_MODIFIER);
	}
	
	/**
	 * Function to calculate the hands y coordinate
	 * from the player node perspective.
	 * @return
	 * An integer representing the y coordinate of the centers of hands.
	 */
	private int calculateHandY() {
		LogService.printTrace(this.getClass(), "private int Method"
				+ " calculateHandY is called.");
		return (int) (getNode().getLayoutY() + HAND_Y_MODIFIER);
	}

	@Override
	public final PlayerHand getRightHand() {
		LogService.printTrace(this.getClass(), "PlayerHand Method"
				+ " getRightHand is called.");
		return rightHand;
	}

	@Override
	public final PlayerHand getLeftHand() {
		LogService.printTrace(this.getClass(), "PlayerHand Method"
				+ " getLeftHand is called.");
		return leftHand;
	}

	@Override
	public final PlayerSnapshot getSnapshot() {
		LogService.printTrace(this.getClass(), "PlayerSnapshot Method"
				+ " getSnapshot is called.");
		return new PlayerSnapshot(this);
	}

	@Override
	public final void useSnapshot(final PlayerSnapshot snap) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " useSnapshot(PlayerSnapshot) is called.");
		score = snap.getScore();
		getNode().setLayoutX(snap.getLayoutX());
		newCenter = snap.getLayoutX();
		leftHand.useSnapshot(snap.getLeftSnapshot());
		rightHand.useSnapshot(snap.getRightSnapshot());
		updateHands();
		notifyObserversScore();
	}

	@Override
	public final void addObserver(final Observer observer) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " addObserver(Observer) is called.");
		playerObservable.addObserver(observer);
	}

	@Override
	public final void removeObserver(final Observer observer) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " removeObserver(Observer) is called.");
		playerObservable.deleteObserver(observer);
	}
	
	@Override
	public final PlayerNumber getPlayerNumber() {
		LogService.printTrace(this.getClass(), "PlayerNumber Method"
				+ " getPlayerNumber is called.");
		return playerNumber;
	}
	
	@Override
	public final void setPlayerNumber(
			final PlayerNumber playerNumberParam) {
		LogService.printTrace(this.getClass(), "void Method"
				+ " setPlayerNumber(PlayerNumber) is called.");
		this.playerNumber = playerNumberParam;
	}

	@Override
	public final void close() {
		LogService.printTrace(this.getClass(), "void Method"
				+ " close is called.");
		this.rightHand.close();
		this.leftHand.close();
		this.rightHand = null;
		this.leftHand = null;
		this.playerObservable = null;
		this.playerNumber = null;
	}
}
