package models.plates.generator;

import java.io.File;
import java.util.List;

import models.plates.Plate;

/**
 * The interface is responsible for plates dynamic loading.
 * @author Bishoy Nader
 *
 */
public interface PlatesLoader {

	/**.
	 * Load dynamically all plates concrete classes from
	 * the given folder path.
	 * @param folder the folder to load the classes from
	 * @return the list of loaded classes.
	 */
	List<Class<? extends Plate>> getPlatesClasses(final File folder);
	
}
