package model;

import model.ProjectSetup.RoadDirection;

public class Sink implements CarAcceptor {
	private ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	public Sink(){
		
	}
	/**Sink only accepts cars on their way to car heaven. Returns 0, a meaningless and
	 * unused value.
	 * @return 0
	 */
	public double getEndPosition(){return 0;}
	/**
	 * Returns false. A return of false from this method causes a failure to
	 * re-enqueue on the TimeServer, erego the car is no longer part of the 
	 * simulation.
	 * 
	 * @return false
	 */
	public boolean accept(Car c, double pos){return false;}
	public boolean isIntersection(){return false;}
	public double distanceToObstacle(Car c, double fromPosition, RoadDirection dir) {return _defaultSetup.getBigNum();}
	public RoadDirection getDirection(){throw new UnsupportedOperationException();}
	public double distanceToCarBack(double fromPosition){return _defaultSetup.getBigNum();}
}
