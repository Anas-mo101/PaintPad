
import javax.swing.*;
import java.awt.*;
import java.lang.Math;

/**
 * Class to draw a rectangle
 */
public class Rectangle extends JComponent { //

    public Color color = Color.BLACK;
    public Point startPoint;
    int x, y, x2, y2;


    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

     Rectangle(Point startPoint, Color color) {
        super();
        x = y = x2 = y2 = 0;
        this.startPoint = startPoint;
        this.color = color;
    }

    /**
     * Used to allow shape to flow cursior

     * @param g   drawing pad graphics
     */
     public void init(Point floatingPoint ,Graphics g) {
         super.paintComponent(g);
         g.setColor(color);
         Graphics2D g2 = (Graphics2D) g;
         int start_corX = (int)startPoint.getX();
         int start_corY = (int)startPoint.getY();
         int floating_corX = (int) floatingPoint.getX();
         int floating_corY = (int) floatingPoint.getY();

         int px = Math.min(start_corX, floating_corX);
         int py = Math.min(start_corY, floating_corY);
         int pw = Math.abs(start_corX - floating_corX);
         int ph = Math.abs(start_corY - floating_corY);
        g2.setColor(color);
        g2.drawRect(px, py, pw, ph);

        try {
            Thread.sleep(60);
            g2.setColor(Color.WHITE);
            g2.drawRect(px, py, pw, ph);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to draw final shape
     * @param g drawing pad graphics
     */
    public void paintComponent(Graphics g, Point floatingPoint) {
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        int start_corX = (int)startPoint.getX();
        int start_corY = (int)startPoint.getY();
        int floating_corX = (int) floatingPoint.getX();
        int floating_corY = (int) floatingPoint.getY();

        int px = Math.min(start_corX, floating_corX);
        int py = Math.min(start_corY, floating_corY);
        int pw = Math.abs(start_corX - floating_corX);
        int ph = Math.abs(start_corY - floating_corY);
        //g2.drawRect(px, py, pw, ph);
        g2.fillRect(px, py, pw, ph);

    }


}
