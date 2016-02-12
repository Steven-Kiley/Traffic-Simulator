package command;

import model.ProjectSetup;

public class CmdChangeMinIntersectionLength implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Minimum Intersection Length";
	private double _length;
	
	public CmdChangeMinIntersectionLength(double newLength){_length = newLength;}
	
	public boolean run(){
		try{
			_defaults.setMinIntersectionLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
