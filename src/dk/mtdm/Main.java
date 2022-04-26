package dk.mtdm;
import dk.mtdm.api.BlackJackCom;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class Main {
  public static void main(String[] args) {
    Draw card = new Draw();
    Table table = new Table((byte) (3), (byte) (1));
    BlackJackCom.main();
  }
}
