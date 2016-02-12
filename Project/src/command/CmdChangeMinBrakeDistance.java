package command;

import model.ProjectSetup;

public class CmdChangeMinBrakeDistance implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Brake Distance";
	private double _distance;
	
	public CmdChangeMinBrakeDistance(double newDistance){_distance = newDistance;}
	
	public boolean run(){
		try{
			_defaults.setMinBrakeDistance(_distance);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
