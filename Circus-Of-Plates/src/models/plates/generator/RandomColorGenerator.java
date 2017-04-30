
package models.plates.generator;

import java.util.List;

import javafx.scene.paint.Color;

/**.
 * Generate a random distinct color list
 * @author Marc Magdi
 *
 */
public interface RandomColorGenerator {

	/**.
	 * Generate a random color list, where it's size
	 * is the default maximum size of the implementation class.
	 * @return a list of colors, its size is the given one,
	 * containing a random distinct colors.
	 */
	List<Color> getRandomColorList();

	/**.
	 * Generate a random color list, where it's
	 * size is the give one.
	 * @param size the size of the returned list of filled colors
	 * @return a list of colors, its size is the given one,
	 * containing a random distinct colors.
	 */
	List<Color> getRandomColorList(int size);

}
