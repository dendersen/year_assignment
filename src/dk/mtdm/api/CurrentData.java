package dk.mtdm.api;

import dk.mtdm.backend.BlackJack.Table;

public class CurrentData {
  public byte playerID;
  public boolean[] AVAILABLE_ACTIONS;
  public boolean action;


  public CurrentData(byte PlayerID) {
    playerID = PlayerID;
    AVAILABLE_ACTIONS = Table.availableActions(playerID);
  }

  /**
   * 
   * @param Action true = hit, false = stand
   */
  public void setAction(boolean Action){
    action = Action;
  }
}