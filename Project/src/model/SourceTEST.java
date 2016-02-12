package model;

import model.ProjectSetup.RoadDirection;
import junit.framework.TestCase;

public class SourceTEST extends TestCase {
	ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	RoadFactory _roadMaker = new RoadFactory();
	
	public void testCarGen(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		Sink s2 = new Sink();
		r1.setNextRoad(s2);
		Source s1 = new Source(r1);
		assertTrue(s1._carGenRate <= _defaultSetup.getMaxCarGenRate());
		assertTrue(s1._carGenRate >= _defaultSetup.getMinCarGenRate());
		assertEquals(r1, s1._firstRoad);
		s1.run();
		System.out.println("Cars on road: " + s1._firstRoad.getCars().size());
		assertEquals(1, s1._firstRoad.getCars().size());
	}
}
