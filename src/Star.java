import java.awt.*;
import javax.swing.*;

/**
 * Class to draw a star
 */
public class Star extends JComponent{
    
    public Color color = Color.BLACK;
    public Point startPoint;

    public Star(){
        super();
    }

    public Star(Point startPoint, Color color){
        super();
        this.startPoint = startPoint;
        this.color = color;
    }

    /**
     * Used to allow shape to flow cursior
     * @param floatingPoint current cursior point
     * @param g drawing pad graphics
     */
    public void init(Point floatingPoint, Color bckground, Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;

        int midX = (int) floatingPoint.getX();
        int midY = (int) floatingPoint.getY();

        int radius[] = getRadius(floatingPoint);
        int nPoints = 10;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
    
        for (double current=0.0; current<nPoints; current++){
            int i = (int) current;
            double x = Math.cos(current*((2*Math.PI)/nPoints)) * radius[i % 2];
            double y = Math.sin(current*((2*Math.PI)/nPoints)) * radius[i % 2];
            X[i] = (int) x+midX;
            Y[i] = (int) y+midY;
        }
        g2.setColor(color);
        g2.fillPolygon(X, Y, nPoints);

        try {
            Thread.sleep(60);
            g2.setColor(bckground);
            g2.fillPolygon(X, Y, nPoints);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    

    /**
     * Used to draw final shape 
     * @param g drawing pad graphics
     * @param floatingPoint current cursior point
     */
    public void paintComponent(Graphics g, Point floatingPoint)
    {
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;

        int midX = (int) floatingPoint.getX();
        int midY = (int) floatingPoint.getY();

        int radius[] = getRadius(floatingPoint);

        int nPoints = 10;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
    
        for (double current=0.0; current<nPoints; current++){
            int i = (int) current;
            double x = Math.cos(current*((2*Math.PI)/nPoints)) * radius[i % 2];
            double y = Math.sin(current*((2*Math.PI)/nPoints)) * radius[i % 2];
            X[i] = (int) x+midX;
            Y[i] = (int) y+midY;
        }
        g2.fillPolygon(X, Y, nPoints);
    }

    /**
     * Used to find width of star using the first mousepressed and following mouse released
     * @param p end point of shape
     * @return radius
     */
    private int[] getRadius(Point p){
        int radius[] = new int[2];
        radius[0] = (int) startPoint.distance(p.getX(), p.getY());
        radius[1] = radius[0] / 2;
        return radius;
    }

}
