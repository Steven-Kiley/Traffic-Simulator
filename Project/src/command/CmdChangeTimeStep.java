package command;

import model.ProjectSetup;

public class CmdChangeTimeStep implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Time Step";
	double _time;
	
	public CmdChangeTimeStep(double newTime){_time = newTime;}
	
	public boolean run(){
		try{
			_defaults.setTimeStep(_time);
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
	
	public String getMenuTitle(){return _menuTitle;}

}
