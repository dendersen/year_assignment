package dk.mtdm.backend.War;

import java.util.Scanner;

import dk.mtdm.backend.BlackJack.CardObject;

public class WarAPI {
  public static void main() {
    WarTable table = new WarTable((byte) 2);
    while (true){
      for(byte j = 0; j < table.NUMBER_OF_PLAYERS; j++){
        CardObject[] cards = table.drawHand(j);
        System.out.println("what will you play?");
        System.out.println();
        for(byte i = 0; i < 2; i++){
          String name = cards[i].getNumberString(); 
          name = translate(name);
          System.out.println( name + "of " + cards[i].getSymbolString());
        }
        while (true){
          try (Scanner scan = new Scanner(System.in)) {
          
          }
        }
      }
    }
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