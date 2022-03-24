import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Credits extends JFrame {

    public Credits(){
        super("Credits");
        setLayout(new GridLayout(4,0));

        JLabel credits = new JLabel("Credits", SwingConstants.CENTER);
        credits.setFont(new Font("Serif", Font.PLAIN, 25));
        credits.setBorder(new EmptyBorder(20,0,20,0));
        this.add(credits,BorderLayout.NORTH);

        JLabel intro = new JLabel("This Application was desgined and built for unit CP2201 assginment", SwingConstants.CENTER);
        intro.setBorder(new EmptyBorder(20,0,20,0));
        this.add(intro,BorderLayout.NORTH);

        JLabel info = new JLabel("Made by:", SwingConstants.CENTER);
        info.setBorder(new EmptyBorder(20,0,20,0));
        this.add(info,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4,0));

        JLabel cr1 = new JLabel("Anas Elsebay", SwingConstants.CENTER);
        panel.add(cr1,BorderLayout.NORTH);

        JLabel cr2 = new JLabel("Wei Song", SwingConstants.CENTER);
        panel.add(cr2,BorderLayout.NORTH);

        JLabel cr3 = new JLabel("Avinaash Kuppusamy", SwingConstants.CENTER);
        panel.add(cr3,BorderLayout.NORTH);

        JLabel cr4 = new JLabel("Nur Aqra", SwingConstants.CENTER);
        panel.add(cr4,BorderLayout.NORTH);

        panel.setBorder(new EmptyBorder(0,0,20,0));

        this.add(panel);
        setSize(600,350);
        setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
