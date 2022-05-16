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
    if(!Table.getPlayer((byte)1).IS_AI){
      draws((byte)1,false, true);
      }
    else
      draws((byte)1, false, false);
    }


  public static void draws(byte playerID, boolean dealer, boolean buttons){
    
    CurrentData transfer = new CurrentData(playerID);
    transfer.dealer = dealer;

    if(buttons)
      Draw.buttons(transfer);
    else{    
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
    if(action){ //do hit and check for death
      BlackJackProcessing.hit(playerID, false);
      if(!BlackJackProcessing.isAlive(playerID)){
        System.out.println("player dead");
        currentPlayer++;
      }
    }else { // stand and go to next player
      currentPlayer++;
    }
    if(currentPlayer != playerID){//starts dealer
      if(currentPlayer >= Table.NUMBER_OF_PLAYERS) currentPlayer = 0;
    }
    
    if(playerID != 0){
      draws(currentPlayer,false, !Table.getPlayer(currentPlayer).IS_AI);
    }else{
      dealer();
    }
  }

  public static void dealer(){
    
  }
}
