package model;

import model.ProjectSetup.RoadDirection;

public class RoadFactory implements RoadAndCarFactoryInterface{
	private ProjectSetup _defaults = ProjectSetup.generateDefaults();
	
	/**
	 * Factory method for making a new Road object. Only way for outside package access to roads.  
	 * 
	 * @param direction RoadDirection enum for cars, will be either VERTICAL or HORIZONTAL.
	 * @return a new Road object
	 */
	public Road generate(RoadDirection direction){
		  double endPosition = _defaults.getMaxRoadLength() - _defaults.getMinRoadLength();	
		  endPosition = (double)(Math.random() * endPosition) + _defaults.getMinRoadLength();
		
		  Road road = new Road(endPosition, direction);
		  
		  return road;
	}

}
