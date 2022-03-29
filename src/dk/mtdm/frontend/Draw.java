package dk.mtdm.frontend;

import javax.swing.*;

public class Draw {
    public int width = 400;
    public int height = 400;
    private JFrame w = new JFrame();// creating a Frame

    public void window() {
        w.setTitle("Black Jack");
        w.setVisible(true);
        w.setSize(width ,height);
        w.setLocation(200,200);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//specifying close operation
    }

    public void dealer() {
        JLabel Dealer = new JLabel("Dealer");
        w.add(Dealer);
    }

}
