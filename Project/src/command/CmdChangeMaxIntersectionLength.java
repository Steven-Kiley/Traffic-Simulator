package command;

import model.ProjectSetup;

public class CmdChangeMaxIntersectionLength implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Maximum Intersection Length";
	private double _length;
	
	public CmdChangeMaxIntersectionLength(double newLength){_length = newLength;}
	
	public boolean run(){
		try{
			_defaults.setMaxIntersectionLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
