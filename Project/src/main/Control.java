package main;

import userinterface.*;
import animation.*;
import java.util.Iterator;
import java.util.Comparator;
import model.ProjectSetup;
import command.*;

/**
 * modified assignment 2 code to run for simulation
 */

class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int TOPMENU = 2;
  private static final int BOTTOMMENU = 3;
  private static final int NUMSTATES = 10;
  private UIMenu[] _menus;
  private int _state;
  ProjectSetup _defaults = ProjectSetup.generateDefaults();
  private UIFormTest _numberTest;  
  private UI _ui;
  
  Control(UI ui) {
    _ui = ui;

    _menus = new UIMenu[NUMSTATES];
    _state = TOPMENU;
    addTOPMENU(TOPMENU);
    addBOTTOMMENU(BOTTOMMENU);
    addEXIT(EXIT);
    

    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
        	  Double.parseDouble(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
  }
  
    void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  

  private double entryFormForDoubles(String menuTitleIn){
	  UIFormBuilder f = new UIFormBuilder();
	  String menuTitle = menuTitleIn;
	  f.add(menuTitle, _numberTest);
	  UIForm form = f.toUIForm("Enter New Value: ");
	  String[] result1 = _ui.processForm(form);
	  double in = Double.parseDouble(result1[0]);
	  return in;
  }
  
  
  
  private int entryFormForIntegers(String menuTitleIn){
	  UIFormBuilder f = new UIFormBuilder();
	  String menuTitle = menuTitleIn;
	  f.add(menuTitle, _numberTest);
	  UIForm form = f.toUIForm("Enter New Value: ");
	  String[] result1 = _ui.processForm(form);
	  int in = Integer.parseInt(result1[0]);
	  return in;
  }
  
  
  private int testForm(String menuTitleIn){
	  UIFormBuilder f = new UIFormBuilder();
	  String menuTitle = menuTitleIn;
	  f.add(menuTitle, _numberTest);
	  UIForm form = f.toUIForm("Enter 1 to alternate roads, 2 for non-alternating roads");
	  String[] result1 = _ui.processForm(form);
	  int in = Integer.parseInt(result1[0]);
	  return in;
  }
  

  /**
   *Second level of menu, also stolen from assignment 2.
   * 
   */
  private void addTOPMENU(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("doh!");
        }
      });
    m.add("Run Simulation",
      new UIMenuAction() {
        public void run() {
          boolean input;
          Command simRun = new CmdRun();
          if (! simRun.run()) {
            _ui.displayError("Command failed");
          }
          
          
        }
      });
    m.add("Change Simulation Parameters", new UIMenuAction(){
    	public void run(){
    		_state = BOTTOMMENU;
    	}
    });
    m.add("Exit", new UIMenuAction(){
    	public void run(){
    		_state = EXIT;
    	}
    });
    
    _menus[stateNum] = m.toUIMenu("Main Menu");
  }
    
  private void addBOTTOMMENU(int stateNum){
	  UIMenuBuilder m = new UIMenuBuilder();
	  
	  m.add("Default", new UIMenuAction() { public void run() {
		  _ui.displayError("Unable to create UI");
	  } });
	  m.add("show current values", new UIMenuAction(){
		  public void run(){
			  System.out.println(_defaults.toString());
		  }
	  });
	  m.add("Simulation time step", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Time Step");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeTimeStep(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Time Step Change Attempt Failed");
			  }
		  }
	  });
	  m.add("Simulation Run Time", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Run Time");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeRunFor(valueIn);
			  if(!cmd.run()){
				  _ui.displayError("Run Time Change Attempt Failed");
			  }
		  }
	  });
	  m.add("Grid Size", new UIMenuAction(){
		  public void run(){
			  int valueIn;
			  try{
			  valueIn = entryFormForIntegers("Rows in Grid");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeHorizontalRoadNum(valueIn);
			  if(!cmd.run()){
				  _ui.displayError("Horizontal road number change error. Input was negative or NaN");
			  }
			  try{
			  valueIn = entryFormForIntegers("Columns in Grid");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeVerticalRoadNum(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Vertical road number changer error. Input was negative or NaN");
			  }
		  }
	  });
	  m.add("Traffic Pattern", new UIMenuAction(){
		 public void run(){
			 int valueIn;
			 try{
			 valueIn = testForm("Traffic Pattern");
			 } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			 try{
			 Command cmd = new CmdChangeAlternating(valueIn);
			 if (!cmd.run()){
				 _ui.displayError("Cannot change road alternation. Input error.");
			 }
			 } catch (IllegalArgumentException e){
				 _ui.displayError("Only valid input is 1 or 2.");
			 }
		 }
	  });
	  m.add("Car Entry Rate", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Car Entry Rate");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxCarGen(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Car Generation Rate Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Car Entry Rate");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinCarGen(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Car Generation Rate Change Attempt Failed.");
			  }
		  }
	  });
	  m.add("Road Segment Length", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Road Length");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxRoadLength(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Max Road Length Change Attempt Failed. Check that input > min value");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Road Length");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinRoadLength(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Road Length Change Attempt Failed.");
			  }
		  }
	  });
	  m.add("Intersection Length", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Intersection Length");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxIntersectionLength(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Intersection Length Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Intersection Length");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinIntersectionLength(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Intersection Length Change Attempt Failed.");
			  }
		  }
	  });
	  m.add("Car Length", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Car Length");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxCarLength(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Car Length Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Car Length");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinCarLength(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Car Length Change Attempt Failed.");
			  }
		  } 
	  });
	  m.add("Car Maximum Velocity", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Maximum Max Velocity");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxVelocity(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Velocity Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Minimum Max Velocity");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinVelocity(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Velocity Change Attempt Failed.");
			  }
		  } 
	  });
	  m.add("Car Stop Distance", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Stop Distance");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxStopDistance(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Stop Distance Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Stop Distance");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinStopDistance(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Stop Distance Change Attempt Failed.");
			  }
		  } 
	  });
	  m.add("Car Brake Distance", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Brake Distance");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxBrakeDistance(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Brake Distance Change Attempt Failed.");
			  }
			  try {
			  valueIn = entryFormForDoubles("Min Brake Distance");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinBrakeDistance(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Brake Distance Change Attempt Failed.");
			  }
		  }  
	  });
	  m.add("Traffic Light Green Time", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Green Time");
			  }catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxGreenTime(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Green Time Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Green Time");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinGreenTime(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Green Time Change Attempt Failed.");
			  }
		  }  
	  });
	  m.add("Traffic Light Yellow Time", new UIMenuAction(){
		  public void run(){
			  double valueIn;
			  try{
			  valueIn = entryFormForDoubles("Max Yellow Time");
			  }catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  Command cmd = new CmdChangeMaxYellowTime(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Maximum Yellow Time Change Attempt Failed.");
			  }
			  try{
			  valueIn = entryFormForDoubles("Min Yellow Time");
			  } catch (IllegalArgumentException e){ _ui.displayError("Input is empty, letter, or NaN");
			  valueIn = 0;}
			  cmd = new CmdChangeMinYellowTime(valueIn);
			  if (!cmd.run()){
				  _ui.displayError("Minimum Yellow Time Change Attempt Failed.");
			  }
		  }  
	  });
	  m.add("Reset Simulation and Return To Main Menu", new UIMenuAction(){
		  public void run(){
			  _state = TOPMENU;
		  }
	  });
	  
	  _menus[stateNum] = m.toUIMenu("Settings Menu");
  }
    
  private void addEXIT(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default", new UIMenuAction() { public void run() {} });
    m.add("Yes",
      new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = TOPMENU;
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
}
