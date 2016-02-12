package model;

import model.ProjectSetup.RoadDirection;
import util.*;

/**
 * Creates car with random parameters within Settings range. This class is Package Private, cars can only be
 * passed to the outside using the CarFactory within this package.
 *
 */
public class Car implements Agent {
	  private double _frontPosition = 0;
	  private CarAcceptor _usableRoad;
	  private TimeServer _timeKeeper;
	  private double _length;
	  private double _maxVelocity;
	  private double _stopDistance;
	  private double _brakeDistance;
	  private ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	  private double _timeStep = _defaultSetup.getTimeStep();
	  private java.awt.Color _color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));
	  RoadDirection _direction;
	  
	  public Car(double lengthIn, double velocityIn, double stopDistIn, double brakeDistIn, ProjectSetup.RoadDirection dir){
		  _length = lengthIn;
		  _maxVelocity = velocityIn;
		  _stopDistance = stopDistIn;
		  _brakeDistance = brakeDistIn;
		  _direction = dir;
		  _timeKeeper = _defaultSetup.getTimeServer();
		  
	  }
	  
	  public java.awt.Color getColor() {
		    return _color;
		  }
	  
	  /**
	   * Action method for car. Execution causes changes in model that must be passed
	   * to observers. Observers must then update the graphical representation.
	   * Finds obstacles and adjusts velocity to avoid "collision" if necessary.
	   *
	   */
		public void run() {
			double carVelocity = this.calculateVelocity();
			_usableRoad.accept(this, _frontPosition + carVelocity * _timeStep);
			_timeKeeper.enqueue(_timeKeeper.currentTime() + _timeStep, this);
		}
	
		  public double calculateVelocity(){
			  double velocity;
			  
			  double distanceToObstacle = _usableRoad.distanceToObstacle(this, this._frontPosition, this.getUsableRoad().getDirection());
			  if (distanceToObstacle == _defaultSetup.getBigNum()){
				  velocity = this._maxVelocity;
				  return velocity;
			  }
			  double moveRequest = this. _maxVelocity *this. _timeStep;
			  if (distanceToObstacle < moveRequest){
					  velocity = distanceToObstacle/this._timeStep;
			  }
			  else {
			      velocity = (this._maxVelocity / (this._brakeDistance - this._stopDistance)) * (this._usableRoad.distanceToObstacle(this, this._frontPosition, this._direction) - this._stopDistance);
			  }
			  
			  velocity = Math.max(0.0, velocity);
			  velocity = Math.min(this._maxVelocity, velocity);
			  return velocity;
		  }
	
	
	/**Get the car ready.
	 */
	public void setUsableRoad(CarAcceptor r){
		  if (r == null){
			  throw new IllegalArgumentException();
		  }
		  _usableRoad = r;
	  }
	  public CarAcceptor getUsableRoad(){return this._usableRoad;}
	  
	  public void setFrontPosition(double frontPos) {
		  if (frontPos < 0){
			  //wanted to throw exception but kept getting off errors where negative numbers were passed back, decided to
			  //just make negative numbers into 0's to solve crashing problems. Imperfect solution, but allows program to run.
			  //throw new IllegalArgumentException("Cannot set car FrontPosition to negative number");
			  frontPos = 0;
		  }
		  this._frontPosition = frontPos;
	  }
	  
	  public double getFrontPosition(){return this._frontPosition;}
	  
	  public void setLength(double l){this._length = l;}
	  
	  public double getLength(){return this._length;}
	  
	  double backPosition() {return this._frontPosition-this._length;}
	  
	  double frontPosition(){return this._frontPosition;}
	  
	  double maxVelocity(){return this._maxVelocity;}
	  
	  double timeStep(){return this._timeStep;}
	  
	  public void setTimeStep(double time){this._timeStep = time;}
	  
	  public TimeServer getTimeServer(){return _timeKeeper;}
	  
	  public double getStopDistance(){return this._stopDistance;}
	 
	  public double getBrakeDistance(){return this._brakeDistance;}
	  
	  public void setMaxVelocity(double velocity){this._maxVelocity = velocity;}

	  public void setDirection(ProjectSetup.RoadDirection dir){this._direction = dir;}
	  
	  /**
	   * Method for test code only
	   */
	  public double callDistanceToObstacle(){
		  double distanceToObstacle = this._usableRoad.distanceToObstacle(this, this._frontPosition, this._usableRoad.getDirection());
		  return distanceToObstacle;
	  }
	  
	  /**
	   * Method for test code only
	   */
	  public double callDistanceToCarBack(){
		  double distanceToCarBack = this._usableRoad.distanceToCarBack(this._frontPosition);
		  return distanceToCarBack;
	  }
	  
}
