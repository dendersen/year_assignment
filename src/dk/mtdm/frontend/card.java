package dk.mtdm.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static dk.mtdm.frontend.Draw.height;
import static dk.mtdm.frontend.Draw.width;


public class card implements ImageObserver {
    private final int symbol;
    private final String number;
    private int x = 0;
    private int y = 25;
    private int w = 200;
    private int h = 300;
    private final int id;
    private boolean dealer;
    private Image back;
    public Graphics g;
    public String path = "src/dk/mtdm/frontend/icons/";


    card(int symbol, String number, int id){
        this.symbol = symbol;
        this.number = number;
        this.id = id;
        this.x = 150 * this.id; // Afstand mellem dem / afstand relative mellem 0,0
    }

    public void show(Graphics g,boolean dealer,int pos,int length) {
        this.g = g;
        this.dealer = dealer;
        if (this.dealer){
            dealerCode(true);
        } else {
            playerCode(pos,length);
        }
    }

    private void playerCode(int pos, int length) {

//        rect(this.x + range.indexOf(this.id) * 75, this.y, this.w, this.h);
//        rect(this.x + 25 + range.indexOf(this.id) * 75  , this.y + 50, 50, 50);

        int pathX = this.x() + width / 2 - 100 * length;
        int pathY = this.y() + height - this.h() - 50;
        g.setColor(Color.white);
        g.fillRect(pathX, pathY, this.w(), this.h());
        g.setColor(Color.black);
        g.drawRect(pathX, pathY, this.w(), this.h());
        pathX = this.x() + width / 2 + 32 - 100 * length;
        pathY = this.y() + height - this.h() / 2 - 30 - 16;
        switch (this.symbol) {
            case 1 -> {
                Image hjerter = new ImageIcon(path + "hjerter.png").getImage();
                g.drawImage(hjerter, pathX, pathY, this);
            }
            case 2 -> {
                Image romber = new ImageIcon(path + "romber.png").getImage();
                g.drawImage(romber, pathX, pathY, this);
            }
            case 3 -> {
                Image klor = new ImageIcon(path + "klør.png").getImage();
                g.drawImage(klor, pathX, pathY, this);
            }
            case 4 -> {
                Image spar = new ImageIcon(path + "spar.png").getImage();
                g.drawImage(spar, pathX, pathY, this);
            }
        }
        g.setFont(new Font("consolas",Font.BOLD,130));

        switch (this.number) {
            case "K" -> {
                Image Kongen;
                if (this.symbol == 1 || this.symbol == 2) {
                    Kongen = new ImageIcon(path + "rød_konge.png").getImage();
                } else {
                    Kongen = new ImageIcon(path + "sort_konge.png").getImage();
                }
                g.drawImage(Kongen, pathX, this.y()+height/2+this.h()/2-64-10, this);
            }
            case "D" -> {
                Image Dronning;
                if (this.symbol == 1 || this.symbol == 2) {
                    Dronning = new ImageIcon(path + "rød_dronning.png").getImage();
                } else {
                    Dronning = new ImageIcon(path + "sort_dronning.png").getImage();
                }
                g.drawImage(Dronning, pathX, this.y()+height/2+this.h()/2-64-10, this);
            }
            case "B" -> {
                Image Bonde;
                if (this.symbol == 1 || this.symbol == 2) {
                    Bonde = new ImageIcon(path + "rød_bonde.png").getImage();
                } else {
                    Bonde = new ImageIcon(path + "sort_bonde.png").getImage();
                }
                g.drawImage(Bonde, pathX, this.y()+height/2+this.h()/2-64-10, this);
            }
            case "A" -> {
                Image Es;
                if (this.symbol == 1 || this.symbol == 2) {
                    Es = new ImageIcon(path + "rød_es.png").getImage();
                } else {
                    Es = new ImageIcon(path + "sort_es.png").getImage();
                }
                g.drawImage(Es, pathX, this.y()+height/2+this.h()/2-64-10, this);
            } //64
            default -> {//36
                g.drawString(this.number,pathX+18,this.y()+height/2+this.h()/2+32);
            }
        }
    }

