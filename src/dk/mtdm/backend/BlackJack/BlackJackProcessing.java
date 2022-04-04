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
  static ArrayList <PlayerHandObject> players = new ArrayList<>();
  
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
  
  public static void runGame(){

  }
}