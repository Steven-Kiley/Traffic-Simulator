package command;

import model.ProjectSetup;

public class CmdChangeMaxCarGen implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Car Generation Rate";
	private double _rate;
	
	public CmdChangeMaxCarGen(double newRate){_rate = newRate;}
	
	public boolean run(){
		try{
			_defaults.setMaxCarGenRate(_rate);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

		public String getMenuTitle(){return _menuTitle;}
}
