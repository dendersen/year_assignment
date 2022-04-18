package dk.mtdm;
import dk.mtdm.backend.BlackJack.Table;
import dk.mtdm.frontend.Draw;

public class Main {
  public static void main(String[] args) {
    Draw card = new Draw();
    Table table = new Table((byte) (2), (byte) (1));
    table.setup();
  }
}
