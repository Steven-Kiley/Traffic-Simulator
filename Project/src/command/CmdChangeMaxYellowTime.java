package command;

import model.ProjectSetup;

public class CmdChangeMaxYellowTime implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Yellow Time";
	private double _time;
	
	public CmdChangeMaxYellowTime(double newTime){_time = newTime;}
	
	public boolean run(){
		try{
			_defaults.setMaxYellowTime(_time);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
