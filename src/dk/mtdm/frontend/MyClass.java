package dk.mtdm.frontend;

import javax.swing.JPanel;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;

class MyCanvas extends JPanel implements Runnable{
    public static ImageObserver paint() {
        return null;
    }
    public static Graphics g;

    /**
    * @Override
    */
    public void paint(Graphics G) {
        g = G;
        setBackground(new Color(52, 166, 0));
        System.out.println("Canvas Reloaded");
    }

    /**
    * @
    */
    public void run() {
        // TODO Auto-generated method stub
        
    }
}