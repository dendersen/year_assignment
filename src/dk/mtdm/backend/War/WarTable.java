package dk.mtdm.backend.War;

import java.util.ArrayList;

import dk.mtdm.backend.BlackJack.CardObject;
import dk.mtdm.backend.BlackJack.PlayerHandObject;

public class WarTable {
  
  private final byte NUMBER_OF_PLAYERS;
  private final byte NUMBER_OF_CARDS = 52+4; //normal cards and 3 jokers
  private static ArrayList <PlayerHandObject> players = new ArrayList <PlayerHandObject>();
  
  public WarTable(byte NumberOfPlayers) {
    NUMBER_OF_PLAYERS = NumberOfPlayers;
    
    for (Integer start = 0; start < NUMBER_OF_CARDS * 4; start++){//scrambles deck
      int scramble = start % NUMBER_OF_CARDS;
      
      
      ArrayList <CardObject> Deck = new ArrayList<CardObject>();
      
      for(byte symbol = 1; symbol < 4; symbol++){
        for(byte number = 0; number < 13; number++){
          Deck.add(new CardObject(number, symbol));
        }
      }
      
      for(byte i = 0; i < NUMBER_OF_CARDS*4; i++){
        CardObject temp = Deck.remove(scramble);
        int k = (int) (Math.random() * NUMBER_OF_CARDS);
        if(k < 0)
        k = (int) (k * -1);
        Deck.add(k % NUMBER_OF_CARDS, temp);
      }
      
      
    }
  }
}