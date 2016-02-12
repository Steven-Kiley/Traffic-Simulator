package model;

import model.ProjectSetup.RoadDirection;
import animation.MP;
import util.TimeServer;

	public class Source implements Agent{
		double _currentTime;
		Road _firstRoad;
		ProjectSetup _defaults;
		double _carGenRate;
		private CarFactory _carMaker = new CarFactory();
		TimeServer _timeKeeper;
		
		/**
		 * Source Object, to act as initiation point for cars.
		 * @param acceptor Road object connected to the Source object
		 */
		public Source(Road acceptor){
			_defaults = ProjectSetup.generateDefaults();
			this.setRoad(acceptor);
			_carGenRate = _defaults.getMinCarGenRate() + (double)(Math.random()*((_defaults.getMaxCarGenRate()- _defaults.getMinCarGenRate())));
			_timeKeeper = _defaults.getTimeServer();
			_timeKeeper.enqueue(_timeKeeper.currentTime(), this);
		}
		
		public void setRoad(Road r){
			if(r == null){
				throw new IllegalArgumentException();
			}
			else{
				_firstRoad = r;
			}
		}
		
		/**
		 * Run method for Agent Interface.
		 * 
		 * Ensures there is space at the start of _firstRoad to place a new car. If not, will skip car generation.  
		 * 
		 * Uses a CarFactory object to add a new car to the beginning of 
		 * the Road, car becomes Agent enqueued on TimeServer.
		 */
		public void run(){
			if (_firstRoad == null){
				throw new IllegalArgumentException("No attached Road");
			}
			else if (_firstRoad.distanceToObstacle(_carMaker.generate(RoadDirection.HORIZONTAL), 0.0, _firstRoad.getDirection()) < MP.carLength){
				_timeKeeper.enqueue(_timeKeeper.currentTime() + _carGenRate, this);
			}
			else if (_firstRoad.getCars().size() >= 5){
				_timeKeeper.enqueue(_timeKeeper.currentTime() + _carGenRate, this);
			}
			else{
				RoadAndCarFactoryInterface _carMaker = new CarFactory();
				if (_firstRoad._direction == RoadDirection.HORIZONTAL){
					Car c = (Car) _carMaker.generate(RoadDirection.HORIZONTAL);
					if(_firstRoad.accept(c, 0.0)){
						System.out.println("Car accepted by road");
						_timeKeeper.enqueue(_timeKeeper.currentTime() + _defaults.getTimeStep(), c);
						_timeKeeper.enqueue(_timeKeeper.currentTime() + _carGenRate, this);
					}
					else{
						throw new IllegalArgumentException();
					}
				}
				else if (_firstRoad._direction == RoadDirection.VERTICAL){
					Car c = (Car) _carMaker.generate(RoadDirection.VERTICAL);
					if(_firstRoad.accept(c, 0.0)){
						System.out.println("Car accepted by road");
						_timeKeeper.enqueue(_timeKeeper.currentTime() + _defaults.getTimeStep(), c);
						_timeKeeper.enqueue(_timeKeeper.currentTime() + _carGenRate, this);
					}
					else{
						throw new IllegalArgumentException();
					}
				}
				
			}
			
		}
		
		public CarAcceptor getNextRoad(){return _firstRoad;}
		

}
