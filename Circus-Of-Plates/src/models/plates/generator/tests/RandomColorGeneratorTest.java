package models.plates.generator.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import javafx.scene.paint.Color;
import models.plates.generator.RandomColorGenerator;
import models.plates.generator.RandomColorGeneratorImp;

public class RandomColorGeneratorTest {

	@Test
	public void test() {
		RandomColorGenerator generator = new RandomColorGeneratorImp();
		Set<Color> set = new HashSet<Color>();
		
		List<Color> list = generator.getRandomColorList();
		for (int i = 0; i < list.size(); i++) {
			if (set.contains(list.get(i))) {
				fail("Repeated colors found");
			}
			
			set.add(list.get(i));
		}
	}

}
