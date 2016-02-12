package animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
  
/**
 * A swing implementation of {@link Animator}, using a {@link JFrame}
 * to display the animation.  The {@link JFrame} is created and
 * displayed by the constructor.
 * 
 * Calls to <code>update()</code> result in a call to
 * <code>painter.paint()</code>.  This is executed in the swing
 * thread while the main thread is paused for <code>delay</code>
 * milliseconds.
 */
public class SwingAnimator implements Animator {
  private int _delay;
  private JFrame _frame; 
  private ContentPane _content; 
  private boolean _disposed = false; 
  
  /**
   * Creates and displays a {@link JFrame} for the animation.
   * @param name  The name to be displayed on the graphical window.
   * @param width The width of the display, in pixels.
   * @param height The height of the display, in pixels.
   * @param delay Time to pause after an update, in milliseconds.
   */
  public SwingAnimator(final SwingAnimatorPainter painter, final String name, final int width, final int height, int delay) {
    _delay = delay;
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          _content = new ContentPane(painter, width, height); 
          _frame = new JFrame();  
          _frame.setTitle(name); 
          _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
          _frame.setContentPane(_content); 
          _frame.pack(); 
          _frame.setVisible(true); 
        }});
  }

  /**
   * Throw away this visualization.
   */
  public void dispose() {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          _frame.dispose();
          _disposed = true;
        }});
  }

  /**
   * Calls to <code>update</code> are executed in the swing thread,
   * while the main thread is paused for <code>delay</code>
   * milliseconds.
   */
  public void update(final Observable model, Object ignored) {
    if (_disposed)
      throw new IllegalStateException();
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
         
          _content.repaint();
        }});
    
    try {
        Thread.currentThread().sleep(_delay); //This took SO LONG to figure out! My animation was a just a flashing light show before this!
    } catch (InterruptedException e) {}
  }

  /**
   * A component for painting.
   * All code is executed in the swing thread.
   */
  private static class ContentPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private int _width;
    private int _height;
    private SwingAnimatorPainter _painter;
    
    ContentPane(SwingAnimatorPainter painter, int width, int height) {
      _painter = painter;
      _width = width;
      _height = height;
      setPreferredSize(new Dimension(width, height));
      setDoubleBuffered(true);
      setOpaque(true);
      setBackground(Color.WHITE);
    }
    
    void setPainter(SwingAnimatorPainter painter) {
      _painter = painter;
    }

    public void paint(Graphics g) {
      if (_painter != null ) {
        g.clearRect(0, 0, _width, _height);
        _painter.paint(g);
      }
    }
  }
}
