package dk.mtdm.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Draw {
    private final JFrame w = new JFrame();// creating a Frame
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        int width;
        int height;

    public void initialize(int w,int h){
        width = w;
        height = h;
        button();
        window();
    }

    private void window() {
        w.setLayout(null);
        w.setTitle("Black Jack");
        w.setVisible(true);
        w.setSize(width ,height);
        w.setLocation(dim.width/2-w.getSize().width/2, dim.height/2-w.getSize().height/2);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//specifying close operation
    }

    private void button() {
        JButton b = new JButton("Start");
        b.setBounds(width/2-100/2 ,height/2-30/2,100,30);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.setVisible(false);
                dealer();
            }
        });
        w.add(b);
    }
    private void dealer() {
        JLabel Dealer = new JLabel("Dealer"); //der burde være en JTextField
        ImageIcon icon = new ImageIcon("src/dk/mtdm/frontend/placeholder.png");
        Dealer.setIcon(icon);
        Dealer.setBounds(width/2-250/2 ,height/2-250/2, 250,250);
        w.add(Dealer);
        w.revalidate();
        w.repaint();
    }

}
