package dk.mtdm.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class card implements ImageObserver {
    private String symbol;
    private String number;
    private int x = 0;
    private int y = 0;
    private int w = 200;
    private int h = 300;
    card(String symbol, String number,int x, int y){
        this.symbol = symbol;
        this.number = number;
        this.x = x;
        this.y = y;
        System.out.println(this.symbol+ " " + this.number);
    }

    public void cards(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.x(), this.y(), this.w(),this.h());
        g.setColor(Color.black);
        g.drawRect(this.x(), this.y(), this.w(),this.h());
        switch (symbol){
            case "hearts":
                Image image = new ImageIcon("src/icons/hearts.png").getImage();
                g.drawImage(image,this.x()+this.w()/2-64,this.y()+this.h()/2,this);
            case "":
                System.out.println("Der er kun hearts");
        }
        g.setFont(new Font("Roboto",Font.BOLD,128));
        g.drawString(number,this.x()+this.w()/2-32,this.y()+this.h()/2-30);
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
