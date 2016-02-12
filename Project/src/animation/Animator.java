package animation;

import java.util.Observer;
  
/**
 * An interface for displaying simulations. Extends Observer to allow updating of animation parameters.
 */
public interface Animator extends Observer {
  public void dispose();
}


