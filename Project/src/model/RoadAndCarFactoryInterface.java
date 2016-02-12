package model;

/**
 * Factory interface, used for roads and cars
 *
 */
public interface RoadAndCarFactoryInterface {
	public Object generate(ProjectSetup.RoadDirection dir);
}
