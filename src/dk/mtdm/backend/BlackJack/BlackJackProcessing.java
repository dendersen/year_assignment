package dk.mtdm.backend.BlackJack;

public class BlackJackProcessing {
  final static byte MAX_DEATH_VALUE = 21;
  final static byte ES_VALUE_SMALL = 1;
  final static byte ES_VALUE_BIG = 11;
  final static byte ES_CARD_NUMBER = 1;
  final static byte STANDARD_BIG_CARD_VALUE = 10;
  
  final static byte highestValue () {
    if(ES_VALUE_SMALL > STANDARD_BIG_CARD_VALUE){
      return ES_VALUE_BIG;
    }
    return STANDARD_BIG_CARD_VALUE;
  }

  public static byte[] getFinals(){
    return new byte[] {MAX_DEATH_VALUE, ES_VALUE_SMALL, ES_VALUE_BIG, ES_CARD_NUMBER, STANDARD_BIG_CARD_VALUE};
  }


  public static void hit (byte playerID){
    Table.getPlayer(playerID).addCard(Table.drawCards((byte) (1))[0]);
  }
  public static byte playerValue(byte playerID){
    byte total = 0;
    byte es = 0;
    for(byte index = 0; index < Table.getPlayer(playerID).getHand().size(); index++){
      byte currentNumber = Table.getPlayer(playerID).getHand().get(index).getNumber();
      if(currentNumber == ES_CARD_NUMBER){
      total += ES_VALUE_BIG;
      es++;
      }
      else if(currentNumber <= STANDARD_BIG_CARD_VALUE)
        total += currentNumber;
      else 
        total += STANDARD_BIG_CARD_VALUE;
    }
    while (total >= MAX_DEATH_VALUE){
      if (es > 0){
      total -= ES_VALUE_BIG - ES_VALUE_SMALL;
      es--;
    }else
      break;
    }
    return(total);
  }
  
  public static byte hasEs(byte playerID){
    byte es = 0;
    for(byte index = 0; index < Table.getPlayer(playerID).getHand().size(); index++){
      if(Table.getPlayer(playerID).getHand().get(index).getNumber() == ES_CARD_NUMBER){
      es++;
      }
    }
    return (es);
  }
  
  public static boolean isAlive(byte playerID){
    return(playerValue(playerID)<=MAX_DEATH_VALUE);
  }
  
  /**
   * @return index of the highest score
   */
  public static byte winnerID(){
    byte[] scores = new byte[Table.NUMBER_OF_PLAYERS];
    for(byte i = 0; i < Table.NUMBER_OF_PLAYERS; i++){
      scores[i] = playerValue(i);
      if(scores[i]>MAX_DEATH_VALUE)
      scores[i] = -1;
    }
    byte largestIndex = 0;
    for(byte i = 1; i < Table.NUMBER_OF_PLAYERS; i++){
      if (scores[i] > scores[largestIndex])
      largestIndex = i;
    }
    return (largestIndex);
  }
  
  public static boolean[] availablePlayerActions (byte playerValue){
    boolean[] actions = new boolean[3];
    
    if(playerValue > MAX_DEATH_VALUE){
      actions[0] = false;
      actions[1] = false;
      actions[2] = false;
    }
    else {
      actions[0] = true;
      actions[1] = true;
      actions[2] = false;
    }
    if(playerValue <= highestValue()){
      actions[2] = true;
    }
    return (actions);
  }
}