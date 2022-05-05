package dk.mtdm;
import java.util.InputMismatchException;
import java.util.Scanner;

import dk.mtdm.backend.War.Warcontroller;
import dk.mtdm.controller.BlackJackCom;


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
        switch (string.toLowerCase()) {
          case "war":
          while (true){
            try{
                System.out.println("how many players will be in the game");
                // Scanner playerNumber = new Scanner(System.in);
                byte number = scan.nextByte();
                Warcontroller.main(number);
                break;
              
            } catch(InputMismatchException e){
              System.out.println(e);
              System.out.println("input mismatch");
              System.out.println("please only use whole numbers");
            }
          }
          break;
          
          case "":
          case "blackjack":
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
