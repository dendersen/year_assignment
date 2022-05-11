package dk.mtdm.controller;


import dk.mtdm.backend.BlackJack.BlackJackProcessing;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class BlackJackController {
  static Table table;
  static Draw draw;

  public BlackJackController(byte numberOfPlayers, byte numberOfSets, byte numberOfAI) {
    table = new Table((byte)(numberOfPlayers+numberOfAI), numberOfSets);
    table.TableSetup(numberOfAI);
    draw = new Draw();
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main (){
    startGame();
  }

  private static void startGame(){
    CurrentData transfer = new CurrentData((byte) 1);
    if(!Table.getPlayer(transfer.playerID).IS_AI){
      System.out.println("draw buttons");
      Draw.playerDraw(Table.getPlayer(transfer.playerID), false);
      Draw.buttons(transfer);
      }
    else{
      System.out.println("don't draw buttons");
      if(transfer.playerID == 0){
      Draw.playerDraw(Table.getPlayer((byte) 0), true);
      }else{
      Draw.playerDraw(Table.getPlayer(transfer.playerID), false);
      }
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      theGame(transfer);
    }
  }


  /**
   * @param data the curent game data that is used to run the game
   */
  public static void theGame(CurrentData data){
    if(Table.getPlayer(data.playerID).IS_AI){
      performAction(data.playerID, data.action);
    }
    else{
      boolean action = true;
      try{
      action = Table.getPlayer(data.playerID).aiAction();
      }catch(Exception e){
        System.out.println(e);
      }
      performAction(data.playerID, action);
    }
  }

  private static void performAction(byte playerID, boolean action) {
    byte currentPlayer = playerID;
    if(action){ //do hit and check for death
      BlackJackProcessing.hit(playerID, false);
      if(!BlackJackProcessing.isAlive(playerID)){
        currentPlayer++;
      }
    }else { // stand and go to next player
      currentPlayer++;
    }
    if(currentPlayer != playerID){//starts dealer
      if(currentPlayer >= Table.NUMBER_OF_PLAYERS) currentPlayer = 0;
    }
    boolean done = false;
    if(playerID == 0 && currentPlayer != playerID){
      done = true;
    }
    if(!done && Table.getPlayer(currentPlayer).IS_AI){
      CurrentData transfer = new CurrentData(currentPlayer); // impliment some dealer code
      Draw.buttons(transfer);
    } else if(!Table.getPlayer(currentPlayer).IS_AI){
      Draw.winner(BlackJackProcessing.winnerID());
    }
  }
}
