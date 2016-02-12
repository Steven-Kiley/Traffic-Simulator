package model;


import util.TimeServer;
import util.TimeServerLinked;

/**
 * Flexible default setting class that is able to be adjusted to the users input.
 * Creates the timeserver and contains methods to reference timeserver for 
 * all created Agent classes. Contains methods to set individual Model variables
 * to user input values, conducting error checks on user input.
 *
 *@see TimeServer
 *@see Agent
 *@see command package
 */
public class ProjectSetup {
	public enum RoadDirection{
		HORIZONTAL, VERTICAL
	}
	private TimeServer timeServerLinked = new TimeServerLinked();
	private double _timeStep = 0.4 ;
	private int _numHorizontalRoads = 2;
	private int _numVerticalRoads = 3;
	private double _minCarLength = 4.0;
	private double _maxCarLength = 9.0;
	private double _minCarGenRate = 3.0;
	private double _maxCarGenRate = 20.0;
	private double _minRoadLength =   200.0;
	private double _maxRoadLength =   500.0;
	private double _minIntersectionLength = 10.0;
	private double _maxIntersectionLength = 15.0;
	private double _minVelocity = 10.0;
	private double _maxVelocity = 35.0;
	private double _minStopDistance = 0.5;
	private double _maxStopDistance = 2.0;
	private double _minBrakeDistance = 3.0;
	private double _maxBrakeDistance = 5.0;
	private double _minGreenTime = 30.0;
	private double _maxGreenTime = 100.0;
	private double _minYellowTime = 4.0;
	private double _maxYellowTime = 6.0;
	private double _runFor = 1000;
	private boolean _alternating = true; 
	private static ProjectSetup _defaults = null;
	private double _bigNum = 1111111111.1111111111;
	
	private ProjectSetup(){}
	

	/**
	 * Access method to ensure all agents utilize one timeserver.
	 */
	public double getBigNum(){return this._bigNum;}
	
	public TimeServer getTimeServer(){
		return this.timeServerLinked;
	}
	
	public void setTimeStep(double tS){
		if (tS <= 0){
			throw new IllegalArgumentException();
		}
		else {
			this._timeStep = tS;
		}
	}
	public double getTimeStep(){
		return this._timeStep;
	}
	public void setHorizontalRoads(int numRoads){
		if (numRoads < 0){
			throw new IllegalArgumentException();
		}
		else{
			this._numHorizontalRoads = numRoads;
		}
	}
	public int getHorizontalRoads(){
		return this._numHorizontalRoads;
	}
	public void setVerticalRoads(int numRoads){
		if (numRoads < 0){
			throw new IllegalArgumentException();
		}
		else{
			this._numVerticalRoads = numRoads;
		}
	}
	public int getVerticalRoads(){
		return this._numVerticalRoads;
	}
	public void setMinCarLength(double length){
		if(length <=0 || length > _maxCarLength){
			throw new IllegalArgumentException();
		}
		else{
			this._minCarLength = length;
		}
	}
	public double getMinCarLength(){
		return this._minCarLength;
	}
	public void setMaxCarLength(double length){
		if(length <=0 || length < _minCarLength){
			throw new IllegalArgumentException();
		}
		else{
			this._maxCarLength = length;
		}
	}
	public double getMaxCarLength(){
		return this._maxCarLength;
	}
	public void setMinCarGenRate(double rate){
		if (rate <= 0 || rate > _maxCarGenRate){
			throw new IllegalArgumentException();
		}
		else{
			this._minCarGenRate = rate;
		}
	}
	public double getMinCarGenRate(){
		return this._minCarGenRate;
	}
	public void setMaxCarGenRate(double rate){
		if (rate <= 0 || rate < _minCarGenRate){
			throw new IllegalArgumentException();
		}
		else{
			this._maxCarGenRate = rate;
		}
	}
	public double getMaxCarGenRate(){
		return this._maxCarGenRate;
	}
	public void setMinRoadLength(double length){
		if(length <= 0 || length > _maxRoadLength){
			throw new IllegalArgumentException();
		}
		else{
			this._minRoadLength = length;
		}
	}
	public double getMinRoadLength(){
		return this._minRoadLength;
	}
	public void setMaxRoadLength(double length){
		if(length <= 0 || length < _minCarLength){
			throw new IllegalArgumentException();
		}
		else{
			this._maxRoadLength = length;
		}
	}
	public double getMaxRoadLength(){
		return this._maxRoadLength;
	}
	public void setMinIntersectionLength(double length){
		if(length < 0 || length > _maxIntersectionLength){
			throw new IllegalArgumentException();
		}
		else{
			this._minIntersectionLength = length;
		}
	}
	public double getMinIntersectionLength(){
		return this._minIntersectionLength;
	}
	public void setMaxIntersectionLength(double length){
		if(length < 0 || length < _minIntersectionLength){
			throw new IllegalArgumentException();
		}
		else{
			this._maxIntersectionLength = length;
		}
	}
	public double getMaxIntersectionLength(){
		return this._maxIntersectionLength;
	}
	public void setMinVelocity(double velocity){
		if (velocity <= 0 || velocity > _maxVelocity){
			throw new IllegalArgumentException();
		}
		else{
			this._minVelocity = velocity;
		}
	}
	public double getMinVelocity(){
		return this._minVelocity;
	}
	public void setMaxVelocity(double velocity){
		if (velocity <= 0 || velocity < _minVelocity){
			throw new IllegalArgumentException();
		}
		else{
			this._maxVelocity = velocity;
		}
	}
	public double getMaxVelocity(){
		return this._maxVelocity;
	}
	public void setMinStopDistance(double stop){
		if(stop < 0 || stop > _maxStopDistance){
			throw new IllegalArgumentException();
		}
		else{
			this._minStopDistance = stop;
		}
	}
	public double getMinStopDistance(){
		return this._minStopDistance;
	}
	public void setMaxStopDistance(double stop){
		if(stop < 0 || stop < _minStopDistance){
			throw new IllegalArgumentException();
		}
		else{
			this._maxStopDistance = stop;
		}
	}
	public double getMaxStopDistance(){
		return this._maxStopDistance;
	}
	public void setMinBrakeDistance(double brake){
		if(brake < 0 || brake > _maxBrakeDistance){
			throw new IllegalArgumentException();
		}
		else{
			this._minBrakeDistance = brake;
		}
	}
	public double getMinBrakeDistance(){
		return this._minBrakeDistance;
	}
	public void setMaxBrakeDistance(double brake){
		if(brake < 0 || brake < _minBrakeDistance){
			throw new IllegalArgumentException();
		}
		else{
			this._maxBrakeDistance = brake;
		}
	}
	public double getMaxBrakeDistance(){
		return this._maxBrakeDistance;
	}
	public void setMinGreenTime(double time){
		if (time <= 0 || time > _maxGreenTime){
			throw new IllegalArgumentException();
		}
		else{
			this._minGreenTime = time;
		}
	}
	public double getMinGreenTime(){
		return this._minGreenTime;
	}
	public void setMaxGreenTime(double time){
		if (time <= 0 || time < _minGreenTime){
			throw new IllegalArgumentException();
		}
		else{
			this._maxGreenTime = time;
		}
	}
	public double getMaxGreenTime(){
		return this._maxGreenTime;
	}
	public void setMinYellowTime(double time){
		if(time <=0 || time > _maxYellowTime){
			throw new IllegalArgumentException();
		}
		else{
			this._minYellowTime = time;
		}
	}
	public double getMinYellowTime(){
		return this._minYellowTime;
	}
	public void setMaxYellowTime(double time){
		if(time <=0 || time < _minYellowTime){
			throw new IllegalArgumentException();
		}
		else{
			this._maxYellowTime = time;
		}
	}
	public double getMaxYellowTime(){
		return this._maxYellowTime;
	}
	public void setRunFor(double time){
		if (time <= 0){
			throw new IllegalArgumentException();
		}
		else{
			this._runFor = time;
		}
	}
	public double getRunFor(){
		return this._runFor;
	}
	public void setAlternating(boolean alternate){
		this._alternating = alternate;
	}
	public boolean getAlternating(){
		return this._alternating;
	}
	
