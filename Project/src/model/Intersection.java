package model;

import java.util.HashSet;
import java.util.Set;

import model.Light.LightStatus;
import model.ProjectSetup.RoadDirection;
/**
 * Intersection of roads. Contains a Light object that acts as an obstacle when RED.
 * 
 */
public class Intersection implements CarAcceptor {
	
	Road _eastWestNextRoad;
	Road _northSouthNextRoad;
	private double _endPosition;
	private Set<Car> _eastWestCars;
	private Set<Car> _northSouthCars;
	private Light _light;
	ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	private boolean _occupied = false;
	
	public Intersection(){
		  _endPosition = _defaultSetup.getMaxIntersectionLength() - _defaultSetup.getMinIntersectionLength();	
		  _endPosition = (double)(Math.random() * _endPosition) + _defaultSetup.getMinIntersectionLength();
		  _eastWestCars = new HashSet<Car>();
		  _northSouthCars = new HashSet<Car>();
		  _light = new Light();
		
	}
	
	public Light getLight(){return _light;}
	
	/**
	 * Intersection acts as caracceptor. Cars are passed to the intersection from one road,
	 * then passed to the next road, depending on traffic conditions on the road.
	 * 
	 * @param c Car object being accepted to road
	 * @param frontPosition current frontPosition of car requesting accept
	 */
	  public boolean accept(Car c, double frontPosition) {
		  if (c == null){
			  throw new IllegalArgumentException();
		  }
		    if(c._direction == RoadDirection.HORIZONTAL){
		    	_eastWestCars.remove(c);
		    }
		    else if (c._direction == RoadDirection.VERTICAL){
		    	_northSouthCars.remove(c);
		    }
		    else{
		    	throw new IllegalArgumentException("Invalid movement direction");
		    }
		    if(c.getFrontPosition() > _endPosition) {
		    	if (c._direction == RoadDirection.HORIZONTAL){
		    		return _eastWestNextRoad.accept(c, frontPosition-_endPosition);
		    	}
		    	else {
		    		return _northSouthNextRoad.accept(c, frontPosition-_endPosition);
		    	} 
		    }
		    	else {
		      c.setUsableRoad(this);
		      c.setFrontPosition(frontPosition);
		      if (c.getUsableRoad().getDirection() == RoadDirection.HORIZONTAL){
		    	  _eastWestCars.add(c);
		      }
		      else {
		    	  _northSouthCars.add(c);
		      }

		      return true;
		    	}
		    }
		    
	  /**
	   * To help "unstuck" cars that wind up stopped in an intersection.
	   */
	 public boolean isIntersection(){return true;}
	  
	  public double distanceToCarBack(double fromPosition){return 0.0;}
	  
	  /**
	   * Checks northSouth road for traffic via _cars list.  If clear, it returns _bigNum
	   * which allows car object to use its maxVelocity.
	   * 
	   * @param fromPosition position of requesting Car object
	   * @return the least distance between requesting car and obstacle car
	   */
	  private double distanceToCarNorthSouth(double fromPosition) {
			double distanceToCar;			    
			double leastDistance = _defaultSetup.getBigNum();	
			for (Car c : _northSouthCars){			    	
				double carBack = c.backPosition();			    	
				if (carBack >= fromPosition){			    		
					distanceToCar = c.backPosition() - fromPosition;			    		
					if ((distanceToCar) < leastDistance){			    			
						leastDistance = distanceToCar; 			    		
					}			    	
				}
			}
			return leastDistance;  
		  }
		  
	  /**
	   * Checks eastWest road for traffic via _cars list.  If clear, it returns _bigNum
	   * which allows car object to use its maxVelocity.
	   * 
	   * @param fromPosition position of requesting Car object
	   * @return the least distance between requesting car and obstacle car
	   */
	  private double distanceToCarEastWest(double fromPosition) {
			double distanceToCar;			    
			double leastDistance = _defaultSetup.getBigNum();	
			for (Car c : _eastWestCars){			    	
				double carBack = c.backPosition();			    	
				if (carBack >= fromPosition){			    		
					distanceToCar = c.backPosition() - fromPosition;			    		
					if (distanceToCar < leastDistance){			    			
						leastDistance = distanceToCar;			    		
					}			    	
				}
			}
			return leastDistance;  
		  }
	  
	  /**
	   * Allows for detection of obstacles. Stop color for light can return the end of incoming road as
	   * an obstacle or for green light return the first obstacle on the outgoing road, if any.  
	   * 
	   * @param c Car object that is requesting obstacle
	   * @param fromPosition starting location of requesting Car object
	   * @param dir direction of requesting Car object, used to determine process
	   */
	  public double distanceToObstacle(Car c, double fromPosition, RoadDirection dir) {

		  if (_eastWestCars.contains(c)){
			  return _eastWestNextRoad.distanceToObstacle(c, c.frontPosition(), c._direction);
		  }
		  else if (_eastWestCars.contains(c)){
			  return _northSouthNextRoad.distanceToObstacle(c, c.frontPosition(), c._direction);
		  }
		  else{
		  if (dir == RoadDirection.HORIZONTAL){
			  if (_light.getStatus() == LightStatus.GREEN_EAST_WEST || _light.getStatus() == LightStatus.YELLOW_EAST_WEST){
				    double obstaclePosition = this.distanceToCarEastWest(fromPosition);
			    	if (obstaclePosition == Double.POSITIVE_INFINITY) {
			    		double distanceToEnd = this._endPosition - fromPosition;
			    		obstaclePosition 
			    		= _eastWestNextRoad.distanceToObstacle(c, 0.0, RoadDirection.HORIZONTAL) + distanceToEnd;
			    	}
			    		return obstaclePosition-fromPosition;
			  }
			  else{
				  return -10.0;
			  }

		  }
		  else{
			  if(_light.getStatus() == LightStatus.GREEN_NORTH_SOUTH || _light.getStatus() == LightStatus.YELLOW_NORTH_SOUTH){
				    double obstaclePosition = this.distanceToCarNorthSouth(fromPosition);
			    	if (obstaclePosition == Double.POSITIVE_INFINITY) {
			    		double distanceToEnd = this._endPosition - fromPosition;
			    		obstaclePosition 
			    		= _northSouthNextRoad.distanceToObstacle(c, 0.0, RoadDirection.VERTICAL) + distanceToEnd;
			    	}
			    		return obstaclePosition-fromPosition;
			  }
			  else{
				  return -10.0;
			  }
			  }
		  }
		  }
		  
	  
		  public double getEndPosition(){return _endPosition;}
		  
		  public void setEastWestRoad(CarAcceptor road){
			  if (road == null){
				  throw new IllegalArgumentException();
			  }
			  this._eastWestNextRoad = (Road) road;
		  }
		  
		  public CarAcceptor getEastWestRoad(){return _eastWestNextRoad;}
		  
		  public void setNextVertRoad(CarAcceptor road){
			  if (road == null){
				  throw new IllegalArgumentException();
			  }
			  _northSouthNextRoad = (Road) road;
		  }
		  public CarAcceptor getNextVertRoad(){return _northSouthNextRoad;}
		   
		  public RoadDirection getDirection(){return null;}
}
