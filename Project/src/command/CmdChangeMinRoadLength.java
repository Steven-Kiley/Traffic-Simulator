package command;

import model.ProjectSetup;

public class CmdChangeMinRoadLength implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Road Length";
	private double _length;
	
	public CmdChangeMinRoadLength(double newLength){_length = newLength;}
	
	public boolean run(){
		try{
			_defaults.setMinRoadLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
