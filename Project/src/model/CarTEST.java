package model;

import model.ProjectSetup.RoadDirection;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Testing code for Car object. Tests instantiation and parameter validity.
 *
 */


public class CarTEST extends TestCase{
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	CarFactory _carMaker = new CarFactory();
	RoadFactory _roadMaker = new RoadFactory();
	Car car = _carMaker.generate(RoadDirection.HORIZONTAL);
	
	public void testValues(){
		
		System.out.println("Car Length is: " + car.getLength());
		//System.out.println("Car Min Length s/b: " + settings.getMinCarLength());
		//System.out.println("Car Max Length s/b: " + settings.getMaxCarLength());
		assertTrue(car.getLength() >= _defaults.getMinCarLength());
		assertTrue(car.getLength() <= _defaults.getMaxCarLength());
		assertTrue(car.maxVelocity() >= _defaults.getMinVelocity());
		assertTrue(car.maxVelocity() <= _defaults.getMaxVelocity());
		assertSame(car.getTimeServer(), _defaults.getTimeServer());
		assertTrue(car.getStopDistance() >= _defaults.getMinStopDistance());
		assertTrue(car.getStopDistance() <= _defaults.getMaxStopDistance());
		assertTrue(car.getBrakeDistance() >= _defaults.getMinBrakeDistance());
		assertTrue(car.getBrakeDistance() <= _defaults.getMaxBrakeDistance());	
	}
	
	public void testRoad(){
		
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		assertTrue(car.getUsableRoad() == null);
		r1.accept(car, 0.0);
		assertEquals(car.getUsableRoad(), r1);
	}
	
	public void testPosition(){
		
		try{
			car.setFrontPosition(0.0);
		} catch (IllegalArgumentException e){}
		try{
			car.setFrontPosition(-5.0);
			Assert.assertEquals(0.0, car.frontPosition());
		} catch (IllegalArgumentException e){}
	}
	
	public void testMovement(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		r1.setNextRoad(new Sink());
		r1.accept(car, 0.0);
		car.run();
		assertTrue(car.getFrontPosition() > 0);
	}
	
	public void testObstacleFind(){
		Road r1 = _roadMaker.generate(RoadDirection.HORIZONTAL);
		Sink s1 = new Sink();
		r1.setNextRoad(s1);
		Car c1 = _carMaker.generate(RoadDirection.HORIZONTAL);
		System.out.println("c1 Car Length is: " + c1.getLength());
		Car c2 = _carMaker.generate(RoadDirection.HORIZONTAL);
		System.out.println("c2 Car Length is: " + c2.getLength());
		Car c3 = _carMaker.generate(RoadDirection.HORIZONTAL);
		System.out.println("c3 Car Length is: " + c3.getLength());

		r1.accept(c1, 40.0);
		assertTrue(r1.getCars().contains(c1));
		r1.accept(c2, 0.0);
		assertTrue(r1.getCars().contains(c2));
		assertTrue(c1.frontPosition() == 40.0);
		assertTrue(c2.frontPosition() == 0.0);
		System.out.println("c2 distance to Car Back " + c2.callDistanceToCarBack());
		System.out.println("road distance to Car Back " + r1.distanceToCarBack(0.0));
		assertEquals((40.0 - c1.getLength()), c2.callDistanceToCarBack());
		assertEquals((40.0 - c1.getLength()), c2.callDistanceToObstacle());
		r1.accept(c3, 20.0);
		System.out.println("c2 distance to Car Back " + c2.callDistanceToCarBack());
		System.out.println("road distance to Car Back " + r1.distanceToCarBack(0.0));
		assertEquals((20.0 - c3.getLength()), c2.callDistanceToCarBack());
		assertEquals((20.0 - c3.getLength()), c2.callDistanceToObstacle());
		//assertEquals(20.0, c1.frontPosition());
		//assertTrue(c2.maxVelocity() > c2.calcVelocity());
		System.out.println("c2 current position is " + c2.frontPosition());
		System.out.println("Max Velocity is " + c2.maxVelocity());
		System.out.println("Calculate velocity: " + c2.calculateVelocity());
		System.out.println("c2 running");
		c2.run();
		assertTrue(c2.frontPosition() < c3.frontPosition());
		System.out.println("car position is: " + c2.frontPosition());
		System.out.println("distance to obstacle is: " + c2.callDistanceToObstacle());
		System.out.println();
	}
}