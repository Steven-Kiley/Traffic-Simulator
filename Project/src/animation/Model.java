package animation;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.*;
import util.TimeServer;


/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimatorBuilder, int, int)}.
 */
public class Model extends Observable {
  private List<Agent> _agents;
  private Animator _animator;
  private boolean _disposed;
  private double _time;
  ProjectSetup _defaultSetup = ProjectSetup.generateDefaults();
  private TimeServer _timeKeeper = _defaultSetup.getTimeServer();
  
  /** Creates a model to be visualized using the <code>builder</code>.
   *  If the builder is null, no visualization is performed.
   *  The number of <code>rows</code> and <code>columns</code>
   *  indicate the number of {@link Light}s, organized as a 2D
   *  matrix.  These are separated and surrounded by horizontal and
   *  vertical {@link Road}s.  For example, calling the constructor with 1
   *  row and 2 columns generates a model of the form:
   *  <pre>
   *     |  |
   *   --@--@--
   *     |  |
   *  </pre>
   *  where <code>@</code> is a {@link Light}, <code>|</code> is a
   *  vertical {@link Road} and <code>--</code> is a horizontal {@link Road}.
   *  Each road has one {@link Car}.
   *
   *  <p>
   *  The {@link AnimatorBuilder} is used to set up an {@link
   *  Animator}.
   *  {@link AnimatorBuilder#getAnimator()} is registered as
   *  an observer of this model.
   *  <p>
   */
  public Model(AnimatorBuilder builder, int rows, int columns) {
    if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
      throw new IllegalArgumentException();
    }
    if (builder == null) {
      builder = new NullAnimatorBuilder();
    }
    
    if (_defaultSetup.getAlternating()){
    	AlternatingGridBuilder alterBuild = new AlternatingGridBuilder();
    	alterBuild.buildModel(builder, rows, columns);
    }
    else{
    	NonalternatingGridBuilder simpleBuild = new NonalternatingGridBuilder();
    	simpleBuild.buildModel(builder, rows, columns);
    }

    _animator = builder.getAnimator();
    super.addObserver(_animator);
    this._timeKeeper.addObserver(_animator);
  }

  /**
   * Run the simulation for <code>duration</code> model seconds.
   */
  public void run(double duration) {
    if (_disposed)
      throw new IllegalStateException();
    _timeKeeper.run(duration);
      super.setChanged();
      super.notifyObservers();
  }


  /**
   * Throw away this model.
   */
  public void dispose() {
    _animator.dispose();
    _disposed = true;
  }

}
