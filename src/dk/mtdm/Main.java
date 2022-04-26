package dk.mtdm;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class Main {
//  public static Draw window;
  public static void main(String[] args) {
    Draw window = new Draw();
    Table table = new Table((byte) (3), (byte) (1));
    table.setup();
  }
}
