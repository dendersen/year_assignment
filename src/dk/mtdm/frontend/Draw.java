package dk.mtdm.frontend;
import dk.mtdm.api.BlackJackCom;
import dk.mtdm.api.CurrentData;
import dk.mtdm.backend.BlackJack.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static dk.mtdm.frontend.Draw.MyCanvas.g;

public class Draw extends JFrame {
    public static final int width = 1000;
    public static final int height = 800;
    private static JButton hit;
    private static JButton stand;
    private static CurrentData Trans;

    private static JButton flipBTN;

    private static boolean flip;

    public Draw() {
        setLayout(new BorderLayout());
        setSize(width+10,height+33);
        setTitle("Black Jack");
        ImageIcon icon = new ImageIcon("src/dk/mtdm/frontend/icons/sort_es.png");
        setIconImage(icon.getImage());
        hit = new JButton("Hit");
        hit.setBounds(100,100,100,100);
        add(hit);
        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hit clicked");
                Trans.setAction(true);
                returnBtn();
            }
        } );

        stand = new JButton("Stand");
        stand.setBounds(100,height - 250,100,100);
        add(stand);
        stand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stand Clicked");
                Trans.setAction(false);
                returnBtn();
            }
        } );
        MyCanvas canvas = new MyCanvas();


        flipBTN = new JButton("Flip");
        flipBTN.setBounds(width / 2-50,height / 2-50,100,100);
        add(flipBTN);
        flipBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flip = !flip;
                repaint();
            }
        } );


        add("Center", canvas);
        canvas.setBounds(0,0,width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GREEN);
        setLocationRelativeTo(null);

        setLayout(null);
        setVisible(true);
        hit.setVisible(false);
        stand.setVisible(false);
    }

    static class MyCanvas extends JPanel {
        public static ImageObserver paint() {
            return null;
        }
        public static Graphics g;

        public MyCanvas() {
            setBackground(new Color(52, 166, 0));

        }

        @Override
        public void paint(Graphics G) {
            g = G;
            dealerShow(0);
            playerDraw(1);
            if (flip == true) {
                showDealer();
            }
            System.out.println("Canvas Reloaded");

        }
    }


    /**
   * @param data booleans that describe available actions: [1] = hit, [2] = stand, [3] = hit can not kill you
   */
    public static void buttons(CurrentData data) {
        Trans = data;
        if (Trans.AVAILABLE_ACTIONS[0]) {
            hit.setVisible(true);
            // laver en knap til hit
        }
        if (Trans.AVAILABLE_ACTIONS[1]){
            // laver en knap til stand
            stand.setVisible(true);
        }
        if (data.AVAILABLE_ACTIONS[2]) {
            // laver en knap til hit men man kan ikke dø
            hit.setVisible(true);
        }
    }

    public static void returnBtn(){
        BlackJackCom.theGame(Trans);
    }



    private static final ArrayList<card> Cards = new ArrayList<card>();
    public static void player() {
        if(!Cards.isEmpty()) {
            return;
        }
        System.out.println("Laver kortene");
        for (byte j = 0; j < Table.NUMBER_OF_PLAYERS; j++) {
            for (byte i = 0; i < Table.getPlayer((byte) (j)).getHand().size(); i++) {
                Cards.add(new card(
                    Table.getPlayer(j).getHand().get(i).getSymbol(),
                    Table.cardNumberString(j, i), i
                ));
            }
        }
        System.out.println("Faerdig");
    }

    //flipper dealerens kort
    public static void showDealer(){
        try {
            Cards.get(0).dealerCode(false);
        } catch (Exception e) {
            System.out.println("Table not made, try run player()");
        }
    }

    public static void dealerShow(int id){
        try {
            Cards.get(2*id).show(g,true);
            Cards.get(2*id+1).show(g,true);
        } catch (Exception e) {
            System.out.println("Table not made, try run player()");
        }

    }

    /**
     * @param id shows players cards, based on they id
     */
    public static void playerDraw(int id) {
        try {
            Cards.get(2*id).show(g,false);
            Cards.get(2*id+1).show(g,false);
        } catch (Exception e) {
            System.out.println("Table not made, try run player()");
        }
    }

    public static void winner(byte winner){
        
    }
}