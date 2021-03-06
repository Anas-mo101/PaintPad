import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


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

    private static Point prePoint_pen;
    private static Point prePoint_drag;
    private static Point prePoint_pressed;
    private static Point prePoint_moved;

    private static Boolean pressed = false;
    private static Boolean loaded = false;


    private static final String states[] = {"CURSIOR","PEN","LINE","STAR","TRIANGLE","ERASER","CIRCLE","RECTANGLE"};
    private static String state = states[0];

    private static DrawingPointer c = new DrawingPointer();

    private static Graphics graphics;
    private static Color inkColor;
    private static Color bckgdColor;
    private static int width;

    public static Boolean checkPressedAndStill = false;

    DrawingPointer(){
        checkPressedAndStill();
        // checkMouseStill();
    }

    public static void doMouseAtion(MouseEvent event, String etype, Color c, Color bgc, int w, Graphics g){
        graphics = g;
        inkColor = c;
        width = w;
        bckgdColor = bgc;
        updateMouseEventsPoints(event, etype);

        switch(state){
            case "CURSIOR": cursior(etype);
                            break;
            case "PEN":     pen(etype, c, w, g);
                            break;
            case "ERASER":  pen(etype, bgc, w, g);
                            break;
            case "LINE":    line(etype, bgc, c, w, g);
                            break;
            case "STAR":    star(etype, bgc, c, g);
                            break;
            case "TRIANGLE":triangle(etype, bgc, c, g);
                            break;
            case "CIRCLE":  circle(etype, bgc, c, g);
                            break;
            case "RECTANGLE":rectangle(etype, bgc, c, g);
                break;
                
            default:        
                break;
        }
    }

    private static void updateMouseEventsPoints(MouseEvent event, String etype){
        switch(etype){
            case "MousePressed": 
                prePoint_moved = prePoint_pen = prePoint_drag = mousePressed = event.getPoint();
                pressed = true;
                break;
            case "MouseReleased":     
                mouseReleased = event.getPoint();
                pressed = false;
                checkPressedAndStill = false;
                break;
            case "MouseEntered":    
                mouseEntered = event.getPoint();
                break;
            case "MouseDragged":    
                mouseDragged = event.getPoint();
                checkPressedAndStill = false;
                break;
            case "MouseExited":    
                mouseExited = event.getPoint();
                break;
            case "MouseMoved":    
                mouseMoved = event.getPoint();
                checkPressedAndStill = false;
                break;
            default:   break;
        }
        
    }

    /**
     * Used to create custom mouse events. e.g Mouse Pressed And Still
     */
    public static void checkPressedAndStill(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            if(pressed){
                if(prePoint_drag == mouseDragged){
                    checkPressedAndStill = true;
                    switch(state){
                        case "LINE":    line("MousePressedAndStill", bckgdColor, inkColor, width, graphics);
                                        break;
                        case "STAR":    star("MousePressedAndStill", bckgdColor, inkColor, graphics);
                                        break;
                        case "TRIANGLE":triangle("MousePressedAndStill", bckgdColor, inkColor, graphics);
                                        break;
                        case "RECTANGLE":rectangle("MousePressedAndStill", bckgdColor, inkColor, graphics);
                            break;
                        case "CIRCLE":circle("MousePressedAndStill", bckgdColor, inkColor, graphics);
                                        break;
                        default:        break;
                    }
                }
            }
        }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public static void checkMouseStill(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            // if(!moved){
                if(prePoint_moved == mouseMoved){
                    System.out.println("STILL");
                    switch(state){
                        case "RECTANGLE":  
                            rectangle("MouseStill", bckgdColor, inkColor, graphics);
                            break;
                        case "CIRCLE":  
                            circle("MouseStill", bckgdColor, inkColor, graphics);
                            break;
                        default:        
                            break;
                    }
                }
            // }
        }
        }, 0, 50, TimeUnit.MILLISECONDS);
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
     * Used to handle pen state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing pen width
     * @param g drawing pad graphics
     */
    public static void pen(String e, Color c, int w, Graphics g){
        Pen dot = new Pen(c, w);
        if(e.equals("MouseDragged")){
            dot.paintComponent(g, mouseDragged, prePoint_pen);
            prePoint_pen = mouseDragged;
        }
    }

    /**
     * Used to handle line state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing line width
     * @param g drawing pad graphics
     */
    public static void line(String e, Color bkc, Color c, int w, Graphics g){
        Line line = new Line(mousePressed, c, w);
        
        if(e.equals("MouseDragged") || e.equals("MousePressedAndStill")){

            line.init(mouseDragged, bkc, g);
            prePoint_drag = mouseDragged;

        }else if(e.equals("MouseReleased")){
            try {
                Thread.sleep(50);
                line.paintComponent(mouseReleased, g);
            } catch (InterruptedException event) {
                event.printStackTrace();
            }
        }
    }

    /**
     * Used to handle star state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing star width
     * @param g drawing pad graphics
     */
    public static void star(String e, Color bkc, Color c, Graphics g){
        Star star = new Star(mousePressed, c);
        if(e.equals("MouseDragged") || e.equals("MousePressedAndStill")){
            star.init(mouseDragged, bkc, g);
            prePoint_drag = mouseDragged;
        }else if(e.equals("MouseReleased")){
            try {
                Thread.sleep(50);
                star.paintComponent(g, mouseReleased);
            } catch (InterruptedException event) {
                event.printStackTrace();
            }
        }
    }

    /**
     * Used to handle triangle state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing star width
     * @param g drawing pad graphics
     */
    public static void triangle(String e, Color bkc, Color c, Graphics g){
        Triangle triangle = new Triangle(mousePressed, c);
        if(e.equals("MouseDragged") || e.equals("MousePressedAndStill")){
            triangle.init(mouseDragged, bkc, g);
            prePoint_drag = mouseDragged;
        }else if(e.equals("MouseReleased")){
            try {
                Thread.sleep(50);
                triangle.paintComponent(g, mouseReleased);
            } catch (InterruptedException event) {
                event.printStackTrace();
            }
        }
    }
    
    /**
     * Used to handle circle state drawing
     * @param e mouse event type 
     * @param c picked drawing color
     * @param w drawing star width
     * @param g drawing pad graphics
     */
    public static void circle(String e, Color bkc, Color c, Graphics g){
        Circle circle = new Circle(c);
        
        if(e.equals("MousePressed")){
            if(!loaded){
                loaded = true;
                prePoint_pressed = mousePressed;
            }else{
                try {
                    Thread.sleep(60);
                    circle.paintComponent(g, prePoint_pressed, mousePressed);
                    loaded = false;
                } catch (InterruptedException event) {
                    event.printStackTrace();
                }
            }
        }
    }

    /**
     * Used to handle rectangle state drawing
     * @param e mouse event type
     * @param c picked drawing color
     * @param g drawing pad graphics
     */
   public static void rectangle (String e, Color bkc, Color c, Graphics g){
        Rectangle rectangle = new Rectangle(c);

        if(e.equals("MousePressed")){
            if(!loaded){
                loaded = true;
                prePoint_pressed = mousePressed;
            }else{
                try {
                    Thread.sleep(60);
                    rectangle.paintComponent(g, prePoint_pressed, mousePressed);
                    loaded = false;
                } catch (InterruptedException event) {
                    event.printStackTrace();
                }
            }
        }
    }
    
    public static void cursior(String e){
        // handle cursor events
    }
}
