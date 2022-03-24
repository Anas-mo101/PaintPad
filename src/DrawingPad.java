import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.cert.Extension;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class DrawingPad extends JPanel{

    private DrawingPointer cursior = DrawingPointer.getInstance();
    private BufferedImage Pad;

    int drawingWidth = 5;
    int layers = 0;
    Color drawingColor = Color.BLACK;
    // Graphics2D graphics2D;

    DrawingPad(){
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(false);


        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseClicked", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MousePressed", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseReleased", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseEntered", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseExited", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            }
        });

        this.addMouseMotionListener(new MouseMotionListener(){ 
            @Override
            public void mouseDragged(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseDragged", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseMoved", drawingColor, drawingWidth, Pad.getGraphics());
                getGraphics().drawImage(Pad, 0, 0, null);
            } 
        });

        checkPressedAndStill();
    }

    public void checkPressedAndStill(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            if(DrawingPointer.checkPressedAndStill){
                getGraphics().drawImage(Pad, 0, 0, null);
            }
        }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void setWidth(int drawingWidth) {
        this.drawingWidth = drawingWidth;
    } 

    public void setColor(Color drawingColor) {
        this.drawingColor = drawingColor;
    } 

    public void clearAll() {
        this.repaint();
        Graphics2D graphics2D = (Graphics2D) Pad.getGraphics();
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setPaint(drawingColor);
        repaint();
    } 

    public void setPointerState(int i) {
        DrawingPointer.setSate(i);
    } 


 

    public String saveImage(Boolean isSaveAs, String path){
        try {
            BufferedImage bimage = new BufferedImage(Pad.getWidth(null), Pad.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage( Pad , 0, 0, null);
            

            if(isSaveAs){
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showSaveDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    ImageIO.write(bimage , "png", new File(chooser.getSelectedFile().getAbsolutePath() + ".png"));
                    
                    bGr.dispose();
                    return chooser.getSelectedFile().getAbsolutePath();
                }
            }else{
                ImageIO.write(bimage , "png", new File(path + ".png"));
            }
            return null;
        } catch (Exception e) {
            System.out.println("error: " + e);
            return null;
        }
    }

    public String openImage(){
        try {

            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
            chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png"));

            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedImage image = ImageIO.read(new File(chooser.getSelectedFile().getAbsolutePath()));
                clearAll();
                Pad.getGraphics().drawImage(image, 0, 0, this);
                return chooser.getSelectedFile().getAbsolutePath();
            }
            return null;
        } catch (Exception e) {
            System.out.println("error: " + e);
            return null;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setDrawingPad();
    }

    public void setDrawingPad() {
        if(Pad == null){
            Pad = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = (Graphics2D) Pad.getGraphics();
            graphics2D.fillRect(0,0,getWidth(),getHeight());
            graphics2D.dispose();
        }else{
            BufferedImage bimage = new BufferedImage(Pad.getWidth(null), Pad.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage( Pad , 0, 0, null);
            Pad.getGraphics().drawImage(bimage, 0, 0, this);
        }
    } 
}
