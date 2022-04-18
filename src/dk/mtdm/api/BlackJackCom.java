package dk.mtdm.api;

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
    
    
    while (running){
      for (byte i = 0; i < table.NUMBER_OF_PLAYERS; i++){

        playerAction((byte) (i+1));
      }
    }
  }

  private static void setup(){
    table.setup();
  }

  private static void playerAction (byte playerID){
    boolean action = Draw.buttons(Table.availableActions(playerID));
    if(action){
      Table.getPlayer(playerID).addCard(Table.drawCards((byte) 1)[0]);;
    }
  }

}
