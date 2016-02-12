package command;

import model.ProjectSetup;

public class CmdChangeMinCarGen implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Car Generation Rate";
	private double _rate;
	
	public CmdChangeMinCarGen(double newRate){_rate = newRate;}
	
	public boolean run(){
		try{
			_defaults.setMinCarGenRate(_rate);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
