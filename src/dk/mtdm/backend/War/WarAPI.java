package dk.mtdm.backend.War;

import java.util.Scanner;

import dk.mtdm.backend.BlackJack.CardObject;

public class WarAPI {
  public static void main(byte numberOfPlayers) {
    WarTable table = new WarTable(numberOfPlayers);
    while (true){
      for(byte playerID = 0; playerID < table.NUMBER_OF_PLAYERS; playerID++){ //needs to allow for only 1 card left
        boolean deadPlayer = false;

        CardObject[] cards = table.drawHand(playerID);
        if(cards[0].getSymbol() == -1)
          deadPlayer = true;
        
        pickCard(cards);
        
        boolean action = pickCard(); //true = card 1
        
        if(action) table.playCard(cards[1],cards[0],playerID);
        else       table.playCard(cards[0],cards[1],playerID);
        
      }
      tempWinner(table);

      
      /**
       *next up
       * 3. remove people with 0 cards
       * 4. figure out when only 1 card
       */
      
    }
  }

  private static void pickCard(CardObject[] cards) {
    System.out.println("what will you play?");
    System.out.println();
    for(byte i = 0; i < 2; i++){
      String name = cards[i].getNumberString(); 
      name = translate(name);
      System.out.println( name + "of " + cards[i].getSymbolString());
    }
  }

  private static void tempWinner(WarTable table) {
    byte shortWinner = table.Winner(table.inPlay); 
    System.out.println("winner is: "+ shortWinner);
    table.collectWinnings(shortWinner);
  }

  private static boolean pickCard() {
    boolean action = true;
    while (true){
      try (Scanner scan = new Scanner(System.in)) {
        boolean succes = false;
        String  string = scan.nextLine();
        if(string.contains("1") && !string.contains("2")){
          succes = true;
          action = true;
        }else if (!string.contains("1") && string.contains("2")){
          succes = true;
          action = false;
        }
        if(succes)
        break;
        System.out.println("please use \" 1 \" or \" 2 \" to select the first or second card");
      }
    }
    return action;
  }

  private static String translate(String names){
    String name;
    switch (names) {
      case "j":
        name = "joker";
        break;
      
      case "A":
        name = "ace";
        break;
      
      case "X":
        name = "10";
        break;
      
      case "B":
        name = "jack";
        break;
      
      case "D":
        name = "queen";
        break;
      
      case "K":
        name = "king";
        break;
        
      default:
      name = names;
        break;
    }
    return(name);
  } 
}