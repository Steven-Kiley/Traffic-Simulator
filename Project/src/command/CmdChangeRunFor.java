package command;

import model.ProjectSetup;

public class CmdChangeRunFor implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Run Simulation for (in seconds)";
	double _time;
	
	public CmdChangeRunFor(double newTime){_time = newTime;}
	
	public boolean run(){
		try{
			_defaults.setRunFor(_time);
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
	
	public String getMenuTitle(){return _menuTitle;}

}
