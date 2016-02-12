package command;

import model.ProjectSetup;

public class CmdChangeMaxRoadLength implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Road Length";
	private double _length;
	
	public CmdChangeMaxRoadLength(double newLength){_length = newLength;}
	
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
