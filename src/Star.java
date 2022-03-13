import java.awt.*;
import javax.swing.*;


public class Star extends JComponent{
    
    public Color color = Color.BLACK;
    public Point startPoint;

    public Star(Point startPoint){
        super();
        this.startPoint = startPoint;
    }

    public Star(Point startPoint, Color color){
        super();
        this.startPoint = startPoint;
        this.color = color;
    }

    public void paintComponent(Graphics g, Point _p)
    {
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;

        int midX = (int) _p.getX();
        int midY = (int) _p.getY();

        int radius[] = getRadius(_p);

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
    }

    private int[] getRadius(Point p){
        int radius[] = {1,2};
        radius[0] = (int) startPoint.distance(p.getX(), p.getY());
        radius[1] = radius[0] / 2;
        return radius;
    }

}
