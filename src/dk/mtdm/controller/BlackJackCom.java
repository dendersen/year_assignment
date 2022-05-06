package dk.mtdm.controller;


import dk.mtdm.backend.BlackJack.BlackJackProcessing;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class BlackJackCom {
  static Table table;
  static Draw draw;

  public BlackJackCom(byte numberOfPlayers, byte numberOfSets, byte numberOfAI) {
    table = new Table(numberOfPlayers, numberOfSets);
    table.TableSetup(numberOfAI);
    draw = new Draw();
    Draw.player();
  }

  public static void main (){
    startGame();
  }

  private static void startGame(){
    CurrentData transfer = new CurrentData((byte) 1);
    if(!Table.getPlayer(transfer.playerID).IS_AI){
      System.out.println("draw buttons");
      Draw.buttons(transfer);
      }
    else{
      System.out.println("don't draw buttons");
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
