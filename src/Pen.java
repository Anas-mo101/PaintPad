import java.awt.*;
// import java.util.Arrays;

import javax.swing.*;

public class Pen extends JComponent{
    private Color color;

    public int width;

    public Pen(Color color, int width)
    {
        super();
        this.color = color;
        this.width = width;
    }

    public JComponent getComp(){
      return this.getComp();
    }

    // public void paintComponent(Graphics g, Point p)
    // {
    //     super.paintComponent(g);
    //     g.setColor(color);
    //     g.fillOval((int)p.getX(), (int)p.getY(), width, width);
    // }

    public void _paintComponent(Graphics g, Point p, Point prep)
    {
        super.paintComponent(g);
        g.setColor(color);
        Graphics2D line = (Graphics2D) g;
        line.setStroke(new BasicStroke(width));
        line.drawLine( (int)p.getX(), (int)p.getY(), (int)prep.getX(), (int)prep.getY());
    }
}