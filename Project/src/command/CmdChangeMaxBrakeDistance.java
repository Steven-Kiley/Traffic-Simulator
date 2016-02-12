package command;

import model.ProjectSetup;

public class CmdChangeMaxBrakeDistance implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Car Brake Distance";
	private double _maxBrake;
	
	public CmdChangeMaxBrakeDistance(double dist){_maxBrake = dist;}
	
	public boolean run(){
		try{
			_defaults.setMaxBrakeDistance(_maxBrake);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
