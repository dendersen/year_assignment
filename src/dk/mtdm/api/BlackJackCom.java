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
    
    while(running){
    for (byte i = 0; i < Table.NUMBER_OF_PLAYERS; i++){
      byte playerID = (byte) (i+1);
      while (true){
        boolean escape = !playerAction(playerID);
        break;
        }
      }
    }
  }

  private static void setup(){
    table.setup();
  }

  private static boolean playerAction (byte playerID){
    boolean action = Draw.buttons(Table.availableActions(playerID));
    if(action) BlackJackProcessing.hit(playerID, false);
Table.getPlayer((byte)(0)).getHand().get(playerID);
    return (action);
  }

}
