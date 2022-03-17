import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame {
    JPanel pad;
    int drawingWidth = 5;
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
        pad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pad.setBackground(Color.WHITE);

        pad.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                DrawingPointer.setMouseClicked(e, drawingColor, drawingWidth, pad.getGraphics());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                DrawingPointer.setMousePressed(e, drawingColor, drawingWidth, pad.getGraphics());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                DrawingPointer.setMouseReleased(e, drawingColor, drawingWidth, pad.getGraphics());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                DrawingPointer.setMouseEntered(e, drawingColor, drawingWidth, pad.getGraphics());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DrawingPointer.setMouseExited(e, drawingColor, drawingWidth, pad.getGraphics());
            }
        });

        pad.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent e) {
                DrawingPointer.setMouseDragged(e, drawingColor, drawingWidth, pad.getGraphics());
                xcoord.setText(Integer.toString(e.getX()));
                ycoord.setText(Integer.toString(e.getY()));
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                DrawingPointer.setMouseMoved(e, drawingColor, drawingWidth, pad.getGraphics());
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

    /**
     * Function to create painting toolbar 
     * @return Toolbar to be added 
     */
    public JPanel toolbar(){
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        Button curBtn = new Button("CURSIOR");
        curBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPointer.setSate(0);
                
            }
        });

        Button penBtn = new Button("PEN");
        penBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // drawingMode = penBtn.getLabel();
                DrawingPointer.setSate(1);
                
            }
        });
        
        Button lineBtn = new Button("LINE");
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // drawingMode = lineBtn.getLabel();
                DrawingPointer.setSate(2);
            }
        });

        Button starBtn = new Button("STAR");
        starBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // drawingMode = starBtn.getLabel();
                DrawingPointer.setSate(3);
            }
        });

        Button clearBtn = new Button("CLEAR");
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


