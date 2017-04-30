
package models.plates.generator;

import java.awt.Choice;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import logs.LogService;
import models.plates.Plate;
/**
 * class that is responsible for loading of classes.
 * @author Michael.
 *
 */
public class PlatesLoaderImp implements PlatesLoader  {

	/**
	 * list of classes to be loaded.
	 */
	private ArrayList<Class<? extends Plate>> list;
	
	/**
	 * choice variable.
	 */
	private Choice chsLib;
	
	/**
	 * URl class loader.
	 */
	private URLClassLoader urlCLassLoader;
	
	/**
	 * list of Added jars.
	 */
	private ArrayList<URL> listOfAddedJars;
	
	/**
	 * constructor.
	 */
	public PlatesLoaderImp() {
		LogService.printTrace(this.getClass(),
				"Construction of PlatesLoaderImp"
				+ " class");
		list = new ArrayList<Class<? extends Plate>>();
		urlCLassLoader = URLClassLoader.newInstance(new URL[] {});
		listOfAddedJars = new ArrayList<URL>();
		chsLib = new Choice();
	}
	
	@Override
	public final List<Class<? extends Plate>>
								getPlatesClasses(final File folder) {
		LogService.printTrace(this.getClass(), "List<Class<? extends Plate>>"
				+ " method getPlatesClasses" + " is called");
		JarFile jarFile = initializeJarFile(folder);
		Enumeration<JarEntry> entrie = jarFile.entries();
		URL[] urls = initializeUrls(folder);
		urlCLassLoader = URLClassLoader.newInstance(urls);
		checkExistingClasses(entrie);
		return list;
	}

	/**
	 * check the jarEntry files for existing classes.
	 * @param entrie the list of jar entry files.
	 */
	private void checkExistingClasses(final Enumeration<JarEntry> entrie) {
		LogService.printTrace(this.getClass(), "List<Class<? extends Plate>>"
				+ " method checkExistingClasses" + " is called");
		while (entrie.hasMoreElements()) {
		    JarEntry enter = entrie.nextElement();
		    if (enter.isDirectory()	|| !enter.getName().endsWith(".class")) {
		        continue;
		    }
		    String className = extractClassName(enter);
		    Class<? extends Plate> c = null;
			try {
				c = (Class<? extends Plate>) urlCLassLoader.loadClass(className);
				checkExistingClasses(c);
			} catch (Exception e1) {
				LogService.printError(this.getClass(),
						"Error loading a class that" + " don't extends Plate"
						+ " abstract class"	+ "is already exist");
			}
		}
	}

	/**
	 * gets class name from a jar entry file.
	 * @param enter the jarEntry file.
	 * @return the class name.
	 */
	private String extractClassName(JarEntry enter) {
		String className = enter.getName().substring(
				0, enter.getName().length()	- ".class".length());
		className = className.replace('/', '.');
		return className;
	}

	/**
	 * @param c list of loaded classes.
	 */
	private void checkExistingClasses(Class<? extends Plate> c) {
		LogService.printTrace(this.getClass(), "List<Class<? extends Plate>>"
				+ " method checkExistingClasses" + " is called");
		String name = this.getClassName(c.getName());
		int choicesLength = chsLib.getItemCount();
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < choicesLength; i++) {
			names.add(chsLib.getItem(i));
		}
		if (names.contains(name)) {
			LogService.printError(this.getClass(),
			"Error loading a class that" + " is already exist");
		} else {
			list.add(c);
			chsLib.add(name);
		}
	}

	/**
	 * @param folder the folder to load urls from.
	 * @return an array of the urls.
	 */
	private URL[] initializeUrls(final File folder) {
		LogService.printTrace(this.getClass(), "List<Class<? extends Plate>>"
				+ " method initilazeUrls" + " is called");
		URL[] urls = new URL[listOfAddedJars.size() + 1];
		try {
			listOfAddedJars.add(new URL("jar:file:"
			+ folder.getAbsolutePath() + "!/"));
		} catch (MalformedURLException e2) {
			LogService.printError(this.getClass(), "MalformedURL"
				+ " in PlatesLoaderImp class >>" + " can't add URL of JarFile");
		}
		urls = listOfAddedJars.toArray(urls);
		return urls;
	}

	/**
	 * @param folder the folder of the jar file.
	 * @return the jar file reference if found.
	 */
	private JarFile initializeJarFile(final File folder) {
		LogService.printTrace(this.getClass(), "List<Class<? extends Plate>>"
				+ " method initilazeUrls" + " is called");
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(folder.getAbsolutePath());
		} catch (IOException e1) {
			LogService.printError(this.getClass(), "not found jarfile"
					+ " in PlatesLoaderImp class");
		}
		return jarFile;
	}

	/**
	 * get name of specific class.
	 * @param className name of class.
	 * @return string of the class name only.
	 */
	private String getClassName(final String className) {
		LogService.printTrace(this.getClass(),
				"private String method getClassName"
				+ " is called");
		 int size = className.length();
		 for (int i = size - 1; i >= 0; i--) {
			 if (className.charAt(i) == '.') {
				 return className.substring(i + 1);
			 }
		 }
		 return className;
	 }

}