    /**
     * @param flip bestemmer om det første kort skal være flipet, true ikke vis og false  vis
     */
    public void dealerCode(boolean flip) {

        if (g == null) return;
        if (this.id == 0) { //Checker om det er dealens første kort
            if (flip) {
                DrawCard(path, false);
            } else {
                DrawCard(path, true);
            }
            if(flip) {
                back = new ImageIcon("src/dk/mtdm/frontend/icons/back.png").getImage();
                g.drawImage(back,
                        this.x() + width/2-this.w(), this.y(),
                        this);
            }
        } else {
            DrawCard(path ,false);
        }
    }


    private void DrawCard(String path, boolean hide) {
        g.setColor(Color.white);
        g.fillRect(this.x() + width / 2 - this.w(), this.y(), this.w(), this.h());
        g.setColor(Color.black);
        g.drawRect(this.x() + width / 2 - this.w(), this.y(), this.w(), this.h());
        if (hide) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.BLACK);
        }
        int simpX = this.x() + this.w() / 2 - 64 + width / 2 - this.w();
        int simpY = this.y() + this.h() / 2;
        switch (symbol) {
            case 1 -> {
                Image hjerter = new ImageIcon(path + "hjerter.png").getImage();
                g.drawImage(hjerter, simpX, simpY, this);
            }
            case 2 -> {
                Image romber = new ImageIcon(path + "romber.png").getImage();
                g.drawImage(romber, simpX, simpY, this);
            }
            case 3 -> {
                Image klor = new ImageIcon(path + "klør.png").getImage();
                g.drawImage(klor, simpX, simpY, this);
            }
            case 4 -> {
                Image spar = new ImageIcon(path + "spar.png").getImage();
                g.drawImage(spar, simpX, simpY, this);
            }
        }

        g.setFont(new Font("consolas", Font.BOLD, 128));
        switch (this.number) {
            case "K" -> {
                Image Kongen;
                if (this.symbol == 1 || this.symbol == 2) {
                    Kongen = new ImageIcon(path + "rød_konge.png").getImage();
                } else {
                    Kongen = new ImageIcon(path + "sort_konge.png").getImage();
                }
                g.drawImage(Kongen, this.x() + this.w() / 2 - 64 + width / 2 - this.w(), this.y() + this.h() / 2 - 128, this);
            }
            case "D" -> {
                Image Dronning;
                if (this.symbol == 1 || this.symbol == 2) {
                    Dronning = new ImageIcon(path + "rød_dronning.png").getImage();
                } else {
                    Dronning = new ImageIcon(path + "sort_dronning.png").getImage();
                }
                g.drawImage(Dronning, this.x() + this.w() / 2 - 64 + width / 2 - this.w(), this.y() + this.h() / 2 - 128, this);
            }
            case "B" -> {
                Image Bonde;
                if (this.symbol == 1 || this.symbol == 2) {
                    Bonde = new ImageIcon(path + "rød_bonde.png").getImage();
                } else {
                    Bonde = new ImageIcon(path + "sort_bonde.png").getImage();
                }
                g.drawImage(Bonde, this.x() + this.w() / 2 - 64 + width / 2 - this.w(), this.y() + this.h() / 2 - 128, this);
            }
            case "A" -> {
                Image Es;
                if (this.symbol == 1 || this.symbol == 2) {
                    Es = new ImageIcon(path + "rød_es.png").getImage();
                } else {
                    Es = new ImageIcon(path + "sort_es.png").getImage();
                }
                g.drawImage(Es, this.x() + this.w() / 2 - 64 + width / 2 - this.w(), this.y() + this.h() / 2 - 128, this);
            }
            default -> {
                g.setColor(Color.BLACK);
                g.drawString(this.number,this.x() + this.w() / 2 - 36 + width / 2 - this.w(),  this.h() / 2 + 32);
            }
        }
    }
//    this.x() + this.w() / 2 - 64 + width / 2 - this.w();
//this.y() + this.h() / 2;

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
