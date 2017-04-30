package models.plates.generator.tests;

import org.junit.Test;

import models.plates.generator.WebColor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WebColorTest {

	@Test
	public void testEquals() {
		WebColor color1 = new WebColor(0, 0, 255);
		WebColor color2 = new WebColor(0, 0, 255);
		WebColor color3 = new WebColor(255, 0, 255);
		
		assertEquals("Equal colors", color1, color2);
		assertNotEquals("Different colors", color1, color3);
	}

}
