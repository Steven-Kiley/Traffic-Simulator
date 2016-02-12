package command;

import model.ProjectSetup;

public class CmdChangeMaxVelocity implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Car Velocity";
	private double _maxVelocity;
	
	public CmdChangeMaxVelocity(double maxV){_maxVelocity = maxV;}
	
	public boolean run(){
		try{
			_defaults.setMaxVelocity(_maxVelocity);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
