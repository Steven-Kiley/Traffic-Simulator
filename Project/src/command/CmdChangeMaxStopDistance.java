package command;

import model.ProjectSetup;

public class CmdChangeMaxStopDistance implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Car Stop Distance";
	private double _distance;
	
	public CmdChangeMaxStopDistance(double newDistance){_distance = newDistance;}
	
	public boolean run(){
		try{
			_defaults.setMaxStopDistance(_distance);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
