package command;

import model.ProjectSetup;

public class CmdChangeMaxGreenTime implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Green Light Time";
	private double _time;
	
	public CmdChangeMaxGreenTime(double newTime){_time = newTime;}
	
	public boolean run(){
		try{
			_defaults.setMaxGreenTime(_time);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
