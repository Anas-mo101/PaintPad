import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame {
    JPanel pad;
    int drawingWidth = 5;
    String drawingMode = "PEN";
    Color drawingColor = Color.BLACK;

	public App(){
		super("Paint");
        this.add(toolbar(),BorderLayout.NORTH);

        JPanel footerbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Label xcoord = new Label("0");
        Label separate = new Label(",");
        Label ycoord = new Label("0");
        footerbar.add(xcoord);
        footerbar.add(separate);
        footerbar.add(ycoord);
        this.add(footerbar,BorderLayout.SOUTH);

        JPanel parentPad = new JPanel(new GridLayout());
        pad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pad.setBackground(Color.WHITE);

        pad.addMouseListener(new MouseListener(){
            Point prePoint = new Point();

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                prePoint.setLocation(e.getX(),e.getY());
                if(drawingMode.equals("PEN")){
                    pen(pad.getGraphics(), e.getPoint(), drawingColor);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(drawingMode.equals("LINE")){
                    line(pad.getGraphics(), prePoint, e.getPoint(), drawingColor);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        pad.addMouseMotionListener(new MouseMotionListener(){
            // Point prePoint = new Point();
            @Override
            public void mouseDragged(MouseEvent e) {
                xcoord.setText(Integer.toString(e.getX()));
                ycoord.setText(Integer.toString(e.getY()));
                if(drawingMode.equals("PEN")){
                    pen(pad.getGraphics(), e.getPoint(), drawingColor);
                }
                // draw(new Point(), e.getPoint());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
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

    // public void draw(Point p, Point _p){
    //     if(drawingMode.equals("LINE")){
    //         line(pad.getGraphics(), p, _p, drawingColor);
    //     }else if(drawingMode.equals("PEN")){
    //         pen(pad.getGraphics(), _p, drawingColor);
    //     }
    // }

    public JPanel toolbar(){
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Button penBtn = new Button("PEN");
        penBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingMode = penBtn.getLabel();
            }
        });
        
        Button lineBtn = new Button("LINE");
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingMode = lineBtn.getLabel();
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
        // slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                drawingWidth = slider.getValue();
            }
        });
   
        
        toolbar.add(penBtn);
        toolbar.add(lineBtn);
        toolbar.add(clearBtn);
        toolbar.add(colorPicker);
        toolbar.add(slider);
        return toolbar;
    }

    public void pen(Graphics g, Point p, Color c){
        Pen dot = new Pen(c, drawingWidth);
        dot.paintComponent(g, p);
    }

    public void line(Graphics g, Point pStart, Point pEnd, Color c){
        Line line = new Line(pStart, c, drawingWidth);
        line.paintComponent(g, pEnd);
    }
	
	public static void main(String[] a){
		new App();
	}
}


