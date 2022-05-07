package dk.mtdm;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.xml.sax.SAXException;

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
      System.out.println("what game shall we play today?");
      try (Scanner scan = new Scanner(System.in)) {
        String string = scan.nextLine();
        switch (string.toLowerCase()) {
          case "war":
          while (true){
            try{
                System.out.println("how many players will be in the game");
                // Scanner playerNumber = new Scanner(System.in);
                byte number = scan.nextByte();
                scan.nextLine();
                scan.close();
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
          while (true){
            try{
            System.out.println("how many humans will be in the game?");
            byte human = scan.nextByte();
            scan.nextLine();
            System.out.println("how many ai will be in the game?");
            byte ai = scan.nextByte();
            scan.nextLine();
            System.out.println("how many sets of playing cards will be in the game?");
            byte cards = scan.nextByte();
            scan.nextLine();
            BlackJack(human, ai, cards);
            break;
            } catch (Exception e){
              System.out.println("something went wrong");
              System.out.println(e);
              e.printStackTrace();
            }
          }
          game = true;
          break;
          
          default:
            System.out.println("this game is not supportet");
            System.out.println("let's play blackJack instead");
            while (true){
              try{
              System.out.println("how many humans will be in the game?");
              byte human = scan.nextByte();
              scan.nextLine();
              System.out.println("how many ai will be in the game?");
              byte ai = scan.nextByte();
              scan.nextLine();
              System.out.println("how many sets of playing cards will be in the game?");
              byte cards = scan.nextByte();
              scan.nextLine();
              BlackJack(human, ai, cards);
              break;
              } catch (Exception e){
                System.out.println("something went wrong");
                System.out.println(e);
                e.printStackTrace();
              }
            }
            game = true;
            break;
        }
      }
      if(game)
      break;
    }
    System.out.println("game finished");
  }

  private static void BlackJack(byte numberOfHumans, byte numberOfAI, byte numberOfCardSets){
    new BlackJackCom(numberOfHumans,numberOfCardSets, numberOfAI);
    BlackJackCom.main();
  }
}
