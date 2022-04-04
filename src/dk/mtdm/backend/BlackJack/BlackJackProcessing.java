package dk.mtdm.backend.BlackJack;

public class BlackJackProcessing {
  public static void hit (byte playerID,boolean debug){
    Table.getPlayer(playerID).addCard(Table.drawCards((byte) (1))[0]);
    if(debug)
    System.out.println(Table.getPlayer(playerID).getHand().get(Table.getPlayer(playerID).getHand().size()-1).getNumber());
  }
}