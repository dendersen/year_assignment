package dk.mtdm.api;


import dk.mtdm.backend.BlackJack.BlackJackProcessing;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class BlackJackCom {
  static Table table;
  static Draw draw;

  public BlackJackCom(byte numberOfPlayers) {
    table = new Table(numberOfPlayers, (byte) (1));
  }

  public static void main (){
    setup();
    
    draw = new Draw();
    
    startGame();
  }

  private static void setup(){
    table.setup();
  }

  private static void startGame(){
    CurrentData transfer = new CurrentData((byte) 1);
    
    Draw.buttons(transfer);
  }


  /**
   * @param data the curent game data that is used to run the game
   */
  public static void theGame(CurrentData data){
    byte currentPlayer = data.playerID;
    if(data.action){
      BlackJackProcessing.hit(data.playerID, false);
      if(!BlackJackProcessing.isAlive(data.playerID)){
        currentPlayer++;
      }
    }else {
    currentPlayer++;
    }
    if(currentPlayer != data.playerID){
      if(currentPlayer >= Table.NUMBER_OF_PLAYERS)
      currentPlayer = 0;
    }
    boolean done = false;
    if(data.playerID == 0 && currentPlayer != data.playerID){
      done = true;
    }
    if(!done){
      CurrentData transfer = new CurrentData(currentPlayer);
      Draw.buttons(transfer);
    } else {
      Draw.winner(BlackJackProcessing.winnerID());
    }
  }
}