	public static ProjectSetup generateDefaults(){
		if (_defaults == null){
			_defaults = new ProjectSetup();
			return _defaults;
		}
		else{
		return _defaults;
		}
	}
	
	public String toString(){
		String s1 = "Simulation time step (seconds)    [" + _defaults.getTimeStep() +"]\n"; 
		s1 += "Simulation run time (seconds)           [" + _defaults.getRunFor() + "]\n";
		s1 += "Road grid (roads by direction)          [horizontal roads =" + _defaults.getHorizontalRoads() + ",vertical roads =" + _defaults.getVerticalRoads() + "]\n";
		String pattern = (_defaults.getAlternating() ? "alternating roads" : "simple roads");
		s1 += "Traffic pattern                         [" + pattern + "]\n";
		s1 += "Car entry rate (seconds/car)            [min=" + _defaults.getMinCarGenRate() + ",max=" + _defaults.getMaxCarGenRate() + "]\n";
		s1 += "Road segment length (meters)            [min=" + _defaults.getMinRoadLength() + ",max=" + _defaults.getMaxRoadLength() + "]\n";
		s1 += "Intersection length (meters)            [min=" + _defaults.getMinIntersectionLength() + ",max=" + _defaults.getMaxIntersectionLength() + "]\n";
		s1 += "Car length (meters)                     [min=" + _defaults.getMinCarLength() + ",max=" +_defaults.getMaxCarLength() + "]\n";
		s1 += "Car maximum velocity (meters/second)    [min=" + _defaults.getMinVelocity() + ",max=" + _defaults.getMaxVelocity() + "]\n";
		s1 += "Car stop distance (meters)              [min=" + _defaults.getMinStopDistance() + ",max=" +_defaults.getMaxStopDistance() + "]\n";
		s1 += "Car brake distance (meters)             [min=" + _defaults.getMinBrakeDistance() + ",max=" +_defaults.getMaxBrakeDistance() + "]\n";
		s1 += "Traffic light green time (seconds)      [min=" + _defaults.getMinGreenTime() + ",max=" +_defaults.getMaxGreenTime() + "]\n";
		s1 += "Traffic light yellow time (seconds)     [min=" + _defaults.getMinYellowTime() + ",max=" +_defaults.getMaxYellowTime() + "]\n";
		return s1;
	}
}
