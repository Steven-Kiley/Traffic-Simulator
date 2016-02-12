package command;

import model.ProjectSetup;

public class CmdChangeAlternating implements Command {
	ProjectSetup _defaults = ProjectSetup.generateDefaults();
	private String _menuTitle;
	private int _type;
	
	public CmdChangeAlternating(int in){
		if (in != 1 && in != 2){
			throw new IllegalArgumentException();
		}
		else{
			_type= in;
		}
	}
	
	public boolean run(){
		if (_type == 1){
			try{
				_defaults.setAlternating(true);
				return true;
			} catch (IllegalArgumentException e){
				return false;
			}
		}
		else{
			try{
				_defaults.setAlternating(false);
				return true;
			} catch (IllegalArgumentException e){
				return false;
			}
		}
	}

		public void fillMenuTitle(){
			String common = "Road Grid is set up to ";
			if(this._type == 1){_menuTitle = (common + "alternate road directions");}
			else{_menuTitle = common + "not alternate road directions";}
		}
	
			
		public String getMenuTitle(){ fillMenuTitle(); return _menuTitle;}
}
