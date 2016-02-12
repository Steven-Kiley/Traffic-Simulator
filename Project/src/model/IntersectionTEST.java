package model;

import model.Light.LightStatus;
import model.ProjectSetup.RoadDirection;
import junit.framework.TestCase;

public class IntersectionTEST extends TestCase{
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	RoadFactory _roadMaker = new RoadFactory();
	CarFactory _carMaker = new CarFactory();
	
	public void testConstructor(){
		Intersection i = new Intersection();
		assertTrue(i.getEndPosition() <=_defaults.getMaxIntersectionLength());
		assertTrue(i.getEndPosition() >= _defaults.getMinIntersectionLength());
		assertTrue(i.getLight() != null);	
	}
	
	public void testRoadLinking(){
		Intersection i = new Intersection();
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		Road r2 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		i.setEastWestRoad(r2);
		assertEquals(r2, i.getEastWestRoad());
	}
	
	public void testLight(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		Road r2 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		Sink s1 = new Sink();
		Car c1 = _carMaker.generate(RoadDirection.HORIZONTAL);
		c1.setMaxVelocity(10);
		c1.setTimeStep(1.0);
		Intersection i1 = new Intersection();
		i1.setEastWestRoad(r2);
		r1.setNextRoad(i1);
		r2.setNextRoad(s1);
		r1.accept(c1, 0.0);
		Light light = i1.getLight();
		assertTrue(light.getStatus() == LightStatus.GREEN_EAST_WEST);
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		light.run();
		light.run();
		assertTrue(light.getStatus() == LightStatus.GREEN_NORTH_SOUTH);
		assertEquals((r1.getEndPosition() -10.0), c1.callDistanceToObstacle());
		assertEquals(c1.maxVelocity(), c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		c1.run();
		assertTrue(c1.frontPosition() > 0);
		assertEquals(10.0, c1.frontPosition());
		assertEquals(c1.maxVelocity(), c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		
		light.run();
		light.run();
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		c1.run();
		assertEquals(20.0, c1.frontPosition());
		assertEquals(c1.maxVelocity(), c1.calculateVelocity());
		System.out.println("Current car velocity: " + c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		
		light.run();
		light.run();
		System.out.println("Calculate velocity: " + c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		
		for(int i = 0; i < 19; i++){
			c1.run();
		}
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		
		c1.run();		
		System.out.println("Calculate velocity: " + c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		
		c1.run();		
		System.out.println("Calculate velocity: " + c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
		
		c1.run();		
		System.out.println("Calculate velocity: " + c1.calculateVelocity());
		System.out.println("car position is: " + c1.frontPosition());
		System.out.println("distance to obstacle is: " + c1.callDistanceToObstacle());
		System.out.println();
	}
}
