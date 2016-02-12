package command;

import model.ProjectSetup;

public class CmdChangeMaxCarLength implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Car Length";
	private double _length;
	
	public CmdChangeMaxCarLength(double newLength){_length = newLength;}
	
	public boolean run(){
		try{
			_defaults.setMaxCarLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
