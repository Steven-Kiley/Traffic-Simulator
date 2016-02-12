package model;

/**
 * Interface designed to help track locations of cars and obstacles.
 * Implemented by sinks, sources, intersections, and roads.
 *
 */
public interface CarAcceptor {
	public double distanceToObstacle(Car c, double fromPosition, ProjectSetup.RoadDirection dir);
	public boolean accept(Car c, double pos);
	public double getEndPosition();
	public ProjectSetup.RoadDirection getDirection();
	public double distanceToCarBack(double fromPosition);
	public boolean isIntersection();
}
