package command;

import model.ProjectSetup;

public class CmdChangeMinGreenTime implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Green Time";
	private double _time;
	
	public CmdChangeMinGreenTime(double newTime){_time = newTime;}
	
	public boolean run(){
		try{
			_defaults.setMinGreenTime(_time);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
