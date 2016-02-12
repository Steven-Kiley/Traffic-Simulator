package command;

import model.ProjectSetup;

public class CmdChangeMinCarLength implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Car Length";
	private double _length;
	
	public CmdChangeMinCarLength(double newLength){_length = newLength;}
	
	public boolean run(){
		try{
			_defaults.setMinCarLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
