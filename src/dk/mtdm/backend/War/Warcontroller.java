package dk.mtdm.backend.War;

import java.util.ArrayList;
import java.util.Scanner;

import dk.mtdm.backend.BlackJack.CardObject;

public class Warcontroller {
  public static void main(byte numberOfPlayers) {
    WarTable table = new WarTable(numberOfPlayers);
    while (true){
      for(byte playerID = 0; playerID < WarTable.NUMBER_OF_PLAYERS; playerID++){ //needs to allow for only 1 card left
        boolean deadPlayer = false;

        CardObject[] cards = WarTable.drawHand(playerID);
        if(cards[0].getSymbol() == -1)
          deadPlayer = true;
        
        if(!deadPlayer && cards.length == 2){
          talkCard(cards,playerID);
          boolean action = pickCard(); //true = card 1
          
          if(action) WarTable.playCard(cards[1],cards[0],playerID);
          else       WarTable.playCard(cards[0],cards[1],playerID);
        }else
        WarTable.playCard(cards[0], cards[0], playerID);
      }
      tempWinner(table);
      byte winner = checkForWinner();
      if(winner != -1){
        win(winner);
        break;
      }
    }
  }

  private static void talkCard(CardObject[] cards, byte playerID) {
    System.out.println("what will you play?\n" + "player: " + playerID);
    System.out.println();
    for(byte i = 0; i < 2; i++){
      String name = cards[i].getNumberString(); 
      name = translate(name);
      System.out.println( name + " of " + cards[i].getSymbolString());
    }
  }

  private static void tempWinner(WarTable table) {
    byte shortWinner = WarTable.Winner(WarTable.inPlay); 
    System.out.println("winner is: "+ shortWinner);
    WarTable.collectWinnings(shortWinner);
  }

  private static boolean pickCard() {
    boolean action = true;
    while (true){
      String  string;
      boolean succes = false;
      Scanner scan = new Scanner(System.in);
      try {
        string = scan.nextLine();
        scan.close();
        if(string.contains("1") && !string.contains("2")){
          succes = true;
          action = true;
        }else if (!string.contains("1") && string.contains("2")){
          succes = true;
          action = false;
        }
        if(succes){
          System.out.println("accepted");
          System.out.println("choice accepted: " + string + "\n\n");
          break;
        }
        scan.close();
        System.out.println("please use \" 1 \" or \" 2 \" to select the first or second card");
      } catch (Exception e) {
        System.out.println("something went wrong");
        System.out.println(e);
        scan.nextLine();
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

  private static byte checkForWinner(){
  ArrayList <Byte> alive = new ArrayList<Byte>();
  for(byte playerID = 0; playerID < WarTable.NUMBER_OF_PLAYERS; playerID++){
    if(WarTable.isAlive(playerID)) alive.add(playerID);
  }
  if(alive.size() == 1)
    return alive.get(0);
  return (byte)-1;
}

  private static void win(byte winner){
    String reset = "\033[0m";

    System.out.print("\n\n" + "good news everyone" + "\n" + "we have a " + "\033[38;5;43m" + "WINNER" + reset +"\n");
    System.out.print("\033[38;5;93m" + "The winner is: \"" + winner + "!\"\n");
    System.out.print("\033[38;5;93m" + "congrats!" + reset);
    System.out.println("this is when the game ends, but do you want to play again?");
    NewGame();
  }

  private static void NewGame() {
    try (Scanner reply = new Scanner(System.in)) {
      if(reply.nextLine().contains("yes")){

        while(true){
          try {
            Scanner reply2 = new Scanner(System.in);
              main(reply2.nextByte());
              reply2.nextLine();
              reply2.close();
              
              break;
          } catch (Exception e) {
            System.out.println("please only use numbers");
          }
        }
      }
    }
  }
}