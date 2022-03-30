package dk.mtdm.backend.BlackJack;

import java.util.ArrayList;
import java.util.Arrays;


public class BlackJackProcessing {
  final static byte NUMBER_OF_SYMBOLS = 4;
  final static byte MAX_CARD_VALUE = 13;
  final static byte NUMBER_OF_CARD_SETS = 1;
  final static byte NUMBER_OF_CARDS = NUMBER_OF_SYMBOLS*MAX_CARD_VALUE*NUMBER_OF_CARD_SETS;
  /***@param deck row of card objects*/
  static ArrayList<CardObject> deck = new ArrayList<>();
  
  public static void main(byte antalSpillere) {
    
    setup();
    
    System.out.println("starting\033[38;5;206m BlackJack\u001B[0m, please wait.");
    
    for (byte i = 0; i < antalSpillere-1; i++){
      CardObject [] card = drawCards((byte) (2));
      TEMPDrawCardToScreen(card, true);
    }
    CardObject [] card = drawCards((byte) (2));
      TEMPDrawCardToScreen(card, true);
    // for(byte i = 0; i < deck.size(); i++){
    //   try{
    //   cardObject = deck.get(i);
    //   System.out.println("index " + i + " [" + cardObject.getNumber()+","+cardObject.getSymbol()+","+cardObject.getSet()+"]");
    //   } catch (Exception e){
    //     System.out.println(e);
    //   }
    // }
  }
  
  private static void setup() {
    for(byte i = 1; i <= NUMBER_OF_SYMBOLS; i++){
      for(byte j= 1; j <= MAX_CARD_VALUE; j++){
        for(byte l = 1; l <= NUMBER_OF_CARD_SETS; l++){
          deck.add(new CardObject(i,j,l));
        }
      }
    }

    for (Integer start = 0; start < NUMBER_OF_CARDS * 4; start++){
      byte scramble = (byte) ((start * (byte) (Math.random() * NUMBER_OF_CARDS)) % NUMBER_OF_CARDS);
      CardObject temp = deck.remove(scramble);
      byte k = (byte) (Math.random() * NUMBER_OF_CARDS);
      deck.add(k % NUMBER_OF_CARDS, temp);
    }
  }

  private static CardObject[] drawCards(byte numberOfDraws) {
    CardObject[] card = new CardObject[numberOfDraws];
    for(byte i = 0; i < numberOfDraws; i++){
      card[i] = deck.remove(0);
    }
    return (card);
  }
  
  public static void TEMPDrawCardToScreen(CardObject[] card,boolean shown){ //needs rework
    String cardString = "";
    for(byte i = 0; i < card.length; i++){
      byte[] cardArray = {card[i].getSymbol(),card[i].getNumber()};
      cardString += Arrays.toString(cardArray);
    }
    cardString = cardString.replace("1,", "hjerter").replace("2,", "romber").replace("3,", "spar").replace("4,", "kloer");
    cardString = cardString.replace("11", "bonde").replace("12","dronning").replace("13","konge").replace("10","ee").replace("1", "es").replace("ee", "10");
    if(shown)
      System.out.println(cardString);
    else
      System.out.println("[  ,  ]");
  }
  
}