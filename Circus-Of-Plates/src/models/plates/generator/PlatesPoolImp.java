
package models.plates.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import logs.LogService;
import models.plates.Plate;
import models.plates.generator.PlatesFactory;
import models.plates.generator.PlatesFactoryImp;
import models.plates.generator.PlatesLoader;
import models.plates.generator.PlatesLoaderImp;
import models.plates.generator.PlatesPool;
import models.plates.generator.PlatesPoolImp;

/**
 * this class responsible for having all plates.
 * Object pool design pattern.
 * @author Michael.
 *
 */
public final class PlatesPoolImp implements PlatesPool {
	
	/**
	 * The pool size.
	 */
	private static final int POOL_SIZE = 150;
	
	/**
	 * list of plates not in scene.
	 */
	private List<Plate> free;
	
	/**
	 * list of plates in scene.
	 */
	private List<Plate> used;
	
	/**
	 * factory of creating plates.
	 */
	private PlatesFactory factory;
	
	/**
	 * static object of this class
	 * to make it a singleton class.
	 */
	private static PlatesPoolImp pool;
	
	/**
	 * plate loader object to load plates.
	 */
	private PlatesLoader load;
	
	/**
	 * private constructor.
	 */
	private PlatesPoolImp() {
		LogService.printTrace(this.getClass(),
				"Construction of PlatesPoolImp"
				+ " class");
		free = new ArrayList<Plate>();
		used = new ArrayList<Plate>();
		load = new PlatesLoaderImp();
		factory = new PlatesFactoryImp(load.getPlatesClasses(
						new File("./ResourcesJar/Reflections.jar")));
		this.setMaxPoolSize(POOL_SIZE);
	}

	/**
	 * get instance from this class.
	 * @return the object of this class.
	 */
	public static PlatesPoolImp getInstance() {
		if (pool == null) {
			pool = new PlatesPoolImp();
		}
		LogService.printTrace(pool.getClass(),
				"static PlatesPoolImp method"
				+ " getInstance"
				+ " is called");
		return pool;
	}
	
	@Override
	public int getPlateClassesCount() {
		LogService.printTrace(this.getClass(),
				"int method getPlateClassesCount"
				+ " is called");
		return factory.getNumberOfClasses();
	}
	
	@Override
	public Plate aquirePlate(final int index) {
		LogService.printTrace(this.getClass(),
				"Plate method aquirePlate(int)"
				+ " is called");
		for (int i = 0; i < free.size(); i++) {
			if (free.get(i).getClass().equals(
					factory.getClasses().get(index))) {
				Plate pl = free.remove(i);
				used.add(pl);
				return pl;
			}
		}
		return null;
	}
	
	@Override
	public void releasePlate(final Plate plate) {
		LogService.printTrace(this.getClass(),
				"void method releasePlate(Plate)"
				+ " is called");
		plate.reset();
		if (used.contains(plate)) {
			used.remove(plate);
			free.add(plate);
		}
	}

	@Override
	public void setMaxPoolSize(final int maxSize) {
		int max = maxSize;
		LogService.printTrace(this.getClass(), "void method setMaxPoolSize(int)"
				+ " is called");
		free.clear();
		int temp = 0;
		while (max > 0) {
			if (temp == factory.getNumberOfClasses()) {
				temp = 0;
			}
			free.add(factory.getNewPlate(temp));
			temp++;
			max--;
		}
	}

	@Override
	public int getTypeIndex(final String plateClass) {
		LogService.printTrace(this.getClass(), "int method"
				+ " getTypeIndex(String)" + " is called");
		int temp = 0;
		for (int i = 0; i < factory.getNumberOfClasses(); i++) {
			if (factory.getClasses().get(i).getName().equals(plateClass)) {
				temp = i;
			}
		}
		return temp;
	}

}
