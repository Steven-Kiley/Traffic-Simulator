package command;

import model.ProjectSetup;

public class CmdChangeMinVelocity implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Car Velocity";
	private double _minVelocity;
	
	public CmdChangeMinVelocity(double minV){_minVelocity = minV;}
	
	public boolean run(){
		try{
			_defaults.setMinVelocity(_minVelocity);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
