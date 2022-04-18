package dk.mtdm.api;

import dk.mtdm.backend.BlackJack.BlackJackProcessing;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class BlackJackCom {
static Table table;

  public BlackJackCom(byte numberOfPlayers) {
    table = new Table(numberOfPlayers, (byte) (1));
  }

  private static boolean running = true;
  
  public static void main (){
    setup();
    
    theGame();
  }

  private static void setup(){
    table.setup();
  }

  private static void theGame(){
    while(running){
      for (byte playerID = 1; playerID < Table.NUMBER_OF_PLAYERS; playerID++){
        while (true){
          boolean escape = !playerAction(playerID);
          if (escape)
          break;
        }
      }
      Draw.winner(BlackJackProcessing.winnerID());
    }
  }

  private static boolean playerAction (byte playerID){
    boolean contenue = Draw.buttons(Table.availableActions(playerID));
    if(contenue){
      BlackJackProcessing.hit(playerID, false);
      contenue = BlackJackProcessing.isAlive(playerID);
    }
    return (contenue);
  }

}
