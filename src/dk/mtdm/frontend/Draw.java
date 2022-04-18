package dk.mtdm.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class draw extends JFrame {
    private static final int width = 1800;
    private static final int height = 1000;
    private final MyCanvas canvas = new MyCanvas();
    private static final card card = new card("hearts","2",width/2-50,10);
    private static final card card2 = new card("hearts","2",width/2-50+110,10);

    public draw() {
        setLayout(new BorderLayout());
        setSize(1800,1000);
        setTitle("Cards");
        add("Center",canvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GREEN);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    static class MyCanvas extends Canvas {


        public static ImageObserver paint() {
            return null;
        }

        @Override
        public void paint(Graphics g) {
            //background
            g.setColor(new Color(52, 166, 0));
            g.fillRect(0,0,getWidth(),getHeight());

            card.cards(g);
            card2.cards(g);
//            g.setColor(Color.black);
//            g.drawString("My first canvas program",10,20);
//            g.drawOval(50,50,100,25);
//            g.drawRect(200,50,100,25);
//            g.setColor(Color.yellow);
//            g.fillOval(50,100,70,70);
//            g.fillRect(200,100,90,90);
//            Image andy = new ImageIcon("src/index.jpg").getImage();
//            Image jony = new ImageIcon("src/index.jpg").getImage();
//            g.drawImage(andy,330,30,this);
//            g.drawImage(jony,350,50,this);
//            g.setColor(Color.blue);
//            g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
//            g.drawString("That's all, folks",10,250);
        }
    }




}
