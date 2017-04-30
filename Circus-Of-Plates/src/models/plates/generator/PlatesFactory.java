package models.plates.generator;

import java.util.List;

import models.plates.Plate;

public interface PlatesFactory {

	/**
	 * Get a new shape object based on the given key.
	 * @param key the key to locate the Shape concrete class with.
	 * @return return a new object of the selected shape.
	 */
	Plate getNewPlate(final int key);

	/**
	 * get number of types of plates we have in system.
	 */
	int getNumberOfClasses();

	/**
	 * get classes loaded.
	 * @return list of classes.
	 */
	List<Class<? extends Plate>> getClasses(); 

}
