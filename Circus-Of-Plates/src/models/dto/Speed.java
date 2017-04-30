package models.dto;

import logs.LogService;

/**.
 * The speed object is used as DTO to transfer any speed.
 * @author Marc Magdi.
 *
 */
public class Speed {
	/**.
	 * The speed component in the x direction
	 */
	private float xSpeed;
	/**.
	 * The speed component in the x direction
	 */
	private float ySpeed;
	/**.
	 * Default constructor
	 * @param xSpeedP the speed in the x direction
	 * @param ySpeedP the speed in the y direction
	 */
	public Speed(final float xSpeedP,
			final float ySpeedP) {
		LogService.printTrace(this.getClass(),
				"Construction of Speed class");
		this.xSpeed = xSpeedP;
		this.ySpeed = ySpeedP;
	}
	/**
	 * @return the xSpeed
	 */
	public float getxSpeed() {
		LogService.printTrace(this.getClass(),
				"float Method getxSpeed is called");
		return xSpeed;
	}
	/**
	 * @param xSpeedP the xSpeed to set
	 */
	public void setxSpeed(final float xSpeedP) {
		LogService.printTrace(this.getClass(),
				"void Method setxSpeed(float)"
				+ " is called");
		this.xSpeed = xSpeedP;
	}
	/**
	 * @return the ySpeed
	 */
	public float getySpeed() {
		LogService.printTrace(this.getClass(),
				"float Method getySpeed is called");
		return ySpeed;
	}
	/**
	 * @param ySpeedP the ySpeed to set
	 */
	public void setySpeed(final float ySpeedP) {
		LogService.printTrace(this.getClass(),
				"void Method setySpeed(float)"
				+ " is called");
		this.ySpeed = ySpeedP;
	}
	/**
	 * Clone to return a copy of the speed.
	 * @return
	 * Returns a copy of the speed.
	 */
	public final Speed clone() {
		LogService.printTrace(this.getClass(),
				"Speed Method clone is called");
		return new Speed(xSpeed, ySpeed);
	}
}
