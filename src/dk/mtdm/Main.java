package dk.mtdm;
import dk.mtdm.frontend.Draw;

import java.awt.*;

public class Main {
  public static void main(String[] args) {
    final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //bruges til at lave en window til skærme stølsen
    Draw Draw = new Draw();
    Draw.initialize(dim.width, dim.height);
  }
}
