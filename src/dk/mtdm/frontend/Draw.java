package dk.mtdm.frontend;
import dk.mtdm.api.CurrentData;
import dk.mtdm.backend.BlackJack.BlackJackProcessing;
import dk.mtdm.backend.BlackJack.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static dk.mtdm.frontend.Draw.MyCanvas.g;

public class Draw extends JFrame {
    public static final int width = 1000;
    public static final int height = 800;
    private static boolean valg;
    private final MyCanvas canvas = new MyCanvas();

    public Draw() {
        setLayout(new BorderLayout());
        setSize(width,height);
        setTitle("Black Jack");
        ImageIcon icon = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png");
        setIconImage(icon.getImage());
        JButton button = new JButton("Hit");
        button.setBounds(400,400,100,100);
        add(button);
        add("Center",canvas);
        canvas.setBounds(0,0,width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GREEN);
        setLocationRelativeTo(null);

        setLayout(null);
        setVisible(true);
    }

    static class MyCanvas extends Canvas {


        public static ImageObserver paint() {
            return null;
        }

        static Graphics g;
        @Override
        public void paint(Graphics G) {
            g = G;
            //background
            g.setColor(new Color(52, 166, 0));
            g.fillRect(0,0,getWidth(),getHeight());
            player(g);
            dealer(0);
            PlayerDraw(2);



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
    
    /**
   * @return true = hit,  flase = stand
   * @param actions booleans that describe available actions: [1] = hit, [2] = stand, [3] = hit can not kill you
   */
    public static void buttons(CurrentData data) {
        if (data.AVAILABLE_ACTIONS[1]) {
            // laver en knap til hit
        }
        if (data.AVAILABLE_ACTIONS[2]){
            // laver en knap til stand
        }
        if (data.AVAILABLE_ACTIONS[3]) {
            // laver en knap til hit men man kan ikke dø
        }
    }




    private static ArrayList<card> Cards = new ArrayList<card>();
    private static void player(Graphics g) {
        for (byte j = 0; j < Table.NUMBER_OF_PLAYERS; j++) {
            for (byte i = 0; i < Table.getPlayer((byte) (j)).getHand().size(); i++) {
                Cards.add(new card(
                    Table.getPlayer(j).getHand().get(i).getSymbol(),
                    Table.cardNumberString(j, i), i
                ));

            }
        }

    }

    public static void dealer(int id){
        Cards.get(2*id).show(g,true);
        Cards.get(2*id+1).show(g,true);

//        final card card2 = new card("hearts","2",0);
//        final card card = new card("hearts","2",1);

//        cards.show(g);
//        card2.show(g);
//        (byte) Table.getPlayer(0).getHand().size()
    }

    /**
     * @param id shows players cards, based on they id
     */
    public static void PlayerDraw(int id) {
        Cards.get(2*id).show(g,false);
        Cards.get(2*id+1).show(g,false);
    }
}


//0:
//0 - 1
//1:
//2 - 3
//2:
//4 - 5
//3:
//6-7