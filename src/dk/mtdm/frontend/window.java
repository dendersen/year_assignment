package dk.mtdm.frontend;

import dk.mtdm.backend.BlackJack.Table;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Objects;

public class window extends PApplet {
    public static void main(String[] args) {
        PApplet.main("window");
    }
    private button Button;
    private button test;

    public void settings() {
        size(1000,1000);
    }

    public void setup() {
        background(0,200,0);
        Button = new button(width/2 - 200,height/2,"Hit");
        test = new button(width/2 + 200,height/2,"Stand");
    }

    public void draw() {
        background(0,200,0);
        Button.show();
        test.show();
        checkDeck();
    }

    private final ArrayList<ArrayList<card>> players = new ArrayList<ArrayList<card>>();
    private void checkDeck() {
//        deck.add(new card("hjerter","B"));
//        deck.get(0).show();
        //        for (int i = 0; i < 10; i++) {
//            deck.add(new card(
//                    Table.getPlayer((byte) i).getHand().get(i).getSymbolString(),
//                    Table.getPlayer((byte) i).getHand().get(i).getNumberString()
//            ));
//        }
        
        for(int j = 0; j < Table.NUMBER_OF_PLAYERS; j++) {
            ArrayList<card> deck = new ArrayList<>();
            for(int i = 0; i < Table.getPlayer((byte) j).getHand().size(); i++) {
                deck.add(new card(
                    Table.getPlayer((byte) j).getHand().get(i).getSymbolString(),
                    Table.getPlayer((byte) j).getHand().get(i).getNumberString(),
                    i,
                    j,
                    Table.getPlayer((byte) j).getHand().size()
                ));
            }
            players.add(deck);
        }
        for (int j = 0; j < players.size(); j++) {
            for (int i = 0; i < players.get(j).size(); i++ ) {
                players.get(j).get(i).show();
            }
        }
        players.clear();
    }


    public void mousePressed() {
        Button.clicked();
        test.clicked();
    }
    public class button {
        private int x;
        private int y;
        private int w = 150;
        private int h = 50;
        private final String input;
        public boolean hide = false;
        button(int x,int y,String input) {
            this.x = x;
            this.y = y;
            this.input = input;
        }

        public void show() {
            if(hide) {
                return;
            }
            push();
            if(mouseX > x - w / 2 && mouseX < x + w / 2 && mouseY > y - h / 2&& mouseY < y + h / 2){
                fill(220);
            } else {
                fill(255);
            }
            rectMode(CENTER);
            rect(x,y,w,h);
            fill(0);
            textAlign(CENTER);
            textSize(50);
            text(input,x,y + h / 2 - 7);
            pop();
        }

        public void clicked() {
            if(mouseX > x - w / 2 && mouseX < x + w / 2 && mouseY > y - h / 2&& mouseY < y + h / 2){
               print("test");
               hide = true;
            }
        }
    }

    public class card {
        private final String symbol;
        private final String Number;


        private final int w = 200;
        private final int h = 300;
        private int x = width / 2;
        private int y = height - h - 25;
        private final String path = "src/dk/mtdm/frontend/icons/";
        private int dealer;
        private int id;
        private boolean hide = false;

        card(String symbol, String Number, int id, int dealer, int size) {
            this.symbol = symbol;
            this.Number = Number;
            this.id = id;
            this.dealer = dealer;
            this.x += id * 125;
            this.x -= ((size - 1) * 175 + 200) / 2;
            if (dealer == 0) {
                this.y = 25;
            }
            if(dealer == 0 && id == 0) {
                hide = true;
            }
        }

        public void show() {
            if(hide) {
                PImage back = null;
                back = loadImage(path + "back.png");
                image(back,x,y);
                return;
            }
            push();
            fill(255);
            rect(x,y,w,h);
            showSymbol();
            showNumber();
            pop();
        }

        public void showHiden() {
            hide = false;
            show();
        }

        private void showNumber() {
            PImage imgNumber = null;

            switch (this.Number) {
                case "K" -> {
                    if (Objects.equals(this.symbol, "hjerter") || Objects.equals(this.symbol, "romber")) {
                        imgNumber = loadImage(path + "rød_konge.png");
                    } else {
                        imgNumber = loadImage(path + "sort_konge.png");
                    }
                }
                case "D" -> {
                    if (Objects.equals(this.symbol, "hjerter") || Objects.equals(this.symbol, "romber")) {
                        imgNumber = loadImage(path + "rød_dronning.png");
                    } else {
                        imgNumber = loadImage(path + "sort_dronning.png");
                    }
                }
                case "B" -> {
                    if (Objects.equals(this.symbol, "hjerter") || Objects.equals(this.symbol, "romber")) {
                        imgNumber = loadImage(path + "rød_bonde.png");
                    } else {
                        imgNumber = loadImage(path + "sort_bonde.png");
                    }
                }
                case "A" -> {
                    if (Objects.equals(this.symbol, "hjerter") || Objects.equals(this.symbol, "romber")) {
                        imgNumber = loadImage(path + "rød_es.png");
                    } else {
                        imgNumber = loadImage(path + "sort_es.png");
                    }
                }
                default -> {
                    fill(0);
                    textAlign(CENTER);
                    textSize(100);
                    text(this.Number,x + w / 2,y + h / 4 * 3);
                }
            }
            if(!(imgNumber == null)) {
                imageMode(CENTER);
                image(imgNumber,x + w / 2, y + h / 4 * 3);
            }
        }

        private void showSymbol() {


            PImage imgSymbol = null;
            switch (this.symbol) {
                case "hjerter" -> {
                    imgSymbol = loadImage(path + "hjerter.png");
                }
                case "romber" -> {
                    imgSymbol = loadImage(path + "romber.png");
                }
                case "klør" -> {
                    imgSymbol = loadImage(path + "klør.png");
                }
                case "spar" -> {
                    imgSymbol = loadImage(path + "spar.png");
                }
            }
            imageMode(CENTER);
            image(imgSymbol,x + w / 2, y + h / 4);
        }
    }

}
