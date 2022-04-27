package dk.mtdm;
import dk.mtdm.api.BlackJackCom;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class Main {
//  public static Draw window;
  public static void main(String[] args) {
    BlackJackCom Game = new BlackJackCom((byte) 2);
    Game.main();
//    Draw window = new Draw();
//    Table table = new Table((byte) (3), (byte) (1));
//    table.TableSetup();
//    Draw.player();
////    Draw.showDealer();
  }
}
