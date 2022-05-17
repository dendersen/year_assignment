package dk.mtdm.frontend;

import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.controller.BlackJackController;
import dk.mtdm.controller.CurrentData;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Objects;

public class window extends PApplet {
    public static void main(String[] args) {
        PApplet.main("window");
    }
    private static button Hit;
    private static button Stand;
    private static button AI;
    private static CurrentData Trans;
    public void settings() {
        size(1000,1000);
    }
    private static int showPlayerID;

    public void setup() {
        background(0,200,0);
        Hit = new button(width/2 - 200,height/2,"Hit");
        Stand = new button(width/2 + 200,height/2,"Stand");
        AI = new button(+225, height-225,"AI");
    }

    public void draw() {
        background(0,200,0);
        checkDeck();
        checkButton();
    }

    private final ArrayList<ArrayList<card>> players = new ArrayList<ArrayList<card>>();
    private void checkDeck() {
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
        for (int i = 0; i < players.get(0).size(); i++ ) {
            players.get(0).get(i).show();
        }
        for (int i = 0; i < players.get(showPlayerID).size(); i++ ) {
            players.get(showPlayerID).get(i).show();
        }
        players.clear();
    }

    public void checkButton() {
        if (!Hit.hide) {
            Hit.show();
        }
        if (!Stand.hide) {
            Stand.show();
        }
        AI.hide = false;
        AI.show();

    }

    public static void buttons(CurrentData data) {
        Trans = data;

        if (Trans.AVAILABLE_ACTIONS[0]) {
            Hit.showBoolean();
            // laver en knap til hit
        }
        if (Trans.AVAILABLE_ACTIONS[1]){
            // laver en knap til stand
            Stand.showBoolean();
        }

        showPlayerID = Trans.playerID;
    }
    public void mousePressed() {
        Hit.clicked("Hit");
        Stand.clicked("Stand");
        AI.clicked("AI");
    }

    public static void setTransfer(CurrentData Transfer){
        Trans = Transfer;
    }

    public static void returnBtn(){
        BlackJackController.theGame(Trans);
    }

    public class button {
        private final int x;
        private final int y;
        private final int w = 150;
        private final int h = 50;
        private final String input;
        public boolean hide = true;
        button(int x,int y,String input) {
            this.x = x;
            this.y = y;
            this.input = input;
        }

        public void show() {
//            if(hide) {
//                return;
//            }
            push();
            if(mouseX > x - w / 2 && mouseX < x + w / 2 && mouseY > y - h / 2 && mouseY < y + h / 2){
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

        private void showBoolean(){
            hide = false;
        }
        private void hideBoolean(){
            hide = true;
        }
        public void clicked(String id) {
            if(hide) {
                return;
            }
            if(!(mouseX > x - w / 2 && mouseX < x + w / 2 && mouseY > y - h / 2&& mouseY < y + h / 2)){
               return;
            }
            if(Objects.equals(id, "Hit")){
                Trans.setAction(true);
            }
            if(Objects.equals(id, "Stand")) {
                Trans.setAction(false);
            }
            if(Objects.equals(id, "AI")) {
                BlackJackController.theGame(Trans);
                print("test");
            }
            hide = true;
            returnBtn();
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
        private boolean hide = false;

        card(String symbol, String Number, int id, int dealer, int size) {
            this.symbol = symbol;
            this.Number = Number;
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
