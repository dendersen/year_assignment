package dk.mtdm.frontend;

import javax.swing.JPanel;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;

public class MyCanvas extends JPanel implements Runnable{

    private String threadName;
    private Thread thread;
    private Graphics g;
    private boolean initialized = false;

    public MyCanvas(String threadName){
        this.threadName = threadName;
        System.out.println("creating thread: " + threadName);
    }

    public void start() {
        System.out.println("starting: " + threadName);
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public static ImageObserver paint() {
        return null;
    }

    /**
    * @Override
    */
    public void paint(Graphics G) {
        this.g = G;
        initialized = true;
        setBackground(new Color(52, 166, 0));
        System.out.println("Canvas Reloaded");
    }

    /**
    * @overide
    */
    public void run(String funk) {
        if(initialized)
        paint(g);

        switch(funk){
            case "test":
            System.out.println("runnning");
            break;
        }
    }

    
    public void run() {
        // System.out.println("i am never called");
        
    }
}