package model;

import java.util.HashSet;
import java.util.Set;
import model.ProjectSetup.RoadDirection;


/**
 * Road object. Provides interaction between car objects, allowing methods for cars on an
 * instance of a road object to identify each other as obstacles and determine movement length.
 */


public class Road implements CarAcceptor {

	  Set<Car> _cars = new HashSet<Car>();
	  CarAcceptor _nextRoad;
	  double _length;
	  private ProjectSetup _defaults = ProjectSetup.generateDefaults();
	  public RoadDirection _direction;
	
	  public Road(double length, RoadDirection dir){
		  _direction = dir;
		  _length = length;
	  }
	  
	  /**
	   * Method from CarAcceptor Interface. Tests and adds passing cars to
	   * _cars HashSet.  
	   * 
	   * @see CarAcceptor
	   * @param c Car object being accepted
	   * @param frontPosition of Car 
	   */
		public boolean accept(Car c, double frontPosition) {
			if (c == null){
				throw new IllegalArgumentException();
			}
			if (this._cars != null) {
				this._cars.remove(c);
			}
			if(frontPosition>_length) {
				return _nextRoad.accept(c,frontPosition-_length);
			} else {
				c.setUsableRoad(this);
				c.setFrontPosition(frontPosition);
				_cars.add(c);
				return true;
			}
		}
		
		
		/**
		 * Uses a cars current _frontPosition to search through _cars 
		 * for a car that is acting as an "obstacle". Obstacle status 
		 * is any car who is between this cars current frontPosition and 
		 * the next position its run() method would arrive at.s
		 * 
		 * @param fromPosition starting position of requesting car
		 * @return either _bigNum for no cars, or distance to car
		 */
		public double distanceToCarBack(double fromPosition) { 		    
			double distanceToCar;			    
			double maxMove = _defaults.getBigNum();	
			for (Car c : _cars){			    	
				double carBack = c.backPosition();			    	
				if (carBack >= fromPosition){			    		
					distanceToCar = c.backPosition() - fromPosition;			    		
					if ((distanceToCar) < maxMove){			    			
						maxMove = distanceToCar; 		    		
					}			    	
				}
			}
			return maxMove;  
		}
			  
		  
			  /**
			   * This method calculates the distance to the Car object's next obstacle.
			   * If there is no car in the way, then this method returns the next obstacle
			   * from the next Road, plus the full remaining distance of this road.
			   * 
			   * @param c The Car object requesting the distance check
 			   * @param fromPosition the position of the requesting Car
			   * @param dir the direction of the car, necessary only because of Intersections
			   * @return the distance to the next obstacle, whatever it is.
			   */
		public double distanceToObstacle(Car c, double start, RoadDirection dir) {			    
			double obstaclePosition = this.distanceToCarBack(start);			    
			if (obstaclePosition == _defaults.getBigNum()) {			    				    	
				double end = this._length - start;			    			    	
				obstaclePosition = _nextRoad.distanceToObstacle(c, 0.0, dir) + end;			    	
				return obstaclePosition;			    
			}			    	
			return obstaclePosition-start;			  
		}
		
		
		public boolean isIntersection(){return false;}
		
		public void setNextRoad(CarAcceptor cA){
			if (cA == null){
				throw new IllegalArgumentException();
			}
			this._nextRoad = cA;
		}
		
		public CarAcceptor getNextRoad(){
			return this._nextRoad;
		}
		
		public double getEndPosition(){
			return this._length;
		}
		
		public RoadDirection getDirection(){
			return this._direction;
		}
	  
		public Set<Car> getCars(){
			return this._cars;
		}
}
