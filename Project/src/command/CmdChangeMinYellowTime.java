package command;

import model.ProjectSetup;

public class CmdChangeMinYellowTime implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Yellow Time";
	private double _time;
	
	public CmdChangeMinYellowTime(double newTime){_time = newTime;}
	
	public boolean run(){
		try{
			_defaults.setMinYellowTime(_time);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
