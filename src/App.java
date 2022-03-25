import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame {

    private DrawingPad drawingPad = new DrawingPad();
    private String fileTitle = null;
    private String filePath = null;
    
	public App(){
		super("PaintPad");
        
        this.setJMenuBar(confgbar()); 
        this.add(toolbar(),BorderLayout.NORTH);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                drawingPad.setDrawingPad();
            }
        });

        JPanel footerbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        Label xcoord = new Label();
        Label separate = new Label(",");
        Label ycoord = new Label();
        footerbar.add(xcoord);
        footerbar.add(separate);
        footerbar.add(ycoord);
        this.add(footerbar,BorderLayout.SOUTH);

        JPanel parentPad = new JPanel(new GridLayout());
        drawingPad.addMouseMotionListener(new MouseMotionListener(){ 
            @Override
            public void mouseDragged(MouseEvent e) {
                xcoord.setText(Integer.toString(e.getX()));
                ycoord.setText(Integer.toString(e.getY()));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xcoord.setText(Integer.toString(e.getX()));
                ycoord.setText(Integer.toString(e.getY()));
            } 
        });
        parentPad.add(drawingPad);
        this.add(parentPad,BorderLayout.CENTER);

		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


    /**
     * Function to create configeration toolbar 
     * @return Toolbar to be added 
     */
    public JMenuBar confgbar(){
        JMenuBar mb = new JMenuBar();  
        JMenu file = new JMenu("File");    
        JMenu more = new JMenu("More");  

        JMenuItem sa = new JMenuItem("Save as");  
        sa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filePath = drawingPad.saveImage(true,null);
                if(filePath != null){
                    fileTitle = filePath.substring(filePath.lastIndexOf(File.separator)+1); 
                    setTitle("PaintPad - " + fileTitle);
                }
            }
        });


        JMenuItem s = new JMenuItem("Save");  
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileTitle == null){
                    drawingPad.saveImage(true,null);
                }else{
                    drawingPad.saveImage(false,filePath);
                }
            }
        });

        JMenuItem open = new JMenuItem("Open");  
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filePath = drawingPad.openImage();
                if(filePath != null){
                    fileTitle = filePath.substring(filePath.lastIndexOf(File.separator)+1); 
                    setTitle("PaintPad - " + fileTitle);
                }
            }
        });

        file.add(s); 
        file.add(sa);
        file.add(open); 

        JMenuItem crds = new JMenuItem("Credits"); 
        crds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Credits();
            }
        });
        more.add(crds);
        
        mb.add(file);  
        mb.add(more); 
        return mb;
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
                drawingPad.setPointerState(0);
            }
        });

        JButton penBtn = new JButton(new ImageIcon("media/pen-fill.png"));
        penBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPad.setPointerState(1);
            }
        });
        
        JButton lineBtn = new JButton(new ImageIcon("media/slash-lg.png")); //line
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPad.setPointerState(2);
            }
        });

        JButton starBtn = new JButton(new ImageIcon("media/star-fill.png")); //star
        starBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPad.setPointerState(3);
            }
        });

        JButton triangleBtn = new JButton(new ImageIcon("media/triangle-fill.png")); //triangle
        triangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // drawingMode = starBtn.getLabel();
                DrawingPointer.setSate(4);
            }
        });
	  
	    //rectangle
        JButton rectangleBtn = new JButton(new ImageIcon("media/rectangle-fill.png"));
        rectangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPad.setPointerState(5);
            }
        });

        JButton clearBtn = new JButton(new ImageIcon("media/trash-fill.png")); // clear
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPad.clearAll();
            }
        });

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                drawingPad.setColor(colorPicker.getSelectedColor());
            }
        });


        DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(20, 0, 1, 100);
        JSlider slider = new JSlider(model);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // drawingWidth = slider.getValue();
                drawingPad.setWidth(slider.getValue());
            }
        });
        
        toolbar.add(curBtn);
        toolbar.add(penBtn);
        toolbar.add(lineBtn);
        toolbar.add(starBtn);
        toolbar.add(triangleBtn);
        toolbar.add(clearBtn);
        toolbar.add(colorPicker);
        toolbar.add(slider);
        return toolbar;
    }

	public static void main(String[] a){
		new App();
	}
}


