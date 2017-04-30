package models.dto;

import logs.LogService;

/**
 * This class represents the speed range data type
 * to be used as data transfer object between classes.
 * @author Marc Magdi
 *
 */
public class SpeedRange {

	/**.
	 * The minimum speed of a given plate
	 */
	private Speed minSpeed;

	/**.
	 * The maximum speed of a given plate
	 */
	private Speed maxSpeed;

	/**.
	 * Default constructor to initialize the minimum and maximum speed
	 * @param minSpeedP the minimum speed to assign
	 * @param maxSpeedP the maximum speed to assign
	 */
	public SpeedRange(final Speed minSpeedP,
			final Speed maxSpeedP) {
		LogService.printTrace(this.getClass(),
				"Construction of SpeedRange class");
		this.minSpeed = minSpeedP;
		this.maxSpeed = maxSpeedP;
	}

	/**
	 * @return the minSpeed
	 */
	public Speed getMinSpeed() {
		LogService.printTrace(this.getClass(),
				"Speed Method getMinSpeed is called");
		return minSpeed;
	}

	/**
	 * @return the maxSpeed
	 */
	public Speed getMaxSpeed() {
		LogService.printTrace(this.getClass(),
				"Speed Method getMaxSpeed is called");
		return maxSpeed;
	}
	/**
	 * Clones the current speed range.
	 * @return
	 * A copy of the speed range.
	 */
	public SpeedRange clone() {
		LogService.printTrace(this.getClass(),
				"SpeedRange Method clone is called");
		return new SpeedRange(minSpeed.clone(),
				maxSpeed.clone());
	}
}
