package command;

import model.ProjectSetup;
import model.ProjectSetup;
import animation.Model;
import animation.SwingAnimatorBuilder;
import util.TimeServer;

public class CmdRun implements Command {
	
	private String _menuTitle = "Running";
	
	public boolean run(){
		  ProjectSetup _defaults = ProjectSetup.generateDefaults();
		  TimeServer _timeKeeper = _defaults.getTimeServer();
		  Model m = new Model(new SwingAnimatorBuilder(), _defaults.getHorizontalRoads(), _defaults.getVerticalRoads());
		  m.run(_defaults.getRunFor());
		  m.dispose();
	    System.exit(0);
	    return true;
	}

	public String getMenuTitle(){return _menuTitle;}
}
