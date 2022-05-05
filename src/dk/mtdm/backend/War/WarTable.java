package dk.mtdm.backend.War;

import java.util.ArrayList;

import dk.mtdm.backend.BlackJack.CardObject;
import dk.mtdm.backend.BlackJack.PlayerHandObject;

public class WarTable {
  
  static public byte NUMBER_OF_PLAYERS;

  private static final byte NUMBER_OF_CARDS = 52+4; //normal cards and 3 jokers
  private static ArrayList <PlayerHandObject> players = new ArrayList <PlayerHandObject>();
  public static CardObject[] inPlay;
  private static ArrayList <CardObject> warBooty = new ArrayList<CardObject>();
  
  public WarTable(byte NumberOfPlayers) {
    WarTable.NUMBER_OF_PLAYERS = NumberOfPlayers;
    
    {
      ArrayList <CardObject> Deck = new ArrayList<CardObject>();
      
      
      for(byte symbol = 1; symbol < 4; symbol++){
        for(byte number = 0; number <= 13; number++){ //0 = joker
          Deck.add(new CardObject(number, symbol));
        }
      }

      for (Integer start = 0; start < NUMBER_OF_CARDS * 4; start++){//scrambles deck
        int scramble = start % NUMBER_OF_CARDS;
        
        CardObject temp = Deck.remove(scramble);
        int k = (int) (Math.random() * NUMBER_OF_CARDS);
        if(k < 0)
        k = (int) (k * -1);
        Deck.add((k % NUMBER_OF_CARDS) - 1, temp);
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

  static public CardObject[] drawHand(byte playerID){
    if(players.get(playerID).getHand().size() >= 2){
      CardObject[] cards = new CardObject[2];
    cards[0] = players.get(playerID).getHand().remove(0);
    cards[1] = players.get(playerID).getHand().remove(0);
    return(cards);
    }else if(players.get(playerID).getHand().size() == 1){
      CardObject[] LastCard = new CardObject[1];
        LastCard[0] = players.get(playerID).getHand().remove(0);
      return LastCard;
    }else{
      if(shuffleDiscard(playerID))
      return(drawHand(playerID));
      CardObject[] zerocard = new CardObject[1];
      zerocard[0] = new CardObject((byte)0,(byte)-1);
      players.get(playerID).IS_ALIVE = false;
      return(zerocard);
    }
  }

  static public void depositHand(byte playerID, CardObject Card){
    players.get(playerID).addCard(Card);
    shuffle(playerID);
  }

  static public void shuffle(byte playerID){
    byte numberOfCards = (byte) (players.get(playerID).getHand().size());
    for(byte i = 0; i < players.get(playerID).getHand().size()*4; i++){
      CardObject temp = players.get(playerID).getHand().remove(i);
      int k = (int) (Math.random() * numberOfCards);
      if(k < 0)
      k = (int) (k * -1);
      players.get(playerID).getHand().add(k % numberOfCards, temp);
    }
  }

  static public byte Winner(CardObject[] inPlai){
    byte high = 0;
    byte highID = 0;
    ArrayList<Byte> twoID = new ArrayList <Byte>();
    ArrayList<Byte> war = new ArrayList <Byte>();
    
    for(byte ID = 0; ID < NUMBER_OF_PLAYERS; ID++){
      byte value = inPlai[ID].getNumber();
      
      if(value == 1){
        value = 14;
      }
      if(value == 0){
        value = 20;
      }
      
      if(value == high){
        war.add(ID);
      }
      
      if(value > high){
        for(byte i = 0; war.size() > i; i++){
          war.remove(0);
        }
        highID = ID;
        high = value;
        war.add(ID);
      }
      if(value == 2){
        twoID.add(ID);
      }
    }
    if(high == 20 && twoID.size() >= 1){
      for(byte i = 0; war.size() > i; i++){
        war.remove(0);
      }
      for(byte i = 0; i < twoID.size(); i++){
        war.add(twoID.get(i));
      }
    }
    if(war.size() > 1){
      highID=warRun(war);
    }
    return(highID);
  }

  static public byte warRun(ArrayList<Byte> IDs){
    ArrayList <CardObject> warPlay = new ArrayList<CardObject>();
    for(byte iD = 0; iD < IDs.size(); iD++){
      byte ID = IDs.get(iD);
      CardObject[] hand = drawHand(ID);
      warBooty.add(hand[0]);
      warBooty.add(hand[1]);
      hand = drawHand(ID);
      warBooty.add(hand[0]);
      warPlay.add(hand[1]);
    }
    CardObject[] play = (CardObject[]) warPlay.toArray();
    byte iD = Winner(play);
    return(IDs.get(iD));
  }

  static public void playCard(CardObject unPlayedCard, CardObject playedCard, byte playerID){
    inPlay[playerID] = playedCard;
    if(unPlayedCard.getSymbol() == -1 || unPlayedCard == playedCard) return;
    depositHand(playerID, unPlayedCard);
  }

  static public void collectWinnings(byte playerID){
    for(byte i = 0; i < inPlay.length; i++){
      if(inPlay[i].getSymbol() != -1)
      players.get(playerID).addToDiscard(inPlay[i]);
    }
  }

  static public boolean shuffleDiscard(byte playerID){
    CardObject[] cards = players.get(playerID).emptyDiscard();
    
    if(cards.length == 0){
      return false;
    }

    for(byte i = 0; i < cards.length; i++){
      CardObject card = cards[i];
      players.get(playerID).addCard(card);
    }
    shuffle(playerID);
    return true;
  }

  static public boolean isAlive(byte playerID){
    
    return(players.get(playerID).IS_AI);
  }
}