package dk.mtdm.backend.BlackJack;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackJack {
  final static byte NUMBER_OF_SYMBOLS = 4;
  final static byte MAX_CARD_VALUE = 13;
  final static byte NUMBER_OF_CARD_SETS = 1;
  final static byte NUMBER_OF_CARDS = NUMBER_OF_SYMBOLS*MAX_CARD_VALUE*NUMBER_OF_CARD_SETS;
  /***@param deck row of card objects*/
  static ArrayList<CardObject> deck = new ArrayList<>();
  private static CardObject cardObject;
  
  public static void main(byte antalSpillere) {
    
    setup();
    
    System.out.println("starting\033[38;5;206m BlackJack\u001B[0m, please wait.");
    
    for (byte i = 0; i < antalSpillere-1; i++){
      // byte [] card = drawCards((byte) (2));
      // TEMPDrawCardToScreen(card, false);
    }
    // byte [] card = drawCards((byte) (2));
      // TEMPDrawCardToScreen(card, true);
    for(byte i = 0; i < deck.size(); i++){
      try{
      cardObject = deck.get(i);
      System.out.println("index " + 1 + " [" + cardObject.getNumber()+","+cardObject.getSymbol()+","+cardObject.getSet()+"]");
      } catch (Exception e){
        System.out.println(e);
      }
    }
  }
  
  private static void setup() {
    for(byte i = 1; i <= NUMBER_OF_SYMBOLS; i++){
      for(byte j= 1; j <= MAX_CARD_VALUE; j++){
        for(byte l = 1; l <= NUMBER_OF_CARD_SETS; l++){
          deck.add(new CardObject(i,j,l));
        }
      }
    }

    for (byte i = 0; i < NUMBER_OF_CARDS * 2; i++){
      deck.
    }
  }

  // private static byte[] drawCards(byte numberOfDraws) {
    
    // while(true){
    //   byte[] card = new byte[2];
    //   card[0] = (byte) (Math.random()*4);
    //   card[1] = (byte) (Math.random()*13);
      
    //   if(!cards[card[0]][card[1]]){
    //   cards[card[0]][card[1]] = true;
    //   return (card);
    //   }
    // }
  // }
  public static void TEMPDrawCardToScreen(byte[] card,boolean shown){
    
    card[1] += (byte) (1);
    String cardString = Arrays.toString(card);
    cardString = cardString.replace("0,", "hjerter").replace("1,", "romber").replace("2,", "spar").replace("3,", "kloer");
    cardString = cardString.replace("11", "bonde").replace("12","dronning").replace("13","konge").replace("1", "es");
    if(shown)
      System.out.println(cardString);
    else
      System.out.println("[  ,  ]");
  }
  
}