package dk.mtdm.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static dk.mtdm.frontend.Draw.height;
import static dk.mtdm.frontend.Draw.width;

public class card implements ImageObserver {
    private String symbol;
    private String number;
    private int x = 0;
    private int y = 0;
    private int w = 200;
    private int h = 300;
    private int id;
    private boolean dealer;
    card(String symbol, String number, int id,boolean dealer){
        this.symbol = symbol;
        this.number = number;
        this.x = 100*id;
        this.y = y;
        this.id = id;
        this.dealer = dealer;
        System.out.println(this.symbol+ " " + this.number);
    }

    public void show(Graphics g) {
        if (this.dealer){
            this.y += 25;
            if (this.id == 0) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.fillRect(this.x() + width/2-this.w(), this.y(), this.w(),this.h());
            g.setColor(Color.black);
            g.drawRect(this.x()+ width/2-this.w(), this.y(), this.w(),this.h());
            if (this.id == 0) return;
            Image image = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
            g.drawImage(image,this.x() + this.w() / 2-64 + width/2-this.w(),this.y() + this.h() / 2,this);
//            switch (symbol){
//                case "spar":
//                case "":
//                    System.out.println("Der er kun hearts");
//            }
            g.setFont(new Font("Roboto",Font.BOLD,128));
            g.drawString(number,this.x() + this.w()/2 - 32 + width/2 - this.w(),this.y()+this.h()/2-30);
        } else {
            if (this.id == 0) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.fillRect(this.x(), this.y()+height-this.h()-20, this.w(),this.h());
            g.setColor(Color.black);
            g.drawRect(this.x(), this.y()+width-this.h()-10, this.w(),this.h());
            if (this.id == 0) return;
            switch (symbol){
                case "spar":
                    Image image = new ImageIcon("src/icons/hearts.png").getImage();
                    g.drawImage(image,this.x()+this.w()/2-64,this.y()+this.h()/2,this);
                case "":
                    System.out.println("Der er kun hearts");
            }
            g.setFont(new Font("Roboto",Font.BOLD,128));
            g.drawString(number,this.x()+this.w()/2-32,this.y()+this.h()/2-30);
        }
//
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int h() {
        return h;
    }
    public int w() {
        return w;
    }


    @Override //Jeg har ingen ide, hvad dette gøre. Men jeg ved jeg skal bruge det til at tegne et billede på canvas
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
