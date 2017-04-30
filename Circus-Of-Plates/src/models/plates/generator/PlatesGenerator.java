package models.plates.generator;

import models.plates.Plate;
/**
 * The interface of the random plate generator.
 * @author Amr
 *
 *
 */
public interface PlatesGenerator {

	/**.
	 * Create a random plate and return it.
	 * @return return a random created plate.
	 */
	Plate getNewPlate();

}
