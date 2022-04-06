package dk.mtdm.backend.BlackJack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @param Table sets the "table" used in blackjack, gives out starting cards to players and centralises variables for players. always run the setup right after the constructor.
 */

public class Table {
  private static PlayerHandObject[] players;
  
  public final byte NUMBER_OF_PLAYERS;
  public static byte ACTIVE_PLAYER_ID;
  
  private final int NUMBER_OF_CARDS;

  private final byte NUMBER_OF_SETS;
  static private final byte NUMBER_OF_SYMBOLS = 4;
  static private final byte MAX_CARD_VALUE = 13;
  
  static private ArrayList <CardObject> Deck = new ArrayList<CardObject>();
  /**
   * @param numberOfPlayers the number of players present in the game (dealer is player 0)
   * @param sets the number of full card sets in play (52 cards each)
   */
  public Table( byte numberOfPlayers, byte sets){
    this.NUMBER_OF_PLAYERS = numberOfPlayers;
    if(sets>=0)
    sets = 1;
    this.NUMBER_OF_SETS = sets;
    Table.players = new PlayerHandObject[numberOfPlayers];
    this.NUMBER_OF_CARDS = NUMBER_OF_SYMBOLS * MAX_CARD_VALUE * NUMBER_OF_SETS;
  }
  
  public void setup(){
    for(byte l = 1; l <= NUMBER_OF_SETS; l++){
      for(byte i = 1; i <= NUMBER_OF_SYMBOLS; i++){ //creates deck
        for(byte j= 1; j <= MAX_CARD_VALUE; j++){
          Deck.add(new CardObject(j, i));
        }
      }
    }
    for (Integer start = 0; start < NUMBER_OF_CARDS * 4; start++){//scrambles deck
      int scramble = start % NUMBER_OF_CARDS;
      
      CardObject temp = Deck.remove(scramble);
      int k = (int) (Math.random() * NUMBER_OF_CARDS);
      if(k < 0)
      k = (int) (k * -1);
      Deck.add(k % NUMBER_OF_CARDS, temp);
    }

    playerSetup();
    
    initialSave();
  }
  
  private void playerSetup(){
    for (byte i = 0; i < NUMBER_OF_PLAYERS; i++){
      CardObject [] card = drawCards((byte) (2));
      PlayerHandObject player = new PlayerHandObject();
      player.addCard(card[0]);
      player.addCard(card[1]);
      players[i] = player;
    }
  }
  
  public static CardObject[] drawCards(byte numberOfDraws) {
    CardObject[] card = new CardObject[numberOfDraws];
    for(byte i = 0; i < numberOfDraws; i++){
      card[i] = Deck.remove(0);
    }
    return (card);
  }
  
  private void initialSave(){
    for(byte i = 0; i < players.length; i++){
      players[i].save(i);
    }
  }
  
  public byte getNumber(byte playerID, byte cardNumber){
    return (players[playerID].getHand().get(cardNumber).getNumber());
  }
  
  public String getSymbol(byte playerID, byte cardNumber){
    byte[] bytes = {players[playerID].getHand().get(cardNumber).getSymbol()};
    String string = Arrays.toString(bytes);
    String done = string.replace("[1]", "hjerter").replace("[2]", "romber").replace("[3]", "spar").replace("[4]", "kloer");
    return(done);
  }
  
  public int getTotalNumberOfCards(){
    return( NUMBER_OF_CARDS);
  }
  
  public byte getPlayerNumberOfCards(byte playerID){
    return((byte) (players[playerID].getHand().size()));
  }

  public String getNumberString(byte playerID, byte cardNumber){
    byte[] bytes = {players[playerID].getHand().get(cardNumber).getNumber()};
    String string = Arrays.toString(bytes);
    String cardString = string.replace("11", "bonde").replace("12","dronning").replace("13","konge").replace("10","ee").replace("1", "es").replace("ee", "10").replace("[", "").replace("]", "");
    return(cardString);
  }

  public void saveState(){
    //unfinished
  }

  public static PlayerHandObject getPlayer(byte playerID){
    return(players[playerID]);
  }
}
