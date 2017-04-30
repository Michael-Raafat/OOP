package models.plates.generator;

import logs.LogService;

/**.
 * This class represents the colors as a web color using RGB
 * It overrides the hashCode and equals so it can be used in a set
 * @author Marc Magdi
 *
 */
public class WebColor {
	/**.
	 * The red
	 */
	private int red;
	/**.
	 * The green
	 */
	private int green;
	/**.
	 * The blue
	 */
	private int blue;
	
	public WebColor() {
		LogService.printTrace(this.getClass(), "Construction of WebColor"
				+ " class");
	}
	
	public WebColor(int red, int green, int blue) {
		LogService.printTrace(this.getClass(), "Construction of "
				+ "WebColor(int, int, int)"
				+ " class");
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	/**
	 * @return the red
	 */
	public int getRed() {
		LogService.printTrace(this.getClass(), "int method getRed is called");
		return red;
	}
	/**
	 * @param red the red to set
	 */
	public void setRed(int red) {
		LogService.printTrace(this.getClass(), "void method setRed is called");
		this.red = red;
	}
	/**
	 * @return the green
	 */
	public int getGreen() {
		LogService.printTrace(this.getClass(), "int method getGreen is called");
		return green;
	}
	/**
	 * @param green the green to set
	 */
	public void setGreen(int green) {
		LogService.printTrace(this.getClass(), "void method setGreen is called");
		this.green = green;
	}
	/**
	 * @return the blue
	 */
	public int getBlue() {
		LogService.printTrace(this.getClass(), "int method getBlue is called");
		return blue;
	}
	/**
	 * @param blue the blue to set
	 */
	public void setBlue(int blue) {
		LogService.printTrace(this.getClass(), "void method setBlue is called");
		this.blue = blue;
	}
	
	@Override
	public int hashCode() {
		// TODO update hash code algorithm
		LogService.printTrace(this.getClass(), "int method hashCode is called");
		return getRed() ^ getBlue() ^ getGreen() ;
	}
	
	@Override
	public boolean equals(Object obj) {
		LogService.printTrace(this.getClass(), "boolean method equals is called");
		if (obj == null) {
	        return false;
	    }
		
		if (!WebColor.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
		
		final WebColor color = (WebColor) obj;
		
		return color.getBlue() == this.blue
				&& color.getGreen() == this.getGreen()
				&& color.getRed() == this.getRed();
	}
}
