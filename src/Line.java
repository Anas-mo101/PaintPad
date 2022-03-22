import java.awt.*;
import java.lang.Math;
import javax.swing.*;

/**
 * Class to draw line
 */
public class Line extends JComponent{
    
    public Color color = Color.BLACK;
    public Point startPoint;

    public int width;
    Graphics2D line;

    public Line(Point startPoint,Color color, int width){
      super();
      this.startPoint = startPoint;
      this.color = color;
      this.width = width;
    }

    /**
     * Used to allow shape to flow cursior
     * @param floatingPoint 
     * @param g
     */
    public void init(Point floatingPoint, Point prePoint, Graphics g){
      line = (Graphics2D) g;
      line.setColor(color);
      line.setStroke(new BasicStroke(width));
      line.drawLine( (int)startPoint.getX(), (int)startPoint.getY(), (int)floatingPoint.getX(), (int)floatingPoint.getY());

      try {
        Thread.sleep(60);
        line.setColor(Color.WHITE);  
        line.drawLine( (int)startPoint.getX(), (int)startPoint.getY(), (int)floatingPoint.getX(), (int)floatingPoint.getY());
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
    
    public void paintComponent(Point _p, Graphics graphics){
      super.paintComponent(graphics);
      graphics.setColor(color);
      line = (Graphics2D) graphics;
      line.setStroke(new BasicStroke(width));
      line.drawLine( (int)startPoint.getX(), (int)startPoint.getY(), (int)_p.getX(), (int)_p.getY());
    }

    public final static float calculateAngleFrom(float obj1X, float obj1Y, float obj2X, float obj2Y)
    {
      float angleTarget = (float) Math.toDegrees(Math.atan2(obj2Y - obj1Y, obj2X - obj1X));
      if (angleTarget < 0)
        angleTarget = 360 + angleTarget;
      return angleTarget;
    }
}

