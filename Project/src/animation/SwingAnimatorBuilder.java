package animation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import model.Car;
import model.Light;
import model.Road;



/** 
 * A class for building Animators.
 */
public class SwingAnimatorBuilder implements AnimatorBuilder {
  MyPainter _painter;
  public SwingAnimatorBuilder() {
    _painter = new MyPainter();
  }
  public Animator getAnimator() {
    if (_painter == null) { throw new IllegalStateException(); }
    Animator returnValue = new SwingAnimator(_painter, "Traffic Simulator",
                                             VP.displayWidth, VP.displayHeight, VP.displayDelay);
    _painter = null;
    return returnValue;
  }
  private static double skipInit = VP.gap;
  private static double skipRoad = VP.gap + MP.roadLength;
  private static double skipCar = VP.gap + VP.elementWidth;
  private static double skipRoadCar = skipRoad + skipCar;
  public void addLight(Light d, int i, int j) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = new TranslatorWE(x, y, MP.carLength, VP.elementWidth, VP.scaleFactor);
    _painter.addLight(d,t);
  }
  public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
    double x = skipInit + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = eastToWest ? new TranslatorEW(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
                              : new TranslatorWE(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }
  public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + i*skipRoadCar;
    Translator t = southToNorth ? new TranslatorSN(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
                                : new TranslatorNS(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }


  /** Class for drawing the Model. */
  private static class MyPainter implements SwingAnimatorPainter {

    /** Pair of a model element <code>x</code> and a translator <code>t</code>. */
    private static class Element<T> {
      T x;
      Translator t;
      Element(T x, Translator t) {
        this.x = x;
        this.t = t;
      }
    }
    
    private List<Element<Road>> _roadElements;
    private List<Element<Light>> _lightElements;
    MyPainter() {
      _roadElements = new ArrayList<Element<Road>>();
      _lightElements = new ArrayList<Element<Light>>();
    }    
    void addLight(Light x, Translator t) {
      _lightElements.add(new Element<Light>(x,t));
    }
    void addRoad(Road x, Translator t) {
      _roadElements.add(new Element<Road>(x,t));
    }
    
    public void paint(Graphics g) {
      // First draw the background elements
      for (Element<Light> e : _lightElements) {
        switch(e.x.getStatus()){
        case GREEN_EAST_WEST:
        	g.setColor(Color.GREEN);
        	break;
        case GREEN_NORTH_SOUTH:
        	g.setColor(Color.RED);
        	break;
        case YELLOW_EAST_WEST:
        	g.setColor(Color.GREEN);
        	break;
        case YELLOW_NORTH_SOUTH:
        	g.setColor(Color.RED);
        	break;
        	default:
        		g.setColor(Color.RED);
        		break;
        }
        XGraphics.fillOval(g, e.t, 0, 0, MP.carLength, VP.elementWidth);
      }
      g.setColor(Color.BLACK);
      for (Element<Road> e : _roadElements) {
        XGraphics.fillRect(g, e.t, 0, 0, MP.roadLength, VP.elementWidth);
      }
      
      // Then draw the foreground elements
      for (Element<Road> e : _roadElements) {
        for (Car d : e.x.getCars().toArray(new Car[0])) {
          g.setColor(d.getColor());
          double roadLength = e.x.getEndPosition();
          double animationPosition = (d.getFrontPosition()-(.5 * d.getLength()))/roadLength;
          animationPosition *= MP.roadLength;
          XGraphics.fillOval(g, e.t, animationPosition, 0, d.getLength(), VP.elementWidth); 
        }
          
        }
      }
    }
  }


