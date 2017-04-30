package models.plates.generator;

import java.util.List;

import logs.LogService;
import models.plates.Plate;
/**
 * this class is responsible for producing plates
 * it's a factory method design pattern.
 * @author Michael.
 *
 */
public class PlatesFactoryImp implements PlatesFactory {

	/**
	 * list of loaded classes.
	 */
	private List<Class<? extends Plate>> classes;

	/**
	 * constructor.
	 * @param loadedclasses
	 * list of loaded classes.
	 */
	public PlatesFactoryImp(final List<Class<? extends Plate>> loadedclasses) {
		LogService.printTrace(this.getClass(),
				"Construction of PlatesFactoryImp" + " class");
		this.classes = loadedclasses;
	}

	@Override
	public final Plate getNewPlate(final int key) {
		LogService.printTrace(this.getClass(),
				"Plate method getNewPlate(int)"
				+ " is called");
		try {
			return classes.get(key).newInstance();
		} catch (Exception e) {
			LogService.printError(this.getClass(), "Exception"
					+ " in Plate method getNewPlate(int)");
			return null;
		}
	}

	@Override
	public final int getNumberOfClasses() {
		LogService.printTrace(this.getClass(),
				"int method getNumberOfClasses"
				+ " is called");
		return classes.size();
	}

	@Override
	public final List<Class<? extends Plate>> getClasses() {
		LogService.printTrace(this.getClass(),
				"List<Class<? extends Plate>>"
				+ " method getClasses"
				+ " is called");
		return classes;
	}

}
