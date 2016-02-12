package model;
/**
 * Agent, active object within the simulation. Contains a run method that alters the 
 * state of the simulation, requiring an Observable to update Observers, who then
 * animate the changes.
 *
 */
public interface Agent {
	public void run();
}
