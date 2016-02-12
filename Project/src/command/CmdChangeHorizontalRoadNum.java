package command;

import model.ProjectSetup;

public class CmdChangeHorizontalRoadNum implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle = "Number of East-West roads";
	private int _roads;
	public CmdChangeHorizontalRoadNum(int r){_roads = r;}
	
	public boolean run(){
		try{
			_defaults.setHorizontalRoads(_roads);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

			
		public String getMenuTitle(){return _menuTitle;}
}
