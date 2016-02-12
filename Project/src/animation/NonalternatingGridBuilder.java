package animation;

import java.util.ArrayList;
import java.util.List;

import model.*;
import model.ProjectSetup.RoadDirection;

public class NonalternatingGridBuilder {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	RoadFactory _roadMaker = new RoadFactory();
	
	/**
	 * Builds a structure where the the roads do not alternate direction. That is to say,
	 * the horizontal roads don't alternate between east and west, they simply go one 
	 * direction. The same is true of the north south roads.  
	 * 
	 * @param builder the AnimatorBuilder that's set up as an observer
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public  void buildModel(AnimatorBuilder builder, int rows, int columns){
	    List<CarAcceptor> roads = new ArrayList<CarAcceptor>();
	    Intersection[][] intersectionObjects = new Intersection[rows][columns];	    
	    
	    // Add Lights
	    for (int i=0; i<rows; i++) {
	      for (int j=0; j<columns; j++) {
	        intersectionObjects[i][j] = new Intersection();
	        builder.addLight(intersectionObjects[i][j].getLight(), i, j);
	      }
	    }
	    
	    
	  // east/west roads
	
	    for (int i = 0; i < rows;  i++){
	    	for (int j = 0; j <= columns; j++){
	    		if (j == 0){
	    			Road road = _roadMaker.generate(RoadDirection.HORIZONTAL);
	    			Source source = new Source(road);
	    			road.setNextRoad(intersectionObjects[i][j]);
	    			builder.addHorizontalRoad(road, i, j, false);
	    }
	    else if (j > 0 && j < columns){
	    	Road road = _roadMaker.generate(RoadDirection.HORIZONTAL);
	    	intersectionObjects[i][j-1].setEastWestRoad(road);
	    	road.setNextRoad(intersectionObjects[i][j]);
	    	builder.addHorizontalRoad(road, i, j, false);
	    }
	    else{
	    	CarAcceptor sink = new Sink();
	    	Road road = _roadMaker.generate(RoadDirection.HORIZONTAL);
	    	road.setNextRoad(sink);
	    intersectionObjects[i][j-1].setEastWestRoad(road);
	    	builder.addHorizontalRoad(road, i, j, false);
	    }
	    	}
	    }

	    // north/south roads

	    for (int j = 0; j < columns; j++){
	    	for (int i = 0; i <= rows; i++){
	    		if (i == 0){
	    			Road road = _roadMaker.generate(RoadDirection.VERTICAL);
	    			Source source = new Source(road);
	    			road.setNextRoad(intersectionObjects[i][j]);
	    			builder.addVerticalRoad(road, i, j, false);
	    		}
	    		else if (i > 0 && i < rows){
	    			Road road = _roadMaker.generate(RoadDirection.VERTICAL);
	    			intersectionObjects[i-1][j].setNextVertRoad(road);
	    			road.setNextRoad(intersectionObjects[i][j]);
	    			builder.addVerticalRoad(road, i, j, false);
	    		}
	    		else{
	    			CarAcceptor sink = new Sink();
	    			Road road = _roadMaker.generate(RoadDirection.VERTICAL);
	    			road.setNextRoad(sink);
	    			intersectionObjects[i-1][j].setNextVertRoad(road);
	    			builder.addVerticalRoad(road, i, j, false);
	    		}
	    	}
	    }
	    
	}
}

