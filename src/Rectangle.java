import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.lang.Math;

/**
 * Class to draw a rectangle
 */
public class Rectangle extends JComponent { //

    public Color color = Color.BLACK;
    public Point startPoint;
    int x, y, x2, y2;


    public Rectangle() {
        super();

    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

    public Rectangle(Point startPoint, Color color) {
        super();
        this.startPoint = startPoint;
        this.color = color;
    }

    /**
     * Used to allow shape to flow cursior
     *
     * @param g             drawing pad graphics
     */
    public void init( Graphics g,MouseEvent e, int x, int y, int x2, int y2) {

        int px = Math.min(x, x2);
        int py = Math.min(y, y2);
        int pw = Math.abs(x - x2);
        int ph = Math.abs(y - y2);
        super.paintComponent(g);
        setStartPoint(e.getX(), e.getY());
        setEndPoint(e.getX(), e.getY());
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(color);
        g2.fillPolygon(px, pw, py, ph);

        try {
            Thread.sleep(60);
            g2.setColor(Color.WHITE);
            g2.fillPolygon(px, py, pw, ph);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to draw final shape
     *
     * @param g drawing pad graphics
     */
    public void paintComponent(Graphics g, Point mouseReleased) {


        int px = Math.min(x, x2);
        int py = Math.min(y, y2);
        int pw = Math.abs(x - x2);
        int ph = Math.abs(y - y2);
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;


        g2.fillPolygon(px, py, pw, ph);
    }


}

