import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class Pen extends JComponent{
    private Color color;
    // private Point points[] = new Point[2];
    private Point prePoint = new Point();
    public int width;

    public Pen(Color color, int width)
    {
        super();
        this.color = color;
        this.width = width;
    }

    public Pen(int width)
    {
        this(Color.BLACK, width);
    }

    public void paintComponent(Graphics g, Point p)
    {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval((int)p.getX(), (int)p.getY(), width, width);
    }
}