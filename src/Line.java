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
    public void init(Point floatingPoint, Color bckground, Graphics g){
      paintComponent(floatingPoint, g);

      try {
        Thread.sleep(60);
        line.setColor(bckground);  
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

}

