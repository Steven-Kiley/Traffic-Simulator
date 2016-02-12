package model;

import util.TimeServer;

public class Light implements Agent {
	private ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
	private TimeServer _timeKeeper = _defaultSetup.getTimeServer();
	private double _length = _defaultSetup.getMaxIntersectionLength();
	private double _greenDuration;
	private double _yellowDuration;
	private LightStatus _lightStatus;
	private boolean _horizCycle = true;
	private boolean _vertCycle = false;
	CarAcceptor _attachedRoad;

	public enum LightStatus {
		GREEN, RED, YELLOW, GREEN_NORTH_SOUTH, YELLOW_NORTH_SOUTH, GREEN_EAST_WEST, YELLOW_EAST_WEST
	}
  
	/**
	 * Creates a Light object, creates the object with
	 * east/west roads green lit.
	 */
  public Light(){
	  this.setLightStatus(LightStatus.GREEN_EAST_WEST);
	  
	  _greenDuration = _defaultSetup.getMaxGreenTime() - _defaultSetup.getMinGreenTime();
	  _greenDuration = (double)(Math.random() * _greenDuration) + _defaultSetup.getMinGreenTime();
	  _yellowDuration = _defaultSetup.getMaxYellowTime() - _defaultSetup.getMinYellowTime();
	  _yellowDuration = (double)(Math.random() * _yellowDuration) + _defaultSetup.getMinYellowTime();
	  
	  _timeKeeper.enqueue(_timeKeeper.currentTime() + _greenDuration, this);
  }
  
  public LightStatus getStatus(){return this._lightStatus;}
  public void setLightStatus(LightStatus newStatus){this._lightStatus = newStatus;}
  
  /**
   * This is a series of switch statements. A strategy pattern would be better here, but time was not on 
   * my side. Checks current LightStatus and changes accordingly.
   */
  public void run(){
	  switch (this._lightStatus){
	  case GREEN_EAST_WEST: 
		  this.setLightStatus(LightStatus.YELLOW_EAST_WEST);
		  System.out.println("getStatus(): " + this.getStatus());
		  System.out.println("Status Changed to Yellow East/West");
		  _timeKeeper.enqueue(_timeKeeper.currentTime() + _yellowDuration, this);
		  break;
	  case YELLOW_EAST_WEST:
		  this.setLightStatus(LightStatus.GREEN_NORTH_SOUTH);
		  System.out.println("getStatus(): " + this.getStatus());
		  System.out.println("Status Changed to Green North/South");
		  _timeKeeper.enqueue(_timeKeeper.currentTime() + _greenDuration, this);
		  break;
	  case GREEN_NORTH_SOUTH: 
		  this.setLightStatus(LightStatus.YELLOW_NORTH_SOUTH);
		  System.out.println("getStatus(): " + this.getStatus());
		  System.out.println("Status Changed to Yellow North/South");
		  _timeKeeper.enqueue(_timeKeeper.currentTime() + _yellowDuration, this);
		  break;
	  case YELLOW_NORTH_SOUTH:
		  this.setLightStatus(LightStatus.GREEN_EAST_WEST);
		  System.out.println("getStatus(): " + this.getStatus());
		  System.out.println("Status Changed to Green East/West");
		  _timeKeeper.enqueue(_timeKeeper.currentTime() + _greenDuration, this);
		  break;
	  default:
		  this.setLightStatus(LightStatus.GREEN_EAST_WEST);
		  _timeKeeper.enqueue(_timeKeeper.currentTime() + _greenDuration, this);
		  break;	  
	  }
  }
  public double getGreenDuration(){return this._greenDuration;}
  public double getYellowDuration(){return this._yellowDuration;}
  
}
