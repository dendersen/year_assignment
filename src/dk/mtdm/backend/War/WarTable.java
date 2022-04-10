package dk.mtdm.backend.War;

import java.util.ArrayList;

import dk.mtdm.backend.BlackJack.CardObject;
import dk.mtdm.backend.BlackJack.PlayerHandObject;

public class WarTable {
  
  private final byte NUMBER_OF_PLAYERS;

  private final byte NUMBER_OF_CARDS = 52+4; //normal cards and 3 jokers
  private static ArrayList <PlayerHandObject> players = new ArrayList <PlayerHandObject>();
  private static CardObject[] inPlay;
  
  public WarTable(byte NumberOfPlayers) {
    this.NUMBER_OF_PLAYERS = NumberOfPlayers;
    
    {
      ArrayList <CardObject> Deck = new ArrayList<CardObject>();
      
      
      for(byte symbol = 1; symbol < 4; symbol++){
        for(byte number = 0; number < 13; number++){ //0 = joker
          Deck.add(new CardObject(number, symbol));
        }
      }

      for (Integer start = 0; start < NUMBER_OF_CARDS * 4; start++){//scrambles deck
        int scramble = start % NUMBER_OF_CARDS;
        
        for(byte i = 0; i < NUMBER_OF_CARDS*4; i++){
          CardObject temp = Deck.remove(scramble);
          int k = (int) (Math.random() * NUMBER_OF_CARDS);
          if(k < 0)
          k = (int) (k * -1);
          Deck.add(k % NUMBER_OF_CARDS, temp);
        }
      }
      
      for (byte i = 0; i > NUMBER_OF_PLAYERS; i++){
        players.add(new PlayerHandObject());
      }
      byte deckRole = 0;
      while(Deck.size() > 0){
        deckRole++;
        players.get(deckRole % NUMBER_OF_PLAYERS).addCard(Deck.remove(0));
      }
    }
    inPlay = new CardObject[NumberOfPlayers];
  }

  public CardObject[] drawHand(byte playerID){
    CardObject[] cards = new CardObject[2];
    cards[0] = players.get(playerID).getHand().remove(0);
    cards[1] = players.get(playerID).getHand().remove(0);
    return(cards);
  }

  public void depositHand(byte playerID, CardObject Card){
    players.get(playerID).addCard(Card);
    shuffle(playerID);
  }

  public void shuffle(byte playerID){
    byte numberOfCards = (byte) (players.get(playerID).getHand().size());
    for(byte i = 0; i < players.get(playerID).getHand().size()*4; i++){
      CardObject temp = players.get(playerID).getHand().remove(i);
      int k = (int) (Math.random() * numberOfCards);
      if(k < 0)
      k = (int) (k * -1);
      players.get(playerID).getHand().add(k % numberOfCards, temp);
    }
  }

  public byte Winner(){
    byte high = 0;
    byte highID = 0;
    boolean controll = true;
    boolean noTwo = true;
    ArrayList<Byte> twoID = new ArrayList <Byte>();
    ArrayList<Byte> war = new ArrayList <Byte>();
    for(byte i = 0; i < NUMBER_OF_PLAYERS; i++){
      byte value = inPlay[i].getNumber();
      if(controll && value > 1){
        if (value == 2){
          noTwo = false;
          twoID.add(i);
        }
        if(value == high){
          war.add(i);
        } else if(value > high){
          high = value;
          highID = i;
          while (war.size() > 0){
            war.remove(0);
          }
          war.add(i);
        }
      }
      else if(value == 0){
        if(noTwo){
          controll = false;
          if(value == high){
            war.add(i);
          }else if (20 > high){
            high = 20;
            highID = i;
            while (war.size() > 0){
              war.remove(0);
            }
            war.add(i);
          }
        }
        else{
          controll = false;
        }
      }
      else if (value == 1 && controll){
        
      }
    }
  return(highID);
  }
}