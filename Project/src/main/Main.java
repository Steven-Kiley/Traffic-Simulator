package main;

import model.*;
import animation.*;
import util.*;
import userinterface.*;

public class Main {
	
	/**
	* Start point for program execution. Initiates the user interface.
	*/
    public Main() {}
   	  	public static void main(String[] args) {
    	    UI ui;
    	    ui = new userinterface.TextUI();
	        Control control = new Control(ui);
	   	    control.run();
	   	  }
	   	}
		  
		  
