import java.awt.*;
import javax.swing.*;
import java.lang.Math;

/**
 * Class to draw a triangle
 */
public class Triangle extends JComponent{
    
    public Color color = Color.BLACK;
    public Point startPoint;
    
    public Triangle(){
        super();
    }

    public Triangle(Point startPoint, Color color){
        super();
        this.startPoint = startPoint;
        this.color = color;
    }

    /**
     * Used to allow shape to flow cursior
     * @param floatingPoint current cursior point
     * @param g drawing pad graphics
     */
    public void init(Point floatingPoint ,Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;

        int nPoints = 3;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
        int start_corX = (int)startPoint.getX();
        int start_corY = (int)startPoint.getY();
        int floating_corX = (int) floatingPoint.getX();
        int floating_corY = (int) floatingPoint.getY();
        int point3_corX;
        int point3_corY = floating_corY;
        int mid_corX = Math.abs(floating_corX - start_corX);
        
        if(floating_corX < start_corX) {
            point3_corX = start_corX + mid_corX;
        }else { 
            point3_corX = start_corX - mid_corX;
        }

        X[0] = start_corX;
        X[1] = floating_corX;
        X[2] = point3_corX;

        Y[0] = start_corY;
        Y[1] = floating_corY;
        Y[2] = point3_corY;

        g2.setColor(color);
        g2.fillPolygon(X, Y, nPoints);

        try {
            Thread.sleep(60);
            g2.setColor(Color.WHITE);
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

        int nPoints = 3;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
        int start_corX = (int)startPoint.getX();
        int start_corY = (int)startPoint.getY();
        int floating_corX = (int) floatingPoint.getX();
        int floating_corY = (int) floatingPoint.getY();
        int point3_corX;
        int point3_corY = floating_corY;
        int mid_corX = Math.abs(floating_corX - start_corX);
        
        if(floating_corX < start_corX) {
            point3_corX = start_corX + mid_corX;
        }else { 
            point3_corX = start_corX - mid_corX;
        }

        X[0] = start_corX;           
        X[1] = floating_corX;
        X[2] = point3_corX;

        Y[0] = start_corY;
        Y[1] = floating_corY;
        Y[2] = point3_corY;

        g2.fillPolygon(X, Y, nPoints);
    }

}
