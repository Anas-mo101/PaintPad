import java.awt.*;
import javax.swing.*;

/**
 * Class to draw a circle
 */
public class Circle extends JComponent{
    
    public Color color = Color.BLACK;

    public Circle(Color color){
        super();
        this.color = color;
    }

    /**
     * Used to allow shape to flow cursior
     * @param floatingPoint current cursior point
     * @param g drawing pad graphics
     */
    public void init(Point floatingPoint, Point startPoint, Color bckground ,Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;

        int diameter = (int) startPoint.distance(floatingPoint);
        g2.fillOval((int) startPoint.getX(), (int)startPoint.getY(), diameter, diameter);

        try {
            Thread.sleep(60);
            g2.setColor(bckground);
            g2.fillOval((int) startPoint.getX(), (int)startPoint.getY(), diameter, diameter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to draw final shape 
     * @param g drawing pad graphics
     * @param floatingPoint current cursior point
     */
    public void paintComponent(Graphics g, Point startPoint,Point floatingPoint)
    {
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        int diameter = (int) startPoint.distance(floatingPoint);
        g2.fillOval( (int) startPoint.getX(), (int)startPoint.getY(), diameter, diameter);
    }
}
