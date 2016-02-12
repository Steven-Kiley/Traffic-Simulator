package command;

import model.ProjectSetup;

public class CmdChangeMinStopDistance implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Car Stop Distance";
	private double _distance;
	
	public CmdChangeMinStopDistance(double newDistance){_distance = newDistance;}
	
	public boolean run(){
		try{
			_defaults.setMinStopDistance(_distance);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
