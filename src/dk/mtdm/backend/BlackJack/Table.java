package dk.mtdm.backend.BlackJack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @param Table sets the "table" used in blackjack, gives out starting cards to players and centralises variables for players. always run the setup right after the constructor.
 */

public class Table {
  private static PlayerHandObject[] players;
  
  public static byte ACTIVE_PLAYER_ID;
  public static byte NUMBER_OF_PLAYERS;
  
  private final int NUMBER_OF_CARDS;

  private final byte NUMBER_OF_SETS;
  static private final byte NUMBER_OF_SYMBOLS = 4;
  static private final byte MAX_CARD_VALUE = 13;
  
  static private ArrayList <CardObject> Deck = new ArrayList<CardObject>();
  /**
   * @param numberOfPlayers the number of players present in the game (1 player means only dealer)
   * @param sets the number of full card sets in play (52 cards each)
   */
  public Table( byte numberOfPlayers, byte sets){
    Table.NUMBER_OF_PLAYERS = numberOfPlayers;
    if(sets<=0)
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
      players[i].save(i,"BlackJack");
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

  public void saveState(byte currentPlayerID){
    for(byte i = 0; i < players.length; i++){
      players[i].save(i,"BlackJack");
    }
    {//saves the hand id of active player
      try{
        File file = new File("Saves\\activePlayer.BlackJack");
        if(file.createNewFile()){
          System.out.println("file created: " + file.getName());
        }else {
          System.out.println("file already exists.");
        }
      }catch (IOException e){
        System.out.println("an error occured while making file");
        e.printStackTrace();
      }
      try {
        FileWriter myWriter = new FileWriter("Saves\\activePlayer.BlackJack");
        myWriter.write(currentPlayerID);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
  }

  public byte loadState(){
    for(byte i = 0; i < players.length; i++){
      players[i].load(i,"BlackJack");
    }
    byte data = 0;
    try {
      File file = new File("Saves\\activePlayer.BlackJack");
      Scanner myReader = new Scanner(file);
      data = myReader.nextByte();
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return (data);
  }

  public static PlayerHandObject getPlayer(byte i){
    return(players[i]);
  }

  /**
   * @param playerID id of the player you want to know the available actions from
   * @return booleans that describe available actions: [1] = hit, [2] = stand, [3] = hit can not kill you
   */
  public static boolean[] availableActions(byte playerID){
    return(
      BlackJackProcessing.availablePlayerActions(
        BlackJackProcessing.playerValue(playerID)
      )
    );
  }

  public static ArrayList <CardObject> getDeck(){
    return(Deck);
  }
}
