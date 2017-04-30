
package models.plates.generator;

import models.plates.Plate;

/**
 * Interface for the plates pool.
 * @author Bishoy Nader
 *
 */
public interface PlatesPool {

	/**.
	 * @return return the number of loaded classes that implements
	 * the plate interface
	 */
	int getPlateClassesCount();
	
	/**.
	 * Get a shape of the given index type, if we have 3 
	 * loaded types of shapes and the given index is 1
	 * then it return an object of the second loaded shape.
	 * @param index the index to located the type of the shape with.
	 * @return return a cleaned object of the required plate type.
	 */
	Plate aquirePlate(int index);
	
	/**.
	 * Remove the given shape, by marking it as unused and free.
	 * This also clean the given shape so it can be used again 
	 * through the pool.
	 * @param plate the plate to remove.
	 */
	void releasePlate(Plate plate);
	
	/**.
	 * set the maximum size of the pool list
	 * @param maxSize the max size to limit the pool list to.
	 */
	void setMaxPoolSize(int maxSize);
	
	/**
	 * Function that returns the type index of a plate.
	 * @param plateClass
	 * The class requested from the pool to return its index.
	 * @return
	 * -1 if not found, or the index of type that will
	 * produce this class.
	 */
	int getTypeIndex(String plateClass);

}
