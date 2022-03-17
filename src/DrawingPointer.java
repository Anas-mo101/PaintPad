import java.awt.*;
import java.awt.event.*;

/**
 * DrawingPointer Class is responsible to make use of mouse events in order to draw shapes
 */
public class DrawingPointer {
    private static Point mouseClicked;
    private static Point mousePressed;
    private static Point mouseReleased;
    private static Point mouseEntered;
    private static Point mouseDragged;
    private static Point mouseExited;
    private static Point mouseMoved;

    public static Point lastPoint;

    private static final String states[] = {"CURSIOR","PEN","LINE","STAR"};
    private static String state = states[0];

    private static DrawingPointer c = new DrawingPointer();

    DrawingPointer(){}

    private static void doAtion(String event, Color c, int w, Graphics g){
        switch(state){
            case "CURSIOR": cursior(event);
                            break;
            case "PEN":     pen(event, c, w, g);
                            break;
            case "LINE":    line(event, c, w, g);
                            break;
            case "STAR":    star(event, c, w, g);
                            break;
        }
    }

    /**
     * Used to get the singleton instance of pointer
     * @return instance of pointer
     */
    public static DrawingPointer getInstance(){
        return c;
    }

   /**
     * Used to set pointer drawing state
     * @param i index of drawing pointer states 
     */
    public static void setSate(int i){
        if(i > 0 || i <= states.length){
            state = states[i];
        }else{
            state = states[0];
        }
    }

    /**
     * Used to set new mouseClicked point
     */
    public static void setMouseClicked(MouseEvent e, Color c, int w, Graphics g){
        mouseClicked = e.getPoint();
    }

    /**
     * Used to set new setMousePressed point
     */
    public static void setMousePressed(MouseEvent e, Color c, int w, Graphics g){
        mousePressed = e.getPoint();
        doAtion("MousePressed", c ,w, g);
    }

    /**
     * Used to set new mouseReleased point
     */
    public static void setMouseReleased(MouseEvent e, Color c, int w, Graphics g){
        mouseReleased = e.getPoint();
        doAtion("MouseReleased", c ,w, g);
    }

    /**
     * Used to set new mouseEntered point
     */
    public static void setMouseEntered(MouseEvent e, Color c, int w, Graphics g){
        mouseEntered = e.getPoint();
        doAtion("MouseEntered", c ,w, g);
    }

    /**
     * Used to set new mouseDragged point
     */
    public static void setMouseDragged(MouseEvent e, Color c, int w, Graphics g){
        mouseDragged = e.getPoint();
        doAtion("MouseDragged", c ,w, g);
    }

    /**
     * Used to set new mouseExited point
     */
    public static void setMouseExited(MouseEvent e, Color c, int w, Graphics g){
        mouseExited = e.getPoint();
        doAtion("MouseExited", c ,w, g);
    }

    /**
     * Used to set new mouseMoved point
     */
    public static void setMouseMoved(MouseEvent e, Color c, int w, Graphics g){
        mouseMoved = e.getPoint();
        doAtion("MouseMoved", c ,w, g);
    }

    /**
     * Used to handle pen state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing pen width
     * @param g drawing pad graphics
     */
    public static void pen(String e, Color c, int w, Graphics g){
        Pen dot = new Pen(c, w);
        if(e.equals("MouseDragged")){
            dot.paintComponent(g, mouseDragged);
        }
    }

    /**
     * Used to handle line state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing line width
     * @param g drawing pad graphics
     */
    public static void line(String e, Color c, int w, Graphics g){
        Line line = new Line(mousePressed, c, w);
        if(e.equals("MouseDragged")){
            line.init(mouseDragged, g);
        }

        if(e.equals("MouseReleased")){
            line.paintComponent(mouseReleased, g);
            System.out.println("Star Released");
        }
    }


    /**
     * Used to handle star state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing star width
     * @param g drawing pad graphics
     */
    public static void star(String e, Color c, int w, Graphics g){
        Star star = new Star(mousePressed, c);
        if(e.equals("MouseDragged")){
            star.init(mouseDragged, g);
        }

        if(e.equals("MouseReleased")){
            star.paintComponent(g, mouseReleased);
        }
    }

    public static void cursior(String e){
        // handle cursor events
    }
}
