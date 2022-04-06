package dk.mtdm.backend.BlackJack;

public class BlackJackProcessing {
  public static void hit (byte playerID,boolean debug){
    Table.getPlayer(playerID).addCard(Table.drawCards((byte) (1))[0]);
    if(debug)
    System.out.println(Table.getPlayer(playerID).getHand().get(Table.getPlayer(playerID).getHand().size()-1).getNumber());
  }

  public static String[] availablePlayerActions (byte playerValue){
    String[] actions = new String[2];
    
    if(playerValue > 21){
      actions [0] ="fold" ;
      actions [1] = "" ;
    }
    else {
      actions [0] = "hit";
      actions [1] = "stand";
    }
    if(playerValue <= 10){
      actions [1] = "stand (no chance of failure)";
    }
    return (actions);
  }
  
}