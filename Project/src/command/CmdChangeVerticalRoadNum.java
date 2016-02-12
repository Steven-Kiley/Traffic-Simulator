package command;

import model.ProjectSetup;

public class CmdChangeVerticalRoadNum implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Number of North-South Roads";
	private int _roads;
	public CmdChangeVerticalRoadNum(int r){_roads = r;}
	public boolean run(){
		try{
			_defaults.setVerticalRoads(_roads);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
