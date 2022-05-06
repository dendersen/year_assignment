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
    
    Draw.buttons(transfer);
  }


  /**
   * @param data the curent game data that is used to run the game
   */
  public static void theGame(CurrentData data){
    if(Table.getPlayer(data.playerID).IS_AI){
      performAction(data.playerID, data.action);
    }
    else{
      boolean action = Table.getPlayer(data.playerID).aiAction();
      performAction(data.playerID, action);
    }
  }

  private static void performAction(byte playerID, boolean action) {
    byte currentPlayer = playerID;
    if(action){
      BlackJackProcessing.hit(playerID, false);
      if(!BlackJackProcessing.isAlive(playerID)){
        currentPlayer++;
      }
    }else {
      currentPlayer++;
    }
    if(currentPlayer != playerID){
      if(currentPlayer >= Table.NUMBER_OF_PLAYERS) currentPlayer = 0;
    }
    boolean done = false;
    if(playerID == 0 && currentPlayer != playerID){
      done = true;
    }
    if(!done){
      CurrentData transfer = new CurrentData(currentPlayer); // impliment some dealer code
      Draw.buttons(transfer);
    } else {
      Draw.winner(BlackJackProcessing.winnerID());
    }
  }
}
