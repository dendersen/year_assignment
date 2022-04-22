package dk.mtdm.api;

import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class BlackJackCom {
  static Table table;


  public BlackJackCom(byte numberOfPlayers) {
    table = new Table(numberOfPlayers, (byte) (1));
  }

  public static void main (){
    setup();
    
    theGame((byte) 1);
  }

  private static void setup(){
    table.setup();
  }

  private static void theGame(byte currentPlayer){
    CurrentData transfer = new CurrentData(currentPlayer);
    
  }
}
