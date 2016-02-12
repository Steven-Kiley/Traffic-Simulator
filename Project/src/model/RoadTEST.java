package model;

import model.ProjectSetup.RoadDirection;
import junit.framework.TestCase;

public class RoadTEST extends TestCase {
	private ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	CarFactory _carMaker = new CarFactory();
	RoadFactory _roadMaker = new RoadFactory();
	
	public void testLength(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		assertTrue(r1.getEndPosition() <= _defaultSetup.getMaxRoadLength());
		assertTrue(r1.getEndPosition() >= _defaultSetup.getMinRoadLength());
	}
	
	public void testAccept(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		try{
			r1.accept(null, 0);
			fail();
		}catch (IllegalArgumentException e){}
		assertTrue(r1._cars.size() == 0);
		Car c1 = _carMaker.generate(RoadDirection.HORIZONTAL);
		r1.accept(c1, 0.0);
		assertEquals(1, r1._cars.size());
		assertTrue(r1._cars.contains(c1));
	}
	
	public void testNextRoad(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		assertTrue(r1._nextRoad == null);
		Road r2 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		r1.setNextRoad(r2);
		assertTrue(r1._nextRoad == r2);
	}
}
