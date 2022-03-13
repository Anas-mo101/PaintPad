import java.awt.*;
import javax.swing.*;

public class Line  extends JComponent{
    
    public Color color = Color.BLACK;
    public Point startPoint;
    public int width;

    public Line(Point startPoint, int width)
    {
        super();
        this.startPoint = startPoint;
        this.width = width;
    }

    public Line(Point startPoint, Color color, int width)
    {
        super();
        this.startPoint = startPoint;
        this.color = color;
        this.width = width;
    }

    public void paintComponent(Graphics g, Point _p)
    {
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width));
        g2.drawLine( (int)startPoint.getX(), (int)startPoint.getY(), (int)_p.getX(), (int)_p.getY());
    }
}

