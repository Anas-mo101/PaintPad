import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class DrawingPad extends JPanel{

    private DrawingPointer cursior = DrawingPointer.getInstance();
    private BufferedImage Pad ;//= new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);

    int drawingWidth = 5;
    int layers = 0;
    Color drawingColor = Color.BLACK;


    DrawingPad(){
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(false);


        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseClicked", drawingColor, drawingWidth, getGraphics());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MousePressed", drawingColor, drawingWidth, getGraphics());
                
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseReleased", drawingColor, drawingWidth, getGraphics());
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseEntered", drawingColor, drawingWidth, getGraphics());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseExited", drawingColor, drawingWidth, getGraphics());
            }
        });

        this.addMouseMotionListener(new MouseMotionListener(){ 
            @Override
            public void mouseDragged(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseDragged", drawingColor, drawingWidth, getGraphics());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseMoved", drawingColor, drawingWidth, getGraphics());
            } 
        });

    }

    public void setWidth(int drawingWidth) {
        this.drawingWidth = drawingWidth;
    } 

    public void setColor(Color drawingColor) {
        this.drawingColor = drawingColor;
    } 

    public void clearAll() {
        this.repaint();
    } 

    public void setPointerState(int i) {
        DrawingPointer.setSate(i);
    } 

    public void setPadSize(int i) {
        
    } 

    public void saveImage(){
        try {
            // Dimension dimension = this.getSize();
            // Rectangle rectangle = new Rectangle(dimension);
            // BufferedImage imagebuf = new Robot().createScreenCapture(rectangle);
            // repaint();
            
            Pad = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            // Graphics2D g = (Graphics2D) Pad.getGraphics();
            // g.drawImage(Pad, 0, 0, null);
            ImageIO.write(Pad,"jpeg", new File("save1.jpeg"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Pad, 0, 0, null);
    }
    
}
