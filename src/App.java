import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame {
    // JPanel pad;
    JLayeredPane pad = new JLayeredPane();
    final int DRAG_LAYER = 2;
    final int DRAW_LAYER = 1;

    int drawingWidth = 5;
    int layers = 0;
    Color drawingColor = Color.BLACK;
    DrawingPointer cursior = DrawingPointer.getInstance();
    
	public App(){
		super("Paint");
        this.add(toolbar(),BorderLayout.NORTH);
        

        JPanel footerbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Label xcoord = new Label();
        Label separate = new Label(",");
        Label ycoord = new Label();
        footerbar.add(xcoord);
        footerbar.add(separate);
        footerbar.add(ycoord);
        this.add(footerbar,BorderLayout.SOUTH);

        JPanel parentPad = new JPanel(new GridLayout());
        // pad = new JLayeredPane(new FlowLayout(FlowLayout.LEFT));
        pad.setOpaque(true);
        pad.setBackground(Color.WHITE);
        pad.setDoubleBuffered(false);

        pad.addMouseListener(new MouseListener(){
            
            @Override
            public void mouseClicked(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseClicked", drawingColor, drawingWidth, pad.getGraphics());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MousePressed", drawingColor, drawingWidth, pad.getGraphics());
                
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseReleased", drawingColor, drawingWidth, pad.getGraphics());
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseEntered", drawingColor, drawingWidth, pad.getGraphics());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseExited", drawingColor, drawingWidth, pad.getGraphics());
            }
        });

        pad.addMouseMotionListener(new MouseMotionListener(){ 
            @Override
            public void mouseDragged(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseDragged", drawingColor, drawingWidth, pad.getGraphics());

                xcoord.setText(Integer.toString(e.getX()));
                ycoord.setText(Integer.toString(e.getY()));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                DrawingPointer.doMouseAtion(e,"MouseMoved", drawingColor, drawingWidth, pad.getGraphics());
                xcoord.setText(Integer.toString(e.getX()));
                ycoord.setText(Integer.toString(e.getY()));
            } 
        });
        parentPad.add(pad);
        this.add(parentPad,BorderLayout.CENTER);

		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    public int getNewLayer(){
        return ++layers;
    }

    /**
     * Function to create painting toolbar 
     * @return Toolbar to be added 
     */
    public JPanel toolbar(){
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton curBtn = new JButton(new ImageIcon("media/hand-index-thumb-fill.png"));
        curBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPointer.setSate(0);
                
            }
        });

        JButton penBtn = new JButton(new ImageIcon("media/pen-fill.png"));
        penBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPointer.setSate(1);
                
            }
        });
        
        JButton lineBtn = new JButton(new ImageIcon("media/slash-lg.png")); //line
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // drawingMode = lineBtn.getLabel();
                DrawingPointer.setSate(2);
            }
        });

        JButton starBtn = new JButton(new ImageIcon("media/star-fill.png")); //star
        starBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // drawingMode = starBtn.getLabel();
                DrawingPointer.setSate(3);
            }
        });

        JButton clearBtn = new JButton(new ImageIcon("media/trash-fill.png")); // clear
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();   
            }
        });

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                drawingColor = colorPicker.getSelectedColor();
            }
        });


        DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(20, 0, 1, 100);
        JSlider slider = new JSlider(model);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                drawingWidth = slider.getValue();
            }
        });
        
        toolbar.add(curBtn);
        toolbar.add(penBtn);
        toolbar.add(lineBtn);
        toolbar.add(starBtn);
        toolbar.add(clearBtn);
        toolbar.add(colorPicker);
        toolbar.add(slider);
        return toolbar;
    }

	public static void main(String[] a){
		new App();
	}
}


