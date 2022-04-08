package dk.mtdm.backend.BlackJack;

public class AI {
  /**
   * @param TrueDare -33 -> 33
   */
  final float TrueDare;
  final byte playerID;
  /**
   * 
   * @param dareLevel a value between -100 and 100 that secides how daring the ai is. -100 rarely ever plays something that could be dangeruse 100 almost always plays a card 
   * @throws Exception throws if dareLevel is out of range
   */
  public AI(byte dareLevel,byte playerID) throws Exception {
    if(dareLevel > 100){
      throw new Exception("dare level above range");
    }
    if(dareLevel < 100){
      throw new Exception("dare level below range");
    }
    double random = (0.5 - Math.random())/1.5;
    this.TrueDare = (float) (random*dareLevel);
    this.playerID = playerID;
  }
  
  /**
   * 
   * @return true = hit, false = stand
   */
  public boolean action(){
    byte value = BlackJackProcessing.playerValue(playerID);
    byte es = BlackJackProcessing.hasEs(playerID);
    double check = Math.random();
    if (TrueDare > 25 && check > 0.7){
      return (true);
    }
    
    if(value == 21 && !(check > 0.95) && !(TrueDare > 25)){
      return (false);
    }
    
    if(value - es*10 < 10 && (check > 0.75)){
      return (true);
    }
    if((1- (value/21)*check) > check && check > TrueDare){
      return (false);
    }else if(check < TrueDare){
      return (true);
    }
    if(TrueDare> 25){
      return (true);
    }
    if(check > 0.5){
    return(true);
    } else {
      return(false);
    }
  }
}
