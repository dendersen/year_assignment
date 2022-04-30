package dk.mtdm;
import java.util.Scanner;

import dk.mtdm.api.BlackJackCom;
import dk.mtdm.backend.War.WarAPI;


public class Main {
//  public static Draw window;
  public static void main(String[] args) {
    choose();
  }

  private static void choose(){
    while (true) {
      boolean game = false;
      try (Scanner scan = new Scanner(System.in)) {
        String string = scan.nextLine();
        switch (string) {
          case "war":
          WarAPI.main((byte) 1);
          break;
          
          case "":
          BlackJack();
          System.out.println("game finished");
          game = true;
          break;
          
          default:
            System.out.println("this game is not supportet");
            System.out.println("let's play blackJack instead");
            BlackJack();
            game = true;
            break;
        }
      }
      if(game)
      break;
    }
  }

  private static void BlackJack(){
    new BlackJackCom((byte) 2);
    BlackJackCom.main();
  }
}
