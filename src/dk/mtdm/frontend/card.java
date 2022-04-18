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
        this.x = 230*id; // Afstand mellem dem / afstand relative mellem 0,0
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
            if (this.id == 0) return; //Checker om det er dealens første kort
            switch (symbol){
                case "hjerter":
                    Image hjerter = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(hjerter,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y() + this.h() / 2,
                            this);
                case "romber":
                    Image romber = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(romber,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y() + this.h() / 2,
                            this);
                case "klør":
                    Image klor = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(klor,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y() + this.h() / 2,
                            this);
                case "spar":
                    Image spar = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(spar,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y() + this.h() / 2,
                            this);
            }
            g.setFont(new Font("consolas",Font.BOLD,128));
            g.drawString(this.number,this.x() + this.w()/2 - 36 + width/2 - this.w(),this.y()+this.h()/2-32);


        } else {
            g.setColor(Color.white);
            g.fillRect(this.x() + width/2-this.w(), this.y()+height-this.h()-50, this.w(), this.h());
            g.setColor(Color.black);
            g.drawRect(this.x() + width/2-this.w(), this.y()+height-this.h()-50, this.w(), this.h());
            switch (symbol){
                case "hjerter":
                    Image hjerter = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(hjerter,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y()+height-this.h()/2-30,
                            this);
                case "romber":
                    Image romber = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(romber,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y()+height-this.h()/2-30,
                            this);
                case "klør":
                    Image klor = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(klor,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y()+height-this.h()/2-30,
                            this);
                case "spar":
                    Image spar = new ImageIcon("src/dk/mtdm/frontend/icons/hearts.png").getImage();
                    g.drawImage(spar,
                            this.x() + this.w() / 2-64 + width/2-this.w(),
                            this.y()+height-this.h()/2-30,
                            this);
            }
            g.setFont(new Font("consolas",Font.BOLD,130));
            g.drawString(this.number,this.x()+width/2-this.w()/2-36,this.y()+height/2+this.h()/2+128);
        }
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
