package model;

/**
 * Factory that creates cars. Cars themselves are package private so this class allows
 * outside access. Standard factory pattern.  
 *
 */
public class CarFactory implements RoadAndCarFactoryInterface{
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	
	/**
	 * Only publicly available car creation method.
	 * 
	 * @param dir Direction enum value of VERTICAL or HORIZONTAL
	 * @return a new Car object
	 */
	public Car generate(ProjectSetup.RoadDirection dir){
		
		  double length = _defaults.getMaxCarLength() - _defaults.getMinCarLength();
		  length = (double)(Math.random() * length) + _defaults.getMinCarLength();
		  double maxVelocity = _defaults.getMaxVelocity()-_defaults.getMinVelocity();
		  maxVelocity = (double)(Math.random() * maxVelocity) + _defaults.getMinVelocity();
		  double stopDistance = _defaults.getMaxStopDistance()-_defaults.getMinStopDistance();
		  stopDistance = (double)(Math.random() * stopDistance) + _defaults.getMinStopDistance();
		  double brakeDistance = _defaults.getMaxBrakeDistance()-_defaults.getMinBrakeDistance();
		  brakeDistance = (double)(Math.random() * brakeDistance) + _defaults.getMinBrakeDistance();
		
		Car c = new Car(length, maxVelocity, stopDistance, brakeDistance, dir);
		
		return c;
	}
}
