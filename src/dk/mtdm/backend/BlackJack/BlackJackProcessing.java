package dk.mtdm.backend.BlackJack;

public class BlackJackProcessing {
  public static void hit (byte playerID,boolean debug){
    Table.getPlayer(playerID).addCard(Table.drawCards((byte) (1))[0]);
    if(debug)
    System.out.println(Table.getPlayer(playerID).getHand().get(Table.getPlayer(playerID).getHand().size()-1).getNumber());
  }
  public static byte playerValue(byte playerID){
    byte total = 0;
    byte es = 0;
    for(byte index = 0; index < Table.getPlayer(playerID).getHand().size(); index++){
      if(Table.getPlayer(playerID).getHand().get(index).getNumber() == 1){
      total += 11;
      es++;
      }
      else total += Table.getPlayer(playerID).getHand().get(index).getNumber();
    }
    while (total >= 21){
      if (es > 0){
      total -= 10;
      es--;
    }else
      break;
    }
    return(total);
  }
  
  public static boolean isAlive(byte playerID){
    return(playerValue(playerID)<=21);
  }
  
  /**
   * @return index of the highest score
   */
  public static byte winnerID(){
    byte[] scores = new byte[Table.NUMBER_OF_PLAYERS];
    for(byte i = 0; i < Table.NUMBER_OF_PLAYERS; i++){
      scores[i] = playerValue(i);
      if(scores[i]>21)
      scores[i] = -1;
    }
    byte largestIndex = 0;
    for(byte i = 1; i < Table.NUMBER_OF_PLAYERS; i++){
      if (scores[i] > scores[largestIndex])
      largestIndex = i;
    }
    return (largestIndex);
  }
  
  
>>>>>>> 3f11dc495c2fd9e311fb74eca63489a94c2ac76e
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