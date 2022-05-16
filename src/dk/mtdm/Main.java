package dk.mtdm;
import java.util.InputMismatchException;
import java.util.Scanner;

import dk.mtdm.backend.War.Warcontroller;
import dk.mtdm.controller.BlackJackController;
import dk.mtdm.frontend.window;
import processing.core.PApplet;


public class Main {
//  public static Draw window;
  public static void main(String[] args) {
    String[] processingArgs = {"window"};
    window mySketch = new window();
    PApplet.runSketch(processingArgs,mySketch);
//    choose();
  }

  private static void choose(){
    while (true) {
      boolean game = false;
      System.out.println("what game shall we play today?");
      try (Scanner scan = new Scanner(System.in)) {
        String string = scan.nextLine();
        switch (string.toLowerCase()) {
          case "war":
            startWar(scan);
          break;
          
          case "":
          case "blackjack":
            game = BlackJackStart(scan);
          break;
          
          default:
            System.out.println("this game is not supportet");
            System.out.println("let's play blackJack instead");
            game = BlackJackStart(scan);
          break;
        }
      }
      if(game)
      break;
    }
    System.out.println("game finished");
  }

  private static boolean BlackJackStart(Scanner scan) {
    boolean game;
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
      scan.close();
      break;
      } catch (Exception e){
        System.out.println("something went wrong");
        System.out.println(e);
        e.printStackTrace();
        scan.nextLine();
      }
    }
    game = true;
    return game;
  }

  private static void startWar(Scanner scan) {
    while (true){
      try{
          System.out.println("how many players will be in the game");
          byte number = scan.nextByte();
          scan.nextLine();
//          Warcontroller.main(number, scan);
          break;
        
      } catch(InputMismatchException e){
        System.out.println(e);
        System.out.println("input mismatch");
        System.out.println("please only use whole numbers");
        scan.nextLine();
      }
    }
  }

  private static void BlackJack(byte numberOfHumans, byte numberOfAI, byte numberOfCardSets){
    new BlackJackController(numberOfHumans,numberOfCardSets, numberOfAI);
    BlackJackController.main();
  }
}
