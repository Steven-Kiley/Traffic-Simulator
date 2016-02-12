package animation;

import java.util.ArrayList;
import java.util.List;

import model.CarAcceptor;
import model.Intersection;
import model.ProjectSetup.RoadDirection;
import model.Road;
import model.RoadFactory;
import model.ProjectSetup;
import model.Sink;
import model.Source;

public class AlternatingGridBuilder {
	ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	RoadFactory roadFactory = new RoadFactory();
	
	/**
	 * Creates an alternating grid by using a two dimensional array to store the roads to be added.
	 * 
	 * @param builder the AnimatorBuilder that's set up as an observer
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public  void buildModel(AnimatorBuilder builder, int rows, int columns){
	    List<CarAcceptor> roads = new ArrayList<CarAcceptor>();
	    Intersection[][] intersectionObjects = new Intersection[rows][columns];	    
	    for (int i=0; i<rows; i++) {
	      for (int j=0; j<columns; j++) {
	        intersectionObjects[i][j] = new Intersection();
	        builder.addLight(intersectionObjects[i][j].getLight(), i, j);
	      }
	    }    
	    //for EastWest roads
	    boolean rightToLeft = false;
	    for (int i = 0; i < rows; i++){
	    	if (!rightToLeft){
	    		for (int j = 0; j <= columns; j++){
	    			if (j == 0){
	    				Road road = roadFactory.generate(RoadDirection.HORIZONTAL);
	    				Source source = new Source(road);
	    				road.setNextRoad(intersectionObjects[i][j]);
	    				builder.addHorizontalRoad(road, i, j, false);
	    			}  
	    			else if (j > 0 && j < columns){
	    				Road road = roadFactory.generate(RoadDirection.HORIZONTAL);
	    				intersectionObjects[i][j-1].setEastWestRoad(road);
	    				road.setNextRoad(intersectionObjects[i][j]);
	    				builder.addHorizontalRoad(road, i, j, false);
	    			}
	    			else{
	    				CarAcceptor sink = new Sink();
	    				Road road = roadFactory.generate(RoadDirection.HORIZONTAL);
	    				road.setNextRoad(sink);
	    				intersectionObjects[i][j-1].setEastWestRoad(road);
	    				builder.addHorizontalRoad(road, i, j, false);
	    			}	
	    		}
	    	}
	    	
	    	else{
	    		for (int j = columns; j >= 0; j--){
	    			if (j == columns){
	    				Road road = roadFactory.generate(RoadDirection.HORIZONTAL);
	    				Source source = new Source(road);
	    				road.setNextRoad(intersectionObjects[i][j-1]);
	    				builder.addHorizontalRoad(road, i, j, true);
	    			}
	    			else if (j < columns && j > 0){
	    				Road road = roadFactory.generate(RoadDirection.HORIZONTAL);
	    				intersectionObjects[i][j].setEastWestRoad(road);
	    				road.setNextRoad(intersectionObjects[i][j-1]);
	    				builder.addHorizontalRoad(road, i, j, true);
	    			}
	    			else{
	    				CarAcceptor sink = new Sink();
	    				Road road = roadFactory.generate(RoadDirection.HORIZONTAL);
	    				road.setNextRoad(sink);
	    				intersectionObjects[i][j].setEastWestRoad(road);
	    				builder.addHorizontalRoad(road, i, j, true);
	    			}
	    		}
	    	}
	    	rightToLeft = !rightToLeft;
	    }
	    
	    //for NorthSouth roads

	    
	    boolean bottomToTop = false;
	    for (int j = 0; j < columns; j++){
	    	if (!bottomToTop){
	    		for (int i = 0; i <= rows; i++){
	    			if (i == 0){
	    				Road road = roadFactory.generate(RoadDirection.VERTICAL);
	    				Source source = new Source(road);
	    				road.setNextRoad(intersectionObjects[i][j]);
	    				builder.addVerticalRoad(road, i, j, false);
	    			}
	    			else if (i > 0 && i < rows){
	    				Road road = roadFactory.generate(RoadDirection.VERTICAL);
	    				intersectionObjects[i-1][j].setNextVertRoad(road);
	    				road.setNextRoad(intersectionObjects[i][j]);
	    				builder.addVerticalRoad(road, i, j, false);
	    			}
	    			else{
	    				CarAcceptor sink = new Sink();
	    				Road road = roadFactory.generate(RoadDirection.VERTICAL);
	    				road.setNextRoad(sink);
	    				intersectionObjects[i-1][j].setNextVertRoad(road);
	    				builder.addVerticalRoad(road, i, j, false);
	    			}
	    		}
	    	}
	    	else{
	    		for (int i = rows; i >= 0; i--){
	    			if (i == rows){
	    				Road road = roadFactory.generate(RoadDirection.VERTICAL);
	    				Source source = new Source(road);
	    				road.setNextRoad(intersectionObjects[i-1][j]);
	    				builder.addVerticalRoad(road, i, j, true);
	    			}
	    			else if (i < rows && i > 0){
	    				Road road = roadFactory.generate(RoadDirection.VERTICAL);
	    				intersectionObjects[i][j].setNextVertRoad(road);
	    				road.setNextRoad(intersectionObjects[i-1][j]);
	    				builder.addVerticalRoad(road, i, j, true);
	    			}
	    			else{
	    				CarAcceptor sink = new Sink();
	    				Road road = roadFactory.generate(RoadDirection.VERTICAL);
	    				road.setNextRoad(sink);
	    				intersectionObjects[i][j].setNextVertRoad(road);
	    				builder.addVerticalRoad(road, i, j, true);
	    			}
	    		}
	    	}
	    	bottomToTop = !bottomToTop;
	    }
	}
}
