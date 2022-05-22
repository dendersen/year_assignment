package dk.mtdm.controller;


import dk.mtdm.backend.BlackJack.BlackJackProcessing;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;
import dk.mtdm.frontend.window;
import processing.core.PApplet;
public class BlackJackController {
  static Table table;
  static public window mySketch;
  static boolean winner = false;
  static boolean dealer = false;

  public BlackJackController(byte numberOfPlayers, byte numberOfSets, byte numberOfAI) {
    table = new Table((byte)(numberOfPlayers+numberOfAI), numberOfSets);
    table.TableSetup(numberOfAI);
  }

  public static void main (){

    String[] processingArgs = {"window"};
    mySketch = new window();
    
    PApplet.runSketch(processingArgs,mySketch);
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    startGame();
    
  }

  private static void startGame(){
    if(!Table.getPlayer((byte)1).IS_AI){
      draws((byte)1,false, true);
      System.out.println("player one not ai");
      }
    else {
      draws((byte)1, false, false);
      System.out.println("player one is ai");
    }
  }


  public static void draws(byte playerID, boolean dealer, boolean buttons){
    
    CurrentData transfer = new CurrentData(playerID);
    System.out.println("transfer loaded, player ID = " + playerID);
    transfer.dealer = dealer;
    System.out.println("is dealer = " + dealer);

    if(buttons){
      System.out.println("drawing buttons");
      window.buttons(transfer);
    }else{    
      window.hideButtons();
      window.setTransfer(transfer);
      System.out.println("no buttons, waiting 200ms before next action");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("ready for new action, please press ai button");
    }
  }

  /**
   * @param data the curent game data that is used to run the game
   */
  public static void theGame(CurrentData data){
    if(winner){
      mySketch.winner(BlackJackProcessing.winnerID());
      return;
    }

    if(dealer){
      dealer();
      return;
    }

    if(!Table.getPlayer(data.playerID).IS_AI){
      performAction(data.playerID, data.action);
    }
    else{
      boolean action = true;
      try{
      action = Table.getPlayer(data.playerID).aiAction();
      }catch(Exception e){
        System.out.println(e);
        e.printStackTrace();
      }
      performAction(data.playerID, action);
    }
  
  
  }

  private static void performAction(byte playerID, boolean action) {
    byte currentPlayer = playerID;
    System.out.println("performing action from player " + currentPlayer);

    if(action){ //do hit and check for death
      BlackJackProcessing.hit(playerID);
      if(!BlackJackProcessing.isAlive(playerID)){
        System.out.println("player dead");
        currentPlayer++;
        System.out.println("player shift to " + currentPlayer);
      }
    }else { // stand and go to next player
      currentPlayer++;
      System.out.println("player shift to " + currentPlayer);
    }
    if(currentPlayer != playerID){//starts dealer
      if(currentPlayer > Table.NUMBER_OF_PLAYERS-1){ 
        currentPlayer = 0;
        System.out.println("player shift to " + currentPlayer);
      }
    }
    
    if(playerID != 0){
      System.out.println("action processed, draw starting");
      draws(currentPlayer,false, !Table.getPlayer(currentPlayer).IS_AI);
    }else{
      System.out.println("dealer turn");
      dealer();
    }
  }

  public static void dealer(){
    mySketch.showDealer = true;
    System.out.println("dealer code here");
    CurrentData transfer = new CurrentData((byte)0);
    transfer.dealer = dealer;;
    boolean Action = false;
      action = Table.getPlayer(data.playerID).aiAction();
      }catch(Exception e){
        System.out.println(e);
        e.printStackTrace();
      transfer.action = action
    if (!transfer.action){
winner = true;
return();
}


window.setTransfer(transfer);

    draws((byte)0, true, false);
  }
}
