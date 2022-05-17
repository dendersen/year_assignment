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

    public void setup() {
        background(0,200,0);
        Button = new button(100,100,"Hit");
        test = new button(300,100,"Stand");
    }


    public void settings() {
        size(1000,1000);
        //        noLoop();
    }

    public void draw() {
        Button.show();
        test.show();
        checkDeck();
    }


    private final ArrayList<card> deck = new ArrayList<card>();

    private void checkDeck() {
        deck.add(new card("hjerter","B"));
        deck.get(0).show();
        //        for (int i = 0; i < 10; i++) {
//            deck.add(new card(
//                    Table.getPlayer((byte) i).getHand().get(i).getSymbolString(),
//                    Table.getPlayer((byte) i).getHand().get(i).getNumberString()
//            ));
//        }

    }


    public void mousePressed() {
        Button.clicked();
        test.clicked();
    }
    public class button {
        private int x;
        private int y;
        private int w = 100;
        private int h = 50;
        private String input;
        button(int x,int y,String input) {
            this.x = x;
            this.y = y;
            this.input = input;
        }

        public void show() {
            if(mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h){
                fill(240);
            } else {
                fill(255);
            }
            if(Objects.equals(input, "Stand")) {
                w = 150;
            }
            rect(x,y,w,h);
            fill(0);
            textAlign(CENTER);
            textSize(50);
            text(input,x + w / 2,y + h - 7);
        }

        public void clicked() {
            if(mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h){
               print("test");
            }
        }
    }

    public class card {
        private String symbol;
        private String Number;
        private int x = 100;
        private int y = 100;
        private int w = 200;
        private int h = 300;
        private final String path = "src/dk/mtdm/frontend/icons/";

        card(String symbol, String Number) {
            this.symbol = symbol;
            this.Number = Number;
        }

        public void show() {
            push();
            fill(255);
            rect(x,y,w,h);
            showSymbol();
            showNumber();
            pop();
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
                } //64
                default -> {//36
                    fill(0);
                    textAlign(CENTER);
                    textSize(50);
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
