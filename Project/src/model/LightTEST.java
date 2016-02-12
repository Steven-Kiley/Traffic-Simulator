package model;

import model.Light.LightStatus;
import junit.framework.TestCase;

public class LightTEST extends TestCase {
	ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	
	public void testValues(){
		Light light = new Light();
		assertTrue(light.getGreenDuration() <= _defaultSetup.getMaxGreenTime());
		assertTrue(light.getGreenDuration() >= _defaultSetup.getMinGreenTime());
		assertTrue(light.getYellowDuration() <= _defaultSetup.getMaxYellowTime());
		assertTrue(light.getYellowDuration() >= _defaultSetup.getMinYellowTime());
		assertEquals(LightStatus.GREEN_EAST_WEST, light.getStatus());
	}
	
	public void testRun(){
		Light light = new Light();
		assertEquals(LightStatus.GREEN_EAST_WEST, light.getStatus());
		light.run();
		assertEquals(LightStatus.YELLOW_EAST_WEST, light.getStatus());
		light.run();
		assertEquals(LightStatus.GREEN_NORTH_SOUTH, light.getStatus());
	}
	
}
