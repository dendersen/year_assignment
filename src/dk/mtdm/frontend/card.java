package dk.mtdm.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static dk.mtdm.frontend.Draw.height;
import static dk.mtdm.frontend.Draw.width;

public class card implements ImageObserver {
    private int symbol;
    private String number;
    private int x = 0;
    private int y = 0;
    private int w = 200;
    private int h = 300;
    private int id;
    private boolean dealer;
    card(int symbol, String number, int id){
        this.symbol = symbol;
        this.number = number;
        this.x = 230*id; // Afstand mellem dem / afstand relative mellem 0,0
        this.id = id;

    }

    public void show(Graphics g,boolean dealer) {
        this.dealer = dealer;
        String path = "src/dk/mtdm/frontend/icons/";
        if (this.dealer){
            dealerCode(g, path);
        } else {
            playerCode(g, path);
        }
    }

    private void playerCode(Graphics g, String path) {
        int pathY = this.y()+height-this.h()-50;
        int pathX = this.x() + width/2-this.w();
        g.setColor(Color.white);
        g.fillRect(pathX, pathY, this.w(), this.h());
        g.setColor(Color.black);
        g.drawRect(pathX, pathY, this.w(), this.h());
        pathX = this.x() + this.w() / 2-64 + width/2-this.w();
        pathY = this.y()+height-this.h()/2-30-16;
        System.out.println("e:" + this.symbol);
        switch (this.symbol){
            case 1:
                Image hjerter = new ImageIcon(path + "hjerter.png").getImage();
                g.drawImage(hjerter, pathX, pathY, this);
                System.out.println("1");
                break;
            case 2:
                Image romber = new ImageIcon(path + "romber.png").getImage();
                g.drawImage(romber, pathX, pathY, this);
                System.out.println("2");
                break;
            case 3:
                Image klor = new ImageIcon(path + "klør.png").getImage();
                g.drawImage(klor, pathX, pathY, this);
                System.out.println("3");
                break;
            case 4:
                Image spar = new ImageIcon(path + "spar.png").getImage();
                g.drawImage(spar, pathX, pathY, this);
                System.out.println("4");
                break;
        }
        g.setFont(new Font("consolas",Font.BOLD,130));
        g.drawString(this.number,this.x()+width/2-this.w()/2-36,this.y()+height/2+this.h()/2+32);
    }

    private void dealerCode(Graphics g, String path) {
        this.y += 25;
        if (this.id == 0) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        if (this.id == 0) { //Checker om det er dealens første kort
            Image back = new ImageIcon("src/dk/mtdm/frontend/icons/back.png").getImage();
            g.drawImage(back,
                    this.x() + width/2-this.w(), this.y(),
                    this);
        } else {
            DrawCard(g, path);

        }
    }

    private void DrawCard(Graphics g, String path) {
        g.fillRect(this.x() + width / 2 - this.w(), this.y(), this.w(), this.h());
        g.setColor(Color.black);
        g.drawRect(this.x() + width / 2 - this.w(), this.y(), this.w(), this.h());

        int simpX = this.x() + this.w() / 2 - 64 + width / 2 - this.w();
        int simpY = this.y() + this.h() / 2;
        switch (symbol) {
            case 1:
                Image hjerter = new ImageIcon(path + "hjerter.png").getImage();
                g.drawImage(hjerter, simpX,simpY, this);
                break;
            case 2:
                Image romber = new ImageIcon(path + "romber.png").getImage();
                g.drawImage(romber, simpX,simpY, this);
                break;
            case 3:
                Image klor = new ImageIcon(path + "klør.png").getImage();
                g.drawImage(klor, simpX,simpY, this);
                break;
            case 4:
                Image spar = new ImageIcon(path + "spar.png").getImage();
                g.drawImage(spar, simpX,simpY, this);
                break;
        }
        g.setFont(new Font("consolas", Font.BOLD, 128));
        g.drawString(this.number, this.x() + this.w() / 2 - 36 + width / 2 - this.w(), this.y() + this.h() / 2 - 32);
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
