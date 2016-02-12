package command;

/**
 * A simple command interface.
 */
public interface Command {
  /**
   * The command body.  All commands follow this interface.
   */
  public boolean run ();
  public String getMenuTitle();
}
